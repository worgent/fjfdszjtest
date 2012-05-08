function f_checkData() {
    if ($("#ExamTestMock_Pass").val() == "") {
        navtab.selectTabItem("testset");
        $.ligerDialog.warn("您没有设置分数百分比值。");
        return false;
    }
    if ($("#ExamTestMock_TimeLength").val() == "") {
        $.ligerDialog.warn("您没有设置考试时长。");
        return false;
    }
    return true;
}

var examineeranges = "";
var examstrAry = new Array();     //初始examineeID数组
$(function () {
    $.metadata.setType("attr", "validate");
    Validator = $("form").validate({
        errorPlacement: function (lable, element) {
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-text-field")) element.parent().ligerTip({ content: lable.html(), appendIdTo: lable });
            else lable.appendTo(element.parents("td:first").next("td"));
        },
        success: function (lable) {
            lable.ligerHideTip();
        }
    });
    $("form").ligerForm();
    deafultValidate($("form"));

    $("#InfoTab").ligerTab();
    navtab = $("#InfoTab").ligerGetTabManager(); //选项卡管理,可选中指定选项卡
    examineeranges = $("#examineeranges").val();
    examstrAry = examineeranges.split(',');      //存放初始examineeID数组

    f_setSystemUserGrid();
    $("#examineeranges").val(examstrAry[0]);  //只存放examineeID数组第一个元素 即主卷ID
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
    if (!Validator.form()) return false;
    //if (!f_checkData()) return;
    var result;
    result = f_systemuserget();
    if (result == false) {
        return;
    }
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        $.ligerDialog.closeWaitting();
        if (result.Type) {
            $.ligerDialog.success(result.Message, function () {
                parent.window.frames[frameid].f_reload();
            });
        }
        else {
            $.ligerDialog.error(result.Message, function () {
            });
        }
    });
}
//可选考生范围
function f_setSystemUserGrid() {
    systemusergridManager = $("#systemusergrid").ligerGrid({
        columns: [
                { display: '用户ID', name: 'ID', align: 'left', width: 100, minWidth: 90 },
                { display: '考生姓名', name: 'FullName', align: 'left', width: 70, minWidth: 50 }
                ], dataAction: 'server',
        // url: "../../Service/BillListData.ashx?Action=GetGridView&gridviewname=system_menu", sortName: 'id',
        url: "/Exam/ExamineeInfo/GridPager", sortName: 'id',
        width: '99%', pageSize: 10, height: 250,
        isChecked: f_isCheckedExamineeid,
        checkbox: true,
        heightDiff: -9,
        onError: function (a, b) {
            debugger
        }
    });
}
//选中上次保存的考生
function f_isCheckedExamineeid(rowdata) {

    for (i = 0; i < examstrAry.length; i++) {
        if (rowdata.ID == examstrAry[i]) {
            return true;
        }
    }
    return false;

}
//保存可选考生ID
function f_systemuserget() {
    var rowsdata = systemusergridManager.getCheckedRows();
    if (!rowsdata.length) {
        $.ligerDialog.alert("考生范围没有选择。");
        navtab.selectTabItem("examineerange");
        return false;
    }
    var str = "";
    $(rowsdata).each(function (i, item) {
        var subject = new Object();
        if (str != "") {
            str += ",";
        }
        str += this.ID;
    });
    $("#examineeranges").val(str);
    return true;
}


//分数百分比值输入验证
function f_onkeyup_pass() {
    if (isNaN($("#ExamTestMock_Pass").val())) {
        $("#ExamTestMock_Pass").val("");
    }
}

//考试时长输入验证
function f_onkeyup_timelength() {
    if (isNaN($("#ExamTestMock_TimeLength").val())) {
        $("#ExamTestMock_TimeLength").val("");
        return;
    }
}