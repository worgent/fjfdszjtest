//var CurID =  <%=CurID %>;
var Validator = null;
var managerCompany;
$(function () {
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
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
    $("form").ligerForm();
    deafultValidate($("form"));
    $("#ExamExamineeInfo_ExamTypeID").change(function () {
        var examtypestr = $("#ExamExamineeInfo_ExamTypeID").find("option:selected").text();
        selExamType(examtypestr);
    })
    selExamType($("#ExamExamineeInfo_ExamTypeID").val());
});

function selExamType(examtypestr) {
    if (examtypestr == "招聘") {
        $("#ExamExamineeInfo_FullName").removeAttr("readonly");
        $(":radio").attr("disabled", false);
        $("#ExamExamineeInfo_IDCardNumber").removeAttr("readonly");
        $("#ExamExamineeInfo_MobileNumber").removeAttr("readonly");
        $("#ExamExamineeInfo_DiplomaID").attr("disabled", false);
        $("#ExamExamineeInfo_BirthDate").attr("disabled", false);
        $("#selPersonnel").hide();
    }
    else {
        $("#ExamExamineeInfo_FullName").attr({ readonly: 'true' });
        $(":radio").attr("disabled", true);
        $("#ExamExamineeInfo_IDCardNumber").attr({ readonly: 'true' });
        $("#ExamExamineeInfo_MobileNumber").attr({ readonly: 'true' });
        $("#ExamExamineeInfo_DiplomaID").attr("disabled", true);
        $("#ExamExamineeInfo_BirthDate").attr("disabled", true);
        $("#selPersonnel").show();
    }
}

//选择人员信息
function f_selPersonnel() {
    var gridtype = "1";
    var URL = '/Exam/ExamineeInfo/SelectList/';
    var resu = window.showModalDialog(URL, window, 'help=0;status=0;dialogWidth=650px;dialogHeight=400px;');
    if (typeof (resu) != 'undefined') {
     for (var i = 0; i < resu.length; i++) {
            var stype = new Object();
            stype = resu[i];
            $("#ExamExamineeInfo_FullName").val(stype.FullName);
             if (stype.Sex == "0")
                 $("input[name='ExamExamineeInfo.Sex'][value=0]").attr("checked", true);
             else
                 $("input[name='ExamExamineeInfo.Sex'][value=1]").attr("checked", true);
             $("#ExamExamineeInfo_LoginName").val(stype.LoginName);
             $("#ExamExamineeInfo_IDCardNumber").val(stype.IDCardNumber);
             $("#ExamExamineeInfo_MobileNumber").val(stype.MobileNumber);
             $("#ExamExamineeInfo_DiplomaID").val(stype.DiplomaId);
             $("#ExamExamineeInfo_BirthDate").val(stype.BirthDate);
        }
    }
 }

 function f_checkData() {
     var LoginName = $("#ExamExamineeInfo_LoginName").val();
 $.ajax({
     type: 'GET', cache: false, dataType: 'json',
     url: "/exam/examineeinfo/Isloginnameexist?r='+new Date().getTime()",
     data: [
                   { name: 'Loginname', value: LoginName }
                   ],
     success: function (result) {
         if (result=="false") {
             $.ligerDialog.warn("考生登录名已存在!");
             return false;
         }
         else
             return true;
     },
     error: function (ex) {
         //debugger;
         //alert(ex);
         alert('发送系统错误,请与系统管理员联系!');
         return false;
     },
     beforeSend: function () {
     },
     complete: function () {
     }
 });

 }
 function f_save() {
     $(":radio").attr("disabled", false);        //取消不可用
     $("#ExamExamineeInfo_DiplomaID").attr("disabled", false);    //取消不可用
     $("#ExamExamineeInfo_BirthDate").attr("disabled", false);    //取消不可用
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
                         window.location.href = "/Exam/ExamineeInfo/add/0";
                     }
                     else
                         window.location.href = "/Exam/ExamineeInfo/";
                 });
             }
             else {
                 $.ligerDialog.success(result.Message, function () {
                     window.location.href = "/Exam/ExamineeInfo/";
                 });
             }
         }
         else {
             $.ligerDialog.closeWaitting();
             $.ligerDialog.error(result.Message, function () {
                 window.location.href = "/Exam/ExamineeInfo/";
             });
         }
     });
}

function f_windowclose() {
    window.location.href = "/Exam/ExamineeInfo/";
} 