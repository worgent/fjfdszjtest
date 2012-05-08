//var CurID =  <%=CurID %>;
var Validator = null;
var modelList = null;
var model = null;
var managerType;
var managerFactory;
var PortTypeList;
$(function () {
    $.ajax({
        type: 'POST',
        url: "/Resources/Equipment/GetDropDownList",
        dataType: 'json',
        success: function (data) {
            PortTypeList = eval("(" + data.Rows + ")");
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

            var strdata = $("#equipment_EquipName").val();
            $.getJSON('/Resources/Equipment/GetEquipName/?id=' + escape(strdata), { Rnd: Math.random() }, function (data) {
                if (data.Type) {
                    if ($("#equipment_Id").val() != data.Id) {
                        $("#showTerim").html("该序列号在仪器仪表信息中已存在，不可重复添加！");
                        $("#SeqNo").focus();
                    }
                    else {
                        $("#showTerim").html("");
                        f_save();
                    }
                }
                else {
                    $("#showTerim").html("");
                    if (f_checkData()) {
                        f_save();
                    }
                }
            });
        }
    });
    $("form").ligerForm();

    managerType = $("#EquipTypeName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
        url: '/Archives/EquipType/GetCombobox',
        valueFieldID: 'EquipTypeId', textFieldID: 'EquipTypeName'
    });
    managerFactory = $("#FactoryName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
        url: '/Archives/Factory/GetCombobox',
        valueFieldID: 'FactoryId', textFieldID: 'FactoryName'
    });

    $("#equipment_EquipModelName").autoSearchText({ width: 250, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            var ary = id.split(',');
            managerType.selectValue(ary[1]);
            managerFactory.selectValue(ary[2]);
            //$("#EquipTypeId").val(ary[1]);
            //$("#FactoryId").val(ary[2]);
        },
        formatItem: function () {
            return ID;
        }
    });
    f_changeNetworkModel();
    $("#equipment_NetWorkingModeId").change(function () {
        f_changeNetworkModel();
    });
});

/*加载数据设备型号信息*/
function getData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Archives/EquipModel/GetEquipModel/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var objData = new Object();
                    var strid = [];
                    var strval = val.toLowerCase();
                    var strmodelName = (strdata[i].modelName).toLowerCase();
                    if (strmodelName.indexOf(strval) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].typeId);
                        strid.push(strdata[i].factoryId);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].modelName;
                        arrData.push(objData);
                    }
                }
            },
            Error: function (err) {
                $.ligerDialog.error(err, function () {
                });
            }
        });
    }
    return arrData;
}
function f_changeNetworkModel() {
    var selmode = $("#equipment_NetWorkingModeId").find("option:selected").text();
    var idno = parseInt($("#TRLastIndex").val()) - 1;
    if (selmode != "MSAP") {
        for (var j = 0; j <= idno; j++) {
            $("#OccupySlot"+j).attr({ "disabled": "disabled" });
            $("#BoardType" + j).attr({ "disabled": "disabled" });

            $("#OccupySlot" + j).removeClass("dd-text");
            $("#BoardType" + j).removeClass("dd-text");
            $("#OccupySlot" + j).addClass("disable-dd-text");
            $("#BoardType" + j).addClass("disable-dd-text");
        }
        var trCount = $("#CountIndex").val();        
        $("#displyMsg").html("");
    }
    else {
        for (var j = 0; j <= idno; j++) {
            $("#OccupySlot" + j).removeAttr("disabled");
            $("#BoardType" + j).removeAttr("disabled");

            $("#OccupySlot" + j).removeClass("disable-dd-text");
            $("#BoardType" + j).removeClass("disable-dd-text");
            $("#OccupySlot" + j).addClass("dd-text");
            $("#BoardType" + j).addClass("dd-text");
        } 
    }
}
function f_checkData() {
    var list = $('input:radio[name="equipment.Position"]:checked').val();
    if (list == "1" && $("#equipment_BaseStationName").val() == "") {
        $("#equipment_BaseStationName").focus();
        return false;
    }
    var trlen = GetClieLen();
    if (trlen <= 1) {
        f_addh();
        return false;
    }
    var idno = parseInt($("#TRLastIndex").val()) - 1;
    if ($("#ClieNo" + idno).val() == "") {
        $("#ClieNo" + idno).focus();
        f_addh();
        return false;
    }
    if ($("#ClieName" + idno).val() == "") {
        $("#ClieName" + idno).focus();
        f_addh();
        return false;
    }
    if ($("#OccupyPort" + idno).val() == "") {
        $("#OccupyPort" + idno).focus();
        return false;
    }
    var selmode = $("#equipment_NetWorkingModeId").find("option:selected").text();
    
    if (selmode == "MSAP") {
        for (var j = 0; j <= idno; j++) {
            if ($("#OccupySlot" + j).val() == "") {
                $("#OccupySlot" + j).focus();
                return false;
            }
            if ($("#BoardType" + j).val() == "") {
                $("#BoardType" + j).focus();
                return false;
            }
        }
    } 
    for (var j = 0; j <= idno; j++) {
        if ($("#OccupyPort" + j).val() == "") {
            $("#OccupyPort" + j).focus();
            return false;
        }
    }
    var seltype = $("#equipment_EquipTypeId").find("option:selected").text();
    if (seltype == "ONU" && $("#equipment_MacAddress").val() == "") {
        $("#equipment_MacAddress").focus();
        return false;
    }
    else if (seltype == "路由器" || seltype == "IAD" || seltype == "IPPBX") {
        if ($("#equipment_UserName").val() == "") {
            $("#equipment_UserName").focus();
            return false;
        }
        if ($("#equipment_PassWord").val() == "") {
            $("#equipment_PassWord").focus();
            return false;
        }
        if ($("#equipment_NetManageIp").val() == "") {
            $("#equipment_NetManageIp").focus();
            return false;
        }
        if ($("#equipment_WebPort").val() == "") {
            $("#equipment_WebPort").focus();
            return false;
        }
    }
    return true;
}
function Setdanbaoren() {
    var b = $('input:radio[name="equipment.Position"]:checked').val();
    if (b == 0) {
        $("#equipment_BaseStationName").val("");
        $("#equipment_BaseStationName").attr({ "disabled": "disabled" });
        $("#equipment_BaseStationName").removeClass("dd-text");
        $("#equipment_BaseStationName").addClass("disable-dd-text");
    }
    if (b == 1) {
        $("#equipment_BaseStationName").removeAttr("disabled");
        $("#equipment_BaseStationName").removeClass("disable-dd-text");
        $("#equipment_BaseStationName").addClass("dd-text");
    }
}
function f_changeequipmode() {
    var factoryId = $("#equipment_FactoryId").val();
    var typeId = $("#equipment_EquipTypeId").val();
    var newData = new Array();
    if (modelList !=null) {
        for (var i = 0; i < modelList.length; i++) {
            if (modelList[i].typeId == typeId && modelList[i].factoryId == factoryId) {
                newData.push(modelList[i]);
            }
        }
    }
    return newData;
}
/*判断在专线设备中设备名称是否存在*/
function getEquipName(val) {
    var arrData = new Array();
    var getFlag = false;
    if (val != "") {
        $.getJSON('/Resources/Equipment/GetEquipName/?id=' +escape(val), { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                if ($("#equipment_Id").val() != data.Id) {
                    $("#showTerim").html("该设备名称在专线设备信息中已存在，不可重复添加！");
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
function f_save() {
    if (!f_checkData()) return false;
    $("#equipclies").val(JSON2.stringify(GetClieRow()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                if (ok) {
                    window.location.href = "/Resources/Equipment/add/?id=0&frameid=" + $("#frameid").val();
                }
                else
                    window.location.href = "/Resources/Equipment?_menuid=" + $("#frameid").val();
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
                if (rowID == "1" && $("#ClieId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    var selmode = $("#equipment_NetWorkingModeId").find("option:selected").text();
                    for (var i = 0; i < resu.length; i++) {
                        var clie = new Object();
                        clie = resu[i];
                        if (rowFlag) {
                            $("#ClieId0").val(clie.ID);
                            $("#ClieNo0").val(clie.ClieNo);
                            $("#ClieName0").val(clie.ClieName);
                            if (selmode != "MSAP") {
                                $("#OccupySlot0").attr({ "disabled": "disabled" });
                                $("#BoardType0").attr({ "disabled": "disabled" });
                            }
                            rowFlag = false;
                        }
                        else {
                            AddClieRow(clie, selmode);
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
    var trlen = GetClieLen() - 1;
    var idno = parseInt($("#TRLastIndex").val()) - 1;
    if ($("#ClieNo" + idno).val() == "") {
        trlen = trlen - 1;
    }
    var selmode = $("#equipment_NetWorkingModeId").find("option:selected").text();
    var strid = [];
    strid.push(selmode);
    strid.push(trlen);
    f_openWindow('/Resources/GroupClie/SelIndex/' + strid, '选择集团客户', 700, 350);
}
function f_windowclose() {
    window.location.href = "/Resources/Equipment?_menuid=" + $("#frameid").val();
} 