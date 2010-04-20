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
<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
<html>
	<body>

		<center>
			<form method='POST' name='form1'
				action='/archives/user.do?action=update' id="form1">
				<table width="90%" height="190" border="1">
					<tr>
						<td colspan="3">
							<strong>网上寄件</strong>
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
						<td>
							<input type="button" onclick="javascript:ClientList('<%=path%>')" value="联系人"/>
						</td>
						<td>
							<s:textfield id="pname" name="search.pname" value="%{order.NAME}"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="pnameTip" style="width: 250px"></div>
						</td>
					</tr>
					<tr id="clientTr" style="display:none">
						<td colspan="3"><div id="clientList"></div></td>
					</tr>
					<tr>
						<td>
							固定电话:
						</td>
						<td>
							区号:
							<s:textfield id="pareacode" name="search.pareacode"
								value="%{order.AREACODE}"></s:textfield>
							<font color="red">*</font> 电话号码:
							<s:textfield id="ptel" name="search.ptel" value="%{order.TEL}"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="ptelTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							手机:
						</td>
						<td>
							<s:textfield id="pmobile" name="search.pmobile"
								value="%{order.MOBILE}"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="pmobileTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							单位地址:
						</td>
						<td>
							<s:textfield id="punit" name="search.punit" value="%{order.UNIT}"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="punitTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td rowspan="2">
							<input type="button" onclick="javascript:AddressList('<%=path%>')" value="取件地址"/>
						</td>
						<td>
							<s:hidden value="%{order.ADDRESSID}" name="search.paddressid"
								id="paddressid"></s:hidden>
							省份:
							<s:textfield id="lprovince" name="search.lprovince"
								value="%{order.PROVINCENAME}"></s:textfield>
							<s:hidden value="%{order.PROVINCE}" name="search.pprovince"
								id="pprovince"></s:hidden>
							地市：
							<s:textfield id="lcity" name="search.lcity"
								value="%{order.CITYNAME}"></s:textfield>
							<s:hidden value="%{order.CITY}" name="search.pcity" id="pcity"></s:hidden>
							县、区：
							<s:textfield id="lcounty" name="search.lcounty"
								value="%{order.COUNTYNAME}"></s:textfield>
							<s:hidden value="%{order.COUNTY}" name="search.pcounty"
								id="pcounty"></s:hidden>
						</td>
						<td rowspan="2">
							<div id="paddressTip" style="width: 250px"></div>
						</td>
					</tr>
					
					<tr>
						<td>
							详细地址:
							<s:textfield id="paddress" name="search.paddress"
								value="%{order.ADDRESS}"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					
					<tr id="trId" style="display:none">
						<td colspan="3"><div id="addressList"></div></td>
					</tr>

					<tr>
						<td>
							邮件种类:
						</td>
						<td>
							<select name="search.pmailtype" id="pmailtype">
								<option value="-1">
									请选择
								</option>
								<option value="0"
									<s:if test="%{order.MAILTYPE==0}">selected</s:if>>
									物品型
								</option>
								<option value="1"
									<s:if test="%{order.MAILTYPE==1}">selected</s:if>>
									文件型
								</option>
							</select>
							<font color="red">*</font>
						</td>
						<td>
							<div id="pmailtypeTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							预约时间:
						</td>
						<td>
							<s:textfield id="pbookingtime" name="search.pbookingtime"
								value="%{order.BOOKINGTIME}" readonly="true"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="pbookingtimeTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							寄件数量:
						</td>
						<td>
							<s:textfield id="porderingnum" name="search.porderingnum"
								value="%{order.ORDERINGNUM}"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="porderingnumTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							物品重量:
						</td>
						<td>
							<s:textfield id="porderingweight" name="search.porderingweight"
								value="%{order.ORDERINGWEIGHT}"></s:textfield>
							(千克)
							<font color="red">*</font>
						</td>
						<td>
							<div id="porderingweightTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							客户要求:
						</td>
						<td>
							<s:textfield id="pclientremark" name="search.pclientremark"
								value="%{order.CLIENTREMARK}"></s:textfield>
							<font color="red">*</font>
						</td>
						<td>
							<div id="pclientremarkTip" style="width: 250px"></div>
						</td>
					</tr>

					<tr>
						<td>
							收件人地址:
						</td>
						<td>
							省份:
							<select id="precprovince" name="search.precprovince"
								onchange="changeCombo('preccity','precprovince','cityvalue')">
							</select>
							地市：
							<select id="preccity" name="search.preccity">
							</select>
						</td>
						<td>
							<div id="precaddressTip" style="width: 250px"></div>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<s:hidden value="%{order.ID}" name="search.pid" id="pid"></s:hidden>
							<input type="button" id="btnupdate" name="btnupdate"
								onclick="javascript:save('<s:property value="%{action}"/>');"
								value="保 存" />
							&nbsp; &nbsp; &nbsp;
							<input type="button" id="btnupdateauding" name="btnupdateauding"
								onclick="javascript:save('send');" value="保存并寄件" />
							&nbsp; &nbsp; &nbsp;
							<input type="reset" id="btnreset" name="btnreset" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</center>
		<!-- 引入日期控件及验证 -->
		<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js"
			type="text/javascript"></script>
		<script language="javascript" src="<%=path%>/js/DateTimeMask.js"
			type="text/javascript"></script>
		<script language="javascript" src="<%=path%>/js/formValidatorRegex.js"
			type="text/javascript"></script>
		<script type="text/javascript">
		//创建iframe框架(取件地址)
		function ClientList(path){
			$("#clientTr").show();
			 $("#clientList").show() ;
		  	$('#clientList').html("<center>页面载入中...</center>"); 
		  	var urls1 = getActionMappingURL("/archives/clientMsg",path);
		  	$.get(urls1,{action:'frame',date:new Date()},showResult);
		  	function showResult(res){
			  $('#clientList').html(res); 
		  	}
		}
		
		//创建iframe框架(取件地址)
	function AddressList(path){
		$("#trId").show();
		 $("#addressList").show() ;
		 
	  	$('#addressList').html("<center>页面载入中...</center>"); 
	  var urls1 = getActionMappingURL("/archives/address",path);
	  $.get(urls1,{action:'frame',date:new Date()},showResult);
	  function showResult(res){
		  $('#addressList').html(res); 
	  }
	}
  //s:select id="pclientbalance"  name="search.pclientbalance" list="clientbalanceList" headerKey="-1" headerValue="---请选择---" listKey="ID" listValue="NAME" key="ID">s:select
    //验证
    $(document).ready(function(){
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('确认保存');return true;}});
		$("#pname").formValidator({onfocus:"最多20个字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法,请确认"});
		$("#pareacode").formValidator({tipid:"ptelTip",onfocus:"地区区号3位或4位数字",oncorrect:"√"}).regexValidator({regexp:"^\\d{3,4}$",onerror:"地区区号不正确"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通",oncorrect:"√"}).regexValidator({regexp:"tel",datatype:"enum",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号码格式不正确"});
		$("#paddress").formValidator({onfocus:"取件地址",oncorrect:"√"}).inputValidator({min:1,onerror:"取件地址不能为空,请确认"});
		$("#pmailtype").formValidator({onfocus:"邮件种类",oncorrect:"√"}).inputValidator({min:1,onerror:"邮件种类不能为空,请确认"});
<<<<<<< .mine
		/*$("#pbookingtime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'%y-%M-\#{%d+1}',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的预约时间",onfocus:"请输入的预约时间",oncorrect:"√"}).inputValidator({min:"2008-01-01 00:00:00",max:"2010-01-01 23:59:59",type:"datetime",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"})//;//.defaultPassed();
=======
		$("#pbookingtime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'%y-%M-\#{%d+1}',oncleared:function(){$(this).blur();},onpicked:function(){$(this).blur();}})}).formValidator({onshow:"请输入的预约时间",onfocus:"请输入的预约时间",oncorrect:"√"})//.inputValidator({min:"2008-01-01 00:00:00",max:"2010-01-01 23:59:59",type:"datetime",onerror:"日期必须在\"1900-01-01\"和\"3000-01-01\"之间"})//;//.defaultPassed();
>>>>>>> .r439
		.functionValidator({
		    fun:function(val,elem){
		        if($("#pbookingtime").val()!="")
		        {
					$.ajax(
					{	
						mode : "abort",
						type : "get", 
						url : "<%=path%>/archives/serTime.do", 
						data : "action=serTimeVerify&search.pbookingtime=" + $("#pbookingtime").val()+"&search.pprovince="+$("#pprovince").val()+"&search.pcity="+$("#pcity").val()+"&search.pcounty="+$("#pcounty").val(),
						dataType : "xml",
						success : function(data){
						    root = data.documentElement;
						    var rowSet = root.selectNodes("//result");
				            if( rowSet.item(0).selectSingleNode("value").text == "0" )
							{
								$.formValidator.setTipState(elem,"onError","预约时间不在服务时间范围内!");
				                return false;
							}
				            else
							{
				                $.formValidator.setTipState(elem,"onError","√");
				                return true;
							}
						},
						complete : function(){
							$("#btnupdate").attr({"disabled":false});
							$("#btnupdateauding").attr({"disabled":false});
						}, 
						beforeSend : function(xhr){
							//再服务器没有返回数据之前，先回调提交按钮
							$("#btnupdate").attr({"disabled":true});
							$("#btnupdateauding").attr({"disabled":true});
							$.formValidator.setTipState(elem,"onLoad","正在对预约时间进行合法性校验，请稍候...");
						}, 
						error : function(){
						    $.formValidator.setTipState(elem,"onError","服务器没有返回数据，可能服务器忙，请重试");
						    alert("服务器没有返回数据，可能服务器忙，请重试");
						}
					});
				}else
				{
					return true;
				}	
			}
		});
		*/
		$("#porderingnum").formValidator({onfocus:"寄件数量",oncorrect:"√"}).regexValidator({regexp:"num",datatype:"enum",onerror:"寄件数量格式不正确"});
		$("#porderingweight").formValidator({onfocus:"物品重量",oncorrect:"√"}).regexValidator({regexp:"decmal4",datatype:"enum",onerror:"物品重量格式不正确"});
		$("#pclientremark").formValidator({onfocus:"客户要求",oncorrect:"√"}).inputValidator({min:1,onerror:"客户要求不能为空,请确认"});
		$("#precprovince").formValidator({tipid:"precaddressTip",onfocus:"收件通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});
		$("#preccity").formValidator({tipid:"precaddressTip",onfocus:"收件通信地址",oncorrect:"√"}).inputValidator({min:1,onerror:"通信地址不能为空,请确认"});

		//默认关闭对话框
		$('#dialog').dialog('close');
	});
	
	//保存数据
	function save(action){
	    if($.formValidator.pageIsValid()){
			var url ='<%=path%>/net/order.do?action='+action;
			document.forms[0].action=url;
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}
	
	//创建iframe框架(取件地址)
	function Addressiframe(){
        if ($("#addressdialog").length == 0){
	    	$("body").append(' <div id="addressdialog" title="取件地址信息列表"> '+
	    	'<iframe src="<%=path%>/archives/address.do?action=frame"  id = "addressframe" width="700" height="300"></iframe>'+
	    	'</div>	');  
	    	
	    	$("#addressdialog").dialog({
				bgiframe: true,
				autoOpen: false,
				width: 700,
				height: 300,
				modal: true,
				buttons: {
					'确认': function() {
					   if(addressframe.document.all.frmid.value!=""){
						   //操作处理
						   $("#paddressid").val(addressframe.document.all.frmid.value);
						   $("#pprovince").val(addressframe.document.all.frmprovince.value);
						   //alert($("#pprovince").val());
						   $("#lprovince").val(addressframe.document.all.frmprovincename.value);
						   $("#pcity").val(addressframe.document.all.frmcity.value);
						   $("#lcity").val(addressframe.document.all.frmcityname.value);
						   $("#pcounty").val(addressframe.document.all.frmcounty.value);
						   $("#lcounty").val(addressframe.document.all.frmcountyname.value);
						   $("#paddress").val(addressframe.document.all.frmaddress.value);
					   }else
					   {
					       alert('请选择一条记录');
					   }
					   $(this).dialog('close');
					},
					'取消': function() {
						$(this).dialog('close');
					}
				},
				close: function() {
					//allFields.val('').removeClass('ui-state-error');
				}
		   });
    	} 
    	//显示
		 $('#addressdialog').dialog('open');
	}
	
	
	
	
	
	//创建iframe框架(取件联系人)
	function Clientiframe(){
        if ($("#clientMsgdialog").length == 0){
	    	$("body").append(' <div id="clientMsgdialog" title="取件人信息列表"> '+
	    	'<iframe src="<%=path%>/archives/clientMsg.do?action=frame"  id = "clientMsgframe" width="700" height="300"></iframe>'+
	    	'</div>	');  
	    	
	    	$("#clientMsgdialog").dialog({
				bgiframe: true,
				autoOpen: false,
				width: 700,
				height: 300,
				modal: true,
				buttons: {
					'确认': function() {
					   if(clientMsgframe.document.all.frmid.value!=""){
						   //操作处理
						   //$("#pname").val(clientMsgframe.document.all.frmid.value);
						   $("#pname").val(clientMsgframe.document.all.frmname.value);
						   $("#pmobile").val(clientMsgframe.document.all.frmmobile.value);
						   $("#punit").val(clientMsgframe.document.all.frmunit.value);
						   $("#pareacode").val(clientMsgframe.document.all.frmareacode.value);
						   $("#ptel").val(clientMsgframe.document.all.frmtel.value);
					   }else
					   {
					       alert('请选择一条记录');
					   }
					   $(this).dialog('close');
					},
					'取消': function() {
						$(this).dialog('close');
					}
				},
				close: function() {
					//allFields.val('').removeClass('ui-state-error');
				}
		   });
    	} 
    	//显示
		 $('#clientMsgdialog').dialog('open');
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
//初始化信息(取件地址)
changeCombo('precprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
//var tmp=<s:property value="%{order.RECPROVINCE}"/>;
document.getElementById('precprovince').value=350000;
<s:if test="%{action=='update'}">
document.getElementById('precprovince').value=<s:property value="%{order.RECPROVINCE}"/>;
</s:if>
changeCombo('preccity','precprovince','cityvalue');
<s:if test="%{action=='update'}">
document.getElementById('preccity').value=<s:property value="%{order.RECCITY}"/>;
</s:if>
	</script>
	</body>
</html>