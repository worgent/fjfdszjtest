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
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
	</head>
	<body>	
	    <strong>详单打印</strong>
	    <div align="right">
	    <a href="/setup.exe" ><img style='vertical-align: middle' width=16 height=16 src='<%=path%>/images/down.gif' border=0 hspace=3>打印控件下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div id="f_bg">
			<div id="f_tabs">
				<ul>
					<li id="tab3" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultList('<%=path%>/net/print.do?action=listdetail',1,1);">全部</a>
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
		    loadDefaultList('<%=path%>/net/print.do?action=listdetail',1,1);
		    //验证	
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){jAlert(msg,'提示')},onsuccess:function(){
			if($('#checkdate').val()==1)
			{
				alert('确认保存');
				return true;
			}else
			{
				alert('请重新选择预约时间');
				return false;
			}
		}});
		
		$("#pname").formValidator({onfocus:"最多20字符",oncorrect:"√"}).inputValidator({min:1,max:20,onerror:"姓名非法"});
		$("#ptel").formValidator({onfocus:"电话号码或小灵通,例059512345678",oncorrect:"√"}).regexValidator({regexp:"^\\d{10,12}$",onerror:"电话号码格式不正确"});
		$("#pmobile").formValidator({onfocus:"11位手机号",oncorrect:"√"}).regexValidator({regexp:"mobile",datatype:"enum",onerror:"手机号不正确"});
		$("#paddress").formValidator({onfocus:"取件地址",oncorrect:"√"}).inputValidator({min:1,onerror:"取件地址不能为空"});
		$("#pmailtype").formValidator({onfocus:"邮件种类",oncorrect:"√"}).inputValidator({min:1,onerror:"邮件种类不能为空"});

		
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
								$('#pbookingtimeTip').show();
								$('#pbookingtimeTip').html("校验失败");
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
						}, 
						error : function(){
						     $('#pbookingtimeTip').show();
						     $('#pbookingtimeTip').html("服务器忙");
						     alert("服务器忙");
						}
					});	
		}})}).formValidator({onshow:"请输入",onfocus:"请输入",oncorrect:"√"});
	   	
	
		$("#porderingnum").formValidator({onfocus:"寄件数量",oncorrect:"√"}).regexValidator({regexp:"intege1",datatype:"enum",onerror:"寄件数量格式不正确"});
		$("#porderingweight").formValidator({onfocus:"物品重量",oncorrect:"√"}).regexValidator({regexp:"decmal4ex",datatype:"enum",onerror:"物品重量格式不正确"});
		$("#pclientremark").formValidator({onfocus:"客户要求",oncorrect:"√"});
		$("#precprovince").formValidator({tipid:"precaddressTip",onfocus:"收件通信地址",oncorrect:"√"});
		$("#preccity").formValidator({tipid:"precaddressTip",onfocus:"收件通信地址",oncorrect:"√"});

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
	 
</script>		    		
	</body>
</html>