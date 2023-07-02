# 1. JSP简介

## 1.1 JSP简介

```
1. JSP 全名 java server page，为了简化Sevlet的工作而出现的替代品。
   Servelt 输出HTML比较困难，部署过程比较复杂。
   JSP 包括 
      Java Bean
      自定义标签（Custom Tags）
      EL 表达式（Expression Language） 
      JSTL 标准标签库（Java Standard Tag Library）
   在视图层（View Tier） 有很大的优势。   
```

### 随堂练习1:

```java
要求:
    使用jsp实现根据服务器的系统时间【获得小时】，响应对应的消息【上午好/下午好】！
    提供的代码片段：
    // 获得用户的本地化信息
    Locale local = request.getLocale() ;
    // 获得用户所在地的时间
    Calendar calendar = Calendar.getInstance(local) ;
    //获得小时
    int hour = calendar.get(Calendar.HOUR_OF_DAY) ;
```

```jsp
<%
    // 获得用户的本地化信息
    Locale local = request.getLocale() ;
    // 获得用户所在地的时间
    Calendar calendar = Calendar.getInstance(local) ;
    //获得小时
    int hour = calendar.get(Calendar.HOUR_OF_DAY) ;
    if (hour<=12){
        out.print("上午好");
    }else{
        out.print("下午好");
    }
%>
```



## 1.2 动/静态网页

### 随堂练习2：

```
要求：
    分别创建  static.html [静态网页]  dy.jsp [动态网页] 获得系统时间
    1. 静态网页，获得系统时间 通过 JS 获得：
    参考代码如下：
    let  d = new Date();
    let n = d.toLocaleString();
    
    2. 动态网页 ：通过java代码获得系统时间
    
```

```jsp
<script>
       let now = new Date()
       document.write('现在的:'+now.toLocaleTimeString())
</script>
```

```jsp
现在是: <%=new Date()%> 或 <%=LocalDateTime.now()%>
```



## 1.3 JSP工作原理

![image-20210806085831519](images\jsp-01.png)

![image-20210806085831519](images\jsp-02.png)

```
	JSP是一种Servlet，但是与HttpServlet的工作方式不太一样。HttpServlet是先由源代码编译为class文件后部署到服务器下的，先编译后部署。
	而JSP则是先部署源代码后编译为class文件的，先部署后编译。
	JSP 会在客户端第一次请求JSP文件时被编译为HttpJspPage类（接口Servlet的一个子类） 。该类会被服务器临时存放在服务器工作目录里面（work）。
```



### 随堂练习3：

```
要求：
  修改jsp代码，出现异常，查看work目录下 *.java 文件？
  
servers:  Using CATALINA_BASE:  ...
```

## 1.4 JSP 生命周期

```
	JSP也是Servlet，运行时只会有一个实例。跟Servlet一样，JSP实例初始化，销毁时也会调用Servlet的 init() 与 destory() 方法。
	JSP还有自己的初始化方法（ _jspInit ）与销毁方法（ _jspDestroy）
```

### 随堂练习4：

```
要求：
   创建一个JSP
   在JSP页面中 重写 初始化方法，销毁方法，多次访问查看执行结果。
   提示：在JSP 定义方法使用语法为： <%!  ...  %>
```

```jsp
<h1>生命周期</h1>
<%!
    //声明全局变量
   /* String name ="zhang3";
    public void aa(){
        // 是否使用 9 内置的变量
    }*/
    public void init() {
       System.out.println("=========_jspInit============");
    }
    public void destory() {      System.out.println("=========_jspDestroy============");
    }
%>
```

# 2. JSP语法

- JSP是HTML代码与Java代码的混合体，其中HTML代码部分遵循HTML语法，Java部分遵循Java语法。

## 2.1 JSP元素与模板数据

- 模板数据：就是JSP中的HTML代码，内容固定，输出到浏览器内容不会变化。无法进行控制程序流程，也不会影响程序运行结果。模板数据写啥输出啥。
- 元素：就是JSP中的Java部分，包括脚本（Scriptlet，JSP中的 Java代码），以及JSP指令与JSP标签等。元素决定着程序的流程。



## 2.2 JSP脚本

java 小脚本  java片段

```
	JSP脚本必须使用 <% 与 %> 包裹起来，否则被视为模板数据。必须遵循Java语法，否则编译错误。
	可以出现在JSP文件的任何地方。
```

```jsp
<ul>
    <%
        List<String>  dataList = Arrays.asList("aaa","bbb","ccc") ;
        //dataList.forEach(str-> System.out.println(str));
        for (String data : dataList) {
    %>
    <li><%=data%></li>
    <%
        }
    %>
</ul>

<% //Java 脚本
      //获得用户请求参数
      String age = request.getParameter("age");
      //输出
      //out.print("<h1>你的年龄是:"+age+"</h1>");
  %>
```



## 2.3 JSP输出

```
	在源程序中是使用 out.println()方法中输出来的，类似Servlet中的输出。 
	JSP使用 <%=  %> ,输出各种数据类型：int，double，boolean ，String，Object 。
注意：
   输出 <%=  %> 中，不能使用 ；  输出对象 自动调用toString（）
```

```jsp
<h1>你的年龄是:<%=age%></h1>
```



## 2.4 JSP注释

``` 
HTML 注释：（客户端注释）  <!--   -->
JSP注释：（服务器端注释）  <%-- -->
```

```jsp
<!-- 客户端注释 【浏览器】 -->

<%-- 服务端注释--%>


<%--
<%
   int k = 9/0 ;
%>
--%>
```



## 2.5 JSP指令

### page指令

| 属性名称属性名称     | 取值范围       | 说明                                       |
| ------------ | ---------- | ---------------------------------------- |
| language     | java       | 该JSP采用的语言，默认：Java                        |
| extends      | 类的完整名称     | 实现Servlet接口的Java类                        |
| import       | 包名，类名      | 类似Java的： import语句                        |
| session      | true，false | 该Jsp是否可以使用session对象，默认：true              |
| autoFlush    | true，false | 类似Servlet： out.flush()  默认：true          |
| isThreadSafe | true，false | 是否线程安全。若true，多个同时运行，排队等待。默认：             |
| buffer       | none 或xxkb | 指定缓存大小。设置autoFlush为true有效。               |
| isErrorPage  | true，false | 是否为错误处理页面，若true 可以使用exception对象，默认：false |
| errorPage    | 某个JSP      | JSP异常，跳转到errorPage指定的JSP页面。              |
| contentType  | 文档类型       | text/html  text/plain  image/jpeg  image/gif  application/json |



### include指令

网页复用

```
//静态包含
<%@ include file=""%>
1. 先包含  2.后执行
```



### taglib指令

```
JSTL 自定义 标签 JSP高级部分讲解
```



## 2.6 JSP行为

### include

```
//动态包含
1. 先执行  2. 再包含
<jsp:include page="./common/footer.jsp?name=addd"/>
```



### JavaBean

```
//使用 JavaBean
Java Bean行为是一组与Java Bean相关的行为，包括 useBean，setProperty，getProperty
```

useBean行为属性

| 属性名   | 取值范围                             | 说明                                       |
| ----- | -------------------------------- | ---------------------------------------- |
| id    | 变量名                              | 指向Java Bean对象（实例）                        |
| class | 类的完整名称                           | JSP自动：Class.forName(...).newIntance()  创建pojo实例 |
| scope | page,request,session,application | page只在该JSP内有效。request当前request有效。session当前会话有效。application当前web 应用程序有效。 默认： page |

### forward

```
	Servlet中通过 request.getRequestDispatcher("...").forward(request,response) ;
	<jsp:forward /> 对forward方法的封装
	<jsp:param name="" value=""/> 跳转 参数名 参数值
```





# 3. JSP 9大内置对象

```
	JSP中无需定义对象，直接可以使用9个内置对象，JSP比Servlet使用更简单，更方便。
	out,request,response,config,session,application,page,pageContext,exception
```

## 3.1 out

```
	JspWriter类的实例，服务器向客户端输出的字符内容可以通过out 输出。
```

| 方法名                       | 说明                                         |
| ---------------------------- | -------------------------------------------- |
| void append（CharSequece s） | 当缓存满或执行out.flush() 输出到客户端浏览器 |
| void println（String str）   | 向客户端输出内容   <%=str%>                  |
| void flush（）               | 将缓存内容flush到客户端浏览器                |



## 3.2 request

| 方法名                                      | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| String getParameter(String name)         | 获得请求的单参数                                 |
| String[ ]  getParameterValues(String name) | 获得请求的多参数                                 |
| Map<String,String[]> getParameterMap()   | 获得请求参数名/参数值                              |
| void setAttribute(String name,Object value) | 在request中保存一对象，本页面或forward通过getAttribute（String name）获得该对象 |
| Object getAttribute（String name）         | 从request中获得name对应的对象                     |
| String getContextPath（）                  | 获得应用程序路径                                 |
| String getRequestURI（）                   | 获得请求URI                                  |
| String getHeader（String name）            | 获得request头信息                             |
| HttpSession getSession（）                 | 获得HttpSession对象                          |
| Cookie[ ] getCookie( )                   | 获得所有的Cookie                              |



## 3.3 response

| 方法名                                      | 说明          |
| ---------------------------------------- | ----------- |
| void sendRedirect（String url）            | 重定向到另外一个URL |
| void setContextType（String contentType）  | 设置文档类型及编码。  |
| PrintWriter getOut（）                     | 响应字符输出流     |
| OutputStream  getOutStream（）             | 响应字节输出流     |
| void setHeader（String name，String  value） | 设置响应头信息     |
| void addCookie（Cookie cookie）            | 把cookie写入浏览 |



## 3.4 config

| 方法名                                  | 说明                                 |
| ------------------------------------ | ---------------------------------- |
| String getInitParameter（String name） | 获得 web.xml中的初始化参数                  |
| ServletContext getServletContext（）   | 获得ServletContext对象 即：applicaiton对象 |

## 3.5 session

| 方法名                                      | 说明                 |
| ---------------------------------------- | ------------------ |
| Stirng getId（）                           | 获得session的id       |
| void setAttribute(String name,Object value) | 设置 session         |
| Object getAttribute(String name)         | 通过name获得session中对象 |
| void setMaxInactiveInterval（long second） | 设置session最大间隔      |
| long getCreationTime( )                  | 返回session创建的时间     |
| long getLastAccessedTime（）               | 该session最后一次访问的时间  |

## 3.6  application

| 方法名                                      | 描述                   |
| ---------------------------------------- | -------------------- |
| void setAttribute(String name,Object obj) | 设置application对象的属性   |
| Object getAttribute(String name)         | 获得application属性对象    |
| void removeAttribute(String name)        | 移除application作用域中的属性 |
| String getInitParameter（String name）     | 获得全局初始化参数            |
| String getRealPath（String relativePath）  | 返回web应用程序内绝对路径       |

## 3.7 page

```
	内置对象page为 当前 JSP 转译成 Servlet的实例。  Object page  = this ;
```



## 3.8 pageContext

| 方法名                                      | 说明                                       |
| ---------------------------------------- | ---------------------------------------- |
| void setAttribute(String name,Object obj) | page作用域设置属性值                             |
| Object getAttribute(String name)         | page作用域获得属性值                             |
| Object getAttribute(String name,int scope) | 指定作用域获得值 PAGE_SCOPE,REQUEST_SCOPE,SESSION_SCOPE,APPLICATION_SCOPE |
| Object findAttribute(String name)        | 查找属性名                                    |
| ServletRequest getRequest（）              | 返回 request对象                             |
| ServletResponse  getResponse（）           | 返回 response对象                            |
| HttpSession getSession（）                 | 返回 session对象                             |
| Object getPage（）                         | 返回 page对象                                |
| JspWrite getOut（）                        | 返回 out 对象                                |

## 3.9 exception

```
必须设置： <%@ page  isErrorPage="true" %> 才能使用exception内置对象
```



### 随堂练习5：

```
要求：
    使用 JSP 9 大内置对象，实现猜数游戏：
```

![image-20210806085831519](images\guess.png)

```jsp
<h2>猜数游戏</h2>
<form action="./guess.jsp" method="post">
    选择难度: <select name="type">
                <option value="10">0~10</option>
                <option value="50">0~50</option>
                <option value="100">0~100</option>
            </select> <br>
            <input type="text" name="gnum" placeholder="请输入你要猜的数字"> <br>
            <button>猜数</button>
</form>
<hr>
<%
    //不是表单提交  直接进入页面
    if ("GET".equals(request.getMethod())) {
        return;
    }
    //尝试获得随机数  session
    Integer randNum = (Integer) session.getAttribute("randNum");
    //当前会话 没有随机数  进行设置随机数
    if (randNum == null) {
        //获得用户设置的难度
        String type = request.getParameter("type");
        //生成随机数
        Random random = new Random();
        randNum = random.nextInt(Integer.parseInt(type));
        System.out.println("此处偷看:" + randNum);
        //保存在 会话中 session
        session.setAttribute("randNum", randNum);
    }
    //获得用户输入的数字
    String gnumStr = request.getParameter("gnum");
    //进行比较
    int gnum = Integer.parseInt(gnumStr);
    if (randNum.equals(gnum)) {
%>
<h2>恭喜！！！猜对了！！！</h2>
<%
        //从session中 移除key
        session.removeAttribute("randNum");
        return;
    }
    if (gnum > randNum) {
%>
<h2>猜大了！！！</h2>
<%
        return;
    }
%>
<h2>猜小了！！！</h2>
</body>
</html>
```

