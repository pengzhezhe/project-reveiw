<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>项目-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/css/global.css"/>
</head>

<body>
<#include "/common/header.ftl">
<div class="layui-container fly-marginTop">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel">
                <div class="layui-tab layui-tab-brief" style="margin-top: 0;" lay-filter="list">
                    <ul class="layui-tab-title">
                        <li <#if index==0>class="layui-this"</#if>>全部</li>
                        <li <#if index==1>class="layui-this"</#if>>审核中</li>
                        <li <#if index==2>class="layui-this"</#if>>已评审</li>
                    </ul>
                    <div class="layui-tab-content">
                        <#if index==0>
                            <div class="layui-tab-item layui-show">
                                <ul class="fly-list">
                                    <#list projects as project>
                                        <li>
                                            <h2>
                                                <a href="/project/${project.id}">${project.name}</a>
                                            </h2>
                                            <div class="fly-list-info">
                                                <a href="" link>
                                                    <cite>${project.userName}</cite>
                                                </a>
                                                <span>${project.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                                <#if project.status==1>
                                                    <span class="layui-badge fly-badge-accept layui-hide-xs">已评审</span>
                                                <#else>
                                                    <span class="layui-badge fly-badge-default layui-hide-xs">审核中</span>
                                                </#if>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                            <div class="layui-tab-item"></div>
                            <div class="layui-tab-item"></div>
                        </#if>
                        <#if index==1>
                            <div class="layui-tab-item"></div>
                            <div class="layui-tab-item layui-show">
                                <ul class="fly-list">
                                    <#list projects as project>
                                        <li>
                                            <h2>
                                                <a href="/project/${project.id}">${project.name}</a>
                                            </h2>
                                            <div class="fly-list-info">
                                                <a href="" link>
                                                    <cite>${project.userName}</cite>
                                                </a>
                                                <span>${project.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                                <#if project.status==1>
                                                    <span class="layui-badge fly-badge-accept layui-hide-xs">已评审</span>
                                                <#else>
                                                    <span class="layui-badge fly-badge-default layui-hide-xs">审核中</span>
                                                </#if>
                                            </div>
                                        </li>
                                    </#list>
                                </ul>
                            </div>
                            <div class="layui-tab-item"></div>
                        </#if>
                        <#if index==2>
                            <div class="layui-tab-item"></div>
                            <div class="layui-tab-item"></div>
                            <div class="layui-tab-item layui-show">
                                <ul class="fly-list">
                                    <#list projects as project>
                                        <li>
                                            <h2>
                                                <a href="/project/${project.id}">${project.name}</a>
                                            </h2>
                                            <div class="fly-list-info">
                                                <a href="" link>
                                                    <cite>${project.userName}</cite>
                                                </a>
                                                <span>${project.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                                                <#if project.status==1>
                                                    <span class="layui-badge fly-badge-accept layui-hide-xs">已评审</span>
                                                <#else>
                                                    <span class="layui-badge fly-badge-default layui-hide-xs">审核中</span>
                                                </#if>
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
        <#include "/common/announcement.ftl">
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    function getQueryVariable(variable) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == variable) {
                return pair[1];
            }
        }
        return false;
    }

    layui.use(['element', 'laypage'], function () {
        var element = layui.element;
        var laypage = layui.laypage;
        element.on('tab(list)', function (data) {
            if (data.index !== 0)
                window.location.href = "/project?type=" + data.index;
            else
                window.location.href = "/project";
        });

        laypage.render({
            elem: 'page',
            limit: 9,
            curr: ${pageNum},
            count: ${count},
            jump: function (obj, first) {
                var page = obj.curr;
                if (!first) {
                    var type = getQueryVariable("type");
                    if (type != false)
                        window.location.href = "/project?type=" + type + "&page=" + page;
                    else
                        window.location.href = "/project?page=" + page;
                }
            }
        });
    });

</script>
</body>

</html>