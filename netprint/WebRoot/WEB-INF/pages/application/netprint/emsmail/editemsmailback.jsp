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
	height:39px;
	z-index:32;
	left: 889px;
	top: 28px;
}
#Layer37 {
	position:absolute;
	width:105px;
	height:39px;
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
	height:39px;
	z-index:41;
	left: 890px;
	top: 146px;
}
-->
</style>



<form methd='POST' name='form1' action='/netprint/emsmail/emsmail.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
  <div id="Layer1"><!--发件人收寄局-->
<tt:TextField name="search.psendoffice" value="search.sendoffice" width="84" cssClass="check" verify="string" required="true" shade="true" requiredColor='#ffffff'/>
  </div>
	  <div id="Layer2"><!--发件人收寄年-->
<tt:TextField name="search.psenddateyear" value="search.senddateyear" width="26" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer3"><!--发件人收寄月-->
<tt:TextField name="search.psenddatemonth" value="search.senddatemonth" width="20" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>		
  </div>
	  <div id="Layer4"><!--发件人收寄日-->
<tt:TextField name="search.psenddateday" value="search.senddateday" width="21" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/> 
  </div>
	  <div id="Layer5"><!--发件人收寄时-->
<tt:TextField name="search.psenddatehour" value="search.senddatehour" width="21" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer6"><!--寄件人姓名-->
<tt:TextField name="search.psendname" value="search.sendname" width="84" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
	  </div>
	  <div id="Layer7"><!--发件人电话-->
<tt:TextField name="search.psendtel" value="search.sendtel" width="109" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
	  </div>
	  <div id="Layer8"><!--发件人单位名称-->
<tt:TextField name="search.psendunit" value="search.sendunit" width="217" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	      </div>
	  <div id="Layer9"><!--发件人地址-->
	  <tt:TextArea name="search.psendaddress" value="search.sendaddress" width="330" height="44" required='true' msg='发件人地址！'/>
<!--<tt:TextField name="search.psendaddress" value="search.sendaddress" width="330" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	 -->
      </div>
	  <div id="Layer10"><!--发件人代码-->
<tt:TextField name="search.psendcode" value="search.sendcode" width="82" height="16" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer11"><!--发件人邮政编码-->
<tt:TextField name="search.psendpost" value="search.sendpost" width="93" height="16" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer12"><!--内件品名-->
<tt:TextArea name="search.psendgoodsname" value="search.sendgoodsname" width="205" height="47" required='true' msg='内件品名！'/>
<!--<tt:TextField name="search.psendgoodsname" value="search.sendgoodsname" width="205" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>-->
  </div>
	  <div id="Layer13"><!--物品数据-->
<tt:TextField name="search.psendcount" value="search.sendcount" width="129" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <p>&nbsp;</p>
	  <div id="Layer36"><!--保存-->
	  </div>
	  <div id="Layer37"><!--重置-->
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
	  <div id="Layer14"><!--交寄人签名-->
<tt:TextField name="search.psendsign" value="search.sendsign" width="250" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer15"><!--收件人姓名-->
<tt:TextField name="search.precname" value="search.recname" width="103" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer16"><!--收件人电话-->
<tt:TextField name="search.prectel" value="search.rectel" width="122" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer17"><!--收件人单位名称-->
<tt:TextField name="search.precunit" value="search.recunit" width="243" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer18"><!--收件人地址-->
	  <tt:TextArea name="search.precaddress" value="search.recaddress" width="350" height="44" required='true' msg='发件人地址！'/>
<!--<tt:TextField name="search.precaddress" value="search.recaddress" width="350" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>-->
  </div>
	  <div id="Layer19"><!--收件人代码-->
<tt:TextField name="search.preccode" value="search.reccode" width="81" height="17" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer20"><!--收件人邮政编码-->
<tt:TextField name="search.precpost" value="search.recpost" width="108" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer21"><!--重量-->
<tt:TextField name="search.pweight" value="search.weight" width="73" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer22"><!--长-->
<tt:TextField name="search.plength" value="search.length" width="22" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>
	  </div>
	  <div id="Layer23"><!--宽-->
<tt:TextField name="search.pwidth" value="search.width" width="29" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer24"><!--高-->
<tt:TextField name="search.pheight" value="search.height" width="24" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>	
  </div>
	  <div id="Layer25"><!--体积-->
<tt:TextField name="search.psolidity" value="search.solidity" width="26" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer26"><!--资费-->
<tt:TextField name="search.pcharge" value="search.charge" width="109" height="17"  verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer27"><!--保价费-->
<tt:TextField name="search.precinsuremoney" value="search.recinsuremoney" width="152" height="17" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer28"><!--费用总计-->
<tt:TextField name="search.ptotalcharge" value="search.totalcharge" width="111" height="17" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer29"><!--收寄人签名-->
<tt:TextField name="search.pacceptsign" value="search.acceptsign" width="96" height="17" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer30"><!--收件人签名-->
<tt:TextField name="search.precsign" value="search.recsign" width="240" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer31"><!--收件人收寄年-->
<tt:TextField name="search.precdateyear" value="search.recdateyear" width="39" height="17" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer32"><!--收件人收寄月-->
<tt:TextField name="search.precdatemonth" value="search.recdatemonth" width="30" height="17" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer33"><!--收件人收寄天-->
<tt:TextField name="search.precdateday" value="search.recdateday" width="29" height="17" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <div id="Layer34"><!--收件人收寄时-->
<tt:TextField name="search.precdatehour" value="search.recdatehour" width="32" height="17" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
	  <p>&nbsp;</p>
	  <div id="Layer35"><!--备注-->
<tt:TextField name="search.premark" value="search.remark" width="294" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
  </div>
  
<div id="Layer38"><!--邮件号-->
<tt:TextField name="search.pmailno" value="search.mailno" width="251" height="19" verify="string" required="true" shade="true" requiredColor='#ffffff'/>
</div>
<div id="sendprovinceDiv"><!--发件人省份-->
</div>
<input type="hidden" name="search.psendprovince" width="80" height="17"  value="">
<div id="sendcityDiv"><!--发件人地市-->
</div>
<input type="hidden" name="search.psendcity" width="80" height="17"  value="">
<div id="sendcountyDiv"><!--发件人区县-->
</div>
<input type="hidden" name="search.psendcounty" width="80" height="17"  value="">
<div id="recprovinceDiv"><!--收件人省份-->
</div>
<input type="hidden" name="search.precprovince" width="80" height="17"  value="">
<div id="reccityDiv"><!--收件人地市-->
</div>
<input type="hidden" name="search.preccity" width="80" height="17"  value="">
<div id="reccountyDiv"><!--收件人地市-->
</div>
<input type="hidden" name="search.preccounty"  width="80" height="17"  value="">
<div id="Layer39">
</div><!--保存客户信息-->
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
	
	//下拉型树型菜单
	Ext.onReady(function(){
		Ext.QuickTips.init();
		//所属省份
		var sendprovinceTree = new Ext.form.TreeField({
				minListHeight:200,
				height:17,
				width:80,
				dataUrl : '/netprint/clientmsg/ajaxProvince.shtml',
	            hiddenName : 'search.psendprovince',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择省份！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.sendprovincename"/>',
	            value:'<ww:property value="search.sendprovince"/>'
		});
		sendprovinceTree.render('sendprovinceDIV');	//输出到指定的对象中
		
		sendprovinceTree.tree.on('click', function(node){	//
			Ext.get('sendcityDIV').dom.innerHTML="";
			createsendcityTree(node[sendprovinceTree.valueField]);
			
			Ext.get('sendcountyDIV').dom.innerHTML="";
			createsendcountyTree(node[sendprovinceTree.valueField]);
		});
		
		//地市
		function createsendcityTree(param){
			var sendcityTree = new Ext.form.TreeField({
					minListHeight:200,
					height:17,
				    width:80,
					dataUrl : '/netprint/clientmsg/ajaxCity.shtml?search.pprovince_code='+param,
		            hiddenName : 'search.psendcity',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择地市！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.sendcityname"/>',
		            value:'<ww:property value="search.sendcity"/>'
			});
			sendcityTree.render('sendcityDIV');	//输出到指定的对象中
			sendcityTree.tree.on('click', function(node){	//
				Ext.get('sendcountyDIV').dom.innerHTML="";
				createsendcountyTree(node[sendcityTree.valueField]);
			});
		}

		//区县
		function createsendcountyTree(param){
			var sendcountyTree = new Ext.form.TreeField({
					minListHeight:200,
					height:17,
				    width:80,					
					dataUrl : '/netprint/clientmsg/ajaxCounty.shtml?search.pcity_code='+param,
		            hiddenName : 'search.psendcounty',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择区县！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.sendcountyname"/>',
		            value:'<ww:property value="search.sendcounty"/>'
			});
			sendcountyTree.render('sendcountyDIV');	//输出到指定的对象中
		}
				
		//初始化数据
		createsendcityTree();    //地市
		createsendcountyTree();  //区县
		


		
		//所属省份
		var recprovinceTree = new Ext.form.TreeField({
				minListHeight:200,
				height:17,
				width:80,				
				dataUrl : '/netprint/clientmsg/ajaxProvince.shtml',
	            hiddenName : 'search.precprovince',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择省份！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.recprovincename"/>',
	            value:'<ww:property value="search.recprovince"/>'
		});

		recprovinceTree.render('recprovinceDIV');	//输出到指定的对象中
		
		recprovinceTree.tree.on('click', function(node){	//
			Ext.get('reccityDIV').dom.innerHTML="";
			createreccityTree(node[recprovinceTree.valueField]);
			
			Ext.get('reccountyDIV').dom.innerHTML="";
			createreccountyTree(node[recprovinceTree.valueField]);
		});
		
		//地市
		function createreccityTree(param){
			var reccityTree = new Ext.form.TreeField({
					minListHeight:200,
					height:17,
				    width:80,					
					dataUrl : '/netprint/clientmsg/ajaxCity.shtml?search.pprovince_code='+param,
		            hiddenName : 'search.preccity',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择地市！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.reccityname"/>',
		            value:'<ww:property value="search.reccity"/>'
			});
			reccityTree.render('reccityDIV');	//输出到指定的对象中
			reccityTree.tree.on('click', function(node){	//
				Ext.get('reccountyDIV').dom.innerHTML="";
				createreccountyTree(node[reccityTree.valueField]);
			});
		}
		//区县
		function createreccountyTree(param){
			var reccountyTree = new Ext.form.TreeField({
					minListHeight:200,
					height:17,
				    width:80,					
					dataUrl : '/netprint/clientmsg/ajaxCounty.shtml?search.pcity_code='+param,
		            hiddenName : 'search.preccounty',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择区县！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.reccountyname"/>',
		            value:'<ww:property value="search.reccounty"/>'
			});
			reccountyTree.render('reccountyDIV');	//输出到指定的对象中
		}
		//初始化数据
		createreccityTree();    //地市
		createreccountyTree();  //区县
	});
</script>