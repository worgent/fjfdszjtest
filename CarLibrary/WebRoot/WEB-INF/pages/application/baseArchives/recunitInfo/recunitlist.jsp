<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
 * 来往单位信息管理
 * @author zhengmh 
--%>
<%@ include file="/common/taglibs.jsp"%>
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
    <script language="javascript">
         function showSearch(){
		   coopUnitTypeData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select * from td_system_para where para_type = 'COOP_UNIT_INFO' and para_name = 'COOP_UNIT_TYPE'\""/>
				<ww:iterator value="#select.selectList" status="coopUnitTypeList">["<ww:property value="PARA_VALUE"/>", "<ww:property value="PARA_VALUE_DESC"/>"]<ww:if test="#coopUnitTypeList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var coopUnitType = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: coopUnitTypeData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.coopUnitType',
				fieldLabel: '往来单位类别',
				name: 'test',
				width:100
			})			
		cityIdData = [
				<tt:setProperty name="#select.dynamicSql" value="'select city_id,city_name from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'"/>
				<ww:iterator value="#select.selectList" status="cityIdList">["<ww:property value="city_id"/>", "<ww:property value="city_name"/>"]<ww:if test="#cityIdList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var cityId = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: cityIdData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.cityId',
				fieldLabel: '所属地市',
				name: 'test',
				width:100
			})				
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/basearchives/recunit/recunit.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/basearchives/recunit/recunit.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '往来单位编号',
			            name: 'search.coopUnitCode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            fieldLabel: '往来单位名称',
			            name: 'search.coopUnitName',
			            width:100  
			        },{
			            fieldLabel: '往来单位简称',
			            name: 'search.unitEasyName',
			            width:100  
			        },coopUnitType,cityId],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '往来单位查询',
			        width: 300,
			        height:220,
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
		    <tt:authority value="TJLWDWXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加往来单位信息','addRecUnit','/basearchives/recunit/recunit.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		   	</tt:authority>
		})
		
		function fun_delete(coopUnitId){
		 if (!confirm('您确定删除该信息!')){
					return;
				}else{
			var url = '/basearchives/recunit/recunit.shtml?actionType=delete&search.coopUnitId='+coopUnitId;
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
						alert("删除往来单位信息，操作成功！");
						parent.document.ifrm_ComeandgoUnitInfo.window.location.reload();
					}else{
						alert("删除往来单位信息，操作失败！");
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
	<div id="addPanel" style="margin:0px;width:100px;"></div>
	<tt:grid id="recunit" value="recunitList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="合作单位编号" width="100">
				<ww:if test="null != coop_unit_id">
					<a href="javascript:parent.addTab('往来单位信息', 'viewRecUnit', '/basearchives/recunit/recunit.shtml?actionType=view&search.coopUnitId=<ww:property value="coop_unit_id"/>','NO')">
						<ww:property value="coop_unit_code"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="合作单位名称" property="coop_unit_name" width="100"/>			
			<tt:col name="合作单位简称" property="unit_easy_name" width="100" visible="true"/>
			<tt:col name="合作单位类别" property="coopUnitType" width="100"/>
			<tt:col name="电话" property="phone" width="80"/>			
			<tt:col name="传真" property="fax" width="80"/>			
			<tt:col name="联系人" property="link_man" width="80"/>
			<tt:col name="联系电话" property="link_man_phone" width="80"/>
			<tt:col name="所属地市" property="cityId" width="80"/>
			<tt:col name="备注" property="memo" visible="true" width="100"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != coop_unit_id">
					<tt:authority value="XGLWDWXX">
						<a href="javascript:parent.addTab('修改往来单位信息', 'editRecUnit', '/basearchives/recunit/recunit.shtml?actionType=edit&search.coopUnitId=<ww:property value="coop_unit_id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCLWDWXX">
						<a href="javascript:fun_delete(<ww:property value="coop_unit_id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</body>