<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/25
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cookie信息</title>
</head>
<body>
<%

    //读取cookie
    Cookie[] cookies = request.getCookies();
    if (cookies != null){
        for (Cookie cookie : cookies) {
            //判断是否有cookie名称为username
            if ("username".equals(cookie.getName())){
                session.setAttribute("username",cookie.getValue());
            }
        }
    }
    String username = (String) session.getAttribute("username");
    if (username!=null){
        out.print("您好："+username);
    }else {
        //若cookie中的值为空 则跳转到登录页
        response.sendRedirect("form.html");
    }
%>
</body>
</html>
