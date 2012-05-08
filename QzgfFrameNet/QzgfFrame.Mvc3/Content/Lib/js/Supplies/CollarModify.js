//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
var idArry = new Array();
$(function () {
    //验证
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        },
        submitHandler: function () {
            $("form .l-text,.l-textarea").ligerHideTip();
            var num = $("#Num").val().replace(/[ ]/g, "");
            var arrivalNum = $("#ArrivalNum").val().replace(/[ ]/g, "");
            if ((parseInt(num) - parseInt(arrivalNum)) < 0) {
                $("#displymsg").html("总数量不可大于已领取数量");
                return false;
            }
            else if (parseInt(num) - parseInt(arrivalNum) == 0) {
                $("#IsArrival").val("1");
                $("#displymsg").html("");
                f_save();
            }
            else {
                $("#displymsg").html("");
                f_save();
            }
        }
    });
    $("form").ligerForm();
});
function f_save() {   
        $.ligerDialog.waitting("正在保存中...");
        //提交数据
        var fm = $("#newForm");
        //提交表单  result为返回的数据，success为成功,form为提交的form
        fm.ajaxSubmit(function (result, success, form) {
            if (result.Type) {
                $.ligerDialog.closeWaitting();
                $.ligerDialog.confirm("是否返回管理界面?", '保存成功!', function (ok) {
                    if (ok) {
                        window.location.href = "/Supplies/CollarSupplies?_menuid=" + $("#frameid").val();
                    }
                });
            }
            else {
                $.ligerDialog.closeWaitting();
                $.ligerDialog.error(result.Message, function () {
                });
            }
        });
}

function f_windowclose() {
    window.location.href = "/Supplies/CollarSupplies?_menuid=" + $("#frameid").val();
} 