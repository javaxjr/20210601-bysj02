<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/27
  Time: 8:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生会端</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/back/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/xadmin.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/Swiper/3.4.2/css/swiper.min.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/Swiper/3.4.2/js/swiper.jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/back/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/back/js/xadmin.js"></script>
    <script>
    </script>
</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="${pageContext.request.contextPath}/back/union/union-index.jsp">多彩校园</a></div>
    <div class="open-nav"><i class="iconfont">&#xe699;</i></div>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${sessionScope.unionLogin.sname}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="">个人信息</a></dd>
                <dd><a href="">切换帐号</a></dd>
                <dd><a href="${pageContext.request.contextPath}/student/logout">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/front/index.jsp">前台首页</a></li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<div class="wrapper">
    <!-- 左侧菜单开始 -->
    <div class="left-nav">
        <div id="side-nav">
            <ul id="nav">
                <li class="list" current>
                    <a href="${pageContext.request.contextPath}/back/union/union-index.jsp">
                        <i class="iconfont">&#xe761;</i>
                        欢迎页面
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                </li>
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe70b;</i>
                        资讯管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/news/findByLike">
                                <i class="iconfont">&#xe6a7;</i>
                                资讯列表
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        活动管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/activity/list">
                                <i class="iconfont">&#xe6a7;</i>
                                活动列表
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="list">
                    <a href="javascript:;">
                        <i class="iconfont">&#xe6a3;</i>
                        兼职管理
                        <i class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <ul class="sub-menu" style="display:none">
                        <li>
                            <a href="${pageContext.request.contextPath}/job/list">
                                <i class="iconfont">&#xe6a7;</i>
                                兼职列表
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="content">
            <!-- 右侧内容框架，更改从这里开始 -->
            <!-- 右侧内容框架，更改从这里结束 -->
        </div>
    </div>
    <!-- 右侧主体结束 -->
</div>
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2017 x-admin v2.3 All Rights Reserved. 本后台系统由X前端框架提供前端技术支持</div>
</div>
<!-- 底部结束 -->
<!-- 背景切换开始 -->
<%--<div class="bg-changer">--%>
<%--    <div class="swiper-container changer-list">--%>
<%--        <div class="swiper-wrapper">--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/a.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/b.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/c.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/d.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/e.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/f.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/g.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/h.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/i.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/j.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><img class="item" src="./images/k.jpg" alt=""></div>--%>
<%--            <div class="swiper-slide"><span class="reset">初始化</span></div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div class="bg-out"></div>--%>
<%--    <div id="changer-set"><i class="iconfont">&#xe696;</i></div>--%>
<%--</div>--%>
<!-- 背景切换结束 -->
</body>
</html>
