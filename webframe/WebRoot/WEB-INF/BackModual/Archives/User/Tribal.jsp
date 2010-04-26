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
var dWin=null;
function uploadSmallPic(str){
var feather="dialogWidth=466px;scrollbars=yes;dialogHeight=200px";
dWin=showModelessDialog('<%=path%>/searchUser.jsp',window,feather);
}

  </script>

   <script type="text/javascript">
	     function Tribal(obj){
	     
	     var recCont=document.getElementById("chatSend").value;
	     if(recCont==''){alert("信息发布不能为空！"); return false;}
	     
	     
	     
	     document.getElementById("chatAccept").value=document.getElementById("chatAccept").value+"\n"+document.getElementById("memberUser").value+"\n"+document.getElementById("chatSend").value+"\n"+'------------';
	     
	    
	     
	     

				  var url ='<%=path%>/user.do?action=Release&search.chatSend='+document.getElementById("chatSend").value+'&search.memberUser='+document.getElementById("memberUser").value+'&search.mainUser='+document.getElementById("mainUser").value;
					
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
							
							document.getElementById("chatSend").value='';	
							alert("发布成功");
							return false;
							}
						}
						
						
						
					}catch(e){
						alert(e);
					}
					
					
				
					
			//return true;		
	     
	     
	     
	     
	     
	     
	     }
	     </script>

  </head>
<body>
<center>
<form  method='POST' name='form1' action='/user.do'  id="form1">
   <table border="1"  width="845" height="337">
    
    <tr>
   <td align="right" width="20%">群名称:</td>
   <td width="40%" align="left"> 
   <s:property value="search.GROUPNAME" />
     (<s:property value="search.MAINUSER" />)
   </td>
   <td width="40%">&nbsp;</td>
   </tr>
   
    <tr>
   <td  align="right">信息记录:</td>
   <td  >
   
        <table> 
        <tr>
          <td  valign="top"  align="left">
          <textarea  id="chatAccept" readonly  name="search.pchatAccept"  cols="60" rows="20">
          
          <s:iterator id="sel"  value="%{chatRecordList.objectList}">
          <s:property value="#sel.MEMBERUSER" />
          <s:property value="#sel.RECORDCONTENT" />
           ------------
          </s:iterator>
          
          
          </textarea> 
          
          
          
          </td>
        </tr>
         <tr>
          <td>  <textarea  id="chatSend" name="search.pchatSend" cols="60" rows="5"></textarea> </td>
        </tr>
        </table>
   
     
      
      
      
      
      
      
   </td>
   
   <td valign="top">
    
   <table border="1" width="208" height="457">
   <tr>
     <td   align="center" height="30%" valign="top">
                 公 告
     </td>
   </tr>
     <tr>
     <td height="70%" valign="top">
  
        <select name="leftSideCartoonCharacters" size="17" style="width:100%;height:100%" id="exampleSubmit_leftSideCartoonCharacters" multiple="multiple">
    		<option value="headerKey">--- 群成员区域 ---</option>
    		  <s:iterator id="sel"  value="%{tribalList.objectList}">
				<option value="<s:property value="#sel.CHATID" />"  >
				  <s:property value="#sel.MEMBERUSER" />
				</option>
			  </s:iterator>			 
		</select>
		
		
     </td>
   </tr>
   
   
   
   
   </table>
   
   
   
   
   </td>
   </tr>
   <tr>
   <td>&nbsp;</td>
   <td  align="right">
          <input type="button" id="saveId" name="save"  onclick="javascript:Tribal(this);"   value="发  送">
          </td>
   <td align="right">
          <a href="<%=path%>/searchUser.jsp?search.MAINUSER=<s:property value="search.MAINUSER"/>&search.chatid=<s:property value="search.chatid"/>">查 找</a>
          <input type="hidden" id="memberUser" name="memberUser"   value="<s:property value="search.MEMBERUSER" />" />
          <input type="hidden" id="mainUser" name="mainUser"   value="<s:property value="search.MAINUSER" />" />
   </td>
   </tr>
  
 </table>

</form>

</center>
</body>
</html>