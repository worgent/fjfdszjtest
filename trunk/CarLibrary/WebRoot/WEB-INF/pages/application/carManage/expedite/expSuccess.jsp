<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 派车管理 - 派车成功返回时页面，同时展示短信发送情况
 	 * @author chenqf 
--%>

<head>
	<script language="javascript">	
		<%--刷新框架--%>
		<ww:iterator value="actionScripts">
			//parent.FunListFrm.window.location.reload();
			<ww:property />
		</ww:iterator>
	</script>
</head>
<body>
	<div align="center" style="color:red">
		::<b>提示信息</b><br>
		<ww:if test="actionMessages==null">
			操作成功!
		</ww:if>
		<ww:else>
			<ww:iterator value="actionMessages">
				<ww:property />
			</ww:iterator>
		</ww:else>
		<br>
		以下为短信发送列表
	</div>
	
	<iframe width="100%", height="400" id="sendSmsFrame" name="sendSmsFrame" src="/system/sms/sendSms.shtml?action=ExpSuccess&search.sourceOrderType=<ww:property value="search.sourceOrderType"/>&search.sourceOrderCode=<ww:property value="search.sourceOrderCode"/>" scrolling="no"></iframe>
</body>