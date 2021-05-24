<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/12/14
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.contextPath}/"/>

</head>
<body>
    <form action="admin/add" method="post">
        姓 &nbsp;&nbsp;&nbsp; 名：<input type="text" name="name" placeholder="请输入您的真实姓名"><br>
        用户名：<input type="text" name="username" placeholder="请输入您的用户名"><br>
        密码：<input type="password" name="password" placeholder="请输入您的密码"><br>
        确认密码：<input type="password" placeholder="请您再次确认您的密码"><br>
        照片：<input type="file" name="photo"><br>
        年龄：<input type="text" name="age" placeholder="请输入您的年龄"><br>
        性别：
        <input type="radio" name="sex" value="男" required>男
        <input type="radio" name="sex" value="女" required>女
        <br>
        联系电话：<input type="text" name="phone" placeholder="请输入您的真实姓名"><br>
        <input type="submit" value="添加">
    </form>
</body>
</html>
