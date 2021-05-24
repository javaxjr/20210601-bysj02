<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/12/14
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script>
            function add() {
                var format=new FormData(document.getElementById("addProduct"));
                $.ajax({
                    url:"product/addSave",
                    data:format,
                    type:"post",
                    dataType:"json",
                    contentType:false,
                    processData:false,
                    success:function (data) {
                        alert(data)
                        if (data == false) {
                            /*location.href="product/listByName";*/
                        }
                    }
                })
            }


    </script>
</head>
<body>
    <form id="addProduct">
    <%--<form action="/product/add" method="post" enctype="multipart/form-data">--%>
        商品名称:<input type="text" id="name" name="name" placeholder="请输入商品名称"><br>
        商品价格:<input type="text" name="price" placeholder="请输入商品价格"><br>
        商品库存量:<input type="text" name="count" placeholder="请输入商品库存"><br>
        商品图片:<input type="file" name="photo"><br>
        商品简介:<input type="text" name="briefly" placeholder="请输入商品简介"><br>
        商品详情:<input type="text" name="details" placeholder="请输入商品详情"><br>
        商品类型:<input type="text" name="type" placeholder="请输入商品类型"><br>
        <input type="button" onclick="add()" value="添加"/>
        <%--<button type="submit">添加</button>--%>
        <button type="reset">重置</button>
    </form>

</body>
</html>
