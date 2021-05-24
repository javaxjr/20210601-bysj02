<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/24
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>layui中的富文本编辑器</title>
    <base href="${pageContext.request.contextPath}/"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>完整功能（没错，目前工具栏只有这么多）</legend>
</fieldset>

<div class="layui-col-md8">
    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-block">
            <textarea class="layui-textarea" id="LAY_demo1" name="content" style="display: none"></textarea>
        </div>
    </div>
</div>
<%--<textarea class="layui-textarea" id="LAY_demo1" name="content" style="display: none"></textarea>--%>


<div class="site-demo-button" style="margin-top: 20px;">
    <button class="layui-btn site-demo-layedit" data-type="content">提交</button><%--获取编辑器内容--%>

</div>

<script>
    //定义全局变量
    var index;
    /**
     * 文本编辑器
     */
    layui.use(['form','layedit','element'], function(){
        var layedit = layui.layedit,
            $ = layui.jquery,
            element = layui.element;


        //上传图片,必须放在创建一个编辑器前面/  注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
        layedit.set({
            uploadImage: {
                url: 'product/uploadImage' //接口url
                ,type: 'post' //默认post
            }
        });

        //构建一个默认的编辑器   内容放在编辑器中
        var index = layedit.build('LAY_demo1',{
            height: 420 //设置编辑器高度

        });


        //编辑器外部操作
        var active = {
            content: function(){
                //编辑器中的内容
              var content =  layedit.getContent(index); //获取编辑器内容
                 // content = JSON.stringify(content); //变成JSON字符串的形式
                //alert(content);
                $.ajax({
                    url:"product/addLayuiAndfwb",
                    data:{content:content},
                    type:"post",
                    dataType:"json",
                    success:function (res) {

                    }
                })
            }
        };

        $('.site-demo-layedit').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });



    });
</script>


</body>
</html>
