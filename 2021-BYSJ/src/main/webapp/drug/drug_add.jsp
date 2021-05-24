<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/10/26
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script>
        function add() {
            var format= new FormData(document.getElementById("drugAdd"));
            $.ajax({
                url:"drug/add",
                type:"post",
                data:format,
                dataType:"json",
                contentType:false,
                processData:false,
                success:function (data) {
                    if (data==true){
                        alert("添加成功")
                        location.href="drug/drug_list.jsp"
                    }else {
                        alert("添加失败")
                        location.href="drug/drug_add.jsp"
                    }
                }
            })
        }
    </script>
    <style>
        input{
            width: 400px;
            height: 50px;
            border-radius: 5px;
            margin-top: 10px;
        }
        #btn{
            height: 50px;
        }
        #nav{
            width: 530px;
            height: 500px;

        }
        .form-group{
            font-size: 18px;
        }
        #photo{
            margin-left: 100px;
        }
    </style>
</head>
<body>
<div id="nav">
    <h3>请输入药品信息</h3>
    <form class="form-horizontal" id="drugAdd">
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
            <div class="col-sm-10">
                <input type="text" name="name" class="form-control" id="inputEmail3"placeholder="请输入药品名称" required="required" >
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">价格</label>
            <div class="col-sm-10">
                <input type="text" name="price" class="form-control" id="price" placeholder="请输入药品价格">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">形状</label>
            <div class="col-sm-10">
                <input type="text" name="type" class="form-control" id="type" placeholder="请输入药品形状" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">适应症</label>
            <div class="col-sm-10">
                <input type="text" name="indications" class="form-control" id="inputPassword3" placeholder="请输入药品适应症"  required="required">
            </div>
        </div>

        <input type="file" name="photo"  required="required" id="photo">

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button"  onclick="add()"  id="btn" class="btn btn-primary btn-lg active">点击添加</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
