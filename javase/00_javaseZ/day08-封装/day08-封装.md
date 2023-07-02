# 1. 构造方法

## 1.1 抽象

### 发现类

![image-20220417205710683](images\image-20220417205710683.png)

![image-20220417205857570](images\image-20220417205857570.png)

### 属性

![image-20220417210050297](images\image-20220417210050297.png)

### 方法

![image-20220417210141849](images\image-20220417210141849.png)

### 类图

![image-20220417210222141](images\image-20220417210222141.png)

### 实现

![image-20220417210338434](images\image-20220417210338434.png)

## 1.2 构造方法

### 简化属性赋值

![image-20220417210531263](images\image-20220417210531263.png)

### 语法

![image-20220417210726164](images\image-20220417210726164.png)

### 来找茬

#### Q1：

```java
public class Penguin {
    public void Penguin() {	
        health=10;
        sex="雄";
        System.out.println("执行构造方法");
    }
    public void print() {
        System.out.println("企鹅的名字是" + name 
                                        + ",健康值是" + health 
                                        + ",性别是" + sex);
    }
}

//测试类
public class PenguinTest{
   public static void main(String[] args) {
        Penguin pgn3=new Penguin();
		pgn3.print();
    }
}
```

#### Q2：

```java
public class Dog {
    String name = "旺财";   // 昵称
    int health = 100;  // 健康值    
    int love = 0;     // 亲密度	
    public void play(int n) {
        int localv;
        health = health - n;		
        System.out.println(name+"\t"+localv+"\t"+health+"\t"+love); 
    }
} 

//测试类
public class DogTest{
    public static void main(String[] args) {
        Dog d=new Dog();
        d.play(5);
    }
}
```

#### Q3:

```java
public class Dog {
    private String name = "旺财"; // 昵称
    private int health = 100;     // 健康值
    private int love = 0;        // 亲密度		
    public void play(int n) {
        private int localv=5;		
        health = health - n;		
        System.out.println(name+" "+localv+" "+health+" "+love);
    }	
    public static void main(String[] args) {
        Dog d=new Dog();
        d.play(5);
    }
}
```

### 构造方法重载

![image-20220417211802295](images\image-20220417211802295.png)

# 2. 封装

## 2.1 为什么使用封装

![image-20220417211850002](images\image-20220417211850002.png)

![image-20220417212455000](images\image-20220417212455000.png)

## 2.2 使用封装

![image-20220417212610739](images\image-20220417212610739.png)

## 2.3 this

![image-20220417212828435](images\image-20220417212828435.png)

## 2.4 制作类图

![image-20220417214101923](images\image-20220417214101923.png)

## 2.5 封装练习

![image-20220417214338264](images\image-20220417214338264.png)

![image-20220417214422971](images\image-20220417214422971.png)



![image-20220417214524364](images\image-20220417214524364.png)



# 3. 今日作业

## 3.1 String类型2参数

![image-20220417223647795](images\image-20220417223647795.png)

## 3.2 系统类型3个参数

![image-20220417231410904](images\image-20220417231410904.png)

## 3.3 系统类型4个参数

![image-20220417231559828](images\image-20220417231559828.png)

## 3.4 自定义类类型参数

![image-20220417231742188](images\image-20220417231742188.png)

## 3.5 类类型参数

![image-20220417213230400](images\image-20220417213230400.png)

