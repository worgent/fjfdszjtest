<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>

<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

<form method='POST' name='form1'
	action='/basearchives/mailfee/Mailfee.shtml' class="formcheck"
	onsubmit="return checkSubmit();">
	<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width: 80%'>
		<thead>
			<tr>
				<th colspan='4'> 
					修改 
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
				 省份
				</td>
				<td width="33%">
						<tt:TextField name="search.pprovince_name" value="search.province_name"  cssClass="check"
						verify='string'  required='true' shade='true'  requiredColor='#ff0000'   />
				</td>
				<td align='right' width="17%">
					地市: 
				</td>
				<td width="33%"> 
				<tt:TextField name="search.pcity_name" value="search.city_name" cssClass="check"
						verify='string'  required='true' shade='true'  requiredColor='#ff0000'   />
				</td>
			</tr>
			
			<tr>
				<td align='right' width="17%">
				区县:
				</td>
				<td width="33%">
					<tt:TextField name="search.pcounty_name" value="search.county_name"
						cssClass="check" verify='string'  shade='true'
						requiredColor='#ffffff' />
				</td>
				<td align='right' width="17%">
					省际封发局:  
				</td>
				<td width="33%">
				<tt:TextField name="search.psend_office" value="search.send_office" cssClass="check"
						verify='string'   shade='true'  requiredColor='#ff0000'   />
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
			      台席:
				</td>
				<td width="33%">
			          <tt:TextField name="search.pemail_fee" value="search.email_fee" cssClass="check"
						verify='string'   shade='true'  requiredColor='#ff0000'   />
			          </td>
				<td align='right' width="17%">
					计费区:
				</td>
				<td width="33%">
					 <tt:TextField name="search.pmail_feearea" value="search.mail_feearea" 
						cssClass="check" verify='string' requiredColor='#ffffff' />
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
				地市定价:
				</td>
				<td  >
				<tt:TextField name="search.pbelong_city" value="search.belong_city"
						cssClass="check" verify='string'   shade='true'
						requiredColor='#ffffff' />
				</td>
						
			    <td align='right' width="17%">
					组合地址库: 
				</td>
				<td width="33%">
					 <tt:TextField name="search.paddressname" value="search.addressname" 
						cssClass="check" verify='string' requiredColor='#ffffff' />
				</td>	
						
			</tr>
				<tr>
				<td align='right' width="17%">
				收件人邮政编码:
				</td>
				<td  >
				<tt:TextField name="search.precpost" value="search.recpost"
						cssClass="check" verify='string'   shade='true'
						requiredColor='#ffffff' />
				</td>
						
			    <td align='right' width="17%">
					&nbsp;
				</td>
				<td width="33%">
						&nbsp;
				</td>	
						
			</tr>
			
		</tbody>
	</table>
	<input type='hidden' name='search.pid' value='<ww:property value="search.id"/>' />
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
</form>
<table style='width: 80%' >
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='left' width='48%'><br></td>
	</tr>
</table>

<script language="javascript">
        Ext.get('form1').un("submit", fm2.onSubmit, fm2);
		new Ext.Button({
	        text: '保 存',
	        handler: function(){
		        if (fm2.isValid()) {
		            Ext.get('search.paddressname').dom.value=Ext.get('search.pprovince_name').dom.value+Ext.get('search.pcity_name').dom.value+Ext.get('search.pcounty_name').dom.value;
		        	document.form1.submit();
	        	}else{
		        	Ext.Msg.show({
	 					title:'信息',
						msg: '请填写完整后再提交!',
						modal : true,
						buttons: Ext.Msg.OK
	 				});
	        	}
	        	    
	        }
	    }).render(document.all.buttonTD1);
	    new Ext.Button({
	        text: '重 置',
	        handler: function(){
	        	document.form1.reset();
	        }
	    }).render(document.all.buttonTD2);
	    
	    
	    
function KeyPress(objTR)
{
   var txtval = objTR.value;
   var key = event.keyCode;
   if((key <48 || key >57)&&key !=46)
   {
         event.keyCode = 0;
   }
   else
   {
         if(key == 46)
         {
               if(txtval.indexOf(".") != -1 || txtval.length == 0)
                     event.keyCode = 0;
         }
   }
} 
	    
	    
	    

</script>