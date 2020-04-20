<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>公告-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body>
<table id="announcement_table" lay-filter="announcement_table"></table>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'table', 'layer'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;

        table.render({
            elem: '#announcement_table',
            url: '/api/announcement',
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
                {
                    field: 'title', title: '公告标题', templet: function (data) {
                        return "<a href='/announcement/" + data.id + "' target='_blank'>" + data.title + "</a>";
                    }
                },
                {field: 'content', title: '公告内容'},
                {
                    field: 'createTime', title: '发布时间', sort: true, templet: function (data) {
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

        table.on('tool(announcement_table)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'edit') {
                var index = layer.open({
                    title: ['修改信息'],
                    type: 2,
                    anim: 0,
                    skin: 'layui-layer-molv',
                    area: ['50%', '70%'],
                    content: '/admin/announcement/update/' + data.id,
                    end: function () {
                        table.reload('announcement_table');
                    }
                });
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    layer.close(index);
                    $.ajax({
                        url: "/api/announcement/" + data.id,
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