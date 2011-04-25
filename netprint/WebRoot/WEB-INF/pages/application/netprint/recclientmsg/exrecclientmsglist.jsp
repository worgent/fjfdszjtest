<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%--
	 * 收件人信息管理 
 	 * @author zhengmh 
--%>
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>

<body>
	<input type='hidden' name='search.pbill_sort' value='0'/>
	<tt:grid id="clientmsg" value="clientmsgList" pagination="true" xls="false" >
		<tt:row >
			<tt:col name="选择" width="80">
				<ww:if test="null != id">
					<input type="radio" name="clientMsg" onclick="setValue('<ww:property value="id"/>','<ww:property value="name"/>', '<ww:property value="tel"/>', '<ww:property value="unit"/>','<ww:property value="address"/>', '<ww:property value="code"/>', '<ww:property value="post"/>')"/>
				</ww:if>
			</tt:col>
			<tt:col name="收件人城市" property="code" width="80"/>		
			<tt:col name="收件人姓名" property="name" width="80"/>			
			<tt:col name="收件人单位" property="unit" width="80"/>
			<tt:col name="收件人详细地址" property="address" width="80"/>	
			<tt:col name="收件人联系电话" property="tel" width="80"/>		
			<tt:col name="收件人邮编" property="post" width="80"/>		
			<tt:col name="开单人" property="create_manname" width="100" visible="true"/>
			<tt:col name="开单时间" property="create_date" width="75"/>
		</tt:row>
	</tt:grid> 
	<input type="hidden" name="frmid" value="">
	<input type="hidden" name="frmname" value="">
	<input type="hidden" name="frmtel" value="">
	<input type="hidden" name="frmunit" value="">
	<input type="hidden" name="frmaddress" value="">
	<input type="hidden" name="frmcode" value="">
	<input type="hidden" name="frmpost" value="">
	<script>
		function setValue(frmid,frmname, frmtel, frmunit,frmaddress,frmcode,frmpost){
			document.all.frmid.value = frmid;
			document.all.frmname.value = frmname;
			document.all.frmtel.value = frmtel;
			document.all.frmunit.value = frmunit;
			document.all.frmaddress.value = frmaddress;
			document.all.frmcode.value = frmcode;
			document.all.frmpost.value = frmpost;			
		}
	</script>
</body>