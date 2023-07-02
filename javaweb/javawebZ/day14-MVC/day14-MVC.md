# 1.跳转

## 1.1 客户端跳转

```
名：
    1. Redirect
    2. 客户端跳转
    3. 客户端重定向
 客户端
      1. 地址栏  变成目标 地址
      2. 两次请求与响应
      3. 不是同一个 request  response
      4. 速度慢
      5. 不能受保护的目录 /WEB-INF/*
      6. 可以跳转到当前应用以外 比如：用户注册成功后，自动跳转到邮箱登录首页
      7.  /   =  tomcat的根   http://localhost:8080   必须加项目名    
      
语法：
 response.sendRedirect(request.getContextPath()+"/WEB-INF/redirect-02.jsp");
```

![image-20220825143450255](images\image-20220825143450255.png)



## 1.2 服务器端跳转

```
名：
    1. forward
    2. 服务器跳转
    3. 服务器转发
服务器跳转
      1. 地址栏  不会变成目标 地址
      2. 一次请求与响应
      3. 是同一个 request  response
      4. 速度快
      5. 能受保护的目录 /WEB-INF/*
      6. 不可以跳转到当前应用以外
      7.  /   =  项目的根   http://localhost:8080/day13-02-MVC   必须加不能加项目名
      
语法：
    request.getRequestDispatcher("/WEB-INF/forward-02.jsp").forward(request,response);
```

![image-20220825144624598](images\image-20220825144624598.png)


# 2. MVC

## 2.1 图解

![image-20210806085831519](images\mvc.png)



```
MVC 模式代表 Model-View-Controller（模型-视图-控制器） 模式。这种模式用于应用程序的分层开发。

Model（模型） - 模型代表一个存取数据的对象或 JAVA POJO。它也可以带有逻辑，在数据变化时更新控制器。
View（视图） - 视图代表模型包含的数据的可视化。
Controller（控制器） - 控制器作用于模型和视图上。
它控制数据流向模型对象，并在数据变化时更新视图。它使视图与模型分离开。
```



## 2.2 案例

![image-20210806085831519](images\mvc-seq.png)

index.jsp

```jsp
<a href="<%=request.getContextPath()%>/stu-list.do">查看学生列表</a>
```

StuListController.java

```java
@WebServlet("/stu-list.do")
public class StuListController extends HttpServlet {
    //创建业务层对象
    private StuService stuService = new StuServiceImpl() ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用业务方法  查询所有的学生
        List<Stu> stuList = stuService.selectAll();
        //跳转到页面显示 所有学生信息  方法一  不推荐  pageContext  request  session  application 【最小化原则】
       /* HttpSession session = request.getSession();
        session.setAttribute("stuList",stuList);
        response.sendRedirect(request.getContextPath()+"/stu-list.jsp");*/

       //使用 request作用域  按需所取  存放值 必须 使用 服务器内部跳转来实现
       request.setAttribute("stuList",stuList);
       request.getRequestDispatcher("/stu-list.jsp").forward(request,response);
    }
}
```



Stu.java

```java
public class Stu {
    private String sid ;
    private String sname ;
}
```

StuService.java

```java
public interface StuService {
    List<Stu> selectAll();
}
```

StuServiceImpl.java

```java
public class StuServiceImpl implements StuService {
    private StuDAO stuDAO = new StuDAOImpl() ;
    @Override
    public List<Stu> selectAll() {
        return stuDAO.selectAll();
    }
}
```

StuDAO.java

```java
public interface StuDAO {
    List<Stu> selectAll() ;
}
```

StuDAOImpl.java

```java
public class StuDAOImpl implements StuDAO {
    @Override
    public List<Stu> selectAll() {
        return Arrays.asList(new Stu("S1","aa1"),new Stu("S2","aa2"),new Stu("S3","aa3"));
    }
}
```

stu-list.jsp

```jsp
<h2>学生列表</h2>
<%
    List<Stu> stuList = (List<Stu>) request.getAttribute("stuList");
%>
<ul>
    <%
        for (Stu stu : stuList) {
    %>
         <li><%=stu.getSid()%>---------<%=stu.getSname()%></li>
    <%
        }
    %>
</ul>
```


# 3. Servlet合并

## 3.0 分析原生痛点

![image-20210806085831519](images\servlet-01.png)

## 3.1 多一个请求参数

![image-20230315112915139](images\image-20230315112915139.png)



index.jsp

```jsp
<h1>02. 一个servlet 配置一个url  多一个参数</h1>
<a href="<%=request.getContextPath()%>/goods.do?method=list">查询所有商品</a> <br>
<a href="<%=request.getContextPath()%>/goods.do?method=view">查询一个商品</a> <br>
<a href="<%=request.getContextPath()%>/goods.do?method=add">新增商品</a> <br>
<a href="<%=request.getContextPath()%>/goods.do?method=upt">修改商品</a> <br>
<a href="<%=request.getContextPath()%>/goods.do?method=del">删除商品</a> <br>
```

```java
@WebServlet(name = "Goods02Controller",urlPatterns = "/goods.do")
public class Goods02Controller extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if ("list".equals(method)){
            this.list(request,response) ;
        }else if ("view".equals(method)){
            this.view(request,response) ;
        }else if ("add".equals(method)){
            this.add(request,response) ;
        }else if ("upt".equals(method)){
            this.upt(request,response) ;
        }else if ("del".equals(method)){
            this.del(request,response) ;
        }else{
            response.sendError(404,"你小子请求路径错误...");
        }
    }
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("使用 多一个参数  实现查询所有商品");
    }
    protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("使用 多一个参数  实现查询一个商品");
    }
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("使用 多一个参数  实现查询 新增 商品");
    }
    protected void upt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("使用 多一个参数  实现查询 修改  商品");
    }
    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("使用 多一个参数  实现查询 删除  商品");
    }
}
```

## 3.2 反射优化分支

```java
 try {
    //通过反射 在当前类中 获得 Method对象
     Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
    //通过反射调用
    declaredMethod.invoke(this,request,response) ;
} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
     e.printStackTrace();
     response.sendError(404,"你小子请求路径错误...");
}
```

## 3.3 基于继承

![image-20230315140718613](images\image-20230315140718613.png)

![image-20230315141010744](images\image-20230315141010744.png)

### 父类：

BaseController.java

```java
public abstract class BaseController extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获得 标识 请求参数  method  list  add del
        String method = request.getParameter("method");
        //2. 获得  Class对象    Class.forName("com.wanho.java178.controller.GoodsController")  GoodsController.class  对象.getClass()
        Class cls = this.getClass();
        try {
            //3. 获得 方法对象 Method  param0:方法名  param1：可变参数 参数列表 Class...  参数类型
            Method clsMethod = cls.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //4. 调用方法  param0: 对象/实例   param1:可变参数 Object ...  实际参数
            clsMethod.invoke(this,request,response) ;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            response.sendError(404,"在类:["+cls.getName()+"]中没有找到方法:["+method+"]");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            response.sendError(500,"对不起,服务器炸了！！！"+e.getMessage());
        }
    }
}
```

### 子类：

GoodsController.java

```java
@WebServlet(urlPatterns = "/goods.do")
public class GoodsController extends BaseController {
    public void importExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======importExcel======");
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // int k = 9/0 ;
        response.getWriter().print("=========goods =======list======");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======add======");
    }
    public void upt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======upt======");
    }

    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======del======");
    }

    public void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======view======");
    }

}
```







# 4. 松耦合Servlet接口

## 4.1 基于xml配置

### 类图

![image-20230315084459056](images\image-20230315084459056.png)

### 配置

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<actions>
    <action path="/goods/list.do" class="com.wanho.java178.controller.GoodsController" method="list">
        <result name="success">/list-goods.jsp</result>
    </action>
    <action path="/goods/add.do" class="com.wanho.java178.controller.GoodsController" method="add">
        <result name="fail">/add-goods.jsp</result>
        <result name="success" type="redirect">/goods/list.do</result>
    </action>
    <action path="/goods/upt.do" class="com.wanho.java178.controller.GoodsController" method="modify">
        <result name="success" type="redirect">/goods/list.do</result>
    </action>
    <action path="/goods/del.do" class="com.wanho.java178.controller.GoodsController" method="remove">
        <result name="success" type="redirect">/goods/list.do</result>
    </action>
    <action path="/goods/view.do" class="com.wanho.java178.controller.GoodsController" method="view">
        <result name="success">/modify-goods.jsp</result>
    </action>
</actions>
```

### 封装

#### Result

```java
public class Result {
    /**方法的返回值 */
    private String name ;
    /** 跳转页面类型： 客户端跳转  服务器内部跳转*/
    private String type ;
    /** 跳转目标url地址*/
    private String url ;
}
```

#### Action

```java
public class Action {
    /** 请求路径 */
    private String path ;
    /** 执行目标类*/
    private String className ;
    /** 执行目标方法*/
    private String methodName ;
    /**当前action包含的result   key: name属性值  value:result对象 */
    private Map<String,Result> resultMap = new HashMap<>() ;
}
```

### 总控制器

#### 配置

web.xml

```xml
<servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>com.wanho.java178.commons.controller.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>configLocation</param-name>
        <param-value>mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

#### DispatcherServlet

```java
public class DispatcherServlet extends HttpServlet {
    /** 解析xml所有的数据 存放在 map中  key: path  value：action节点实例*/
    private Map<String,Action> actionMap = new HashMap<>() ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            //1.获得 mvc配置文件的位置  mvc.xml
            String configLocation = config.getInitParameter("configLocation");
            //2. 获得输入流
            InputStream is = DispatcherServlet.class.getClassLoader().getResourceAsStream(configLocation);
            //3. 创建 dom4j解析器
            SAXReader reader = new SAXReader();
            //4. 解析目标xml文件
            Document document = reader.read(is);
            //5. 获得所有的 //actions/action
            List<Element> actionEltList = document.selectNodes("//actions/action");
            //6. 循环所有  action 标签
            for (Element actionElt : actionEltList) {
                String path = actionElt.attributeValue("path");
                String className = actionElt.attributeValue("class");
                String method = actionElt.attributeValue("method");
                Action action = new Action();
                action.setClassName(className);
                action.setMethodName(method);
                action.setPath(path);
                //8. 封装对象
                List<Element> resultEltList = actionElt.elements("result");
                //7. 循环当前action下 所有 result 标签
                for (Element resultElt : resultEltList) {
                    String name = resultElt.attributeValue("name");
                    String type = resultElt.attributeValue("type");
                    String url = resultElt.getTextTrim();
                    Result result = new Result();
                    result.setName(name);
                    result.setType(type);
                    result.setUrl(url);
                    action.getResultMap().put(name,result) ;
                }
                //9. 存储数据  【磁盘把数据 加载内存中】
                if (actionMap.containsKey(path)){
                    throw new RuntimeException("请求检查你的【"+configLocation+"】文件中，配置路径:【"+path+"】是否重复？？？") ;
                }
                actionMap.put(path,action) ;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("解析xml异常");
        }

        System.out.println("==============初始化==============");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //1.获得用户请求路径   /day13-03-MVC-xml/goods/list.do
            String requestURI = request.getRequestURI();
            //  /goods/list.do
            requestURI = requestURI.replace(request.getContextPath(),"");
            //2.xml中 查找请求路径
            //3.如果请求路径不存在  客户端错误！！！
            if (!actionMap.containsKey(requestURI)){
                response.sendError(404,"请求的路径【"+requestURI+"】不存在！！！");
                return;
            }
            //4.获得 Action对象
            Action action = actionMap.get(requestURI);
            //5.获得 Class类
            String className = action.getClassName();
            Class<?> cls = Class.forName(className);
            //6.获得Method方法
            Method method = cls.getMethod(action.getMethodName(), HttpServletRequest.class, HttpServletResponse.class);
            //7.通过反射创建实例
            Object instance = cls.newInstance();
            //8.调用方法 有返回值
            Object returnVal = method.invoke(instance, request, response);
            //controller的方法 返回类型为 void  子控制器 自行处理  不进行配置文件统一操作
            if (method.getReturnType()==void.class){
                return;
            }
            //9. 根据返回值 获得 Result
            String name = (String) returnVal;
            Result result = action.getResultMap().get(name);
            if (result==null){
                response.sendError(500,"方法的返回值,没有配置对应结果页面！！！");
                return;
            }
            String type = result.getType();
            String url = result.getUrl();
            //10. type属性值  进行如何跳转？ 客户端 服务器？
            if ("redirect".equals(type)){
                response.sendRedirect(request.getContextPath()+url);
                return;
            }
            request.getRequestDispatcher(url).forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
```

### 子控制器

```java
public class GoodsController {

    public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======list======");
        return "success" ;
    }

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======add======");
        return "success" ;
    }
    public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======upt======");
        return "success" ;
    }

    public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======del======");
        return "success" ;
    }

    public String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======view======");
        return "success" ;
    }
}
```

## 4.2 基于注解@配置

### 类图



![image-20230316110922629](images\image-20230316110922629.png)

### 自定义注解

Controller.java

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
```

RequestMapping.java

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String value() ;
}
```

### 前端控制器

```java
import com.wanho.java178.commons.annon.Controller;
import com.wanho.java178.commons.annon.RequestMapping;
import org.reflections.Reflections;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class DispatcherServlet extends HttpServlet {
    /** 解析xml所有的数据 存放在 map中  key: path  value：Method对象*/
    private Map<String,Method> methodMap = new HashMap<>() ;
    /**存放 key：path  value ：当前controller的实例 */
    private Map<String,Object> instanceMap = new HashMap<>() ;
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            //1.获得 mvc配置文件的位置  mvc.xml
            String scanBasePackage = config.getInitParameter("scanBasePackage");
            //2. 扫描 所有在 基本包下 所有 包的java类 拥有 @Controller注解的类
            //反射工具包，指明扫描路径
            Reflections reflections = new Reflections(scanBasePackage);
            //获取带Controller注解的类
            Set<Class<?>> classList = reflections.getTypesAnnotatedWith(Controller.class);
            //3.获得该类中所有的Method @RequestMapping
            for (Class<?> controllerCls : classList) {
                //创建 子控制器的实例
                Object instance = controllerCls.newInstance();
                //获得当前类中 所有的 public 修饰的方法
                Method[] methodAy = controllerCls.getMethods();
                for (Method method : methodAy) {
                    //4. 存放在map中
                    RequestMapping requestMappingAnnon = method.getAnnotation(RequestMapping.class);
                    //拥有该 注解
                    if(requestMappingAnnon!=null){
                        //获得请求路径
                        String path = requestMappingAnnon.value();
                        methodMap.put(path,method) ;
                        instanceMap.put(path,instance) ;
                    }
                }
            }
        } catch (InstantiationException |IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("==============初始化==============");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            //1.获得用户请求路径   /day13-03-MVC-xml/goods/list.do
            String requestURI = request.getRequestURI();
            //  /goods/list.do
            requestURI = requestURI.replace(request.getContextPath(),"");
            //2.xml中 查找请求路径
            //3.如果请求路径不存在  客户端错误！！！
            if (!methodMap.containsKey(requestURI)){
                response.sendError(404,"请求的路径【"+requestURI+"】不存在！！！");
                return;
            }
            //4.获得 Action对象
            Method method = methodMap.get(requestURI);
            //5.获得controller的实例
            Object instance = instanceMap.get(requestURI);
            //6.调用方法 有返回值
            Object returnVal = method.invoke(instance, request, response);
            // 7 controller的方法 返回类型为 void  子控制器 自行处理  不进行配置文件统一操作
            if (method.getReturnType()==void.class){
                return;
            }
            //8 . 根据返回值 获得 Result
            String url = (String) returnVal;
            //9 . type属性值  进行如何跳转？ 客户端 服务器？
            if (url.startsWith("redirect:")){
                response.sendRedirect(request.getContextPath()+url.substring("redirect:".length()));
                return;
            }
            request.getRequestDispatcher(url).forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
```

### web.xml

```xml
<servlet>
    <servlet-name>mvc</servlet-name>
    <servlet-class>com.wanho.java178.commons.controller.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>scanBasePackage</param-name>
        <param-value>com.wanho.java178</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>mvc</servlet-name>
    <url-pattern>*.do</url-pattern>
</servlet-mapping>
```

### 子控制器

```java
@Controller
public class GoodsController {

    @RequestMapping("/goods/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======list======");
        return "/list-goods.jsp" ;
    }

    @RequestMapping("/goods/add.do")
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======add======");
        return "redirect:/goods/list.do" ;
    }

    @RequestMapping("/goods/upt.do")
    public String modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======upt======");
        return "redirect:/goods/list.do" ;
    }

    @RequestMapping("/goods/del.do")
    public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======del======");
        return "redirect:/goods/list.do" ;
    }

    @RequestMapping("/goods/view.do")
    public String view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("=========goods =======view======");
        return "/modify-goods.jsp" ;
    }
}
```

