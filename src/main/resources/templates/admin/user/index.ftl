<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>项目-项目评审系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css"/>
</head>
<body>
<table id="user_table" lay-filter="user_table"></table>
<script src="/layui/layui.js"></script>
<script>
    layui.use(['element', 'table'], function () {
        var element = layui.element;
        var $ = layui.jquery;
        var table = layui.table;

        table.render({
            elem: '#user_table',
            height: 500,
            url: '/user',
            page: true,
            parseData: function (res) {
                console.log(res);
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
                {field: 'sex', title: '性别', sort: true},
                {field: 'email', title: '邮箱'},
                {field: 'createTime', title: '注册时间', sort: true}
            ]]
        });
    });
</script>

</body>
</html>