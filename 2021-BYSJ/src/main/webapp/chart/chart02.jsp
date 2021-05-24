<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/5/9
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <base href="${pageContext.request.contextPath}/"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title></title>

    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js" type="text/javascript"></script>
    <script src="bootstrap/js/jquery-1.12.4.js" type="text/javascript"></script>
    <link id="layuicss-skinlayim-mobilecss" rel="stylesheet" href="LayIM_files/layim.css" media="all">
    <style type="text/css">

        body .layui-layim-tab {
            display: none;
        }
    </style>
</head>

<body>

<script src="LayIM_files/jquery-3.0.0.min.js"></script>
<script>
    ;
    (function($) {
        $.getUrlParam = function(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if(r != null) return unescape(r[2]);
            return null;
        }
    })(jQuery);
    var _sendUser = $.getUrlParam('sendUser');
    var _toUser = $.getUrlParam('toUser');
    console.log("_sendUser--》" + _sendUser)
    console.log("_toUser--》" + _toUser)

    var sendUser = null;
    var toUser = null;

    $.ajax({
        url: '/GetObj',
        type: 'POST',
        data: 'numder=' + _sendUser,
        async: false, //异步
        dataType: 'json',
        success: function(data) {
            sendUser = data.obj
        },
        error: function(ex) {
            console.log(ex);
        }
    });
    $.ajax({
        url: '/GetObj',
        type: 'POST',
        data: 'numder=' + _toUser,
        dataType: 'json',
        async: false, //异步
        success: function(data) {
            toUser = data.obj
        },
        error: function(ex) {
            console.log(ex);
        }
    });
    layui.config({
        version: '20171011',

    }).use('mobile', function() {
        var mobile = layui.mobile,
            layim = mobile.layim,
            layer = mobile.layer;
        var socket = new WebSocket('ws://zhao.natapp1.cc/chatDemo/' + _sendUser);
        console.log(toUser);
        console.log(sendUser)
        //监听收到的消息
        socket.onmessage = function(res) {
            console.log(res)
            layim.getMessage({
                username: toUser.name,
                avatar: toUser.url,
                id: toUser.id,
                type: "friend",
                cid: Math.random() * 100000 | 0 //模拟消息id，会赋值在li的data-cid上，以便完成一些消息的操作（如撤回），可不填
                ,
                content: res.data
            });
            //      var jsonMsg = {"sendUser":5,"toUser":1,"message":"jkkk"}
            //      socket.send(JSON.stringify(jsonMsg));
            //res为接受到的值，如 {"emit": "messageName", "data": {}}

        };

        //演示自动回复
        var autoReplay = [
            '您好，我现在有事不在，一会再和您联系。',
            '你没发错吧？face[微笑] ',
            '洗澡中，请勿打扰，偷窥请购票，个体四十，团体八折，订票电话：一般人我不告诉他！face[哈哈] ',
            '你好，我是主人的美女秘书，有什么事就跟我说吧，等他回来我会转告他的。face[心] face[心] face[心] ',
            'face[威武] face[威武] face[威武] face[威武] ',
            '<（@￣︶￣@）>',
            '你要和我说话？你真的要和我说话？你确定自己想说吗？你一定非说不可吗？那你说吧，这是自动回复。',
            'face[黑线]  你慢慢说，别急……',
            '(*^__^*) face[嘻嘻] ，是贤心吗？'
        ];

        layim.config({


            title:'消息',
            chatTitleColor:'gray',

            init: {
                //我的信息
                mine: {
                    username: sendUser.name,
                    id: sendUser.id,
                    avatar: sendUser.url,
                    sign: "懒得签名"
                }
                //我的好友列表
                ,
                friend: [{}]

            }

            //扩展更多列表
            ,
            moreList: []

            //,isNewFriend: false //是否开启“新的朋友”
            //,isgroup: true //是否开启“群聊”
            //,chatTitleColor: '#c00' //顶部Bar颜色
            //,title: 'LayIM' //应用名，默认：我的IM
        });


        //创建一个会话

        // layim.chat({
        // 	id: toUser.id
        // 	,name: toUser.name
        // 	,type: 'friend' //friend、group等字符，如果是group，则创建的是群聊
        // 	,avatar: toUser.url
        // });


        //监听点击“新的朋友”
        layim.on('newFriend', function() {
            layim.panel({
                title: '新的朋友' //标题
                ,
                tpl: '<div style="padding: 10px;">自定义模版，{{d.data.test}}</div>' //模版
                ,
                data: { //数据
                    test: '么么哒'
                }
            });
        });

        //查看聊天信息
        layim.on('detail', function(data) {
            //console.log(data); //获取当前会话对象
            layim.panel({
                title: data.name + ' 聊天信息' //标题
                ,
                tpl: '<div style="padding: 10px;">自定义模版，<a href="http://www.layui.com/doc/modules/layim_mobile.html#ondetail" target="_blank">参考文档</a></div>' //模版
                ,
                data: { //数据
                    test: '么么哒'
                }
            });
        });

        //监听点击更多列表
        layim.on('moreList', function(obj) {
            switch(obj.alias) {
                case 'find':
                    layer.msg('自定义发现动作');

                    //模拟标记“发现新动态”为已读
                    layim.showNew('More', false);
                    layim.showNew('find', false);
                    break;
                case 'share':
                    layim.panel({
                        title: '邀请好友' //标题
                        ,
                        tpl: '<div style="padding: 10px;">自定义模版，{{d.data.test}}</div>' //模版
                        ,
                        data: { //数据
                            test: '么么哒'
                        }
                    });
                    break;
            }
        });

        //监听发送消息
        layim.on('sendMessage', function(data) {

            // console.log(data.mine.content)
            // console.log("sign"+data.to.sign)
            var jsonMsg = {
                "sendUser": _sendUser,
                "toUser": _toUser,
                "message": data.mine.content
            }
            socket.send(JSON.stringify(jsonMsg));
            //  //演示自动回复
            //  setTimeout(function(){
            //    var obj = {};
            //    if(To.type === 'group'){
            //      obj = {
            //        username: '模拟群员'+(Math.random()*100|0)
            //        ,avatar: layui.cache.dir + 'images/face/'+ (Math.random()*72|0) + '.gif'
            //        ,id: To.id
            //        ,type: To.type
            //        ,content: autoReplay[Math.random()*9|0]
            //      }
            //    } else {
            //      obj = {
            //        username: To.name
            //        ,avatar: To.avatar
            //        ,id: To.id
            //        ,type: To.type
            //        ,content: autoReplay[Math.random()*9|0]
            //      }
            //    }
            //    layim.getMessage(obj);
            //  }, 1000);
        });

        //模拟收到一条好友消息
        // setTimeout(function() {
        // 	layim.getMessage({
        // 		username: toUser.name,
        // 		avatar: toUser.url,
        // 		id: toUser.id,
        // 		type: "friend",
        // 		cid: Math.random() * 100000 | 0 //模拟消息id，会赋值在li的data-cid上，以便完成一些消息的操作（如撤回），可不填
        // 			,
        // 		content: "嗨，欢迎体验LayIM。演示标记：" + new Date().getTime()
        // 	});
        // }, 2000);

        //监听查看更多记录
        layim.on('chatlog', function(data, ul) {
            console.log(data);
            layim.panel({
                title: '与 ' + data.username + ' 的聊天记录' //标题
                ,
                tpl: '<div style="padding: 10px;">这里是模版，{{d.data.test}}</div>' //模版
                ,
                data: { //数据
                    test: 'Hello'
                }
            });
        });

        //模拟"更多"有新动态
        layim.showNew('More', true);
        layim.showNew('find', true);
    });
</script>

<div id="layui-m-layer0" class="layui-m-layer layui-m-layer1" index="0">
    <div class="layui-m-layermain">
        <div class="layui-m-layersection">
            <div class="layui-m-layerchild  layui-m-anim--1">
                <div class="layui-m-layercont">
                    <div class="layim-panel">
                        <div class="layim-title" style="background-color: #36373C;">
                            <p>消息<span class="layim-chat-status"></span></p>
                        </div>
                        <div class="layui-unselect layim-content">
                            <div class="layui-layim">
                                <!--										<div class="layim-tab-content">-->
                                <!--											<ul class="layim-list-friend">-->
                                <!--												<ul class="layui-layim-list layui-show layim-list-history">-->
                                <!--													<li layim-event="chat" data-type="history" data-index="friend100001" class="layim-friend100001 ">-->
                                <!--														<div><img src="LayIM_files/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></div><span>贤心</span>-->
                                <!--														<p>嗨，欢迎体验LayIM。演示标记：1583116141246</p><span class="layim-msg-status layui-show">1</span></li>-->
                                <!--													<li layim-event="chat" data-type="history" data-index="friend100001222" class="layim-friend100001222 ">-->
                                <!--														<div><img src="LayIM_files/475bb144jw8f9nwebnuhkj20v90vbwh9.jpg"></div><span>刘涛tamia</span>-->
                                <!--														<p>&lt;（@￣︶￣@）&gt;</p><span class="layim-msg-status">new</span></li>-->
                                <!--												</ul>-->
                                <!--											</ul>-->
                                <!--										</div>-->
                                <div class="layim-tab-content  layui-show">
                                    <ul class="layim-list-friend">
                                        <li>
                                            <h5 layim-event="spread" lay-type="true"><i class="layui-icon"></i><span>知名人物</span><em>(<cite class="layim-count"> 5</cite>)</em></h5>
                                            <ul class="layui-layim-list  layui-show">
                                                <li layim-event="chat" data-type="friend" data-index="0" class="layim-friend100001 ">
                                                    <div><img src="LayIM_files/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></div><span>贤心</span>
                                                    <p>这些都是测试数据，实际使用请严格按照该格式返回</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="0" class="layim-friend100001222 ">
                                                    <div><img src="LayIM_files/475bb144jw8f9nwebnuhkj20v90vbwh9.jpg"></div><span>刘涛tamia</span>
                                                    <p>如约而至，不负姊妹欢乐颂</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="0" class="layim-friend10034001 ">
                                                    <div><img src="LayIM_files/633f068fjw8f9h040n951j20ku0kr74t.jpg"></div><span>谢楠</span>
                                                    <p></p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="0" class="layim-friend168168 ">
                                                    <div><img src="LayIM_files/7fde8b93jw1e8qgp5bmzyj2050050aa8.jpg"></div><span>马小云</span>
                                                    <p>让天下没有难写的代码</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="0" class="layim-friend666666 ">
                                                    <div><img src="LayIM_files/6a4acad5jw8eqi6yaholjj20e80e8t9f.jpg"></div><span>徐小峥</span>
                                                    <p>代码在囧途，也要写到底</p><span class="layim-msg-status">new</span></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <h5 layim-event="spread" lay-type="undefined"><i class="layui-icon"></i><span>网红</span><em>(<cite class="layim-count"> 5</cite>)</em></h5>
                                            <ul class="layui-layim-list ">
                                                <li layim-event="chat" data-type="friend" data-index="1" class="layim-friend121286 ">
                                                    <div><img src="LayIM_files/4a02849cjw8fc8vn18vktj20hs0hs75v.jpg"></div><span>罗玉凤</span>
                                                    <p>在自己实力不济的时候，不要去相信什么媒体和记者。他们不是善良的人，有时候候他们的采访对当事人而言就是陷阱</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="1" class="layim-friend108101 ">
                                                    <div><img src="LayIM_files/8693225ajw8fbimjimpjwj20yi0zs77l.jpg"></div><span>Z_子晴</span>
                                                    <p>微电商达人</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="1" class="layim-friend12123454 ">
                                                    <div><img src="LayIM_files/images/icon/005LMAegjw8f2bp9qg4mrj30e80e8dg5.jpg"></div><span>大鱼_MsYuyu</span>
                                                    <p>我瘋了！這也太準了吧 超級笑點低</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="1" class="layim-friend102101 ">
                                                    <div><img src="LayIM_files/6d424ea5jw1e8qgp5bmzyj2050050aa8.jpg"></div><span>Lemon_CC</span>
                                                    <p></p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="1" class="layim-friend3435343 ">
                                                    <div><img src="LayIM_files/961a9be5jw8fczq7q98i7j20kv0lcwfn.jpg"></div><span>柏雪近在它香</span>
                                                    <p></p><span class="layim-msg-status">new</span></li>
                                            </ul>
                                        </li>
                                        <li>
                                            <h5 layim-event="spread" lay-type="true"><i class="layui-icon"></i><span>我心中的女神</span><em>(<cite class="layim-count"> 2</cite>)</em></h5>
                                            <ul class="layui-layim-list  layui-show">
                                                <li layim-event="chat" data-type="friend" data-index="2" class="layim-friend76543 ">
                                                    <div><img src="LayIM_files/48f122e6jw8fcmi072lkyj20e80e8t9i.jpg"></div><span>林心如</span>
                                                    <p>我爱贤心</p><span class="layim-msg-status">new</span></li>
                                                <li layim-event="chat" data-type="friend" data-index="2" class="layim-friend4803920 ">
                                                    <div><img src="LayIM_files/5033b6dbjw8etqysyifpkj20ku0kuwfw.jpg"></div><span>佟丽娅</span>
                                                    <p>我也爱贤心吖吖啊</p><span class="layim-msg-status">new</span></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                                <div class="layim-tab-content">

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
