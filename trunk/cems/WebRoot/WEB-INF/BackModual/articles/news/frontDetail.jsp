<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
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
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
	</head>
	<style type="text/css"> 

a:link {color: #000000; text-decoration:none} 
a:visited {color:#000000; text-decoration: none} 
a:active {color:#000000; text-decoration: none} 
a:hover {color:#ff0000; text-decoration:underline} 

</style> 
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
  <script type="text/javascript">
    function chgTDColor(oTD) {
	
	if(oTD.style.backgroundColor == "") {
		oTD.style.backgroundColor = "#990099"
		if(oTD.className == "header") {
			oTD.style.color = "#00ff00";
		}
	} else {
		oTD.style.backgroundColor = "";
		if(oTD.className == "header") {
			oTD.style.color = "#0000ff";
		}
	}
    }  
</script>
<script type="text/javascript">	
	document.body.style.backgroundImage="url(images/bg.jpg)";
</script>
	 <body ><!-- 装饰器屏蔽了background -->
	 <center>
	 <table border="4" cellpadding="0" style="border-collapse: collapse" width="969" id="table3" height="100%" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table4">
				<tr>
					<td>
					<img border="0" src="images/top1.jpg" width="518" height="76"><img border="0" src="images/top2.jpg" width="443" height="76"></td>
					<td>　</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="961" id="table5" background="images/nav.jpg" height="40">
				<tr>
					<td width="145" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt"><a href="/news.do?action=fir">首页</a></font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt"><a href="/news.do?action=FrontNews&search.artype=1">企业品牌</a></font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt"><a href="/news.do?action=FrontNews&search.artype=2">企业文化</a></font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt"><a href="/news.do?action=FrontNews&search.artype=3">新闻动态</a></font></b></td>
					
					<td width="167" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">产品服务</font></b></td>
					<td width="157" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">专项营销</font></b></td>
				</tr>
			</table>
			<table border="2" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6" height="272">
				<tr>
					<td width="884"  height="100%" valign="top" bgcolor="#e5e5e5">
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
										<span style="font-weight: 700"><strong> <font color="#ff0000"> 
										<s:if test="search.ARTICLESTYPE==1">企业品牌</s:if>
					                    <s:elseif test="search.ARTICLESTYPE==2">企业文化</s:elseif> 
					                    <s:elseif test="search.ARTICLESTYPE==3">新闻动态</s:elseif> 
					                    <s:elseif test="search.ARTICLESTYPE==4">EMS新闻</s:elseif>  
					                    <s:elseif test="search.ARTICLESTYPE==5">行业动态</s:elseif> 
					                    <s:elseif test="search.ARTICLESTYPE==6">公 告</s:elseif> 
					                    <s:else>全部新闻</s:else> 
										
					                    </font>
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
			<table class="tbl" width="99%"  height="100%" cellspacing="0" cellpadding="1"
						border="0" >
						<tr>
							    <td colspan="2" 
								background="../../images/newsystem/th2.gif" class="txt_b"
								align="center">  
								 <strong><font color="blue" size="4" ><s:property value="search.TITLE" /></font></strong>
								 <br/>
								      <font size="2">来源:<s:property value="search.SOURCE"/> &nbsp;
								      作者:<s:property value="search.AUTHOR" /> &nbsp;
								      时间:<s:property value="search.RELEASETIME" /> 
								      </font>
								</td>
						</tr>
						

						<tr>
					<td class="main" align="left">
					   <br/>
					   <p style="LINE-HEIGHT: 150%; MARGIN: 0cm 0cm 0pt" class="MsoNormal">
				      &nbsp;&nbsp;&nbsp;&nbsp;
				      <font size="2"><s:property value="search.CONTENT" /></font>
				      </p>
				    </td>
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
										
									

										<p style="text-align: left; line-height: 150%; margin-left: 0cm; margin-right: 0cm; margin-top: 0cm; margin-bottom: 0pt" class="MsoNormal" align="right">　
				
										<br/> 
				                     
									
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
					<td valign="top" bgcolor="#02687F">
					
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="248" id="table38" background="images/left333.jpg" height="210">
						
						<tr>
							<td valign="top">
							 <table cellpadding="0"  cellspacing="0">
							 <tr>
							 <td>
							 <a href="/news.do?action=FrontNews&search.artype=3">
							 <img border="0" src="images/left221.jpg"  height="45"></a>
							 </td>
							 <td>
							 <a href="/news.do?action=FrontNews&search.artype=4">
							 <img border="0" src="images/left222.jpg"  height="45"></a>
							 </td>
							 <td>
							 <a href="/news.do?action=FrontNews&search.artype=5">
							 <img border="0" src="images/left223.jpg"  height="45"></a>
							 </td>
							 </tr>
							 </table>
                            </td>
						</tr>
						
						<tr>
							<td valign="top">
							<table border="0" cellpadding="0" style="border-collapse: collapse;" width="248" id="table39" height="193">
								<tr>
									<td valign="top">
										<table id="table40" style="width: 238px; border-collapse: collapse" cellPadding="0" border="0">
                                         <s:iterator id="teight" value="%{topEightNews.objectList}">	
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="238" align="center" background="images/listbg.jpg" border="0" id="table41">
													<tr>
														<td align="left" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td align="left">
														<font color="#FFFFFF">
														<span style="font-size: 9pt; text-decoration: none">
													    <a href="/news.do?action=newsDetail&search.newsID=<s:property value="#teight.NEWSID"/>" >
													    <s:property value="#teight.TITLE"/></a></span></font><span id="TabSkinContainer34"><span id="TabSkinContainer35"><span id="TabSkinContainer36"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer37"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											</s:iterator>
										
											
											
										</table>
									
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table10" background="images/di.jpg" height="27">
				<tr>
					<td valign="middle"><span style="font-size: 4pt"><br>
					</span><span style="font-size: 9pt">&nbsp;<font color="#FFFFFF">All 
					rights reserved. 2009&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					技术支持 by </font><font color="#02687F">泉州市冠发信息科技有限公司</font></span></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
