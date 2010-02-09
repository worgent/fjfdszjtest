<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	员工信息
 -->
<body>
	<tt:grid  id="userInfo" width="750" value="userList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="" width="45">
				<ww:if test="null != staff_info_id">
					<input type="checkbox" name="staffInfoId" value="<ww:property value="staff_info_id"/>^<ww:property value="staff_name"/>"/>
				</ww:if>
				<ww:else>
					<a href="javascript:chk_all()">选择</a>
				</ww:else>
			</tt:col>
			<tt:col name="员工编号" width="130">
				<ww:if test="null != fixing_id">
					<a href="javascript:parent.addTab('员工信息', 'viewEmployeeInfo', '/basearchives/employeeInfo/employee.shtml?actionType=view&search.staffInfoId=<ww:property value="staff_info_id"/>','NO')"><ww:property value="staff_code"/></a>
				</ww:if>
			</tt:col>
			<tt:col name="员工姓名" property="staff_name" width="130"/>
			<tt:col name="员工类型" property="staffType" width="80"/>	
			<tt:col name="联系电话" property="link_phone" width="80"/>	
			<tt:col name="所属公司" property="belongCompany" width="100"/>
			<tt:col name="所属部门" property="belongDept" width="100"/>
			<tt:col name="所属地市" property="cityId" width="80"/>
		</tt:row>
	</tt:grid> 

	<script>
	    //全选
		function chk_all(){
			var obj = $(staffInfoId);
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