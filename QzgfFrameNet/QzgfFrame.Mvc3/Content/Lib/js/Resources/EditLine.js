/*加载数据集团客户信息*/
function getData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Resources/GroupClie/GetClie/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var strid = []; var objData = new Object();
                    if (strdata[i].clieName.indexOf(val) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].clieNo);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].clieName;
                        arrData.push(objData);
                    }
                }
            },
            Error: function (err) {
                alert(err);
            }
        });
    }
    return arrData;
} 
function getNoData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Resources/GroupClie/GetClieNo/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var strid = []; var objData = new Object();
                    if (strdata[i].clieNo.indexOf(val) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].clieName);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].clieNo;
                        arrData.push(objData);
                    }
                }
            },
            Error: function (err) {
                alert(err);
            }
        });
    }

    return arrData;
}
var managerClie;var Validator=null;
$(function () {
    $.metadata.setType("attr", "validate");
    Validator = $("form").validate({
        errorPlacement: function (lable, element) {
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        }
    });
    $("form").ligerForm();
    deafultValidate($("form"));

    $('#T7').MultiFile({
        max: 10,
        accept: 'doc|docx|rar|zip',
        list: '#T7-list',
        STRING: {
            remove: '<img src="../../../../Content/Lib/images/index/del_file.gif" height="16" width="16" alt="x"/>'
        }
    });
    $('#ClieNo').autoSearchText({ width: 138, itemHeight: 150, minChar: 1, datafn: getNoData,
        fn: function (id, name) {
            if (id != "" && id != null) {
                var ary = id.split(',');
                $("#ClieId").val(ary[0]);
                $("#ClieName").val(ary[1]);
            }
        }, formatItem: function () {
            return ID;
        }
    });
    $('#ClieName').autoSearchText({ width: 283, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            if (id != "" && id != null) {
                var ary = id.split(',');
                $("#ClieId").val(ary[0]);
                $("#ClieNo").val(ary[1]);
            }
        }, formatItem: function () {
            return ID;
        }
    });
    //f_changeclie();

    $("#DediLine_ClieId").change(function () {
        f_changeclie();
    });
    $("#DediLine_NetWorkingModeId").change(function () {
        f_changemode("");
    });
    $(".tab .table1 td:first").addClass("current"); //为第一个SPAN添加当前效果样式 
    $(".tab ul li:not(:first)").hide(); //隐藏其它的UL 
    $(".tab .table1 .tab_tit").click(function () {
        $(".tab .table1 td").removeClass("current"); //去掉所有SPAN的样式 
        $(this).addClass("current");
        $(".tab ul li").hide();
        $("." + $(this).attr("id")).fadeIn('slow');
    });
    if ($("#editFlag").val() != "VOIP" && $("#editFlag").val() != "Wide") {
        f_changemode("");
    }
    else if ($("#editFlag").val() == "Wide") {
        $('#ZClieNo').autoSearchText({ width: 138, itemHeight: 150, minChar: 1, datafn: getNoData,
            fn: function (id, name) {
                if (id != "" && id != null) {
                    var ary = id.split(',');
                    $("#ZClieId").val(ary[0]);
                    $("#ZClieName").val(ary[1]);
                }
            }, formatItem: function () {
                return ID;
            }
        });
        $('#ZClieName').autoSearchText({ width: 283, itemHeight: 150, minChar: 1, datafn: getData,
            fn: function (id, name) {
                if (id != "" && id != null) {
                    var ary = id.split(',');
                    $("#ZClieId").val(ary[0]);
                    $("#ZClieNo").val(ary[1]);
                }
            }, formatItem: function () {
                return ID;
            }
        });
        f_changemode("");
    }
    var options = {
        dataType: 'json',
        beforeSubmit: showRequest,
        error: showError,
        success: showResponse
    };
    $('#newForm').ajaxForm(options);
});
//$('#newForm').ajaxForm(options);
function f_changeclie() {
    var clieid = $("#DediLine_ClieId").val();
    $.getJSON('/Resources/GroupClie/Get/' + clieid, { Rnd: Math.random() }, function (data) {
        $("#ClieName").val(data.ClieName);
    });
}

/*判断在客户中该客户是否存在*/
function getLineClieNo(val) {
    if (val != "") {
        $.getJSON('/Resources/GroupClie/GetLineClieNo/' + val, { Rnd: Math.random() }, function (data) {
            if (!data.Type) {
                $("#ClieId").val("");
                $("#showClie").html("该客户编号在系统中不存在，无法添加！");
            }
            else {
                $("#showClie").html("");
                $("#ClieId").val(data.Id);
            }
        });
    }
} 
/*判断在专线中该专线编号是否存在*/
function getProductNo(val) {
    var arrData = new Array();
    var getFlag = false;
    if (val != "") {
        $.getJSON('/Resources/DedicateLine/GetLineNo/?id=' + val, { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                if ($("#DediLine_Id").val() != data.Id) {
                    $("#showTerim").html("该专线编号在专线信息中已存在，不可重复添加！");
                    $("#terimFlag").val("1");
                    getFlag = false;
                }
                else {
                    $("#showTerim").html(""); $("#terimFlag").val("0");
                    getFlag = true;
                }
            }
            else {
                $("#showTerim").html(""); $("#terimFlag").val("0");
                getFlag = true;
            }
            return getFlag;
        });
    }
    else {
        getFlag = false;
        return getFlag;
    }
}