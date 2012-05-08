//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
var idArry = new Array();
var gosArray = new Array(); //保存耗材,解决重复问题
var tidsArray = new Array(); //保存耗材设置选项
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
    $("#Count").val(GetAllNum());
}

function f_checkData() {
    var trlen = GetSuppliesLen();
    if (trlen <= 1) {
        f_addh();
        return false;
    }
    var idno = parseInt($("#TRLastIndex").val()) - 1;

    if ($("#SuppliesTypeId" + idno).val() == "") {
        f_addh();
        return false;
    }
    else if ($("#SuppliesTypeName" + idno).val() == "") {
        f_addh()
        return false;
    }
    else {
    return CheckNum();
    }
    return true;
}
function f_save() {
    //  $("input:text", document.forms[0]).each(function () { alert(this.name) });
    $("#suppliesTypeIds").val(GetTids());

        if (!f_checkData()) return false;
        $("#collarSuppliesDetails").val(JSON2.stringify(GetSuppliesRow()));

        $.ligerDialog.waitting("正在保存中...");
        //提交数据
        var fm = $("#newForm");
        //提交表单  result为返回的数据，success为成功,form为提交的form
        fm.ajaxSubmit(function (result, success, form) {
            if (result.Type) {
                $.ligerDialog.closeWaitting();
                $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                    if (ok) {
                        window.location.href = "/Supplies/CollarSupplies/add/?id=0&frameid=" + $("#frameid").val();
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
function f_openWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastIndex").val());
                var rowFlag = false;
                if (rowID == "1" && $("#SuppliesTypeId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    for (var i = 0; i < resu.length; i++) {
                        var SuppliesType = new Object();
                        SuppliesType = resu[i];
                        var selFlag = true;
                        for (var k = 0; k < gosArray.length; k++) {
                            if (gosArray[k] == SuppliesType.SuppliesTypeId) {
                                selFlag = false;
                            }
                        }
                        var idsFlag = true;
                        if (selFlag) {
                            gosArray.push(SuppliesType.SuppliesTypeId);

                            var tidflag = true;
                            for (var n = 0; n < tidsArray.length; n++) {
                                if (tidsArray[n] == SuppliesType.SuppliesTypeId)
                                    tidflag = false;
                            }
                            if (tidflag)
                                tidsArray.push(SuppliesType.SuppliesTypeId);

                            if (rowFlag) {
                                $("#SuppliesTypeId0").val(SuppliesType.SuppliesTypeId);
                                $("#SuppliesTypeName0").val(SuppliesType.SuppliesTypeName);
                                $("#UnitName0").val(SuppliesType.UnitName);
                                $("#Num0").val("");
                                $("#Num0").removeClass("disable-dd-text");
                                $("#Num0").addClass("dd-text");
                                rowFlag = false;
                            }
                            else {
                                AddSuppliesRow(SuppliesType);
                            }
                        }
                    }
                    dialog.close();
                }
            }
            },
            { text: '关闭', onclick: function (item, dialog) {
                dialog.close();
            }
            }
            ], isResize: true, timeParmName: 'a'
    };
    activeDialog = parent.jQuery.ligerDialog.open(dialogOptions);
}

function f_addh() {
    var strid = GetTids();
    f_openWindow('/Archives/SuppliesType/SelIndex/?id=' + strid, '选择耗材', 700, 350);
} 