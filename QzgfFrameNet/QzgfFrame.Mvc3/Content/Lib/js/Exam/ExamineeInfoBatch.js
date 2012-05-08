//var CurID =  <%=CurID %>;
var saveary = new Array();
$(function () {
    f_setGrid();

});

//表格—选中多行记录
function f_setGrid() {
    gridManager = $("#maingrid").ligerGrid({
        columns: [
                { display: '人员姓名', name: 'FullName', align: 'left', width: 130, minWidth: 60 },
                { display: '身份证号码', name: 'IDCardNumber', align: 'left', width: 130, minWidth: 60 },
                { display: '手机号码', name: 'MobileNumber', align: 'left', width: 130, minWidth: 60 },
                { display: '出生日期', name: 'BirthDate', type: 'date', align: 'left', width: 130, minWidth: 60 },
                { display: '操作', name: 'Id', type: 'download', link: '/Resources/Personnel/Display/', linkName: '查看详细', align: 'left', width: 130, minWidth: 60 }
                ], dataAction: 'server',
        url: "/Exam/ExamineeInfo/selectgridpager", sortName: 'id',
        width: '99%', height: '100%', pageSize: 13,
        onCheckRow: function (checked, data, rowindex, rowobj) {
            $("#surelist").val("");
        },
        checkbox: true,
        heightDiff: -9,
        onError: function (a, b) {
            debugger
        }
    });
}


function f_getdata() {
    var rowsdata = gridManager.getCheckedRows();
    if (!rowsdata.length) {
        $.ligerDialog.alert('请先选择行!');
        return null;
    }
    var ary = new Array();
    $(rowsdata).each(function (i, item) {
        var items = new Object();
        items["FullName"] = this.FullName;
        items["LoginName"] = this.Id;
        items["Sex"] = this.Sex;
        items["IDCardNumber"] = this.IDCardNumber
        items["MobileNumber"] = this.MobileNumber
        items["DiplomaID"] = this.DiplomaId
        var birthdate = f_convertDate(this.BirthDate, "yyyy-MM-dd");
        items["BirthDate"] = birthdate;
        items["ExamTypeID"] = $("#ExamType").val();
        ary.push(items);
    });
    return ary;
}

//$.Ajax GET方式验证考生登录名是否存在
//参数：  表格数组，表格数组索引
function f_getAjaxItem(ary, index) {
    $.ajax({
        type: 'GET', cache: false, dataType: 'json',
        url: "/exam/examineeinfo/Isloginnameexist?r='+new Date().getTime()",
        data: [
                   { name: 'Loginname', value: ary[index].LoginName }
                   ],
        success: function (result) {
            if (result) {     //不存在
                saveary.push(ary[index]);
            }
            if (++index < ary.length) {
                f_getAjaxItem(ary, index);
            }
            else {
                if (saveary.length == 0) {
                    $("#surelist").val("no");
                    $.ligerDialog.warn("本次选择的考生已存在!");
                }
                else {
                    $("#examineeinfos").val(JSON2.stringify(saveary));
                    $.ligerDialog.waitting("正在保存中...");
                    //提交数据
                    var fm = $("#newForm");
                    //提交表单  result为返回的数据，success为成功,form为提交的form
                    fm.ajaxSubmit(function (result, success, form) {
                        $.ligerDialog.closeWaitting();
                        if (result.Type) {
                            $.ligerDialog.success(result.Message, function () {
                                    parent.window.frames["qzgfframe20120203151706499135"].f_reload();
                            });
                        }
                        else {
                            $.ligerDialog.error(result.Message, function () {
                            });
                        }
                    });
                }
            }


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

function f_save(frameid) {
    saveary.length = 0;
    var ary = f_getdata();
    if (ary != null) {
        f_getAjaxItem(ary, 0);
    }
}

function f_windowclose() {
    window.location.href = "/Exam/ExamineeInfo/";
} 