# 1.异常

## 1.1 什么是异常

![image-20220423204516580](images\image-20220423204516580.png)

![image-20220423204652827](images\image-20220423204652827.png)

![image-20220423204800746](images\image-20220423204800746.png)

![image-20220423204825533](images\image-20220423204825533.png)

## 1.2 异常处理

### try catch

![image-20220423204916372](images\image-20220423204916372.png)

![image-20220423204952156](images\image-20220423204952156.png)

![image-20220423205503381](images\image-20220423205503381.png)

![image-20220423205854720](images\image-20220423205854720.png)

![image-20220423205949154](images\image-20220423205949154.png)

![image-20220423210022919](images\image-20220423210022919.png)

![image-20220423210052513](images\image-20220423210052513.png)

### finally

![image-20220423210126305](images\image-20220423210126305.png)

![image-20220423210205747](images\image-20220423210205747.png)

![image-20220423210246709](images\image-20220423210246709.png)

![image-20220423210350299](images\image-20220423210350299.png)

![image-20220423210419173](images\image-20220423210419173.png)

### 多重catch

![image-20220423210745789](images\image-20220423210745789.png)

![image-20220423210900010](images\image-20220423210900010.png)

### 随堂练习1：

![image-20220423210956200](images\image-20220423210956200.png)

### throws

![image-20220423211052883](images\image-20220423211052883.png)



### 随堂练习2：

![image-20220423211248872](images\image-20220423211248872.png)

### 忽略异常

![image-20220423211531212](images\image-20220423211531212.png)



![image-20220423211547423](images\image-20220423211547423.png)

### 随堂练习3：

```java
使用： SimpleDateFormat 可以把字符串转换成日期类？ 参数：String  返回值：Date
DateFormat  sdf = new SimpleDateFormat(“yyyy-MM-dd”);
Date date = sdf.parse(“2020-04-13”) ;
制作 DateUtil 工具类，实现支持
“2020-04-13”
“2020/04/13”
“2020年04月13日”
三种任意格式字符串转成日期类型
```



## 1.3 自定义异常

![image-20220423211725097](images\image-20220423211725097.png)

![image-20220423211821274](images\image-20220423211821274.png)



## 1.4 异常分类

![image-20220423212211161](images\image-20220423212211161.png)

# 2. Object

## 2.1 根类

![image-20220423212517621](images\image-20220423212517621.png)

## 2.2 equals

![image-20220423212611769](images\image-20220423212611769.png)

## 2.3 toString

![image-20220423212809611](images\image-20220423212809611.png)

## 2.4 finalize

![image-20220423212852387](images\image-20220423212852387.png)

## 2.5 clone

![image-20220423212924318](images\image-20220423212924318.png)

# 3. ArrayList

## 3.1 为什么

![image-20220423213031995](images\image-20220423213031995.png)

## 3.2 是什么

![image-20220423213054793](images\image-20220423213054793.png)

![image-20220423214733888](images\image-20220423214733888.png)

![image-20220423214758985](images\image-20220423214758985.png)

![image-20220423222231747](images\image-20220423222231747.png)

![image-20220423222319053](images\image-20220423222319053.png)

![image-20220423222341018](images\image-20220423222341018.png)

# 4. 今日作业

## 4.1 数组与ArrayList

```
产生10个1-100的随机数，并放到一个数组中，把数组中大于等于10的数字放到一个list集合中，并打印到控制台。
```



## 4.2 ArrayList查找元素

```
定义一个方法listTest(ArrayList<Integer> al, Integer s)，要求返回s在al里面第一次出现的索引，如果s没出现过返回-1。
```



## 4.3 ArrayList去重

```
已知数组存放一批QQ号码，QQ号码最长为11位，最短为5位
String[] strs = {"12345","67891","12347809933","98765432102","67891","12347809933"}。
将该数组里面的所有qq号都存放在ArrayList中，将list中重复元素删除，将list中所有元素for循环打印出来
```

