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
			   var url = '<%=path%>/selfconfig/bulletin.do?action=delete&search.pid='+pid
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
						   window.location.href='<%=path%>/selfconfig/bulletin.do?action=list&search.ptype=3';
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
	  <!-- 
	  <div>
		  <ul>
             <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/selfconfig/bulletin.do?action=list&search.ptype=1" title='优惠劵'>优惠劵</a></li>
             <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/selfconfig/bulletin.do?action=list&search.ptype=2" title='狩猎贴'>狩猎贴</a></li>
             <li style="float:left;margin:0px 10px 0 0;list-style-type:none;"><a href="/selfconfig/bulletin.do?action=list&search.ptype=3" title='领主招纳'>领主招纳</a></li>
           </ul>
       </div>
        -->
		<table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
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
		    <a href='<%=path%>/selfconfig/bulletin.do?action=add&search.ptype=3' target='_self'>
			增加
			</a>
			<tr>
				 <s:label value="领主招纳信息" ></s:label>
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
					<th width="47%">
						领主招纳内容
					</th>
					<th width="10%">
						发布者
					</th>
					<th width="15%">
						创建时间
					</th>
					<th width="8%">
						 关注数
					</th>
					<th width="20%">
						操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="bulletin" value="%{pageList.objectList}">
					<tr>
						<td class="bgColor3">
							 <span class="font1">
							 <a href='<%=path%>/selfconfig/bulletin.do?action=view&search.ptype=3&search.pid=<s:property value="#bulletin.ID" />' target='_self'>
							 <s:property value="#bulletin.BULLETINCONTENT" />
							 </a>
							 </span>
						</td>
						<td class="bgColor4">
							<s:property value="#bulletin.USERNAME" />
						</td>
						<td class="bgColor4">
							<s:property value="#bulletin.CREATETIME" />
						</td>
						<td align="center">
							<s:property value="#bulletin.COUNTRECEVE" />
						</td>
						<td class="bgColor4">
						    <a href="javascript:fun_delete(<s:property value="#bulletin.ID" />)">
						    删除
						    </a>
							|
							<a href='<%=path%>/selfconfig/bulletin.do?action=edit&search.ptype=3&search.pid=<s:property value="#bulletin.ID" />' target='_self'>
                                修改
                            </a>
                            |
                            <a href='<%=path%>/selfconfig/bulletin.do?action=replist&search.pbulletinid=<s:property value="#bulletin.ID" />' target='_self'>
							关注明细
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