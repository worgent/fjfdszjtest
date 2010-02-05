<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * EMS邮件信息管理
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
        //全选复选框
    	function chk_all(flag){
			var obj = $(searchpid);
			if (obj != null){
				if (obj.length > 1){
					for (var i=0; i<obj.length; i++){
						obj[i].checked = flag;
					}
				}else{
					obj.chekced = flag;
				}
			}
		}
		//查询
		function showSearch(){
			//省
			provinceData = [
				<tt:setProperty name="#select.dynamicSql" value="'select the_code,the_name from td_province"/>
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
			        url:'/netprint/emsmail/emsmail.shtml',	
			        defaultType: 'textfield',	//默认控件类型
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/netprint/emsmail/emsmail.shtml';
			        	this.getEl().dom.submit();
			        },
			       items: [{
			            fieldLabel: '邮件号',
			            name: 'search.pmailno',
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
			            fieldLabel: '寄件人姓名',
			            name: 'search.psendname',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '寄件人电话',
			            name: 'search.psendtel',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '收件人姓名',
			            name: 'search.precname',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '收件人电话',
			            name: 'search.prectel',
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
			        title: '客户信息查询',
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
		    <tt:authority value="AddEmsMail">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加EMS邮件信息','addEmsMail','/netprint/emsmail/emsmail.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		    new Ext.Button({
		        text: '批量打印',
		        handler: function(){
		            var obj = $(searchpid);
		            var value="";
		            var flag=0;
		            //组合打印单据的字符串
		            if (obj != null){
						if (obj.length > 1){
							for (var i=0; i<obj.length; i++){
								if(obj[i].checked == true)
								{
								  flag=1;
								  value=value+obj[i].value+',';
								}
							}
						}else{
						    if(obj.chekced == true)
						    {
						      flag=1;
						      value=obj;
						    }
						}
					}
					if(flag==1)
					{
					   if(value.charAt(value.length-1)==',')
					   {
					      value=value.substr(0,value.length-1);
					      //Ext.MessageBox.alert('提示',value);
					   }
					   //printoxc('http://172.30.193.28:8080/ocxprint.shtml?actionType=ocxprint&search.pid='+value);
					   parent.addTab('批量EMS邮件信息','printEmsMail','/ocxprint.shtml?actionType=ocxprint&search.pid='+value,'NO');
					   //parent.addTab('批量EMS邮件信息','printEmsMail','/netprint/emsmail/emsmail.shtml?actionType=ocxprint&search.pid='+value,'NO');
					   //window.open("/netprint/emsmail/emsmail.shtml?actionType=pageprint&search.pid="+value);
					   //,"","toolbars=0,scrollbars=0,location=0,statusbars=0,menubars=0,resizable=0,width=100,height=100"
					}else{
					   Ext.MessageBox.alert('提示', '至少选择一项打印！');
					}
		        }
		    }).render(document.all.printPanel);
		    
		    new Ext.Button({
		        text: '全 选',
		        handler: function(){
		        	chk_all(true);
		        }
		    }).render(document.all.selectAllPanel);
		    
		    new Ext.Button({
		        text: '取 消',
		        handler: function(){
		        	chk_all(false);
		        }
		    }).render(document.all.cancelAllPanel);
 
		})

		//删除操作
		function fun_delete(id){
			Ext.MessageBox.confirm('提示', '您确定删除该信息!', function(btn) {
				if (btn == 'yes'){			    
				   	var url = '/netprint/emsmail/emsmail.shtml?actionType=delete&search.pid='+id;
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
								Ext.MessageBox.alert('提示', '删除EMS邮件，操作成功！', function(btn) {
								    parent.document.ifrm_EmsMail.window.location.reload();
								});	
							}else{
							    Ext.MessageBox.alert('提示', '删除EMS邮件，操作失败！');
							}
						}
					}catch(e){ 
						alert(e);
					}							    
				}
			});
		}
		
		//打印操作
		function printoxc(url)
		{
			//第一参数为报表模板版本号，第二个参数为报表模板文件，第三个参数为报表文件名称，第四字段为上边距，第五字段为左边距，第六参数为base64的数据
			//	edit1.value=aa.printdata('1.0','http://localhost:8080/x.fr3','x',20,20,document.all("RMVIEWER_DATA").value);
			aa.printxml('1.0','http://172.30.193.28:8080/x.fr3','x',0,0,url);
			//aa.printxml('1.0','http://172.30.193.28:8080/x.fr3','x',0,0,'http://172.30.193.28:8080/my.xml');
		}
	</script>
</head>
<body>
<OBJECT id="aa"
	  classid="clsid:40E8496C-E64A-4CC3-A380-99A93EBBF739"
	  width=0
	  height=0
	  align=center
	  hspace=0
	  vspace=0
>
</OBJECT>
	<div id="searchPanel" style="margin: 0px;width: 100px; float: left;"></div>
	<div id="addPanel" style="margin: 0px; width: 100px;float: left;"></div>
	<div id="selectAllPanel" style="margin: 0px; width: 100px;float: left;"></div>
	<div id="cancelAllPanel" style="margin: 0px; width: 100px;float: left;"></div>
	<tt:authority value="UpdateprintEmsMail">	
	<div id="printPanel" style="margin: 0px; width: 100px;"></div>
	</tt:authority>	
	
	<tt:grid id="emsmail" value="emsmailList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="邮件号" width="80">
				<ww:if test="null != id">
					<a href="javascript:parent.addTab('EMS邮件信息', 'viewEmsMail', '/netprint/emsmail/emsmail.shtml?actionType=view&search.pid=<ww:property value="id"/>','NO')">
						<!--<a href="javascript:chk_all()">选择</a>-->
						<input type="checkbox" name="searchpid" value="<ww:property value="id"/>"/>
						<ww:property value="mailno"/>
					</a>
				</ww:if>
			</tt:col>
			<tt:col name="寄件人收寄局" property="sendoffice" width="80" visible="true"/>			
			<tt:col name="寄件人姓名" property="sendname" width="80"/>
			<tt:col name="寄件人电话" property="sendtel" width="80"/>	
			<tt:col name="寄件人单位名称" property="sendunit" width="80" visible="true"/>	
			<tt:col name="寄件人地址" property="sendaddress" width="80" visible="true"/>	
			<tt:col name="收件人姓名" property="recname" width="80"/>
			<tt:col name="收件人电话" property="rectel" width="80"/>	
			<tt:col name="收件人单位名称" property="recunit" width="80" visible="true"/>	
			<tt:col name="收件人地址" property="recaddress" width="80" visible="true"/>	
			<tt:col name="内件品名" property="sendgoodsname" width="80"/>		
			<tt:col name="开单人" property="create_manname" width="100" visible="true"/>
			<tt:col name="开单时间" property="create_date" width="75"/>
			<tt:col name="最后打印时间" property="printtime" width="75"/>
			<tt:col name="打印次数" property="printcount" width="75"/>
			<tt:col name="操作" align="center" width="100">			
				<ww:if test="null != id">
					<tt:authority value="EditEmsMail">
						<a href="javascript:parent.addTab('修改EMS邮件信息', 'editEmsMail', '/netprint/emsmail/emsmail.shtml?actionType=edit&search.pid=<ww:property value="id"/>','NO')">修改</a>
					</tt:authority>
					<tt:authority value="DelEmsMail">
						<a href="javascript:fun_delete(<ww:property value="id"/>)">删除</a>
					</tt:authority>
					<tt:authority value="UpdateprintEmsMail">
					   <a href="javascript:parent.addTab('打印邮件信息', 'printEmsMail', '/ocxprint.shtml?actionType=ocxprint&search.pid=<ww:property value="id"/>','NO')">打印</a>
					   <!-- 
					   <a href="javascript:printoxc(/netprint/emsmail/emsmail.shtml?actionType=ocxprint&search.pid=<ww:property value="id"/>)">打印</a>
					   
					    <a href="/netprint/emsmail/emsmail.shtml?actionType=pageprint&search.pid=<ww:property value="id"/>" target=_blank>打印</a>
					   -->
					</tt:authority>    
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
    <!-- 
	<div id="selectAllPanel" style="margin: 0px; width: 100px;float: left;"></div>
	<div id="cancelAllPanel" style="margin: 0px; width: 100px;float: left;"></div>
	<tt:authority value="UpdateprintEmsMail">	
	<div id="printPanel" style="margin: 0px; width: 100px;"></div>
	</tt:authority>
	 -->
</body>