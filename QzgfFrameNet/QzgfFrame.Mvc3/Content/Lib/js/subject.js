
/**添加选择项行**/
function AddMultipleRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    if (rowID > 10) {
        return;
    }
    var signFrame = findObj("subjectFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignItem" + rowID;

    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.className = "tr_top";
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //添加列:NO
    var newNoNTD = newTR.insertCell(1);
    newNoNTD.className = "tr_top";
    var No = Conversion(rowID.toString());
    newNoNTD.innerHTML = "<div align='center'><span>" + No + "：</span></div>";

    //添加列: 试题内容
    var newNameTD = newTR.insertCell(2);
    newNameTD.className = "tr_top";
    newNameTD.innerHTML = "<input size='120' name='MultipleContent" + rowID + "' id='MultipleContent" + rowID + "' type='text' />";

    //添加列: 试题答案
    var newAnswerTD = newTR.insertCell(3);
    newAnswerTD.className = "tr_top";
    newAnswerTD.innerHTML = "<div align='center'><input name='Answer' id='Answer" + rowID + "'type='radio'/></div>";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(4);
    newDeleteTD.className = "tr_top";
    newDeleteTD.innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteMultipleRow('SignItem" + rowID + "')\">删除</a></div>";

    //增加选择项行前的行数只有2行的时候，增加第三行后，把前两行隐藏的“删除链接”重新显示。
    if (rowID == 3) {
        for (i = 1; i < 3; i++) {
            signFrame.rows[i].cells[4].innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteMultipleRow('SignItem" + i + "')\">删除</a></div>";
        }
    }
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();

//    var gustBookDiv = document.getElementById("subjectFrame").innerHTML;
//    alert(gustBookDiv);
}
//删除选项组指定行
function DeleteMultipleRow(rowid) {
    var signFrame = findObj("subjectFrame", document);
    var signItem = findObj(rowid, document);
    var txtTRLastIndex = findObj("TRLastIndex", document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;

    //如果删除的行是第一行并且是答案的选项，那么第二行为答案选项
    if (rowIndex == 1 && signFrame.rows[1].cells[3].getElementsByTagName("input")[0].checked == true) {
        signFrame.rows[2].cells[3].getElementsByTagName("input")[0].checked = true;
    }
    else {
        //如果删除的行是答案的选项，那么默认第一行为答案选项
        if (signFrame.rows[rowIndex].cells[3].getElementsByTagName("input")[0].checked == true) {
            signFrame.rows[1].cells[3].getElementsByTagName("input")[0].checked = true;
        }
    }   
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    var signFrameLength = signFrame.rows.length;
    var rowID = parseInt(txtTRLastIndex.value);
    //将行号减少一行
    txtTRLastIndex.value = (rowID - 1).toString();

    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].id = "SignItem" + i;
//        signFrame.rows[i].cells[0].innerHTML = "<input name='OrderNo" + i + "' id='OrderNo" + i + "' type='hidden' value='" + i.toString() + "' />";
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].id = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value = i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].name = "OrderNo" + i;
        var No = Conversion(i.toString());
        signFrame.rows[i].cells[1].innerHTML = "<div align='center'><span>" + No + "：</span></div>";
        signFrame.rows[i].cells[2].getElementsByTagName("input")[0].id = "MultipleContent" + i;
        signFrame.rows[i].cells[2].getElementsByTagName("input")[0].name = "MultipleContent" + i;
        signFrame.rows[i].cells[3].getElementsByTagName("input")[0].id = "Answer" + i;
        signFrame.rows[i].cells[4].innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteMultipleRow('SignItem" + i + "')\">删除</a></div>";
    }

    //选项只有2个的时候，隐藏删除选项按钮
    if (signFrameLength == 3) {
        for (i = 1; i < signFrame.rows.length; i++) {
            signFrame.rows[i].cells[4].innerHTML = "<div align='center';display:none'></div>";
        }
    }
//    var gustBookDiv = document.getElementById("subjectFrame").innerHTML;
//    alert(gustBookDiv);
}
//保存选择项组信息
function GetMultipleRow() {
    var ary = new Array();
    var subjectFrame = findObj("subjectFrame", document);
    var rowscount = subjectFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var subject = new Object();
        subject["OrderNo"] = subjectFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        subject["MultipleContent"] = subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        subject["Answer"] = subjectFrame.rows[i].cells[3].getElementsByTagName("input")[0].checked;
        ary.push(subject);
    }
    return ary;
}

function Conversion(OrderNo) {
    var No = "";
    switch (OrderNo) {
        case "1": { No = "A"; break; }
        case "2": { No = "B"; break; }
        case "3": { No = "C"; break; }
        case "4": { No = "D"; break; }
        case "5": { No = "E"; break; }
        case "6": { No = "F"; break; }
        case "7": { No = "G"; break; }
        case "8": { No = "H"; break; }
        case "9": { No = "I"; break; }
        case "10": { No = "J"; break; }
        case "11": { No = "K"; break; }
        case "12": { No = "L"; break; }
        case "13": { No = "M"; break; }
        case "14": { No = "N"; break; }
        case "15": { No = "O"; break; }
        case "16": { No = "P"; break; }
        case "17": { No = "Q"; break; }
        case "18": { No = "R"; break; }
    }
    return No;
}


/**添加填空题空格行**/
function AddFillBlankRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("subjectFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignItem" + rowID;

    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.className = "tr_top";
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //添加列:NO
    var newNoNTD = newTR.insertCell(1);
    newNoNTD.className = "tr_top";
    newNoNTD.innerHTML = "<div align='center'><span>&nbsp;" + rowID + "&nbsp;</span></div>";

    //添加列: 填空题空格答案
    var newNameTD = newTR.insertCell(2);
    newNameTD.className = "tr_top";
    newNameTD.innerHTML = "<input  size='120'  name='Answer" + rowID + "' id='Answer" + rowID + "' type='text'/>";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(3);
    newDeleteTD.className = "tr_top";
    newDeleteTD.innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteFillBlankRow('SignItem" + rowID + "')\">删除</a></div>";

    //增加选择项行前的行数只有1行的时候，增加第二行后，把第一行隐藏的“删除链接”重新显示。
    if (rowID ==2) {
        for (i = 1; i < 2; i++) {
            signFrame.rows[i].cells[3].innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteFillBlankRow('SignItem" + i + "')\">删除</a></div>";
        }
    }
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();

//    var gustBookDiv = document.getElementById("subjectFrame").innerHTML;
//    alert(gustBookDiv);

}
//删除填空组指定行
function DeleteFillBlankRow(rowid) {
    var signFrame = findObj("subjectFrame", document);
    var signItem = findObj(rowid, document);
    var txtTRLastIndex = findObj("TRLastIndex", document);

    //获取将要删除的行的Index
    var rowIndex = signItem.rowIndex;
    //删除指定Index的行
    signFrame.deleteRow(rowIndex);
    var signFrameLength = signFrame.rows.length;
    var rowID = parseInt(txtTRLastIndex.value);
    //将行号减少一行
    txtTRLastIndex.value = (rowID - 1).toString();

    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowIndex; i < signFrame.rows.length; i++) {
        signFrame.rows[i].id = "SignItem" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].id = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value = i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].name = "OrderNo" + i;
        signFrame.rows[i].cells[1].innerHTML = "<div align='center'><span>&nbsp;" + i + "&nbsp;</span></div>";
        signFrame.rows[i].cells[2].getElementsByTagName("input")[0].id = "Answer" + i;
        signFrame.rows[i].cells[2].getElementsByTagName("input")[0].name = "Answer" + i;
        signFrame.rows[i].cells[3].innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteFillBlankRow('SignItem" + i + "')\">删除</a></div>";
    }

    //选项只有1个的时候，隐藏删除选项按钮
    if (signFrameLength == 2) {
        for (i = 1; i < signFrame.rows.length; i++) {
            signFrame.rows[i].cells[3].innerHTML = "<div align='center';display:none'></div>";
        }
    }

//    var gustBookDiv = document.getElementById("subjectFrame").innerHTML;
//    alert(gustBookDiv);

}
//保存填空题空格组信息
function GetFillBlankRow() {
    var ary = new Array();
    var subjectFrame = findObj("subjectFrame", document);
    var rowscount = subjectFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var subject = new Object();
        subject["OrderNo"] = subjectFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        subject["Answer"] = subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        ary.push(subject);
    }
    return ary;
}

//保存判断题组信息
function GetJudgeRow() {
    var ary = new Array();
    var subjectFrame = findObj("subjectFrame", document);
    var rowscount = subjectFrame.rows.length;
    ary.length = 0;
    for (i = 0; i < rowscount; i++) {
        var subject = new Object();
        subject["Answer"] = subjectFrame.rows[i].cells[0].getElementsByTagName("input")[0].checked;
        ary.push(subject);
    }
    return ary;
}