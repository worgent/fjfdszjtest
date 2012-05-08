
	/********************************************
	 * 文件名称：browser.js
	 * 功能描述：处理浏览器的兼容问题
	 * 创建日期：2008-12-23
	 * @author：codeslave
	 *********************************************/

var lBrowser = {};
lBrowser.agt = navigator.userAgent.toLowerCase();
lBrowser.isW3C = document.getElementById ? true:false;
lBrowser.isIE = ((lBrowser.agt.indexOf("msie") != -1) && (lBrowser.agt.indexOf("opera") == -1) && (lBrowser.agt.indexOf("omniweb") == -1));
lBrowser.isNS6 = lBrowser.isW3C && (navigator.appName=="Netscape") ;
lBrowser.isOpera = lBrowser.agt.indexOf("opera") != -1;
lBrowser.isGecko = lBrowser.agt.indexOf("gecko") != -1;
lBrowser.ieTrueBody = function()
{
  return (document.compatMode && document.compatMode != "BackCompat")? document.documentElement : document.body;
};

// 为Firefox下的DOM对象增加innerText属性
if(lBrowser.isNS6){
	HTMLElement.prototype.__defineGetter__( "innerText", 
		function()
		{ 
			return this.textContent; 
		} 
	); 
	HTMLElement.prototype.__defineSetter__( "innerText", 
		function(sText){ 
			this.textContent=sText;
		} 
	); 
}

// 处理IE和Firefox获取event问题
function searchEvent()
{
	// IE
	if(document.all)
		return window.event;
	
	// Firefox
	func = searchEvent.caller;
	while(func != null)
	{
		var arg0 = func.arguments[0];
		if(arg0)
		{
			if(arg0.constructor == Event)
				return arg0;
		}
		func = func.caller;
	}
	return null;
}

// 交换两节点(IE和Firefox通用)
function swapNode(node1, node2)
{
	var parent = node1.parentNode; // 父节点
	var t1 = node1.nextSibling; // 两节点的相对位置
	var t2 = node2.nextSibling;
	if(t1) parent.insertBefore(node2, t1);
	else parent.appendChild(node2);
	if(t2) parent.insertBefore(node1, t2);
	else parent.appendChild(node1);
}
