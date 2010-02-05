<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 收件人信息管理 
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
			//省
			provinceData = [
				<tt:setProperty name="#select.dynamicSql" value="\"select the_code,the_name from td_province\""/>
				<ww:iterator value="#select.selectList" status="provinceList">["<ww:property value="the_code"/>", "<ww:property value="the_name"/>"]<ww:if test="#provinceList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var province = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: provinceData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.pprovince',
				fieldLabel: '所属省级',
				name: 'test',
				width:100
			})	
			//市
			cityData = [
				<tt:setProperty name="#select.dynamicSql" value="'select the_code,the_name from td_citys where province_code=' + #session['UserInfo'].staffId+')'"/>
				<ww:iterator value="#select.selectList" status="cityList">["<ww:property value="the_code"/>", "<ww:property value="the_name"/>"]<ww:if test="#cityList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var city = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: cityData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.pcity',
				fieldLabel: '所属地市',
				name: 'test',
				width:100
			})	
			//县	  
			countyData = [
				<tt:setProperty name="#select.dynamicSql" value="'select the_code,the_name from td_county where city_code=' + #session['UserInfo'].staffId+')'"/>
				<ww:iterator value="#select.selectList" status="countyList">["<ww:property value="the_code"/>", "<ww:property value="the_name"/>"]<ww:if test="#countyList.count != #select.selectList.size()">,</ww:if></ww:iterator>
			];
			var county = new Ext.form.ComboBox({
				valueField :"id",
				displayField: "text",
				store:new Ext.data.SimpleStore({
					fields: ["id", "text"],
					data: countyData
				}),
				editable:false,
				triggerAction: 'all',
				mode: 'local',
				blankText:'请选择',
				emptyText:'请选择',
				hiddenName:'search.pcounty',
				fieldLabel: '所属区县',
				name: 'test',
				width:100
			})	
			
			//==================以上参数组建============================
			
			//查询界面			
			Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        url:'/netprint/clientmsg/recclientmsg.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/netprint/clientmsg/recclientmsg.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '收件人用户代码',
			            name: 'search.pcode',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '开始日期',
			            name: 'search.begincreate_date',
			            width:100  
			        },{
			            xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '结束日期',
			            name: 'search.endcreate_date',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '收件人姓名',
			            name: 'search.pname',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '收件人联系电话',
			            name: 'search.ptel',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '收件人地址',
			            name: 'search.paddress',
			            width:100  // 设置宽度，百分比的需加‘号
			        },new Ext.form.Hidden({//hidden  
               			id:'search.pbill_sort',
                		name:'search.pbill_sort',
                		value:0
                    })],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
			    });
			
			    var window = new Ext.Window({
			        title: '收件人信息查询',
			        width: 300,
			        height:270,
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
		//绑定控件信息
		Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		    <tt:authority value="AddRecClientMsg">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加收件人信息','addRecClientMsg','/netprint/clientmsg/recclientmsg.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
		//删除操作
		function fun_delete(id){
			Ext.MessageBox.confirm('提示', '您确定删除该信息!', function(btn) {
				if (btn == 'yes'){			    
				   	var url = '/netprint/clientmsg/recclientmsg.shtml?actionType=delete&search.pid='+id;
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
								Ext.MessageBox.alert('提示', '删除收件人信息，操作成功！', function(btn) {
								     parent.document.ifrm_RecClientMsg.window.location.reload();
								});	
							}else{
							    Ext.MessageBox.alert('提示', '删除收件人信息，操作失败！');
							}
						}
					}catch(e){ 
						alert(e);
					}							    
				}
			});
		}
	</script>
</head>
<body>
	<div id="searchPanel" style="margin: 0px;width: 100px; float: left;"></div>
	<div id="addPanel" style="margin: 0px; width: 100px;"></div>
	<tt:grid id="clientmsg" value="clientmsgList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="收件人用户代码" width="80">
				<ww:if test="null != id">
					<a href="javascript:parent.addTab('收件人信息', 'viewRecClientMsg', '/netprint/clientmsg/recclientmsg.shtml?actionType=view&search.pid=<ww:property value="id"/>','NO')">
						<ww:property value="code"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="收件人姓名" property="name" width="80"/>			
			<tt:col name="收件人单位" property="unit" width="80"/>
			<tt:col name="收件人县级" property="countyname" width="80"/>	
			<tt:col name="收件人详细地址" property="address" width="80"/>	
			<tt:col name="收件人联系电话" property="tel" width="80"/>		
			<tt:col name="收件人邮编" property="post" width="80"/>		
			<tt:col name="开单人" property="create_manname" width="100" visible="true"/>
			<tt:col name="开单时间" property="create_date" width="75"/>
			<tt:col name="操作" align="center" width="100">			
				<ww:if test="null != id">
					<tt:authority value="EditRecClientMsg">
						<a href="javascript:parent.addTab('修改收件人信息', 'editRecClientMsg', '/netprint/clientmsg/recclientmsg.shtml?actionType=edit&search.pid=<ww:property value="id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="DelRecClientMsg">
						<a href="javascript:fun_delete(<ww:property value="id"/>)">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
	<input type='hidden' name='search.pbill_sort' value='0'/>
</body>