/*轮播*/
var currentIndex=0;
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
    picList.eq(index).fadeTo("normal",1);
    picList.eq(currentIndex).fadeTo("normal",0);
    currentIndex = index;
}
/*轮播发放*/
var scrollBanner =function () {
    skipPicture();
    $(".prev").click(prevPic);
    $(".next").click(nextPic)
    /*轮播*/
    setInterval(function () {
        nextPic();
    },3000);
}/**
 * Created by admin1 on 2017/9/26.
 */
/*类目*/
var catTmpHover= function () {
    var myCard = $(".m-ctgcard");
    $(".catli").hover(function () {
        var index = $(".catli").index(this);
        myCard.eq(index).show();
    },function () {
        var index = $(".catli").index(this);
        myCard.eq(index).hide();
    })
}
/*计时盒子*/
var timeBox = function (aimTime) {
    var time = setInterval(function () {
        var hour ,minute,second;
        var aimDate = new Date(aimTime);
        var now = new Date();
        //时间到去除计时器
        if(aimDate.getTime()-now.getTime()<=0){
            clearInterval(time);
        }
        var $i = $('.timebox i');
        hour= aimDate.getHours()-now.getHours();
        hour=hour>0?hour:hour+23;
        if(hour>=10){
            $i[0].innerHTML = String(hour).substring(0,1);
            $i[1].innerHTML = String(hour).substring(1,2);
        }else {
            $i[0].innerHTML = 0;
            $i[1].innerHTML = hour;
        }
        minute = aimDate.getMinutes() - now.getMinutes();
        minute = minute>0?minute:minute+60;
        if(minute>=10){
            $i[2].innerHTML = String(minute).substring(0,1);
            $i[3].innerHTML = String(minute).substring(1,2);
        }else {
            $i[2].innerHTML = 0;
            $i[3].innerHTML = minute;
        }
        second = aimDate.getSeconds() - now.getSeconds();
        second=second>0?second:second+60;
        if(second>=10){
            $i[4].innerHTML = String(second).substring(0,1);
            $i[5].innerHTML = String(second).substring(1,2);
        }else {
            $i[4].innerHTML = 0;
            $i[5].innerHTML = second;
        }
    },1000)
}

/*固定搜索栏*/
var tabSearchFixed = function () {
    $(window).scroll(function (val) {
        var height = $(this).scrollTop();
        height<=40?$(".topTabBoxFixed").addClass("con"):$(".topTabBoxFixed").removeClass("con");
    })
}