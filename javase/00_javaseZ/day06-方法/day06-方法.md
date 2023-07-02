# 1.一个参数的方法

## 1.1 图解

### 方法调用

![image-uninstall](images\function2.png)

### 方法参数

![image-uninstall](images\function.png)

![image-uninstall](images\function-args.png)

## 1.2 方法定义

```java
参数列表：
(数据类型  参数1，数据类型  参数2…)
```



![image-20220414212457125](images\image-20220414212457125.png)

```java
public class Juicer{
    public String juicing (String fruit ) {
          String juice = fruit + "汁";
          return juice; 
     } 
}
```

## 1.3 方法调用

```
注意： 调用方法，传递的参数要与参数列表一一对应
```

```java
/*调用zhazhi方法*/
Juicer myZhazhi = new Juicer();
String myFruit = "苹果";
String myJuice = myZhazhi. juicing (myFruit);
System.out.println(myJuice);
```

## 1.4 一个参数 void返回值

![image-20220414212755387](images\image-20220414212755387.png)

![image-20220414212831262](images\image-20220414212831262.png)

## 1.5 随堂练习1

### 一个参数void返回值

![image-20220414212950783](images\image-20220414212950783.png)

## 1.6 随堂练习2

### 一个参数boolean返回值

![image-20220414213115136](images\image-20220414213115136.png)

# 2. 多个参数的方法

## 2.1  2个参数boolean返回值

### 随堂练习3：

![image-20220414213322694](images\image-20220414213322694.png)

![image-20220414213436485](images\image-20220414213436485.png)

![image-20220414213520809](images\image-20220414213520809.png)

### 随堂练习4：

![image-20220414220341907](images\image-20220414220341907.png)

## 2.2 大家来找茬

### Q1:

```java
//方法定义
public void addName(String name){ 
	//方法体
}

//方法调用
对象名.addName(String  "张三");
```

### Q2:

```java
//方法定义
public boolean searchName(int start ,int end ,String name){
	//方法体
}

//方法调用
String s="开始";
int e=3;
String name="张三";
boolean flag =对象名.searchName(s ,e ,name);
```

### Q3:

```java
//方法定义
public boolean searchName(int start,int end,String name){
	//方法体
}

//方法调用
int s=1;
int e=3;
boolean flag = 对象名.searchName(s,e);
```

### Q4:

```java
//方法定义
public boolean searchName(int start,int end,String name){
		//方法体
}

//方法调用
int s=1;
int e=3;
String name="张三";
对象名.searchName(s,e,name);
```

### Q5:

```java
//方法定义
public void addName(name){ 
	//方法体
}

//方法调用
int n = 对象名.addName("张三")；
```

# 3. 方法练习

## 3.1 方法定义


   <修饰符>*   <返回值类型 > 方法名（<参数列表>*）{
         //方法体
         return 语句 ; 
   }

- 修饰符：public
- 返回值类型：方法执行后  调用者期望的结果类型
- 参数列表：未知数据  调用者调用方法时传递
- return：方法的返回值【返回给调用者】

## 3.2 练习1

需求：定义方法实现两个整数之和?

- 明确返回值类型：
      方法计算的是整数的求和，结果也必然是个整数，返回值类型定义为 int类型。

- 明确参数列表：
      计算哪两个整数的和，并不清楚，但可以确定为整数，

     参数列表可以定义两个int类型的变量，由调用者调用方法时传递。

```java

```

## 3.3 练习2

需求：定义方法实现比较两个整数是否相同? 

- 明确返回值类型：
  是否相同    boolean  true 相同  false 不相同
- 明确参数列表：
  两个整数  ？ 未知 

```java

```

## 3.4 练习3

需求：定义方法实现比较两个整数，如果相等返回0，第一个数大 返回1  第一数小返回-1?

- 明确返回值类型：

  期望返回值  0   1  -1     类型为 int

- 明确参数列表

  两个整数？

```java

```

## 3.5 练习4

需求：计算1+2+3+…+100的和？

- 明确返回值类型：
       整数  int
- 明确参数列表：
      已知  计算数据   没有未知条件

```java

```

## 3.6 练习5

需求：计算整数n1+…+n2的和

- 明确返回值类型：
       整数  int
- 明确参数列表：
     未知条件  整数n1  整数n2

```java

```

## 3.7 练习6

需求：打印不定次数的Hello？

- 明确返回值类型：
        打印  没有计算结果  返回值类型void
- 明确参数列表：
      次数不固定  int   未知次数

```java

```



# 4. 可变参数列表

JDK 1.5 [数组参数的变种]

![image-20220414224814018](images\image-20220414224814018.png)



注意：

```
 必须在参数列表的最后一个！！！
```

# 5. 拆箱与装箱

![image-20220414225211576](images\image-20220414225211576.png)

![image-20220414225243020](images\image-20220414225243020.png)

![image-20220414225319699](images\image-20220414225319699.png)



# 6.重载

```
调用者： 只关心功能，不关心参数列表及返回值类型！！！
```



![image-20220414225054528](images\image-20220414225054528.png)

![image-20220414225127453](images\image-20220414225127453.png)

# 7. 人机猜拳

## 7.1 需求

- 任务
  完成人机猜拳互动游戏的开发 
- 主要功能 
  选取对战角色
  猜拳
  记录分数



![image-20220414225651972](images\image-20220414225651972.png)

![image-20220414225902340](images\image-20220414225902340.png)

![image-20220414225922810](images\image-20220414225922810.png)

![image-20220414230015072](images\image-20220414230015072.png)

![image-20220414230101546](images\image-20220414230101546.png)

![image-20220414230156746](images\image-20220414230156746.png)

![image-20220414230457640](images\image-20220414230457640.png)

![image-20220414230557304](images\image-20220414230557304.png)

