<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Mall商城运营系统</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/metro/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/css/backstage/backstage-home.css">
    <link rel="shortcut icon" href="${ctx.contextPath}/static/image/bitbug_favicon.ico">
    <link rel="stylesheet" type="text/css"
          href="${ctx.contextPath}/static/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <style>

    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north',collapsible:false"
     style="height:60px;background: #246dad;color: #fff;font-size: 20px;line-height: 60px;">
    <span style="display: inline-block;width: 240px;text-align: center;">Mall商城运营管理系统</span>
</div>
<div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
    <div id="aa" class="easyui-accordion" style="width:194px;height:100%;">
        <div title="会员管理" style="overflow:auto;padding:10px;">
            <ul style="list-style: none;margin: 0;padding: 0;">
                <li>
                    <div><a href="#" onclick="addTab('会员列表','/mall/user/user-list')">会员列表</a></div>
                </li>
                <li>
                    <div>会员审核</div>
                </li>
            </ul>
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
<div data-options="region:'center'" style="padding:5px;background:#eee;" class="main-content easyui-tabs">

</div>
</body>
<script type="text/javascript">
    function addTab(title, url) {
        if ($('.main-content').tabs('exists', title)) {
            $('.main-content').tabs('select', title);
        } else {
            var content = '<iframe scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:100%;"></iframe>';
            $('.main-content').tabs('add', {
                title: title,
                content: content,
                closeable: true
            })
        }
    }
</script>
</html>