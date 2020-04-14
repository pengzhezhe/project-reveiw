<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>项目-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <style type="text/css">
        #content_frame {
            position: absolute;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "/admin/common/header.ftl">
    <#include "/admin/common/nav.ftl">
    <div class="layui-body" style="bottom: 0">
        <iframe id="content_frame" name="content_frame" frameborder="0"></iframe>
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;
        var $ = layui.jquery;
    });
</script>

</body>
</html>