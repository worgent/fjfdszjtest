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
	     function addFriend(obj){
	
				  var url ='<%=path%>/user.do?action=isExistUser&search.frienduser='+document.getElementById("frienduser").value;
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
		 document.forms[0].attributes[83].value='/user.do?action=addFriendSet';//ie7支持用attributes[83]
		}else{
		 document.forms[0].action='/user.do?action=addFriendSet';	//alert('firefox');firefox浏览器用action
		}
		 document.forms[0].submit();	
		 
							}
								
						}
					}catch(e){
						alert(e);
					}
			return true;				
	}
	
	
	
	 function checkSet(obj){
	     
	     if(document.forms[0].attributes[83]){
		 document.forms[0].attributes[83].value='/user.do?action=checkSet&search.pinvitetype='+document.getElementById("invitetypeValue").value;//ie7支持用attributes[83]
		}else{
		 document.forms[0].action='/user.do?action=checkSet&search.pinvitetype='+document.getElementById("invitetypeValue").value;	//alert('firefox');firefox浏览器用action
		}
		 document.forms[0].submit();	
}
	
		 function SetValue(obj){
	      document.getElementById("invitetypeValue").value=obj;
	      }
	</script>
	
	<script type="text/javascript">
		function fun_delete(FRIENDID,MAINUSER,FRIENDUSER){
		    if (!confirm('您确定删除该用户吗?')){
				return;
			}else{ 
			   var url = '/user.do?action=deleteFriend&search.FRIENDID='+FRIENDID+'&search.mainuser='+MAINUSER+'&search.frienduser='+FRIENDUSER;
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
		 document.forms[0].attributes[83].value='<%=path%>/user.do?action=friendSet';//ie7支持用attributes[83]
		}else{
		 document.forms[0].action='<%=path%>/user.do?action=friendSet';	//alert('firefox');firefox浏览器用action
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
<form  method='POST' name='form1'   id="form1">
   <table border="1"  width="90%">

    <tr>
   
   <td>
    <a href="<%=request.getContextPath() %>/user.do?action=friendSet"  target="webframe_mainFrame">我的好友</a>
           
    <a href="<%=request.getContextPath() %>/user.do?action=friendBlack"  target="webframe_mainFrame">  黑名单  </a>  
    </td>
  
   </tr>
   
    <tr>
   
   <td>&nbsp; 
    
    		
				
				    
				
				    
				
				
   </td>
   
   
   </tr>

   
   <tr>
   
   <td  align="left">
      <div style="padding: 7px; margin-top: 10px; margin-left: 30px; width: 92%; text-align: left;" class="goodfriend"><li class="g">添加好友说明：<br>
</li><li style="line-height: 22px;">1、 输入猎图网的用户帐号，及要发送的信息，点击发送；<br>
</li><li style="line-height: 22px;">2、 如果对方设置为&ldquo;允许任何人把我列为好友&rdquo;，您就可以成功加他（她）为好友；<br>
</li><li style="line-height: 22px;">3、 
如果对方设置为&ldquo;需要身份认证才能把我列为好友&rdquo;，则要等对方接受您的邀请后，您才能收到&ldquo;好友接受您的交友请求&rdquo;的短消息。</li></div><font color=red></font>
   </td>
  
   </tr>
  

    <tr>
   

   <td>&nbsp;</td>
   </tr>
    
    <tr>

   <td align="left">
   
   <table width="827" height="147">
   <tr>
   <td>
       <table>
       <tr>
         <td>   向<input type="text" id="frienduser" name="search.frienduser"></input>发送交友邀请：</td>
       </tr>
       
         <tr>
         <td> 
          <textarea id="sendcontext" name="search.sendcontext" rows="6" cols="35"></textarea> 
          
          
          </td>
       </tr>
       
         <tr>
         <td  align="right"> 
         <table>
         <tr><td><font color="#ff0000"><s:actionmessage theme="webframe0"/></font>
         <font color="#ff0000"><s:actionerror theme="webframe0"/></font>  </td>
         <td>   <input type="button" onClick="javascript:addFriend(this);" value=" 发 送 " /></td>
         </tr>
         </table>
        
         
         
         
         
          </td>
       </tr>
       
       </table>
        
     
            
   </td>
   
   <td>
   
   <table>
     <tr><td><strong>» 身份验证设置</strong></td></tr>
     <tr>
        <td>
       <input type="radio" value="0" name="search.invitetype"   onclick="javascript:SetValue(0);" id="invitetype" <s:if test="search.uinvitetype==0">checked</s:if> /> 允许任何人把我列为好友
       </td>
      </tr>
     <tr>
     <td>
     <input type="radio" value="1" name="search.invitetype" onclick="javascript:SetValue(1);" id="invitetype" <s:if test="search.uinvitetype==1">checked</s:if>  >需要身份认证才能把我列为好友</td></tr>
     <tr>
     <td>
     <input type="radio" value="2" name="search.invitetype" onclick="javascript:SetValue(2);" id="invitetype" <s:if test="search.uinvitetype==2">checked</s:if> >不允许任何人把我列为好友</td></tr>
      <tr><td  align="right">
       <input type="button" onClick="javascript:checkSet(this);" value="设 置" />
       <input type="hidden"  name="search.invitetypeValue"  id="invitetypeValue" />
    
    </td></tr>
   </table>
   
      
   </td>
   </tr>
   </table>
    </td>
  
   </tr>
				
    <tr>
  
   <td align="left">
       <br> 
   </td>
   
   </tr>
				 
   <tr>
   
   <td align="left">
     &raquo; 好友列表 
   </td>
  
   </tr>
   
   
   <tr>
   <td>&nbsp;
   
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
					邀请时间
					</th>
					<th>
					接受时间
					</th>
					<th>
					状 态
					</th>
					
					<th>
					 操作
					</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator id="frie" value="%{pageFriendList.objectList}">
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
							<td  class="bgColor4">
						  	<s:property value="#frie.ACCEPTTIME" />
						</td>
							<td  class="bgColor4">
							<s:if test="#frie.STATE==0">
							<font color="#ff0000">
							未接受
							</font>
							</s:if>
							
						    <s:if test="#frie.STATE==1">
							<font color="#ff0000">
							已接受
							</font>
							</s:if>  	
						</td>
						<td  class="bgColor4" align="left">
						 <s:if test="#frie.STATE==0">
                         <s:if test="#frie.MAINUSER!=#frie.SEND">
                          <a href="<%=path%>/user.do?action=updateAccept&search.friendid=<s:property value="#frie.FRIENDID"/>">
                              我要接受
                          </a>
						 </s:if>
						 </s:if>
						 
						   <a href="javascript:fun_delete(<s:property value="#frie.FRIENDID"/>,<s:property value="#frie.MAINUSER"/>,<s:property value="#frie.FRIENDUSER"/>);">删除</a>
						
						
						</td>
						</tr>
				</s:iterator>
			</tbody>
			<tr class="bgColor3">
				<td colspan="29">
					分页:
					<qzgf:pages value="%{pageFriendList.pages}" />
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