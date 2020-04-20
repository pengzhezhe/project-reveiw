<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>项目-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body>
<table id="project_table" lay-filter="project_table"></table>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'table', 'layer'], function () {
        var $ = layui.jquery;
        var element = layui.element;
        var table = layui.table;
        var layer = layui.layer;

        table.render({
            elem: '#project_table',
            url: '/api/project?type=0',
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
                    field: 'name', title: '项目名', templet: function (data) {
                        return "<a href='/project/" + data.id + "' target='_blank'>" + data.name + "</a>";
                    }
                },
                {field: 'introduction', title: '项目简介'},
                {field: 'userName', title: '项目发起人姓名'},
                {field: 'userId', title: '项目发起人id'},
                {
                    field: 'status', title: '项目状态', sort: true, templet: function (data) {
                        if (data.status === 0)
                            return "审核中";
                        else if (data.status === 1)
                            return "已审核";
                    }
                },
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

        table.on('tool(project_table)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;

            if (layEvent === 'review') {
                window.location.href = '/admin/project/review/' + data.id;
            }
        });
    });
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="review">评审</a>
</script>
</body>
</html>