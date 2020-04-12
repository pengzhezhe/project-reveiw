<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>公告-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/global.css">
</head>

<body>
<#include "/common/header.ftl">
<div class="layui-container fly-marginTop">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${announcement.title}</h1>
                <div class="fly-detail-info">
                    <span class="layui-badge">管理员</span>
                    <span style="font-size: small;color:#999;padding-left: 15px;">${announcement.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                </div>
                <div class="detail-body">
                    <p>
                        ${announcement.content}
                    </p>
                    <hr class="layui-bg-gray">
                </div>
            </div>
        </div>
        <#include "/common/announcement.ftl">
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;

    });
    layui.cache.page = 'jie';
</script>
</body>

</html>