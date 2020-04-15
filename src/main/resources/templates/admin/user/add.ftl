<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>添加用户</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>

<body style="padding: 10px">
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加用户</legend>
</fieldset>
<form class="layui-form" lay-filter="basic">
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
            <input type="radio" name="sex" value="0" checked title="男">
            <input type="radio" name="sex" value="1" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="addUser">添加</button>
        </div>
    </div>
</form>

<script src="/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer', 'element'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var layer = layui.layer;
        var element = layui.element;

        form.on('submit(addUser)', function (data) {
            $.ajax({
                url: "/user",
                method: "POST",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    layer.msg(response.msg, {time: 1000}, function () {
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