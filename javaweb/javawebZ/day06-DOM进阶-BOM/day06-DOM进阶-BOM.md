# 1. DOM进阶

## 1.1 DOM事件

### 行内事件

```
什么是行内事件？
     监听一个元素的事件，也可以写在标签内，这种写法称之为行内事件
语法：
    <元素名 on事件名="js代码">
    
此时也可以给函数传参
    注：此时函数内的this是window
```

```html
<button onclick="f()">按钮</button> <br />
<button onclick="console.log(`行内事件 点击...222`)">按钮</button>
<br />
<button
  onclick="if(`aa`){console.log(`行内事件 点击...33`)}else{console.log(`行内事件 点击...44`)}"
>
  按钮
</button>
<br />

<script>
  let f = () => console.log(`行内事件 点击..111.`)
</script>
```



### DOM L0事件

```
DOM L0 指的是 DOM 刚诞生时的版本
语法：
   元素.on事件名 = 调用的函数
   
这种写法就是在事件类型前固定加on，即监听此类事件
还是有事件三要素： 事件源、事件类型、处理程序

我们之前学的 addEventListener 是 L2 事件（推荐使用）   
```

```html
<button>按钮</button>
<script>
  //1.获得事件源
  let btnComp = document.querySelector(`button`)
  //2. DOM L0方式   只能绑定一次
  btnComp.onclick = () => {
    console.log(`你小子点我干嘛 111 ??`)
  }

  btnComp.onclick = () => {
    console.log(`你小子点我干嘛 222 ??`)
  }
</script>
```



### DOM L0 VS L2

```
DOM L0 和 DOM L2 区别
DOM L0 写法： 相同事件类型只能绑定一次[onXX的属性值覆盖]
DOM L2 写法： 相同事件类型可以绑定多次
```

```html
<button>按钮</button>
<script>
  //1.获得事件源
  let btnComp = document.querySelector(`button`)
  //2. DOM L0方式   只能绑定一次
  btnComp.onclick = () => {
    console.log(`DOM L0方式   只能绑定一次  你小子点我干嘛 111 ??`)
  }

  btnComp.onclick = () => {
    console.log(`DOM L0方式   只能绑定一次 你小子点我干嘛 222 ??`)
  }

  //3. DOM L2方法 可以绑定多次
  btnComp.addEventListener(`click`, () => {
    console.log(` DOM L2方法 你小子点我干嘛 111 ??`)
  })

  btnComp.addEventListener(`click`, () => {
    console.log(` DOM L2方法 你小子点我干嘛 222 ??`)
  })
</script>
```



## 1.2 事件移除

### L0 事件移除

```
元素.on事件名 = null 
```

```html
<button>打</button>
<script>
  let btnElt = document.querySelector('button')

  btnElt.onclick = () => {
    console.log(`你打我? 分手`)
    // = null  DOM 取消事件
    btnElt.onclick = null
  }

  /* let f = () => {
    console.log(`你打我? 分手`)
    // = null  DOM 取消事件
    //btnElt.onclick = null
    btnElt.removeEventListener(`click`, f)
  }
  btnElt.onclick = f */
</script>
```

### L2 事件移除

```
元素.removeEventListener("事件类型",回调函数名)
	例如：btn.removeEventListener("click",f1)
注意：
   如果添加事件监听时是用的匿名函数，则无法移除
   通过DOM L0注册的事件  使用DOM L0移除
   通过DOM L2注册的事件  使用DOM L2移除
```

```html
<button>DOM L0 按钮</button>
<button>DOM L0 移除</button>
<hr />
<button>DOM L2 按钮</button>
<button>DOM L2 移除</button>
<script>
  //获得事件源
  let btnCompAy = document.querySelectorAll(`button`)

  //注册 按钮 1  DOM L0
  btnCompAy[0].onclick = () => {
    console.log(`====注册 按钮 1  =========`)
  }

  btnCompAy[1].onclick = () => {
    btnCompAy[0].onclick = null
  }

  //定义函数
  let printInfo = () => {
    console.log(`====注册 按钮 3  =========`)
  }

  //注册 按钮 3  DOM L2
  btnCompAy[2].addEventListener(`click`, printInfo)

  btnCompAy[3].addEventListener(`click`, () => {
    // btnCompAy[2] 添加事件 addEventListenter   移除 removeEventListenter(`事件类型`,函数名)
    // btnCompAy[2].onclick = null
    btnCompAy[2].removeEventListener(`click`, printInfo)
  })
</script>
```

### 随堂练习1：点击切换颜色

```
需求：点击按钮切换文字颜色，点击另外一个按钮移除事件
分析：
  需要一个颜色数组
  点击切换按钮文字变化颜色
  点击移除按钮移除点击切换事件
```

```html
<p>文字</p>
<button>切换颜色</button>
<button>停止切换</button>
<script>
  //1.获得 所有的按钮
  let btnCompAy = document.querySelectorAll(`button`)
  let pcomp = document.querySelector(`p`)
  const COLOR_AY = ['red', 'pink', 'blue']

  //2.定义事件处理函数
  let changeColor = () => {
    let idx = Math.floor(Math.random() * COLOR_AY.length)
    pcomp.style.color = COLOR_AY[idx]
    console.log(`切换颜色`)
  }

  //3.注册 监听 DOM L2
  btnCompAy[0].addEventListener(`click`, changeColor)

  btnCompAy[1].addEventListener(`click`, () =>
    btnCompAy[0].removeEventListener(`click`, changeColor)
  )
</script>
```

## 1.3 事件类型

### 鼠标事件

| 事件类型      | 作用   | 说明         |
| --------- | ---- | ---------- |
| click     | 鼠标单击 | 鼠标点击一次     |
| dblclick  | 鼠标双击 | 快速双击两次     |
| mouseover | 鼠标移入 | 鼠标经过元素触发   |
| mouseout  | 鼠标移出 | 鼠标离开元素触发   |
| mousemove | 鼠标移动 | 鼠标在元素上移动触发 |
| mousedown | 鼠标按下 | 鼠标按下的时候触发  |
| mouseup   | 鼠标弹起 | 鼠标弹起的时候触发  |

```html
<button>按钮1</button><button>按钮2</button><button>按钮3</button
    ><button>按钮4</button><button>按钮5</button><button>按钮6</button
    ><button>按钮7</button>
<script>
  let btnCompAy = document.querySelectorAll(`button`)

  const EVENT_AY = [
    `click`,
    `dblclick`,
    `mouseover`,
    `mouseout`,
    `mousemove`,
    `mousedown`,
    `mouseup`,
  ]

  EVENT_AY.forEach((e, i) => {
    btnCompAy[i].addEventListener(e, () =>
      console.log(event.target.innerText)
    )
  })
</script>
```

### 加载事件

```
语法：
	element.addEventListener(‘load’,function () {});
注意：
	通过window来监听页面加载的情况
	window.addEventListener(‘load’, function ()  {});
	页面所有的外部资源加载完成【html内容执行完毕后】才会触发、和顺序无关 【可以把script写到head中】
```

```html
<script>
  // JS 代码 随便写
  window.addEventListener(`load`, () => {
    //1.获得组件
    let inputComp = document.querySelector(`input`)
    console.log(`姓名:${inputComp.value}`)
  })
</script>
</head>
<body>
<input type="text" value="admin" />
```

### 表单事件

| 表单事件分类 | 事件类型     | 说明              | 说明                                  |
| ------ | -------- | --------------- | ----------------------------------- |
| 焦点事件   | focus    | 获得焦点            | 焦点事件是与鼠标的光标相关的事件类型  input  textarea |
| 焦点事件   | blur     | 失去焦点            |                                     |
| 输入事件   | input    | 输入内容            | 监听文本的输入 input  textarea             |
| 输入事件   | change   | 内容改变            | 只有表单元素中的内容发生改变时才会被触发  大部分表单         |
| 键盘事件   | keydown  | 按下键盘            | 通过 keyCode获得ASII码                   |
| 键盘事件   | keyup    | 键盘释放            |                                     |
| 键盘事件   | keypress | keydown，keyup之间 | 只针对输入字符有效                           |
| 提交事件   | submit   | 触发submit表单提交    | return true 提交action的URL  false 不提交 |

### 正则表达式

```
//正则表达式作用：表单验证，内容替换
作用一：表单验证
  let reg = /^[a-zA-Z0-9_]{6,16}$/;
  let inputName = 'abc';
  let rs = reg.test(inputName) ;
  备注：
  // 正则的开始 结束符
  ^  匹配开始 边界符
  $  匹配结束 边界符
  [] 范围匹配 字符集
  {n,m}最少匹配n次  最多匹配m次  量次符
  
作用二：内容替换
   let reg = /SB/gi
   let msg = '你这个SB 不买眼 不买TP 真sb啊'
   console.log(msg.replace(reg,'**'));
   备注：
   g 全局  i 忽略大小写
   replace字符串的替换方法
```

```html
<form action="">
  姓名: <input type="text" id="name" /> <span id="msg"></span><br />
  密码: <input type="password" id="pswd" /> <br />
  住址:
  <select id="address">
    <option value="">--请选择--</option>
    <option value="NJ">南京</option>
    <option value="BJ">北京</option>
    <option value="SH">上海</option>
  </select>
  <br />
  <button>提交1</button>
  <input type="submit" value="提交2" />
</form>

<script>
  //1. 获得表单控件
  let formElt = document.querySelector(`form`)
  let nameElt = document.querySelector(`#name`)

  //2.注册提交事件
  formElt.addEventListener(`submit`, () => {
    /* let nameLength = nameElt.value.length
    if (nameLength < 2 || nameLength > 4) {
      //阻止表单提交默认行为
      event.preventDefault()
      return alert(`姓名非法`)
    } */
    let reg = /^[a-zA-Z0-9_]{2,4}$/
    if (!reg.test(nameElt.value)) {
      //阻止表单提交默认行为
      event.preventDefault()
      return alert(`姓名非法`)
    }
    console.log(`========提交=======`)
  })
</script>
```

![image-20220816135301893](\images\image-20220816135301893.png)



### 随堂练习2：表单验证

```
要求：
    验证用户名必须为：要求6-12位，只能有大小写字母 数字 _  
        密码必须为： 要求6-20位，只能有大小写字母 数字 _
    确认密码必须为： 与密码一致
    
    验证通过，提交到 action="reg.do"的服务器路径
```

![image-20210806085831519](images\form-submit.png)

```html
<form action="reg.do">
  姓名: <input type="text" id="name" /> <br />
  密码: <input type="password" id="pswd" /> <br />
  确认密码: <input type="password" id="repswd" /> <br />
  <button>提交</button>
</form>

<script>
  //1.获得 form 表单元素
  let formElt = document.querySelector(`form`)
  let nameElt = document.querySelector(`#name`)
  let pswdElt = document.querySelector(`#pswd`)
  let repswdElt = document.querySelector(`#repswd`)

  //2.注册 监听事件
  formElt.addEventListener(`submit`, () => {
    let nameReg = /^\w{6,12}$/
    if (!nameReg.test(nameElt.value)) {
      event.preventDefault()
      return alert(`用户名非法`)
    }
    let pswdReg = /^\w{6,20}$/
    if (!pswdReg.test(pswdElt.value)) {
      event.preventDefault()
      return alert(`密码非法`)
    }

    if (pswdElt.value !== repswdElt.value) {
      event.preventDefault()
      return alert(`两次密码不一致`)
    }
    console.log(`====提交========`)
  })
</script>
```





### 随堂练习3：键盘控制小鸟移动

![image-20210806085831519](images\bird.png)

```html
<style>
  body {
    background: skyblue;
  }

  img {
    width: 100px;
    /* 绝对定位: left 左顶点距左 px  top 左顶点距上 px*/
    position: absolute;
    left: 0;
    top: 0;
  }
  /*CSS3 动画效果 Y轴旋转180度*/
  .toLeft {
    transform: rotateY(180deg);
  }
</style>
</head>

<body>
<img src="./images/xn.png" alt="" />

<script>
  //1. 元素
  let htmlElt = document.documentElement
  let imgElt = document.querySelector(`img`)

  let startX = 0
  let startY = 0

  let step = 10

  //2.注册事件
  htmlElt.addEventListener(`keydown`, () => {
    if (event.keyCode === 39) {
      startX += step
      imgElt.className = ``
    }else  if (event.keyCode === 37) {
      startX -= step
      imgElt.className = `toLeft`
    }else  if (event.keyCode === 38) {
      startY -= step
    }else  if (event.keyCode === 40) {
      startY += step
    }
    imgElt.style.left = `${startX}px`
    imgElt.style.top = `${startY}px`
    console.log(`===注册事件====${event.keyCode}====`)
  })
</script>
```



## 1.4 事件对象

```
事件对象是什么
	也是个对象，这个对象里有事件触发时的相关信息
	例如：鼠标点击事件中，事件对象就存了鼠标点在哪个位置等信息
如何获取
	在事件绑定的回调函数的第一个参数就是事件对象
	一般命名为event、ev、e
部分常用属性
	type
		获取当前的事件类型
	clientX/clientY
		获取光标相对于浏览器可见窗口左上角的位置
	offsetX/offsetY
		获取光标相对于当前DOM元素左上角的位置
```





## 1.5 节点操作

### 创建节点

```
创造出一个新的网页元素，再添加到网页内，一般先创建节点，然后插入节点
创建元素节点方法：
	let 元素对象  = document.createElement('元素名') ;

cloneNode会克隆出一个跟原标签一样的元素，括号内传入布尔值
  若为true，则代表克隆时会包含后代节点一起克隆
  若为false，则代表克隆时不包含后代节点
  默认为false
```

```html
 <ul>
  <li>data1</li>
  <li>data2</li>
  <li>data3</li>
  <li id="data4">data4</li>
  <li>data5</li>
  <li>data6</li>
  <li>data7</li>
  <li>data8</li>
</ul>
<script>
  //1. 创建 元素  [写作业]
  let liElt = document.createElement(`li`)
  liElt.innerHTML = `data...`
  console.log(liElt)

  //2. 克隆 元素  [抄作业]  
  let li4Elt = document.querySelector(`#data4`)
  let cloneElt = li4Elt.cloneNode(true)
  console.log(cloneElt)
</script>
```



### 插入节点

```
要想在界面看到，还得插入到某个父元素中
插入到父元素的最后一个子元素
	父元素.appendChild(要插入的元素)

插入到父元素中某个子元素的前面
    父元素.insertBefore(要插入的元素,在哪个元素前)
```

```html
<ul>
      <li>data1</li>
      <li>data2</li>
      <li>data3</li>
      <li>data4</li>
      <li>data5</li>
    </ul>
    <button>创建元素+添加</button>
    <button>克隆元素+添加</button>
    <script>
      let btnCompAy = document.querySelectorAll(`button`)
      let ulComp = document.querySelector(`ul`)
      let liComp = document.querySelector(`li:nth-child(1)`)

      btnCompAy[0].addEventListener(`click`, () => {
        //创建元素
        let liComp = document.createElement(`li`)
        //添加内容
        liComp.innerText = `data6`
        //添加  [父元素 添加子元素]  尾部添加
        ulComp.appendChild(liComp)
        console.log(`创建元素+添加`)
      })

      btnCompAy[1].addEventListener(`click`, () => {
        //克隆元素
        let cloneLiComp = liComp.cloneNode(true)
        cloneLiComp.id = `xxx`
        //添加  [父元素 添加子元素]  插队
        //ulComp.appendChild(cloneLiComp)
        ulComp.insertBefore(cloneLiComp, liComp)
        console.log(`克隆元素+添加`)
      })
    </script>
```



### 删除节点

```
若一个节点在页面中已不需要时，可以删除它
在 JavaScript 原生DOM操作中，要删除元素必须通过父元素删除
	例：父元素.removeChild(要删除的元素)
	
注：如不存在父子关系则删除不成功
```

```html
<table>
  <tbody>
    <tr>
      <td>data1</td>
      <td>data2</td>
    </tr>
    <tr>
      <td>data3</td>
      <td>data4</td>
    </tr>
    <tr>
      <td>data5</td>
      <td>data6</td>
    </tr>
  </tbody>
</table>

<button>删除</button>

<script>
  let btnComp = document.querySelector(`button`)

  let trComp = document.querySelector(`tr:nth-child(2)`)
  let parentComp = document.querySelector(`tbody`)

  btnComp.addEventListener(`click`, () => {
    parentComp.removeChild(trComp)
    console.log(`====删除======`)
  })
</script>
```



### 替换节点

```
替换节点是指把一个已经存在页面中的元素替换成新的元素
方法一：
   语法: 父元素.replaceChild(新节点，被替换的节点)
   
innerHTML本质上是修改双标签里面的内容，只不过遇到标签也会解析成元素   
方法二:
   语法： 父元素.innerHTML = 内容
```

```html
<table>
  <tbody>
    <tr>
      <td>data1</td>
      <td>data2</td>
    </tr>
    <tr>
      <td>data3</td>
      <td>data4</td>
    </tr>
    <tr>
      <td>data5</td>
      <td>data6</td>
    </tr>
  </tbody>
</table>

<button>替换1</button>
<button>替换2</button>

<script>
  let btnCompAy = document.querySelectorAll(`button`)

  let tr2Comp = document.querySelector(`tr:nth-child(2)`)
  let parentComp = document.querySelector(`tbody`)

  btnCompAy[0].addEventListener(`click`, () => {
    let tr1Comp = document.createElement(`tr`)
    tr1Comp.innerHTML = `<td>xxx</td>
      <td>yyy</td>`
    parentComp.replaceChild(tr1Comp, tr2Comp)
    console.log(`====替换1======`)
  })

  btnCompAy[1].addEventListener(`click`, () => {
    parentComp.innerHTML = `<tr>
      <td>data1</td>
      <td>data2</td>
    </tr>
    <tr>
      <td>AAA</td>
      <td>BBB</td>
    </tr>
    <tr>
      <td>data5</td>
      <td>data6</td>
    </tr>`
    console.log(`====替换2======`)
  })
</script>
```

### 查找节点

```
父子关系查找：
      1. 获取父节点方法 
			parentNode 属性  返回最近一级的父节点 找不到返回为null
      2. 获取子节点
			childNodes
				获得所有子节点、包括文本节点（空格、换行）、注释节点等
			children
				仅获得所有元素节点
				
			xxx.querySelector()  xxx 可以为dom的任意节点	
```

```html
<table>
  <tbody>
    <tr>
      <td>data1</td>
      <td>data2</td>
      <td><button>删除</button></td>
    </tr>
    <tr>
      <td>data3</td>
      <td>data4</td>
      <td><button>删除</button></td>
    </tr>
    <tr>
      <td>data5</td>
      <td>data6</td>
      <td><button>删除</button></td>
    </tr>
  </tbody>
</table>
<script>
  let btnCompAy = document.querySelectorAll(`button`)

  btnCompAy.forEach((btn) => {
    btn.addEventListener(`click`, () => {
      let trComp = event.target.parentNode.parentNode
      trComp.parentNode.removeChild(trComp)
      console.log(`====删除=======`)
    })
  })
</script>
```



### 随堂练习4:发布微博案例

```
要求:
    1. 点击发布按钮，内容添加到下面
    2. 0/200的字数动态显示 【string的 length 可以获得字符个数】
```



![image-20210806085831519](images\news-comment.png)

```html
 <style>
  * {
    margin: 0;
    padding: 0;
  }

  ul {
    list-style: none;
  }

  .w {
    width: 900px;
    margin: 0 auto;
  }

  .controls textarea {
    width: 878px;
    height: 100px;
    resize: none;
    border-radius: 10px;
    outline: none;
    padding-left: 20px;
    padding-top: 10px;
    font-size: 18px;
  }

  .controls {
    overflow: hidden;
  }

  .controls div {
    float: right;
  }

  .controls div span {
    color: #666;
  }

  .controls div .useCount {
    color: red;
  }

  .controls div button {
    width: 100px;
    outline: none;
    border: none;
    background: rgb(0, 132, 255);
    height: 30px;
    cursor: pointer;
    color: #fff;
    font: bold 14px '宋体';
    transition: all 0.5s;
  }

  .controls div button:hover {
    background: rgb(0, 225, 255);
  }

  .controls div button:disabled {
    background: rgba(0, 225, 255, 0.5);
  }

  .contentList {
    margin-top: 50px;
  }

  .contentList li {
    padding: 20px 0;
    border-bottom: 1px dashed #ccc;
  }

  .contentList li .info {
    position: relative;
  }

  .contentList li .info span {
    position: absolute;
    top: 15px;
    left: 100px;
    font: bold 16px '宋体';
  }

  .contentList li .info p {
    position: absolute;
    top: 40px;
    left: 100px;
    color: #aaa;
    font-size: 12px;
  }

  .contentList img {
    width: 80px;
    border-radius: 50%;
  }

  .contentList li .content {
    padding-left: 100px;
    color: #666;
    word-break: break-all;
  }
</style>
</head>
<body>
<div class="w">
  <div class="controls">
    <img src="images/tip.png" alt="" /><br />
    <textarea
      placeholder="说点什么吧..."
      id="area"
      cols="30"
      rows="10"
      maxlength="200"
    ></textarea>
    <div>
      <span class="useCount">0</span>
      <span>/</span>
      <span>200</span>
      <button id="send">发布</button>
    </div>
  </div>
  <div class="contentList">
    <ul>
      <li>
        <div class="info">
          <img src="./images/03.jpg" /><span>百里守约</span>
          <p>发布于: 2022-3-6 10:14:8</p>
        </div>
        <div class="content">aaasdddaadaddadd</div>
      </li>
    </ul>
  </div>
</div>

<script>
  //1. 按钮事件源
  let btnElt = document.querySelector(`#send`)
  let areaElt = document.querySelector(`#area`)
  let ulElt = document.querySelector(`ul`)

  //2. 注册监听事件
  btnElt.addEventListener(`click`, () => {
    let liElt = ulElt.lastElementChild
    let addLiElt = liElt.cloneNode(true)
    addLiElt.querySelector(`.content`).innerHTML = `${areaElt.value}`
    ulElt.insertBefore(addLiElt, ulElt.firstElementChild)
    //清空
    areaElt.value = ``
    console.log(`=====发布=====`)
  })

  areaElt.addEventListener(`input`, () => {
    document.querySelector(`.useCount`).innerHTML = `${areaElt.value.length}`
    console.log(`======输入=====`)
  })
</script>
```



# 2. BOM

![image-20210806085831519](images\BOM.png)

```
1. window对象是js中的顶级对象
2. 平时所有的函数默认都被添加到window对象
3. document也属于window对象下面的一个属性
```

## 2.1 history

```
history 的数据类型是对象，该对象与浏览器地址栏的操作相对应，如前进、后退、历史记录等。
写法:
	window.history
总结：
	back()就是浏览器中对应的后退按钮的功能back 方法跳转至上一个链接地址对应的页面，与浏览器的后退操作一致
	forward(); 就是浏览器中对应的前进按钮的功能
	go(): 保存浏览器中前进页面或者后退页面(数字, 如果是正数表示前进,如果是负数表示后退)
```

```html
login.html
<form action="rs.html">
      <input type="text" name="name" placeholder="请输入账号" /> <br />
      <input type="text" name="pswd" placeholder="请输入密码" /> <br />
      <button>提交</button>
</form>
```

```html
rs.html
用户名或密码错误 点击 <a href="javaScript:history.go(-1)">这里</a>重新登录
```



## 2.2 location

```
href 属性获取完整的 URL 地址，对其赋值时用于地址的跳转
reload() 方法用来刷新当前页面，传入参数 true 时表示强制刷新
```

```html
<table>
      <tbody>
        <tr>
          <td><input type="checkbox" name="sid" value="S1" /></td>
          <td>data1</td>
          <td>data2</td>
          <td><button>删除</button></td>
        </tr>
        <tr>
          <td><input type="checkbox" name="sid" value="S2" /></td>
          <td>data3</td>
          <td>data4</td>
          <td><button>删除</button></td>
        </tr>
        <tr>
          <td><input type="checkbox" name="sid" value="S3" /></td>
          <td>data5</td>
          <td>data6</td>
          <td><button>删除</button></td>
        </tr>
      </tbody>
    </table>
    <button class="delBtn">删除选中</button>

    <script>
      let btnComp = document.querySelector(`button.delBtn`)
      let sidCheckedBoxCompAy = document.querySelectorAll(
        `input[type="checkbox"]`
      )
      btnComp.addEventListener(`click`, () => {
        console.log(sidCheckedBoxCompAy.length)
        let ay = ['S1','S3']
        location.href = `./del-stu.do?sid=${ay.join(',')}`
        console.log(`=====删除选中======`)
      })
    </script>
    <!-- <h1>3 秒跳转到百度</h1>
    <script>
      setInterval(() => {
        location.href = `http://www.baidu.com`
      }, 3000)
    </script> -->
```



# 3. 本地存储

## 3.1 localStorage

```
本地存储: 将数据保存到本地
本地: 在浏览器中保存
```

### 保存数据

```js
localStorage.setItem('key', 'value')
```

### 获取数据

```js
localStorage.getItem('key')
```

### 删除数据

```js
localStorage.removeItem('uname')
```



### 实际开发运用

```
实际开发中本地存储如何使用?
1. 本地存储可以保存对象格式的数据
2. 保存的数据如果是一个对象,则需要将对象转化为字符串格式
   通过 JSON.stringify(对象) 将对象转化为字符串格式
3. 获取的结果通过 JSON.parse(结果) 将得到的结果转化为对象
```

```html
<script>
  //  map.put(k,v)
  //存
  localStorage.setItem(`name`, `admin`)
  //删
  //localStorage.removeItem(`name`)
  //取  Object v = map.get(k)
  let msg = localStorage.getItem(`name`)
  console.log(`消息:${msg}`)
</script>
<!--  <script>
  //  map.put(k,v)
  //存
  sessionStorage.setItem(`name`, `admin`)
  //删
  sessionStorage.removeItem(`name`)
  //取  Object v = map.get(k)
  let msg = sessionStorage.getItem(`name`)
  console.log(`消息:${msg}`)
</script> -->
```

## 3.2 sessionStorage

```
sessionStorage: localStorage 一模一样
作用: 保存数据
使用方式:
  sessionStorage.setItem('uname', 'zs');
  sessionStorage.getItem('uname');
  sessionStorage.removeItem('uname');


区别:
  a) localStorage 保存的数据属于持久化保存 (数据除非手动删除否则一直存在)
  b) sessionStorage 保存的数据属于临时保存 (数据会随着页面的关闭而消失)

  c) localStorage 保存的数据可以实现跨页面访问
  d) sessionStorage 保存的数据只能在当前页面中访问
```

```js
let AY = [
    { id: 1, name: 'aa' },
    { id: 2, name: 'bb' },
    { id: 3, name: 'cc' },
]

//把 对象数组 转换成 string 字符串
let json = JSON.stringify(AY)
//存
sessionStorage.setItem(`stuList`, json)

let dbJson = sessionStorage.getItem(`stuList`)
//把字符串 转成 对象数组
let AY2 = JSON.parse(dbJson)
console.log(AY2)
```

