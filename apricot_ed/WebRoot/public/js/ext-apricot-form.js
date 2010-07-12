function createFormPanel(obj){
	 
   var fm=new Ext.FormPanel({
    	  id:obj.id,
        width:obj.width,
        height:obj.height,
        minSize:obj.minSize,
        maxSize:obj.maxSize,
        region:nvl(obj.region,"center"),
        bodyStyle:nvl(obj.bodyStyle,"padding:3px 3px 0"),
        //border:obj.border,
        bodyBorder:obj.bodyBorder,
        frame:nvl(obj.frame,true),
        split:nvl(obj.split,true),
        title:obj.title,
        disabled:nvl(obj.disabled,false),
        labelWidth:nvl(obj.labelWidth,80),
        url:obj.href,
        labelAlign: 'left',
        layout:"fit",
        items:getColumns(obj),
        collapsible:obj.collapsible,
        buttons:obj.buttons
   	});
   	
   	
   	function getColumns(obj){
   		 var cols=nvl(obj.cols,2);
   	   var row=null;
   	   var rows=new Array();
   	   
   	   var showEls=new Array();
   	   var hideEls=new Array();
   	   
   	   for(var i=0;i<obj.items.length;i++){
   	      var o=obj.items[i];
   	      if(o.hide==true || o.hide=="form" || o.hide=="all"){
   	        hideEls.push(o);
   	      }else showEls.push(o);
   	   }
   	   
   	   if(cols>showEls.length) cols=showEls.length;//不满足分行要球
   	   for(var i=0;i<showEls.length;i++){
   	      var a=i%cols;
   	      var o=showEls[i];

   	      if(o.singleRow==true){//单独行
   	         rows.push(row);
   	         row=null;
   	         //处理单独行
   	         rows.push({});
   	         continue;
   	      }
   	      if(row==null) row=createRow(cols);
   	      
   	      if(obj.filter==true){
   	      	 if(o.readOnly==true) continue;
   	         if(o.xtype && o.xtype!=="textfield" && o.xtype!=="combo") continue;
   	      } 

   	      row.items[a].items.push(createField(o,obj.id,obj.editable));
   	      
   	   }
   	   if(row!=null) rows.push(row);
   	   //处理隐藏元素
   	   
   	   for(var i=0;i<hideEls.length;i++){
   	      var o=hideEls[i];
 	        rows.push({
   	      	   fieldLabel:o.label,
   	      	   name:o.name,
   	      	   hideMode:"display",
   	      	   hidden:true,
   	      	   hideLabel:true,
   	      	   inputType:o.inputType,
   	      	   xtype:"textfield",//默认文本框
   	      	   //anchor:"90%",
   	      	   allowBlank:nvl(obj.allowBlank,true)

   	      });

   	   }
   	   return rows;
   	}

   	
   	
   	function createRow(cols){
   		 var colsArr=new Array();
   	   var row={layout:'column',items:colsArr};
   	   var r=(1/nvl(obj.cols,2));
   	   var cw=(r==1)?"01":""+r;
   	   cw=cw.substr(1);

   	   for(var i=0;i<cols;i++){
   	       colsArr[i]={columnWidth:cw,layout:"form",items:[]};
   	   }
   	   
   	   return row;
   	}

   	
   	return fm;
}


/**
 * 创建一个表单域的模态对话框
 */
function createFormWindow(obj){
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
              form.getEl().dom.reset();
              if(obj.close) getCmp("dialogFormWindow_xudahu").on("beforedestroy",obj.close);
              getCmp("dialogFormWindow_xudahu").destroy();
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
   
    var form=createFormPanel({
       	   id:obj.id+"_win_form",
       	   bodyBorder:true,
       	   bodyStyle:"background-color:#DFE8F6;padding:2px",
       	   items:obj.items,
       	   region:"center",
       	   labelWidth:obj.labelWidth,
       	   cols:nvl(obj.cols,2),
           buttons:[
             {text:"保存",handler:function(){
                getCmp(obj.id+"_win_form").getForm().doAction("submit",getFormAction(getCmp(obj.id+"_win_form").getForm(),obj.label,obj.action));
             
             }},
             {text:"关闭",handler:function(){Ext.getCmp("dialogFormWindow_xudahu").destroy(true);}}
           ]
   });
   
   form.on("afterlayout",function(o){form.getForm().setValues(obj.data)});
   
   var win=new Ext.Window({
            id:"dialogFormWindow_xudahu",
            title    : obj.label,
            closable : true,
            width    : nvl(obj.width,300),
            height   : nvl(obj.height,400),
            layout   : 'border',
            border   : false,
            bodyBorder: false,
            bodyStyle: "padding:5px",
            modal    : true,
            items    : [form]
   	});
   return win;

}


/**
 * 创建一个表单域的模态对话框
 */
function createFilterWindow(obj,vals){
    var formId=obj.id+"_win_filter";
    var form=createFormPanel({
       	   id:formId,
       	   cols:1,
       	   filter:true,
       	   bodyBorder:true,
       	   items:obj.items

   });
   
   var win=new Ext.Window({
            id:"dialogFilterWindow_xudahu",
            title    : obj.label,
            closable : true,
            width    : 250,
            height   : 300,
            layout   : 'border',
            border   : false,
            bodyBorder: false,
            bodyStyle: "padding:5px",
            modal    : true,
            items    : [form],
            title    :"填写过滤条件",
            buttons:[
             {text:"确定",handler:function(){
                 var st=getCmp(obj.id).getStore();
                 var pm=(st.lastOptions)?st.lastOptions.params:{};
                 if(!pm) pm={};
                 pm=Ext.apply(pm,Ext.getCmp(formId).getForm().getValues());
              
                 load(obj.id,pm);
                 win.destroy();
             }},
             {text:"重置",handler:function(){Ext.getCmp(formId).getForm().getEl().dom.reset();}}
           ]
   	});
   
   //if(vals){
     // form.getForm().setValues(vals);      
  // }
   
   return win;

}

/**
 * 创建一个表格编辑窗口
 */
function cTabEditWindow(o){
   if(o.check && !o.check()) return; //
   var d={};
   
   if(o.load==true) Ext.apply(d,getData(o.id));
   if(o.data) Ext.apply(d,o.data);

   var v=createFormWindow({
          items   : o.items,
          width   : nvl(o.width,400),
          height  : nvl(o.height,300),
          cols    : nvl(o.cols,1),
          id      : o.id,
          label   : o.label,
          action  : o.action,
          close   : function(){reload(o.id);if(o.close) o.close();},
          data    : d,
          labelWidth:o.labelWidth
   });
   v.show();
   
}



