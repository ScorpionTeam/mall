<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="../../static/css/component.css">
    <link rel="stylesheet" href="../../static/css/register.css">
</head>
<body>
<div class="g-hd">
    <div class="g-in">
        <a href="http://www.zjhkhl.com" target="_blank">
            <div class="m-logo">红康互联</div>
        </a>
        <div class="m-tr-block">
            已有账号，去<a href="../common/login.html">登录</a>
        </div>
    </div>
</div>
<div class="g-bd">
    <div class="g-tab">Mall 商城</div>
    <div class="g-form none-select">
        <div class="u-input">
            <label for="input-account" class="u-label">帐号：</label>
            <input type="text" name="phone" id="input-account"  class="i-inpt" placeholder="6-18位字母数字组合">
        </div>
        <div class="u-input">
            <label for="input-psd" class="u-label">密码：</label>
            <input type="password" id="input-psd"  class="i-inpt" placeholder="6-18位密码，区分大小写">
        </div>
        <div class="u-input">
            <label for="input-pw2" class="u-label">确认密码：</label>
            <input type="password"  id="input-pw2"  class="i-inpt" placeholder="再次输入密码">
        </div>
        <div class="u-input">
            <label for="input-phone" class="u-label">手机号：</label>
            <input type="text"  id="input-phone"  class="i-inpt" placeholder="11位手机号">
        </div>
        <div class="u-input">
            <label for="input-sms" class="u-label">短信验证码：</label>
            <input type="text"  id="input-sms"  class="i-inpt smsize" placeholder="输入短信验证码">
            <span class="btn-getsms btn-disabled">获取验证码</span>
        </div>
        <div class="u-input">
            <label class="u-label">&nbsp;</label>
            <div class="b-btn btndisabled">注&nbsp&nbsp册</div>
        </div>
    </div>
</div>
</body>
</html>