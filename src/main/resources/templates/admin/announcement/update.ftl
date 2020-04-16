<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>公告更新</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body style="padding: 10px">
<form class="layui-form" lay-filter="basic">
    <div class="layui-form-item">
        <label class="layui-form-label">公告ID</label>
        <div class="layui-input-inline">
            <input type="text" name="id" required autocomplete="off" class="layui-input" readonly>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">标题</label>
        <div class="layui-input-inline">
            <input type="text" name="title" required autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-inline">
            <textarea name="content" required autocomplete="off" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="update">确认修改</button>
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

        form.val("basic", {
            "id": "${announcement.id}",
            "title": "${announcement.title}",
            "content": "${announcement.content}",
        });

        form.on('submit(update)', function (data) {
            $.ajax({
                url: "/api/announcement",
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