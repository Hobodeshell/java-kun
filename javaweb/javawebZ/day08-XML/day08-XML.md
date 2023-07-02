# 0. IDEA

## 0.1 安装

![image-uninstall](images\install01.png)

![image-uninstall](images\install02.png)

![image-uninstall](images\install03.png)

![image-uninstall](images\install04.png)

![image-uninstall](images\install05.png)

![image-uninstall](images\install06.png)

## 0.2 配置

### 破解

```
1. 打开 readme.txt 安装步骤进行破解
```

![image-uninstall](images\pack01.png)

![image-uninstall](images\pack2.png)

![image-uninstall](images\pack03.png)

![image-uninstall](images\pack05.png)

![image-uninstall](images\pack06.png)

![image-uninstall](images\pack07.png)

### JDK

![image-uninstall](images\config01.png)

![image-uninstall](images\config02.png)

![image-uninstall](images\jdk3.png)

### 字体

![image-uninstall](images\font01.png)

![image-uninstall](images\font02.png)

### 编码

![image-uninstall](images\encoding.png)

## 0.3 运行

### 创建项目

![image-uninstall](images\run01.png)

![image-uninstall](images\run02.png)

![image-uninstall](images\run03.png)

![image-uninstall](images\run04.png)

![image-uninstall](images\run05.png)

### 创建Java类

![image-uninstall](images\new01.png)

### 编写/运行源代码

![image-uninstall](images\hello.png)

![image-uninstall](images\run7.png)

## 0.4 debug

![image-uninstall](images\debug.png)

## 0.5 卸载

![image-uninstall](images\uninstall01.png)

![image-uninstall](images\uninstall02.png)

![image-uninstall](images\uninstall03.png)

![image-uninstall](images\uninstall04.png)

![image-uninstall](images\uninstall05.png)

删除安装目录：

![image-uninstall](images\uninstall06.png)

# 1.XML基本语法

## 1.1 作用

![image-20210806085831519](images\xml.jpg)

```
1. XML 被设计用来传输和存储数据。
当我们看到 XML 标准突飞猛进的开发进度，以及大批的软件开发商采用这个标准的日新月异的速度时，真的是不禁感叹这真是令人叹为观止。
目前，XML 在 Web 中起到的作用不会亚于一直作为 Web 基石的 HTML。
XML 无所不在。XML 是各种应用程序之间进行数据传输的最常用的工具，并且在信息存储和描述领域变得越来越流行。

一切尽在xml中  【3阶段】

2. HTML 被设计用来显示数据。

假如他们都是理性的，就让未来的应用程序使用 XML/JSON [前后端分离] 来交换数据吧。
```

![image-20220818114724713](images\image-20220818114724713.png)



## 1.2 简介

```
XML 指可扩展标记语言（EXtensible Markup Language）
XML 是一种标记语言，很类似 HTML
XML 的设计宗旨是传输数据，而非显示数据
XML 标签没有被预定义。您需要自行定义标签。
XML 被设计为具有自我描述性。
XML 是 W3C 的推荐标准
```



## 1.3 初体验

```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<bookstore>
  <book category="COOKING">
    <title lang="en">Everyday Italian</title> 
    <author>Giada De Laurentiis</author> 
    <year>2005</year> 
    <price>30.00</price> 
  </book>
  <book category="WEB">
    <title lang="en">Learning XML</title> 
    <author>Erik T. Ray</author> 
    <year>2003</year> 
    <price>39.95</price> 
  </book>
</bookstore>
```

![image-20210806085831519](images\dom.png)



## 1.4 Well Formed

```
形式良好的 XML 文档
“形式良好”或“结构良好”的 XML 文档拥有正确的语法。

“形式良好”（Well Formed）的 XML 文档会遵守前几章介绍过的 XML 语法规则：

XML 文档必须有根元素
XML 文档必须有关闭标签 
   <info/>  ==   <info></info>
XML 标签对大小写敏感
XML 元素必须被正确的嵌套
XML 属性必须加引号
```



## 随堂练习1：

![image-20210806085831519](images\ex01-xml.png)

```
要求: 如图设计 XML 进行数据存储
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<stulist info="学生成绩表">
    <!-- 使用 属性    -->
    <stu name="张三" birthday="2020-05-15">
        <course name="编译方法" score="79"/>
        <course name="C" score="85"/>
        <course name="数据结构" score="93"/>
    </stu>
    <stu name="李四" birthday="2020-06-15">
        <course name="计算复杂性" score="79"/>
        <course name="C" score="85"/>
        <course name="数据结构" score="93"/>
    </stu>
</stulist>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<stulist>
    <info>学生成绩表</info>
    <stu>
        <name>张三</name>
        <course>
            <name>SQL</name>
            <score>77</score>
        </course>
        <course>
            <name>SQL</name>
            <score>77</score>
        </course>
        <course>
            <name>SQL</name>
            <score>77</score>
        </course>
        <bithday>
            <year>2022</year>
            <month>05</month>
            <day>15</day>
        </bithday>
    </stu>
    <stu>
        <name>李四</name>
        <course>
            <name>SQL</name>
            <score>77</score>
        </course>
        <course>
            <name>SQL</name>
            <score>77</score>
        </course>
        <course>
            <name>SQL</name>
            <score>77</score>
        </course>
        <bithday>
            <year>2022</year>
            <month>06</month>
            <day>15</day>
        </bithday>
    </stu>
</stulist>
```



# 2.XML约束

## 2.1 DTD

### 语法

*.dtd

```
<!ELEMENT 元素名 (子元素名)>
1.	*  该元素出现任意次         0~N次
2.	,  出现顺序        		比如: (id,name)  先id 在name
3.	   =1 出现一次
4.	+  >=1 一次               1~N次
5.	?  0 或 1 次 
#PCDATA  解析字符串数据
```

```dtd
<!ELEMENT students (stu)+>
<!ELEMENT stu (sid,sname+,age?)>
<!ELEMENT sid (#PCDATA)>
<!ELEMENT sname (#PCDATA)>
<!ELEMENT age (#PCDATA)>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE students SYSTEM "stu.dtd">
<students>
    <stu>
        <sid>S1</sid>
        <sname>aaa</sname>
        <sname>AAA</sname>
        <age>18</age>
    </stu>

    <stu>
        <sid>S2</sid>
        <sname>bbb</sname>
    </stu>
</students>
```



### 随堂练习2：

```
自定义DTD  
要求如下：
   【员工列表（至少有一个员工）：
        工号（有且只有一个）  
        姓名（可有多个 至少一个）  
        工资   
        奖金（可有可无）
    】
```

### 验证

```dtd
<!ELEMENT emps (emp+)>
<!ELEMENT emp (empno,ename+,sal,comm?)>
<!ELEMENT empno (#PCDATA)>
<!ELEMENT ename (#PCDATA)>
<!ELEMENT sal (#PCDATA)>
<!ELEMENT comm (#PCDATA)>
```

### 随堂练习3：

```
创建xml文件关联 随堂练习 2 的 DTD文件
进行验证语法：
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE emps SYSTEM "emp.dtd">
<emps>
    <emp>
        <empno>7788</empno>
        <ename>SCOTT</ename>
        <sal>3800</sal>
    </emp>
    <emp>
        <empno>7788</empno>
        <ename>SCOTT</ename>
        <ename>SCOTT</ename>
        <sal>3800</sal>
    </emp>
    <emp>
        <empno>7788</empno>
        <ename>SCOTT</ename>
        <ename>SCOTT</ename>
        <sal>3800</sal>
        <comm>2000</comm>
    </emp>
</emps>
```



## 2.2 Schema

### 语法

*.xsd

```
XML Schema 是基于 XML 的 DTD 替代者。
XML Schema 可描述 XML 文档的结构。
XML Schema 语言也可作为 XSD（XML Schema Definition）来引用。

<xs:element name="xxx" type="yyy"/>
xs:string
xs:decimal
xs:integer
xs:boolean
xs:date
xs:time
 minOccurs="1" maxOccurs="unbounded"    = dtd 的 +
 minOccurs="0" maxOccurs="1"            = dtd 的 ?
 minOccurs="0" maxOccurs="unbounded"    = dtd 的 *
 minOccurs="1" maxOccurs="1"  = 没写      = 1 次
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://www.w3schools.com"
           xmlns="http://www.w3schools.com"
           elementFormDefault="qualified">
    <!-- 定义根元素 students -->
    <xs:element name="students">
        <!--包含复合类型-->
        <xs:complexType>
            <!--出现子元素的顺序-->
            <xs:sequence>
                <!-- 元素 stu-->
                <xs:element name="stu" minOccurs="1" maxOccurs="unbounded">
                    <xs:complexType>
                       <xs:sequence>
                           <xs:element name="sid">
                               <xs:simpleType>
                                  <xs:restriction base="xs:string">
                                      <xs:pattern value="[A-Z]\d{3}"/>
                                  </xs:restriction>
                               </xs:simpleType>
                           </xs:element>
                           <xs:element name="sname" type="xs:string"/>
                           <xs:element name="age" minOccurs="0">
                               <xs:simpleType>
                                   <xs:restriction base="xs:int">
                                       <xs:minInclusive value="18"/>
                                       <xs:maxInclusive value="60"/>
                                   </xs:restriction>
                               </xs:simpleType>
                           </xs:element>
                       </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```

### 随堂练习4:

```
要求： 创建  schema文件
1. 学号 4 位字符  第一位 A-Z   后三位 0-9
2. 姓名  2 ~ 4 字符
3. 年龄  18 ~ 60
4. 性别  男 或 女
```



### 验证

```xml
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://www.w3schools.com"
           xmlns="http://www.w3schools.com"
           elementFormDefault="qualified">
    <!--    自定义数据类型 开始-->
    <xs:simpleType name="sidType">
        <xs:restriction base="xs:string">
            <xs:pattern value="[A-Z]\d{3}"/>
        </xs:restriction>
    </xs:simpleType>

    <xs:simpleType name="snameType">
        <xs:restriction base="xs:string">
            <xs:minLength value="2"/>
            <xs:maxLength value="4"/>
        </xs:restriction>
    </xs:simpleType>

    <xs:simpleType name="ageType">
        <xs:restriction base="xs:int">
            <xs:minInclusive value="18"/>
            <xs:maxInclusive value="60"/>
        </xs:restriction>
    </xs:simpleType>
    
    <xs:simpleType name="sexType">
        <xs:restriction base="xs:string">
            <xs:pattern value="男|女"/>
        </xs:restriction>
    </xs:simpleType>

    <xs:complexType name="studentType">
        <xs:sequence>
            <xs:element name="sid" type="sidType"/>
            <xs:element name="sname" type="snameType"/>
            <xs:element name="age" type="ageType"/>
            <xs:element name="sex" type="sexType"/>
        </xs:sequence>
    </xs:complexType>
    <!--    自定义数据类型 结束-->

    <!-- 定义根元素开始-->
    <xs:element name="students">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="student" type="studentType" minOccurs="1"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
    <!-- 定义根元素结束-->

</xs:schema>
```

### 随堂练习5：

```
要求：创建 xml 进行对上面的 xsd 文件关联 进行验证
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--<stu xmlns="http://www.w3schools.com">-->
<stu xmlns="http://www.w3schools.com"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.w3schools.com stu.xsd">
    <stu>
        <sid>S102</sid>
        <sname>admin</sname>
    </stu>
    <stu>
        <sid>S102</sid>
        <sname>admin</sname>
        <age>28</age>
    </stu>
</stu>
```

# 3. XML解析

## 3.1 解析类型

```
1. Java API  dom  正规军
	一次性 把 xml 文件，读入内存，在内存中 dom 树
2. Java API  sax  游击队
    基于事件
3. 调包侠     dom4j
4. 调包侠     jdom
5. 调包侠     xstream
```

## 3.2 jar包准备

https://mvnrepository.com/

```
dom4j-1.6.1.jar
xml-apis-1.0.b2.jar
jaxen-1.1-beta-6.jar
```

## 3.3 数据准备

```xml
<?xml version="1.0" encoding="UTF-8"?>
<bookstore>
    <book category="COOKING">
        <title lang='zh'>Everyday Italian</title>
        <author>Giada De Laurentiis</author>
        <year>2005</year>
        <price>30.00</price>
    </book>
    <book category="WEB">
        <title lang="en">学习XML</title>
        <author>Erik T. Ray</author>
        <year>2003</year>
        <price>39.95</price>
    </book>
    <book category="Java">
        <title lang="zh">Java编程思想</title>
        <author>Erik T. Ray 11</author>
        <year>2103</year>
        <price>49.95</price>
    </book>
</bookstore>
```



## 3.4 基本解析

```java
public class ParseXml01 {
    public static void main(String[] args) throws Exception {
        //1.创建 dom4j 解析器
        SAXReader saxReader = new SAXReader();
        //2. 把xml 读 doc 对象中
        Document document = saxReader.read("src/bookstore.xml");
        //3. 通过 xpath 获得 元素节点
        List<Element> bookEltList = document.selectNodes("//bookstore/book");
        //4. 循环 所有 book 元素节点
        bookEltList.forEach(bookElt->{
            String category = bookElt.attributeValue("category");
            String title = bookElt.elementText("title");
            String lang = bookElt.element("title").attributeValue("lang");
            System.out.println("类别:"+category+",标题:"+title+",语言:"+lang);
        });
        System.out.println("==============程序结束============");
    }
}
```



## 3.5 xpath



### 随堂练习6：

解析如下 xml ：获得所有的类别（category）为 WEB的书籍信息：

```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<bookstore>
  <book category="WEB">
    <title lang="en">Learning HTML</title> 
    <author>Erik T. Ray HTML</author> 
    <year>2013</year> 
    <price>38.95</price> 
  </book>
  <book category="COOKING">
    <title lang="en">Everyday Italian</title> 
    <author>Giada De Laurentiis</author> 
    <year>2005</year> 
    <price>30.00</price> 
  </book>
  <book category="WEB">
    <title lang="en">Learning XML</title> 
    <author>Erik T. Ray</author> 
    <year>2003</year> 
    <price>39.95</price> 
  </book>
</bookstore>
```



# 4. 工厂改写

## 4.1 beans.xml

objects.properties

```properties
jdbcTemplate=com....
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans>
	<bean id="userController" class="...."/>
    <bean id="userService" class="...."/>
    <bean id="userDAO" class="...."/>
    <bean id="jdbcTemplate" class="...."/>
</beans>
```



## 4.2 解析 xml



## 4.3 管理bean



### 随堂练习7：

datasources.properties

```
使用 xml文件格式：配置 db-config.xml文件  替换 之前的 jdbc.properties文件
并且 测试 是否能够成功 获得 Connection对象
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <environments default="dev">
        <environment id="dev">
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://192.168.36.251:3306/dev"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
        <environment id="test">
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://192.168.36.188:3306/test"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
        <environment id="product">
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://192.168.36.128:3306/product"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="com/wanho/java171/dao/StuDAO.xml"/>
    </mappers>
</configuration>
```

附件题【使用xml作为DAO接口的实现类】：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<mapper namespace="com.wanho.java173.dao.StuDAO">
    <select id="selectAll" resultType="com.wanho.java171.entity.Stu">
        select userid,username,age,cls "cls.clsno" from t_user
    </select>

    <select id="selectById" resultType="com.wanho.java171.entity.Stu">
        select userid,username,age,cls "cls.clsno" from t_user where id = #{id}
    </select>

    <insert id="insert">
        insert into t_user(userid,username,age,cls) values (#{userid},#{username},#{age},#{cls.clsno})
    </insert>

    <update id="update">
        update t_user  set username=#{username},age=#{age},cls=#{cls.clsno} where userid=#{userid}
    </update>

    <delete id="delete">
        delete from t_user where userid = #{id}
    </delete>
</mapper>
```

参考代码:

```java
String insertSQL = "insert into t_user(userid,username,age,cls) values (#{userid},#{username},#{age},#{cls.clsno})" ;
String rs = insertSQL.replaceAll("#\\{[a-zA-z0-9_.]+}", "?");
System.out.println(rs);
//insert into t_user(userid,username,age,cls) values (?,?,?,?)
String[] rs2 = insertSQL.split("#\\{");
List<String> list = Stream.of(rs2).skip(1).map(s -> s.substring(0, s.length() - 2).trim()).collect(Collectors.toList());
// userid   username
list.forEach(System.out::println);
```

# 5. JSON

## 5.1 格式

```json
// JSON对象
{sid:'s1',sname:'aaa',age:18}

// JSON对象数组
[
    {sid:'s1',sname:'aaa',age:18},
    {sid:'s1',sname:'aaa',age:18},
    {sid:'s1',sname:'aaa',age:18}
]

// JSON对象中对象
{
    name:{
        firstName:'zhang',
        lastName:'san'
	},
 	age:18
}

//JSON对象中数组
{
    nameList:[{
        firstName:'zhang',
        lastName:'san'
	},{
        firstName:'zhang',
        lastName:'3'
	}],
 	age:18
}
```



## 5.2 fastjson

https://github.com/alibaba/fastjson/wiki/Quick-Start-CN

```java
String text = JSON.toJSONString(obj); //序列化
VO vo = JSON.parseObject("{...}", VO.class); //反序列化
```

```java
public class User {
    private String sid ;
    private String sname ;
    private  int age ;
    private String userAddress ;
}
```

```java
//创建 对象
User user = new User();
user.setSid("S1");
user.setSname("zhang3");
user.setAge(18);
user.setUserAddress("南京");

//把 java对象 转成 json格式的字符串
String text = JSON.toJSONString(user); //序列化
System.out.println(text);

//        String json = "{\"age\":18,\"sid\":\"S1\",\"sname\":\"zhang3\",\"user_address\":\"南京\"}" ;
String json = "{\"age\":18,\"sid\":\"S1\",\"sname\":\"zhang3\",\"userAddress\":\"南京\"}" ;
User user2 = JSON.parseObject(json, User.class);
System.out.println(user2);
```



```java
//创建 对象
User user1 = new User();
user1.setSid("S1");
user1.setSname("zhang3");
user1.setAge(18);
User user2 = new User();
user2.setSid("S2");
user2.setSname("zhang4");
user2.setAge(19);

//创建对象集合
List<User> userList = new ArrayList<>() ;
userList.add(user1) ;
userList.add(user2) ;

String json = JSON.toJSONString(userList);
System.out.println(json);

String json2 = "[{\"age\":18,\"sid\":\"S1\",\"sname\":\"zhang3\"},{\"age\":19,\"sid\":\"S2\",\"sname\":\"zhang4\"}]" ;
List<User> userList2 = JSONArray.parseArray(json2,User.class) ;
for (User user : userList2) {
    System.out.println(user);
}
```

