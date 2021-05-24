<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/25
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用cookie实现7天免登录</title>
</head>
<body>
<%
    //读取cookie
    Cookie[] cookies = request.getCookies();
    //判断是否有cookie名称为username
    for (Cookie cookie : cookies) {
        if ("username".equals(cookie.getName())){
           session.setAttribute("username",cookie.getValue());
        }
    }
    String username = (String) session.getAttribute("username");

    if (username != null){
        out.print("爱豆，您好 "+username);
    }else {
        response.sendRedirect("form.html");
    }
%>

</body>
</html>
