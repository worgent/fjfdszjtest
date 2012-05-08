/**添加行**/
function AddEquipRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TREqipIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("equipFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignEquipItem" + rowID;
    newTR.className = "bno";

    //添加列:设备厂家
    var newNoTD = newTR.insertCell(0);
    var str = "";
    str += "<input type='hidden' name='EquipTypeId" + rowID + "' id='EquipTypeId" + rowID + "'/><select name='FactoryId" + rowID + "' id='FactoryId" + rowID + "'>";
    for (var k = 0; k < FactoryList.length; k++) {
        str += "<option value='" + FactoryList[k].Id + "'>" + FactoryList[k].Abbrevia + "</option>";
    }
    str += "</select>";
    newNoTD.innerHTML = str;

    //添加列: 设备型号
    var newNameTD = newTR.insertCell(1);

    newNameTD.innerHTML = "<input type='hidden' name='EquipModelId" + rowID + "' id='EquipModelId" + rowID + "'/><input type='text' name='EquipModelName" + rowID + "' id='EquipModelName" + rowID + " size = '40', class = 'dd-text'/>";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(2);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DelEquipRow('SignEquipItem" + rowID + "')\">删除</a></div>";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
    $("select[name='FactoryId" + rowID + "']").ligerComboBox();
   // $("select[name='EquipModelId" + rowID + "']").ligerComboBox();
    $("input[name='EquipModelName" + rowID + "']").autoSearchText({ width: 160, itemHeight: 150, minChar: 1, datafn: getData,
        fn: function (id, name) {
            var ary = id.split(',');
            $("#EquipModelId" + rowID).val(ary[0]);
            $("#EquipTypeId" + rowID).val(ary[1]);
            //$("#FactoryId" + rowID).val(ary[2]);
           // $("input[name='EquipModelId" + rowID + "']").val(ary[0]);
            //$("input[name='EquipTypeId" + rowID + "']").val(ary[1]);
            // $("input[name='FactoryId" + rowID + "']").val(ary[2]);
        },
        formatItem: function () {
            return ID;
        }
    });
}
//删除指定行
function DelEquipRow(rowid) {
    var signFrame = findObj("equipFrame", document);
    var signItem = findObj(rowid, document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);

}
//保存设备群信息
function GetEquipRow() {
    var ary = new Array();
    var numberFrame = findObj("equipFrame", document);
    var rowscount = numberFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var FactoryId = numberFrame.rows[i].cells[0].getElementsByTagName("select")[0].value;
        if (FactoryId != "0") {
            var EquipTypeId = numberFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
            var EquipModelId = numberFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
            var Equip = new Object();
            Equip["FactoryId"] = FactoryId; 
            Equip["EquipModelId"] = EquipModelId;
            Equip["EquipTypeId"] = EquipTypeId;
            Equip["EquipName"] = numberFrame.rows[i].cells[1].getElementsByTagName("input")[1].value;
            ary.push(Equip);
        }
    }
    return ary;
}