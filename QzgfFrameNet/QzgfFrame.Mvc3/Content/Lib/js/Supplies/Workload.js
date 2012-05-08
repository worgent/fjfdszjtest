//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
$(function () {
    //验证
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        },
        submitHandler: function () {
            $("form .l-text,.l-textarea").ligerHideTip();
            f_save();
        }
    });

    $("#SaleDepartmentId").change(function () {
        var idStr = "";
        var DistrictId = $("#DistrictId").val();
        idStr += DistrictId;
        idStr +=","+ this.value;
        if ($("#NYDate").val() != "") {
        idStr +=","+ $("#NYDate").val();
        $.getJSON('/Supplies/Register/GetCount/' + idStr, { Rnd: Math.random() }, function (data) {
                alert(data.Type);
            });
        }
    });
    $("form").ligerForm();

});
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
                    window.location.href = "/Supplies/Workload/add/0";
                }
                else
                    window.location.href = "/Supplies/Workload/";
            });
        }
        else {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
}