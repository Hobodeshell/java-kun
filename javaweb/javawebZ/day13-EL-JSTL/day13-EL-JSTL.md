# 1. EL表达式

## 1.1 语法/运算符

```jsp
${内置对象.key}     比如:  ${param.name}    =  <% request.getParameter("name") %>
                        ${requestScope.stu}  =  <% request.getAttribute("stu") %>

${内置对象['key']}         ${param['name']}

page 指令： isELIgnored="false"  如果 EL表达式不生效
```

初体验：

```jsp
<%--   http://localhost:8080/day11-01-EL-JSTL/EL/1.jsp?age=18 --%>
<%
    String age = request.getParameter("age");
    int intAge = Integer.parseInt(age) ;
    intAge += 1 ;
%>
传统方式:<%=intAge%>
<hr>
EL:  ${param.age+1}
```



EL运算符

![image-20211027093150803](images\image-20211027093150803.png)

```jsp
<h1>EL运算符</h1>
${2+"4"}   ${"2"+"4"}  ${"2"}abc <br>
${5/2} ${5 div 2}<br>
${5%2}  ${5 mod 2} <br>
${1==1}  ${1 eq 1} <br>
${1!=1}  ${1 ne 1} <br>
${1<1}  ${1 lt 1} <br>
${1<=1}  ${1 le 1} <br>
${1>1}  ${1 gt 1} <br>
${1>=1}  ${1 ge 1} <br>
${true && true}  ${true and true} <br>
${true || true}  ${true or true} <br>
${! true}  ${not true} <br>
<%
    // empty  null  ""  size =0  length =0
    pageContext.setAttribute("a1",null);
    pageContext.setAttribute("a2","");
    pageContext.setAttribute("a3",new String[]{});
    pageContext.setAttribute("a4",new ArrayList<>());
%>
${empty pageScope.a1} <br>
${empty pageScope.a2} <br>
${empty pageScope.a3} <br>
${empty pageScope.a4} <br>
```

## 1.2 取值

![image-20211027100307760](images\image-20211027100307760.png)

###    pageContext:

```jsp
${pageContext.request.contextPath} 
```

### 请求参数： param ，paramValues

```jsp
${param.name}
${paramValues.likes}
```

### 作用域： pageScope  requestScope   sessionScope   applicationScope

```jsp
<%
    pageContext.setAttribute("lt","xxxx");
    pageContext.setAttribute("user.name","zhangsan");
    pageContext.setAttribute("a","page....");
    request.setAttribute("a","request....");
    session.setAttribute("a","session....");
    application.setAttribute("a","application....");
%>
自动查找[page->reqeust->session->application]: ${a} <br>
page:  ${pageScope.a}  ${pageScope['a']}  ${pageScope["a"]}  ${pageScope['lt']}  ${pageScope['user.name']} <br>
request:  ${requestScope.a}  ${requestScope['a']}  ${requestScope["a"]} <br>
session:  ${sessionScope.a}  ${sessionScope['a']}  ${sessionScope["a"]} <br>
application:  ${applicationScope.a}  ${applicationScope['a']}  ${applicationScope["a"]} <br>
```

#### 数组/list

```jsp
<%
    // 数组类型
    pageContext.setAttribute("ay",new String[]{"aaa","bbb","ccc"});
    //  List
    pageContext.setAttribute("list", Arrays.asList("AAA","BBB","CCC"));
%>
数组：${ay[0]}  ${ay['0']}  ${ay["0"]}     <br>
List: ${list[0]}  ${list['0']}  ${list["0"]}     <br>
```



#### po实体/级联类【JavaBean】

```java
<%
    Name name = new Name() ;
    name.setFirstname("张");
    name.setLastname("三疯");

    Person person = new Person();
    person.setAge(108);
    person.setName(name);

    pageContext.setAttribute("person",person);
%>
EL取JAVABean: 年龄:${person.age} 姓: ${person.name.firstname}  名: ${person.name.lastname}
```



### map

```jsp
<%
    Map<String,Object>  map = new HashMap<>() ;
    map.put("sid","s1001") ;
    map.put("sname","xxxx") ;
    pageContext.setAttribute("map",map);
%>
EL取map: 学号:${map.sid} 姓名: ${map.sname}
```

```jsp
<%
    Name name = new Name() ;
    name.setFirstname("张");
    name.setLastname("三疯");

    Person person = new Person();
    person.setAge(108);
    person.setName(name);

    Map<String,Person>  map = new HashMap<>() ;
    map.put("p1",person) ;
    pageContext.setAttribute("map",map);
%>
EL取map:  年龄:${map.p1.age} 姓: ${map.p1.name.firstname}  名: ${map.p1.name.lastname}
```



### Cookie

```jsp
<h1>获得cookie的值</h1>
${cookie} <br>
${cookie.JSESSIONID} <br>
${cookie.JSESSIONID.name}   ${cookie.JSESSIONID.value}  <br>
```

### Header

```jsp
<h1>获得 header 的值</h1>
${header.Accept} <br>
<hr>
${headerValues.Accept[0]} <br>
```

### initParam

```xml
//web.xml
 <context-param>
        <param-name>database</param-name>
        <param-value>mysql</param-value>
    </context-param>
```

```jsp
<h1>获得 initParam 的值</h1>
${initParam} <br>
${initParam.database} <br>
```



# 2. JSTL 标准标签库

​    https://mvnrepository.com/artifact/javax.servlet/jstl/1.2

   A.  jstl-1.2.jar

   B. <%@ taglib   ... %> 导入标签处理指令

## 2.1 core核心标签库

```jsp
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

### 通用 set  out  remove

```
setAttribute("key",obj) ;
out.println( getAttribute("key") )
removeAttribute("key")
```

```jsp
<c:set var="a" value="aaa..." scope="page"/>   
${pageScope.a}
```

```jsp
${a} <br>
<c:out value="${a}" default="保密" escapeXml="true"/> <br>
```

```jsp
<c:remove var="a" scope="session"/>
```

### 条件标签

```jsp
if

choose
    when 
    othewise
```

```jsp
<%-- if.jsp?age=18 --%>
<c:if test="${param.age gt 18}">
   <h1>朋友你好！</h1>
</c:if>
<c:if test="${param.age le 18}">
    <h1>小朋友你好！</h1>
</c:if>
```

```jsp
<%-- if.jsp?age=18 --%>
<c:choose>
    <c:when test="${param.age gt 18}">
        朋友好
    </c:when>
    <c:when test="${param.age gt 0 and param.age le 18}">
        小朋友好
    </c:when>
    <c:otherwise>
        你还没出生
    </c:otherwise>
</c:choose>
```

### 迭代标签

```
forEach                          for(String s : list)
    items 循环集合            list
    var   当前循环的变量        s
    begin 开始索引
    end   结束索引
    step  步长
    varStatus  当前变量的状态
    
字符串 分割处理
forTokens [ split(",") ]
```



![image-20211027151439877](images\image-20211027151439877.png)

![image-20211027151520777](images\image-20211027151520777.png)

```jsp
 <%
       pageContext.setAttribute("ay",new String[]{"AAA","BBB","CCC","DDD","EEE"});
       Name name1 = new Name() ;
       name1.setFirstname("张");
       name1.setLastname("三疯");
       Person person1 = new Person();
       person1.setAge(108);
       person1.setName(name1);

       Name name2 = new Name() ;
       name2.setFirstname("灭绝");
       name2.setLastname("师太");
       Person person2 = new Person();
       person2.setAge(98);
       person2.setName(name2);
       List<Person> personList = new ArrayList<>() ;
       personList.add(person1) ;
       personList.add(person2) ;
       pageContext.setAttribute("personList",personList);
   %>
   <hr>
   <c:forEach items="${personList}" var="person">
       ${person.age}  ${person.name.firstname}  ${person.name.lastname}<br>
   </c:forEach>
   <hr>
  <c:forEach items="aa,bbb,ccc" var="s">
      ${s} <br>
  </c:forEach>
   <hr>
   <c:forEach items="${ay}" var="s" begin="1" end="3" step="2" varStatus="vs">
       ${s}  ${vs.index}  ${vs.first}  ${vs.last}  ${vs.count} <br>
   </c:forEach>
```



```jsp
<c:set var="dataList" value="aaa,bbb;ccc:ddd,eee,xxx,yyy:zzz"/>
<c:forTokens items="${dataList}" delims=",;:" var="s">
    ${s} <br>
</c:forTokens>
```





## 2.2 fmt 格式化标签库

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

### fmtDate

```jsp
<%
    pageContext.setAttribute("now",new Date());
%>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss.SSS"/>
```

### i18n 国际化

src\com\wanho\java165\res\msg_en_US.properties

```properties
hello=hello world
```

src\com\wanho\java165\res\msg_zh_CN.properties

jdk\bin\native2ascii.exe

```properties
hello=\u60a8\u597d
```

```jsp
<fmt:setBundle basename="com.wanho.java165.res.msg"/>
<h1>
    <fmt:message key="hello"/>
</h1>
```



## 2.3 fn 函数

string类方法封装

```jsp
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
${fn:length("abcd")} <br>
${fn:toUpperCase("abDCCSxxx")}
```

## 2.4 sql标签库



## 2.5 xml标签库



# 3. 自定义标签/函数【画瓢】

## 3.1 自定义标签

###  标签类实现 Tag 接口  

```java
public class SelectTag extends SimpleTagSupport {
    private String value ;

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public void doTag() throws JspException, IOException {
        StringBuffer sb = new StringBuffer() ;
        sb.append("<select name='address'>") ;
        sb.append("<option value=''>--请选择地址--</option>") ;
        if ("NJ".equals(value)){
            sb.append("<option value='NJ' selected>南京市</option>") ;
        }else {
            sb.append("<option value='NJ'>南京市</option>");
        }
        if ("BJ".equals(value)){
            sb.append("<option value='BJ' selected>北京市</option>") ;
        }else {
            sb.append("<option value='BJ'>北京市</option>") ;
        }
        if ("SH".equals(value)){
            sb.append("<option value='SH' selected>上海市</option>") ;
        }else {
            sb.append("<option value='SH'>上海市</option>") ;
        }
        sb.append("</select>") ;
        JspWriter out = super.getJspContext().getOut();
        out.print(sb.toString());
    }
}
```

### 标签描述文件【cp jar tld】

/WEB-INF/*.tld

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<taglib xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
        version="2.1">

    <description>JSTL 1.1 core library</description>
    <display-name>JSTL core</display-name>
    <tlib-version>1.1</tlib-version>
    <short-name>mt</short-name>
    <uri>http://www.wanho.com/java165/mytag</uri>

    <tag>
        <description>
           显示地址
        </description>
        <name>showAddress</name>
        <tag-class>com.wanho.java165.tag.SelectTag</tag-class>
        <body-content>empty</body-content>
        <attribute>
            <description>
              地址选中的默认值
            </description>
            <name>value</name>
            <required>false</required>
            <rtexprvalue>true</rtexprvalue>
        </attribute>
    </tag>
</taglib>
```

### 页面使用

```jsp
<mt:showAddress/> <br>
<mt:showAddress value="BJ"/> <br>
<mt:showAddress value="${param.address}"/> <br>
```





## 3.2 自定义函数

   【工具类 utils  static 方法】

### 工具类

```java
public class SexUtil {
    public static String sexCode2Text(Integer code){
        if (code.equals(0)){
            return  "男" ;
        }
        if (code.equals(1)){
            return  "女" ;
        }
        if (code.equals(-1)){
            return  "人妖" ;
        }
        return "保密" ;
    }
}
```

### tld [函数描述文件]

fn.tld  注意 xsd约束 ： <function> 必须 在 <tag>标签之后

```xml
<!--自定义函数  实现 性别状态码 转成 文本-->
    <function>
        <description>
            实现 性别状态码 转成 文本
        </description>
        <name>sexCode2Text</name>
        <function-class>com.wanho.java165.util.SexUtil</function-class>
        <function-signature>java.lang.String sexCode2Text(java.lang.Integer)</function-signature>
        <example>
            ${mt:sexCode2Text(0)}
        </example>
    </function>
```

### 页面使用

```jsp
${mt:sexCode2Text(0)} <br>
${mt:sexCode2Text(1)} <br>
${mt:sexCode2Text(-1)} <br>
${mt:sexCode2Text(3)} <br>
${mt:sexCode2Text(param.sex)} <br>
```

