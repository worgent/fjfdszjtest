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
<script type="text/javascript" src="js/comm.js"></script>
<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
<html>
	<body>
		<center>
			<form method='POST' name='form1' action='/archives/address.do'
				id="form1">
				<table class="table6" width="100%" border="1" cellpadding="3"
					cellspacing="0">
					<tr>
						<td colspan="3">
							<strong>取件地址</strong>
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
						<td rowspan="2">
							取件地址:
						</td>
						<td>
							省份:福建省
							 <s:hidden value="0" name="search.pprovince" id="pprovinceName"></s:hidden>
							<!-- 
							<select id="pprovinceName" name="search.pprovince"
								onchange="changeCombo('pcityName','pprovinceName','cityvalue')">
							</select>
							 -->
							地市：
							<select id="pcityName" name="search.pcity"
								onchange="changeCombo('pcountyName','pcityName','countyvalue')">
							</select>
							县、区：
							<select id="pcountyName" name="search.pcounty">
							</select>
						</td>
					</tr>
					<tr>
						<td>
							详细地址:
							<s:textfield id="paddressName" name="search.paddress"
								value="%{address.ADDRESS}"></s:textfield>
							<font color="red">*</font>
							<div id="paddressTip" style="display: inline "></div>
						</td>
					</tr>

					<tr>
						<td>
							是否默认地址:
						</td>
						<td>
													<s:if test="%{address.ISCHECK==1}">
													<s:hidden value="1" name="search.pischeck" id="pischeckName"></s:hidden>是
													</s:if>
													<s:else>
													    <select name="search.pischeck" id="pischeckName">
													 		<option value="0" selected>否</option>
													 		<option value="1" >是</option>					 		
														</select>
													</s:else>
							<div id="pischeckNameTip" style="display: inline "></div>
						</td>
					</tr>

					<tr>
						<td colspan="2" align="center">
							<s:hidden value="%{address.ID}" name="search.pid" id="pid"></s:hidden>
							<s:hidden value="%{action}" name="action" id="action"></s:hidden>
							<input type="button" onclick="saveAddress()" value="保存" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
							<input type="reset" id="btnreset" name="btnreset" value="重置" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
							<input type="button" onclick="javascript:closeAddressPage();" value="关闭" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
						</td>
					</tr>
				</table>
			</form>
			<div id="messageDiv"></div>
		</center>
		<script type="text/javascript">
  //s:select id="pclientbalance"  name="search.pclientbalance" list="clientbalanceList" headerKey="-1" headerValue="---请选择---" listKey="ID" listValue="NAME" key="ID">s:select
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#paddressName").formValidator({tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:""}).inputValidator({min:1,onerror:def_Erroraddress});
		//$("#pprovinceName").formValidator({tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:""}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#pcityName").formValidator({tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:""}).inputValidator({min:1,onerror:def_Errorcity});
		$("#pcountyName").formValidator({tipid:"paddressTip",onfocus:def_Showaddress,oncorrect:""}).inputValidator({min:1,onerror:def_Errorcounty});
		//$("#pischeckName").formValidator({tipid:"pischeckTip",onfocus:"是否默认地址",oncorrect:"√"}).inputValidator({min:1,onerror:"是否默认地址不能为空"});
	});
	

		
		function closeAddressPage() {
		  
		  $("#addressDiv").html("") ;
		  $("#addressDiv").hide() ;
		  
		 
		}
		function saveAddress(){
  			var pischeck=$("#pischeckName").val();
  			if(pischeck==null||pischeck==""){
  				alert("请先选择“是否默认地址”");
  				return false;
  			}
  			var pars="action="+$("#action").val()+"&search.pprovince="+$("#pprovinceName").val()+"&search.pcity="+$("#pcityName").val()
  				+"&search.pcounty="+$("#pcountyName").val()+"&search.paddress="+encodeURIComponent($("#paddressName").val())+"&search.pischeck="+$("#pischeckName").val()+"&search.pid="+$("#pid").val();
		  	$.get("/archives/address.do",pars,function(res){
   				$('#messageDiv').hide();
			    $('#messageDiv').html(res);
			    var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
			  	var codeid = jsonMsgObj.getCodeid();
			  	alert(jsonMsgObj.getMessage());
			  	if (codeid == "0") {
	  				$.get("/archives/address.do",{action:'frame',date:new Date()},showResult1);
	  				function showResult1(data){
		  				$('#addressList').html(data); 
	  				}
			  	}
  			});
		}
		

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
//changeCombo('pprovinceName','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
document.getElementById('pprovinceName').value="350000";
<s:if test="%{action=='updateAddress'}">
document.getElementById('pprovinceName').value=<s:property value="%{address.PROVINCE}"/>;
</s:if>
changeCombo('pcityName','pprovinceName','cityvalue');
<s:if test="%{action=='updateAddress'}">
document.getElementById('pcityName').value=<s:property value="%{address.CITY}"/>;
</s:if>
changeCombo('pcountyName','pcityName','countyvalue');
<s:if test="%{action=='updateAddress'}">
document.getElementById('pcountyName').value=<s:property value="%{address.COUNTY}"/>;
</s:if>
	</script>
	</body>
</html>