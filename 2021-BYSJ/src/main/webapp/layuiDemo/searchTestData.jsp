<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2020/12/28
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>layui</title>
    <base href="${pageContext.request.contextPath}/"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css">
    <script src="layui/layui.js" charset="utf-8"></script>
</head>

<body>

<script type="text/html" id="barDemo">
    <a class="layui_detail_search layui-btn layui-btn-xs" lay-event="update" data-method="offset" data-type="auto">修改</a>
    <a class="layui-btn-danger layui-btn layui-btn-xs" lay-event="delete" data-method="offset" data-type="auto">删除</a>
    <a class="layui-btn-danger layui-btn layui-btn-xs" lay-event="add" data-method="offset" data-type="auto">添加</a>
</script>

<!-- 商品名称 -->
<div class="layui-col-lg3">
    <div class="layui-form-item">
        <label class="layui-form-label layui-bg-green lable-font-size" style="width:80px">商品名称</label>
        <div class="layui-input-block">
            <input id="product_name" type="text" name="product_name" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
</div>
<div class="layui-row">
    <div class="layui-col-lg12">
        <div id="btn_group" class="layui-form">
            <button type="button"  lay-submit lay-filter="LAY-app-contlist-search" class="layui-btn layui-btn-sm" data-type="reload"><i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>查询</button>
            <button type="reset" id="btn_reset" layadmin-event="refresh" class="layui-btn layui-btn-sm layui-btn-primary"><i class="layui-icon layui-icon-refresh layuiadmin-button-btn"></i>重置</button>
        </div>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <!--表格容器-->
        <button type="button" id="downloadProduct"  class="layui-btn layui-btn-sm" style="margin-left: 1100px">生成Excel表格</button>
        <button type="button" id="uploadProduct"  class="layui-btn layui-btn-sm">批量上传商品</button>
        <table id="LAY-pay-settlement-rule2" lay-filter="LAY-pay-settlement-rule2"></table>

    </div>
</div>

<!-- 上传按钮，拖拽上传 -->
<%--<div class="layui-col-lg3">
    <div class="layui-upload-drag layui-input-block" id="uploadExcel"
         style="display: block;margin-left: 0px;">
        <i class="layui-icon"></i>
        <p>点击上传，或将文件拖拽到此处</p>
    </div>
</div>--%>

<script>
    layui.use(['layer',  'form', 'layedit','laypage','carousel','element', 'laydate', 'upload', 'jquery', 'table'],function () {
        var form = layui.form
            , layer = layui.layer//弹出层
            , layedit = layui.layedit  //
            , laydate = layui.laydate//日期
            , upload = layui.upload  //上传文件
            , table = layui.table  //表格
            , view = layui.view  //
            , $ = layui.jquery  //ajax操作
            , carousel=layui.carousel  //轮播
            ,layPage = layui.laypage  //分页
            ,element = layui.element

        /*var laydate = layui.laydate //日期
            ,
            laypage = layui.laypage //分页
            ,
            layer = layui.layer //弹层
            ,
            table = layui.table //表格
            ,
            carousel = layui.carousel //轮播
            ,
            upload = layui.upload //上传
            ,
            element = layui.element //元素操作
            ,
            slider = layui.slider //滑块*/


        table.render({
            elem:"#LAY-pay-settlement-rule2",
            id: 'LAY-pay-settlement-rule2',
            url:"product/listByName" ,
            even:true,
            height:500,
            initSort: {
                field: 'createtime', //排序字段，对应 cols 设定的各字段名
                type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            },
            cols:[
                [
                    {field: 'id', title: '编号', sort: true, width: 200, align: 'center'},
                    {field: 'name', title: '名称', sort: true, width: 200,align: 'center'},
                    {field: 'price', title: '单价', sort: true, width: 200,align: 'center'},
                    {field: 'count', title: '数量', sort: true, width: 200,style:'text-align:center',align: 'center'},
                    {field: 'briefly', title: '简介', sort: true, width: 150,style:'text-align:center',align: 'center'},
                    {field: 'details', title: '详情', sort: true, width: 200,style:'text-align:center',align: 'center'},
                    {field: 'type', title: '类型', sort: true, width: 200,style:'text-align:center',align: 'center'},
                    {fixed: 'right', title: '操作', align: 'center' ,width: 200,toolbar:'#barDemo'}
                ]
            ],
            page: true,
            limit: 5,
            limits:[5,10,15,20]

        });

        form.on('submit(LAY-app-contlist-search)',function () {

            var productName = $("#product_name").val();
            console.log(productName);
            table.reload('LAY-pay-settlement-rule2',{
                where:{
                    product_name:productName
                },
                url:'product/listByName'
            })

        });
        $("#btn_reset").on('click',function () {
            $("#product_name").val("");
            table.reload('LAY-pay-settlement-rule2',{
                where:{
                    product_name:''
                },
                url:'product/listByName'
            })
        });

        //监听头事件
        table.on('tool(LAY-pay-settlement-rule2)',function (obj) {
            var data = obj.data;
            var id = data.id;
            var layEvent = obj.event;

            switch (layEvent) {
                case 'add':
                    // layer.msg('添加');
                    layer.open({
                        type: 2,
                        area: ["100%", "100%"],
                        title: "添加",
                        shadeClose: true,
                        cellMinWidth: 80,
                        content: ['index.jsp', 'auto'], //跳转write页面即表单增加页面，详情查看之前博
                    end: function () {//关闭iframe刷新页面
                        location.reload()
                    },
                    success: function () {}
                });
                break;
                case 'update':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个');
                    } else {
                        layer.open({
                            type: 2,
                            area: ["100%", "100%"],
                            title: "编辑",
                            shadeClose: true,
                            cellMinWidth: 80,
                            content: 'update.html?id=' + id, //跳转update页面，和write.html一样，但是update.html多调用了数据查询的接口，no代表不显示滚动条
                            end: function () {
                                location.reload()
                            },
                            success: function () {

                            }
                        });

                    }
                break;
                case 'delete':
                    if (data.length === 0) {
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除');
                        console.log(data);

                        layer.confirm('真的删除行么', function (index) {

                            $.ajax({
                                type: 'post',
                                url: 'product/deleteById',//调用删除接口，将选中行的id传给后端
                                data:{id:id},
                                success: function (data) {
                                    if (data.msg == "0") {
                                        layer.msg('删除成功');
                                        layer.closeAll();
                                       // layer.reload();
                                        window.parent.location.reload(); //刷新父页面
                                    }
                                }
                            });
                        });
                    }
                break;



            }

            /*if (layEvent === 'update') {
                layer.msg("修改操作");
                //console.log("修改："+layEvent);

            }else if (layEvent === 'delete') {
                layer.msg("删除操作");
                console.log("删除："+layEvent);
            }
            console.log(data);*/
        });

        //下载文件
        $("#downloadProduct").on('click',function () {

            var url = prompt("请输入您的文件名称");
            if (url != null && url.trim().length>0){
                alert("您的Excel文档将保存到您的桌面上");
                if (confirm("请您再次确认是否下载？？")){
                    $.ajax({
                        url:"product/downloadProduct",
                        data:{url:url},
                        type:"post",
                        dataType:"json",
                        success:function (res) {
                            alert(res == true ? "下载成功" : "下载失败");
                        }
                    })
                }
            }else {
                alert("文件名称不能为空！！");
            }

        });

        $("#uploadProduct").on('click',function () {
            location.href="layuiDemo/layuiExcel.jsp";
        })
        //1.拖拽、点击自动上传  选完文件后不自动上传
        /*upload.render({
            elem: '#uploadExcel'
            ,url: "product/uploadExcelSendedNumUp"
            ,multiple: true
            ,accept: 'file' //文件  后台：@RequestParam("file")
            ,acceptMime:'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,application/vnd.ms-excel.sheet.macroEnabled.12'
            //文件窗口默认显示 xlsx|xls|xlsm 的文件
            ,exts:'xlsx|xls|xlsm' //限制后缀名
            ,progress: function(n){
                var percent = n + '%' //获取进度百分比
                element.progress('demo', percent); //可配合 layui 进度条元素使用
            }
            ,done: function(res){
                var errorArray = res.msg.split("；");
                var error=res.msg;
                var prefix = "<ul>"
                var suffix = "</ul>"
                var errorCon = "";
                var errorRemind = "<b>上传失败：</b>"
                for(var i = 0;i<errorArray.length-1;i++){
                    errorCon += '<li>'+(i+1)+'、'+errorArray[i]+'</li>';
                }
                var errorReal = errorRemind + prefix + errorCon + suffix;
                if(!errorCon){
                    if( error !== null || error !== undefined || error !== ''){
                        errorReal = "<b>"+error+"</b>";
                        table.reload('dcSendedNum');
                    }else{console.log(error);
                        errorReal = "<b>上传成功</b>";
                        table.reload('dcSendedNum');//重新渲染，局部页面刷新
                    }
                }
                layer.open({
                    id:"openErrorMsg"
                    ,btnAlign:'c'
                    ,title:'提示信息'
                    ,content:errorReal
                });
                //調用此方法，使數據回顯渲染
                table.reload('dcSendedNumFileInfo');

            },error:function (res) {
                layer.msg("上传异常，请检查上传的Excel表格");
            }
        });*/



    })
</script>
</body>


        <%--<table class="layui-hide" id="loadList" lay-filter="menu-filter" style=" margin: 0 10px;"></table>
        <script type="text/html" id="toolbtn">
            <a class="" lay-event="down"><img src="/images/add.png" alt=""></a>    //按钮样式
        </script>
        table.render({
        elem: '#loadList'
        , url: 'loadList'
        , id: 'loadList'
        , method : 'POST'
        , cols: [[
        {type: 'checkbox', toolbar: '#toolbtn', minWidth: 10,width:30}
        , {type: 'nn', toolbar: '#toolbtn', minWidth: 10,width:50}
        , {field: 'codeUder', title: '揽收员', minWidth: 80,width:110}
        ]]
        , page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
        , curr: 1
        , groups: 5 //只显示 1 个连续页码
        , first: false //不显示首页
        , last: false //不显示尾页
        }
        });
        //新添按钮弹窗
        $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
        });
        table.on('tool(menu-filter)', function (obj) {
        var data = obj.data;
        if (obj.event == "down") {
        sendRequestByAjax("orderCsbDetail", "orderNo="+data.orderNo, function (data) {
        var reg = /<[^>]+>/g;
        if (!reg.test(data)){
        layer.alert(data);
        return;
        }
        layer.open({
        type: 0,
        title: "订单管理列表详情",
        shadeClose: true,
        shade: 0.8,
        offset: "200px",
        move: false,
        area: ['1300px', '570px'],
        content: data
        });
        }, function () {
        layer.alert("请求失败！");
        });
        }
        });
        });
        //监听复选框事件，被选中的行高亮显示
        table.on('checkbox(menu-filter)', function (obj) {
        // console.log(obj)
        if (obj.checked == true && obj.type == 'all') {
        //点击全选
        $('.layui-table-body table.layui-table tbody tr').addClass('layui-table-click');
        } else if (obj.checked == false && obj.type == 'all') {
        //点击全不选
        $('.layui-table-body table.layui-table tbody tr').removeClass('layui-table-click');
        } else if (obj.checked == true && obj.type == 'one') {
        //点击单行
        if (obj.checked == true) {
        obj.tr.addClass('layui-table-click');
        var checkStatus = table.checkStatus('loadList')
        , data = checkStatus.data;
        $('.sp_num').text(data.length)
        } else {
        obj.tr.removeClass('layui-table-click');
        }
        } else if (obj.checked == false && obj.type == 'one') {
        //点击全选之后点击单行
        if (obj.tr.hasClass('layui-table-click')) {
        obj.tr.removeClass('layui-table-click');
        var checkStatus = table.checkStatus('loadList')
        , data = checkStatus.data;
        $('.sp_num').text(data.length)
        }
        }
        });
        ————————————————
        版权声明：本文为CSDN博主「九亿宅男的梦」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/Guoyu1_/article/details/100096741--%>

</html>
