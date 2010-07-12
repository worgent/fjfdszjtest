var buyOrderListFields11=[
      {label:"采购单号",name:"buy_no",width:180,readOnly:true},
      {label:"采购日期",name:"buy_date",xtype:"date",format:"Ymd",readOnly:true},
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"采购员",name:"staff_name",hide:"form"},
      {label:"总金额",name:"total_money",readOnly:true},
      {label:"操作",name:"buy_state",hide:"grid",xtype:"combo",allowBlank:false,data:[["5","未通过,存在问题退回"],["4","通过，采购数据填写完毕"]]},
      {label:"审核描述",name:"record_desc",xtype:"textarea"}
   ];

var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}

];

var matFields=[
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name"},
      {label:"原材料说明",name:"mat_memo"},
      {label:"原材料类型",name:"mat_type",code:"MAT_TYPE"},
      {label:"原材料计量单位",name:"mat_measure_unit",code:"MAT_MEASURE_UNIT"},
      {label:"原材料价格",name:"mat_price"}
   ];
var buyOrderListFields=[
      {label:"采购单号",name:"buy_no",hide:"form"},
      {label:"采购日期",name:"buy_date",xtype:"date",format:"Ymd"},
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"审核人",name:"record_staff_id",hide:"all"},
      {label:"审核人",name:"staff_name",xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"record_staff_id"}},
      {label:"预计金额",name:"total_money"},
      {label:"储藏部门",name:"dept_name",readOnly:true},
      {label:"状态",name:"buy_state",hide:"all"},
      {label:"审核描述",name:"record_desc"}
      
   ];
function clcPrice()
{
	var ddd = getData(app_sg_buy_order222);
	var n=Ext.getCmp("buy_num");
	var p=Ext.getCmp("buy_price");
	var a=Ext.getCmp("all_price");
	var b=Ext.getCmp("buyed_price");
	var tm=Ext.getCmp("total_money");
	var t = ((n.getValue()*p.getValue())).toFixed(2);
	b.setValue(t);
}
var buyOrderDetailFields=[
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name",readOnly:true},
      {label:"计量单位",name:"mat_measure_unit_text",readOnly:true},
      {label:"预计采购数量",name:"pre_buy_num",id:"pre_buy_num",readOnly:true,allowBlank:false,xtype:"number"},
      {label:"实际采购数量",name:"buy_num",id:"buy_num",allowBlank:false,listeners:{"blur":clcPrice},xtype:"number"},
      {label:"预计采购单价(元)",name:"pre_buy_price",id:"pre_buy_price",readOnly:true,allowBlank:false,xtype:"number"},
      {label:"实际采购单价(元)",name:"buy_price",id:"buy_price",allowBlank:false,listeners:{"blur":clcPrice},xtype:"number"},
	  {label:"预计小结(元)",name:"all_price",id:"all_price",xtype:"number",readOnly:true},
	  {label:"实用(元)",name:"buyed_price",id:"buyed_price",readOnly:true},
      {label:"明细ID",name:"detail_id",hide:"all"}
   ];
   
 function getBuyOrderTbar(){
     return [
        {text:"申请审核",iconCls:"gridItemEdit",
         handler:function(){
         	var d=getData("app_sg_buy_order111",["buy_id"]);
            if(!d.buy_id){
                $alt("请选择一个采购单进行申请！");
                return;
            }
         	cTabEditWindow({
               label : "采购单审核",
               width : 400,
               height: 300,
               id    : "app_sg_buy_order111",
               items : buyOrderListFields11,
               action: "/buy/order/apply5.do",
               load  : true,
               close : function(){load("app_sg_buy_order222",{});},
               data  : {buy_state:"",record_desc:""},
               check : function(){return existProp("app_sg_buy_order111","buy_id");}
               
            });
         }},"-",
          {text:"刷新",iconCls:"gridBarItemQuery",handler:cLoadEvent("app_sg_buy_order111")}    
     ];
 }  
 
 function getBuyOrderDetailTbar(){
 	 
     var c=function(){
        if(existProp("app_sg_buy_order111","buy_id")) return true;
        $alt("请选择一个采购单进行明细处理！");
        return false;
     }
     return [
        {text:"实际采购量",iconCls:"gridItemAdd",
         handler:function(){
         	var dd = getSelectedData("app_sg_buy_order222");
            cTabEditWindow({
               label : "实际采购量",
               labelWidth : 120,
               width : 400,
               height: 300,
               id    : "app_sg_buy_order222",
               items : buyOrderDetailFields,
               action: "/buy/order/orderExcute.do",
               data  : dd,
               check : c
            });}}
     ];
 } 
   

var orderList=createPageGrid({
     id         : "app_sg_buy_order111",
     title      : "采购单列表",
     region     : "west",
     items      : buyOrderListFields,
     urls       : "/buy/order/excute.do",
     filter     : false,
     split      : true,
     height     : 400,
     width      : 400,
     tbar       : getBuyOrderTbar(),
     rowclick   : function(){
                     var d=getData("app_sg_buy_order111",["buy_id"]);
                     load("app_sg_buy_order222",d);
                  }
 });
 
var detailList=createPageGrid({
     id         : "app_sg_buy_order222",
     title      : "采购单明细",
     region     : "center",
     items      : buyOrderDetailFields,
     urls       : "/buy/order/detail.do",
     firstLoad  : true,
     store      : true,
     singleSelect:true,
     filter     : false,
     split      : true,
     height     : 400,
     width      : 400,
     tbar       : getBuyOrderDetailTbar()
 });
 

 
 
 return [orderList,detailList]; 
   