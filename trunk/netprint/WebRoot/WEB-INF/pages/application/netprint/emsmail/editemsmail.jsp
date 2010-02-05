<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * EMS邮件管理
 	 * @author zhengmh 
--%><style type="text/css">
<!--
body {
	background-image: url(../../../../../images/mailformat/ems.jpg);
	background-repeat: no-repeat;
}
#Layer1 {
	position:absolute;
	width:84px;
	height:21px;
	z-index:1;
	left: 133px;
	top: 93px;
}
#Layer2 {
	position:absolute;
	width:26px;
	height:22px;
	z-index:1;
	left: 268px;
	top: 92px;
}
#Layer3 {
	position:absolute;
	width:20px;
	height:22px;
	z-index:2;
	left: 304px;
	top: 92px;
}
#Layer4 {
	position:absolute;
	width:21px;
	height:21px;
	z-index:1;
	left: 333px;
	top: 93px;
}
#Layer5 {
	position:absolute;
	width:21px;
	height:21px;
	z-index:2;
	left: 366px;
	top: 93px;
}
#Layer6 {
	position:absolute;
	width:84px;
	height:25px;
	z-index:2;
	left: 133px;
	top: 119px;
}
#Layer7 {
	position:absolute;
	width:109px;
	height:23px;
	z-index:3;
	left: 297px;
	top: 120px;
}
#Layer8 {
	position:absolute;
	width:217px;
	height:24px;
	z-index:4;
	left: 187px;
	top: 147px;
}
#Layer9 {
	position:absolute;
	width:330px;
	height:44px;
	z-index:5;
	left: 79px;
	top: 194px;
}
#Layer10 {
	position:absolute;
	width:82px;
	height:16px;
	z-index:6;
	left: 132px;
	top: 241px;
}
#Layer11 {
	position:absolute;
	width:93px;
	height:17px;
	z-index:7;
	left: 316px;
	top: 241px;
}
#Layer12 {
	position:absolute;
	width:205px;
	height:47px;
	z-index:8;
	left: 77px;
	top: 309px;
}
#Layer13 {
	position:absolute;
	width:129px;
	height:50px;
	z-index:9;
	left: 286px;
	top: 307px;
}
#Layer14 {
	position:absolute;
	width:250px;
	height:27px;
	z-index:10;
	left: 166px;
	top: 385px;
}
#Layer15 {
	position:absolute;
	width:103px;
	height:22px;
	z-index:11;
	left: 489px;
	top: 120px;
}
#Layer16 {
	position:absolute;
	width:122px;
	height:21px;
	z-index:12;
	left: 658px;
	top: 120px;
}
#Layer17 {
	position:absolute;
	width:243px;
	height:25px;
	z-index:13;
	left: 538px;
	top: 145px;
}
#Layer18 {
	position:absolute;
	width:350px;
	height:44px;
	z-index:14;
	left: 433px;
	top: 194px;
}
#Layer19 {
	position:absolute;
	width:81px;
	height:16px;
	z-index:15;
	left: 480px;
	top: 241px;
}
#Layer20 {
	position:absolute;
	width:108px;
	height:17px;
	z-index:16;
	left: 672px;
	top: 241px;
}
#Layer21 {
	position:absolute;
	width:73px;
	height:18px;
	z-index:17;
	left: 454px;
	top: 263px;
}
#Layer22 {
	position:absolute;
	width:22px;
	height:20px;
	z-index:18;
	left: 620px;
	top: 263px;
}
#Layer23 {
	position:absolute;
	width:29px;
	height:19px;
	z-index:19;
	left: 662px;
	top: 264px;
}
#Layer24 {
	position:absolute;
	width:27px;
	height:18px;
	z-index:20;
	left: 704px;
	top: 264px;
}
#Layer25 {
	position:absolute;
	width:26px;
	height:17px;
	z-index:21;
	left: 744px;
	top: 264px;
}
#Layer26 {
	position:absolute;
	width:109px;
	height:15px;
	z-index:22;
	left: 453px;
	top: 287px;
}
#Layer27 {
	position:absolute;
	width:152px;
	height:15px;
	z-index:23;
	left: 632px;
	top: 289px;
}
#Layer28 {
	position:absolute;
	width:111px;
	height:20px;
	z-index:24;
	left: 492px;
	top: 307px;
}
#Layer29 {
	position:absolute;
	width:96px;
	height:20px;
	z-index:25;
	left: 689px;
	top: 308px;
}
#Layer30 {
	position:absolute;
	width:240px;
	height:23px;
	z-index:26;
	left: 545px;
	top: 334px;
}
#Layer31 {
	position:absolute;
	width:39px;
	height:17px;
	z-index:27;
	left: 474px;
	top: 362px;
}
#Layer32 {
	position:absolute;
	width:30px;
	height:17px;
	z-index:28;
	left: 535px;
	top: 363px;
}
#Layer33 {
	position:absolute;
	width:29px;
	height:16px;
	z-index:29;
	left: 586px;
	top: 363px;
}
#Layer34 {
	position:absolute;
	width:32px;
	height:17px;
	z-index:30;
	left: 630px;
	top: 362px;
}
#Layer35 {
	position:absolute;
	width:294px;
	height:27px;
	z-index:31;
	left: 483px;
	top: 384px;
}
#Layer36 {
	position:absolute;
	width:105px;
	height:40px;
	z-index:32;
	left: 889px;
	top: 28px;
}
#Layer37 {
	position:absolute;
	width:105px;
	height:40px;
	z-index:33;
	left: 890px;
	top: 85px;
}
#Layer38 {
	position:absolute;
	width:251px;
	height:19px;
	z-index:34;
	left: 495px;
	top: 90px;
}
#sendprovinceDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:35;
	left: 144px;
	top: 174px;
}
#sendcityDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:36;
	left: 236px;
	top: 175px;
}
#sendcountyDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:37;
	left: 326px;
	top: 175px;
}
#recprovinceDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:38;
	left: 507px;
	top: 174px;
}
#reccityDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:39;
	left: 605px;
	top: 175px;
}
#reccountyDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:40;
	left: 702px;
	top: 174px;
}
#Layer39 {
	position:absolute;
	width:105px;
	height:40px;
	z-index:41;
	left: 890px;
	top: 146px;
}
#Layer40 {
	position:absolute;
	width:105px;
	height:40px;
	z-index:42;
	left: 892px;
	top: 215px;
}
#Layer41 {
	position:absolute;
	width:105px;
	height:40px;
	z-index:43;
	left: 896px;
	top: 276px;
}
#Layer42 {
	position:absolute;
	width:104px;
	height:43px;
	z-index:44;
	left: 897px;
	top: 341px;
}
-->
</style>



<form methd='POST' name='form1' action='/netprint/emsmail/emsmail.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
<div id="Layer6"><!--寄件人姓名-->
<tt:TextField name="search.psendname" value="search.sendname" width="84" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'  onchange="geteqname()"/>
  </div>
	  <div id="Layer7"><!--发件人电话-->
<tt:TextField name="search.psendtel" value="search.sendtel" width="109" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' onblur="ajaxGetClientMsg(1,Ext.get('search.psendname').dom.value,Ext.get('search.psendtel').dom.value)"/>
	  </div>
	  <div id="Layer8"><!--发件人单位名称-->
<tt:TextField name="search.psendunit" value="search.sendunit" width="217" cssClass="check" verify='string'  shade='true' requiredColor='#ffffff'/>	      </div>
	  <div id="Layer9"><!--发件人地址-->
	  <tt:TextArea name="search.psendaddress" value="search.sendaddress" width="330" height="44" required='true' msg='发件人地址！'/>
<!--<tt:TextField name="search.psendaddress" value="search.sendaddress" width="330" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	 -->
      </div>
	  <div id="Layer10"><!--发件人代码-->
<tt:TextField name="search.psendcode" value="search.sendcode" width="82" height="16" cssClass="check" verify='string' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer11"><!--发件人邮政编码-->
<tt:TextField name="search.psendpost" value="search.sendpost" width="93" height="16" cssClass="check" verify='int+' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer12"><!--内件品名-->
<tt:TextArea name="search.psendgoodsname" value="search.sendgoodsname" width="205" height="47" required='true' msg='内件品名！'/>
<!--<tt:TextField name="search.psendgoodsname" value="search.sendgoodsname" width="205" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>-->
  </div>
	  <div id="Layer13"><!--物品数据-->
<tt:TextField name="search.psendcount" value="search.sendcount" width="129" cssClass="check" verify='int' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <p>&nbsp;</p>
	  <div id="Layer36"><!--保存-->
	  </div>
	  <div id="Layer37"><!--重置-->
	  </div>
	  
	  <div id="Layer39"><!--保存客户信息-->
	  </div>
	  <div id="Layer40"><!--寄件人信息-->
	  </div>
	  <div id="Layer41"><!--发件人信息-->
	  </div>
	  <div id="Layer42"><!--地地识别按钮--></div>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <div id="Layer14"><!--交寄人签名-->
<tt:TextField name="search.psendsign" value="search.sendsign" width="250" cssClass="check" verify='string'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer15"><!--收件人姓名-->
<tt:TextField name="search.precname" value="search.recname" width="103" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer16"><!--收件人电话-->
<tt:TextField name="search.prectel" value="search.rectel" width="122" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' onblur="ajaxGetClientMsg(0,Ext.get('search.precname').dom.value,Ext.get('search.prectel').dom.value)"/>
  </div>
	  <div id="Layer17"><!--收件人单位名称-->
<tt:TextField name="search.precunit" value="search.recunit" width="243" cssClass="check" verify='string'  shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer18"><!--收件人地址-->
	  <tt:TextArea name="search.precaddress" value="search.recaddress" width="350" height="44" required='true' msg='发件人地址！'/>
<!--<tt:TextField name="search.precaddress" value="search.recaddress" width="350" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>-->
  </div>
	  <div id="Layer19"><!--收件人代码-->
<tt:TextField name="search.preccode" value="search.reccode" width="81" height="17" verify='string'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer20"><!--收件人邮政编码-->
<tt:TextField name="search.precpost" value="search.recpost" width="108" height="17"  verify='int+'  shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer30"><!--收件人签名-->
<tt:TextField name="search.precsign" value="search.recsign" width="240" cssClass="check" verify='string'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer31"><!--收件人收寄年-->
<tt:TextField name="search.precdateyear" value="search.recdateyear" width="39" height="17" cssClass="check" verify='int+'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer32"><!--收件人收寄月-->
<tt:TextField name="search.precdatemonth" value="search.recdatemonth" width="30" height="17" cssClass="check" verify='int+'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer33"><!--收件人收寄天-->
<tt:TextField name="search.precdateday" value="search.recdateday" width="29" height="17" cssClass="check" verify='int+'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer34"><!--收件人收寄时-->
<tt:TextField name="search.precdatehour" value="search.recdatehour" width="32" height="17" cssClass="check" verify='int+'  shade='true' requiredColor='#ffffff'/>
  </div>
	  <p>&nbsp;</p>
	  <div id="Layer35"><!--备注-->
<tt:TextField name="search.premark" value="search.remark" width="294" cssClass="check" verify='string'  shade='true' requiredColor='#ffffff'/>
  </div>
  
<div id="Layer38"><!--邮件号-->
<tt:TextField name="search.pmailno" value="search.mailno" width="251" cssClass="check" height="19" verify="string" shade="true" requiredColor='#ffffff'/>
</div>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	<input type='hidden' name='search.pid' value='<ww:property value="search.id"/>' />
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
</form>



<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;
			
	  </td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>
<script type="text/javascript" src="/js/TreeField.js"></script>
<script language="javascript">

    /*动态触发取得客户信息的数据*/
    function ajaxGetClientMsg(pamtype,pamname,pamtel){
    	var url = '/netprint/clientmsg/ajaxClientMsg.shtml?search.pbill_sort='+pamtype+'&search.pname='+pamname+'&search.ptel='+pamtel;
		try{
			var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
			oXMLDom.async = false ;
			oXMLDom.load(url); 
			var root;
			if (oXMLDom.parseError.errorCode != 0) {
				var myErr = oXMLDom.parseError;
				return;
			} else {
				root = oXMLDom.documentElement;
			}
			if (null != root){
				var rowSet = root.selectNodes("//ClientMsg");
				if (rowSet.length > 0){					
								if(pamtype==0)//收
				            	{
									//收件人的信息
					            	document.all("search.precname").value = rowSet.item(0).selectSingleNode("name").text;
					            	document.all("search.prectel").value = rowSet.item(0).selectSingleNode("tel").text;
					            	document.all("search.precunit").value = rowSet.item(0).selectSingleNode("unit").text;
					            	document.all("search.precaddress").value = rowSet.item(0).selectSingleNode("address").text;
					            	document.all("search.preccode").value = rowSet.item(0).selectSingleNode("code").text;
					            	document.all("search.precpost").value = rowSet.item(0).selectSingleNode("post").text;	            	
				            	}
				            	else if(pamtype==1)//寄
				            	{
									//寄件人的信息
					            	document.all("search.psendname").value = rowSet.item(0).selectSingleNode("name").text;
					            	document.all("search.psendtel").value = rowSet.item(0).selectSingleNode("tel").text;
					            	document.all("search.psendunit").value = rowSet.item(0).selectSingleNode("unit").text;
					            	document.all("search.psendaddress").value = rowSet.item(0).selectSingleNode("address").text;
					            	document.all("search.psendcode").value = rowSet.item(0).selectSingleNode("code").text;
					            	document.all("search.psendpost").value = rowSet.item(0).selectSingleNode("post").text;
					            	
					            	//交寄人签名
					            	document.all("search.psendsign").value = rowSet.item(0).selectSingleNode("name").text;
				            	}
					
				}else{
					alert('获取客户信息时出错，请重试！');
				}
			}
		}catch(e){ 
			alert(e);
		}
    }





//同步name的值
function geteqname(){
	$('search.psendsign').value=$('search.psendname').value;
}

	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid()) {
	        	Ext.Msg.show({
				 	title:'再确认一下',
				 	modal : false,
				 	msg: '您确定资料正确吗?',
				 	buttons: Ext.Msg.OKCANCEL,
				 	fn: function(btn, text){
						if (btn == 'ok'){
					 		document.form1.submit();
					 	} 
				 	},
				 	animEl: 'buttonTD1'
			 	});
        	} else {
        		Ext.Msg.show({
 					title:'信息',
					msg: '请填写完整后再提交!',
					modal : true,
					buttons: Ext.Msg.OK
 				});
        	}
        }
    }).render(document.all.Layer36);
			
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.Layer37);
	
	<tt:authority value="AddEmsMailClientMsg">	
	if(document.all("actionType").value!='update')
	{
		new Ext.Button({
	        text: '保存(客户)',
	        handler: function(){
	        	if (fm2.isValid()) {
		        	Ext.Msg.show({
					 	title:'再确认一下',
					 	modal : false,
					 	msg: '您确定资料正确吗?',
					 	buttons: Ext.Msg.OKCANCEL,
					 	fn: function(btn, text){
							if (btn == 'ok'){
							    document.all("actionType").value="insertclient";
						 		document.form1.submit();
						 	} 
					 	},
					 	animEl: 'buttonTD1'
				 	});
	        	} else {
	        		Ext.Msg.show({
	 					title:'信息',
						msg: '请填写完整后再提交!',
						modal : true,
						buttons: Ext.Msg.OK
	 				});
	        	}
	        }
	    }).render(document.all.Layer39);
	}
	</tt:authority>
	
	new Ext.Button({
        text: '寄件人信息',
        handler: function(){
 			showSearchPlan(1);//收是1
        }
    }).render(document.all.Layer40);
	

	new Ext.Button({
        text: '收件人信息',
        handler: function(){
  			showSearchPlan(0);//收是0
        }
    }).render(document.all.Layer41);
	
    //邮件信息的寄件人与收件人信息
        var searchPlan = new Ext.Window();
    	function showSearchPlan(sort){
		searchPlan.hidden = true;
		if (searchPlan.hidden){
			searchPlan = new Ext.Window({
		        title: '查询客户信息',
		        width: 800,
		       	height:400,
		        layout: 'fit',
		        plain:true,
		        bodyStyle:'padding:5px;',
		        buttonAlign:'center',
		        items:[{
	                region:'center',
	                layout:'column',
	                baseCls:'x-plain',
	                autoScroll:true,
	                items:[{
			        	columnWidth:.7,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    layout: 'form',
	                    labelWidth:120,
			        	items:[{
			        		xtype: 'textfield',
				            fieldLabel: '搜索客户名称',
				            name: 'clientname',
				            width:300  // 设置宽度，百分比的需加‘号
			        	}]
			        },{
			        	columnWidth:.3,
	                    baseCls:'x-plain',
	                    bodyStyle:'margin:0px 0px 0px 0px',
			        	buttons: [{
				            text: '查询',
				            width:50,
				            handler: function(){
				                if(sort==0)//收
				            	document.all.ClientMsgIframe.src="/netprint/clientmsg/recclientmsg.shtml?actionType=exsearch&search.pname="+document.all.clientname.value+"&search.pbill_sort="+sort;
				            	else if (sort==1)//寄
				            	document.all.ClientMsgIframe.src="/netprint/clientmsg/sendclientmsg.shtml?actionType=exsearch&search.pname="+document.all.clientname.value+"&search.pbill_sort="+sort;
				            	
				            }
				        },{
				            text: '确定',
				            width:50,
				            handler: function(){
				            	if (document.all.ClientMsgIframe.src == ''){
				            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先查询!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
				            		return;
				            	}
				            	if (document.ClientMsgIframe.frmname.value == ''){
				            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择客户信息!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
				            		return;
				            	}
				            	if(sort==0)//收
				            	{
									//收件人的信息
					            	document.all("search.precname").value = document.ClientMsgIframe.frmname.value;
					            	document.all("search.prectel").value = document.ClientMsgIframe.frmtel.value;
					            	document.all("search.precunit").value = document.ClientMsgIframe.frmunit.value;
					            	document.all("search.precaddress").value = document.ClientMsgIframe.frmaddress.value;
					            	document.all("search.preccode").value = document.ClientMsgIframe.frmcode.value;
					            	document.all("search.precpost").value = document.ClientMsgIframe.frmpost.value;				            	
				            	}
				            	else if(sort==1)//寄
				            	{
									//寄件人的信息
					            	document.all("search.psendname").value = document.ClientMsgIframe.frmname.value;
					            	document.all("search.psendtel").value = document.ClientMsgIframe.frmtel.value;
					            	document.all("search.psendunit").value = document.ClientMsgIframe.frmunit.value;
					            	document.all("search.psendaddress").value = document.ClientMsgIframe.frmaddress.value;
					            	document.all("search.psendcode").value = document.ClientMsgIframe.frmcode.value;
					            	document.all("search.psendpost").value = document.ClientMsgIframe.frmpost.value;
					            	
					            	//交寄人签名
					            	document.all("search.psendsign").value = document.ClientMsgIframe.frmname.value;
				            	}
				            	searchPlan.close();
				            }
				        }]
			        },{
			        	columnWidth:1,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px',
	                    items:[{
	                    	width:"100%",
	                    	height:"100%",
	                        html: "<iframe id='ClientMsgIframe' name='ClientMsgIframe' src=''width='100%' height='285' frameborder=0/>"
	                    }]
			        }]
		        }]
		    });
	    }
		searchPlan.show();
	}
	
	
	
	new Ext.Button({
        text: '地址识别',
        handler: function(){
  			showAddress();//收是0
        }
    }).render(document.all.Layer42);
	
	    //邮件信息的寄件人与收件人信息
        var AddressPlan = new Ext.Window();
        //var searchWindow = new  Ext.form.FormPanel();
        
        /*
        function showAddress()
        {
searchWindow = new Ext.form.FormPanel({
		labelWidth: 45,
		 width: 500,
				       	height:300,
				       	layout: 'fit',
		frame:false,
		bodyStyle:'padding:5px 5px 0',
		width: 286,
		items: [{
				xtype:'textfield',
				id: 'searchCriteria',
				fieldLabel: 'Search',
				width: 284,
				minLength: 1
			},
			{
				xtype:'fieldset',
				title: 'Search In',
				checkboxToggle: false,
				autoHeight:true,
				items: [{
					layout:'column',
					labelWidth: 0,
					border:false,
					items:[{
						columnWidth:0.35,
						layout: 'form',
						labelSeparator: ' ',
						border:false,
						items: [{
								xtype:'radio',
								id: 'ts1',
								boxLabel: 'Thread Title',
								name: 'searchtype',
								inputValue: 'Thread Title',
								checked : true 
							}]
						},
						{
						columnWidth:0.46,
						layout: 'form',
						labelSeparator: ' ',
						border:false,
						items: [{
								xtype:'radio',
								boxLabel: 'Message Contents',
								name: 'searchtype',
								inputValue: 'Message Contents'
							}]
						},
						{
						columnWidth:0.19,
						layout: 'form',
						labelSeparator: ' ',
						border:false,
						items: [{
								xtype:'radio',
								boxLabel: 'Both',
								name: 'searchtype',
								inputValue: 'Both'
							}]
						}]
					}]
				},
				{
					layout:'column',
					border:false,
					items:[{
							columnWidth:0.35,
							layout: 'form',
							labelWidth: 30,
							border:false,
							items: [{
								xtype:'textfield',
								id: 'userFilter',
								fieldLabel: 'User',
								width: 80
								}]
						},
						{
							columnWidth:0.65,
							layout: 'form',
							labelWidth: 60,
							border:false,
							items: [{
								xtype:'combo',
								id: 'channelFilter',
								fieldLabel: '&nbsp;&nbsp;Channel',
								width: 152
								}]
						}]
				}
			],
			buttons: [{
				text: 'Search',
				handler: function(){
				var component = Ext.ComponentMgr.get('searchCriteria');
				var searchCriteria;
				if ((!component.validate()) ||
				    ((searchCriteria = component.getValue()) == ''))
				{
					component.markInvalid('');
					return;
				}
				component = Ext.ComponentMgr.get('userFilter');
				if (!component.validate())
				{
					component.markInvalid('');
					return;
				}
				var userFilter = component.getValue();
				var searchLocation = Ext.ComponentMgr.get('ts1').getGroupValue();
				var channel = Ext.ComponentMgr.get('channelFilter').getValue();

				//Ext.Ajax.request( ....... );

				searchWindow.hide();
			}
		},{
			text: 'Cancel',
			handler: function(){
				searchWindow.hide();
			}
		}]
	});
		    searchWindow.show();
        }
        
        
        
        
       	
					        	{
					        	    layout:'column',
					        		items:[
					        		    new Ext.form.Radio({fieldLabel: '横向Radio Group',boxLabel:'opt1', name:'opt1'}),new Ext.form.Radio({boxLabel:'opt2', name:'opt1'}),new Ext.form.Radio({boxLabel:'opt2', name:'opt1'})
					        		  
					        		    ,
									  {
									  xtype: 'radio',
								            checked: true,
								            fieldLabel: 'Favorite Color',
								            boxLabel: 'Red',
								            name: 'favcolor',
								            inputValue: 'red'
								        },{
								        xtype: 'radio',
								            fieldLabel: '',
								            labelSeparator: '',
								            boxLabel: 'Blue',
								            name: 'favcolor',
								            inputValue: 'blue'
								        },			        	
											
					        		    { 
					        		     layout: 'form', 
					        		     items: [new Ext.form.Radio({fieldLabel: '横向Radio Group',boxLabel:'opt1', name:'opt1'}),new Ext.form.Radio({boxLabel:'opt2', name:'opt1'}),new Ext.form.Radio({boxLabel:'opt2', name:'opt1'})]
					        		    }
					        		   	*/		        		    
					        		    /*ext3.0支持
					        		    RadioBoxField = new Ext.form.RadioGroup({ 
										id:'InputRadio', 
										fieldLabel: '单选框', 
										columns: 1, 
										vertical: true, 
										items: [ 
										{boxLabel: '单选1', name: 'rb001', inputValue: 1}, 
										{boxLabel: '单选2', name: 'rb002', inputValue: 2, checked: true}, 
										{boxLabel: '单选3', name: 'rb003', inputValue: 3}, 
										{boxLabel: '单选4', name: 'rb004', inputValue: 4}, 
										{boxLabel: '单选5', name: 'rb005', inputValue: 5} 
										] 
										});
					        		    {    
					        		      layout: 'form', 
					        		      items: [new Ext.form.Radio({fieldLabel: '横向Radio Group',boxLabel:'opt1', value : '1',   name:'opt1'})]
					        		      },
					        		      {items: [new Ext.form.Radio({boxLabel:'opt3', value : '1',  name:'opt1'})]}
					        		  
        */
        
    	function showAddress(){
			AddressPlan.hidden = true;
			if (AddressPlan.hidden){
				AddressPlan = new Ext.Window({
				        title: '地址识别',
				        width: 500,
				       	height:300,
				        layout: 'fit',
				        plain:true,
				        bodyStyle:'padding:5px;',
				        buttonAlign:'left',
				        items:[{
			                region:'center',
			                baseCls:'x-plain',
			                autoScroll:true,
			                items:[{
			                    baseCls:'x-plain',
			                    bodyStyle:'padding:5px 0 5px 5px',
			                    layout: 'form',
					        	items:[{
					        		xtype: 'textarea',
						            fieldLabel: '需要识别的内容',
						            name: 'addresscontent',
						            id:'addresscontent',
						            width:250  // 设置宽度，百分比的需加‘号
					        	}]
					        },{
			                    baseCls:'x-plain',
			                    bodyStyle:'margin:0px 0px 0px 0px',
			                    layout: 'column',
					        	buttons:[{
						            text: '寄件人',
						            width:50,
						            handler: function(){
						            	if (document.all.addresscontent.value == ''){
						            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先写需识别的内容!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
						            		return;
						            	}
							            	/*
							            	   格式:贾大庆 ，15235021186 ， 0595XXXX，山西省 忻州市 忻府区 和平西街国力花园4号楼4单元4楼西门 ，034000 
							            	*/
										    var content=document.all.addresscontent.value;
											var myArray=content.split("，",6);
											if(myArray.length==5){
												//收件人的信息
								            	document.all("search.psendname").value = myArray[0];
								            	document.all("search.psendtel").value = myArray[1]+" "+myArray[2];
								            	document.all("search.psendunit").value ="";
								            	document.all("search.psendaddress").value = myArray[3];
								            	document.all("search.psendcode").value = "";
								            	document.all("search.psendpost").value = myArray[4];  	
						            		}else
						            		{
						            			Ext.MessageBox.alert('提示', '不支持该格式!<br>正确格式:姓名，手机，电话，地址，邮编,<br>例如:贾大庆 ，15235021186 ， 0595XXXX，山西省 忻州市 忻府区 和平西街国力花园4号楼4单元4楼西门 ，034000<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
						            		}
						            		AddressPlan.close();
						            }
						        },{
						            text: '收件人',
						            width:50,
						            handler: function(){
						            	if (document.all.addresscontent.value == ''){
						            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先写需识别的内容!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
						            		return;
						            	}
							            	/*
							            	   格式:贾大庆 ，15235021186 ， 0595XXXX，山西省 忻州市 忻府区 和平西街国力花园4号楼4单元4楼西门 ，034000 
							            	*/
										    var content=document.all.addresscontent.value;
											var myArray=content.split("，",6);
											if(myArray.length==5){
												//收件人的信息
								            	document.all("search.precname").value = myArray[0];
								            	document.all("search.prectel").value = myArray[1]+" "+myArray[2];
								            	document.all("search.precunit").value ="";
								            	document.all("search.precaddress").value = myArray[3];
								            	document.all("search.preccode").value = "";
								            	document.all("search.precpost").value = myArray[4];  	
						            		}else
						            		{
						            			Ext.MessageBox.alert('提示', '不支持该格式!<br>正确格式:姓名，手机，电话，地址，邮编,<br>例如:贾大庆 ，15235021186 ， 0595XXXX，山西省 忻州市 忻府区 和平西街国力花园4号楼4单元4楼西门 ，034000<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
						            		}
						            		AddressPlan.close();
						            }
						        }]
					        }]
					     }]
				    });
				 
		    	}
				AddressPlan.show();
			}
</script>