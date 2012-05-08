
var gosArray = new Array(); //保存设备,解决重复问题
var tidsArray = new Array(); //保存设备设置选项
function f_changemode(selmode) {
    var count = Number($("#TRLastEquipIndex").val());
    for (var i = 0; i < count; i++) {
        var eid = $("#EquipId" + i).val();
        gosArray.push(eid);
        var idsFlag = false;
        for (var j = 0; j < tidsArray.length; j++) {
            if (tidsArray[j] == eid) {
                idsFlag = true;
            }
        }
        if (idsFlag == false) {
            tidsArray.push(eid);
        }
    }
     if(selmode=="")
         selmode = $("#DediLine_NetWorkingModeId").find("option:selected").text();
    if (selmode.indexOf("MSTP") >= 0 || selmode == "MSAP") {
        $("#bizcomroom").hide();
        $("#ddf").hide();
        $("#citynet").show();
        $("#aciytnett").show();
        $("#zstation").show();
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#zstation").addClass("current");
        $(".tab ul li").hide();
        $(".zstation").fadeIn('slow');
    }
    if (selmode == "协转") {
        $("#bizcomroom").hide();
        $("#ddf").show();
        $("#citynet").show();
        $("#aciytnett").show();
        $("#zstation").show();
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#zstation").addClass("current");
        $(".tab ul li").hide();
        $(".zstation").fadeIn('slow');
    }
    if (selmode == "PON") {
        $("#bizcomroom").show();
        $("#ddf").hide();
        $("#citynet").show();
        $("#aciytnett").hide();
        $("#zstation").hide();
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#bizcomroom").addClass("current");
        $(".tab ul li").hide();
        $(".bizcomroom").fadeIn('slow');
    }
    if (selmode == "裸纤") {
        $("#bizcomroom").hide();
        $("#ddf").hide();
        $("#citynet").hide();
        $("#aciytnett").hide();
        $("#zstation").hide();
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#core").addClass("current");
        $(".tab ul li").hide();
        $(".core").fadeIn('slow');
    }
}
function f_checkData() {
    if ($("#ClieId").val() == "") {
        $("#showClie").html("该客户编号在系统中不存在，无法添加！");
        $("#ClieNo").focus();
        return false;
    }
    var selmode = $("#DediLine_NetWorkingModeId").find("option:selected").text();
    if (selmode.indexOf("MSTP") >= 0 || selmode == "MSAP") {
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $(".tab ul li").hide();
        if ($("#DediLine_StationName").val() == "") {
            $("#zstation").addClass("current");        
            $(".zstation").fadeIn('slow');
            $("#DediLine_StationName").focus();
            return false;
        }
        else if ($("#DediLine_StationTEquipAndPort").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_StationTEquipAndPort").focus();
            return false;
        }
        else if ($("#DediLine_CircuitNumber").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_CircuitNumber").focus();
            return false;
        }
    }
    if (selmode == "协转") {
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#zstation").addClass("current");
        $(".tab ul li").hide();
        $(".zstation").fadeIn('slow');
        if ($("#DediLine_StationName").val() == "") {
            $("#DediLine_StationName").focus();
            return false;
        }
        else if ($("#DediLine_StationTEquipAndPort").val() == "") {
            $("#DediLine_StationTEquipAndPort").focus();
            return false;
        }
        else if ($("#DediLine_CircuitNumber").val() == "") {
            $("#DediLine_CircuitNumber").focus();
            return false;
        }
        else if ($("#DediLine_DDFT").val() == "") {
            $("#DediLine_DDFT").focus();
            return false;
        }
        else if ($("#DediLine_DDFE").val() == "") {
            $("#DediLine_DDFE").focus();
            return false;
        }
    }
    if (selmode == "PON") {
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#bizcomroom").addClass("current");
        $(".tab ul li").hide();
        $(".bizcomroom").fadeIn('slow');
        if ($("#DediLine_BizComputRoomName").val() == "") {
            $("#DediLine_BizComputRoomName").focus();
            return false;
        }
        else if ($("#DediLine_OLTEquipAPort").val() == "") {
            $("#DediLine_OLTEquipAPort").focus();
            return false;
        }
    }
    if (selmode != "裸纤") {
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $(".tab ul li").hide();
        if ($("#DediLine_CityNetSwitch").val() == "") {
            $("#citynet").addClass("current");
            $(".citynet").fadeIn('slow');
            $("#DediLine_CityNetSwitch").focus();
            return false;
        }
        else if ($("#DediLine_CityNetSwitchPort").val() == "") {
            $("#citynet").addClass("current");
            $(".citynet").fadeIn('slow');
            $("#DediLine_CityNetSwitchPort").focus();
            return false;
        }
    }
    var trlen = GetLineEquipLen();
    if (trlen <= 1) {
        $("#bizequip").addClass("current");
        $(".bizequip").fadeIn('slow');
        $("#displyMsg").html("请选择关联设备！！");
        f_addAEquip();
        return false;
    }
    var idno = parseInt($("#TRLastEquipIndex").val()) - 1;

    if ($("#EquipId" + idno).val() == "") {
        $("#aciytnett").addClass("current");
        $(".aciytnett").fadeIn('slow');
        $("#displyMsg").html("请选择关联设备！！");
        f_addAEquip();
        return false;
    }
    else {
        $("#aciytnett").addClass("current");
        $(".aciytnett").fadeIn('slow');
        var clieid = $("#ClieId").val();
        return CheckClie(clieid);
    }
    return true;
}
function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    if (!f_checkData()) return false;
    if ($("#terimFlag").val() == "1") return false;
    $("#lineEquips").val(JSON2.stringify(GetLineEquipRow()));
        $("#forces").val(JSON2.stringify(GetCoreRow()));
        $("#delArys").val(delary);
        $.ligerDialog.waitting("正在保存中...");
        return true;
}
function showError(data) {
    $.ligerDialog.error(data.Message, function () {
    }); 
}
function showResponse(data, status) {
    if (data.Type) {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
            if (ok) {
                window.location.href = "/Resources/DedicateLine/Add/?BizType=Internet&id=0&frameid=" + $("#frameid").val();
            }
            else
                window.location.href = "/Resources/DedicateLine/Internet?_menuid=" + $("#frameid").val();
        }); 
    }
    else {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.error(data.Message, function () {
        }); 
    }
}

function f_windowclose() {
    window.location.href = "/Resources/DedicateLine/Internet?_menuid=" + $("#frameid").val();
}

function f_addAEquip() {
    strid = $("#ClieId").val();
    strid += ";" + GetTids();
    f_openAWindow('/Resources/Equipment/SelIndex/?id=' + strid, '选择A端业务设备', 700, 350);
}

function f_openAWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastEquipIndex").val());
                var rowFlag = false;
                if (rowID == "1" && $("#EquipId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    for (var i = 0; i < resu.length; i++) {
                        var Equip = new Object();
                        Equip = resu[i];
                        var selFlag = true;
                        for (var k = 0; k < gosArray.length; k++) {
                            if (gosArray[k] == Equip.EquipId) {
                                selFlag = false;
                            }
                        }
                        if (selFlag) {
                            gosArray.push(Equip.EquipId);
                            var tidflag = true;
                            for (var n = 0; n < tidsArray.length; n++) {
                                if (tidsArray[n] == Equip.EquipId)
                                    tidflag = false;
                            }
                            if (tidflag)
                                tidsArray.push(Equip.EquipId);
                            if (rowFlag) {
                                $("#EquipId0").val(Equip.EquipId);
                                $("#EquipName0").val(Equip.EquipName);
                                $("#ClieId0").val(Equip.ClieId);
                                $("#OccupyPort0").val(Equip.OccupyPort);
                                rowFlag = false;
                            }
                            else
                                AddLineEquipRow(Equip);

                            $("#displyMsg").html("");
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