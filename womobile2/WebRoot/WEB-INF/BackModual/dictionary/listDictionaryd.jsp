<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="webframe"%>
<%
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript">
<!--
	//加载修改页面
	function loadEditPage(id,state,name){
		document.getElementById("id1").value=id;
		document.getElementById("oldVal").value=name;
		document.getElementById("newVal").value=name;
		document.getElementById("state").value=state;
		document.getElementById("tab11").style.display="";
		document.getElementById("tab22").style.display="none";
	}
	
	//加载新增页面
	function loadAddPage(){
		document.getElementById("tab11").style.display="none";
		document.getElementById("tab22").style.display="";
	}
	
	function delDictionarydId(id,dictId,name){
		if(confirm("确认删除“"+name+"”吗？")){
			document.getElementById("tab11").style.display="none";
			document.getElementById("tab22").style.display="none";
			window.location.href="<%=path%>/dictionary.do?action=delDeictionaryd&search.id="+id+"&search.dictId="+dictId;
		}
	}
//-->
</script>
<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td colspan="5">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15" style="vertical-align: middle;">
						<img src="<%=path%>/css/images/tab_03.gif" width="15" />
					</td>
					<td background="<%=path%>/css/images/tab_05.gif">
						<img src="<%=path%>/css/images/311.gif" width="16" />
						<span class="STYLE4">${search.dictName}</span>
					</td>
					<td width="14">
						<img src="<%=path%>/css/images/tab_07.gif" width="14" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	 <tr>
    	<td colspan="5">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
		        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
		          <tr>
		            <td height="18" width="140" background="<%=path%>/css/images/tab_14.gif" class="STYLE1" align="center">值</td>
		            <td width="60" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1">状态</td>
		            <td width="140" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1">操作 [
			<strong><a href="javascript:;"
				onclick='loadAddPage();'>新增</a>
			</strong>]</td>
		          </tr>
		          <s:iterator value="%{dictionarydList}" id="d">
					<tr>
						<td height="18" bgcolor="#FFFFFF" class="STYLE2">
							<div align="center" class="STYLE2 STYLE1"><s:property value="#d.dictvalue" /></div>
						</td>
						<td height="18" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">
							<s:if test="#d.state==1">启用</s:if>
							<s:if test="#d.state==0">停用</s:if>
							</div>
						</td>
						<td height="18" bgcolor="#FFFFFF">
							<div align="center" class="STYLE2 STYLE1">
							<a href="javascript:;"
								onclick="loadEditPage('<s:property value="#d.id"/>','<s:property value="%{state}"/>','<s:property value="dictvalue"/>');">修改</a>|
							<a href="javascript:;"
								onclick="delDictionarydId('<s:property value="#d.id"/>',<s:property value='${search.dictId}'/>,'<s:property value="%{dictvalue}"/>')">删除</a>
							</div>
						</td>
					</tr>
				</s:iterator>		          
				</table>
				</td>
		        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
		      </tr>
    		</table>
    	</td>
 	</tr>
	
</table>
<br/>
<s:form action="dictionary" namespace="/" enctype="multipart/form-data">
<s:hidden name="action" value="updateDictionaryd"></s:hidden>
<s:hidden name="search.id" id="id1"></s:hidden>
<s:hidden name="search.dictId"></s:hidden>
<table width="100%" id="tab11" style="display: none">
	<tr><td colspan="2" align="center">修改</td></tr>
	<tr>
		<td align="right" width="50%">旧值：</td><td><input type="text" id="oldVal" name="oldVal" value="" disabled="disabled"></td>
	</tr>
	<tr>
		<td align="right">新值：</td>
		<td>
			<input type="text" id="newVal" name="search.newVal" value=""/>
			<s:select list="#{1:'启用',0:'停用'}" listKey="key" name="search.state" id="state" name="search.state" listValue="value" value="1" /> 
		</td>
	</tr>
	<tr><td colspan="2" align="center"><input type="submit" class="btn"  value="修改"/></td></tr>
</table>
</s:form>

<s:form action="dictionary" namespace="/" enctype="multipart/form-data">
<s:hidden name="action" value="addDictionaryd"></s:hidden>
<s:hidden name="search.dictId"></s:hidden>
<table width="100%" id="tab22" style="display: none">
	<tr><td colspan="2" align="center">添加</td></tr>
	<tr>
		<td align="right" width="50%">新值：</td>
		<td width="50%">
			<input type="text" id="newVal" name="search.newVal" value=""/>
			<s:select list="#{1:'启用',0:'停用'}" listKey="key" name="search.state" id="state" name="search.state" listValue="value" value="1" /> 
		</td>
	</tr>
	<tr><td colspan="2" align="center"><input type="submit" class="btn"  value="添加"/></td></tr>
</table>
</s:form>