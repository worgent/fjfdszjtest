//var CurID =  <%=CurID %>;
var Validator = null;
var stagnationList = null;
var gridList = null;
var stagnation = null;
var grid = null;
var aryDataList = new Array();
var managerCompany;
var districtList = null;
var managerDistrict;
$(function () {
    //验证
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        },
        submitHandler: function () {
            $("form .l-text,.l-textarea").ligerHideTip();

            var strdata = $("#SeqNo").val();
            $.getJSON('/Resources/Apparatus/GetSeqNo/?id=' + strdata, { Rnd: Math.random() }, function (data) {
                if (data.Type) {
                    if ($("#Id").val() != data.Id) {
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
                    f_save();
                }
            });
        }
    });
    $("form").ligerForm();
    var getid = [];
    getid.push("");
    getid.push("");
    $.getJSON('/Resources/Personnel/GetDropDownList/' + getid, { Rnd: Math.random() }, function (data) {
        stagnationList = eval("(" + data.Rows[0] + ")");
        gridList = eval("(" + data.Rows[1] + ")");
        aryDataList = f_changeDistrict();
        stagnation = $("#StagnationName").ligerComboBox({ data: aryDataList[0], isMultiSelect: false, isShowCheckBox: false,
            valueFieldID: 'StagnationId', textFieldID: 'StagnationName', initValue: $("#StagnationId").val(),
            onSelected: null
        });
        grid = $("#GridName").ligerComboBox({ data: aryDataList[1], isMultiSelect: false, isShowCheckBox: false,
            valueFieldID: 'GridId', initValue: $("#GridId").val(),
            onSelected: null
        });
        managerCompany = $("#CompanyName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
            url: '/Archives/Company/GetCombobox',
            valueFieldID: 'CompanyId', textFieldID: 'CompanyName',
            onSelected: function (newvalue, newText) {
                aryDataList = f_changeDistrict();
                stagnation.setData(aryDataList[0]);
                grid.setData(aryDataList[1]);
                if (newvalue != $("#bcompanyid").val()) {
                    stagnation.selectText("");
                    grid.selectText("");
                }
                if (newText == "国脉" && $("#DistrictName").val() == "鲤城区")
                    managerDistrict.selectText("丰泽区");
                else if (newText == "泉邮" && $("#DistrictName").val() == "丰泽区")
                    managerDistrict.selectText("鲤城区");

            }
        });
        $.getJSON('/Archives/District/GetCombobox/', { Rnd: Math.random() }, function (data) {
            districtList = eval("(" + data.Rows + ")");
            var vdata = f_changeCity();
            managerDistrict = $("#DistrictName").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
                data: vdata, valueFieldID: 'DistrictId', textFieldID: 'DistrictName',
                onSelected: function (newvalue, newText) {
                    aryDataList = f_changeDistrict();
                    stagnation.setData(aryDataList[0]);
                    grid.setData(aryDataList[1]);
                    if (newvalue != $("#bdistrictid").val()) {
                        stagnation.selectText("");
                        grid.selectText("");
                        if ($("#curcompany").val() != "") {
                            managerCompany.selectValue($("#curcompany").val());
                        }
                        else if (newText == "丰泽区") {
                            managerCompany.selectText("国脉");
                        }
                        else if (newText == "鲤城区")
                            managerCompany.selectText("泉邮");
                    }
                }
            });
        });
    });
    $("#CityId").change(function () {
        var vdata = f_changeCity();
        managerDistrict.setData(vdata);
        managerDistrict.selectText("");
        aryDataList = f_changeDistrict();
        stagnation.setData(aryDataList[0]);
        grid.setData(aryDataList[1]);
        stagnation.selectText("");
        grid.selectText("");
    });
});
function f_changeCity() {
    var CityId = $("#CityId").val();
    var newData = new Array();
    if (districtList != null) {
        for (var i = 0; i < districtList.length; i++) {
            if (districtList[i].cid == CityId) {
                newData.push(districtList[i]);
            }
        }
    }
    return newData;
}
function f_changeDistrict() {
    var aryData = new Array();
    var DistrictId = $("#DistrictId").val();
    var CompanyId = $("#CompanyId").val();
    var CityId = $("#CityId").val();
    var newData = new Array();
    if (stagnationList != null) {
        for (var i = 0; i < stagnationList.length; i++) {
            if (stagnationList[i].did == DistrictId && stagnationList[i].cid == CompanyId && stagnationList[i].cityid == CityId) {
                newData.push(stagnationList[i]);
            }
            else if (stagnationList[i].did == DistrictId && CompanyId == "" && stagnationList[i].cityid == CityId) {
                newData.push(stagnationList[i]);
            }
            else if (DistrictId == "" && stagnationList[i].cid == CompanyId && stagnationList[i].cityid == CityId) {
                newData.push(stagnationList[i]);
            }
            else if (DistrictId == "" && CompanyId == "" && stagnationList[i].cityid == CityId) {
                newData.push(stagnationList[i]);
            }
        }
    }
    aryData.push(newData);
    var newData = new Array();
    if (gridList != null) {
        for (var j = 0; j < gridList.length; j++) {
            if (gridList[j].did == DistrictId && gridList[j].cid == CompanyId && gridList[j].cityid == CityId) {
                newData.push(gridList[j]);
            }
            else if (gridList[j].did == DistrictId && CompanyId == "" && gridList[j].cityid == CityId) {
                newData.push(gridList[j]);
            }
            else if (DistrictId == "" && gridList[j].cid == CompanyId && gridList[j].cityid == CityId) {
                newData.push(gridList[j]);
            }
            else if (DistrictId == "" && CompanyId == "" && gridList[j].cityid == CityId) {
                newData.push(gridList[j]);
            }
        }
    }
    aryData.push(newData);
    return aryData;
}

/*判断在仪器仪表信息中该序列号是否存在*/
function getSeqNo(val) {
    var arrData = new Array();
    var getFlag = false;
    if (val != "") {
        $.getJSON('/Resources/Apparatus/GetSeqNo/?id=' + val, { Rnd: Math.random() }, function (data) {
            if (data.Type) {
                if ($("#Id").val() != data.Id) {
                    $("#showTerim").html("该序列号在仪器仪表信息中已存在，不可重复添加！");
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
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                if (ok) {
                    window.location.href = "/Resources/Apparatus/add/?id=0&frameid=" + $("#frameid").val();
                }
                else
                    window.location.href = "/Resources/Apparatus?_menuid=" + $("#frameid").val();

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
    window.location.href = "/Resources/Apparatus?_menuid=" + $("#frameid").val();
} 