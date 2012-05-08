function f_checkData() {
    if ($("#ExamTestContent_Name").val() == "") {
        name.focus();
        //$.ligerDialog.warn("没有设置试卷名称");
        return false;
    }
    var points = findObj("ExamTestContent_Points", document);
    var CurrentPoints = findObj("CurrentPoints", document);
    if (points.value == "") {
        points.focus();
        //$.ligerDialog.warn("没有设置试卷总分");
        return false;
    }

    var typeFrame = findObj("typeFrame", document);   //试卷题型表格
    var count = typeFrame.rows.length;            //试卷题型表格行数等于对应的试题列表的表格数量
    if (count == 1) {
        $.ligerDialog.warn("新增题型为空。");
        return false;
    }
    var IsSubject = false;   //是否有试题
    for (i = 1; i < count; i++) {
        var addtable = findObj("addtestsubjecttable" + i, document);
        var typename = addtable.rows[0].cells[0].getElementsByTagName("input")[0].name   //获得试题类型名称
        var subjectFrame = findObj("subjectFrame" + i, document);    //遍历每一个试题列表表格，查找分数没有设置或为0的。
        if (subjectFrame.rows.length > 1) {
            IsSubject = true;
            for (j = 1; j < subjectFrame.rows.length; j++) {
                if (subjectFrame.rows[j].cells[3].getElementsByTagName("input")[0].value == "" || subjectFrame.rows[j].cells[3].getElementsByTagName("input")[0].value == 0) {
                    //subjectFrame.rows[j].cells[3].getElementsByTagName("input")[0].focus();
                    $.ligerDialog.warn(typename + "的第" + j + "小题分值未设置。");
                    return false;
                }
            }
        }
    }
    if (IsSubject == false) {
        $.ligerDialog.warn("新增试题为空。");
        return false;
    }

    if (CurrentPoints.value != points.value) {

        $.ligerDialog.warn('试卷总分"' + points.value + '"分和当前总分"' + CurrentPoints.value + '"不相等，请修改试卷总分或者试题分值');
        return false;
    }
    return true;
}

var Validator = null;
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
    $("#testsubjecttypes").val(JSON2.stringify(GetTestSubjectTypeRow()));
    $("#testsubjects").val(JSON2.stringify(GetTestSubjectRow()));
    $.ligerDialog.waitting("正在保存中...");
    return true;
}
function showError(data) {
    alert(data.Message);
}
function showResponse(data, status) {
    if (data.Type) {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.success(data.Message, function () {
            window.location.href = "/Exam/Test";
        }); 
    }
    else {
        $.ligerDialog.closeWaitting();
        $.ligerDialog.error(data.Message);
    }
}

function f_save() {
    if (!f_checkData()) return false;
    $("#testsubjecttypes").val(JSON2.stringify(GetTestSubjectTypeRow()));
    $("#testsubjects").val(JSON2.stringify(GetTestSubjectRow()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    fm.ajaxSubmit(function (result, success, form) {
        if (result.Type) {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.success(result.Message, function () {
                window.location.href = "/Exam/Test";
            }); 
        }
        else {
            $.ligerDialog.closeWaitting();
            $.ligerDialog.error(result.Message, function () {
            }); 
        }
    });
}

function f_ondblclick(rowid) {
    var signFrame = findObj("typeFrame", document);
    signFrame.rows[rowid].cells[2].getElementsByTagName("input")[0].focus();
}
//输入当前分数
function f_onkeyup_Points() {
    if (isNaN($("#ExamTestContent_Points").val())) {
        $("#ExamTestContent_Points").val("");
    }
}

//分数文本框输入
function f_onkeyup(tableid, rowid) {
    var signFrame = findObj(tableid, document);
    var signItem = findObj(rowid, document);
    var score = signItem.cells[3].getElementsByTagName("input")[0].value;;
    //判断是否数字
    if (isNaN(score)) {
        signItem.cells[3].getElementsByTagName("input")[0].value = $("#temppoints").val();
        return;
    }  
    if (score!="0" && score.substr(0, 1) == "0") {     //去掉数字前面的0 如09
        score = score.substr(1);
        signItem.cells[3].getElementsByTagName("input")[0].value = score;
    }
    var subjectPointsTxt = signFrame.rows[0].cells[3].innerText;         //试题列表总分
    subjectPointsTxt = subjectPointsTxt.replace(/\s+/g, "");    
    subjectPointsTxt = subjectPointsTxt.substr(3)      //去掉“总分(”
    var subjectPoints = subjectPointsTxt.substr(0, (subjectPointsTxt.length - 1));  //去掉“)”
    var CurrentPoints = $("#CurrentPoints").val();
    var temppoints = $("#temppoints").val();
    subjectPoints = Number(subjectPoints) + Number(score) - Number(temppoints);
    signFrame.rows[0].cells[3].innerHTML = "总分(" + subjectPoints + ")";
    $("#CurrentPoints").val(Number(CurrentPoints) + Number(score) - Number(temppoints));
    $("#temppoints").val(score);
}

//分数文本框获得焦点
function f_onFocus(tableid, rowid) {
    var signFrame = findObj(tableid, document);
    var signItem = findObj(rowid, document);
    var score = signItem.cells[3].getElementsByTagName("input")[0].value;
    $("#temppoints").val(score);
}

//试题内容文本框获得焦点
function f_onFocusSub(tableid, rowid) {
    var signFrame = findObj(tableid, document);
    var signItem = findObj(rowid, document);
    var content = signItem.cells[2].getElementsByTagName("input")[0].value;  //试题内容
    $("#tempsubjectname").val(content);
}

//试题内容文本框输入
function f_onkeyupSub(tableid, rowid) {
    var signFrame = findObj(tableid, document);
    var signItem = findObj(rowid, document);
    signItem.cells[2].getElementsByTagName("input")[0].value = $("#tempsubjectname").val();
}

//添加试题类型
function f_addtype() {
    var resu = window.showModalDialog('/Exam/SubjectType/Display', window, 'help=0;status=0;dialogWidth=650px;dialogHeight=400px;');
    if (typeof (resu) != 'undefined') {
        for (var i = 0; i < resu.length; i++) {
            var stype = new Object();
            stype = resu[i];
            AddTestSubjectTypeRow(stype);
            //f_showsubmit();
            AddTestSubjectFrame(stype);
            AddPreviewTestSubjectFrame(stype);
        }
    }
}
//添加试题
function f_addsubject(subjecttypeid,tableid) {
    var obj = $("#ExamTestContent_TypeID")[0];

    var coursetypeid = obj.options[obj.selectedIndex].value.toString();
    var prams = subjecttypeid + ',' + coursetypeid
    var URL = '/Exam/Subject/Display/' + prams;
    f_openWindow(URL, '选择题库试题', 650, 400,tableid);
}

//打开试题窗口
function f_openWindow(url, title, width, height,tableid) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var resu = dialog.frame.f_getdata();
                dialog.close();
                if (resu != null) {
                   
                    var subjects = [];
                    var isTrue = false;
                    for (a = 0; a < resu.length; a++) {

                        isTrue = f_findSubject(resu[a]);  //验证选择的试题是否已经存在
                        if (isTrue == true) {
                            subjects.push(resu[a]);
                        }
                    }
                    if (subjects.length > 0) {
                        f_getAjaxSubject(tableid, subjects, 0);
                    }
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

 //查找试题是否存在
 function f_findSubject(subject) {
     var typeFrame = findObj("typeFrame", document);   //试卷题型表格
     var count = typeFrame.rows.length;            //试卷题型表格行数等于对应的试题列表的表格数量
     for (i = 1; i < count; i++) {
         //var testsubject = findObj("testsubject", document);
         //var SubjectDiv = findObj("SubjectDiv" + i, document);
         var addtable = findObj("addtestsubjecttable" + i, document);
         var typename = addtable.rows[0].cells[0].getElementsByTagName("input")[0].name   //获得试题类型名称
         var subjectFrame = findObj("subjectFrame" + i, document);    //遍历每一个试题列表表格，查找分数没有设置或为0的。
         for (j = 1; j < subjectFrame.rows.length; j++) {
             if (subjectFrame.rows[j].cells[1].getElementsByTagName("input")[0].value == subject.ID) {
                 $.ligerDialog.warn(typename + "的第" + j + "小题已经使用了您所选择的试题，将不会重复添加到试题中。");
                 return false;
             }
         }
     }
     return true;
 }

//$.Ajax GET方式取试题的题型内容
function f_getAjaxSubject(tableid,subject,index) {
    var URL = "";
    //查找试题对应的试题内容
    switch (subject[index].SubjectType) {
        case "选择题":
            URL = "/Exam/MultipleSubject/GetList";
            break;
        case "填空题":
            URL = "/Exam/FillBlanksSubject/GetList";
            break;
        case "判断题":
            URL = "/Exam/JudgeSubject/GetList";
            break;
    }
    $.ajax({
        type: 'GET', cache: false, dataType: 'json',
        url: URL,
        data: [
                   { name: 'id', value: subject[index].ID }
                   ],
        success: function (result) {


            if (result == null) {

            }
            else {
                AddTestSubjectRow(tableid, subject[index]);
                var subObj = result;
                AddPreviewTestSubjectRow(tableid, subject[index], subObj);
                //index++;
                if (++index < subject.length) {
                    f_getAjaxSubject(tableid, subject, index);
                }
            }
        },
        error: function (ex) {
            //debugger;
            //alert(ex);
            alert('发送系统错误,请与系统管理员联系!');
        },
        beforeSend: function () {
        },
        complete: function () {
        }
    });
}

//隐藏增加试题按钮
function f_hidesubmit() {
    $("#addtestsubject").hide();
}

//显示增加试题按钮
function f_showsubmit() {
    $("#addtestsubject").show();
}

function f_windowclose() {
    window.location.href = "/Exam/Test/";
} 
