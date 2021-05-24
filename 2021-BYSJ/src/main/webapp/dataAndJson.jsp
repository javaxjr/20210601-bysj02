<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/4/2
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="bootstrap/css/file_index.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
    <script type="text/javascript">
        $.ajax({
            url:"product/dataAndJSON",
            type:"POST",
            dataType:"json",
            success:function (res) {
                console.log(res);
            }
        })
    </script>
</head>
<body>

</body>
</html>
