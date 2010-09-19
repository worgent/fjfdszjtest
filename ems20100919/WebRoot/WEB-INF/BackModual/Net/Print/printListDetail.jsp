<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr class="trClass">
		<td width="12%">
			<strong>邮件号</strong>
		</td>
		<td width="10%">
			<strong>寄件人姓名</strong>
		</td>
		<td width="20%">
			<strong>寄件人地址</strong>
		</td>
		<td width="10%">
			<strong>收件人姓名</strong>
		</td>
		<td width="20%">
			<strong>收件人地址</strong>
		</td>
		<!-- 
		<td>
			<strong>最后打印时间</strong>
		</td>
		 -->
		<td>
			<strong>打印次数</strong>
		</td>
		<td align="center">
			<strong>操作</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}">
		<tr>
			<td>
			<input type='checkbox' name='searchpid' id='searchpid' value='<s:property value="#g.ID"/>'/>
				<s:property value="#g.MAILNO" />
			</td>
			<!-- 
			<td>
				<s:property value="#g.MAIL_SENDOFFICE" />
			</td>
			 -->
			<td>
			   <s:label value="%{#g.SENDNAME}" title="%{#g.SENDTEL}"></s:label>
			</td>
			<td>
				<s:property value="#g.SENDADDRESS"/>
			</td>
			<td>
				<s:label value="%{#g.RECNAME}" title="%{#g.RECTEL}"></s:label>
			</td>
			<td>
				<s:property value="#g.RECADDRESS"/>
			</td>
			<!-- 
			<td>
				<s:property value="#g.PRINTTIME" />
			</td>
			 -->
			<td>
			   <s:label value="%{#g.PRINTCOUNT}" title="%{#g.PRINTTIME}"></s:label>
			</td>
			<td align="left">	
			<a href='<%=path%>/net/print.do?action=view&search.pid=<s:property value="#g.ID" />'  target='_self'>查看</a>			
		    <a href='<%=path%>/net/print.do?action=new' target='_self'>增加</a>
		    <a href='<%=path%>/net/ocxprint.do?action=ocxprint&search.pid=<s:property value="#g.ID" />'>打印</a>
			<a href='<%=path%>/net/print.do?action=edit&search.pid=<s:property value="#g.ID" />'>修改</a>
			<a href="javascript:fun_delete(<s:property value="#g.ID"/>)">删除</a>
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
	    <td>
	    	<a href="javascript:chk_all(true);">全选</a>&nbsp;<a href="javascript:chk_all(false);">取消</a>&nbsp;<a href="javascript:allprint(1);">打印</a>&nbsp;<a href="javascript:allprint(0);">删除</a>
	    </td>
		<td align="center" colspan="8" >
			<center>
			分页:<qzgf:pages value="%{pageList.pages}" javaScript="loadDefaultList" styleClass="text-align:left;"/>
			</center>
		</td>
	</tr>
</table>
<script type="text/javascript">
        //全选复选框
    	function chk_all(flag){
			var obj = $(searchpid);
			if (obj != null){
				if (obj.length > 1){
					for (var i=0; i<obj.length; i++){
						obj[i].checked = flag;
					}
				}else{
					obj.chekced = flag;
				}
			}
		}
		
        function allprint(myvar){
        			var obj = $(searchpid);
		            var value="";
		            var flag=0;
		            //组合打印单据的字符串
		            if (obj != null){
						if (obj.length > 1){
							for (var i=0; i<obj.length; i++){
								if(obj[i].checked == true)
								{
								  flag=1;
								  value=value+obj[i].value+',';
								}
							}
						}else{
						    if(obj.chekced == true)
						    {
						      flag=1;
						      value=obj;
						    }
						}
					}
					if(flag==1)
					{
					   if(value.charAt(value.length-1)==',')
					   {
					      value=value.substr(0,value.length-1);
					   }
					   if(myvar==0){//删除
							jConfirm('您确定删除选中的!', '确认框', function(r) {
								    		if (r){
												var url = '<%=path%>/net/print.do?action=alldel&search.pid='+value
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
														if(0<rowSet.item(0).selectSingleNode("value").text){
														    //jAlert("删除成功!",fuction(r){
														     window.location.href='<%=path%>/net/print.do';
														    //});
														   
														}
														else{
														    jAlert('删除失败!','提示');  
														}
													}	
												}catch(e){ 
													jAlert(e,"提示");
												}	
											}
										});
					   }else{//打印
					     window.location.href='<%=path%>/net/ocxprint.do?action=ocxprint&search.pid='+value;
					   }
					}else{
					  jAlert( '至少选择一项！','提示');
					}
        }

		function fun_delete(pid){
			jConfirm('您确定删除该信息!', '确认框', function(r) {
	    		if (r){
					var url = '<%=path%>/net/print.do?action=delete&search.pid='+pid
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
						
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    //jAlert("删除成功!",fuction(r){
							   window.location.href='<%=path%>/net/print.do';
							    //});
							}
							else{
							    jAlert('删除失败!','提示');  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示");
					}	
				}
			});
		}
		//2010-01-05用户提交信息到派揽系统
		function fun_submit(action,pid){
			//jConfirm('您确定执行该操作!', '确认框', function(r) {	
			jPrompt('客户要求:', ' ', '提示框', function(r) {
	    		if (r){
	    		var url = '<%=path%>/net/order.do?action='+action+'&search.pid='+pid+'&search.pclientremark='+r
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
							var rowSet = root.selectNodes("//result");
							var remark=rowSet.item(0).selectSingleNode("remark").text;
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    // jAlert("操作成功!",fuction(r){
							   window.location.href='<%=path%>/net/order.do';
							   // });
							}
							else{
							    jAlert(remark);  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示异常");
					}	
				}
			});
		}
        //2010-01-05用户提交信息到派揽系统
		function fun_submitex(action,pid){
			jConfirm('您确定执行该操作!', '确认框', function(r) {	
			//jPrompt('客户要求:', '', '提示框', function(r) {
	    		if (r){
	    		var url = '<%=path%>/net/order.do?action='+action+'&search.pid='+pid+'&search.pclientremark='+r
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
							var rowSet = root.selectNodes("//result");
							var remark=rowSet.item(0).selectSingleNode("remark").text;
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    // jAlert("操作成功!",fuction(r){
							   window.location.href='<%=path%>/net/order.do';
							   // });
							}
							else{
							   jAlert(remark);  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示异常");
					}	
				}
			});
		}
 </script>
