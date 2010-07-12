var buyOrderListFields=[
      {label:"盘点批次号",name:"check_no"},
      {label:"盘点人",name:"staff_id"},
      {label:"盘点时间",name:"check_time"},
      {label:"店面ID",name:"sf_id",hide:"all"},
      {label:"盘点描述",name:"check_desc"}
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
        {text:"当天盘点",iconCls:"gridItemEdit",
         handler:function(){
              var d=getData("app_sg_stocking_order11",["buy_id"]);
              cTabEditWindow({
                 label      : "入库",
                 id         : "app_sg_stocking_order11",
                 action     : "/buy/order/instorage.do",
                 data       : d,
                 check      : function c(){if(!existProp("app_sg_stocking_order11","buy_id")){$alt("请选择一个采购单入库");return false;}return true;},
                 items      : inStorageFields,
                 width      : 300,
                 height     : 200
              });
              
         }},"-",
         {text:"刷新",iconCls:"gridBarItemQuery",handler:cLoadEvent("app_sg_stocking_order11")}    
     ];
 }  

var orderList=createPageGrid({
     id         : "app_sg_stocking_order11",
     title      : "盘点列表",
     region     : "west",
     items      : buyOrderListFields,
     urls       : "/buy/order/waitstorage.do",
     filter     : false,
     split      : true,
     height     : 400,
     width      : 600,
     tbar       : getBuyOrderTbar(),
     rowclick   : function(){
                     var d=getData("app_sg_stocking_order11",["buy_id"]);
                     load("app_sg_stocking_order12",d);
                  }
 });
 
var detailList=createPageGrid({
     id         : "app_sg_stocking_order12",
     title      : "盘点明细",
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
   