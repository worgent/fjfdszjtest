/*
//提示信息窗
function alert(message){ 
    if ($("#dialogalert").length == 0) { 
        $("body").append(' <div id="dialogalert"> </div>'); 
        $("#dialogalert").dialog({ 
            autoOpen: false, 
            title: '消息框', 
            modal: true, 
            resizable:false, 
            overlay: { 
                opacity: 0.5, 
                background: "black" 
            }, 
            buttons: { 
                "确定": function(){ 
                    $(this).dialog("close"); 
                } 
            } 
        }); 
    } 
    
    $("#dialogalert").html(message); 
    $("#dialogalert").dialog("open"); 
} 
//确认信息窗
function confirm(message, callback,scope,par){ 
    if ($("#dialogconfirm").length == 0) { 
        $("body").append(' <div id="dialogconfirm"> </div>'); 
        $("#dialogconfirm").dialog({ 
            autoOpen: false, 
            title: '消息框', 
            modal: true, 
            resizable:false, 
            overlay: { 
                opacity: 0.5, 
                background: "black" 
            }, 
            buttons: { 
                "确定": function(){ 
                    //callback.call(scope);
                    callback.apply(scope,par);
                    $(this).dialog("close"); 
                }, 
                "取消": function(){ 
                    $(this).dialog("close"); 
                } 
            } 
        }); 
    } 
    $("#dialogconfirm").html(message); 
    $("#dialogconfirm").dialog("open");    
} 

//确认信息窗
function confirmex(message, callback){ 
    if ($("#dialogconfirm").length == 0) { 
        $("body").append(' <div id="dialogconfirm"> </div>'); 
        $("#dialogconfirm").dialog({ 
            autoOpen: false, 
            title: '消息框', 
            modal: true, 
            resizable:false, 
            overlay: { 
                opacity: 0.5, 
                background: "black" 
            }, 
            buttons: { 
                "确定": function(){ 
					eval(callback);
                }, 
                "取消": function(){ 
                    $(this).dialog("close"); 
                } 
            } 
        }); 
    } 
    $("#dialogconfirm").html(message); 
    $("#dialogconfirm").dialog("open");    
} 
*/


//共用方法声明
function isUndefined(_1){
return typeof _1=="undefined";
}
function isString(_2){
return typeof _2=="string";
}
function isElement(_3){
return _3&&_3.nodeType==1;
}
function isFunction(_4){
return typeof _4=="function";
}
function isObject(_5){
return typeof _5=="object";
}
function isArray(_6){
return Object.prototype.toString.call(_6)==="[object Array]";
}
function isNumber(_7){
return typeof _7=="number";
}

//加载定义
function onloadRegister(handler){
	window.loaded?_runHook(handler):_addHook('onloadhooks',handler);
}

//去空格
String.prototype.Trim = function() 
{ 
return this.replace(/(^\s*)|(\s*$)/g, ""); 
} 
 
String.prototype.LTrim = function() 
{ 
return this.replace(/(^\s*)/g, ""); 
} 
 
String.prototype.RTrim = function() 
{ 
return this.replace(/(\s*$)/g, ""); 
} 
