<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>

		<title>福建省183速递官方网站</title>
		<link href="<%=path%>/css/indexnew.CSS"  type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
	</head>
     <script type="text/javascript">
		function fun_delete(newsid){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			
			   var url = '/news.do?action=delRecord&search.newsid='+newsid;
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
						    
					 
						 window.location.href='<%=path%>/news.do?action=index';
	    
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
	 <body ><!-- 装饰器屏蔽了background -->
	 <center>
	 <table border="0" cellpadding="0" style="border-collapse: collapse" width="700" id="table3" height="648" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">
			
			
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6" height="272">
				<tr>
					<td width="884" valign="top" bgcolor="#E5E5E5">
					<table border="0" width="100%" id="table49" style="border-collapse: collapse" cellpadding="0">
						<tr>
							<td>　</td>
						</tr>
					</table>
					<div align="center">
						<table border="0" width="687" id="table50" cellpadding="0" style="border-collapse: collapse">
							<tr>
								<td>
								<table border="0" cellpadding="0" style="border-collapse: collapse" width="687" id="table54" background="images/nei01.jpg" height="34">
									<tr>
										<td width="38">　</td>
										<td align="left">
										<font color="#FFFFFF" style="font-size: 10pt">
										<span style="font-weight: 700"><strong> <font color="#ff0000">图片信息</font>
					                   </strong></span></font><br></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td background="images/nei02.jpg">
								<span id="TabSkinContainer44">
								<span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent" class="Normal">
								<table border="0" cellSpacing="0" cellPadding="0" width="100%" align="center" id="table52">
									
									<tr>
										<td height="12">
											
										
										<div align="center">
											
		
		<table border="0" width="100%" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="right" width="90%">
				  <a href="picture.do?action=add">添 加</a>
				</td>
				<td align="right" width="10%">
				&nbsp;
				</td>	
			</tr>
		</table>
		<table id="myTable"  class="tablesorter" align="center"
			border="0" cellpadding="0" cellspacing="1">
			
				<tr class="trClass">
				 <td>
			      <strong>图片说明</strong>
		         </td>
		         
		          <td>
			      <strong>图片</strong>
		         </td>
                 <td>
			      <strong>发布时间</strong>
		         </td>
		          <td>
			      <strong>是否审核</strong>
		         </td>
		          <td>
			      <strong>操作</strong>
		         </td>
				</tr>
			
		
				<s:iterator id="pic" value="%{pageList.objectList}">
					<tr>
						<td class="bgColor3">
							<s:property value="#pic.EXPLAIN" />	
						</td>
						<td class="bgColor4">
							<s:property value="#pic.PICPATH" />
						</td>
						
						<td class="bgColor4">
							<s:property value="#pic.RELEASETIME" />
						</td>
						<td class="bgColor4">
							<s:if test="#pic.ISAUDIT==1">已审核</s:if>
							<s:if test="#pic.ISAUDIT!=1">未审核</s:if>
						</td>
                        <td class="bgColor4" align="left">
							<a href="javascript:fun_delete(<s:property value="#pic.NEWSID"/>);">删除</a>
						</td>
					</tr>
				</s:iterator>
			
			<tr class="bgColor3">
				<s:if test="%{pageList.pages!=null}">
					
						<td colspan="7">
							分页:
							<qzgf:pages value="%{pageList.pages}" />
						</td>
					
				</s:if>
			</tr>
		</table>
										</div>
										
										<p style="LINE-HEIGHT: 150%; MARGIN: 0cm 0cm 0pt" class="MsoNormal">
										<b>
										<span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt">
										</span></b><span id="TabSkinContainer48"><span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent3" class="Normal"></span></span><b><span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt"><br></span></b></p>
										
										<p style="LINE-HEIGHT: 150%; MARGIN: 0cm 0cm 0pt" class="MsoNormal">
										<b>
										<span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt">
										</span></b><span id="TabSkinContainer49"><span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent4" class="Normal"></span></span><b><span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt"><br></span></b></p>
										
									

										<p style="text-align: left; line-height: 150%; margin-left: 0cm; margin-right: 0cm; margin-top: 0cm; margin-bottom: 0pt" class="MsoNormal" align="left">　</p>
										<br>
　<p style="text-align: center; line-height: 200%; text-indent: 18.05pt; margin-left: 0cm; margin-right: 0cm; margin-top: 0cm; margin-bottom: 0pt" class="MsoNormal" align="center">
										<b>
										<span style="line-height: 200%; font-family: 宋体; color: #333333; font-size: 9pt">
										详情请咨询<font face="宋体" color="#333333"><span lang="EN-US">“</span>在线客服<span lang="EN-US">”</span>或当地客服热线：</font></span></b><span lang="en-us"><b><font face="宋体" style="font-size: 9pt" color="#333333">11185</font></b></span></p>
										&nbsp; </td>
									</tr>
								</table>
								</span></span>
								<p>　</td>
							</tr>
							<tr>
								<td>
								<img border="0" src="images/nei03.jpg" width="687" height="4"></td>
							</tr>
						</table>
						<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table51">
							<tr>
								<td>　</td>
							</tr>
						</table>
					</div>
					</td>
					
				</tr>
			</table>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table10" background="images/di.jpg" height="27">
				<tr>
					<td valign="middle"><span style="font-size: 4pt"><br>
					</span><span style="font-size: 9pt">&nbsp;<font color="#FFFFFF">All 
					rights reserved. 2010&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					技术支持 by </font><font color="#02687F">泉州市冠发信息科技有限公司</font></span></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
