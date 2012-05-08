/**添加行**/
function AddSuppliesRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
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
    //添加列:领料时间

    var newEmailTD = newTR.insertCell(1); newEmailTD.className = "btno";
    newEmailTD.innerHTML = "<input name='CollarDate" + rowID + "' id='CollarDate" + rowID + "' type='text' size='40'  class = 'dd-text'  />";
    //添加列: 领料数量
    var newNumTD = newTR.insertCell(2);
    newNumTD.innerHTML = "<input type='text' name='Num" + rowID + "' id='Num" + rowID + "' onkeyup='GetCount(" + rowID + ")'  class = 'dd-text' validate = '{required:true,isNumCode:true}'/>";
    //添加列:序号
    var newNoTD = newTR.insertCell(3);
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";
    $("input[name='CollarDate" + rowID + "']").ligerDateEditor();
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
    var Num = signFrame.rows[rowIndex].cells[2].getElementsByTagName("input")[0].value;
    var num = Num.replace(/[ ]/g, "");
    if (num == "")
        Num = 0;
    Count = Count - parseInt(Num);
    $("#Count").val(Count)
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].cells[3].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
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
    for (i = 0; i < rowscount; i++) {
        var Num = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
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
    for (i = 0; i < rowscount; i++) {
        var Numobj = signFrame.rows[i].cells[2].getElementsByTagName("input")[0];
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
    for (i = 0; i < rowscount; i++) {
        var CollarDate = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        var Num = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        var strobj = new Object();
        strobj["CollarDate"] = CollarDate;
        strobj["Num"] = Num;
        ary.push(strobj);
    }
    return ary;
}

//获取所有typeId,不重复
function GetTids() {
    var ary = new Array();
    var signFrame = findObj("SuppliesFrame", document);
    var rowscount = signFrame.rows.length;
    for (i = 1; i < rowscount; i++) {
        var addflag = true;
        var SuppliesTypeId = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        for (var n = 0; n < ary.length; n++) {
            if (ary[n] == SuppliesTypeId)
                addflag = false;
        }
        if(addflag)
          ary.push(SuppliesTypeId);
    }
    return ary;
}