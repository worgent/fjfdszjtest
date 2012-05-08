//---------随机出卷-------//
/**添加随机出卷试卷随机类型行**/
function AddGetSubjectRow() { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("subjectFrame", document);

    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignItem" + rowID;

    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.innerHTML = "<input  size='5' name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='text' value='" + newTR.rowIndex.toString() + "' readonly='readonly' />";
    //添加列: 题型名称 
    var newNameTD = newTR.insertCell(1); 
    newNameTD.innerHTML = "<select id='SubjectTypeName' name='SubjectTypeName'><option>选择题</option><option>填空题</option><option>判断题</option></select>";
    //添加列: 题型数量
    var newAnswerNumber = newTR.insertCell(2);
    newAnswerNumber.innerHTML ="<input name='SubjectTypeNumber"+ rowID + "' id='SubjectTypeNumber"+ rowID + "' type='text' value='0' onkeyup=\"f_onkeyup('" + rowID + "','2')\"/>";
    //添加列: 每小题分值
    var newAnswerScore = newTR.insertCell(3);
    newAnswerScore.innerHTML = "<input name='SubjectTypeScore" + rowID + "' id='SubjectTypeScore" + rowID + "' type='text' value='0' onkeyup=\"f_onkeyup('" + rowID + "','3')\"/>";
    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(4);
    newDeleteTD.innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteGetSubjectRow('SignItem" + rowID + "')\">删除</a></div>";

    //增加选择项行前的行数只有1行的时候，增加第二行后，把前两行隐藏的“删除链接”重新显示。
    if (rowID == 2) {
        for (i = 1; i < 3; i++) {
            signFrame.rows[i].cells[4].innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteGetSubjectRow('SignItem" + i + "')\">删除</a></div>";
        }
    }
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();

//    var gustBookDiv = document.getElementById("subjectFrame").innerHTML;
//    alert(gustBookDiv);
}
//删除随机出卷试卷随机类型指定行
function DeleteGetSubjectRow(rowid) {
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
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].name = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value = i;
        signFrame.rows[i].cells[1].getElementsByTagName("select")[0].id = "SubjectTypeName" + i;
        signFrame.rows[i].cells[1].getElementsByTagName("select")[0].name = "SubjectTypeName" + i;
        signFrame.rows[i].cells[2].getElementsByTagName("input")[0].id = "SubjectTypeNumber" + i;
        signFrame.rows[i].cells[2].getElementsByTagName("input")[0].name = "SubjectTypeNumber" + i;
        signFrame.rows[i].cells[3].getElementsByTagName("input")[0].id = "SubjectTypeScore" + i;
        signFrame.rows[i].cells[3].getElementsByTagName("input")[0].name = "SubjectTypeScore" + i;
        signFrame.rows[i].cells[4].innerHTML = "<div align='center'><a href='javascript:;' onclick=\"DeleteGetSubjectRow('SignItem" + i + "')\">删除</a></div>";
    }

    //选项只有1个的时候，隐藏删除选项按钮
    if (signFrameLength == 2) {
        for (i = 1; i < signFrame.rows.length; i++) {
            signFrame.rows[i].cells[4].innerHTML = "<div align='center';display:none'></div>";
        }
    }
//    var gustBookDiv = document.getElementById("subjectFrame").innerHTML;
//    alert(gustBookDiv);
}
//保存随机出卷试卷随机类型信息
function GetSubjectRow() {
    var ary = new Array();
    var subjectFrame = findObj("subjectFrame", document);
    var rowscount = subjectFrame.rows.length;
    ary.length = 0;
    for (i = 1; i < rowscount; i++) {
        var subject = new Object();
        subject["OrderNo"] = subjectFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        var obj = subjectFrame.rows[i].cells[1].getElementsByTagName("select");
        var index = obj[0].selectedIndex;
        subject["SubjectTypeName"] = obj[0].options[index].text;
        subject["SubjectTypeNumber"] = subjectFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        subject["SubjectTypeScore"] = subjectFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        ary.push(subject);
    }
    return ary;
}
//---------随机出卷-------//



//---------试卷题型-------//
/**添加试卷题型行**/
function AddTestSubjectTypeRow(stype) { //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var txtTRLastIndex = findObj("TypeTRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    var signFrame = findObj("typeFrame", document);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = "SignItem" + rowID;
    newTR.className = "testsubjecttype_tr";

    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //添加列:试题类型ID
    var newTypeTD = newTR.insertCell(1);
    newTypeTD.innerHTML = "<input name='SubjectTypeID" + rowID + "' id='SubjectTypeID" + rowID + "' type='hidden' value='" + stype.ID + "' />";

    //添加列: 题型名称
    var newTypeNameTD = newTR.insertCell(2);
    newTypeNameTD.className = "typename";
    newTypeNameTD.innerHTML = "<input name='TypeName" + rowID + "' id='TypeName" + rowID + "' type='text'  value='" + stype.Type + "' onclick=\"SubjectDisplay('" + rowID + "')\" ondblclick=\"f_ondblclick('" + rowID + "')\" />";

    //添加列:删除按钮
    var newDeleteTD = newTR.insertCell(3);
    newDeleteTD.innerHTML = "<a href='javascript:;' onclick=\"DeleteTestSubjectTypeRow('" + rowID + "')\">删除</a>";

    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();

//    var gustBookDiv = document.getElementById("typeFrame").innerHTML;
//    alert(gustBookDiv);
}
//删除试卷题型指定行
function DeleteTestSubjectTypeRow(rowid) {  
    var signFrame = findObj("typeFrame", document);
    var txtTRLastIndex = findObj("TypeTRLastIndex", document);
    //删除指定Index的行
    signFrame.deleteRow(rowid);
    var rowID = parseInt(txtTRLastIndex.value);
    //将行号减少一行
    txtTRLastIndex.value = (rowID - 1).toString();

    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowid; i < signFrame.rows.length; i++) {
        signFrame.rows[i].id = "SignItem" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].id = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].name = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value = i;
        signFrame.rows[i].cells[1].getElementsByTagName("input")[0].id = "SubjectTypeID" + i;
        signFrame.rows[i].cells[1].getElementsByTagName("input")[0].name = "SubjectTypeID" + i;
        var typename= signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        signFrame.rows[i].cells[2].innerHTML = "<input name='TypeName" + i + "' id='TypeName" + i + "' type='text'  value='" + typename + "' onclick=\"SubjectDisplay('" + i + "')\" ondblclick=\"f_ondblclick('" + i + "')\" />";
        signFrame.rows[i].cells[3].innerHTML = "<a href='javascript:;' onclick=\"DeleteTestSubjectTypeRow('" + i + "')\">删除</a>";
    }
//    var gustBookDiv = document.getElementById("typeFrame").innerHTML;
//    alert(gustBookDiv);
    DelTestSubjectFrame(rowid);
    DelPreviewTestSubjectFrame(rowid);
}
//保存试卷题型信息
function GetTestSubjectTypeRow() {
    var ary = new Array();
    var signFrame = findObj("typeFrame", document);
    var rowscount = signFrame.rows.length;
    for (i = 1; i < rowscount; i++) {
        var subject = new Object();
        subject["OrderNo"] = signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value;
        subject["SubjectTypeID"] = signFrame.rows[i].cells[1].getElementsByTagName("input")[0].value;
        subject["TypeName"] = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        ary.push(subject);
    }
    return ary;
}
///根据题型ID显示对应的试题列表和预览试题列表
function SubjectDisplay(typenameOrderNo) {
    var typeFrame = findObj("typeFrame", document);
    typeFrame.rows[typenameOrderNo].cells[2].getElementsByTagName("input")[0].blur() ;
    var testsubject = findObj("testsubject", document);
    var divs = testsubject.childNodes;
    var id = "";
    for (i = 0; i < divs.length; i++) {
        id = divs[i].id;
        switch (id) {
            case "SubjectDiv" + typenameOrderNo:
                divs[i].style.display = "block";
                break;
            case undefined:
                break;
            case "TableLastIndex":
                break;
            case "testsubjects":
                break;
            case "":
                break;
            default:
                divs[i].style.display = "none";
                break;
        }
    }

    var previewFrame = findObj("preview", document);
    var tables = previewFrame.childNodes;
    for (i = 0; i < tables.length; i++) {
        id = tables[i].id;
        switch (id) {
            case "previewtype" + typenameOrderNo:
                tables[i].style.display = "block";
                var previewsubjects = tables[i].childNodes;
                for (j = 1; j < previewsubjects.length; j++) {
                    id = previewsubjects[j].id;
                    switch (id) {
                        case undefined:
                            break;
                        case "":
                            break;
                        default:
                            previewsubjects[j].style.display = "block";
                    }
                }
                break;
            case "":
                break;
            case undefined:
                break;
            default:
                tables[i].style.display = "none";
                break;
        }
    }
}
//---------试卷题型-------//



//---------试卷试题列表-------//

/**添加试卷题型对应的试题列表**/
function AddTestSubjectFrame(stype) {
    var txtTRLastIndex = findObj("TypeTRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    rowID = rowID - 1;    //试题列表的RowID对应题型的RowID
    var txtTableLastIndex = findObj("TableLastIndex", document);
    var TableID = parseInt(txtTableLastIndex.value);
    
    var testsubject = findObj("testsubject", document);
    var newDiv = document.createElement("div");
    newDiv.id = "SubjectDiv" + rowID;
    testsubject.appendChild(newDiv);

    var addDiv = document.createElement("div");
    addDiv.id = "addtestsubject" + rowID;
    addDiv.className = "addtestsubjecttype";
    
    //添加一个表格，存放试题类型ID和新增试题按钮
    var addtable = document.createElement("table");
    addtable.id = "addtestsubjecttable" + rowID;
    //添加一个表格行
    var addnewTR = addtable.insertRow(addtable.rows.length);
    //添加一个表格行的试题类型ID列
    var addnewNoTD = addnewTR.insertCell(0);
    addnewNoTD.style.width = "0px";
    addnewNoTD.innerHTML = "<input name='" + stype.Type + "' type='hidden' id='addSubjectTypeID" + rowID + "' value='" + stype.ID + "' />";
    //var tableID = String(TableID);
    //添加一个表格行的新增试题按钮列
    var addnewSubjectTD = addnewTR.insertCell(1);
    addnewSubjectTD.innerHTML = " <img src='../../../../Content/Lib/images/index/add_questions.png' style='float: left;padding-left: 3px' /><input type='button' name='Submitsubject' value='新增试题'  class='l-add_questions' onclick=\"f_addsubject('" + stype.ID + "' ,'subjectFrame" + TableID.toString() + "')\" />";
    addDiv.appendChild(addtable);

    newDiv.appendChild(addDiv);

    var table = document.createElement("table");
    table.id = "subjectFrame" + TableID.toString();
    var newTR = table.insertRow(table.rows.length);
    newTR.id = "subjectTitle";
    newTR.className = "stem";

    var newNoTD = newTR.insertCell(0);
    newNoTD.style.width = "1px";
    newNoTD.innerHTML = "";

    var newSubjectTD = newTR.insertCell(1);
    newSubjectTD.style.width = "1px";
    newSubjectTD.innerHTML = "";

    var newNameTD = newTR.insertCell(2);
    newNameTD.innerHTML = "题型：" + stype.Type;
    newNameTD.className = "testsubject_tt";

    var newPointsTD = newTR.insertCell(3);
    newPointsTD.innerHTML = "总分(0)";
    newPointsTD.className = "subjectPoints_tt";

    newDiv.appendChild(table);

    var trInput = document.createElement("input");
    trInput.id = "SubjectTRLastIndex" + rowID;
    trInput.name = "SubjectTRLastIndex";
    trInput.type = "hidden";
    trInput.value = "1";
    newDiv.appendChild(trInput);

    SubjectDisplay(rowID);

    //将表的行号推进下一行
    txtTableLastIndex.value = (TableID + 1).toString();

//   var gustBookDiv = document.getElementById("testsubject").innerHTML;
//   alert(gustBookDiv);
}
/**删除试卷题型对应的试题列表**/
function DelTestSubjectFrame(rowid) {
    var subjectDivID = "SubjectDiv" + rowid;
    var CurrentPoints = findObj("CurrentPoints", document);   //获得当前总分文本框
    var testsubject = findObj("testsubject", document);
    var div = findObj(subjectDivID, document);
    var tables = div.childNodes;
    var delTableID = "";
    var divIndex = subjectDivID.substr(10);    //subjectDivID 标号  如 subjectDivID1 取出1
    for (i = 0; i < tables.length; i++) {
        switch (tables[i].id) {
            case "addtestsubject" + divIndex:
                break;
            case undefined:
                break;
            case "SubjectTRLastIndex" + divIndex:
                break;
            default:
                delTableID = tables[i].id.substr(12);          //subjectFrame标号  如 subjectFrame1 取出1
                var score = "";
                for (j = 1; j < tables[i].rows.length; j++) {
                    score = tables[i].rows[j].cells[3].getElementsByTagName("input")[0].value;
                    CurrentPoints.value = parseInt(CurrentPoints.value) - parseInt(score);
                }
        }
    }
    var divDisplay = false;
    if (div.style.display == "block")
        divDisplay = true;
    if (div != null)
        div.parentNode.removeChild(div);       //移除节点

    var txtTableLastIndex = findObj("TableLastIndex", document);
    var TableID = parseInt(txtTableLastIndex.value);
    txtTableLastIndex.value = (TableID - 1).toString();

    var divs = testsubject.childNodes;           //获取删除完后剩余div
    var newDivs = new Array;
    for (i = 0; i < divs.length; i++) {    //遍历删除完后剩下的div
        switch (divs[i].id) {
            case undefined:
                break;
            case "TableLastIndex":
                break;
            case "testsubjects":
                break;
            case "":
                break;
            default:                //SubjectDiv
                var divID = divs[i].id.substr(10);   //subjectDivID 标号  如 subjectDivID1 取出1
                if (divID > divIndex) {     //剩余SubjectDiv 顺序在删除的SubjectDiv之后的，顺序标号向前代替删除的SubjectDiv
                    divs[i].id = "SubjectDiv" + divIndex;
                    divIndex++;
                }
                tables = divs[i].childNodes;
                var typeid = "";
                for (j = 0; j < tables.length; j++) {   //遍历删除完后剩下的table
                    switch (tables[j].id) {
                        case "addtestsubject" + divID:
                            break;
                        case undefined:
                            break;
                        case "SubjectTRLastIndex" + divID:
                            break;
                        default:        //table的id  subjectFrame
                            newDivs.push(divs[i]);    //div数组存放剩余所有SubjectDiv
                            var tableid = tables[j].id.substr(12);   //subjectFrame标号  如 subjectFrame1 取出1
                            if (tableid > delTableID) {   //剩余table的id  subjectFrame 顺序在删除的subjectFrame之后的，顺序标号向前代替删除的subjectFrame
                                tables[j].id = "subjectFrame" + delTableID;    //更改table的id  subjectFrame的顺序
                                var addtable = findObj("addtestsubjecttable" + tableid, document);
                                typeid = addtable.rows[0].cells[0].getElementsByTagName("input")[0].value   //获得试题类型ID
                                addtable.rows[0].cells[1].innerHTML = "<input type='button' name='Submitsubject' value='新增试题'  class='l-button-add' onclick=\"f_addsubject('" + typeid + "','" + tables[j].id + "')\" />";
                                addtable.id = "addtestsubjecttable" + delTableID;
                                var subjectIndexDiv = findObj("SubjectTRLastIndex" + tableid, document);
                                subjectIndexDiv.id = "SubjectTRLastIndex" + delTableID;
                                var addDiv = findObj("addtestsubject" + tableid, document);
                                addDiv.id = "addtestsubject" + delTableID;
                                // 更正表格每一行的Score列和Del列的事件参数
                                for (k = 1; k < tables[j].rows.length; k++) {
                                    tables[j].rows[k].id = delTableID + "subjectSignItemn" + k;
                                    var signContent = tables[j].rows[k].cells[2].getElementsByTagName("input")[0].value;
                                    var signScore = tables[j].rows[k].cells[3].getElementsByTagName("input")[0].value;
                                    tables[j].rows[k].cells[2].innerHTML = "<span class='questions_number' style='color:#140b88'>" + k + ".&nbsp;&nbsp;</span><input name='SubjectName" + k + "' id='SubjectName" + k + "' type='text'  class='questions_input' value='" + signContent + "' onkeyup=\"f_onkeyupSub('" + tables[j].id + "','" + delTableID + "subjectSignItemn" + k + "')\" onfocus=\"f_onFocusSub('" + tables[j].id + "','" + delTableID + "subjectSignItemn" + k + "')\" onclick=\"OneSubjectDisplay('" + k + "','" + tables[j].id + "')\" />";
                                    tables[j].rows[k].cells[3].innerHTML = "<input name='Score" + k + "' id='Score" + k + "' maxlength = '3'  class='questions_fraction' value='" + signScore + "' type='text' value='0' onkeyup=\"f_onkeyup('" + tables[j].id + "','" + delTableID + "subjectSignItemn" + k + "')\" onfocus=\"f_onFocus('" + tables[j].id + "','" + delTableID + "subjectSignItemn" + k + "')\" onclick=\"OneSubjectDisplay('" + k + "','" + tables[j].id + "')\" >&nbsp;<a href='javascript:;' onclick=\"DeleteTestSubjectRow('" + tables[j].id + "', '" + k + "')\">删除</a>";
                                }
                                delTableID++;
                            }
                    }
                }
        }
    }

    if (divDisplay == true)   //如果删除的是当前选中的题型试题列表，删除完毕，显示第一个列表
        if (newDivs.length > 0)
            newDivs[0].style.display = "block";

//    var gustBookDiv = document.getElementById("testsubject").innerHTML;
//    alert(gustBookDiv);
}
//---------试卷试题列表-------//


//---------试卷试题-------//
/**添加试卷题型中的试题行**/
function AddTestSubjectRow(tableid, subject) { 
    var signFrame = findObj(tableid, document);
    var OrderNo = tableid.substr(12, 1);  //如subjectFrame1 取出1
    var txtTRLastIndex = findObj("SubjectTRLastIndex" + OrderNo, document);  //读取最后一行的行号，存放在txtTRLastIndex文本框中
    var rowID = parseInt(txtTRLastIndex.value);
    //添加行
    var newTR = signFrame.insertRow(signFrame.rows.length);
    newTR.id = OrderNo + "subjectSignItemn" + rowID;

    //添加列:序号
    var newNoTD = newTR.insertCell(0);
    newNoTD.className = "subjectSignItem";
    newNoTD.innerHTML = "<input name='OrderNo" + rowID + "' id='OrderNo" + rowID + "' type='hidden' value='" + newTR.rowIndex.toString() + "' />";

    //添加列: 试题ID
    var newSubjectTD = newTR.insertCell(1);
    newSubjectTD.className = "subjectSignItem";
    newSubjectTD.innerHTML = "<input name='SubjectID" + rowID + "' id='SubjectID" + rowID + "' type='hidden' value='" + subject.ID + "' />";

    //添加列: 试题名称
    var newNameTD = newTR.insertCell(2);
    newNameTD.className = "subjectSignItem";
    newNameTD.innerHTML = "<span class='questions_number' style='color:#140b88'>" + rowID + ".&nbsp;&nbsp;</span><input name='SubjectName" + rowID + "' id='SubjectName" + rowID + "' type='text' class='questions_input' value='" + subject.Content + "' onkeyup=\"f_onkeyupSub('" + tableid + "','" + OrderNo + "subjectSignItemn" + rowID + "')\" onfocus=\"f_onFocusSub('" + tableid + "','" + OrderNo + "subjectSignItemn" + rowID + "')\" onclick=\"OneSubjectDisplay('" + rowID + "','" + tableid + "')\" />";

    //添加列: 试题分数和删除按钮
    var newScoreTD = newTR.insertCell(3);
    newScoreTD.className = "subjectSignItem";
    newScoreTD.innerHTML = "<input name='Score" + rowID + "' id='Score" + rowID + "' type='text' maxlength = '3' class='questions_fraction' value='0' onkeyup=\"f_onkeyup('" + tableid + "','" + OrderNo + "subjectSignItemn" + rowID + "')\" onfocus=\"f_onFocus('" + tableid + "','" + OrderNo + "subjectSignItemn" + rowID + "')\"  onclick=\"OneSubjectDisplay('" + rowID + "','" + tableid + "')\" >&nbsp;<a href='javascript:;' onclick=\"DeleteTestSubjectRow('" + tableid + "', '" + rowID + "')\">删除</a>";
    
    //将行号推进下一行
    txtTRLastIndex.value = (rowID + 1).toString();

//    var gustBookDiv = document.getElementById(divid).innerHTML;
//    alert(gustBookDiv);
}
//删除试卷试题指定行
function DeleteTestSubjectRow(tableid, rowid) {
    var signFrame = findObj(tableid, document);
    //var signItem = findObj(rowid, document);
    var OrderNo = tableid.substr(12, 1);  //如subjectFrame1 取出1
    var txtTRLastIndex = findObj("SubjectTRLastIndex" + OrderNo, document);
    var typePointsTxt = signFrame.rows[0].cells[3].innerText;          //获得当前题型总分
    typePointsTxt = typePointsTxt.replace(/\s+/g, "");
    typePointsTxt = typePointsTxt.substr(3)      //去掉“总分(”
    typePointsTxt = typePointsTxt.substr(0, (typePointsTxt.length - 1));  //去掉“)”
    var CurrentPoints = findObj("CurrentPoints", document);   //获得当前总分文本
    
    //获取将要删除的行的Index
    //var rowIndex = signItem.rowIndex;   
    //获得将要删除的行的分数
    var score = signFrame.rows[rowid].cells[3].getElementsByTagName("input")[0].value;
    //当前题型总分减去删除的行的分数
    var typePoints = parseInt(typePointsTxt) - parseInt(score);
    signFrame.rows[0].cells[3].innerHTML = "总分(" + typePoints + ")";
    //当前总分减去删除的行的分数
    CurrentPoints.value = parseInt(CurrentPoints.value) - parseInt(score);
    
    //删除指定Index的行
    signFrame.deleteRow(rowid);
    var rowID = parseInt(txtTRLastIndex.value);
    //将行号减少一行
    txtTRLastIndex.value = (rowID - 1).toString();

    var signScore = "";
    var signName = "";
    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowid; i < signFrame.rows.length; i++) {
        signFrame.rows[i].id = OrderNo + "subjectSignItemn" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].id = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].name = "OrderNo" + i;
        signFrame.rows[i].cells[0].getElementsByTagName("input")[0].value = i;
        signFrame.rows[i].cells[1].getElementsByTagName("input")[0].id = "SubjectID" + i;
        signFrame.rows[i].cells[1].getElementsByTagName("input")[0].name = "SubjectID" + i;       
        signName = signFrame.rows[i].cells[2].getElementsByTagName("input")[0].value;
        signScore = signFrame.rows[i].cells[3].getElementsByTagName("input")[0].value;
        signFrame.rows[i].cells[2].innerHTML = "<span class='questions_number' style='color:#140b88'>" + i + ".&nbsp;&nbsp;</span><input name='SubjectName" + i + "' id='SubjectName" + i + "' type='text' class='questions_input' value='" + signName + "'onkeyup=\"f_onkeyupSub('" + tableid + "','" + OrderNo + "subjectSignItemn" + i + "')\" onfocus=\"f_onFocusSub('" + tableid + "','" + OrderNo + "subjectSignItemn" + i + "')\" onclick=\"OneSubjectDisplay('" + i + "','" + tableid + "')\" >";
        signFrame.rows[i].cells[3].innerHTML = "<input name='Score" + i + "' id='Score" + i + "'maxlength = '3' class='questions_fraction' value='" + signScore + "' type='text'  onkeyup=\"f_onkeyup('" + tableid + "','" + OrderNo + "subjectSignItemn" + i + "')\" onfocus=\"f_onFocus('" + tableid + "','" + OrderNo + "subjectSignItemn" + i + "')\"  onclick=\"OneSubjectDisplay('" + i + "','" + tableid + "')\" >&nbsp;<a href='javascript:;' onclick=\"DeleteTestSubjectRow('" + tableid + "', '" + i + "')\">删除</a>";
    }
    DeletePreviewTestSubjectRow(tableid, rowid);
//    var gustBookDiv = document.getElementById(tableid).innerHTML;
//    alert(gustBookDiv);
}

//保存试卷试题信息
function GetTestSubjectRow() {
    var testsubject = findObj("testsubject", document);
    var Divs = testsubject.childNodes;
    var subjectary = new Array();
    for (i = 0; i < Divs.length; i++) {
        id = Divs[i].id;
        switch (id) {
            case undefined:
                break;
            case "TableLastIndex":
                break;
            case "testsubjects":
                break;
            case "":
                break;
            default:
                var divIndex = id.substr(10);    //subjectDivID 标号  如 subjectDivID1 取出1
                var tables = Divs[i].childNodes;
                for (j = 0; j < tables.length; j++) {
                    var id = tables[j].id;
                    switch (id) {
                        case "addtestsubject" + divIndex:
                            break;
                        case undefined:
                            break;
                        case "SubjectTRLastIndex" + divIndex:
                            break;
                        default:
                            var signFrame = tables[j];
                            var ary = new Array();
                            var rowscount = signFrame.rows.length;
                            for (k = 1; k < rowscount; k++) {
                                var subject = new Object();
                                subject["OrderNo"] = signFrame.rows[k].cells[0].getElementsByTagName("input")[0].value;
                                subject["SubjectID"] = signFrame.rows[k].cells[1].getElementsByTagName("input")[0].value;
                                subject["Score"] = signFrame.rows[k].cells[3].getElementsByTagName("input")[0].value;
                                ary.push(subject);
                            }
                            subjectary.push(ary);
                    }
                }
        }
    }
    return subjectary;
}


//---------试卷试题-------//


//---------试卷试题列表预览-------//
/**添加试卷题型对应的试题预览列表**/
function AddPreviewTestSubjectFrame(stype) {
    var txtTRLastIndex = findObj("TypeTRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    rowID = rowID - 1;    //试题列表的RowID对应题型的RowID
    var previewFrame = findObj("preview", document);

    var newUL = document.createElement("ul");
    newUL.id = "previewtype" + rowID;
    previewFrame.appendChild(newUL);

    var nameLI = document.createElement("li");
    nameLI.className = "li_tt";
    nameLI.innerHTML = stype.Type;
    newUL.appendChild(nameLI);

}

/**删除试卷题型对应的试题预览列表**/
function DelPreviewTestSubjectFrame(rowid) {
    var subjectDivID = "subjectDiv" + rowid;
    var txtTRLastIndex = findObj("TypeTRLastIndex", document);
    var rowID = parseInt(txtTRLastIndex.value);
    rowID = rowID - 1;    //试题列表的RowID对应题型的RowID
    var divIndex = subjectDivID.substr(10);
    var previewFrame = findObj("preview", document);
    var UIpreviewtype = findObj("previewtype" + rowid, document);
   
    var UIDisplay = false;
    if (UIpreviewtype.style.display == "block")
        UIDisplay = true;
    if (UIpreviewtype != null)
        UIpreviewtype.parentNode.removeChild(UIpreviewtype);

    var UIpreviewtypes = previewFrame.childNodes;

    var previewtypes = [];      //取出剩余试题预览列表
    var count = 1;
    for (i = 0; i < UIpreviewtypes.length; i++) {
        if (UIpreviewtypes[i].id != undefined) {
            previewtypes.push(UIpreviewtypes[i]);
        }
    }

    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowid; i < previewtypes.length; i++) {
        var previewsubjectJ = 0;
        var subjecttableK = 0;
        previewtypes[i].id = "previewtype" + i;
        var previewsubjects = previewtypes[i].childNodes;
        for (j = 0; j < previewsubjects.length; j++) {
            if (previewsubjects[j].id != undefined && previewsubjects[j].id != "") {
                previewsubjectJ++;
                previewsubjects[j].id = i + "previewsubject" + previewsubjectJ;
                var subjecttables = previewsubjects[j].childNodes;
                for (k = 0; k < subjecttables.length; k++) {
                    if (subjecttables[k].id != undefined) {
                        subjecttableK++;
                        subjecttables[k].id = i + "subjecttable" + subjecttableK;
                    }
                }
            }
        }    
    }

    if (UIDisplay == true)   //如果删除的是当前选中的题型试题列表，删除完毕，显示第一个列表
        if (previewtypes.length > 1)
            previewtypes[1].style.display = "block";

}

//---------试卷试题列表预览-------//


//---------试卷试题预览-------//


/**添加试卷题型中的预览试题行**/
//试题列表层ID、试题列表ID、试题对象、试题的内容
function AddPreviewTestSubjectRow(tableid, subject, subObj) {
    var OrderNo = tableid.substr(12, 1);  //如subjectFrame1 取出1
    var txtTRLastIndex = findObj("SubjectTRLastIndex"+ OrderNo, document);
    var rowID = parseInt(txtTRLastIndex.value);
    rowID = rowID - 1;
    var previewtypeUI = findObj("previewtype" + OrderNo, document);
    var subjectLI = document.createElement("li");
    subjectLI.id = OrderNo + "previewsubject" + rowID;
    subjectLI.className = "li_bc";
    previewtypeUI.appendChild(subjectLI);
    newTable = document.createElement("table");
    newTable.id = OrderNo + "subjecttable" + rowID; 
    subjectLI.appendChild(newTable);
    
    //添加一个表格行
    var newTR = newTable.insertRow(0);
    var newNoTD = newTR.insertCell(0);
    newNoTD.className = "td_Qnumber";
    newNoTD.vAlign = "top";
    newNoTD.innerHTML = rowID + "．";

    var newNameTD = newTR.insertCell(1);
    newNameTD.innerHTML = subject.Content + " <span style='color:Red'>(0分)</span>";

    switch (subject.SubjectType) {
        case "选择题":
            AddPreviewTestMultipleSubject(newTable,rowID, subObj);
            break;
        case "填空题":
            AddPreviewTestFillBlankSubject(newTable, rowID, subObj);
            break;
        case "判断题":
            AddPreviewTestJudgeSubject(newTable, rowID, subObj);
            break;
    }

    OneSubjectDisplay(rowID, tableid);

//    var gustBookDiv = document.getElementById("preview").innerHTML;
//    alert(gustBookDiv);
}

//添加试题选择题选项
function AddPreviewTestMultipleSubject(newTable, rowID, subObj) {
    var multiplesAnswer = "";
    for (i = 0; i < subObj.length; i++) {
        //添加一个表格行
        var newTR = newTable.insertRow(newTable.rows.length);
        newTR.id = 'testSignItemn' + (i + 1);
        //添加列:序号
        var newNoTD = newTR.insertCell(0);
        newNoTD.innerHTML = "<input name='multiplesOrderNo" + (i + 1) + "' type='hidden' id='multiplesOrderNo" + (i + 1) + "' value='" + (i + 1) + "' />";

        var No = Conversion((i + 1).toString());
        //添加列: 试题选项内容
        var newSubjectTD = newTR.insertCell(1);
        newSubjectTD.innerHTML = "<span>" + No + "</span><input name='MultipleAnswerName" + rowID + "'  id='MultipleAnswerID" + (i + 1) + "'  type='radio' />" + subObj[i].MultipleContent;

        if (subObj[i].Answer == true) {
            multiplesAnswer = (i + 1).toString();
            multiplesAnswer = Conversion(multiplesAnswer);
        }
    }

    var AnswerTR = newTable.insertRow(newTable.rows.length);
    //添加空列
    var newTD = AnswerTR.insertCell(0);
    newTD.innerHTML = "";

    //添加列: 选择题答案
    var newAnswer = AnswerTR.insertCell(1);
    newAnswer.innerHTML = "<div>参考答案：" + multiplesAnswer + "</div>";

}

//添加试题填空题空格
function AddPreviewTestFillBlankSubject(newTable, rowID, subObj) {

    for (i = 0; i < subObj.length; i++) {
        //添加一个表格行
        var newTR = newTable.insertRow(newTable.rows.length);
        newTR.id = 'testSignItemn' + (i + 1);
        //添加列:序号
        var newNoTD = newTR.insertCell(0);
        newNoTD.innerHTML = "<input name='fillblanksOrderNo" + (i + 1) + "' type='hidden' id='fillblanksOrderNo" + (i + 1) + "' value='" + (i + 1) + "' />";

        //添加列: 填空题空格
        var newSubjectTD = newTR.insertCell(1);
        newSubjectTD.innerHTML = "(" + (i + 1) + ")．<input name='fillblanksAnswer" + (i + 1) + "'  id='fillblanksAnswer" + (i + 1) + "' type='text'/>";
    }

    var AnswerTR = newTable.insertRow(newTable.rows.length);
    //添加空列
    var newTD = AnswerTR.insertCell(0);
    newTD.innerHTML = "";

    var addDiv = document.createElement("div");

    //遍历填空题答案
    for (i = 0; i < subObj.length; i++) {
        var div = document.createElement("div");
        div.className = "fillanswer";
        div.innerHTML = "<span>(" + subObj[i].OrderNo + ")．</span>参考答案：<span>" + subObj[i].Answer + "</span>";
        addDiv.appendChild(div);
    }

    //添加列: 填空题答案
    var newAnswer = AnswerTR.insertCell(1);
    newAnswer.appendChild(addDiv);
}

//添加试题判断题选项
function AddPreviewTestJudgeSubject(newTable, rowID, subObj) {

    var judgesAnswer = "";
    for (i = 0; i < subObj.length; i++) {
        //添加一个表格行
        var newTR = newTable.insertRow(newTable.rows.length);
        newTR.id = 'testSignItemn' + (i + 1);

        //添加空列
        var newNoTD = newTR.insertCell(0);
        newNoTD.innerHTML = "";

        //添加列: 判断题内容
        var newSubjectTD = newTR.insertCell(1);
        newSubjectTD.innerHTML = "<input name='JudgesAnswerName" + rowID + "'  id=''judgesTrue" + rowID + "'  type='radio' />正确&nbsp;&nbsp;<input name= 'JudgesAnswerName" + rowID + "'   id='judgesFalse" + rowID + "'  type='radio'  />错误";

        if (subObj[i].Answer == true) { judgesAnswer = "正确"; }
        else { judgesAnswer = "错误"; }
    }

    var AnswerTR = newTable.insertRow(newTable.rows.length);
    //添加空列
    var newTD = AnswerTR.insertCell(0);
    newTD.innerHTML = "";

    //添加列: 判断题答案
    var newAnswer = AnswerTR.insertCell(1);
    newAnswer.innerHTML = "<div>参考答案: " + judgesAnswer + "</div>";
}

//删除试卷试题预览指定行
function DeletePreviewTestSubjectRow(tableid, rowid) {
    var OrderNo = tableid.substr(12, 1);  //如subjectFrame1 取出1
    var previewtypeUI = findObj("previewtype" + OrderNo, document);
    var subjectLI = findObj(OrderNo + "previewsubject" + rowid, document);

    var LIDisplay = false;
    if (subjectLI.style.display == "block")
        LIDisplay = true;
    if (subjectLI != null)
        subjectLI.parentNode.removeChild(subjectLI);

    var subjectsLI = previewtypeUI.childNodes;
    var subjects = [];
    // 获得subjecttable个数
    var count = 1;
    for (i = 0; i < subjectsLI.length; i++) {
        if (subjectsLI[i].id != undefined) {
            subjects.push(subjectsLI[i]);
        }
    }

    // 重新排列序号，如果没有序号，这一步省略
    for (i = rowid; i < subjects.length; i++) {
        var tables = subjects[i].childNodes;
        for (j = 0; j < tables.length; j++) {
            if (tables[j].id != undefined) {
                subjects[i].id = OrderNo + "previewsubject" + i;
                tables[j].id = OrderNo + "subjecttable" + i;
                tables[j].rows[0].cells[0].innerHTML = i + "．";
            }
        }
    }

    if (LIDisplay == true)   //如果删除的是当前选中的题型试题列表，删除完毕，显示第一个列表
        if (subjects.length > 1) 
            subjects[1].style.display = "block";

//    var gustBookDiv = document.getElementById("previewtype" + OrderNo).innerHTML;
//    alert(gustBookDiv);
}


///根据试题行ID显示对应的预览试题内容
function OneSubjectDisplay(subjectOrderNo, tableID) {
    var subjectFrame = findObj(tableID, document);
    subjectFrame.rows[subjectOrderNo].cells[2].getElementsByTagName("input")[0].blur();
    var OrderNo = tableID.substr(12, 1);  //如subjectFrame1 取出1
    var previewFrame = findObj("preview", document);
    var previewtypeUL = findObj("previewtype" + OrderNo, document);
    var previewsubjects = previewtypeUL.childNodes;
    for (i = 1; i < previewsubjects.length; i++) {
        var id = previewsubjects[i].id;
        switch (id) {
            case OrderNo + "previewsubject" + subjectOrderNo:
                previewsubjects[i].style.display = "block";
                break;
            case undefined:
                break;
            case "":
                break;
            default:
                previewsubjects[i].style.display = "none";
        }
    }
}

function Conversion(OrderNo) {
    var No = "";
    switch (OrderNo) {
        case "1": { No = "A："; break; }
        case "2": { No = "B："; break; }
        case "3": { No = "C："; break; }
        case "4": { No = "D："; break; }
        case "5": { No = "E："; break; }
        case "6": { No = "F："; break; }
        case "7": { No = "G："; break; }
        case "8": { No = "H："; break; }
        case "9": { No = "I："; break; }
        case "10": { No = "J："; break; }
        case "11": { No = "K："; break; }
        case "12": { No = "L："; break; }
        case "13": { No = "M："; break; }
        case "14": { No = "N："; break; }
        case "15": { No = "O："; break; }
        case "16": { No = "P："; break; }
        case "17": { No = "Q："; break; }
        case "18": { No = "R："; break; }
    }
    return No;
}

//---------试卷试题预览-------//