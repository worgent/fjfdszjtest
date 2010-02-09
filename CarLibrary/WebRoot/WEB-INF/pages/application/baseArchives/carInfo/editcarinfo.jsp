<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<!--
	车辆信息管理 
 -->
<form methd='POST' name='form1' action='/basearchives/car/carInfo.shtml' class="formcheck"  onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>车辆信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">车牌编号(车牌号):</td>
				<td width="33%">
				    <!-- 
					<c:if test="${param.actionType == 'new'}">
						<tt:TextField name="search.carNoCode" value="search.car_no_code" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' onblur="chk_exist()"/>
					</c:if>
					<c:if test="${param.actionType != 'new'}">
						<tt:TextField name="search.carNoCode" value="search.car_no_code" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' readonly="true"/>
					</c:if>
					 -->
					 <tt:TextField name="search.carNoCode" value="search.car_no_code" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' onblur="chk_exist()"/>
					<span id="carNoCodeMsg" style="color:red"></span>
				</td>
				<td align='right' width="17%">终端类型:</td>
				<td width="33%">
					<tt:ComboBox name="search.terminalType" value="search.terminal_type" width="150" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
						sql="select para_value id, para_value_desc text from td_system_para  t where t.para_type = 'CAR_INFO' and para_name = 'TERMINAL_TYPE'"/>
				</td>
			</tr>
			<tr>
				<td align='right'>车载电话:</td>
				<td>
					<tt:TextField name="search.carPhone" value="search.car_phone" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>车辆颜色:</td>
				<td>
					<tt:ComboBox name="search.color" value="search.color" width="150" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
						sql="select para_value id, para_value_desc text from td_system_para  t where t.para_type = 'CAR_INFO' and para_name = 'COLCOR'"/>
			    </td>	
			</tr>
			<tr>
				<td align='right'>行驶里程初始:</td>
				<td>
					<tt:TextField name="search.runMileageInit" value="search.run_mileage_init" width="150" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/> 单位：米
				</td>
			</tr>
			<tr>
				<td align='right'>所属公司:</td>
				<td>
					<div id="belongCompanyDIV"></div>
					<input type="hidden" name="search.belongCompany" value="">
			    </td>
				<td align='right'>所属部门:</td>
			    <td>
					<div id="belongDeptDIV"></div>
					<input type="hidden" name="search.belongDept" value="">
			    </td>
			</tr>			
			<tr>
				<td align='right'>安装时间:</td>
				<td>
					<tt:DateFiled name="search.installDate" value="search.install_date" width="185" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right'>购买日期:</td>
				<td>
					<tt:DateFiled name="search.buyDate" value="search.buy_date" width="185" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>			
			<tr>				
				<td align='right'>所属地市:</td>
				<td>
					<tt:ComboBox name="search.cityId" value="search.city_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>
				<td align='right'>油箱总容量:</td>
				<td>
					<tt:TextField name="search.oilTotal" value="search.oil_total" width="150" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/> 单位：升
				</td>
			</tr>	
		</tbody>
	</table>
	<div style="color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;注：“行驶里程初始”为车辆信息登记时的抄表里程值。
	</div>
	
	<br>
	<table id="equipmentTab" class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan="4">设备信息</th>
			</tr>
		</thead>
		<tbody id="equipmentBody">
			<tr>
				<td align='center' width="20%">设备编号</td>
				<td align='center' width="20%">设备名称</td>
				<td align='center'>备注</td>
				<td align='center' width="8%">
					<a href="javascript:fun_add()">[添加]</a>
				</td>
			</tr>
			<ww:if test="null != carFixingList && carFixingList.size()>0">
				<input type="hidden" name="search.fixingNum" value="<ww:property value="carFixingList.size()"/>">
				<ww:iterator value="carFixingList">
					<tr>
						<td align='center' width="20%">
							<input type="hidden" name="search.fixingId" value="<ww:property value="fixing_id"/>"/>
							<ww:property value="fixing_code"/>
						</td>
						<td align='center' width="20%"><ww:property value="fixing_name"/></td>
						<td align='center'><ww:property value="memo"/></td>
						<td align='center' width="8%">
							<A style='cursor:hand' onclick='del_row();'>删除</a>
						</td>
					</tr>
				</ww:iterator>
			</ww:if>
			<ww:else>
				<input type="hidden" name="search.fixingNum" value="0">
			</ww:else>
			
		</tbody>
		<tfoot id="equipmentFoot">
		</tfoot>
	</table>
	<input type='hidden' name='search.carNoId' value='<ww:property value="search.car_no_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
</form>
<table style='width:80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;</td>
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
        		<c:if test="${param.actionType == 'new'}">
	        	if (!chk_exist()){
	        		alert("对不起，该车辆编号已存在，请重新输入！");
	        		return;
	        	}
	        	</c:if>
	        	
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
    
    function chk_exist(){
    	var value = $('search.carNoCode').value;
    	if (value == ''){
    		$(carNoCodeMsg).innerHTML = '请输入车辆编号！';
    		return;
    	}
    	var url = '/basearchives/car/carInfo.shtml?actionType=isExist&search.carNoCode='+value;
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
				var rowSet = root.selectNodes("//isExist");
				if (0 < rowSet.item(0).selectSingleNode("value").text){
					$(carNoCodeMsg).innerHTML = "对不起，该车辆编号已存在，请重新输入！";
					return false;
				}else{
					$(carNoCodeMsg).innerHTML = '';
					return true;
				}
			}
		}catch(e){ 
			alert(e);
		}
    }

	//下拉型树型菜单
	Ext.onReady(function(){
		Ext.QuickTips.init();
		
		//所属公司
		var belongCompanyTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/basearchives/institution/ajaxInstitution.shtml',
	            hiddenName : 'search.belongCompany',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择员工所属公司！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.belongCompany"/>',
	            value:'<ww:property value="search.belong_to_company"/>'
		});
		belongCompanyTree.render('belongCompanyDIV');	//输出到指定的对象中
		belongCompanyTree.tree.on('click', function(node){	//
			$(belongDeptDIV).innerHTML = '';
			crateDeptTree(node[belongCompanyTree.valueField]);
		});
		
		function crateDeptTree(param){
			var belongDeptTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml?belongCompany='+param,
		            hiddenName : 'search.belongDept',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择部门所属部门！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.belongDept"/>',
		            value:'<ww:property value="search.belong_to_dept"/>'
			});
			belongDeptTree.render('belongDeptDIV');	//输出到指定的对象中
		}
		
		crateDeptTree();
	});


	var searchPlan = new Ext.Window();
	
	function fun_add(){
		var fixingIds = '';
		if (null != document.all('search.fixingId')){
			var obj = document.all('search.fixingId');
			if (obj.length > 1){
				for (var i=0; i<obj.length; i++){
					if (fixingIds == ''){
						fixingIds = "'"+obj[i].value+"'";
					}else{
						fixingIds += ',' + "'"+obj[i].value+"'";
					}
				}
			}else{
				fixingIds = "'"+obj.value+"'";
			}
		}
		
		searchPlan.hidden = true;
		if (searchPlan.hidden){
			searchPlan = new Ext.Window({
		        title: '查询设备信息',
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
			        	columnWidth:.33,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    layout: 'form',
	                    labelWidth:60,
			        	items:[{
			        		xtype: 'textfield',
				            fieldLabel: '设备编码',
				            name: 'fixingCode',
				            width:100  // 设置宽度，百分比的需加‘号
			        	}]
			        },{
			        	columnWidth:.33,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    layout: 'form',
	                    labelWidth:60,
			        	items:[{
			        		xtype: 'textfield',
				            fieldLabel: '设备名称',
				            name: 'fixingName',
				            width:100  // 设置宽度，百分比的需加‘号
			        	}]
			        },{
			        	columnWidth:.33,
	                    baseCls:'x-plain',
	                    bodyStyle:'margin:0px 0px 0px 0px',
			        	buttons: [{
				            text: '查询',
				            width:50,
				            handler: function(){
				            	document.all.EquipmentIframe.src="/basearchives/equipment/equipment.shtml?search.action=extSearch"+
				            																				"&search.fixingIds="+fixingIds+
				            																				"&search.fixingCode="+document.all.fixingCode.value+
				            																				"&search.fixingName="+document.all.fixingName.value;
				            }
				        },{
				            text: '确定',
				            width:50,
				            handler: function(){
				            	if (document.all.EquipmentIframe.src == ''){
				            		Ext.MessageBox.alert('提示', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请先查询!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;', null);
				            		return;
				            	}
				            	
				            	var obj = document.EquipmentIframe.fixingId;
				            	if (obj.length > 1){
				            		var objValue = '';
				            		var fixingId = '';
				            		var fixingCode = '';
				            		var fixingName = '';
				            		var memo = '';
				            		
				            		for (var i=0; i<obj.length; i++){
				            			if (obj[i].checked){
				            				objValue = obj[i].value;
				            				fixingId = objValue.substring(0, objValue.indexOf('^'));
				            				objValue = objValue.substring(objValue.indexOf('^') + 1, objValue.length);
				            				fixingCode = objValue.substring(0, objValue.indexOf('^'));
				            				objValue = objValue.substring(objValue.indexOf('^') + 1, objValue.length);
				            				fixingName = objValue.substring(0, objValue.indexOf('^'));
				            				memo = objValue.substring(objValue.indexOf('^') + 1, objValue.length);
				            				fun_addrow(fixingId, fixingCode, fixingName, memo);
				            			}
				            		}
				            	}else{
				            		if (obj.checked){
					            		var objValue = obj.value;
			            				var fixingId = objValue.substring(0, objValue.indexOf('^'));
			            				objValue = objValue.substring(objValue.indexOf('^') + 1, objValue.length);
			            				var fixingCode = objValue.substring(0, objValue.indexOf('^'));
			            				objValue = objValue.substring(objValue.indexOf('^') + 1, objValue.length);
			            				var fixingName = objValue.substring(0, objValue.indexOf('^'));
			            				var memo = objValue.substring(objValue.indexOf('^') + 1, objValue.length);
			            				fun_addrow(fixingId, fixingCode, fixingName, memo);
		            				}
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
	                        html: "<iframe id='EquipmentIframe' name='EquipmentIframe' src=''width='100%' height='285' frameborder=0/>"
	                    }]
			        }]
		        }]
		    });
	    }
		searchPlan.show();
	}
	
	function fun_addrow(fixingId, fixingCode, fixingName, memo){
		var num = Math.floor(document.form1('search.fixingNum').value);
		num = num + 1;
		document.form1('search.fixingNum').value = num;
		
		var oBody = $(equipmentBody);
		document.all("equipmentTab").insertBefore(oBody,document.all("equipmentFoot"));
		var myTR =oBody.insertRow();
		var myTD1 = myTR.insertCell();
		myTD1.innerHTML = "<div align='center'><input type='hidden' name='search.fixingId' value='"+fixingId+"'/>"+fixingCode+"</div>";
		var myTD2 = myTR.insertCell();
		myTD2.innerHTML = "<div align='center'>"+fixingName+"</div>";
		
		var myTD3 = myTR.insertCell();
		myTD3.innerHTML = "<div align='center'>"+memo+"</div>";
		
		var myTD4 = myTR.insertCell();
		myTD4.innerHTML = "<div align='center'><A style='cursor:hand' onclick='del_row();'>删除</a></div>";
	}
	
	// 删除行
	function del_row(){
		if (!confirm('您确定是否删除，删除后该数据将无法恢复!')){
			return;
		}
		
		var num = Math.floor(document.form1('search.fixingNum').value);
		num = num-1;
		document.form1('search.fixingNum').value = num;
		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.equipmentTab.deleteRow(cur_row);
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