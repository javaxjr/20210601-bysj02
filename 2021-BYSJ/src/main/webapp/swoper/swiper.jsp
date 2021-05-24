<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/4/5
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/back/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
</head>
<body>
<div class="login-logo"><h1>1688个人中心</h1></div>
<div class="login-box">
    <form class="layui-form layui-form-pane" action="${pageContext.request.contextPath}/teacher/login">

        <h3>登录你的帐号</h3>
        <label class="login-title" for="tphone">手机号</label>
        <div class="layui-form-item">
            <label class="layui-form-label login-form"><i class="iconfont">&#xe6b8;</i></label>
            <div class="layui-input-inline login-inline">
                <input type="text" name="tphone" id="tphone" lay-verify="required" placeholder="请输入你的帐号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <label class="login-title" for="tpassword">密码</label>
        <div class="layui-form-item">
            <label class="layui-form-label login-form"><i class="iconfont">&#xe82b;</i></label>
            <div class="layui-input-inline login-inline">
                <input type="password" name="tpassword" id="tpassword" lay-verify="required" placeholder="请输入你的密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="form-actions">
            <button class="btn btn-warning pull-right"   type="submit">登录</button>
            <div class="forgot"><a href="#" class="forgot">忘记帐号或者密码</a></div>
        </div>
    </form>
</div>
<script>
    $(function  () {
        layui.use('form', function(){
            var form = layui.form();
            //监听提交
            form.on('submit(login)', function(data){
                layer.msg(JSON.stringify(data.field),function(){
                    location.href='index.html'
                });
                return false;
            });
        });
    })

</script>
<script>
    /*样式一*/
    (function($){
        $.fn.snow = function(options){
            var $flake = $('<div id="snowbox" />').css({'position': 'absolute','z-index':'9999', 'top': '-50px'}).html('&#10052;'),
                documentHeight 	= $(document).height(),
                documentWidth	= $(document).width(),
                defaults = {
                    minSize		: 10,
                    maxSize		: 20,
                    newOn		: 1000,
                    flakeColor	: "#FFFFFF" /* 此处可以定义雪花颜色，若要白色可以改为#FFFFFF */
                },
                options	= $.extend({}, defaults, options);
            var interval= setInterval( function(){
                var startPositionLeft = Math.random() * documentWidth - 100,
                    startOpacity = 0.5 + Math.random(),
                    sizeFlake = options.minSize + Math.random() * options.maxSize,
                    endPositionTop = documentHeight - 200,
                    endPositionLeft = startPositionLeft - 500 + Math.random() * 500,
                    durationFall = documentHeight * 10 + Math.random() * 5000;
                $flake.clone().appendTo('body').css({
                    left: startPositionLeft,
                    opacity: startOpacity,
                    'font-size': sizeFlake,
                    color: options.flakeColor
                }).animate({
                    top: endPositionTop,
                    left: endPositionLeft,
                    opacity: 0.2
                },durationFall,'linear',function(){
                    $(this).remove()
                });
            }, options.newOn);
        };
    })(jQuery);
    $(function(){
        $.fn.snow({
            minSize: 5, /* 定义雪花最小尺寸 */
            maxSize: 50,/* 定义雪花最大尺寸 */
            newOn: 300  /* 定义密集程度，数字越小越密集 */
        });
    });
</script>
</body>
</html>
