<%@page contentType="text/html; charset=gbk"%>
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
		<script type="text/javascript">
		<!--
		   
			$(document).ready(function(){
				var urls="http://10.3.10.83/ems/newsystem/thesecond/ttq/ttqMailquery.jsp";
		  	    var myEmsbarCode;
		  	    var pars="date="+new Date();
		       $.get(urls,pars,showResult1);
			});
			
			function showResult1(res){
				  $('#queryResult2').html(res);
				  myEmsbarCode=document.getElementById("myEmsbarCode").value;
				 	
					var url1="http://10.3.10.83/ems/qcgzBaseQueryAction.do";
					var pars1="reqCode=regoUrl&myEmsbarCode="+myEmsbarCode+"&mailNum="+"<s:property value="%{mailNum}"/>"+"&select=1&date="+new Date();
					$.get(url1,pars1,showResult);
				    function showResult(res2){
				      
				      $(document).unbind(); 
					  $('#queryResult2').html(res2);
					  //alert(res2);
					 
					  $("body").unbind();
					 
					  var frame_action=document.getElementById("ajaxFrameArea").src;
					  
					  var url2="http://10.3.10.83/ems/qcgzBaseQueryAction.do";
					  var pars2="reqCode=browseBASE&select=1&md5Mail="+frame_action.split("&")[2].substring(8)+"&mailNum="+"<s:property value="%{mailNum}"/>"+"&date="+new Date();
					  
					  
					  $.get(url2,pars2,showResult2);
					  function showResult2(res5){
					  	//$('#queryResult1').text(res5);
					  	
					  	$('#queryResult2').html(res5);
					  	//alert($('#frameTrackBaseInfo tr td:eq(1)').text());
					  	$('#td11').text($('#frameTrackBaseInfo tr td:eq(1)').text());
					  	$('#td12').text($('#frameTrackBaseInfo tr td:eq(3)').text());
					  	$('#td21').text($('#frameTrackBaseInfo tr td:eq(5)').text());
					  	$('#td22').text($('#frameTrackBaseInfo tr td:eq(7)').text());
					  	$('#td31').text($('#frameTrackBaseInfo tr td:eq(9)').text());
					  	$('#td32').text($('#frameTrackStatueInfo tr td:eq(0)').text());
					  	$('#td40').text("("+$('#frameTrackStatueInfo tr td:eq(2)').text()+")");
					  	$('#td41').text($('#frameTrackStatueInfo tr td:eq(4)').text());
					  	$('#td42').text($('#frameTrackStatueInfo tr td:eq(6)').text());
					  	//alert($('.lineTr').text());
					  	var oBody=document.all("table53");//mobileTbody
					  	$("tr[class='lineTr']:gt(0)").each(function(idx1,item){
					  		var myTR =oBody.insertRow(); //插入行
						  	$(this).children("td").each(function(idx1,item2){
						  		var myTD1=myTR.insertCell();	
								myTD1.innerHTML="<td>"+$(this).text()+"</td>";
						  	
						  		//$("#maintaintbody").text($(this).text());
						  	});
							
						});
					  	
					  	
					  	/*可行
					  	$('#queryResult2').html(res5);
					  	alert($('#frameTrackBaseInfo').text());
					  	*/
					  	
					  	
					  }
					  
					  
				    }
			   }
			
		//-->
		</script>
	</head>


<body background="http://localhost:8088/images/bg.jpg">

<div align="center">
	<table border="4" cellpadding="0" style="border-collapse: collapse" width="969" id="table3" height="648" bordercolor="#FFFFFF" cellspacing="0">
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
					<font color="#FFFFFF" style="font-size: 9pt">首页</font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">企业品牌</font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">企业文化</font></b></td>
					<td align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">新闻动态</font></b></td>
					<td width="167" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">产品服务</font></b></td>
					<td width="157" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">专项营销</font></b></td>
				</tr>
			</table>
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
										<td>
										<font color="#FFFFFF" style="font-size: 10pt">
										<span style="font-weight: 700">EMS邮件查询</span></font></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td background="images/nei02.jpg">
								<span id="TabSkinContainer44">
								<span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent" class="Normal">
								<table border="1" cellSpacing="0" cellPadding="0" width="100%"  id="table52">
									<tr>
										<td height="14" width="566" >
											<table width="100%">
												<tr>
													<td align="left" width="25%">收寄时间:</td><td width="25%"><div id="td11"></div></td>
													<td width="25%">收寄局:</td><td width="25%"><div id="td12"></div></td>
												</tr>
												<tr>
													<td align="left" width="25%">寄达地:</td><td width="25%"><div id="td21"></div></td>
													<td width="25%">邮件种类:</td><td width="25%"><div id="td22"></div></td>
												</tr>
												<tr>
													<td align="left" width="25%">邮件性质:</td><td width="25%"><div id="td31"></div></td>
													<td width="25%">邮件编号:</td><td width="25%"><div id="td32"></div></td>
												</tr>
												<tr>
													<td align="left" width="25%"><div id="td40"></div></td><td width="25%"><div id="td41"></div></td>
													<td width="25%">地点:</td><td width="25%"><div id="td42"></div></td>
												</tr>
												
											</table>
										<div id="queryResult1"></div>
										<div id="queryResult2" style="display:none"></div>
										<div id="queryResult3" ></div>
										</td>
									</tr>
									
									<tr >
										<td height="14" width="100%" >
											<table width="100%" border="1"  id="table53">
												<tr>
													<td align="left" width="20%">时间</td><td width="20%">处理站点</td>
													<td width="20%">处理动作</td><td width="20%">处理说明</td><td width="20%">备注</td>
												</tr>
												
											</table>
										
											<a href="/index.jsp">返回</a>
										</td>
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
					<td valign="top" bgcolor="#02687F">
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table37">
						<tr>
							<td>
							<img border="0" src="images/left22.jpg" width="248" height="45"></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="248" id="table38" background="images/left333.jpg" height="210">
						<tr>
							<td>
							<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table39">
								<tr>
									<td><span id="TabSkinContainer">
									<div align="center">
										<table id="table40" style="width: 238px; border-collapse: collapse" cellPadding="0" border="0">
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table41">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<font color="#FFFFFF">
														<span style="font-size: 9pt; text-decoration: none">
														自2010年1月6日起，EMS新开通安徽</span></font><span id="TabSkinContainer34"><span id="TabSkinContainer35"><span id="TabSkinContainer36"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer37"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table42">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer0">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span style="font-size: 9pt; color: #FFFFFF; text-decoration: none" id="titlestylespan">
														2010年1月4日起，</span><span id="TabSkinContainer38"><font color="#FFFFFF"><span style="font-size: 9pt; text-decoration: none">EMS</span></font></span><span style="font-size: 9pt; color: #FFFFFF; text-decoration: none" id="titlestylespan">为您打开韩国</span><span id="TabSkinContainer30"><span id="TabSkinContainer31"><span id="TabSkinContainer32"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer33"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table43">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer1">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span style="font-size: 9pt; color: #FFFFFF; text-decoration: none" id="titlestylespan">
														元旦期间</span><span id="TabSkinContainer39"><font color="#FFFFFF"><span style="font-size: 9pt; text-decoration: none">EMS</span></font></span><span style="font-size: 9pt; color: #FFFFFF; text-decoration: none" id="titlestylespan">提供收派件服务，详情</span><span id="TabSkinContainer26"><span id="TabSkinContainer27"><span id="TabSkinContainer28"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer29"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table44">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer2">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span style="font-size: 9pt; text-decoration: none">
														<font color="#FFFFFF">
														自2010年1月1日起，</font></span><span id="TabSkinContainer40"><font color="#FFFFFF"><span style="font-size: 9pt; text-decoration: none">EMS</span></font></span><span style="font-size: 9pt; text-decoration: none"><font color="#FFFFFF">新开通贵州</font></span><span id="TabSkinContainer22"><span id="TabSkinContainer23"><span id="TabSkinContainer24"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer25"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table45">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer3">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span style="font-size: 9pt; color: #FFFFFF; text-decoration: none" id="titlestylespan">
														12月6日起，</span><span id="TabSkinContainer41"><font color="#FFFFFF"><span style="font-size: 9pt; text-decoration: none">EMS</span></font></span><span style="font-size: 9pt; color: #FFFFFF; text-decoration: none" id="titlestylespan">网站月结积分兑换礼</span><span id="TabSkinContainer18"><span id="TabSkinContainer19"><span id="TabSkinContainer20"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer21"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table46">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer4">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span style="font-size: 9pt; text-decoration: none">
														<font color="#FFFFFF">
														重要提醒！假冒</font></span><span id="TabSkinContainer42"><font color="#FFFFFF"><span style="font-size: 9pt; text-decoration: none">EMS</span></font></span><span style="font-size: 9pt; text-decoration: none"><font color="#FFFFFF">网站骗取客户钱</font></span><span id="TabSkinContainer14"><span id="TabSkinContainer15"><span id="TabSkinContainer16"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer17"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												</td>
											</tr>
											<tr>
												<td vAlign="top">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table47">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer5">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span style="font-size: 9pt; text-decoration: none">
														<font color="#FFFFFF">
														自2009年12月1日起，</font></span><span id="TabSkinContainer43"><font color="#FFFFFF"><span style="font-size: 9pt; text-decoration: none">EMS</span></font></span><span style="font-size: 9pt; text-decoration: none"><font color="#FFFFFF">新开通河</font></span><span id="TabSkinContainer10"><span id="TabSkinContainer11"><span id="TabSkinContainer12"><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer13"><font color="#FFFFFF">…</font></span></span></span></span></td>
													</tr>
												</table>
												<span id="TabSkinContainer6">
												<table height="23" cellSpacing="0" cellPadding="0" width="100%" align="center" background="images/listbg.jpg" border="0" id="table48">
													<tr>
														<td align="middle" width="22" Command="ImageManager">
														<font color="#FFFFFF">
														<span style="font-size: 9pt" id="TabSkinContainer7">
														<img alt="" src="images/jiantou1.gif" border="0" width="5" height="9"></span></font></td>
														<td>
														<span id="TabSkinContainer8">
														<span style="font-size: 9pt; text-decoration: none">
														<font color="#FFFFFF">
														9月起, 大陆地区月结客户可享受</font></span><font color="#FFFFFF"><span style="font-size: 9pt">电</span></font><span style="font-size: 9pt; text-decoration: none" id="TabSkinContainer9"><font color="#FFFFFF">…</font></span></span></td>
													</tr>
												</table>
									</span>
												</td>
											</tr>
										</table>
									</div>
									</span></td>
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
</div>
</body>

</html>
