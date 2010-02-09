<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<%--
	 * 派车登记
 	 * @author FANGZL 
--%>
<ww:action name="'select'" id="select"></ww:action>
<head>
<style type="text/css">
    .x-panel-body p {
        margin:10px;
    }
    #container {
        padding:10px;
    }
    </style>
    <script type="text/javascript" src="/js/TreeField.js"></script>
    <script language="javascript">
    
    		function fun_Agent(expediteapplyId,parm,usestate){
		    if (!confirm('您确定吗!')){
				return;
			}else{
			   	var url = '/carmanage/expedite/applyer.shtml?actionType=agent&search.expediteapplyId='+expediteapplyId+'&search.agentFlag='+parm+'&search.useState='+usestate;
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
						var rowSet = root.selectNodes("//delete");
						if (0 < rowSet.item(0).selectSingleNode("value").text){
							alert("审合，操作成功！");
							parent.document.ifrm_Applyering.window.location.reload();
						}else if(rowSet.item(0).selectSingleNode("value").text==-1){
							alert("单据状态已经变更了，请刷新！");
							parent.document.ifrm_Applyering.window.location.reload();					
						}
						else{
							alert("您没有该单据的审合权限！");
						}
					}
				}catch(e){ 
					alert(e);
				}
			}
		}
		  //派车类型为市内，长途
 		expTypeDate = [["0", "市内"],["1","长途"]];
		var expType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: expTypeDate
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.expediteapplyType',
				fieldLabel: '派车类型',
				name: 'test',
				width:100
			})	 
			
        //单据使用状态。
 		useStateData = [
				<tt:setProperty name="#select.dynamicSql" 
				value="\"select '0' id,'不通过' name 
				     union select '1' id,'通过' name 
				     union select '2' id,'派车登记' name 
				     union select '3' id,'申请' name 
				     union 
				     select a.flowernode_rowno+4 id,a.flowernode_name name 
				     from td_flowernode a 
				     where a.flowerdefine_id =
				     (select b.flowerdefine_id from td_flowerdefine b where b.flowerdefine_code='Exp_apply' order by flowerdefine_version limit 1)\""/>
				<ww:iterator value="#select.selectList" status="useStateList">["<ww:property value="id"/>", "<ww:property value="name"/>"]<ww:if test="#useStateList.count != #select.selectList.size()">,</ww:if></ww:iterator>
				
		];  
		var useState = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: useStateData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.useState',
				fieldLabel: '单据状态',
				name: 'test',
				width:100
			})	 
		function showSearch(){
			
			//使用单位
			var belongCompanyTree = new Ext.form.TreeField({
					minListHeight:200,
					dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
		            hiddenName : 'search.deptId',
		            valueField : 'id',
		            fieldLabel: '使用单位',
		            treeRootConfig : {
		            	id:'',   
				        text : '请选择',   
				        draggable:false  
		            },
		            value:''
			});
							
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			    	labelAlign: 'right', 
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/carmanage/expedite/applyer.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/carmanage/expedite/applyer.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [belongCompanyTree,useState,expType,{
			            fieldLabel: '单据号',
			            name: 'search.expediteapplyId',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '用车人',
			            name: 'search.useMan',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '预计用车时间',
			            name: 'search.begintendingDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endintendingDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        }],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '派车查询',
			        width: 300,
			        height:300,
			        minWidth: 300,
			        minHeight: 200,
			        layout: 'fit',
			        plain:true,
			        bodyStyle:'padding:5px;',
			        buttonAlign:'center',
			        items: schForm
			    });
			
			    window.show();
			});
		}
		
			Ext.onReady(function(){
				new Ext.Button({
			        text: '查 询',
			        handler: showSearch
			    }).render(document.all.searchPanel);
			    
			    <tt:authority value="Applyering_Add">	    
			    new Ext.Button({
			        text: '添 加',
			        handler: function(){
			        	parent.addTab('添加派车申请','addApplyer','/carmanage/expedite/applyer.shtml?actionType=new','NO');
			        }
			    }).render(document.all.addPanel);	 
                </tt:authority>
			})
		/*审批处理机制*/

					
		function fun_delete(expediteapplyId){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = '/carmanage/expedite/applyer.shtml?actionType=delete&search.expediteapplyId ='+expediteapplyId ;
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
						var rowSet = root.selectNodes("//delete");
						if (0 < rowSet.item(0).selectSingleNode("value").text){
							alert("删除派车申请信息，操作成功！");
							parent.document.ifrm_Applyering.window.location.reload();
						}else{
							alert("删除派车申请信息，操作失败！");
						}
					}
				}catch(e){ 
					alert(e);
				}
			}	
		}

	</script>
</head>
<body>
    <div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	<div  id="addPanel" style="margin:0px;width:100px;"></div>
	<tt:grid id="applyer" value="applyerList" pagination="true">
		<tt:row >
			<tt:col  name="单据号" width="150">
				<ww:if test="null != expediteapply_id">
					<a href="javascript:parent.addTab('派车申请信息', 'viewApply', '/carmanage/expedite/applyer.shtml?actionType=view&search.expediteapplyId=<ww:property value="expediteapply_id"/>','NO')">
						<ww:property value="expediteapply_id"/>
					</a>
				</ww:if>
			</tt:col>			 		
			<tt:col name="派车类型" property="expediteapply_type_name" width="80"/>			
			<tt:col name="使用单位" property="dept_name" width="100"/>		
			<tt:col name="运达地点" property="destination_local" width="100" />
			<tt:col name="用车事由" property="use_excuse" width="75" visible="true"/>			
			<tt:col name="预计用车时间" property="intending_date" width="120"/>			
			<tt:col name="用车人" property="use_man" width="80"/>
			<tt:col name="用车人联系方式" property="use_mobile" width="90"/>
			<tt:col name="所属地市" property="city_name" width="90"  visible="true" />
			<tt:col name="单据状态" property="use_state_name" width="120"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != expediteapply_id">
					<ww:if test="use_state == 3">
					    <tt:authority value="Applyering_Update">
						<a href="javascript:parent.addTab('修改派车申请', 'editApply', '/carmanage/expedite/applyer.shtml?actionType=edit&search.expediteapplyId=<ww:property value="expediteapply_id"/>','NO')">修改</a>
						</tt:authority>
						
						<tt:authority value="Applyering_Delete">
						<a href="javascript:fun_delete(<ww:property value="expediteapply_id"/>)">删除</a>
						</tt:authority>
					</ww:if>
					<ww:elseif test="use_state==2">
					    <tt:authority value="Applyering_ExpRecord">
						<a href="javascript:parent.addTab('派车登记', 'editExpedite', '/carmanage/expedite/expedite.shtml?actionType=new&search.expediteapplyId=<ww:property value="expediteapply_id"/>','NO')">派车登记</a>
						</tt:authority>
					</ww:elseif>
					<ww:elseif test="use_state==0 or use_state==1">
					</ww:elseif>
					<ww:else>
						<a href="javascript:fun_Agent(<ww:property value="expediteapply_id"/>,1,<ww:property value="use_state"/>)">同意</a>
						<a href="javascript:fun_Agent(<ww:property value="expediteapply_id"/>,2,<ww:property value="use_state"/>)">不同意</a>
					</ww:else>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>