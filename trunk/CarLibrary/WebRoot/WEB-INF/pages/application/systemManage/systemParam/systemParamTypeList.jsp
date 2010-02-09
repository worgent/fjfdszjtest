<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>

<tt:grid id="systemParamList" value="systemParamList" pagination="true" xls="false">
	<tt:row >
		<tt:col name="参数类型" property="PARA_TYPE_DESC" width="150"/>
		<tt:col name="参数名称" property="PARA_DESC" width="150"/>
		<tt:col name="明细" align="center" width="100">
			<ww:if test="null != PARA_TYPE">
				<a href="javascript:parent.addTab('<ww:property value="PARA_DESC"/>', 'systemParamDetail', '/system/systemparam/systemParam.shtml?actionType=detail&search.paraType=<ww:property value="PARA_TYPE"/>&search.paraName=<ww:property value="PARA_NAME"/>&paraDesc=<ww:property value="PARA_DESC"/>&paraTypeDesc=<ww:property value="PARA_TYPE_DESC"/>', 'NO')">
				查看
				</a>
			</ww:if>
		</tt:col>
	</tt:row>
</tt:grid>	