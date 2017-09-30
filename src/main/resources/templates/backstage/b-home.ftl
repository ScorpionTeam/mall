<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Mall商城运营系统</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/css/backstage/backstage-home.css">
    <link rel="shortcut icon" href="${ctx.contextPath}/static/image/bitbug_favicon.ico">
    <link rel="stylesheet" type="text/css"
          href="${ctx.contextPath}/static/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',collapsible:false" style="height:60px;"></div>
<div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
    <div id="aa" class="easyui-accordion" style="width:194px;height:100%;">
        <div title="会员管理" style="overflow:auto;padding:10px;">
            <h3 style="color:#0099FF;">Accordion for jQuery</h3>
            <p>Accordion is a part of easyui framework for jQuery.
                It lets you define your accordion component on web page more easily.</p>
        </div>
        <div title="商家管理" style="padding:10px;">
            content2
        </div>
        <div title="平台管理">
            content3
        </div>
        <div title="活动">
            content3
        </div>
        <div title="红包">
            content3
        </div>
    </div>
</div>
<div data-options="region:'center',title:'内容'" style="padding:5px;background:#eee;"></div>
</body>
</html>