var buyOrderListFields=[
      {label:"采购单号",name:"buy_no",width:180},
      {label:"采购日期",name:"buy_date",xtype:"date",format:"Ymd"},
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"采购员",name:"staff_name"},
      {label:"审核人",name:"r_name"},
      {label:"总金额",name:"total_money"},
      {label:"储藏部门",name:"dept_name",readOnly:true},
      {label:"状态",name:"buy_state",hide:"grid",xtype:"combo",allowBlank:false,data:[["1","未通过"],["4","通过"]]},
      {label:"审核描述",name:"record_desc",xtype:"textarea"}
   ];

var buyOrderDetailFields=[
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name"},
      {label:"预计采购数量",name:"pre_buy_num"},
      {label:"实际采购数量",name:"buy_num"},
      {label:"预计采购单价",name:"pre_buy_price"},
      {label:"实际采购单价",name:"buy_price"},
      {label:"明细ID",name:"detail_id",hide:"all"}
   ];

var inStorageFields=[
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"备注",name:"store_memo",xtype:"textarea"}
   ];
   
   
 function getBuyOrderTbar(){
     return [
        {text:"采购单入库",iconCls:"gridItemEdit",
         handler:function(){
              var d=getData("app_sg_in_order11",["buy_id"]);
              cTabEditWindow({
                 label      : "入库",
                 id         : "app_sg_in_order11",
                 action     : "/buy/order/instorage.do",
                 data       : d,
                 check      : function c(){if(!existProp("app_sg_in_order11","buy_id")){$alt("请选择一个采购单入库");return false;}return true;},
                 items      : inStorageFields,
                 width      : 300,
                 height     : 200,
                 close      : function(){load("app_sg_in_order12",{});}
              });
              
         }},"-",
         {text:"退回给采购",iconCls:"gridItemDel",
          handler:function(){
              var d=getData("app_sg_in_order11",["buy_id"]);
              if(!d.buy_id){
                  $alt("请选择一个采购单进行打回操作！");
                  return;
              }
              
              d["buy_state"]="6";
              
              doPost("/buy_order_info.modify.go",d,function(){load("app_sg_in_order11");load("app_sg_in_order12",{});});
              
          }},"-",
          {text:"刷新",iconCls:"gridBarItemQuery",handler:cLoadEvent("app_sg_in_order11")}    
     ];
 }  

var orderList=createPageGrid({
     id         : "app_sg_in_order11",
     title      : "待入库采购单列表",
     region     : "west",
     items      : buyOrderListFields,
     urls       : "/buy/order/waitstorage.do",
     filter     : false,
     split      : true,
     height     : 400,
     width      : 600,
     tbar       : getBuyOrderTbar(),
     rowclick   : function(){
                     var d=getData("app_sg_in_order11",["buy_id"]);
                     load("app_sg_in_order12",d);
                  }
 });
 
var detailList=createPageGrid({
     id         : "app_sg_in_order12",
     title      : "采购单明细",
     region     : "center",
     items      : buyOrderDetailFields,
     urls       : "/buy/order/detail.do",
     firstLoad  : false,
     filter     : false,
     split      : true,
     height     : 400,
     width      : 400
 });
 
 return [orderList,detailList]; 
   