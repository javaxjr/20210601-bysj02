<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/25
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读取cookie</title>
</head>
<body>

<%

    //步骤1 获取当前domain下的可读取cookie
    Cookie[] cookies = request.getCookies();

    //便利cookie数组
    for (Cookie cookie : cookies) {
        out.print(cookie.getName() +": "+cookie.getValue());
    }

%>


</body>
</html>
