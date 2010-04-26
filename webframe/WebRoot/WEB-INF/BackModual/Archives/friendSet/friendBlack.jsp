<%@ page language="java"  import="java.util.Date" errorPage="/error.jsp" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>

<jsp:directive.page import="java.util.List" />
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@include file="/common/taglibs.jsp"%>
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
  <head>
  
 
	
	<script type="text/javascript">
	     function addFriendBlack(obj){
	
	             var  frienduser=document.getElementById("frienduser").value;
				  var url ='<%=path%>/user.do?action=isExistBlackUser&search.frienduser='+frienduser;
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url); 
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return ;
						} else {
							root = oXMLDom.documentElement;
						}
						if (null != root){
							var rowSet = root.selectNodes("//check");
							var mgr=rowSet.item(0).selectSingleNode("value").text;
							if(mgr!=0){
							alert(mgr);
							return false;
							}
							else
							{
						
		if(document.forms[0].attributes[83]){
		 document.forms[0].attributes[83].value='/user.do?action=setFriendBlack';//ie7支持用attributes[83]
		}else{
		 document.forms[0].action='/user.do?action=setFriendBlack';	//alert('firefox');firefox浏览器用action
		}
		 document.forms[0].submit();	
		 
							}
								
						}
					}catch(e){
						alert(e);
					}
			return true;				
	}
	
	</script>
	
	
	<script type="text/javascript">
		function fun_delete(FRIENDID,MAINUSER,FRIENDUSER){
		    if (!confirm('您确定删除该黑名单吗?')){
				return;
			}else{ 
			   var url = '/user.do?action=deleteFriendBlack&search.FRIENDID='+FRIENDID+'&search.mainuser='+MAINUSER+'&search.frienduser='+FRIENDUSER;
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
					 
						 					
		if(document.forms[0].attributes[83]){
		 document.forms[0].attributes[83].value='<%=path%>/user.do?action=friendBlack';//ie7支持用attributes[83]
		}else{
		 document.forms[0].action='<%=path%>/user.do?action=friendBlack';	//alert('firefox');firefox浏览器用action
		}
		 document.forms[0].submit();	
						 
						 
						 
						 
						 
						 
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
	
	
	
	
	
	
	
	
  </head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do'  id="form1">
   <table border="1"  width="90%">

    <tr>
   
   <td>
   <a href="<%=request.getContextPath() %>/user.do?action=friendSet"  target="webframe_mainFrame">我的好友</a>
           
    <a href="<%=request.getContextPath() %>/user.do?action=friendBlack"  target="webframe_mainFrame">  黑名单  </a>  
   
   
    </td>
  
   </tr>
   
    <tr>
  
   <td>
   
    &nbsp;
   </td>
   
   
   </tr>

   
   <tr>
   
   <td>
   把<input type="text" id="frienduser" name="search.frienduser"></input>加入黑名单
   <input type="button"   onclick="javascript:addFriendBlack(this);" value="添 加" />
   &nbsp;
    
   </td>
  
   </tr>
   
   
   
   
    <tr>
   
   <td>&nbsp;<br></td>
   
   </tr>
   
    <tr>
   
   <td>黑名单<br></td>
   
   </tr>

   
   
   <tr>
  
   <td  align="center">
     
   <table id="myTable" width="150%" class="tablesorter" align="center" border="0"
			cellpadding="0" cellspacing="1" >
			<thead>
				<tr>
					<th>
					用户帐号
					</th>
					<th>
					昵称
					</th>
				    <th>
					加入时间
					</th>
				
					<th>
					 操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="frie" value="%{pageFriBlackList.objectList}">
					<tr>
						<td  class="bgColor3">
						    <s:property value="#frie.FRIENDUSER" />
						</td>
						<td  class="bgColor4">
						  	<s:property value="#frie.NICKNAME" />
						</td>
						<td  class="bgColor4">
						  	<s:property value="#frie.SENDTIME" />
						</td>
						
						   <td  class="bgColor4" align="left">
 <a href="javascript:fun_delete(<s:property value="#frie.FRIENDID"/>,<s:property value="#frie.MAINUSER"/>,<s:property value="#frie.FRIENDUSER"/>);">删除</a>
                           </td>
						</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageFriBlackList.pages}" />
				</td>
			</tr>
		</table>
   
     
     
     </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>