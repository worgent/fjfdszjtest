//var CurID =  <%=CurID %>;
/*加载数据网点信息*/
function getData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Archives/NetworkName/GetNetworkName/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var strid = []; var objData = new Object();
                    if (strdata[i].networkName.indexOf(val) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].networkNo);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].networkName;
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
} /*加载数据网点信息*/
function getNoData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Archives/NetworkName/GetNetworkNo/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var strid = []; var objData = new Object();
                    if (strdata[i].networkNo.indexOf(val) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].networkName);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].networkNo;
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
/*加载数据设备型号信息*/
function getEquipData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Archives/SelfHelpEquipModel/GetEquipModel/",
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
var modelList = null;
var model = null;
var managerType;
var managerFactory;
var ComponentList;
$(function () {
    $(".dd-text").focus(function () {
        $(this).addClass("dd-text-focus");
    }).blur(function () {
        $(this).removeClass("dd-text-focus");
    });
    $.ajax({
        type: 'POST',
        url: "/Resources/selfHelpEquip/GetDropDownList",
        dataType: 'json',
        success: function (data) {
            ComponentList = eval("(" + data.Rows + ")");
        }
    });
    $('#selfHelpEquip_UseNetName').autoSearchText({ width: 300, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            if (id != "" && id != null) {
                var ary = id.split(',');
                $("#selfHelpEquip_UseNetNo").val(ary[1]);
            }
        },
        formatItem: function () {
            return ID;
        }
    });
    $('#selfHelpEquip_UseNetNo').autoSearchText({ width: 138, itemHeight: 150, minChar: 1, datafn: getNoData,
        fn: function (id, name) {
            if (id != "" && id != null) {
                var ary = id.split(',');
                $("#selfHelpEquip_UseNetName").val(ary[1]);
            }
        },
        formatItem: function () {
            return ID;
        }
    });
    $("select[name='selComponent']").ligerComboBox();
    //验证
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        },
        submitHandler: function () {
            $("form .l-text,.l-textarea").ligerHideTip();

            var strdata = $("#selfHelpEquip_TermiId").val();
            $.getJSON('/Resources/SelfHelpEquip/GetTerimNo/?id=' + strdata, { Rnd: Math.random() }, function (data) {
                if (data.Type) {
                    if ($("#selfHelpEquip_Id").val() != data.Id) {
                        $("#showTerim").html("该终端ID在自助设备信息中已存在，不可重复添加！");
                        $("#selfHelpEquip_TermiId").focus();
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
                return getFlag;
            });
        }
    });
    $("form").ligerForm();

    managerType = $("#EquipTypeName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
        url: '/Archives/SelfHelpEquipType/GetCombobox',
        valueFieldID: 'EquipTypeId', textFieldID: 'EquipTypeName'
    });
    managerFactory = $("#FactoryName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
        url: '/Archives/SelfHelpFactory/GetCombobox',
        valueFieldID: 'FactoryId', textFieldID: 'FactoryName'
    });
    $("#selfHelpEquip_EquipModelName").autoSearchText({ width: 138, itemHeight: 150, minChar: 1, datafn: getEquipData,
        fn: function (id, name) {
            var ary = id.split(',');
            managerType.selectValue(ary[1]);
            managerFactory.selectValue(ary[2]);
        },
        formatItem: function () {
            return ID;
        }
    });

});

function f_success() {
    $(document).ready(function () {
        $.ligerDialog.confirm("是否继续编辑", "保存成功", function (ok) {
            $.ligerDialog.closeWaitting();
            parent.window.frames["mainframe"].f_reload();
            if (!ok) {
                parent.window.frames["mainframe"].f_closeWindow();
            }
        });
    });
}
function f_error(message) {
    $(document).ready(function () {
        $.ligerDialog.error(message);
    });
}
function f_save() {
    $("#equipComponents").val(JSON2.stringify(GetComponentRow()));
    //if(!Validator.form()) return;
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                if (ok) {
                    window.location.href = "/Resources/selfHelpEquip/add/?id=0&frameid=" + $("#frameid").val();
                }
                else
                    window.location.href = "/Resources/selfHelpEquip?_menuid=" + $("#frameid").val();
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
    window.location.href = "/Resources/SelfHelpEquip?_menuid=" + $("#frameid").val();
}
/*判断在设备中该设备是否存在*/
function getTerimNo(val) {
    var arrData = new Array();
    var getFlag = false;
    if (val != "") {
        $.getJSON('/Resources/SelfHelpEquip/GetTerimNo/' + val, { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                if ($("#selfHelpEquip_Id").val() != data.Id) {
                    $("#showTerim").html("该终端ID在自助设备信息中已存在，不可重复添加！");
                    getFlag = false;
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
    else
    {
        getFlag = false;
        return getFlag;
    }
}
function times() {
    var dt1 = new Date();
    var dt2 = $("#selfHelpEquip_BuyDatetime").val().replace(/\-/i, '\/');
    var strdt = "";
    if (dt2 != "") {
        var jdt = dt1 - new Date(dt2); 
        //计算出相差年
        var years = Math.floor(jdt / (12 * 30 * 24 * 3600 * 1000))

        //计算出相差月
        var leave1 = jdt % (12 * 30 * 24 * 3600 * 1000)    //计算年后剩余的毫秒
        var months = Math.round(jdt / (30 * 24 * 3600 * 1000))
        if(years!=0)
            strdt = years + "年" + months + "月";
        else
            strdt = months + "月";
    }
    $("#useMonth").val(strdt);
} 