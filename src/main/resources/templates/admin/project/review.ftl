<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>项目详情-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/css/global.css"/>
</head>

<body style="margin-top: 20px">
<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md10">
            <div class="fly-panel detail-box">
                <h1>${project.name}</h1>
                <div class="fly-detail-info">
                    <span style="color: #999;">${project.userName}</span>
                    <span style="font-size: small;color:#999;padding-left: 15px;">${project.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                </div>
                <hr class="layui-bg-gray">
                <div class="detail-body">
                    <div style="min-height:200px">${project.introduction}</div>
                    <div class="layui-row">
                        <hr class="layui-bg-gray">
                        <#list attachments as attachment>
                            <a href="/attachment/download/${attachment.id}"
                               style="font-size: small;">${attachment.originalName}</a>
                            <br>
                        </#list>
                    </div>
                </div>
            </div>
            <div class="fly-panel detail-box">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>项目评审</legend>
                </fieldset>
                <form class="layui-form">
                    <div class="layui-form-item">
                        <label class="layui-form-label">评审结果</label>
                        <div class="layui-input-block">
                            <input type="radio" name="status" value="1" title="未通过" checked>
                            <input type="radio" name="status" value="2" title="通过">
                        </div>
                    </div>
                    <div class="layui-form-item layui-form-text">
                        <label class="layui-form-label">评审意见</label>
                        <div class="layui-input-block">
                            <textarea name="opinion" placeholder="请输入内容" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="review">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'form'], function () {
        var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;

        form.on('submit(review)', function (data) {
            data.field.projectId = ${project.id};
            $.ajax({
                url: "/admin/project/review",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    if (response.code === 0) {
                        layer.msg(response.msg);
                        window.location.href = "/admin/review";
                    } else
                        layer.msg(response.msg);
                }
            });
            return false;
        });
    });
</script>
</body>

</html>