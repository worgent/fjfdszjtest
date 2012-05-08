var ary = new Array();
$(function () {
    $("#newForm").ligerForm();
    deafultValidate($("#newForm"));
    var options = {
        dataType: 'json',      //期望服务器的的回应的类型。null、"xml"、"script"或者"json"其中之一。
        beforeSubmit: showRequest, //提交成功时执行
        error: showError, //提交错误时执行
        success: showResponse//后台返回后执行
    };
    $('#newForm').ajaxForm(options);

    $.metadata.setType("attr", "validate");
});

//提交成功时的数据
function showRequest(formData, jqForm, options) {
    $.ligerDialog.waitting("正在保存中...");
    return true;
}

//提交出错返回信息
function showError(data) {
    alert("showError " + data.Message);
}

//提交成功后跳转
function showResponse(data, status) {
    if (data.Type) {
        $.ligerDialog.success(data.Message);
        //添加巡检登记，将返回巡检计划页面；编辑巡检登记，将返回巡检登记页面
        if (data.Status == "Add") {
            window.location.href = "/Cop/TerminalPlan/Index"; //提交后，页面跳转的地址
        } else {
            window.location.href = "/Cop/TerminalEnrol/Index"; //提交后，页面跳转的地址
        }
    }
    else {
        $.ligerDialog.closeWaitting();
        alert(data.Message);
    }
}

//弹出框
function f_openWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastIndex").val());
                var resu = dialog.frame.f_getdata();

                if (resu != null) {
                    for (var i = 0; i < resu.length; i++) {
                        var bools = true;
                        var clie = new Object();
                        clie = resu[i];
                        //先判断该条数据是否已经添加
                        for (var j = 0; j < ary.length; ++j) {
                            if (ary[j] == clie.ID.toString()) {
                                bools = false;
                            }
                        }
                        if (bools == true) {
                            ary.push(clie.ID.toString());
                            AddEquipmentRow(clie);
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
