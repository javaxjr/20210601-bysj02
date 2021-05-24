<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/10/27
  Time: 12:00
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
    <style>
        input{
            width: 400px;
            height: 50px;
            border-radius: 5px;
            margin-top: 10px;
        }
        #btn{
            margin-left: -100px;
        }
        #nav{
            width: 530px;
            height: 500px;
            margin-left: 100px;

        }
        .form-group{
            font-size: 18px;
        }
        img{
            width: 100px;
        }
    </style>
    <script>
        function update() {
            var formdata=new FormData(document.getElementById("drugAdd"));
            $.ajax({
                url:"drug/updata",
                data:formdata,
                type:"post",
                dataType:"json",
                contentType:false,
                processData:false,
                success:function (data) {
                    if (data == true) {
                        alert("修改成功")
                        location.href="drug/drug_list.jsp"
                    }else {
                        alert("修改失败")
                        location.href="drug/drug_list.jsp"
                    }
                }
            })
        }

    </script>
</head>
<body>
<div id="nav">
    <form class="form-horizontal" id="drugAdd">
        <div class="form-group" hidden>
            <label for="inputEmail3" class="col-sm-2 control-label"></label>
            <div class="col-sm-10">
                <input type="text" name="id" class="form-control" id="id" value="${d.id}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputEmail3" class="col-sm-2 control-label">名称</label>
            <div class="col-sm-10">
                <input type="text" name="name" class="form-control" id="inputEmail3" value="${d.name}" required="required" >
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">价格</label>
            <div class="col-sm-10">
                <input type="text" name="price" class="form-control" id="price" value="${d.price}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">形状</label>
            <div class="col-sm-10">
                <input type="text" name="type" class="form-control" id="type" value="${d.type}"  required="required" >
            </div>
        </div>
        <div class="form-group">
            <label for="inputPassword3" class="col-sm-2 control-label">适应症</label>
            <div class="col-sm-10">
                <input type="text" name="indications" class="form-control" id="inputPassword3" value="${d.indications}"  required="required" >
            </div>
        </div>

        图片：<br>
        <img src="${d.photopath}"><br>
        <input type="file" name="photo"  value="${d.photopath}" required="required" id="photo">

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" id="btn" class="btn btn-primary btn-lg active" onclick="update()">点击修改</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
