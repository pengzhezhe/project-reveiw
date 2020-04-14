<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登录</title>
    <link rel="stylesheet" href="/layui/css/layui.css"/>
    <link rel="stylesheet" href="/css/login.css"/>
</head>
<body>
<div class="m-login-bg">
    <div class="m-login">
        <h3>评审系统登录</h3>
        <div class="m-login-warp">
            <form class="layui-form">
                <div class="layui-form-item">
                    <input type="text" name="username" required lay-verify="required" placeholder="用户名"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="password" name="password" required lay-verify="required" placeholder="密码"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item m-login-btn">
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">登录</button>
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

        form.on('submit(login)', function (data) {
            $.ajax({
                url: "/login",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    console.log(response);
                    if (response.code === 0){
                        layer.msg(response.msg);
                        window.location.href = "/project";
                    }
                    else
                        layer.msg(response.msg);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>