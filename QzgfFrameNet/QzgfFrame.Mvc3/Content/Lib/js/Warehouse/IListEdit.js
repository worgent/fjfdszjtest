//var CurID =  <%=CurID %>;
var Validator = null;
var idArry = new Array();
var gosArray = new Array(); //保存设备型号,解决重复问题
var tidsArray = new Array(); //保存设备型号设置选项
var managerType0;
var managerFactory0;
var UnitList;
$(function () {
    $.ajax({
        type: 'POST',
        url: "/Warehouse/iolist/GetUnitList",
        dataType: 'json',
        success: function (data) {
            UnitList = eval("(" + data.Rows + ")");
        }
    });
    f_changeIOClass();
    $("#warehouseIOList_IOClassId").change(function () {
        f_changeIOClass();
    });
    
    /**
    managerType0 = $("#EquipTypeName0").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
        url: '/Archives/EquipType/GetCombobox',
        valueFieldID: 'EquipTypeId0', textFieldID: 'EquipTypeName0'
    });
    managerFactory0 = $("#FactoryName0").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
        url: '/Archives/Factory/GetCombobox',
        valueFieldID: 'FactoryId0', textFieldID: 'FactoryName0'
    });
    $("#EquipModelName0").autoSearchText({ width: 250, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            var ary = id.split(',');
            managerType0.selectValue(ary[1]);
            managerFactory0.selectValue(ary[2]);
        },
        formatItem: function () {
            return ID;
        }
    });**/
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
        var idsFlag = true;
        for (var n = 0; n < tidsArray.length; n++) {
            if (tidsArray[n] == tid)
                idsFlag = false;
        }
        if (idsFlag)
            tidsArray.push(tid);
    }
});
function f_changeIOClass() {
    var selmode = $("#warehouseIOList_IOClassId").find("option:selected").text();
    /**
    var s1 = document.getElementById("s1");
    var s2 = document.getElementById("s2");
    if (selmode == "全新入库") {
        s2.style.display = "none";
        s1.style.display = "block";
    }
    if (selmode == "回收入库" || selmode == "归还入库" || selmode == "返修回收") {
        s1.style.display = "none";
        s2.style.display = "block";
    }
    **/
}

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
        $("#Num" + i).val(0);
    if (isNaN($("#Num" + i).val()))
        $("#Num" + i).val(0);
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
    f_openWindow('/Archives/EquipModel/SelIndex/?id=' + strid, '选择设备', 700, 350);
} 