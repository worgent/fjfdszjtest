function f_checkData() {
    if ($("#ExamTestSet_Pass").val() == "") {
        navtab.selectTabItem("testset");
        $.ligerDialog.warn("您没有设置分数百分比值。");
        return false;
    }
    if ($("#ExamTestSet_TimeLength").val() == "") {
        $.ligerDialog.warn("您没有设置考试时长。");
        navtab.selectTabItem("testset");
        return false;
    }
    if ($("#ExamTestSet_BeginTime").val() == "") {
        $.ligerDialog.warn("您没有设置开始时间。");
        navtab.selectTabItem("testset");
        return false;
    }

    return true;
}

var typeid = "";
var testid = "";
var teststrAry = new Array();     //初始TestID数组
var examineeranges = "";
var examstrAry = new Array();     //初始examineeID数组
var navtab = null;
var Validator = null;
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
    typeid = $("#TestTypeID").val();
    testid = $("#testids").val();    //考卷可选卷ID
    examstrAry = testid.split(',');  //临时存放TestID
    testid = $("#ExamTestSet_TestID").val();  //考卷主卷ID
    //examstrAry存放teststrAry初始考卷可选ID数组
    for (i = 0; i < examstrAry.length; i++) {
        if (examstrAry[i] == "") {
            continue;
        }
        var testObj = new Object();       //试卷ID和State对象
        testObj["ID"] = examstrAry[i];
        testObj["State"] = "4";
        teststrAry.push(testObj);
    }
    examineeranges = $("#examineeranges").val();
    examstrAry = examineeranges.split(','); //存放初始examineeID数组
    if ($("#ExamTestSet_BeginTime").val() != "") {
        begintime = $("#ExamTestSet_BeginTime").val();
        $("#ExamTestSet_BeginTime").val(DateInitialization(begintime));
    }

    f_setGrid();
    f_setSystemUserGrid();
    //f_Browser();
    $("#examineeranges").val(examstrAry[0]);   //只存放examineeID数组第一个元素 即主卷ID
});

function f_Browser() {
    var element = document.getElementById("ExamTestSet_BeginTime");
    if ($.browser.msie) {
        element.onpropertychange = f_onkeyup;
    }
    else {
        element.attachEvent("input", f_onkeyup, false);
    }
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
    if (!Validator.form()) return false;
    //if (!f_checkData()) return;
    var result;
    result = f_get();
    if (result == false) {;
        return;
    }
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

//可选试卷表格
function f_setGrid() {
    gridManager = $("#maingrid").ligerGrid({
        columns: [
                { display: '试卷ID', name: 'TestID', align: 'left', width: 160, minWidth: 140 },
                { display: '试卷名称', name: 'TestName', align: 'left', width: 250, minWidth: 220 },
                { display: '总分', name: 'Points', align: 'left', width: 40, minWidth: 20 },
                { display: '试卷类型', name: 'TestType', align: 'left', width: 100, minWidth: 80 },
                { display: '创建时间', name: 'CreateTime', type: 'date', align: 'left', width: 120, minWidth: 90 },
                { display: '预览', name: 'ViewTestUrl', align: 'left', width: 70, minWidth: 50 }
                ], dataAction: 'server',
        // url: "../../Service/BillListData.ashx?Action=GetGridView&gridviewname=system_menu", sortName: 'id',
        url: "/Exam/Test/gridpager", sortName: 'id',
        width: '99%', pageSize: 10, height: 250,
        isChecked: f_isCheckedTestid,
        parms: [{ name: "gridsearch", value: typeid + "," + testid}],
        onCheckRow: function (checked, data, rowindex, rowobj) {
            if (checked) {
                var rows = gridManager.getCheckedRows();
                var count = 0;
                $(rows).each(function () {
                    count++;
                });
                if (count > 4) {
                    $.ligerDialog.alert("可选试卷不能超过4张。");
                }
            }

        },
        checkbox: true,
        heightDiff: -9,
        onError: function (a, b) {
            debugger
        }
    });
}

//选中上次保存的可选考卷
function f_isCheckedTestid(rowdata) {
    for (i = 0; i < teststrAry.length; i++) {
        if (rowdata.TestID == teststrAry[i].ID) {
            return true;
        }
    }
    return false;

}

//保存试卷ID字符串
function f_get() {
    var rowsdata = gridManager.getCheckedRows();
    if (rowsdata.length > 4) {
        $.ligerDialog.warn("可选试卷不能超过4张。")
        navtab.selectTabItem("testset");
        return false;
    }
    var testAry = new Array();
    $(rowsdata).each(function (i, item) {
        temptestid = this.TestID;
        //循环页面加载时保存的初始TestID数组
        for (i = 0; i < teststrAry.length; i++) {
            //行中的TestID在teststrAry中存在，移除存在的数组，剩下不存在的试卷ID
            if (temptestid == teststrAry[i].ID) {
                teststrAry.splice(i, 1);
                break;
            }
        }
        var testObj = new Object();       //试卷ID和State对象
        testObj["ID"] = temptestid;
        testObj["State"] = "4";
        testAry.push(testObj);
    });
    if (teststrAry.length > 0) {
        for (i = 0; i < teststrAry.length; i++) {
            teststrAry[i].State = "1";
            testAry.push(teststrAry[i]);
        }
    }
    $("#testids").val(JSON2.stringify(testAry));
    return true;
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
        width: '95%', pageSize: 10, height: 350,
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

//日期格式初始化(2011/01/01 12:00:00 改为 2011-01-01 12:00:00)
function DateInitialization(timestr) {
    var timearr = DateSplitTwo(timestr);
    var time = new Date(Number(timearr[0]), Number(timearr[1]) - 1, Number(timearr[2]), Number(timearr[3]), Number(timearr[4]), Number(timearr[5]));
    timeresult = DateConversion(time);
    return timeresult;
}

//日期拆分 2011-01-01 12:00:00
function DateSplit(time) {
    var timearr = time.replace(" ", ":").replace(/\:/g, "-").split("-");
    return timearr;
}

//日期拆分 2011/01/01 12:00:00 
function DateSplitTwo(time) {
    var timearr = time.replace(" ", ":").replace(/\:/g, "/").split("/");
    return timearr;
}

//日期格式转换 个位数补零  2011/1/1 12:0:0 转换为 2011/01/01 12:00:00 
function DateConversion(time) {
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    month = month >= 10 ? month.toString() : "0" + month.toString();
    var date = time.getDate();
    date = date >= 10 ? date.toString() : "0" + date.toString();
    var hours = time.getHours();
    hours = hours >= 10 ? hours.toString() : "0" + hours.toString();
    var minutes = time.getMinutes();
    minutes = minutes >= 10 ? minutes.toString() : "0" + minutes.toString();
    var seconds = time.getSeconds();
    seconds = seconds >= 10 ? seconds.toString() : "0" + seconds.toString();
    var result = year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
    return result;
}

function f_BeginTimeonPropertyChange() {
    f_onkeyup();
}

//分数百分比值输入验证
function f_onkeyup_pass() {
    if (isNaN($("#ExamTestSet_Pass").val())) {
        $("#ExamTestSet_Pass").val("");
    }
}

//考试时长输入验证
function f_onkeyup_timelength() {
    if (isNaN($("#ExamTestSet_TimeLength").val())) {
        $("#ExamTestSet_TimeLength").val("");
        return;
    }
}

function f_onkeyup() {
    //考试时长输入验证
    if (isNaN($("#ExamTestSet_TimeLength").val())) {
        $("#ExamTestSet_TimeLength").val("");
        return;
    }
    if ($("#ExamTestSet_BeginTime").val() == "") return;
    var TimeLength = 0;
    if ($("#ExamTestSet_TimeLength").val() == "" || $("#ExamTestSet_TimeLength").val() == undefined) TimeLength = 0;
    else TimeLength = Number($("#ExamTestSet_TimeLength").val());   

    var time = $("#ExamTestSet_BeginTime").val();

    var i = time.indexOf('-');   //根据不同格式选择对应的日期拆分方法

    var timearr = new Array();
    if (i > 0) timearr = DateSplit(time);
    else timearr = DateSplitTwo(time);

    var begintime = new Date(Number(timearr[0]), Number(timearr[1]) - 1, Number(timearr[2]), Number(timearr[3]), Number(timearr[4]) + TimeLength, Number(timearr[5]));
    var endtime = DateConversion(begintime);

    $("#ExamTestSet_EndTime").val(endtime);
}
