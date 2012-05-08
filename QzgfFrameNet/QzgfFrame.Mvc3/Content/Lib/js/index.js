/*function alert(message) {
    $.ligerDialog.success(message);
}

var alert = function (message) {
    $.ligerDialog.alert('tt');
};
*/

var current = {};
function bulidLayout() {
    $("#mainbody").ligerLayout({ height: '100%', heightDiff: -32, leftWidth: 200 });
}
function bulidMenu(AfterShowMainMenu, AfterShowTopMenu) {
    $("#topmenu .l-topmenu-items").html("");
    var url = 'Service/SystemData.ashx?Action=GetApplication';
    url += "&rnd" + Math.random();
    $.ajax({
        type: 'post', cache: false, dataType: 'json',
        url: url,
        success: function (data) {
            if (!data || !data.length) {
                AfterShowMainMenu && AfterShowMainMenu();
                if (data.Type && data.Type == "Error" && data.Message) $.ligerDialog.error(data.Message);
                return;
            }
            var width = 0;
            $(data).each(function (i, dataitem) {
                var menuitem = $('<div class="l-topmenu-item"><span></span><div class="l-topmenu-item-l"></div><div class="l-topmenu-item-r"></div></div> ');
                this.name && $(">span:first", menuitem).html(this.name);
                this.code && menuitem.attr("code", this.code);
                if (i == 0) menuitem.addClass("l-topmenu-item-selected");

                menuitem.appendTo($("#topmenu .l-topmenu-items:first"));
                width += menuitem.width() + 4;
            });

            current.ApplicationCode = data[0].code;
            current.ApplicationName = data[0].name;
            bulidMainMenu('Service/SystemData.ashx?Action=GetMenu&ApplicationCode=' + data[0].code, AfterShowMainMenu);


            $(".l-topmenu-item").hover(function () {
                $(this).addClass("l-topmenu-item-over");
            }, function () {
                $(this).removeClass("l-topmenu-item-over");
            }).click(function () {
                if ($(this).hasClass("l-topmenu-item-selected")) return;
                $(this).addClass("l-topmenu-item-selected").siblings().removeClass("l-topmenu-item-selected");
                var code = $(this).attr("code");
                var name = $(">span", this).html();
                current.ApplicationCode = code;
                current.ApplicationName = name;
                bulidMainMenu('Service/SystemData.ashx?Action=GetMenu&ApplicationCode=' + code);
            });
            $("#topmenu .l-topmenu-items").width(width + 20);


            AfterShowTopMenu && AfterShowTopMenu(data);
        },
        error: function () {
            alert('发送系统错误,请与系统管理员联系!');
            AfterShowMainMenu && AfterShowMainMenu();
        }
    });
}

function bulidMainMenu(url, AfterShowMainMenu) {
    var mainmenu = $("#mainmenu");
    $(".l-link", mainmenu)
    .unbind("mouseover")
    .unbind("mouseout")
    .unbind("click");
    mainmenu.html("");
    if (!url) return;
    url += "&rnd" + Math.random();
    $.ajax({
        type: 'post', cache: false, dataType: 'json',
        url: url,
        success: function (data) {
            if (!data) return;
            if (data.Type && data.Type == "Error") {
                alert(data.Message); return;
            } else if (!data.length) return;
            $(data).each(function (i, dataitem) {
                var menuitem = $('<div class="l-link"><div class="l-icon"></div><span></span></div>');
                if (this.icon) {
                    $("> div", menuitem).addClass("l-icon-" + this.icon);
                    menuitem.attr("menuicon", this.icon);
                }
                this.name && $("> span", menuitem).html(this.name);
                this.url && menuitem.attr("url", this.url);
                this.menuno && menuitem.attr("menuno", this.menuno);
                mainmenu.append(menuitem);
            });
            AfterShowMainMenu && AfterShowMainMenu(data);
            $(".l-link", mainmenu).bind("mouseover", function () {
                $(this).addClass("l-link-over");
            });
            $(".l-link", mainmenu).bind("mouseout", function () {
                $(this).removeClass("l-link-over");
            });
            $(".l-link", mainmenu).bind("click", function () {
                if (!$(this).hasClass("l-link-selected")) {
                    $(this).addClass("l-link-selected").siblings().removeClass("l-link-selected");
                }
                var src = $(this).attr("url");
                var menuno = $(this).attr("menuno");
                var name = $(">span", this).html();
                current.MenuUrl = src;
                current.MenuIcon = $(this).attr("menuicon");
                current.MenuName = name;
                current.MenuNo = menuno;
                if (src) {
                    //附上参数
                    src += src.indexOf('?') == -1 ? "?" : "&";
                    src += "time=" + new Date().getTime();
                    src += "&MenuNo=" + current.MenuNo;
                    f_linkto(src);
                }
            });
        },
        error: function () {
            alert('发送系统错误,请与系统管理员联系!');
            AfterShowMainMenu && AfterShowMainMenu();
        }
    });
}
function f_linkto(src) {
    $("#mainframe").attr("src", src);
    f_changeStatus();
}
function f_changeStatus() {
    var str = "位置：首页 >> ";
    str += current.ApplicationName + " >> ";
    str += current.MenuName;
    $(".l-navigationbars-c span").html(str);

    $(".l-layout-center .l-layout-header").html('<div class="l-icon l-icon-' + current.MenuIcon + '"></div>栏目: ' + current.MenuName);
}