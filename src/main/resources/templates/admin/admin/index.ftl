<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>管理员-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body>
<table id="admin_table" lay-filter="admin_table"></table>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'table', 'layer'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        var table = layui.table;
        var layer = layui.layer;

        table.render({
            elem: '#admin_table',
            url: '/admin/list',
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
            ]]
        });

    });
</script>
</body>
</html>