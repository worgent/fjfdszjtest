﻿/**添加行**/
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
    newDeleteTD.innerHTML = "<div align='center' style='width:40px;margin-left:25px;'><a href='javascript:;' onclick=\"DelSuppliesRow('SignSuppliesItem" + rowID + "')\">删除</a></div>";
    
    //添加列:耗材类型
    var newEmailTD = newTR.insertCell(1); newEmailTD.className = "sno";
    newEmailTD.innerHTML = "<input type='hidden' value='" + SuppliesType.SuppliesTypeId.toString() + "' name='SuppliesStockId" + rowID + "' id='SuppliesTypeId" + rowID + "'/><input name='SuppliesTypeName" + rowID + "' id='SuppliesTypeName" + rowID + "' type='text' size='40'    value='" + SuppliesType.SuppliesTypeName.toString() + "' class = 'disable-dd-text'  readonly='readonly' />";

    //添加列: 耗材类型单位
    var newNameTD = newTR.insertCell(2);

    newNameTD.innerHTML = "<input type='text' name='UnitName" + rowID + "' id='UnitName" + rowID + "'value='" + SuppliesType.UnitName.toString() + "'  class = 'disable-dd-text'  readonly='readonly'/>";
    //添加列: 数量
    var newNumTD = newTR.insertCell(3);

    newNumTD.innerHTML = "<input type='text' name='Num" + rowID + "' id='Num" + rowID + "' onkeyup='GetCount(" + rowID + ")' value='' class = 'dd-text' validate = '{required:true,isNumCode:true}'/><input type='hidden' value='" + SuppliesType.MaxNum.toString() + "' name='MaxNum" + rowID + "' id='MaxNum" + rowID + "'/><input type='hidden' value='0' name='NumFlag" + rowID + "' id='NumFlag" + rowID + "'/>";

   //添加列:序号
    var newNoTD = newTR.insertCell(4);
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
    var SuppliesTypeId = signFrame.rows[rowIndex].cells[1].getElementsByTagName("input")[0].value;

    for (var j = 0; j < idsArray.length; j++) {
        if (idsArray[j].tid == SuppliesTypeId) {
            idsArray[j].Num = idsArray[j].Num + parseInt(Num);
        }
    }
    gosArray.remove(SuppliesTypeId);
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
       signFrame.rows[i].cells[4].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
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
    var  ZNum=0;
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
        var SuppliesTypeId = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        var SuppliesTypeName = signFrame.rows[i].cells[1].getElementsByTagName("input")[1].value;
        var UnitName = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        var Num = signFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        var strobj = new Object();
        strobj["SuppliesTypeId"] = SuppliesTypeId;
        strobj["SuppliesTypeName"] = SuppliesTypeName;
        strobj["UnitName"] = UnitName;
        strobj["Num"] = Num;
        ary.push(strobj);
    }
    return ary;
}
function ClearAllSign() {
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;

    //循环删除行,从最后一行往前删除
    for (i = rowscount - 1; i > 0; i--) {
        signFrame.deleteRow(i);
    }

    //重置最后行号为1
    var txtTRLastIndex = findObj("TRLastIndex", document);
    txtTRLastIndex.value = "0";
    AddSignRow();
}
/**添加空行**/
function AddSignRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("SuppliesFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignSuppliesItem" + rowID;
    newTR.className = "bno";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(0);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DelSuppliesRow('SignSuppliesItem" + rowID + "')\">删除</a></div>";
    //添加列:耗材类型
    var newEmailTD = newTR.insertCell(1); newEmailTD.className = "sno";
    newEmailTD.innerHTML = "<input type='hidden'  name='SuppliesStockId" + rowID + "' id='SuppliesTypeId" + rowID + "'/><input name='SuppliesTypeName" + rowID + "' id='SuppliesTypeName" + rowID + "' type='text' size='40'  class = 'disable-dd-text'   readonly='readonly' />";

    //添加列: 耗材类型单位
    var newNameTD = newTR.insertCell(2);

    newNameTD.innerHTML = "<input type='text' name='UnitName" + rowID + "' id='UnitName" + rowID + "'  class = 'disable-dd-text'  readonly='readonly'/>";
    //添加列: 数量
    var newNumTD = newTR.insertCell(3);

    newNumTD.innerHTML = "<input type='text' name='Num" + rowID + "' id='Num" + rowID + "' onkeyup='GetCount(" + rowID + ")' value='' class = 'disable-dd-text' /><input type='hidden'  name='MaxNum" + rowID + "' id='MaxNum" + rowID + "'/><input type='hidden'  name='NumFlag" + rowID + "' id='NumFlag" + rowID + "'/>";

    //添加列:序号
    var newNoTD = newTR.insertCell(4);
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();

}
//清除已被删除的orgids
function GetTids() {
    var ary = new Array();
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    for (var n = 0; n < tidsArray.length; n++) {
        var tidflag = true;
        for (i = 1; i < rowscount; i++) {
            var SuppliesTypeId = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
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
