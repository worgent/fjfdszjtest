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
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
		<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
	</head>
<body>
   <table width="90%" height="190" border="0">
   <tr>
   <td  align="center" colspan=3 height="30" style="vertical-align:middle;"> 
       <strong>事项申报</strong>
   </td>
   </tr>
    <tr>
	<td colspan="3">
		<s:actionerror theme="ems" /><s:actionmessage theme="ems" />
	</td>
  </tr>  
   <tr> 
   <td align="left" class="tdex" style="font-size:12px">主&nbsp;&nbsp;&nbsp;&nbsp;题:
   <s:property value="%{tdeclare.title}"/>
   </td>
   <td align="left" class="tdex" style="font-size:12px">登记时间:
   <s:property value="%{tdeclare.makedatetime}"/>
   </td>  
   
  <td align="left" class="tdex" style="font-size:12px">登&nbsp;记&nbsp;人:
   <s:property value="%{tdeclare.maker}"/>
   </td>
   </tr>   
   <tr> 
   <td align="left" class="tdex" style="font-size:12px">申报类别:
   <s:property value="%{tdeclare.declaretypename}"/>
   </td>

 <td align="left" class="tdex" style="font-size:12px">任务级别:
   <s:property value="%{tdeclare.missiongradename}"/>
   </td>
  <td align="left" class="tdex" style="font-size:12px">附&nbsp;&nbsp;&nbsp;&nbsp;件:
   <s:property value="%{tdeclare.attachment}"/>
   </td>
  
   </tr>   
 
   <tr> 
   <td align="left" class="tdex" style="font-size:12px">指&nbsp;&nbsp;&nbsp;&nbsp;标:
   <s:property value="%{tdeclare.target}"/>值:
   <s:property value="%{tdeclare.targetvalue}"/>
   </td>
   
   <td align="left" class="tdex" style="font-size:12px">事项申报模板:
   <s:property value="%{tdeclare.declarepatternname}"/>
   </td>
   
   <td align="left" class="tdex" style="font-size:12px">执&nbsp;行&nbsp;人:
   <s:property value="%{tdeclare.executors}"/>
   </td>
   
   </tr>   
 
   <tr> 
   <td colspan="3" align="left" class="tdex" style="font-size:12px">描&nbsp;&nbsp;&nbsp;&nbsp;述:
   <s:property value="%{tdeclare.remark}"/>
   </td>
   </tr>    
   <tr> 
   <td colspan="3" align="left" class="tdex" style="font-size:12px">任务状态:
   <s:property value="%{tdeclare.state}"/>
   </td>
   </tr>   
   <tr>
   <td align="center" colspan="3">
      <input type="button" onclick="javascript:history.go(-1)" value="返回" class="btn"/>
   </td>  
   </tr>
 </table>

<div id="f_main" style="display:inline;width:100%;position:relative;left:0px;top:0px;padding-top:0px;padding-left:0px;">
	<div id="defaultlist"></div>
</div>
		<script language="javascript" type="text/javascript">	
		    //加载默认页面    
		    $(document).ready(function(){
			    //默认加载页面
			    loadSearchList('<%=path%>/work/tdeclare.do?action=feeddetail&pid=<s:property value="%{tdeclare.feedbackpattern}"/>','',1,1);
			})
		</script>
</body>
</html>