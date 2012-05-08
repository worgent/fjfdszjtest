///<reference path="jquery-1.5.1.js" />
(function ($) {
    var itemIndex = 0;
    var GetId;
    $.fn.autoSearchText = function (options) {
        //以下为该插件的属性及其默认值
        var deafult = {
            width: 200,           //文本框宽
            itemHeight: 150,      // 下拉框高
            minChar: 1,            //最小字符数(从第几个开始搜索)
            datafn: null,         //加载数据函数
            fn: null,           //选择项后触发的回调函数
            formatItem: function () { return ID; }
        };
        var textDefault = $(this).val();
        var ops = $.extend(deafult, options);
        GetId = ops.ID;
        $(this).width(ops.width);
        //var autoSearch = '<div id="autoSearch"><ul class="menu_v"></ul></div>';
        //$(this).after(autoSearch);

        $(this).focus(function () {
            if ($(this).val() == textDefault) {
                //$(this).val('');
                $(this).css('color', 'black');
            }
        });
        var itemCount = $('#autoSearch ul li').length; //项个数
        /*鼠标按下键时，显示下拉框，并且划过项时改变背景色及赋值给输入框*/
        $(this).bind('keyup', function (e) {
            if ($(this).val().length >= ops.minChar) {
                var position = $(this).position();
                $('#autoSearch').css({ 'visibility': 'visible', 'left': position.left, 'top': position.top + 21 });

                //$('#autoSearch').css({ 'visibility': 'visible', 'left': position.left + 0 + 'px', 'top': position.top + 21 + 'px' });

                if (e.keyCode != 40 && e.keyCode != 38 && e.keyCode != 13) {
                    var data = ops.datafn($(this).val());
                    initItem($(this), data, ops);
                    itemCount = data.length;
                }
            }
            if ($('#autoSearch').css('visibility') == "visible") {
                switch (e.keyCode) {
                    case 38:   //上
                        $('#autoSearch ul li:nth-child(' + itemIndex + ')').css({ 'background': 'white', 'color': 'black' });

                        itemIndex--;
                        if (itemIndex == 0)
                            itemIndex = 1;
                        $('#autoSearch ul li:nth-child(' + itemIndex + ')').css({ 'background': '#FFE7A2', 'color': 'black' });
                        $(this).val($('#autoSearch ul li:nth-child(' + itemIndex + ')').find('font').text());
                        var sid = ($('#autoSearch ul li:nth-child(' + itemIndex + ')').find("input[name='sid']")).val();
                        ops.fn(sid, name);
                        break;
                    case 40: //下
                        $('#autoSearch ul li:nth-child(' + itemIndex + ')').css({ 'background': 'white', 'color': 'black' });
                        itemIndex++;
                        if (itemIndex > itemCount) {
                            itemIndex = itemCount;
                        }
                        $('#autoSearch ul li:nth-child(' + itemIndex + ')').css({ 'background': '#FFE7A2', 'color': 'black' });
                        var name = $('#autoSearch ul li:nth-child(' + itemIndex + ')').find('font').text();
                        $(this).val(name);
                        var name = $(this).find('font').text();
                        var sid = ($('#autoSearch ul li:nth-child(' + itemIndex + ')').find("input[name='sid']")).val();
                        ops.fn(sid, name);
                        break;
                    case 13:  //Enter
                        if (itemIndex > 0 && itemIndex <= itemCount) {
                            $(this).val($('autoSearch ul li:nth-child(' + itemIndex + ')').find('font').text());
                            
                            $('#autoSearch').css('visibility', 'hidden');
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        /*点击空白处隐藏下拉框*/
        $(document).click(function () {
            $('#autoSearch').css('visibility', 'hidden');
        });
    };
    /*获取文本框的值*/
    $.fn.getValue = function (sid, name) {
        ops.onSelected(sid, name);
        //return strobj;
    };

    /*初始化下拉框数据,鼠标移过每项时，改变背景色并且将项的值赋值给输入框*/
    function initItem(obj, data, ops) {
        $('#autoSearch').width(ops.width + 2); //设置项宽
        $('#autoSearch').height(ops.itemHeight); //设置项高
        var str = "";
        if (data.length == 0) {
            $('#autoSearch ul').html('<div style="text-align:center;color:red;">&nbsp;<div>');
        }
        else {
            for (var i = 1; i <= data.length; i++) {
                var objData = new Object();
                objData = data[i - 1];
                str += "<li><font>" + objData.Code + "</font><input type='hidden' id='sid' name='sid' value='" + objData.id.toString() + "' style='width:0px;'/></li>";
            }
            $('#autoSearch ul').html(str);
        }
        /*点击项时将值赋值给搜索文本框*/
        $("#autoSearch ul li").each(function () {
            $(this).bind('click', function () {
                obj.val($(this).find('font').text());
                var name = $(this).find('font').text();
                var sid = ($(this).find("input[name='sid']")).val();
                ops.fn(sid, name);
                $('#autoSearch').css('visibility', 'hidden');
            });
        });
        /*鼠标划过每项时改变背景色*/
        $("#autoSearch ul li").each(function () {
            $(this).hover(
                function () {
                    $(this).css({ 'background': '#FFE7A2', 'color': 'black' });
                    var name = $(this).find('font').text();
                    var sid = ($(this).find("input[name='sid']")).val();
                    obj.val(name);
                    ops.fn(sid, name);
                },
                function () {
                    $(this).css({ 'background': 'white', 'color': 'black' });
                }
            );
        });
    };
})(jQuery);