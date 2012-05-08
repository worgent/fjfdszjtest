//var CurID =  <%=CurID %>;
var Validator = null;

$(function () {
    /**处理下拉框
    $("#ParentName").ligerComboBox({
        selectBoxWidth: 200, treeLeafOnly: false,
        selectBoxHeight: 150, valueFieldID: 'ParentId', textFieldID: 'ParentName',
        tree: { url: '/archives/district/getfather/' + $("#ParentId").val(), checkbox: false, topParentIDValue: -1, idFieldName: 'id', parentIDFieldName: 'pid' }
    });**/
    $("#selParent").click(function () {
        f_openWindow('/archives/district/SelDistrict/' + $("#ParentId").val(), '选择区县结点', 400, 400);
    });
    $("#ParentName").focus(function () {
        f_openWindow('/archives/district/SelDistrict/' + $("#ParentId").val(), '选择区县结点', 400, 400);
    });
    $.metadata.setType("attr", "validate");
    $("form").ligerForm();
    deafultValidate($("form"));
    Validator = $("form").validate();
});
function f_openWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var resu = dialog.frame.f_getdata();                
                if (resu != null) {
                    for (var i = 0; i < resu.length; i++) {
                        var strobj = new Object();
                        strobj = resu[i];
                        $("#ParentId").val(strobj.id);
                        $("#ParentName").val(strobj.text);
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