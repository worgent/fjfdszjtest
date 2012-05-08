function f_checkData() {
    var subjectFrame = findObj("subjectFrame", document);    //遍历每一个选择题选项，选项内容是否为空
    for (i = 1; i < subjectFrame.rows.length; i++) {    
        if (subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].value == "") {
            subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].focus();
            return false;
        }
        if (subjectFrame.rows[i].cells[3].getElementsByTagName("input")[0].value == "") {
            subjectFrame.rows[i].cells[3].getElementsByTagName("input")[0].focus();
            return false;
        }
        if (subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].value == "0") {
            subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].focus();
            $.ligerDialog.warn("您填写的相关题型数量为0");
            return false;
        }
        if (subjectFrame.rows[i].cells[3].getElementsByTagName("input")[0].value == "0") {
            subjectFrame.rows[i].cells[3].getElementsByTagName("input")[0].focus();
            return false;
        }
    }
    return true;
}

function f_onkeyup(rowid, cellid) {
    var signFrame = findObj("subjectFrame", document);
    var number = signFrame.rows[rowid].cells[cellid].getElementsByTagName("input")[0].value;
    //判断是否数字
    if (isNaN(number)) {
        signFrame.rows[rowid].cells[cellid].getElementsByTagName("input")[0].value = "";
        return;
    }
    if (number != "0" && number.substr(0, 1) == "0") {     //去掉数字前面的0 如09
        number = number.substr(1);
        signFrame.rows[rowid].cells[cellid].getElementsByTagName("input")[0].value = number;
    }
}

$(function () {
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
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
    $("#form").ligerForm();

//    deafultValidate($("#newForm"));
//    Validator = $("#newForm").validate();
//    var options = {
//        dataType: 'json',
//        beforeSubmit: showRequest,
//        error: showError,
//        success: showResponse
//    };
//    $('#newForm').ajaxForm(options);
});

function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    if (!f_checkData()) return false;
    $("#getsubjects").val(JSON2.stringify(GetSubjectRow()));
    $.ligerDialog.waitting("正在保存中...");
    return true;
}
function showError(data) {
    alert(data.Message);
}
function showResponse(data, status) {
    if (data.Type) {
        $.ligerDialog.success(data.Message);
        //alert(data.Message);
        window.location.href = "/Exam/Test/ModifyHand/" + data.ID;
    }
    else {
        $.ligerDialog.closeWaitting();
        //alert(data.Message);
        $.ligerDialog.error(data.Message);
    }
}

function f_save() {
    if (!f_checkData()) return false;
    $("#getsubjects").val(JSON2.stringify(GetSubjectRow()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.success(result.Message, function () {
                window.location.href = "/Exam/Test/ModifyHand/" + result.ID;
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
    window.location.href = "/Exam/Test/";
} 