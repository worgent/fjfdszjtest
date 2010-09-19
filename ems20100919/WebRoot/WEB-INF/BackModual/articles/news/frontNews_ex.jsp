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
		<!-- 
		<link href="<%=path%>/css/indexnew.CSS"  type="text/css" rel="stylesheet" />
		BACKGROUND: url(../images/recprod_09.gif);
		 -->
<style type="text/css">
.cs0 {
	FONT-SIZE: 18px;  WIDTH: 124px;  HEIGHT: 30px; COLOR: #ff7c00;cursor:hand;
}
.cs1 {
	FONT-SIZE: 18px;  WIDTH: 124px;  HEIGHT: 30px;  
}

table{ font-size:18px; line-height: 2em;font-family:"Times New Roman",Georgia,Serif; /* 行间距，相对数值 */} 

</style>
		<script type="text/javascript" src="<%=path%>/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="<%=path%>/js/tab_css.js"></script>

	</head>
<SCRIPT language=javascript>
function divTags(tagID)
{
    
    var Categorys;
    if(tagID==3)
    {
        document.getElementById("li3").className="cs0";
        document.getElementById("li4").className="cs1";
        document.getElementById("li5").className="cs1";
        document.getElementById("li6").className="cs1";

        div3.style.display="block";
        div4.style.display="none";
        div5.style.display="none";
        div6.style.display="none";
    }
    else if(tagID==4)
    {
        document.getElementById("li3").className="cs1";
        document.getElementById("li4").className="cs0";
        document.getElementById("li5").className="cs1";
        document.getElementById("li6").className="cs1";
    
        div3.style.display="none";
        div4.style.display="block";
        div5.style.display="none";
        div6.style.display="none";
        
    }else if(tagID==5)
    {
    	document.getElementById("li3").className="cs1";
        document.getElementById("li4").className="cs1";
        document.getElementById("li5").className="cs0";
        document.getElementById("li6").className="cs1";
        
        div3.style.display="none";
        div4.style.display="none";
        div5.style.display="block";
        div6.style.display="none";
        
    }else if(tagID==6)
    {
    	document.getElementById("li3").className="cs1";
        document.getElementById("li4").className="cs1";
        document.getElementById("li5").className="cs1";
        document.getElementById("li6").className="cs0";

        div3.style.display="none";
        div4.style.display="none";
        div5.style.display="none";
        div6.style.display="block";
    }

}
</SCRIPT>
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
	 <table border="4" cellpadding="0" style="border-collapse: collapse"  width="687"  id="table3" height="100%" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">
			<table border="2" cellpadding="0" style="border-collapse: collapse" width="100%" id="table6" height="272">
				<tr>
					<td width="884" valign="top" bgcolor="#E5E5E5">
					<div align="center">
						<table border="0" width="687" id="table50"   cellpadding="0" style="border-collapse: collapse">
								<td background="images/nei02.jpg">
								<table border="0" cellSpacing="0" cellPadding="0" width="100%" align="center" id="table52">
									<tr>
										<td height="12">
										<!-- flashad头 -->								
										<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="687" height="110" title="flashad">
									                                            <param name="movie" value="../../../../images/frontNew/flashad.swf">
									                                            <param name="quality" value="high">
									                                            <embed src="../../../../images/frontNew/flashad.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="775" height="110"></embed>
										</object>								
<div align="left">
<table>	
<tr><td>		
<!-- 专项营销内容存放处 -->
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中秋节送礼，何须东奔西走？
邮政速递礼仪一站式服务，让您省时省力又省心！
即日起，凡选购“闽月邮情”系列月饼即可享受EMS免费速递全国！
服务热线：11185
</td></tr>    
</table>
<p>
</p>
<table align="center">
<!-- 动态选项卡 -->	
<TBODY>
                    <TR>
                      <TD height=24 width=73  class=cs0 id=li3>
                        <DIV align=center><A id=herf3 style="TEXT-DECORATION: none"  onmouseover="divTags('3');">【亲友问候】</A></DIV></TD>
                      <TD width=4>&nbsp;</TD>
                      <TD height=24 width=73 class=cs1 id=li4>
                        <DIV align=center><A id=herf4 style="TEXT-DECORATION: none"  onmouseover="divTags('4');">【客户回馈】</A></DIV></TD>
                      <TD width=4>&nbsp;</TD>
                      <TD height=24 width=73  class=cs1 id=li5>
                        <DIV align=center><A id=herf5 style="TEXT-DECORATION: none"  onmouseover="divTags('5');">【员工福利】</A></DIV></TD>
                      <TD width=4>&nbsp;</TD>
                      <TD  height=24 width=73 class=cs1 id=li6>
                        <DIV align=center><A id=herf6  style="TEXT-DECORATION: none"  onmouseover="divTags('6');">【商务公关】</A></DIV></TD>
                      <TD width=4>&nbsp;</TD>
                    </TR>
</TBODY>
</table>
<DIV id=div3>
<table>
<tr align="center"><td>
<img src="../../../../images/frontNew/ad1.jpg" alt="">
</td></tr>
<tr><td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【亲友问候】
中秋观月明，良宵念挚心。在家，觉得外面的月圆；出门，才知家中的月亲。把思念融其中，将问候遥相寄。一盒月饼，一声问候，一份祝福！
</td></tr>
</table>
</div>
<DIV  id=div4 style="DISPLAY: none">
<table>
<tr align="center"><td>
<img src="../../../../images/frontNew/ad2.jpg" alt="" align="middle">
</td></tr>
<tr><td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【客户回馈】
企业的发展，有赖于客户的大力支持，一句谢谢不足以表达谢意；当可以寄情的中秋佳节来临，向尊敬的客户献上一份祝福，借助月饼表达对客户最真挚的感谢和问候。
</td></tr>
</table>
</div>
<DIV  id=div5 style="DISPLAY: none">
<table>
<tr align="center"><td>
<img src="../../../../images/frontNew/ad3.jpg" alt="">
</td></tr>
<tr><td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【员工福利】
一份中秋月饼，寄托一份心意！对于朝夕相处的同事，一句安慰可以鼓舞心田，一次鼓励可以激发向前！谨值中秋契机，借一份月饼表达“公司事业的蓬勃发展，全赖您的参与和努力！”让员工享有归属感，让员工享有受重视的鼓舞！
</td></tr>
</table>
</div>
<DIV  id=div6 style="DISPLAY: none">
<table>
<tr align="center"><td>
<img src="../../../../images/frontNew/ad4.jpg" alt="">
</td></tr>
<tr><td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【商务公关】
现代社会，拥有人际关系就意味着拥有了成功的资源！关系建立不在一时，人情积累更要选时择机！中秋佳节正是一个传情达意的好时机！让月饼为您带去一份人脉资源的机会。
</td></tr>
</table>
</div>
<h2 align="center">“闽月邮情”系列月饼</h2>
<table align="center">
<tr align="center">
	<td><img src="../../../../images/frontNew/1.jpg" 
	alt="内配：蛋黄纯白莲蓉月饼  150g*2个
         玫瑰豆沙月饼      100g*2个
         麦香月饼          100g*2个
         五仁月饼          100g*1个
         特爽哈密哈味月饼  75g*2个
         桃山绿茶莲蓉月饼  60g*2个
外包装规格：450*395*68"><br>产品品名：富贵添香 <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥268&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
    <td width="5"></td>
	<td><img src="../../../../images/frontNew/2.jpg" 
	alt="内配：蛋黄玫瑰豆沙月饼  100g*2个
         椰蓉味月饼        75g*2个
         奶油巧克力莲蓉月饼75g*2个
         绿茶味水晶月饼    60g*2个
         奶皇味水晶月饼    60g*2个
外包装规格：393×345×66"><br>产品品名：花语传情 <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥188&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
</tr>
<tr align="center">
	<td><img src="../../../../images/frontNew/3.jpg" 
	alt="内配：蛋黄玫瑰豆沙月饼  100g*2个
         玉米味月饼        60g*2个
         特爽哈密瓜味月饼  60g*2个
         绿茶味水晶月饼    60g*3个
外包装规格：349×350×62"><br>产品品名：荷韵呈祥 <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥138&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
    <td width="5"></td>
	<td><img src="../../../../images/frontNew/4.jpg" 
	alt="内配：白莲蓉月饼     75g*2个
         椰蓉月饼          75g*2个
         豆沙月饼          75g*2个
         特爽香橙味月饼    60g*2个
外包装规格：325×325×58"><br>产品品名：浓情祝福 <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥98&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
</tr>
<tr align="center">
	<td><img src="../../../../images/frontNew/5.jpg" 
	alt="内配：白莲蓉月饼  50g*3个
         特爽香橙味月饼    50g*3个
         玫瑰豆沙月饼      50g*3个
外包装规格：210×210×65"><br>产品品名：秋韵浓情 <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥58&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
    <td width="5"></td>
	<td><img src="../../../../images/frontNew/6.jpg" 
	alt="内配：白莲蓉月饼  50g*3个
         特爽香橙味月饼    50g*3个
         玫瑰豆沙月饼      50g*3个
外包装规格：210×210×65"><br>产品品名：金秋珍邮 <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥58&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
</tr>
<tr align="center">
	<td><img src="../../../../images/frontNew/7.jpg" 
	alt="内配：蛋黄白莲蓉月饼  125g*1个
         蛋黄玫瑰豆沙月饼  125g*1个
         椰蓉味月饼        125g*1个
         红枣味月饼        125g*1个         
外包装规格：210×210×65"><br>产品品名：雅莲&nbsp;&nbsp;&nbsp;&nbsp; <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥58&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
    <td width="50"></td>
	<td><img src="../../../../images/frontNew/8.jpg" 
	alt="内配：椰蓉味月饼  40g*2个
         特爽香橙味月饼    40g*2个
         特爽菠萝味月饼    40g*2个
         特爽哈密瓜味月饼  40g*2个 
外包装规格：310×220×60"><br>产品品名：团圆月&nbsp;&nbsp; <br>销&nbsp;&nbsp;售&nbsp;&nbsp;价：￥38&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <br>&nbsp;
    </td>
</tr>	
</table>	

										<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>购买小贴士</b>：<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*您可以直接在线下单或拨打客服热线11185，我们的客户经理将在接收到您的订单后24小时内与您联系！请您务必留下您的联络方式和手机号码，方便我们与您保持联系！ <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*有时电脑显示商品的颜色与商品实际颜色会有出入，如发生实物与网页介绍稍有不符，请以实物为准。 <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*收到您的订单后，我们以就近的配货中心发货，尽快将您的礼品送达。配送周期一般在1-7天，如有特别需求请与客服咨询！ <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*除产品质量问题外，恕不支持货品退换。请您谅解！ <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*产品配送范围为中国大陆地区！ </p>	
										</div>
										</td>
									</tr>
								</table>
								<p>
								</td>
							<tr>
								<td>
								<img border="0" src="images/nei03.jpg" width="687" height="4"></td>
							</tr>
						</table>
						

					</div>
					</td>
			</tr>
			</table>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
