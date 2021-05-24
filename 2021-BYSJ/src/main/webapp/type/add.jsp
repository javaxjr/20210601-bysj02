<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/12/30
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.contextPath}/"/>
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <%--<script>
        function add() {
            var format=new FormData(document.getElementById("productAdd"))
            $.ajax({
                url:"type/add",
                data:format,
                type:"post",
                dataType:"json",
                contentType:false,
                processData:false,
                success:function (data) {
                    alert(data)
                }
            })
        }
    </script>--%>
</head>
<body>
<form action="type/add" method="post">
    名称：<input type="text" name="name"><br>
    <input type="submit"  value="添加">
</form>

</body>
</html>
