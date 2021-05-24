<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/25
  Time: 16:09
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

    <style type="text/css">
        #qrcode {
            width:160px;
            height:160px;
            margin-top:15px;
        }
    </style>

    <script>
        var qrcode = new QRCode("qrcode");

        function makeCode () {
            var elText = document.getElementById("text");

            if (!elText.value) {
                alert("Input a text");
                elText.focus();
                return;
            }

            qrcode.makeCode(elText.value);
        }
        makeCode();
        $("#text").
        on("blur", function () {
            makeCode();
        }).
        on("keydown", function (e) {
            if (e.keyCode == 13) {
                makeCode();
            }
        });

    </script>

</head>
<body>
<input id="text" type="text" value="http://www.runoob.com" /><br />
<div id="qrcode"></div>
</body>
</html>
