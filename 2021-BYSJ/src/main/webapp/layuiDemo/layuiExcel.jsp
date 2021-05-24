<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/12/14
  Time: 21:23
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
    <script>
        $(function () {
            //上传用户excel

            $("#upload").click(function(){
                var format = new FormData(document.getElementById("uploadForm"));
                $.ajax({
                    url : "product/uploadExcelDistributeAccess",
                    data : format,
                    type : "post",
                    processData : false,
                    contentType : false,
                    dataType:"json",
                    success:function(result){
                        alert(result==true?"上传成功":"上传失败");
                        location.href="searchTestData.jsp";
                    }
                });
            });

        })

    </script>
</head>
<body>
<form id="uploadForm">
    <%--名称：<input name="name" type="text"><br>
    单价：<input name="price" type="text"><br>--%>
    <input type="file" name="file"/>
    <input id="upload" type="button" value="上传数据"/>
</form>

</body>
</html>
