var stockingFields=[
      {label:"原材料ID",name:"mat_id",hide:"all"},
      {label:"原材料名称",name:"mat_name"},
      {label:"盘点批次号",name:"check_no"},
      {label:"储藏部门",name:"dept_name"},
      {label:"上期盘点结余",name:"storage_total"},
      {label:"盘点至今销售量",name:"sale_total"},
//      {label:"当天退菜量",name:"return_total",hide:"all"},
      {label:"盘点至今采购量",name:"buy_total"},
      {label:"当前库存量",name:"stog_count"},
      {label:"调整量",name:"adjust_total",xtype:"number"},
      {label:"盘点结果",name:"mat_num"}
   ];
 
 function checkSave(){
    var ss={check_no:getCmp("ap_st_stocking_check_no").getValue()};
    if(ss.check_no=="" || !ss.check_no){
       $alt("请先查询库存信息，然后盘点！");
       return;
    }
    var d=getAllData("ap_st_stocking");
    if(d.length<1){
       $alt("请先查询库存信息，然后盘点！");
       return;
    }
    d.push(ss);
    
    doPost("/storage/check_save.do",d,cLoadEvent("ap_st_stocking",ss));
 }  
 
 var TBar=function(){
    return [
       "&nbsp;&nbsp;盘点日","&nbsp;","-","&nbsp;",
       createField({xtype:"datetime",width:100,id:"ap_st_stocking_check_no"}),
       "&nbsp;",
       {iconCls:"gridBarItemQuery",pressed:true,text:"查询",handler:stockingQuery},
       "&nbsp;",
       {iconCls:"stocking",pressed:true,text:"盘点",handler:checkSave}
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
    label       : "库存盘点",
    items       : stockingFields,
    region      : "center",
    url         : "/storage/check_query.do",
    filter      : false,
    tbar        : new TBar()
 });
 


return [grid];