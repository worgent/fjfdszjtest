<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>自定义列表</title>		
		<script type="text/javascript">
			//进入新增模板页面
			function newPattern(url){
				//alert("new");
				var url = "<%=basePath%>/custom.do?date="+new Date();
			    window.open(url,'frmcenter','location=no '); 
			}
		</script>
	</head>
	<body>
		<s:form method='POST' name="custlist"  id="custlist"  action="custom" namespace="/" enctype="multipart/form-data">
		<s:hidden name="actionType" id="actionType" value="query"/>
<table width="500" align="center" cellspacing=0 cellpadding="0" border="0" class="tableex">
   <tr class="trex">
   <td  align="center" colspan=3 height="50" style="vertical-align:middle;">
       <strong>反馈单模板</strong>
   </td>
   </tr>
     <tr>
		<td  class="tdex" style="font-size:12px;" align="center">
		模板类别：
		<select name="custom.patternType" style="editcss" >
			<option value="0" <s:if test="%{custom.patternType==0}">selected="selected"</s:if>>
			---全部---
			</option>
			<option value="1" <s:if test="%{custom.patternType==1}">selected="selected"</s:if>>
			日常工作
			</option>
			<option value="2" <s:if test="%{custom.patternType==2}">selected="selected"</s:if>>
			督办工作
			</option>
			<option value="3" <s:if test="%{custom.patternType==3}">selected="selected"</s:if>>
			事项申报
			</option>
		</select>
		</td>
		<td class="tdex" style="font-size:12px">
		  <input type="submit" value="查询" class="btn"  />&nbsp;&nbsp;
		  <input type="button" value="新增" onclick="newPattern()" class="btn"/></td>
		</tr>			
	</table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="vertical-align:middle;"><img src="<%=path%>/css/images/tab_03.gif" width="15" /></td>
        <td background="<%=path%>/css/images/tab_05.gif"><img src="<%=path%>/css/images/311.gif" width="16" /> <span class="STYLE4">反馈单模板</span></td>
        <td width="14"><img src="<%=path%>/css/images/tab_07.gif" width="14"  /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
          <tr>
           
            <td height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">模板名称</div></td>
            <td width="60" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">模板类型</div></td>
            <td width="60" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">状态</div></td>
            <td height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">备注</div></td>
            <td width="140" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">创建时间</div></td>
            <td width="140" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">操作</div></td>
          </tr>
          
          <s:iterator id="g" value="%{customList}">
          <tr>
            
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.PatternName" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
            <s:if test="#g.PatternType==1">
					日常工作
					</s:if>
					<s:if test="#g.PatternType==2">
					督办工作
					</s:if>
					<s:if test="#g.PatternType==3">
					事项申报
					</s:if></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
                 <s:if test="#g.State==1">
					启用
					</s:if>
					<s:if test="#g.State==0">
					停用
					</s:if></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.Remark" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g.MakeDatetime" /></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=path%>/css/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="custom.do?actionType=toEditPage&custom.id=<s:property value='#g.id'/>">修改</a><span class="STYLE1">]</span> 
					&nbsp; 
					<img src="<%=path%>/css/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="custom.do?actionType=del&custom.id=<s:property value='#g.id'/>">删除</a><span class="STYLE1">]</span></div></td>
         </tr>
		 </s:iterator>
        </table></td>
        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">
              分页:<webframe:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
            </span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>	
			</s:form>
			
	</body>
</html>