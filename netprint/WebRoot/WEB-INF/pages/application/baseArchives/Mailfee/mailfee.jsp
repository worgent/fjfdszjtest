<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>

<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>

<form name="form1" method="post" action='/basearchives/mailfee/Mailfee.shtml' class="formcheck" onsubmit="return checkSubmit();" enctype="multipart/form-data">
<table><tr><td>excel文件</td>
<td>
<input type="file"  name="upload"  id="upload" onpropertychange="if(!/(xls)/.test(this.value.substr(this.value.lastIndexOf('.')+1))){Ext.MessageBox.alert('提示','类型不支持');}" />
</td> 
<td><input type="button"  name="buttonName"  id="buttonId" value="上传" onclick="selectJg()" />
模板:<a href="http://print.yzyogo.com/model2.xls"><font color="red">下载</font></a>
</td>
<td>
<div id="waitlog" style="display:none">
处理中,请等待......<img src="/images/extanim32.gif">
</div>
</td>
</tr>
</table>
<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
</form>

<div id="searchPanel" style="margin: 0px; float: left;"></div>
<div id="addPanel" style="margin: 0px; width: 100px;"></div>
	<tt:grid id="mailfeeListExport" value="mailfeeList" pagination="true" xlsex="false"  xls="false" >
		<tt:row>
		    <tt:col name="省份" property="province_name" width="80" />
		    <tt:col name="地市" property="city_name" width="80" />
			<tt:col name="区县" property="county_name" width="120" />
			<tt:col name="省际封发局" property="send_office" width="120" />
			<tt:col name="台席" property="email_fee" width="120" />
			<tt:col name="计费区" property="mail_feearea" width="120" />
			<tt:col name="地市定价" property="belong_city" width="120" />
			<tt:col name="组合地址库" property="addressname" width="120" />
			<tt:col name="收件人邮政编码" property="recpost" width="120" />
			<tt:col name="操作" align="center" width="200">
				<ww:if test="null != id">
				<a href="javascript:parent.addTab('修改', 'mailfeeEdit', '/basearchives/mailfee/Mailfee.shtml?actionType=editList&search.id=<ww:property  value="id" />','NO');">修改</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:fun_delete(<ww:property value="id"/>);"> 删除 </a>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid> 
<script>
function selectJg(){
    Ext.get('waitlog').dom.style.display='block';
    //document.form1.getbyid('waitlog').style.display='block';//同上
   	document.form1.submit();
}
</script>
 <script language="javascript">
	   Ext.onReady(function(){
            new Ext.Button({
		        text: '查 询',
		        handler:showSearch
		   }).render(document.all.searchPanel);
		   
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab('添加封发局信息','addMailfee','/basearchives/mailfee/Mailfee.shtml?actionType=new','NO');
		        }
		    }).render(document.all.addPanel);
		})
     
       function showSearch(){
	       var schForm = new Ext.form.FormPanel({
	           baseCls: 'x-plain',
			   method:'GET', 	//提交方法
			   labelWidth: 100,//文本标签长度
			   url:'/basearchives/mailfee/Mailfee.shtml',//form的action地址	
			   defaultType: 'textfield',	//默认控件类型
			   defaults: {width: 100}, 	//默认宽度
			   onSubmit:Ext.emptyFn,
			   submit:function(){
			       this.getEl().dom.action='/basearchives/mailfee/Mailfee.shtml';
			       this.getEl().dom.submit();
			   },
			   items:[ {
			       fieldLabel: '省份',
			       name: 'search.pprovince_name',
			       width:100  
			   }, {
			       fieldLabel: '地市',
			       name: 'search.pcity_name',
			       width:100  
			   },{
			       fieldLabel: '区县',
			       name: 'search.pcounty_name',
			       width:100  
			   },{
			       fieldLabel: '省际封发局',
			       name: 'search.psend_office',
			       width:100  
			   }],
			   buttons: [{
			                text: '查 询',
			                handler: function(){
			            	    schForm.form.submit(); 
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
			   var url = '/basearchives/mailfee/Mailfee.shtml?actionType=delete&search.pid='+id;
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
	 				    	Ext.MessageBox.show('提示','删除记录信息，操作成功！');
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
				}
			   }
			});
		}
 </script>