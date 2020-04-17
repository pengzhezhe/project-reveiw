<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>用户-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body>
<table id="user_table" lay-filter="user_table"></table>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'table', 'layer'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;

        table.render({
            elem: '#user_table',
            height: 500,
            url: '/user',
            page: true,
            toolbar: true,
            parseData: function (res) {
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.data.count, //解析数据长度
                    "data": res.data.data //解析数据列表
                };
            },
            cols: [[ //表头
                {field: 'id', title: 'ID', sort: true, fixed: 'left'},
                {field: 'username', title: '用户名'},
                {field: 'name', title: '姓名'},
                {
                    field: 'sex', title: '性别', sort: true, templet: function (data) {
                        if (data.sex === 0)
                            return "男";
                        else
                            return "女";
                    }
                },
                {field: 'email', title: '邮箱'},
                {
                    field: 'createTime', title: '注册时间', sort: true, templet: function (data) {
                        var date = new Date(data.createTime);
                        var Y = date.getFullYear() + '-';
                        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                        var D = date.getDate() + ' ';
                        var h = date.getHours() + ':';
                        var m = date.getMinutes() + ':';
                        var s = date.getSeconds();
                        return Y + M + D + h + m + s;
                    }
                },
                {title: '操作', fixed: 'right', align: 'center', toolbar: '#barDemo'}
            ]]
        });

        table.on('tool(user_table)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'edit') {
                layer.msg(data.id);
                var index = layer.open({
                    title: ['修改信息'],
                    type: 2,
                    anim: 0,
                    skin: 'layui-layer-molv',
                    area: ['50%', '70%'],
                    content: '/admin/user/update/' + data.id,
                    end: function () {
                        table.reload('user_table');
                    }
                });
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url: "/user/" + data.id,
                        method: "DELETE",
                        dataType: "json",
                        success: function (response) {
                            if (response.code === 0)
                                obj.del();
                            layer.msg(response.msg);
                        }
                    });
                });
            }
        });
    });
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</body>
</html>