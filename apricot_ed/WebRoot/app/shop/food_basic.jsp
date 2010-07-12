var sfSelectColumn=[
  {name:"sf_name",label:"店面名称",query:true},
  {label:"店面地址",name:"sf_addr",width:200},
  {name:"sf_id",label:"店面标识",hidden:true}
];

var deptFields=[
 	{label:"部门ID",name:"dept_id",hide:true,hidden:true},
	{label:"部门名称",name:"dept_name"},
	{label:"上级部门",name:"parent_id",hide:true,hidden:true},
	{label:"上级部门",name:"parent_name"}
];

var fm=[
  {name:"food_id",label:"菜品",hidden:true,hide:true},
  {name:"mat_id",label:"源材料",hidden:true,hide:true},
  {name:"food_name",label:"菜品名称",readOnly:true},
  {name:"food_price",label:"菜品单价(元)",hide:true},
  {name:"mat_name",label:"源材料名称",xtype:"trigger",table:"material_info",columns:[{name:"mat_name",label:"源材料名称"},{name:"mat_id",label:"源材料标识",hidden:true},{name:"mat_measure_unit",label:"计量单位",code:"MEASURE_UNIT"}],map:{mat_measure_unit:"mat_measure_unit"}},
  {name:"mat_price",label:"源材料单价",hide:true},
  {name:"mat_total",label:"所需数量"},
  {name:"mat_measure_unit_text",label:"计量单位",code:"MEASURE_UNIT"}
];


/**
 * 设置菜品的图片脚本段落
 */
function foodImageSet(){
   var d=getData("app_shop_food_basic_table",["food_id","food_name"]);
   
   if(!d.food_id){
     $alt("请选择一个菜品进行图片设置！");
     return;
   }
   
   var fls=[ 
   {text:"对当前指定菜品的图片进行上传。\r请注意的一点：每一次上传都会将上一次上传的图片覆盖。",
    xtype:"label",style:"font-size:9pt"},
   {text:"(图片类型：gif，图片大小：64*64(像素))",xtype:"label",style:"font-size:9pt;font-color:red"},
   {text:" ",xtype:"label"},
   createField({label:"菜品名称",name:"food_name",xtype:"text",readOnly:true}),
   createField({label:"菜品图片",name:"food_image",xtype:"upload"}),
   {xtype:"textfield",hidden:true,name:"food_id",hideLabel:true}
   ];

    var msg = function(title, msg){
        Ext.Msg.show({
            title: title, 
            msg: msg,
            minWidth: 200,
            modal: true,
            icon: Ext.Msg.INFO,
            buttons: Ext.Msg.OK
        });
    };

   var its=new Ext.FormPanel({
       items   : fls,
       style   : "padding:5px",
       frame   : true,
       fileUpload : true,
       region   : "center",
       layout  :"form",
       labelWidth:60,
      
       defaults: {
            anchor: '95%',
            allowBlank: false,
            msgTarget: 'side'
       },
       buttons:[
       {text:"保存",
        handler: function(){
                if(its.getForm().isValid()){
                   its.getForm().submit({
	                    url: toURL('/shop/food/image/add.do'),
	                    
	                    waitMsg: '正在上传菜品图片...',
	                    success: function(fp, o){
	                        msg('成功', '菜品图片上传成功。');
	                        viewImage(d);
	                    }
	                });
                }
         }
       },
       {text:"关闭",handler:function(){getCmp("shopfoodbasic_image_w").destroy();}}
       ]
   });
   
   its.on("afterlayout",function(o){its.getForm().setValues(d)});

   var win=new Ext.Window({
      title   :  "设置菜品图片",
      items   : its,
      width   : 400,
      id      : "shopfoodbasic_image_w",
      height  : 220,
      modal   : true,
      bodyStyle : "background-color:white",
      layout  : "border"
   });
   
   win.show();
}


function viewImage(d){
   var f=getCmp("app_shop_food_basic_form");
   var img=f.findById("food_image_id");
   
   /**
   if(img.autoEl && img.autoEl.src){
      img.autoEl.src=toURL("/shop/food/image/get.do")+"?food_id="+d.food_id;
   }**/
   img.getEl().dom.style.width=64;
   img.getEl().dom.style.height=64;
   img.getEl().dom.src=toURL("/shop/food/image/get.do")+"?food_id="+d.food_id;
   
}



/*************************/

var l= createTableForm({
   id:"app_shop_food_basic",
   tableTitle:"菜品列表",
   formTitle:"菜品基本信息",
   horizontal:true,
   height:650,
   table:"food_info",
   tableURL:"/shop/food_pages.do",
   addURL:"/shop/food_add.do",
   modifyURL:"/shop/food_modify.do",
   deleteURL:"/shop/food_delete.do",
   formCols:1,
   items:[
         {label:"店面",name:"sf_id",hidden:true,hide:true},
    	 {label:"店面名称",name:"sf_name",xtype:"trigger",table:"storefront_info",columns:sfSelectColumn},
    	 {label:"菜品ID",name:"food_id",hide:true,hidden:true},
    	 {label:"菜品名称",name:"food_name",allowBlank:false,sortable:true},
    	 {label:"菜品单价(元)",name:"food_price",allowBlank:false},
    	 {label:"菜品类型",name:"food_type",code:"FOOD_TYPE",xtype:"combo"},
    	 {label:"计量单位",name:"food_price_unit",code:"MEASURE_UNIT",xtype:"combo"},
    	 {label:"退菜",name:"can_return",code:"IS_OR_NOT",xtype:"combo"},
    	 {label:"网上订餐",name:"intelnetorder_food",code:"INTELNETORDER_FOOD",xtype:"combo"},
    	 {label:"辣味程度",name:"spicy_level",code:"SPICY_LEVEL",xtype:"combo"},
    	 {label:"菜品状态",name:"food_status_text",hide:true},
    	 {label:"所属部门",name:"dept_id",hide:"all"},
    	 {label:"所属部门",name:"dept_name",xtype:"trigger",table:"dept_info",columns:deptFields},
    	 {label:"部门关系",hide:"grid",id:"relat_type",name:"relat_type",xtype:"checkboxgroup",items:
    	 [
    	 		{boxLabel: '打印', name: 'relat_type1',inputValue:'1'},
                {boxLabel: '上菜', name: 'relat_type2',inputValue:'2'},
                {boxLabel: '调拨', name: 'relat_type3',inputValue:'4'}

    	 ]},
    	 {label:"菜品图片",height:64,width:64,xtype:"image",id:"food_image_id",autoEl:{tag:"img",src:toURL("/shop/food/image/get.do")},hide:"grid"}
    	
    ],
    onRowClick:function(gd){
       var pm=Ext.apply({},getSelectedData("app_shop_food_basic_table"));
       foodMaterial.getStore().baseParams=pm;
       var v = pm["relat_type"];
	   var temp;
       if( (v & 1) == 1){
       		temp = getCmp("relat_type").items.get(0);
       		 temp.setValue(true); 
       }
       if( (v & 2) == 2){
       		 temp = getCmp("relat_type").items.get(1);
       		 temp.setValue(true);
       		 
       }
       if( (v & 4) == 4){
       		  temp = getCmp("relat_type").items.get(2);
       		 temp.setValue(true);
       		 
       }

       pm["start"]=0;
       pm["limit"]=5;
       foodMaterial.getStore().load({params:pm});
       viewImage(pm);
    },
    toolBar:[
      {text:"添加图片",iconCls:"foodimage",handler:foodImageSet}
    ]
});


var foodMaterial=createPageGrid({
       	   title: "菜品源材料映射表",
       	   id:"app_shop_food_basic_material",
       	   urls:"/shop/food_material_pages.do",
           split:true,
           page:true,
           height:150,
           width:600,
           region:"south", 
       	   items:fm,
       	   createFunction:function(){
                var pm=Ext.apply({},foodMaterial.getStore().baseParams);
                pm["start"]=0;
                pm["limit"]=5;
                var w=createFormWindow({label:"菜品源材料",items:fm,id:"app_shop_food_basic_material_map",action:"food_material_info.insert.go"});
                w.show(this);
                getCmp("app_shop_food_basic_material_map_win_form").getForm().setValues(foodMaterial.getStore().baseParams);
                w.on("beforedestroy",function(){foodMaterial.getStore().load(pm);});
       	   	},
       	   deleteFunction:function(){
                Ext.Ajax.request(getAjaxRequest("/shop/food_material_delete.do",getSelectedData("app_shop_food_basic_material"),
                   function(f,a){
                    attrList.getStore().load();
                }));
       	   }
   });

l.push(foodMaterial);

return l;