# 1.事务

## 1.0 准备

### 表结构

![image-20220730093827039](images\image-20220730093827039.png)



### 时序图

![image-20220730100152648](images\image-20220730100152648.png)



## 1.1 事务

### 短连接

一条SQL 使用 一个Connection 对象

```java
 public boolean update(final String DML_SQL,final Object ... PARAM_AY) throws DataAccessException{
        Connection conn = JDBCUtil.getConnection();
        try {
            QueryRunner qr = new QueryRunner();
            return qr.update(conn,DML_SQL,PARAM_AY)>0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("执行DML[sql]:"+DML_SQL+",参数:"+ Arrays.toString(PARAM_AY)+"异常！") ;
        } finally {
            JDBCUtil.closeConnection(conn);
        }
}
```

### 长连接

一个业务方法 使用一个Connection 对象

JDBCUtil.java

```java
 /** 二级缓存 */
private static ThreadLocal<Connection> local = new ThreadLocal<>() ;

public static Connection getConnection(){
    try {
        //尝试从二级缓存中获得连接对象
        Connection conn = local.get();
        //二级缓存中没有连接
        if (conn==null){
            //从一级缓存中获得
            conn = dataSource.getConnection();
            //设置到 二级缓存中
            local.set(conn);
        }
        return conn ;
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("获得数据库连接异常！") ;
    }
}

public static  void closeConnection(Connection conn){
    try {
        if (conn!=null && !conn.isClosed()){
            conn.close();
            //从二级缓存中移除
            local.remove();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("关闭数据库连接异常！") ;
    }
}
```

Junit测试

```java
@Test
public void getConnection() {
    Connection conn1 = JDBCUtil.getConnection();
    Connection conn2 = JDBCUtil.getConnection();
    assertThat((conn1==conn2),is(true));
}
```

### 事务处理代码

```java
public interface TxManager {
    void beginTx() ;

    void commitTx() ;

    void rollbackTx() ;
}
```

```java
public class TxManagerImpl implements TxManager {
    @Override
    public void beginTx() {
        try {
            //设置为手动提交
            JDBCUtil.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commitTx() {
        try {
            //事务提交
            JDBCUtil.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void rollbackTx() {
        try {
            //事务回滚
            JDBCUtil.getConnection().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### 没有使用代理

```
1. 上层耦合下层的API
比如: Service业务处理 ==> TxManager[事务 数据库] ==> begin commit rollback
2. N个Service 类N个方法
没有service 方法都需要写  事务代码
动态代理:【实现】
```

JDBCTemplate[释放连接]

```java
// 删除掉 finally{ ... }
finally {
    JDBCUtil.closeConnection(conn);
}
```



Service的方法体中[添加事务代码调用]

```java
private TxManager txManager ;

@Override
public boolean save(Orders orders) throws ServiceException {
    try {
        //开始事务
        txManager.beginTx();
        .....
        //所有操作都没有出现异常  执行事务提交
        txManager.commitTx();
        return true ;
    } catch (Throwable  e) {
        e.printStackTrace();
        //事务回滚
        txManager.rollbackTx();
        throw new ServiceException(e.getMessage()) ;
    }finally {
        //释放数据库连接
        JDBCUtil.closeConnection(JDBCUtil.getConnection());
    }
}
```

## 3.3 动态代理

### 代理类

```java
public class TxProxy {
    private static final  TxManager txManager = BeanFactory.getBean(TxManager.class) ;
    
    public static  <T> T getProxy(Object targetObj){
        ClassLoader loader = targetObj.getClass().getClassLoader() ;
        Class<?>[] interfaces = targetObj.getClass().getInterfaces() ;
        InvocationHandler h = (proxy,method,args) -> {
            Object returnObj = null;
            try {
                txManager.beginTx();
                returnObj = method.invoke(targetObj, args);
                txManager.commitTx();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                txManager.rollbackTx(); 
                throw e.getTargetException() ;
            } finally {
                JDBCUtil.closeConnection(JDBCUtil.getConnection());
            }
            return returnObj ;
        };
        return (T) Proxy.newProxyInstance(loader,interfaces,h);
    }
}
```

### 工厂类

```java
 public static <T> T getBean(Class interfaceCls){
    //根据key 从map中获得
    Object targertObj =  beanMap.get(interfaceCls.getSimpleName());
    //事务代理 只针对 Service层
    if (interfaceCls.getSimpleName().endsWith("Service")){
        return TxProxy.getProxy(targertObj) ;
    }
    //其他层对象 直接返回 不进行代理
    return (T) targertObj;
}
```

### Service

零污染

```java
@Override
public boolean save(Orders orders) throws ServiceException {
    try {
        ....
        return true ;
    } catch (DAOException | RuntimeException e) {
        e.printStackTrace();
        throw new ServiceException(e.getMessage()) ;
    }
}
```
