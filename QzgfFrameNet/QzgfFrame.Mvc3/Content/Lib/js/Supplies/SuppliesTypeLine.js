/**添加行**/
function AddSuppliesRow(SuppliesType) { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("SuppliesFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignSuppliesItem" + rowID;
    newTR.className = "bno";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(0);
    newDeleteTD.className = "bdno";
    newDeleteTD.innerHTML = "<div align='center' style='width:40px;margin-left:25px;'><a href='javascript:;' onclick=\"DelSuppliesRow('SignSuppliesItem" + rowID + "')\">删除</a></div>";
    
    //添加列:耗材类型
    var newEmailTD = newTR.insertCell(1); newEmailTD.className = "sno";
    newEmailTD.innerHTML = "<input name='SuppliesTypeName" + rowID + "' id='SuppliesTypeName" + rowID + "' type='text' size='40'   class = 'disable-dd-text'  readonly='readonly' value='" + SuppliesType.SuppliesTypeName.toString() + "' />";

    //添加列: 耗材类型单位
    var newNameTD = newTR.insertCell(2);

    newNameTD.innerHTML = "<input type='hidden' value='" + SuppliesType.SuppliesTypeId.toString() + "' name='SuppliesTypeId" + rowID + "' id='SuppliesTypeId" + rowID + "'/><input type='text' name='UnitName" + rowID + "' id='UnitName" + rowID + "'  class = 'disable-dd-text'  readonly='readonly' value='" + SuppliesType.UnitName.toString() + "'/>";
    //添加列: 数量
    var newNumTD = newTR.insertCell(3);

    newNumTD.innerHTML = "<input type='text' name='Num" + rowID + "' id='Num" + rowID + "' onkeyup='GetCount(" + rowID + ")'  class = 'dd-text' value='' validate = '{required:true,isNumCode:true}'/>";
     //添加列: 备注
    var newRemarkTD = newTR.insertCell(4);
    newRemarkTD.innerHTML = " <textarea  cols='350' rows='2' class = 'l-textarea' name='Remark" + rowID + "' id='Remark" + rowID + "' style='width:350px' validate = '{required:false,minlength:0,maxlength:50}'></textarea>";
   //添加列:序号
    var newNoTD = newTR.insertCell(5);
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
}
//删除指定行
function DelSuppliesRow(rowid) {
    var signFrame = findObj("SuppliesFrame", document);
    var signItem = findObj(rowid, document);
    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    var Count = parseInt($("#Count").val());
    var Num = signFrame.rows[rowIndex].cells[3].getElementsByTagName("input")[0].value;
    var num = Num.replace(/[ ]/g, "");
    if (num == "")
        Num = 0;
    Count = Count - parseInt(Num);
    $("#Count").val(Count)
    var SuppliesTypeId = signFrame.rows[rowIndex].cells[2].getElementsByTagName("input")[0].value;
    gosArray.remove(SuppliesTypeId);
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].cells[5].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
    }
}
//获取总行数
function GetSuppliesLen() {
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    return rowscount;
}
//获取总数量
function GetAllNum() {
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    var ZNum = 0;
    for (i = 1; i < rowscount; i++) {
        var Num = signFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        var num = Num.replace(/[ ]/g, "");
        if (num == "")
            Num = 0;
        ZNum += parseInt(Num);
    }
    return ZNum;
}
//验证总数量
function CheckNum() {
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    var ZNum = 0;
    for (i = 1; i < rowscount; i++) {
        var Numobj = signFrame.rows[i].cells[3].getElementsByTagName("input")[0];
        var Num = Numobj.value;
        var strnum = Num.replace(/[ ]/g, "");
        if (strnum == "" || parseInt(strnum) == 0) {
            $("#displyMsg").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;★ 存在数量为空或为零的耗材！！");
            Numobj.value = "";
            Numobj.focus();
            return false;
        }
    }
    return true;
}
//保存耗材信息
function GetSuppliesRow() {
    var ary = new Array();
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var SuppliesTypeName = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        var SuppliesTypeId = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        var UnitName = signFrame.rows[i].cells[2].getElementsByTagName("input")[1].value;
        var Num = signFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        var Remark = signFrame.rows[i].cells[4].getElementsByTagName("textarea")[0].value;
        var strobj = new Object();
        strobj["SuppliesTypeId"] = SuppliesTypeId;
        strobj["SuppliesTypeName"] = SuppliesTypeName;
        strobj["UnitName"] = UnitName;
        strobj["Num"] = Num;
        strobj["Remark"] = Remark;
        ary.push(strobj);
    }
    return ary;
}


/*加载数据设备型号信息*/
function getData(val) {
    var arrData = new Array();
    if (val != "") {
        $.ajax({
            type: "post",
            async: false, //控制同步
            url: "/Archives/SuppliesType/GetSuppliesType/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var objData = new Object();
                    var strid = [];
                    var strval = val.toLowerCase();
                    var strCode = (strdata[i].suppliesTypeName).toLowerCase();
                    if (strCode.indexOf(strval) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].unitName);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].suppliesTypeName;
                        arrData.push(objData);
                    }
                }
            },
            Error: function (err) {
                $.ligerDialog.error(err, function () {
                });
            }
        });
    }
    return arrData;
}
//清除已被删除的orgids
function GetTids() {
    var ary = new Array();
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    for (var n = 0; n < tidsArray.length; n++) {
        var tidflag = true;
        for (i = 1; i < rowscount; i++) {
            var SuppliesTypeId = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
            if (tidsArray[n] == SuppliesTypeId)
                tidflag = false;
        }
        if (tidflag)
            ary.push(tidsArray[n]);
    }
    for (var k = 0; k < ary.length; k++) {
        tidsArray.remove(ary[k]);
    }
    return tidsArray;
}