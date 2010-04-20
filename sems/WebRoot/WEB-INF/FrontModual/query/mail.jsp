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
		<base href="<%=basePath%>">
		<title>福建省183速递官方网站</title>
		<link href="<%=path%>/css/indexnew.CSS"  type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
	</head>
	<SCRIPT language="javascript">
function divTags(tagID)
{
    
    var Categorys;
    if(tagID==1)
    {
        document.getElementById("herf1").className="cheng";
        document.getElementById("herf2").className="hui";
        
        
        document.getElementById("li1").className="limenu1";
        document.getElementById("li2").className="limenu";
       
        
        
        var ione = document.getElementById('divOne');
        divOne.style.display="block";
        divTwo.style.display="none";
       
        
    }
    else if(tagID==2)
    {
        document.getElementById("herf1").className="hui";
        document.getElementById("herf2").className="cheng";
      
		
        
        document.getElementById("li1").className="limenu";
        document.getElementById("li2").className="limenu1";
       
        
        divOne.style.display="none";
        divTwo.style.display="block";
        
    }

}
</SCRIPT>
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: scroll;
	overflow-x: hidden;
	overflow-y: auto;
}

.text-m-blue {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 20px;
	color: 54a2ef;
}

.lineTr td {
	border-bottom: 1px solid #54a2ef;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #003366;
	line-height: 20px;
}
-->
</style>
	<script type="text/javascript">	
	document.body.style.backgroundImage="url(images/bg.jpg)";
</script>
	<body>
		<!-- 装饰器屏蔽了background -->
		<center>
		 <table border="0" cellpadding="0" style="border-collapse: collapse" width="969" id="table3" height="100%" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">
			<jsp:include page="/index_top.jsp"></jsp:include>
			
			<table border="0" cellpadding="0" style="border-collapse: collapse"
				width="961" id="table6">
				<tr>
					<td width="884" height="100%" valign="top" bgcolor="#e5e5e5">
						<table border="0" width="100%" id="table49"
							style="border-collapse: collapse" cellpadding="0">
							<tr>
								<td>
								</td>
							</tr>
						</table>
						<div align="center">
							<table border="0" width="687" id="table50" cellpadding="0"
								style="border-collapse: collapse">
								<tr>
									<td>
										<table border="0" cellpadding="0"
											style="border-collapse: collapse" width="687" id="table54"
											background="images/nei01.jpg" height="34">
											<tr>
												<td width="38">
												</td>
												<td align="left">
													<font color="#FFFFFF" style="font-size: 10pt"> <span
														style="font-weight: 700"><strong> <font
																color="#ff0000">EMS邮件查询</font> </strong>
													</span>
													</font>
													<br>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td background="images/nei02.jpg">
										<span id="TabSkinContainer44"> 
										<span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent" class="Normal">
										<table width="90%" border="0" cellpadding="0" cellspacing="0"
											class="text-m-blue">
											<tr>
												<td height="14">
													<table width="100%" border="0" bgcolor="#F6F7FD">
														<tr>
															<td align="right" width="10%">
																收寄时间:
															</td>
															<td width="40%" align="left">
																<s:property value="%{search.receiveTime}" />
															</td>
															<td width="10%" align="right">
																收寄局:
															</td>
															<td width="40%" align="left">
																<s:property value="%{search.receiveBureau}" />
															</td>
														</tr>
														<tr>
															<td align="right">
																寄达地:
															</td>
															<td align="left">
																<s:property value="%{search.sendAddress}" />
															</td>
															<td align="right">
																邮件种类:
															</td>
															<td align="left">
																<s:property value="%{search.postType}" />
															</td>
														</tr>
														<tr>
															<td align="right">
																邮件性质:
															</td>
															<td align="left">
																<s:property value="%{search.postKind}" />
															</td>
															<td align="right">
																邮件编号:
															</td>
															<td align="left">
																<s:property value="%{search.mailNum}" />
															</td>
														</tr>
														<tr>
															<td align="right">
																最后处理时间:
															</td>
															<td align="left">
																<s:property value="%{search.nowHandleTime}" />
															</td>
															<td align="right">
																地点:
															</td>
															<td align="left">
																<div id="td42">
																	<s:property value="%{search.nowHandle}" />
																</div>
															</td>
														</tr>

													</table>
													<div id="queryResult1"></div>
													<div id="queryResult2" style="display: none"></div>
													<div id="queryResult3"></div>
												</td>
											</tr>

											<tr>
												<td width="100%">
													<br />
													<table id="stateTable" border="0" width="610" border="0"
														cellpadding="0" cellspacing="0">
														<tr align="center" bgcolor="#F6F7FD" class="lineTr">
															<td width="100" height="30" align="left">
																时 间
															</td>
															<td width="124" align="left">
																处理站点
															</td>
															<td width="104" align="left">
																处理动作
															</td>
															<td align="left">
																处理说明
															</td>
															<td width="94" align="left">
																备 注
															</td>
															<td width="30" align="left">
																&nbsp;
															</td>
														</tr>
														<s:iterator id="detail" value="%{search.detailList}">
															<tr class="lineTr"
																onMouseOver="this.style.backgroundColor='#7db8f3';this.style.color ='#ffffff';"
																onMouseOut="this.style.backgroundColor='';this.style.color ='';">
																<td>
																	<s:property value="#detail.time" />
																</td>
																<td>
																	<s:property value="#detail.handleAddress" />
																</td>
																<td>
																	<s:property value="#detail.handleAction" />
																</td>
																<td>
																	<s:property value="#detail.handleExplain" />
																</td>
																<td>
																	<s:property value="#detail.remark" />
																</td>
															</tr>
														</s:iterator>
													</table>

												</td>
											</tr>

										</table>
										
										</span>
										</span>
										<p>
									</td>
								</tr>
								<tr>
									<td>
										<img border="0" src="images/nei03.jpg" width="687" height="4">
									</td>
								</tr>
							</table>
							<table border="0" cellpadding="0"
								style="border-collapse: collapse" width="100%" id="table51">
								<tr>
									<td>
									</td>
								</tr>
							</table>
						</div>
					</td>
					<td valign="top" bgcolor="#5b6e75">

					  <table width="252" border="0"  cellspacing="0" cellpadding="0">
              <tr>
              <td height="30"><img src="images/left221.jpg" border="0" ></img></td>
              <td height="30"  class=limenu1 id=li1><div align="center" ><a  class=cheng onMouseMove="divTags('1');" id=herf1 
  href="/news.do?action=FrontNews&search.artype=4">福建EMS</a>&nbsp;|</div></td>
               
                <td  height="30"  class=limenu id=li2><div align="left"><a class=hui onMouseMove="divTags('2');" id=herf2 
  href="/news.do?action=FrontNews&search.artype=5">行业动态</a></div></td>
              </tr>
               
                <tr>
        <td colspan="4">
        <DIV id=divOne>
        <table width="100%"  border="0" cellspacing="0" bgcolor="#5b6e75" cellpadding="0">
             <tr>
             <td height="215" valign="top">
                                        <s:if test="topEightNews!=null">
											<s:iterator id="teight" value="%{topEightNews.objectList}">	
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center"  bgcolor="#5b6e75" border="0" id="table29">
													<tr>
														<td align="middle" width="22" command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td align="left" >
														<font color="#FFFFFF">
														<span style="font-size: 9pt; text-decoration: none">
														<a href="/news.do?action=newsDetail&search.newsID=<s:property value="#teight.NEWSID"/>"  target="_blank">
													    <s:property value="#teight.TITLE"/></a>…</span></font></td>
													</tr>
												</table>
													</s:iterator>
													</s:if>




</td>
            </tr>
        </table>
      </DIV>
               
         <DIV class=con_bottom id=divTwo style="DISPLAY: none">
        <table width="100%" border="0" bgcolor="#5b6e75" cellspacing="0" cellpadding="0">
          <tr>
          <td height="215" valign="top">
          
              <s:if test="hangEightNews!=null">
											<s:iterator id="teight" value="%{hangEightNews.objectList}">	
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center"  bgcolor="#5b6e75" border="0" id="table29">
													<tr>
														<td align="middle" width="22" command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td align="left" >
														<font color="#FFFFFF">
														<span style="font-size: 9pt; text-decoration: none">
														<a href="/news.do?action=newsDetail&search.newsID=<s:property value="#teight.NEWSID"/>"  target="_blank">
													    <s:property value="#teight.TITLE"/></a>…</span></font></td>
													</tr>
												</table>
													</s:iterator>
													</s:if>
          
          
          
          </td>
          </tr>
          </table>
          </DIV>      
               
               
              </td>
              </tr>  
            </table>
					
					
					
					
					
					
					
					
					
					
					
					
					
				
					
					</td>
				</tr>
			</table>

			<jsp:include page="/index_bottom.jsp"></jsp:include>
			</td>
		</tr>
	</table>
		</center>
	</body>
</html>
