<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 派车管理
 	 * @author fangzl 
 	 <script type="text/javascript" src="/themes/default/js/calendar.js"></script>
--%>

<form methd='POST' name='form1' action='/carmanage/expedite/applyer.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>派车申请信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>	
				<td align='right' width="17%">派车类型:</td>
				<td width="33%">
				 <tt:ComboBox name="search.expediteapplyType" value="search.expediteapply_type"  verify="empty" required="true" editable="true" 
				  sql="select 0 id ,'市内' text union select 1 id ,'长途' text"/>
				</td>						
				<td align='right'>预计用车时间:</td>
				<td>
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="search.intendingDate" type="text" value="<ww:property value="search.intending_date"/>" onfocus="calendar();"  size="18">					
				</td>
			</tr>		
			<tr>
				<td align='right' width="17%" >使用单位:</td>
				<td width="33%">
					<div id="deptDIV"></div>
					<input type="hidden" name="search.deptId" value="">
				</td>		
				<td align='right'>运达地点:</td>
				<td>
				   <tt:TextField name="search.destinationLocal" value="search.destination_local" width="150" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>		
			</tr>
			<tr>
		    	<td align='right' width="17%">用车人:</td>
				<td>		     				  
					<tt:TextField name="search.useMan" value="search.use_man" width="150" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>用车人联系人方式:</td>				
				<td>
					<tt:TextField name="search.useMobile" value="search.use_mobile" width="150" cssClass="check" verify='int+' required='true' shade='true' requiredColor='#ffffff'/>
				</td>			
			</tr>
            <tr>   
				<td align='right'>用车事由:</td>
				<td colspan="3">
					<tt:TextArea name="search.useExcuse" value="search.use_excuse" width="400" height="50" verify='string' required='true' msg='请填写用车事由'/>
				</td>
			</tr>
            <tr>
				<td align='right'>所属地市:</td>
				<td colspan="3">
					<tt:ComboBox name="search.cityId" value="search.city_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>				
			</tr>			
		</tbody>
	</table>
	<input type='hidden' name='search.expediteapplyId' value='<ww:property value="search.expediteapply_id"/>' />
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
	<input type='hidden' name='search.cmdFlag'/>
</form>
<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='33%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='center' width='33%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD3" align='left' width='33%'></td>
	</tr>
</table>
	<div style="color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;注：“保存：临时保存申请单并没有提交审批；保存并提交：保存申请单并提交审批"。
	</div>
<script type="text/javascript" src="/js/TreeField.js"></script>
<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid() && document.form1.checkSubmit()) {
	        	Ext.Msg.show({
				 	title:'再确认一下',
				 	modal : false,
				 	msg: '您确定资料正确吗?',
				 	buttons: Ext.Msg.OKCANCEL,
				 	fn: function(btn, text){
						if (btn == 'ok'){
						    //提交类型,保存
						    document.all("search.cmdFlag").value=0;
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
    }).render(document.all.buttonTD1);
    
 	var savesubmitButton =new Ext.Button({
        text: '保存并提交',
        handler: function(){
        	if (fm2.isValid() && document.form1.checkSubmit()) {
	        	Ext.Msg.show({
				 	title:'再确认一下',
				 	modal : false,
				 	msg: '您确定资料正确吗?',
				 	buttons: Ext.Msg.OKCANCEL,
				 	fn: function(btn, text){
						if (btn == 'ok'){
						    //提交类型，保存并提交
						    document.all("search.cmdFlag").value=1;
					 		document.form1.submit();
					 	} 
				 	},
				 	animEl: 'buttonTD2'
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
    }).render(document.all.buttonTD2);   
			
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD3);
    

	//下拉型树型菜单
	Ext.onReady(function(){
		Ext.QuickTips.init();
		
		//部门
		var belongCompanyTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
	            hiddenName : 'search.deptId',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择用车部门！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.dept_name"/>',
	            value:'<ww:property value="search.dept_id"/>'
		});
		belongCompanyTree.render('deptDIV');	//输出到指定的对象中
	});
</script>