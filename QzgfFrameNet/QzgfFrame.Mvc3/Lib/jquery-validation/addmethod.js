// 字符验证       
  jQuery.validator.addMethod("stringCheck", function(value, element) {       
     return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);       
 }, "只能包括中文字、英文字母、数字和下划线");

 // 字符跟数字      
 jQuery.validator.addMethod("isDataCode", function (value, element) {
     var dvar = /^[A-Za-z0-9]*$/; 
     return this.optional(element) || (dvar.test(value)) ;
 }, "只能包括英文字母、数字");

 // 数字      
 jQuery.validator.addMethod("isNumCode", function (value, element) {
     var dvar = /^[0-9]*$/;
     return this.optional(element) || (dvar.test(value));
 }, "只能包括数字");

 // 字符跟数字 下划线   PRODUCT_VOICELINE,VOIP专线 ，IMS专线 
 jQuery.validator.addMethod("isVoiceNo", function (value, element) {
     var dvar = /^(PRODUCT_VOICELINE_)[0-9_\s]*$/;
     return this.optional(element) || (dvar.test(value));
 }, "只能包括'PRODUCT_VOICELINE_'开头加数字、下划线、空格");

 // 字符跟数字 下划线,互联网专线   
 jQuery.validator.addMethod("isIPNo", function (value, element) {
     var dvar = /^(PRODUCT_IPLINE_)[0-9_\s]*$/;
     return this.optional(element) || (dvar.test(value));
 }, "只能包括'PRODUCT_IPLINE_'开头加数字、下划线、空格");

 // 字符跟数字 下划线,广域网专线   
 jQuery.validator.addMethod("isWideNo", function (value, element) {
     var dvar = /^(PRODUCT_TRANLINE_)[0-9_\s]*$/;
     return this.optional(element) || (dvar.test(value));
 }, "只能包括'PRODUCT_TRANLINE_'开头加数字、下划线、空格");

 // 字符跟数字 横线  
 jQuery.validator.addMethod("isSeqNo", function (value, element) {
     var dvar = /^[A-Za-z0-9-]*$/;
     return this.optional(element) || (dvar.test(value));
 }, "只能包括英文字母、数字、横线");  

 // 中文字两个字节       
  jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {       
     var length = value.length;       
      for(var i = 0; i < value.length; i++){       
          if(value.charCodeAt(i) > 127){       
         length++;       
         }       
     }       
     return this.optional(element) || ( length >= param[0] && length <= param[1] );       
 }, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");
 


 // 中英文姓名    
 jQuery.validator.addMethod("isFullName", function (value, element) { 
     var yname = /^[a-zA-Z ]{1,20}$/;    //英文20个字符以内        
     var zname = /^[\u4e00-\u9fa5]{1,10}$/;    //中文20个字符以内
     return this.optional(element) || (yname.test(value)) || (zname.test(value));
 }, "请正确填写您的姓名");

 // 中英文姓名 多个名称
 jQuery.validator.addMethod("isMFullName", function (value, element) {
     var yname = /^[a-zA-Z]{1,20}$/;    //英文20个字符以内        
     var zname = /^[\u4e00-\u9fa5]|，|\{1,10}$/;    //中文20个字符以内   
     return this.optional(element) || (yname.test(value)) || (zname.test(value));
 }, "请正确填写您的姓名");
  /^[\u0391-\uFFE5\w]+$/
 // 车牌号     
 jQuery.validator.addMethod("isVehicleNo", function (value, element) {
     var strvar = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Za-z0-9]{5}$/;
     return this.optional(element) || (strvar.test(value));
 }, "请正确填写您的车牌号(汉字+大写字母+5个任意字母或数字)");   
        
 // 身份证号码验证
 jQuery.validator.addMethod("isIdCardNo", function (value, element) {
     //var cardNo = /^[\d]{6}(19|20)*[\d]{2}((0[1-9])|(11|12))([012][\d]|(30|31))[\d]{3}[xX\d]*$/;

     return this.optional(element) || (isIdCardNo(value) );
 }, "请正确输入您的身份证号码");
      
 // 手机号码验证       
  jQuery.validator.addMethod("isMobile", function(value, element) {       
     var length = value.length;   
     var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
     return this.optional(element) || (length == 11 && mobile.test(value));       
 }, "请正确填写您的手机号码");       
      
 // 电话号码验证       
  jQuery.validator.addMethod("isTel", function(value, element) {       
     var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678   
     return this.optional(element) || (tel.test(value));       
 }, "请正确填写您的电话号码");   
   
 // 联系电话(手机/电话皆可)验证
 jQuery.validator.addMethod("isPhone", function (value, element) {
     var length = value.length;
      var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
     var tel = /^\d{3,4}-?\d{7,9}$/;
     //var tel = /(^[[0-9]{3}-|\[0-9]{4}-]?([0-9]{8}|[0-9]{7})$)|(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
     return this.optional(element) || ((tel.test(value)) || (length == 11 && mobile.test(value)));

 }, "请正确填写您的联系电话");   
      
 // 邮政编码验证       
  jQuery.validator.addMethod("isZipCode", function(value, element) {       
     var tel = /^[0-9]{6}$/;       
     return this.optional(element) || (tel.test(value));
 }, "请正确填写您的邮政编码");
 var re = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
 // IP验证   
 jQuery.validator.addMethod("isIP", function (value, element) {
     var length = value.length;
     var ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
     return this.optional(element) || (ip.test(value));

 }, "请正确填写您的IP地址");
 // 子网掩码
 jQuery.validator.addMethod("isSub", function (value, element) {
     var length = value.length;
     var ip = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
     return this.optional(element) || (ip.test(value));

 }, "请正确填写您的子网掩码");
 // 可为空，IP验证
 jQuery.validator.addMethod("isIPB", function (value, element) {
     var length = value.length;
     var ip = /^$|^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
     var blank = /^$/;
     return this.optional(element) || (ip.test(value)) || (blank.test(value));
 }, "请正确填写您的IP地址");
 // MAC地址验证   var reg_name=/[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}:[A-F\d]{2}/; 
 jQuery.validator.addMethod("isMAC", function (value, element) {
     var length = value.length;
     var mac = /[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}-[A-F\d]{2}/;
     var strmac = /[A-F\d]{2}[A-F\d]{2}[A-F\d]{2}[A-F\d]{2}[A-F\d]{2}[A-F\d]{2}/;
     var blank = /^$/;
     return this.optional(element) || (mac.test(value)) || (blank.test(value)) || (strmac.test(value));
 }, "请正确填写您的MAC地址");   
  //数字及三位小数点内 
 jQuery.validator.addMethod("isRate", function (value, element) {
     var length = value.length;
     var mac = /^\d+(\.\d{1,3})?$/;
     return this.optional(element) || (mac.test(value));
 }, "请正确填写您的数字,可有小数点");         
 
 //身份证验证 
 function isIdCardNo (str) { 
//身份证正则表达式(15位) 
var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/; 
//身份证正则表达式(18位)
var isIDCard2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
var isIDCard3 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}[xX\d]$/; 
 
//验证身份证，返回结果  
return (isIDCard1.test(str) || isIDCard2.test(str) || isIDCard3.test(str));
} 


