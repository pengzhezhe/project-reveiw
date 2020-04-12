<div class="layui-col-md4">
    <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title">公告</dt>
        <#list announcements as announcement>
            <dd>
                <a href="/announcement/${announcement.id}">${announcement.title}</a>
            </dd>
        </#list>
    </dl>
</div>