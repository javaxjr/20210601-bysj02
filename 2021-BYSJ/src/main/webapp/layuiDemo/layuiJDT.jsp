<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/4/1
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="demo">
        <div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>
    </div>

    <div class="site-demo-button" style="margin-top: 20px; margin-bottom: 0;">
        <button class="layui-btn site-demo-active" data-type="setPercent">设置50%</button>
        <button class="layui-btn site-demo-active" data-type="loading">模拟loading</button>
    </div>
    <script>
        layui.use('element', function(){
            var $ = layui.jquery
                ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

            //触发事件
            var active = {
                setPercent: function(){
                    //设置50%进度
                    element.progress('demo', '50%')
                }
                ,loading: function(othis){
                    var DISABLED = 'layui-btn-disabled';
                    if(othis.hasClass(DISABLED)) return;

                    //模拟loading
                    var n = 0, timer = setInterval(function(){
                        n = n + Math.random()*10|0;
                        if(n>100){
                            n = 100;
                            clearInterval(timer);
                            othis.removeClass(DISABLED);
                        }
                        element.progress('demo', n+'%');
                    }, 300+Math.random()*1000);

                    othis.addClass(DISABLED);
                }
            };

            $('.site-demo-active').on('click', function(){
                var othis = $(this), type = $(this).data('type');
                active[type] ? active[type].call(this, othis) : '';
            });
        });
    </script>
</div>
</body>
</html>
