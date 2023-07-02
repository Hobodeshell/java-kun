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
    <!-- 购物车中没有任何商品 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <h1>购物车中没有任何宝贝</h1>
    </div>
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
        <tr>
            <td><img src="<%=request.getContextPath()%>/images/game.jpg" class="img-thumbnail"> </td>
            <td>游戏机</td>
            <td>15</td>
            <td>
                <button class="btn btn-primary" type="button" onclick="">-</button>
                <input type="text" id="count" class="form-control" value="1" style="width: 40px">
                <button class="btn btn-primary"  type="button" onclick="">+</button>
            </td>
            <td>15</td>
            <td>
                <a href="javaScript:" class="btn btn-info btn-sm">修改</a>
                <a href="javaScript:" class="btn btn-danger btn-sm">删除</a>
            </td>
        </tr>
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
