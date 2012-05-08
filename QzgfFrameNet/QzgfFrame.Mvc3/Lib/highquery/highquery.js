
	 /********************************************
	 * 文件名称：highquery.js
	 * 功能描述：高级组合查询控件
	 * 创建日期：2008-07-22
	 * @author：codeslave
     * 修改日期: 2012-02-07
     * 由于本框架已经支持排序功能，所以暂时屏蔽排序
     * 修改日期: 2012-02-21
1.去除in,not in,is.运算符
2.changeCondition
like去除高级选择如%,_等
objSignPanel.innerHTML = getLikeHtml(id);
///objBContent.value = "%" + objBContent.value + "%"; // 两边模糊
这个转到方言oracle.js实现
3.changeField
//默认开启动等于
    objCondition.value = "=";
 //日期 默认开启
objCondition.value = "between";
4.去掉左括号，右号,连接符去or剩and.
// 初始化条件相连的运算符
function initJoin(id)
function initLParenthesisPanel(id)
function initRParenthesisPanel(id)
function initHighQuery()
	 *********************************************/

// 配置
function HQConfig()
{
	this.dialect = "oracle"; // 方言
	this.basePath = "/Lib/highquery/"; // 基础路径(如果目录改变这里要相应改变)
	this.version = "0.5.3.2"; // 版本
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

$import(HQConfig.basePath + "browser.js");
$import(HQConfig.basePath + "calendar.js");
$import(HQConfig.basePath + "check.js");
$import(HQConfig.basePath + HQConfig.dialect + ".js");

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
function Field(name, alias, type, length, dictCode, event)
{
	this.name = name; // 字段名
	this.alias = alias; // 别名
	this.type = type; // 类型
	this.length = length; // 长度
	this.dictCode = dictCode; // 业务字典代码
	this.event = event; // 事件(主要用于选择外键表数据)
}

// 存放业务字典实体的容器
function DictList()
{
	this.dicts = [];
}

// 添加实体方法
DictList.prototype.add = function(dict)
{
	this.dicts[this.dicts.length] = dict;
};

var DictList = new DictList();

// 业务字典实体
function Dict(code, name, text, value)
{
	this.code = code; // 代码
	this.name = name; // 名称
	this.text = text; // 文本
	this.value = value; // 值
}

// 保存当前操作对象的一些信息(用于业务字典)
function CurrModel(contentId, hibContentId, flag)
{
	this.contentId = contentId; // 内容文本控件的id
	this.hibContentId = hibContentId; // 内容文本控件的id(隐藏的)
	this.flag = flag; // 标记
}

var CurrModel = new CurrModel("", "", "");

// 取得一般查询条件的html文件(日期)
function getDateHtml(contentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDate('" + contentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + contentId + "') value='清除'>";
	return strHtml;
}

// 取得in或not in查询条件的html文件(日期)
function getDateInHtml(contentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDateIn('" + contentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + contentId + "') value='清除'>";
	return strHtml;
}

// 取得一般查询条件的html文件(日期时间)
function getDateTimeHtml(contentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDateTime('" + contentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + contentId + "') value='清除'>";
	return strHtml;
}

// 取得in或not in查询条件的html文件(日期时间)
function getDateTimeInHtml(contentId)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=selDateTimeIn('" + contentId + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=clearContent('" + contentId + "') value='清除'>";
	return strHtml;
}

// 取得like查询条件的html文本
function getLikeHtml(id) 
{
	var strHtml = "<select id='darkSign_" + id + "'><option value='0'>%</option><option value='1'>_</option></select>";
	strHtml += "&nbsp;<span id='darkB_" + id + "' onClick=addDarkSign('" + id + "','b') style='font-weight: bold;cursor: pointer'>←→</span>" + 
		"&nbsp;<span id='darkL_" + id + "' onClick=addDarkSign('" + id + "','l') style='font-weight: bold;cursor: pointer'>←</span>" + 
		"&nbsp;<span id='darkR_" + id + "' onClick=addDarkSign('" + id + "','r') style='font-weight: bold;cursor: pointer'>→</span>";
	return strHtml;
}

// 取得is查询条件的html文本
function getIsHtml(id) 
{
	var strHtml = "<span id='null_" + id + "' onClick=\"document.getElementById('bContent_" + id + "').value='null'\"" + 
		" style='font-weight: bold;cursor: pointer'>○</span>" + 
		"&nbsp;<span id='notNull_" + id + "' onClick=\"document.getElementById('bContent_" + id + "').value='not null'\"" + 
		" style='font-weight: bold;cursor: pointer'>●</span>";
	return strHtml;
}

// 取得带字典查询条件的html文本
function getDictHtml(buttonId, contentId, hibContentId, dictCode)
{
	var strHtml = "<input id='" + buttonId + "' type='button' class='HQ_BUTTON' onClick=showDictPanel('" + buttonId + "','" + contentId + "','" + hibContentId + "','" + dictCode + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=\"clearContent('" + contentId + "');clearContent('" + hibContentId + "');\"" + 
		"value='清除'>";
	return strHtml;
}

// 取得带字典查询条件的html文本(用于in或not in查询)
function getDictInHtml(buttonId, contentId, hibContentId, dictCode) 
{
	var strHtml = "<input id='" + buttonId + "' type='button' class='HQ_BUTTON' onClick=showDictPanelIn('" + buttonId + "','" + contentId + "','" + hibContentId + "','" + dictCode + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=\"clearContent('" + contentId + "');clearContent('" + hibContentId + "');\"" + 
		"value='清除'>";
	return strHtml;
}

// 取得带事件查询条件的html文本
function getEventHtml(contentId, hibContentId, event)
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=executeEvent('" + contentId + "','" + hibContentId + "','" + event + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=\"clearContent('" + contentId + "');clearContent('" + hibContentId + "');\"" + 
		"value='清除'>";
	return strHtml;
}

// 取得带事件查询条件的html文本(用于in或not in查询)
function getEventInHtml(contentId, hibContentId, event) 
{
	var strHtml = "<input type='button' class='HQ_BUTTON' onClick=executeEventIn('" + contentId + "','" + hibContentId + "','" + event + "') value='选择'>";
	strHtml += "<input type='button' class='HQ_BUTTON' onClick=\"clearContent('" + contentId + "');clearContent('" + hibContentId + "');\"" + 
		"value='清除'>";
	return strHtml;
}

// 初始化字段列表
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

// 初始化条件相连的运算符
function initJoin(id)
{
    //var strJoin = "<select id='join_" + id + "'><option value='and'>AND</option><option value='or'>OR</option></select>";
    var strJoin = "<select id='join_" + id + "'><option value='and' selected>AND</option></select>";
	return strJoin;
}

// 初始化条件运算符
function initCondition(id)
{
	var strCondition = "<select onChange='changeCondition()' id='condition_" + id + "'>";
	if (id != 0) // 第一个条件必须查询
		strCondition += "<option value=''>不查询</option>";
	strCondition +="<option value='like'>模糊查询</option>" + 
        "<option value='='>等于</option><option value='<'>小于</option>" +
		"<option value='<='>小于等于</option><option value='>'>大于</option>" +
		"<option value='>='>大于等于</option><option value='<>'>不等于</option>" +
		//"<option value='in'>IN</option><option value='not_in'>NOT IN</option><option value='is'>IS</option>" + 
		"<option value='between'>之间</option></select>";
    strCondition += "<span id='signPanel_" + id + "'></span>";  // 存放查询符号，主要用于like和is查询
	return strCondition;
}

// 初始化条件内容
function initContent(id)
{
	var strContent = "<input class='HQ_TEXT' id='bContent_" + id + "' maxLength=" + FieldList.fields[FieldList.fields.length - 1].length + ">";
	strContent += "<input type='hidden' class='HQ_TEXT' id='hibBContent_" + id + "'>";
	strContent += "<span id='bChoicePanel_" + id + "'></span>"; // 存放第一个文本控件的"选择"和"清除"按钮
	strContent += "<span id='eContentPanel_" + id + "'></span>"; // 存放第二个文本控件、以及eChoicePanel面板和他内部的"选择"和"清除"按钮
	return strContent;
}

// 初始化左括号面板
function initLParenthesisPanel(id)
{
	//var strPanel = "<span id='lAdd_" + id + "' class='HQ_ADD_P' onClick=addParenthesis('" + id + "','l')>+</span>";
	//strPanel += "&nbsp;<span id='lRemove_" + id + "' class='HQ_REMOVE_P' onClick=removeParenthesis('" + id + "','l')>-</span>";
    //strPanel += "&nbsp;<span id='lParenthesisPanel_" + id + "' class='HQ_P'></span>";
    var strPanel = "&nbsp;<span id='lParenthesisPanel_" + id + "' class='HQ_P'></span>";
	return strPanel;
}

// 初始化右括号面板
function initRParenthesisPanel(id)
{
	//var strPanel = "<span id='rAdd_" + id + "' class='HQ_ADD_P' onClick=addParenthesis('" + id + "','r')>+</span>";
	//strPanel += "&nbsp;<span id='rRemove_" + id + "' class='HQ_REMOVE_P' onClick=removeParenthesis('" + id + "','r')>-</span>";
    //strPanel += "&nbsp;<span id='rParenthesisPanel_" + id + "' class='HQ_P'></span>";
    var strPanel = "&nbsp;<span id='rParenthesisPanel_" + id + "' class='HQ_P'></span>";
	return strPanel;
}

// 初始化排序
function initSort(id)
{
	var strSort = "<select id='sort_" + id + "'>" +
		"<option value=''>不排序</option><option value='asc'>升序</option><option value='desc'>降序</option>" +
		"</select>";
	return strSort;
}

// 初始化业务字典面板
function initDictPanel(dictCode)
{
	var dicts = DictList.dicts;
	var strDict = "<table id='dictChoice' cellspacing='0' class='HQ_DICT_TABLE'>";
	//strDict += "<th class='HQ_DICT_TH'>数值</th>";//update by cpe 20120209
	strDict += "<th class='HQ_DICT_TH'  align='center'>选项</th>";
	for(var i = 0; i < dicts.length; i++)
	{
		if(dicts[i].code == dictCode)
		{
			strDict += "<tr class='HQ_DICT_TR' onClick=\"backfillDict('" + dicts[i].value + "','" + dicts[i].text + "')\"" +
				" onMouseMove=\"this.className='HQ_DICT_MOUSE_OVER'\" onMouseOut=\"this.className='HQ_DICT_MOUSE_OUT'\">" +
			//"<td class='HQ_TD'>" + dicts[i].value + "</td>" +  //update by cpe 20120209
				"<td class='HQ_TD' width='130px' align='center'>" + dicts[i].text + "</td></tr>";
		}
	}
strDict += "<tr><td colspan='2' style='background:#A0CFFE;text-align:center;'><span style='cursor: pointer;width:130px;font-weight:bold' onClick='closeDictPanel()'>关闭</span></td></tr>";
	strDict += "</table>";
	return strDict;
}

// 初始化控件
function initHighQuery()
{
	document.write("<div id='highQueryPanel'></div>");
	var strHtml = "<div id='dictPanel' style='display:none; position:absolute'></div>";
	strHtml += "<table id='highQuery' class='HQ_TABLE'>" + 
		"<th class='HQ_TH'>连接</th>" +
	//"<th class='HQ_TH'>左括号</th>" +
    		"<th class='HQ_TH'></th>" +
		"<th class='HQ_TH'>字段</th>" +
		"<th class='HQ_TH'>运算符</th>" +
		"<th class='HQ_TH'>内容</th>" +
	//"<th class='HQ_TH'>右括号</th>" +
    		"<th class='HQ_TH'></th>" +
		//"<th class='HQ_TH'>排序</th>" +
		"<th class='HQ_TH'>操作</th>" +
		"</table>";

	var objHighQueryPanel = document.getElementById("highQueryPanel");
	if(objHighQueryPanel != null)
	{
		objHighQueryPanel.innerHTML = strHtml;
		// 初始化第一行
		initFristRow();
	} 
	else
		$.ligerDialog.alert("没有发现highQueryPanel！");
}

// 初始化第一行
function initFristRow()
{
	highQuery.insertRow(highQuery.rows.length);
	var newRow = highQuery.rows[highQuery.rows.length - 1];
	
	newRow.id = "row_" + autoId;

	newRow.insertCell(0);
	newRow.cells[0].className = "HQ_TD";
	newRow.cells[0].innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

	newRow.insertCell(1);
	newRow.cells[1].className = "HQ_TD";
	newRow.cells[1].width = 60;
	newRow.cells[1].innerHTML = initLParenthesisPanel(autoId);
	
	newRow.insertCell(2);
	newRow.cells[2].className = "HQ_TD";
	newRow.cells[2].innerHTML = initField(autoId);
	
	newRow.insertCell(3);
	newRow.cells[3].className = "HQ_TD";
	newRow.cells[3].innerHTML = initCondition(autoId);
	
	newRow.insertCell(4);
	newRow.cells[4].className = "HQ_TD";
	newRow.cells[4].innerHTML = initContent(autoId);

	newRow.insertCell(5);
	newRow.cells[5].className = "HQ_TD";
	newRow.cells[5].width = 60;
	newRow.cells[5].innerHTML = initRParenthesisPanel(autoId);

	//newRow.insertCell(6);
	//newRow.cells[6].className = "HQ_TD";
	//newRow.cells[6].innerHTML = initSort(autoId);

		newRow.insertCell(6);
		newRow.cells[6].className = "HQ_TD";
		newRow.cells[6].innerHTML = "<input type='button' class='HQ_BUTTON' value='增加' onClick='addRow()'>";
		
    
//	newRow.insertCell(7);
//	newRow.cells[7].className = "HQ_TD";
//	newRow.cells[7].innerHTML = "<input type='button' class='HQ_BUTTON' value='增加' onClick='addRow()'>";
//	
	var objField = document.getElementById("field_" + autoId); // 取得当前的字段选择控件对象
	if(objField.options.length > 0)
		objField.options.selectedIndex = objField.options.length - 1; // 选中字段列表的最后一个"请选择一个字段"

	autoId += 1; // autoId加1
}

/******************************以下为条件增,删,移部分******************************/

// 新添加一行
function addRow()
{
	highQuery.insertRow(highQuery.rows.length);
	var newRow = highQuery.rows[highQuery.rows.length - 1];
	newRow.id = "row_" + autoId;
	
	newRow.insertCell(0);
	newRow.cells[0].className = "HQ_TD";
	newRow.cells[0].innerHTML = initJoin(autoId);
	
	newRow.insertCell(1);
	newRow.cells[1].className = "HQ_TD";
	newRow.cells[1].width = 60;
	newRow.cells[1].innerHTML = initLParenthesisPanel(autoId);

	newRow.insertCell(2);
	newRow.cells[2].className = "HQ_TD";
	newRow.cells[2].innerHTML = initField(autoId);
	
	newRow.insertCell(3);
	newRow.cells[3].className = "HQ_TD";
	newRow.cells[3].innerHTML = initCondition(autoId);
	
	newRow.insertCell(4);
	newRow.cells[4].className = "HQ_TD";
	newRow.cells[4].innerHTML = initContent(autoId);
	
	newRow.insertCell(5);
	newRow.cells[5].className = "HQ_TD";
	newRow.cells[5].width = 60;
	newRow.cells[5].innerHTML = initRParenthesisPanel(autoId);

//	newRow.insertCell(6);
//	newRow.cells[6].className = "HQ_TD";
//	newRow.cells[6].innerHTML = initSort(autoId);

		newRow.insertCell(6);
		newRow.cells[6].className = "HQ_TD";
		newRow.cells[6].innerHTML = "<input type='button' class='HQ_BUTTON' value='删除' onClick=removeRow('" + newRow.id + "')>" + 
			"<span id='up_" + autoId + "' class='HQ_UP' onClick=goUp('" + newRow.id + "')>↑</span>" + 
			"<span id='down_" + autoId + "' class='HQ_DOWN' onClick=goDown('" + newRow.id + "')>↓</span>";
		
    
//	newRow.insertCell(7);
//	newRow.cells[7].className = "HQ_TD";
//	newRow.cells[7].innerHTML = "<input type='button' class='HQ_BUTTON' value='删除' onClick=removeRow('" + newRow.id + "')>" + 
//		"<span id='up_" + autoId + "' class='HQ_UP' onClick=goUp('" + newRow.id + "')>↑</span>" + 
//		"<span id='down_" + autoId + "' class='HQ_DOWN' onClick=goDown('" + newRow.id + "')>↓</span>";
//	
	var objField = document.getElementById("field_" + autoId); // 取得当前的字段选择控件对象
	if(objField.options.length > 0)
		objField.options.selectedIndex = objField.options.length - 1; // 选中字段列表的最后一个"请选择一个字段"

	autoId += 1; // autoId加1
}

// 删除行
function removeRow(rowId)
{
	var trRow = document.getElementById(rowId);
	var table = trRow.parentNode;
	table.removeChild(trRow);
}

// 上移
function goUp(rowId)
{
	var trRow = document.getElementById(rowId); // 取得行对象
	var cur = trRow.rowIndex;
	var table = trRow.parentNode;
	if (cur > 2)
	{
		swapNode(table.rows[cur], table.rows[cur - 1]);
	}
}

// 下移
function goDown(rowId)
{
	var trRow = document.getElementById(rowId); // 取得行对象
	var cur = trRow.rowIndex;
	var table = trRow.parentNode;
	if (cur < table.rows.length - 1)
	{
		swapNode(table.rows[cur], table.rows[cur + 1]);
	}
}

// 生成条件查询语句
function interpreter()
{
	var arrReturn = new Array();
	var strCondition = "";
	var strAliasCondition = "";
	var strSort = "";
	var strAliasSort = "";
	var isNoProblem = true;
	
	// 语法检测
	if(!checkExpression())
	{
		isNoProblem = false;
		arrReturn[0] = "";
		arrReturn[1] = "";
		arrReturn[2] = "";
		arrReturn[3] = "";
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
		//var objSort = document.getElementById("sort_" + id); // 取得排序选择控件对象
		//var objSort = ""; // 因暂时不需要屏蔽
		var isHasDict = FieldList.fields[objField.selectedIndex].dictCode == ""?false:true; // 是否有字典
		var isHasEvent = FieldList.fields[objField.selectedIndex].event == ""?false:true; // 是否有事件
		var strType = FieldList.fields[objField.selectedIndex].type; // 内容类别
		var strAlias = FieldList.fields[objField.selectedIndex].alias; // 字段别名
		
		/*******生成条件部分*******/
		
		// 如果不是第一个条件并且运算符不为空就要加上连接条件符号(AND,OR)
		if(i > 1 && objCondition.value != "") 
		{
			var objJoin = document.getElementById("join_" + id); // 条件相连的运算符
			strCondition += " " + objJoin.value + " ";
			strAliasCondition +=  " " + objJoin.options[objJoin.selectedIndex].text + " ";
		}

		// 加上左括号
		strCondition += objLParenthesisPanel.innerText;
		strAliasCondition += objLParenthesisPanel.innerText;

		if (objCondition.value != "") // 运算符不为空(处理运算符为不查询)
		{
			strCondition += objField.value;
			strAliasCondition += strAlias;
		}

		switch(objCondition.value)
		{
			case "": // 不查询时不做任何事情
				break;
			case "like": // 查询条件为like时
				if(checkDataLike(objBContent, strAlias, emendationType(strType))) 
				{
					strCondition += likeCondition(objBContent, strType, objCondition.value);
					strAliasCondition += likeCondition(objBContent, strType, objCondition.options[objCondition.selectedIndex].text);
				}
				else
					isNoProblem = false;
				break;
			case "in": // 查询条件为in时
				if(checkDataIn(((isHasDict || isHasEvent)?objHibBContent:objBContent), strAlias, emendationType(strType)))
				{
					strCondition += inCondition(((isHasDict || isHasEvent)?objHibBContent:objBContent), strType, objCondition.value);
					strAliasCondition += inCondition(objBContent, strType, objCondition.options[objCondition.selectedIndex].text);
				}
				else
					isNoProblem = false;
				break;
			case "not_in": // 查询条件为not in时
				if(checkDataIn(((isHasDict || isHasEvent)?objHibBContent:objBContent), strAlias, emendationType(strType)))
				{
					strCondition += inCondition(((isHasDict || isHasEvent)?objHibBContent:objBContent), strType, objCondition.value);
					strAliasCondition += inCondition(objBContent, strType, objCondition.options[objCondition.selectedIndex].text);
				}
				else
					isNoProblem = false;
				break;
			case "between": // 查询条件为between时
				var objEContent = document.getElementById("eContent_" + id); // between查询时才会出现的第二个条件内容对象
				var objHibEContent = document.getElementById("hibEContent_" + id); // between查询时才会出现的第二个条件内容对象(隐藏的)
				if(checkDataBetween(((isHasDict || isHasEvent)?new Array(objHibBContent, objHibEContent):new Array(objBContent, objEContent)), strAlias, emendationType(strType)))
				{
					strCondition += betweenCondition(((isHasDict || isHasEvent)?objHibBContent:objBContent), ((isHasDict || isHasEvent)?objHibEContent:objEContent), strType, objCondition.value);
					strAliasCondition += betweenCondition(objBContent, objEContent, strType, objCondition.options[objCondition.selectedIndex].text);
				}
				else
					isNoProblem = false;
				break;
			case "is": // 查询条件为is时
				if(checkDataIs(objBContent, strAlias, "checknull"))
				{
					strCondition += isCondition(objBContent, strType, objCondition.value);
					strAliasCondition += isCondition(objBContent, strType, objCondition.options[objCondition.selectedIndex].text);
				}
				else
					isNoProblem = false;
				break;
			default: // 查询条件为其他时
				if(checkDataNormal(((isHasDict || isHasEvent)?objHibBContent:objBContent), strAlias, emendationType(strType)))
				{
					strCondition += " " + objCondition.value + normalCondition(((isHasDict || isHasEvent)?objHibBContent:objBContent), strType);
					strAliasCondition +=  " " + objCondition.options[objCondition.selectedIndex].text + normalCondition(objBContent, strType);
				}
				else
					isNoProblem = false;
				break;
		}
		if(isNoProblem != true)
		{
			arrReturn[0] = "";
			arrReturn[1] = "";
			arrReturn[2] = "";
			arrReturn[3] = "";
			return arrReturn;
		}

		// 加上右括号
		strCondition += objRParenthesisPanel.innerText;
		strAliasCondition += objRParenthesisPanel.innerText;

		/*******生成排序部分*******/

//		if(objSort.value != "")
//		{
//			strSort += objField.value + " " + objSort.value + ",";
//			strAliasSort += strAlias + " " + objSort.options[objSort.selectedIndex].text + ",";
//		}
	}
	arrReturn[0] = strAliasCondition;
	arrReturn[1] = strCondition;
	arrReturn[2] = (strAliasSort == ""?"":strAliasSort.substring(0, strAliasSort.length - 1));
	arrReturn[3] = (strSort == ""?"":strSort.substring(0, strSort.length - 1));
	return arrReturn;
}

/******************************以下为字段类型验测函数******************************/

// 根据类型返回相应校正的项目
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
	var strAllParenthesis = "";

	for(var i = 1; i < highQuery.rows.length; i++)
	{
		var id = highQuery.rows[i].id.split("_")[1];
		var objField = document.getElementById("field_" + id); // 取得字段选择控件对象
		var objCondition = document.getElementById("condition_" + id); // 取得条件运算符选择控件对象
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得右括号面板对象

		/** update by cpe**/
		if(objField.value == "") // 字段是否为空
		{
			//$.ligerDialog.alert("所选的条件中存在不明确的字段名，请先选择一个有效字段名！");
			objField.focus();
			return false;
		}
        
		if(objCondition.value == "" && (objLParenthesisPanel.innerText != "" || objRParenthesisPanel.innerText != ""))
		{
			$.ligerDialog.alert("不查询的字段两边不能加括号，请将不合法的括号删除！");
			objField.focus();
			return false;
		}

		strLParenthesis += objLParenthesisPanel.innerText;
		strRParenthesis += objRParenthesisPanel.innerText;
		strAllParenthesis += objLParenthesisPanel.innerText + objRParenthesisPanel.innerText;
	}
	
	// "("和")"数量是否相等
	if(strLParenthesis.length != strRParenthesis.length)
	{
		$.ligerDialog.alert("\"(\"与\")\"的数量不对应，请检查！");
		return false;
	}
	
	//  "("和")"的位置是否正确设置
	var arrParenthesis = strAllParenthesis.split("");
	var lCount = 0;
	var rCount = 0;
	for(var i = 0; i < arrParenthesis.length; i++)
	{
		if(arrParenthesis[i] == "(")
		{
			lCount += 1;
		} 
		else if(arrParenthesis[i] == ")") 
		{
			rCount += 1;
		}
		if(lCount < rCount)
		{
			$.ligerDialog.alert("\"(\"与\")\"存在错误设置，请检查！");
			return false;
		}
	}

	return true;
}

// 数据校正(like查询专用)
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

// 数据校正(in或not in查询专用)
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

// 数据校正(between查询专用)
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

// 数据校正(is查询时用)
function checkDataIs(objTemp, strText, action)
{
	var strTemp = objTemp.value;
	return checkS(objTemp, strTemp, strText, action);
}

// 数据校正(一般查询时用)
function checkDataNormal(objTemp, strText, action)
{
	var strTemp = objTemp.value;
	if(strTemp == "")
		return true;
	return checkS(objTemp, strTemp, strText, action);
}

/******************************以下为触发功能******************************/

// 改变所选字段时，相应改变界面
function changeField()
{
	closeDictPanel(); // 关闭字典界面
	var evt = searchEvent();
	var objField = evt.target?evt.target:evt.srcElement; // 取得字段选择控件
	var id = objField.id.split("_")[1];
	var trRow = document.getElementById("row_" + id); // 取得行对象
	var objCondition = document.getElementById("condition_" + id); // 取得条件运算符选择控件对象
	var objSignPanel = document.getElementById("signPanel_" + id); // 取得查询符号面板对象
	var objBChoicePanel = document.getElementById("bChoicePanel_" + id); // 取得第一个内容文本"选择"面板对象
	var objEContentPanel = document.getElementById("eContentPanel_" + id); // 取得第二个内容文本面板对象
	var objBContent = document.getElementById("bContent_" + id); // 取得第一个条件内容文件控件对象
	var objHibBContent = document.getElementById("hibBContent_" + id); // 取得第一个条件内容文件控件对象(隐藏的)
	var isDate = FieldList.fields[objField.selectedIndex].type == "date"?true:false;
	var isDateTime = FieldList.fields[objField.selectedIndex].type == "datetime"?true:false;
	var isHasDict = FieldList.fields[objField.selectedIndex].dictCode == ""?false:true; // 是否有字典
	var isHasEvent = FieldList.fields[objField.selectedIndex].event == ""?false:true; // 是否有事件
	var dictCode = !isHasDict?"":FieldList.fields[objField.selectedIndex].dictCode; // 取得字典Code
	var event = !isHasEvent?"":FieldList.fields[objField.selectedIndex].event; // 取得事件
	objBContent.value = "";
	objHibBContent.value = "";
	objBContent.maxLength = FieldList.fields[objField.selectedIndex].length; // 根据字段长度改变文本控件的长度
    
//    if(isDate || isDateTime) {
//        //日期 默认开启
//        objCondition.value = "between";
//        changeCondition();
//    }else {
//        //默认开启动等于
//        objCondition.value = "=";
//        changeCondition();
//    }
    
    // 如果对应的字段类型是date、datetime、有字典，或者是有事件存在时,删除like查询条件
	if(isDate || isDateTime || isHasDict || isHasEvent) 
	{
		var conditionTemp = objCondition.value;
		if(objCondition.options[objCondition.length-1].value == "like")
		{
		    objCondition.remove(objCondition.options[objCondition.length - 1].index);
			objSignPanel.innerHTML = ""; // 清除查询符号面板内容
		}
		if(conditionTemp != "like")
		    objCondition.value = conditionTemp;
	}
	else 
	{
		if(objCondition.options[objCondition.length-1].value != "like")
		{
			//objCondition.options.add(new Option('LIKE', 'like'));
		}
	}
	
	// 内容控件的只读属性处理
	if(isDate || isDateTime || objCondition.value == "is" || isHasDict || isHasEvent || objCondition.value == "")
		objBContent.setAttribute("readOnly", true);
	else 
		objBContent.removeAttribute("readOnly");
	
	switch(objCondition.value)
	{
		case "": // 不查询时
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
		case "like": // 查询条件为like时
			//objSignPanel.innerHTML = getLikeHtml(id);
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			//增加模糊条件
			//objBContent.value = "%" + objBContent.value + "%";
		    break;
		case "in": // 查询条件为in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasDict) // 如果有字典存在
				objBChoicePanel.innerHTML = getDictInHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventInHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
		case "not_in": // 查询条件为not in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasDict) // 如果有字典存在
				objBChoicePanel.innerHTML = getDictInHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
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
			else if(isHasDict) // 如果有字典存在
			{
				objEContent.setAttribute("readOnly", "true");
				objBChoicePanel.innerHTML = getDictHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
				objEChoicePanel.innerHTML = getDictHtml("eButton_" + id, "eContent_" + id, "hibEContent_" + id, dictCode);
			}
			else if(isHasEvent) // 如果有事件存在
			{
				objEContent.setAttribute("readOnly", "true");
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
				objEChoicePanel.innerHTML = getEventHtml("eContent_" + id, "hibEContent_" + id, event);
			}
			else
			{
				objEContent.removeAttribute("readOnly");
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
			else if(isHasDict) // 如果有字典存在
				objBChoicePanel.innerHTML = getDictHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			break;
	}
}

// 改变条件运算符时，相应改变界面
function changeCondition()
{
	closeDictPanel(); // 关闭字典界面
	var evt = searchEvent();
	var objCondition = evt.target?evt.target:evt.srcElement; // 取得条件运算符选择控件对象
	var id = objCondition.id.split("_")[1];
	var trRow = document.getElementById("row_" + id); // 取得行对象
	var objField = document.getElementById("field_" + id); // 取得字段选择控件对象
	var objSignPanel = document.getElementById("signPanel_" + id); // 取得查询符号面板对象
	var objBChoicePanel = document.getElementById("bChoicePanel_" + id); // 取得第一个内容文本"选择"面板对象
	var objEContentPanel = document.getElementById("eContentPanel_" + id); // 取得第二个内容文本面板对象
	var objBContent = document.getElementById("bContent_" + id); // 取得第一个条件内容文件控件对象
	var objHibBContent = document.getElementById("hibBContent_" + id); // 取得第一个条件内容文件控件对象(隐藏的)
	var isDate = FieldList.fields[objField.selectedIndex].type == "date"?true:false;
	var isDateTime = FieldList.fields[objField.selectedIndex].type == "datetime"?true:false;
	var isHasDict = FieldList.fields[objField.selectedIndex].dictCode == ""?false:true; // 是否有字典
	var isHasEvent = FieldList.fields[objField.selectedIndex].event == ""?false:true; // 是否有事件
	var dictCode = !isHasDict?"":FieldList.fields[objField.selectedIndex].dictCode; // 取得字典Code
	var event = !isHasEvent?"":FieldList.fields[objField.selectedIndex].event; // 取得事件
	objBContent.maxLength = FieldList.fields[objField.selectedIndex].length; // 根据字段长度改变文本控件的长度
	
	// 如果文本内容长度大于最大长度(通常出现在in查询时)就清除内容或者条件运算符为"is"
	if(objBContent.value.length > objBContent.maxLength || objCondition.value == "is" || objCondition.value == "")
	{
		objBContent.value = "";
		objHibBContent.value = "";
	}
	
	// 内容控件的只读属性处理
	if(isDate || isDateTime || objCondition.value == "is" || isHasDict || isHasEvent || objCondition.value == "")
		objBContent.setAttribute("readOnly", "true");
	else 
		objBContent.removeAttribute("readOnly");

	switch(objCondition.value)
	{
		case "": // 不查询时
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;

case "like": // 查询条件为like时

    //objSignPanel.innerHTML = getLikeHtml(id);
    objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
    //增加模糊条件
    //objBContent.value = "%" + objBContent.value + "%";
    break;
		case "in": // 查询条件为in时
			objBContent.maxLength = '2147483647'; // 设为最大值
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateInHtml("bContent_" + id):getDateTimeInHtml("bContent_" + id);
			else if(isHasDict) // 如果有字典存在
				objBChoicePanel.innerHTML = getDictInHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
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
			else if(isHasDict) // 如果有字典存在
				objBChoicePanel.innerHTML = getDictInHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventInHtml("bContent_" + id, "hibBContent_" + id, event);
			else
				objBChoicePanel.innerHTML = "";
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;

case "between": // 查询条件为between时

    var maxLen = objBContent.maxLength;

    if (isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
    {

        objBChoicePanel.innerHTML = isDate ? getDateHtml("bContent_" + id) : getDateTimeHtml("bContent_" + id);

        objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' readOnly maxLength='" + maxLen + "'>" +
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" +
					"<span id='eChoicePanel_" + id + "'>" + (isDate ? getDateHtml("eContent_" + id) : getDateTimeHtml("eContent_" + id)) + "</span>";

    }

    else if (isHasDict) // 如果有字典存在
    {

        objBChoicePanel.innerHTML = getDictHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);

        objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' readOnly maxLength='" + maxLen + "'>" +
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" +
					"<span id='eChoicePanel_" + id + "'>" + getDictHtml("eButton_" + id, "eContent_" + id, "hibEContent_" + id, dictCode) + "</span>";

    }

    else if (isHasEvent) // 如果有事件存在
    {

        objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);

        objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' readOnly maxLength='" + maxLen + "'>" +
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" +
					"<span id='eChoicePanel_" + id + "'>" + getEventHtml("eContent_" + id, "hibEContent_" + id, event) + "</span>";

    }

    else {

        objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)

        objEContentPanel.innerHTML = "--<input class='HQ_TEXT' id='eContent_" + id + "' maxLength='" + maxLen + "'>" +
					"<input type='hidden' class='HQ_TEXT' id='hibEContent_" + id + "'>" +
					"<span id='eChoicePanel_" + id + "'></span>";

    }

    objSignPanel.innerHTML = ""; // 清除查询符号面板内容(不论是否存在)


    break;
		case "is": // 查询条件为is时
			objSignPanel.innerHTML = getIsHtml(id);
			objBChoicePanel.innerHTML = ""; // 清除(date、datetime、带事件字段)查询时留下的第一个内容文本"选择"面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
		default: // 查询条件为其他时
			if(isDate || isDateTime) // 如果对应的字段类型是date或datetime时,给用户选择日期
				objBChoicePanel.innerHTML = isDate?getDateHtml("bContent_" + id):getDateTimeHtml("bContent_" + id);
			else if(isHasDict) // 如果有字典存在
				objBChoicePanel.innerHTML = getDictHtml("bButton_" + id, "bContent_" + id, "hibBContent_" + id, dictCode);
			else if(isHasEvent) // 如果有事件存在
				objBChoicePanel.innerHTML = getEventHtml("bContent_" + id, "hibBContent_" + id, event);
			objSignPanel.innerHTML= ""; // 清除查询符号面板内容(不论是否存在)
			objEContentPanel.innerHTML = ""; // 清除between查询的第二个文本面板内容(不论是否存在)
			break;
	}
}

/******************************以下为应用函数******************************/

// 添加模糊查询符号
function addDarkSign(id, strFlag)
{
	var objBContent = document.getElementById("bContent_" + id); // 取得当前的第一个条件内容文件控件对象
	var objDarkSign = document.getElementById("darkSign_" + id); // 取得当前的标记选择控件对象('%','_')

	if(objBContent.value != "")
	{
		if(objDarkSign.value == "0")
		{
			if(strFlag == 'b')
			{
				objBContent.value = objBContent.value.trimPS();	// 清除内容两边的"%"
				objBContent.value = "%" + objBContent.value + "%"; // 两边模糊
			}
			else if(strFlag == 'l')
			{
				objBContent.value = objBContent.value.trimPS(); // 清除内容两边的"%"
				objBContent.value = "%" + objBContent.value; // 左边模糊
			}
			else if(strFlag == 'r')
			{
				objBContent.value = objBContent.value.trimPS(); // 清除内容两边的"%"
				objBContent.value = objBContent.value + "%"; // 右边模糊
			}
		}
		else if(objDarkSign.value == "1")
		{
			if(strFlag == 'b')
			{
				objBContent.value = "_" + objBContent.value + "_"; // 两边模糊
			}
			else if(strFlag == 'l')
			{
				objBContent.value = "_" + objBContent.value; // 左边模糊
			}
			else if(strFlag == 'r')
			{
				objBContent.value = objBContent.value + "_"; // 右边模糊
			}
		}

	}
}

// 添加"(" 或 ")"
function addParenthesis(id, strFlag)
{
	if(strFlag == "l")
	{
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		objLParenthesisPanel.innerText = objLParenthesisPanel.innerText + "(";
	}
	else if(strFlag == 'r')
	{
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得左括号面板对象
		objRParenthesisPanel.innerText = objRParenthesisPanel.innerText + ")";
	}
}

// 删除"(" 或 ")"
function removeParenthesis(id, strFlag)
{
	if(strFlag == "l")
	{
		var objLParenthesisPanel = document.getElementById("lParenthesisPanel_" + id); // 取得左括号面板对象
		if(objLParenthesisPanel.innerText.length > 0)
			objLParenthesisPanel.innerText = objLParenthesisPanel.innerText.substring(0, objLParenthesisPanel.innerText.length - 1);
	}
	else if(strFlag == 'r')
	{
		var objRParenthesisPanel = document.getElementById("rParenthesisPanel_" + id); // 取得左括号面板对象
		objRParenthesisPanel.innerText = objRParenthesisPanel.innerText.substring(0, objRParenthesisPanel.innerText.length - 1);
	}
}

// 清除内容
function clearContent(contentId)
{
	var objContent = document.getElementById(contentId);
	objContent.value = "";
}

// 选择查询日期
function selDate(contentId)
{
	var objContent = document.getElementById(contentId);
	objContent.value = showDateForm();
}

// 选择查询日期(in或not in查询专用)
function selDateIn(contentId)
{
	var objContent = document.getElementById(contentId);
	if(objContent.value == "")
		objContent.value = showDateForm();
	else
	{
		var strTemp = showDateForm();
		objContent.value += (strTemp==""?strTemp:"," + strTemp);
	}
}

// 选择查询日期时间
function selDateTime(contentId)
{
	var objContent = document.getElementById(contentId);
	objContent.value = showDateTimeForm();
}

// 选择查询日期时间(in或not in查询专用)
function selDateTimeIn(contentId)
{
	var objContent = document.getElementById(contentId);
	if(objContent.value == "")
		objContent.value = showDateTimeForm();
	else
	{
		var strTemp = showDateTimeForm();
		objContent.value += (strTemp==""?strTemp:"," + strTemp);
	}
}

// 执行事件
function executeEvent(contentId, hibContentId, event)
{
	var arrReturn = eval(event + "();");
	if(arrReturn != null && arrReturn.length == 2)
	{
		var objContent = document.getElementById(contentId);
		var objHibContent = document.getElementById(hibContentId);
		objHibContent.value = arrReturn[0];
		objContent.value = arrReturn[1];
	}
}

// 执行事件(用于in或not in查询)
function executeEventIn(contentId, hibContentId, event)
{
	var arrReturn = eval(event + "();");
	if(arrReturn != null && arrReturn.length == 2)
	{
		var objContent = document.getElementById(contentId);
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

// 显示业务字典面板
function showDictPanel(buttonId, contentId, hibContentId, dictCode) 
{
	var button = document.getElementById(buttonId);
	var objDictPanel = document.getElementById("dictPanel");
	var buttonPos = getPosition(button);

	if(objDictPanel != null)
	{
		objDictPanel.innerHTML = initDictPanel(dictCode);
		objDictPanel.style.display = "block";
		objDictPanel.style.top = buttonPos.top + buttonPos.height;
		objDictPanel.style.left = buttonPos.left - 130;//update by cpe 2012/02/09
		CurrModel.contentId = contentId;
		CurrModel.hibContentId = hibContentId;
		CurrModel.flag = "";
	}
}

// 显示业务字典面板(用于in或not in查询)
function showDictPanelIn(buttonId, contentId, hibContentId, dictCode) 
{
	var button = document.getElementById(buttonId);
	var objDictPanel = document.getElementById("dictPanel");
	var buttonPos = getPosition(button);

	if(objDictPanel != null)
	{
		objDictPanel.innerHTML = initDictPanel(dictCode);
		objDictPanel.style.display = "block";
		objDictPanel.style.top = buttonPos.top + buttonPos.height;
		objDictPanel.style.left = buttonPos.left - 130; //update by cpe 2012/02/09
		CurrModel.contentId = contentId;
		CurrModel.hibContentId = hibContentId;
		CurrModel.flag = "in";
	}
}

// 关闭业务字典面板
function closeDictPanel()
{
	var objDictPanel = document.getElementById("dictPanel");

	if(objDictPanel != null)
	{
		objDictPanel.innerHTML = "";
		objDictPanel.style.display = "none";
		objDictPanel.style.top = 0;
		objDictPanel.style.left = 0;
		CurrModel.contentId = "";
		CurrModel.hibContentId = "";
		CurrModel.flag = "";
	}
}

// 取得元素位置
function getPosition(obj) 
{ 
	var top = 0; 
	var left = 0; 
	var width = obj.offsetWidth; 
	var height = obj.offsetHeight; 
	while (obj.offsetParent) 
	{ 
    top += obj.offsetTop; 
    left += obj.offsetLeft; 
    obj = obj.offsetParent; 
  }
  return {"top":top,"left":left,"width":width,"height":height}; 
}

// 回填业务字典数据
function backfillDict(value, text)
{
	var objContent = document.getElementById(CurrModel.contentId);
	var objHibContent = document.getElementById(CurrModel.hibContentId);

	if(CurrModel.flag == "in")
	{
		if(objContent.value == "")
		{
			objHibContent.value = value;
			objContent.value = text;
		}
		else
		{
			objHibContent.value += "," + value;
			objContent.value += "," + text;
		}
	}
	else
	{
		objHibContent.value = value;
		objContent.value = text;
	}
	closeDictPanel();
}
