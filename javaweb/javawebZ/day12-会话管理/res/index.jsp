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