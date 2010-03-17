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
 <style type="text/css"> 
  a:link {color: #000000; text-decoration:none} 
  a:visited {color:#000000; text-decoration: none} 
  a:active {color:#000000; text-decoration: none} 
  a:hover {color:#ff0000; text-decoration:underline} 
 </style> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>

		<title>福建省183速递官方网站</title>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
	</head>
<script type="text/javascript">
 	//保存数据
	function login(){
	     if(checkmsg()==true){
			var url ='/frontlogin.do';
			document.forms[0].action=url;
			document.forms[0].method="POST";
			document.forms[0].submit();
		}
	}
	
	function checkEms(){
		document.forms[0].submit();
	}
	
	//验证
    function checkmsg(){
    	result=false;
    	var pcode=document.getElementById("pcode").value;
    	var ppasswd=document.getElementById("ppasswd").value;
    	var pverifycode=document.getElementById("pverifycode").value;
    	if(!(pcode.length>=6&&pcode.length<=40))
    	{
    		alert('用户名必须在6到40个字符之间');
    		return result;
    	}
    	if(!(ppasswd.length>=6&&ppasswd.length<=20))
    	{
    		alert('密码必须在6到20个字符之间');
    		return result;
    	}
    	if(!(pverifycode.length==4))
    	{
    		alert('验证码必须4个字符');
    		return result;
    	}
		return true;
    }
	
	//发送密码到手机,邮箱==手机.密码设置
	function fun_forget(){
			var str=prompt("请输入电子邮箱:","123@163.com");
			if (str)
			{
			    alert("电子邮箱:"+str+"已经提交到服务器,您的手机将会得到重置后的密码!");
			}
			else
			{
			    alert("你点击了取消");
			}
	}
	
	//注册页面
	function regedit(){
	        window.location.href="/regedit.jsp";
	}
	
	function makeRequest(url,ownertypenum){
				
				var urls="http://211.156.193.130/tools/postageresult.jsp";
				var addressfrom=$("#addressfrom").val();
				var addressto=$("#addressto").val();
				var weight=$("#weight").val();
				if(addressfrom==""){
					alert("寄件人省份下拉选框无数据!");
					return false;
				}
				if(addressto==""){
					alert("收件人省份下拉选框无数据!");
					return false;
				}
				if(weight==""){
					alert("查询的输入值不能为空!");
					return false;
				}
				if(weight>40){
					alert("国内特快专递邮件,单件重量不能超过40公斤,超过的需分件交寄!");
					return false;
				}
		  	    var pars="date="+new Date()+"&reqCode=Postage&addressfrom="+encodeURIComponent(addressfrom)+"&addressto="+encodeURIComponent(addressto)+"&weight="+weight ;
		  	    //alert(pars);
		        $.get(urls,pars,showResult1);
		      	function showResult1(res){
		      		//alert(res);没有相关资费讯息
		      		$("#gofee").val(res);
		      	}
			}
	</script>
	

	<script type="text/javascript">	
	 document.body.style.backgroundImage="url(images/bg.jpg)";
    </script>
	
	<body>
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
					<font color="#FFFFFF" style="font-size: 9pt">
					<a href="/news.do?action=fir">首页</a> </font></b></td>
					<td align="center">
									<b> <font color="#FFFFFF" style="font-size: 9pt"><a
											href="/news.do?action=FrontNews&search.artype=1">企业品牌</a> </font> </b>
								</td>
								<td align="center">
									<b> <font color="#FFFFFF" style="font-size: 9pt"><a
											href="/news.do?action=FrontNews&search.artype=2">企业文化</a> </font> </b>
								</td>
								<td align="center">
									<b> <font color="#FFFFFF" style="font-size: 9pt"> <a
											href="/news.do?action=FrontNews&search.artype=3">新闻动态</a> </font> </b>
								</td>
					<td width="167" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">产品服务</font></b></td>
					<td width="157" align="center"><b>
					<font color="#FFFFFF" style="font-size: 9pt">专项营销</font></b></td>
				</tr>
			</table>
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6">
				<tr>
					<td width="193" valign="top">
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="252" id="table7" background="images/lft1.jpg" height="242">
						<tr>
							<td valign="top">
						
							<table border="0" cellpadding="0" style="border-collapse: collapse" width="252" id="table17" height="92">
								<tr>
									<td valign="top">
									<table border="0" cellpadding="0" style="border-collapse: collapse;" width="251" id="table18" height="237">
										<tr>
											<td height="20" align="left" >
											<img border="0" src="images/gonggaolan.jpg" width="71" height="13"></td>
										</tr>
										<tr>
											<td height="200" >
           
              
              <font color="#6BBDCF" style="font-size: 9pt">
<marquee direction=up scrollAmount=1 height=188 onMouseOut=this.start(); onMouseOver=this.stop(); width=227 border="8" style="padding:0; ">
 <s:if test="topSixNews!=null">
 <s:iterator id="tsix" value="%{topSixNews.objectList}">	
<br>● <a href="/news.do?action=newsDetail&search.newsID=<s:property value="#tsix.NEWSID"/>" >
													    <s:property value="#tsix.TITLE"/></a>(<s:property value="#tsix.RELEASETIME"/>)
</s:iterator>
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
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table8">
						<tr>
							<td><table cellpadding="0"  cellspacing="0" width="252"><tr><td><a href="/news.do?action=FrontNews&search.artype=3"><img border="0" src="images/top111.jpg"  height="45"></a></td>
							 <td><a href="/news.do?action=FrontNews&search.artype=4"><img border="0" src="images/top222.jpg"  height="45"></a></td>
							 <td><a href="/news.do?action=FrontNews&search.artype=5"><img border="0" src="images/top333.jpg"  height="45"></a></td>
							 </tr></table></td>
						</tr>
					</table>
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="252" id="table9" background="images/left33.jpg" height="210">
						<tr>
							<td>
							<table border="0" cellpadding="0" style="border-collapse: collapse;" width="251" id="table19" height="201">
								<tr>
									<td valign="top">
									
										<table id="table28" style="width: 238px; border-collapse: collapse" cellPadding="0" border="0">
											
											
											<tr>
												<td>
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
														<a href="/news.do?action=newsDetail&search.newsID=<s:property value="#teight.NEWSID"/>" >
													    <s:property value="#teight.TITLE"/></a>…</span></font></td>
													</tr>
												</table>
													</s:iterator>
													</s:if>
												</td>
											</tr>
										
											
											
											
											
											
										</table>
									
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
					<td valign="top">
					<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table11">
						<tr>
							<td>
							<img border="0" src="images/pic.jpg" width="709" height="242"></td>
						</tr>
					</table>
					
					
					
				<table border="0" cellpadding="0"
										style="border-collapse: collapse" width="100%" id="table12">
										<tr>
											<td background="images/navA.jpg" id="td1"
													onclick="loadOnLineQuery();"  width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">自助寄件</span>
												</font>
											</td>
											<td background="images/navB.jpg" id="td2"
											 onclick="loadPrint();" width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">自助打单</span>
												</font>
											</td>
											<td background="images/navB.jpg" id="td3"
											   onclick="loadEmsQuery();" width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														EMS邮件查询</span> </font>
											</td>
											<td background="images/navB.jpg" id="td4"
												 onclick="loadBoundQuery();" width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														揽收范围查询</span> </font>
											</td>
											<td background="images/navB.jpg" id="td5"
												  onclick="loadTimeQuery();" width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">时限查询</span>
												</font>
											</td>
											<td background="images/navB.jpg" id="td6"
												onclick="loadMoneyQuery();" width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">
														寄件资费查询</span> </font>
											</td>
											<td background="images/navB.jpg" id="td7"
												 onclick="loadAddressQuery();" width="101" height="45"
												align="center">
												<font color="#FFFFFF"> <span
													style="font-weight: 700; font-size: 3pt"><br> </span>
													<span style="font-size: 9pt; font-weight: 700">邮政编号查询</span>
												</font>
											</td>
											<td>
											</td>
										</tr>
									</table>
								

										<table border="0" cellpadding="0"
											style="border-collapse: collapse" width="100%" id="table13"
											height="210" bgcolor="#E5E5E5">
											<tr>
												<td valign="middle">
													<s:form action="query" method="get" target="_top" namespace="/">
													
													
													<div id="tab1"  align="center">
														<table>
															<tr>
																<td>
																<s:form action="frontlogin" target="_top" namespace="/">
																<table border="0" cellpadding="0"
																	style="border-collapse: collapse" width="248"
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
																		<td width="79" height="29">
																			<input type="text" name="search.pverifycode"
																				id="pverifycode" size="8"
																				style="border: 1px solid #808080; background-color: #A6D7E1">
																		</td>
																		<td width="91" height="29">
																			<img border=0 width="60" height="18" src="/authimg"
																				id="imgcode" style="cursor: pointer;" border="0"
																				onClick="document.getElementById('imgcode').src='/authimg?action='+Math.random();"
																				title="看不清？！换一个" alt="看不清？！换一个" />
																	</tr>
																	<tr>
																		<td width="68" height="29">
																		</td>
																		<td width="180" height="29" colspan="2">
																			<span style="font-size: 9pt"> <input
																					type="button" onclick="javascript:login();"
																					value="登 陆" name="B1"
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
																	</tr>
																</table>
															</s:form>
																</td>
															</tr>
														</table>
													</div>
													
													
													
													
													<div id="tab2" style="display: none">
														<table>
															<tr>
																<td>
																	2222
																</td>
															</tr>
														</table>
													</div>
													
													
													<div align="center" id="tab3" style="display: none">
														<table border="0" cellpadding="0"
															style="border-collapse: collapse" width="620"
															id="table37">
															<tr>
																<td height="20" width="80">
																</td>
																<td height="20" width="540" colspan="2">
																</td>
															</tr>
															<tr>
																<td height="67" width="80">
																	<font color="#5B6E75" style="font-size: 9pt">
																		输入单号：</font>
																</td>
																<td height="67" width="540" colspan="2" align="left">
																	<input type="text" name="mailNum" id="mailNum"
																		style="border: 1px solid #A8A8A8; background-color: #E0EFF6" />
																</td>
															</tr>
															
															<tr>
																<td width="80" height="34">
																</td>
																<td width="540" height="34" colspan="2">
																	<s:hidden name="emsCheckCode" id="emsCheckCode"></s:hidden>
																	<span style="font-size: 9pt"> <input
																			type="button" value="查 询" name="B6"
																			onclick="javascript:checkEms();"
																			style="width: 44; height: 22; font-size: 12px; color: #6D6D6D; border-style: outset; border-width: 0px; background-image: url('images/denglu.gif'); background-position: left top">&nbsp;
</span>

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
													</div>
														
													<div id="tab4" style="display: none">
														<table>
															<tr>
																<td>
																	4444
																</td>
															</tr>
														</table>
													</div>
													
														
													<div id="tab5" style="display: none">
														<table>
															<tr>
																<td>
																	55555
																</td>
															</tr>
														</table>
													</div>
													<div id="tab6" style="display: none">
														<table width="514" border="0" cellpadding="0"
															cellspacing="0">
															<tr>
																<td width="41%" height="10" align="left" valign="bottom"
																	nowrap="nowrap" class="txt-form-n-tt">
																	寄出所在省份
																</td>
																<td width="39%" height="10" align="left" valign="bottom">
																	<span class="txt-form-n-tt">计费重量（公斤）</span>
																</td>
																<td width="20%" rowspan="4" align="center">
																	<span class="unnamed2"> <input
																			onclick="makeRequest('http://www.ems.com.cn/tools/postageresult.jsp',1)"
																			type="button" value="计  算" name="clickfee" /> </span>
																</td>
															</tr>
															<tr>
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
																			class="input1" /> </span>
																</td>
															</tr>
															<tr>
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
															<tr valign="bottom">
																<td height="20" align="left" class="unnamed2">
																	&nbsp;&nbsp;
																	<!--<div id=process style="width: 300; height: 10"></div>-->
																</td>
																<td height="20" align="left" class="unnamed2">
																	&nbsp;
																	<span id="msg" class="txt-form-n-tt"></span>
																</td>
																<td align="left" class="unnamed2">
																	&nbsp;
																</td>
															</tr>

														</table>
													</div>
													
														
													<div id="tab7" style="display: none">
														<table>
															<tr>
																<td>7777 
																<br></td>
															</tr>
														</table>
													</div>
													
													
													
													
													
														</s:form>
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
