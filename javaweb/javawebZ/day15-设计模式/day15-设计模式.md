# 	设计模式

## 一、简介

### 1. 概念

​	设计模式（Design Pattern）是一套被反复使用、多数人知晓的、经过分类的、代码设计经验的总结。

​	使用设计模式的目的：为了代码可重用性、让代码更容易被他人理解、保证代码可靠性。

### 2. 设计原则

​	面向对象的基本原则：高低原则

- 高内聚

  模块内部要高度内聚，每一个类完成特定的独立的功能

- 低耦合

  模块之间要降低耦合度，类之间的依赖应该要尽量低，当一个类发生改变，另一个类不受影响或者受到最小影响

​      设计模式的六大原则：

1. 单一职责原则

   一个类只负责一个职责

2. 开闭原则

   对扩展开放，对修改关闭

   在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果（需要使用接口和抽象类）

3. 里氏替换原则

   所有引用基类（父类）的地方必须能透明地使用其子类的对象

   任何基类出现的地方，都可以换成子类，程序还可以正常运行

4. 依赖倒置原则

   细节应当依赖于抽象，抽象不应该依赖于细节，也就是“面向接口编程” 或者说 “面向抽象编程

   要尽可能使用接口或抽象类，如变量的表面类型尽量是接口或者抽象类

5. 接口隔离原则

   使用多个隔离的接口，而不使用单一的总接口

   每一个接口应该承担一种相对独立的角色，不干不该干的事

6. 迪米特法则，也称最少知道原则

   一个类对自己依赖的类知道的越少越好，尽量减少对象之间的交互

### 3. 23种设计模式

​	分为三类：

- 创建型模式

  **工厂模式**、抽象工厂模式、**单例模式**、建造者模式、原型模式

- 结构型模式

  适配器模式、装饰器模式、**代理模式**、外观模式、桥接模式、组合模式、享元模式

- 行为型模式

  策略模式、**模板模式**、观察者模式、迭代子模式、责任链模式、命令模式、备忘录模式、状态模式、访问者模式、中介者模式、解释器模式

## 二、单例模式

### 1. 简介

​	Singleton 单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。

​	场景：单例模式只应在有真正的“单一实例”的需求时才可使用。

### 2. 实现方式

​	两种实现方式：

- 饿汉式

  在类加载时创建实例，不管后期是否使用都会创建

  线程安全

  ```java
  public class Singleton {
      private static Singleton instance = new Singleton() ;
      private Singleton(){
          System.out.println("=====只被执行一次的代码=====");
      }
  
      public static Singleton getInstance() {
          return instance;
      }
  }
  ```

  

- 懒汉式

  在需要时才创建实例，延迟加载

  线程不安全
  
  单线程模式：
  
  ```java
  public class Singleton {
      private static Singleton instance  ;
      private Singleton(){
          System.out.println("=====只被执行一次的代码=====");
      }
  
      public static Singleton getInstance() {
          if (instance==null){
              instance = new Singleton() ;
          }
          return instance;
      }
  }
  ```
  
  多线程模式【锁】:
  
  ```java
  public class Singleton03 {
      private static Singleton03 instance  ;
  
      private Singleton03() {
          System.out.println(" 懒汉式 单例=====多线程环境=======只被执行一次");
      }
  
      public static Singleton03 getInstance() {
          //双重检查
          if (instance==null) {
              synchronized (Singleton03.class) {
                  if (instance==null) {
                      //延迟加载模式
                      instance = new Singleton03();
                  }
              }
          }
          return instance;
      }
  
      /*public static synchronized Singleton03 getInstance() {
          if (instance==null) {
              //延迟加载模式
              instance = new Singleton03() ;
          }
          return instance;
      }*/
  }
  ```
  
  ```java
  public class Singleton03Test {
      @Test
      public void test(){
          for (int i = 0; i < 50; i++) {
              new Thread(Singleton03::getInstance).start();
          }
      }
  }
  ```
  
  
  
  反射破坏：
  
  ```java
  public class Singleton04 {
      private static Singleton04 instance  ;
  
      private Singleton04() {
          if (instance!=null){
              throw new RuntimeException("你小子太狠啦,反射调用干嘛？？？") ;
          }
          System.out.println(" 懒汉式 单例=====反射=======只被执行一次");
      }
  
      public static Singleton04 getInstance() {
          if (instance==null) {
              //延迟加载模式
              instance = new Singleton04() ;
          }
          return instance;
      }
  }
  ```
  
  ```java
  public class Singleton04Test {
      @Test
      public void test() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
          Singleton04 instance1 = Singleton04.getInstance();
          Class<? extends Singleton04> cls = instance1.getClass();
          Constructor<? extends Singleton04> constructor = cls.getDeclaredConstructor() ;
          constructor.setAccessible(true);
          Singleton04 i2 = constructor.newInstance() ;
          Singleton04 i3 = constructor.newInstance() ;
          Singleton04 i4 = constructor.newInstance() ;
      }
  }
  ```
  
  
  
  
  
  更多实现请求参考：
  
  防止反射破坏，对象序列化破坏等等
  
  https://blog.csdn.net/weixin_52574640/article/details/127972850

### 3.实战 JDBCUtil

数据库配置信息

jdbc.properties

```properties
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/java180?useUnicode=true&characterEncoding=UTF-8
username=root
password=root
```

单例获得数据库连接关闭数据库连接工具类

```java
public class JDBCUtil {
    private static JDBCUtil instance = new JDBCUtil();
    private static DataSource dataSource ;
    private JDBCUtil() {
        //获得 配置文件
        InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        // 创建 properties对象
        Properties prop = new Properties();
        // 读取 加载目标文件
        try {
            prop.load(is) ;
            // 创建数据源 连接池
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取文件失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("创建连接池失败");
        }


    }

    public Connection getConnection(){
        System.out.println("获得数据库连接对象");
        try {
            return dataSource.getConnection() ;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获得数据库连接失败");
            return  null ;
        }
    }

    public void close(ResultSet rs, PreparedStatement pstm, Connection conn){

        try {
            if (rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstm!=null) {
                pstm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn!=null ) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("释放数据库连接对象");
    }

    public static JDBCUtil getInstance() {
        return instance;
    }
}
```



## 三、模板模式

### 1. 简介

​	Template 定义一个抽象类，将部分逻辑以具体方法的形式实现，然后声明一些抽象方法来迫使子类实现剩余的逻辑。

​	不同的子类可以以不同的方式实现这些抽象方法，从而对剩余的逻辑有不同的实现。

​	先制定一个顶级逻辑框架，而将逻辑的细节留给具体的子类去实现。 

​	场景：当方法中有多行代码不确定时（可能只有当使用到该方法时才能确定），可以使用模板模式

### 2. 实现方式

####  需求

![image-20220722110257666](images\image-20220722110257666.png)



##### 没有模板方法

![image-20220722110518268](images\image-20220722110518268.png)

CharDisplay.java

```java
public class CharDisplay {
    private char ch ;

    public CharDisplay(char ch) {
        this.ch = ch;
    }

    public void open(){
        System.out.print("<<");
    }

    public void print(){
        System.out.print(this.ch);
    }

    public void close(){
        System.out.print(">>");
    }

    public void display(){
        this.open();
        for (int i = 0; i < 5; i++) {
            this.print();
        }
        this.close();
    }
}
```

StringDisplay.java

```java
public class StringDisplay {
    private String str ;

    public StringDisplay(String str) {
        this.str = str;
    }

    public void open(){
        System.out.println("--------------------------------");
    }

    public void print(){
        System.out.println(this.str);
    }

    public void close(){
        System.out.println("--------------------------------");
    }

    public void display(){
        this.open();
        for (int i = 0; i < 5; i++) {
            this.print();
        }
        this.close();
    }
}
```



JUnit

```java
@Test
public void display() {
    CharDisplay charDisplay = new CharDisplay('A');
    charDisplay.display();
}
```

```java
@Test
public void display() {
    StringDisplay stringDisplay = new StringDisplay("Hello world !!!");
    stringDisplay.display();
}
```



##### 使用模板方法

![image-20220722110837097](images\image-20220722110837097.png)

#### 模板方法

```java
public abstract class BaseDisplay {
   public abstract void open();
   public abstract void close();
   public abstract void print();
   public final void display() {
       //调用开始
      open();
       //循环 5 次
      for (int i = 0; i < 5; i++) {
         print();
      }
       //调用结束
      close();
   }
}
```

##### 子类重写

```java
public class CharDisplay extends BaseDisplay {
   private char ch ;
   /** @param ch */
   public CharDisplay(char ch) {
      this.ch = ch ;
   }
   
   public void open() {
      System.out.print("<<");
   }
   
   public void close() {
      System.out.print(">>");
   }
   
   public void print() {
      System.out.print(ch);
   }

}
```

```java
public class StringDisplay extends BaseDisplay {
   private String str ;
   /** @param str */
   public StringDisplay(String str) {
     this.str = str ;
   }
   
   public void open() {
      System.out.println("------------------------");
   }
   
   public void close() {
      System.out.println("------------------------");
   }
   
   public void print() {
      System.out.println(str);
   }

}
```

##### Main

```java
public class Main {
   public static void main(String[] args) {
      BaseDisplay d1 = new CharDisplay('A') ;
      d1.display();
      System.out.println();
      BaseDisplay d2 = new StringDisplay("hello world !!!") ;
      d2.display();
   }

}
```

### 3.实战  JDBC改写

#### BaseDAOImpl

```java
public abstract class BaseDAOImpl<T,PK> {
    private JDBCTemplate jdbcTemplate = new JDBCTemplate() ;
    private Class<?> pojoClass ;

    public BaseDAOImpl() {
        //动态获得子类的 泛型
        Type genType = this.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        this.pojoClass =  (Class)params[0];
    }

    //获得 新增一条记录的SQL
    protected abstract String insertSQL() ;
    //获得 删除一条记录的SQL 【批处理】
    protected abstract String deleteSQL() ;
    //获得 修改一条记录的SQL
    protected abstract String updateSQL() ;
    //获得  根据id 查询的一条记录SQL
    protected abstract String selectByIdSQL() ;
    //获得 条件查询 返回多条记录SQL
    protected abstract String selectAllSQL();

    public final List<T> selectAll() {
        String sql = this.selectAllSQL() ;
        Object[] paramAy = {} ;
        return jdbcTemplate.queryList(sql,pojoClass,paramAy);
    }


    public final T selectById(PK id) {
        String sql = this.replaceParam(this.selectByIdSQL()) ;
        Object[] paramAy = {id} ;
        return (T) jdbcTemplate.queryObject(sql,pojoClass,paramAy);
    }


    public final boolean insert(T pojo) {
        // insert into t_student (sid, sname, age, sex) values (#{sid},#{sname},#{age},#{sex})
        // insert into t_student (sid, sname, age, sex) values (?,?,?,?)
        String sql = this.replaceParam(this.insertSQL());
        //#{sid},#{sname},#{age},#{sex} => {pojo.getSid(),pojo.getSname(),pojo.getAge(),pojo.getSex()}
        Object[] paramAy = this.getParamAy(this.insertSQL(),pojo);
        return jdbcTemplate.update(sql,paramAy);
    }


    public final boolean update(T pojo) {
        String sql = this.replaceParam(this.updateSQL());
        Object[] paramAy = this.getParamAy(this.updateSQL(),pojo);
        return jdbcTemplate.update(sql,paramAy);
    }


    public final boolean delete(String ... idAy) {
        //批处理
        String sql = this.replaceParam(this.deleteSQL()) ;
        Object[][] paramAy = new Object[idAy.length][1] ;
        for (int i = 0; i < idAy.length; i++) {
            paramAy[i][0] = idAy[i] ;
        }
        return jdbcTemplate.updateBatch(sql,paramAy);
    }

    /**
     * 把 子类的SQL 中 #{xx} 替换成？
     * @param sql
     * @return
     */
    private String replaceParam(String sql){
        return sql.replaceAll("#\\{[a-zA-Z0-9_.]+}", "?");
    }

    /**
     * 把子类的SQL #{xxx} 转换 Object[]中的具体的值
     * @param sql
     * @param obj
     * @return
     */
    private Object[] getParamAy(String sql,Object obj){
        //参数值  占位符的值
        List<Object> paramList = new ArrayList<>() ;
        //使用 字符串进行分割   #{
        String[] rs2 = sql.split("#\\{");
        // 跳过 第一个元素   映射 截取 开始到}【不包括】  去空格  收集  List    [sid,sname,age,sex]
        List<String> list = Stream.of(rs2).skip(1).map(s -> s.substring(0, s.indexOf("}")).trim()).collect(Collectors.toList());
        //循环 属性列名 集合
        list.forEach(propName->{
            try {
                //调用 apache 反射jar  实现 根据属性名 直接获得属性值
                Field declaredField = obj.getClass().getDeclaredField(propName);
                declaredField.setAccessible(true);
                Object propValue = declaredField.get(obj);
                //属性值 添加到 集合中
                paramList.add(propValue) ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 对象数组
        return paramList.toArray() ;
    }
}
```



#### StuDAOImpl

```java
public class StudentDAOImpl extends BaseDAOImpl<Student,String> implements StudentDAO {

    @Override
    protected String insertSQL() {
        return "insert into t_student (sid, sname, age, sex) values (#{sid},#{sname},#{age},#{sex})";
    }

    @Override
    protected String deleteSQL() {
        return "DELETE FROM t_student WHERE sid = #{sid}";
    }

    @Override
    protected String updateSQL() {
        return "UPDATE\n" +
                "  `t_student`\n" +
                "SET\n" +
                "  `sname` = #{sname},\n" +
                "  `age` = #{age},\n" +
                "  `sex` = #{sex}\n" +
                "WHERE `sid` = #{sid}" ;
    }

    @Override
    protected String selectByIdSQL() {
        return "SELECT\n" +
                "  `sid`,\n" +
                "  `sname`,\n" +
                "  `age`,\n" +
                "  `sex`\n" +
                "FROM\n" +
                "  `t_student` where sid = #{sid}";
    }

    @Override
    protected String selectAllSQL() {
        return "SELECT\n" +
                "  `sid`,\n" +
                "  `sname`,\n" +
                "  `age`,\n" +
                "  `sex`\n" +
                "FROM\n" +
                "  `t_student`";
    }
}
```

## 四、工厂模式

### 1. 简介

​	Factory 定义一个工厂类，对实现同一个接口的一组类进行实例化对象操作

### 2. 实现方式

#### 准备代码

##### DAO

```java
public interface UserDAO {
    boolean delete(String id) ;
}
```

```java
public class UserDAOImpl implements UserDAO {
    @Override
    public boolean delete(String id) {
        System.out.println("============UserDAOImpl===============");
        return false;
    }
}
```

##### Service

```java
public interface UserService {
    boolean remove(String id) ;
}
```

```java
public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl() ;
    @Override
    public boolean remove(String id) {
        System.out.println("=========UserServiceImpl======begin=====");
        boolean delRs = userDAO.delete(id);
        System.out.println("=========UserServiceImpl======end=====");
        return delRs;
    }
}
```

##### Junit

```java
public class UserServiceTest {
    private UserService userService = new UserServiceImpl() ;
    @Test
    public void remove() {
        userService.remove(null) ;
    }
}
```



#### IOC 控制反转

```java
1. [ 主动new-->耦合实现类 ] 
    UserService userService = new UserServiceImpl();
2. 创建对象 交给工厂：【工厂new 实现类  装配】
    public class BeanFactory {
        public static UserService getBean(){
            return new UserServiceImpl1() ;
        }
    }
```

##### 简单工厂

```java
public class BeanFactory {
    public static UserService getUserServiceInstance(){
        return new UserServiceImpl() ;
    }

    public static UserDAO getUserDAOInstance(){
        return new UserDAOImpl() ;
    }
}
```

公共代码合并

```java
public class BeanFactory {
    public static Object getInstance(Class<?> interfaceCls){
        if (interfaceCls == UserDAO.class){
            return new UserDAOImpl() ;
        }
        if (interfaceCls == UserService.class) {
            return new UserServiceImpl();
        }
        return null ;
    }
}
```



### 抽象工厂【properties】

beans.properties

```properties
UserDAO=com.wanho.java168.service.dao.impl.UserDAOImpl
UserService=com.wanho.java168.service.impl.UserServiceImpl
```

```java
public abstract class BeanFactory {
    private static Map<String,Object> beanMap = new HashMap<>() ;
    private BeanFactory(){}

    static {
        InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("beans.properties") ;
        try(BufferedReader br =new BufferedReader(new InputStreamReader(is))) {
            String data = null ;
            while((data=br.readLine())!=null){
                String[] split = data.split("=");
                //userDAO  userService
                String key = split[0] ;
                // 类的完整名称
                String cls = split[1] ;
                // 通过类的完整名称 获得 Class对象
                Class<?> c = Class.forName(cls);
                // 通过反射调用 默认无参构造 new
                Object obj = c.newInstance();
                //存放在map中
                beanMap.put(key,obj) ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String key){
        //根据key 从map中获得
        return beanMap.get(key) ;
    }
}
```

调用：

```java
private UserDAO userDAO = (UserDAO) BeanFactory.getBean("UserDAO");
private UserService userService = (UserService) BeanFactory.getBean("UserService");
```



改造：

```java
获得 bean实例： 
 1. "xxx" 单词大小写  写错   
 2. 类型强制问题
 
  public static <T> T getBean(Class interfaceCls){
        //根据key 从map中获得
        return (T) beanMap.get(interfaceCls.getSimpleName());
  }
 //注意： beans.properties   key [接口的简单名称]  value [实现类的完整名称]
 //约定大于配置
private UserDAO userDAO =  BeanFactory.getBean(UserDAO.class);
private UserService userService = BeanFactory.getBean(UserService.class);
```

### 抽象工厂【xml】

beans.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans>
    <bean id="UserDAO" class="com.wanho.java168.service.dao.impl.UserDAOImpl"/>
    <bean id="UserService" class="com.wanho.java168.service.impl.UserServiceImpl"/>
</beans>
```

BeanFactory

```java
static {
        InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml") ;
        try{
            //1.dom4j的解析器
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(is);
            List<Element> beanEltList = doc.selectNodes("//beans/bean");
            for (Element beanElt : beanEltList) {
                String id = beanElt.attributeValue("id");
                String clz = beanElt.attributeValue("class");
                // 通过类的完整名称 获得 Class对象
                Class<?> c = Class.forName(clz);
                // 通过反射调用 默认无参构造 new
                Object obj = c.newInstance();
                //存放在map中
                beanMap.put(id,obj) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```

## 1.2 DI 依赖注入

### xml版本

注意：DI 必须依赖 IOC 【该类的属性交给工厂自动赋值  ========必须 该类交给工厂new】

```java
public class UserServiceImpl implements UserService {
	//该属性 依赖工厂进行注入  【赋值】
	private UserDAO userDAO ;
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans>
    <bean id="UserService" class="com.wanho.java178.service.impl.UserServiceImpl">
        <property name="userDAO" ref="UserDAO"/>
    </bean>

    <bean id="UserDAO" class="com.wanho.java178.dao.impl.UserDAOImpl"/>
</beans>
```

```java
public class BeanFactory {
    private static Map<String,Object> beanMap = new HashMap<>() ;

    // JVM 加载当前 class 文件 即执行
    static {
        try {
            //输入流
            InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml") ;
            SAXReader reader = new SAXReader();
            Document doc = reader.read(is);
            //读取文件
            List<Element> beanEltList = doc.selectNodes("//beans/bean") ;
            // IOC [控制反转] 循环 数据  工厂 帮我们 new  Bean  .newInstance();
            for (Element beanElt : beanEltList) {
                //接口的简单名称
                String interfaceSimpleName = beanElt.attributeValue("id") ;
                //实现类的完整名称
                String implClsName = beanElt.attributeValue("class") ;
                //获得实现类 的Class对象
                Class<?> cls = Class.forName(implClsName);
                //通过反射 调用实现类的默认无参构造方法 进行实例化 new
                Object instance = cls.newInstance();
                //存放在 map中
                beanMap.put(interfaceSimpleName,instance) ;
            }

            // DI [依赖注入]
            for (Element beanElt : beanEltList){
                //当前bean 标签中  <property> 集合
                List<Element> propertyEltList = beanElt.selectNodes("./property");
                //没有 property
                if (propertyEltList==null || propertyEltList.isEmpty()){
                    //无需 做 DI 属性注入
                    continue;
                }
                //当前bean的实例
                Object beanObj = beanMap.get(beanElt.attributeValue("id"));
                for (Element propertyElt : propertyEltList) {
                    //属性名
                    String name = propertyElt.attributeValue("name");
                    //属性  引用 bean的id值
                    String ref = propertyElt.attributeValue("ref");
                    //通过 bean的id  获得 属性值
                    Object instance = beanMap.get(ref);
                    //属性对象
                    Field declaredField = beanObj.getClass().getDeclaredField(name);
                    declaredField.setAccessible(true);
                    //属性赋值
                    declaredField.set(beanObj,instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object getInstance(Class<?> interfaceCls){
        return beanMap.get(interfaceCls.getSimpleName()) ;
    }
}
```

### 注解版本

自定义注解类：

```
IOC: 控制反转  书写位置 类定义

@Controller  控制层Bean

@Service     业务层Bean

@Repository  数据访问层Bean

@Component   工具类Bean
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Repository {
}
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
}
```

```
DI: 依赖注入   书写位置 属性

@Autowired  
```

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {
}
```

#### bean

util

```java
@Component
public class JDBCTemplate {
    public boolean update(){
        System.out.println("========JDBCTemplate======删除开始=====");
        System.out.println("========JDBCTemplate======删除结束=====");
        return true ;
    }
}
```

dao

```java
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private JDBCTemplate jdbcTemplate ;

    @Override
    public boolean delete(String id) {
        System.out.println("========UserDAO======删除开始=====");
        boolean update = jdbcTemplate.update();
        System.out.println("========UserDAO======删除结束=====");
        return update;
    }
}
```

service

```java
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO ;

    @Override
    public boolean remove(String id) {
        System.out.println("========UserService======删除开始=====");
        boolean delete = userDAO.delete(id);
        System.out.println("========UserService======删除结束=====");
        return delete;
    }
}
```

controller

```java
@Controller
public class UserController {
    @Autowired
    private UserService userService ;

    public void delete(){
        System.out.println("========UserController======删除开始=====");
        boolean remove = userService.remove(null);
        System.out.println("========UserController======删除结束=====");
    }
}
```

junit

```java
public class UserControllerTest {
    private UserController userController = BeanFactory.getBean(UserController.class) ;
    @Test
    public void delete() {
        userController.delete();
    }
}
```

#### factory

```java
public abstract class BeanFactory {
    private static Map<String,Object> beanMap = new HashMap<>() ;

    /**
     * 不能被继承
     */
    private BeanFactory(){
        
    }
    // JVM 加载当前 class 文件 即执行
    static {
        try {
            //反射工具包，指明扫描路径
            //Reflections reflections = new Reflections("com.wanho.java178");
            Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("com.wanho.java178").addScanners(new FieldAnnotationsScanner()));
            //获取  IOC 需要 工厂  帮new的java类： 注意 该类必须是可以new 实现类 默认无参构造
            Set<Class<?>> componentClassList = reflections.getTypesAnnotatedWith(Component.class);
            Set<Class<?>> controllerClassList = reflections.getTypesAnnotatedWith(Controller.class);
            Set<Class<?>> serviceClassList = reflections.getTypesAnnotatedWith(Service.class);
            Set<Class<?>> repositoryClassList = reflections.getTypesAnnotatedWith(Repository.class);

            //统一添加一个 ioc集合中
            Set<Class<?>> iocClassSet = new HashSet<>() ;
            iocClassSet.addAll(componentClassList) ;
            iocClassSet.addAll(controllerClassList) ;
            iocClassSet.addAll(serviceClassList) ;
            iocClassSet.addAll(repositoryClassList) ;
            // IOC [控制反转] 循环 数据  工厂 帮我们 new  Bean  .newInstance();
            for (Class<?> beanCls : iocClassSet) {
                //当前实现类的 接口数组
                Class<?>[] interfaceAy = beanCls.getInterfaces();
                //没有接口 比如: JDBCTemplate UserController
                //接口的简单名称  map的key  存放 该类的简单名称
                String interfaceSimpleName = beanCls.getSimpleName() ;
                // 当前类有实现接口 比如： DAOImpl  ServiceImpl
                if (interfaceAy!=null && interfaceAy.length>0){
                    interfaceSimpleName = interfaceAy[0].getSimpleName() ;
                }
                //通过反射 调用实现类的默认无参构造方法 进行实例化 new
                Object instance = beanCls.newInstance();
                //存放在 map中
                beanMap.put(interfaceSimpleName,instance) ;
            }

            // DI [依赖注入]
            // 获取注解对应的字段
            Set<Field> autowiredFieldSet = reflections.getFieldsAnnotatedWith(Autowired.class);
            for (Field field : autowiredFieldSet) {
                //当前声明的类
                Class<?> declaringClass = field.getDeclaringClass();
                //根据当前类 获得 当前类的实例
                Class<?>[] interfaceAy = declaringClass.getInterfaces();
                if (interfaceAy!=null && interfaceAy.length>0){
                    declaringClass = interfaceAy[0] ;
                }
                Object obj = beanMap.get(declaringClass.getSimpleName()) ;
                //当前属性类型
                Class<?> type = field.getType();
                //根据 属性类型 进行自动到 map中 找到 该类型的属性值
                Object instance = beanMap.get(type.getSimpleName());
                //进行属性赋值  设置可被访问
                field.setAccessible(true);
                //进行 自动赋值
                field.set(obj,instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T getBean(Class<?> interfaceCls){
        return (T) beanMap.get(interfaceCls.getSimpleName());
    }
}
```

## 五、代理模式

### 1. 简介

​	Proxy 为其他对象提供一种代理，以控制对这个对象的访问，起到**中介**的作用

​	被代理的对象称为目标对象，代替目标对象的对象称为代理对象，通过代理对象访问目标对象

​	作用：可以扩展目标对象的功能，增强额外的操作，同时不侵入原代码

​	代理的三要素：

- 共同接口（被代理接口）
- 目标对象（target）
- 代理对象（proxy）

### 2. 实现方式

​	两种：静态代理、动态代理

#### 2.1 静态代理

​	代理类由程序员创建或工具生成

​	所谓静态就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了

​	缺点：代理对象需要和目标对象实现相同的接口，所以会有很多代理类，如果接口增加方法，目标对象与代理对象都要维护		

```java
public class StaticProxy implements HelloService {
    private HelloService targerObj ;

    public StaticProxy(HelloService targerObj){
        this.targerObj = targerObj ;
    }
    @Override
    public void sayHello() {
        System.out.println("==========前置增强");
        targerObj.sayHello();
        System.out.println("==========后置增强");
    }
}
```

```java
public class HelloServiceTest {
    private HelloService staticHelloServiceProxy = new StaticProxy(new HelloServiceImpl()) ;
    @Test
    public void sayHello() {
        staticHelloServiceProxy.sayHello();
    }
}
```

#### 2.2 动态代理

​	代理类是在程序运行期间由JVM根据反射等机制动态生成的，自动生成代理类和代理对象

​	所谓动态是指在程序运行前不存在代理类的字节码文件，代理类和委托类的关系是在程序运行时确定

​	动态代理的实现：

- jdk技术：只适用于实现了接口的类，使用`java.lang.reflect.Proxy`
- cglib技术：可用于没有实现任何接口的类，需要使用第三方jar包`CGLIB`（通过继承实现的，让代理类继承目标类）

​        注：如果一个类没有实现任何接口，并且被final修饰，那么，这个类无法创建代理对象



##### 面向接口JDK

目标对象：必须实现接口，被增强的方法必须在接口中定义？ [has-a]

```java
package com.wanho.java168.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy {
    public static <T> T getProxy(Object targetObj){
        //param0  jvm 类加载器
         ClassLoader loader = targetObj.getClass().getClassLoader() ;
         //param1 目标对象实现的接口数组
         Class<?>[] interfaces = targetObj.getClass().getInterfaces() ;
         //param2 增强处理类
         InvocationHandler h = new InvocationHandler() {
             /**
              *
              * @param proxy  代理对象
              * @param method  被代理目标 方法
              * @param args   方法的参数列表
              * @return
              * @throws Throwable
              */
             @Override
             public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                 System.out.println("========JDKProxy begin===========");
                 //反射 方法调用
                Object returnObj =  method.invoke(targetObj,args);
                 System.out.println("========JDKProxy end===========");
                 return returnObj;
             }
         } ;
        return (T) Proxy.newProxyInstance(loader,interfaces,h);
    }
}
```



```java
 private HelloService helloServiceProxy = JDKProxy.getProxy(new HelloServiceImpl()) ;
```



##### 面向父类CGlib

目标对象：目标类创建子类：目标类必须能创建子类，被增强的方法必须可以被重写？[is-a]

目标类：

```java
public /*final*/ class UserService {
    /*private UserService(){

    }*/
    /*public UserService(int k){

    }*/
    public /*final*/ /*static*/ void delete(String sid){
        System.out.println("========UserService====delete===");
    }
}
```

代理类:

```java
public class CglibProxy {
    public static <T> T getProxy(Class superCls){
        Enhancer enhancer = new Enhancer();
        //设置父类 【创建目标类的子类】
        enhancer.setSuperclass(superCls);
        //设置方法增强代码
        MethodInterceptor interceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("==============使用 cglib 前置增强");
                Object returnObj = methodProxy.invokeSuper(o, objects);
                System.out.println("==============使用 cglib 后置增强");
                return returnObj;
            }
        } ;
        enhancer.setCallback(interceptor);
       return (T) enhancer.create();
    }
}
```

调用：

```java
private UserService userServiceProxy = CglibProxy.getProxy(UserService.class) ;
```
