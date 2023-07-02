# 1. 同步上传/下载

## 1.1 文件上传

### 要求

https://commons.apache.org/proper/commons-fileupload/

```html
1. form 表单必须 method="POST"
2. form 表单必须 enctype="multipart/form-data"
3. form 表单上传控件 type="file"
```

###  客户端页面

upload.jsp

```jsp
<form method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadSynchronize.do">
    头像: <input type="file" name="pic"><br/>
    姓名: <input type="text" name="name"><br/>
    <br/>
    <input type="submit" value="添加用户">
</form>
```

### 封装的表单

pojo

```java
public class User {
    private String name ;
    private String pic ;
}
```

### Servlet

```java
@WebServlet("/uploadSynchronize.do")
public class UploadSynchronizeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 检查当前请求是否符合上传要求
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart){
            request.setAttribute("msg","请检查客户端代码,没有达到上传要求!");
            request.getRequestDispatcher("/upload01.jsp").forward(request,response);
            return;
        }
        //获得tomcat目录
        String tomcatPath = request.getServletContext().getRealPath("/");
        //创建 User对象
        User user = new User();
        try {
            // 创建硬盘工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 创建 解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求
            List<FileItem> items = upload.parseRequest(request);
            // 循环请求 Item
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                // 表单的普通控件 【比如：text  radio  checkbox】
                if (item.isFormField()) {
                    // <input name="xxx"  />  xxx
                    String name = item.getFieldName();
                    // <input name="xxx" value="yyy"/>  yyy
                    String value = item.getString("UTF-8");
                    //用户姓名属性赋值
                    if ("name".equals(name)){
                      user.setName(value);
                    }
                } else { //上传控件  [type="file"]
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf(".")) ;
                    File uploadedFile = new File(tomcatPath,fileName);
                    item.write(uploadedFile);
                    //用户姓名属性赋值
                    if ("pic".equals(fieldName)){
                        user.setPic(fileName);
                    }
                }
            }
            //把新增的用户设置到 request作用域
            request.setAttribute("user",user);
            //跳转到上传结果页面
            request.getRequestDispatcher("/upload01-rs.jsp").forward(request,response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
```

### 结果页面

```jsp
<h1>上传结果</h1>
姓名: ${user.name} <br>
头像: <img src="${pageContext.request.contextPath}/${user.pic}"/>
```

## 1.2 文件下载

### 页面

```jsp
<h1>下载</h1>
<a href="${pageContext.request.contextPath}/download.do">文件下载</a>
```

### Servlet

https://www.cnblogs.com/ys-wuhan/p/5772730.html

```java
@WebServlet("/download.do")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //下载目录文件夹
        String downloadDir = request.getServletContext().getRealPath("/download/");
        File file = new File(downloadDir, "学生模板.rar");
        FileInputStream fis = new FileInputStream(file);
        //解决中文文件名下载后乱码的问题   [学生模板.rar]
        String filename= URLEncoder.encode(file.getName(),"utf-8");
        byte[] b = new byte[fis.available()];
        fis.read(b);
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition","attachment; filename="+filename+"");
        //获取响应报文输出流对象   [字节流]
        ServletOutputStream out =response.getOutputStream();
        //输出
        out.write(b);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
```

# 2.异步上传

## 2.1 接口文档

### 请求的根路径

> http://localhost:8080/day22-01-fileupload

### FormData

### 文件上传

+ 接口URL：  /uploadAsynchronous.do
+ 调用方式： POST
+ 参数格式：FormData 格式

| 参数名称 | 参数类型   | 是否必选 | 参数说明 |
| ---- | ------ | ---- | ---- |
| name | String | 是    | 用户名  |
| pic  | file   | 是    | 用户头像 |

+ 响应格式：

| 数据名称   | 数据类型   | 说明               |
| ------ | ------ | ---------------- |
| status | Number | 200 成功；500 失败；   |
| msg    | String | 对 status 字段的详细说明 |
| data   | Object | 用户对象             |
| +name  | String | 用户名              |
| +pic   | String | 图片的URL地址         |

+ 返回示例：

```json
{
    "data": {
        "name": "张三疯",
        "pic": "http://localhost:8080/ajax-fileupload/63f42be8-173c-49d0-9c7c-20bff6f924e7.gif"
    },
    "msg": "上传成功！",
    "status": 200
}
```

## 2.2 服务器端接口开发

```java
@WebServlet("/api/uploadAsynchronous.do")
public class UploadAsynchronousController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		AjaxResult ajaxResult = this.upload(request, response) ;
		String json = JSON.toJSONString(ajaxResult);
		out.print(json);
		out.flush();
		out.close();
	}
	protected AjaxResult upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 检查当前请求是否符合上传要求
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart){
        	return new AjaxResult(501, "请检查客户端代码,没有达到上传要求!");
        }
        //获得tomcat目录
        String tomcatPath = request.getServletContext().getRealPath("/");
        //创建 User对象
        User user = new User();
        try {
            // 创建硬盘工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 创建 解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析请求
            List<FileItem> items = upload.parseRequest(request);
            // 循环请求 Item
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                // 表单的普通控件 【比如：text  radio  checkbox】
                if (item.isFormField()) {
                    // <input name="xxx"  />  xxx
                    String name = item.getFieldName();
                    // <input name="xxx" value="yyy"/>  yyy
                    String value = item.getString("UTF-8");
                    //用户姓名属性赋值
                    if ("name".equals(name)){
                      user.setName(value);
                    }
                } else { //上传控件  [type="file"]
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf(".")) ;
                    File uploadedFile = new File(tomcatPath,fileName);
                    item.write(uploadedFile);
                    //用户姓名属性赋值
                    if ("pic".equals(fieldName)){
                    	String  realPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/" ;
                        user.setPic(realPath+fileName);
                    }
                }
            }
            return new AjaxResult(200, "上传成功！",user) ;
        }catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(505, "上传异常！") ;
        }
	}
}
```

## 2.3 postman测试

![image-20210806085831519](images\ajax-upload.png)

## 2.4 异步上传

###  原生

```html
<input type="text" /><br /><br />
<!-- 选择文件, multiple可以选择多个 -->
<input type="file" /><br /><br />
<!-- 点击上传 -->
<button>上传</button><br /><br />
预览图片:<br /><br />
<img src="" />

<script>
  // 获取元素，绑定事件
  let inName = document.querySelector('input[type="text"]')
  let inp = document.querySelector('input[type="file"]')
  let btn = document.querySelector('button')
  let img = document.querySelector('img')
  btn.onclick = function () {
    // console.log(inp.value); // C:\fakepath\floor-1-1.png
    // files是一个伪数组，因为可以同时上传多个图片;
    // console.log(inp.files); // 存储的所有渲染文件信息;
    // 非空校验
    let file = inp.files[0]
    if (file == undefined) {
      return alert('请渲染上传图片！')
    }
    // 创建一个 formdata 对象
    let fd = new FormData()
    // avatar属性名，从接口文档中获取
    fd.append('pic', file)
    fd.append('name', inName.value)
    // 发送ajax
    let xhr = new XMLHttpRequest()
    xhr.open(
      'POST',
      'http://localhost:8080/ajax-fileupload/uploadAsynchronous.do'
    )
    // 把FormData对象放入send()里。不需要设置 Content-Type 头信息了;
    xhr.send(fd)
    // 接受数据
    xhr.onload = function () {
      // 判断
      let res = JSON.parse(xhr.responseText)
      if (res.status !== 200) {
        return alert(res.msg)
      }
      // 提示上传成功，预览
      alert('恭喜您，文件上传成功！')
      img.src = res.data.pic
    }
  }
</script>
```

### jquery版本

```html
<!-- 选择文件, multiple可以选择多个 -->
<input type="text" /><br /><br />
<input type="file" /><br /><br />
<!-- 点击上传 -->
<button>上传</button><br /><br />
预览图片:<br /><br />
<img src="" />

<!-- 导入jQuery -->
<script src="./lib/jquery.js"></script>
<!-- 书写自己的js -->
<script>
  // jQuery版的文件上传
  $('button').on('click', function () {
    // 非空校验，
    //  $("input")[0]是转换为 DOM 对象;
    //  files[0] 是把文件从为数组中取出来进行判断和操作;
    let file = $("input[type='file']")[0].files[0] // files是DOM元素上的属性;
    let name = $("input[type='text']").val()
    if (file === undefined) {
      return alert('请选择上传文件!')
    }
    // 创建 FormData 对象
    let fd = new FormData()
    // avatar 从接口文档中拿到
    fd.append('pic', file)
    fd.append('name', name)
    // 发送ajax
    $.ajax({
      method: 'POST',
      url: 'http://localhost:8080/ajax-fileupload/uploadAsynchronous.do',
      data: fd,
      // 上传文件，不要忘了两个false
      contentType: false,
      processData: false,
      success: function (res) {
        // console.log(res);
        // 判断
        if (res.status !== 200) {
          return alert(res.msg)
        }
        // 成功后提示，图片预览
        alert('恭喜您，上传文件成功！')
        $('img').attr('src', res.data.pic)
      },
    })
  })
</script>
```



### 原生带进度条

```html
<style>
    progress {
      display: none;
      width: 300px;
      margin: 0 50px;
    }
  </style>
</head>
<body>
  <input type="text" /><br /><br />
  <!-- 选择文件, multiple可以选择多个 -->
  <input type="file" />
  <!-- h5新增的进度条标签 -->
  <progress max="200" value="0"></progress>
  <br /><br />
  <!-- 点击上传 -->
  <button>上传</button><br /><br />
  预览图片:<br /><br />
  <img src="" />

  <script>
    // 获取元素，绑定事件
    let inName = document.querySelector('input[type="text"]')
    let inp = document.querySelector('input[type="file"]')
    let btn = document.querySelector('button')
    let img = document.querySelector('img')
    let progress = document.querySelector('progress');
    btn.onclick = function () {
      // console.log(inp.value); // C:\fakepath\floor-1-1.png
      // files是一个伪数组，因为可以同时上传多个图片;
      // console.log(inp.files); // 存储的所有渲染文件信息;
      // 非空校验
      let file = inp.files[0]
      if (file == undefined) {
        return alert('请渲染上传图片！')
      }
      // 创建一个 formdata 对象
      let fd = new FormData()
      // avatar属性名，从接口文档中获取
      fd.append('pic', file)
      fd.append('name', inName.value)
      // 发送ajax
      let xhr = new XMLHttpRequest()

      // xhr level2 新特性，尽量在open之前设定！
      // xhr.upload.onprogress 上传一点儿就触发一次，持续触发
      xhr.upload.onprogress = function (e) {
        // // e.total 文件总大小;   e.loaded 已经上传的数据大小
        // let pro = (e.loaded/e.total*100).toFixed(2) + '%';
        // console.log(e.loaded, e.total, pro);
        progress.max = e.total
        progress.value = e.loaded
      }
      // 添加显示隐藏: upload.onloadstart 和 upload.onloadend
      xhr.upload.onloadstart = function () {
        progress.style.display = 'inline-block'
      }
      xhr.upload.onloadend = function () {
        progress.style.display = 'none'
      }

      xhr.open(
        'POST',
        'http://localhost:8080/ajax-fileupload/uploadAsynchronous.do'
      )
      // FormData对象不用设置头信息
      xhr.send(fd)
      // 接收
      xhr.onload = function () {
        // 转换为对象，判断数据中的状态值
        let res = JSON.parse(xhr.response)
        if (res.status !== 200) {
          return alert(res.msg)
        }
        // 成功后提示，渲染
        alert('上传成功！')
        img.src = res.data.pic
      }
    }
  </script>
```

![image-20210806085831519](images\3g.png)



### jquery带进度条

```html
<style>
    progress {
      display: none;
      width: 300px;
      margin: 0 50px;
    }
  </style>
</head>
<body>
  <input type="text" /><br /><br />
  <!-- 选择文件, multiple可以选择多个 -->
  <input type="file" />
  <!-- h5新增的进度条标签 -->
  <progress max="200" value="0"></progress>
  <br /><br />
  <!-- 点击上传 -->
  <button>上传</button><br /><br />
  预览图片:<br /><br />
  <img src="" />
  <!-- 导入jQuery -->
  <script src="./lib/jquery.js"></script>
  <!-- 书写自己的js -->
  <script>
    // jQuery版的文件上传
    $('button').on('click', function () {
      // 非空骄校验
      //  $('input')[0]是把jQuery对象转换为DOM对象;
      //  files[0] 从files属性的伪数组中取出第一个文件对象;
      let name = $("input[type='text']").val()
      let file = $('input[type="file"]')[0].files[0]
      if (file == undefined) {
        return alert('请选择图片！')
      }
      // 创建 FormData 对象
      let fd = new FormData()
      fd.append('pic', file)
      fd.append('name', name)
      // 发送ajax
      $.ajax({
        method: 'POST',
        url: 'http://localhost:8080/ajax-fileupload/uploadAsynchronous.do',
        data: fd,
        // jQuery文件上传需要两个false - Uncaught TypeError: Illegal invocation
        contentType: false,
        processData: false,
        success: function (res) {
          console.log(res)
          // 判断
          if (res.status !== 200) {
            return alert(res.message)
          }
          // 成功后提示，渲染图片
          alert('恭喜您，上传图片成功！')
          $('img').attr('src', res.data.pic)
        },
        // 进度条处理中，jQuery没有特殊封装 progress事件，所以要操作xhr
        xhr: function () {
          // 获取xhr
          // let xhr = new XMLHttpRequest(); // 创建新xhr
          let xhr = $.ajaxSettings.xhr() // 获取内置的xhr
          // 回归到原生js
          xhr.upload.onprogress = function (e) {
            $('progress').attr('max', e.total)
            $('progress').attr('value', e.loaded)
          }
          // 添加显示隐藏: upload.onloadstart 和 upload.onloadend
          xhr.upload.onloadstart = function () {
            $('progress').show()
          }
          xhr.upload.onloadend = function () {
            $('progress').hide()
          }
          return xhr
        },
      })
    })
  </script>
```
