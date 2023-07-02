# 1.过滤器



![filter](images\filter.png)

```
类似动态代理？
   1. 目标类的方法  进行增强
      比如：订单新增方法中： 在运行中 动态添加  事务开启 提交 回滚
   执行目标对象的方法：
      method.invoke(obj,args)       JDK
      method.invokeSuper(obj,args)  CGLIB
基于请求URL？
      *.do    =  *Servlet
   A.  post中文乱码处理  request.setChara..Encoding("UTF-8") ;
   B.  /admin/* 鉴权 =  需要登录的后台管理页面   验证当前session是否存放用户登录的key
   C.  *.jpg    =  动态添加水印  / 图片放盗
   D.  脏话/自动纠正   TMD=***   chia = china
   E.  IP黑白名单
   执行目标请求：
      //过滤器放行
      filter.doFilter(request,response) ;
如何实现Filter？
      必须实现xx规范，在Java中 规范基本上就是指的xx接口【实现 Filter接口】
```

## 1.1 中文乱码过滤器

### 准备

add-comment.jsp

```jsp
<h1>添加留言</h1>
<form action="${pageContext.request.contextPath}/addComment.do" method="post">
    内容: <textarea name="msg"  cols="30" rows="10"></textarea>
    <button>提交</button>
</form>
```

AddCommentServlet.java

```java
@WebServlet("/addComment.do")
public class AddCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = request.getParameter("msg");
        response.getWriter().println("<h1>"+msg+"</h1>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
```

### 实现

#### filter

```java
public class EncodingFilter implements Filter {
    private  String encoding ;
    //初始化
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
    }
    //过滤
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset="+encoding);
        //放行
        chain.doFilter(req, resp);
    }
    //销毁
    public void destroy() {
        encoding = null ;
    }
}
```

#### web.xml

```xml
<filter>
    <filter-name>encodingFitler</filter-name>
    <filter-class>com.wanho.java167.filter.EncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encodingFitler</filter-name>
    <url-pattern>*.do</url-pattern>
    <!-- 默认 url 使用 .do结尾 -->
    <dispatcher>REQUEST</dispatcher>
    <!-- 请求jsp  jsp中使用：   request.getRequestDispatcher("addComment.do").forward(request,response);-->
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>ERROR</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
</filter-mapping>
```

#### @WebFilter

```java
@WebFilter(
        urlPatterns = "*.do",
        initParams = {@WebInitParam(name="encoding",value = "UTF-8")},
        dispatcherTypes = {DispatcherType.REQUEST,             	                   DispatcherType.FORWARD,  DispatcherType.ERROR,
                           DispatcherType.INCLUDE}
)
```



## 1.2 鉴权

### 活动图

![image-20210806085831519](images\login.png)

### 准备

pojo

```java
public class Admin {
    private String account ;
    private String username ;
    private String pswd ;
    private String role ;
}
```

jsp

```jsp
<h1>管理员登录</h1>
<form action="${pageContext.request.contextPath}/login.do" method="post">
    账号: <input type="text" name="account"> <br>
    密码: <input type="text" name="pswd"> <br>
    <button>提交</button>
</form>
${msg}
```

admin/main.jsp

```jsp
 <h1>欢迎${currAdmin.username} 角色: ${currAdmin.role}</h1>
 <hr>
 <a href="${pageContext.request.contextPath}/goods.do?method=list">查询商品列表</a>
 <a href="${pageContext.request.contextPath}/goods.do?method=view">查询商品详情</a>
 <a href="${pageContext.request.contextPath}/goods.do?method=add">添加商品信息</a>
 <a href="${pageContext.request.contextPath}/goods.do?method=upt">修改商品信息</a>
 <a href="${pageContext.request.contextPath}/goods.do?method=del">删除商品信息</a>
```

Sevlet

LoginServlet

```java
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private Map<String, Admin> adminMap = new ConcurrentHashMap<>() ;

     public LoginServlet() {
        adminMap.put("zs",new Admin("zs","张三","111","member")) ;
        adminMap.put("ls",new Admin("ls","李四","222","member")) ;
        adminMap.put("ww",new Admin("ww","王五","333","admin")) ;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String pswd = request.getParameter("pswd");
        Admin admin = adminMap.get(account);
        if (admin==null || !admin.getPswd().equals(pswd)){
            request.setAttribute("msg","用户名或密码错误！！！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        request.getSession().setAttribute("currAdmin",admin);
        response.sendRedirect(request.getContextPath()+"/admin/main.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
```

GoodsSevlet

```java
@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String method = request.getParameter("method");
            Method m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            m.invoke(this,request,response) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("=========GoodsServlet====查询商品列表  权限 ====list===========");
    }

    protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("=========GoodsServlet====查询商品详情 权限 ====view===========");
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("=========GoodsServlet== 添加商品信息 权限 ======add===========");
    }
    protected void upt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("=========GoodsServlet===修改商品信息 权限 =====upt===========");
    }
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("=========GoodsServlet==删除商品信息 权限 ======del===========");
    }
}
```

### 实现

#### 认证

用户自身信息

```
1. 身份   账号/手机号/邮箱/指纹/人脸 ...      [唯一标识]   account 属性
2. 凭证   密码/U盾                ....     [秘钥]       pswd属性
account + pswd  = session 中 currAdmin[不为null]
```

AuthFilter

```java
@WebFilter(urlPatterns = {"*.do","/admin/*"},initParams = {@WebInitParam(name="excludePath",value = "/login.do")})
public class AuthFilter implements Filter {
    private String excludePath ;
    public void init(FilterConfig config) throws ServletException {
        //排除过滤的路径
        excludePath = config.getInitParameter("excludePath");
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获得用户请求路径  request.getRequestURI() = /day17-01-filter/login.do
        //             .replace(request.getContextPath(),"") = /login.do
        String requestURI = request.getRequestURI().replace(request.getContextPath(),"") ;
        // 如果 排除的路径  直接放行
        if (excludePath.equals(requestURI)){
            chain.doFilter(req, resp);
            return;
        }
        // 必须登录成功的用户才能访问资源  *.do    /admin/*
        Admin admin = (Admin) request.getSession().getAttribute("currAdmin");
        //如果没有登录  跳转到登录界面
        if (admin==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        //已经登录成功  放行
        chain.doFilter(request,response);
    }


    public void destroy() {
        excludePath = null ;
    }
}
```

#### 授权

```
RBAC  Resource Base Access Controller 基于资源的访问控制
      Role  Base Access Controller  基于角色的访问控制
      
DAO   Data  Access Object 数据访问对象
```

auth.properties

```properties
# url = role   \转义字符
# 会员角色 只能 查询商品权限
/goods.do?method\=list=member
/goods.do?method\=view=member
# 管理员角色 可以对商品 所有操作
/goods.do?method\=*=admin
```

403.jsp

```jsp
<h1>对不起,您没有访问该资源的权限！ 请联系管理员...</h1>
```

AuthFilter

```java
@WebFilter(urlPatterns = {"*.do","/admin/*"},
        initParams = {
        @WebInitParam(name="excludePath",value = "/login.do"),
        @WebInitParam(name="authFileLocation",value = "auth.properties"),
         //只需要登录【认证】  不需要授权【角色】的路径
        @WebInitParam(name="onlyLoginPath",value = "/admin/*,/addComment.do")
        })
public class AuthFilter implements Filter {
    private String excludePath ;
    private String[] onlyLoginPathAy ;
    private Properties prop ;
    public void init(FilterConfig config) throws ServletException {
        try {
            //排除过滤的路径
            excludePath = config.getInitParameter("excludePath");
            onlyLoginPathAy = config.getInitParameter("onlyLoginPath").split(",");
            for (int i = 0; i < onlyLoginPathAy.length; i++) {
                onlyLoginPathAy[i] = onlyLoginPathAy[i].replace("*",".*") ;
            }
            //读取 授权配置文件
            String authFileLocation = config.getInitParameter("authFileLocation");
            InputStream is = AuthFilter.class.getClassLoader().getResourceAsStream(authFileLocation) ;
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //获得用户请求路径  request.getRequestURI() = /day17-01-filter/login.do
        //             .replace(request.getContextPath(),"") = /login.do
        String requestURI = request.getRequestURI().replace(request.getContextPath(),"") ;
        // 如果 排除的路径  直接放行
        if (excludePath.equals(requestURI)){
            chain.doFilter(req, resp);
            return;
        }
        // 必须登录成功的用户才能访问资源  *.do    /admin/*
        Admin admin = (Admin) request.getSession().getAttribute("currAdmin");
        //如果没有登录  跳转到登录界面
        if (admin==null){
            response.sendRedirect(request.getContextPath()+"/login.jsp");
            return;
        }
        //如果请求路径 只需要登录 不需要角色授权 放行
        for (String onlyLoginPath : onlyLoginPathAy) {
            if (requestURI.matches(onlyLoginPath)){
                chain.doFilter(request, response);
                return;
            }
        }
        //获得当前用户的角色
        String role = admin.getRole();
        // 获得请求参数 method  对应的值
        String method = request.getParameter("method");
        //获得请求路径   /goods.do?method=list
        String uri = requestURI+"?method="+method ;
        //默认不放行
        boolean isPass = false ;
        for (Object keyObj : prop.keySet()) {
            //配置文件的请求路径 【带正则的】
            String key = (String) keyObj;
            //配置文件 该请求路径对应的  角色  member  admin
            String value = prop.getProperty(key);
            //替换正则表达式
            String key2 = key.replace("?","\\?").replace(".","\\.").replace("*",".*") ;
            //判断 请求 uri 是否匹配正则表达式
            if (uri.matches(key2) && role.equals(value)){
                isPass = true ;
                break;
            }
        }
        //拥有访问权限  放行
        if (isPass) {
            chain.doFilter(request, response);
            return;
        }
        //登录成功 没有访问权限
        response.sendRedirect(request.getContextPath()+"/403.jsp");
    }


    public void destroy() {
        prop.clear();
        prop = null ;
        excludePath = null ;
    }
}
```

![image-20220902142005256](images\auth-shiro.png)

## 1.3 内容替换/装饰者模式

dw.properties

```properties
# 错误 = 正确
SB=**
TMD=***
Chna=China
www.baidu.cn=www.baidu.com
```

装饰者设计模式

```java
public class DWRequest extends HttpServletRequestWrapper {
    private Properties dwProp ;
    public DWRequest(HttpServletRequest request, Properties dwProp){
        super(request);
        this.dwProp = dwProp ;
    }

    @Override
    public String getParameter(String name) {
       String inputValue = super.getParameter(name);
        for (Object keyObj : dwProp.keySet()) {
            String key = (String) keyObj;
            String value = dwProp.getProperty(key);
            inputValue = inputValue.replaceAll(key,value) ;
        }
        return  inputValue ;
    }
}
```

内容替换过滤器

```java
@WebFilter(urlPatterns = "*.do",initParams = {@WebInitParam(name = "dwFileLocation",value="dw.properties")})
public class DWFilter implements Filter {
    private Properties dwProp ;
    
    public void init(FilterConfig config) throws ServletException {
        try {
            String dwFileLocation = config.getInitParameter("dwFileLocation");
            InputStream is = DWFilter.class.getClassLoader().getResourceAsStream(dwFileLocation) ;
            dwProp = new Properties();
            dwProp.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //装饰者 类
        DWRequest dwRequest = new DWRequest(request,dwProp);
        // 放行
        chain.doFilter(dwRequest,response);
    }

   
    public void destroy() {
    }

}
```

注意：

```
过滤器链：优先级：
基于注解： @WebFilter(name="a")
  A. 有name  根据name的值 自然顺序  
  B. 没有name  根据 过滤器的简单类名 自然顺序
  
基于xml :  web.xml  <filter> <filter-mapping>
  A. 最先声明优先
```



# 2.监听器

```
javaScript  事件 【点击/鼠标】  监听函数
database    事件 【DML/DDL】   触发器
listenter   三种属性作用域【request，session,application】  初始化 销毁 设置 替换 删除
```

## 2.1 在线用户

### 准备：

main.jsp

```jsp
<h2><a href="${pageContext.request.contextPath}/logout.do">安全退出</a></h2>

在线用户: <br>
<c:forEach items="${adminMap}" var="map">
 用户会话ID: ${map.key.id} ,姓名: ${map.value.username} <br>
</c:forEach>
```



LogoutServlet

```java
@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("currAdmin");
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }
}
```



OnLineAdminListenter

```java
@WebListener
public class OnLineAdminListener implements HttpSessionAttributeListener,ServletContextListener {
    private Map<HttpSession, Admin> adminMap = new ConcurrentHashMap<>() ;

    //session.setAttribute("k","v") ;
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        bind(httpSessionBindingEvent);
    }

    private void bind(HttpSessionBindingEvent httpSessionBindingEvent) {
        //获得当前session对象
        HttpSession session = httpSessionBindingEvent.getSession();
        // name 设置的 key
        String name = httpSessionBindingEvent.getName();
        //触发 setAttribute()   用户登录 购物车 表单跨页提交
        if ("currAdmin".equals(name)){
            //当前登录用户 value 设置值
            Admin admin = (Admin) httpSessionBindingEvent.getValue();
            adminMap.put(session,admin) ;
        }
    }

    //session.removeAttribute("k") ;
    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession() ;
        if ("currAdmin".equals(httpSessionBindingEvent.getName())){
            adminMap.remove(session) ;
        }
    }

    //session.setAttribute("k","v") ; 同名 key
    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        bind(httpSessionBindingEvent);
    }

    //application  new()
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //application 对象创建时候  【tomcat启动时】
        ServletContext application = servletContextEvent.getServletContext();
        application.setAttribute("adminMap",adminMap);
    }

    //applicaton = null
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //application对象销毁  【tomcat 停止】
        adminMap.clear();
        adminMap = null ;
    }
}
```



注解:

```java
@WebListener
```

web.xml

```xml
<listener>
	<listener-class>com.wanho.java167.listenter.OnLineAdminListener</listener-class>
</listener>
```
