//var CurID =  <%=CurID %>;
var Validator = null;
var idArry = new Array();
$(function () {
    //验证
    $.metadata.setType("attr", "validate");
    Validator = $("form").validate({
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
            f_save();
        }
    });
    $("form").ligerForm();
});
function GetCount(i) {
    var num = $("#Num" + i).val().replace(/[ ]/g, "");
    if (num == "")
        $("#Num" + i).val(0);
    if (isNaN($("#Num" + i).val()))
        $("#Num" + i).val(0);
    var ANum = parseInt($("#ArrNum").val()) + GetAllNum();
    if (ANum > $("#suppliesCollarSupplies_Num").val()) {
        var endNum = parseInt(num) - (ANum - parseInt($("#suppliesCollarSupplies_Num").val()));
        $("#Num" + i).val(endNum);
        $("#Count").val($("#suppliesCollarSupplies_Num").val());
    }
    //$("#displyMsg").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ★ 领料数量不可大于采购总量！！");
     else
       $("#Count").val(ANum);
}

function f_checkData() {
    return CheckNum();
}
function f_save() {
   if (!f_checkData()) return false;
   $("#Collars").val(JSON2.stringify(GetSuppliesRow()));        
        $.ligerDialog.waitting("正在保存中...");
        //提交数据
        var fm = $("#newForm");
        //提交表单  result为返回的数据，success为成功,form为提交的form
        fm.ajaxSubmit(function (result, success, form) {
            if (result.Type) {
                $.ligerDialog.closeWaitting();
                $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                    if (ok) {
                        window.location.href = "/Supplies/CollarSupplies/Confirm/?frameid=" + $("#frameid").val() + "&id=" + $("#Id").val() + "";
                    }
                    else
                        window.location.href = "/Supplies/CollarSupplies?_menuid=" + $("#frameid").val();
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


    function f_windowclose() {
        window.location.href = "/Supplies/CollarSupplies?_menuid=" + $("#frameid").val();
    } 