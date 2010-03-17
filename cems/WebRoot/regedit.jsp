<%@page contentType="text/html; charset=gbk"%>
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
<body>
<div align="center" >
	<table border="4" cellpadding="0" style="border-collapse: collapse" width="969" id="table3" height="648" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table4">
				<tr>
					<td>
					<img border="0" src="images/top1.jpg" width="518" height="76"><img border="0" src="images/top2.jpg" width="443" height="76"></td>
					<td>　</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="961" id="table5" background="images/nav.jpg" height="40">
				<tr>
					<td width="145" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">首页</font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">企业品牌</font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">企业文化</font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">新闻动态</font></b></td>
					<td width="167" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">产品服务</font></b></td>
					<td width="157" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">专项营销</font></b></td>
				</tr>
			</table>
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
<TABLE height=502 width=619 border=0 id="table12" cellpadding="0" style="border-collapse: collapse" bordercolor="#C0C0C0">
  <TBODY>
  <TR>
    <TD width="95"></TD>
    <TD width="524"></TD></TR>
  <TR>
    <TD colSpan=2><s:actionerror theme="ems" /><s:actionmessage theme="ems" /></TD></TR>
  <TR>
    <TD align=left colSpan=2><b><font size="2" color="red">【必填项】 </font></b> </TD></TR>
  <TR align="left">
    <TD width="95"><font size="2">用户名:</font></TD>
    <TD width="524"><s:textfield id="pcode" name="search.pcode"  ></s:textfield><font size="2"> <FONT color=red>
	*</FONT> 
    </font> 
        <div id="pcodeTip" style="display: inline "></div>
    </TD>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">密码:</font></TD>
    <TD width="524"><s:password id="ppasswd" name="search.ppasswd"></s:password> <font size="2"> <FONT 
      color=red>*</FONT> </font> 
       <div id="ppasswdTip" style="display: inline "></div>
      </TD>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">确认密码:</font></TD>
    <TD width="524"><s:password id="preppasswd" name="search.preppasswd"></s:password><font size="2"> <FONT 
      color=red>*</FONT> </font>
    <div id="preppasswdTip" style="display: inline "></div>  
       </TD>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">姓名:</font></TD>
    <TD width="524"> <s:textfield id="pname" name="search.pname" ></s:textfield><font size="2"> <FONT color=red>*</FONT> 
	</font>
	<div id="pnameTip" style="display: inline "></div>
	 </TD>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">固定电话:</font></TD>
    <TD width="524"><s:textfield id="ptel" name="search.ptel" ></s:textfield><font size="2"> </font> </TD>
    <div id="ptelTip" style="display:inline"></div>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">手机:</font></TD>
    <TD width="524"><s:textfield id="pmobile" name="search.pmobile"></s:textfield><font size="2"> <FONT color=red>*</FONT> 
	</font> 
	<div id="pmobileTip" style="display: inline "></div>
	</TD>
    </TR>
  <TR align="left">
    <TD rowSpan=2 width="95"><font size="2">地址:</font></TD>
    <TD width="524"><font size="2">省份: </font>
	<select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue')"></select>
	<font size="2"> 地市：</font>
	<select id="pcity" name="search.pcity" onchange="changeCombo('pcounty','pcity','countyvalue')"></select>
	<font size="2"> 县、区：</font>
	<select id="pcounty" name="search.pcounty"></select>
	<font size="2"> </font> </TD>
    </TR>
  <TR align="left">
    <TD width="524"><font size="2">详细地址: </font> 
    <s:textfield id="paddress"  name="search.paddress" size="40"> </s:textfield>
	<font size="2"><FONT color=red>*</FONT> </font>
<div id="paddressTip" style="display: inline "></div>	
</TD>
	
	</TR>
  <TR>
    <TD align=left colSpan=2><font size="2" color="red"><b>【选填项】</b></font><font size="2">
	</font> </TD></TR>
  <TR align="left">
    <TD width="95"><font size="2">单位名称:</font></TD>
    <TD width="524"><s:textfield id="punit" name="search.punit"></s:textfield><font size="2"> </font>
    <div id="punitTip" style="display: inline "></div>
     </TD>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">电子邮箱:</font></TD>
    <TD width="524"><s:textfield id="pemail" name="search.pemail"></s:textfield><font size="2"> </font>
    <div id="pemailTip" style="display: inline "></div>
     </TD>
    </TR>
  <TR align="left">
    <TD width="95"><font size="2">验证码：</font></TD>
    <TD width="524"><s:textfield id="pverifycode" name="search.pverifycode"  size="6" maxlength="4" ></s:textfield>
    <font size="2"> <IMG  id=imgcode title=看不清？！换一个 style="CURSOR: pointer" 
      onclick="document.getElementById('imgcode').src='/authimg?action='+Math.random();" 
      alt=看不清？！换一个 src="/authimg" border=0> 
      <FONT color=red>*</FONT> </font>
     <div id="pverifycodeTip" style="display: inline "></div> 
       </TD>
    </TR>
  <TR>
    <TD align="center" colSpan=2>
													<input type="button" onclick="javascript:save()" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
													<input type="button" onclick="javascript:reset()" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
												    <input type="button" onclick="javascript:history.go(-1)" value="返回" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
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
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table10" background="images/di.jpg" height="27">
				<tr>
					<td valign="middle"><span style="font-size: 4pt"><br>
					</span><span style="font-size: 9pt">&nbsp;<font color="#FFFFFF">All 
					rights reserved. 2009&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					技术支持 by </font><font color="#02687F">泉州市冠发信息科技有限公司</font></span></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
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

$(document).ready(function(){
	//设置背景
	$("body").css("background-img","url('images/bg.jpg')");


	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){
	   if($("#ptel").val()==""&&$("#pmobile").val()=="")
	   {
		   alert('电话和手机必填一项');
		   return false;	   
	   }else
	   {
		   alert('确认保存!');
		   return true;
	   }
	}});
	$("#pcode").formValidator({onfocus:"用户名至少6个字符,最多40个字符",oncorrect:"√"}).inputValidator({min:6,max:40,onerror:"你输入的用户名非法,请确认"})//.regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"})
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
		onerror : "该用户名不可用，请更换用户名",
		onwait : "正在对用户名进行合法性校验，请稍候..."//function(){alert($("#pcode").val());}
	});
	



	$("#ppasswd").formValidator({onfocus:"密码不能为空,且长度为6~20个字符",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"密码两边不能有空符号"},onerror:"密码不能为空,请确认"});
	$("#preppasswd").formValidator({onfocus:"两次密码必须一致",oncorrect:"√"}).inputValidator({min:6,max:20,empty:{leftempty:false,rightempty:false,emptyerror:"重复密码两边不能有空符号"},onerror:"重复密码不能为空,请确认"}).compareValidator({desid:"ppasswd",operateor:"=",onerror:"两次密码不一致,请确认"});

	$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
	$("#ptel").formValidator({onfocus:"电话号码或小灵通,例059512345678",oncorrect:"√"}).regexValidator({regexp:"^$|^\\d{10,12}$",onerror:"电话号码格式不正确"});
	$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"^$|^(13|15|18)[0-9]{9}$",onerror:"手机号码格式不正确"});
	$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	$("#pcity").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	$("#pcounty").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	$("#pverifycode").formValidator({onfocus:"验证码",oncorrect:"√"}).inputValidator({min:1,max:4,onerror:"验证码为4位"});
	$("#pemail").formValidator({onfocus:"电子邮箱",oncorrect:"√"}).regexValidator({regexp:"emailex",datatype:"enum",onerror:"电子邮箱格式不正确"});	
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
//初始化信息
changeCombo('pprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
document.getElementById('pprovince').value="350000";
changeCombo('pcity','pprovince','cityvalue');
changeCombo('pcounty','pcity','countyvalue');
</script>
</body>
</html>