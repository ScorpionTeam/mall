<html>
<title>Mall商城运营系统</title>
<link rel="stylesheet" type="text/css"
      href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
<script type="text/javascript" src="${ctx.contextPath}/static/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<body>
<table class="easyui-datagrid" style="width:100%;height:430px;"
       data-options="rownumbers:true,singleSelect:true,url:'datagrid_data1.json',method:'get',toolbar:'#tb'">
    <thead>
    <tr>
        <th data-options="field:'itemid',width:80">ID</th>
        <th data-options="field:'productid',width:100">姓名</th>
        <th data-options="field:'listprice',width:80,align:'right'">手机</th>
        <th data-options="field:'unitcost',width:80,align:'right'">邮箱</th>
        <th data-options="field:'unitcost',width:80,align:'right'">性别</th>
        <th data-options="field:'unitcost',width:80,align:'right'">年龄</th>
        <th data-options="field:'unitcost',width:80,align:'right'">地址</th>
        <th data-options="field:'unitcost',width:80,align:'right'">邮箱</th>
        <th data-options="field:'unitcost',width:80,align:'right'">实名认证</th>
        <th data-options="field:'attr1',width:240">身份证号</th>
        <th data-options="field:'status',width:60,align:'center'">Status</th>
    </tr>
    </thead>
</table>
<div id="tb" style="padding:2px 5px;">
    注册日期: <input class="easyui-datebox" style="width:110px">
    至: <input class="easyui-datebox" style="width:110px">
    性别:
    <select class="easyui-combobox" panelHeight="auto" style="width:100px">
        <option value="java">男</option>
        <option value="c">女</option>
    </select>
    <a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
</div>
<div style="margin:20px 0;"></div>
<div class="easyui-panel">
    <div class="easyui-pagination" data-options="total:114"></div>
</div>
</body>
</html>