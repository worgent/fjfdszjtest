<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	操作员信息
 -->
<body>
	<tt:grid  id="userInfo" width="750" value="userList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="" width="45">
				<ww:if test="null != staffId">
					<input type="checkbox" name="staffIds" value="<ww:property value="staffId"/>^<ww:property value="staffName"/>"/>
				</ww:if>
				<ww:else>
					<a href="javascript:chk_all()">选择</a>
				</ww:else>
			</tt:col>
			<tt:col name="工号" property="staffNo" width="130"/>
			<tt:col name="用户名" property="staffName" width="100"/>
			<tt:col name="联系电话" property="phone" width="100"/>
			<tt:col name="所属地市" property="cityName" width="100"/>
		</tt:row>
	</tt:grid> 

	<script>
	    //全选
		function chk_all(){
			var obj = $(staffIds);
			if (obj != null){
				if (obj.length > 1){
					for (var i=0; i<obj.length; i++){
						obj[i].checked = true;
					}
				}else{
					obj.chekced = true;
				}
			}
		}
	</script>
</body>