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
                        <form class="layui-form" style="margin: 0 auto;max-width: 80%;padding-top: 40px;">
                            <input type="hidden" value="" id="projectId">
                            <div class="layui-upload">
                                <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
                                <button type="button" class="layui-btn" id="uploadFile">开始上传</button>
                                <div class="layui-upload-list">
                                    <table class="layui-table">
                                        <thead>
                                        <tr>
                                            <th>文件名</th>
                                            <th>大小</th>
                                            <th>状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody id="fileList"></tbody>
                                    </table>
                                </div>
                                <button class="layui-btn" lay-submit lay-filter="formStep">
                                    &emsp;确定&emsp;
                                </button>
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

        var introduction = layedit.build('introduction'); //建立编辑器

        form.on('submit(formDemo)', function (data) {
            data.field.introduction = layedit.getContent(introduction);
            data.field.username = "${Session.username}";
            $.ajax({
                url: "/api/project",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    if (response.code === 0) {
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

        //多文件上传
        var demoListView = $('#fileList');
        var uploadListIns = upload.render({
            elem: '#selectFile',
            url: '/attachment/upload',
            accept: 'file',
            multiple: true,
            auto: false,
            bindAction: '#uploadFile',
            data: {
                projectId: function () {
                    return $('#projectId').val();
                }
            },
            choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td>' + file.name + '</td>'
                        , '<td>' + (file.size / 1024).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            },
            done: function (res, index, upload) {
                if (res.code === 0) { //上传成功
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                layer.msg(res.msg);
                this.error(index, upload);
            },
            error: function (index, upload) {
                var tr = demoListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });

        form.on('submit(formStep)', function (data) {
            step.next('#stepForm');
            return false;
        });

        $('#back').click(function () {
            let projectId = $('#projectId').val();
            window.location.href = "/project/" + projectId;
        });
    })
</script>

</body>

</html>