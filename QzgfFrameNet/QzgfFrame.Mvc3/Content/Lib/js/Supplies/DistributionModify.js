//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
var orgids = new Array();
var gosArray = new Array(); //保存耗材,解决重复问题
var idsArray = new Array(); //保存耗材，最大数量，保存最大数量
var tidsArray = new Array(); //保存耗材设置选项
$(function () {
    //验证
    $.metadata.setType("attr", "validate");
    var v = $("form").validate({
        debug: true,
        errorPlacement: function (lable, element) {
            if (element.hasClass("l-textarea")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
            else if (element.hasClass("dd-text")) element.ligerTip({ content: lable.html(), appendIdTo: lable });
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
    var count = Number($("#TRLastIndex").val());
    for (var i = 0; i < count; i++) {
        var tid = $("#SuppliesTypeId" + i).val();
        gosArray.push(tid);
        var idsFlag = false;
        for (var j = 0; j < idsArray.length; j++) {
            if (idsArray[j].tid == tid) {
                idsFlag = true;
                idsArray[j].Num = idsArray[j].Num;
            }
        }
        if (idsFlag == false) {
            var gNum = new Object(); //gid,maxNum
            gNum.tid = tid;
            gNum.Num = parseInt($("#MaxNum" + i).val()) - parseInt($("#Num" + i).val());
            idsArray.push(gNum);
        }
    }
    if ($("#suppliesDistribution_State").val() == "2") {
        $("#showReason").show();
    }
    else if ($("#suppliesDistribution_State").val() == "1") {
        $("#showReason").hide();
    } 
});
function f_openWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastIndex").val());
                var rowFlag = false;
                if (rowID == "1" && $("#SuppliesTypeId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    var count = parseInt($("#Count").val());
                    for (var i = 0; i < resu.length; i++) {
                        var SuppliesType = new Object();
                        SuppliesType = resu[i];
                        var selFlag = true;
                        for (var k = 0; k < gosArray.length; k++) {
                            if (gosArray[k] == SuppliesType.SuppliesTypeId) {
                                selFlag = false;
                            }
                        }
                        var idsFlag = true;
                        var addgid = false;
                        var maxNum = SuppliesType.MaxNum;
                        if (selFlag) {
                            for (var j = 0; j < idsArray.length; j++) {
                                if (idsArray[j].tid == SuppliesType.SuppliesTypeId) {
                                    idsFlag = false;
                                    if ((idsArray[j].Num - 0) >= 0) {
                                        idsArray[j].Num = idsArray[j].Num - 0;
                                        addgid = true;
                                    }
                                }
                            }
                            if (idsFlag) {
                                var gNum = new Object(); //did,cid,maxNum
                                gNum.tid = SuppliesType.SuppliesTypeId;
                                if ((maxNum - 0) >= 0) {
                                    addgid = true;
                                    gNum.Num = maxNum - 0;
                                }
                                else {
                                    gNum.Num = 0;
                                    addgid = false;
                                }
                                idsArray.push(gNum);
                            }
                            if (addgid) {
                                gosArray.push(SuppliesType.SuppliesTypeId);

                                var tidflag = true;
                                for (var n = 0; n < tidsArray.length; n++) {
                                    if (tidsArray[n] == SuppliesType.SuppliesTypeId)
                                        tidflag = false;
                                }
                                if (tidflag)
                                    tidsArray.push(SuppliesType.SuppliesTypeId);

                                count++;
                                if (rowFlag) {
                                    $("#SuppliesTypeId0").val(SuppliesType.SuppliesTypeId);
                                    $("#SuppliesTypeName0").val(SuppliesType.SuppliesTypeName);
                                    $("#UnitName0").val(SuppliesType.UnitName);
                                    $("#Num0").val(1);
                                    $("#SuppliesTypeName0").removeClass("disable-dd-text");
                                    $("#UnitName0").removeClass("disable-dd-text");
                                    $("#Num0").removeClass("disable-dd-text");
                                    $("#SuppliesTypeName0").addClass("dd-text");
                                    $("#UnitName0").addClass("dd-text");
                                    $("#Num0").addClass("dd-text");
                                    rowFlag = false;
                                }
                                else {
                                    AddSuppliesRow(SuppliesType);
                                }
                            }
                        }
                    }
                    $("#Count").val(count);
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
function f_addh() {
    var cityid = $("#suppliesDistribution_CityId").val();
    var companyid = $("#suppliesDistribution_CompanyId").val();
    var strid = "";

    strid = cityid;
    strid += ";" + companyid;
    strid += ";" + GetTids();
    f_openWindow('/Supplies/SuppliesStock/SelSupIndex/?id=' + strid, '选择耗材', 700, 350);
}
function GetCount(i) {
    var num = $("#Num" + i).val().replace(/[ ]/g, "");
    if (num == "")
        $("#Num" + i).val(0);
    if (isNaN($("#Num" + i).val()))
        $("#Num" + i).val(0);
    var strMaxNum = parseInt($("#MaxNum" + i).val());
    var strNumFlag = parseInt($("#NumFlag" + i).val());
    for (var j = 0; j < idsArray.length; j++) {
        if (idsArray[j].tid == $("#SuppliesTypeId" + i).val()) {
            strMaxNum = idsArray[j].Num + strNumFlag;
            if (parseInt($("#Num" + i).val()) > parseInt(strMaxNum)) {
                $("#Num" + i).val(strMaxNum);
                idsArray[j].Num = idsArray[j].Num - parseInt(strMaxNum) + strNumFlag;
            }
            else
                idsArray[j].Num = idsArray[j].Num - parseInt($("#Num" + i).val()) + strNumFlag;

           
            break;
        }
    }
    var setNum = parseInt($("#Num" + i).val());
    $("#NumFlag" + i).val(setNum); 
    var count = GetAllNum();
    $("#Count").val(count);
}
function f_checkData() {
    var trlen = GetSuppliesLen();
    if (trlen <= 1) {
        f_addh();
        return false;
    }
    var idno = parseInt($("#TRLastIndex").val()) - 1;

    if ($("#SuppliesTypeId" + idno).val() == "") {
        f_addh();
        return false;
    }
    else if ($("#SuppliesTypeName" + idno).val() == "") {
        f_addh();
        return false;
    }
    else {
        return CheckNum();
    }
    return true;
}
function f_save() {
    if (!f_checkData()) return false; 

     $("#distributionDetails").val(JSON2.stringify(GetSuppliesRow()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
    
    fm.ajaxSubmit(function (result, success, form) {
    if (result.Type) {
    $.ligerDialog.closeWaitting();
    /**$.ligerDialog.success('保存成功!', function () {
        window.location.href = "/Supplies/Distribution/";
    });**/
    
    $.ligerDialog.confirm("是否返回管理页面?", '保存成功!', function (ok) {
        if (ok) {
    if($("#TypeFlag").val()=="YW")
        window.location.href = "/Supplies/Distribution/YwIndex?_menuid=" + $("#frameid").val()
    else if($("#TypeFlag").val()=="QX"&& $("#suppliesDistribution_State").val()=="2"){
        window.location.href = "/Supplies/Distribution/TRIndex?_menuid=" + $("#frameid").val()
    }
    else
        window.location.href = "/Supplies/Distribution/Index?_menuid=" + $("#frameid").val()
    }
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
    if ($("#TypeFlag").val() == "YW")
        window.location.href = "/Supplies/Distribution/YwIndex?_menuid=" + $("#frameid").val()
    else if ($("#TypeFlag").val() == "QX" && $("#suppliesDistribution.State").val() == "2") {
        window.location.href = "/Supplies/Distribution/TRIndex?_menuid=" + $("#frameid").val()
    }
    else
        window.location.href = "/Supplies/Distribution/Index?_menuid=" + $("#frameid").val()
} 