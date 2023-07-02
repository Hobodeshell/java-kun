# 1. 逻辑训练

## 1.1 属性观察

![image-uninstall](images\light.png)

## 1.2 逆向思维

![image-uninstall](images\gold.png)


# 2. 循环语句

## 2.1 为什么要使用？

![image-uninstall](images\whyloop.png)

## 2.2 生活中循环

![image-uninstall](images\loop2.png)

## 2.3 while

先问服不服？不服气，打【不知道打多少次，但是知道终止条件（打到服为止）】

![image-uninstall](images\while.png)

### 随堂练习1：

![image-uninstall](images\ex01.png)

## 2.4 do..while

先打一顿（至少挨揍一次），再问服不服？不服气，打【不知道打多少次，但是知道终止条件（打到服为止）】

![image-uninstall](images\dowhile.png)

![image-uninstall](images\dowhile2.png)

## 2.5 for

知道固定的次数

![image-uninstall](images\for.png)

### 随堂练习2：

![image-uninstall](images\ex02.png)

### 大家来找茬:

#### Q1:

```java
for(;i<10;i++){
     System.out.println("这是 "+i);
}
```

#### Q2:

```java
for(int i=0;;i++){
      System.out.println("这是 "+i);
}
```

#### Q3:

```
for(int i=0;i<10;){
       System.out.println("这是 "+i);  
}
```

#### Q4:

```java
for(;;){
     System.out.println("这是测试");
}
```



## 2.6 break

![image-uninstall](images\break.png)

### 随堂练习3：

![image-uninstall](images\ex03.png)

## 2.7 continue

![image-uninstall](images\continue.png)

# 3.数组

## 3.1 为什么使用数组？

![image-uninstall](images\why.png)

![image-uninstall](images\array2.png)

![image-uninstall](images\array3.png)

## 3.2 数组基本概念

![image-uninstall](images\array4.png)

![image-uninstall](images\array5.png)

```
下列哪组数据能存储在数组中？数组的类型是什么？ 
“刘星”，“夏雨”，“夏雪”
8，98，“c”，23
98.1，341.2，34.3
```

## 3.3 使用数组

![image-uninstall](images\arr01.png)

![image-uninstall](images\arr3.png)

![image-20220412221838170](images\image-20220412221838170.png)

![image-20220412221953800](images\image-20220412221953800.png)

![image-20220412222130184](images\image-20220412222130184.png)

![image-20220412222231883](images\image-20220412222231883.png)

## 3.4 大家来找茬

### Q1:

```java
public class ErrorDemo1 {
     public static void main(String[ ] args){
          int[ ] score = new int[ ];
          score[0] = 89;
          score[1] = 63;
          System.out.println(score[0]);
    }
} 
```

### Q2:

```java
public class ErrorDemo2 {
	public static void main(String[ ] args) {
		int[ ] scores = new int[2];
		scores[0] = 90;
		scores[1] = 85;
		scores[2] = 65;
		System.out.println(scores[2]);
	}
}
```

### Q3:

```java
public class ErrorDemo3 {
    public static void main(String[ ] args){
           int[ ] score = new int[5];
           score = {60, 80, 90, 70, 85};

           int[ ] score2;
           score2 = {60, 80, 90, 70, 85}; 
    } 
}
```

### 随堂练习4

```
有一个数列：8，4，2，1，23，344，12
1.循环输出数列的值
2.求数列中所有数值的和
3.猜数游戏：从键盘中任意输入一个数据，判断数列中是否包含此数 
```

## 3.5 数组极值

![image-20220412223858506](images\image-20220412223858506.png)

![image-20220412224001148](images\image-20220412224001148.png)

![image-20220412224148961](images\image-20220412224148961.png)

### 随堂练习5

![image-20220412224313134](images\image-20220412224313134.png)

## 3.6 调整大小

![image-20220412224951872](images\image-20220412224951872.png)

### 随堂练习6

```
已知：字符串数组 stuAy
	String[] stuAy = {"张三","李四","王五"} ;
要求：向数组stuAy中 新增一个学生 "赵六"
```

## 3.7 系统API

![image-20220412225639430](images\image-20220412225639430.png)

### 随堂练习7

```
调用 系统 API 实现排序功能
```

## 3.8 第3方 API

![image-20220412225925776](images\image-20220412225925776.png)

### 随堂练习8

```
已知数组： int[] scoreAy = {100,90,80,77} ;
使用Apache lang3包：实现如下
 1. 向数组中添加一个元素
 2. 从数组中删除 索引为0的元素
```

# 4.今日作业
## 4.1 录入会员

![image-uninstall](images\hw1.png)

## 4.2 登录系统

![image-uninstall](images\hw2.png)


## 4.3 数组排序及输出

![image-20220412230452138](images\image-20220412230452138.png)

## 4.4 判断数组元素相同

```
需求说明[判断数组元素相同]：
 int[ ] arr1 = {1,2,3,4,3,2,1};	         
 int[ ] arr2 = {1,2,3,4,3,2,1};
```



## 4.5 评分

```
题目：10个评委，给歌手打分0~10分
评分规则：去掉一个最高分，一个最低分，然后取平均值。
```

