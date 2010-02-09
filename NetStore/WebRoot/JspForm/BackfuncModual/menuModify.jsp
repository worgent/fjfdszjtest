<%@ page language="java" import="com.fredck.FCKeditor.*,java.util.Map" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<html:base />
	<title>菜单信息修改</title>
</head>

<body background="<%=request.getContextPath()%>/images/bg.gif">
	<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/menu.do"
		method="post">
		<center>
			<table border=1 width="60%">
			 <logic:present name="menuMap">
				<tr>
					<td colspan="2" align="center">
						菜单信息修改
					</td>
				</tr>
				<tr>
					<td>
						菜单编号
					</td>
					<td>
						<input type="text" name="theCode" value="${menuMap.theCode }"/>
					</td>
				</tr>
				<tr>
					<td>
						菜单名称
					</td>
					<td>
						<input type="text" name="theName" value="${menuMap.theName}"/>
					</td>
				</tr>
    			<tr>
    					<td>
    					父结点:
    					</td>
    					<td>
    				<select  name="theParentCode" onchange="fnChange()">
    	              <logic:present name="ParentNameList">
	                    <logic:iterate id="Pitem" name="ParentNameList" scope="request">
						<logic:equal name="Pitem" property="theCode" value="${menuMap.theParentCode}" >
						<option value="${Pitem.theCode}" select=true>${Pitem.theName}</option>			
						</logic:equal>
						<logic:notEqual name="Pitem" property="theCode" value="${menuMap.theParentCode}" >
						<option value="${Pitem.theCode}" >${Pitem.theName}</option>			
	                    </logic:notEqual>
	                   </logic:iterate>
	                   </logic:present>
	                  </select>
    					</td>
    				</tr>  
				<tr>
					<td>
						地址
					</td>
					<td>
						<input type="text" name="theUrl" value="${menuMap.theUrl}" />
					</td>
				</tr>
				<tr>
					<td>
						备注
					</td>
					<td>
						<input type="text" name="theRemark" value="${menuMap.theRemark}"/>
					</td>
				</tr>
				<tr>
					<td>
						排序编号
					</td>
					<td>
						<input type="text" name="theOrderId" value="${menuMap.theOrderId}" />
					</td>
				</tr>
			    <tr>
					<td>
						菜单简介
					</td>
					<td  valign="top">
						<%
						// 获得上下文路径
						String path = request.getContextPath();
						FCKeditor oFCKeditor;
						// 定义一个属性来使Action通过request来获得FCKeditor编辑器中的值
						oFCKeditor = new FCKeditor(request, "theRemark");
						oFCKeditor.setBasePath(path + "/fckeditor/");
						// 设置FCKeditor编辑器打开时的默认值
						Map map=(Map)request.getAttribute("menuMap");
						oFCKeditor.setValue((String)map.get("theRemark"));
						out.println(oFCKeditor.create());
						%>
					</td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="hidden" name="status" value="updateMenu"/>
						<input type="submit" value="保存" />
						<input type="button" value="返回"  onclick="javascript:self.history.back();"/>
					</td>
				</tr>
			</logic:present>	
			</table>
		</center>
	</form>
</body>
</html:html>
<logic:present name="xgResult">
   <script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>
