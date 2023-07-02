# 	1. 基础语法

## 1.1 介绍

### 了解

![image-20211119095401318](images\css.png)

### 书写位置

参考CSS：

- CSS 书写位置：

1. 行内样式表
2. 内部样式表
3. 外部样式表

- JavaScript 书写位置：

1. 行内 JavaScript

   ```html
   <button onclick="console.log(`行内 js调用`)">按钮</button>
   ```

2. 内部 JavaScript

   ```html
   <script>
       console.log(`内部 JavaScript`);
   </script>
   ```

   

   ```
   直接写在html文件里，用script标签包住
   规范：script标签写在</body>上面
   拓展： alert(‘你好，js’) 页面弹出警告对话框
   ```

3. 外部 JavaScript

   ```
   代码写在以.js结尾的文件里
   语法：通过script标签，引入到html页面中
   注意： script标签中间无需写代码，否则会被忽略！
   ```

1.js

```js
console.log(`我是外部js调用`);
```

1.html

```html
<script src="./01.js"></script>
```



### 注释

1. 单行注释

​    符号：//

   作用：//右边这一行的代码会被忽略

   快捷键：ctrl + /

2. 块注释

​    符号：/* */

​    作用：在/* 和 */ 之间的所有内容都会被忽略

​    快捷键：shift + alt + A



### 结束符

- 代表语句结束

​    英文分号 ; 

​    可写可不写（现在不写结束符的程序员越来越多）

   换行符（回车）会被识别成结束符 ,因此在实际开发中有许多人主张书写 JavaScript 代码时省略结束符

   但为了风格统一，要写结束符就每句都写，要么每句都不写（按照团队要求.）

```java
//结构性语句可省略   功能性语句不能省略
public class A{
  public void f(){
      int i = 9 ;
      int j ;
      System.out.println(i) ;
  };  
};
```



### 输入和输出语句

- 输出语句

  1. document.write('.....')

     向body内输出内容

     如果输出的内容写的是标签，也会被解析成网页元素

     

  2. alert('....')

     页面弹出警告对话框

     

  3. console.log('.....')

  ​        F12 控制台输出

  

- 输入语句

  1. prompt('...')

     显示一个对话框，对话框中包含一条文字信息，用来提示用户输入文字
     
     2. confirm("...")
     
        返回boolean  一般提示 是否删除

```html
<!-- 输出 -->
<h2>测试输入 输出</h2>
<script>
//1.alert 警告框
// alert(`你好...`)

// 2.控制台输出
console.log(`控制台输出`)

// 3. 输出
document.write(`<h2>document</h2>`)

// let isDel = confirm(`你确认删除?`)
// console.log(isDel)

let value = prompt("请输入你的姓名:","admin")
console.log(value);
</script>
```



## 1.2 变量

### 声明变量

声明变量有两部分构成：声明关键字、变量名（标识）

 let 即关键字 (let: 允许、许可、让、要)，所谓关键字是系统提供的专门用来声明（定义）变量的词语

```js
let 变量名

let  uName  错误命名  setuName  getuName
```

注意： let 不允许多次声明一个变量名。



### 命名规范

![image-20211119095401318](images\let.png)

不能用关键字

- 关键字：有特殊含义的字符，JavaScript 内置的一些英语词汇。例如：let、var、if、for等 
- 只能用下划线、字母、数字、$组成，且数字不能开头
- 字母严格区分大小写，如 Age 和 age 是不同的变量

```java
int int = 9 ;
```

```js
let let = 'zhangsan'
```



### 随堂练习1：

让用户输入自己的名字、年龄、性别，再输出到网页

思路：

1. 弹出 输入 框（prompt）： 请输入您的姓名： 用变量保存起来。
2. 弹出输入框（prompt） ： 请输入您的年龄： 用变量保存起来。
3. 弹出输入框（prompt） ： 请输入您的性别： 用变量保存起来。
4. 页面分别 输出 (document.write) 刚才的 3 个变量。

```html
<script>
  let name = prompt('请输入你的姓名', '张三')
  let age = prompt('请输入你的年龄', '18')
  let sex = prompt('请输入你的性别', '男')
  document.write(`姓名:${name}<br>年龄:${age}<br>性别:${sex}`)
  //document.write('姓名:' + name + '<br>年龄:' + age + '<br>性别:' + sex)
</script>
```



## 1.3 数据类型 

### 数据类型

| 数据类型      | 作用             | 表示形式         | 列                 |
| --------- | -------------- | ------------ | ----------------- |
| string    | 表示文本内容         | 单引号 双引号 反引号  | ‘aa'  "aaa" `aaa` |
| number    | 表示数字内容         | 直接写数字        | 10,12,12.5        |
| boolean   | 真和假   对和错  开和关 | true   false | true  false       |
| undefined | 表示未定义          | undefined    | undefined         |
| object    | 对象类型           |              |                   |



### 检测数据类型

通过 typeof 关键字检测数据类型

```html
 <script>
  // let str1 = '字符串'
  let str1 = '1000'
  let num = 100.5
  let flag = true
  let name
  let now = new Date()
  console.log(typeof str1)
  console.log(typeof num)
  console.log(typeof flag)
  console.log(name)
  console.log(typeof now)
</script>
```

```java
//类型转换  ClassCastException
Object obj = ...
if(obj instanceof Person){
    Person p = (Person)obj ;
}
```



## 1.4 运算符

### 算术运算符

主要包括加、减、乘、除、取余（求模）。

```
+ 求和
- 求差
* 求积
/ 求商
%  取模（取余数）
```



### 优先级

优先级越高越先被执行，优先级相同时以书从左向右执行。 

```
乘、除、取余优先级相同

加、减优先级相同

乘、除、取余优先级大于加、减

使用 () 可以提升优先级

总结： 先乘除后加减，有括号先算括号里面的~~~
```



### +运算符

1. +运算符在数字型(number)中是求和运算
2. 运算符在字符串型(string)中是拼接



### 随堂练习2：

输入姓名和年龄，在页面上显示：大家好，我叫xxx，今年x岁

思路：

1. 弹出 输入 框（prompt）： 请输入您的姓名： 用变量保存起来。
2. 弹出输入框（prompt） ： 请输入您的年龄： 用变量保存起来。
3. 输出需要字符串拼接 + 运算符。



### 模板字符串

```
``
在英文输入模式下按键盘的tab键上方那个键（1左边那个键）
内容拼接变量时，用 ${} 包住变量
```



## 1.5 类型转换

### 为什么要类型转换

JavaScript是弱数据类型： JavaScript也不知道变量到底属于那种数据类型，只有赋值了才清楚。

坑： 使用表单、prompt 获取过来的数据默认是字符串类型的，此时就不能直接简单的进行加法运算。



### 隐式转换

某些运算符被执行时，系统内部自动将数据类型进行转换，这种转换称为隐式转换。

规则：

+号两边只要有一个是字符串，都会把另外一个转成字符串

除了+以外的所有算术运算符都会把数据转成数值类型

缺点：

转换类型不明确，靠经验才能总结

```js
//例子1：
let age = +prompt('请输入您的年龄:')

//例子2：【减法运算】
let age = prompt('请输入您的年龄:')-0
```



### 显式转换

- Number（数据）

   转成数值类型

   如果字符串内容里有非数字，转换失败时结果为 NaN（Not a Number）即不是一个数字

  NaN也是number类型的数据，代表非数字


- parseInt（数据）


- parseFloat（数据）

- Boolean（数据）

  转成布尔类型

​       0、空字符串、NaN、undefined、null转成false,其他都是true



### 随堂练习3：

输入2个数，计算两者的和，打印到页面中



### 随堂练习4：

![image-20211119095401318](images\case01.png)

```html
<script>
  let total = prompt(`总金额`, `1000`)
  let w = prompt(`水`, `9`)
  let e = prompt(`电`, `90`)
  let n = prompt(`网`, `80`)
  let l = total - w - e - n

  document.write(`<table>
  <caption>
    xxxx年xx月
  </caption>
  <thead>
    <tr>
      <th>余额</th>
      <th>余额</th>
      <th>余额</th>
      <th>余额</th>
      <th>余额</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>${total}</td>
      <td>${w}</td>
      <td>${e}</td>
      <td>${n}</td>
      <td>${l}</td>
    </tr>
  </tbody>
</table>`)
</script>
```



# 2.函数

## 2.1 作用

函数：

​	function，是被设计为执行特定任务的代码块

说明：

​	函数可以把具有相同或相似逻辑的代码“包裹”起来，通过函数调用执行这些被“包裹”的代码逻辑，

​	这么做的优势是有利于精简代码方便复用。

![image-20211119095401318](images\function.png)



![image-20211119095401318](images\function2.png)



## 2.2 语法

### 无参函数

1. 函数的声明语法

   ```java
   <修饰符>*  返回值类型   方法名(<参数列表>*){
       //方法体
   }
   
   修饰符： private  默认 protected public
         final  static native(JNI) sy...
   
   返回值类型: void  String  int  List  Object ...
    
   方法名: 小驼峰命名  动词  selectById delete...
   参数列表: ()  (int id)  (int id,String name) ...    
   ```

   

```js
function 函数名() {
  
}
```

函数名命名规范

- 和变量命名基本一致
- 尽量小驼峰式命名法
- 前缀应该为动词
- 命名建议：常用动词约定

| 动词   | 含义          |
| ---- | ----------- |
| can  | 判断是否可执行某个动作 |
| has  | 判断是否含义某个值   |
| is   | 判断是否为某个值    |
| get  | 获取某个值       |
| set  | 设置某个值       |
| load | 加载某些数据      |



2. 函数的调用语法

```js
//函数调用，这些函数体内的代码逻辑会被执行
函数名()
```

```html
 <script>
  console.log(`测试 debug ..111.. `)
  // 定义函数
  function sayHello() {
    console.log(`======sayHello=======`)
  }

  console.log(`测试 debug ..222.. `)
  //调用函数
  sayHello()

  console.log(`测试 debug ..333.. `)
</script>
```



### Debug 断点调试

![image-20211119095401318](images\function-order.jpg)





### 有参函数

![image-20211119095401318](images\function-args.png)

1. 声明语法

```js
function 函数名(参数列表){
  函数体
}
```



1. 参数列表

   传入数据列表

   声明这个函数需要传入几个数据

   多个数据用逗号隔开

   ![image-20211119095401318](images\call-function-args.png)



​      ![image-20211119095401318](images\params.png)

```html
<script>
  console.log(`测试：js 没有方法重载  只有方法覆盖`)
  // js 没有方法重载  只有方法覆盖
  function sayHello(name) {
    console.log(`${name}说 大家好！`)
  }

  function sayHello(name, cut) {
    console.log(`${name}说 ${cut}大家好！`)
  }

  /* sayHello(`张三`)
sayHello(`张三`,10) 
sayHello()*/

  sayHello(`张三`)
</script>
```



### 随堂练习5：

让用户输入开车时速，以及开了多少小时的车，计算出这辆车跑了多少公里 

要求：计算部分封装成函数

效果如下：

![image-20211119095401318](images\ex5-rs.png)



思路：

1. 用户输入开车时速
2. 用户输入小时
3. 计算公式部分要封装到函数里面
4. 命名： 时速 speed 小时 hour 函数命名 getDistance

```html
<h1>计算汽车开了多少公里</h1>

<script>
  let speed = prompt(`请输入时速`, `80`)
  let hour = prompt(`请输入小时`, `3`)

  function getDistance(speed, hour) {
    document.write(
      `<p>时速${speed},开了${hour}小时,一共${speed * hour}公里</p>`
    )
  }

  getDistance(speed, hour)
</script>
```



### 有返回值的函数

目标：掌握有返回值的函数，把函数处理结果给调用者

1. 为什么要让函数有返回值
2. 用 return 返回数据



细节：

   在函数体中使用 return 关键字能将内部的执行结果交给函数外部使用

   函数内部只能出现 1 次 return，并且 return 后面代码不会再被执行，所以 return 后面的数据不要换行写 

  return会立即结束当前函数

  函数可以没有 return，这种情况默认返回值为 undefined

```html
<script>
  function add(n1, n2) {
    if (!n1){
      return alert(`无效数字`)
    }
    /* if (!n1) {
      n1 = 0
    }
    if (!n2) {
      n2 = 0
    } */
    return n1 + n2
  }

  // NaN  Not a Number
  let rs = add()
  // let rs = add(10)
  // let rs = add(100,200)
  console.log(`结果:${rs}`)
</script>
```



### 内置函数

JavaScript内部封装好的一些函数，让开发者直接调用就完成对应功能

例：prompt() alert() document.write() Number() Boolean()   parseInt()  parseFloat() 

内置函数带括号的，意味着可以传递参数



# 3. 对象

## 3.1 语法

###  对象的声明语法

```js
let 对象名 = { }
```

## 3.2 属性

数据描述性的信息称为属性，如人的姓名、身高、年龄、性别等，一般是名词性的。 

属性都是成 对出现的，包括属性名和值，它们之间使用英文 : 分隔

多个属性之间使用英文 , 分隔

属性就是依附在对象上的变量（外面是变量，对象内是属性）

属性名可以使用 "" 或 ''，一般情况下省略，除非名称遇到特殊符号如空格、中横线等



```html
<script>
  //定义 stu 对象
  let stu = {
    // 属性名 :  属性值
    sid: 'S101',
    sname: 'zhang3',
  }
  //属性值覆盖
  stu.sname = 'li4'
  //添加属性
  stu.age = 18

  //获得属性
  console.log(`学号:${stu.sid},姓名:${stu.sname}`)
  console.log(`学号:${stu['sid']},姓名:${stu['sname']}`)

  console.log(stu)

  // let prop = prompt(`请输入stu的属性名`, `sid`)
  // console.log(`学生的信息属性名${prop},属性值:${stu[prop]}`)
</script>
```



### 随堂练习6:

请声明一个产品对象，里面包如下信息：

要求：

1. 对象是一个产品信息可以命名为： goods
2. ​                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              商品名称命名为： name
3. 商品编号： num
4. 商品毛重： weight
5. 商品产地： address
6. 自定义方法：toString() 显示商品信息

```html
<script>
  let goods = {
    name: `苹果`,
    num: `G001`,
    weight: 50,
    address: `山东`,
  }

  console.log(
    `名称:${goods.name},编号:${goods.num},重量:${goods['weight']},产地:${goods.address}`
  )
</script>
```



### 属性访问

声明对象，并添加了若干属性后，可以使用 . 或 [] 获得对象中属性对应的值，我称之为属性访问。

简单理解就是获得对象里面的属性值。





###  增加属性

也可以动态为对象添加属性，动态添加与直接定义是一样的，只是语法上更灵活。





## 3.3 方法

对象中的方法

数据行为性的信息称为方法，如跑步、唱歌等，一般是动词性的，其本质是函数。

1. 方法是由方法名和函数两部分构成，它们之间使用 : 分隔
2. 多个属性之间使用英文 , 分隔
3. 方法是依附在对象中的函数
4. 方法名可以使用 "" 或 ''，一般情况下省略，除非名称遇到特殊符号如空格、中横线等



### 访问方法

对象中的方法访问

声明对象，并添加了若干方法后，可以使用 . 调用对象中函数，我称之为方法调用。



### 新增方法

新增对象中的方法

也可以动态为对象添加方法，动态添加与直接定义是一样的，只是语法上更灵活

注：无论是属性或是方法，同一个对象中出现名称一样的，后面的会覆盖前面的。

```html
<script>
  //定义方法
  let stu = {
    sname: 'zhang3',
    study: function () {
      console.log(`========study=======`)
    },
    sayHi: function (cut) {
      return `${this.sname}说${cut}次hello...`
    },
  }

  //添加方法
  stu.sleep = function () {
    console.log(`======sleep=============`)
  }

  //调用
  stu.study()
  stu.sleep()

  let msg = stu.sayHi(5)
  console.log(msg)
</script>
```



## 3.4 null

null 也是 JavaScript 中数据类型的一种，通常只用它来表示不存在的对象。使用 typeof 检测类型它的类型

时，结果为 object。

```html
<script>
  let obj = null
  console.log(obj)
  console.log(typeof obj)
</script>
```



## 3.5 内置对象

### 是什么

JavaScript内部提供的对象，包含各种属性和方法给开发者调用

思考：我们之前用过内置对象吗？

document.write()

console.log()



### 内置对象Math

Math对象是JavaScript提供的一个“数学高手”对象

提供了一系列做数学运算的方法

方法有：

1. random：生成0-1之间的随机数（包含0不包括1） 
2. ceil：向上取整
3. floor：向下取整
4. max：找最大数
5. min：找最小数
6. pow：幂运算
7. abs：绝对值

```html
<script>
  console.log(Math.random())
  console.log(Math.ceil(11.000000));
  console.log(Math.floor(11.99999999999999));
  console.log(Math.max(100,90,80,150,180));
</script>
```
# 4. 流程控制

## 4.1 比较运算符

### 场景

![image-20211119095401318](images\bj.png)

### 语法

```
> ： 左边是否大于右边
< ： 左边是否小于右边
>=： 左边是否大于或等于右边
<=： 左边是否小于或等于右边
==： 左右两边是否相等
===： 左右两边是否类型和值都相等   
!==： 左右两边是否不全等
比较结果为boolean类型，即只会得到true或false
```

```js
let a = '100'
let b = 100

console.log(a==b);
console.log(a===b);
console.log(a!==b);
```



#### 注意

```
1. 字符串比较，是比较的字符对应的ASCII码 
	从左往右依次比较
	如果第一位一样再比较第二位，以此类推
	比较的少，了解即可
2. NaN不等于任何值，包括它本身
   用isNaN函数判断是否为NaN
3. 尽量不要比较小数，因为小数有精度问题
4.不同类型之间比较会发生隐式转换
  最终把数据隐式转换转成number类型再比较
  所以开发中，如果进行准确的比较我们更喜欢 === 或者 !==
```

```js
let a = '100'
 let b = 100

  console.log(a == b)
  console.log(a === b)
  console.log(a !== b)

  let str1 = `abc`
  let str2 = `abb`

  console.log(str1 > str2)

  // is not a number
  console.log(isNaN('abc'))
  console.log(isNaN('288'))

  let price = 3.123456789123451389
  console.log(price);
```



## 4.2 逻辑运算符

逻辑运算符用来解决多重条件判断

| 符号   | 名称   | 日常读法 | 特点                     | 口诀      |
| ---- | ---- | ---- | ---------------------- | ------- |
| &&   | 逻辑与  | 并且   | 符号两边都为true结果才为true     | 一假则假    |
| \|\| | 逻辑或  | 或者   | 符号两边有一个true 就为true     | 一真则真    |
| ！    | 逻辑非  | 取反   | true变false  false变true | 真变假，假变真 |

#### 短路

| 符号   | 短路条件        |
| ---- | ----------- |
| &&   | 左边为false就短路 |
| \|\| | 左边为true就短路  |

#### 短路实战

通过短路 实现参数的完整性

![image-20211119095401318](images\dl.png)



## 4.3 流程控制

### 程序三大流程控制

![image-20211119095401318](images\lckz.png)

### 术语

| 术语      | 解释                   | 举例                                       |
| ------- | -------------------- | ---------------------------------------- |
| 关键字     | 有特殊意义的词汇             | let,var,function,if,else,swtich,case,break |
| 保留字     | 目前没意义，将来有可能特殊意义的词汇   | int,short,long,char                      |
| 标识（标识符） | 变量名,函数名的另一种叫法        | 无                                        |
| 表达式     | 产生值得代码 配合运算符出现       | 10+3   ，  age>=18                        |
| 语句      | 一句代码  输出语句 声明语句 分支语句 | 无                                        |



### 分支结构

#### if语句

if语句有三种使用：单分支、双分支、多分支

```js
if(条件){
    
}
```

```js
if(条件){
    
}else{
    
}

let rs = (布尔值)?true:false
```

```js
if(条件1){
    
}else if(条件2){
    
}else if(条件3){
    
}else{
    
}
```

```js
switch(key){
    case 值:  .... break ;
    case 值:  .... break ;
    default: ...
}
```



#### 随堂练习1:

代码模拟进网吧，询问是否年满18，满18才让进网吧



#### 随堂练习2：

进网吧案例修改为如果年满18就进网吧，否则就去图书馆学习



#### 随堂练习3：

让用户输入年份，判断这一年是闰年还是平年并输出

能被4整除但不能被100整除，或者被400整除的年份是闰年，否则都是平年

把判断部分封装成函数



#### 随堂练习4：

输入分数，根据分数奖励不同的车 

 注：90以上奖励法拉利、70以上奖励大众、60以上奖励奇瑞、60以下打一顿



#### switch语句

```
break作用：立即结束所在的switch语句
break可防止穿透，若不写会发生穿透效果 
穿透效果：从一个case块进入到下一个case块
```



#### 随堂练习5:

输入数字，转换成对应的星期几 

例：输入1，则页面输出星期一，输入2，页面输出星期二



#### 随堂练习6：

穿透可合理运用

输入月份，判断是哪个季节



#### 三元表达式

也是双分支

符号：?与:配合使用

一般用来取值



#### 随堂练习7：

判断两个数谁是更大数



#### 随堂练习8：

月份补0 【 1~9 => 01~09 】





### 赋值运算符

```
赋值运算符：对变量进行赋值的运算符
已经学过的赋值运算符：= 
其他赋值运算符：
+=
-=
*=
/=
%=
```

```java
//java
short s = 9 ;
//error
// s = s+1 ;
// s = (short)(s+1);
s += 1 ;
```



### 一元运算符

```
众多的 JavaScript 的运算符可以根据所需表达式的个数，分为一元运算符、二元运算符、三元运算符
1. 二元运算符：需要两个表达式才能进行运算
   例：  let num = 10 + 20

2. 三元运算符：需要三个表达式才能进行运算
   例:   let max = 10>5?10:5
   
3.一元运算符：需要一个表达式就可以进行运算
   已经学过的运算符：!
   例：  let res = !true
  
  自增：[前置自增/后置自增]
    符号：++ 
    作用：让变量的值 +1
  自减：
    符号：--
    作用：让变量的值 -1 l 使用场景：
  经常用于计数来使用。 比如进行10次操作，用它来计算进行了多少次了
```



### 循环结构

#### while 循环

![image-20211119095401318](images\while.png)





#### 随堂练习9：

在页面中打印输出10句“月薪过万”

要求：

1. 使用while循环
2. 页面中打印，可以添加换行效果

思考：

 能不能改进，让用户输入打印输出的个数呢？



#### do-while 循环

先执行do 里面的语句

执行完毕后，到 while里面判断条件是否满足，

如果满足则回头继续执行do里面的语句，

如果条件不满足，则退出循环



#### 随堂练习10：

利用do-while 求 1~ 10的和





#### for 循环

把声明起始值、循环条件、变化值写到一起，让人一目了然





#### 随堂练习11：

求1-100之间所有数的平均值



#### for循环嵌套

![image-20211119095401318](images\for-loop.png)



#### 随堂练习12：

请打印5行5列的星星



#### 循环退出：

continue：结束本次循环，继续下次循环

 break：跳出所在的循环





# 5.数组

## 5.1数组是什么

数组(Array)是一种可以按顺序保存数据的数据类型

为什么要数组？

思考：如果我想保存一个班里5个人的姓名怎么办？ 

​           如果有多个数据可以用数组保存起来





## 5.2数组使用

### 声明

语法：

```js
let 数组名 = [数据1，数据2，...,数据N]
```



```
数组是按顺序保存，所以每个数据都有自己的编号
l 计算机中的编号从0开始，所以小明的编号为0，小刚编号为1，以此类推
l 在数组中，数据的编号也叫索引或下标
l 数组可以存储任意类型的数据
```



###  遍历数组

   用循环把数组中每个元素都访问到,一般会用for循环遍历



### 随堂练习13：

求数组 [2,6,1,7, 4] 里面所有元素的和以及平均值

思路：

1. 声明一个求和变量 sum。
2. 遍历这个数组，把里面每个数组元素加到 sum 里面。
3. 用求和变量 sum 除以数组的长度就可以得到数组的平均值。



### 数组和对象

```
1. 数组也属于对象类型
	数组返回的数据类型是 object
	对象也可以通过 [] 访问属性值 
	对象也可以被遍历
遍历对象
	一般不用这种方式遍历数组、主要是用来遍历对象
	一定记住： k 是获得对象的属性名， 对象名[k] 是获得 属性值
```

![image-20211119095401318](images\loop-obj.png)





### 随堂练习14：

随机点名：

1. 把咱们班同学的名字都放到数组里面
2. 利用随机函数，随机抽取一个同学



### 随堂练习15：

请把下面数据中的学生姓名分别打印到页面中

```js
// 定义一个存储了若干学生信息的数组
let students = [
{name: '小明', age: 18, gender: '男', hometown: '河北省'},
{name: '小红', age: 19, gender: '女', hometown: '河南省'},
{name: '小刚', age: 17, gender: '男', hometown: '山西省'},
{name: '小丽', age: 18, gender: '女', hometown: '山东省'}
];
```

![image-20211119095401318](images\stu-list.png)



## 5.3 数组方法

```
数组做为对象数据类型，不但有 length 属性可以使用，还提供了许多方法
1. push() 动态向数组的尾部添加一个单元
2. unshift() 动态向数组头部添加一个单元
3. pop() 删除最后一个单元
4. shift() 删除第一个单元
5. splice() 动态删除任意单元
6. join() 指定字符 拼接成字符串
```

### 随堂练习16：

随机点名案例完善：

1. 把咱们班同学的名字都放到数组里面
2. 利用随机函数，随机抽取一个同学
3. 已经点过名的同学不允许再次点到（抽取了就删除数组）





### 随堂练习17：

体验根据数组生成网页数据

![image-20211119095401318](images\course-list.png)
