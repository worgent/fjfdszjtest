<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<!-- 
	设备管理
 -->
<form methd='POST' name='form1' action='/basearchives/equipment/equipment.shtml' class="formcheck"  onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>设备信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			    <td align='right'>设备编号:</td>
				<td width="33%">
					<tt:TextField name="search.fixingCode" value="search.fixing_code" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">设备名称:</td>
				<td width="33%">
					<tt:TextField name="search.fixingName" value="search.fixing_name" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注：</td>
				<td colspan='3'>
					<tt:TextArea name="search.memo" value="search.memo" width="400" height="50"/>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	
	
	<table id='paramTab' class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan="4">设备保养参数</th>
			</tr>
			<tr>
				<td align='center' width='5%'>序号</td>
				<td align='center' width='15%'>参数类型</td>
				<td align='center' width='30%'>参数值</td>
				<td><a href="javascript:fun_addrow();">[添加]</a></td>
			</tr>
		</thead>
		<tbody id='paramBody'>
			<ww:if test="null != equipentParamList && equipentParamList.size()>0">
				<input type='hidden' name='search.paramNum' value='<ww:property value="equipentParamList.size()"/>'/>
				<ww:iterator value="equipentParamList" status="equipentParam">    
					<tr>
						<td align='center'>1</td>
						<td align='center'>
							<select name='search.paramType'>
								<tt:setProperty name="#select.dynamicSql" value="\"select * 
															 from td_system_para 
															where para_type = 'FIXING_PARA' 
															  and para_name = 'MAINTAIN_PARAM'\""/>
								<ww:iterator value="#select.selectList">
									<option value="<ww:property value="PARA_VALUE"/>" <ww:if test="PARA_VALUE.equals(''+param_type)">selected</ww:if>><ww:property value="PARA_VALUE_DESC"/></option>
								</ww:iterator>
							</select>
						</td>
						<td align='center'>
							<input type='text' class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='search.paramValue' value='<ww:property value="param_value"/>'/>
						</td>
						<td><A style='cursor:hand' onclick='del_row();'>删除</a></td>
					</tr>
				</ww:iterator>
			</ww:if>
			<ww:else>
				<input type='hidden' name='search.paramNum' value='1'/>
				<tr>
					<td align='center'>1</td>
					<td align='center'>
						<select name='search.paramType'>
							<tt:setProperty name="#select.dynamicSql" value="\"select * 
														 from td_system_para 
														where para_type = 'FIXING_PARA' 
														  and para_name = 'MAINTAIN_PARAM'\""/>
							<ww:iterator value="#select.selectList">
								<option value="<ww:property value="PARA_VALUE"/>"><ww:property value="PARA_VALUE_DESC"/></option>
							</ww:iterator>
						</select>
					</td>
					<td align='center'>
						<input type='text' class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='search.paramValue' value=''/>
					</td>
					<td><A style='cursor:hand' onclick='del_row();'>删除</a></td>
				</tr>
			</ww:else>
		</tbody>
		<tfoot id='paramFoot'>
			<tr>
				<td colspan="4"></td>
			</tr>
		</tfoot>
	</table>
	
	<input type='hidden' name='search.fixingId' value='<ww:property value="search.fixing_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
</form>
<table style='width:80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>

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
    
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);

	function fun_addrow(){
		var num = Math.floor(document.form1('search.paramNum').value);
		num = num + 1;
		document.form1('search.paramNum').value = num;
		
		var oBody = $(paramBody);
		document.all("paramTab").insertBefore(oBody,document.all("paramFoot"));
		var myTR =oBody.insertRow();
		var myTD1 = myTR.insertCell();
		myTD1.innerHTML = "<div align='center'>"+num+"</div>";
		
		
		<tt:setProperty name="#select.dynamicSql" value="\"select * 
														 from td_system_para 
														where para_type = 'FIXING_PARA' 
														  and para_name = 'MAINTAIN_PARAM'\""/>
		var myTD2 = myTR.insertCell();
		myTD2.innerHTML = "<div align='center'>"+
						  "<select name='search.paramType'>"+
						  <ww:iterator value="#select.selectList">
						  	"<option value='<ww:property value="PARA_VALUE"/>'><ww:property value="PARA_VALUE_DESC"/></option>"+
						  </ww:iterator>
						  "</select>"+
						  "</div>";
		
		var myTD3 = myTR.insertCell();
		myTD3.innerHTML = "<div align='center'><input type='text' class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='search.paramValue' value=''/></div>";
		
		var myTD4 = myTR.insertCell();
		myTD4.innerHTML = "<A style='cursor:hand' onclick='del_row();'>删除</a>";
	}
	
	// 删除行
	function del_row(){
		if (!confirm('您确定是否删除，删除后该数据将无法恢复!')){
			return;
		}
		
		var num = Math.floor(document.form1('search.paramNum').value);
		num = num-1;
		document.form1('search.paramNum').value = num;
		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.paramTab.deleteRow(cur_row);
	}
	
	// 被上个函数调用
	function get_Element(the_ele,the_tag){
	   the_tag = the_tag.toLowerCase();
	   if(the_ele.tagName.toLowerCase()==the_tag)return the_ele;
	   while(the_ele=the_ele.offsetParent){
	     if(the_ele.tagName.toLowerCase()==the_tag)return the_ele;
	   }
	   return(null);
	}
</script>