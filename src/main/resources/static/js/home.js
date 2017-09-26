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
