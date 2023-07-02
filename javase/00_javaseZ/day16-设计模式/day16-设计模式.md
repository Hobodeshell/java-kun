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

- 懒汉式

  在需要时才创建实例，延迟加载

  线程不安全

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
