return createTableFormNoButton({
   id:"app_shop_shop_basic",
   tableTitle:"店面列表",
   formTitle:"店面基本信息",
   horizontal:true,
   tableURL: "shop/storefront_info_page.do",
   deleteURL:"/shop/storefront_info_delete.do",
   table:"storefront_info",
   formCols:1,
   items:[
    	 {label:"店面标识",name:"sf_id",hide:"all"},
    	 {label:"店面名称",name:"sf_name",allowBlank:false,sortable:true},
    	 {label:"店面地址",name:"sf_addr",width:200,allowBlank:false},
    	 {label:"店长",name:"sf_manager_name"},
    	 {label:"店面联系电话",name:"sf_phone"},
    	 {label:"店长联系电话",name:"sf_manager_phone"},
    	 {label:"店面标志",name:"sf_status",hide:"all"},
    	 {label:"服务器IP地址",name:"service_ip"},
    	 {label:"短信接口",name:"sms_status",xtype:"combo",code:"SMS_STATUS"}
    ]
});

