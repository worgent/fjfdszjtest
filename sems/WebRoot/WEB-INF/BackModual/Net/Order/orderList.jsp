<%@page contentType="text/html; charset=utf-8"%>
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
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
		<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
	</head>
	<body>
<center>
<s:form method='POST' name='form1'  id="form1" enctype="multipart/form-data">
   <table width="95%" height="190" cellspacing=0 cellpadding="0" border="0" class="tableex">
   <tr class="trex">
   <td class="tdex"> 
       <strong>网上寄件</strong>
   </td>
   </tr>
   <tr class="trex">
    <td class="tdex">
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
   </td>
   </tr>
   <tr class="trex"> 
     <td align="left" class="tdex">
     <font color="red">必填</font>
     </td>
   </tr>

   <tr class="trex" align="left">
   <td class="tdex">
   <input type="button" onclick="javascript:ClientList('<%=path%>')" value="联系人" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
   <s:textfield id="pname"  name="search.pname" value="%{order.NAME}" size="4"></s:textfield>
   &nbsp;手机:
   <s:textfield id="pmobile"  name="search.pmobile" value="%{order.MOBILE}" size="9"></s:textfield>
   &nbsp;固定电话:
   <s:textfield id="ptel"  name="search.ptel" value="%{order.TEL}" size="9"  title="(例:059512345678)" ></s:textfield>
   &nbsp;单位名称:
   <s:textfield id="punit"  name="search.punit" value="%{order.UNIT}" size="15"  ></s:textfield>
     <div id="pnameTip" style="display: inline "></div>
      &nbsp;<div id="pmobileTip" style="display: inline "></div>
      &nbsp;<div id="ptelTip" style="display: inline "></div>
      &nbsp;<div id="punitTip" style="display: inline "></div>
   </td>
   </tr>  
<tr id="clientTr" style="display:none">
						<td colspan="3" style="border-width : 0px;" ><div id="clientList"></div></td>
</tr>
   <tr class="trex" align="left">
   <td class="tdex">
   <input type="button" onclick="javascript:AddressList('<%=path%>')" value="取件地址" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'" />
  <s:textfield id="lprovince"  name="search.lprovince" value="%{order.PROVINCENAME}" readonly="true" size="9"></s:textfield>
  <s:textfield id="lcity"  name="search.lcity" value="%{order.CITYNAME}" readonly="true" size="9"></s:textfield> 
  <s:textfield id="lcounty"  name="search.lcounty" value="%{order.COUNTYNAME}" readonly="true" size="9"></s:textfield>
  <s:textfield id="paddress"  name="search.paddress" value="%{order.ADDRESS}" readonly="true" size="40"></s:textfield>
 <s:hidden value="%{order.ADDRESSID}" name="search.paddressid" id="paddressid"></s:hidden>
        <s:hidden value="%{order.PROVINCE}" name="search.pprovince" id="pprovince"></s:hidden>
        <s:hidden value="%{order.CITY}" name="search.pcity" id="pcity" ></s:hidden>
        <s:hidden value="%{order.COUNTY}" name="search.pcounty" id="pcounty" ></s:hidden>
  <div id="paddressTip"></div>   
   </td>
   </tr>
   <tr id="trId" style="display:none">
						<td colspan="3" style="border-width : 0px;" ><div id="addressList"></div></td>
					</tr>
   <tr class="trex">
   <td class="tdex">邮件种类:
   <select name="search.pmailtype" id="pmailtype">
 		<option value="-1">请选择</option>
 		<option value="0" <s:if test="%{order.MAILTYPE==0}">selected</s:if>>物品型</option>
 		<option value="1" <s:if test="%{order.MAILTYPE==1}">selected</s:if>>文件型</option>
   </select>
   <div id="pmailtypeTip" style="display: inline"></div>
   </td>
   </tr>
   
   <tr class="trex"> 
     <td align="left" class="tdex">
     <font color="red">选填</font>
     </td>
   </tr>
   <tr class="trex">
	    <td class="tdex">
	    揽收要求:
	   <s:textfield  id="pclientremark"  name="search.pclientremark" value="%{order.CLIENTREMARK}"  cssStyle="width:80%"></s:textfield>
	   <div id="pclientremarkTip"  ></div>
	    </td>
   </tr>
   <tr class="trex"> 
   <td class="tdex">预约时间:
   <s:textfield id="pbookingtime"  name="search.pbookingtime" value="%{order.BOOKINGTIME}" readonly="true"></s:textfield>(注意揽收时服务时间且需提前40分钟,不选择为立即上门揽收)
   <div id="pbookingtimeTip"  ></div>
   </td>
   </tr>   
   <tr class="trex"> 
   <td class="tdex">寄件数量:
   <s:textfield id="porderingnum"  name="search.porderingnum" value="%{order.ORDERINGNUM}"></s:textfield>
   &nbsp;物品重量:
   <s:textfield id="porderingweight"  name="search.porderingweight" value="%{order.ORDERINGWEIGHT}"></s:textfield>(千克)(注:所有邮件总重量)
     <div id="porderingnumTip" style="display: inline " ></div>
     &nbsp; <div id="porderingweightTip" style="display: inline " ></div> 
   </td>
   </tr>
   <tr class="trex"> 
   <td align="left" class="tdex">
     目的地&nbsp;&nbsp;&nbsp;:                    
                       <select id="precprovince" name="search.precprovince" onchange="changeCombo('preccity','precprovince','cityvalue')">
						</select>省
                       <select id="preccity" name="search.preccity">
						</select>市
  <div id="precaddressTip"  ></div>						
   </td>
   </tr>
   
      
   <tr class="trex">
   <td style="text-align:center;" class="tdex">
      <s:if test="%{action=='insert'}">
      <s:hidden value="1" name="checkdate" id="checkdate"></s:hidden>
      </s:if>
      <s:else>
      <s:hidden value="0" name="checkdate" id="checkdate"></s:hidden>
      </s:else>
      <s:hidden value="%{order.ID}" name="search.pid" id="pid"></s:hidden>
      <s:if test="%{order.ORDERINGVALUE.substring(7,8)==1}">
			<input type="button" id="btnupdate" name="btnupdate"  onclick="javascript:save('<s:property value="%{action}"/>');"  value="保 存"  alt="保存订单" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'"  />
	  </s:if>
	  <s:if test="%{order.ORDERINGVALUE.substring(8,9)==1}">
 	        &nbsp; &nbsp; &nbsp;<input type="button" id="btnupdateauding" name="btnupdateauding"  onclick="javascript:save('clientsaveadd');"  value="保存寄件" alt="保存订单并通知EMS上门取件" class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'"  />
 	  </s:if>
      &nbsp; &nbsp; &nbsp;<input type="reset"  id="btnreset"  name="btnreset" value="重置"  class="outStyle" onmouseover="this.className='overStyle'"  onmouseout="this.className='outStyle'"  /></td>
   </tr>
 </table>
</s:form>
</center>	
		<div id="f_bg">
			<div id="f_tabs">
				<ul>
					<li id="tab1" class="f_tabClass1">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail','',1,11);">全部</a>
					</li>
					<li id="tab2" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','等待寄件',2,11);">等待寄件</a>
					</li>
					<li id="tab3" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','等待收件',3,11);">等待收件</a>
					</li>
					<li id="tab4" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','等待处理',4,11);">等待处理</a>
					</li>
					<li id="tab5" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户催揽',5,11);">客户催揽</a>
					</li>
					<li id="tab6" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户撤单',6,11);">客户撤单</a>
					</li>
					<li id="tab7" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','超范围',7,11);">超范围</a>
					</li>
					<li id="tab8" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','无法联系客户',8,11);">无法联系客户</a>
					</li>
					<li id="tab9" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户取消',9,11);">客户取消</a>
					</li>
					<li id="tab10" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','客户要求延时',10,11);">客户要求延时</a>
					</li>
					<li id="tab11" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultListex('<%=path%>/net/order.do?action=listdetail&search.porderingstate=','揽收成功',11,11);">揽收成功</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="f_main">
			<div id="defaultlist"></div>
		</div>
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>		
<script language="javascript" type="text/javascript">	
		    //加载默认页面    
		    $(document).ready(function(){
		    //默认加载页面
		    loadDefaultListex('<%=path%>/net/order.do?action=listdetail','',1,11);
		    //验证
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){jAlert(msg,'提示')},onsuccess:function(){
			if($('#checkdate').val()==1)
			{
				return true;
			}else
			{
				alert('请重新选择预约时间');
				return false;
			}
		}});
		
		
		$("#pname").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).inputValidator({min:1,max:20,onerror:def_Errorname});
		$("#ptel").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).regexValidator({regexp:"^\\d{10,12}$",onerror:def_Errortel});
        $("#pmobile").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).regexValidator({regexp:"^(13|15|18)[0-9]{9}$|^\\d{10,12}$",onerror:def_Errormobile});
		$("#paddress").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).inputValidator({min:1,onerror:"请输入取件地址"});
		$("#pmailtype").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).inputValidator({min:1,onerror:"请选择邮件种类"});

		
		$("#pbookingtime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'%y-%M-\#{%d+1}',oncleared:function(){$('#checkdate').val('1');$(this).blur();},onpicked:function(){
					$(this).blur();
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
							    stime=rowSet.item(0).selectSingleNode("stime").text;
							    etime=rowSet.item(0).selectSingleNode("etime").text;
								$('#pbookingtimeTip').show();
								$('#pbookingtimeTip').html("已超过揽收服务时间("+stime+"~"+etime+"),请重新输入!");
								$('#pbookingtimeTip').removeClass();
								$('#pbookingtimeTip').addClass("onError");
				                return false;
							}
				            else
							{
							    $('#pbookingtimeTip').hide();
				                $('#checkdate').val('1');
				                return true;
							}
						},
						complete : function(){
							$("#btnupdate").attr({"disabled":false});
							$("#btnupdateauding").attr({"disabled":false});
						}, 
						beforeSend : function(xhr){
							$("#btnupdate").attr({"disabled":true});
							$("#btnupdateauding").attr({"disabled":true});
							$('#checkdate').val('0');
							$('#pbookingtimeTip').show();
							$('#pbookingtimeTip').html("正在校验");
							$('#pbookingtimeTip').removeClass();
							$('#pbookingtimeTip').addClass("onError");
						}, 
						error : function(){
						     $('#pbookingtimeTip').show();
						     $('#pbookingtimeTip').html("服务器忙");
						     $('#pbookingtimeTip').removeClass();
							 $('#pbookingtimeTip').addClass("onError");
						     alert("服务器忙");
						}
					});	
		}})}).formValidator({onshow:" ",onfocus:" ",oncorrect:" "});
	   	
	
		$("#porderingnum").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).regexValidator({regexp:"intege1",datatype:"enum",onerror:"请输入寄件数量"});
		$("#porderingweight").formValidator({onshow:" ",onfocus:" ",oncorrect:" "}).regexValidator({regexp:"decmal4ex",datatype:"enum",onerror:"请输入物品重量"});
		$("#pclientremark").formValidator({onshow:" ",onfocus:" ",oncorrect:" "});
		$("#precprovince").formValidator({onshow:" ",tipid:"precaddressTip",onfocus:" ",oncorrect:" "});
		$("#preccity").formValidator({onshow:" ",tipid:"precaddressTip",onfocus:" ",oncorrect:" "});

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
					       jAlert('请选择一条记录','提示');
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
						   //$("#pareacode").val(clientMsgframe.document.all.frmareacode.value);
						   $("#ptel").val(clientMsgframe.document.all.frmtel.value);
					   }else
					   {
					       jAlert('请选择一条记录','提示');
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
function changeComboex(chCombo,srcCombo,action)
{
	    var parmid='';
	    if(srcCombo!='')
	    {
	      parmid=document.getElementById(srcCombo).value
	    }
	    
		 $.ajax({
			 mode : "abort",
			 type : "get", 
		     url:"<%=path%>/archives/area.do",
		     data : "action="+action+"&search.pid="+parmid+"&search.rnd="+Math.random(),
		     dataType:"xml",
		     success:function(xml)
		     {
		          document.getElementById(chCombo).length=0;
		          document.getElementById(chCombo).options[0]=new Option('请选择','0');    
		          $(xml).find("ROOT>NODE").each(
			            function(i){
				            var id=$(this).find("THE_CODE").text();
				            var name=$(this).find("THE_NAME").text(); 
				            document.getElementById(chCombo).options[i+1]=new Option(name,id);    
		            	}
		         )
		     }
	     });
}

//初始化信息(取件地址)
changeCombo('precprovince','','provincevalue');
//document.getElementById('pprovince').options[12].selected;
//默认福建省
//var tmp=<s:property value="%{order.RECPROVINCE}"/>;
document.getElementById('precprovince').value=350000;
<s:if test="%{order.RECPROVINCE==''}">
</s:if>
<s:else>
document.getElementById('precprovince').value=<s:property value="%{order.RECPROVINCE}"/>;
</s:else>
changeCombo('preccity','precprovince','cityvalue');
<s:if test="%{order.RECCITY==''}">
</s:if>		
<s:else>
document.getElementById('preccity').value=<s:property value="%{order.RECCITY}"/>;
</s:else>

</script>		    		
	</body>
</html>