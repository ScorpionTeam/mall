<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>mall商城,专注正品!</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="../static/css/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../static/css/home.css">
    <link rel="stylesheet" href="../static/css/component.css">
</head>
<body>
<nav class="topNav">
    <div class="topNavWrap">
        <div class="topNavLeft">
            <span>mall欢迎您</span>
            <a href="" class="login">登录</a>
            <span class="step">|</span>
            <a href="" class="register">免费注册</a>
            <a href="" class="mobile">
                <i class="fa fa-mobile"></i>
                mall手机app客户端
            </a>
            <div class="navRight">
                <a href="" class="signIn">
                    <i class="fa fa-flag"></i>
                    每日签到
                </a>
                <span class="step">|</span>
                <a href="">我的订单</a>
                <span class="step">|</span>
                <a href="">个人中心</a>
                <span class="step">|</span>
                <a href="">
                    客户服务
                    <i class="fa fa-caret-down"></i>
                </a>
            </div>
        </div>
    </div>
</nav>
<header class="searchHeader">
    <div class="searchWrap">
        <div class="logo">Mall商城</div>
        <div class="searchBox">
            <div class="search">
                <div class="wrap">
                    <input type="text" placeholder="莆田A货">
                    <i class="fa fa-search"></i>
                </div>
            </div>
        </div>
        <a href="" class="shopCart">
            <i class="fa fa-shopping-cart"></i>
            <span>购物车</span>
        </a>
    </div>
</header>
<article>
    <#--<div class="bannerBox">
        banner
    </div>-->
        <div class="img-banner">
            <div class="img-box" onmouseover="showBtn()" onmouseout="hiddenBtn()">
                <ul id="show_pic">
                    <li style="opacity: 1">
                        <a href="" class="item" target="_blank">
                            <img class="showImg" src="http://haitao.nos.netease.com/GTJXyZXUOlgbS0yiCGRuqBOa-uapCE4Lo3G2G3gzeWaJK3tT1709252026_1920_400.jpg?imageView&thumbnail=1920x0&quality=90" alt="">
                        </a>
                    </li>
                    <li style="opacity: 0">
                        <a href="" class="item" target="_blank">
                            <img class="showImg" src="http://haitao.nos.netease.com/UDGcdLHfBtUgM7fH1920X400PCao1INET1709191440_1920_400.jpg?imageView&thumbnail=1920x0&quality=90" alt="">
                        </a>
                    </li>
                    <li  style="opacity: 0">
                        <a href="" class="item" target="_blank">
                            <img class="showImg" src="http://haitao.nos.netease.com/W1vIsJG1UiXXXn8wPCT1709251919_1920_400.jpg?imageView&thumbnail=1920x0&quality=90" alt="">
                        </a>
                    </li>
                </ul>
                <table class="img_pagebox" cellspacing="0" border="0">
                    <tr class="img-page">
                        <td id="icon_num" class="page-box">
                        <span class="w-slbtn">
                            <a class="idxicon " href="">
                                <span class="num active">1</span>
                            </a>
                            <a class="idxicon" href="">
                                <span class="num">2</span>
                            </a>
                            <a class="idxicon" href="">
                                <span class="num">3</span>
                            </a>
                            <a class="idxicon" href="">
                                <span class="num">4</span>
                            </a>
                            <a class="idxicon" href="">
                                <span class="num">5</span>
                            </a>
                            <a class="idxicon" href="">
                                <span class="num">6</span>
                            </a>
                        </span>
                        </td>
                    </tr>
                </table>
                <div class="control none-select">
                    <div>
                        <a class="prev ani con" ></a>
                        <a class="next ani con" ></a>
                    </div>
                </div>
            </div>
        </div>
</article>
</body>
<script src="../static/jquery/jquery-3.2.1.min.js"></script>
<script>
    var currentIndex=0;
    $(function () {
        skipPicture();
        $(".prev").click(prevPic);
        $(".next").click(nextPic)
    })
    /*显示、隐藏轮播前进后退*/
    var showBtn = function () {
        var btn = $(".ani")
        btn.show();
    };
    var hiddenBtn = function () {
        var btn = $(".ani")
        btn.hide()
    };
    /*前后退按钮效果*/
    var prevPic = function(){
        if(currentIndex==0){
            currentIndex=6
        }
        var numIndex = currentIndex -1;
        var num = $('.num')
        skipPic(numIndex)
        for(var i =0 ;i<num.length;i++){
            i!= numIndex?$(num).eq(i).removeClass("active"):num.eq(i).addClass("active");
        }
    }
    var nextPic = function(){
        if(currentIndex ==5){
            currentIndex =-1;
        }
        var numIndex = currentIndex +1;
        var num = $('.num')
        skipPic(numIndex)
        for(var i =0 ;i<num.length;i++){
            i!= numIndex?$(num).eq(i).removeClass("active"):num.eq(i).addClass("active");
        }
    }
    /*轮播根据页码跳转图片*/
    var skipPicture = function () {
        //获取页码dom
        var num = $('.num')
        //页码跳转
        num.hover(function () {
            var numIndex = $(".num").index(this);
            skipPic(numIndex)
            for(var i =0 ;i<num.length;i++){
                i!= numIndex?$(num).eq(i).removeClass("active"):$(this).addClass("active");;
            }
        })
    }
    var skipPic =function (index) {
        var picList =$("#show_pic li")
        if(currentIndex==index)return;
        picList.eq(index).fadeTo("fast",1);
        picList.eq(currentIndex).fadeTo("fast",0);
        currentIndex = index;
    }
</script>
</html>