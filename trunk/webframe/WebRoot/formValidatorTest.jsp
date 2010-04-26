<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@include file="./top.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<title><qzgf:webinfo type="guidename" /> - <s:text
				name="test.title" /> <qzgf:webinfo type="poweredby" />
		</title>
		<qzgf:webinfo type="meta" />

		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="<%=path%>/js/jquery-latest.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/jquery.tablesorter.js"></script>
		<link href="<%=path%>/css/table/style.css" rel="stylesheet"
			type="text/css" />
		<link type="text/css" href="<%=path%>/css/jquery-ui-1.7.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery-ui-1.7.custom.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/jquery.form.js"></script>
		<!-- 引入验证js,及验证样式库 -->
		<link type="text/css" href="<%=path%>/css/validator.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=path%>/js/formValidator.js"></script>
		<script type="text/javascript" src="<%=path%>/js/formValidatorRegex.js"></script>
    </head>
<body>
<form method='POST' name='form1' action='#' >
	<table >
		
		

				<tr> 
			      <td align="right">整数:</td>
			      <td><input type="text" id="zs" style="width:120px" /></td>
			      <td><div id="zsTip" style="width:300px"></div></td>
			    </tr>
			    <tr> 
			      <td align="right">正整数:</td>
			      <td><input type="text" id="zzs" style="width:120px" /></td>
			      <td><div id="zzsTip" style="width:300px"></div></td>
			    </tr>
			        <tr> 
				      <td align="right">正数（正整数 + 0）:</td>
				      <td><input type="text" id="zs1" style="width:120px" /></td>
				      <td><div id="zs1Tip" style="width:300px"></div></td>
				    </tr>
				    
				    <tr> 
				      <td align="right">身份证(正则表达式库):</td>
				      <td><input name="sfz" type="text" id="sfz" /></td>
				      <td><div id="sfzTip" style="width:300px"></div></td>
				    </tr>
				    <tr> 
				      <td align="right">身份证(外部函数):</td>
				      <td><input type="text" id="sfz1" style="width:120px" /></td>
				      <td><div id="sfz1Tip" style="width:300px"></div></td>
				    </tr>
				   <tr> 
				      <td align="right">额外校验:</td>
				      <td><input name="text" type="text" id="ewjy" style="width:120px" /></td>
				      <td><div id="ewjyTip" style="width:270px"></div></td>
				    </tr>
	</table>


</form>
      <script type="text/javascript">
   //Jquery数据验证
$(document).ready(function() {
    //窗体验证
    $.formValidator.initConfig({formid:"form1",onerror:function(){alert("校验没有通过，具体错误请看错误提示")}});
    //数据类型验证      
	$("#zs").formValidator({onshow:"请输入整数",oncorrect:"谢谢你的合作，你的整数正确"}).regexValidator({regexp:"int",datatype:"enum",onerror:"整数格式不正确"});
	$("#zzs").formValidator({onshow:"请输入正整数",oncorrect:"谢谢你的合作，你的正整数正确"}).regexValidator({regexp:"intp",datatype:"enum",onerror:"正整数格式不正确"});
	$("#zs1").formValidator({onshow:"请输入正整数+0",oncorrect:"谢谢你的合作，你的负整数正确"}).regexValidator({regexp:"intp0",datatype:"enum",onerror:"请输入正整数+0"});
    //外部函数调用，是共用的函数库
	$("#sfz").formValidator({onshow:"请输入15或18位的身份证",onfocus:"输入15或18位的身份证",oncorrect:"输入正确"}).regexValidator({regexp:"idcard",datatype:"enum",onerror:"你输入的身份证格式不正确"});;
    $("#sfz1").formValidator({onshow:"请输入15或18位的身份证",onfocus:"输入15或18位的身份证",oncorrect:"输入正确"}).functionValidator({fun:isCardID});
    //字符长度的验证，及单页面的个性化函数验证.
	$("#ewjy").formValidator().inputValidator({min:1,onerror:"这里至少要一个字符,请确认"}).functionValidator({
	    fun:function(val,elem){
	        if(val=="猫冬"){
			    return true;
		    }else{
			    return "额外校验失败,想额外校验通过，请输入\"猫冬\""
		    }
		}
	});  
	} );  
     </script>
     </body>
     </html>
