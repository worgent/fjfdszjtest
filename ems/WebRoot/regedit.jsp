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
<body>
<center>
<form  method='POST' name='form1' action='/frontlogin.do?action=regedit'  id="form1">
   <table width="905" height="301" border="1">
   <tr>
   <td colspan="3"> 
       <strong>注册页面</strong>
   </td>
   </tr>
  <tr>
	<td>
		<s:actionerror theme="ems" />
	</td>
	<td>
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr> 
   <td colspan="3">&nbsp;</td>
   </tr>
   <tr> 
   <td>用户名:</td>
   <td>
   <input width=50 type="text" id="pcode" value=""  name="search.pcode"/>
   <font color="red">*</font>
   </td>
   <td><div id="pcodeTip" style="width:250px"></div></td>
   </tr>

   <tr> 
   <td>密码:</td>
   <td>
   <input type="password" id="ppasswd"  name="search.ppasswd"   />
   <font color="red">*</font>
   </td>
   <td><div id="ppasswdTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>确认密码:</td>
   <td>
   <input type="password" id="preppasswd"  name="search.preppasswd"   />
   <font color="red">*</font>
   </td>
   <td><div id="preppasswdTip" style="width:250px"></div></td>
   </tr>
   
   
   <tr> 
   <td>姓名:</td>
   <td>
   <input type="text" id="pname"  name="search.pname"/>
   <font color="red">*</font>
   </td>
   <td><div id="pnameTip" style="width:250px"></div></td>
   </tr>
   
   
   <tr> 
   <td>固定电话:</td>
   <td>
   区号:
   <input type="text" id="pareacode"  name="search.pareacode"/>
   <font color="red">*</font>
   电话号码:
   <input type="text" id="ptel"  name="search.ptel"/>
   <font color="red">*</font>
   </td>
   <td><div id="ptelTip" style="width:250px"></div></td>
   </tr>
   
   
   
   <tr> 
   <td>手机:</td>
   <td>
   <input type="text" id="pmobile"  name="search.pmobile"/>
   <font color="red">*</font>
   </td>
   <td><div id="pmobileTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td rowspan="2">地址:</td>
   <td>
                         省份:   
                       <select id="pprovince" name="search.pprovince" onchange="changeCombo('pcity','pprovince','cityvalue')">
						</select>
                        地市：<select id="pcity" name="search.pcity"   onchange="changeCombo('pcounty','pcity','countyvalue')">
						</select>
					  县、区：<select id="pcounty" name="search.pcounty" >
						</select>
   </td>
   
   <td rowspan="2"><div id="paddressTip" style="width:250px"></div></td>
   </tr>
   <tr> 
   <td>
      详细地址:
   <input type="text" id="paddress"  name="search.paddress" width="550"/>
   <font color="red">*</font>
   </td>
   </tr>   
   
   <tr> 
   <td>
		选填
   </td>
   </tr>
   
   <tr> 
   <td>单位名称:</td>
   <td>
   <input type="text" id="punit"  name="search.punit"/>
   </td>
   <td><div id="punitTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>电子邮箱:</td>
   <td>
   <input type="text" id="pemail"  name="search.pemail"/>
   </td>
   <td><div id="pemailTip" style="width:250px"></div></td>
   </tr>
   
      
   <tr>
      <td>验证码：</td>
      <td>
       <input type="text"  id="pverifycode" name="search.pverifycode"  size="6" maxlength="4"/> 
       <img border=0 src="/authimg" id="imgcode" style="cursor:pointer;" border="0" onClick="document.getElementById('imgcode').src='/authimg?action='+Math.random();" title="看不清？！换一个" alt="看不清？！换一个"/>
      <font color="red">*</font>
      </td>
      <td><div id="pverifycodeTip" style="width:250px"></div></td>
   </tr>
   <tr>
   <td colspan="3" align="center">
     <s:submit id="saveId" value="保 存"></s:submit> &nbsp;<s:reset id="setId" value="重 置"></s:reset>
   </td>
   </tr>
 </table>

</form>
</center>
<script type="text/javascript">
//前台脚本验证   onclick="javascript:isExist(this);" 
$(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认保存!');return true;}});
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
	$("#pareacode").formValidator({tipid:"ptelTip",onfocus:"地区区号3位或4位数字",oncorrect:"√"}).regexValidator({regexp:"^\\d{3,4}$",onerror:"地区区号不正确"});
	$("#ptel").formValidator({onfocus:"电话号码或小灵通",oncorrect:"√"}).regexValidator({regexp:"tel",datatype:"enum",onerror:"电话号码格式不正确"});
	$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
	$("#pprovince").formValidator({tipid:"paddressTip",onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});

	$("#paddress").formValidator({onfocus:"通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
	$("#pverifycode").formValidator({onfocus:"验证码",oncorrect:"√"}).inputValidator({min:1,max:4,onerror:"验证码为4位"});
	$("#pemail").formValidator({onfocus:"电子邮箱",oncorrect:"√"}).regexValidator({regexp:"email",datatype:"enum",onerror:"电子邮箱格式不正确"});	
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
    cdsales.load("/archives/area.do?action="+action+"&search.pid="+parmid);
     var bi;
     if(cdsales.documentElement!=null)
         bi=cdsales.documentElement.selectNodes("NODE");
    if(bi!=null&&bi.length>0)
    {
       for(var i=0;i<bi.length;i++){     
                document.getElementById(chCombo).length=i+1; 
	   			document.getElementById(chCombo).options[i].value = bi[i].selectSingleNode("THE_CODE").text;//隐藏值
	   			document.getElementById(chCombo).options[i].text =  bi[i].selectSingleNode("THE_NAME").text;//显示值
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