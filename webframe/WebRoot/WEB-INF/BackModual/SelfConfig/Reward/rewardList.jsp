<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<script type="text/javascript">
		function fun_delete(pid){
			jConfirm('您确定删除该信息!', 'Confirmation Dialog', function(r) {	
	    		if (r){
				   var url = '<%=path%>/selfconfig/reward.do?action=delete&search.pid='+pid
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
							   window.location.href='<%=path%>/selfconfig/reward.do?action=list';
							    //});
							}
							else{
							    jAlert('删除失败!');  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示");
					}	
				}
			});
		}
 </script>
	</head>
	<body>
		<table width="780" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
					<td colspan="4">
						<s:actionerror theme="webframe0" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<s:actionmessage theme="webframe0" />
					</td>
				</tr>
			<!-- 
		    <a href='/selfconfig/mapcard.do?action=add' target='_self'>
			增加
			</a>
			 -->
			<tr>
				 <s:label value="悬赏记录" ></s:label>
			</tr>
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1"/>
				</td>
			</tr>
		</table>
		<table id="myTable" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" width="750">
			<thead>
				<tr>
					<th>
						标题
					</th>
					<th>
						内容
					</th>
					<th>
						类型
					</th>
					<th>
						发布人
					</th>
					<th>
						发布时间
					</th>
					<th>
						分值
					</th>
					<th>
						操作
					</th>					
				</tr>
			</thead>
			<tbody>
				<s:iterator id="reward" value="%{pageList.objectList}">
					<tr>
						<td class="bgColor3">
							 <span class="font1">
							 <a href='<%=path%>/selfconfig/reward.do?action=view&search.pid=<s:property value="#reward.ID" />' target='_self'>
							 <s:property value="#reward.TITLE" />
							 </a>
							 </span>
						</td>
						<td class="bgColor4">
							<s:property value="#reward.CONTENT" />
						</td>
						<td class="bgColor4">
							<s:property value="#reward.TYPE" />
						</td>
						<td class="bgColor4">
							<s:property value="#reward.USERID" />
						</td>
						<td class="bgColor4">
							<s:property value="#reward.CREATTIME" />
						</td>
						<td class="bgColor4">
							<s:property value="#reward.INTEGRAL" />
						</td>
						<td class="bgColor4">
						    <a href="javascript:fun_delete(<s:property value="#reward.ID" />)">
						    删除
						    </a>
							|
							<a href='<%=path%>/selfconfig/reward.do?action=edit&search.pid=<s:property value="#reward.ID" />' target='_self'>
                                修改
                            </a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="8">
					分页:
					<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>
