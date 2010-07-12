var realTimeStockingFields=[
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name"},
      {label:"储藏部门",name:"dept_name"},
      {label:"盘点批次号",name:"check_no"},
      {label:"盘点结余",name:"mat_num"},
      {label:"盘点至今采购量",name:"buy_total"},
      {label:"盘点至今销售量",name:"sale_total"},
//      {label:"当天退菜量",name:"return_total",hide:"all"},
      {label:"当前库存量",name:"stog_count"}
      /**
      {label:"当前库存量",name:"storage_total"},
      {label:"最近盘点库存量",name:"final_total"},
      {label:"盘点采购量",name:"final_total"},
      {label:"盘点后总消耗量",name:"adjust_total",xtype:"number"}
      */
   ];
 
 var TBar=function(){
    return [
       "&nbsp;&nbsp;盘点日","&nbsp;","-","&nbsp;",
       createField({xtype:"date",format:"Ymd",width:100,id:"ap_st_stocking_check_no"}),
       "&nbsp;",
       {iconCls:"gridBarItemQuery",pressed:true,text:"查询",handler:stockingQuery}
    ];
 }
 
 function stockingQuery(){
    var d={check_no:getCmp("ap_st_stocking_check_no").getValue()};
    if(d.check_no=="" || !d.check_no){
       $alt("请选择一个日期进行盘点查询！");
       return;
    }
    
    load("ap_st_stocking",d);
 }
 


 var grid=cEditGrid({
    id          : "ap_st_stocking",
    label       : "实时库存",
    items       : realTimeStockingFields,
    region      : "center",
    url         : "/storage/realTime_query.do",
    filter      : false,
    tbar        : new TBar()
 });
 


return [grid];