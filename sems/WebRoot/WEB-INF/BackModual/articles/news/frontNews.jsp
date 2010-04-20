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
		<link href="<%=path%>/css/indexnew.CSS"  type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
	</head>
	
  <SCRIPT language=javascript>
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

	 <body background="../../../../images/bg.jpg"><!-- 装饰器屏蔽了background -->
	 <center>
	 <table border="4" cellpadding="0" style="border-collapse: collapse" width="969" id="table3" height="100%" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">
			<jsp:include page="/index_top.jsp"></jsp:include>
            
			<table border="2" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6" height="272">
				<tr>
					<td width="884" valign="top" bgcolor="#E5E5E5">
					<table border="0" width="100%" id="table49" style="border-collapse: collapse" cellpadding="0">
						<tr>
							<td>　</td>
						</tr>
					</table>
					<div align="center">
						<table border="0" width="687" id="table50"   cellpadding="0" style="border-collapse: collapse">
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
					                   
					                    <s:elseif test="search.ARTICLESTYPE==4">福建EMS</s:elseif>  
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
											
										
										<div align="left">
											
		
		<table width="80%"   align="center"  border="0" cellpadding="0"  cellspacing="0" >

				<s:iterator id="news" value="%{pageList.objectList}">
					<tr>
						<td >
						<font size=2> 
						·<a href="/news.do?action=newsDetail&search.newsID=<s:property value="#news.NEWSID"/>"  target="_blank">  
                        <s:property value="#news.TITLE" /></a>
                        </font>
						</td>
						<td align='right'>
							<font size=2><s:property value="#news.RELEASETIME" /></font>
						</td>	
					</tr>
				</s:iterator>
			
			<tr class="bgColor3">
				<td colspan="29" align="center">
					<font size=2> 分页:
					<qzgf:pages value="%{pageList.pages}" /></font>
					<input type="hidden" id="search.artype" name="search.artype" value="<s:property value="search.artype"/>"/>
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
					<td valign="top"  bgcolor="#5b6e75">
                    



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
													    <div style="width:180px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;float:left;border:0px;color:#FFFFFF"><s:property value="#teight.TITLE"/></div></a></span></font></td>
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
													    <div style="width:180px; white-space:nowrap;overflow:hidden;text-overflow:ellipsis;float:left;border:0px;color:#FFFFFF"><s:property value="#teight.TITLE"/></div></a></span></font></td>
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
