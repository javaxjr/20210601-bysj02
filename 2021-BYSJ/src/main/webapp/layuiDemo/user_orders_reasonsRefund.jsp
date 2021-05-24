<%--
  Created by IntelliJ IDEA.
  User: 17814
  Date: 2021/3/21
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户退货订单信息</title>
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

<div class="layui-card">
    <div class="layui-card-body">
        <!--表格容器-->
        <table id="LAY-pay-settlement-rule2" lay-filter="LAY-pay-settlement-rule2"></table>

    </div>
</div>


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
            ,element = layui.element;

        table.render({
            elem:"#LAY-pay-settlement-rule2",
            id: 'LAY-pay-settlement-rule2',
            url:"product/listByNameAndOrdersReasonsRefund" ,
            even:true,
            height:500,
            width:900,
            initSort: {
                field: 'createtime', //排序字段，对应 cols 设定的各字段名
                type: 'desc' //排序方式  asc: 升序、desc: 降序、null: 默认排序
            },
            cols:[
                [
                    {field: 'id', title: '编号', sort: true, width: 150, align: 'center'},
                    {field: 'user_name', title: '收货人姓名', sort: true, width: 150,align: 'center'},
                    {field: 'user_phone', title: '收货人联系电话', sort: true, width: 150,align: 'center'},
                    {field: 'reasonsRefund', title: '退款原因', sort: true, width: 150,align: 'center'},
                    {field: 'cargoStatus', title: '货物状态', sort: true, width: 150,align: 'center'},
                    {field: 'pCount', title: '购买数量', sort: true, width: 150,style:'text-align:center',align: 'center'},
                    {field: 'order_status', title: '订单状态', sort: true, width: 150,style:'text-align:center',align: 'center'},
                    {fixed: 'right', title: '操作', align: 'center' ,width: 150,toolbar:'#barDemo'}
                ]
            ],
            page: true,
            limit: 10

        });
    })

</script>

</body>
</html>
