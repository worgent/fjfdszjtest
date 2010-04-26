<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<script type="text/javascript">
		$(document).ready(function(){ 
			$("#myTable").tablesorter({ 
				headers: { 
					1: {sorter: false }
				}   
			}); 
		}); 
		</script>
		<script type="text/javascript">
		function fun_delete(USERID){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
			
			   
			   var url = '/user.do?action=delete&search.USERID='+USERID;
				try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false;
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
						     window.location.href='<%=path%>/user.do?action=list';
						}
						else{
						    alert('删除失败!');
						}
					}
	
				}catch(e){ 
					//alert(e);
				}		
			}
		}
 </script>
 
 
 
 
 
 
 <script type="text/javascript">
 function fun_edit(SPACEID){
 
  var url = '/space.do?action=edit&search.SPACEID='+SPACEID;
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
	
				}catch(e){
					//alert(e);
				}	
				
 }
 </script>
 
</head>
	<body>
		<table  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>
		<table  border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
				<td  align="right">
				 &nbsp;
				</td>
			</tr>
		</table>
		<table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					用户名
					</th>
					<th>
					性别
					</th>
					<th>
					地图位置标注
					</th>
					<th>
					经验值	
					</th>
					<th>
					贡献值
					</th>
					<th>
					名望值
					</th>
					<th>
					实力值
					</th>
					<th>
					人气值	
					</th>
					<th>
					财力值
					</th>
					<th> 
					安全问题 
					</th>
					
					
					
					<th>
					安全答案
					</th>
					<th>
					猎友实名
					</th>
					<th>
					手机号码
					</th>
					<th>
					职业
					</th>
					<th>
					行业
					</th>
					<th>
					生日
					</th>
					<th>
					邮箱
					</th>
					<th>
					QQ
					</th>
					<th>
					工作位置
					</th>
					<th>
					推荐者
					</th>
					
					
					
					
					<th>
					喜爱的地方
					</th>
					<th>
					常去的地方
					</th>
					<th>
					常消费的地方
					</th>
					<th>
					大部分时间做的事
					</th>
					<th>
					常参加的活动
					</th>
					<th>
					看过的书
					</th>
					<th>
					兴趣爱好
					</th>
					<th>
					传音入密
					</th>


					 <th>
					操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="user" value="%{pageUserList.objectList}">
					<tr>
						<td  class="bgColor3">
							<span class="font1"> <s:property value="#user.USERNAME" /> </span>
						</td>
						<td  class="bgColor4">
							<s:property value="#user.SEX" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.POSITION" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.EXPERIENCE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.CONTRIBUTE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.FAME" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.STRENGTH" />
						</td>
					    <td  class="bgColor4">
							<s:property value="#user.FAVOR" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.FINANCIAL" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.SAFEQUESTION" />
						</td>

						<td  class="bgColor3">
							<span class="font1"> <s:property value="#user.SAFEANSWER" /> </span>
						</td>
						<td  class="bgColor4">
						
							<s:property value="#user.REALNAME" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.TELEPHONE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.OCCUPATION" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.VOCATION" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.BIRTHDAY" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.EMAIL" />
						</td>
					    <td  class="bgColor4">
							<s:property value="#user.QQ" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.WORKPLACE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.COMMEND" />
						</td>
						
						
						
						<td  class="bgColor3">
							<span class="font1"> <s:property value="#user.FAVORPLACE" /> </span>
						</td>
						<td  class="bgColor4">
						
							<s:property value="#user.OFTENGOPLACE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.OFTENCONSUMEPLACE" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.OFTENDOTHING" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.OFTENJOINACTIVITY" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.LOOKEDBOOK" />
						</td>
						<td  class="bgColor4">
							<s:property value="#user.INTERESTLIKE" />
						</td>
					    <td  class="bgColor4">
							<s:property value="#user.NEWMOOD" />
						</td>
					
						
						<td  class="bgColor4" align="left">
							<a href="/user.do?action=editList&search.USERID=<s:property value="#user.USERID"/>&search.pusername=<s:property value="#user.USERNAME"/>" >编辑</a> |
							<a href="javascript:fun_delete(<s:property value="#user.USERID"/>);">删除</a>
						</td>
						
						
					</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageUserList.pages}" />
				</td>
			</tr>
		</table>
	</body>
</html>





