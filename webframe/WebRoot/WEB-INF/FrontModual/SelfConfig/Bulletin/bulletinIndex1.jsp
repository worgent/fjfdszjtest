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
		function fun_countReceve(pid){
		jConfirm('您确定下载该优惠劵!', 'Confirmation Dialog', function(r) {	
    		if (r){
			   var url = '<%=path%>/selfconfig/bulletin.do?action=countReceve&search.pid='+pid
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
						   window.location.href='<%=path%>/selfconfig/bulletin.do?action=index&search.ptype=1';
						}
						else{
						    jAlert('下载失败!');  
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
	   <!-- 导航
	   <div>
		  <ul>
             <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/selfconfig/bulletin.do?action=index&search.ptype=1" title='优惠劵'>优惠劵</a></li>
             <li style="float:left;margin:0px 50px 0 0;list-style-type:none;"><a href="/selfconfig/bulletin.do?action=index&search.ptype=2" title='狩猎贴'>狩猎贴</a></li>
             <li style="float:left;margin:0px 10px 0 0;list-style-type:none;"><a href="/selfconfig/bulletin.do?action=index&search.ptype=3" title='领主招纳'>领主招纳</a></li>
           </ul>
       </div>
        -->
       <!--消息具体内容 -->
	    <div>
		<table  style="height: 90px;  width: 90%;">
			<thead>
				<tr>
					<th>
						优惠劵内容
					</th>
					<th width="10%">
						优惠劵数
					</th>
					<th width="10%">
						下载数
					</th>
					<th  width="10%" align="center">
						发布者
					</th>
					<th width="20%" align="center">
						创建时间
					</th>
					<th width="10%" align="center">
						操作
					</th>									
				</tr>
			</thead>
			<tbody>
				<s:iterator id="bulletin" value="%{pageList.objectList}">
					<tr>
						<td align="left">
							 <span class="font1">
							 <a href='<%=path%>/selfconfig/bulletin.do?action=view&search.ptype=1&search.pid=<s:property value="#bulletin.ID" />' target='_self'>
							 <s:property value="#bulletin.BULLETINCONTENT" />
							 </a>
							 </span>
						</td>
						<td align="center">
							<s:property value="#bulletin.COUNT" />
						</td>
						<td align="center">
							<s:property value="#bulletin.COUNTRECEVE" />
						</td>
						<td align="center">
							<s:property value="#bulletin.USERNAME" />
						</td>
						<td align="center">
							<s:property value="#bulletin.CREATETIME" />
						</td>
						<td align="center">
							<a href="javascript:fun_countReceve(<s:property value="#bulletin.ID" />)"/>
							下载
                            </a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			<tr>
				<td colspan="8">
					分页:
					<qzgf:pages value="%{pageList.pages}" />
				</td>
			</tr>
		</table>
		</div>
	</body>
</html>