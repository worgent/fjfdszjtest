
	/********************************************
	 * 文件名称：check.js
	 * 功能描述：打开日期控件
	 * 创建日期：2008-07-21
	 * @author：codeslave
	 *********************************************/

// 打开日期控件(日期)
function showDateForm(d)
{
  var arg = new Object();
  arg.str_datetime = d;
  arg.time_comp = false;
  var rtn = window.showModalDialog(HQConfig.basePath + 'calendar.htm', arg, 'dialogWidth=210px;dialogHeight=220px;status:no;scroll=no;');
  return (rtn == null ? "" : rtn);
}

// 打开日期控件(日期时间)
function showDateTimeForm(d)
{
  var arg = new Object();
  arg.str_datetime = d;
  arg.time_comp = true;
  var rtn = window.showModalDialog(HQConfig.basePath + 'calendar.htm', arg, 'dialogWidth=210px;dialogHeight=220px;status:no;scroll=no;');
  return (rtn == null ? "" : rtn);
}