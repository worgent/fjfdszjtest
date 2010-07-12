var buyOrderListFields=[
      {label:"采购单号",name:"buy_no",width:180,readOnly:true},
      {label:"采购日期",name:"buy_date",xtype:"date",format:"Ymd"},
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"采购员",name:"staff_name",hide:"form"},
//      {label:"总金额",name:"total_money"},
      {label:"储藏部门",name:"dept_name",readOnly:true},
//      {label:"审核操作",name:"buy_state",hide:"grid",xtype:"combo",allowBlank:false,data:[["1","未通过,退回"],["6","通过，执行采购"]]},
      {label:"描述",name:"record_desc",xtype:"textarea"}
   ];

var buyOrderDetailFields=[
      {label:"购买记录ID",name:"buy_id",hide:"all"},
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name"},
//      {label:"预计采购数量",name:"pre_buy_num"},
      {label:"实际采购数量",name:"buy_num"},
//      {label:"预计采购单价",name:"pre_buy_price"},
      {label:"实际采购单价",name:"buy_price"},
      {label:"明细ID",name:"detail_id",hide:"all"}
   ];
   
   
 function getBuyOrderTbar(){
     return [
        {text:"采购单审核",iconCls:"gridItemEdit",
         handler:function(){
            cTabEditWindow({
               label : "采购单审核",
               width : 400,
               height: 300,
               id    : "app_sg_check_order1",
               items : buyOrderListFields,
               action: "/buy/order/apply5.do",
               load  : true,
               close : function(){load("app_sg_check_order2",{});},
               data  : {buy_state:"",record_desc:""},
               check : function(){return existProp("app_sg_check_order1","buy_id");}
               
            });
         }},"-",
          {text:"刷新",iconCls:"gridBarItemQuery",handler:cLoadEvent("app_sg_check_order1")}    
     ];
 }  

var orderList=createPageGrid({
     id         : "app_sg_check_order1",
     title      : "未审核采购单列表",
     region     : "west",
     items      : buyOrderListFields,
     urls       : "/buy/order/waitcheck.do",
     filter     : false,
     split      : true,
     height     : 400,
     width      : 600,
//     tbar       : getBuyOrderTbar(),
     rowclick   : function(){
                     var d=getData("app_sg_check_order1",["buy_id"]);
                     load("app_sg_check_order2",d);
                  }
 });
 
var detailList=createPageGrid({
     id         : "app_sg_check_order2",
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
   