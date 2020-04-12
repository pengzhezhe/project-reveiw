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
                        <span class="layui-badge" style="background-color: #999;">未通过</span>
                    <#elseif project.status==2>
                        <span class="layui-badge" style="background-color: #5FB878;">通过</span>
                    <#else>
                        <span class="layui-badge" style="background-color: #FF5722;">审核中</span>
                    </#if>
                    <span style="font-size: small;color:#999;padding-left: 15px;">${project.createTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                </div>
                <div class="detail-body">
                    <p>${project.introduction}</p>
                    <hr class="layui-bg-gray">
                    <#list attachments as attachment>
                        <a href="/attachment/download/${attachment.id}"
                           style="font-size: small;">${attachment.originalName}</a>
                        <br>
                    </#list>
                    <br>
                </div>
            </div>

            <div class="fly-panel" style="padding: 5px 15px;">
                <fieldset class="layui-elem-field layui-field-title">
                    <legend style="text-align: center">评审意见</legend>
                    <#if project.status!=0>
                        <div class="layui-field-box">
                            <p>${project.opinion}</p>
                            <div class="fly-list-info" style="padding-top: 10px;font-size: smaller;">
                                <cite style="color: #1E9FFF;">管理员</cite>
                                <span class="detail-hits">${project.reviewTime?string("yyyy-MM-dd HH:mm:ss")}</span>
                            </div>
                        </div>
                    </#if>
                </fieldset>
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
</script>
</body>

</html>