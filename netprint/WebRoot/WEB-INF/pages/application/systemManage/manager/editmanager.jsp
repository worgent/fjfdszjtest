<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

<form method='POST' name='form1' action='/system/manage/manager.shtml' class="formcheck"  onsubmit="return checkSubmit();">
	<table class='simple' style='width:80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>添加新管理员人信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width='20%'>管理员工号:</td>
				<td width='30%'>
					<input type='text' class='wenbenkuang check' <c:if test="${param.actionType == 'edit'}">disabled</c:if> verify='string' required='true' shade='true' requiredColor='#ffffff'  name='userInfo.staffNo' value='<ww:property value="userInfo.staffNo"/>' onblur='chk_ifExist()'/>
					<span style='color:red' id='msgSpan'></span>
				</td>
				<td align='right' width='20%'>管理员姓名:</td>
				<td width='30%'><input type='text' class='wenbenkuang check' verify='string' required='true' shade='true' requiredColor='#ffffff' name='userInfo.staffName' value='<ww:property value="userInfo.staffName"/>'/></td>
			</tr>
			<tr>
				<td align='right'>登录密码:</td>
				<td><input type='password' class='wenbenkuang check' verify='numchar' <c:if test="${param.actionType != 'edit'}">required='true' shade='true' requiredColor='#ffffff'</c:if> name='userInfo.password' value=''/></td>
				<td align='right'>确认密码:</td>
				<td><input type='password' class='wenbenkuang check' verify='samefield' field='userInfo.password' name='userpwd1' value=''/></td>
			</tr>
			<tr>
				<td align='right'>联系电话:</td>
				<td>
					<input type='text' class='wenbenkuang' name='userInfo.phone' value='<ww:property value="userInfo.phone"/>'/>
				</td>
				<td width="17%" align="right">
					<a onclick="selectJg()" style="color:red">所在机构</a>:
				</td>
				<td width="33%">
	<input type='text' class='wenbenkuang check' verify='string' required='true' shade='true'  readonly="true"
	requiredColor='#ffffff' name='userInfo.deptName' onclick="selectJg()"  value='<ww:property value="userInfo.deptName"/>'/>				
					<input type="hidden" name="userInfo.deptId"
						value="<ww:property value="userInfo.deptId"/>" />
				</td>
			</tr>
			<tr>
				<td align='right'>是否启用:
				</td>
				<td>
					<select name="userInfo.isUse">
					<ww:property value="userInfo.isUse"/>
						
						<option value='1' <ww:if test="\"1\".equals(userInfo.isUse)">selected</ww:if>>是</option>
						<option value='0' <ww:if test="\"0\".equals(userInfo.isUse)">selected</ww:if>>否</option>
						<tt:setProperty name="#select.dynamicSql" value="'select '1' id,'是' text '"/>	
						<ww:iterator value="#select.selectList">
							<option value='<ww:property value="id"/>' <ww:if test="id == userInfo.isUse">selected</ww:if>><ww:property value="text"/></option>
						</ww:iterator>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<input type='hidden' name='userInfo.staffId' value='<ww:property value="userInfo.staffId"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
	<input type="hidden" name="search.JGID" value=<ww:property value="jgid"/>>
	<input type="hidden" name="search.JG_MC" value=<ww:property value="jg_mc"/>>	
</form>
<input type="hidden" value="true" name="isTips" id="isTips" />
<table style='width:80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>&nbsp;</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>

<script language="javascript">
	new Ext.Button({
        text: '保 存',
        handler: function(){
        	if( document.form1.checkSubmit()){
        	<c:if test="${param.actionType != 'edit'}">
        		if (!chk_ifExist()){
        			return;
        		}
        	</c:if>
        		$(isTips).value = 'false';
        		document.form1.submit();
        	}
        }
    }).render(document.all.buttonTD1);
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
    
    function chk_ifExist(){
		var url = '/system/manage/manager.shtml?actionType=ifExist&userInfo.staffNo='+$('userInfo.staffNo').value;
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
				if (1 == rowSet.item(0).selectSingleNode("value").text){
					$(msgSpan).innerHTML = '该工号已存在';
					return false;
				}else{
					$(msgSpan).innerHTML = '';
					return true;
				}
			}
		}catch(e){ 
			alert(e);
		}
		return false;
	}
	
	window.onbeforeunload = function(){
		if(document.all("isTips").value == "true")
			event.returnValue = "您的文档可能没有保存!" 
	}
	
    
</script>


 <script>
    Ext.BLANK_IMAGE_URL = '/ext/resources/images/default/s.gif';
     function selectJg(){
        var JGID=Ext.get('search.JGID').dom.value;
        var JG_MC=Ext.get('search.JG_MC').dom.value;
        var tree = new Ext.tree.TreePanel({
        
		    width:'100%',   
			height:400,   
			checkModel: 'cascade',   
			onlyLeafCheckable: false,   
			animate: false,   
			rootVisible: true,   
			autoScroll:true,   
			loader: new Ext.tree.TreeLoader({   
			    dataUrl:'/basearchives/organ/organManage.shtml?actionType=organTreeJson&a='+new Date(),
				baseParams: {sjjg_Id:JGID},
				clearOnLoad : false,
				preloadChildren : false,
				requestMethod : "GET"
			}),   
			root: new Ext.tree.AsyncTreeNode( {
			    id:JGID,
				text : JG_MC,
				draggable : false,
				expanded : true
			}),
			listeners : {
				beforeload :function(node){
					if ('ynode-39' != node.attributes.id){
						this.loader.baseParams.sjjg_Id = node.attributes.id;
					}
				}
			}
		});
	    
	    
		tree.on('dblclick',function(node){  
		      
             document.all("userInfo.deptName").value=node.attributes.text;
		     document.all("userInfo.deptId").value=node.attributes.id;
		      //alert("cc"+Ext.get("search.JGMC").dom.value);
		      //alert("ss"+document.getElementById("search.JG_ID").value);
		     win.hide();
		});
		
		tree.on('click',function(node){  
		     
             document.all("userInfo.deptName").value=node.attributes.text;
		     document.all("userInfo.deptId").value=node.attributes.id;
		     
		});
		
		
		
		tree.getRootNode().expand();
        var tabs = new Ext.TabPanel({
		    region: 'center',
		    margins:'3 3 3 0', 
		    activeTab: 0,
		    defaults:{autoScroll:true},
		    //border:false,
		    items:[{
		        title: '机构列表',
		        items: tree
		    }]
		});
		
        var win = new Ext.Window({
		    title: '选择机构',
		    closable:true,
		    modal : true,
		    width:600,
		    height:350,
		    plain:true,
		    layout: 'border',
		    iconCls:'treeselect',
		    items: [tabs],
		    buttons : [{
		        text: '确定',
		        handler: function() {
		       
		            win.hide();
		        }
		    },{
		        text: '关闭',
		        handler: function() {
		         
             document.all("userInfo.deptName").value='';
		     document.all("userInfo.deptId").value='';
		            win.hide();
		        }
		    }]
		});
        win.show();
    }
    
   
</script>




