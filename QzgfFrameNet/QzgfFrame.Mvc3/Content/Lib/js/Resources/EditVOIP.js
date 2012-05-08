
function f_checkData() {
    if ($("#ClieId").val() == "") {
        $("#showClie").html("该客户编号在系统中不存在，无法添加！");
        $("#ClieNo").focus();
        return false;
    }
    return true;
}
function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    if (!f_checkData()) return false;
    if ($("#terimFlag").val() == "1") return false;
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
                window.location.href = "/Resources/DedicateLine/Add/?BizType=VOIP&id=0&frameid=" + $("#frameid").val();
            }
            else
                window.location.href = "/Resources/DedicateLine/VOIP?_menuid=" + $("#frameid").val();
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
    window.location.href = "/Resources/DedicateLine/VOIP?_menuid=" + $("#frameid").val();
} 