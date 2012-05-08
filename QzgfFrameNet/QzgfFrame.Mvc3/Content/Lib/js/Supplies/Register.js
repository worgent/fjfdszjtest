//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
var idArry = new Array();
var gosArray = new Array(); //保存耗材,解决重复问题
var tidsArray = new Array(); //保存耗材设置选项
var maintainerManger;
var maintainerList = null;
var saledepList = null;
$(function () {
    $('#suppliesRegister_CommunityCode').autoSearchText({ width: 138, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            var ary = id.split(',');
            $("#suppliesRegister_CommunityName").val(ary[0]);
            $("#suppliesRegister_CommunityTypeId").val(ary[1]);
            $("#suppliesRegister_CommunityTypeName").val(ary[2]);
            $("#suppliesRegister_BuildWayId").val(ary[3]);
            $("#suppliesRegister_BuildWayName").val(ary[4]);
            $("#suppliesRegister_AccessWayId").val(ary[5]);
            $("#suppliesRegister_AccessWayName").val(ary[6]);
        },
        formatItem: function () {
        }
    });
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
            var strdata = $("#suppliesRegister_PBOSSJobNo").val();
            $.getJSON('/Supplies/Register/GetPBOSSJobNo/?id=' + strdata, { Rnd: Math.random() }, function (data) {
                if (data.Type) {
                    if ($("#Id").val() != data.Id) {
                        $("#showTerim").html("该PBOSS工单号在使用登记信息中已存在，不可重复添加！");
                        $("#suppliesRegister_PBOSSJobNo").focus();
                    }
                    else {
                        $("#showTerim").html("");
                        f_save();
                    }
                }
                else {
                    $("#showTerim").html("");
                    f_save();
                }
            });
        }
    });
    $("form").ligerForm();
    var count = Number($("#TRLastIndex").val());
    for (var i = 0; i < count; i++) {
        var tid = $("#SuppliesTypeId" + i).val();
        gosArray.push(tid);
       }
    $.getJSON('/Supplies/CommunityManager/GetCombobox/', { Rnd: Math.random() }, function (data) {
        maintainerList = eval("(" + data.Rows + ")");
        var vardata = f_changeDistrict();
        maintainerManger = $("#Maintainer").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
            data: vardata, valueFieldID: 'MaintainerId', textFieldID: 'Maintainer',
            onSelected: null
        });
        $.getJSON('/Archives/SaleDepartment/GetCombobox/', { Rnd: Math.random() }, function (data) {
            saledepList = eval("(" + data.Rows + ")");
            var vcdata = f_changeCompany();
            saledepCompany = $("#SaleDepartmentName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
                data: vcdata, valueFieldID: 'SaleDepartmentId', textFieldID: 'SaleDepartmentName',
                onSelected: function (newvalue, newText) {
                    var vdata = f_changeDistrict();
                    maintainerManger.setData(vdata);
                    if ($("#bSaleDepartmentId").val() == "") {
                        maintainerManger.selectText("");
                    }
                   // ClearAllSign(); tidsArray.length = 0;
                   // gosArray.length = 0; idsArray.length = 0;
                }
            });
            $("#suppliesRegister_CompanyId").change(function () {
                var vcdata = f_changeCompany();
                saledepCompany.setData(vcdata);
                saledepCompany.selectText("");
            });
        });
    });
});
function f_changeCompany() {
    var DistrictId = $("#suppliesRegister_DistrictId").val();
    var CompanyId = $("#suppliesRegister_CompanyId").val();
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
function f_changeDistrict() {
    var SaleDepartmentId = $("#SaleDepartmentId").val();
    var newData = new Array();
    if (maintainerList != null) {
        for (var i = 0; i < maintainerList.length; i++) {
            if (maintainerList[i].sid == SaleDepartmentId) {
                newData.push(maintainerList[i]);
            }
        }
    }
    return newData;
}
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
        f_addh();
        return false;
    }
    else {
        return CheckNum();
    }
    return true;
}
function f_save() {
    if (!f_checkData()) return false;
    $("#registerDetails").val(JSON2.stringify(GetSuppliesRow()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                if (ok) {
                    window.location.href = "/Supplies/Register/add/?id=0&frameid=" + $("#frameid").val();
                }
                else
                    window.location.href = "/Supplies/Register?_menuid=" + $("#frameid").val();
            });
        }
        else {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
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
                                $("#NumFlag0").val("0");
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

function f_windowclose() {
    window.location.href = "/Supplies/Register?_menuid=" + $("#frameid").val();
}
/*判断在使用登记信息中该PBOSS工单号是否存在*/
function getPBOSSJobNo(val) {
    var arrData = new Array();
    var getFlag = false;
    if (val != "") {
        $.getJSON('/Supplies/Register/GetPBOSSJobNo/?id=' + val, { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                if ($("#Id").val() != data.Id) {
                    $("#showTerim").html("该PBOSS工单号在使用登记信息中已存在，不可重复添加！");
                    return false;
                }
                else {
                    $("#showTerim").html("");
                    getFlag = true;
                }
            }
            else {
                $("#showTerim").html("");
                getFlag = true;
            }
            return getFlag;
        });
    }
    else {
        return getFlag;
    }
    return getFlag;
}

/*判断在小区信息中该小区编码是否存在*/
function getCommunityCode(val) {
    var arrData = new Array();
    if (val != "") {
        $.getJSON('/Supplies/CommunityInfo/GetCheckCode/?id=' + val, { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                var strdata = data.Rows;
                $("#suppliesRegister_CommunityName").val(strdata.CommunityName);
                $("#suppliesRegister_CommunityTypeId").val(strdata.CommunityTypeId);
                $("#suppliesRegister_CommunityTypeName").val(strdata.CommunityTypeName);
                $("#suppliesRegister_BuildWayId").val(strdata.BuildWayId);
                $("#suppliesRegister_BuildWayName").val(strdata.BuildWayName);
                $("#suppliesRegister_AccessWayId").val(strdata.AccessWayId);
                $("#suppliesRegister_AccessWayName").val(strdata.AccessWayName);
                $("#showCode").html("");
            }
            else {
                $("#showCode").html("该小区编码在小区信息中不存在，不可用！");
                $("#suppliesRegister_CommunityName").val("");
                $("#suppliesRegister_CommunityTypeId").val("");
                $("#suppliesRegister_CommunityTypeName").val("");
                $("#suppliesRegister_BuildWayId").val("");
                $("#suppliesRegister_BuildWayName").val("");
                $("#suppliesRegister_AccessWayId").val("");
                $("#suppliesRegister_AccessWayName").val("");
            }
        });
    }
}
/*加载数据小区信息*/
function getData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Supplies/CommunityInfo/GetCommunityCode/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var strid = []; var objData = new Object();
                    if (strdata[i].CommunityCode.indexOf(val) >= 0) {
                        strid.push(strdata[i].CommunityName);
                        strid.push(strdata[i].CommunityTypeId);
                        strid.push(strdata[i].CommunityTypeName);
                        strid.push(strdata[i].BuildWayId);
                        strid.push(strdata[i].BuildWayName);
                        strid.push(strdata[i].AccessWayId);
                        strid.push(strdata[i].AccessWayName);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].CommunityCode;
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