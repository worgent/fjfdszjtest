<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>

<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>


<form name="form1"  action='/basearchives/uldExcel/uploadExcel.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
<!-- 
	<input type="file"  name="fileName"  id="fileNameId"  onpropertychange="modifyfilename();" />
	<input type="hidden"  name="fileNameex"  id="fileNameIdex" value=""/>
		<script   language='javascript'>   
	  function   modifyfilename(){   
		  var ttt=document.getElementById("fileNameIdex");
		  alert(ttt);
		  document.getElementById("fileNameIdex")=ttt;
	  }   
	</script>  
	<input type="file" name="fileNamedig"  id="fileNameIddig" style="visibility:hidden">
	<input name="fileName"  id="fileNameId" type="text" readonly>
	<input type=button value=浏览 onclick="fileNameIddig.click(); fileNameId.value= fileNameIddig.value">

	<input type="file"  name="fileName"  id="fileNameId"  onpropertychange="modifyfilename();" />
	<input type="hidden"  name="fileNameex"  id="fileNameIdex" value=""/>
 	<script   language='javascript'>   
	  function   modifyfilename(){   
		  var ttt=document.getElementById("fileNameIdex");
		  alert(ttt);
		  document.getElementById("fileNameIdex")=ttt;
	  }   
	</script>  
 -->
<table><tr><td>excel文件</td>
<td>
	<input type="file"  name="fileName"  id="fileNameId" />
</td> 
<td><input type="button"  name="buttonName"  id="buttonId" value="上传" onclick="selectJg()" /></td>
<td><input type="button"  name="btnprint"  id="btnprintid" value="打印" onclick="print()" /></td>
</tr>
</table>
<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
 <div id="searchPanel" style="margin: 0px; float: left;"></div>
	<div id="addPanel" style="margin: 0px; width: 100px;"></div>
	<tt:grid id="uploadExcelExport" value="uploadExcelList" pagination="true" xlsex="true"  xls="false" >
		<tt:row>
		    <tt:col name="运营商" property="Operators" width="80" />
		    <tt:col name="号码" property="number" width="80" />
			<tt:col name="用户名" property="userName" width="120" />
			<tt:col name="所属地" property="address" width="120" />
			<tt:col name="品牌" property="Brand" width="120" />
			<tt:col name="最近缴费时间" property="JFtime" width="120" />
			<tt:col name="总金额" property="money" width="120" />
			<tt:col name="缴费网点" property="payNet" width="120" />
			<tt:col name="更新网点" property="updateNet" width="120" />
			<tt:col name="导入时间" property="ImportTime" width="120" />
			<tt:col name="文件名" property="fileName" width="120" />
			<tt:col name="操作" align="center" width="200">
				<ww:if test="null != id">
				<a href="javascript:parent.addTab('修改', 'baseInfoEdit', '/basearchives/uldExcel/uploadExcel.shtml?actionType=editList&search.id=<ww:property  value="id" />','NO');">修改</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:fun_delete(<ww:property value="id"/>);"> 删除 </a>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
</form>
<script type="text/javascript" src="/js/TreeCheckNodeUI.js"></script>
<script>
    function selectJg(){
    document.form1.submit();
    }
    
    function print(){
    Ext.get('actionType').dom.value="report";
    //document.form1.actionType="report";//actionType
    document.form1.submit();
    }
</script>
 <script language="javascript">
	   Ext.onReady(function(){
            new Ext.Button({
		        text: '查 询',
		        handler:showSearch
		   }).render(document.all.searchPanel);
		   
		})
     
       function showSearch(){
	       var schForm = new Ext.form.FormPanel({
	           baseCls: 'x-plain',
			   method:'GET', 	//提交方法
			   labelWidth: 100,//文本标签长度
			   url:'/basearchives/uldExcel/uploadExcel.shtml',//form的action地址	
			   defaultType: 'textfield',	//默认控件类型
			   defaults: {width: 100}, 	//默认宽度
			   onSubmit:Ext.emptyFn,
			   submit:function(){
			       this.getEl().dom.action='/basearchives/uldExcel/uploadExcel.shtml';
			       this.getEl().dom.submit();
			   },
			   items:[ {
			       fieldLabel: '运营商',
			       name: 'search.Operators',
			       width:100  
			   }, {
			       fieldLabel: '号码',
			       name: 'search.number',
			       width:100  
			   },{
			       fieldLabel: '用户名',
			       name: 'search.userName',
			       width:100  
			   },{
			       fieldLabel: '所属地',
			       name: 'search.address',
			       width:100  
			   },{
			       xtype: 'datefield',
			       readOnly:true,
			       format: 'Y-m-d',
			       fieldLabel: '<font color="red">缴费时间开始</font>',
			       name: 'search.KSRQ',
			       width:100

			   },{
			       xtype: 'datefield',
			       readOnly:true,
			       format: 'Y-m-d',
			       fieldLabel: '<font color="red">缴费时间结束</font>',
			       name: 'search.JSRQ',
			       width:100

			   },{
			       fieldLabel: '缴费网点',
			       name: 'search.payNet',
			       width:100  
			   },{
			       fieldLabel: '更新网点',
			       name: 'search.updateNet',
			       width:100  
			   },{
			       fieldLabel: '文件名',
			       name: 'search.fileName',
			       width:100  
			   }],
			   buttons: [{
			                text: '查 询',
			                handler: function(){
			                  if((Ext.get('search.KSRQ').dom.value!='')||(Ext.get('search.JSRQ').dom.value!=''))
			                 {
			                  alert('开始日期跟结束日期都为空或者都不为空!');
			                  }
                             else
                             {
			            	    schForm.form.submit(); 
			            	    }
			                }
			            },{
			                text: '重 置',
			                handler: function(){
			            	    schForm.form.reset(); 
			                }
			            }]
	       });
	       
	       var window = new Ext.Window({
			   title: '查询条件',
			   width: 300,
			   height:320,
			   minWidth: 300,
			   minHeight: 200,
			   layout: 'fit',
			   plain:true,
			   bodyStyle:'padding:5px;',
			   buttonAlign:'center',
			   items: schForm
			});
			
			window.show();       
	   }
 </script>
 <script language="javascript">
 function fun_delete(id){
		    Ext.MessageBox.confirm("提示","确定要删除该信息吗?",function(button,text){
		       if(button=="yes"){
			   var url = '/basearchives/uldExcel/uploadExcel.shtml?actionType=delete&search.id='+id;
                   			       try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false ;
					oXMLDom.load(url); 
					var root;
					if (oXMLDom.parseError.errorCode != 0) {
						var myErr = oXMLDom.parseError;
						return;
					} else {
						root = oXMLDom.documentElement;
					}
					if (null != root){
						var rowSet = root.selectNodes("//delete");
						if (1 == rowSet.item(0).selectSingleNode("value").text){
	 				    	alert('删除记录信息，操作成功！');
							parent.document.ifrm_UploadExcel.window.location.reload();
						}else if(2==rowSet.item(0).selectSingleNode("value").text){
						    Ext.Msg.show({
			 					title:'信息',
								msg: "删除记录信息,不能被删除",
								modal : true,
								buttons: Ext.Msg.OK
	 				    	});
						}
						else{
							Ext.Msg.show({
			 					title:'信息',
								msg: '删除记录信息，操作失败！',
								modal : true,
								buttons: Ext.Msg.OK
	 				    	});
						}
					}
				}catch(e){ 
					//alert(e);
				}
			   }
			});
		}
 
 </script>
 
 
 
 
 
 
 
 
 
 
 