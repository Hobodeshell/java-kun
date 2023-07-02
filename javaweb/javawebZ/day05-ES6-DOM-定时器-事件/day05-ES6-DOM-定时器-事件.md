#  1. ES6

## 1.1 let声明变量

```js
var 声明变量
1. 变量重复定义
// var name='zhang3'
// var name = 'li4'
let name='zhang3'
//let name = 'li4'
// Identifier 'name' has already been declared
console.log("=======name:"+name)
2. 作用域自动提升
{
  //var age = 18
  let age = 18
}
//Uncaught ReferenceError: age is not defined
console.log("========age:"+age)
3. 执行顺序
console.log('==========address:' + address)
//Cannot access 'address' before initialization
// var address = '南京'
let address = '南京'
```

## 1.2 const常量

```js
//1.常量不能被重新赋值
const SCHOOL = '万和'
//Assignment to constant variable.
// SCHOOL = '万和'
console.log('========' + SCHOOL)

//2.对象类型常量  对象的属性可以被改变
const PERSON_ARRAY = ['zhang3', 'li4']
PERSON_ARRAY.push('wang5')
console.log('================' + PERSON_ARRAY)
```

## 1.3 解构赋值

```js
//1.数组的解构赋值
  const PERSON_ARRAY = ['ZHANG3', 'LI4', 'WANG5']
  //传统方式
  //let name1 = PERSON_ARRAY[0]
  //let name2 = PERSON_ARRAY[1]
  //解构赋值  [] 数组  通过索引对应
  let [name1, name2] = PERSON_ARRAY
  console.log('name1:' + name1 + ',name2:' + name2)

  //2.对象解构赋值
  const PERSON = {
    name: 'zhang3',
    age: 18,
    study: function () {
      console.log('=========study()===')
    },
  }
  //传统的方式
  // let name = PERSON.name
  // let age = PERSON.age
  // let study = PERSON.study
  // 对象的解构赋值 {} 通过属性名同名  顺序无关
  let { name, age, study } = PERSON
  console.log('name:' + name + ',age:' + age)
  study()
```

## 1.4 模板字符串

```js
//ES6之前
let s = 'data'
let str =
' <ul>\
            <li>' + s + '</li>\
          </ul>'
console.log(str)
//ES6
let str2 = `<ul>
        <li>${s}</li>
      </ul>`
console.log(str2)
```

## 1.5 对象简写

```js
 //ES6之前
  let name = 'zhang3'
  let age = 18
  let study = function () {
    console.log('=======study()====')
  }
  //传统方式
  //let stu = { name: name, age: age, study: study }
  //对象简写 属性名 = 变量名
  let stu = { name, age, study }
  console.log(`姓名:${stu.name},年龄:${stu.age}`)
  stu.study()
```

## 1.6 对象定义方法简写

```js
 //对象方法定义简写
  let stu = {
    // study: function () {
    //   console.log('=====stu=====study()=====')
    // },
    study() {
      console.log('=====stu=====study()=====')
    },
  }
  //调用方法
  stu.study()
```

## 1.7 参数默认值

```js
  function add(a, b, c = 0) {
    return a + b + c
  }
  //调用函数
  let rs = add(100, 200)
  console.log(`计算结果:${rs}`)

  //参数默认值 + 对象解构赋值
  function printStu({ sid, sname, age = 18 }) {
    console.log(`学号:${sid},姓名:${sname},年龄:${age}`)
  }
  let stu = { sid: 'S1', sname: 'zhang3' }
  printStu(stu)
```

## 1.8 对象扩展运算符

```js
 let person1 = { name: 'zhang3', age: 18 }
  // let person2 = person1
  // person2.name = 'li4'
  // console.log(`person1的name:${person1.name}`)

  let person3 = { ...person1 }
  person3.name = 'li4'
  console.log(`person1的name:${person1.name},person3的name:${person3.name}`)
```

## 1.9 箭头函数

```js
 function f1() {
    console.log('=====f1()==========')
  }
  f1()
  let f2 = function() {
    console.log('=====f2()==========')
  }
  f2()
  //箭头函数  (参数列表)=>{方法体}  参数列表只有一个() 可省略  方法体只有一条语句 {} 省略
  let f3 = a => console.log(a)
  f3('abc')
  let f4 = ()=>{
    console.log("====111111111111")
    console.log("====2222222")
  }
  f4()

  let f5 = (a,b)=>console.log(`a的值${a},b的值${b}`)
  f5(100,200)

  let ay = ['aaa','bb','cccc','ddd']
 // ay.forEach(e=>console.log(e))
 ay.forEach((e,i)=>console.log(`元素:${e},索引:${i}`))
```

# 2. DOM

## 2.1 DOM概念

### 什么是DOM

```
DOM是浏览器提供的一套专门用来操作网页内容的功能
1. DOM的核心思想
	把网页内容当做对象来处理
2. DOM作用
	开发网页内容特效和实现用户交互
3. DOM全称
	Document Object Model（文档对象模型）
```



### DOM树

```
DOM树是什么
1. 将 HTML 文档以树状结构直观的表现出来，我们称之为文档树或 DOM 树
2. 描述网页内容关系的名词
3. 作用：文档树直观的体现了标签与标签之间的关系
```

![image-20210806085831519](images\dom.png)



### DOM节点

```
DOM节点
	DOM树里每一个内容都称之为节点
节点类型
	元素节点
		<body>  <div>  <html>
		所有的标签 比如 body、 div
 		html 是根节点
	属性节点
	    <a href="1.html">...</a>
		所有的属性 比如 href
	文本节点
		所有的文本 
        <div>div</div>
	其他
```



### document

```
是 DOM 里提供的一个对象
所以它提供的属性和方法都是用来访问和操作网页内容的
	例：document.write()
代表浏览器显示网页内容的区域
网页所有内容都在document里面
document 是学习 DOM 的核心
```

## 2.2 查找元素节点

### 目标

```
提问：我们想要操作某个标签首先做什么？
	肯定首先选中这个标签，跟 CSS选择器类似，选中标签才能操作
HTML 中任意标签都是【元素类型节点】
所以： 查找元素节点就是选择页面中标签元素

学习路径：
1.根据 id 来查找dom元素节点
2.查找页面中的 html 元素节点和 body 元素节点
```

### id查找dom元素节点

语法：

```js
let node = document.getElementById("id属性值")
```

```
根据id查找标签
传入的id是字符串，记得加引号，直接写id名即可，不需要加 #
返回一个匹配到 ID 的 DOM Element 对象（所有节点都是对象）
找不到会得到null
可以通过对象里面的 nodeType 属性来标识节点类型
```

示例：

```html
 <input type="text" id="name" name="username" value="admin" />
    <div id="d1">div....</div>
    <script>
      let inputComp = document.getElementById('name')
      console.log(inputComp)

      let divComp = document.getElementById('d1')
      console.log(divComp)
    </script>
```

### 查找html,body元素节点

```js
// 查找 html 元素
let htmlNode = document.documentElement

// 查找 body 元素
let bodyNode = document.body
```



## 2.3 操作元素节点

### 操作元素属性

```js
// 1.直接修改元素的属
元素节点.属性 = 值
```

```js
//2.通过 setAttribute 方法修改
元素节点.setAttribute("属性名",属性值)
```

```html
<input type="text" id="name" value="admin" />
<script>
  //1.获得元素节点
  let inputComp = document.getElementById('name')
  //2. 获得属性值
  console.log(
    `值:${inputComp.value},id:${inputComp.id},type:${inputComp.type}`
  )

  console.log(
    `值:${inputComp.getAttribute('value')},id:${inputComp.getAttribute(
      'id'
    )},type:${inputComp.getAttribute('type')}`
  )

  //3.设置属性
  // inputComp.type = `radio`
  // inputComp.value = `张三疯`
  //inputComp.setAttribute('type', `radio`)
  inputComp.setAttribute(`value`,`li4`)
</script>
```

```js
//3.修改样式
元素节点.style.样式属性名 = 值
注意：
  修改样式通过style属性引出
  如果属性有-连接符，需要转换为小驼峰命名法
  赋值的时候，需要的时候不要忘记加css单位
```

```html
<input type="text" id="name" value="admin" style="width: 80px;"/>
<script>
  //1.获得元素节点
  let inputComp = document.getElementById('name')
  //console.log(inputComp.style.width);
  inputComp.style.width = `800px`
  inputComp.style.fontSize = `80px`
</script>   
```



### 操作元素文本

```js
1. document.write
	只能将文本内容追加到</body> 前面的位置
	文本中包含的标签会被解析
```

```html
<script>
      document.write(`<h1>hello...</h1>`)
</script>
```

```js
2. innerText 属性
    将文本内容添加/更新到任意标签位置
    文本中包含的标签不会被解析
```

```js
3. innerHTML 属性
    将文本内容添加/更新到任意标签位置
    文本中包含的标签会被解析
```

```html
 <p id="p1">p段落</p>

    <ul id="qa">
      <li>data1</li>
      <li>data2</li>
      <li>data3</li>
    </ul>
    <script>
      // document.write(`<h1>hello...</h1>`)

      //1.获得元素
      let pcomp = document.getElementById(`p1`)
      //2.获得包裹内容
      console.log(`包裹内容: ${pcomp.innerText}`)
      console.log(`包裹内容: ${pcomp.innerHTML}`)

      console.log(`====================`)

      let upComp = document.getElementById(`qa`)
      console.log(upComp.innerText)
      console.log(upComp.innerHTML)
    </script>
```

### 随堂练习1：

```
页面随机变化背景
需求：每次刷新页面背景图片都会随机生成
分析：
①：每次刷新页面，页面都会显示不同的页面背景
②：用到随机函数 Math.random()
```

```html
<script>
      //1.获得 元素组件
      let bodyComp = document.body
      //获得 1 ~ 10   Math.floor(Math.random()*10)+1   Math.ceil(Math.random()*10)
      let rand = Math.floor(Math.random() * 10) + 1
      //2.设置 style 属性
      bodyComp.style.backgroundImage = `url(./res/desktop_${rand}.jpg)`
</script>
```



# 3. 时间对象

![image-20210806085831519](images\date.png)

## 3.1 实例化

```js
在代码中发现了 new 关键字时，一般将这个操作称为实例化
创建一个时间对象并获取时间
// 获得当前时间
let now = new Date()

//获得指定时间
let birthday = new Date('1998-10-05')
```



## 3.2 时间对象方法

| 方法             | 作用       | 说明       |
| -------------- | -------- | -------- |
| getFullYear( ) | 获得年份     | 获取四位年份   |
| getMonth( )    | 获得月份     | 取值为0~11  |
| getDate( )     | 获得月份中第几天 | 月份中第几天   |
| getDay( )      | 获取星期     | 取值 0 ~6  |
| getHours（）     | 获取小时     | 取值 0 ~23 |
| getMinutes（）   | 获取分钟     | 取值 0~59  |
| getSeconds（）   | 获取秒      | 取值 0~59  |

### 随堂练习2：

```
页面显示时间
需求：将当前时间以：YYYY-MM-DD HH:mm 形式显示在页面
分析：
①：调用时间对象方法进行转换
②：字符串拼接后，通过 innerText 给 标签
```

```html
<div id="time"></div>
<script>
  //1.获得 当前时间对象
  let now = new Date()
  //2.调用 方法 获得 年月日  时分秒
  let y = now.getFullYear()
  let m = now.getMonth() + 1
  m = m < 10 ? '0' + m : m
  let d = now.getDate()
  let h = now.getHours()
  let mi = now.getMinutes()
  let s = now.getSeconds()
  //3. 拼接 目标字符串
  let content = `${y}-${m}-${d} ${h}:${mi}:${s}`
  //4. 获得 div 元素组件
  let divComp = document.getElementById('time')
  //5. 进行 div 包裹内容赋值
  divComp.innerText = content
</script>
```



## 3.3 时间戳

```js
//方法一： 日期对象.getTime() 方法

//方法二： +new Date( )

//方法三:  Date.now( ) [只能获得当前时间戳]
```

```js
let t1 = +new Date()
console.log(t1);

let now = new Date()
let t2 = now.getTime()
console.log(t2);

let t3 = Date.now()
console.log(t3);
```



### 随堂练习3：

```
阶段倒计时效果
需求：计算到阶段结束还有多少时间，格式：xx天xx小时xx分钟xx秒
分析：
①：用将来时间减去现在时间就是剩余的时间
②：核心： 使用将来的时间戳减去现在的时间戳
③：把剩余的时间转换为 天 时 分 秒

注意：
通过时间戳得到是毫秒，需要转换为秒在计算
转换公式：
 d = parseInt(总秒数/ 60/60 /24);    //  计算天数
 h = parseInt(总秒数/ 60/60 %24)   //   计算小时
 m = parseInt(总秒数 /60 %60 );     //   计算分数
 s = parseInt(总秒数%60);               //   计算当前秒数
```

参考：

```html
<div id="d1"></div>
<script>
  //1.当前时间
  let beginNow = new Date()
  //2.毕业时间
  let endNow = new Date(2022, 11, 8, 17, 30, 0)
  // let endNow = new Date(`2022-12-08 17:30:00`)
  //console.log(endNow.toLocaleString())
  //3. 计算毫秒  11183368  11158667  11122389
  let time = endNow - beginNow
  let sec = time / 1000

  d = parseInt(sec / 60 / 60 / 24) //  计算天数
  h = parseInt((sec / 60 / 60) % 24) //   计算小时
  m = parseInt((sec / 60) % 60) //   计算分数
  s = parseInt(sec % 60) //   计算当前秒数

  //4.获得 div 元素
  let divElt = document.getElementById('d1')
  divElt.innerHTML = `距离结束:${d}天${h}小时${m}分数${s}秒`
</script>
```



# 4. 定时器函数

## 4.1 介绍

![image-20210806085831519](images\timer.gif)

## 4.2 基本使用

### 开启定时器

```js
//作用：每隔一段时间调用这个函数
//间隔时间单位是毫秒
setInterval(函数名,间隔时间)
// 注意：函数名字不需要加括号
```

```html
<script>
  //ES6 箭头函数  [匿名函数]
  setInterval(() => console.log(`hello....`), 3000)

  // 具名函数
  let f = () => console.log(`hello....`)

  setInterval(f, 1000)
</script>
```



### 随堂练习4：

```
页面显示时间自动显示
```

```html
<div id="time"></div>
<script>
  setInterval(() => {
     //1.获得 当前时间对象
    let now = new Date()
    //2.调用 方法 获得 年月日  时分秒
    let y = now.getFullYear()
    let m = now.getMonth() + 1
    m = m < 10 ? '0' + m : m
    let d = now.getDate()
    let h = now.getHours()
    let mi = now.getMinutes()
    let s = now.getSeconds()
    //3. 拼接 目标字符串
    let content = `${y}-${m}-${d} ${h}:${mi}:${s}`
    //4. 获得 div 元素组件
    let divComp = document.getElementById('time')
    //5. 进行 div 包裹内容赋值
    divComp.innerText = content
  }, 1000)
</script>
```





### 关闭定时器

```js
//一般不会刚创建就停止，而是满足一定条件再停止
let 定时器对象 = setInterval(函数名,间隔时间)
clearInterval(定时器对象)
```

```html
<button id="btn" disabled>我已经阅读协议(10)</button>
<script>
  // 倒计时时间
  let cut = 10

  //设置定时器
  let timer = setInterval(() => {
    cut--
    //1.获得组件
    let btnComp = document.getElementById('btn')
    //cut 为 0 清除定时器
    if (cut === 0) {
      btnComp.disabled = false
      btnComp.innerText = `注册`
      return clearInterval(timer)
    }
    //2. 设置 按钮 包裹内容
    btnComp.innerText = `我已经阅读协议(${cut})`
  }, 1000)
</script>
```



### 随堂练习5：

倒计时效果

![image-20210806085831519](images\clear-timer.png)



```
需求：按钮60秒之后才可以使用
分析：
①：开始先把按钮禁用（disabled 属性）
②：一定要获取元素
③：函数内处理逻辑
	秒数开始减减
	按钮里面的文字跟着一起变化
	如果秒数等于0 停止定时器 里面文字变为 获取验证码  最后 按钮可以点击
```

```html
 <input type="text" /><button id="sendSMSBtn" disabled>6S</button>

    <script>
      let cut = 6
      let btnElt = document.getElementById(`sendSMSBtn`)

      let timer = setInterval(() => {
        cut--
        btnElt.innerText = `${cut}S`
        if (cut === 0) {
          clearInterval(timer)
          btnElt.innerText = `发送验证码`
          btnElt.disabled = false
        }
        console.log(`==============`)
      }, 1000)
    </script>
```



### 随堂练习6:

```
网页轮播图效果
需求：每隔一秒钟切换一个图片
分析：
①：获取元素（图片和文字）
②：设置定时器函数
	设置一个变量++
	更改图片张数
	更改文字信息
③：处理图片自动复原从头播放
	如果图片播放到最后一张就是第9张
	则把变量重置为0
	注意逻辑代码写到图片和文字变化的前面
```

```html
<style>
      .img-box {
        width: 700px;
        height: 320px;
        background: #000;
        margin: 50px auto 0;
        position: relative;
      }

      .img-box .tip {
        width: 700px;
        height: 53px;
        line-height: 53px;
        position: absolute;
        bottom: 0px;
        z-index: 10;
        background-color: rgba(0, 0, 0, 0.5);
      }

      .img-box .tip h3 {
        width: 82%;
        margin: 0;
        margin-right: 20px;
        margin-left: 20px;
        color: #98e404;
        font-size: 28px;
        float: left;
        font-weight: 400;
      }
    </style>
  </head>
  <body>
    <div class="img-box">
      <img src="./res/b01.jpg" id="pic" />
      <div class="tip">
        <h3 id="info">第1张图的描述信息</h3>
      </div>
    </div>
    <script>
      let idx = 1

      setInterval(() => {
        //获得元素
        let imgComp = document.getElementById('pic')
        let infoComp = document.getElementById('info')
        if (idx > 9) {
          idx = 1
        }
        imgComp.src = `./res/b0${idx}.jpg`
        infoComp.innerText = `第${idx}张图的描述信息`
        idx++
      }, 500)
    </script>
```





# 5. 函数进阶

## 5.1 函数表达式

### 具名函数

```js
//具名函数
function 函数名(){
  ...
}
  
//调用函数
 函数名()
```



### 匿名函数

```js
// 将函数赋值给变量 【函数表达式】
let 变量 = function(){
  ....
}
```



### 注意：

```js
函数表达式和普通函数并无本质上的区别：
1. 普通函数的声明与调用无顺序限制，推荐做法先声明再调用
2. 函数表达式必须要先声明再调用
```

```html
 <script>
  let f1 = function () {
    console.log(`=====f()========`)
  }

  //调用
  f1()

  // 声明
  /* function f1() {
    console.log(`=====f()========`)
  } */
</script>
```





## 5.2 回调函数

### 介绍

```js
把函数当做另外一个函数的参数传递，这个函数就叫回调函数
回调函数本质还是函数，只不过把它当成参数使用
使用匿名函数做为回调函数比较常见
```

### 场景

```
如果将函数 A 做为参数传递给函数 B 时，我们称函数 A 为回调函数
简单理解： 当一个函数当做参数来传递给另外一个函数的时候，这个函数就是回调函数
```



![image-20210806085831519](images\fn-callback.png)

![image-20210806085831519](images\fn-call2.png)

![image-20210806085831519](images\fn-call3.png)

# 6. 环境对象

```
环境对象指的是函数内部特殊的变量 this ，它代表着当前函数运行时所处的环境
作用：弄清楚this的指向，可以让我们代码更简洁
1. 函数的调用方式不同，this 指代的对象也不同
2.【谁调用， this 就是谁】 是判断 this 指向的粗略规则
3. 直接调用函数，其实相当于是 window.函数，所以 this 指代 window
```



# 7. DOM进阶

## 7.1 事件

```
事件是编程语言中的术语，它是用来描述程序的行为或状态的，一旦行为或状态发生改变，便立即调用一个函数。
例如：在网页中用户【鼠标点击】一个按钮，就叫触发了一个事件
```



## 7.2 事件监听

```
什么是事件监听？
	就是让程序检测是否有事件产生，一旦有事件触发，就立即调用一个函数做出响应
语法：
	元素.addEventListenter(事件类型,事件触发时调用的回调函数)
	注意：事件类型要加引号
事件监听三要素：
	事件源：  那个dom元素被事件触发了，要获取dom元素
	事件类型： 用什么方式触发，比如鼠标单击 click、鼠标经过 mouseover 等
	事件调用的回调函数： 要做什么事
```



### 随堂练习7：

```
淘宝点击关闭二维码
需求：点击关闭之后，淘宝二维码关闭
分析：
①：点击的是关闭按钮
②：关闭的是父盒子
核心：利用样式的显示和隐藏完成， display:none 隐藏元素 display:block 显示元素
```

![image-20210806085831519](images\code.png)

```html
<img src="./code.png" alt="" id="img" /> <button id="btn">关闭</button>

<script>
  //1.获得元素
  let btnElt = document.getElementById('btn')
  let imgElt = document.getElementById('img')
  //2.注册事件
  btnElt.addEventListener(`click`, () => {
    if (btnElt.innerText === `关闭`) {
      imgElt.style.display = `none`
      btnElt.innerText = `显示`
      return
    }
    imgElt.style.display = `block`
    btnElt.innerText = `关闭`
    console.log(`=====关闭=========`)
  })
</script>
```



```
事件中的this
任何函数内都有this，事件触发时的回调函数里也不例外
事件回调函数里的 this 指向的是 当前被添加事件监听的DOM元素对象
简单理解【给哪个元素调用的addEventListener，回调函数里的this就是哪个元素】
```



### 随堂练习8：

```
点按钮获取验证码倒计时
```

![image-20210806085831519](images\timer.gif)



## 7.3 查找节点

```js
document.getElementsByName('name属性值')             //根据name属性值查找所有元素
document.getElementsByTagName('标签名')             //根据标签名查找所有元素
document.querySelector('选择器');                   // 根据指定选择器返回第一个元素对象
document.querySelectorAll('选择器');                // 根据指定选择器返回所有元素
以上2个方法会得到一个伪数组，找到几个元素长度就为几
querySelector 和 querySelectorAll里面的选择器需要加符号
   比如:document.querySelector('#nav'); 
```



### 随堂练习9：

```js
随机问题案例
需求：点击开始随机抽取，点击结束输出结果
业务分析：
1.点击开始按钮随机抽取数组的一个数据，放到页面中
2.点击结束按钮删除数组当前抽取的一个数据
3.当抽取到最后一个数据的时候，两个按钮同时禁用
核心：利用定时器快速展示，停止定时器结束展示
```

![image-20210806085831519](images\rand-qa.png)



## 7.4 节点样式

```js
更新DOM元素样式有三种方法
1. 元素.style.样式名 =  样式值
	缺点：如果要同时设置多个样式会比较繁琐
2. cssText
	通过cssText属性，可以一次设置多个样式
	缺点：如果这类样式多个元素要用，改动麻烦
3. className
	本质是先写好类样式，再通过className修改元素拥有这个类
	缺点：需要预先写样式
```

```html
<style>
  .big{
    width: 800px;
  }
</style>
</head>
<body>
<input type="text" />
<button>style</button>
<button>cssText</button>
<button>className</button>
<script>
  //获得事件源
  let btnComp = document.querySelectorAll(`button`)
  let inputComp = document.querySelector(`input`)

  btnComp[0].addEventListener(`click`, () => {
    inputComp.style.width = `800px`
    console.log(`style`)
  })

  btnComp[1].addEventListener(`click`, () => {
    inputComp.style.cssText = `width:800px`
    console.log(`cssText`)
  })

  btnComp[2].addEventListener(`click`, () => {
    inputComp.className = `big`
    console.log(`className`)
  })
</script>
```



### 随堂案例10：

```
Tab栏切换案例
```

![image-20210806085831519](images\nav.png)



## 7.5 自定义属性

```
在 HTML 中除了标签的【标准属性】外，开发者还可以给标签【自定义属性】
	例：img的标准属性为src、alt等，但若写一个 data-info 则data-info叫自定义属性
作用：一般用来存储额外数据辅助完成某个功能
规范：自定义属性一律以 data- 开头，后面接名字，且名字中若多个单词用-隔开
	例：data-info 、data-label、data-login-name等
	理由：方便区分什么是【标准属性】，什么是【自定义属性】
JavaScript操作自定义属性：dataset
	元素.dataset.自定义属性名
	注意：不用加data-，后面遵守驼峰命名法
	例：某元素的自定义属性叫 data-label，则 元素.dataset.label 
	通过dataset可以获取自定义属性也可以重新赋值，若赋值的自定义属性不存在时则自动添加
```

```html
<table border="1">
  <tbody>
    <tr>
      <td>data1</td>
      <td>data2</td>
      <td>
        <button data-id="U1">查看</button>
      </td>
    </tr>
    <tr>
      <td>data3</td>
      <td>data4</td>
      <td><button data-id="U2">查看</button></td>
    </tr>
    <tr>
      <td>data5</td>
      <td>data6</td>
      <td><button data-id="U3">查看</button></td>
    </tr>
  </tbody>
</table>

<script>
  //获得所有的 button 按钮
  let btnEltAy = document.querySelectorAll(`button`)

  // 循环所有button
  btnEltAy.forEach((btnElt) => {
    //注册点击事件
    btnElt.addEventListener(`click`, () => {
      console.log(
        `=====click====${btnElt.dataset.id}======${btnElt.getAttribute(
          'data-id'
        )}====`
      )
    })
  })
</script>
```

