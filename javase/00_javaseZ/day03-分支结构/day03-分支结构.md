# 1. 学习方法

## 1.1 学习顺序

![image-store](images\store.png)

## 1.2 自己努力

![image-cd](images\cd.png)

## 1.3 三季人

![image-mz](images\mz.png)

# 2. boolean数据类型

## 图解

- 开【通电】
- 关【断电】

![image-cd](images\on-off.png)

## 使用场景

- 一件艺术品是真货还是假货
- 地铁2号线的首发车时间是5：00吗
- 这次考试成绩在90分之上吗

## boolean类型值

boolean 类型只有这两个值

- true 【真】
- false 【假】

## 随堂练习1

```
要求：从控制台输入张三同学的成绩，与李四的成绩（80分）比较，
     声明boolean类型的变量，存放比较结果。
     输出“张三的成绩比李四的成绩高吗?” 的判断结果
```

![image-cd](images\boolean.png)

# 3. 关系/比较运算符

## 图解

![image-cd](images\bj.png)

## 使用场景

比较高低、大小、长短等

- 张三的考试成绩是否比李四高
- 大象是否比乌龟更长寿
- 篮球跟地球一样大吗

## 关系运算符

```
< 小于
<= 小于等于
> 大于
>= 大于等于
== 等于
!= 不等于


张三的成绩 > 李四的成绩           假
大象的寿命 < 乌龟的寿命           真
篮球的大小 == 地球的大小         假
关系运算符的作用：用来做比较运算
比较的结果：boolean类型
```

# 4.顺序执行

## 图解

![image-cd](images\order.png)

## JVM死给你看

![image-cd](images\order-err.png)

# 6.分支执行

## 图解

![image-cd](images\if.png)

## if

### 使用场景

```
如果张浩的Java考试成绩大于98分，张浩就能获得一个MP4作为奖励
```

### 语法

```
if(布尔表达式){
  语句或语句块
}
或
【当且只有一条语句，{}可以被省略！！！】
if(布尔表达式)
  语句或语句块
```

### 示例

![image-cd](images\use-if.png)

## 逻辑运算符

### 并且 and &&

![image-cd](images\and.png)

```
短路情况： 【第一个条件为  假 】
```

### 或者 or ||

![image-cd](images\cl.png)

```
短路情况： 【第一个条件为 真 】
```

### 取反 not ！

![image-cd](images\not.png)

```
取反【真 -> 假   假-> 真】
```

### 总结：

| 运算符  | 表达式            | 描述        |
| ---- | -------------- | --------- |
| &&   | 条件1 && 条件2     | 一假则假      |
| \|\| | 条件1  \|\|  条件2 | 一真则真      |
| ！    | ！条件            | 真为假   假为真 |

### 随堂练习2

```
使用：逻辑运算符   
张浩Java成绩大于98分，而且音乐成绩大于80分，老师奖励他；
或者Java成绩等于100分，音乐成绩大于70分，老师也可以奖励他
注意： 优先级 【了解】
       最高： （）
       最低：  =
       顺序：  ！> 算术运算符 > 比较运算符 > && > ||
```

## if  else

![image-cd](images\if-else1.png)

![image-cd](images\if-else2.png)

### 随堂练习3

```
要求：使用 if ... else ... 解决实际问题？
  输入学生人数
  输入每间宿舍入住人数
  计算总共需要多少宿舍
```

### 随堂练习4

```
用户输入两个数a、b。
  如果a能被b整除 或 a加b大于1000，
  则输出a；
  否则输出b
```

### 大家来找茬

#### 条件

```java
int age =10；
if(age = 20)   {
     System.out.println("年龄是20岁"); 
}
```

#### {}逻辑

```java
int  score=100； 
if (score > 98) 
	System.out.println("老师说:不错，奖励一个MP4！");
 else
	System.out.println("老师说:惩罚进行编码！");
	System.out.println("老师说:一直到学会为止！");
```

## if elseif else

![image-cd](images\if-elseif2.png)

![image-cd](images\ifelse3.png)

```java
if(条件1){
  代码块1
}else if（条件2）{    //可以有多个 else if  
  代码块2
}else{
  代码块3
}  
注意： 自上向下  只要有一个条件为 true  后面所有的条件都不被执行 ！！！
```

### 随堂练习5

```
使用 if elseif else 结构，解决如下问题？
我想买车，买什么车决定于我在银行有多少存款
如果我的存款超过500万，我就买凯迪拉克
否则，如果我的存款超过100万，我就买帕萨特
否则，如果我的存款超过50万，我就买依兰特
否则，如果我的存款超过10万，我就买奥托
否则，我买捷安特
```

## if嵌套

### 使用场景

```
问题： 
    学校举行运动会，百米赛跑跑入10秒内的学生有资格进决赛，否则被淘汰。再根据性别分别进入男子组和女子组。
分析：1. 要判断是否能够进入决赛
	 2. 在确定进入决赛的情况下，还要判断是进入男子组，还是进入女子组
```



![image-cd](images\if22.png)

```java
 if（条件1) {
    if（条件2) {
        代码块1
    } else {
        代码块2
    }
} else {
     代码块3
}
```

### 随堂练习6

```
训练要点：
  嵌套if选择结构
  需求说明：
  普通顾客购物满100元打9折；会员购物打8折；会员购物满200元打7.5折
实现思路：
  1、外层判断是否是会员
  2、内层判断是否达到相应打折要求
难点指导：
	嵌套if选择结构中{ }的使用
```

![image-cd](images\innerif.png)

## switch

### 使用场景

```
张三参加计算机编程大赛
  如果获得第一名，将参加麻省理工大学组织的1个月夏令营
  如果获得第二名，将奖励惠普笔记本电脑一部
  如果获得第三名，将奖励移动硬盘一个
  否则，不给任何奖励
该问题属于等值判断
  解决方法：
    1.使用多重if选择结构实现
    2.使用switch选择结构解决
```

语法：

```java
switch(表达式){
    case 值1： 语句1 ； break ；
    case 值2： 语句2 ； break ；
    case 值3： 语句3 ； break ；
    default:  语句4 ； break ；
}
```

### 大家来找茬

#### break

```java
int mingCi = 1;
 switch (mingCi){
	    case 1:
                System.out.println("参加麻省理工大学组织的1个月夏令营");               
        case 2:
                System.out.println("奖励惠普笔记本电脑一部");                
        case 3:
                System.out.println("奖励移动硬盘一个");                
        default:
                System.out.println("没有任何奖励 ");
 }
```

#### 值相同

```java
int mingCi = 1;
switch (mingCi){
        case 1:
                System.out.println("参加麻省理工大学组织的1个月夏令营");
        case 2:
                System.out.println("奖励惠普笔记本电脑一部");
        case 2:
                System.out.println("奖励移动硬盘一个");
        default:
                System.out.println("没有任何奖励 ");
}
```

#### default

```java
int mingCi = 6;
switch (mingCi){
       default:
                System.out.println("没有任何奖励 ");
 	   case 1:
                System.out.println("参加麻省理工大学组织的1个月夏令营");
        case 2:
                System.out.println("奖励惠普笔记本电脑一部");
        case 3:
                System.out.println("奖励移动硬盘一个");
}
```

### switch和多重if

```
相同： 都是用来处理多分支条件的结构

不同： 
      switch
        只能处理等值条件判断的情况，而且条件必须是整型变量或字符（串）型变量
      多重if
        没有switch选择结构的限制，特别适合某个变量处于某个连续区间时的情况
```

### 随堂练习7

```
使用 if 嵌套  switch 实现：【商品换购】
综合运用嵌套if选择结构、
       switch选择结构、
       多重if选择结构
       进行实现商品换购功能
```

![image-cd](images\innerSwtich.png)



![image-cd](images\if-go.png)