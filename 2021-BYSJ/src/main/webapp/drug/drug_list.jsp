<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/10/27
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <title>Title</title>
    <style>
        img{
            width: 100px;
        }
    </style>
    <script>
        $(function () {
            dopost("","1")
        })
        function dopost(name, pageNum) {
            //除了第一行，每次执行都会移除其他
            $("#tb tr:gt(0)").remove();
            $("button").remove();
            //使用ajax从后台取得数据回显当前页
            $.ajax({
                url:"drug/list",
                type:"post",
                data:{
                    name:name,
                    pageNum:pageNum
                },
                dataType:"json",
                success:function (data) {
                    var list=data.list;
                    var row="";
                    for (var i = 0; i < list.length;i++){
                        var drug=list[i];
                        row+="<tr><td>"+drug.id+"</td><td>"+drug.name+"</td><td>"+drug.price+"</td><td>"+drug.type+"</td><td>"+drug.indications+"</td><td><img src='"+drug.photopath+"'></td><td><button onclick='findById("+drug.id+")'>编辑</button><button onclick='del("+drug.id+")'>删除</button></td></tr>";
                    }
                    $("#tb").append(row);
                    row="<tr><td colspan='8'><button onclick='fenye(1)'>首页</button>" +
                        "<button onclick='fenye("+(data.pageNum==1?1:data.prePage)+")'>上一页</button>" +
                        "当前页"+data.pageNum+'/'+data.pages+"" +
                        "<button onclick='fenye("+(data.pageNum==data.pages?data.pages:data.nextPage)+")'>" +
                        "下一页</button><button onclick='fenye("+data.pages+")'>尾页</button> </td></tr>";
                    $("#tb").append(row);
                }
            })
        }
        function fenye(pageNum) {
            dopost($("#name").val(),pageNum)
        }
        function findById(id) {
            location.href="drug/findById?id="+id;
        }
        function del(id) {
            if (confirm("您是否确定要删除此条信息！！"))
            location.href="drug/delById?id="+id;
        }
    </script>
</head>
<body>

<div>
    <input type="text" id="name" placeholder="请输入药品关键字"><input type="button" onclick="fenye(1)" value="查询">
</div>

<table class="table table-hover" id="tb">
    <tr>
        <th>药品编号</th>
        <th>药品名称</th>
        <th>药品价格</th>
        <th>药品规格</th>
        <th>药品适应症</th>
        <th>药品图片</th>
        <th>操作</th>
    </tr>
</table>

</body>
</html>
