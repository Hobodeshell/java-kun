# 1. JDBC

## 1.1 简介

![image-20220506202345258](images\image-20220506202345258.png)

## 1.2 工作原理

![image-20220506202452003](images\image-20220506202452003.png)

## 1.3 JDBC API

![image-20220506202537196](images\image-20220506202537196.png)

## 1.4 JDBC 驱动

![image-20220506202623525](images\image-20220506202623525.png)

![image-20220506202656291](images\image-20220506202656291.png)

## 1.5 Connection[会话]

![image-20220506203007872](images\image-20220506203007872.png)

### 随堂练习1

```
数据库名“scott”，用户名“root”，密码“root” 
使用纯Java方式连接该数据库
如果连接成功，输出“建立连接成功！”，否则输出“建立连接失败！” 
进行相关异常处理
```

## 1.6 Statement【执行SQL】

### Statement

用于执行静态 SQL 语句

![image-20220506204152891](images\image-20220506204152891.png)

![image-20220506204629191](images\image-20220506204629191.png)

![image-20220506204512610](images\image-20220506204512610.png)

### PreparedStatement

表示预编译的 SQL 语句的对象。

![image-20220506204254289](images\image-20220506204254289.png)

![image-20220506204400101](images\image-20220506204400101.png)

### CallableStatement

用于执行 SQL 存储过程的接口。

#### 存储过程定义

```sql
DELIMITER $$

CREATE PROCEDURE p_save_dept(v_deptno INT,v_dname VARCHAR(20),v_loc VARCHAR(20) ,OUT v_rs INT )
 BEGIN
   DECLARE cut INT(1) ;
   SELECT COUNT(0) INTO cut FROM dept WHERE deptno = v_deptno ;
   IF cut = 1 THEN 
     SET v_rs = -1 ; 
   END IF ;
   IF cut = 0 THEN 
     SET v_rs = 1 ;
     INSERT INTO dept (deptno,dname,loc) VALUES (v_deptno,v_dname,v_loc) ;
     COMMIT ;
   END IF ;
END  $$

DELIMITER ;
```

#### 数据库直接调用

```sql
CALL p_save_dept(11,'aa','NJ',@rs) ;
SELECT @rs ;
```

#### JDBC调用

```java
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
CallableStatement cs = conn.prepareCall("{call p_save_dept(?,?,?,?)}");
cs.setInt(1,12);
cs.setString(2,"dev");
cs.setString(3,"NJ");
cs.registerOutParameter(4, JDBCType.INTEGER);
cs.execute() ;
int out = cs.getInt(4);
System.out.println(out);
cs.close();
conn.close();
```



## 1.7 ResultSet 【查询结果集】

![image-20220506204212423](images\image-20220506204212423.png)

# 2. DAO

## 2.1 什么是DAO

![image-20220506210707068](images\image-20220506210707068.png)

## 2.2 DAO作用

![image-20220506210831671](images\image-20220506210831671.png)

## 2.3 组成部分

![image-20220506210903737](images\image-20220506210903737.png)

## 2.4 示例

![image-20220506210953246](images\image-20220506210953246.png)

# 3.Service

## 3.1 了解Service

![image-20220506211105546](images\image-20220506211105546.png)