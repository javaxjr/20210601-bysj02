<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2021/10/21
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>五星好评</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link href="bootstrap/css/file_index.css" rel="stylesheet">
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>
</head>

<%--样式--%>
<style>
    ul{
        list-style-type: none;
    }
    li{
        float: left;
        margin-left: 15px;
        font-size: 60px;
        font-family: "simsun";
        cursor: pointer;
    }
    div{
        clear: both;
    }
    button{
        margin-left: 70px;
        font-size:30px;
    }
</style>

<%--js代码--%>
<script>
    //    入口函数
    $(function(){
//        设置两个状态的星星
        var emptyStar="☆";
        var fullStar="★";
//        li鼠标移入事件
        $("li").mouseenter(function(){
            $(this).text(fullStar).prevAll().text(fullStar).end().nextAll().text(emptyStar);
        });
//        li鼠标离开事件
        $("li").mouseleave(function(){
            $("li").text(emptyStar);
            $("li.current").text(fullStar).prevAll().text(fullStar).end().nextAll().text(emptyStar);

        });
//        li点击事件
        $("li").click(function(){
            if($(this).hasClass("current")){
                $(this).removeAttr("class");
            }else{
                $(this).attr("class","current").siblings().removeAttr("class");
            }
        })
//        button点击事件
        $("button").click(function(){
            var boo=false;
            for(var i=0;i<$("ul>li").length;i++){
                if($("ul>li").hasClass("current")){
                    boo=true;
                }
            }
            if(boo){
                $("#jquDiv").html("<span>分数:"+($("ul>li.current").index()+1)+" 分</span>");
                console.log("分数："+($("ul>li.current").index()+1)+"分");
            }else{
                return alert("您还没有选择呢！");
            }
        })
    })
</script>

<body>
<ul class="box">
    <li>☆</li>
    <li>☆</li>
    <li>☆</li>
    <li>☆</li>
    <li>☆</li>
</ul>

<div id="jquDiv">
    <button>提交</button>
</div>

</body>
</html>
