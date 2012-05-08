/**添加行**/
function AddIODetailRow(EquipModel) { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("IODetailFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignItem" + rowID;
    newTR.className = "bno";

    var managerType = "managerType" + rowID;
    var managerFactory = "managerFactory" + rowID;
    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(0);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DelIODetailRow('SignItem" + rowID + "')\">删除</a></div>";

    //添加列:设备厂家
    var newNoTD = newTR.insertCell(1);
    newNoTD.innerHTML = "<input type='hidden' name='FactoryId" + rowID + "' id='FactoryId" + rowID + "' value='" + EquipModel.FactoryId.toString() + "'/><input name='FactoryName" + rowID + "' id='FactoryName" + rowID + "' type='text' size='15'   value='" + EquipModel.FactoryName.toString() + "' class = 'disable-dd-text'  readonly='readonly'/>";

    //添加列: 设备名称
    var newNameTD = newTR.insertCell(2);
    newNameTD.innerHTML = "<input type='hidden' name='EquipTypeId" + rowID + "' id='EquipTypeId" + rowID + "' value='" + EquipModel.EquipTypeId.toString() + "'/> <input type='text' name='EquipTypeName" + rowID + "' id='EquipTypeName" + rowID + "' size='15'    value='" + EquipModel.EquipTypeName.toString() + "' class = 'disable-dd-text'  readonly='readonly'/>";
    
    //添加列:设备型号
    var newEmailTD = newTR.insertCell(3);
    newEmailTD.innerHTML = "<input type='hidden' name='EquipModelId" + rowID + "' id='EquipModelId" + rowID + "' value='" + EquipModel.EquipModelId.toString() + "'/><input name='EquipModelName" + rowID + "' id='EquipModelName" + rowID + "' type='text' size='60'  value='" + EquipModel.EquipModelName.toString() + "' class = 'disable-dd-text'  readonly='readonly'/>";
   
    //添加列:单位
    var newUnitTD = newTR.insertCell(4);
    newUnitTD.innerHTML = "<input name='UnitName" + rowID + "' id='UnitName" + rowID + "' type='text' size='10'  value='" + EquipModel.UnitName + "' class = 'disable-dd-text'  readonly='readonly'/>";

    //添加列:设备状态
    var newStateTD = newTR.insertCell(5);
    newStateTD.innerHTML = "<input name='EquipState" + rowID + "' id='EquipState" + rowID + "' type='text' size='10'  value='" + EquipModel.EquipState.toString() + "' class = 'disable-dd-text'  readonly='readonly'/>";

    
    //添加列: 数量
    var newNumTD = newTR.insertCell(6);
    newNumTD.innerHTML = "<input type='text' name='Num" + rowID + "' id='Num" + rowID + "'  size='4'  style='width:50px;' maxlength='4'  class = 'dd-text' onkeyup='GetCount(" + rowID + ")' validate = '{required:true,isNumCode:true}'/><input type='hidden' value='" + EquipModel.MaxNum.toString() + "' name='MaxNum" + rowID + "' id='MaxNum" + rowID + "'/><input type='hidden' value='0' name='NumFlag" + rowID + "' id='NumFlag" + rowID + "'/>";
    
    //添加列: 备注
    var newRemarkTD = newTR.insertCell(7);
    newRemarkTD.innerHTML = "<textarea  cols='160' rows='2'  name='Remark" + rowID + "' id='Remark" + rowID + "' class = 'l-textarea' ltype='text'   style='width:160px' validate = '{required:false,minlength:0,maxlength:50}'></textarea> ";

    
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
    /**
    managerType = $("input[name='EquipTypeName" + rowID + "']").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
    url: '/Archives/EquipType/GetCombobox',
    valueFieldID: 'EquipTypeId' + rowID, textFieldID: 'EquipTypeName' + rowID
    });
    managerFactory = $("input[name='FactoryName" + rowID + "']").ligerComboBox({ isShowCheckBox: false, isMultiSelect: false,
    url: '/Archives/Factory/GetCombobox',
    valueFieldID: 'FactoryId' + rowID, textFieldID: 'FactoryName' + rowID
    });
    //$("select[name='FactoryId" + rowID + "']").ligerComboBox();
    // $("select[name='EquipModelId" + rowID + "']").ligerComboBox();
    
    $("input[name='EquipModelName" + rowID + "']").autoSearchText({ width: 250, itemHeight: 150, minChar: 1, datafn: getData,
    fn: function (id, name) {
    var ary = id.split(',');
    managerType.selectValue(ary[1]);
    managerFactory.selectValue(ary[2]);
    },
    formatItem: function () {
    return ID;
    }
    });**/
    $("select[name='EquipState" + rowID + "']").ligerComboBox();
    $("select[name='UnitName" + rowID + "']").ligerComboBox();
}
//获取总行数
function GetIODetailLen() {
    var signFrame = findObj("IODetailFrame", document);
    var rowscount = signFrame.rows.length;
    return rowscount;
}
//删除指定行
function DelIODetailRow(rowid) {
    var signFrame = findObj("IODetailFrame", document);
    var signItem = findObj(rowid, document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    var Count = parseInt($("#warehouseIOList_TotalNum").val());
    var Num = signFrame.rows[rowIndex].cells[6].getElementsByTagName("input")[0].value;
    var num = Num.replace(/[ ]/g, "");
    if (num == "")
        Num = 0;
    Count = Count - parseInt(Num);
    $("#warehouseIOList_TotalNum").val(Count)
    var EquipModelId = signFrame.rows[rowIndex].cells[3].getElementsByTagName("input")[0].value;
    gosArray.remove(EquipModelId);
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);

}

//验证总数量
function CheckNum() {
    var signFrame = findObj("IODetailFrame", document);
    var rowscount = signFrame.rows.length;
    var ZNum = 0;
    for (i = 1; i < rowscount; i++) {
        var Numobj = signFrame.rows[i].cells[6].getElementsByTagName("input")[0];
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
//清除已被删除的orgids
function GetTids() {
    var ary = new Array();
    var signFrame = findObj("IODetailFrame", document);
    var rowscount = signFrame.rows.length;
    for (var n = 0; n < tidsArray.length; n++) {
        var tidflag = true;
        for (i = 1; i < rowscount; i++) {
            var EquipModelId = signFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
            if (tidsArray[n] == EquipModelId)
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
//保存设备群信息
function GetIODetailRow() {
    var ary = new Array();
    var signFrame = findObj("IODetailFrame", document);
    var rowscount = signFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var FactoryId = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        var FactoryName = signFrame.rows[i].cells[1].getElementsByTagName("input")[1].value;
        var EquipTypeId = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        var EquipTypeName = signFrame.rows[i].cells[2].getElementsByTagName("input")[1].value;
        var EquipModelId = signFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        var EquipModelName = signFrame.rows[i].cells[3].getElementsByTagName("input")[1].value;
        var UnitName = signFrame.rows[i].cells[4].getElementsByTagName("input")[0].value;
        var EquipState = signFrame.rows[i].cells[5].getElementsByTagName("input")[0].value;
        var Num = signFrame.rows[i].cells[6].getElementsByTagName("input")[0].value;
        var strobj = new Object();
        strobj["FactoryId"] = FactoryId;
        strobj["FactoryName"] = FactoryName;
        strobj["EquipTypeId"] = EquipTypeId;
        strobj["EquipTypeName"] = EquipTypeName;
        strobj["EquipModelId"] = EquipModelId;
        strobj["EquipModelName"] = EquipModelName;
        strobj["UnitName"] = UnitName;
        strobj["EquipState"] = EquipState;
        strobj["Num"] = Num;
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
            url: "/Archives/EquipModel/GetEquipModel/",
            data: "list=" + val,
            dataType: "json",
            cache: false,
            success: function (data) {
                var strdata = data.Data;
                for (var i = 0; i < strdata.length; i++) {
                    var objData = new Object();
                    var strid = [];
                    var strval = val.toLowerCase();
                    var strmodelName = (strdata[i].modelName).toLowerCase();
                    if (strmodelName.indexOf(strval) >= 0) {
                        strid.push(strdata[i].Id);
                        strid.push(strdata[i].typeId);
                        strid.push(strdata[i].factoryId);
                        objData["id"] = strid;
                        objData["Code"] = strdata[i].modelName;
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
//获取总数量
function GetAllNum() {
    var signFrame = findObj("IODetailFrame", document);
    var rowscount = signFrame.rows.length;
    var ZNum = 0;
    var displayFlag = true;
    for (i = 1; i < rowscount; i++) {
        var Num = signFrame.rows[i].cells[6].getElementsByTagName("input")[0].value;
        var num = Num.replace(/[ ]/g, "");
        if (num == "") {
            Num = 0; displayFlag = false;
        }
        if (parseInt(Num) == 0) displayFlag = false;
        ZNum += parseInt(Num);
    }
    if (displayFlag)
        $("#displyMsg").html("");
    else
        $("#displyMsg").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;★ 存在数量为空或为零的耗材！！");
    return ZNum;
}