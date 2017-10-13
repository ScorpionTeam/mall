/**
 * Created by admin1 on 2017/10/11.
 */

//增加标签页
function addTab(title, url) {
    var tabContain = $('.main-content');
    if (tabContain.tabs('exists', title)) {
        tabContain.tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0" src="' + './b-user.html' + '" style="width:100%;height:100%;"></iframe>';
        tabContain.tabs('add', {
            title: title,
            /*content: content,*/
            href: url,
            closable: true,
            loadingMessage: '正在加载中......'
        });
        tabClose();
        tabMenuHandler();
    }
}

//初始化左边菜单栏
var initMenu = function () {
    var contain = $("#aa");
    var base = document.getElementById("baseUrl").href;
    $.ajax({
        url: base + '/static/mockJson.json',
        type: 'Get',
        success: function (res) {
            for (var i = 0; i < res.length; i++) {
                var content = '';
                for (var j = 0; j < res[i].leaf.length; j++) {
                    content += '<p class="submenu" data-url="' + base + '/' + res[i].leaf[j].url + '">' + res[i].leaf[j].title + '</p>'
                }
                contain.accordion('add', {
                    title: res[i].title,
                    content: content,
                    animate: true,
                    selected: false
                });
            }
            subMenuClickHandler();
        },
        error: function (err) {
            console.log(err)
        }
    });
};

//子按钮添加点击事件
var subMenuClickHandler = function () {
    $(".submenu").click(function () {
        $(".submenu").removeClass("selected");//给所有子按钮移除选中样式
        $(this).addClass("selected");//给当前点击子按钮增加选中样式
        addTab($(this).text(), $(this).data("url"));
    })
};

/*Tab事件*/
//双击关闭当前tab页
var tabClose = function () {
    $('.tabs-inner').dblclick(function () {
        var subtitle = $(this).children(".tabs-closable").text();
        $('.main-content').tabs('close', subtitle);
    });
};

//给所有Tab标签绑定事件
var tabMenuHandler = function () {
    //绑定右键点击事件
    $('.tabs-inner').bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY,
            hideOnUnhover: false
        });
        //获取当前的tab的title
        var subtitle = $(this).children(".tabs-closable").text();
        $('#mm').data("currtab", subtitle);
        $('.main-content').tabs('select', subtitle);
        return false;//阻止浏览器默认的右键弹出行为
    });

};

//Tab按钮页面绑定事件
var tabEvent = function () {
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data('currtab');
        $('.main-content').tabs('close', currtab_title);
    });

    //关闭所有
    $("#mm-tabcloseall").click(function () {
        var tab_list = $('.tabs-inner');
        for (var i = 0; i < tab_list.length; i++) {
            var tit = tab_list.eq(i).text();
            $('.main-content').tabs('close', tit);
        }
    });

    //关闭左侧所有
    $("#mm-tabcloseleft").click(function () {
        var tab = $(".main-content").tabs('getSelected');
        var index = $(".main-content").tabs('getTabIndex', tab);//获取当前tab页索引
        for (var i = index - 1; i >= 0; i--) {
            $(".main-content").tabs('close', i);
        }
    });

    //关闭右侧所有
    $("#mm-tabcloseright").click(function () {
        var tab = $(".main-content").tabs("getSelected");
        var len = $(".tabs-inner").length;
        var index = $(".main-content").tabs("getTabIndex", tab);//获取当前tab页索引
        for (var i = len - 1; i >= index + 1; i--) {
            $(".main-content").tabs('close', i);
        }
    });

    //除之外全部关闭
    $("#mm-tabcloseother").click(function () {
        var tab = $(".main-content").tabs("getSelected");
        var len = $(".tabs-inner").length;
        var index = $(".main-content").tabs("getTabIndex", tab);
        for (var i = len - 1; i >= 0; i--) {
            i == index ? '' : $(".main-content").tabs("close", i);
        }
    });
};