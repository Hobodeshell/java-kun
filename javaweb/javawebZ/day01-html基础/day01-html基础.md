# 0.网页认知

## web标准

![html-css-js](images\web标准.png)



![html-css-js](images\html-css-js.png)

## html结构

![html](images\html.png)

## 标签结构

![html-tag](images\html-tag.png)

## 标签关系

![html-tag-ref](images\html-tag-ref.png)

## 初体验

*.html    *.htm

```html
hello  <h1>html</h1> 
```

## 工具

![vscode-chrome](images\剪头.png)

![vscode-chrome](images\开发工具.png)



![vscode-chrome](images\浏览器.png)



![vscode-chrome](images\vscode-chrome.png)

### vs code

####  黑屏

![image-20220225095730117](images\vscode-01.png)

![image-20220225095832942](images\image-20220225095832942.png)

#### 安装插件

![image-20220225100336773](images\image-20220225100336773.png)

#### 设置字体

![image-20220225100837538](images\image-20220225100837538.png)

![image-20220225100937997](images\image-20220225100937997.png)

```json
{
  "editor.fontSize": 18,
  "editor.tabSize": 2,
  "editor.defaultFormatter": "esbenp.prettier-vscode",
  "editor.formatOnSave": true,
  "prettier.semi": false,
  "prettier.singleQuote": true,
  "files.autoSave": "afterDelay",
  "git.enableSmartCommit": true,
  "liveServer.settings.CustomBrowser": "chrome",
  "explorer.confirmDelete": false
}
```

#### 体验

![image-20220225102055414](images\image-20220225102055414.png)

# 1. HTML基本标签

## 1.1 排版标签

### 标题

```html
<!-- 注释：ctrl+/   Ement 1. 标签名 直接写  2.出现次数 *3   3. h${排版标签$}*6  -->
<h1>一级标签</h1>
<h2>二级标签</h2>
<h3>三级标签</h3>
<h4>四级标签</h4>
<h5>五级标签</h5>
<h6>六级标签</h6>
```

### 段落

```html
<!-- p{段落$}*3 -->
<p>段落1</p>
<p>段落2</p>
<p>段落3</p>
```

### 换行

```html
<!-- (br+{换行$})*3 -->
<br>换行1 
<br>换行2 
<br>换行3
```

### 水平线

```html
 aaaaaaaaaaa <hr>
 bbbbbbb 
```

## 1.2 文本格式化

![font](images\font.png)

```java
if(...){
    ....
}
或
if( ...)
   ....
```

```html
<strong>aaaa</strong>   <b>aaaa</b>  <br>
<ins>bbbb</ins>          <u>bbbb</u> <br>
<em>ccccc</em>          <em>cccc</em> <br>
<del>ddddd</del>         <s>ddddd</s>
```

```sql
-- ID数据类型  varchar(20)   Full Table 全表扫描  index 索引
-- 隐式转换 无法使用索引

select * from t where id = 1 ;  -- 全表
select * from t where id = '1' ; -- 索引
```



## 1.3 媒体标签

### 图片

![image](images\image.png)

![img-prop](images\img-prop.png)

```html
<h1 title="测试h1 title属性"> title属性</h1>
<img src="2.png" alt="这是一张图片" title="这是提示" width="40px">
<img src="2.png" alt="这是一张图片" title="这是提示" height="20px">
<img src="2.png" alt="这是一张图片" title="这是提示" height="2000px" width="20px">
```



### 路径

#### 相对路径

```
相对: 当前路径  当前文件 *.html
使用 . 开头
```



同级目录

```html
<img src="map.jpg" alt="">
或
<img src="./map.jpg" alt="">
```

下级目录

```html
<img src="./images/2.png" alt="">
```

上级目录

```html
<img src="../images/2.png" alt="">
注意：目前 使用 live Server ：根 code 目录 code/**
<img src="../../../doc/day01-html基础/images/html-css-js.png" alt="">
```

#### 绝对路径

使用协议开头

盘符： D:/.xxx/sd/x.jpg [不推荐]

```html
<img src="D:\java172_2\code\day01-html基础\map.jpg" alt="">
```

完整URL

```
URL:     协议 :// 主机【ip/域名】:端口/资源名?参数
比如:    jdbc:mysql://localhost:3306/test?useEncoding=UTF-8
        jdbc:mysql:///test?useEncoding=UTF-8

https://demo-video-oss.oss-cn-hangzhou.aliyuncs.com/res/2.png
https://demo-video-oss.oss-cn-hangzhou.aliyuncs.com/res/2.png?x-oss-process=image/resize,w_60,h_60
```

```html
<img src="https://demo-video-oss.oss-cn-hangzhou.aliyuncs.com/res/2.png" alt="">
<img src="https://demo-video-oss.oss-cn-hangzhou.aliyuncs.com/res/2.png?x-oss-process=image/resize,w_60,h_60" alt="">
```

#### 服务器路径

```html
使用 / 开头
/  = http://127.0.0.1:5500/

只能访问当前服务器内部的资源 无法访问服务器之外资源
```

### 音频

属性： boolean 值属性  【controls ， autoplay , loop 】

谷歌浏览器 不支持 自动播放

![audio](images\audio.png)

```html
<audio src="./res/music.mp3" controls autoplay loop></audio>
```

### 视频

![audio](images\video.png)

```html
<!-- autoplay muted 谷歌浏览器 一起使用 -->
<video src="./res/video.mp4" controls autoplay muted loop></video>
```

## 1.4 超链接标签

href 属性值：  默认当前页面

```html
<!-- 相对路径 -->
<a href="./09-随堂练习招聘.html">跳转到招聘页面</a>
<!-- 绝对路径 -->
<a href="http://www.baidu.com:80">跳转到百度</a>
<!-- 打开目标页面位置 target=""  _self  默认值： 当前页面   _blank 新页面打开 空白页面-->
<a href="http://www.baidu.com" target="_blank">跳转到百度</a>
```

锚点链接 hash

```html
url#对应的id值

<a href="./15.锚点链接.html#a">跳转第一章</a>
<a href="#b">跳转第二章</a>
<a href="#c">跳转第三章</a>
<h2 id="a">第一章</h2>
<p>data</p>
<p>data</p>
<p>data</p>
<p>data</p>
<h2 id="b">第二章</h2>

<a href="./15.锚点链接.html#b">跳转锚点链接 第二章</a>
```

## 1.5列表标签

### 无序列表

```html
<!-- ul>li*2{包裹内容} -->
<ul>
    <li>空指针</li>
    <li>类型转换异常</li>
</ul>
```

### 有序列表

```html
<!-- ol>li*2{包裹内容} -->
<ol>
    <li>注册基本信息</li>
    <li>实名认证</li>
    <li>标签选择</li>
</ol>
```

### 自定义列表

```html
<!--  dl>dt{xxx}+dd*2{data}   > 儿子  + 弟弟 -->
<dl>
  <dt>xxx</dt>
  <dd>data</dd>
  <dd>data</dd>
</dl>
```

![list](images\list.png)

## 1.6 表格标签

```html
<!-- 属性 [属性名='值'] id属性名 #d1 class属性值 .c1 -->
<!-- table[border='1']>tr*3>td*2{data} -->
<table border="1">
  <caption>
    JAVA180
  </caption>
  <thead>
    <tr>
      <th>desc</th>
      <th>header</th>
      <th>header</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td rowspan="3">JAVA最优秀的班级</td>
      <td>....................data</td>
      <td>data</td>
    </tr>
    <tr>
      <td>data</td>
      <td>data</td>
    </tr>
    <tr>
      <td>data</td>
      <td>data</td>
    </tr>
  </tbody>
  <tfoot>
    <tr>
      <td colspan="3">很好的班级</td>
    </tr>
  </tfoot>
</table>
```

## 1.7 表单标签

### input

```
1. type 类型属性
   text 单行文本框    默认类型
   password 密码框
   radio    单选按钮  必须相同name值 不同value 值    布尔值属性：checked  选中状态
   checkbox 复选按钮  必须相同name值 不同value 值    布尔值属性：checked  选中状态
   button   js 语言进行调用
   submit   提交按钮   配合 form表单
   reset    重置按钮
```



```html
<form action="">
  姓名: <input type="text" /> <br />
  <!--  input[type="password"] 表单  String pswd = request.getParameter("pswd") ; -->
  密码: <input type="password" name="pswd" id="" /> <br />
  性别: <input type="radio" name="sex" id="" value="0" />男
  <input type="radio" name="sex" id="" value="1" checked />女<br />
  <!-- String[] likesAy = request.getParameterValues("likes") ; -->
  爱好: <input type="checkbox" name="likes" value="CS" id="" checked />CS
  <input type="checkbox" name="likes" value="LOL" id="" checked />LOL<br />
  <input type="button" value="button按钮" />
  <input type="submit" value="提交" />
  <input type="reset" value="重置" />
</form>
```

# 随堂练习

## 1. 招聘信息

效果

![zp](images\zp.png)

素材

```
招聘案例-文本资料

腾讯科技高级web前端开发岗位
职位描述
负责重点项目的前端技术方案和架构的研发和维护工作；
岗位要求
5年以上前端开发经验， 精通html5/css3/javascript等 web开发技术；
熟悉bootstrap，vue，angularjs，reactjs等框架，熟练掌握一种以上；
代码⻛格严谨，能⾼保真还原设计稿，能兼容各种浏览器； 对web前端的性能优化以及web常见漏洞有一定的理解和相关实践；
具备良好的分析解决问题能力，能独立承担任务，有开发进度把控能力；
责任心强，思路路清晰，抗压能力好，具备良好的对外沟通和团队协作能力。
工作地址
上海市-徐汇区-腾云大厦
```

参考

```html
<h1>腾讯科技高级web前端开发岗位</h1>
<hr>
<h2>职位描述</h2>
<p>负责重点项目的前端技术方案和架构的研发和维护工作；</p> 
<h2>岗位要求</h2>
<p>5年以上前端开发经验， <strong>精通html5/css3/javascript等</strong>  web开发技术；</p>
<p>熟悉bootstrap，vue，angularjs，reactjs等框架，熟练掌握一种以上；</p> 
<p>代码⻛格严谨，能⾼保真还原设计稿，能兼容各种浏览器； 对web前端的性能优化以及web常见漏洞有一定的理解和相关实践；</p> 
<p>具备良好的分析解决问题能力，能独立承担任务，有开发进度把控能力；</p>
<p>责任心强，思路路清晰，抗压能力好，具备良好的对外沟通和团队协作能力。</p>
<h2>工作地址</h2>
<p>上海市-徐汇区-腾云大厦</p> 

<img src="./map.jpg" alt="">
<img src="./images/2.png" alt="">
```



## 2. 今日热词

效果

![href](images\href.png)

素材

```
今日热词案例-文字资料

今日搜索热词
1、阿卡贝拉
阿卡贝拉（意大利：Acappella )即无伴奏合唱。其起源可追溯至中世纪的教会音乐，当时的教会音乐只以人声清唱，并不应用乐器。音频示例：阿卡贝拉《千与千寻》 2、翻唱
“翻唱”是指将已经发表并由他人演唱的歌曲根据自己的风格重新演唱，包括重新填词，编曲。现在已有不少明星，都在翻唱他人的歌，不断在翻唱中突破自己，给大家带
来不一样的风格。视频示例： 有一种悲伤（翻唱）-《A Kind of Sorrow》
```

参考

index.html

```html
<h1>今日搜索热词</h1>
<hr />
<h2>1、阿卡贝拉</h2>
<p>
  阿卡贝拉（意大利：Acappella
  )即无伴奏合唱。其起源可追溯至中世纪的教会音乐，当时的教会音乐只以人声清唱，并不应用乐器。音频示例：<a href="./one.html" target="_blank">阿卡贝拉《千与千寻》</a> 
</p>

<h2>2、翻唱</h2>
<p>
  “翻唱”是指将已经发表并由他人演唱的歌曲根据自己的风格重新演唱，包括重新填词，编曲。现在已有不少明星，都在翻唱他人的歌，不断在翻唱中突破自己，给大家带
  来不一样的风格。视频示例： <a href="./two.html" target="_blank">有一种悲伤（翻唱）-《A Kind of Sorrow》</a>
</p>
```

one.html

```html
<h1>音频示例：阿卡贝拉《千与千寻》</h1>
<hr>
<h2>音频</h2>
<audio src="../res/music.mp3" controls loop autoplay></audio>
```

two.html

```html
<h1>视频示例： 有一种悲伤（翻唱）-《A Kind of Sorrow》</h1>
<hr>
<h2>视频</h2>
<video src="../res/video.mp4" controls loop autoplay muted></video>
```

## 3. 学生信息

![table](images\table.png)

参考

```html
<table  border="1"  width="500" height="300">
    <caption>
      <h3>JAVA最优秀的班级</h3>
    </caption>
    <thead>
      <tr>
        <th>校区</th>
        <th>姓名</th>
        <th>学号</th>
        <th>班级</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td rowspan="3">卡子门</td>
        <td>张3</td>
        <td>18</td>
        <td>JAVA172</td>
      </tr>
      <tr>
        <td>李4</td>
        <td>19</td>
        <td>JAVA172</td>
      </tr>
      <tr>
        <td>王5</td>
        <td>20</td>
        <td>JAVA173</td>
      </tr>
    </tbody>
    <tfoot>
      <tr>
        <td>评语</td>
        <td colspan="3">很优秀</td>
      </tr>
    </tfoot>
</table>
```



