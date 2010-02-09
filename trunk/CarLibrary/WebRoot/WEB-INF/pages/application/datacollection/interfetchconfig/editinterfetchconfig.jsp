<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

<form methd='POST' name='form1' action='/datacollection/interFatchConfig.shtml' >
	<table class='simple' style='width:90%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>GPS接口数据采集规则配置</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">接口采集规则配置名称:</td>
				<td width="33%">
					<tt:TextField name="search.interFetchConfigName" value="search.inter_fetch_config_name" width="200" cssClass="check" verify='string' required='true' shade='true' requiredColor='#ffffff'/>
				</td>
				<td align='right' width="17%">采集方式</td>
				<td width="33%">
					<tt:ComboBox name="search.fetchMode" value="search.fetch_mode" sql="select para_value id, para_value_desc text from td_system_para where para_type = 'INTER_FETCH_CONFIG' and para_name = 'FETCH_MODE'" />
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table class='simple' style='width:90%' align='center'>
		<thead>
			<tr>
				<th>采集接口数据Select SQL语句</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='center'>
					<textarea name="search.interSelectSql" rows="10" cols="100"><ww:property value='search.inter_select_sql'/></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table class='simple' style='width:90%' align='center'>
		<thead>
			<tr>
				<th>本地数据表Insert SQL语句</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='center'>
					<textarea name="search.localInsertSql" rows="10" cols="100"><ww:property value='search.local_insert_sql'/></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table class='simple' style='width:90%' align='center'>
		<thead>
			<tr>
				<th>本地数据表Update SQL语句</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='center'>
					<textarea name="search.localUpdateSql" rows="10" cols="100"><ww:property value='search.local_update_sql'/></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<input type='hidden' name='search.interFetchConfigId' value='<ww:property value="search.inter_fetch_config_id"/>'/>
	<input type='hidden' name='actionType' value='<ww:property value="action"/>'/>
	<table style='width:80%' align='center'>
		<tr>
			<td id="buttonTD1" align='right' width='48%'><input type="submit" value="保存"/></td>
			<td>&nbsp;</td>
			<td id="buttonTD2" align='left' width='48%'></td>
		</tr>
	</table>
</form>
