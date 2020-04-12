<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>帐号设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/css/global.css">
</head>

<body>
<#include "/common/header.ftl">
<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li class="layui-this">基本资料</li>
                <li>修改密码</li>
            </ul>
            <div class="layui-tab-content" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane" lay-filter="basic">
                        <form method="post">
                            <div class="layui-form-item">
                                <label for="email" class="layui-form-label">邮箱</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="email" name="email" required lay-verify="email"
                                           autocomplete="off" value="" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">姓名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="name" name="name" required lay-verify="required"
                                           autocomplete="off" value="" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-inline">
                                    <input type="radio" name="sex" value="0" checked title="男">
                                    <input type="radio" name="sex" value="1" title="女">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-submit lay-filter="updateUser">确认修改</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="layui-tab-item">
                    <div class="layui-form layui-form-pane" lay-filter="passwordForm">
                        <form method="post">
                            <div class="layui-form-item">
                                <label class="layui-form-label">原密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="password" name="password" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">新密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="new_password" name="new_password" required
                                           lay-verify="required" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <button class="layui-btn" lay-submit lay-filter="changePwd">修改</button>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>
</div>


<script src="/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer', 'element'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var layer = layui.layer;
        var element = layui.element;

        form.val("basic", {
            "email": "${user.email}",
            "name": "${user.name}",
            "sex": ${user.sex}
        });

        form.on('submit(updateUser)', function (data) {
            console.log(data.field);
            $.ajax({
                url: "/user/update",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    layer.msg(response.msg, function () {
                        window.location.reload();
                    });
                }
            });
            return false;
        });
        form.on('submit(changePwd)', function (data) {
            console.log(data.field);
            $.ajax({
                url: "/user/update/password",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    layer.msg(response.msg, function () {
                        window.location.reload();
                    });
                }
            });
            return false;
        });

    });
</script>

</body>

</html>