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
        <div class="layui-col-md10">
            <div class="fly-panel">
                <div class="layui-tab layui-tab-brief" style="margin-top: 0;" lay-filter="list">
                    <ul class="layui-tab-title">
                        <li <#if index==0>class="layui-this"</#if>>最新</li>
                        <li <#if index==1>class="layui-this"</#if>>全部</li>
                    </ul>
                    <div class="layui-tab-content">
                        <#if index==0>
                            <div class="layui-tab-item layui-show">
                                <ul class="fly-list">
                                    <#list announcements as announcement>
                                        <li>
                                            <h2>
                                                <a href="/announcement/${announcement.id}">${announcement.title}</a>
                                            </h2>
                                            <div class="fly-list-info">
                                                <span>${announcement.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                            <div class="layui-tab-item"></div>
                        </#if>
                        <#if index==1>
                            <div class="layui-tab-item"></div>
                            <div class="layui-tab-item layui-show">
                                <ul class="fly-list">
                                    <#list announcements as announcement>
                                        <li>
                                            <h2>
                                                <a href="/announcement/${announcement.id}">${announcement.title}</a>
                                            </h2>
                                            <div class="fly-list-info">
                                                <span>${announcement.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
            <div id="page"></div>
        </div>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'laypage'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        element.on('tab(list)', function (data) {
            if (data.index === 0) {
                window.location.href = "/announcement"
            } else {
                window.location.href = "/announcement?type=1";
            }
        });
        laypage.render({
            elem: 'page',
            limit: 9,
            curr: ${pageNum},
            count: ${count},
            jump: function (obj, first) {
                var page = obj.curr;
                if (!first)
                    window.location.href = "/announcement?type=" + ${index} +"&page=" + page;
            }
        });

    });
</script>
</body>

</html>