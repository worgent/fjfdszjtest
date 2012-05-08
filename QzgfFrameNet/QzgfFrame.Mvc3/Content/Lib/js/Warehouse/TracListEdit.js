//var CurID =  <%=CurID %>;
var Validator = null;
var gosArray = new Array(); //保存设备型号,解决重复问题
var idsArray = new Array(); //保存设备型号，最大数量，保存最大数量
var tidsArray = new Array(); //保存设备型号设置选项
var managerType0;
var managerFactory0;
var UnitList;
$(function () {
    //验证
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
    var count = Number($("#TRLastIndex").val());
    for (var i = 0; i < count; i++) {
        var tid = $("#EquipModelId" + i).val();
        gosArray.push(tid);
        var idsFlag = false;
        for (var j = 0; j < idsArray.length; j++) {
            if (idsArray[j].tid == tid) {
                idsFlag = true;
                idsArray[j].Num = idsArray[j].Num;
            }
        }
        if (idsFlag == false) {
            var gNum = new Object(); //gid,maxNum
            gNum.tid = tid;
            gNum.Num = parseInt($("#MaxNum" + i).val()) - parseInt($("#Num" + i).val());
            idsArray.push(gNum);
        }
        var tidsFlag = true;
        if (tid == "") {
            tidsFlag = false;
        }
        else {
            for (var n = 0; n < tidsArray.length; n++) {
                if (tidsArray[n] == tid)
                    tidsFlag = false;
            }
        }
        if (tidsFlag)
            tidsArray.push(tid);
    }
});

function f_checkData() {
    var trlen = GetIODetailLen();
    if (trlen <= 1) {
        f_addh();
        return false;
    }
    var idno = parseInt($("#TRLastIndex").val()) - 1;

    if ($("#EquipModelId" + idno).val() == "") {
        f_addh();
        return false;
    }
    else if ($("#EquipModelName" + idno).val() == "") {
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
   
    if (!f_checkData()) return false;
    $("#ioDetails").val(JSON2.stringify(GetIODetailRow()));

    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                if (ok) {
                    window.location.href = "/Warehouse/CollarSupplies/add/?id=0&frameid=" + $("#frameid").val();
                }
                else
                    window.location.href = "/Warehouse/IList?_menuid=" + $("#frameid").val();
            });
        }
        else {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
}

function GetCount(i) {
    var num = $("#Num" + i).val().replace(/[ ]/g, "");
    if (num == "")
        $("#Num" + i).val(1);
    if (isNaN($("#Num" + i).val()))
        $("#Num" + i).val(1);
    var strMaxNum = parseInt($("#MaxNum" + i).val());
    var strNumFlag = parseInt($("#NumFlag" + i).val());
    for (var j = 0; j < idsArray.length; j++) {
        if (idsArray[j].tid == $("#EquipModelId" + i).val()) {
            strMaxNum = idsArray[j].Num + strNumFlag;
            if (parseInt($("#Num" + i).val()) > parseInt(strMaxNum)) {
                $("#Num" + i).val(strMaxNum);
                idsArray[j].Num = idsArray[j].Num - parseInt(strMaxNum) + strNumFlag;
            }
            else
                idsArray[j].Num = idsArray[j].Num - parseInt($("#Num" + i).val()) + strNumFlag;

            break;
        }
    }
    var setNum = parseInt($("#Num" + i).val());
    $("#NumFlag" + i).val(setNum);  
    $("#warehouseIOList_TotalNum").val(GetAllNum());
}

function f_openWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastIndex").val());
                var rowFlag = false;
                if (rowID == "1" && $("#EquipModelId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    for (var i = 0; i < resu.length; i++) {
                        var EquipModel = new Object();
                        EquipModel = resu[i];
                        var selFlag = true;
                        for (var k = 0; k < gosArray.length; k++) {
                            if (gosArray[k] == EquipModel.EquipModelId) {
                                selFlag = false;
                            }
                        }
                        var idsFlag = true;
                        if (selFlag) {
                            var maxNum = EquipModel.MaxNum;
                            var addgid = false; var idsFlag = true;
                            for (var j = 0; j < idsArray.length; j++) {
                                if (idsArray[j].tid == EquipModel.EquipModelId) {
                                    idsFlag = false;
                                    if ((idsArray[j].Num - 1) >= 0) {
                                        idsArray[j].Num = idsArray[j].Num - 0;
                                        addgid = true;
                                    }
                                }
                            }
                            if (idsFlag) {
                                var gNum = new Object(); //did,cid,maxNum
                                gNum.tid = EquipModel.EquipModelId;
                                if ((maxNum - 1) >= 0) {
                                    addgid = true;
                                    gNum.Num = maxNum - 0;
                                }
                                else {
                                    gNum.Num = 0;
                                    addgid = false;
                                }
                                idsArray.push(gNum);
                            }
                            if (addgid) {
                                gosArray.push(EquipModel.EquipModelId);
                                var tidflag = true;
                                for (var n = 0; n < tidsArray.length; n++) {
                                    if (tidsArray[n] == EquipModel.EquipModelId)
                                        tidflag = false;
                                }
                                if (tidflag)
                                    tidsArray.push(EquipModel.EquipModelId);

                                if (rowFlag) {
                                    $("#EquipModelId0").val(EquipModel.EquipModelId);
                                    $("#EquipModelName0").val(EquipModel.EquipModelName);
                                    $("#EquipTypeId0").val(EquipModel.EquipTypeId);
                                    $("#EquipTypeName0").val(EquipModel.EquipTypeName);
                                    $("#FactoryId0").val(EquipModel.FactoryId);
                                    $("#FactoryName0").val(EquipModel.FactoryName);
                                    $("#UnitName0").val(EquipModel.UnitName);
                                    $("#EquipState0").val(EquipModel.EquipState);
                                    $("#Num0").val("");
                                    $("#Num0").removeClass("disable-dd-text");
                                    $("#Num0").addClass("dd-text");
                                    rowFlag = false;
                                }
                                else {
                                    AddIODetailRow(EquipModel);
                                }
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
    var strid = $("#warehouseIOList_ODistrictId").val();
    strid += ":" + $("#warehouseIOList_OCompanyId").val();
    strid += ":" + GetTids();
    f_openWindow('/Warehouse/Stock/SelIndex/?id=' + strid, '选择设备', 700, 350);
} 