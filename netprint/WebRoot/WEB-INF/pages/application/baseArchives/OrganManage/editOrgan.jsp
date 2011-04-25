<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>

<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<form method='POST' name='form1'
	action='/basearchives/organ/organManage.shtml?' class="formcheck"
	onsubmit="return checkSubmit();">
	<script>
	    var fm2 = new Ext.form.BasicForm('form1'); 
    </script>	
	<table class='simple' style='width: 80%' >
		<thead>
			<tr>
				<th colspan='4'>
					机构维护
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					<font color="red">机构编号:</font>
				</td>
				<td width="33%">
					<tt:TextField name="search.pdeptno" value="search.deptno" cssClass="check" required='true' shade='true' requiredColor='#ffffff'/> 
				</td> 
				<td align='right' width="17%"> 
					<font color="red">机构名称:</font>
				</td>
				<td width="33%">
					<tt:TextField name="search.pdeptname"  value="search.deptname" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff' />
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
					<a onclick="selectJg()"><U><font color="red">上级机构:</font></U></a>
				</td>
				<td width="33%" nowrap="nowrap">
					<tt:TextField name="pparentname" value="search.parentname" cssClass="check" verify='string' required='true' shade='true'  readonly="true" requiredColor='#ffffff' onclick="selectJg()" /> 
					<input type="hidden"  name="search.pparentid" value="<ww:property value="search.parentid"/>" />
				</td>
				<td align='right' width="17%">
					联系人:
				</td>
				<td width="33%">
					<tt:TextField name="search.plinkman" value="search.linkman" cssClass="check" verify='string' />
				</td>
			</tr>
			<tr>
				<td align='right' width="17%">
					联系电话:
				</td>
				<td colspan="3">
					<tt:TextField name="search.plinkmantel" value="search.linkmantel" cssClass="check" verify='string'  />
				</td>
			</tr>
		</tbody>
	</table>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
	<input type='hidden' name='search.pid' value='<ww:property value="search.id"/>' />
	<input type="hidden" name="search.JGID" value='<ww:property value="search.JGID"/>' />
	<input type="hidden" name="search.JG_MC" value='<ww:property value="search.JG_MC"/>' />
</form>

<table style='width: 80%' >
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>
<script type="text/javascript" src="/js/TreeCheckNodeUI.js"></script>
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
		     Ext.get('pparentname').dom.value=node.attributes.text;
		     Ext.get("search.pparentid").dom.value=node.attributes.id;
		     win.hide();
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
		        text: '关闭',
		        handler: function() {
		            win.hide();
		        }
		    }]
		});
        win.show();
    }
    
    new Ext.Button({
	        text: '保 存',
	        handler: function(){
	            if(Ext.get('search.pid').dom.value==Ext.get('search.pparentid').dom.value){
                    Ext.MessageBox.alert('提示', '上级机构不能是本机构!');
                }
                else{
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
</script>