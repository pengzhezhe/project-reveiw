<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/css/register.css"/>
</head>
<body>
<div class="m-login-bg">
    <div class="m-login">
        <h3>用户注册</h3>
        <div class="m-login-warp">
            <form class="layui-form">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="username" required autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="password" class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" name="password" required autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="email" class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="text" name="email" required lay-verify="email" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" name="name" required lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">性别</label>
                    <div class="layui-input-inline">
                        <input type="radio" name="sex" value="0" title="男" checked>
                        <input type="radio" name="sex" value="1" title="女">
                    </div>
                </div>
                <div class="layui-form-item m-login-btn">
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="register">提交</button>
                    </div>
                    <div class="layui-inline">
                        <button type="reset" class="layui-btn layui-btn-primary">清空</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;

        form.on('submit(register)', function (data) {
            $.ajax({
                url: "/register",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    console.log(response);
                    if (response.code === 0) {
                        layer.msg(response.msg, {time: 1000}, function () {
                            window.location.href = "/login";
                        });
                    } else
                        layer.msg(response.msg);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>