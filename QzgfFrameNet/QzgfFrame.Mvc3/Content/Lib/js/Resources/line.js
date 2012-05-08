/**
纤芯信息编辑
**/
//添加纤芯行
function AddSignRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("txtTRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("SignFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length - 1);
    newTR.id = "SignItem" + rowID;
    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.innerHTML = "<input name='SeqNo" + rowID + "' id='SeqNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //添加列: 基站名
    var newNameTD = newTR.insertCell(1);
    newNameTD.innerHTML = "经<input name='BaseStationName" + rowID + "' id='BaseStationName" + rowID + "' class='dd-text' type='text' size='20' />&nbsp;跳纤点";

    //添加列:ODU
    var newEmailTD = newTR.insertCell(2);
    newEmailTD.innerHTML = "<textarea  class='l-textarea' cols='20' name='ODUStation" + rowID + "' rows='1' style='width:300px; height:19px;' validate = '{required:false,minlength:1,maxlength:80}' ></textarea>";

    //添加列:纤芯
    var newTelTD = newTR.insertCell(3);
    newTelTD.innerHTML = "至&nbsp;<textarea  class='l-textarea' cols='20' name='Core" + rowID + "' rows='1' style='width:300px; height:19px;' validate = '{required:false,minlength:1,maxlength:80}' ></textarea>";
   

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(4);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteSignRow('SignItem" + rowID + "')\">删除</a></div>";
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
}
//删除纤芯行
function DeleteSignRow(rowid) {
    var signFrame = findObj("SignFrame", document);
    var signItem = findObj(rowid, document);
    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);

    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].cells[0].innerHTML = "<input name='SeqNo" + i + "' id='SeqNo" + i + "' type='hidden' value='" + i.toString() + "' />";
    }
}
//保存纤芯信息
function GetCoreRow() {
    var ary = new Array();
    var signFrame = findObj("SignFrame", document);
    var rowscount = signFrame.rows.length;
    ary.length = 0;
    for (i = 0; i < rowscount; i++) {
        var core = new Object();
        core["SeqNo"] = signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        if (core["SeqNo"] == "")
            core["SeqNo"] = i;
        core["BaseStationName"] = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        core["ODUStation"] = signFrame.rows[i].cells[2].getElementsByTagName("textarea")[0].value;
        core["Core"] = signFrame.rows[i].cells[3].getElementsByTagName("textarea")[0].value;
        ary.push(core);
    }
    return ary;
}
/**号码信息编辑**/
//添加号码行
function AddRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("numberFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignNumberItem" + rowID;
    newTR.className = "bno"; 


    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //添加列: 号码
    var newNameTD = newTR.insertCell(1);
    newNameTD.innerHTML = "<input name='TelNumber" + rowID + "' id='TelNumber" + rowID + "' type='text' size='12' />";

    //添加列:用户名
    var newEmailTD = newTR.insertCell(2);
    newEmailTD.innerHTML = "<input name='UserName" + rowID + "' id='UserName" + rowID + "' type='text' size='20' />";

    //添加列:密码
    var newTelTD = newTR.insertCell(3);
    newTelTD.innerHTML = "<input name='PassWord" + rowID + "' id='PassWord" + rowID + "' type='text' size='10' />";


    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(4);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteRow('SignNumberItem" + rowID + "')\">删除</a></div>";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
}
//删除指定号码行
function DeleteRow(rowid) {
    var signFrame = findObj("numberFrame", document);
    var signItem = findObj(rowid, document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].cells[0].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
    }
}
//获取号码总行数
function GetNumberLen() {
    var numberFrame = findObj("numberFrame", document);
    var rowscount = numberFrame.rows.length;
    return rowscount;
}
//保存号码群信息
function GetNumberRow() {
    var ary = new Array();
    var numberFrame = findObj("numberFrame", document);
    var rowscount = numberFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var telnum = numberFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        if (telnum != "") {
            var number = new Object();
            number["OrderNo"] = numberFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
            number["TelNumber"] = numberFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
            number["UserName"] = numberFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
            number["PassWord"] = numberFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
            ary.push(number);
        }
    }
    return ary;
}

/**在专线设备里添加客户行编辑**/
//添加集团客户
function AddClieRow(clie, selmode) { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var CountIndex = findObj("CountIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("clieFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignItem" + rowID;
    newTR.className = "bno";
    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(0);
    newDeleteTD.className = "bdno";
    newDeleteTD.innerHTML = "<div align='center' style='width:40px;margin-left:10px;'><a href='javascript:;' onclick=\"DeleteClieRow('SignItem" + rowID + "')\">删除</a></div>";
    
    
    //添加列: 客户编号
    var newNameTD = newTR.insertCell(1);
    newNameTD.innerHTML = "<input name='ClieId" + rowID + "' id='ClieId" + rowID + "'  type='hidden' value='" + clie.ID.toString() + "' /><input name='ClieNo" + rowID + "' id='ClieNo" + rowID + "' type='text' class = 'disable-dd-text' maxlength='15'  size='16'  readonly='readonly'  value='" + clie.ClieNo.toString() + "'/>";

    //添加列:客户名称
    var newEmailTD = newTR.insertCell(2);
    newEmailTD.innerHTML = "<input name='ClieName" + rowID + "' id='ClieName" + rowID + "' type='text' class = 'disable-dd-text' size='40' readonly='readonly'  value='" + clie.ClieName.toString() + "'/>";
    if (selmode != "MSAP") {
        //添加列:占用槽位
        var newTelTD = newTR.insertCell(3);
        newTelTD.innerHTML = "<input name='OccupySlot" + rowID + "' id='OccupySlot" + rowID + "' type='text' size='10' class='disable-dd-text' disabled='disabled' />";

        //添加列:板卡类型
        var newTelTD = newTR.insertCell(4);
        newTelTD.innerHTML = "<input name='BoardType" + rowID + "' id='BoardType" + rowID + "' type='text' size='10' class='disable-dd-text' disabled='disabled'/>";
               
    }
    else {
        //添加列:占用槽位
        var newTelTD = newTR.insertCell(3);
        newTelTD.innerHTML = "<input name='OccupySlot" + rowID + "' id='OccupySlot" + rowID + "' type='text' size='10' class='dd-text' />";

        //添加列:板卡类型
        var newTelTD = newTR.insertCell(4);
        newTelTD.innerHTML = "<input name='BoardType" + rowID + "' id='BoardType" + rowID + "' type='text' size='10' class='dd-text' />";

    } 
    //添加列:占用端口
    var newTelTD = newTR.insertCell(5);
    newTelTD.innerHTML = "<input name='OccupyPort" + rowID + "' id='OccupyPort" + rowID + "' type='text' size='10' class='dd-text'   validate = '{required:true}'/>";
    //添加列:部件类型
    var newTypeTD = newTR.insertCell(6);
    newTypeTD.className = "sno"; 
    var str = "";
    str += "<select name='PortTypeId" + rowID + "' id='PortTypeId" + rowID + "' ltype = 'select' >";
    for (var k = 0; k < PortTypeList.length; k++) {
        str += "<option value='" + PortTypeList[k].Id + "'>" + PortTypeList[k].PortTypeName + "</option>";
    }
    str += "</select>";
    newTypeTD.innerHTML = str;

   //添加列:序号
    var newSeqTD = newTR.insertCell(7);
    //添加列内容
    newSeqTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
    CountIndex.value =( parseInt(CountIndex.value) + 1).toString();
}
//删除指定客户行
function DeleteClieRow(rowid) {
    var signFrame = findObj("clieFrame", document);
    var signItem = findObj(rowid, document);
    var CountIndex = findObj("CountIndex", document);
    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
   //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].cells[6].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
    }
    CountIndex.value = (parseInt(CountIndex.value) - 1).toString();
    var trCount = CountIndex.value;
    if (trCount > 1) {
        $("#displyMsg").html("组网方式为" + selmode + "的设备只能有一个集团客户");
        return false;
    }
    else
        $("#displyMsg").html("");
}
//获取集团客户总行数
function GetClieLen() {
    var clieFrame = findObj("clieFrame", document);
    var rowscount = clieFrame.rows.length;
    return rowscount;
}
//保存客户信息
function GetClieRow() {
    var ary = new Array();
    var clieFrame = findObj("clieFrame", document);
    var rowscount = clieFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var EquipClie = new Object();
        EquipClie["ClieId"] = clieFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        EquipClie["ClieNo"] = clieFrame.rows[i].cells[1].getElementsByTagName("input")[1].value;
        EquipClie["ClieName"] = clieFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        EquipClie["OccupySlot"] = clieFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        EquipClie["BoardType"] = clieFrame.rows[i].cells[4].getElementsByTagName("input")[0].value;
        EquipClie["OccupyPort"] = clieFrame.rows[i].cells[5].getElementsByTagName("input")[0].value;
        var PortTypeId = clieFrame.rows[i].cells[6].getElementsByTagName("select")[0].value;
        EquipClie["PortTypeId"] = PortTypeId;
        ary.push(EquipClie);
    }
    return ary;
}
/**文件行编辑**/
var delary = new Array();
//删除指定文件行
function DeleteFileRow(rowid) {
    var signFrame = findObj("FileFrame", document);
    var signItem = findObj(rowid, document);
    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    var delid = signFrame.rows[rowIndex].cells[0].getElementsByTagName("input")[0].value;
    delary.push(delid);
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
}

/**添加170编辑设备行**/
function AddEquipRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("equipFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "EquipSignItem" + rowID;
    //添加列:设备厂家
    var newNoTD = newTR.insertCell(0);
    var str = "";
    str += "<select name='FactoryId" + rowID + "' id='FactoryId" + rowID + "'>";
    for (var k = 0; k < FactoryList.length; k++) {
        str += "<option value='" + FactoryList[k].Id + "'>" + FactoryList[k].Abbrevia + "</option>";
    }
    str += "</select>";
    newNoTD.innerHTML = str;
    
    //添加列: 设备型号
    var newNameTD = newTR.insertCell(1);
    var str = "";
    str += "<select name='EquipModelId" + rowID + "' id='EquipModelId" + rowID + "'>";
    for (var k = 0; k < EquipModelList.length; k++) {
        str += "<option value='" + EquipModelList[k].Id + "'>" + EquipModelList[k].EquipModelName + "</option>";
    }
    str += "</select>";
    newNoTD.innerHTML = str;

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(2);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DeleteEquipRow('EquipSignItem" + rowID + "')\">删除</a></div>";
    //添加列:序号
    var newSeqTD = newTR.insertCell(3);
    //添加列内容
    newSeqTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
}
//删除指定设备行
function DeleteEquipRow(rowid) {
    var signFrame = findObj("equipFrame", document);
    var signItem = findObj(rowid, document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].cells[3].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
    }
}
//保存设备信息
function GetEquipRow() {
    var ary = new Array();
    var clieFrame = findObj("equipFrame", document);
    var rowscount = clieFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var Equip= new Object();
        Equip["FactoryId"] = clieFrame.rows[i].cells[0].getElementsByTagName("select")[0].value;
        Equip["EquipModelId"] = clieFrame.rows[i].cells[1].getElementsByTagName("select")[1].value;
        Equip["OrderNo"] = clieFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        ary.push(Equip);
    }
    return ary;
}

/**在专线里添加多行业务设备信息**/
//添加专线设备信息
function AddLineEquipRow(equip) { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastEquipIndex", document);
    var CountIndex = findObj("CountIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("lineEquipFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignEquipItem" + rowID;
    newTR.className = "bno";
    
    //添加列: 设备名称
    var newNameTD = newTR.insertCell(0);
    newNameTD.innerHTML = "<input name='EquipId" + rowID + "' id='EquipId" + rowID + "'  type='hidden' value='" + equip.EquipId.toString() + "' /><input name='EquipName" + rowID + "' id='EquipName" + rowID + "' type='text' class = 'disable-dd-text' maxlength='40'  size='80'  readonly='readonly'  value='" + equip.EquipName.toString() + "'/>";

    //添加列:设备占用端口
    var newEmailTD = newTR.insertCell(1);
    newEmailTD.innerHTML = "<input name='ClieId" + rowID + "' id='ClieId" + rowID + "'  type='hidden' value='" + equip.ClieId.toString() + "' /><input name='OccupyPort" + rowID + "' id='OccupyPort" + rowID + "' type='text' class = 'disable-dd-text' size='20' readonly='readonly'  value='" + equip.OccupyPort.toString() + "'/>";
    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(2);
    newDeleteTD.className = "bdno";
    newDeleteTD.innerHTML = "<div align='center' style='width:40px;margin-left:10px;'><a href='javascript:;' onclick=\"DeleteLineEquipRow('SignEquipItem" + rowID + "')\">删除</a></div>";


   
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
    CountIndex.value = (parseInt(CountIndex.value) + 1).toString();
}
//删除指定专线设备
function DeleteLineEquipRow(rowid) {
    var signFrame = findObj("lineEquipFrame", document);
    var signItem = findObj(rowid, document);
    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    var EquipId = signFrame.rows[rowIndex].cells[0].getElementsByTagName("input")[0].value;
    gosArray.remove(EquipId);
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);   
}
//保存专线设备信息
function GetLineEquipRow() {
    var ary = new Array();
    var clieFrame = findObj("lineEquipFrame", document);
    var rowscount = clieFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var lineEquip = new Object();
        lineEquip["EquipId"] = clieFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        lineEquip["EquipName"] = clieFrame.rows[i].cells[0].getElementsByTagName("input")[1].value;
        lineEquip["ClieId"] = clieFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        lineEquip["OccupyPort"] = clieFrame.rows[i].cells[1].getElementsByTagName("input")[1].value;
        ary.push(lineEquip);
    }
    return ary;
}
//清除已被删除的设备
function GetTids() {
    var ary = new Array();
    var signFrame = findObj("lineEquipFrame", document);
    var rowscount = signFrame.rows.length;
    for (var n = 0; n < tidsArray.length; n++) {
        var tidflag = true;
        for (i = 1; i < rowscount; i++) {
            var EquipId = signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
            if (tidsArray[n] == EquipId)
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
//验证集团客户是否一样
function CheckClie(clieid) {
    var signFrame = findObj("lineEquipFrame", document);
    var rowscount = signFrame.rows.length;
    var ZNum = 0;var flag = false;
    for (i = 1; i < rowscount; i++) {
        var ClieId = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        var EquipNameobj = signFrame.rows[i].cells[0].getElementsByTagName("input")[1];
        if (ClieId != clieid ) {
            $("#displyMsg").html("存在关联设备不属于该集团客户！！");
            EquipNameobj.className = "fault-dd-text";
            flag = true;
        }
    }
    if (flag)
        return false;
    return true;
}
//获取总行数
function GetLineEquipLen() {
    var signFrame = findObj("lineEquipFrame", document);
    var rowscount = signFrame.rows.length;
    return rowscount;
}
/**在专线里添加多行Z端业务设备信息**/
//添加专线设备信息
function AddLineZEquipRow(equip) { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastZEquipIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("lineZEquipFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignZEquipItem" + rowID;
    newTR.className = "bno";
    
    //添加列: 设备名称
    var newNameTD = newTR.insertCell(0);
    newNameTD.innerHTML = "<input name='EquipId" + rowID + "' id='ZEquipId" + rowID + "'  type='hidden' value='" + equip.EquipId.toString() + "' /><input name='EquipName" + rowID + "' id='ZEquipName" + rowID + "' type='text' class = 'disable-dd-text' maxlength='40'  size='80'  readonly='readonly'  value='" + equip.EquipName.toString() + "'/>";

    //添加列:设备占用端口
    var newEmailTD = newTR.insertCell(1);
    newEmailTD.innerHTML = "<input name='ClieId" + rowID + "' id='ZClieId" + rowID + "'  type='hidden' value='" + equip.ClieId.toString() + "' /><input name='OccupyPort" + rowID + "' id='ZOccupyPort" + rowID + "' type='text' class = 'disable-dd-text' size='20' readonly='readonly'  value='" + equip.OccupyPort.toString() + "'/>";
    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(2);
    newDeleteTD.className = "bdno";
    newDeleteTD.innerHTML = "<div align='center' style='width:40px;margin-left:10px;'><a href='javascript:;' onclick=\"DeleteLineZEquipRow('SignZEquipItem" + rowID + "')\">删除</a></div>";



    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
}
//删除指定专线设备
function DeleteLineZEquipRow(rowid) {
    var signFrame = findObj("lineZEquipFrame", document);
    var signItem = findObj(rowid, document);
    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    var EquipId = signFrame.rows[rowIndex].cells[0].getElementsByTagName("input")[0].value;
    ZgosArray.remove(EquipId);
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
}
//保存专线设备信息
function GetLineZEquipRow() {
    var ary = new Array();
    var clieFrame = findObj("lineZEquipFrame", document);
    var rowscount = clieFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var lineEquip = new Object();
        lineEquip["EquipId"] = clieFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        lineEquip["EquipName"] = clieFrame.rows[i].cells[0].getElementsByTagName("input")[1].value;
        lineEquip["ClieId"] = clieFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        lineEquip["OccupyPort"] = clieFrame.rows[i].cells[1].getElementsByTagName("input")[1].value;
        ary.push(lineEquip);
    }
    return ary;
}
//清除已被删除的设备
function GetZTids() {
    var ary = new Array();
    var signFrame = findObj("lineZEquipFrame", document);
    var rowscount = signFrame.rows.length;
    for (var n = 0; n < ztidsArray.length; n++) {
        var tidflag = true;
        for (i = 1; i < rowscount; i++) {
            var EquipId = signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
            if (ztidsArray[n] == EquipId)
                tidflag = false;
        }
        if (tidflag)
            ary.push(ztidsArray[n]);
    }
    for (var k = 0; k < ary.length; k++) {
        ztidsArray.remove(ary[k]);
    }
    return ztidsArray;
}
//验证集团客户是否一样
function CheckZClie(clieid) {
    var signFrame = findObj("lineZEquipFrame", document);
    var rowscount = signFrame.rows.length;
    var flag = false;
    for (i = 1; i < rowscount; i++) {
        var ClieId = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        var EquipNameobj = signFrame.rows[i].cells[0].getElementsByTagName("input")[1];
        if (ClieId != clieid) {
            $("#displyMsg").html("存在关联设备不属于该集团客户！！");
            EquipNameobj.className = "fault-dd-text";
            flag = true;
        }
    }
    if (flag)
        return false;
    return true;
}
//获取总行数
function GetLineZEquipLen() {
    var signFrame = findObj("lineZEquipFrame", document);
    var rowscount = signFrame.rows.length;
    return rowscount;
}