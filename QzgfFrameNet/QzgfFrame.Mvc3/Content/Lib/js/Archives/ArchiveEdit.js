//var CurID =  <%=CurID %>;
var Validator = null;
$(function () {
    $.metadata.setType("attr", "validate");
    $("form").ligerForm();
    deafultValidate($("form"));
    Validator = $("form").validate();
});

function f_success() {
    $(document).ready(function () {
        $.ligerDialog.confirm("是否继续编辑", "保存成功", function (ok) {
            $.ligerDialog.closeWaitting();
            parent.window.frames["mainframe"].f_reload();
            if (!ok) {
                parent.window.frames["mainframe"].f_closeWindow();
            }
        });
    });
}
function f_error(message) {
    $(document).ready(function () {
        $.ligerDialog.error(message);
    });
}
function f_save(frameid) {
    if (!Validator.form()) return;
   $.ligerDialog.waitting("正在保存中...");
    //提交数据
   
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        $.ligerDialog.closeWaitting();
        if (result.Type) {
            $.ligerDialog.success('保存成功!', function () {
                parent.window.frames[frameid].f_reload();
            });
        }
        else {
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
    //document.forms[0].submit();
} 