function isNull(v) {
	if (v == null || typeof v == "undefined") {
		return true;
	}

	return false;
}

function nvl(v, v1) {
	return (isNull(v) ? v1 : v);
}

function getValue(valueId, parentId) {
	var o = null;
	if (parentId) {
		o = getCmp("parentId");
		if (!o || !o.findById)
			return "";
		o = o.findById(valueId);

	} else
		o = getCmp(valueId);
	if (!o || !o.getValue)
		return "";

	return o.getValue();
}

function toURL(ul) {
	if (!ul)
		return null;
	return currentPageBaseHREF + "/apricot" + (startWith(ul, "/") ? "" : "/")
			+ ul;
}

function toURL2(ul,flag) {
	if (!ul)
		return null;
	if(flag)
	{
		return currentPageBaseHREF + "/apricot" + (startWith(ul, "/") ? "" : "/") + ul;
	}
	else
	{
		return currentPageBaseHREF + (startWith(ul, "/") ? "" : "/") + ul;
	}
	
}

function startWith(v, s) {
	if (!v)
		return false;
	if (!s)
		return false;
	return v.indexOf(s) == 0;
}

function setFormValues(id, d) {
	var f=getCmp(id);
	if(f && f.getForm && f.getForm()){
	f.getForm().setValues(d);
	f["data"]=d;
	}
}

function getFormValues(id, nms) {
	var f=getCmp(id);
	if(!f) return {};
	if(!f.getForm()) return {};
	return copyProps( {}, getCmp(id).getForm().getValues(), nms);
}

function getFormData(id, nms) {
	var f=getCmp(id);
	if(!f) return {};
	return copyProps( {}, f.data, nms);
}

function removeEvents(id) {
	getCmp(id).purgeListeners();
}

function removeHandler(id) {
	var o = getCmp(id);
	if (!o)
		return;
	if (!o.setHandler)
		return;
	o.setHandler( function() {
	});
}

function getCmp(id) {
	return Ext.getCmp(id);
}

function setStoreBaseParams(id, dt) {
	if (id && dt) {
		getCmp(id).getStore().baseParams = dt;
	}
}

function setWindowFormData(id, dt) {
	getCmp(id + "_win_form").getForm().setValues(dt);
}

function arrayJoin(o) {
	var a = new Array();
	if(!o) return null;
	a = a.concat(o);
	return a;
}

function getData(girdId, names) {
	var a = Ext.apply( {}, getSelectedData(girdId));
	if (!names)
		return a;
	else
		names = arrayJoin(names);
	var b = {};
	for ( var i = 0; i < names.length; i++) {
		b[names[i]] = a[names[i]];
// alert(b[names[i]]);
	}
	a = null;
	return b;
}

function getDataArray(girdId, names) {
	var a = new Array();
	var arr = getCmp(girdId).getSelectionModel().getSelections();
	for ( var n = 0; n < arr.length; n++) {
		var oo = arr[n].data;
		if (!names) {
			a.push(Ext.apply( {}, oo));
			continue;
		} else
			names = arrayJoin(names);
		for ( var i = 0; i < names.length; i++) {
			var o = {};
// alert(oo[names[i]]);
			o[names[i]] = oo[names[i]];
			// alert(names[i]+"="+o[names[i]]);
			a.push(o);
		}
	}
	arr = null;
	return a;
}

function getDataArrayStr(girdId, names) {
	var a = new Array();
	var arr = getCmp(girdId).getSelectionModel().getSelections();
	var o = {};
	for ( var n = 0; n < arr.length; n++) {
		var oo = arr[n].data;
		if (!names) {
			var x;
			for (x in oo)
			{
				if(!o[x]){
					o[x]=oo[x];
					continue;
				}
				o[x] = o[x].concat(",").concat(oo[x]);
			}
			a.push(Ext.apply( {}, oo));
			continue;
		} else
			names = arrayJoin(names);
			
		for ( var i = 0; i < names.length; i++) {

			if(!o[names[i]]){
				o[names[i]]=oo[names[i]];
				continue;
			}
			o[names[i]] = o[names[i]].concat(",").concat(oo[names[i]]);
			// alert(names[i]+"="+o[names[i]]);
			a.push(o);
		}
	}
	arr = null;
	return o;
}

function updateGridSelectedData(id,d){
	var grid=getCmp(id);
	var sm=grid.getSelectionModel();
    
	var record=(sm && sm.getSelected)?sm.getSelected():null;
	
	if(record){
	 
		for(var n in d){
		  record.set(n,d[n]);
		  
	  }
	  record.commit();
	}
}

function existProp(id, name) {
	if (getData(id)[name])
		return true;
	return false;
}

function cLoadEvent(id, dt) {
	return function() {
		load(id, dt);
	};
}

function getSelectedData(gridId) {
	var grid = getCmp(gridId);
	if (grid == null)
		return {};
	var sc = grid.getSelectionModel();

	if (sc == null || typeof(sc)=="undefined")
		return {};
	if(typeof(sc.getSelected)=="undefined")
		return {};

	sc = sc.getSelected();
	if (sc == null || typeof(sc)=="undefined")
		return {};
	return sc.data;
}

function getSelectedRows(gridId) {
	var grid = getCmp(gridId);
	if (grid == null)
		return [];

	var sc = grid.getSelectionModel();

	if (sc == null)
		return [];

	var arr = sc.getSelections();
	if (!arr || arr.length < 1)
		return [];

	var rt = new Array();
	for (var i = 0; i < arr.length; i++) {
		rt.push(Ext.apply({}, arr[i].data));
	}
	return rt;
}



function load(id, dt) {
	if (!dt) {
		reload(id);
		return;
	}

	var st = getCmp(id).getStore();
	st.removeAll();

	st.baseParams = Ext.apply( {}, dt);
	if (!dt.limit)
		dt["limit"] = 20;
	if (!dt.start)
		dt["start"] = 0;
	st.load( {
		params :dt
	});
}

function removeAll(id) {
	var st = getCmp(id).getStore();
	if (st)
		st.removeAll();
}

function reload(id) {
	var st = getCmp(id).getStore();
	// alert(st);
	st.removeAll();
	st.load(st.lastOptions);
}

function getAllData(id, names) {
	var rows = getCmp(id).getStore().getRange();
	var dats = new Array();
	for ( var i = 0; i < rows.length; i++) {
		dats.push(copyProps( {}, rows[i].data, arrayJoin(names)));
	}

	return dats;
}

function copyProps(desc, src, props) {
	if (!props)
		return Ext.apply(desc, src);
	for ( var i = 0; i < props.length; i++) {
		var v = src[props[i]];
		if (!v || v == null || v == "")
			continue;
		desc[props[i]] = src[props[i]];
	}

	return desc;
}

function urlEncode(ov) {
	if (!ov)
		return "";
	var buf = [];
	var type = typeof ov;
	if (type == 'undefined') {
		return "";
	}

	if (Ext.isArray(ov) && ov.length) {
		for ( var i = 0, len = ov.length; i < len; i++) {
			var o = ov[i];
			for ( var k in o) {
				if (!o[k] || o[k] == "")
					continue;
				buf.push(k, "=", o[k], "&");
			}
		}
	} else {
		if (type == "object") {
			for ( var k in ov) {
				if (!ov[k] || ov[k] == "")
					continue;
				buf.push(k, "=", ov[k], "&");
			}
		}
	}

	buf.pop();
	return buf.join("");
}

function getAjaxRequest(urls, pms, cb, enableAlert) {
	if (!urls || urls == "" || urls == null)
		Ext.Msg
				.alert("系统提示",
						"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;没有指定要提交的路径!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	return {
		url :toURL(urls),
		params :urlEncode(pms),
		waitMsk :"正在向服务器提交数据，请稍侯!",
		success : function(e) {
			var action = Ext.decode(e.responseText);
			var rt = action.data;
			if (rt.errorCode == "00") {
				if (enableAlert != false)
					Ext.Msg
							.alert("提交成功",
									"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;恭喜！数据提交成功！&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				if (cb)
					cb(rt);
			} else {
				Ext.Msg.alert("提交失败", "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原因："
						+ rt.errorMessage);
			}

		},
		failure : function(e) {
			Ext.Msg.alert("连接失败", "系统连接失败，请联系管理员!");
		}
	};
}

function doGet(action, pms, cb) {
	Ext.Ajax.request( {
		url :toURL(action),
		params :urlEncode(pms),
		method :"GET",
		waitMsk :"正在向服务器获取数据，请稍侯!",
		success : function(e) {
			//alert(e.responseText);
			var d = Ext.decode(e.responseText);
			if (cb)
				cb(d);
		},
		failure : function(e) {
			if (cb)
				cb( {});
		}

	});
}

function $alt(v) {
	Ext.Msg.alert("系统提示", v);
}

function containKey(obj, key) {
	if (obj[key])
		return true;
	else
		return false;
}

function equals(map, name, value) {
	if (!map[name])
		return false;
	return map[name] == value;
}

function doPost(action, data, cb, alt) {
	Ext.Ajax.request(getAjaxRequest(action, data, cb, nvl(alt, true)));
}

function doSyncRequest(url, data, cb) {
	var conn = Ext.lib.Ajax.getConnectionObject().conn;
	conn.open("GET", toURL(url) + "?" + urlEncode(data), false);
	conn.send(null);

	while (true && cb) {
		if (conn.readyState == 4) {
			cb(Ext.decode(conn.responseText));
			break;
		}
	}
}

function buildXmlElement(o) {
	var type = typeof o;
	var el = new Array();
	if(!o) return ">";
	// 没有定义
	if (type == 'undefined') {
		el.push(' type="undefined">');
		return el.join("");
	}

	if (type == "string" || type == "boolean" || type=="number") {
		
		el.push(' type="string">');
		el.push("<![CDATA[");
		el.push(o);
		el.push("]]>");
		return el.join("");
	}

   
	if (Ext.isArray(o) && o.length) {
		el.push(' type="array">');
		for ( var i = 0, len = o.length; i < len; i++) {
			if(typeof o[i]=="string"){
				el.push("<value><![CDATA[");
				el.push(o[i]);
				el.push("]]></value>");
				continue;
			}
			el.push("<row ");
			el.push(buildXmlElement(o[i]));
			el.push("</row>");
		}
		
		
		
	} else if (type == "object") {
		el.push(' type="object">');
		for ( var k in o) {
			if (!o[k])
				continue;
			el.push('<element name="');
			el.push(k);
			el.push('"');
			if(k=='food_name')
			{
				el.push(buildXmlElement(encodeURI(o[k])));
// alert(encodeURI(o[k]));
			}
			else
			{
				el.push(buildXmlElement(o[k]));
			}
			el.push("</element>");
		}
		
		
	}

	return el.join("");
}

function buildXml(o) {
	var xml = new Array();
	xml.push('<?xml version="1.0" encoding="GBK"?>');
	xml.push("<request");
	xml.push(buildXmlElement(o));
	xml.push("</request>");

	var xmlDocument;
	if (Ext.isIE) {
		xmlDocument = new ActiveXObject("Msxml2.FreeThreadedDOMDocument")
		xmlDocument.async = false;
		xmlDocument.resolveExternals = false;
		xmlDocument.loadXML(xml.join(""));
	} else {
		xmlDocument = (new DOMParser()).parseFromString(xml.join(""),
				"text/xml");
	}

	xml = null;
	

	return xmlDocument;
}

function doPostByXML(action, data, cb) {
	Ext.Ajax.request( {
		headers: {'Content-Type': 'text/xml;charset=GBK'}, 
		method :"POST",
		url :toURL(action),
		xmlData :buildXml(data),
		waitMsk :"正在向服务器获取数据，请稍侯!",
		success : function(e) {
			var d = Ext.decode(e.responseText);
			if (cb)
				cb(d);
		},
		failure : function(e) {
			if (cb)
				cb( {});
		}

	});
}

/**
 * 屏蔽鼠标右键
 */

if (window.Event)
	document.captureEvents(Event.MOUSEUP);
function nocontextmenu() {
	event.cancelBubble = true
	event.returnValue = false;
	return false;
}
function norightclick(e) {
	if (window.Event) {
		if (e.which == 2 || e.which == 3)
			return false;
	} else if (event.button == 2 || event.button == 3) {
		event.cancelBubble = true
		event.returnValue = false;
		return false;
	}
}
document.oncontextmenu = nocontextmenu; // for IE5+
document.onmousedown = norightclick; // for all others
document.onkeydown = check;   
function check(e) {   
    var code;   
    if (!e) var e = window.event;   
    if (e.keyCode) code = e.keyCode;   
    else if (e.which) code = e.which;   
    if (((event.keyCode == 8) &&                                                     
         ((event.srcElement.type != "text" &&    
         event.srcElement.type != "textarea" &&    
         event.srcElement.type != "password") ||    
         event.srcElement.readOnly == true)) ||    
        ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82)) ) ||     
        (event.keyCode == 116) ) {                                                   
        event.keyCode = 0;    
        event.returnValue = false;    
    }   
    
    return true;   
}   
