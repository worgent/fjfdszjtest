
function f_changemode(selmode) {
    if (selmode == "")
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
    var trlen = GetNumberLen();
    if (trlen <= 1) {
        $(".tab .table1 td ").removeClass("current");
        $(".tab ul li").hide();
        AddRow();
        var idno = $("#TRLastIndex").val();
        $("#number").addClass("current");
        $(".number").fadeIn('slow');
        $("#TelNumber" + idno).focus();
        return false;
    }
    var idno = parseInt($("#TRLastIndex").val()) - 1;    
    if ($("#TelNumber" + idno).val() == "") {
        $(".tab .table1 td ").removeClass("current");
        $(".tab ul li").hide();
        $("#number").addClass("current");
        $(".number").fadeIn('slow');
        $("#TelNumber" + idno).focus();
        return false;
    }
    if ($("#UserName" + idno).val() == "") {
        $(".tab .table1 td ").removeClass("current");
        $(".tab ul li").hide();
        $("#number").addClass("current");
        $(".number").fadeIn('slow');
        $("#UserName" + idno).focus();
        return false;
    }
    if ($("#PassWord" + idno).val() == "") {
        $(".tab .table1 td ").removeClass("current");
        $(".tab ul li").hide();
        $("#number").addClass("current");
        $(".number").fadeIn('slow');
        $("#PassWord" + idno).focus();
        return false;
    }
    return true;
}
function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    if (!f_checkData()) return false;
    if ($("#terimFlag").val() == "1") return false;
    $("#forces").val(JSON2.stringify(GetCoreRow()));
    $("#numbers").val(JSON2.stringify(GetNumberRow()));
    $("#delArys").val(delary);
   // alert($("#DediLine_ClieId").val());
   // $("input:text", document.forms[0]).each(function () { alert(this.name);alert(this.val()); }); 
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
                window.location.href = "/Resources/DedicateLine/Add/?BizType=IMS&id=0&frameid=" + $("#frameid").val();
            }
            else
                window.location.href = "/Resources/DedicateLine/IMS?_menuid=" + $("#frameid").val();
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
    window.location.href = "/Resources/DedicateLine/IMS?_menuid=" + $("#frameid").val();
} 