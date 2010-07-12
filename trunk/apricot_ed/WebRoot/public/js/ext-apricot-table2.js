function createTableForm2(obj){
   function getFormAction(fm,lb,urls,params){
      var pms=fm.getValues();
      if(params) pms=Ext.apply(pms,params);
      return {
         url:toURL(nvl(urls,fm.url)),
         params:pms,
         success:function(form,action){
            var rt=action.result.data;
            if(rt.errorCode=="00"){
              Ext.Msg.alert(lb+"成功","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数据保存成功!&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
              list.getStore().load();
            }else{
              Ext.Msg.alert(lb+"失败","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;原因："+rt.errorMessage);
            }  
         },
         failure:function(form,action){
              if(action.failureType=="client"){
                 Ext.Msg.alert("数据校验失败","数据没有按照要求数据输入，请鼠标移动到红色文本框上面查看校验信息。"); 
                 return;
              }
              Ext.Msg.alert("连接失败","系统连接失败，请联系管理员!"); 
         }
      };
   }
 
   var simpleForm = createFormPanel({
       title: obj.formTitle,
       id:obj.id+"_form",
       cols:nvl(obj.formCols,2),
       //region:(obj.horizontal==true)?"east":"south",
       region:"center",
       height:300,
       width:nvl(obj.width,300),
       split:true,
       editable:obj.editable,
       items: obj.items
   });

   var list=createPageGrid({
       	   title:obj.tableTitle,
       	   id:obj.id+"_table",
       	   urls:nvl(obj.tableURL,obj.table+".pages.go"),
           split:true,
           page:true,
           height:600,
           width:nvl(obj.width,600),
           firstLoad:nvl(obj.firstLoad,true),
           region:(obj.horizontal==true)?"west":"north", 
           toolBarFields : obj.toolBar, 
       	   items:obj.items,
       	  
       	   rowclick:(obj.rowclick)?obj.rowclick:function(){if(obj.onRowClick) obj.onRowClick(list);simpleForm.getForm().loadRecord(list.getSelectionModel().getSelected());}
   });
   
   return [list,simpleForm];
}


