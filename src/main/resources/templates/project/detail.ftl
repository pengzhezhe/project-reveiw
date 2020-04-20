<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>项目详情-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/css/global.css"/>
</head>

<body>
<#include "/common/header.ftl">
<div class="layui-container fly-marginTop">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${project.name}</h1>
                <div class="fly-detail-info">
                    <#if project.status==1>
                        <span class="layui-badge layui-bg-blue">已评审</span>
                    <#else>
                        <span class="layui-badge">审核中</span>
                    </#if>
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
            <#if project.reviewStatus!=0>
                <div class="fly-panel" style="padding: 5px 15px;">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend style="text-align: center">评审结果</legend>
                    </fieldset>
                    <blockquote class="layui-elem-quote">
                        <p>${project.opinion}</p>
                        <div class="fly-list-info" style="padding-top: 10px;font-size: smaller;">
                            <#if project.reviewStatus==1>
                                <span style="padding-right: 6px;" class="layui-badge layui-bg-black">未通过</span>
                            <#elseif project.reviewStatus==2>
                                <span style="padding-right: 6px;" class="layui-badge layui-bg-green">通过</span>
                            </#if>
                            <span class="detail-hits">${project.reviewTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                        </div>
                    </blockquote>
                </div>
            <#else>
                <div class="fly-panel" style="padding: 5px 15px;">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend style="text-align: center">评审结果</legend>
                    </fieldset>
                    <blockquote style="margin-top: 10px" class="layui-elem-quote">
                        评审结果正在处理中，请耐心等待！
                    </blockquote>
                </div>
            </#if>
        </div>
        <#include "/common/announcement.ftl">
    </div>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>

</html>