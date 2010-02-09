<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	设备管理
 -->
 
<body>
	<tt:grid  id="equipment" width="750" value="equipmentList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="" width="45">
				<ww:if test="null != fixing_id">
					<input type="checkbox" name="fixingId" value="<ww:property value="fixing_id"/>^<ww:property value="fixing_code"/>^<ww:property value="fixing_name"/>^<ww:property value="memo"/>"/>
				</ww:if>
				<ww:else>
					<a href="javascript:chk_all()">选择</a>
				</ww:else>
			</tt:col>
			<tt:col name="设备编号" width="130">
				<ww:if test="null != fixing_id">
					<a href="javascript:parent.addTab('查看设备信息', 'editEquipment', '/basearchives/equipment/equipment.shtml?actionType=view&search.fixingId=<ww:property value="fixing_id"/>','NO')"><ww:property value="fixing_code"/></a>
				</ww:if>
			</tt:col>
			<tt:col name="设备名称" property="fixing_name" width="130"/>			
			<tt:col name="备注" property="memo" width="490"/>
		</tt:row>
	</tt:grid> 

	<script>
		function chk_all(){
			var obj = $(fixingId);
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