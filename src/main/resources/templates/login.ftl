<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${ctx.contextPath}/static/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${ctx.contextPath}/static/css/login.css">
    <style>

    </style>
</head>
<body>
<header class="loginTop">
    <div class="wrap">
        <a href="">
            Mall商城
        </a>
        <div class="img">
            <img src="${ctx.contextPath}/static/image/top.png" alt="">
        </div>
    </div>
    <div class="loginBanner">
        <div class="loginBody">
            <a href="">热区</a>
            <div class="loginForm">
                <div class="loginHeader">
                    <span class="mobileLogin active">手机号登录</span>
                    <span class="seg"></span>
                    <span class="emailLogin">邮箱登录</span>
                </div>
                <div class="loginInnerBody">
                    <div class="login-phone1">
                        <form action="loginByMobileSubmit" method="post" target="loginBack">
                            <div class="inputbox" style="margin-bottom: 16px">
                                <i class="icon">
                                    <img src="../static/image/mobile.png" alt="">
                                </i>
                                <input  type="text" placeholder="请输入手机号" name="mobile">
                            </div>
                            <div class="inputbox">
                                <i class="icon">
                                    <img src="../static/image/lock.png" alt="">
                                </i>
                                <input  type="password" placeholder="请输入密码" name="password">
                            </div>
                            <!--验证码-->
                            <div class="inputbox yzm">
                                <input type="text" name="yzm" id="yzmCode"  placeholder="请输入验证码">
                            </div>
                            <div class="yzmScr"></div>
                            <div class="login-phone1-err err-hid">
                                <img src="../static/image/err.png" alt="">
                                <div class="err-msg">手机号格式错误</div>
                            </div>
                            <input type="submit" class="f-cb loginBtn" value="登录">
                            <div class="unlogin">
                                <input type="checkbox" >
                                <div class="notLogin">
                                    10天免登录
                                </div>
                            </div>
                            <div class="forgetPwd">
                                忘记密码
                            </div>
                        </form>
                        <iframe name="loginBack" frameborder="0" style="display: none;"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
</body>
<script src="${ctx.contextPath}/static/jquery/jquery-3.2.1.min.js"></script>
<script src="${ctx.contextPath}/static/js/login.js"></script>
<script>
    $(function () {
        createCode();
        $(".yzmScr").click(createCode);
    })
</script>
</html>