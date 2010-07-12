var staffFields=[
  {label:"员工ID",name:"staff_id",hidden:true,hide:true},
  {label:"员工工号",name:"staff_code"},
  {label:"员工类型",  name:"staff_type",xtype:"combo",code:"STAFF_TYPE"},
  {label:"员工姓名",  name:"staff_name"}

];
var deptFields=[
  {label:"部门ID",name:"dept_id",hidden:true,hide:true},
  {label:"部门名称",  name:"dept_name"}

];
var inStorageFields=[
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"备注",name:"store_memo",xtype:"textarea"}
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
      {label:"采购人",name:"record_staff_id",hide:"all"},
      {label:"采购人",name:"staff_name",xtype:"trigger",table:"staff_info",columns:staffFields,map:{staff_id:"record_staff_id"}},
      {label:"储藏部门",name:"record_dept_id",hide:"all"},
      {label:"储藏部门",name:"dept_name",xtype:"trigger",table:"dept_info",columns:deptFields,map:{dept_id:"record_dept_id"}},
      {label:"预计金额",name:"total_money"},
      {label:"状态",name:"buy_state",hide:"all"},
      {label:"描述",name:"record_desc"}
      
   ];
function clcPrice()
{
	var ddd = getData(app_sg_buy_order2);
	var n=Ext.getCmp("buy_num");
	var p=Ext.getCmp("buy_price");
	var a=Ext.getCmp("all_price");
	var b=Ext.getCmp("buyed_price");
	var tm=Ext.getCmp("total_money");
	var t = ((n.getValue()*p.getValue())).toFixed(2);
	a.setValue(t);

	if((Number(t)+Number(b.getValue())) > Number(tm.getValue()))
	{
		alert("金额"+(Number(t)+Number(b.getValue())).toFixed(2)+"超过预计金额");
		n.focus(true, true);
	}
}
var buyOrderDetailFields=[
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name",xtype:"trigger",table:"material_info",columns:matFields},
      {label:"计量单位",name:"mat_measure_unit_text",hide:"grid",readOnly:true},
//      {label:"预计采购数量",name:"pre_buy_num",hide:"all",id:"pre_buy_num",listeners:{"blur":clcPrice},allowBlank:false,xtype:"number"},
      {label:"实际采购数量",name:"buy_num",id:"buy_num",allowBlank:false,xtype:"number",listeners:{"blur":clcPrice}},
//      {label:"预计采购单价(元)",name:"pre_buy_price",hide:"all",id:"pre_buy_price",listeners:{"blur":clcPrice},allowBlank:false,xtype:"number"},
      {label:"实际采购单价(元)",name:"buy_price",id:"buy_price",allowBlank:false,xtype:"number",listeners:{"blur":clcPrice}},
	  {label:"总价(元)",name:"all_price",id:"all_price",xtype:"number",readOnly:true},
	  {label:"已用(元)",name:"buyed_price",id:"buyed_price",readOnly:true},
	  {label:"预计(元)",name:"total_money",id:"total_money",readOnly:true},
      {label:"明细ID",name:"detail_id",hide:"all"}
   ];
   
 function getBuyOrderTbar(){
     return [
        {text:"新建采购单",iconCls:"gridItemAdd",
         handler:function(){
            cTabEditWindow({
               label : "新建采购单",
               width : 400,
               height: 300,
               id    : "app_sg_buy_order1",
               items : buyOrderListFields,
               action: "/buy/order/create.do",
               data  : {buy_state:"1"}
               
            });}},"-",
        {text:"修改采购单",iconCls:"gridItemEdit",
         handler:function(){
            cTabEditWindow({
               label : "修改采购单",
               width : 400,
               height: 300,
               id    : "app_sg_buy_order1",
               items : buyOrderListFields,
               action: "/buy_order_info.modify.go",
               load  : true,
               check : function(){return existProp("app_sg_buy_order1","buy_id");}
               
            });
         }},"-",
         {text:"采购单入库",iconCls:"gridItemEdit",
         handler:function(){
              var d=getData("app_sg_buy_order1",["buy_id"]);
              cTabEditWindow({
                 label      : "入库",
                 id         : "app_sg_buy_order1",
                 action     : "/buy/order/instorage.do",
                 data       : d,
                 check      : function c(){if(!existProp("app_sg_buy_order1","buy_id")){$alt("请选择一个采购单入库");return false;}return true;},
                 items      : inStorageFields,
                 width      : 300,
                 height     : 200,
                 close      : function(){load("app_sg_buy_order2",{});}
              });
              
         }},"-",
         /**
        {text:"申请审核",iconCls:"orderCheck",
         handler:function(){
              var d=getData("app_sg_buy_order1");
              if(!d.buy_id){
                  $alt("请选择一个采购单进行申请！");
                  return;
              }
              
              d["buy_state"]="5";
              
              doPost("/buy/order/apply5.do",d,function(){
                 load("app_sg_buy_order1",d);
                 load("app_sg_buy_order2",{});
              });
         
         }},"-",*/
         {text:"取消采购单",iconCls:"gridItemDel",
          handler:function(){
              var d=getData("app_sg_buy_order1",["buy_id"]);
              if(!d.buy_id){
                  $alt("请选择一个采购单进行取消！");
                  return;
              }
              
              d["buy_state"]="0";
              
              doPost("/buy_order_info.modify.go",d,function(){
                 load("app_sg_buy_order1",d);
                 load("app_sg_buy_order2",{});
              });
              
          }},"-",
          {text:"刷新",iconCls:"gridBarItemQuery",handler:cLoadEvent("app_sg_buy_order1")}    
     ];
 }  
 
 function getBuyOrderDetailTbar(){
 	 
     var c=function(){
        if(existProp("app_sg_buy_order1","buy_id")) return true;
        $alt("请选择一个采购单进行明细处理！");
        return false;
     }
     return [
        {text:"增加原材料",iconCls:"gridItemAdd",
         handler:function(){
         	var sm = getCmp("app_sg_buy_order2").getSelectionModel();
         	sm.selectAll();
         	var d = getData("app_sg_buy_order1");
         	var dd = getSelectedRows("app_sg_buy_order2");
         	var ddd = getData("app_sg_buy_order2",["buy_id","buyed_price","total_money"]);
         	var allPrice = 0.00;
         	for(var i = 0;i < dd.length;i ++)
         	{
         		var temp=dd[i];
         		allPrice = Number(allPrice) + Number(temp.all_price);
         	}
         	ddd.buyed_price=allPrice.toFixed(2);
         	ddd.total_money = d.total_money;
         	ddd.buy_id = d.buy_id;
            cTabEditWindow({
               label : "增加原材料",
               labelWidth : 120,
               width : 400,
               height: 300,
               id    : "app_sg_buy_order2",
               items : buyOrderDetailFields,
               action: "/buy_order_list.insert.go",
               data  : ddd,
               check : c
            });}},"-",
         {text:"删除原材料",iconCls:"gridItemDel",
          handler:function(){
              var d=getData("app_sg_buy_order2",["detail_id","buy_id"]);
              if(!d.detail_id){
                  $alt("请选择一个采购单明细进行取消！");
                  return;
              }
                        
              doPost("/buy_order_list.delete.go",d,cLoadEvent("app_sg_buy_order2",d));
              
          }}    
     ];
 } 
   

var orderList=createPageGrid({
     id         : "app_sg_buy_order1",
     title      : "采购单列表",
     region     : "west",
     items      : buyOrderListFields,
     urls       : "/buy/order/nocheck.do",
     filter     : false,
     split      : true,
     height     : 400,
     width      : 400,
     tbar       : getBuyOrderTbar(),
     rowclick   : function(){
                     var d=getData("app_sg_buy_order1",["buy_id"]);
                     load("app_sg_buy_order2",d);
                  }
 });
 
var detailList=createPageGrid({
     id         : "app_sg_buy_order2",
     title      : "采购单明细",
     region     : "center",
     items      : buyOrderDetailFields,
     urls       : "/buy/order/detail.do",
     firstLoad  : true,
     store      : true,
     singleSelect:false,
     filter     : false,
     split      : true,
     height     : 400,
     width      : 400,
     tbar       : getBuyOrderDetailTbar()
 });
 

 
 
 return [orderList,detailList]; 
   