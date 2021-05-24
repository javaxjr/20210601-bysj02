<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/25
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>大写的jsp名称，其中的代码要移到后台去，实现后台处理</title>
</head>

<%
   //获取用户名
   String uname = request.getParameter("uname");
   //获取密码
   String upass = request.getParameter("upass");

   String rent_login = null;
   if (request.getParameterValues("rent_login") !=null){
      rent_login =  request.getParameterValues("rent_login")[0];
   }

   //判断用户名和密码是否正确
   if ("admin".equals(uname) && "123456".equals(upass)){
       //创建session
       session.setAttribute("username",uname);

       if ("true".equals(rent_login)){
           //将用户信息写入到cookie
           Cookie cookie = new Cookie("username",uname);

           //设置7天免登录
           cookie.setPath("/");
           cookie.setMaxAge(60*60*24*7);
           response.addCookie(cookie);
       }

       //只请求一次 服务器内部转发
       request.getRequestDispatcher("cookie7.jsp").forward(request,response);
       //请求2次
       //response.sendRedirect("");
   }else {
       response.sendRedirect("form.html");
   }

%>

