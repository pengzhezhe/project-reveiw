<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>发布项目-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/step-lay/step.css"/>
    <link rel="stylesheet" href="/css/global.css"/>
</head>

<body>
<#include "/common/header.ftl">
<div class="layui-container">
    <div class="layui-row">
        <div class="fly-panel" style="padding: 5px 15px;margin-top: 10px;">
            <div class="layui-carousel" id="stepForm" lay-filter="stepForm" style="margin-top: 30px;overflow: hidden">
                <div carousel-item>
                    <div>
                        <form class="layui-form" style="margin: 0 auto;max-width: 80%;padding-top: 40px;">
                            <div class="layui-form-item">
                                <label class="layui-form-label">项目名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="name" required lay-verify="required"
                                           placeholder="请输入项目名" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">项目简介</label>
                                <div class="layui-input-block">
                                    <textarea name="introduction" id="introduction" placeholder="请输入内容"
                                              style="display: none"></textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="formDemo">下一步</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <form class="layui-form" style="margin: 0 auto;max-width: 50%;padding-top: 40px;">
                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <input type="hidden" value="" id="projectId">
                                    <button type="button" class="layui-btn" id="test1">
                                        <i class="layui-icon">&#xe67c;</i>上传文件
                                    </button>
                                    <button class="layui-btn" lay-submit lay-filter="formStep">
                                        &emsp;确定&emsp;
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div>
                        <div style="text-align: center;margin-top: 90px;">
                            <i class="layui-icon layui-circle"
                               style="color: white;font-size:30px;font-weight:bold;background: #52C41A;padding: 20px;line-height: 80px;">&#xe605;</i>
                            <div style="font-size: 24px;color: #333;font-weight: 500;margin-top: 30px;">
                                上传成功
                            </div>
                        </div>
                        <div style="text-align: center;margin-top: 50px;">
                            <button class="layui-btn layui-btn-primary" id="back">返回</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/layui/layui.js"></script>
<script src="/step-lay/step.js"></script>

<script>
    layui.config({base: '/step-lay/'}).use(['form', 'layedit', 'step', 'layer', 'element', 'upload'], function () {
        var $ = layui.$;
        var form = layui.form;
        var step = layui.step;
        var layer = layui.layer;
        var upload = layui.upload;
        var layedit = layui.layedit;
        var element = layui.element;
        step.render({
            elem: '#stepForm',
            filter: 'stepForm',
            width: '100%', //设置容器宽度
            stepWidth: '100%',
            height: '600px',
            stepItems: [{
                title: '填写项目信息'
            }, {
                title: '项目附件上传'
            }, {
                title: '完成'
            }]
        });

        var contentIndex = layedit.build('introduction'); //建立编辑器

        form.on('submit(formDemo)', function (data) {
            data.field.introduction = layedit.getContent(contentIndex);
            console.log(data.field);
            $.ajax({
                url: "/project/add",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    if (response.code === 1) {
                        $('#projectId').val(response.data);
                        step.next('#stepForm');
                    } else
                        layer.msg(response.msg);
                }
            });
            return false;
        });

        upload.render({
            elem: '#test1',
            accept: 'file',
            url: '/attachment/upload',
            data: {
                projectId: function () {
                    return $('#projectId').val();
                }
            },
            done: function (data) {
                layer.msg(data.msg);
            },
            error: function (data) {
                layer.msg("上传失败")
            }
        });

        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        $('#back').click(function () {
            window.location.href = "/project"
        });
    })
</script>

</body>

</html>