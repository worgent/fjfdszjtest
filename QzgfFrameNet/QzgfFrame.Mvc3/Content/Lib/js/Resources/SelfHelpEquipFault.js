/*加载数据网点信息*/
function getData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Archives/SelfHelpEquip/GetTerim/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var strid = []; var objData = new Object();
                    if (strdata[i].termiId.indexOf(val) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].districtId);
                        strid.push(strdata[i].districtName);
                        strid.push(strdata[i].useNetNo);
                        strid.push(strdata[i].useNetName);
                        strid.push(strdata[i].factoryId);
                        strid.push(strdata[i].factoryName);
                        strid.push(strdata[i].equipModelName);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].termiId;
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
var modelList = null;
var model = null;
$(function () {
    $('#TermiId').autoSearchText({ width: 138, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            var ary = id.split(',');
            $("#SelfHelpEquipId").val(ary[0]);
            $("#DistrictId").val(ary[1]);
            $("#DistrictName").val(ary[2]);
            $("#UseNetNo").val(ary[3]);
            $("#UseNetName").val(ary[4]);
            $("#FactoryId").val(ary[5]);
            $("#FactoryName").val(ary[6]);
            $("#EquipModelName").val(ary[7]);
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
            else if (element.hasClass("disable-dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        },
        submitHandler: function () {
            $("form .l-text,.l-textarea").ligerHideTip();
            if ($("#SelfHelpEquipId").val() == null || $("#SelfHelpEquipId").val() == "")
                $("#TermiId").focus();
            else
              f_save();
        }
    });
    $("form").ligerForm();
});
function times() {
    var dt1 = $("#NoticeDateTime").val().replace(/\-/i, '\/');
    var dt2 = $("#SolveDatetime").val().replace(/\-/i, '\/');
    var strdt = "";
    if (dt1 != "" && dt2 != "") {
        var jdt = new Date(dt2) - new Date(dt1);
        //计算出相差分钟
        var minutes = Math.round(jdt / (60 * 1000))
        var strdt = minutes;
    }
     $("#HandleTime").val(strdt);
 }
 /*判断在设备中该设备是否存在*/
 function getTerimNo(val) {
     var arrData = new Array();
     if (val != "") {
         $.getJSON('/Resources/SelfHelpEquip/GetCheckTerimNo/?id=' + val, { Rnd: Math.random() }, function (data) {
             if (data.Type) {
                 var strdata = data.Rows;
                 $("#SelfHelpEquipId").val(strdata.Id);
                 $("#DistrictId").val(strdata.districtId);
                 $("#DistrictName").val(strdata.districtName);
                 $("#UseNetNo").val(strdata.useNetNo);
                 $("#UseNetName").val(strdata.useNetName);
                 $("#FactoryId").val(strdata.factoryId);
                 $("#FactoryName").val(strdata.factoryName);
                 $("#EquipModelName").val(strdata.equipModelName);
             }
             else {
                 $("#showTerim").html("该终端ID在自助设备信息中不存在，不可用！");
             }
         });
     }
 }
function f_changeequipmode() {
    var factoryId = $("#FactoryId").val();
    var newData = new Array();
    if (modelList != null) {
        for (var i = 0; i < modelList.length; i++) {
            if (modelList[i].factoryId == factoryId) {
                newData.push(modelList[i]);
            }
        }
    }
    return newData;
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
                    window.location.href = "/Resources/EquipFault/add/?id=0&frameid=" + $("#frameid").val();
                }
                else
                    window.location.href = "/Resources/EquipFault?_menuid=" + $("#frameid").val();
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
    window.location.href = "/Resources/EquipFault?_menuid=" + $("#frameid").val();
} 