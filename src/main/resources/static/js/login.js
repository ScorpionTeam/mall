/*生成验证码*/
var createCode = function () {
    var codeStandard='';
    var length = 6;
    var arr = [0,1,2,3,4,5,6,7,8,9];
    for(var i =0;i<length;i++){
        codeStandard+=arr[Math.round(Math.random()*9)]
    }
    $(".yzmScr")[0].innerHTML = codeStandard;
};

var validateCode = function () {
    var standard = $(".yzmScr")[0].innerHTML;
    var code = $("#yzmCode").val();
    if(standard===code){
        console.log(true);
        return true;
    }else {
        return false;
    }
};