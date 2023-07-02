# 1.html基本标签

## 1.1 表单标签

### input系列标签

name，value，placeholder 属性

- name 给 服务器端 发送请求参数名      

  String value = request.getParamter("username")

- value 根据该参数名 获得对应的文本框的值

- placeholder 占位符  提示用户输入内容

![image-20211118100835621](images\input-name.png)

type属性

【2列 22 行】

| 值              | 描述                                       |
| -------------- | ---------------------------------------- |
| text           | 默认值，单行文本框    value属性回显值   readonly只读    disabled 禁用 |
| password       | 密码框                                      |
| radio          | 单选按钮 相同name  不同value   选中：checked        |
| checkbox       | 复选框    相同name  不同value   选中： checked     |
| button         | js语言调用                                   |
| submit         | 提交按钮   必须被 form包裹   提交的路径 form表单 action属性值 |
| reset          | 重置按钮  页面加载的初始状态                          |
| file           | 文件上传   multiple   支持多文件上传                |
| search         | 搜索文本输入框   多一个 X 清除                       |
| number         | 数值  min   max  step                      |
| range          | 进度条                                      |
| date           | 日期： 年月日                                  |
| month          | 日期： 年月                                   |
| time           | 日期： 时 分                                  |
| datetime-local | 日期： 年月日  时分                              |
| week           | 日期： 年   周                                |
| color          | 颜色                                       |
| email          | 邮箱                                       |
| tel            | 电话                                       |
| url            | URL地址                                    |
| hidden         | 隐藏表单【给服务器端使用  比如：修改的id】                  |

readonly ，disable 【布尔值属性】

只读， 禁用

###  button按钮标签

[2列*4行]

双标签： 方便包裹  文本  图片

| type属性 | 描述            |
| ------ | ------------- |
| submit | 谷歌浏览器，提交  默认值 |
| reset  | 重置            |
| button | js 调用         |

### select下拉菜单标签

select>option{data$}*3

被选中：  selected

```html
<!--  select>optgroup*2>option{data$}*3  -->
<select name="" id="">
  <optgroup label="安徽省">
    <option value="">data1</option>
    <option value="">data2</option>
    <option value="">data3</option>
  </optgroup>
  <optgroup label="江苏省">
    <option value="">data1</option>
    <option value="">data2</option>
    <option value="">data3</option>
  </optgroup>
</select>
```

### textarea文本域标签

注意： 没有value 属性值，双标签  包裹内容

- rows 显示行数


- cols 显示列数

```html
<textarea name="info" cols="30" rows="10">
  个人学习能力强
</textarea>
```

###  label标签

绑定内容与标签的关系

- 方法 1 ：

  1. 使用label标签把内容（如：文本）包裹起来
  2. 在表单标签上添加id属性
  3. 在label标签的for属性中设置对应的id属性值

- 方法 2：

  1. 直接使用label标签把内容（如：文本）和表单标签一起包裹起来

  2. 需要把label标签的for属性删除即可

     

```html
<!-- 方法一 -->
<input type="checkbox" name="sex" id="body" value="0"><label for="body">帅哥</label>
<!-- 方法二 -->   
<label><input type="checkbox" name="sex" id="girl" value="1">美女</label>
```

###  form表单标签

#### action

```
1. 提交服务器端路径      比如： javaWeb  servlet  或 jsp    springmvc 控制器   【服务器端处理程序】

默认提交路径  =  当前页面 【提交给自己】 类似 a 超链接的 href 属性值  同样也有 target="_blank"
```

#### method

如何判断：GET/POST [ F12 -查看网络 ]

```
1. GET  【地址栏带参数 ? 分割 请求资源与参数  多个参数使用 &  参数名=参数值】     
   http://127.0.0.1:5500/day01/reg.jsp?username=admin&age=18
   ?username=admin&age=18        查询字符串
    ? 分割请求资源 与 参数
    &  多个参数
    =  参数名 与 参数值的分割
   java服务器端代码：
   String inputUsername = request.getParameter("username") ;   //admin
   String inputAge = request.getParameter("age") ;   //admin
   
   GET请求 一次性把请求资源 与 请求数据【参数】发送给服务器端
   
   优点： 速度快 【查询】
   
   缺点： 数据量限制 1KB
         安全性低
2. POST 【地址栏不带参数】     
                                                 
   优点： 安全高
         数据量不受限制
         
   缺点： 速度慢      
 
 比如：  文件上传 图片 excel 表格  GET ? POST ?  [POST]
          当表单中有文件上传控件时，必须添加 enctype="multipart/form-data" 
```

![image-20211118161007657](images\get-post.png)



####   Q1 

#####   Q: 什么情况下，服务器端无法拿到form表单中数据？

1. input  select  textarea 表单控件没有name属性  
2. 无效name【空格中文特殊字符】
3. disabled 禁用 【布尔值属性】

#### Q2:   

##### GET请求 ，POST请求，使用  a href="reg.jsp"?

只有 表单提交 method=“post”提交  ，其他所有的请求都是GET ？

F12

#### Q3: 

##### 请求的URL中有  ?method=list 请问是不是一定为GET请求？

```html
看 F12
```



#### Q4: 

##### action的url 与 GET请求冲突？

```html
 <form action="reg.jsp?method=list" method="get">
  姓名: <input type="text" name="username" value="zhangsan" />
  <br />
  密码: <input type="password" name="password" /> <br />
  <button>提交</button>
</form>
```

```html
使用 隐藏表单
<form action="user.jsp" method="get">
    <input type="hidden" name="method" value="reg">
    <input type="text" name="username" value="admin" id=""> <br>
    <input type="password" name="pswd" value="123" id=""> <br>
    <button>提交</button>
  </form>
```



#### Q5：

##### 解决disabled服务器拿不到数据？

```html
 <!-- update t_stu set sname=? where sid=? -->
<h1>修改学生</h1>
<form action="modify.jsp" method="post">
  <input type="hidden" name="sid" value="S1001">
  学号: <input type="text" name="sid" value="S1001" disabled /> <br />
  姓名: <input type="text" name="sname" value="zhangsan" /> <br />
  <button>修改</button>
</form>
```



## 1.2 语义化标签

### 没有语义的布局标签

- div标签：一行只显示一个（独占一行） 
- span标签：一行可以显示多个

### 有语义的布局标签

![image-20211118100835621](images\h5.png)

```html
<!-- 我是头 -->
<header>我是头</header>
<!-- 我是菜单 -->
<menu>我是菜单</menu>
<!-- 我是侧边栏 -->
<aside>侧边栏</aside>
<!-- 网页区块 -->
<section>网页区块</section>
<!-- 文章 -->
<article>文章</article>
<!-- 页脚 -->
<footer>页脚</footer>
```

## 1.3 字符实体

![image-20211118100835621](images\entity.png)



# 2. CSS基础

## 2.1 基础认知

### 介绍

![image-20211118100835621](images\css.png)

### 语法规则

 写在哪？

- css写在style标签中，style标签一般写在head标签里面，title标签下面

怎么写？

![image-20211118100835621](images\write-css.png)

### 体验

![image-20211118100835621](images\use-css.png)

```html
<style>
      /* css注释 */
      /* 这里写的都是css语法 */
      /* 选择器 {css 属性} */
      /* 选择器： 查找标签 */
      p {
        /* 文字颜色  红色 c */
        color: red;
        /* 字体大小 30px  像素 fz */
        font-size: 30px;
        /* 背景颜色 bgc  */
        background-color: green;
        /* 宽度  高度  w400+h400*/
        width: 400px;
        height: 400px;
      }
    </style>
  </head>
  <body>
    <p>hello css</p>
  </body>
```

### CSS引入方式

- 内嵌式： CSS 写在style标签中

   style标签虽然可以写在页面任意位置，但是通常约定写在 head 标签中

- 外联式：CSS 写在一个单独的.css文件中

  需要通过link标签在网页中引入

- 行内式：CSS 写在标签的style属性中

​       目前不推荐使用，之后会配合js使用

my.css

```css
/* 选择器 {} */
p {
    color: red;
}
```

*.html

```html
<!-- 关系: 样式表 -->
<link rel="stylesheet" href="./my.css">
<!-- css到底能写在哪里 -->
<p>这是p标签</p>
<div style="color: green; font-size: 30px;">这是div标签</div>
<div>这个div是什么颜色</div>
```



【4*4】

| 引入方式 | 书写位置                 | 作用范围 | 使用场景   |
| ---- | -------------------- | ---- | ------ |
| 内嵌式  | 写在 style标签中          | 当前页面 | 小案例    |
| 外联式  | *.css文件中  通过link标签引入 | 多个页面 | 项目中    |
| 行内式  | 标签的style属性中          | 当前标签 | 配合js使用 |

## 2.2 基础选择器

选择器的作用：

- 选择页面中对应的标签（找她），方便后续设置样式（改她）

### 标签选择器

结构：标签名 { css属性名：属性值； } 

作用：通过标签名，找到页面中所有这类标签，设置样式

注意点：

1. 标签选择器选择的是一类标签，而不是单独某一个
2. 标签选择器无论嵌套关系有多深，都能找到对应的标签

```html
<style>
    /* 选择器 {} */
    /* 标签选择器 就是 以标签名命名的选择器 */
    p {
        color: red;
    }

    /* 标签选择器 选中所有的这个标签都生效css */
</style>
<p>pppppppp</p>
<p>这个p是什么颜色呢</p>
<p>2222</p>
```

### 类选择器

结构：.类名 { css属性名：属性值； } 

作用：通过类名，找到页面中所有带有这个类名的标签，设置样式

注意点：

1. 所有标签上都有class属性，class属性的属性值称为类名（类似于名字）
2. 类名可以由数字、字母、下划线、中划线组成，但不能以数字或者中划线开头
3. 一个标签可以同时有多个类名，类名之间以空格隔开
4. 类名可以重复，一个类选择器可以同时选中多个标签

```html
<style>
    .red {
        color: red;
    }

    .size {
        font-size: 66px;
    }
</style>
<!-- 类: 定义 和 使用才能生效 -->
<p>111</p>
<!-- 一个标签可以使用多个类名 , 需要空格隔开即可 -->
<p class="red size">222</p>
<div class="red">这个标签文字也要变红</div>
```



### id选择器

结构：#id属性值 { css属性名：属性值； } 

作用：通过id属性值，找到页面中带有这个id属性值的标签，设置样式

注意点：

1. 所有标签上都有id属性
2. id属性值类似于身份证号码，在一个页面中是唯一的，不可重复的！
3. 一个标签上只能有一个id属性值
4. 一个id选择器只能选中一个标签

```html
<style>
    /* 定义id选择器 */
    #blue {
        color: skyblue;
    }
</style>
<div id="blue">这个div文字是蓝色的</div>
<p id="blue">111</p>
```



class类名与id属性值的区别

- class类名相当于姓名，可以重复，一个标签可以同时有多个class类名
- id属性值相当于身份证号码，不可重复，一个标签只能有一个id属性值

类选择器与id选择器的区别

- 类选择器以 . 开头
- id选择器以 # 开头

实际开发的情况

- 类选择器用的最多
- id一般配合js使用，除非特殊情况，否则不要使用id设置样式
- 实际开发中会遇到冗余代码的抽取 （可以将一些公共的代码抽取到一个公共的类中去）



### 通配符选择器

结构：* { css属性名：属性值； } 

作用：找到页面中所有的标签，设置样式

注意点：

1. 开发中使用极少，只会在极特殊情况下才会用到
2. 在页面中可能会用于去除标签默认的margin和padding

```css
*{
   margin: 0;
   padding: 0;
}
```



```html
<style>
    * {
        color: red;
    }
</style>
<div>div</div>
<p>pppp</p>
<h1>h1</h1>
<span>span</span>
<p>pppp</p>
<h2>h2</h2>
```

## 2.3 字体和文本样式

### 字体样式

字体大小： font-size

- 属性名：font-size
- 取值：数字 + px
- 注意点：

1.  谷歌浏览器默认文字大小是16px
2.  单位需要设置，否则无效

```html
<style>
    p {
        font-size: 30px;
    }
</style>
<!-- 默认字号是16 -->
<p>段落文字</p>
```



字体粗细：font-weight

- 属性名：font-weight
- 取值：

​          关键字：

| 描述   | 关键字    |
| ---- | ------ |
| 正常   | normal |
| 加粗   | bold   |



​       纯数字：100~900的整百数：

| 描述   | 数值   |
| ---- | ---- |
| 正常   | 400  |
| 加粗   | 700  |



- 注意点：

1. 不是所有字体都提供了九种粗细，因此部分取值页面中无变化
2. 实际开发中以：正常、加粗两种取值使用最多。

```html
<style>
        div {
            /* 加粗 */
            font-weight: 700;
        }

        h1 {
            /* 不加粗 */
            font-weight: 400;
        }
</style>
<div>这是div</div>
<h1>一级标题</h1>
```



字体样式: font-style

- 属性名：font-style
- 取值：

1. 正常（默认值）：normal
2. 倾斜：italic

```html
<style>
    div {
        /* 倾斜 */
        font-style: italic;
    }

    em {
        /* 正常的, 不倾斜 */
        font-style: normal;
    }
</style>
<div>div文字</div>
<em>em</em>
```



字体类型： font-family

- 属性名：font-family
- 常见取值：具体字体1,具体字体2,具体字体3,具体字体4,...,字体系列

1. 具体字体："Microsoft YaHei"、微软雅黑、黑体、宋体、楷体等……
2. 字体系列：sans-serif、serif、monospace等……

-  渲染规则：

1. 从左往右按照顺序查找，如果电脑中未安装该字体，则显示下一个字体
2. 如果都不支持，此时会根据操作系统，显示最后字体系列的默认字体

- 注意点：

1. 如果字体名称中存在多个单词，推荐使用引号包裹
2. 最后一项字体系列不需要引号包裹
3. 网页开发时，尽量使用系统常见自带字体，保证不同用户浏览网页都可以正确显示

| 操作系统    | 默认字体 |
| ------- | ---- |
| Windows | 微软雅黑 |
| macOS   | 苹方   |

```html
<style>
    div {
        /* font-family: 宋体; */
        /* 如果用户电脑没有安装微软雅黑, 就按黑体显示文字 */
        /* 如果电脑没有安装黑体, 就按任意一种非衬线字体系列显示 */
        font-family: 微软雅黑, 黑体, sans-serif;
    }
</style>
<div>
    这是一个div标签
</div>
```

![image-20211118100835621](images\css-override.png)

```html
<style>
    p {
        /* 层叠性: 后面的覆盖前面的属性 */
        /* 覆盖 */
        color: red;
        color: blue;
    }
</style>
<p>ppppp</p>
```

字体类型：font属性连写

- 属性名：font (复合属性) 
- 取值：

​         font : style weight size family;

- 省略要求：

​          只能省略前两个，如果省略了相当于设置了默认值

- 注意点：如果需要同时设置单独和连写形式

1. 要么把单独的样式写在连写的下面
2. 要么把单独的样式写在连写的里面

```html
<style>
  p {
          /* font-size: ;
          font-style: ;
          font-weight: ;
          font-family: ; */
          /* font: style   weight   size    字体; */

          /* font: italic 700 66px 宋体;
          font-style: normal; */
          font: 100px 微软雅黑;

          /* 一个属性冒号后面书写多个值的写法 -- 复合属性 [类似Java中pojo构造方法]*/
      }
</style>
<p>这是p标签</p>
```

![image-20211118100835621](images\font-all.png)



### 文本样式

文本缩进

- 属性名：text-indent
- 取值：

1. 数字+px
2. 数字+em（推荐：1em = 当前标签的font-size的大小）

```html
<style>
    p {
        /* text-indent: 50px; */
        /* 首行缩进2个字的大小 */
        /* 默认字号: 16px ; 32 */
        /* text-indent: 40px;
        font-size: 20px; */

        /* em: 一个字的大小 */
        text-indent: 2em;
        font-size: 40px;
    }
</style>
<p>2019年，事件视界望远镜团队让世界首次看到了黑洞的样子。不过，研究人员公布的这张发光环形物体的图像并不是传统的图片，而是经过计算获得的。利用位于美国、墨西哥、智利、西班牙和南极地区的射电望远镜所得到的数据，研究人员进行了数学转换，最终合成了这张标志性的图片。研究团队还发布了实现这一壮举所用的编程代码，并撰文记录这一发现，其他研究者也可以在此基础上进一步加以分析。</p>
```

文本水平对齐方式

- 属性名：text-align

- 取值：

  | 属性值    | 效果   |
  | ------ | ---- |
  | left   | 左对齐  |
  | center | 居中对齐 |
  | right  | 右对齐  |

  

- 注意点：

​          如果需要让文本水平居中，text-align属性给文本所在标签（文本的父元素）设置

```html
<style>
    h1 {
        /* text-align: left; */
        /* text-align: right; */
        text-align: center;
    }

    body {
        text-align: right;
    }
</style>
<h1>新闻标题</h1>
<img src="./images/1.jpg" alt="">
```

![image-20211118100835621](images\center.png)

文本修饰

- 属性名：text-decoration
- 取值：

| 属性值          | 效果        |
| ------------ | --------- |
| underline    | 下划线（常用）   |
| line-through | 删除线（不常用）  |
| overline     | 上划线（几乎不用） |
| none         | 无装饰线（常用）  |



- 注意点：

​         开发中会使用 text-decoration : none ; 清除a标签默认的下划线

```html
<style>
    div {
        text-decoration: underline;
    }

    p {
        text-decoration: line-through;
    }

    h2 {
        text-decoration: overline;
    }

    a {
        text-decoration: none;
    }
</style>
<div>div</div>
<p>ppp</p>
<h2>h2</h2>
<a href="#">我是超链接, 点呀</a>
```

文本样式总计：

| 样式       | 属性名             | 常见属性值             |
| -------- | --------------- | ----------------- |
| 文本缩进     | text-indent     | 数字+px/数字+em       |
| 文本水平对齐方式 | text-align      | left/center/right |
| 文本修饰     | text-decoration | underline/none    |

### line-height行高

![image-20211118100835621](images\line-height.png)

- 作用：控制一行的上下行间距
- 属性名：line-height
- 取值：

1. 数字+px
2. 倍数（当前标签font-size的倍数）

- 应用：

1.  让单行文本垂直居中可以设置 line-height : 文字父元素高度
2.  网页精准布局时，会设置 line-height : 1 可以取消上下间距

- 行高与font连写的注意点：

1. 如果同时设置了行高和font连写，注意覆盖问题

2. font : style weight size/line-height family ;

   

```html
<style>
    p {
        /* line-height: 50px; */
        /* 自己字号的1.5倍 */
        /* line-height: 1.5; */

        /* 66px 宋体 倾斜 加粗 行高是2倍 */
        font: italic 700 66px/2 宋体;
    }
</style>
<p>2019年，事件视界望远镜团队让世界首次看到了黑洞的样子。不过，研究人员公布的这张发光环形物体的图像并不是传统的图片，而是经过计算获得的。利用位于美国、墨西哥、智利、西班牙和南极地区的射电望远镜所得到的数据，研究人员进行了数学转换，最终合成了这张标志性的图片。研究团队还发布了实现这一壮举所用的编程代码，并撰文记录这一发现，其他研究者也可以在此基础上进一步加以分析</p>
```

## 2.4 Chrome调试工具

1. 打开方式

   ① 右击 → 检查 ② 看哪里

2. 选择元素

​       两种常见方法

3. 控制样式 

   ① 修改属性值 ② 添加属性 ③ 控制样式生效

4. 特殊情况

   ① 出现删除线 ② 出现小三角形

![image-20211118100835621](images\chrome.png)



## 2.5 扩展

### 颜色

![image-20211118100835621](images\color.png)

- 取值类型1：关键词

​     常见颜色取值：

1. red：红色
2. green：绿色
3. blue：蓝色
4. yellow：黄色
5. orange：橘色
6. skyblue：天蓝色
7. pink：粉色

- 取值类型2：rgb表示法  每项取值范围：0~255

​    常见颜色取值：

1. rgb ( 255 , 0 , 0 ) ：红色
2. rgb ( 0 , 255 , 0 ) ：绿色
3. rgb ( 0 , 0 , 255 ) ：蓝色
4. rgb ( 0 , 0 , 0 ) ：黑色
5. rgb ( 255 , 255 , 255 ) ：白色

- 取值类型3：rgba表示法

  其实，比rgb表示法多个一个a，a表示透明度

  a的取值范围：0~1

  1：完全不透明

  0：完全透明

  省略写法：

  rgba ( 0 , 0 , 0 , 0.5 )  可以省略写成 rgba ( 0 , 0 , 0 , .5 )



- 取值类型4：十六进制表示法

![image-20211118100835621](images\color-rgb.png)





取值范围：

两个数字为一组，每个数字的取值范围：0~9 , a , b , c , d , e , f

 省略写法：

如果三组中，每组数字都相同，此时可以每组可以省略只写一个数字

正确写法：#ffaabb 改写成 #fab

常见取值：

1. #fff ：白色
2. #000 ：黑色

注意点

1. 类似于：#ffaabc 不能改写成 #fabc
2. 实际开发中会直接使用测量工具直接得到颜色，不需要前端自己设计颜色，直接复制粘贴即可。





### 标签水平居中

- 如果需要让div、p、h（大盒子）水平居中？

### 

​        可以通过margin : 0 auto ; 实现

- 注意点：

1. 如果需要让 div、p、h（大盒子） 水平居中，直接给 当前元素本身 设置即可
2. margin：0 auto 一般针对于固定宽度的盒子，如果大盒子没有设置宽度，此时会默认占满父元素的宽度

```html
<style>
    div {
        width: 300px;
        height: 300px;
        background-color: pink;
        margin: 0 auto;
    }
</style>
<div></div>
```



# 随堂练习：

## 1. 表单

### 效果：

![image-20211118100835621](images\ex01.png)

### 参考：

```html
<h1>注册用户</h1>
  <hr>
  <form action="./reg.jsp" method="post">
  昵称: <input type="text" name="nickName" placeholder="请输入昵称"> <br>
  性别: <input type="radio" name="sex" value="0" checked>男
  <input type="radio" name="sex" value="1">女 <br>
  所在城市: <select name="address">
    <option value="SH">上海</option>
    <option value="NJ">南京</option>
    <option value="BJ">北京</option>
  </select> <br>
  喜欢的类型: <input type="checkbox" name="likes" value="1001" checked>可爱
  <input type="checkbox" name="likes" value="1002" checked>性感
  <input type="checkbox" name="likes" value="1003">御姐 <br>
  个人介绍: <br>
  <textarea name="info" cols="30" rows="10"></textarea>
  <br>
   <h3>我承诺</h3> 
   <ul>
    <li>单身</li>
    <li>单身</li>
    <li>单身</li>
   </ul>
   <input type="submit" value="免费注册">
   <input type="reset" value="重置">
</form>
```



## 2. 新闻

### 效果：

![image-20211118100835621](images\news.png)

### 素材：

```
《自然》评选改变科学的10个计算机代码项目
2077年01月28日14:58 新浪科技 收藏本文

2019年，事件视界望远镜团队让世界首次看到了黑洞的样子。不过，研究人员公布的这张发光环形物体的图像并不是传统的图片，而是经过计算获得的。利用位于美国、墨西哥、智利、西班牙和南极地区的射电望远镜所得到的数据，研究人员进行了数学转换，最终合成了这张标志性的图片。研究团队还发布了实现这一壮举所用的编程代码，并撰文记录这一发现，其他研究者也可以在此基础上进一步加以分析。

这种模式正变得越来越普遍。从天文学到动物学，在现代每一项重大科学发现的背后，都有计算机的参与。美国斯坦福大学的计算生物学家迈克尔·莱维特因“为复杂化学系统创造了多尺度模型”与另两位研究者分享了2013年诺贝尔化学奖，他指出，今天的笔记本电脑内存和时钟速度是他在1967年开始获奖工作时实验室制造的计算机的1万倍。“我们今天确实拥有相当可观的计算能力，”他说，“问题在于，我们仍然需要思考。”

如果没有能够解决研究问题的软件，以及知道如何编写并使用软件的研究人员，一台计算机无论再强大，也是毫无用处的。如今的科学研究从根本上已经与计算机软件联系在一起，后者已经渗透到研究工作的各个方面。近日，《自然》（Nature）杂志将目光投向了幕后，着眼于过去几十年来改变科学研究的关键计算机代码，并列出了其中10个关键的计算机项目。

最初的现代计算机并不容易操作。当时的编程实际上是手工将电线连接成一排排电路来实现的。后来出现了机器语言和汇编语言，允许用户用代码为计算机编程，但这两种语言都需要对计算机的架构有深入的了解，使得许多科学家难以掌握。20世纪50年代，随着符号语言的发展，特别是由约翰·巴克斯及其团队在加州圣何塞的IBM开发的“公式翻译”语言Fortran，这种情况发生了变化。利用Fortran，用户可以用人类可读的指令来编程，例如x = 3 + 5。然后由编译器将这些指令转换成快速、高效的机器代码。
```

### 参考:

```html
<style>
  .content {
    width: 800px;
    height: 570px;
    /* background-color: pink; */
    margin: 0 auto;
  }

  .center {
    text-align: center;
  }

  .c1 {
    color: #ffcf9f;
  }

  .c2 {
    color: #b7eeff;
    font-weight: 700;
  }

  a {
    text-decoration: none;
  }

  .tab {
    text-indent: 2em;
  }
</style>
</head>
<body>
<div class="content">
  <h1 class="center">《自然》评选改变科学的10个计算机代码项目</h1>
  <p class="center">
    <span class="c1">2077年01月28日14:58</span>
    <span class="c2">新浪科技</span> <a href="">收藏本文</a>
  </p>
  <hr />
  <p class="tab">
    2019年，事件视界望远镜团队让世界首次看到了黑洞的样子。不过，研究人员公布的这张发光环形物体的图像并不是传统的图片，而是经过计算获得的。利用位于美国、墨西哥、智利、西班牙和南极地区的射电望远镜所得到的数据，研究人员进行了数学转换，最终合成了这张标志性的图片。研究团队还发布了实现这一壮举所用的编程代码，并撰文记录这一发现，其他研究者也可以在此基础上进一步加以分析。
  </p>
  <p class="tab">
    这种模式正变得越来越普遍。从天文学到动物学，在现代每一项重大科学发现的背后，都有计算机的参与。美国斯坦福大学的计算生物学家迈克尔·莱维特因“为复杂化学系统创造了多尺度模型”与另两位研究者分享了2013年诺贝尔化学奖，他指出，今天的笔记本电脑内存和时钟速度是他在1967年开始获奖工作时实验室制造的计算机的1万倍。“我们今天确实拥有相当可观的计算能力，”他说，“问题在于，我们仍然需要思考。”
  </p>
</div>
```





## 3. 产品

### 效果:

![image-20211118100835621](images\product.png)



### 素材:

```
九号平衡车
成年人的玩具
1999元
```



### 参考：

```html
<style>
    body{
      background-color: #f5f5f5;
    }
    .goods{
      width: 235px;
      height: 300px;
      background-color: #fff;
      margin: 0 auto;
      text-align: center;
    }

    img{
      width: 160px;
    }

    .title{
      font-size: 14px;
      line-height: 25px;
    }

    .desc{
      font-size: 12px;
      color: #cccccf;
      line-height: 30px;
    }

    .price{
      font-size: 14px;
      color: #ffa500;
    }
  </style>
</head>
<body>
  <div class="goods">
    <img src="./car.jpg" alt="">
    <div class="title">九号平衡车</div>
    <div class="desc">成年人的玩具</div>
    <div class="price">1999元</div>
  </div>
```

