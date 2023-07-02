# 1. static

## 1.1 static关键字

- static 关键字用作变量、方法和嵌套类的修饰符。
- static 关键字声明属性或方法与整个类而不是类的任何特定实例相关。
  因此，静态成员经常称为类成员，
  例如，类属性或类方法
- JVM 类加载执行【类加载器，字节码校验器，解释执行器】
  1. static 代码执行顺序
  2. static 域中不能使用 this  super关键字

## 1.2 类属性

### 所有对象[实例]共享

![image-20220420210717433](images\image-20220420210717433.png)

### 类属性调用

![image-20220420210748769](images\image-20220420210748769.png)

## 1.3 类方法

### 类方法定义

![image-20220420211202682](images\image-20220420211202682.png)

### 类方法调用

![image-20220420211328226](images\image-20220420211328226.png)

## 1.4 静态导入

![image-20220420211422544](images\image-20220420211422544.png)

## 1.5 static语句块

![image-20220420211524890](images\image-20220420211524890.png)

## 1.6 执行顺序

![image-20220420211715323](images\image-20220420211715323.png)

# 2. final

## 2.1 final关键字

![image-20220420212306956](images\image-20220420212306956.png)

## 2.2 修饰变量

### 成员属性

![image-20220420212438279](images\image-20220420212438279.png)

### 局部变量

![image-20220420212458082](images\image-20220420212458082.png)

## 2.3 修饰类

```
修饰类： 该类不能被子类化。
注意： 抽象类 不能使用 final
```



## 2.4 修饰方法

```
修饰方法： 该方法不能被重写。
注意： 抽象方法 不能使用 final
```

# 3. 枚举类

## 3.1 系统API中static final

![image-20220420213400143](images\image-20220420213400143.png)

## 3.2 旧版本

![image-20220420213612063](images\image-20220420213612063.png)

```java
public class IGrade {
    /**优*/
    public static final char EXCELLENT = 'A' ;
    /**良*/
    public static final char GOOD = 'B' ;
    /**中*/
    public static final char PASS = 'C' ;
    /**差*/
    public static final char FAILURE = 'D' ;
}
```

## 3.3 新版本

### 基本使用

![image-20220420213713261](images\image-20220420213713261.png)

### 高级使用

![image-20220420213821724](images\image-20220420213821724.png)

![image-20220420213847819](images\image-20220420213847819.png)

# 4.接口类

## 4.1 为什么使用接口

![image-20220420214226687](images\image-20220420214226687.png)

![image-20220420214244249](images\image-20220420214244249.png)

![image-20220420214302704](images\image-20220420214302704.png)

## 4.2 接口定义及实现

![image-20220420214445025](images\image-20220420214445025.png)

![image-20220420214525066](images\image-20220420214525066.png)

![image-20220420214602176](images\image-20220420214602176.png)

## 4.3 面向接口编程

![image-20220420214804508](images\image-20220420214804508.png)

![image-20220420214827072](images\image-20220420214827072.png)

![image-20220420214856389](images\image-20220420214856389.png)

## 4.4 接口继承

![image-20220420215059246](images\image-20220420215059246.png)

![image-20220420215121317](images\image-20220420215121317.png)

![image-20220420215144022](images\image-20220420215144022.png)

# 5.今日作业

## 5.1 作业1

![image-20220420215311243](images\image-20220420215311243.png)

## 5.2 动物乐园

### 第一关：面向类

![image-20220420215712278](images\image-20220420215712278.png)

### 第二关：面向父类

![image-20220420215627348](images\image-20220420215627348.png)

### 第三关：添加接口

![image-20220420215939610](images\image-20220420215939610.png)

![image-20220420220108992](images\image-20220420220108992.png)

![image-20220420220131705](images\image-20220420220131705.png)

![image-20220420220238042](images\image-20220420220238042.png)