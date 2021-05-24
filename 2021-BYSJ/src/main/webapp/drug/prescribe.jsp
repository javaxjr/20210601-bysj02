<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/11/4
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>开药</title>
    <base href="${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <script src="bootstrap/js/jquery-1.11.3.js" type="text/javascript"></script>
    <script src="bootstrap/js/bootstrap.js" type="text/javascript"></script>

    <style type="text/css">
        #container{
            width: 1200px;
            height: 600px;
            margin: auto;
            margin-left: 230px;
        }
        input{
            border:0px;
            outline: none;
        }
        dl{
            width: 250px;
            height: 250px;
            float: left;
            margin-left: 50px;
        }
        img{
            width: 120px;
            height: 120px;
        }
        h3{
            margin-left: 50px;
        }
        #btn{
            width: 400px;
            margin-left: 450px;
        }
        .dname{
            border: 1px solid #cccccc;
            margin-left: 50px;
        }
    </style>
    <script>
        $(function () {
            dopost("","1")

            $(document).on("click",".chex",function () {
                var flag=$(this).prop("checked");
                var did=$(this).prev().val();

                if (flag == true){
                    $.ajax({
                        url:"moment/addDrug",
                        data:{
                            did:did,
                            flag:flag,
                            pid:2
                        },
                        type:"post",
                        dataType:"json",
                        success:function (data) {
                            alert(data)
                        }
                    })
                }else {
                    $.ajax({
                        url:"moment/delDrug",
                        data:{
                            did:did,
                            pid:2
                        },
                        type:"post",
                        dataType:"json",
                        success:function (data) {
                            alert(data)
                        }
                    })
                }
            })


        })
        function dopost(name, pageNum) {
            $("#nav dl").remove();
            $.ajax({
                url:"drug/list2",
                data:{
                    name:name,
                    pageNum:pageNum
                },
                type:"post",
                dataType:"json",
                success:function (data) {
                    var drug2=data.list;
                    var row="";
                    var flad= true;
                    for (var i = 0; i < drug2.length; i++) {
                        var dr=drug2[i];
                        console.log(dr.id)
                        $.ajax({
                            url:"moment/listByPid",
                            data:{pid:2},
                            type:"post",
                            dataType:"json",
                            success:function (result) {
                                for (var j = 0; j < result.length; j++) {
                                    console.log(result[j].did+"0拉");
                                    console.log(dr.id+"1哈")
                                    //alert(data[i].flag)
                                    //$("#did").next().prop("checked")
                                    /*var did=$(".did").val()

                                    alert(did)*/
                                    if (result[j].did==dr.id) {
                                        flad = false;
                                        console.log(dr.id)
                                        break;
                                    }
                                }
                            }
                        })
                        if(!flad){
                            console.log(flad+"0")
                            row+="<dl><dt><img src='"+dr.photopath+"' name='photo'/></dt>" +
                                "<dd>名称:<input type='text' name='name' class='name' value='"+dr.name+"' readonly='readonly'><br>" +
                                "价格:<input type='text' name='price'  value='"+dr.price+'元'+"' readonly='readonly'><br />" +
                                "形状:<input type='text' name='type'  value='"+dr.type+"' readonly='readonly'><br />" +
                                "适应症状:<input type='text' name='indications'  value='"+dr.indications+"' readonly='readonly'><br /> " +
                                "<input type='text' name='did' class='did' value='"+dr.id+"' readonly='readonly' hidden>  <input type='checkbox'></dd></dl>";
                        }else{
                            console.log(flad+"1")
                            row+="<dl><dt><img src='"+dr.photopath+"' name='photo'/></dt>" +
                                "<dd>名称:<input type='text' name='name' class='name' value='"+dr.name+"' readonly='readonly'><br>" +
                                "价格:<input type='text' name='price'  value='"+dr.price+'元'+"' readonly='readonly'><br />" +
                                "形状:<input type='text' name='type'  value='"+dr.type+"' readonly='readonly'><br />" +
                                "适应症状:<input type='text' name='indications'  value='"+dr.indications+"' readonly='readonly'><br /> " +
                                "<input type='text' name='did' class='did' value='"+dr.id+"' readonly='readonly' hidden>  <input type='checkbox' checked='checked'></dd></dl>";
                        }
                    }
                    $("#nav").append(row);
                    row="<dl id='btn'><br>  <button onclick='fenye(1)' >首页</button><button onclick='fenye("+data.prePage+")'>上一页</button>当前页"+data.pageNum+'/'+data.pages+"<button onclick='fenye("+data.nextPage+")'>下一页</button><button onclick='fenye("+data.pages+")'>尾页</button></dl>";
                    $("#nav").append(row);
                }
            })



        }
        function fenye(pageNum) {
            dopost($("#name").val(),pageNum)
        }

    </script>
</head>
<body>
<div id="container">
    <h3>请选择您的药品</h3>
    <div>
        <input type="text" id="name" class="dname" placeholder="请输入药品关键字">
        <button onclick="fenye(1)">查询</button>
    </div>
    <form id="nav">

    </form>
</div>
</body>
</html>
