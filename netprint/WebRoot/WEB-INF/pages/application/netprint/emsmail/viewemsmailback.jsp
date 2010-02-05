<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * EMS邮件信息管理增加与编辑
 	 * @author zhengmh 
--%><style type="text/css">
<!--
body {
	background-image: url(../../../../../images/mailformat/ems.jpg);
	background-repeat: no-repeat;
}
#Layer1 {
	position:absolute;
	width:84px;
	height:21px;
	z-index:1;
	left: 133px;
	top: 93px;
}
#Layer2 {
	position:absolute;
	width:26px;
	height:22px;
	z-index:1;
	left: 268px;
	top: 92px;
}
#Layer3 {
	position:absolute;
	width:20px;
	height:22px;
	z-index:2;
	left: 304px;
	top: 92px;
}
#Layer4 {
	position:absolute;
	width:21px;
	height:21px;
	z-index:1;
	left: 333px;
	top: 93px;
}
#Layer5 {
	position:absolute;
	width:21px;
	height:21px;
	z-index:2;
	left: 366px;
	top: 93px;
}
#Layer6 {
	position:absolute;
	width:84px;
	height:25px;
	z-index:2;
	left: 133px;
	top: 119px;
}
#Layer7 {
	position:absolute;
	width:109px;
	height:23px;
	z-index:3;
	left: 297px;
	top: 120px;
}
#Layer8 {
	position:absolute;
	width:217px;
	height:24px;
	z-index:4;
	left: 187px;
	top: 147px;
}
#Layer9 {
	position:absolute;
	width:330px;
	height:44px;
	z-index:5;
	left: 79px;
	top: 194px;
}
#Layer10 {
	position:absolute;
	width:82px;
	height:16px;
	z-index:6;
	left: 132px;
	top: 241px;
}
#Layer11 {
	position:absolute;
	width:93px;
	height:17px;
	z-index:7;
	left: 316px;
	top: 241px;
}
#Layer12 {
	position:absolute;
	width:205px;
	height:47px;
	z-index:8;
	left: 77px;
	top: 309px;
}
#Layer13 {
	position:absolute;
	width:129px;
	height:50px;
	z-index:9;
	left: 286px;
	top: 307px;
}
#Layer14 {
	position:absolute;
	width:250px;
	height:27px;
	z-index:10;
	left: 166px;
	top: 385px;
}
#Layer15 {
	position:absolute;
	width:103px;
	height:22px;
	z-index:11;
	left: 489px;
	top: 120px;
}
#Layer16 {
	position:absolute;
	width:122px;
	height:21px;
	z-index:12;
	left: 658px;
	top: 120px;
}
#Layer17 {
	position:absolute;
	width:243px;
	height:25px;
	z-index:13;
	left: 538px;
	top: 145px;
}
#Layer18 {
	position:absolute;
	width:350px;
	height:44px;
	z-index:14;
	left: 433px;
	top: 194px;
}
#Layer19 {
	position:absolute;
	width:81px;
	height:16px;
	z-index:15;
	left: 480px;
	top: 241px;
}
#Layer20 {
	position:absolute;
	width:108px;
	height:17px;
	z-index:16;
	left: 672px;
	top: 241px;
}
#Layer21 {
	position:absolute;
	width:73px;
	height:18px;
	z-index:17;
	left: 454px;
	top: 263px;
}
#Layer22 {
	position:absolute;
	width:22px;
	height:20px;
	z-index:18;
	left: 620px;
	top: 263px;
}
#Layer23 {
	position:absolute;
	width:29px;
	height:19px;
	z-index:19;
	left: 662px;
	top: 264px;
}
#Layer24 {
	position:absolute;
	width:27px;
	height:18px;
	z-index:20;
	left: 704px;
	top: 264px;
}
#Layer25 {
	position:absolute;
	width:26px;
	height:17px;
	z-index:21;
	left: 744px;
	top: 264px;
}
#Layer26 {
	position:absolute;
	width:109px;
	height:15px;
	z-index:22;
	left: 453px;
	top: 287px;
}
#Layer27 {
	position:absolute;
	width:152px;
	height:15px;
	z-index:23;
	left: 632px;
	top: 289px;
}
#Layer28 {
	position:absolute;
	width:111px;
	height:20px;
	z-index:24;
	left: 492px;
	top: 307px;
}
#Layer29 {
	position:absolute;
	width:96px;
	height:20px;
	z-index:25;
	left: 689px;
	top: 308px;
}
#Layer30 {
	position:absolute;
	width:240px;
	height:23px;
	z-index:26;
	left: 545px;
	top: 334px;
}
#Layer31 {
	position:absolute;
	width:39px;
	height:17px;
	z-index:27;
	left: 474px;
	top: 362px;
}
#Layer32 {
	position:absolute;
	width:30px;
	height:17px;
	z-index:28;
	left: 535px;
	top: 363px;
}
#Layer33 {
	position:absolute;
	width:29px;
	height:16px;
	z-index:29;
	left: 586px;
	top: 363px;
}
#Layer34 {
	position:absolute;
	width:32px;
	height:17px;
	z-index:30;
	left: 630px;
	top: 362px;
}
#Layer35 {
	position:absolute;
	width:294px;
	height:27px;
	z-index:31;
	left: 483px;
	top: 384px;
}
#Layer38 {
	position:absolute;
	width:251px;
	height:19px;
	z-index:34;
	left: 493px;
	top: 4px;
}
#sendprovinceDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:35;
	left: 144px;
	top: 174px;
}
#sendcityDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:36;
	left: 236px;
	top: 175px;
}
#sendcountyDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:37;
	left: 326px;
	top: 175px;
}
#recprovinceDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:38;
	left: 507px;
	top: 174px;
}
#reccityDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:39;
	left: 605px;
	top: 175px;
}
#reccountyDiv {
	position:absolute;
	width:80px;
	height:17px;
	z-index:40;
	left: 702px;
	top: 174px;
}
#Layer36 {
	position:absolute;
	width:120px;
	height:20px;
	z-index:41;
	left: 96px;
	top: 5px;
}
-->
</style>
	  <div id="Layer1"><!--发件人收寄局-->
	  <ww:property value="search.sendoffice"/>
	  </div>
	  <div id="Layer2"><!--发件人收寄年-->
	  <ww:property value="search.senddateyear"/>
	  </div>
	  <div id="Layer3"><!--发件人收寄月-->
	  <ww:property value="search.senddatemonth"/>
	  </div>
	  <div id="Layer4"><!--发件人收寄日-->
	  <ww:property value="search.senddateday"/>
	  </div>
	  <div id="Layer5"><!--发件人收寄时-->
	  <ww:property value="search.senddatehour"/>
	  </div>
	  <div id="Layer6"><!--寄件人姓名-->
	  <ww:property value="search.sendname"/>
	  </div>
	  <div id="Layer7"><!--发件人电话-->
	  <ww:property value="search.sendtel"/>
	  </div>
	  <div id="Layer8"><!--发件人单位名称-->
	  <ww:property value="search.sendunit"/>
	  </div>
	  <div id="Layer9"><!--发件人地址-->
	  <ww:property value="search.sendaddress"/>
	  </div>
	  <div id="Layer10"><!--发件人代码-->
	  <ww:property value="search.sendcode"/>
      </div>
	  <div id="Layer11"><!--发件人邮政编码-->
	  <ww:property value="search.sendpost"/>
	  </div>
	  <div id="Layer12"><!--内件品名-->
	  <ww:property value="search.sendgoodsname"/>
	  </div>
	  <div id="Layer13"><!--物品数据-->
	  <ww:property value="search.sendcount"/>
	  </div>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <div id="Layer14"><!--交寄人签名-->
	  <ww:property value="search.sendsign"/>
	  </div>
	  <div id="Layer15"><!--收件人姓名-->
	  <ww:property value="search.recname"/>
	  </div>
	  <div id="Layer16"><!--收件人电话-->
	  <ww:property value="search.rectel"/>
	  </div>
	  <div id="Layer17"><!--收件人单位名称-->
	  <ww:property value="search.recunit"/>
	  </div>
	  <div id="Layer18"><!--收件人地址-->
	  <ww:property value="search.recaddress"/>
	  </div>
	  <div id="Layer19"><!--收件人代码-->
	  <ww:property value="search.reccode"/>
	  </div>
	  <div id="Layer20"><!--收件人邮政编码-->
	  <ww:property value="search.recpost"/>
	  </div>
	  <div id="Layer21"><!--重量-->
	  <ww:property value="search.weight"/>
	  </div>
	  <div id="Layer22"><!--长-->
	  <ww:property value="search.length"/>
	  </div>
	  <div id="Layer23"><!--宽-->
	  <ww:property value="search.width"/>
	  </div>
	  <div id="Layer24"><!--高-->
	  <ww:property value="search.height"/>
	  </div>
	  <div id="Layer25"><!--体积-->
	  <ww:property value="search.solidity"/>
	  </div>
	  <div id="Layer26"><!--资费-->
	  <ww:property value="search.charge"/>
	  </div>
	  <div id="Layer27"><!--保价费-->
	  <ww:property value="search.recinsuremoney"/>
	  </div>
	  <div id="Layer28"><!--费用总计-->
	  <ww:property value="search.totalcharge"/>
	  </div>
	  <div id="Layer29"><!--收寄人签名-->
	  <ww:property value="search.acceptsign"/>
	  </div>
	  <div id="Layer30"><!--收件人签名-->
	  <ww:property value="search.recsign"/>
	  </div>
	  <div id="Layer31"><!--收件人收寄年-->
	  <ww:property value="search.recdateyear"/>
	  </div>
	  <div id="Layer32"><!--收件人收寄月-->
	  <ww:property value="search.recdatemonth"/>
	  </div>
	  <div id="Layer33"><!--收件人收寄天-->
	  <ww:property value="search.recdateday"/>
	  </div>
	  <div id="Layer34"><!--收件人收寄时-->
	  <ww:property value="search.recdatehour"/>
	  </div>
	  <p>&nbsp;</p>
	  <div id="Layer35"><!--备注-->
	  <ww:property value="search.remark"/>
	  </div>
	  <div id="Layer38"><!--邮件号-->
	  <ww:property value="search.mailno"/>
	  </div>

	 <div id="sendprovinceDiv"><!--发件人省份-->
	 <ww:property value="search.sendprovincename"/>
	 </div>
	 <div id="sendcityDiv"><!--发件人地市-->
	 <ww:property value="search.sendcityname"/>
	 </div>
	<div id="sendcountyDiv"><!--发件人区县-->
	<ww:property value="search.sendcountyname"/>
	</div>
	<div id="recprovinceDiv"><!--收件人省份-->
		<ww:property value="search.recprovincename"/>
	</div>
	<div id="reccityDiv"><!--收件人地市-->
	<ww:property value="search.reccityname"/>
	</div>
	<div id="reccountyDiv"><!--收件人地市-->
	<ww:property value="search.reccountyname"/>
	</div>
     <div id="Layer36"><!--计费区-->
	 <ww:property value="search.mail_feearea"/>
     </div>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>