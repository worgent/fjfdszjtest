//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
$(function () {
    f_changeIOClass();
    $("#warehouseIOList_IOClassId").change(function () {
        f_changeIOClass();
    });
    var options = {
        dataType: 'json',
        beforeSubmit: showRequest,
        error: showError,
        success: showResponse
    };
    $('#newForm').ajaxForm(options);

    $("#EquipModelName0").autoSearchText({ width: 250, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            var ary = id.split(',');
            $("#EquipModelId0").val(ary[0]);
            $("#EquipTypeId0").val(ary[1]);
            $("#FactoryId0").val(ary[2]);
        },
        formatItem: function () {
            return ID;
        }
    });
    //验证
    $.metadata.setType("attr", "validate");
    $("#newForm").ligerForm();
    deafultValidate($("#newForm"));
    Validator = $("#newForm").validate();
});
function f_changeIOClass() {
    var selmode = $("#warehouseIOList_IOClassId").find("option:selected").text();
    var s1 = document.getElementById("s1");
    var s2 = document.getElementById("s2");
    if (selmode == "领用出库") {
        s2.style.display = "none";
        s1.style.display = "block";
    }
    if (selmode == "借用出库") {
        s1.style.display = "none";
        s2.style.display = "block";
    }
}
function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    $("#distributionDetails").val(JSON2.stringify(GetSuppliesRow()));

    $.ligerDialog.waitting("正在保存中...");
    return true;
}
function showError(data) {
    $.ligerDialog.error(data.Message, function () {
    });
}
function showResponse(data, status) {
    if (data.Type) {
        //$.ligerDialog.success(data.Message);
        $.ligerDialog.success('保存成功!', function () {
            window.location.href = "/Resources/OneSevenZero/";
        });
    }
    else {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.error(data.Message, function () {
        });
    }
}
