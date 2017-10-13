<div id="memberList"></div>
<base id="baseUrl" href="${ctx.contextPath}">
<script>
    var memberCloumn = [
        [
            {title: "ID", field: "id", align: "center", width: 80, halign: "center", hidden: true},
            {title: "姓名", field: "name", align: "center", width: 80, halign: "center"},
            {title: "年龄", field: "age", align: "center", width: 80, halign: "center"},
            {title: "手机号", field: "mobile", align: "center", width: 80, halign: "center"},
            {title: "性别", field: "sex", align: "center", width: 80, halign: "center"},
            {title: "身份证号", field: "certificateId", align: "center", width: 80, halign: "center"},
        ]
    ];
    var baseUrl = document.getElementById("baseUrl").href;
    $("#memberList").datagrid({
        url: baseUrl + "/static/mockMember.json",
        method: "get",
        striped: true,
        toolbar: [{
            iconCls: 'icon-add',
            text: '新增',
            handler: function () {
                $("#loginModal").dialog({
                    title: "登录",
                    width: 400,
                    height: 200,
                    href: "./login.html"
                })
            }
        }, '-', {
            iconCls: 'icon-help',
            text: '帮助',
            handler: function () {
                alert('帮助按钮')
            }
        }],
        columns: memberCloumn,
        fitColumns: true,
        pagination: true
    });
</script>