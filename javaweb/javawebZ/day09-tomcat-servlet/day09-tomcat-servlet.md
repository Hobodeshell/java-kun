# 1.网络编程

## 1.1 图解

![image-20220601202154425](images\image-20220601202154425.png)

## 1.2 Server

```java
public class ServerApp {
    public static void main(String[] args) throws IOException {
        //忠告 数组
        final String[] ADVICE_AY = {"每天吃钙，到老我也健康膝盖!","听人劝，吃饱饭！","代码写的好，要饭要到老！"};
        //随机数
        final Random RAND = new Random();
        //创建服务器端 套接字
        ServerSocket serverSocket = new ServerSocket(8080);
        //服务器端 一直接受 客户端的请求 死循环
        while (true){
            //获得 客户端套接字
            Socket socket = serverSocket.accept();
            new Thread(()->{
                try {
                    // 获得输出流
                    OutputStream os = socket.getOutputStream();
                    //输出 随机字符串  字节流数组
                    os.write(ADVICE_AY[RAND.nextInt(ADVICE_AY.length)].getBytes("UTF-8"));
                    //清空 缓存
                    os.flush();
                    InputStream is = socket.getInputStream();
                    byte[] ay = new byte[1024] ;
                    is.read(ay) ;
                    System.out.println("来着客户端的消息:"+new String(ay));
                    //释放流
                    os.close();
                    is.close();
                    //关闭套接字
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
```



## 1.3 Client

```java
public class ClientApp {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        //获得输入流
        InputStream is = socket.getInputStream();
        byte[] ay = new byte[1024];
        is.read(ay) ;
        //打印 服务器端消息
        System.out.println("来着服务器端消息:"+new String(ay));

        //获得输出流
        OutputStream os = socket.getOutputStream();
        os.write(("hello Server !!!"+Math.random()).getBytes());
        os.flush();

        is.close();
        os.close();
        socket.close();
    }
}
```



# 2. CS/BS架构

## 2.1 图解

![image-20210806085831519](images\bs-cs.png)

## 2.2 C/S与B/S区别

```
1．硬件环境不同：

C/S 一般建立在专用的网络上，小范围里的网络环境，局域网之间再通过专门服务器提供连接和数据交换服务。B/S 建立在广域网之上的，不必是专门的网络硬件环境，例如电话上网，租用设备. 信息自己管理. 有比C/S更强的适应范围，一般只要有操作系统和浏览器就行。

2．对安全要求不同：

C/S 一般面向相对固定的用户群，对信息安全的控制能力很强。 一般高度机密的信息系统采用C/S 结构适宜。可以通过B/S发布部分可公开信息。B/S 建立在广域网之上， 对安全的控制能力相对弱， 可能面向不可知的用户。

3．对程序架构不同：

C/S 程序可以更加注重流程， 可以对权限多层次校验， 对系统运行速度可以较少考虑。B/S 对安全以及访问速度的多重的考虑，建立在需要更加优化的基础之上. 比C/S有更高的要求 B/S结构的程序架构是发展的趋势，从MS的.Net系列的BizTalk 2000 Exchange 2000等，全面支持网络的构件搭建的系统。SUN 和IBM推JavaBean 构件技术等，使 B/S更加成熟.。

4．软件重用不同：

C/S 程序可以不可避免的整体性考虑， 构件的重用性不如在B/S要求下的构件的重用性好。B/S 的多重结构，要求构件相对独立的功能， 能够相对较好的重用，就如买来的餐桌可以再利用，而不是做在墙上的石头桌子。

5．系统维护不同：

C/S 程序由于整体性，必须整体考察，处理出现的问题以及系统升级、升级难、 可能是再做一个全新的系统。B/S 构件组成，方便构件个别的更换，实现系统的无缝升级. 系统维护开销减到最小.用户从网上自己下载安装就可以实现升级。
```



# 3. tomcat

## 3.1 下载

官网：https://tomcat.apache.org/

![image-20220310093342369](images\tomcat.png)

![image-20220310093604639](images\image-20220310093604639.png)

## 3.2 安装

解压

注意：目录【不能包含中文/空格目录】

![image-20220310094030394](images\image-20220310094030394.png)



## 3.3 运行

![image-20220310094223870](images\image-20220310094223870.png)

![image-20220310094315069](images\image-20220310094315069.png)

![image-20220310094923024](images\image-20220310094923024.png)



## 3.4 Bug

一闪而过:  解决：【配置JAVA_HOME】

![image-20220310094614012](images\image-20220310094614012.png)



## 3.5 QA

### Q1: 浏览器无法访问？

![image-20220310100909737](images\image-20220310100909737.png)

A1: tomcat 是否已启动 【tomcat 启动】



### Q2: 协议错误

URL :    

1. 协议  【http , https ,tcp ,udp ,sms】   

2. 主机  localhost  IP地址  127.0.0.1  域名

3. 端口  ：8080   http协议 默认端口 ：80

   http://localhost:80  =  http://localhost

   4.资源： http://localhost:80/1.html

      1.html 资源名

    http://localhost:80/html/1.html

   5. 查询字符串

      http://localhost:80/html/1.html?id=1&name=abc

      ?id=1&name=abc

 https://localhost:8080/

![image-20220310101315161](images\image-20220310101315161.png)



### Q3：启动多次

端口被占用



## 3.6 目录

![image-20220310102527110](images\image-20220310102527110.png)

## 3.7 改端口

![image-20220310102853068](images\image-20220310102853068.png)

![image-20220310102945506](images\image-20220310102945506.png)

![image-20220310103017172](images\image-20220310103017172.png)



# 4. 手动部署

## 4.0 创建web应用

![image-20220310103928098](images\image-20220310103928098.png)

![image-20220310104500193](images\image-20220310104500193.png)

## 4.1 静态html

![image-20220310104735744](images\image-20220310104735744.png)

http://localhost/web01/hello.html

## 4.2 动态jsp

![image-20220310111454412](images\image-20220310111454412.png)

http://localhost/web01/hello.jsp

![image-20220310111650338](images\image-20220310111650338.png)



## 4.3 Servlet

```
Servlet  = Server  applet  运行在服务器 tomcat上的小程序
1.必须 规范：必须实现 Servlet 接口
 //直接实现  Servlet 接口
 A  implements Servlet{
 
 
 }
 
 HttpServlet implments Servlet{
 
 
 }
 
 //间接实现 Servlet 接口
 A  extends HttpServlet{
   ...
 }
```

### 源码

参考：

![image-20220310113227634](images\image-20220310113227634.png)

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Hello World!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Hello World Servlet ... !</h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
```

### 编译

![image-20220310113730274](images\image-20220310113730274.png)

javac -cp D:\java168_2\server\apache-tomcat-8.5.73\lib\servlet-api.jar .\HelloWorld.java

### 部署

![image-20220310134552680](images\image-20220310134552680.png)

```
1. 桌面： aa文件下/HelloWorld.class 放在 classess文件夹下
2.  tomcat/lib/servlet-api.jar   lib 空着
```

### 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

    <servlet>
        <servlet-name>HelloWorldExample</servlet-name>
        <servlet-class>HelloWorld</servlet-class>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>HelloWorldExample</servlet-name>
        <url-pattern>/hello.do</url-pattern>
    </servlet-mapping>
 
</web-app>
```



![image-20220310135945200](images\image-20220310135945200.png)

### 访问

http://localhost/web01/hello.do

![image-20230309145910314](images\image-20230309145910314.png)



# 5.IDE配置tomcat

注意： 第一步： 把 启动tomcat 关闭  ，删除 解压目录 ，重新解压 

## 5.1 默认配置

![image-20220601194515126](images\image-20220601194515126.png)

![image-20220601194737182](images\image-20220601194737182.png)

![image-20220601194932740](images\image-20220601194932740.png)



![image-20220601195110025](images\image-20220601195110025.png)



## 5.2 配置热部署![image-20220601200140991](images\image-20220601200140991.png)

![image-20230309151922856](images\image-20230309151922856.png)

![image-20230309151951442](images\image-20230309151951442.png)



# 6. IDE web开发

## 6.1 创建项目

![image-20220601195318245](images\image-20220601195318245.png)



## 6.2 创建Servlet

```java
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//控制台输出  IDE console 
		System.out.println("=====HelloServlet===doGet 该方法被执行啦=======");
		//通过 流  浏览器 输出  hello ...
		resp.getWriter().println("<h1> hello ...</h1>");
	}
}
```



## 6.3 配置Servlet

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">
    <!--  注册 servlet 类 -->
    <servlet>
    	<servlet-name>hello</servlet-name>
    	<servlet-class>com.wanho.java171.servlet.HelloServlet</servlet-class>
    </servlet>
    <!--  映射 servlet 类访问路径 -->
    <servlet-mapping>
    	<servlet-name>hello</servlet-name>
    	<url-pattern>/hello.do</url-pattern>
    </servlet-mapping>
  </web-app>
```



## 6.4 访问Servlet

http://localhost/day10-01-servlet/hello.do

基于请求驱动   地址栏直接怼  超链接  location.href   form action=“”

基于事件驱动

![image-20220601195318245](images\servlet.png)

## 6.5 QA

### Q1: 访问 webapps目录

http://localhost/day10-01-servlet/WEB-INF/web.xml

/WEB-INF/**   地址栏 不能直接访问  【受保护的目录】

### Q2: servlet-class

```xml
/day10-01-servlet/src/com/wanho/java168/servlet/HelloServlet.java

<servlet-class>com.wanho.java171.servlet.HelloServlet</servlet-class>
```

# 7.Servlet API

## 7.1 HttpServletRequest

```java
//获得请求参数的值
方法1： String name = request.getParameter("name");
"name" 参数名：
      注意：如果参数名 不存在   String name 的值？ null
           如果参数名 存在 没有值   String name 的值？ ""
    
//1.针对 post 请求有效  中文乱码
   req.setCharacterEncoding("UTF-8");    
```

## QA:

### Q1: name问题？

```html
<input age="age">
```

### Q2: url请求路径问题?

```
1. 相对路径  [   ./  ../ ]
     action="hello.do"   action="./hello.do"
2. 绝对路径 [完整的URL 协议  主机  端口  资源]
     action="http://localhost/day10-01-servlet/request.do"
3. 服务器路径 [/]
     /  =  http://localhost/
     http://localhost/request.do?name=aaa&age=111
     action="/hello.do"  action="http://localhost/hello.do" [项目名 没了]
```

### Q3: web.xml

```xml
<url-pattern>/request.do</url-pattern>  [包裹内容 第一个字符 必须是 / ]
```

![image-20220310170118718](images\image-20220310170118718.png)

### Q4: 405

请求的method 与 Servlet 的代码 不对应：

```
请求 method : GET   servlet 没有 doGet 重写
```



![image-20220310170633498](images\image-20220310170633498.png)

```java
//兼容： get/post
public class TestRequestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		 //获得用户请求参数  使用 HttpServletRequest req 对象
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		System.out.println("姓名:"+name+",年龄:"+age);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
```



## 7.2 HttpServletResponse

### 响应字符流

```java
// 1.响应 [html内容] 字符输出流
resp.setContentType("text/html;charset=UTF-8");
PrintWriter out = resp.getWriter();
//通过 流  浏览器 输出  hello ...    类似： js ： document.write("html代码")
out.println("<h1> hello . 该方法被执行啦..</h1>");
```

### 响应URL

```java
//2. 响应URL  浏览器 根据响应URL 再次发送新的请求
resp.sendRedirect("./request.do") ;
```



