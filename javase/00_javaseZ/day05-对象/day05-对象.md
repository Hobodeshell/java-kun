# 1. 对象

## 1.1图解

![image-20220413212931558](images\image-20220413212931558.png)



## 1.2 面向过程VS面向对象

![image-20220413213241742](images\image-20220413213241742.png)

## 1.3 身边对象

![image-20220413213927042](images\image-20220413213927042.png)

### 抽象

![image-20220413214050832](images\image-20220413214050832.png)

![image-20220413214153991](images\image-20220413214153991.png)

### 观察属性/方法

![image-20220413214854155](images\image-20220413214854155.png)

# 2. 类

## 2.1 封装

![image-20220413220225268](images\image-20220413220225268.png)

![image-20220413220340428](images\image-20220413220340428.png)

## 2.2 类

![image-20220413220434214](images\image-20220413220434214.png)

![image-20220413220529389](images\image-20220413220529389.png)

## 2.3 类与对象关系

![image-20220413220716980](images\image-20220413220716980.png)

## 2.4 定义类

### 语法

![image-20220413221315836](images\image-20220413221315836.png)

![image-20220413223303486](images\image-20220413223303486.png)

![image-20220413223502699](images\image-20220413223502699.png)



### 类图

![image-20220413221737048](images\image-20220413221737048.png)

### 定义类

![image-20220413221913236](images\image-20220413221913236.png)

## 2.5 调用类

### 语法

![image-20220413222038204](images\image-20220413222038204.png)

### 创建对象/调用属性及方法

![image-20220413222511485](images\image-20220413222511485.png)

## 2.6 随堂练习

### 练习1：

![image-20220413223817033](images\image-20220413223817033.png)

![image-20220413223923630](images\image-20220413223923630.png)

### 练习2：

![image-20220413224157994](images\image-20220413224157994.png)

![image-20220413224250770](images\image-20220413224250770.png)

## 2.7 OOP优点

- 与人类的思维习惯一致
  把人类解决问题的思维过程转变为程序能够理解的过程 
- 信息隐藏，提高了程序的可维护性和安全性 
  封装实现了模块化和信息隐藏 
  封装使得在对象外部不能随意访问对象的属性和方法
- 提高了程序的可重用性
  一个类可以创建多个对象实例，增加了重用性

## 2.8 数据类型

![image-20220413224534164](images\image-20220413224534164.png)

![image-20220413224606573](images\image-20220413224606573.png)

# 3.方法

## 3.1 图解

![image-20220413224910267](images\image-20220413224910267.png)

## 3.2 方法定义

![image-20220413224950584](images\image-20220413224950584.png)

![image-20220413225037125](images\image-20220413225037125.png)

![image-20220413225126507](images\image-20220413225126507.png)

## 3.3 方法调用

![image-20220413225204472](images\image-20220413225204472.png)

![image-20220413225316672](images\image-20220413225316672.png)

![image-20220413225357201](images\image-20220413225357201.png)

## 3.4 大家来找茬

### Q1:

```java
public class Student{
	public void showInfo(){
       	return "我是一名学生";
    }
}
```

### Q2:

```java
public class Student{
	public double getInfo(){
       	double weight = 95.5;
       	double height = 1.69;
 		return weight, height;
    	}
}
```

### Q3:

```java
public class Student{
	public String showInfo(){
		return "我是一名学生";
		public double getInfo(){
             double weight = 95.5;
             double height = 1.69;
   			 return weight;
       }
     }
}

```

### Q4:

```java
public class Student{
	int age=20;
	if(age<20){
	    System.out.println("年龄不符合入学要求！");  
	}
	public void showInfo(){
		return "我是一名学生";
	}
}
```



# 4. 成员变量/局部变量

## 4.1 作用域问题

![image-20220413230559452](images\image-20220413230559452.png)

![image-20220413230649563](images\image-20220413230649563.png)

![image-20220413230743818](images\image-20220413230743818.png)

## 4.2 来找茬

```java
public class Test {
     int score1 = 88;
     int score2 = 98;
      public void calcAvg(){
          int avg = (score1 + score2)/2;
      }    
      public void showAvg(){
         System.out.println("平均分是： " + avg);
     }
}
```

## 练习3：

![image-20220413231053109](images\image-20220413231053109.png)

# 5. 文档注释

## 5.1 类注释

![image-20220413231708351](images\image-20220413231708351.png)

## 5.2 属性 方法注释

![image-20220413231745187](images\image-20220413231745187.png)



# 6. 今日作业

## 6.1 类定义及调用

![image-20220413232113243](images\image-20220413232113243.png)

## 6.2 类方法练习

![image-20220413232752671](images\image-20220413232752671.png)

