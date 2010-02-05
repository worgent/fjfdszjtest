<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 发件人信息管理增加与编辑 
 	 * @author zhengmh 
--%>

<form methd='POST' name='form1' action='/netprint/clientmsg/sendclientmsg.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					寄件人信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					寄件人用户代码:
				</td>
				<td width="33%">
					<tt:TextField name="search.pcode" value="search.code" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">
					寄件人姓名:
				</td>
				<td width="33%">
				    <tt:TextField name="search.pname" value="search.name" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					寄件人单位:
				</td>
				<td colspan=3>
					<tt:TextArea name="search.punit" value="search.unit" width="400" height="50" required='true' msg='请填写单位信息！'/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					寄件人所属国家:
				</td>
				<td>
				    <input type="text" name="search.pnation" value="中国" readonly="readonly" class="check" width="200"/>
				</td>
				<td align='right'>
					寄件人所属省份:
				</td>
				<td>
					<div id="provinceDIV"></div>
					<input type="hidden" name="search.pprovince" value="">
				</td>			
			</tr>
			<tr>
				<td align='right'>
					寄件人所属地市:
				</td>
				<td>
					<div id="cityDIV"></div>
					<input type="hidden" name="search.pcity" value="">
				</td>				
				<td align='right'>
					寄件人所属区县:
				</td>
				<td>
					<div id="countyDIV"></div>
					<input type="hidden" name="search.pcounty" value="">
				</td>
			</tr>
            <tr>
				<td align='right'>
					寄件人详细地址:
				</td>
				<td colspan="3">
					<tt:TextArea name="search.paddress" value="search.address" width="400" height="50" required='true' msg='请填写详细地址！'/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					寄件人联系电话:
				</td>
				<td>
					<tt:TextField name="search.ptel" value="search.tel" width="200" verify='string' required='true'/>
				</td>
				<td align='right'>
					寄件人邮编:
				</td>
				<td>
					<tt:TextField name="search.ppost" value="search.post" width="200" verify='string' required='true'/>
				</td>
			</tr>
		</tbody>
	</table>
	<input type='hidden' name='search.pid' value='<ww:property value="search.id"/>' />
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
	<input type='hidden' name='search.pbill_sort' value='1'/>
</form>
<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
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
    }).render(document.all.buttonTD1);
			
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
    
    
    //下拉型树型菜单
	Ext.onReady(function(){
		Ext.QuickTips.init();
		//所属省份
		var provinceTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/netprint/clientmsg/ajaxProvince.shtml',
	            hiddenName : 'search.pprovince',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择省份！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.provincename"/>',
	            value:'<ww:property value="search.province"/>'
		});
		provinceTree.render('provinceDIV');	//输出到指定的对象中
		
		provinceTree.tree.on('click', function(node){	//
			$(cityDIV).innerHTML = '';
			createcityTree(node[provinceTree.valueField]);
			
			$(countyDIV).innerHTML = '';
			createcountyTree(node[provinceTree.valueField]);
		});
		
		//地市
		function createcityTree(param){
			var cityTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/netprint/clientmsg/ajaxCity.shtml?search.pprovince_code='+param,
		            hiddenName : 'search.pcity',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择地市！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.cityname"/>',
		            value:'<ww:property value="search.city"/>'
			});
			cityTree.render('cityDIV');	//输出到指定的对象中
			cityTree.tree.on('click', function(node){	//
				$(countyDIV).innerHTML = '';
				createcountyTree(node[cityTree.valueField]);
			});
		}
		//区县
		function createcountyTree(param){
			var countyTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/netprint/clientmsg/ajaxCounty.shtml?search.pcity_code='+param,
		            hiddenName : 'search.pcounty',
		            valueField : 'id',
		            allowBlank:false,
	            	blankText : '请选择区县！',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            displayValue:'<ww:property value="search.countyname"/>',
		            value:'<ww:property value="search.county"/>'
			});
			countyTree.render('countyDIV');	//输出到指定的对象中
		}
		//初始化数据
		createcityTree();    //地市
		createcountyTree();  //区县
	});
</script>