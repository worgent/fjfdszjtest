var fld=[
      {label:"餐位序号",name:"set_no",width:50},
      {label:"订单号",name:"order_no",allowBlank:false},
      {label:"来宾人数",name:"man_count",allowBlank:false,width:50},
      {label:"订单时间",name:"order_time",allowBlank:false},
      {label:"订单状态",name:"order_status_text",width:50},
      {label:"订单类型",name:"order_type_text",width:50},
      {label:"预定人名字",name:"prearrange_name"},
      {label:"预定人电话号码",name:"prearrange_phone"},
      {label:"预定人数",name:"prearrange_man_count",width:50},
      {label:"预定VIP卡号",name:"vip_card_no"},
      {label:"催菜次数",name:"hurry_times",width:50},
      {label:"订餐人地址",name:"prearrange_addr"}
   ];
   
   
  var grid=cGroupGridJson({
      id           : "app_rp_currday_order",
      title        : "当日订单报表",
      items        : fld,
      groupField   : "order_status_text",
      url          : "/report/curr_day_order.do",
      region       : "center"
  }); 
  
  
  return [grid];