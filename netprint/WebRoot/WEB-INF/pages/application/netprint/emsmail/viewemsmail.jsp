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
	left: 494px;
	top: 91px;
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
	height:22px;
	z-index:41;
	left: 96px;
	top: 3px;
}
#Layer37 {
	position:absolute;
	width:416px;
	height:24px;
	z-index:42;
	left: 385px;
	top: 1px;
}
#Layer39 {
	position:absolute;
	width:143px;
	height:23px;
	z-index:42;
	left: 223px;
	top: 2px;
}

#Layer43 {
	position:absolute;
	width:332px;
	height:25px;
	z-index:41;
	left: 78px;
	top: 261px;
}


#Layer40 {
	position:absolute;
	width:109px;
	height:28px;
	z-index:47;
	left: 98px;
	top: 49px;
}
#Layer41 {
	position:absolute;
	width:117px;
	height:19px;
	z-index:101;
	left: 77px;
	top: 361px;
}
#Layer42 {
	position:absolute;
	width:168px;
	height:25px;
	z-index:102;
	left: 212px;
	top: 361px;
}
#Layer44 {
	position:absolute;
	width:95px;
	height:24px;
	z-index:103;
	left: 117px;
	top: 91px;
}
#Layer45 {
	position:absolute;
	width:45px;
	height:24px;
	z-index:104;
	left: 251px;
	top: 91px;
}
#Layer46 {
	position:absolute;
	width:34px;
	height:27px;
	z-index:105;
	left: 301px;
	top: 90px;
}
#Layer47 {
	position:absolute;
	width:30px;
	height:27px;
	z-index:106;
	left: 340px;
	top: 90px;
}
#Layer48 {
	position:absolute;
	width:35px;
	height:25px;
	z-index:107;
	left: 374px;
	top: 91px;
}

-->
</style>

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
	  
  <div id="Layer43"><!--邮件类型-->
		<input type="radio" name="search.psendmailtype" id="psendmailtype0" value="0" <ww:if test="\"0\".equals(search.sendmailtype)">checked</ww:if>  /><label for="psendmailtype0">信函</label>
		<input type="radio" name="search.psendmailtype" id="psendmailtype1" value="1" <ww:if test="\"1\".equals(search.sendmailtype)">checked</ww:if> /><label for="psendmailtype1">文件资料</label>
		<input type="radio" name="search.psendmailtype" id="psendmailtype2" value="2" <ww:if test="\"2\".equals(search.sendmailtype)">checked</ww:if> /><label for="psendmailtype2">物品</label>
  </div>
  
	  <div id="Layer12"><!--内件品名-->
	  <ww:property value="search.sendgoodsname"/>
	  </div>
	  <div id="Layer13"><!--物品数据-->
	  <ww:property value="search.sendcount"/>
	  </div>
	  <p>&nbsp;</p>
	  <div id="Layer40"></div>
	  <p>&nbsp;</p>
	  <p>&nbsp;</p>
	  <div id="Layer44"> <ww:property value="search.sendoffice"/></div>
	  <div id="Layer45"> <ww:property value="search.senddateyear"/></div>
	  <div id="Layer46"> <ww:property value="search.senddatemonth"/></div>
	  <div id="Layer47"> <ww:property value="search.senddateday"/></div>
	  <div id="Layer48"> <ww:property value="search.senddatehour"/></div>
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
	  <div id="Layer41">
	  <input type="checkbox" name="search.psendinsure" id="psendinsure" value="1" <ww:if test="\"1\".equals(search.sendinsure)">checked</ww:if>  />  
	  
	  </div>
	  <div id="Layer42">
	  
	    <ww:property value="search.sendinsuremoney"/>
	  </div>
	  <p>&nbsp;</p>
	  <div id="Layer35"><!--备注-->
	  <ww:property value="search.remark"/>
	  </div>
	  <div id="Layer38"><!--邮件号-->
	  <ww:property value="search.mailno"/>
	  </div>

	  <div id="Layer36"><!--计费区-->
	 	<ww:property value="search.mail_feearea"/>
	  </div>
      <div id="Layer37"><!--省际封发局-->
	    <ww:property value="search.mail_sendoffice"/>
	  </div>
	   <div id="Layer39"><!--E邮宝标识-->
	      <ww:property value="search.email_fee"/>
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
	  <script language="javascript">
	   new Ext.Button({
        text: '打印',
        handler: function(){
        	parent.addTab('打印邮件信息', 'printEmsMail', '/ocxprint.shtml?actionType=ocxprint&search.printtype=1&search.pagenum=1&search.pid=<ww:property value="search.id"/>','NO')
        }
    }).render(document.all.Layer40);
	  </script>