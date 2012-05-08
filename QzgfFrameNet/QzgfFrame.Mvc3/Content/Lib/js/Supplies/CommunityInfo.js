//var CurID =  <%=CurID %>;
var Validator = null;
var saledepCompany;
var saledepList = null;
$(function () {
    //验证
    $.metadata.setType("attr", "validate");
    Validator = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        },
        submitHandler: function () {
            $("form .l-text,.l-textarea").ligerHideTip();            
        }
    });
    $("form").ligerForm();
});
function f_changeDistrict() {
    var DistrictId = $("#DistrictId").val();
    var CompanyId = $("#CompanyId").val();
    var newData = new Array();
    if (saledepList != null) {
        for (var i = 0; i < saledepList.length; i++) {
            if (saledepList[i].cid == CompanyId && saledepList[i].did == DistrictId) {
                newData.push(saledepList[i]);
            }
        }
    }
    return newData;
}
function f_save(frameid) {
    if (!Validator.form()) return;
    if ($("#terimFlag").val() == "1") return false;
    $.ligerDialog.waitting("正在保存中...");
    //提交数据

    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        $.ligerDialog.closeWaitting();
        if (result.Type) {
            $.ligerDialog.success('保存成功!', function () {
                parent.window.frames[frameid].f_reload();
            });
        }
        else {
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
}

/*判断在小区信息中该小区编码是否存在*/
function getCode(val) {
    var arrData = new Array();
    if (val != "") {
        $.getJSON('/Supplies/CommunityInfo/GetCheckCode/?id=' + val, { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                if ($("#Id").val() != data.Id) {
                    $("#showTerim").html("该小区编码在小区信息中已存在，不可重复添加！");
                    $("#terimFlag").val("1");
                    return false;
                }
                else {
                    $("#showTerim").html("");
                    $("#terimFlag").val("0");
                    return true;
                }
            }
            else {
                $("#showTerim").html("");
                $("#terimFlag").val("0");
                return true;
            }
        });
    }
    else {
        $("#terimFlag").val("1");
        return false;
    }
}