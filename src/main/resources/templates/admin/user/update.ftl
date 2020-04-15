<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>帐号设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>

<body>
<div class="layui-container" style="padding: 10px">
    <form class="layui-form" lay-filter="basic">
        <div class="layui-form-item">
            <label for="username" class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" id="username" name="username" required autocomplete="off" value=""
                       class="layui-input" readonly>
            </div>
        </div>
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
            <div class="layui-input-inline">
                <button class="layui-btn" lay-submit lay-filter="updateUser">确认修改</button>
            </div>
        </div>
    </form>
</div>

<script src="/layui/layui.js"></script>
<script>
    layui.use(['form', 'layer', 'element'], function () {
        var $ = layui.jquery;
        var form = layui.form;
        var layer = layui.layer;
        var element = layui.element;

        form.val("basic", {
            "username": "${user.username}",
            "email": "${user.email}",
            "name": "${user.name}",
            "sex": ${user.sex}
        });

        form.on('submit(updateUser)', function (data) {
            $.ajax({
                url: "/user",
                method: "PUT",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data.field),
                success: function (response) {
                    layer.msg(response.msg, {time: 1000}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            });
            return false;
        });
    });
</script>

</body>

</html>