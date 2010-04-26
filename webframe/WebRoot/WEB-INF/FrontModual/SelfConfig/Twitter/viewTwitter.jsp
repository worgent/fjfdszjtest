<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript">
			//保存某一日志的评论
			function saveEditDo(path) {
			    var url = getActionMappingURL("/twitter",path);

			    url=url+"?action=editdo&content="+encodeURI(encodeURI($('#newWordContent').val()))+"&date=new Date()";
			   
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
							alert(mgr);
						}
					}catch(e){ 
						alert(e);
					}
					
			    
  			} 
		</script>
	</head>
	<body>
		<table width="780" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<hr size="3" noshade class="hrcolor1">
				</td>
			</tr>
		</table>

		<table width="700" border="0" align="center" cellpadding="10"
			cellspacing="0" class="table1">
			<tr>
				<td>
					<table width="70%" border="0" align="center" cellpadding="5"
						cellspacing="0">
						<tr>
							<td colspan="2">
								<strong>查看日志</strong>
							</td>
						</tr>

						<tr>
							<td width="34%">
								<div align="right">
									标题:
								</div>
							</td>
							<td width="66%">
								<s:property value="twitterTitle" />
							</td>
						</tr>
						<tr>
							<td width="34%">
								<div align="right">
									分类:
								</div>
							</td>
							<td width="66%">
								<s:property value="twitterTypeName" />
							</td>
						</tr>
						<tr>
							<td width="34%">
								<div align="right">
									内容:
								</div>
							</td>
							<td width="66%">
								<s:textarea cols="30" rows="20" name="twitterContent"
									id="twitterContent" cssClass="input2" readonly="true"></s:textarea>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div align="right"  style="width: 50%">
			<s:textarea id="newWordContent" name="newWordContent" cols="45"
				rows="8" cssClass="textarea1"></s:textarea>
			<input type="button" name="Submit" value="保存" class="button1"
				onclick="saveEditDo('<%=path %>');" />
		</div>
	</body>
</html>