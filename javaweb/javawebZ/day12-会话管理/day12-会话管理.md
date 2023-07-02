# 1. 会话管理

```
Q1：为什么使用会话管理？
http协议是无状态的【一问一答模式，没有上下文】
```

![image-20220608111709081](images\image-20220608111709081.png)



## 1.1 Cookie

```
存储在 客户端 浏览器中： 
比如：
 <script>
   alert(document.cookie)
 </script> 
```



### 常用方法

```java
//1.创建Cookie对象
Cookie cookie = new Cookie("name","value") ;
//2. 写入客户端浏览器
response.addCookie(cookie) ;

// 获得所有cookie
Cookie[] cookieAy = request.getCookies() ;
```

![image-20210806085831519](images\cookie.png)



### 跨域

```
// 跨域访问 cookie
```



### 中文处理

```java
// 中文转 Unicode编码
java.net.URLEncoder.encode("中文","UTF-8") ;
// Unicode 编码转 中文
java.net.URLDecoder.decode("Unicode字符串","UTF-8") ;
```

### 随堂练习1:

```
要求: 
    使用cookie技术，实现 跨页[分步表单] 表单提交功能
效果如下：    
```

![image-20210806085831519](images\ex-cookie.png)

add-step01.jsp

```jsp
<h1>添加商品 cookie 第一步</h1>
<form action="./add-step02.jsp" method="get">
   <input type="text" name="id" placeholder="请输入商品编号"> <br>
   <input type="text" name="name" placeholder="请输入商品名称"> <br>
   <button>下一步</button>
</form>
```

add-step02.jsp

```jsp
<%
//只针对 post 请求
request.setCharacterEncoding("UTF-8");
String id = request.getParameter("id");
String name = request.getParameter("name");
// 中文转 Unicode编码
name = java.net.URLEncoder.encode(name,"UTF-8") ;
Cookie idCookie = new Cookie("id",id) ;
Cookie nameCookie = new Cookie("name",name) ;

response.addCookie(idCookie);
response.addCookie(nameCookie);
%>
<h1>添加商品 cookie 第二步</h1>
<form action="./add-step03.jsp" method="post">
   <input type="text" name="area" placeholder="请输入商品产地"> <br>
   <input type="text" name="stock" placeholder="请输入商品库存"> <br>
   <button>完成</button>
</form>
```

add-step03.jsp

```jsp
<%
   //只针对 post 请求
   request.setCharacterEncoding("UTF-8");
   Cookie[] cookies = request.getCookies();
   String id = null ;
   String name = null ;
   for (Cookie cookie : cookies) {
       if ("id".equals(cookie.getName())){
           id = cookie.getValue() ;
       }else if("name".equals(cookie.getName())){
           name = cookie.getValue() ;
           // Unicode编码 转中文
           name = java.net.URLDecoder.decode(name,"UTF-8") ;
       }
   }
%>
<h1>添加商品信息[使用 cookie 技术]</h1>
编号：<%=id%> <br>
产品：<%=name%> <br>
产地：<%=request.getParameter("area")%> <br>
库存：<%=request.getParameter("stock")%> <br>
```

## 1.2 session

```
存储在 服务器上 tomcat   5K计划  5000 tomcat
```

![image-20210806085831519](images\session-seq.png)



### 常用方法

![image-20210806085831519](images\session.png)

### 用户登录

![image-20210806085831519](images\login.png)

login.jsp

```html
<h1>用户登录</h1>
<form action="./login-do.jsp" method="post">
    <input type="text" name="account" placeholder="请输入账号" value="<%=request.getParameter("account")%>"> <br>
    <input type="text" name="pswd" placeholder="请输入密码" value="<%=request.getParameter("pswd")%>"> <br>
    <button>登录</button>
</form>
<%=request.getAttribute("msg")%>
```

User.java

```java
@Data
public class User {
    private String account ;
    private String username ;
    private String pswd ;
}
```

login-do.jsp

```jsp
<jsp:useBean id="user" class="com.wanho.java171.entity.User"/>
<jsp:setProperty name="user" property="*"/>
<%
    //假设 admin  密码 123 登录成功
    if ("admin".equals(user.getAccount()) && "123".equals(user.getPswd())){
        session.setAttribute("user",user);
        response.sendRedirect("./main.jsp");
        return ;
    }
    //登录失败
    request.setAttribute("msg","用户名或密码错误！！！");
    //跳转到登录页面 login.jsp
    request.getRequestDispatcher("./login.jsp").forward(request,response);
%>
```

main.jsp

```jsp
<h1>欢迎<%=((User)session.getAttribute("user")).getAccount()%></h1>
```



### 随堂练习2：

```
要求：
    拷贝随堂练习1的界面，使用 session实现分步表单提交功能？
```

add-step01.jsp

```jsp
<h1>添加商品 session 第一步</h1>
<form action="./add-step02.jsp" method="get">
   <input type="text" name="id" placeholder="请输入商品编号"> <br>
   <input type="text" name="name" placeholder="请输入商品名称"> <br>
   <button>下一步</button>
</form>
```

add-step02.jsp

```jsp
<%
    //只针对 post 请求
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    session.setAttribute("id",id);
    session.setAttribute("name",name);
%>
<h1>添加商品 session 第二步</h1>
<form action="./add-step03.jsp" method="get">
   <input type="text" name="area" placeholder="请输入商品产地"> <br>
   <input type="text" name="stock" placeholder="请输入商品库存"> <br>
   <button>完成</button>
</form>
```

add-step03.jsp

```jsp
<h1>添加商品信息[使用 session 技术]</h1>
编号：<%=session.getAttribute("id")%> <br>
产品：<%=session.getAttribute("name")%> <br>
产地：<%=request.getParameter("area")%> <br>
库存：<%=request.getParameter("stock")%> <br>
```



## 1.3 URL重写

```java
response.encodeURL("index.jsp?c=1&wd=java")
```

### 随堂练习3：

```
要求：
    拷贝随堂练习1的界面，使用重写 URL 实现分步表单提交功能？
```

add-step01.jsp

```jsp
<h1>添加商品 url 第一步</h1>
<form action="./add-step02.jsp" method="get">
   <input type="text" name="id" placeholder="请输入商品编号"> <br>
   <input type="text" name="name" placeholder="请输入商品名称"> <br>
   <button>下一步</button>
</form>
```

add-step02.jsp

```jsp
<%
    //只针对 post 请求
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
//    String url = response.encodeURL("./add-step03.jsp?id=" + id + "&name=" + name);
//    System.out.println(url);
%>
   <h1>添加商品 url 第二步</h1>
   <form action="./add-step03.jsp?id=<%=id%>&name=<%=name%>" method="post">
       <input type="text" name="area" placeholder="请输入商品产地"> <br>
       <input type="text" name="stock" placeholder="请输入商品库存"> <br>
       <button>完成</button>
   </form>
```

add-step03.jsp

```jsp
<h1>添加商品信息[使用 url 技术]</h1>
<%
   request.setCharacterEncoding("UTF-8");
%>
编号：<%=request.getParameter("id")%> <br>
产品：<%=request.getParameter("name")%> <br>
产地：<%=request.getParameter("area")%> <br>
库存：<%=request.getParameter("stock")%> <br>
```

## 1.4 hidden隐藏表单

```
使用 ： <input type="hidden" /> 实现会话管理
```

### 随堂练习4：

```
要求：
     拷贝随堂练习1 ，使用 hidden隐藏表单，实现分步表单提交？
     
     其中 add-step01.jsp  add-step03.jsp 与重写url一致！
```

add-step02.jsp

```jsp
<%
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
%>
<h1>添加商品  使用 hidden  第二步</h1>
<form action="./add-step03.jsp" method="get">
    <input type="hidden" name="id" value="<%=id%>">
    <input type="hidden" name="name" value="<%=name%>">
    <input type="text" name="area" placeholder="请输入商品产地"> <br>
    <input type="text" name="stock" placeholder="请输入商品库存"> <br>
    <button>完成</button>
</form>
```



# 2. 简化登录

使用 session 与 cookie 配合使用：

直接访问 main.jsp  [活动图]

![image-20210806085831519](images\cookie-active.png)

login.jsp

```jsp
<h2>用户登录</h2>
<form action="./login-do.jsp" method="post">
    <input type="text" name="account" placeholder="请输入账号"> <br>
    <input type="text" name="pswd" placeholder="请输入密码"> <br>
    <input type="checkbox" name="rememberMe" value="true">7天记住密码 <br>
    <button>提交</button>
</form>
```

Admin.java

```java
public class Admin {
    private String account ;
    private String username ;
    private String pswd ;
}
```

login-do.jsp

```jsp
<%
    //假设 数据库中 有3条记录
    Map<String, Admin> adminMap = new HashMap<>() ;
    adminMap.put("zhang3",new Admin("zhang3","张三","111")) ;
    adminMap.put("li4",new Admin("li4","李四","222")) ;
    adminMap.put("wang5",new Admin("wang5","王五","333")) ;

    //获得用户输入的值
    String account = request.getParameter("account");
    String pswd = request.getParameter("pswd");
    String rememberMe = request.getParameter("rememberMe");

    //尝试从 map 根据key取值
    Admin admin = adminMap.get(account);
    //判断 账号不存在  密码错误
    if (admin==null || !admin.getPswd().equals(pswd)){
%>
<h2>用户名或密码错误！！！</h2>
<%
        return ;
    }
    //用户登录成功
    //用户选中 7 天记住密码
    if("true".equals(rememberMe)){
        //把账号  密码  写入cookie中  设置cookie的失效时间 为7天
        Cookie accountCookie = new Cookie("account", account);
        accountCookie.setMaxAge(7*24*60*60);
        response.addCookie(accountCookie);
        Cookie pswdCookie = new Cookie("pswd", pswd);
        pswdCookie.setMaxAge(7*24*60*60);
        response.addCookie(pswdCookie);
    }
    //当前会话中 绑定已经登录的用户
    session.setAttribute("admin",admin);
    //跳转到主页面
    response.sendRedirect("./main.jsp");
%>
```

main.jsp

```jsp
<h1>管理后台</h1>
<%
    //1.尝试从 session 中 获得 admin的key
    Admin admin = (Admin) session.getAttribute("admin");
    //2. 当前会话中 有 admin key 直接欢迎xxx
    if (admin!=null){
%>
<h2>欢迎<%=admin.getUsername()%></h2>
<%
        return ;
    }
    //3.尝试从 cookie中获得
    String account = null ;
    String pswd = null ;
    Cookie[] cookieAy = request.getCookies();
    if (cookieAy!=null && cookieAy.length!=0){
        for (Cookie cookie : cookieAy) {
            if("account".equals(cookie.getName())){
                account = cookie.getValue() ;
            }else if("pswd".equals(cookie.getName())){
                pswd = cookie.getValue() ;
            }
        }
    }
    //4. 如果cookie没有值
    if (account==null || pswd==null){
        //跳转到登录页面
        response.sendRedirect("./login.jsp");
        return;
    }
    //5. cookie 获得账号和密码
    response.sendRedirect("./login-do.jsp?account="+account+"&pswd="+pswd);
%>
```



# 3. 购物车

## 3.1 商品列表

![image-20210806085831519](images\index.png)

index.jsp

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
  <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
  <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <style>
    *{
      text-align: center;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>商品列表</h1>
  <div class="row" >
    <div class="col-md-3">
      <img src="<%=request.getContextPath()%>/images/game.jpg"/>
    </div>
    <div class="col-md-3">
      <img src="<%=request.getContextPath()%>/images/hppavilion.jpg"/>
    </div>
    <div class="col-md-3">
      <img src="<%=request.getContextPath()%>/images/lcd.jpg"/>
    </div>
    <div class="col-md-3">
      <img src="<%=request.getContextPath()%>/images/learning.jpg"/>
    </div>
  </div>
  <div class="row" >
    <div class="col-md-3">
      编号:1,单价：10,名称:游戏机
    </div>
    <div class="col-md-3">
      编号:2,单价：3000,名称:笔记本
    </div>
    <div class="col-md-3">
      编号:3,单价：800,名称:显示器
    </div>
    <div class="col-md-3">
      编号:4,单价：40,名称:小霸王
    </div>
  </div>
  <div class="row" >
    <div class="col-md-3">
      <a href="<%=request.getContextPath()%>/add-item-do.jsp?id=1&price=10&pic=game.jpg&name=游戏机"><img src="<%=request.getContextPath()%>/images/buybutton.gif"/></a>
    </div>
    <div class="col-md-3">
      <a href="<%=request.getContextPath()%>/add-item-do.jsp?id=2&price=3000&pic=hppavilion.jpg&name=笔记本"><img src="<%=request.getContextPath()%>/images/buybutton.gif"/></a>
    </div>
    <div class="col-md-3">
      <a href="<%=request.getContextPath()%>/add-item-do.jsp?id=3&price=800&pic=lcd.jpg&name=显示器"><img src="<%=request.getContextPath()%>/images/buybutton.gif"/></a>
    </div>
    <div class="col-md-3">
      <a href="<%=request.getContextPath()%>/add-item-do.jsp?id=4&price=40&pic=learning.jpg&name=小霸王"><img src="<%=request.getContextPath()%>/images/buybutton.gif"/></a>
    </div>
  </div>
  <div class="row" >
    <div class="col-md-3">
      <img src="<%=request.getContextPath()%>/images/lenovo.jpg"/>
    </div>
    <div class="col-md-3">
      <img src="<%=request.getContextPath()%>/images/mouse.jpg"/>
    </div>
    <div class="col-md-3">
    </div>
    <div class="col-md-3">
    </div>
  </div>
  <div class="row" >
    <div class="col-md-3">
      编号:5,单价：6800,名称:联想电脑
    </div>
    <div class="col-md-3">
      编号:6,单价：15,名称:逻辑鼠标
    </div>
    <div class="col-md-3">
    </div>
    <div class="col-md-3">
    </div>
  </div>
  <div class="row" >
    <div class="col-md-3">
      <a href="<%=request.getContextPath()%>/add-item-do.jsp?id=5&price=6800&pic=lenovo.jpg&name=联想电脑"><img src="<%=request.getContextPath()%>/images/buybutton.gif"/></a>
    </div>
    <div class="col-md-3">
      <a href="<%=request.getContextPath()%>/add-item-do.jsp?id=6&price=15&pic=mouse.jpg&name=逻辑鼠标"><img src="<%=request.getContextPath()%>/images/buybutton.gif"/></a>
    </div>
    <div class="col-md-3">
    </div>
    <div class="col-md-3">
    </div>
  </div>
</div>
</body>
</html>
```

Item.java

```java
public class Item {
    private String id ;
    private String pic;
    private String name ;
    private Integer price ;
    private Integer count = 1;
}
```

add-item-do.jsp

```jsp
<jsp:useBean id="item" class="com.wanho.java173.pojo.Item"/>
<jsp:setProperty name="item" property="*"/>
<%
    //1. 封装请求参数
    //2. 尝试从session 获得 key "cart"  value Map<String,Item> [new HashMap 单例]
    Map<String, Item> itemMap = (Map<String, Item>) session.getAttribute("cart");
    //3. 第一次添加商品
    if (itemMap==null) {
        //4.创建 单例 map实例
        itemMap = new LinkedHashMap<>() ;
        //5.绑定 session 会话中
        session.setAttribute("cart",itemMap);
    }
    //6. map中存放数据
    itemMap.put(item.getId(),item) ;
    //7. 跳转到购物车列表页面
    response.sendRedirect("./cart.jsp");
%>
```

## 3.2 购物车列表页

![image-20210806085831519](images\cart.png)

cart.jsp

```jsp
<%@ page import="com.wanho.java173.pojo.Item" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.js"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
    <style>
        h1,h2,th,td{
            text-align: center;
        }
        thead{
            background: #ebeaea;
        }
        img{
            width: 60px;
            height: 60px;
        }
        .table tbody tr td{
            vertical-align: middle;
        }
    </style>
    <script>

    </script>
</head>
<body>
<div class="container">
    <h1>我的购物车 </h1>
    <h2>
        <a href="<%=request.getContextPath()%>/index.jsp">继续购物</a>
        <a href="<%=request.getContextPath()%>/clear-do.jsp">清空购物车</a>
    </h2>
    <%
        //从session 中 获得所有商品列表
        Map<String, Item> itemMap = (Map<String, Item>) session.getAttribute("cart");
        //非法访问
       /* if (itemMap==null){
            response.sendRedirect("./index.jsp");
            return;
        }*/
       //购物车中商品为空
       if (itemMap.isEmpty()){
    %>
    <!-- 购物车中没有任何商品 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <h1>购物车中没有任何宝贝</h1>
    </div>
    <%
           return ;
       }
    %>
    <!-- 购物车中有商品 -->
    <table class="table table-hover table-striped table-bordered">
        <thead>
        <tr>
            <th>图片</th>
            <th>名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>小计</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Item item : itemMap.values()) {
        %>
        <tr>
            <td><img src="<%=request.getContextPath()%>/images/<%=item.getPic()%>" class="img-thumbnail"> </td>
            <td><%=item.getName()%></td>
            <td><%=item.getPrice()%></td>
            <td>
                <form action="" class="form-inline">
                    <button class="btn btn-primary" type="button" onclick="">-</button>
                    <input type="text" id="count" class="form-control" value="<%=item.getCount()%>" style="width: 40px">
                    <button class="btn btn-primary"  type="button" onclick="">+</button>
                </form>
            </td>
            <td><%=item.getPrice()*item.getCount()%></td>
            <td>
                <a href="javaScript:" class="btn btn-info btn-sm">修改</a>
                <a href="javaScript:" class="btn btn-danger btn-sm">删除</a>
            </td>
        </tr>
        <%}%>
        </tbody>
        <tfoot>
        <tr>
            <td>总计:</td>
            <td colspan="5" style="text-align: left">￥15</td>
        </tr>
        </tfoot>
    </table>

    <!--删除确认弹框-->
    <div class="modal fade" tabindex="-1" role="dialog" id="deleteItemTip" aria-labelledby="gridSystemModalLabel">
        <input type="hidden" id="delId">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                </div>
                <div class="modal-body">
                    确认删除此商品？
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary deleteSure" onclick="doDelItem()">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
```

