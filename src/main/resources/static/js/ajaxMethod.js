/*
 *config:{}请求配置对象：
 *  type:是否带参数。
 *      0:请求参数以对象形式传入,
 *      1:请求参数写在url中
 * url:请求地址，
 * data:请求数据
 *success:成功回调函数,
 * error:失败回调函数
 * */
var ajaxPost = function (config) {
    if(config.type==0){
        $.ajax({
            url:config.url,
            type:"POST",
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(config.data),
            success:function (res) {
                config.success(res);
            },
            error:function (err) {
                config.error(err);
            }
        })
    }else {
        $.ajax({
            url:config.url,
            type:"POST",
            dataType:"json",
            success:function (res) {
                config.success(res);
            },
            error:function (err) {
                config.error(err);
            }
        })
    }
}

var ajaxGet = function (config) {
    $.ajax({
        url:config.url,
        type:"GET",
        dataType:"json",
        success:function (res) {
            config.success(res);
        },
        error:function (err) {
            config.error(err)
        }
    })
}