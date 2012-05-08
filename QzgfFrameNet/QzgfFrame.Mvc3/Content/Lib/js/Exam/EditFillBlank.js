function f_checkData() {
    if ($("#ExamSubContent_Content").val() == "") {   //试题内容
        $("#ExamSubContent_Content").focus();
        return false;
    }
    if ($("#ExamSubContent_Content").val().length > 255) {
        $.ligerDialog.warn("您输入的试题内容长度超过限制!");
        return false;
    }
    var subjectFrame = findObj("subjectFrame", document);    //遍历每一个选择题选项，选项内容是否为空
    for (i = 1; i < subjectFrame.rows.length; i++) {    
        if (subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].value == "") {
            subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].focus();
            return false;
        }
    }
    return true;
}

$(function () {
//    $.metadata.setType("attr", "validate");
//    $("#newForm").ligerForm();
//    deafultValidate($("#newForm"));
//    Validator = $("#newForm").validate();
//    var options = {
//        dataType: 'json',
//        beforeSubmit: showRequest,
//        error: showError,
//        success: showResponse
//    };
//    $('#newForm').ajaxForm(options);
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
            if (f_checkData()) {
                f_save();
            }
        }
    });
    $("form").ligerForm();
});
function f_save() {
    $("#fillblanks").val(JSON2.stringify(GetFillBlankRow()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            if ($("#state").val() == "add") {
                $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
                    if (ok) {
                        window.location.href = "/Exam/Subject/EditFillBlank?id=0";
                    }
                    else
                        window.location.href = "/Exam/Subject/";
                });
            }
            else {
                $.ligerDialog.success(result.Message, function () {
                    window.location.href = "/Exam/Subject/";
                });
            }
        }
        else {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
}
function showRequest(formData, jqForm, options) {
    if (!Validator.form()) return false;
    if (!f_checkData()) return false;
    $("#fillblanks").val(JSON2.stringify(GetFillBlankRow()));
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
        window.location.href = "/Exam/Subject/";
    }
    else {
        $.ligerDialog.closeWaitting();
        //alert(data.Message);
        $.ligerDialog.error(data.Message);
    }
}

function f_windowclose() {
    window.location.href = "/Exam/Subject/";
} 
