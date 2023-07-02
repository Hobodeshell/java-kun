# 1. 学习方法

## 1.1 点石成金

![image-fz01](images\store.png)

## 1.2 烧水三步走

![image-fz01](images\water.png)

## 1.3 慢慢熏

![image-fz01](images\qg.png)



# 2. 变量

## 2.1 为什么使用？

![image-why-var](images\why-var.png)



## 2.2 三步走

![image-var-type](images\var-type.png)

![image-var](images\var.png)

1. 声明

   【根据数据类型在内存申请空间】

   语法： 数据类型  变量名 ;

   int  age ;

2. 赋值

   【将数据存储至对应的内存空间】

   语法： 变量名 =  数值 ；

   age = 18 ;

   注意： 【1,2 可以合二为一   声明的同时进行赋值，比如： int  age = 18 ； 】

3. 使用

   【取出数据使用】

   System.out.println(age) ;

   ![image-var3](images\var3.png)

![image-var4](images\var4.png)

## 2.3 值覆盖问题

![image-override](images\var-override.png)

```java
int  age ;
age = 5 ;
age = 7 ;
System.out.print(age) ;
```

# 3. 数据类型

## 3.1 常用类型

![image-type](images\type.png)

## 3.2  + 变量与字符串连接符

![image-test-type](images\test-type.png)



随堂练习：

```
要求: 使用变量存储以下MP3信息，并打印输出
品牌（brand）：爱国者F928
重量（weight）：12.4
电池类型（type）：内置锂电池
价格（price）：499 
```



## 3.3 变量命名规则

驼峰命名法：

大驼峰命名【类名   public  class  UserManager { }】

小驼峰命名 【变量名  int  maxAge ；】

![image-test-type](images\let.png)

![image-test-type](images\var5.png)

# 4.大家来找茬

## 4.1 命名规范

![image-test-type](images\var-all.png)

## 4.2 变量使用三步走

```java
public class Error1 {
     public static void main(String[ ] args) {
            String title;
            System.out.println( title );
      }
}
```

## 4.3 错误命名

```java
public class Error2 {
     public static void main(String[ ] args) {
           int %hour = 18;
           System.out.println(%hour);
     }
}
```

## 4.4 重复定义

```java
public class Error3 {
       public static void main(String[ ] args) {
             String name = "张三";
             String name = "李四";
             System.out.println("姓名:"+name);
       }
}
```

## 4.5 数值与类型不符

```java
public class Error4 {
       public static void main(String[ ] args) {
             char sex = '先生';
             System.out.println("性别:"+sex);
       }
}
```

# 5.运算符

## 5.1 赋值运算符

![image-fz01](images\fz01.png)

![image-fz01](images\fz2.png)

## 5.2 算术运算符

```
+  加法 或 字符串拼接
-  减法
*  乘法
/  取整  或 除法（除数或被除数出现小数）
%  取余
```

![image-fz01](images\add.png)

## 5.3 类型转换

表达式运算：自动提升数据类型   int + double = double

强制转型【语法： （类型）数值；】

```java
double d = 3.14 ;
int i = (int)d ;
```



![image-fz01](images\type-convert.png)

### 常见错误

Q1:

```java
 int age = 19;
 char sex = '女';       
 char result = age + sex;
```



Q2:

```java
int a = 10;
int b = 10.2;
double c = 10; 
c = a;
int d = c;
```



# 6. 作业

## 练习： 

 要求:  使用变量，运算符完成作业

  1 .效果如下：

![image-ex-01](images\ex-01.png)

2. 效果如下：

![image-ex-01](images\ex-02.png)

   3.效果如下：

![image-ex-03](images\ex-03.png)

## 总结：

要求：【根据上课内容，进行回忆来制作】使用思维导图  进行今日所学进行知识梳理和总结！！！

![image-all](images\all.png)

## 反馈：

邮件地址：  zhangcf@wanho.net

注意：请使用真实姓名，不要使用艺名！！！

1. 作业运行效果及源码。
2. 总结的思维导图。
3. 有任何意见及建议，请直接写邮件内容【这样更加直接】。