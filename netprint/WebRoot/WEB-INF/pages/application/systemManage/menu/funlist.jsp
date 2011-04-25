<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
	<script language="javascript">
		/*
			操作方法，根据传入的类型进行不同的操作。
			edit 修改动作
			dele 删除动作
			insert 添加动作
		*/
		function fun_opera(vAction){
			var id="${param['menuInfo.superId']}";
			if(vAction == 'edit'){
				parent.OptFrm.location="/system/manage/menu.shtml?actionType=edit&menuInfo.menuId="+id;
			}else if(vAction == 'insert'){
				parent.OptFrm.location="/system/manage/menu.shtml?actionType=new&menuInfo.superId="+id;
			}else if(vAction == 'dele'){
				parent.OptFrm.location="/system/manage/menu.shtml?actionType=delete&menuInfo.superId="+id;
			}
		}
	</script>
	<table width="100%">
		<tr><td align="left">
		<%-- 如果是根菜单不能被修改或删除 --%>
		<c:if test="${param['menuInfo.superId']!='Root' && param['menuInfo.superId']!=null}">	
			<input type="button" value="修改" onclick="fun_opera('edit')">		
			<input type="button" value="删除" onclick="fun_opera('del')">
			
		</c:if>
		<input type="button" value="添加" onclick="fun_opera('insert')">
		</td></tr>
	</table>
	<%--数据显示表格--%>
	<tt:grid id="menu" value="menus" pagination="false" xls="false">
		<tt:row>
			<tt:col name="菜单标志*" property="menuId" width="100"/>
			<tt:col name="菜单名称*" property="menuName" width="100"/>
			<tt:col name="父菜单ID*" property="superId" width="100"/>
			<tt:col name="类型*" width="100">
				<ww:if test="menuId != null">
					<ww:if test="isMenu.equals(\"1\")">
						菜单
					</ww:if>
					<ww:elseif test="isMenu.equals(\"0\")">
						功能
					</ww:elseif>
				</ww:if>
			</tt:col>
			<tt:col name="图标地址*" property="imgSrc"/>
			<tt:col name="备注" property="title" width="100"/>
			<tt:col name="功能" width="100">
				<ww:if test="menuId != null">
					<A href="/system/manage/menu.shtml?actionType=edit&menuInfo.menuId=<ww:property value="menuId"/>" target="OptFrm">编辑</A> 
					<A href="/system/manage/delmenu.shtml?actionType=delete&menuInfo.menuId=<ww:property value="menuId"/>" target="OptFrm">删除</A>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>
	