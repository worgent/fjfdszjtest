<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
<%@  page import="java.util.*"%>

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
		<style type="text/css">
		<!--
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
				overflow:scroll;
				overflow-x:hidden;
				overflow-y:auto;
			}
			.text-m-blue { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; line-height: 20px; color: 54a2ef; }
			.lineTr td{ border-bottom:1px solid #54a2ef; font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #003366; line-height: 20px; }
			.fontTr td{ font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #003366; line-height: 20px; }  
			
			
			  a:link {color: #000000; text-decoration:none} 
			  a:visited {color:#000000; text-decoration: none} 
			  a:active {color:#000000; text-decoration: none} 
			  a:hover {color:#ff0000; text-decoration:underline} 
		-->
		</style>
		<link href="<%=path%>/css/indexnew.CSS"  type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<script type="text/javascript" src="<%=path%>/js/index_query.js"></script>
		<script type="text/javascript" src="/js/setday.js"></script>
		<script type="text/javascript">
		//=========================新闻选项卡==============================
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

		  //设置默认选择
		  function setUsername()
		  {
		     var url ='/news.do?action=fir';
			 document.forms[0].action=url;
	      }
		  //设置背景 
		  document.body.style.backgroundImage="url(images/bg.jpg)";    
    </script>
</head>
 <body onload="setUsername()">
 <div align="center">
	<jsp:include page="/index_top.jsp"></jsp:include>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="961" id="table6">
				<tr>
					<td width="193" valign="top">
						<table border="0" cellpadding="0" style="border-collapse: collapse" width="252" id="table7" background="images/lft1.jpg" height="246">
						<tr>
							<td valign="top">
						
							<table border="0" cellpadding="0" style="border-collapse: collapse" width="247" id="table17" height="92">
								<tr>
									<td valign="top">
									<table border="0" cellpadding="0" style="border-collapse: collapse;" width="248" id="table18" height="237">
										<tr>
											<td height="20" align="left" >
											</td>
										</tr>
										<tr>
											<td height="200" >
           
              
              <font color="#6BBDCF" style="font-size: 9pt">
<marquee direction=up scrollAmount=1 height=188 onMouseOut=this.start(); onMouseOver=this.stop(); width=227  style="padding:0; ">
 <s:if test="topSixNews!=null">
 <s:iterator id="tsix" value="%{topSixNews.objectList}">	
<br>● <a href="/news.do?action=newsDetail&search.newsID=<s:property value="#tsix.NEWSID"/>"  target="_blank">
													    <s:property value="#tsix.TITLE"/></a>(<s:property value="#tsix.RELEASETIME"/>)
</s:iterator>
<br>

<table width="230"><tr><td align="right"><a href="/news.do?action=FrontNews&search.artype=6" target="_blank">更多</a></td></tr></table> 
</s:if>
</marquee> 

</font>
</td>
										</tr>
									</table>
									</td>
									
								</tr>
							</table>
							</td>
						</tr>
					</table>
					
					
					
					
				<table width="252" border="0"  cellspacing="0" cellpadding="0">
              <tr>
                <td width="73" height="36"  class=limenu1 id=li1><div align="center" ><a  class=cheng onMouseMove="divTags('1');" id=herf1 
  href="">福建EMS</a></div></td>
               
                <td width="73" height="36"  class=limenu id=li2><div align="center"><a class=hui onMouseMove="divTags('2');" id=herf2 
  href="">行业动态</a></div></td>
             
               </tr>
               
                <tr>
        <td colspan="2">
        <DIV id=divOne>
        <table width="100%"  border="0" cellspacing="0" background="images/listbg.jpg" cellpadding="0">
             <tr>
             <td height="215" valign="top">
                                        <s:if test="topEightNews!=null">
											<s:iterator id="teight" value="%{topEightNews.objectList}">	
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table29">
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
        <table width="100%" border="0" background="images/listbg.jpg" cellspacing="0" cellpadding="0">
          <tr>
          <td height="215" valign="top">
          
              <s:if test="hangEightNews!=null">
											<s:iterator id="teight" value="%{hangEightNews.objectList}">	
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table29">
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
					<td valign="top">
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table11">
						<tr>
							<td>
						
							<script language="JavaScript" src="<%=request.getContextPath() %>/main.js" type="text/javascript"></script>
							
							
							
							</td>
						</tr>
					</table>
					
					
					
				<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table12">
										<tr>
											
											<td width="101" background="images/navB.jpg" id="td1"
													onclick="loadOnLineQuery();"   height="45"
												align="center" style="cursor:hand">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">自助服务</span>
												</font>
											</td>
											
											<td background="images/navA.jpg" id="td3"
											   onclick="loadEmsQuery();" width="101" height="45"
												align="center" style="cursor:hand">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														EMS邮件查询</span> </font>
											</td>
											<td background="images/navB.jpg" id="td5"
												  onclick="loadTimeQuery();" width="101" height="45"
												align="center" style="cursor:hand">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">国内时限查询</span>
												</font>
											</td>
											<td background="images/navB.jpg" id="td4"
												 onclick="loadBoundQuery();" width="101" height="45"
												align="center" style="cursor:hand">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														国际时限查询</span> </font>
											</td>
											
											<td background="images/navB.jpg" id="td6"
												onclick="loadMoneyQuery();" width="101" height="45"
												align="center" style="cursor:hand">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														国内资费查询</span> </font>
											</td>
											<td width="101" height="45" background="images/navB.jpg" id="td8" onclick="loadFMoneyQuery();" align="center" style="cursor:hand">
												<font color="#FFFFFF" > <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														国际资费查询</span> </font>
											</td>
											<td background="images/navB.jpg" id="td7"
												 onclick="loadAddressQuery();" width="101" height="45"
												align="center" style="cursor:hand">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">邮政编号查询</span>
												</font>
											</td>
											
										</tr>
									</table>
								

										<table border="0" cellpadding="0"
											style="border-collapse: collapse" width="100%" id="table13"
											height="210" bgcolor="#E5E5E5" >
											<tr >
												<td valign="middle" ><br><br>
													<div id="tab1"  align="center" style="display: none">
														
																<s:form action="frontlogin" target="_top" namespace="/">
																<table border="0" cellpadding="0"
																	style="border-collapse: collapse" width="70%"
																	id="table16" height="115">
																	<tr>
																		<td width="68" height="29">
																			<font 
																				style="font-size: 9pt; font-weight: 700">&nbsp;
																				用户名:</font>
																		</td>
																		<td align="left" width="180" height="29" colspan="2">
																			<input type="text" name="search.pcode" id="pcode"
																				size="23"
																				style="border: 1px solid #808080; background-color: #A6D7E1">
																		</td>
																	</tr>
																	<tr>
																		<td width="68" height="29" >
																			<font 
																				style="font-size: 9pt; font-weight: 700">&nbsp;
																				密 码:</font>
																		</td>
																		<td width="180" height="29" colspan="2" align="left">
																			<input type="password" name="search.ppasswd"
																				id="ppasswd" size="24"
																				style="border: 1px solid #808080; background-color: #A6D7E1">
																		</td>
																	</tr>
																	<tr>
																		<td width="68" height="29" >
																			<font 
																				style="font-size: 9pt; font-weight: 700">&nbsp;
																				验证码:</font>
																		</td>
																		<td width="79" height="29" align="left">
																			<input type="text" name="search.pverifycode"
																				id="pverifycode" size="8"
																				style="border: 1px solid #808080; background-color: #A6D7E1">
																				<img border=0 width="60" height="18" src="/authimg"
																				id="imgcode" style="cursor: pointer;" border="0"
																				onClick="document.getElementById('imgcode').src='/authimg?action='+Math.random();"
																				title="看不清？！换一个" alt="看不清？！换一个" />
																		</td>
																		<td width="91" height="29">
																			
																	</tr>
																	<tr>
																		<td width="180" height="29" colspan="2" align="center">
																			<span style="font-size: 9pt"> <input
																					type="button" onclick="javascript:login();"
																					value="登 录" name="B1"
																					style="width: 44; height: 22; font-size: 12px; color: #6D6D6D; border-style: outset; border-width: 0px; background-image: url('images/denglu.gif'); background-position: left top">
																				<input type="button"
																					onclick="javascript:fun_forget();" value="忘记密码"
																					name="B5"
																					style="width: 63; height: 22; font-size: 12px; color: #6D6D6D; border-style: outset; border-width: 0px; background-image: url('images/qingchu.gif'); background-position: left top">
																				<input type="button" onclick="javascript:regedit();"
																					value="注 册" name="B4"
																					style="width: 44; height: 22; font-size: 12px; color: #6D6D6D; border-style: outset; border-width: 0px; background-image: url('images/denglu.gif'); background-position: left top">
																			</span>
																		</td>
																		<td>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="3">
																			<font color="#CE7000"> <span
																				style="font-size: 9pt"> 温馨提示：欢迎使用福建省邮政速递网上自助服务功能，我们提供给您便捷快速的网上寄件、详情单打印、客户管理等贴心实用的功能，使用自助服务功能请先登录或注册。
																			</span> </font>
																		</td>
																	</tr>
																	
																</table>
															</s:form>
													</div>
													<div id="tab3" >
														<s:form action="/query/mail.do" method="post" target="_top">
														<table border="0" cellpadding="0"
															style="border-collapse: collapse" width="620"
															id="table37">
															
															<tr>
																<td height="29" width="30%" align="right">
																	<font color="#5B6E75" style="font-size: 9pt">
																		输入单号：</font>
																</td>
																<td  width="70%"  align="left">
																	<input type="text" name="mailNum" id="mailNum"
																		style="border: 1px solid #A8A8A8; background-color: #E0EFF6" />
																</td>
															</tr>
															<tr>
																		<td width="30%" height="29" align="right">
																			<font color="#5B6E75" style="font-size: 9pt">
																				验证码:</font>
																		</td>
																		<td width="70%" height="29" align="left" nowrap="nowrap">
																			<input type="text" name="mailverifycode"
																				id="mailverifycode" size="8"
																				style="border: 1px solid #A8A8A8; background-color: #E0EFF6" /><img border=0 width="60" height="18" src="/mailauthimg"
																				id="mailimgcode" style="cursor: pointer;" border="0"
																				onClick="document.getElementById('mailimgcode').src='/mailauthimg?action='+Math.random();"
																				title="看不清？！换一个" alt="看不清？！换一个" />
																		</td>
																		
															</tr>
															<tr>
																<td width="540" height="34" align="center" colspan="2">
																	<s:hidden name="emsCheckCode" id="emsCheckCode"></s:hidden>
																	<span style="font-size: 9pt"> <input
																			type="button" value="查 询" name="B6"
																			onclick="javascript:checkEms('<%=path %>');"
																			style="width: 44; height: 22; font-size: 12px; color: #6D6D6D; border-style: outset; border-width: 0px; background-image: url('images/denglu.gif'); background-position: left top">&nbsp;
</span>
<div id="messageDiv3" style="display:none"></div>
																	</td>
															</tr>
															<tr>
																<td colspan="3">
																	<font color="#CE7000"> <span
																		style="font-size: 9pt"> 温馨提示：EMS还可以为您提供更便捷、个性化的
																	</span> </font>
																	<b> <font color="#005BAC" style="font-size: 9pt">
																			邮件订阅</font> </b><font color="#CE7000"><span
																		style="font-size: 9pt"> 及 </span> </font><b> <font
																		color="#005BAC" style="font-size: 9pt"> 短信追踪</font> </b><font
																		color="#CE7000"><span style="font-size: 9pt">
																			查询快件服务，赶快来参与。</span> </font>
																</td>
															</tr>
														</table>
														</s:form>
													</div>
														
													<div id="tab5" style="display: none">
														<table width="100%" border="0" cellpadding="0" cellspacing="0">
															<tr >
																<td>
																	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr  height=30 class="fontTr">
				<td width="15%">
					原寄地省份：
				</td>
				<td align="left" width="35%">
					<select id="pprovince3" onchange="changeCombo1('pcity3','pprovince3','cityvalue')" ></select>
				</td>
				<td width="15%">
					寄达地省份：
				</td>
				<td align="left" width="35%">
					<select id="pprovince4" onchange="changeCombo1('pcity4','pprovince4','cityvalue')" ></select>
				</td>
				<td rowspan="2" valign="bottom">
					<input type="button" id="timeQueryId" value="时限查询" onClick="timeQuery(this,'<%=path %>')" />
				</td>
			</tr>
			
			<tr class="lineTr">
				<td>
					原寄地城市：
				</td>
				<td align="left">
					<select id="pcity3" name="pcity3" onchange="changeCity('pcity4','pcity3')">	
					</select>
				</td>
				<td>
					寄达地城市：
				</td>
				<td align="left">
					<select id="pcity4" name="pcity4" onchange="changeCity('pcity3','pcity4')">
					</select>
				</td>
			</tr>
			<tr class="fontTr" onMouseOver="this.style.backgroundColor='#7db8f3';this.style.color ='#ffffff';" onMouseOut="this.style.backgroundColor='';this.style.color ='';">
				<td colspan="5" align="left">
					查询结果:<span id="resultValue"></span><br>
					<br>
					<span id="resultInfo"><font color="#CE7000" style="font-size: 9pt">说明：查询结果仅作为参考</font></span>
				</td>
			</tr>
		</table>
		
		<div id="messageDiv1" style="display:none"></div>
																</td>
															</tr>
														</table>
													</div>
													<div id="tab6" style="display: none">
														<table width="514" border="0" cellpadding="0"
															cellspacing="0">
															<tr class="fontTr">
																<td width="41%" height="10" align="left" valign="bottom"
																	nowrap="nowrap" class="txt-form-n-tt">
																	寄出所在省份
																</td>
																<td width="39%" height="10" align="left" valign="bottom">
																	<span class="txt-form-n-tt">计费重量（公斤）</span>
																</td>
																<td width="20%" rowspan="4" align="left" valign="bottom">
																	<span class="unnamed2"> <input id="queryFeeId"
																			onclick="makeRequest('<%=path%>','http://www.ems.com.cn/tools/postageresult.jsp',1)"
																			type="button" value="计  算" name="clickfee" /> </span>
																</td>
															</tr>
															<tr class="fontTr">
																<td width="41%" height="24" align="left" nowrap="nowrap"
																	class="txt-main">
																	<select name="addressfrom" id="addressfrom" isneed="Y"
																		fieldname="寄件人省份">
																		<option value="" selected="selected">
																			==寄件人省份==
																		</option>
																		<option value="安徽">
																			安徽
																		</option>
																		<option value="北京">
																			北京
																		</option>
																		<option value="福建">
																			福建
																		</option>
																		<option value="甘肃">
																			甘肃
																		</option>
																		<option value="广东">
																			广东
																		</option>
																		<option value="广西">
																			广西
																		</option>
																		<option value="贵州">
																			贵州
																		</option>
																		<option value="海南">
																			海南
																		</option>
																		<option value="河北">
																			河北
																		</option>
																		<option value="河南">
																			河南
																		</option>
																		<option value="黑龙江">
																			黑龙江
																		</option>
																		<option value="湖北">
																			湖北
																		</option>
																		<option value="湖南">
																			湖南
																		</option>
																		<option value="吉林">
																			吉林
																		</option>
																		<option value="江苏">
																			江苏
																		</option>
																		<option value="江西">
																			江西
																		</option>
																		<option value="辽宁">
																			辽宁
																		</option>
																		<option value="内蒙古">
																			内蒙古
																		</option>
																		<option value="宁夏">
																			宁夏
																		</option>
																		<option value="青海">
																			青海
																		</option>
																		<option value="山东">
																			山东
																		</option>
																		<option value="山西">
																			山西
																		</option>
																		<option value="陕西">
																			陕西
																		</option>
																		<option value="上海">
																			上海
																		</option>
																		<option value="四川">
																			四川
																		</option>
																		<option value="天津">
																			天津
																		</option>
																		<option value="西藏">
																			西藏
																		</option>
																		<option value="新疆">
																			新疆
																		</option>
																		<option value="云南">
																			云南
																		</option>
																		<option value="浙江">
																			浙江
																		</option>
																		<option value="重庆">
																			重庆
																		</option>
																	</select>
																</td>
																<td width="39%" height="24" align="left">
																	<span class="unnamed2"> <input name="weight"
																			id="weight" type="text" maxlength="7" size="18"
																			fieldname="快件重量" isneed="Y" checktype="number"
																			class="input1" /> </span><div id="messageDiv" style="display:none"></div>
																</td>
															</tr>
															<tr class="fontTr">
																<td width="41%" height="20" align="left" valign="bottom"
																	nowrap="nowrap">
																	<span class="txt-form-n-tt">寄达所在省份</span>
																</td>
																<td width="39%" align="left" valign="bottom">
																	<span class="txt-form-n-tt">计算结果 (元)：</span>
																</td>
															</tr>
															<tr valign="bottom">
																<td width="41%" height="20" align="left"
																	class="unnamed2">
																	<select name="addressto" id="addressto" isneed="Y"
																		fieldname="收件人省份">
																		<option value="" selected="selected">
																			==收件人省份==
																		</option>
																		<option value="安徽">
																			安徽
																		</option>
																		<option value="北京">
																			北京
																		</option>
																		<option value="福建">
																			福建
																		</option>
																		<option value="甘肃">
																			甘肃
																		</option>
																		<option value="广东">
																			广东
																		</option>
																		<option value="广西">
																			广西
																		</option>
																		<option value="贵州">
																			贵州
																		</option>
																		<option value="海南">
																			海南
																		</option>
																		<option value="河北">
																			河北
																		</option>
																		<option value="河南">
																			河南
																		</option>
																		<option value="黑龙江">
																			黑龙江
																		</option>
																		<option value="湖北">
																			湖北
																		</option>
																		<option value="湖南">
																			湖南
																		</option>
																		<option value="吉林">
																			吉林
																		</option>
																		<option value="江苏">
																			江苏
																		</option>
																		<option value="江西">
																			江西
																		</option>
																		<option value="辽宁">
																			辽宁
																		</option>

																		<option value="内蒙古">
																			内蒙古
																		</option>

																		<option value="宁夏">
																			宁夏
																		</option>

																		<option value="青海">
																			青海
																		</option>

																		<option value="山东">
																			山东
																		</option>
																		<option value="山西">
																			山西
																		</option>

																		<option value="陕西">
																			陕西
																		</option>

																		<option value="上海">
																			上海
																		</option>

																		<option value="四川">
																			四川
																		</option>

																		<option value="天津">
																			天津
																		</option>
																		<option value="西藏">
																			西藏
																		</option>
																		<option value="新疆">
																			新疆
																		</option>
																		<option value="云南">
																			云南
																		</option>
																		<option value="浙江">
																			浙江
																		</option>
																		<option value="重庆">
																			重庆
																		</option>
																	</select>
																</td>
																<td width="39%" align="left" class="unnamed2">
																	<input readonly size="18" value="0" id="gofee"
																		name="gofee" class="input1" />
																</td>
															</tr>
															<tr class="fontTr" >
																<td colspan="5" align="left">
																	<br>
																	<span id="resultInfo"><font color="#CE7000" style="font-size: 9pt">说明：查询结果仅作为参考</font></span>
																</td>
															</tr>
														</table>
													</div>
													
													<div id="tab8" style="display: none">
														<table width="514" border="0" cellpadding="0"
															cellspacing="0">
															<tr class="fontTr">
																<td width="41%" height="10" align="left" valign="bottom"
																	nowrap="nowrap" class="txt-form-n-tt">
																	寄达国家或地区
																</td>
																<td width="39%" height="10" align="left" valign="bottom">
																	<span class="txt-form-n-tt">计费重量（公斤）</span>
																</td>
																<td width="20%" rowspan="4" align="left" valign="bottom">
																	<span class="unnamed2"> <input id="queryFFeeId"
																			onclick="makeRequest1('<%=path%>','http://www.ems.com.cn/tools/postageresult.jsp',2)"
																			type="button" value="计  算" name="clickfee" /> </span>
																</td>
															</tr>
															<tr class="fontTr">
																<td width="41%" height="24" align="left" nowrap="nowrap"
																	class="txt-main">
																	<select name="addresstoc" id="addresstoc" >
																		<option value="阿尔巴尼亚">阿尔巴尼亚</option>
                            
                              <option value="阿尔及利亚">阿尔及利亚</option>
                            
                              <option value="阿根廷">阿根廷</option>
                            
                              <option value="阿联酋">阿联酋</option>
                            
                              <option value="阿鲁巴">阿鲁巴</option>
                            
                              <option value="阿曼">阿曼</option>
                            
                              <option value="阿塞拜疆">阿塞拜疆</option>
                            
                              <option value="埃及">埃及</option>
                            
                              <option value="埃塞俄比亚">埃塞俄比亚</option>
                            
                              <option value="爱尔兰">爱尔兰</option>
                            
                              <option value="爱沙尼亚">爱沙尼亚</option>
                            
                              <option value="安道尔">安道尔</option>
                            
                              <option value="安哥拉">安哥拉</option>
                            
                              <option value="安圭拉">安圭拉</option>
                            
                              <option value="安提瓜和巴布达">安提瓜和巴布达</option>
                            
                              <option value="奥地利">奥地利</option>
                            
                              <option value="澳大利亚">澳大利亚</option>
                            
                              <option value="澳门 ">澳门 </option>
                            
                              <option value="巴巴多斯">巴巴多斯</option>
                            
                              <option value="巴布亚新几内亚">巴布亚新几内亚</option>
                            
                              <option value="巴哈马">巴哈马</option>
                            
                              <option value="巴基斯坦">巴基斯坦</option>
                            
                              <option value="巴拉圭">巴拉圭</option>
                            
                              <option value="巴林">巴林</option>
                            
                              <option value="巴拿马">巴拿马</option>
                            
                              <option value="巴西">巴西</option>
                            
                              <option value="白俄罗斯">白俄罗斯</option>
                            
                              <option value="百慕大">百慕大</option>
                            
                              <option value="保加利亚">保加利亚</option>
                            
                              <option value="比利时">比利时</option>
                            
                              <option value="冰岛">冰岛</option>
                            
                              <option value="玻利维亚">玻利维亚</option>
                            
                              <option value="波多黎各">波多黎各</option>
                            
                              <option value="波兰">波兰</option>
                            
                              <option value="博茨瓦纳">博茨瓦纳</option>
                            
                              <option value="布基纳法索">布基纳法索</option>
                            
                              <option value="朝鲜">朝鲜</option>
                            
                              <option value="丹麦">丹麦</option>
                            
                              <option value="德国">德国</option>
                            
                              <option value="东萨摩亚">东萨摩亚</option>
                            
                              <option value="多哥">多哥</option>
                            
                              <option value="多米尼加共和国">多米尼加共和国</option>
                            
                              <option value="多米尼加联邦">多米尼加联邦</option>
                            
                              <option value="俄罗斯">俄罗斯</option>
                            
                              <option value="厄瓜多尔">厄瓜多尔</option>
                            
                              <option value="厄立特里亚">厄立特里亚</option>
                            
                              <option value="法国">法国</option>
                            
                              <option value="法属圭亚那">法属圭亚那</option>
                            
                              <option value="菲律宾 ">菲律宾 </option>
                            
                              <option value="芬兰">芬兰</option>
                            
                              <option value="佛得角">佛得角</option>
                            
                              <option value="冈比亚">冈比亚</option>
                            
                              <option value="刚果（布）">刚果（布）</option>
                            
                              <option value="刚果（金）">刚果（金）</option>
                            
                              <option value="哥伦比亚">哥伦比亚</option>
                            
                              <option value="哥斯达黎加">哥斯达黎加</option>
                            
                              <option value="格林纳达">格林纳达</option>
                            
                              <option value="格陵兰岛">格陵兰岛</option>
                            
                              <option value="格鲁吉亚">格鲁吉亚</option>
                            
                              <option value="古巴">古巴</option>
                            
                              <option value="瓜德罗普">瓜德罗普</option>
                            
                              <option value="关岛">关岛</option>
                            
                              <option value="圭亚那 ">圭亚那 </option>
                            
                              <option value="哈萨克斯坦">哈萨克斯坦</option>
                            
                              <option value="海地">海地</option>
                            
                              <option value="海峡群岛">海峡群岛</option>
                            
                              <option value="韩国">韩国</option>
                            
                              <option value="荷兰">荷兰</option>
                            
                              <option value="洪都拉斯">洪都拉斯</option>
                            
                              <option value="基里巴斯">基里巴斯</option>
                            
                              <option value="吉布提">吉布提</option>
                            
                              <option value="吉尔吉斯斯坦">吉尔吉斯斯坦</option>
                            
                              <option value="几内亚">几内亚</option>
                            
                              <option value="几内亚比绍">几内亚比绍</option>
                            
                              <option value="加拿大 ">加拿大 </option>
                            
                              <option value="加那利群岛">加那利群岛</option>
                            
                              <option value="加纳">加纳</option>
                            
                              <option value="加蓬">加蓬</option>
                            
                              <option value="柬埔寨 ">柬埔寨 </option>
                            
                              <option value="捷克">捷克</option>
                            
                              <option value="津巴布韦">津巴布韦</option>
                            
                              <option value="卡塔尔">卡塔尔</option>
                            
                              <option value="开曼群岛">开曼群岛</option>
                            
                              <option value="科摩罗群岛">科摩罗群岛</option>
                            
                              <option value="科特迪瓦">科特迪瓦</option>
                            
                              <option value="科威特">科威特</option>
                            
                              <option value="克罗地亚">克罗地亚</option>
                            
                              <option value="肯尼亚">肯尼亚</option>
                            
                              <option value="库克群岛">库克群岛</option>
                            
                              <option value="库腊索岛（荷属）">库腊索岛（荷属）</option>
                            
                              <option value="拉脱维亚 ">拉脱维亚 </option>
                            
                              <option value="莱索托">莱索托</option>
                            
                              <option value="老挝">老挝</option>
                            
                              <option value="黎巴嫩">黎巴嫩</option>
                            
                              <option value="利比里亚">利比里亚</option>
                            
                              <option value="利比亚">利比亚</option>
                            
                              <option value="立陶宛">立陶宛</option>
                            
                              <option value="留尼汪岛">留尼汪岛</option>
                            
                              <option value="卢森堡">卢森堡</option>
                            
                              <option value="卢旺达">卢旺达</option>
                            
                              <option value="罗马尼亚">罗马尼亚</option>
                            
                              <option value="马达加斯加">马达加斯加</option>
                            
                              <option value="马耳他">马耳他</option>
                            
                              <option value="马尔代夫">马尔代夫</option>
                            
                              <option value="马拉维">马拉维</option>
                            
                              <option value="马来西亚">马来西亚</option>
                            
                              <option value="马里">马里</option>
                            
                              <option value="马其顿">马其顿</option>
                            
                              <option value="马绍尔群岛">马绍尔群岛</option>
                            
                              <option value="马提尼克">马提尼克</option>
                            
                              <option value="马约特">马约特</option>
                            
                              <option value="毛里求斯">毛里求斯</option>
                            
                              <option value="毛里塔尼亚">毛里塔尼亚</option>
                            
                              <option value="美国 ">美国 </option>
                            
                              <option value="美属维尔京群岛">美属维尔京群岛</option>
                            
                              <option value="蒙古">蒙古</option>
                            
                              <option value="蒙特塞＋拉特">蒙特塞＋拉特</option>
                            
                              <option value="蒙特塞拉特">蒙特塞拉特</option>
                            
                              <option value="孟加拉">孟加拉</option>
                            
                              <option value="秘鲁">秘鲁</option>
                            
                              <option value="缅甸 ">缅甸 </option>
                            
                              <option value="摩尔多瓦">摩尔多瓦</option>
                            
                              <option value="摩洛哥">摩洛哥</option>
                            
                              <option value="莫桑比克">莫桑比克</option>
                            
                              <option value="墨西哥">墨西哥</option>
                            
                              <option value="纳米比亚">纳米比亚</option>
                            
                              <option value="南非">南非</option>
                            
                              <option value="南斯拉夫">南斯拉夫</option>
                            
                              <option value="尼泊尔 ">尼泊尔 </option>
                            
                              <option value="尼加拉瓜">尼加拉瓜</option>
                            
                              <option value="尼日尔">尼日尔</option>
                            
                              <option value="尼日利亚">尼日利亚</option>
                            
                              <option value="挪威">挪威</option>
                            
                              <option value="葡萄牙">葡萄牙</option>
                            
                              <option value="日本">日本</option>
                            
                              <option value="瑞典 ">瑞典 </option>
                            
                              <option value="瑞士">瑞士</option>
                            
                              <option value="萨尔瓦多">萨尔瓦多</option>
                            
                              <option value="塞班">塞班</option>
                            
                              <option value="塞内加尔">塞内加尔</option>
                            
                              <option value="塞浦路斯">塞浦路斯</option>
                            
                              <option value="塞舌尔">塞舌尔</option>
                            
                              <option value="沙特阿拉伯">沙特阿拉伯</option>
                            
                              <option value="圣巴泰勒米">圣巴泰勒米</option>
                            
                              <option value="圣多美">圣多美</option>
                            
                              <option value="圣基茨">圣基茨</option>
                            
                              <option value="圣卢西亚">圣卢西亚</option>
                            
                              <option value="圣马丁">圣马丁</option>
                            
                              <option value="圣文森特">圣文森特</option>
                            
                              <option value="圣尤斯特歇斯">圣尤斯特歇斯</option>
                            
                              <option value="斯里兰卡">斯里兰卡</option>
                            
                              <option value="斯洛伐克">斯洛伐克</option>
                            
                              <option value="斯洛文尼亚">斯洛文尼亚</option>
                            
                              <option value="斯威士兰 ">斯威士兰 </option>
                            
                              <option value="苏丹">苏丹</option>
                            
                              <option value="苏里南">苏里南</option>
                            
                              <option value="索马里">索马里</option>
                            
                              <option value="所罗门群岛">所罗门群岛</option>
                            
                              <option value="塔吉克斯坦">塔吉克斯坦</option>
                            
                              <option value="塔希提">塔希提</option>
                            
                              <option value="台湾 ">台湾 </option>
                            
                              <option value="泰国">泰国</option>
                            
                              <option value="坦桑尼亚">坦桑尼亚</option>
                            
                              <option value="汤加">汤加</option>
                            
                              <option value="特克斯和凯科斯群岛">特克斯和凯科斯群岛</option>
                            
                              <option value="特立尼达和多巴哥">特立尼达和多巴哥</option>
                            
                              <option value="突尼斯">突尼斯</option>
                            
                              <option value="图瓦鲁">图瓦鲁</option>
                            
                              <option value="土耳其">土耳其</option>
                            
                              <option value="土库曼斯坦">土库曼斯坦</option>
                            
                              <option value="瓦努阿图 ">瓦努阿图 </option>
                            
                              <option value="危地马拉">危地马拉</option>
                            
                              <option value="委内瑞拉 ">委内瑞拉 </option>
                            
                              <option value="文莱">文莱</option>
                            
                              <option value="乌干达">乌干达</option>
                            
                              <option value="乌克兰">乌克兰</option>
                            
                              <option value="乌拉圭">乌拉圭</option>
                            
                              <option value="乌兹别克斯坦">乌兹别克斯坦</option>
                            
                              <option value="西班牙">西班牙</option>
                            
                              <option value="西萨摩亚">西萨摩亚</option>
                            
                              <option value="希腊">希腊</option>
                            
                              <option value="香港">香港</option>
                            
                              <option value="新加坡">新加坡</option>
                            
                              <option value="新喀里多尼亚 ">新喀里多尼亚 </option>
                            
                              <option value="新西兰">新西兰</option>
                            
                              <option value="匈牙利">匈牙利</option>
                            
                              <option value="叙利亚">叙利亚</option>
                            
                              <option value="牙买加">牙买加</option>
                            
                              <option value="亚美尼亚">亚美尼亚</option>
                            
                              <option value="也门">也门</option>
                            
                              <option value="伊拉克">伊拉克</option>
                            
                              <option value="伊朗">伊朗</option>
                            
                              <option value="以色列">以色列</option>
                            
                              <option value="意大利">意大利</option>
                            
                              <option value="印度">印度</option>
                            
                              <option value="印度尼西亚">印度尼西亚</option>
                            
                              <option value="英国">英国</option>
                            
                              <option value="英属维尔京群岛">英属维尔京群岛</option>
                            
                              <option value="约旦">约旦</option>
                            
                              <option value="越南">越南</option>
                            
                              <option value="赞比亚">赞比亚</option>
                            
                              <option value="乍得">乍得</option>
                            
                              <option value="直布罗陀">直布罗陀</option>
                            
                              <option value="智利">智利</option>
                            
                              <option value="瑙鲁">瑙鲁</option>
                            
                              <option value="斐济">斐济</option>
																	</select>
																</td>
																<td width="39%" height="24" align="left">
																	<input name="weightid1"
																			id="weightid1" type="text" maxlength="7" size="18"
																			class="input1" />
																</td>
															</tr>
															<tr class="fontTr">
																<td width="41%" height="20" align="left" valign="bottom"
																	nowrap="nowrap">
																	<span class="txt-form-n-tt">内件性质</span>
																</td>
																<td width="39%" align="left" valign="bottom">
																	<span class="txt-form-n-tt">计算结果 (元)：</span>
																</td>
															</tr>
															<tr valign="bottom">
																<td width="41%" height="20" align="left"
																	class="unnamed2">
																	<input type="radio" name="njxz" value="files" checked="checked">文件
																	<input type="radio" name="njxz" value="goods">物品
																</td>
																<td width="39%" align="left" class="unnamed2">
																	<input readonly size="18" value="0" id="goffee"
																		name="goffee" class="input1" />
																</td>
															</tr>
															<tr class="fontTr" >
																<td colspan="5" align="left">
																	<br>
																	<span id="resultInfo"><font color="#CE7000" style="font-size: 9pt">说明：查询结果仅作为参考</font></span>
																</td>
															</tr>
														</table>
													</div>
													
													
													<div id="tab4" style="display: none">
														<form action="/query/ftime.do" target="_top" method="post">
		<table border="0" width="620">
			<tr class="fontTr">
				<td align="right" width="30%">
					交寄日期：
				</td>
				<td width="70%" align="left" >
					<%
						Calendar calendar = Calendar.getInstance();
						int year = calendar.get(Calendar.YEAR);
						int month = calendar.get(Calendar.MONTH) + 1;
						int day = calendar.get(Calendar.DATE);
						String date = year + "-" + month + "-" + day;
					%>
					<input type="text" id="I_AcceptDate" name="search.I_AcceptDate" value="<%=date%>" onclick="setday(this)"
						readonly size="12" />
				</td>
			</tr>
			<tr class="fontTr">
				<td align="right">
					交寄地邮政编码：
				</td>
				<td align="left">
					<input type="text" id="I_OriginPostalCode" name="search.I_OriginPostalCode" value="362000"/>
				</td>
			</tr>
			<tr class="fontTr">
				<td align="right">
					寄达国家（地区）：
				</td>
				<td align="left">
					<select id="I_DestinationCountry" name="search.I_DestinationCountry" onchange="changeDestinationCountry()">
						<option value="AU">
							AUSTRALIA
						</option>
						<option value="ES">
							SPAIN
						</option>
						<option value="FR">
							FRANCE
						</option>
						<option value="GB">
							GREAT BRITAIN
						</option>
						<option value="HK" selected="selected">
							HONG KONG
						</option>
						<option value="JP">
							JAPAN
						</option>
						<option value="KR">
							KOREA (REP.)
						</option>
						<option value="SG">
							SINGAPORE
						</option>
						<option value="US">
							UNITED STATES
						</option>
					</select>
					<input type="hidden" id="I_DestinationCountryName" name="search.I_DestinationCountryName" />
					<input type="hidden" id="I_OriginCountry" name="search.I_OriginCountry" value="CN"/>
					<input type="hidden" id="I_I_OriginCountryName" name="search.I_OriginCountryName" value="CHINA"/>
				</td>
			</tr>
			<tr class="fontTr">
				 <td align="right">
					寄达地邮政编码：
				</td>
				<td align="left">
					<input type="text" id="I_DestinationPostalCode" name="search.I_DestinationPostalCode"  disabled="disabled"/> &nbsp;&nbsp;&nbsp;   <input type="button" value="查询" onclick="queryFTime()"/>
				</td>
			</tr>
			
		</table>
		</form>
													</div>
														
													<div id="tab7" style="display: none">
														<table id="stateTable" border="0" width="100%"  border="0" cellpadding="0" cellspacing="0">
															<tr align="center" class="lineTr">
																<td align="left" width="25%">省份:<select id="pprovince" name="search.pprovince"
								onchange="changeCombo('pcity','pprovince','cityvalue')"></select></td><td align="left" width="30%">地市:<select id="pcity" name="search.pcity"
								onchange="changeCombo('pcounty','pcity','countyvalue')"></select></td><td align="left" width="30%" nowrap="nowrap">县、区:<select id="pcounty" name="search.pcounty"></select></td>
								<td width="20%" align="left"><input type="button" value="查询" onclick="query()"/></td>
															</tr>
															<tr class="lineTr" onMouseOver="this.style.backgroundColor='#7db8f3';this.style.color ='#ffffff';" onMouseOut="this.style.backgroundColor='';this.style.color ='';">
																
																<td align="left" colspan="2">查询结果:<span id="address"></span></td><td align="left" colspan="2">邮编:<span id="postalcode"></span></td>
															</tr>
														</table>
													</div>
													
														
												</td>
											</tr>
										</table>
								</td>
							</tr>
						</table>
				<jsp:include page="/index_bottom.jsp"></jsp:include>
</div>
<script type="text/javascript">
	//2009-12-01
			//省,地,市连动
			//参数说明:chCombo变化控件的id
			//		  srcCombo引起变化的控件id
			//		  action事件类型
			function changeCombo(chCombo,srcCombo,action)
			{
				var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
			    cdsales.async=false; //使用异步加载
			    var parmid='';
			    if(srcCombo!='')
			    {
			      parmid=document.getElementById(srcCombo).value
			    }
			    cdsales.load("<%=path%>/archives/area.do?action="+action+"&search.pid="+parmid);
			     var bi;
			     if(cdsales.documentElement!=null)
			         bi=cdsales.documentElement.selectNodes("NODE");
			    if(bi!=null&&bi.length>0)
			    {
			       //默认行
			       document.getElementById(chCombo).options.length = bi.length+1;   
			       document.getElementById(chCombo).options[0].value ="0";//隐藏值
				   document.getElementById(chCombo).options[0].text = "请选择1";//显示值
			       for(var i=1;i<bi.length+1;i++){     
				   			document.getElementById(chCombo).options[i].value = bi[i-1].selectSingleNode("THE_CODE").text;//隐藏值
				   			document.getElementById(chCombo).options[i].text =  bi[i-1].selectSingleNode("THE_NAME").text;//显示值
			       }
			    }else{
			       document.getElementById(chCombo).options.length = 1;   
			       document.getElementById(chCombo).options[0].value ="0";//隐藏值
				   document.getElementById(chCombo).options[0].text = "请选择";//显示值
			    }
			}
			//初始化信息
			changeCombo('pprovince','','provincevalue');
			//document.getElementById('pprovince').options[12].selected;
			//默认福建省
			document.getElementById('pprovince').value="350000";
			changeCombo('pcity','pprovince','cityvalue');
			changeCombo('pcounty','pcity','countyvalue');
			
			
			///////时限列表
			//2010-03-11
			//地市不能重复
			//chCombo变化控件的id
			//参数说明:srcCombo 引起变化的控件id
			function changeCity(chCombo,srcCombo){
				var srcId=document.getElementById(srcCombo).value;//源CityId 
				var chId=document.getElementById(chCombo).value;//另一个CityId
				if(srcId==chId){
					//若相等
					document.getElementById(chCombo).value="0";
				}
			}
			
			function timeQuery(o,path){
				$("#resultValue").text("");
				var s=$("#pcity3").val();
				var r=$("#pcity4").val();
				if (s== "" ||  s=="0"){alert("如果进行模糊查询请选择原寄地城市");s.focus();return;}
    			if (r== "" ||  r=="0"){alert("如果进行模糊查询请选择收寄地城市");r.focus();return;}
    			
    			var urls = getActionMappingURL("/query/time",path);
    			var sendCityCode=$('#pcity3').val();
    			var rcvCityCode=$('#pcity4').val();
    			var sendProvinceCode=$("#pprovince3").val().substring(0,2);
    			var rcvProvinceCode=$("#pprovince4").val().substring(0,2);
		        var pars="date="+new Date+"&search.sendCityCode="+sendCityCode+"&search.rcvCityCode="+rcvCityCode+
		        	"&search.sendProvinceCode="+sendProvinceCode+"&search.rcvProvinceCode="+rcvProvinceCode;
		        $("#timeQueryId").attr({"disabled":true});
		        $.ajax({type: "POST",
		        url:urls,data:pars,success:function(res){
			    	$('#messageDiv1').html(res);
			  		var jsonMsgObj = new JsonMsgObj($('#messageDiv1').text());
			  		var codeid = jsonMsgObj.getCodeid();
			  		//alert(jsonMsgObj.getMessage());
			  		if (codeid == "0") {
			  			$("#resultValue").text(jsonMsgObj.getMessage());
			  			$("#timeQueryId").attr({"disabled":false});
			  		}
		        }});
		        
		        
		      	function showResult1(res){
		      		$("#resultValue").html(res);
		      		
		      	}
    			
    			
    			
			}
			
		
			//2009-12-01
			//省,地,市连动
			//参数说明:chCombo变化控件的id
			//		  srcCombo引起变化的控件id
			//		  action事件类型
			function changeCombo1(chCombo,srcCombo,action)
			{
						var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
					    cdsales.async=false; //使用异步加载
					    var parmid='';
					    if(srcCombo!='')
					    {
					    	//触发省份的
					      	parmid=document.getElementById(srcCombo).value; //省份的值
					    }
					    cdsales.load("<%=path%>/query/area.do?action="+action+"&search.pid="+parmid);
					     var bi;
					     if(cdsales.documentElement!=null)
					         bi=cdsales.documentElement.selectNodes("NODE");
					    if(bi!=null&&bi.length>0)
					    {
					       //默认行
					       document.getElementById(chCombo).options.length = bi.length+1;   
					       document.getElementById(chCombo).options[0].value ="0";//隐藏值
						   document.getElementById(chCombo).options[0].text = "请选择";//显示值
					       for(var i=1;i<bi.length+1;i++){     
						   		document.getElementById(chCombo).options[i].value = bi[i-1].selectSingleNode("THE_CODE").text;//隐藏值
						   		document.getElementById(chCombo).options[i].text =  bi[i-1].selectSingleNode("THE_NAME").text;//显示值
					       }
					    }else{
					       document.getElementById(chCombo).options.length = 1;   
					       document.getElementById(chCombo).options[0].value ="0";//隐藏值
						   document.getElementById(chCombo).options[0].text = "请选择";//显示值
					    }
					}
				
					//初始化信息
					changeCombo1('pprovince3','','provincevalue');
					changeCombo1('pprovince4','','provincevalue');
					//默认福建省
					document.getElementById('pprovince3').value="350000";
					
					changeCombo1('pcity3','pprovince3','cityvalue');
					changeCombo1('pcity4','pprovince4','cityvalue');
					
		//设置默认页面
		<s:if test="%{search.ptab==1}">
			loadOnLineQuery();	
		</s:if>
		
</script>
</body>

</html>
