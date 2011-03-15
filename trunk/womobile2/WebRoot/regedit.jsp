<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>	
<head>
	<title>联通沃移动助理平台</title>
	<link href="<%=path%>/css/indexnew.CSS"  type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="/js/defValidator.js"></script>
</head>
<body>
<div align="center" >
	<table border="4" cellpadding="0" style="border-collapse: collapse" width="969" id="table3" height="648" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">

			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6" height="272">
				<tr>
					<td valign="top" bgcolor="#FFFFFF">
					<div align="center">
						<table border="0" width="933" id="table11" cellpadding="0" style="border-collapse: collapse" height="100%" bgcolor="#FFFFFF">
							<tr>
								<td height="18"></td>
							</tr>
							<tr>
								<td height="55">
								<img border="0" src="images/zhucelogo.jpg" width="191" height="55"></td>
							</tr>
							<tr>
								<td height="41">
								<img border="0" src="images/zhuce01.jpg" width="933" height="41"></td>
							</tr>
							<tr>
								<td background="images/zhuce02.jpg">
								<div align="center">
<form  method='POST' name='form1' action='/frontlogin.do?action=regedit'  id="form1">								
<TABLE height=502 width=639 border=0 id="table12" cellpadding="0" style="border-collapse: separate;border-spacing:1px;" bordercolor="#C0C0C0">
  <TBODY>
  <TR>
    <TD width="95"></TD>
    <TD></TD></TR>
  <TR>
    <TD colspan="2"><s:actionerror theme="ems" /><s:actionmessage theme="ems" /></TD></TR>
  <TR align="left">
    <TD width="95" align="right"><span style="FONT-SIZE: 9pt">用&nbsp;&nbsp;户&nbsp;&nbsp;名:</span></TD>
    <TD width="524" align="left">
    <span style="font-size: 9pt">
    <s:textfield id="pcode" name="search.pcode" size="20"></s:textfield> <font size="2"> <FONT color=red>*</FONT></font>
    </span> <div id="pcodeTip" style="display:inline"></div>
    </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</font></TD>
    <TD width="524" align="left"><s:password id="ppasswd" name="search.ppasswd" size="21"></s:password> <font size="2"><FONT 
      color=red>*</FONT> </font> 
       <div id="ppasswdTip" style="display: inline "></div>
      </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">确认密码:</font></TD>
    <TD width="524" align="left"><s:password id="preppasswd" name="search.preppasswd" size="21"></s:password><font size="2">&nbsp;<FONT 
      color=red>*</FONT> </font>
    <div id="preppasswdTip" style="display: inline "></div>  
       </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</font></TD>
    <TD width="524" align="left"> <s:textfield id="pname" name="search.pname"  size="20"></s:textfield><font size="2"> <FONT color=red>*</FONT> 
	</font>
	<div id="pnameTip" style="display: inline "></div>
	 </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">固定电话:</font></TD>
    <TD width="540" align="left"><s:textfield id="ptel" name="search.ptel"  size="20"></s:textfield><font size="2"><FONT>&nbsp;&nbsp;&nbsp;</FONT>  </font> 
     <div id="ptelTip" style="display:inline"></div>
    </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</font></TD>
    <TD width="524" align="left"><s:textfield id="pmobile" name="search.pmobile" size="20"></s:textfield><font size="2"> <FONT color=red>*</FONT> 
	</font> 
	<div id="pmobileTip" style="display: inline "></div>
	</TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">省&nbsp;市&nbsp;&nbsp;区:</font></TD>
    <TD width="524"><font size="2">福建省 </font>
    <s:hidden value="0" name="search.pprovince" id="pprovince"></s:hidden>
    <!-- 
	<select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue');clearCombo('pcounty')"></select>
	 -->
	<font size="2"> 地市:</font>
	<select id="pcity" name="search.pcity" onchange="changeCombo('pcounty','pcity','countyvalue')"></select>
	<font size="2"> 县、区:</font>
	<select id="pcounty" name="search.pcounty"></select>
	<font size="2"> </font> </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">详细地址:</font></TD>
    <TD width="524" align="left"><font size="2"></font> 
    <s:textfield id="paddress"  name="search.paddress" size="36"> </s:textfield>
	<font size="2"><FONT color=red>*</FONT> </font>
<div id="paddressTip" style="display: inline "></div>	
</TD>
	
	</TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">单位名称:</font></TD>
    <TD width="524" align="left"><s:textfield id="punit" name="search.punit" size="20"></s:textfield><font size="2"> </font>
    <div id="punitTip" style="display: inline "></div>
     </TD>
    </TR>
  <TR align="left">
    <TD width="95" align="right"><font size="2">电子邮箱:</font></TD>
    <TD width="524" align="left"><s:textfield id="pemail" name="search.pemail" size="20"></s:textfield><font size="2"> <FONT color=red>*</FONT> 
	</font> 
    <div id="pemailTip" style="display: inline "></div>
     </TD>
    </TR>
    <TR align="left">
    <TD width="95" align="right"><font size="2">客户代码:</font></TD>
    <TD width="524" align="left"><s:textfield id="pclientcode" name="search.pclientcode" size="20"></s:textfield><font size="2">&nbsp;&nbsp;&nbsp;
	</font> 
   <div id="pclientcodeTip" style="display: inline "></div>
     </TD>
    </TR>  
  <TR align="left">
    <TD width="95" align="right" style="vertical-align:middle;"><font size="2">验&nbsp;&nbsp;证&nbsp;码:</font></TD>
    <TD width="524" align="left" style="vertical-align:middle;"><s:textfield id="pverifycode" name="search.pverifycode"  size="9" maxlength="4" ></s:textfield>
    <font size="2"> <IMG  id=imgcode title=看不清？！换一个 style="CURSOR: pointer" 
      onclick="document.getElementById('imgcode').src='/authimg?action='+Math.random();" 
      alt=看不清？！换一个 src="/authimg" border=0>&nbsp;&nbsp;&nbsp;&nbsp; 
      </font>
     <div id="pverifycodeTip" style="display: inline;vertical-align:top; "></div> 
       </TD>
    </TR>
  <TR>
    <TD align="center" colSpan=2>
													<input type="button" onclick="javascript:save()" value="注册" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
												    <input type="button" onclick="javascript:mainpage()" value="返回" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
	</TD>
	</TR>
	</TBODY></TABLE>
</form>	
	</div>
  <p>
  </td>
  </tr>
							<tr>
								<td>
								<img border="0" src="images/zhuce03.jpg" width="933" height="7"></td>
							</tr>
							<tr>
								<td>　</td>
							</tr>
						</table>
					</div>
					</td>
				</tr>
			</table>
			<div>
				<table border="0" cellpadding="0" align="center"
					style="border-collapse: collapse" width="530px"
					height="30">
					<tr>
						<td align="center" width=100% height=31>
							<span style="FONT-SIZE: 12px; COLOR: #000; FONT-FAMILY: Verdana">福建省邮政速递物流有限公司
								版权所有 闽ICP备10200383号 <br/><br />技术支持 泉州市冠发信息科技有限公司 </span>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
function toindex(){
			jAlert('新用户注册成功,请重新登录!', '提示', function(r){
				mainpage();
			});
}			
//设置注册成功时跳到首页
<s:if test="%{search.phide==1}">
toindex();
</s:if>
		
//前台脚本验证   onclick="javascript:isExist(this);" 

//保存数据
	function save(){
	    if($.formValidator.pageIsValid()){
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}
	
	 //重置数据
	function reset(){
			document.forms[0].reset();
			return true;
	}
	
	//返回首页
	function mainpage(){
   			window.location.href="/news.do?action=fir&search.ptab=1";
	}

$(document).ready(function(){
	//设置背景
	$("body").css("background-img","url('images/bg.jpg')");


	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){
			   if($("#ptel").val().Trim()==""&&$("#pmobile").val().Trim()=="")
			   {
				   alert('电话和手机必填一项');
				   return false;	   
			   }else
			   {
				   return true;
			   }
    }});
	
	
	$("#pcode").formValidator({onshow:def_Showthecode,onfocus:def_Showthecode,oncorrect:" "}).inputValidator({min:3,max:40,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorthecode},onerror:def_Errorthecodeex})//.regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
	.ajaxValidator({
	    type : "get",
		url : "frontlogin.do",
		data:"action=isExist",
		datatype : "xml",
		success : function(data){
		    root = data.documentElement;
		    var rowSet = root.selectNodes("//delete");
            if( rowSet.item(0).selectSingleNode("value").text == "0" )
			{
                return true;
			}
            else
			{
                return false;
			}
		},
		buttons: $("#saveId"),
		error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},
		onerror : def_Eixtthecode,
		onwait : "正在对用户名进行合法性校验，请稍候..."//function(){alert($("#pcode").val());}
	});

	$("#ppasswd").formValidator({onshow:def_Showpasswd,onfocus:def_Showpasswd,oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorpasswd},onerror:def_Errorpasswdex});
	$("#preppasswd").formValidator({onshow:def_Showrepasswd,onfocus:def_Showrepasswd,oncorrect:" "}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorrepasswd},onerror:def_Errorrepasswdex}).compareValidator({desid:"ppasswd",operateor:"=",onerror:def_Tworepasswd});

	$("#pname").formValidator({onshow:def_Showname,onfocus:def_Showname,oncorrect:" "}).inputValidator({min:1,max:20,empty:{leftempty:false,rightempty:false,emptyerror:def_Errorname},onerror:def_Errorname});
	$("#ptel").formValidator({onshow:def_Showtel,onfocus:def_Showtel,oncorrect:" "}).regexValidator({regexp:"^\\s+$|^$|^[0-9\\-]+$",onerror:def_Errortel});
	$("#pmobile").formValidator({onshow:def_Showmobile,onfocus:def_Showmobile,oncorrect:" "}).regexValidator({regexp:"^\\s+$|^$|^(13|15|18)[0-9]{9}$|^\\d{10,12}$",onerror:def_Errormobile});
	//$("#pprovince").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:"通信地址",oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
	$("#pcity").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Errorcity});
	$("#pcounty").formValidator({onshow:def_Showaddress,tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Errorcounty});
	$("#paddress").formValidator({onshow:def_Showaddress,onfocus:def_Showaddress,oncorrect:" "}).inputValidator({min:1,onerror:def_Erroraddress});
	$("#pverifycode").formValidator({onshow:def_Showverifycode,onfocus:def_Showverifycode,oncorrect:" "}).inputValidator({min:1,max:4,onerror:def_Errorverifycode});
	$("#pemail").formValidator({onshow:def_Showemail,onfocus:def_Showemail,oncorrect:" "}).regexValidator({regexp:"email",datatype:"enum",onerror:def_Erroremail});	

	$("#pclientcode").formValidator({onshow:def_Showclientcode,onfocus:def_Showclientcode,oncorrect:" "}).regexValidator({regexp:"^\\S*$",onerror:"客户代码不能为空,请确认"});
});

//2009-12-01
//省,地,市连动
//参数说明:chCombo变化控件的id
//		  srcCombo引起变化的控件id
//		  action事件类型
function changeCombo(chCombo,srcCombo,action)
{
	var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
    cdsales.async=false; //使用异步加载
    var parmid='';
    if(srcCombo!='')
    {
      parmid=document.getElementById(srcCombo).value
    }
    cdsales.load("<%=path%>/archives/area.do?action="+action+"&search.pid="+parmid);
     var bi;
     if(cdsales.documentElement!=null)
         bi=cdsales.documentElement.selectNodes("NODE");
    if(bi!=null&&bi.length>0)
    {
       //默认行
       document.getElementById(chCombo).options.length = bi.length+1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
       for(var i=1;i<bi.length+1;i++){     
	   			document.getElementById(chCombo).options[i].value = bi[i-1].selectSingleNode("THE_CODE").text;//隐藏值
	   			document.getElementById(chCombo).options[i].text =  bi[i-1].selectSingleNode("THE_NAME").text;//显示值
       }
    }else{
       document.getElementById(chCombo).options.length = 1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
    }
}

//2010-03-10
//省,地,市连动,jquery实现
//参数说明:chCombo变化控件的id
//		  srcCombo引起变化的控件id
//		  action事件类型
/*
String.prototype.Trim = function() { return this.replace(/(^\s*)|(\s*$)/g, ""); } 
function changeCombo(chCombo,srcCombo,action)
{
	    var parmid='';
	    if(srcCombo!='')
	    {
	      parmid=document.getElementById(srcCombo).value;
	    }
		 $.ajax({
			 mode : "abort",
			 type : "get", 
			 async: false,
		     url:"<%=path%>/archives/area.do",
		     data : "action="+action+"&search.pid="+parmid,
		     dataType:"xml",
		     success:function(xml)
		     {
		          document.getElementById(chCombo).length=0;
		          document.getElementById(chCombo).options[0]=new Option('请选择','0');
		          //document.getElementById(chCombo).options[0].value = "0";//隐藏值
	   			  //document.getElementById(chCombo).options[0].text ="请选择";//显示值    
		          $(xml).find("ROOT>NODE").each(
			            function(i){
				            var id=$(this).find("THE_CODE").text();
				            id=id.replace(/(^\s*)|(\s*$)/g, "");
				            var name=$(this).find("THE_NAME").text(); 
				            document.getElementById(chCombo).options[i+1]=new Option(name,id);  
				            
				            //document.getElementById(chCombo).options[i+1].value = trim(id);//隐藏值
	   			            //document.getElementById(chCombo).options[i+1].text = name;//显示值
	   			            
		            	}
		         )
		     }
	     });
}
*/
/**
*对于连动bug处理,清空关连的数据,当省,市,县连动时使用
*/
function clearCombo(chCombo)
{
	   document.getElementById(chCombo).options.length = 0;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
}

//初始化信息
//changeCombo('pprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
document.getElementById('pprovince').value="350000";
changeCombo('pcity','pprovince','cityvalue');
changeCombo('pcounty','pcity','countyvalue');
//不能修改省份
document.getElementById('pprovince').disable=true;
</script>
</body>
</html>