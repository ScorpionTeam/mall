<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Mall商城运营系统</title>
    <link rel="stylesheet" type="text/css"
          href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/jquery-easyui-1.5.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx.contextPath}/static/css/backstage/backstage-home.css">
    <link rel="shortcut icon" href="${ctx.contextPath}/static/image/bitbug_favicon.ico">
    <link rel="stylesheet" type="text/css"
          href="${ctx.contextPath}/static/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx.contextPath}/static/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <style>

    </style>
    <base id="baseUrl" href="${ctx.contextPath}">
</head>
<body class="easyui-layout">
<div data-options="region:'north',collapsible:false"
     style="height:62px;background: #246dad;color: #fff;font-size: 20px;line-height: 60px;">
    <span style="display: inline-block;width: 240px;text-align: center;">Mall商城运营管理系统</span>
</div>
<div data-options="region:'west',title:'菜单'"  style="width:190px;">
    <div id="aa" class="easyui-accordion menu" style="width:190px;height:100%;">
    </div>
</div>
<div id="mm" class="easyui-menu" style="width: 150px;">
    <div id="mm-tabclose">
        关闭
    </div>
    <div id="mm-tabcloseall">
        全部关闭
    </div>
    <div id="mm-tabcloseother">
        除此之外全部关闭
    </div>
    <div class="menu-sep">
    </div>
    <div id="mm-tabcloseright">
        当前页右侧全部关闭
    </div>
    <div id="mm-tabcloseleft">
        当前页左侧全部关闭
    </div>
    <div class="menu-sep">
    </div>
    <div id="mm-exit">
        退出
    </div>
</div>
<div data-options="region:'center'" style="padding:0 5px;background:#eee;" class="main-content easyui-tabs"></div>
<div id="loginModal"></div>
</body>
<script src="${ctx.contextPath}/static/js/backstage/b-home.js"></script>
<script type="text/javascript">
    $(function(){
        initMenu();
        tabEvent();
    });
</script>
</html>