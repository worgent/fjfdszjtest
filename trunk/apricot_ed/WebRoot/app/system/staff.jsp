var wFields=[
  {label:"店面ID",name:"sf_id",hidden:true,hide:true},
  {label:"店面名称",name:"sf_name",query:true},
  {label:"店面地址",  name:"sf_addr"}
];

var deptFields=[
 	{label:"部门ID",name:"dept_id",hidden:true,hide:true},
	{label:"部门名称",name:"dept_name"},
	{label:"上级部门",name:"parent_id"},
	{label:"部门路径编码",name:"path_code"}
];

var mFields=[
  {label:"店面ID",name:"sf_id",hidden:true,hide:true},
  {label:"店面名称",name:"sf_name",allowBlank:false,xtype:"trigger",table:"storefront_info",columns:wFields},
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code",allowBlank:false},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE",allowBlank:false},
  {label:"员工姓名",  name:"staff_name",allowBlank:false},
  {label:"隶属部门",name:"dept_id",hide:true},
  {label:"隶属部门",name:"dept_name",allowBlank:false,xtype:"trigger",table:"dept_info",columns:deptFields,getData:function(){return{is_valid:1,cAndAndAnd:true}}},
  {label:"联系电话",  name:"staff_phone",allowBlank:false},
  {label:"家庭住址",  name:"staff_addr"},
  {label:"生日",  name:"staff_birthday"},
  {label:"角色ID",  name:"role_id",value:"0",hidden:true,hide:true},
  {label:"登陆密码",  name:"staff_password",hidden:true}

];


function passwordReset(){
   var fld=[
   	  
       {label:"新密码",width:60,name:"staff_password",emptyText:"输入新密码",inputType:"password"},
       {label:"员工ID",name:"staff_id",hide:"all"}
   ];
   var d=getData("app_system_staff_table","staff_id");
   var w=createFormWindow({
       id        : "staff_password_reset",
       label     : "密码重置",
       items     : fld,
       data      : d,
       action    : "/staff/password_reset.do",
       height    : 125,
       cols      : 1,
       width     : 280
   });
   
   w.show();
}




var obj= {
   id:"app_system_staff",
   tableTitle:"员工列表",
   formTitle:"员工信息",
   horizontal:true,
   tableURL:"/app/system/staff_pages.do",
   table:"staff_info",
   addURL:"/staff/add.do",
   modifyURL:"/staff/modify.do",
   formCols:2,
   items:mFields,
   horizontal:false,
   rowclick:function(){
      var d=getData("app_system_staff_table");
      d["staff_password"]="员工密码不能这里修改";
      setFormValues("app_system_staff_form",d);
      load("system_staffrole_grid",getData("app_system_staff_table",["staff_id"]));
   },
   toolBar :[{text:"密码重置",iconCls:"password",handler:passwordReset}]
};


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
					list.getStore().load();

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
			if (obj.checkValid
					&& !obj.checkValid(getFormValues(obj.id + "_form"))) {
				return;
			}
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
		title : obj.formTitle,
		id : obj.id + "_form",
		cols : nvl(obj.formCols, 2),
		//region:(obj.horizontal==true)?"east":"south",
		region : "center",
		height : 300,
		width : nvl(obj.width, 300),
		split : true,
		editable : obj.editable,
		items : obj.items,
		buttons : [ saveButton, resetButton, cancelButton ]
	});

	var list = createPageGrid( {
		title : obj.tableTitle,
		id : obj.id + "_table",
		urls : nvl(obj.tableURL, obj.table + ".pages.go"),
		split : true,
		page : true,
		height : 300,
		width : nvl(obj.width, 600),
		firstLoad : nvl(obj.firstLoad, true),
		region : (obj.horizontal == true) ? "west" : "north",
		toolBarFields : obj.toolBar,
		items : obj.items,
		createFunction : function() {
			simpleForm.getForm().getEl().dom.reset();

			simpleForm.getForm().setValues(list.getStore().baseParams);
			simpleForm.getForm().url = nvl(obj.addURL, obj.table + ".insert.go");
			if (obj.data)
				simpleForm.getForm().setValues(obj.data);
			saveButton.enable();
			resetButton.enable();
			cancelButton.enable();
		},
		modifyFunction : function() {
			if (!list.getSelectionModel().getSelected()) {
				Ext.Msg.alert("系统提示", "请选中一条记录修改!");
				return;
			}
			simpleForm.getForm().url = nvl(obj.modifyURL, obj.table
					+ ".modify.go");
			saveButton.enable();
			resetButton.enable();
			cancelButton.enable();
		},
		deleteFunction : function() {
			if (!list.getSelectionModel().getSelected()) {
				Ext.Msg.alert("系统提示", "请至少选中一条记录删除!");
				return;
			}
			simpleForm.getForm().url = nvl(obj.deleteURL, obj.table
					+ ".delete.go");
			simpleForm.getForm().doAction("submit",
					getFormAction(simpleForm.getForm(), "删除数据"));
			// list.getStore().load();
		},
		rowclick : (obj.rowclick) ? obj.rowclick : function() {
			if (obj.onRowClick)
				obj.onRowClick(list);
			
			simpleForm.getForm().loadRecord(
					list.getSelectionModel().getSelected());
				
		    
		}
	});
	var roleFields=[
      {label:"员工权限id",name:"staff_priv_id",allowBlank:false,hide:"all"},
      {label:"员工ID",name:"staff_id",allowBlank:false,hide:"all"},
      {label:"权限ID",name:"priv_id",allowBlank:false,hide:"all"},
      {label:"权限名称",name:"priv_name",allowBlank:false},
      {label:"权限状态",name:"priv_status",allowBlank:false,code:"STATUS"},
      {label:"权限类型",name:"priv_type",allowBlank:false,code:"PRIV_TYPE"},
      {label:"权限描述",name:"priv_desc"}
   ];
 function editGrid(o){

   return function(){
      var oo=Ext.apply({},o);
      var ab=getData("app_system_staff_table",["staff_id"]);
      oo.data.staffid=ab["staff_id"];
      var d={};
      var w=createGridWindow({
       id        : "staffrow_edit",
       tableURL  : "staff/role_notin.do",
       columns   : roleFields,
       label     : oo.title,
       data      : oo.data,
       afterRowClick:function(a){Ext.apply(d,a);}
      });
      w.addButton({text:"保存",handler:function(){oo.action(d);w.destroy();}});
      w.show();
   }
}
	var rolegrid=createPageGrid({
     id           : "system_staffrole_grid",
     title        : "员工角色",
     items        : roleFields,
     urls         : "/staff/role_all.do",
     firstLoad    : false,
     page         : true,
     filter       : false,
     split        : true,
     region       : "center",
     createFunction : editGrid({
                         data   : {staffid:"-1"},
                         title  : "添加角色",
                         action : function(o){
                            var d=getData("app_system_staff_table",["staff_id"]);
                            d["priv_id"]=o.priv_id;
                            doPost("staff_role_map.insert.go",d,cLoadEvent("system_staffrole_grid"));
                         }
                      }),
                      
     deleteFunction : function(o){
                            var d=getData("system_staffrole_grid","staff_priv_id");
                            doPost("staff_role_map.delete.go",d,cLoadEvent("system_staffrole_grid"));
                       }             
});
	
	return [list,{xtype:"tabpanel", split        : true,region:"center",bodyStyle:"padding:2px",activeTab:0,items:[simpleForm,rolegrid]}];


