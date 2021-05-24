<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/12/14
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <title>Title</title>
    <style type="text/css">
        table{
            width: 800px;
            text-align: center;
            border-collapse: collapse;
        }
        th,td{
            border: 1px solid #CCCCCC;
        }
    </style>
    <script type="text/javascript">
        function fenye(pageNum) {
            location.href="admin/list?pageNum="+pageNum+"&name="+document.getElementById("name").value;
        }
    </script>
</head>
<body>
    <div>
        <input type="text" value="${name}" id="name" placeholder="请输入管理员姓名">
        <button onclick="fenye(1)">查询</button>
    </div>
    <table>
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>用户名</th>
            <th>密码</th>
            <th>年龄</th>
            <th>性别</th>
            <th>联系方式</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${page.list}" var="a">
            <tr>
                <td>${a.id}</td>
                <td>${a.name}</td>
                <td>${a.username}</td>
                <td>${a.password}</td>
                <td>${a.age}</td>
                <td>${a.sex}</td>
                <td>${a.phone}</td>
                <td>
                    <button>编辑</button>
                    <button>删除</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="8">
                <button onclick="fenye(1)">首页</button>
                <button onclick="fenye(${page.prePage})">上一页</button>
                当前页${page.pageNum}/${page.pages}
                <button onclick="fenye(${page.nextPage})">下一页</button>
                <button onclick="fenye(${page.pages})">尾页</button>
            </td>
        </tr>
    </table>
</body>
</html>
