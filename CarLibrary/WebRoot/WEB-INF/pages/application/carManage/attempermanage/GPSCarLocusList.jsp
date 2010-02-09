<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	调度管理
 -->
<ww:action name="'select'" id="select"></ww:action>

<head>
	
</head>

<body>
	<tt:grid id="attemperList" value="attemperList" pagination="false" xls="false">
		<tt:row>
			<tt:col name="选择" width="30">
				<ww:if test="null != CAR_MARK">
					<input type="radio" name="carMark" onclick="setValue('<ww:property value="CAR_MARK"/>', '<ww:property value="MOTORID"/>', '<ww:property value="LAST_LOCATION"/>')"/>
				</ww:if>
			</tt:col>
			<tt:col name="车牌号" property="CAR_MARK" width="70"/>
			<tt:col name="司机号" property="MOTORID" width="70"/>
			<tt:col name="车辆最后位置" property="LAST_LOCATION" width="100"/>
			<tt:col name="最后定位经度" property="LAST_LON" width="100"/>
			<tt:col name="最后定位纬度" property="LAST_LAT" width="100"/>
			<tt:col name="最后定位速度" property="LAST_SPEED" width="100"/>
			<tt:col name="最后定位方向" property="LAST_ANGLE" width="100"/>
			<tt:col name="最后定位时间" property="LAST_TIME" width="100"/>
			<tt:col name="最后定位标志" property="LAST_FLAG" width="100"/>
		</tt:row>
	</tt:grid>
	<input type="hidden" name="carNoCode" value="">
	<input type="hidden" name="motorman" value="">
	<input type="hidden" name="attemperLocus" value="">
	
	<script>
		function setValue(carNoCode, motorman, attemperLocus){
			document.all.carNoCode.value = carNoCode;
			document.all.motorman.value = motorman;
			document.all.attemperLocus.value = attemperLocus;
		}
	</script>
</body>