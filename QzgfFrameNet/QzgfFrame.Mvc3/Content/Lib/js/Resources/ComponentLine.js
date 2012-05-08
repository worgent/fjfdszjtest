/**添加行**/
function AddComponentRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("ComponentFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignComponentItem" + rowID;
    newTR.className = "bno"; 

    //添加列:部件类型
    var newNoTD = newTR.insertCell(0);
    newNoTD.className = "sno"; 
    var str = "";
    str += "<select name='ComponentId" + rowID + "' id='ComponentId" + rowID + "' ltype = 'select' >";
    for (var k = 0; k < ComponentList.length; k++) {
        str += "<option value='" + ComponentList[k].Id + "'>" + ComponentList[k].ComponentName + "</option>";
    }
    str += "</select>";
    newNoTD.innerHTML = str;
    
    //添加列: 部件型号
    var newNameTD = newTR.insertCell(1);
    newNameTD.innerHTML = "<input name='ComponentModel" + rowID + "' id='ComponentModel" + rowID + "' type='text' size='20'  class='dd-text' />";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(2);
    newDeleteTD.innerHTML = "<div align='center' style='width:40px'><a href='javascript:;' onclick=\"DelComponentRow('SignComponentItem" + rowID + "')\">删除</a></div>";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();
    $("select[name='ComponentId" + rowID + "']").ligerComboBox();
}
//删除指定行
function DelComponentRow(rowid) {
    var signFrame = findObj("ComponentFrame", document);
    var signItem = findObj(rowid, document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);

}
//保存部件信息
function GetComponentRow() {
    var ary = new Array();
    var numberFrame = findObj("ComponentFrame", document);
    var rowscount = numberFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
    var ComponentId=numberFrame.rows[i].cells[0].getElementsByTagName("select")[0].value;
    if (ComponentId != "0" && ComponentId != "") {
        var Component = new Object();
        Component["ComponentId"] = ComponentId;
        Component["ComponentModel"] = numberFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        ary.push(Component);
        }
    }
    return ary;
}