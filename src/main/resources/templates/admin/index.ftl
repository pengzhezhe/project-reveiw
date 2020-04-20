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
    <div class="layui-header">
        <div class="layui-logo">项目评审</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <#if Session.username??>
                        ${Session.username}
                    <#else>
                        username
                    </#if>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/admin/update" target="content_frame">基本资料</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/user/add" target="content_frame">添加用户</a></dd>
                        <dd><a href="/admin/user" target="content_frame">用户列表</a></dd>
                        <dd><a href="/admin/index" target="content_frame">管理员列表</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">项目管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/project" target="content_frame">项目列表</a></dd>
                        <dd><a href="/admin/review" target="content_frame">项目评审</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">公告管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/admin/announcement/add" target="content_frame">发布公告</a></dd>
                        <dd><a href="/admin/announcement" target="content_frame">公告列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body" style="bottom: 0">
        <iframe id="content_frame" name="content_frame" frameborder="0" src="/admin/user"></iframe>
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