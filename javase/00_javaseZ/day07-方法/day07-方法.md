# 1.根据界面设计方法

## 1.1 简易四则运算

### view界面

![image-20230416202918264](\images\01-js.png)

### 代码示例

```java
// 加法功能实现的示例代码:

/**
 * 加法
 */
public void add() {
    //获得 用户输入的 2 个数字
    String num1Str = num1Txf.getText();
    String num2Str = num2Txf.getText();
    //用户输入的一切都是字符串  此次需要类型转换
    double num1 = Double.parseDouble(num1Str) ;
    double num2 = Double.parseDouble(num2Str) ;
    //进行加法计算
    double rs = num1+num2 ;
    //进行数据回显  此次 +"" 把double 和字符串进行拼接 实现类型转换为字符串
    rsTxf.setText(rs+"");
}
```

### 实现方案

```
1. CV 大法
  拷贝  只进行修改  计算逻辑
  
2. 抽象4个方法 公共部分的代码
  在当前类 实现
  
3. 单独定义一个类：实现四则运算  
```

## 1.2 用户登录

### view界面

![image-20230416210117423](\images\image-20230416210117423.png)



![image-20230416210145094](\images\image-20230416210145094.png)

### 部分逻辑代码

```java
/**
 * 用户登录
 */
public void login() {
    //获得用户输入的账号  密码
    String account = accountTxf.getText();
    String pswd = pswdPw.getText();
    //假设 已知系统存在： 账号  admin  密码 123
    //此次 补全 判断逻辑
    boolean isLoginSuc = false ;
    if (isLoginSuc) {
        AlertUtil.alertInfo("登录成功！！！");
        return;
    }
    AlertUtil.alertError("账号或密码错误！！！");
}
```

### 实现方案

```
1. 直接怼死  
   当前方法中 直接写登录 逻辑
   
2. 单独定义类
   定义方法
   此次调用 另一个类的方法
```

## 1.3 顾客管理

### view界面

![image-20230416223914115](\images\image-20230416223914115.png)

点击查询

![image-20230416223946594](\images\image-20230416223946594.png)

点击添加

![image-20230416224058992](\images\image-20230416224058992.png)

点击删除

![image-20230416224142893](\images\image-20230416224142893.png)



### 代码示例

```java
/**
 * 点击 界面 搜索按钮执行的代码逻辑
 */
public void search() {
    //获得 用户输入的关键字
    String customerName = customerNameTxf.getText();
    //根据关键字 获得 包含关键字的顾客姓名 放在数组中

    //此次为 伪码
    String[] customerAy = {"丐帮帮主-乔峰","大理王子-段誉","天山-童姥","峨眉-灭绝师太","武当-张三丰","天鹰教-白眉鹰王"} ;
    //填充数据到界面中
    fillData2ListView(customerAy);
}


/**
 * 点击 界面 添加按钮执行的代码逻辑
 */
public void add() {
    //获得 用户输入的关键字
    String customerName = customerNameTxf.getText();
    //非空判断
    if ("".equals(customerName)) {
        AlertUtil.alertInfo("顾客姓名不能为空！！！");
        return;
    }
    //输出新增的顾客姓名
    System.out.println("新增的顾客姓名:" + customerName);
    //把数据存放在数组中

    //要求顾客姓名不能重复

    boolean isAddSuc = true ;
    //如果失败  提示错误消息
    if (isAddSuc) {
        //清空输入框内容
        customerNameTxf.setText("");
        //更新视图
        datList.add(customerName) ;
        return;
    }
    AlertUtil.alertError("新增顾客["+customerName+"]失败！！！");
}

/**
 * 点击 界面 删除按钮执行的代码逻辑
 */
public void delete() {
    //获得选中的数据
    int idx = dataListView.getSelectionModel().getSelectedIndex();
    //如果没有选中
    if (idx==-1) {
        AlertUtil.alertError("请选中要删除的顾客！！！");
        return;
    }
    //删除顾客信息
    System.out.println("被选中删除的顾客索引:" + idx);
    //从数组中删除被选中的顾客 [此次需要 代码进行补全]
    boolean isDelSuc = false ;

    //删除成功 更新视图
    if (isDelSuc) {
        datList.remove(idx);
        return;
    }

    //删除失败  提示错误信息
    AlertUtil.alertError("删除失败！！！");
}

```



### 实现方案

```
1.  当前类直接操作数组

2. 单独定义类
   定义方法 进行方法调用
```




# 2.大家来找茬

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

# 7. 部分同学今日作业

![image-20230416224459280](\images\image-20230416224459280.png)

![image-20230416224533237](\images\image-20230416224533237.png)

