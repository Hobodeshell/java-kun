# 1.选择器进阶

## 1.1 复合选择器

### 后代选择器：空格

- 作用：根据 HTML 标签的嵌套关系，选择父元素 后代中 满足条件的元素
- 选择器语法：选择器1 选择器2 { css } 
- 结果：

​        在选择器1所找到标签的后代（儿子、孙子、重孙子…）中，找到满足选择器2的标签，设置样式

- 注意点：

1.  后代包括：儿子、孙子、重孙子……
2.  后代选择器中，选择器与选择器之前通过 空格 隔开
3.  ps:(自补)id选择器，元素选择器，类选择器都可以用

```html
<style>
    #box span{
      color:red ;
    }
  </style>
</head>
<body>
  <div id="box">
    <span>box的儿子</span>
    <p><span>box的孙子</span></p>
  </div>
```

### 子代选择器：>

- 作用：根据 HTML 标签的嵌套关系，选择父元素 子代中 满足条件的元素
- 选择器语法：选择器1 > 选择器2 { css } 
- 结果：

​      在选择器1所找到标签的子代（儿子）中，找到满足选择器2的标签，设置样式

- 注意点：

1. 子代只包括：儿子
2. 子代选择器中，选择器与选择器之前通过 > 隔开

```html
<style>
    /* 空格隔开的是后代, 儿子,孙子,重孙子 */
    /* div a {
        color: red;
    } */

    /* 只想选中儿子a */
    /* div的儿子a文字颜色是红色 */
    div>a {
        color: red;
    }
</style>
</head>
<body>
<div>
    父级
    <a href="#">这是div里面的a</a>
    <p>
        <a href="#">这是div里面的p里面的a</a>
    </p>
```



## 1.2 并集选择器

并集选择器：,  【 或者关系  OR  || 】

- 作用：同时选择多组标签，设置相同的样式
- 选择器语法：选择器1 ， 选择器2 { css } 
- 结果：

​       找到 选择器1 和 选择器2 选中的标签，设置样式

- 注意点：

1. 并集选择器中的每组选择器之间通过 , 分隔
2. 并集选择器中的每组选择器可以是基础选择器或者复合选择器
3. 并集选择器中的每组选择器通常一行写一个，提高代码的可读性

```html
<style>
    /* p div span h1 文字颜色是红色 */
    /* 选择器1, 选择器2 {} */
    p, 
    div, 
    span, 
    h1 {
        color: red;
    }
</style>
</head>
<body>
<p>ppp</p>
<div>div</div>
<span>span</span>
<h1>h1</h1>

<h2>h2</h2>
```



## 1.3 交集选择器

交集选择器：紧挨着 【 并且关系  and  && 】

- 作用：选中页面中 同时满足 多个选择器的标签
- 选择器语法：选择器1选择器2 { css } 
- 结果：

（既又原则）找到页面中 既 能被选择器1选中，又 能被选择器2选中的标签，设置样式

- 注意点：

1. 交集选择器中的选择器之间是紧挨着的，没有东西分隔
2. 交集选择器中如果有标签选择器，标签选择器必须写在最前面

```html
<style>
    /* p {
        color: red;
    } */

    /* .box {
        color: red;
    } */

    /* 必须是p标签,而且添加了box类 */
    /* p.box {
        color: red;
    } */
    /* #dilireba {
        color: red;
    } */

    .box {
        font-size: 32px;
    }
    
    .box1 {
        color: green;
    }
</style>
</head>
<body>
<!-- 找到第一个p,带box类的, 设置文字颜色是红色 -->
<p class="box1 box" id="dilireba">这是p标签:box</p>
<p class="box">这是p标签:box</p>
<p>pppppp</p>
<div class="box">这是div标签:box</div>
```



## 1.4 hover伪类选择器

- 作用：选中鼠标悬停在元素上的状态，设置样式
- 选择器语法：选择器:hover { css } 
- 注意点：

1. 伪类选择器选中的元素的某种状态

```html
<style>
        /* 悬停的时候文字颜色是红色 */
        a:hover {
            color: red;
            background-color: green;
        }

        /* 用户鼠标悬停到div的时候, 文字是绿色 */
        div:hover {
            color: green;
        }
    </style>
</head>
<body>
    <a href="#">这是超链接</a>
    <!-- 任何标签都可以添加伪类, 任何一个标签都可以鼠标悬停 -->
    <div>div</div>
```

## 总结：

| 选择器        | 作用             | 格式             | 示例                |
| ---------- | -------------- | -------------- | ----------------- |
| 后代选择器      | 找后代            | 选择器之间通过 空格 分割  | .father .son{css} |
| 子代选择器      | 找儿子            | 选择器之间通过  >  分割 | .father>.son{css} |
| 并集选择器      | 找到多类元素         | 选择器之间通过  ， 分割  | div,p,span{css}   |
| 交集选择器      | 同时满足多个选择器的元素   | 选择器之间   紧挨着    | p.red{css}        |
| hover伪类选择器 | 选中鼠标 悬停在元素上的状态 | ：hover         | a:hover{ css }    |



# 2.背景相关属性

## 2.1 背景颜色

![image-20211119095401318](images\bgc.png)

- 属性名：background-color（bgc） 
- 属性值：

​              颜色取值：关键字、rgb表示法、rgba表示法、十六进制……

- 注意点：

1. 背景颜色默认值是透明： rgba(0,0,0,0) 、transparent
2. 背景颜色不会影响盒子大小，并且还能看清盒子的大小和位置，一般在布局中会习惯先给盒子设置背景颜色

```html
 <style>
      div {
          width: 400px;
          height: 400px;（ps；没有大小不显示）
          /* background-color: pink; */
          /* background-color: #ccc; */
          /* 红绿蓝三原色, a是透明度0-1 */
          /* background-color: rgba(0, 0, 0, 0.5); */
          background-color: rgba(0, 0, 0, .5);
      }
  </style>
</head>
<body>
  <div>div</div>
```



## 2.2 背景图片

- 属性名：background-image（bgi） 

- 属性值：

  background-image: url(./images/1.jpg);

- 注意点：

1. 背景图片中url中可以省略引号
2. 背景图片默认是在水平和垂直方向平铺的
3. 背景图片仅仅是指给盒子起到装饰效果，类似于背景颜色，是不能撑开盒子的

```html
<style>
      div {
          width: 400px;
          height: 400px;
          background-color: pink;
          background-image: url(./images/1.jpg);
      }
  </style>
</head>
<body>
  <div>文字</div>
```



## 2.3 背景平铺

- 属性名：background-repeat（bgr） 
- 属性值：

| 取值        | 效果              |
| --------- | --------------- |
| repeat    | （默认值）水平和垂直方向都平铺 |
| no-repeat | 不平铺             |
| repeat-x  | 沿着水平方向（x轴）平铺    |
| repeat-y  | 沿着垂直方向（y轴）平铺    |

```html
<style>
      div {
          width: 400px;
          height: 400px;
          background-color: pink;
          background-image: url(./images/1.jpg);
          /* background-repeat: repeat; */
          /* 不平铺: 图片只显示一个 */
          /* background-repeat: no-repeat; */
          /* background-repeat: repeat-x; */
          /* background-repeat: repeat-y; */
      }
  </style>
</head>
<body>
  <div>文字</div>
```

## 2.4 背景位置

- 属性名：background- （bgp） 

- 属性值：

  ![image-20211119095401318](images\position.png)

- 注意点：

​     方位名词取值和坐标取值可以混使用，第一个取值表示水平，第二个取值表示垂直

```html
<style>
        div {
            width: 400px;
            height: 400px;
            background-color: pink;
            background-image: url(./images/1.jpg);
            background-repeat: no-repeat;
            /* background-position: right 0; */
            /* background-position: right bottom; */
            /* background-position: center center; */
            /* background-position: center; */
            /* background-position: 50px 0; */
            /* background-position: 50px 100px; */
            background-position: -50px -100px;

            /* 正数: 向右向下移动; 负数:向左向上移动 */
            /* 注意: 背景色和背景图只显示在盒子里面 */
        }
    </style>
</head>
<body>
    <div>文字</div>
```



## 2.5 背景相关属性连写

- 属性名：background（bg） 
- 属性值：

​      单个属性值的合写，取值之间以空格隔开

- 书写顺序：

​      推荐：background：color image repeat position

- 省略问题：

1. 可以按照需求省略
2. 特殊情况：在pc端，如果盒子大小和背景图片大小一样，此时可以直接写 background：url() 

- 注意点

1. 如果需要设置单独的样式和连写

① 要么把单独的样式写在连写的下面

② 要么把单独的样式写在连写的里面

```html
<style>
      div {
          width: 400px;
          height: 400px;
          /* 不分先后顺序 背景色  背景图  背景图平铺  背景图位置 */
          /* background: pink url(./images/1.jpg) no-repeat center bottom; */
          /* 背景图位置如果是英文单词可以颠倒顺序 */
          background: pink url(./images/1.jpg) no-repeat bottom center ;

          /* 测试背景图位置如果是数值 不要颠倒顺序 */
          /* 水平50px, 垂直100px */
          /* background: pink url(./images/1.jpg) no-repeat 50px 100px; */
          background: pink url(./images/1.jpg) no-repeat 100px 50px;


      }
  </style>
</head>
<body>
  <div></div>
```

## 扩展：

- 需求：需要在网页中展示一张图片的效果？

1. 直接写上img标签即可   img标签是一个标签，不设置宽高默认会以原尺寸显示
2. div标签 + 背景图片    需要设置div的宽高，因为背景图片只是装饰的CSS样式，不能撑开div标签





# 3. 元素显示特性

## 3.1 块级元素

![image-20211119095401318](images\block.png)

- 显示特点：

1.  独占一行（一行只能显示一个）
2.  宽度默认是父元素的宽度，高度默认由内容撑开
3.  可以设置宽高

- 代表标签：

​    div、p、h系列、ul、li、dl、dt、dd、form、header、nav、footer……



## 3.2 行内元素

![image-20211119095401318](images\inline.png)

- 显示特点：

1.  一行可以显示多个
2.  宽度和高度默认由内容撑开
3.  不可以设置宽高

-  代表标签：

​       a、span 、b、u、i、s、strong、ins、em、del……



## 3.3 行内块元素

![image-20211119095401318](images\inline-block.png)

- 显示特点：

1. 一行可以显示多个
2. 可以设置宽高

- 代表标签：

​      input、textarea、button、select……

- 特殊情况：img标签有行内块元素特点，但是Chrome调试工具中显示结果是inline



## 3.4 元素显示模式转换

- 目的：改变元素默认的显示特点，让元素符合布局要求
- 语法：

| 属性                   | 效果       | 使用频率 |
| -------------------- | -------- | ---- |
| display:block        | 转换成块级元素  | 较多   |
| display:inline-block | 转换成行内块元素 | 较多   |
| display:inline       | 转换成行内元素  | 极少   |

```html
<style>
    div{
      width: 40px;
      height: 200px;
      background-color:pink;
      display: inline-block;
    }

    span{
      width: 400px;
      height: 200px;
      background: red;
      display: block;
    }

    input{
      width: 40px;
      height: 60px;
    }

    img{
      width: 40px;
      height: 40px;
    }
  </style>
</head>
<body>
  <!-- div、p、h系列、ul、li、dl、dt、dd、form、header、nav、footer -->
  <div>我的div1</div><div>我的div2</div><div>我的div3</div>
  <!--  a、span 、b、u、i、s、strong、ins、em、del -->
  <span>data1</span>
  <span>data2</span>
  <span>data3</span>
  <!-- input、textarea、button、select -->
  <hr>
  <input type="text">
  <input type="text">
  <input type="text">
  <hr>
  <!-- ctrl+shift+alt+方向键 -->
  <img src="./images/1.jpg" alt="">
  <img src="./images/1.jpg" alt="">
  <img src="./images/1.jpg" alt="">
```



## 拓展1：HTML嵌套规范注意点

1. 块级元素一般作为大容器，可以嵌套：文本、块级元素、行内元素、行内块元素等等……

​       但是：p标签中不要嵌套div、p、h等块级元素

2. a标签内部可以嵌套任意元素

​      但是：a标签不能嵌套a标签

```html
<style>
    .d1{
      width: 400px;
      height: 400px;
      background-color: pink;
    }

    .d2{
      width: 100px;
      height: 100px;
      background-color: yellow;
    }

    .d3{
      width: 40px;
      height: 40px;
      background-color: red;
    }
  </style>
</head>
<body>
   <!-- a标签不能嵌套a标签 -->
   <a href=""><a href="">去百度</a></a>
  <a href="http://www.baidu.com"><img src="./images/1.jpg" alt=""></a>
  <!-- p标签中不要嵌套div、p、h等块级元素 -->
  <div class="d1">
    <div class="d2">
      <div class="d3">div</div>
    </div>
  </div>

  <p>
    <div>ppp11</div>
  </p>
  <p>
    <div>ppp22</div>
  </p>

  <p>
    <p>data</p>
  </p>

  <p>
    <h1>标题</h1>
  </p>
```



## 拓展2：居中总结

![image-20211119095401318](images\center.png)

```html
<style>
    .inputElt{
      text-align: center;
    }

    .center{
      width: 400px;
      height: 400px;
      background-color: pink;
      margin: 0 auto;
    }

    p{
      text-align: center;
    }

    a{
      text-align: center;
      width: 100px;
      height: 50px;
      background-color: pink;
      display: inline-block ;
      line-height: 50px;
    }
  </style>
</head>
<body>
  <a href="">data1</a>
  <a href="">data2</a>
  <a href="">data3</a>
  <a href="">data4</a>
  <a href="">data5</a>

  <p>data1</p>
  <p>data2</p>
  <p>data3</p>
  <div class="inputElt"><input type="text"></div>

  <div class="center">div</div>
```



# 4.CSS特性

## 4.1 继承性

![image-20211119095401318](images\css-extends.png)

### 介绍：

- 特性：子元素有默认继承父元素样式的特点（子承父业）
- 可以继承的常见属性(文字控制属性都可以继承)

1.  color
2.  font-style、font-weight、font-size、font-family
3.  text-indent、text-align
4.  line-height

- 注意点：

  可以通过调试工具判断样式是否可以继承



### 继承的应用：

- 好处：可以在一定程度上减少代码
- 常见应用场景：

1. 可以直接给ul设置 list-style:none 属性，从而去除列表默认的小圆点样式
2. 直接给body标签设置统一的font-size，从而统一不同浏览器默认文字大小

```html
 <style>
    div {
      color: red;
      font-size: 40px;
      width: 100px;
      height: 100px;
      background-color: pink;
    }

    ul{
      list-style:none;
    }

    body{
      font-size: 14px;
    }
  </style>
</head>

<body>
  <ul>
    <li>data1</li>
    <li>data2</li>
    <li>data3</li>
  </ul>
  <div>div<span>span</span></div>
```



### 继承失效的特殊情况

- 如果元素有浏览器默认样式，此时继承性依然存在，但是优先显示浏览器的默认样式

1. a标签的color会继承失效

​         其实color属性继承下来了，但是被浏览器默认设置的样式给覆盖掉了

2. h系列标签的font-size会继承失效

​       其实font-size属性继承下来了，但是被浏览器默认设置的样式给覆盖掉了



## 4.2 层叠性

- 特性：

1. 给同一个标签设置不同的样式 → 此时样式会层叠叠加 → 会共同作用在标签上
2. 给同一个标签设置相同的样式 → 此时样式会层叠覆盖 → 最终写在最后的样式会生效

- 注意点：

1. 当样式冲突时，只有当选择器优先级相同时，才能通过层叠性判断结果

```html
<style>
div{
  width: 100px;
  height: 100px;
}

#d1{
  background-color: red;
}

#d1{
  background-color: blue;
}
</style>
<div id="d1"></div>
```



## 4.3 优先级

### 介绍：

- 特性：不同选择器具有不同的优先级，优先级高的选择器样式会覆盖优先级低选择器样式
- 优先级公式：

​      继承 < 通配符选择器 < 标签选择器 < 类选择器 < id选择器 < 行内样式 < !important

- 注意点：

1. !important写在属性值的后面，分号的前面！
2. !important不能提升继承的优先级，只要是继承优先级最低！
3. 实际开发中不建议使用 !important 



### 权重叠加计算：

- 场景：如果是复合选择器，此时需要通过权重叠加计算方法，判断最终哪个选择器优先级最高会生效
- 权重叠加计算公式：（每一级之间不存在进位）

![image-20211119095401318](images\order.png)



- 比较规则：

1. 先比较第一级数字，如果比较出来了，之后的统统不看
2. 如果第一级数字相同，此时再去比较第二级数字，如果比较出来了，之后的统统不看
3. ……
4. 如果最终所有数字都相同，表示优先级相同，则比较层叠性（谁写在下面，谁说了算!） 

- 注意点：!important如果不是继承，则权重最高，天下第一！

### 权重叠加计算案例

- 权重计算题解题步骤：

1. 先判断选择器是否能直接选中标签，如果不能直接选中 → 是继承，优先级最低 → 直接pass
2. 通过权重计算公式，判断谁权重最高

- 注意点：

​     实际开发中选择标签需要精准，尽量避免多个选择器同时选中一个标签的情况，不要自己难为自己

案例1：

```html
<title>第1题：普通题</title>
	<style>
		/* (行内, id, 类, 标签) */
		/* !important 最高 */
		/* 继承: 最低 */

		/* (0, 2, 0, 0) */
		#father #son {
			color:blue; 
		} 
		
		/* (0, 1, 1, 1) */
		#father p.c2 {
			color:black;
		} 
		
		/* (0, 0, 2, 2) */
		div.c1 p.c2 {
			color:red;
		} 

		/* 继承, 最低 */
		#father { 
			color:green !important;
		} 

		/* 继承, 最低 */
		div#father.c1 {
			color: yellow ;
		} 

	</style>
</head>
<body>
	<div id="father" class="c1">
		<p id="son" class="c2">
			这行文本是什么颜色的？ 
		</p>
	</div>
```

第二题：

```html
<title>第2题: 标签选择器选择一类</title>
	<style>
		/* (行内, id, 类, 标签) */
		/* !important 最高 */
		/* 继承: 最低 */
	
		/* 2 */
	  div div {
			color: skyblue;
		} 

		/* 1 */
		div {
			color: red;
		}
	</style>
</head>
<body>
	<div>
		<div>
			<div>
				这行文本是什么颜色？
			</div>
		</div>
	</div>
```

第三题

```html
<title>第3题: 权重叠加每位不存在进制</title>
	<style>
		/* (行内, id, 类, 标签) */
		div div div div div div div div div div div div {  
			color: red;
		}
		
		.one { 
			color: pink;
		}
	</style>
</head>
<body>
	<div>
		<div>
			<div>
				<div>
					<div>
						<div>
							<div>
								<div>
									<div>
										<div>
											<div>
												<div class="one">这行文字是什么颜色的？</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
```

第四题

```html
<title>第4题：权重相同此时比较层叠性</title>
	<style>
		/* (0, 0, 2, 1) */
		.c1 .c2 div { 
			color: blue;
		}
		
		/* (0, 1, 0, 1) */
		div #box3 {
			color:green;
		}
		
		/* (0, 1, 0, 1) */
		#box1 div {
			color:yellow;
		}
	</style>
</head>
<body>
	<div id="box1" class="c1">
		<div id="box2" class="c2">
			<div id="box3" class="c3">
				这行文本是什么颜色的？
			</div>
		</div>
	</div>
```

第五题

```html
<title>第5题: 全是继承的特殊情况</title>
	<style>
		/* 都是继承, 继承里面谁高, 看继承哪个父级, 哪个父级高, 哪个选择器生效 */

		/* 继承 */
		div p {
			color:red;
		} 

		/* 继承 */
		.father {
			color:blue;
		} 
	</style>
</head>
<body>
	<div class="father">
		<p class="son"> 
			<span>文字</span>
		</p>
	</div>
```



# 5. Chrome调试工具

## 5.1 调试

![image-20211119095401318](images\debug-css.png)

## 5.2 案例

第一题：

```html
<style>
    .father .son .sun {
      color: redd;
      font-size: 20px;
    }
  </style>
</head>
<body>
  <div class="father">
    <div class="son">
      <div class="sun">孙子</div>
    </div>
  </div>
```

第二题:

```html
<style>
    .father .son .sun {
      color: red;
      font-siz: 20px;
    }
  </style>
</head>
<body>
  <div class="father
    <div class="son">
      <div class="sun">孙子</div>
    </div>
  </div>
```

第三题：

```html
<style>
    }

    .father .son .sun {
      color: orange;
    }
    
  </style>
</head>
<body>
  <div class="father">
    <div class="son">
      <div class="sun">孙子</div>
    </div>
  </div>
```

# 6.PxCook的基本使用

能够使用 PxCook 工具测量设计图的 尺寸 和 颜色 ，能够从psd文件中直接获取数据

PxCook的基本使用

- 通过软件打开设计图

① 打开软件 ② 拖拽入设计图 ③ 新建项目

- 常用快捷键

1. 放大设计图：ctrl + +
2. 缩小设计图：ctrl + -
3. 移动设计图：空格按住不放，鼠标拖动

- 常用工具

1. 量尺寸
2. 吸颜色

- 从psd文件中直接获取数据

1. 切换到开发界面，直接点击获取数据



# 7. 盒子模型

能够认识 盒子模型的组成 ，能够掌握盒子模型 边框、内边距、外边距 的设置方法

## 7.1盒子模型的介绍

- 盒子的概念

1. 页面中的每一个标签，都可看做是一个 “盒子”，通过盒子的视角更方便的进行布局
2. 浏览器在渲染（显示）网页时，会将网页中的元素看做是一个个的矩形区域，我们也形象的称之为 盒子

- 盒子模型

   CSS 中规定每个盒子分别由：

​      内容区域（content）、

​      内边距区域（padding）、

​     边框区域（border）、外边距区域（margin）构成，这就是 盒子模型

- 记忆：可以联想现实中的包装盒

![image-20211119095401318](images\box.png)





## 7.2内容区域的宽度和高度

- 作用：利用 width 和 height 属性默认设置是盒子 内容区域 的大小
- 属性：width / height
- 常见取值：数字+px

![image-20211119095401318](images\content.png)





## 7.3边框（border）

### 单个属性

- 作用：给设置边框粗细、边框样式、边框颜色效果

| 作用   | 属性名          | 属性值                       |
| ---- | ------------ | ------------------------- |
| 边框粗细 | border-width | 数字+px                     |
| 边框样式 | border-style | 实线solid，虚线dashed，点线dotted |
| 边框颜色 | border-color | 颜色取值                      |



### 连写形式

- 属性名：border

- 属性值：单个取值的连写，取值之间以空格隔开

  如：border : 10px solid red;

- 快捷键：bd + tab（或 回车）



### 单方向设置

- 场景：只给盒子的某个方向单独设置边框
- 属性名：border - 方位名词
- 属性值：连写的取值

```css
border-left: 5px dotted #000;
border-right: 5px dotted #000;
border-top: 1px solid red;
border-bottom: 1px solid red;
```



### QA:

1. 给盒子设置四周 20像素、实线、蓝色的边框，属性应该如何设置？

​         border：20px solid blue； 

2. 给盒子设置上边框 10像素、虚线、黄色的边框，属性应该如何设置？

​        border-top：10px dashed yellow；

### 盒子实际大小初级计算公式

需求：盒子尺寸 400*400，背景绿色，边框10px 实线 黑色，如何完成？

注意点： ① 设置width和height是内容的宽高！② 设置border会撑大盒子！

- 盒子实际大小初级计算公式：

    盒子宽度 = 左边框 + 内容宽度 + 右边框

    盒子高度 = 上边框 + 内容高度 + 下边框

- 解决：当盒子被border撑大后，如何满足需求？

  解决：计算多余大小，手动在内容中减去（手动内减）

![image-20211119095401318](images\box-size.png)

```html
<style>
    div{
      width: 380px;
      height: 380px;
      background-color: pink;
      /* border-width: 10px;
      border-style:solid;
      border-color: red; */
      border: 10px solid red;
      /* border-left: 10px solid red;
      border-right: 5px solid blue;
      border-top: 2px solid red;
      border-bottom: 1px solid red; */
    }
  </style>
</head>
<body>
  <div></div>
```



### 盒子边框的小案例

需求：根据设计图，通过PxCook量取数据，通过代码在网页中完成一致的效果

![image-20211119095401318](images\case01.png)

```html
  <style>
    div{
      width: 280px;
      height: 280px;
      background-color: #ffc0cb;
      border: 10px solid #00f;
    }
  </style>
</head>
<body>
  <div></div>
```



### 新浪导航案例

- 需求：根据设计图，通过PxCook量取数据，通过代码在网页中完成一致的效果

![image-20211119095401318](images\case02.png)

- 布局顺序：

1. 从外往内，从上往下

- 每一个盒子的样式：

1. 宽高
2. 辅助的背景颜色
3. 盒子模型的部分：border、padding、margin
4. 其他样式：color、font-、text-、……

```html
<style>
    div.nav{
      height: 40px;
      border-top: 3px solid #ff8500;
      border-bottom: 1px solid #edeef0;
    }

    .nav a{
      text-decoration: none;
      width: 80px;
      height: 40px;
      /* 行内 修改成 行内块 */
      display: inline-block ;
      /* background-color: pink; */
      text-align: center;
      line-height: 40px;
      font-size: 12px;
      color: #4c4c4c;
    }

    .nav a:hover{
      background-color: #edeef0;
      color: #ff8500;
    }
  </style>
</head>
<body>
  <div class="nav">
    <a href="">新浪导航</a>
    <a href="">新浪导航</a>
    <a href="">新浪导航</a>
    <a href="">新浪导航</a>
  </div>
```



## 7.4内边距（padding）

### 取值

- 属性名：padding
- 常见取值：

| 取值   | 示例                           | 含义                                |
| ---- | ---------------------------- | --------------------------------- |
| 一个值  | padding:10px;                | 上右下左都设置10px                       |
| 两个值  | padding:10px 20px;           | 上下设置10px，左右设置为20px                |
| 三个值  | padding: 10px 20px 30px ;    | 上设置为10px ，左右设置为20px ，下设置为30px     |
| 四个值  | padding:10px 20px 30px 40px; | 上设置10px ，右设置20px，下设置30px, 左设置40px |

- 记忆规则：从上开始赋值，然后顺时针赋值，如果设置赋值的，看对面的！！



### 单方向设置

- 场景：只给盒子的某个方向单独设置内边距
- 属性名：padding - 方位名词
- 属性值：数字 + px



### QA:

1. 给盒子设置四周 20px 的内边距可以通过什么属性设置？

    padding : 20px ;

2.  给盒子设置上下20px、左右30px的内边距可以通过什么属性设置？

    padding : 20px 30px ;

3.  给盒子设置左侧50px的内边距可以通过什么属性设置？

   padding-left : 50px ;



### 盒子实际大小终极计算公式

- 需求：盒子尺寸300*300，背景粉色，边框10px实线黑色，上下左右20px的内边距，如何完成？

 注意点：① 设置width和height是内容的宽高！② 设置border会撑大盒子 ③ 设置padding会撑大盒子

- 盒子实际大小终极计算公式：

  盒子宽度 = 左边框 + 左padding + 内容宽度 + 右padding + 右边框

  盒子高度 = 上边框 + 上padding + 内容宽度 + 下padding + 下边框

- 解决：当盒子被border和padding撑大后，如何满足需求？

  自己计算多余大小，手动在内容中减去（手动内减）

![image-20211119095401318](images\border-end-size.png)



### 新浪导航的优化

需求：优化之前的新浪导航，如果每个导航的字数增加，如何完成效果？

![image-20211119095401318](images\sina-nav-02.png)

```html
<style>
    div.nav{
      height: 40px;
      border-top: 3px solid #ff8500;
      border-bottom: 1px solid #edeef0;
    }

    .nav a{
      text-decoration: none;
      /* width: 80px; */
      padding: 0 16px;
      height: 40px;
      /* 行内 修改成 行内块 */
      display: inline-block ;
      /* background-color: pink; */
      text-align: center;
      line-height: 40px;
      font-size: 12px;
      color: #4c4c4c;
    }

    .nav a:hover{
      background-color: #edeef0;
      color: #ff8500;
    }
  </style>
</head>
<body>
  <div class="nav">
    <a href="">新浪导航</a>
    <a href="">新浪导航新浪导航</a>
    <a href="">新浪导航</a>
    <a href="">新浪导航</a>
  </div>
```



### 不会撑大盒子的特殊情况

- 不会撑大盒子的特殊情况（块级元素）

1. 如果子盒子没有设置宽度，此时宽度默认是父盒子的宽度
2. 此时给子盒子设置左右的padding或者左右的border，此时不会撑大子盒子



### CSS3盒模型（自动内减）

- 需求：盒子尺寸300*300，背景粉色，边框10px实线黑色，上下左右20px的内边距，如何完成？

  ​	给盒子设置border或padding时，盒子会被撑大，如果不想盒子被撑大？

- 解决方法 ① ：手动内减

  操作：自己计算多余大小，手动在内容中减去

  缺点：项目中计算量太大，很麻烦

- 解决方法 ② ：自动内减

  操作：给盒子设置属性 box-sizing : border-box ; 即可

  优点：浏览器会自动计算多余大小，自动在内容中减去



## 7.5外边距（margin）

### 取值

- 作用：设置边框以外，盒子与盒子之间的距离
- 属性名：margin
- 常见取值：

| 取值   | 示例                          | 含义                              |
| ---- | --------------------------- | ------------------------------- |
| 一个值  | margin:10px;                | 上右下左 设置10px                     |
| 两个值  | margin:10px 20px;           | 上下设置10px  左右设置20px              |
| 三个值  | margin:10px 20px 30px;      | 上设置10px 左右设置20px 下设置30px        |
| 四个值  | margin:10px 20px 30px 40px; | 上设置10px 右设置20px 下设置30px 左设置40px |

- 记忆规则：从上开始赋值，然后顺时针赋值，如果设置赋值的，看对面的！！



### 单方向设置

- 场景：只给盒子的某个方向单独设置外边距
- 属性名：margin - 方位名词
- 属性值：数字 + px

![image-20211119095401318](images\marge-one-direct.png)



### QA:

1. 给盒子设置四周 20px 的外边距可以通过什么属性设置？

   margin : 20px ;

2.  给盒子设置上下20px、左右30px的外边距可以通过什么属性设置？

    margin : 20px 30px ;

3. 给盒子设置左侧50px的外边距可以通过什么属性设置？

   margin-left : 50px ;

### 清除默认内外边距

- 场景：浏览器会默认给部分标签设置默认的margin和padding，

  ​	但一般在项目开始前需要先清除这些标签默认的

margin和padding，后续自己设置

比如：body标签默认有margin：8px

比如：p标签默认有上下的margin

比如：ul标签默认由上下的margin和padding-left

- 解决方法：

![image-20211119095401318](images\clear-default.png)

### 网页新闻列表案例

- 需求：根据设计图，通过PxCook量取数据，代码在网页中完成一致的效果
- 布局顺序：

1. 从外往内，从上往下

- 每一个盒子的样式：

1. 宽高
2. 辅助的背景颜色
3. 盒子模型的部分：border、padding、margin
4. 其他样式：color、font-、text-、……

![image-20211119095401318](images\news-case.png)

```html
<style>
    /* 通配符选择器  清除默认 内边距  外边距 */
    * {
      margin: 0;
      padding: 0;
      /* css3  自动内减 */
      box-sizing: border-box;
    }

    /* 继承 清除 li显示样式  */
    ul {
      list-style: none;
    }

    /* 最外部的div */
    .newslist {
      /* 设置宽高 */
      width: 500px;
      height: 400px;
      /* 布局设置 测试 背景色 */
      /* background-color: pink; */
      border: 1px solid #ccc;
      /* 设置外边距  距上25px 左右居中 */
      margin: 25px auto;
      /* 设置内边距  距上下42px  距左右 30px */
      padding: 42px 30px;
    }

    /* 标题 */
    .newslist h2 {
      /* 下边框 实线框 1px ccc */
      border-bottom: 1px solid #ccc;
      /* 字体大小 30px */
      font-size: 30px;
      /* 垂直居中 */
      line-height: 1;
      /* 内边距 下 9px */
      padding-bottom: 9px;
    }

    /* 列表项 */
    .newslist li {
      /* 行高  50px */
      height: 50px;
      /* 字体大小 18px */
      font-size: 18px;
      /* 字体颜色 666 */
      color: #666;
      /* 下边框  1px  ccc 点线 */
      border-bottom: 1px dashed #ccc;
      /* 左 内边距  28px */
      padding-left: 28px;
      /*  垂直居中 */
      line-height: 50px;
    }

    /*设置 a 超链接*/
    .newslist a {
      /* 文本颜色 666 */
      color: #666;
      /* 去掉下划线 */
      text-decoration: none;
    }
  </style>
</head>

<body>
  <div class="newslist">
    <h2>最新文章/New Articles</h2>
    <ul>
      <li><a href="">北京招聘网页设计......</a></li>
      <li><a href="">北京招聘网页设计......</a></li>
      <li><a href="">北京招聘网页设计......</a></li>
      <li><a href="">北京招聘网页设计......</a></li>
      <li><a href="">北京招聘网页设计......</a></li>
    </ul>
  </div>
```



### 外边距正常情况

- 场景：水平布局 的盒子，左右的margin正常，互不影响
- 结果：最终两者距离为左右margin的和



### 外边距折叠现象 – ① 合并现象

- 场景：垂直布局 的 块级元素，上下的margin会合并

-  结果：最终两者距离为margin的最大值

- 解决方法：避免就好

  只给其中一个盒子设置margin即可



### 外边距折叠现象 – ② 塌陷现象

- 场景：互相嵌套 的 块级元素，子元素的 margin-top 会作用在父元素上
- 结果：导致父元素一起往下移动
- 解决方法：

1. 给父元素设置border-top 或者 padding-top（分隔父子元素的margin-top）
2. 给父元素设置overflow：hidden
3. 转换成行内块元素
4. 设置浮动



### 行内元素的margin和padding无效情况

- 场景：给行内元素设置margin和padding时 
- 结果：

1. 水平方向的margin和padding布局中有效！
2. 垂直方向的margin和padding布局中无效！





# 随堂练习

## 练习1：普通导航

### 效果图：

![image-20211119095401318](images\nav-01.png)

### 参考代码：

```html
 <style>
    a{
      text-decoration: none;
      display: inline-block ;
      width: 80px;
      height: 50px;
      background-color: #f00;
      text-align: center;
      line-height: 50px;
    }

    a:hover{
      background-color: #ffa500;
    }
  </style>
</head>
<body>
  <a href="">导航1</a>
  <a href="">导航2</a>
  <a href="">导航3</a>
  <a href="">导航4</a>
  <a href="">导航5</a>
```



## 练习2:五彩导航

### 效果图:

![image-20211119095401318](images\nav-02.png)

### 参考代码:

```html
<style>
    a{
      text-decoration: none;
      display:inline-block;
      width: 120px;
      height: 58px;
      text-align: center;
      line-height:50px ;
      color: #fff;
      /* background-color: pink; */
    }

    .c1{
      background-image: url(./images/bg1.png);
    }
    .c1:hover{
      background-image: url(./images/bg5.png);
    }

    .c2{
      background-image: url(./images/bg2.png);
    }
    .c2:hover{
      background-image: url(./images/bg6.png);
    }

    .c3{
      background-image: url(./images/bg3.png);
    }
    .c3:hover{
      background-image: url(./images/bg7.png);
    }

    .c4{
      background-image: url(./images/bg4.png);
    }
    .c4:hover{
      background-image: url(./images/bg8.png);
    }
  </style>
</head>
<body>
  <a href="" class="c1">五彩导航</a>
  <a href="" class="c2">五彩导航</a>
  <a href="" class="c3">五彩导航</a>
  <a href="" class="c4">五彩导航</a>
```



