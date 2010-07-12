var roleFields=[
  {label:"角色ID",name:"priv_id",hidden:true,hide:true},
  {label:"角色名称",name:"priv_name"},
  {label:"角色状态",name:"priv_status",xtype:"combo",code:"STATUS"},
  {label:"角色类型",name:"priv_type",xtype:"combo",hide:"all",code:"PRIVILEGE_TYPE"},
  {label:"角色描述",  name:"priv_desc"}
];

var privFields=[
  {label:"角色ID",name:"priv_id",hidden:true,hide:true},
  {label:"权限ID",name:"child_priv_id",hidden:true,hide:true},
  {label:"权限名称",name:"priv_name"},
  {label:"权限状态",name:"priv_status",xtype:"combo",code:"STATUS"},
  {label:"权限类型",name:"priv_type",xtype:"combo",code:"PRIVILEGE_TYPE"},
  {label:"权限描述",name:"priv_desc"}
];

var gggFields=[
  {label:"权限ID",name:"priv_id",hidden:true,hide:true},
  {label:"权限名称",name:"priv_name"},
  {label:"权限状态",name:"priv_status",xtype:"combo",code:"STATUS"},
  {label:"权限类型",name:"priv_type",xtype:"combo",code:"PRIVILEGE_TYPE"},
  {label:"权限描述",name:"priv_desc"}
];

	function getFormAction(fm, lb, urls, params) {
		var pms = fm.getValues();
		if (params)
			pms = Ext.apply(pms, params);
		return {
			url : toURL(nvl(urls, fm.url)),
			params : pms,
			success : function(form, action) {
				var rt = action.result.data;
				if (rt.errorCode == "00") {
					Ext.Msg
							.alert(lb + "成功",
									"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据保存成功!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					saveButton.disable();
					resetButton.disable();
					cancelButton.disable();
					load("app_system_role_list");

				} else {
					Ext.Msg.alert(lb + "失败",
							"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原因："
									+ rt.errorMessage);
				}
			},
			failure : function(form, action) {
				if (action.failureType == "client") {
					Ext.Msg
							.alert("数据校验失败",
									"数据没有按照要求数据输入，请鼠标移动到红色文本框上面查看校验信息。");
					return;
				}
				Ext.Msg.alert("连接失败", "系统连接失败，请联系管理员!");
			}
		};
	}
	var saveButton = new Ext.Button( {
		disabled : true,
		text : "&nbsp;&nbsp;&nbsp;保存&nbsp;&nbsp;&nbsp;",
		handler : function() {
			simpleForm.getForm().doAction("submit",
					getFormAction(simpleForm.getForm(), "保存数据"));

		}
	});

	var resetButton = new Ext.Button( {
		disabled : true,
		text : "&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;",
		handler : function() {
			simpleForm.getForm().getEl().dom.reset();
		}
	});

	var cancelButton = new Ext.Button( {
		disabled : true,
		text : "&nbsp;&nbsp;&nbsp;取消&nbsp;&nbsp;&nbsp;",
		handler : function() {
			simpleForm.getForm().getEl().dom.reset();
			saveButton.disable();
			resetButton.disable();
			this.disable();
		}
	});
	var simpleForm = createFormPanel( {
		title : "基本信息",
		id : "app_system_role_form",
		cols : 2,
	
		region : "center",

		split : true,
		items : roleFields,
		buttons : [ saveButton, resetButton, cancelButton ]
	});

var roleList=createPageGrid({
       	   title: "角色列表",
       	   id:"app_system_role_list",
       	   urls:"/privilege_info.pages.go?priv_type=01",
           split:true,
           page:true,
           region:"center", 
       	   items:roleFields,
       	   filter:true,
       	   rowclick:function(){
       	       var pm={};
       	       pm={role_id:getSelectedData("app_system_role_list").priv_id};
       	       pm["start"]=0;
       	       pm["limit"]=10;
       	       getCmp("app_system_role_map").getStore().baseParams=pm;
       	       getCmp("app_system_role_map").getStore().load();
			simpleForm.getForm().setValues(getData("app_system_role_list"));
       	   },		createFunction : function() {
			//simpleForm.getForm().getEl().dom.reset();
			simpleForm.getForm().setValues({priv_type:"01",priv_id:"",priv_name:"",priv_desc:""});
			simpleForm.getForm().url = "privilege_info.insert.go";

			saveButton.enable();
			resetButton.enable();
			cancelButton.enable();
		},
		modifyFunction : function() {
			if (!list.getSelectionModel().getSelected()) {
				Ext.Msg.alert("系统提示", "请选中一条记录修改!");
				return;
			}
			simpleForm.getForm().url =  "privilege_info.modify.go";
			saveButton.enable();
			resetButton.enable();
			cancelButton.enable();
		},
		deleteFunction : function() {
			if (!list.getSelectionModel().getSelected()) {
				Ext.Msg.alert("系统提示", "请至少选中一条记录删除!");
				return;
			}
			simpleForm.getForm().url ="privilege_info.delete.go";
			simpleForm.getForm().doAction("submit",
					getFormAction(simpleForm.getForm(), "删除数据"));
			// list.getStore().load();
		}
   });
   
var privList=createPageGrid({
       	   title: "权限列表",
       	   id:"app_system_role_map",
       	   urls:"/app/system/system_role_priv_map_pages.do",
       	   firstLoad:false,
           split:true,
           page:true,
 
           region:"center", 
       	   items:privFields,
       	   filter:false,
           createFunction:function(){
              var a=getSelectedData("app_system_role_list");
              var b={};
              if(!a.priv_id){ $alt("请选择一个角色处理");return;}
              b["priv_type"]="00";
              b["role_id"]=a.priv_id;
              var w=createFormWindow({
                  id:"app_system_role_map_f",
                  action:"privilege_rela.insert.go?priv_type=00",
                  label:"添加权限",
                  cols:1,
                  items:[
                    {label:"权限名称",name:"priv_name",xtype:"trigger",
                     columns:gggFields,tableURL:"/app/system/system_role_priv_select.do",
                     getData : function(){return b;},
                     map:{priv_id:"child_priv_id"},
                     readOnly:true
                    },
                    {label:"权限ID",name:"child_priv_id",hidden:true,hide:true},
                    {label:"角色",name:"priv_id",hidden:true,hide:true},
                    {label:"权限描述",name:"priv_desc",hidden:true,xtype:"textarea",readOnly:true}
                  ]
                  
                });
              w.show(this);
              getCmp("app_system_role_map_f_win_form").getForm().setValues({priv_id:a.priv_id});  
              
              w.on("beforedestroy",function(){load("app_system_role_map");});
           },
           deleteFunction:function(){
              var gd=getCmp("app_system_role_map");
              if(!gd.getSelectionModel().getSelected()){
                 $alt("请选择一条数据删除");
                 return;
              }
              var b=getSelectedData("app_system_role_map");
              Ext.Ajax.request(getAjaxRequest(
                                              "/app/system/system_role_priv_map_del.do",
                                              b,
                                              function(rt){
                                                gd.getStore().load();
                                              }
                                              ));
           }
   });
   
return [roleList,{xtype:"tabpanel", split        : true,height:200,bodyStyle:"padding:2px",region:"south",activeTab:0,items:[simpleForm,privList]}];