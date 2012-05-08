
var ZgosArray = new Array(); //保存设备,解决重复问题
var gosArray = new Array(); //保存设备,解决重复问题
var tidsArray = new Array(); //保存设备设置选项
var ztidsArray = new Array(); //保存设备设置选项
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
    var count = Number($("#TRLastZEquipIndex").val());
    for (var i = 0; i < count; i++) {
        var eid = $("#ZEquipId" + i).val();
        ZgosArray.push(eid);
        var idsFlag = false;
        for (var j = 0; j < ztidsArray.length; j++) {
            if (ztidsArray[j] == eid) {
                idsFlag = true;
            }
        }
        if (idsFlag == false) {
            ztidsArray.push(eid);
        }
    }
    if (selmode == "")
        selmode = $("#DediLine_NetWorkingModeId").find("option:selected").text();
    if (selmode.indexOf("MSTP") >= 0 || selmode == "MSAP" || selmode == "协转") {
        $("#aciytnett").show();
        $("#zstation").show();
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#zstation").addClass("current");
        $(".tab ul li").hide();
        $(".zstation").fadeIn('slow');
    }
    if (selmode == "裸纤") {
        $("#aciytnett").hide();
        $("#zstation").hide();
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $("#core").addClass("current");
        $(".tab ul li").hide();
        $(".core").fadeIn('slow');
    }

}
/*判断在客户中该客户是否存在*/
function getLineZClieNo(val) {
    if (val != "") {
        $.getJSON('/Resources/GroupClie/GetLineClieNo/' + val, { Rnd: Math.random() }, function (data) {
            if (!data.Type) {
                $("#ZClieId").val("");
                $("#showZClie").html("该客户编号在系统中不存在，无法添加！");
            }
            else {
                $("#showZClie").html("");
                $("#ZClieId").val(data.Id);
            }
        });
    }
} 
function f_checkData() {
    if ($("#ClieId").val() == "") {
        $("#showClie").html("该A端客户编号在系统中不存在，无法添加！");
        $("#ClieNo").focus();
        return false;
    }
    if ($("#ZClieId").val() == "") {
        $("#showZClie").html("该Z端客户编号在系统中不存在，无法添加！");
        $("#ZClieNo").focus();
        return false;
    }
    var selmode = $("#DediLine_NetWorkingModeId").find("option:selected").text();
    if (selmode.indexOf("MSTP") >= 0 || selmode == "MSAP" || selmode == "协转") {
        $(".tab .table1 td ").removeClass("current"); //去掉所有SPAN的样式 
        $(".tab ul li").hide();
        if ($("#DediLine_ZStationName").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_ZStationName").focus();
            return false;
        }
        else if ($("#DediLine_ZComputRoomName").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_ZComputRoomName").focus();
            return false;
        }
        else if ($("#DediLine_ZTEquip").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_ZTEquip").focus();
            return false;
        }
        else if ($("#DediLine_ZTEquipPort").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_ZTEquipPort").focus();
            return false;
        }
        else if ($("#DediLine_ZDDFT").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_ZDDFT").focus();
            return false;
        }
        else if ($("#DediLine_ZDDFE").val() == "") {
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            $("#DediLine_ZDDFE").focus();
            return false;
        }
        //以上为Z端信息，以下为A端信息
        else if ($("#DediLine_StationName").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            $("#DediLine_StationName").focus();
            return false;
        }
        else if ($("#DediLine_BizComputRoomName").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            $("#DediLine_BizComputRoomName").focus();
            return false;
        }
        else if ($("#DediLine_ATEquip").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            $("#DediLine_ATEquip").focus();
            return false;
        }
        else if ($("#DediLine_ATEquipPort").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            $("#DediLine_ATEquipPort").focus();
            return false;
        }
        else if ($("#DediLine_ADDFT").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            $("#DediLine_ADDFT").focus();
            return false;
        }
        else if ($("#DediLine_ADDFE").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            $("#DediLine_ADDFE").focus();
            return false;
        }
        else if ($("#DediLine_ABizEquip").val() == "") {
            $("#aciytnett").addClass("current");
            $(".aciytnett").fadeIn('slow');
            f_addAEquip();
            return false;
        }
        else if ($("#DediLine_ZBizEquip").val() == "") {
            f_addZEquip();
            $("#zstation").addClass("current");
            $(".zstation").fadeIn('slow');
            return false;
        }
    }

    var trlen = GetLineEquipLen();
    if (trlen <= 1) {
        $("#aciytnett").addClass("current");
        $(".aciytnett").fadeIn('slow');
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
    //Z端

    var trlen = GetLineZEquipLen();
    if (trlen <= 1) {
        $("#zstation").addClass("current");
        $(".zstation").fadeIn('slow');
        $("#displyZMsg").html("请选择关联设备！！");
        f_addZEquip();
        return false;
    }
    var idno = parseInt($("#TRLastZEquipIndex").val()) - 1;

    if ($("#ZEquipId" + idno).val() == "") {
        $("#zstation").addClass("current");
        $(".zstation").fadeIn('slow');
        $("#displyZMsg").html("请选择关联设备！！");
        f_addAEquip();
        return false;
    }
    else {
        $("#zstation").addClass("current");
        $(".zstation").fadeIn('slow');
        var clieid = $("#ZClieId").val();
        return CheckZClie(clieid);
    }
    return true;
}
function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    if (!f_checkData()) return false;
    if ($("#terimFlag").val() == "1") return false;
    $("#lineEquips").val(JSON2.stringify(GetLineEquipRow()));
    $("#ZlineEquips").val(JSON2.stringify(GetLineZEquipRow()));
    $("#forces").val(JSON2.stringify(GetCoreRow()));
    $("#delArys").val(delary);
    $.ligerDialog.waitting("正在保存中...");
    return true;
}
function showError(data) {
    alert(data.Message);
}
function showResponse(data, status) {
    if (data.Type) {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
            if (ok) {
                window.location.href = "/Resources/DedicateLine/Add/?BizType=Wide&id=0&frameid=" + $("#frameid").val();
            }
            else
                window.location.href = "/Resources/DedicateLine/Wide?_menuid=" + $("#frameid").val();
        }); 
    }
    else {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.error(data.Message, function () {
        }); 
        //$.ligerDialog.error(data.Message);
    }
}

function f_windowclose() {
    window.location.href = "/Resources/DedicateLine/Wide?_menuid=" + $("#frameid").val();
}

function f_openZWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastEquipIndex").val());
                var rowFlag = false;
                if (rowID == "1" && $("#ZEquipId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    for (var i = 0; i < resu.length; i++) {
                        var Equip = new Object();
                        Equip = resu[i];
                        var selFlag = true;
                        for (var k = 0; k < ZgosArray.length; k++) {
                            if (ZgosArray[k] == Equip.EquipId) {
                                selFlag = false;
                            }
                        }
                        if (selFlag) {
                            ZgosArray.push(Equip.EquipId);
                            var tidflag = true;
                            for (var n = 0; n < ztidsArray.length; n++) {
                                if (ztidsArray[n] == Equip.EquipId)
                                    tidflag = false;
                            }
                            if (tidflag)
                                ztidsArray.push(Equip.EquipId);
                            if (rowFlag) {
                                $("#ZEquipId0").val(Equip.EquipId);
                                $("#ZEquipName0").val(Equip.EquipName);
                                $("#ZClieId0").val(Equip.ClieId);
                                $("#ZOccupyPort0").val(Equip.OccupyPort);
                                rowFlag = false;
                            }
                            else
                                AddLineZEquipRow(Equip);

                            $("#displyZMsg").html("");
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
function f_addZEquip() {
    strid = $("#ZClieId").val();
    strid += ";" + GetZTids();
    f_openZWindow('/Resources/Equipment/SelIndex/?id=' + strid, '选择Z端业务设备', 700, 350);
}
function f_addAEquip() {
    strid = $("#ClieId").val();
    strid += ";" + GetTids();
    f_openAWindow('/Resources/Equipment/SelIndex/?id=' + strid, '选择A端业务设备', 700, 350);
} 