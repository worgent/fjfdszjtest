//var CurID =  <%=CurID %>;
var Validator = null;
var FactoryList = null;
var EquipModelList = null;
var orgids = new Array();
var gosArray = new Array(); //保存区县，公司，耗材,解决重复问题
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

    $("#DistrictName0").addClass("disable-dd-text");
    $("#SuppliesTypeName0").addClass("disable-dd-text");
    $("#UnitName0").addClass("disable-dd-text");
    $("#Num0").addClass("disable-dd-text");
});
function f_openWindow(url, title, width, height) {
    var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '确认', onclick: function (item, dialog) {
                var rowID = parseInt($("#TRLastIndex").val());
                var rowFlag = false;
                if (rowID == "1" && $("#DistrictId0").val() == "")
                    rowFlag = true;
                var resu = dialog.frame.f_getdata();
                if (resu != null) {
                    // var count = parseInt($("#Count").val());
                    var orgarys = resu[0];
                    orgids.length = 0;
                    for (var m = 0; m < orgarys.length; m++) {
                        var orgId = new Object();
                        orgId = orgarys[m];
                        orgids.push(orgId);
                    }
                    var arys = resu[1];
                    for (var i = 0; i < arys.length; i++) {
                        var SuppliesType = new Object();
                        SuppliesType = arys[i];
                        var selFlag = true;
                        for (var k = 0; k < gosArray.length; k++) {
                            var goObject = gosArray[k];
                            if (goObject.did == SuppliesType.DistrictId && goObject.cid == SuppliesType.CompanyId && goObject.tid == SuppliesType.SuppliesTypeId) {
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
                                    if ((idsArray[j].Num - 1) >= 0) {
                                        idsArray[j].Num = idsArray[j].Num - 0;
                                        addgid = true;
                                    }
                                }
                            }
                            if (idsFlag) {
                                var gNum = new Object(); //did,cid,maxNum
                                gNum.tid = SuppliesType.SuppliesTypeId;
                                if ((maxNum - 1) >= 0) {
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
                                var goObject = new Object(); //gid,orgid
                                goObject.did = SuppliesType.DistrictId;
                                goObject.cid = SuppliesType.CompanyId;
                                goObject.tid = SuppliesType.SuppliesTypeId;
                                gosArray.push(goObject);

                                var tidflag = true;
                                for (var n = 0; n < tidsArray.length; n++) {
                                    if (tidsArray[n] == SuppliesType.SuppliesTypeId)
                                        tidflag = false;
                                }
                                if (tidflag)
                                    tidsArray.push(SuppliesType.SuppliesTypeId);
                                //count++;
                                if (rowFlag) {
                                    $("#DistrictId0").val(SuppliesType.DistrictId);
                                    $("#DistrictName0").val(SuppliesType.DistrictName);
                                    $("#SuppliesTypeId0").val(SuppliesType.SuppliesTypeId);
                                    $("#SuppliesTypeName0").val(SuppliesType.SuppliesTypeName);
                                    $("#UnitName0").val(SuppliesType.UnitName);
                                    $("#Num0").val("");
                                    $("#Num0").removeClass("disable-dd-text");
                                    $("#Num0").addClass("dd-text");
                                    rowFlag = false;
                                }
                                else {
                                    AddSuppliesRow(SuppliesType);
                                }
                            }
                        }
                    }
                    //$("#Count").val(count);
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

    var strid = "";
    var cid = "";
    var did ="";
    var tid = "";
    var cids = new Array();
    var dids = new Array();
    var aryOrg = GetOrgIds();
    for (var i = 0; i < aryOrg.length; i++) {
        var orgId = new Object(); orgId = orgids[i];
        var didFlag=true;var cidFlag=true;
        for (var j = 0; j < dids.length; j++) {
            if (dids[j] == orgId["DistrictId"]) {
                didFlag = false;
            }
        }
        if (didFlag) {
            dids.push(orgId["DistrictId"]);
        }
    }
    for (var i = 0; i < dids.length; i++) {
        if (did == "")
            did = dids[i];
        else
            did += ";" + dids[i];
    }
    for(var i=0;i<cids.length;i++){
    if (cid == "")
                cid = cids[i];
            else
                cid += ";" +  cids[i];
    }
    strid=did;
    strid+=":"+GetTids();

    f_openWindow('/Supplies/SuppliesStock/SelIndex/?id=' + strid, '选择耗材', 700, 350);
}
function GetCount(i) {
    var num = $("#Num" + i).val().replace(/[ ]/g, "");
    if (num == "")
        $("#Num" + i).val(1);
    if (isNaN($("#Num" + i).val()))
        $("#Num" + i).val(1);
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
    if ($("#DistrictId" + idno).val() == "") {
       f_addh();
        return false;
    }
    else if ($("#DistrictName" + idno).val() == "") {
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
    $("#OrgIds").val(JSON2.stringify(GetOrgIds()));
    $.ligerDialog.waitting("正在保存中...");
    //提交数据
    var fm = $("#newForm");
    //提交表单  result为返回的数据，success为成功,form为提交的form
   
    fm.ajaxSubmit(function (result, success, form) {
    if (result.Type) {
    $.ligerDialog.closeWaitting();
    $.ligerDialog.confirm("是否继续添加记录?", '保存成功!', function (ok) {
    if (ok) {
        window.location.href = "/Supplies/Distribution/add/?TypeFlag=QX&id=0&frameid=" + $("#frameid").val();
    }
    else
        window.location.href = "/Supplies/Distribution?_menuid=" + $("#frameid").val();
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
    window.location.href = "/Supplies/Distribution?_menuid=" + $("#frameid").val();
} 