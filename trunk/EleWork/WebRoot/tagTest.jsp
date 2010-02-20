<br><%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>
多选框
<div>
<tt:InputDBMultiCheckListBox name="test" sql="select city_id id,city_name text from td_city"/>
</div>
<br>
下拉框
<div>
<tt:ComboBox name="search.proxyLevel" value="search.proxyLevel" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true"
				 		sql="select para_value id, para_value_desc text from td_system_para  t where t.para_type = 'MANAGE_ORGAN_ARCHIVES' and para_name = 'PROXY_LEVEL'"/>
</div>
<br>
日期框
<div>
<tt:DateFiled name="search.executeDate" value="search.execute_date"/>
</div>
<br>
多选框
<div>
<tt:CheckBox name="test" filedValue="test" text="test"/>
</div>
<br>
单选框
<div>
<tt:Radio name="test1" filedValue="test1" text="test1"/>
</div>
<br>
文本框
<div>
<tt:TextField name="textField" value="text" cssClass="check" shade="true" requiredColor="#ffffff" verify="int+" required="true"/>
</div>
文本域
<div>
<tt:TextArea name="textArea" value="text" rows="4" width="200"/>
</div>
时间框
<div>
<tt:TimeField name="timeField1" minValue="7:00am" maxValue="8:00pm" increment="60"/>
</div>
Html编辑器
<div>
<tt:HtmlEditor name="htmlEditor" value="aaaaaaaaabbbbbbbbbcccccc"/>
<input type="button" value="aa" onclick="alert(document.all.htmlEditor.value);">
</div>



	
	<div style='background:black;width:200px;height:200px;'>
		<div style='background:red;width:50px;height:50px;float:left;'></div>
		<div style='background:green;width:50px;height:50px;float:left;'></div>
	</div>
	
	
	<div>
		<div id="simpleFormDiv"></div>
		<script>
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';    
			var simpleForm = new Ext.FormPanel({  
				renderTo:'simpleFormDiv',  
				labelAlign: 'left',    
				title: '表单例子',    
				buttonAlign:'right',    
				bodyStyle:'padding:5px',    
				width: 600,    
				frame:true,    
				labelWidth:80,    
				items: [{        
					layout:'column',        
					border:false,        
					labelSeparator:'：',        
					items:[{            
						columnWidth:.5,            
						layout: 'form',            
						border:false,            
						items: [{                
							xtype:'textfield',                
							fieldLabel: '姓名',                
							name: 'name',                
							anchor:'90%',
							blankText:'请输入姓名', 
							allowBlank:false     
						}]        
					},{            
						columnWidth:.25,            
						layout: 'form',            
						border:false,            
						items: [{                       
							style:'margin-top:5px',                
							xtype:'radio',                
							fieldLabel: '性别',                
							boxLabel:'男',                
							name: 'sex',                
							checked:true,                
							inputValue:'男',                
							anchor:'95%'            
						}]        
					},{            
						columnWidth:.25,            
						layout: 'form',            
						labelWidth:0,            
						labelSeparator:'',            
						hideLabels:true,            
						border:false,            
						items: [{                       
							style:'margin-top:5px',                
							xtype:'radio',                
							fieldLabel: '',                
							boxLabel:'女',                
							name: 'sex',                
							inputValue:'女',                
							anchor:'95%'            
						}]        
					},{            
						columnWidth:.5,            
						layout: 'form',            
						border:false,            
						items: [{                
							xtype:'datefield',                
							fieldLabel: '出生日期',                
							name: 'birthday',                
							anchor:'90%'            
						}]      
					},{            
						columnWidth:.5,            
						layout: 'form',            
						border:false,            
						items: [{                
							xtype:'combo',                                                             
							store: new Ext.data.SimpleStore({                                              
								fields: ["retrunValue", "displayText"],                                              
								data: [[1,'小学'],[2,'初中'],[3,'高中'],[4,'中专'],[5,'大专'],[6,'大学']]                                            
							}),                                            
							valueField :"retrunValue",                                            
							displayField: "displayText",                                            
							mode: 'local',                                            
							forceSelection: true,                                            
							blankText:'请选择学历',                                            
							emptyText:'选择学历',                                            
							hiddenName:'education',                                            
							editable: false,                                                             
							triggerAction: 'all',                                                             
							allowBlank:false,                
							fieldLabel: '学历',                
							name: 'education',                
							anchor:'90%'            
						}]      
					},{            
						columnWidth:.35,            
						layout: 'form',            
						border:false,            
						items: [{                
							xtype:'checkbox',                
							fieldLabel: '权限组',                
							boxLabel:'系统管理员',                
							name: 'popedom',                
							inputValue:'1',                
							anchor:'95%'            
						}]      
					},{            
						columnWidth:.2,            
						layout: 'form',            
						labelWidth:0,            
						labelSeparator:'',            
						hideLabels:true,            
						border:false,            
						items: [{                
							xtype:'checkbox',                
							fieldLabel: '',                
							boxLabel:'管理员',                
							name: 'pepedom',                
							inputValue:'2',                
							anchor:'95%'            
						}]        
					},{            
						columnWidth:.2,            
						layout: 'form',            
						labelWidth:0,            
						labelSeparator:'',            
						hideLabels:true,            
						border:false,            
						items: [{                
							xtype:'checkbox',                
							fieldLabel: '',                
							boxLabel:'普通用户',                
							name: 'pepedom',                
							inputValue:'3',                
							anchor:'95%'            
						}]        
					},{            
						columnWidth:.25,            
						layout: 'form',            
						labelWidth:0,            
						labelSeparator:'',            
						hideLabels:true,            
						border:false,            
						items: [{                
							xtype:'checkbox',                
							fieldLabel: '',                
							boxLabel:'访客',                
							name: 'pepedom',                
							inputValue:'4',                
							anchor:'95%'            
						}]        
					},{            
						columnWidth:.5,            
						layout: 'form',            
						border:false,            
						items: [{                
							xtype:'textfield',                
							fieldLabel: '电子邮件',                
							name: 'email',                
							vtype:'email',                
							allowBlank:false,                
							anchor:'90%'            
						}]        
					},{            
						columnWidth:.5,            
						layout: 'form',            
						border:false,            
						items: [{                
							xtype:'textfield',                
							fieldLabel: '个人主页',                
							name: 'url',                
							vtype:'url',                
							anchor:'90%'            
						}]       
					}]    
				},{        
					xtype:'tabpanel',        
					plain:true,        
					activeTab: 0,        
					height:235,        
					defaults:{bodyStyle:'padding:10px'},        
					items:[{            
						title:'登录信息',            
						layout:'form',            
						defaults: {width: 230},            
						defaultType: 'textfield',             
						items: [{                
							fieldLabel: '登录名',                
							name: 'loginID',                
							allowBlank:false,                
							vtype:'alphanum',                
							allowBlank:false            
						},{                       
							inputType:'password',                
							fieldLabel: '密码',                
							name: 'password',                
							allowBlank:false            
						}]        
					},{            
						title:'数字时间字母',            
						layout:'form',            
						defaults: {width: 230},           
						defaultType: 'textfield',             
						items: [{                       
							xtype:'numberfield',                
							fieldLabel: '数字',                
							name: 'number'            
						},{                       
							xtype:'timefield',                
							fieldLabel: '时间',                
							name: 'time'            
						},{                
							fieldLabel: '纯字母',                
							name: 'char',                
							vtype:'alpha'            
						}]        
					},{            
						title:'文本区域',            
						layout:'fit',            
						items: {                
							xtype:'textarea',                
							id:'area',                
							fieldLabel:''            
							}        
					}]    
				}],
				buttons: [{        
					text: '保存',        
					handler:function(){               
						if(simpleForm.form.isValid()){                       
							this.disabled=true;                       
							simpleForm.form.doAction('submit',{                                                     
								url:'test.asp',                                                     
								method:'post',                                                     
								params:'',                                                      
								success:function(form,action){                                                             
									Ext.Msg.alert('操作',action.result.data);                                                             
									this.disabled=false;                                                     
								},                                                      
								failure:function(){                                                             
									Ext.Msg.alert('操作','保存失败！');                                                             
									this.disabled=false;                                                     
								}                       
							});               
						}        
					}                
				},{        
					text: '取消',        
					handler:function(){
						simpleForm.form.reset();
					}    
				}]
			}); 
		</script>
	</div>