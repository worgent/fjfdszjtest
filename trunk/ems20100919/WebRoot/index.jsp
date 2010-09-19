<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@ page import="java.util.*"%>
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
		<link href="<%=path%>/css/indexnew.CSS" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>
		<script type="text/javascript" src="<%=path%>/js/index_query.js"></script>
		<script type="text/javascript"> 
var theInt = null;
var curclicked = 0;
 
$(function(){
	$('#transparence').css('opacity','0.4');
	$('#scrolltransparence').css('opacity','0.4');
	$('#imgload').css({'opacity':'1'});
	$('#pic_list img').css({'opacity':'0.6'});
	$('#pic_list img:eq(0)').css({'top':'0','opacity':'1'});
	$('#pic_list a').click(function(){return false});
	t(0);
	$('#pic_list img').mouseover(function(){
		if($('#this_pic').attr('src') == $(this).attr('src')) return;
		if(!$(this).is(':animated')){
			t($('#pic_list img').index($(this)));
		}
	})
})
 
t = function(i){
	clearInterval(theInt);
	if( typeof i != 'undefined' )
	curclicked = i;
		$('#this_pic').fadeOut(0).fadeIn(1500).attr('src',$('#pic_list img').eq(i).attr('src'));
		$('#this_a').attr('href',$('#pic_list img').eq(i).parents('a').attr('href'));
		$('#this_a').attr('title',$('#pic_list img').eq(i).parents('a').attr('title'));
		$('#pic_list img').eq(i).parents('li').nextAll('li').find('img').animate({top:18,opacity:0.6},1500);
		$('#pic_list img').eq(i).parents('li').prevAll('li').find('img').animate({top:18,opacity:0.6},1500);
		$('#pic_list img').eq(i).animate({top:0},1500).css('opacity','1');
	theInt = setInterval(function (){
		i++;
		if (i > $('#pic_list img').length - 1) {i = 0};
		$('#this_pic').fadeOut(0).fadeIn(1500).attr('src',$('#pic_list img').eq(i).attr('src'));
		$('#this_a').attr('href',$('#pic_list img').eq(i).parents('a').attr('href'));
		$('#this_a').attr('title',$('#pic_list img').eq(i).parents('a').attr('title'));
		$('#pic_list img').eq(i).parents('li').nextAll('li').find('img').animate({top:18,opacity:0.6},1500);
		$('#pic_list img').eq(i).parents('li').prevAll('li').find('img').animate({top:18,opacity:0.6},1500);
		$('#pic_list img').eq(i).animate({top:0},1500).css('opacity','1');
	},5000)
}
</script>		
		<!-- 前台图片样式 -->
		<style type="text/css">


#ft {
	margin: 10px auto 16px;
}

</style>

</head>
<body>
<br />	
<table border="0" cellpadding="0" style="border-collapse: collapse"
				width="800px" height="120px" id="table2" align="center" valign="middle">
<tr>
<td>
<img src="images/emslog.jpg" />
</td>
</tr>				
</table>				
<table border="0" cellpadding="0" style="border-collapse: collapse"
				width="540px" id="table1" align="center">
				<tr>
				<td>
		<br />
		<br />		
			<table border="0" cellpadding="0" style="border-collapse: collapse"
				width="540px" id="table11">
				<tr>
					<td>
						<div id="slide">
							<a href="#" target="_blank" id="this_a"><img
									src="images/1.jpg" id="this_pic" alt="" /> </a>
							<ul id="pic_list">
								<li>
									<a href="/news.do?action=FrontNews&search.artype=ex" target="_blank"><img src="images/1.jpg" alt="详询11185" />
									</a>
								</li>
								<li>
									<a href="/news.do?action=FrontNews&search.artype=ex" target="_blank"><img src="images/2.jpg" alt="详询11185" />
									</a>
								</li>
								<li>
									<a href="/news.do?action=FrontNews&search.artype=ex" target="_blank"><img src="images/3.jpg" alt="详询11185" />
									</a>
								</li>
							</ul>
							<span id="transparence"> </span>
						</div>
					</td>
				</tr>
			</table>
	

		<form action="/query/mail.do" method="post" target="_blank">

			<table cellpadding="0" border="0" style="border-collapse: collapse"
				width="540px">
				<tr>
				<td  style="display:table-cell;display: inline-block;vertical-align:middle">
				<font style="font-size: 11pt; font-weight: 700">邮件查询<input type="text" name="mailNum" id="mailNum" style="border: 1px solid #808080; background-color: #A6D7E1"/>
				&nbsp;&nbsp;验证码<input type="text" name="mailverifycode" id="mailverifycode" size="8" style="border: 1px solid #808080; background-color: #A6D7E1" /></font>
						<img style="vertical-align: middle" height="20" src="/mailauthimg"
							id="mailimgcode" style="vertical-align:middle;cursor: pointer;" border="1"
							onClick="document.getElementById('mailimgcode').src='/mailauthimg?action='+Math.random();"
							title="看不清？！换一个" alt="看不清？！换一个" /><s:hidden name="emsCheckCode" id="emsCheckCode"></s:hidden>
						&nbsp;&nbsp;<span style="font-size: 9pt"> 
						  <input type="button" value="查 询" name="B6" onclick="javascript:checkEms('<%=path%>');"
								style="cursor: hand; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: center center" />&nbsp;
						</span>
						<div id="messageDiv3" style="display: none"></div>							
				</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
					<td height="30" align="left">
						
					</td>
					<td align="left" nowrap="nowrap" style="vertical-align: middle;">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
			</table>
		</form>
		<div>
			<table border="0" cellpadding="0" height="30px"
				style="border-collapse: collapse" width="540px">
				<tr height="30">
					<td width="88px" height="30px" background="images/navB.jpg"
						id="td1" onclick="loadOnLineQuery();" align="center"
						style="cursor: hand; vertical-align: middle;">
						<font color="#FFFFFF"><span
							style="font-size: 9pt; font-weight: 700">自助服务</span> </font>
					</td>
					<td width=2px></td>
					<td background="images/navB.jpg" id="td5"
						onclick="loadTimeQuery();" width="88px" height="30"
						align="center" style="cursor: hand">
						<font color="#FFFFFF"> <span
							style="font-weight: 700; font-size: 3pt"></span> <span
							style="font-size: 9pt; font-weight: 700">国内时限查询</span> </font>
					</td>
					<td width=2px></td>
					<td background="images/navB.jpg" id="td4"
						onclick="loadBoundQuery();" width="88px" height="30"
						align="center" style="cursor: hand">
						<font color="#FFFFFF"> <span
							style="font-weight: 700; font-size: 3pt"></span> <span
							style="font-size: 9pt; font-weight: 700">国际时限查询</span> </font>
					</td> 
					<td width=2px></td>
					<td background="images/navB.jpg" id="td6"
						onclick="loadMoneyQuery();" width="88px" height="30" align="center"
						style="cursor: hand">
						<font color="#FFFFFF"> <span
							style="font-weight: 700; font-size: 3pt"></span> <span
							style="font-size: 9pt; font-weight: 700">国内资费查询</span> </font>
					</td>
					<td width=2px></td>
					<td width="88px" height="30" background="images/navB.jpg" id="td8"
						onclick="loadFMoneyQuery();" align="center" style="cursor: hand">
						<font color="#FFFFFF"> <span
							style="font-weight: 700; font-size: 3pt"></span> <span
							style="font-size: 9pt; font-weight: 700">国际资费查询</span> </font>
					</td>
					<td width=2px></td>
					<td background="images/navB.jpg" id="td7"
						onclick="loadAddressQuery();" width="88px" height="30"
						align="center" style="cursor: hand">
						<font color="#FFFFFF"> <span
							style="font-weight: 700; font-size: 3pt"></span> <span
							style="font-size: 9pt; font-weight: 700">邮政编码查询</span> </font>
					</td>
				</tr>
			</table>
		</div>

	
			<table border="0" cellpadding="0" style="border-collapse: collapse"
				width="540px" id="table13" height=210 bgcolor="#E5E5E5"
				style="display:none" align="center">
				<tr>
					<td>
						<div id="tab1" style="text-align: center; display: none">
							<s:form action="frontlogin" target="_top" namespace="/">
								<table border="0" cellpadding="0"
									style="border-collapse: collapse" width="540px" id="table16" align="center">
									<tr class="fontTr">
										<br />
										<td width="33%" align="right">
											用&nbsp;户&nbsp;名:
										</td>
										<td align="left" style="line-height: 2.5">
											<input type="text" name="search.pcode" id="pcode" size="23" />
										</td>
							
									</tr>
									<tr class="fontTr">
										<td align="right">
											密&nbsp;&nbsp;&nbsp;&nbsp;码:
										</td>
										<td  align="left" style="line-height: 2.5">
											<input type="password" name="search.ppasswd" id="ppasswd"
												size="24" />
										</td>
									</tr>
									<tr class="fontTr">
										<td align="right">
											验&nbsp;证&nbsp;码:
										</td>
										<td align="left" style="line-height: 2.5">
											<input type="text" name="search.pverifycode" id="pverifycode"
												size="8" />
											&nbsp;&nbsp;
											<img border=0 width="60" height="18" src="/authimg"
												id="imgcode" style="cursor: pointer;" border="0"
												onClick="document.getElementById('imgcode').src='/authimg?action='+Math.random();"
												title="看不清？！换一个" alt="看不清？！换一个" />
										</td>
									</tr>
									<tr class="fontTr">
										<td  align="center" colspan="2">
											<br />
											<span style="font-size: 9pt"> <input type="button"
													onclick="javascript:login();" value="登 录" name="B1"
													style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
												<input type="button" onclick="javascript:regedit();"
													value="注 册" name="B4"
													style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />

												&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
													onclick="javascript:back();" value="返回" name="B4"
													style="cursor: pointer; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
											</span>
										</td>
									</tr>
								</table>
								<br />
								<table border="0" cellpadding="0"
									style="border-collapse: collapse" width="70%">
									<tr>
										<td valign=bottom>
											<font color="#CE7000"> <span style="font-size: 9pt">
												温馨提示：欢迎使用福建省邮政速递网上自助服务功能，我们提供给您便捷快速的网上寄件、详情单打印、客户管理等贴心实用的功能，使用自助服务功能请先登录或注册。</span>
											</font>
										</td>
									</tr>
								</table>

							</s:form>

						</div>
						<div id="tab5" align="center" style="display: none">
							<table border="0" cellpadding="0"
								style="border-collapse: collapse" width="540px" id="table16">
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr height=30 class="fontTr">
												<td width="15%">
													原寄地省份：
												</td>
												<td align="left" width="35%">
													<select id="pprovince3"
														onchange="changeCombo1('pcity3','pprovince3','cityvalue')"
														style="width: 150px"></select>
												</td>
												<td width="15%">
													寄达地省份：
												</td>
												<td align="left" width="35%">
													<select id="pprovince4"
														onchange="changeCombo1('pcity4','pprovince4','cityvalue')"
														style="width: 150px"></select>
												</td>
											</tr>
											<tr class="fontTr">
												<td>
													原寄地城市：
												</td>
												<td align="left">
													<select id="pcity3" name="pcity3"
														onchange="changeCity('pcity4','pcity3')"
														style="width: 150px"></select>
												</td>
												<td>
													寄达地城市：
												</td>
												<td align="left">
													<select id="pcity4" name="pcity4"
														onchange="changeCity('pcity3','pcity4')"
														style="width: 150px">
													</select>
												</td>
											</tr>
											<tr>
												
												<td align="center"colspan="4" align="center">
													<br />
													&nbsp;&nbsp;
													<input type="button" id="timeQueryId" value="&nbsp;&nbsp;&nbsp;&nbsp;时限查询"
														style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top"
														onClick="timeQuery(this,'<%=path%>')" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="button" onclick="javascript:back();"
														value="返回" name="B4"
														style="cursor: pointer; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
												</td>
											</tr>
											<tr class="lineTr">
												<td colspan="4">
													&nbsp;
												</td>
											</tr>
											<tr class="fontTr"
												onMouseOver="this.style.backgroundColor='#7db8f3';this.style.color ='#ffffff';"
												onMouseOut="this.style.backgroundColor='';this.style.color ='';">
												<td colspan="4" align="left">
													<br />
													查询结果:
													<span id="resultValue"></span>
													<span id="resultInfo"><font color="#CE7000"
														style="font-size: 9pt"> <br />
														说明：查询结果仅作为参考 </font> </span>
												</td>
											</tr>

										</table>
										<div id="messageDiv1" style="display: none"></div>
									</td>
								</tr>
							</table>

						</div>

						<div id="tab6" align="center" style="display: none;">
							<table border="0" cellpadding="0"
								style="border-collapse: collapse" width="540px" id="table16">
								<tr class="fontTr">
									<td width="50%" height="10" align="left" valign="bottom"
										nowrap="nowrap" class="txt-form-n-tt">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;寄出所在省份
									</td>
									<td width="50%" height="10" align="left" valign="bottom">
										<span class="txt-form-n-tt">计费重量（公斤）</span>
									</td>
								</tr>
								<tr class="fontTr">
									<td width="35%" height="24" align="left" nowrap="nowrap"
										class="txt-main">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<select name="addressfrom" id="addressfrom" isneed="Y"
											fieldname="寄件人省份" style="width: 150px">
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
												style="width: 150px" id="weight" type="text" maxlength="7"
												size="18" fieldname="快件重量" isneed="Y" checktype="number"
												class="input1" /> </span>
										<div id="messageDiv" style="display: none"></div>
									</td>
								</tr>
								<tr class="fontTr">
									<td width="41%" height="30" align="left" valign="bottom"
										nowrap="nowrap">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="txt-form-n-tt">寄达所在省份</span>
									</td>
									<td width="39%" align="left" valign="bottom">
										<span class="txt-form-n-tt">计算结果 (元)：</span>
									</td>
								</tr>
								<tr valign="bottom">
									<td width="41%" height="20" align="left" class="unnamed2">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<select name="addressto" id="addressto" isneed="Y"
											fieldname="收件人省份" style="width: 150px">
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
											style="width: 150px" name="gofee" class="input1" />
									</td>
								</tr>
								<tr class="fontTr">
									
									<td align="left" height="18px" colspan="2" style="text-align: center;">
										<br />
										
										<span class="unnamed2"> <input id="queryFeeId"
												onclick="makeRequest('<%=path%>','http://www.ems.com.cn/tools/postageresult.jsp',1)"
												type="button" value="计  算" name="clickfee"
												style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" onclick="javascript:back();" value="返回"
											name="B4"
											style="cursor: pointer; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
										</span> 
										
									</td>
									<td>
									</td>
								</tr>

								<tr class="fontTr">
									<td colspan="5" align="left">
										<br />
										<br />
										<span id="resultInfo"><font color="#CE7000"
											style="font-size: 9pt">说明：查询结果仅作为参考</font> </span>
									</td>
								</tr>
							</table>
						</div>
						<div id="tab8" align="center" style="display: none">
							<table border="0" cellpadding="0"
								style="border-collapse: collapse" width="540px" id="table16">
								<tr class="fontTr">
									<td width="50%" height="10" align="left" valign="bottom"
										nowrap="nowrap" class="txt-form-n-tt">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;寄达国家或地区
									</td>
									<td width="50%" height="10" align="left" valign="bottom">
										<span class="txt-form-n-tt">计费重量（公斤）</span>
									</td>
								</tr>
								<tr class="fontTr">
									<td width="41%" height="24" align="left" nowrap="nowrap"
										class="txt-main">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<select name="addresstoc" id="addresstoc" style="width: 150px">
											<option value="阿尔巴尼亚">
												阿尔巴尼亚
											</option>
											<option value="阿尔及利亚">
												阿尔及利亚
											</option>
											<option value="阿根廷">
												阿根廷
											</option>
											<option value="阿联酋">
												阿联酋
											</option>
											<option value="阿鲁巴">
												阿鲁巴
											</option>
											<option value="阿曼">
												阿曼
											</option>
											<option value="阿塞拜疆">
												阿塞拜疆
											</option>
											<option value="埃及">
												埃及
											</option>
											<option value="埃塞俄比亚">
												埃塞俄比亚
											</option>
											<option value="爱尔兰">
												爱尔兰
											</option>
											<option value="爱沙尼亚">
												爱沙尼亚
											</option>
											<option value="安道尔">
												安道尔
											</option>
											<option value="安哥拉">
												安哥拉
											</option>
											<option value="安圭拉">
												安圭拉
											</option>
											<option value="安提瓜和巴布达">
												安提瓜和巴布达
											</option>
											<option value="奥地利">
												奥地利
											</option>
											<option value="澳大利亚">
												澳大利亚
											</option>
											<option value="澳门 ">
												澳门
											</option>
											<option value="巴巴多斯">
												巴巴多斯
											</option>
											<option value="巴布亚新几内亚">
												巴布亚新几内亚
											</option>
											<option value="巴哈马">
												巴哈马
											</option>
											<option value="巴基斯坦">
												巴基斯坦
											</option>
											<option value="巴拉圭">
												巴拉圭
											</option>
											<option value="巴林">
												巴林
											</option>
											<option value="巴拿马">
												巴拿马
											</option>
											<option value="巴西">
												巴西
											</option>
											<option value="白俄罗斯">
												白俄罗斯
											</option>
											<option value="百慕大">
												百慕大
											</option>
											<option value="保加利亚">
												保加利亚
											</option>
											<option value="比利时">
												比利时
											</option>
											<option value="冰岛">
												冰岛
											</option>
											<option value="玻利维亚">
												玻利维亚
											</option>
											<option value="波多黎各">
												波多黎各
											</option>
											<option value="波兰">
												波兰
											</option>
											<option value="博茨瓦纳">
												博茨瓦纳
											</option>
											<option value="布基纳法索">
												布基纳法索
											</option>
											<option value="朝鲜">
												朝鲜
											</option>
											<option value="丹麦">
												丹麦
											</option>
											<option value="德国">
												德国
											</option>
											<option value="东萨摩亚">
												东萨摩亚
											</option>
											<option value="多哥">
												多哥
											</option>
											<option value="多米尼加共和国">
												多米尼加共和国
											</option>
											<option value="多米尼加联邦">
												多米尼加联邦
											</option>
											<option value="俄罗斯">
												俄罗斯
											</option>
											<option value="厄瓜多尔">
												厄瓜多尔
											</option>
											<option value="厄立特里亚">
												厄立特里亚
											</option>
											<option value="法国">
												法国
											</option>
											<option value="法属圭亚那">
												法属圭亚那
											</option>
											<option value="菲律宾 ">
												菲律宾
											</option>
											<option value="芬兰">
												芬兰
											</option>
											<option value="佛得角">
												佛得角
											</option>
											<option value="冈比亚">
												冈比亚
											</option>
											<option value="刚果（布）">
												刚果（布）
											</option>
											<option value="刚果（金）">
												刚果（金）
											</option>
											<option value="哥伦比亚">
												哥伦比亚
											</option>
											<option value="哥斯达黎加">
												哥斯达黎加
											</option>
											<option value="格林纳达">
												格林纳达
											</option>
											<option value="格陵兰岛">
												格陵兰岛
											</option>
											<option value="格鲁吉亚">
												格鲁吉亚
											</option>
											<option value="古巴">
												古巴
											</option>
											<option value="瓜德罗普">
												瓜德罗普
											</option>
											<option value="关岛">
												关岛
											</option>
											<option value="圭亚那 ">
												圭亚那
											</option>
											<option value="哈萨克斯坦">
												哈萨克斯坦
											</option>
											<option value="海地">
												海地
											</option>
											<option value="海峡群岛">
												海峡群岛
											</option>
											<option value="韩国">
												韩国
											</option>
											<option value="荷兰">
												荷兰
											</option>
											<option value="洪都拉斯">
												洪都拉斯
											</option>
											<option value="基里巴斯">
												基里巴斯
											</option>
											<option value="吉布提">
												吉布提
											</option>
											<option value="吉尔吉斯斯坦">
												吉尔吉斯斯坦
											</option>
											<option value="几内亚">
												几内亚
											</option>
											<option value="几内亚比绍">
												几内亚比绍
											</option>
											<option value="加拿大 ">
												加拿大
											</option>
											<option value="加那利群岛">
												加那利群岛
											</option>
											<option value="加纳">
												加纳
											</option>
											<option value="加蓬">
												加蓬
											</option>
											<option value="柬埔寨 ">
												柬埔寨
											</option>
											<option value="捷克">
												捷克
											</option>
											<option value="津巴布韦">
												津巴布韦
											</option>
											<option value="卡塔尔">
												卡塔尔
											</option>
											<option value="开曼群岛">
												开曼群岛
											</option>
											<option value="科摩罗群岛">
												科摩罗群岛
											</option>
											<option value="科特迪瓦">
												科特迪瓦
											</option>
											<option value="科威特">
												科威特
											</option>
											<option value="克罗地亚">
												克罗地亚
											</option>
											<option value="肯尼亚">
												肯尼亚
											</option>
											<option value="库克群岛">
												库克群岛
											</option>
											<option value="库腊索岛（荷属）">
												库腊索岛（荷属）
											</option>
											<option value="拉脱维亚 ">
												拉脱维亚
											</option>
											<option value="莱索托">
												莱索托
											</option>
											<option value="老挝">
												老挝
											</option>
											<option value="黎巴嫩">
												黎巴嫩
											</option>
											<option value="利比里亚">
												利比里亚
											</option>
											<option value="利比亚">
												利比亚
											</option>
											<option value="立陶宛">
												立陶宛
											</option>
											<option value="留尼汪岛">
												留尼汪岛
											</option>
											<option value="卢森堡">
												卢森堡
											</option>
											<option value="卢旺达">
												卢旺达
											</option>
											<option value="罗马尼亚">
												罗马尼亚
											</option>
											<option value="马达加斯加">
												马达加斯加
											</option>
											<option value="马耳他">
												马耳他
											</option>
											<option value="马尔代夫">
												马尔代夫
											</option>
											<option value="马拉维">
												马拉维
											</option>
											<option value="马来西亚">
												马来西亚
											</option>
											<option value="马里">
												马里
											</option>
											<option value="马其顿">
												马其顿
											</option>
											<option value="马绍尔群岛">
												马绍尔群岛
											</option>
											<option value="马提尼克">
												马提尼克
											</option>
											<option value="马约特">
												马约特
											</option>
											<option value="毛里求斯">
												毛里求斯
											</option>
											<option value="毛里塔尼亚">
												毛里塔尼亚
											</option>
											<option value="美国 ">
												美国
											</option>
											<option value="美属维尔京群岛">
												美属维尔京群岛
											</option>
											<option value="蒙古">
												蒙古
											</option>
											<option value="蒙特塞＋拉特">
												蒙特塞＋拉特
											</option>
											<option value="蒙特塞拉特">
												蒙特塞拉特
											</option>
											<option value="孟加拉">
												孟加拉
											</option>
											<option value="秘鲁">
												秘鲁
											</option>
											<option value="缅甸 ">
												缅甸
											</option>
											<option value="摩尔多瓦">
												摩尔多瓦
											</option>
											<option value="摩洛哥">
												摩洛哥
											</option>
											<option value="莫桑比克">
												莫桑比克
											</option>
											<option value="墨西哥">
												墨西哥
											</option>
											<option value="纳米比亚">
												纳米比亚
											</option>
											<option value="南非">
												南非
											</option>
											<option value="南斯拉夫">
												南斯拉夫
											</option>
											<option value="尼泊尔 ">
												尼泊尔
											</option>
											<option value="尼加拉瓜">
												尼加拉瓜
											</option>
											<option value="尼日尔">
												尼日尔
											</option>
											<option value="尼日利亚">
												尼日利亚
											</option>
											<option value="挪威">
												挪威
											</option>
											<option value="葡萄牙">
												葡萄牙
											</option>
											<option value="日本">
												日本
											</option>
											<option value="瑞典 ">
												瑞典
											</option>
											<option value="瑞士">
												瑞士
											</option>
											<option value="萨尔瓦多">
												萨尔瓦多
											</option>
											<option value="塞班">
												塞班
											</option>
											<option value="塞内加尔">
												塞内加尔
											</option>
											<option value="塞浦路斯">
												塞浦路斯
											</option>
											<option value="塞舌尔">
												塞舌尔
											</option>
											<option value="沙特阿拉伯">
												沙特阿拉伯
											</option>
											<option value="圣巴泰勒米">
												圣巴泰勒米
											</option>
											<option value="圣多美">
												圣多美
											</option>
											<option value="圣基茨">
												圣基茨
											</option>
											<option value="圣卢西亚">
												圣卢西亚
											</option>
											<option value="圣马丁">
												圣马丁
											</option>
											<option value="圣文森特">
												圣文森特
											</option>
											<option value="圣尤斯特歇斯">
												圣尤斯特歇斯
											</option>
											<option value="斯里兰卡">
												斯里兰卡
											</option>
											<option value="斯洛伐克">
												斯洛伐克
											</option>
											<option value="斯洛文尼亚">
												斯洛文尼亚
											</option>
											<option value="斯威士兰 ">
												斯威士兰
											</option>
											<option value="苏丹">
												苏丹
											</option>
											<option value="苏里南">
												苏里南
											</option>
											<option value="索马里">
												索马里
											</option>
											<option value="所罗门群岛">
												所罗门群岛
											</option>
											<option value="塔吉克斯坦">
												塔吉克斯坦
											</option>
											<option value="塔希提">
												塔希提
											</option>
											<option value="台湾 ">
												台湾
											</option>
											<option value="泰国">
												泰国
											</option>
											<option value="坦桑尼亚">
												坦桑尼亚
											</option>
											<option value="汤加">
												汤加
											</option>
											<option value="特克斯和凯科斯群岛">
												特克斯和凯科斯群岛
											</option>
											<option value="特立尼达和多巴哥">
												特立尼达和多巴哥
											</option>
											<option value="突尼斯">
												突尼斯
											</option>
											<option value="图瓦鲁">
												图瓦鲁
											</option>
											<option value="土耳其">
												土耳其
											</option>
											<option value="土库曼斯坦">
												土库曼斯坦
											</option>
											<option value="瓦努阿图 ">
												瓦努阿图
											</option>
											<option value="危地马拉">
												危地马拉
											</option>
											<option value="委内瑞拉 ">
												委内瑞拉
											</option>
											<option value="文莱">
												文莱
											</option>
											<option value="乌干达">
												乌干达
											</option>
											<option value="乌克兰">
												乌克兰
											</option>
											<option value="乌拉圭">
												乌拉圭
											</option>
											<option value="乌兹别克斯坦">
												乌兹别克斯坦
											</option>
											<option value="西班牙">
												西班牙
											</option>
											<option value="西萨摩亚">
												西萨摩亚
											</option>
											<option value="希腊">
												希腊
											</option>
											<option value="香港">
												香港
											</option>
											<option value="新加坡">
												新加坡
											</option>
											<option value="新喀里多尼亚 ">
												新喀里多尼亚
											</option>
											<option value="新西兰">
												新西兰
											</option>
											<option value="匈牙利">
												匈牙利
											</option>
											<option value="叙利亚">
												叙利亚
											</option>
											<option value="牙买加">
												牙买加
											</option>
											<option value="亚美尼亚">
												亚美尼亚
											</option>
											<option value="也门">
												也门
											</option>
											<option value="伊拉克">
												伊拉克
											</option>
											<option value="伊朗">
												伊朗
											</option>
											<option value="以色列">
												以色列
											</option>
											<option value="意大利">
												意大利
											</option>
											<option value="印度">
												印度
											</option>
											<option value="印度尼西亚">
												印度尼西亚
											</option>
											<option value="英国">
												英国
											</option>
											<option value="英属维尔京群岛">
												英属维尔京群岛
											</option>
											<option value="约旦">
												约旦
											</option>
											<option value="越南">
												越南
											</option>
											<option value="赞比亚">
												赞比亚
											</option>
											<option value="乍得">
												乍得
											</option>
											<option value="直布罗陀">
												直布罗陀
											</option>
											<option value="智利">
												智利
											</option>
											<option value="瑙鲁">
												瑙鲁
											</option>
											<option value="斐济">
												斐济
											</option>
										</select>
									</td>
									<td width="39%" height="24" align="left">
										<input name="weightid1" style="width: 150px" id="weightid1"
											type="text" maxlength="7" size="18" class="input1" />
									</td>
								</tr>
								<tr class="fontTr">
									<td width="41%" height="35" align="left" valign="bottom"
										nowrap="nowrap">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="txt-form-n-tt">内件性质</span>
									</td>
									<td width="39%" align="left" valign="bottom">
										<span class="txt-form-n-tt">计算结果 (元)：</span>
									</td>
								</tr>
								<tr valign="bottom">
									<td width="41%" height="20" align="left" class="unnamed2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="njxz" value="files"
											checked="checked" />
										文件
										<input type="radio" name="njxz" value="goods" />
										物品
									</td>
									<td width="39%" align="left" class="unnamed2">
										<input readonly size="18" value="0" id="goffee"
											style="width: 150px" name="goffee" class="input1" />
									</td>
								</tr>
								<tr>
									
									<td align="left" height="18px" colspan="2" style="text-align: center;">
										<br />
										<input id="queryFFeeId"
											style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top"
											onclick="makeRequest1('<%=path%>','http://www.ems.com.cn/tools/postageresult.jsp',2)"
											type="button" value="计  算" name="clickfee" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" onclick="javascript:back();" value="返回"
											name="B4"
											style="cursor: pointer; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
									</td>
									
								</tr>
								<tr class="fontTr">
									<td colspan="5" align="left">
										<br />
										<br />
										<span id="resultInfo"><font color="#CE7000"
											style="font-size: 9pt">说明：查询结果仅作为参考</font> </span>
									</td>
								</tr>
							</table>
						</div>
						<div id="tab4" align="center"
							style="display: none; vertical-align: top">
							<form action="/query/ftime.do" target="_blank" method="post">
								<table border="0" cellpadding="0"
									style="border-collapse: collapse" width="540px" id="table16">
									<tr class="fontTr">
										<td align="right" height="33" width="33%">
											交寄日期：
										</td>
										<td align="left">
											<%
													Calendar calendar = Calendar.getInstance();
													int year = calendar.get(Calendar.YEAR);
													int month = calendar.get(Calendar.MONTH) + 1;
													int day = calendar.get(Calendar.DATE);
													String date = year + "-" + month + "-" + day;
												%>
											<input type="text" id="I_AcceptDate"
												name="search.I_AcceptDate" value="<%=date%>"
												onclick="setday(this)" readonly size="12"
												style="width: 150px" />
										</td>
									</tr>
									<tr class="fontTr">
										<td align="right" height="33">
											交寄地邮政编码：
										</td>
										<td align="left">
											<input type="text" style="width: 150px"
												id="I_OriginPostalCode" name="search.I_OriginPostalCode"
												value="362000" />
										</td>
									</tr>
									<tr class="fontTr">
										<td align="right" height="33">
											寄达国家（地区）：
										</td>
										<td align="left">
											<select id="I_DestinationCountry"
												name="search.I_DestinationCountry" style="width: 150px"
												onchange="changeDestinationCountry()">
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
											<input type="hidden" id="I_DestinationCountryName"
												name="search.I_DestinationCountryName" />
											<input type="hidden" id="I_OriginCountry"
												name="search.I_OriginCountry" value="CN" />
											<input type="hidden" id="I_I_OriginCountryName"
												name="search.I_OriginCountryName" value="CHINA" />
										</td>
									</tr>
									<tr class="fontTr">
										<td align="right" height="33">
											寄达地邮政编码：
										</td>
										<td align="left">
											<input type="text" id="I_DestinationPostalCode"
												style="width: 150px" name="search.I_DestinationPostalCode"
												disabled="disabled" />
										</td>
									</tr>
									<tr class="fontTr">
										
										<td align="left" height="18px" colspan="2" style="text-align: center">
											<input type="button"
												style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top"
												value="查询" onclick="queryFTime()" />
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" onclick="javascript:back();" value="返回"
												name="B4"
												style="cursor: pointer; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
										</td>
									</tr>
								</table>
							</form>
						</div>
						<br />
						<div id="tab7" align="center" style="display: none">
							<table border="0" cellpadding="0"
								style="border-collapse: collapse" width="540px" id="table16">
								<tr align="center">
									<td width="33%" style="text-align: right">
										省份:
									</td>
									<td style="text-align: left">
										<select id="pprovince" name="search.pprovince"
											style="width: 150px"
											onchange="changeCombo('pcity','pprovince','cityvalue')"></select>
									</td>
								</tr>
								<tr>
									<td style="text-align: right">
										地市:
									</td>
									<td style="text-align: left">
										<select id="pcity" name="search.pcity" style="width: 150px"
											onchange="changeCombo('pcounty','pcity','countyvalue')"></select>
									</td>
								</tr>
								<tr>
									<td style="text-align: right"  nowrap="nowrap">
										县、区:
									</td>
									<td style="text-align: left">
										<select id="pcounty" name="search.pcounty"
											style="width: 150px"></select>
										<br />
									</td>
								</tr>
								<tr>
									
									<td height=30 align="left" colspan="2" style="text-align: center">
										<br />
										<input type="button" value="查询"
											style="width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top"
											onclick="query()" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" onclick="javascript:back();" value="返回"
											name="B4"
											style="cursor: pointer; width: 75px; height: 24px; font-size: 12px; color: #000; border-style: outset; border-width: 0px; background-image: url('images/button_search.gif'); background-position: left top" />
									</td>
								</tr>
								<tr class="lineTr"
									onMouseOver="this.style.backgroundColor='#7db8f3';this.style.color ='#ffffff';"
									onMouseOut="this.style.backgroundColor='';this.style.color ='';">
									<td align="left" ><br/>
										查询结果:
										<span id="address"></span>
										<br />
									</td>
									<td align="left"><br/>
										邮编:
										<span id="postalcode"></span>
										<br />
									</td>
								</tr>
							</table>
						</div>

					</td>
				</tr>
			</table>
			<span id="scrolltransparence"
				style="display: none; border: 1px solid #666; text-align: center; vertical-align: middle">
				<table id="imgload" border="0" height="100%" width="100%">
					<tr>
						<td align="center" valign="middle" height=100%>
							<img src="images/ajax-loader.gif" id="tableprodress"
								style="widht: 72px; height: 72px" />
						</td>
					</tr>
				</table> </span>
	
		<div>
			<div>
				<table border="0" cellpadding="0" align="center"
					style="border-collapse: collapse" width="530px"
					height="150">
					<tr>
						<td align="center" width=100% height=51>
							<span style="FONT-SIZE: 12px; COLOR: #000; FONT-FAMILY: Verdana">福建省邮政速递物流有限公司
								版权所有 闽ICP备10200383号 <br/><br />技术支持 泉州市冠发信息科技有限公司 </span>
						</td>
					</tr>
				</table>
			</div>
</td>
</tr>
</table>
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
		
		            hidetable();
    //通过ajax方式,验证是否登录成功2010-04-14
	function login(){
			 if(checkmsg()==true){
	    		    var url = '<%=path%>/frontlogin.do?search.pcode='+encodeURIComponent(encodeURIComponent($("#pcode").val()))+'&search.ppasswd='+$("#ppasswd").val()+'&search.pverifycode='+$("#pverifycode").val();
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url);  
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return;
						} else {
							root = oXMLDom.documentElement;
						}
						
						if (null != root){
							var rowSet = root.selectNodes("//result");
							var value=rowSet.item(0).selectSingleNode("value").text;
							if(0==value){//验证码错误
							   jAlert("验证码不正确,请重新输入!","提示");  
							}else if(1==value){//成功
							//window.open("index.htm",   "about:blank")
							   window.open("<%=path%>/adminFrame.jsp");
							   //window.location.target="_blank"
							   //window.location.href='<%=path%>/adminFrame.jsp';
							}else if(3==value){//用户名,密码异常
							   jAlert("用户名或密码不正确,请核对后输入!","提示"); 
							}
							else{//登录异常
							   jAlert("数据不正确,请与管理员联系!","提示");  
							}
						}	
					}catch(e){ 
						jAlert(e,"连接数据库不正确,请与管理员联系!","提示");
					}	
				}
		}
		
		function hidetable(){//初始化隐藏table13
			document.getElementById("table13").style.display = "none";
		}			
</script>
	</body>
</html>

