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

<div class="layui-form layui-form-pane" lay-filter="LAY-popup-addDistributeAccess" id="LAY-popup-addDistributeAccess" style="padding: 0px 00px 0 0px;">

    <div class="layui-row layui-col-space10">

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">消息备注</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="msg" name="msg"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">数据日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="upload_date" name="upload_date"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">接入基础企业</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="telecom_operator" name="telecom_operator"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">码号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="code" name="code"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">码号证书编号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="code_certificate" name="code_certificate"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">开通状态</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="status" name="status"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">开通时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="open_date" name="open_date"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">拆机时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="close_date" name="close_date"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">拆机原因</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="close_reason" name="close_reason"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">码号开通使用位长</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="use_bit" name="use_bit"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">接入合同状态</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="contract_status" name="contract_status"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">接入合同签约日期</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="contract_date" name="contract_date"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">接入合同签约单位</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="contractors" name="contractors"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">解约时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="cancel_date" name="cancel_date"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">解约原因</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="cancel_reason" name="cancel_reason"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">业务平台所在省份</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="site_province" name="site_province"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">业务平台所在城市</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="site_city" name="site_city"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">业务平台所在区县</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="site_district" name="site_district"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">业务平台所在地址</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="machine_room_site" name="machine_room_site"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">业务平台所在地区号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="machine_room_code" name="machine_room_code"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">接入线路资源类型</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="line_type" name="line_type"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">线路总数量</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="line_num" name="line_num"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">开通业务类型</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="code_ability" name="code_ability"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">呼出日上限</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="call_up_capability" name="call_up_capability"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">语音外呼路数</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="call_concurrent_capability" name="call_concurrent_capability"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">上行短信容量上限</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="sms_up_capability" name="sms_up_capability"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">下行短信容量上限</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="sms_down_capability" name="sms_down_capability"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">号码详细用途</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="use" name="use"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">语音功能</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="voice_type" name="voice_type"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">呼出开通时间</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="call_up_open_date" name="call_up_open_date"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">呼出显示的长途区号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="code_format" name="code_format"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">呼入加拨的长途区号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="dial_type" name="dial_type"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">基础企业联系人</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="telecom_contact_man" name="telecom_contact_man"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">基础企业联系人手机号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="telecom_contact_number" name="telecom_contact_number"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">码号使用单位联系人</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="contact_man" name="contact_man"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

        <div class="layui-col-lg4">
            <div class="layui-form-item">
                <label class="layui-form-label layui-bg-green" style="width:110px;font-size: 12px;">码号使用单位联系人手机号</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="contact_number" name="contact_number"   placeholder="请输入" autocomplete="off" style="width:240px">
                </div>
            </div>
        </div>

    </div>
    <div>
        <button type="button" id="apply-submit02" lay-submit lay-filter="settle-app-form-submit" class="layui-btn layui-btn-sm layui-bg-blue">添加</button>
        <button type="button" id="btn_apply-close02" class="layui-btn layui-btn-sm layui-bg-blue" style="margin-left: 5px">取消</button>
    </div>
</div>

<script>
    layui.use(['upload','form','jquery'],function () {
        var form = layui.form,
            upload = layui.upload,
            $ = layui.jquery


        upload.render({

        })



    })

    $(function(){
        var uploadFileUrl = '../map/upload';
        layui.use('upload', function(){
            var upload = layui.upload;
            aler("dfdsf");
//执行实例
            upload.render({

                elem: '#test1' //绑定元素
                ,url: '../map/upload' //上传接口
                ,done: function(res){
//上传完毕回调
                    alert(res);
                }
                ,error: function(){
//请求异常回调
                    alert(111);
                }
                ,accept:'file'//允许的文件类型
                ,auto: false//选择文件后是否自动上传
                ,bindAction:'#submit'

            });
        });
    });
</script>
</body>
</html>
