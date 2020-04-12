<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <ul class="layui-nav fly-nav">
            <li class="layui-nav-item">
                <a href="/project">我的项目</a>
            </li>
            <li class="layui-nav-item">
                <a href="/project/add">发布项目</a>
            </li>
            <li class="layui-nav-item">
                <a href="/announcement">公告</a>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:">
                    <#if Session.username??>
                        ${Session.username}
                    <#else>
                        username
                    </#if>
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="/user/update">个人信息</a></dd>
                    <dd><a href="/logout">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>
</div>