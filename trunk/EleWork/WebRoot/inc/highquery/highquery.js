
	 /********************************************
	 * 文件名称：highquery.js
	 * 功能描述：高级组合查询控件
	 * 创建日期：2008-07-22
	 * @author：codeslave
	 * @version 0.4
	 *********************************************/

// 配置
function HQConfig()
{
	this.dialect = "oracle"; // 方言
	//this.basePath = ""; // 基础路径
	this.version = "0.4"; // 版本
}

var HQConfig = new HQConfig();

// 加载js
var $import = function(s)
{   
	var script = document.createElement("script");
	script.setAttribute("type", "text/javascript");
	script.setAttribute("src", s);
	try   
	{
		document.getElementsByTagName("head")[0].appendChild(script);   
	}
	catch(e)   
	{
	}
};

// 如果目录改变这里要相应改变
$import("highquery/calendar.js");
$import("highquery/check.js");
$import("highquery/hqconfig.js");
$import("highquery/" + HQConfig.dialect + ".js");

/******************************以下为初始化部分******************************/

var autoId = 0; // 自增变量

// 存放字段实体的容器
function FieldList()
{
	this.fields = [];
}

// 添加实体方法
FieldList.prototype.add = function(field)
{
	this.fields[this.fields.length] = field;
};

var FieldList = new FieldList();

// 字段实体
function Field(name, alias, type, length, event)
{
	this.name = name; // 字段名
	this.alias = alias; // 别名
	this.type = type; // 类型
	this.length = length; // 长度
	this.event = event; // 事件(主要用于选择外键表数据)
}

// 取得一般查询条件的html文件(日期)。
function getDateHtml(ContentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDate('" + ContentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + ContentId + "') value='清除'>";
	return strHtml;
}

// 取得in或not in查询条件的html文件(日期)。
function getDateInHtml(ContentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDateIn('" + ContentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + ContentId + "') value='清除'>";
	return strHtml;
}

// 取得一般查询条件的html文件(日期时间)。
function getDateTimeHtml(ContentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDateTime('" + ContentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + ContentId + "') value='清除'>";
	return strHtml;
}

// 取得in或not in查询条件的html文件(日期时间)。
function getDateTimeInHtml(ContentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDateTimeIn('" + ContentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + ContentId + "') value='清除'>";
	return strHtml;
}

// 取得like查询条件的html文本。
function getLikeHtml(id) 
{
	var strHtml = "<select id='darkSign_" + id + "'><option value='0'>%</option><option value='1'>_</option></select>";
	strHtml += "&nbsp;<span id='darkB_" + id + "' onClick=addDarkSign('b') style='font-weight: bold;cursor: hand'>←→</span>" + 
		"&nbsp;<span id='darkL_" + id + "' onClick=addDarkSign('l') style='font-weight: bold;cursor: hand'>←</span>" + 
		"&nbsp;<span id='darkR_" + id + "' onClick=addDarkSign('r') style='font-weight: bold;cursor: hand'>→</span>";
	return strHtml;
}

// 取得is查询条件的html文本。
function getIsHtml(id) 
{
	var strHtml = "<span id='null_" + id + "' onClick=document.getElementById('bContent_" + id + "').value='null'" + 
		" style='font-weight: bold;cursor: hand'>○</span>" + 
		"&nbsp;<span id='notNull_" + id + "' onClick=document.getElementById('bContent_" + id + "').value='not&nbsp;null'" + 
		" style='font-weight: bold;cursor: hand'>●</span>";
	return strHtml;
}

// 取得带事件查询条件的html文本。
function getEventHtml(ContentId, hibContentId, event) 
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=executeEvent('" + ContentId + "','" + hibContentId + "','" + event + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=\"clearContent('" + ContentId + "');clearContent('" + hibContentId + "');\"" + 
		"value='清除'>";
	return strHtml;
}

// 取得带事件查询条件的html文本(用于in或not in查询)。
function getEventInHtml(ContentId, hibContentId, event) 
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=executeEventIn('" + ContentId + "','" + hibContentId + "','" + event + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=\"clearContent('" + ContentId + "');clearContent('" + hibContentId + "');\"" + 
		"value='清除'>";
	return strHtml;
}

// 初始化字段列表。
function initField(id)
{
	var strField = "<select onChange='changeField()' id='field_" + id + "'>";
	var arrField = FieldList.fields;
	for(var i = 0; i < arrField.length; i++)
	{
		strField += "<option value='" + arrField[i].name + "'>" + arrField[i].alias + "</option>"
	}
	strField += '</select>';
	return strField;
}

// 初始化条件相连的运算符。
function initJoin(id)
{
	var strJoin = "<select id='join_" + id + "'><option value='and'>AND</option><option value='or'>OR</option></select>";
	return strJoin;
}

// 初始化条件运算符。
function initCondition(id)
{
	var strCondition = "<select onChange='changeCondition()' id='condition_" + id + "'>" + 
		"<option value='='>=</option><option value='<'><</option>" + 
		"<option value='<='><=</option><option value='>'>></option>" +
		"<option value='>='>>=</option><option value='<>'><></option>" +
		"<option value='in'>IN</option><option value='not_in'>NOT IN</option>" + 
		"<option value='between'>BETWEEN</option><option value='is'>IS</option>" +
		"<option value='like'>LIKE</option></select>";
	strCondition += "<span id='signPanel_" + id + "'></span>" // 存放查询符号，主要用于like和is查询
	return strCondition;
}

// 初始化条件内容。
function initContent(id)
{
	var strContent = "<input class='HQ_TEXT' id='bContent_" + id + "' maxLength=" + FieldList.fields[FieldList.fields.length - 1].length + ">";
	strContent += "<input type='hidden' class='HQ_TEXT' id='hibBContent_" + id + "'>";
	strContent += "<span id='bChoicePanel_" + id + "'></span>"; // 存放第一个文本控件的"选择"和"清除"按钮
	strContent += "<span id='eContentPanel_" + id + "'></span>"; // 存放第二个文本控件、以及eChoicePanel面板和他内部的"选择"和"清除"按钮
	return strContent;
}

// 初始化控件
function initHighQuery()
{
	strHtml = "<table id='highQuery' class='HQ_TABLE'>" + 
		"<th class='HQ_TH'>连接</th>" +
		"<th class='HQ_TH'>左括号</th>" +
		"<th class='HQ_TH'>字段</th>" +
		"<th class='HQ_TH'>运算符</th>" +
		"<th class='HQ_TH'>内容</th>" +
		"<th class='HQ_TH'>右括号</th>" +
		"<th class='HQ_TH'>操作</th>" +
		"</table>";

	var objHighQueryPanel = document.getElementById("highQueryPanel");
	if(objHighQueryPanel != null)
	{
		objHighQueryPanel.innerHTML = strHtml;
		// 初始化第一行。
		initFristRow();
	} 
	else
		alert("没有发现highQueryPanel！");
}

// 初始化第一行。
function initFristRow()
{
	highQuery.insertRow();
	var newRow = highQuery.rows[highQuery.rows.length - 1];
	
	newRow.id = "row_" + autoId;

	newRow.insertCell();
	newRow.cells[0].className = "HQ_TD";
	newRow.cells[0].innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	newRow.insertCell();
	newRow.cells[1].className = "HQ_TD";
	newRow.cells[1].innerHTML = "<span id='lParenthesisPanel_" + autoId + "' style='font-weight: bold;'></span>"; // 左括号面板
	
	newRow.insertCell();
	newRow.cells[2].className = "HQ_TD";
	newRow.cells[2].innerHTML = initField(autoId);
	
	newRow.insertCell();
	//newRow.cells[3].width = 200;
	newRow.cells[3].className = "HQ_TD";
	newRow.cells[3].innerHTML = initCondition(autoId);
	
	newRow.insertCell();
	newRow.cells[4].className = "HQ_TD";
	newRow.cells[4].innerHTML = initContent(autoId);

	newRow.insertCell();
	newRow.cells[5].className = "HQ_TD";
	newRow.cells[5].innerHTML = "<span id='rParenthesisPanel_" + autoId + "' style='font-weight: bold;'></span>"; // 右括号面板
	
	newRow.insertCell();
	newRow.cells[6].className = "HQ_TD";
	newRow.cells[6].innerHTML = "<input type='button' class='HQ_BUTTON' value='+(' onClick=addParenthesis('" + autoId + "','l')>&nbsp;" + 
		"<input type='button' class='HQ_BUTTON' value='+)' onClick=addParenthesis('" + autoId + "','r')>&nbsp;" +
		"<input type='button' class='HQ_BUTTON' value='-(' onClick=removeParenthesis('" + autoId + "','l')>&nbsp;" +
		"<input type='button' class='HQ_BUTTON' value='-)' onClick=removeParenthesis('" + autoId + "','r')>&nbsp;" +
		"<input type='button' class='HQ_BUTTON' value='增加条件' onClick='addRow()'>";
	
	var objField = document.getElementById("field_" + autoId); // 取得当前的字段选择控件对象
	if(objField.options.length > 0)
		objField.options.selectedIndex = objField.options.length - 1; // 选中字段列表的最后一个"请选择一个字段"

	autoId += 1; // autoId加1
}

/******************************以下为条件增删部分******************************/

// 新添加一行。
function addRow()
{
	highQuery.insertRow();
	var newRow = highQuery.rows[highQuery.rows.length - 1];

	newRow.id = "row_" + autoId;
	
	newRow.insertCell();
	newRow.cells[0].className = "HQ_TD";
	newRow.cells[0].innerHTML = initJoin(autoId);
	
	newRow.insertCell();
	newRow.cells[1].className = "HQ_TD";
	newRow.cells[1].innerHTML = "<span id='lParenthesisPanel_" + autoId + "' style='font-weight: bold;'></span>"; // 左括号面板

	newRow.insertCell();
	newRow.cells[2].className = "HQ_TD";
	newRow.cells[2].innerHTML = initField(autoId);
	
	newRow.insertCell();
	newRow.cells[3].className = "HQ_TD";
	newRow.cells[3].innerHTML = initCondition(autoId);
	
	newRow.insertCell();
	newRow.cells[4].className = "HQ_TD";
	newRow.cells[4].innerHTML = initContent(autoId);
	
	newRow.insertCell();
	newRow.cells[5].className = "HQ_TD";
	newRow.cells[5].innerHTML = "<span id='rParenthesisPanel_" + autoId + "' style='font-weight: bold;'></span>"; // 右括号面板

	newRow.insertCell();
	newRow.cells[6].className = "HQ_TD";
	newRow.cells[6].innerHTML = "<input type='button' class='HQ_BUTTON' value='+(' onClick=addParenthesis('" + autoId + "','l')>&nbsp;" + 
		"<input type='button' class='HQ_BUTTON' value='+)' onClick=addParenthesis('" + autoId + "','r')>&nbsp;" +
		"<input type='button' class='HQ_BUTTON' value='-(' onClick=removeParenthesis('" + autoId + "','l')>&nbsp;" +
		"<input type='button' class='HQ_BUTTON' value='-)' onClick=removeParenthesis('" + autoId + "','r')>&nbsp;" +
		"<input type='button' class='HQ_BUTTON' value='删除' onClick=removeRow('" + newRow.id + "')>";
	
	var objField = document.getElementById("field_" + autoId); // 取得当前的字段选择控件对象
	if(objField.options.length > 0)
		objField.options.selectedIndex = objField.options.length - 1; // 选中字段列表的最后一个"请选择一个字段"

	autoId += 1; // autoId加1
}

// 删除行。
function removeRow(rowId)
{
	var trRow = document.getElementById(rowId);
	trRow.removeNode(true);
}

// 生成条件查询语句。
function interpreter()
{
	var arrReturn = new Array();
	var strCondition = "";
	var strAliasCondition = "";
	var isNoProblem = true;
	
	// 语法检测
	if(!checkExpression())
	{
		isNoProblem = false;
		arrReturn[0] = "";
		arrReturn[1] = "";
		return arrReturn;
	}

	for(var i = 1; i < highQuery.rows.length; i++)
	{
		var id = highQuery.rows[i].id.split("_")[1];
		var objField = document.getElementById("field_" + id); // 取得字段选择控件对象
		var objCondition = document.getElementById("condition_" + id); // 取得条件运算符选择控件对象
		var objBContent = document.getElementById("bContent_" + id); // 取得第一个条件内容文件控件对象
		var objHibBContent = document.getElementById("hibBContent_" + id); // 取得第一个条件内容文件控件对象(隐藏的)
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得右括号面板对象
		var isHasEvent = FieldList.fields[objField.selectedIndex].event == ""?false:true; // 是否有事件
		var strType = FieldList.fields[objField.selectedIndex].type; // 内容类别
		var strAlias = FieldList.fields[objField.selectedIndex].alias; // 字段别名

		if(i > 1) // 如果不是第一个条件就要加上连接条件符号(AND,OR)。
		{
			var objJoin = document.getElementById("join_" + id); // 条件相连的运算符
			strCondition += " " + objJoin.value + " ";
			strAliasCondition +=  " " + objJoin.value + " ";
		}

		// 加上左括号
		strCondition += objLParenthesisPanel.outerText;
		strAliasCondition += objLParenthesisPanel.outerText;
			
		if(objField.value != "") // 字段是否为空
		{
			strCondition += objField.value;
			strAliasCondition += strAlias;
		}
		else
		{
			isNoProblem = false;
			alert("所选的条件中存在不明确的字段名，请先选择一个有效字段名！");
			objField.focus();
			arrReturn[0] = "";
			arrReturn[1] = "";
			return arrReturn;
		}

		switch(objCondition.value)
		{
			case "like": // 查询条件为like时
				if(checkDataLike(objBContent, strAlias, emendationType(strType))) 
				{
					strCondition += likeCondition(objBContent, strType, objCondition.value);
					strAliasCondition += likeCondition(objBContent, strType, objCondition.value);
				}
				else
					isNoProblem = false;
				break;
			case "in": // 查询条件为in时
				if(checkDataIn((isHasEvent?objHibBContent:objBContent), strAlias, emendationType(strType)))
				{
					strCondition += inCondition((isHasEvent?objHibBContent:objBContent), strType, objCondition.value);
					strAliasCondition += inCondition(objBContent, strType, objCondition.value);
				}
				else
					isNoProblem = false;
				break;
			case "not_in": // 查询条件为not in时
				if(checkDataIn((isHasEvent?objHibBContent:objBContent), strAlias, emendationType(strType)))
				{
					strCondition += inCondition((isHasEvent?objHibBContent:objBContent), strType, objCondition.value);
					strAliasCondition += inCondition(objBContent, strType, objCondition.value);
				}
				else
					isNoProblem = false;
				break;
			case "between": // 查询条件为between时
				var objEContent = document.getElementById("eContent_" + id); // between查询时才会出现的第二个条件内容对象
				var objHibEContent = document.getElementById("hibEContent_" + id); // between查询时才会出现的第二个条件内容对象(隐藏的)
				if(checkDataBetween((isHasEvent?new Array(objHibBContent, objHibEContent):new Array(objBContent, objEContent)), strAlias, emendationType(strType)))
				{
					strCondition += betweenCondition((isHasEvent?objHibBContent:objBContent), (isHasEvent?objHibEContent:objEContent), strType, objCondition.value);
					strAliasCondition += betweenCondition(objBContent, objEContent, strType, objCondition.value);
				}
				else
					isNoProblem = false;
				break;
			case "is": // 查询条件为is时
				if(checkDataIs(objBContent, strAlias, "checknull"))
				{
					strCondition += isCondition(objBContent, strType, objCondition.value);
					strAliasCondition += isCondition(objBContent, strType, objCondition.value);
				}
				else
					isNoProblem = false;
				break;
			default: // 查询条件为其他时
				if(checkDataNormal((isHasEvent?objHibBContent:objBContent), strAlias, emendationType(strType)))
				{
					strCondition += " " + objCondition.value + normalCondition((isHasEvent?objHibBContent:objBContent), strType);
					strAliasCondition +=  " " + objCondition.value + normalCondition(objBContent, strType);
				}
				else
					isNoProblem = false;
				break;
		}
		if(isNoProblem != true)
		{
			arrReturn[0] = "";
			arrReturn[1] = "";
			return arrReturn;
		}

		// 加上右括号
		strCondition += objRParenthesisPanel.outerText;
		strAliasCondition += objRParenthesisPanel.outerText;
	}
	arrReturn[0] = strAliasCondition;
	arrReturn[1] = strCondition;
	return arrReturn;
}

/******************************以下为字段类型验测函数******************************/

// 根据类型返回相应校正的项目。
function emendationType(strType)
{
	if(strType == 'number')
	{
		return "checknumber";
	}
	else if(strType == 'date' || strType == 'datetime')
	{
		return "";
	}
	return "";
}

/******************************以下为校正函数**********************************/

// 语法校正
function checkExpression()
{
	var strLParenthesis = "";
	var strRParenthesis = "";

	for(var i = 1; i < highQuery.rows.length; i++)
	{
		var id = highQuery.rows[i].id.split("_")[1];
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得右括号面板对象
		strLParenthesis += objLParenthesisPanel.outerText;
		strRParenthesis += objRParenthesisPanel.outerText;
	}

	if(strLParenthesis.length != strRParenthesis.length)
	{
		alert("\"(\"与\")\"的数量不对应，请检查！");
		return false;
	}
	return true;
}

// 数据校正(like查询专用)。
function checkDataLike(objTemp, strText, action)
{
	var strTemp = objTemp.value;
	//if(strTemp == "" || action == "checkdate")
	//	return true;
	strTemp = strTemp.replace(/\%/g, "");
	strTemp = strTemp.replace(/\_/g, "");
	if(strTemp == "")
		return true;
	return checkS(objTemp, strTemp, strText, action);
}

// 数据校正(in或not in查询专用)。
function checkDataIn(objTemp, strText, action)
{
	var strTemp = objTemp.value;
	var temps = strTemp.split(',');
	var boolOK = true;
	for(var i = 0; i < temps.length; i++)
	{
		if(temps[i] != "")
			boolOK = checkS(objTemp, temps[i], strText, action);
		if(boolOK == false)
			break;
	}
	return boolOK;
}

// 数据校正。(between查询专用)
function checkDataBetween(objTemp, strText, action)
{
	var strTemp = new Array(objTemp[0].value, objTemp[1].value);
	var boolOK = true;
	for(var i = 0; i < objTemp.length; i++)
	{
		if(strTemp[i] != "")
			boolOK = checkS(objTemp[i], strTemp[i], strText, action);
		if(boolOK == false)
			break;
	}
	return boolOK;
}

// 数据校正。(is查询时用)
function checkDataIs(objTemp, strText, action)
{
	var strTemp = objTemp.value;
	return checkS(objTemp, strTemp, strText, action);
}

// 数据校正。(一般查询时用)
function checkDataNormal(objTemp, strText, action)
{
	var strTemp = objTemp.value;
	if(strTemp == "")
		return true;
	return checkS(objTemp, strTemp, strText, action);
}

/******************************以下为触发功能******************************/

// 改变所选字段时，相应改变界面。
function changeField()
{
	var objField = window.event.srcElement; // 取得字段选择控件
	var id = objField.id.split("_")[1];
	var trRow = document.getElementById("row_" + id); // 取得行对象。
	var objCondition = document.getElementById("condition_" + id); // 取得条件运算符选择控件对象
	var objSignPanel = document.getElementById("signPanel_" + id); // 取得查询符号面板对象
	var objBChoicePanel = document.getElementById("bChoicePanel_" + id); // 取得第一个内容文本"选择"面板对象
	var objEContentPanel = document.getElementById("eContentPanel_" + id); // 取得第二个内容文本面板对象
	var objBContent = document.getElementById("bContent_" + id); // 取得第一个条件内容文件控件对象
	var objHibBContent = document.getElementById("hibBContent_" + id); // 取得第一个条件内容文件控件对象(隐藏的)
	var isDate = FieldList.fields[objField.selectedIndex].type == "date"?true:false;
	var isDateTime = FieldList.fields[objField.selectedIndex].type == "datetime"?true:false;
	var isHasEvent = FieldList.fields[objField.selectedIndex].event == ""?false:true; // 是否有事件
	var event = !isHasEvent?"":FieldList.fields[objField.selectedIndex].event; // 取得事件
	objBContent.value = "";
	objHibBContent.value = "";
	objBContent.maxLength = FieldList.fields[objField.selectedIndex].length; // 根据字段长度改变文本控件的长度
	
	// 如果对应的字段类型是date或datetime、又或者是有事件存在时,删除like查询条件
	if(isDate || isDateTime || isHasEvent) 
	{
		var conditionTemp = objCondition.value;
		if(objCondition.options[objCondition.length-1].value == "like")
		{
			objCondition.options.remove(objCondition.options[objCondition.length-1].index)
			objSignPanel.innerHTML = ""; // 清除查询符号面板内容
		}
		if(conditionTemp != "like")
			objCondition.value = conditionTemp;
	}
	else 
	{
		if(objCondition.options[objCondition.length-1].value != "like")
		{
			objCondition.options.add(new Option('LIKE', 'like'));
		}
	}
	
	// 内容控件的只读属性处理
	if(isDate || isDateTime || objCondition.value == "is" || isHasEvent)
		objBContent.setAttribute("readOnly", "true");
	else 
		objBContent.setAttribute("readOnly", "");
	
	switch(objCondition.value)
	{
		case "like": // 查询条件为like时
			objSignPanel.innerHTML = getLikeHtml(id);
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
		case "in": // 查询条件为in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventInHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
		case "not_in": // 查询条件为not in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventInHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
		case "between": // 查询条件为between时
			var objEContent = document.getElementById("eContent_" + id); // between查询时才会出现的第二个条件内容对象
			var objHibEContent = document.getElementById("hibEContent_" + id); // between查询时才会出现的第二个条件内容对象(隐藏的)
			var objEChoicePanel = document.getElementById("eChoicePanel_" + id); // between查询时才会出现的第二个内容文本"选择"面板对象
			objEContent.value = ""; // 如果查询条件使用了between时，这个元素就存在
			objHibEContent.value = ""; // 如果查询条件使用了between时，这个元素就存在
			objEContent.maxLength = objBContent.maxLength;
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
			{
				objEContent.setAttribute("readOnly", "true");
				objBChoicePanel.innerHTML = isDate?getDateHtml("bContent_" + id):getDateTimeHtml("bContent_" + id);
				objEChoicePanel.innerHTML = isDate?getDateHtml("eContent_" + id):getDateTimeHtml("eContent_" + id);
			}
			else if(isHasEvent) // 如果有事件存在
			{
				objEContent.setAttribute("readOnly", "true");
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
				objEChoicePanel.innerHTML = getEventHtml("eContent_" + id, "hibEContent_" + id, event);
			}
			else
			{
				objEContent.setAttribute("readOnly", "");
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
				objEChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第二个内容文本"选择"面板内容(不论是否存在)
			}
			break;
		case "is": // 查询条件为is时
			objSignPanel.innerHTML = getIsHtml(id);
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
		default: // 查询条件为其他时
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateHtml("bContent_" + id):getDateTimeHtml("bContent_" + id);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
	}
}

// 改变条件运算符时，相应改变界面。
function changeCondition()
{
	var objCondition = window.event.srcElement; // 取得条件运算符选择控件对象
	var id = objCondition.id.split("_")[1];
	var trRow = document.getElementById("row_" + id); // 取得行对象。
	var objField = document.getElementById("field_" + id); // 取得字段选择控件对象
	var objSignPanel = document.getElementById("signPanel_" + id); // 取得查询符号面板对象
	var objBChoicePanel = document.getElementById("bChoicePanel_" + id); // 取得第一个内容文本"选择"面板对象
	var objEContentPanel = document.getElementById("eContentPanel_" + id); // 取得第二个内容文本面板对象
	var objBContent = document.getElementById("bContent_" + id); // 取得第一个条件内容文件控件对象
	var objHibBContent = document.getElementById("hibBContent_" + id); // 取得第一个条件内容文件控件对象(隐藏的)
	var isDate = FieldList.fields[objField.selectedIndex].type == "date"?true:false;
	var isDateTime = FieldList.fields[objField.selectedIndex].type == "datetime"?true:false;
	var isHasEvent = FieldList.fields[objField.selectedIndex].event == ""?false:true; // 是否有事件
	var event = !isHasEvent?"":FieldList.fields[objField.selectedIndex].event; // 取得事件
	objBContent.maxLength = FieldList.fields[objField.selectedIndex].length; // 根据字段长度改变文本控件的长度
	
	// 如果文本内容长度大于最大长度(通常出现在in查询时)就清除内容或者条件运算符为"is"
	if(objBContent.value.length > objBContent.maxLength || objCondition.value == "is")
	{
		objBContent.value = "";
		objHibBContent.value = "";
	}
	
	// 内容控件的只读属性处理
	if(isDate || isDateTime || objCondition.value == "is" || isHasEvent)
		objBContent.setAttribute("readOnly", "true");
	else 
		objBContent.setAttribute("readOnly", "");

	switch(objCondition.value)
	{
		case "like": // 查询条件为like时
			objSignPanel.innerHTML = getLikeHtml(id);
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
		case "in": // 查询条件为in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventInHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = "";
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
		case "not_in": // 查询条件为not in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventInHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = "";
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
		case "between": // 查询条件为between时
			var maxLen = objBContent.maxLength;
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
			{
				objBChoicePanel.innerHTML = isDate?getDateHtml("bContent_" + id):getDateTimeHtml("bContent_" + id);
				objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' readOnly maxLength='" + maxLen + "'>" +
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" +
					"<span id='eChoicePanel_" + id + "'>" + (isDate?getDateHtml("eContent_" + id):getDateTimeHtml("eContent_" + id)) + "</span>";
			}
			else if(isHasEvent) // 如果有事件存在
			{
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
				objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' readOnly maxLength='" + maxLen + "'>" +
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" +
					"<span id='eChoicePanel_" + id + "'>" + getEventHtml("eContent_" + id, "hibEContent_" + id, event) + "</span>";
			}
			else
			{
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
				objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' maxLength='" + maxLen + "'>" + 
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" + 
					"<span id='eChoicePanel_" + id + "'></span>";
			}
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			break;
		case "is": // 查询条件为is时
			objSignPanel.innerHTML = getIsHtml(id);
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
		default: // 查询条件为其他时
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateHtml("bContent_" + id):getDateTimeHtml("bContent_" + id);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
	}
}

/******************************以下为应用函数******************************/

// 添加模糊查询符号。
function addDarkSign(strFlag)
{
	var id = window.event.srcElement.id.split("_")[1];
	var objBContent = document.getElementById("bContent_" + id); // 取得当前的第一个条件内容文件控件对象
	var objDarkSign = document.getElementById("darkSign_" + id); // 取得当前的标记选择控件对象('%','_')

	if(objBContent.value != "")
	{
		if(objDarkSign.value == "0")
		{
			if(strFlag == 'b')
			{
				objBContent.value = objBContent.value.trimPS();	// 清除内容两边的"%"
				objBContent.value = "%" + objBContent.value + "%"; // 两边模糊。
			}
			else if(strFlag == 'l')
			{
				objBContent.value = objBContent.value.trimPS(); // 清除内容两边的"%"
				objBContent.value = "%" + objBContent.value; // 左边模糊。
			}
			else if(strFlag == 'r')
			{
				objBContent.value = objBContent.value.trimPS(); // 清除内容两边的"%"
				objBContent.value = objBContent.value + "%"; // 右边模糊。
			}
		}
		else if(objDarkSign.value == "1")
		{
			if(strFlag == 'b')
			{
				objBContent.value = "_" + objBContent.value + "_"; // 两边模糊。
			}
			else if(strFlag == 'l')
			{
				objBContent.value = "_" + objBContent.value; // 左边模糊。
			}
			else if(strFlag == 'r')
			{
				objBContent.value = objBContent.value + "_"; // 右边模糊。
			}
		}

	}
}

//添加"(" 或 ")"
function addParenthesis(id, strFlag)
{
	if(strFlag == "l")
	{
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		objLParenthesisPanel.innerText = objLParenthesisPanel.outerText + "(";
	}
	else if(strFlag == 'r')
	{
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得左括号面板对象
		objRParenthesisPanel.innerText = objRParenthesisPanel.outerText + ")";
	}
}

//删除"(" 或 ")"
function removeParenthesis(id, strFlag)
{
	if(strFlag == "l")
	{
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		if(objLParenthesisPanel.outerText.length > 0)
			objLParenthesisPanel.innerText = objLParenthesisPanel.outerText.substring(0, objLParenthesisPanel.outerText.length - 1);
	}
	else if(strFlag == 'r')
	{
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得左括号面板对象
		objRParenthesisPanel.innerText = objRParenthesisPanel.outerText.substring(0, objRParenthesisPanel.outerText.length - 1);
	}
}

// 清除内容。
function clearContent(ContentId)
{
	var objContent = document.getElementById(ContentId);
	objContent.value = "";
}

// 选择查询日期。
function selDate(ContentId)
{
	var objContent = document.getElementById(ContentId);
	objContent.value = showDateForm();
}

// 选择查询日期(in或not in查询专用)。
function selDateIn(ContentId)
{
	var objContent = document.getElementById(ContentId);
	if(objContent.value == "")
		objContent.value = showDateForm();
	else
	{
		var strTemp = showDateForm();
		objContent.value += (strTemp==""?strTemp:"," + strTemp);
	}
}

// 选择查询日期时间。
function selDateTime(ContentId)
{
	var objContent = document.getElementById(ContentId);
	objContent.value = showDateTimeForm();
}

// 选择查询日期时间(in或not in查询专用)。
function selDateTimeIn(ContentId)
{
	var objContent = document.getElementById(ContentId);
	if(objContent.value == "")
		objContent.value = showDateTimeForm();
	else
	{
		var strTemp = showDateTimeForm();
		objContent.value += (strTemp==""?strTemp:"," + strTemp);
	}
}

// 执行事件
function executeEvent(ContentId, hibContentId, event)
{
	var arrReturn = eval(event + "();");
	if(arrReturn != null && arrReturn.length == 2)
	{
		var objContent = document.getElementById(ContentId);
		var objHibContent = document.getElementById(hibContentId);
		objHibContent.value = arrReturn[0];
		objContent.value = arrReturn[1];
	}
}

// 执行事件(用于in或not in查询)
function executeEventIn(ContentId, hibContentId, event)
{
	var arrReturn = eval(event + "();");
	if(arrReturn != null && arrReturn.length == 2)
	{
		var objContent = document.getElementById(ContentId);
		var objHibContent = document.getElementById(hibContentId);
		if(objContent.value == "")
		{
			objHibContent.value = arrReturn[0];
			objContent.value = arrReturn[1];
		}
		else
		{
			objHibContent.value += "," + arrReturn[0];
			objContent.value += "," + arrReturn[1];
		}
	}
}
