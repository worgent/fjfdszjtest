<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>

<head>
	<script language="javascript">
		function fun_delete(paraType, paraName, paraValue, paraValueMemo){
		    if (!confirm('您确定删除该信息!')){
				return;
			}else{
			   	var url = "/system/systemparam/systemParam.shtml?actionType=delete&search.paraType="+paraType+
			   													"&search.paraName="+paraName+
			   													"&search.oldParaValue="+paraValue+
			   													"&search.paraValueMemo="+paraValueMemo;
				try{
					var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
					oXMLDom.async = false ;
					oXMLDom.load(url); 
					var root;
					if (oXMLDom.parseError.errorCode != 0) {
						var myErr = oXMLDom.parseError;
						return;
					} else {
						root = oXMLDom.documentElement;
					}
					if (null != root){
						var rowSet = root.selectNodes("//delete");
						if (0 < rowSet.item(0).selectSingleNode("value").text){
							alert("删除数据字典信息，操作成功！");
							parent.document.ifrm_systemParamDetail.window.location.reload();
						}else{
							alert("删除数据字典信息，操作失败！");
						}
					}
				}catch(e){ 
					alert(e);
				}
			}
		}
		
		Ext.onReady(function(){
			<tt:authority value="TJSJZDXX">
		    new Ext.Button({
		        text: '添 加',
		        handler: function(){
		        	parent.addTab("添加数据字典信息","addSystemParamInfo","/system/systemparam/systemParam.shtml?actionType=new&search.paraType=${param['search.paraType']}&search.paraName=${param['search.paraName']}&paraDesc=${param.paraDesc}&paraTypeDesc=${param.paraTypeDesc}","NO");
		        }
		    }).render(document.all.addPanel);
		    </tt:authority>
		})
	</script>
</head>
<body>
	<div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	<div id="addPanel" style="margin:0px;width:100px;"></div>
	
	<tt:grid id="systemParamList" value="systemParamList" pagination="true" xls="false">
		<tt:row >
			<tt:col name="参数类型" property="PARA_TYPE_DESC" width="150"/>
			<tt:col name="参数名称" property="PARA_DESC" width="150"/>
			<tt:col name="参数值" property="PARA_VALUE" width="150"/>
			<tt:col name="参数值说明" property="PARA_VALUE_DESC" width="150"/>
			<tt:col name="参数值备注" property="PARA_VALUE_MEMO" width="150"/>
			<tt:col name="操作" align="center" width="100">
				<ww:if test="null != PARA_TYPE">
					<tt:authority value="XGSJZDXX">
						<a href="javascript:parent.addTab('<ww:property value="PARA_DESC"/>', 'editSystemParamDetail', '/system/systemparam/systemParam.shtml?actionType=edit&search.paraType=<ww:property value="PARA_TYPE"/>&search.paraName=<ww:property value="PARA_NAME"/>&search.paraValue=<ww:property value="PARA_VALUE"/>&search.paraValueMemo=<ww:property value="PARA_VALUE_MEMO"/>', 'NO')">修改</a>
					</tt:authority>
					<tt:authority value="SCSJZDXX">
						<a href="javascript:fun_delete('<ww:property value="PARA_TYPE"/>', '<ww:property value="PARA_NAME"/>', '<ww:property value="PARA_VALUE"/>', '<ww:property value="PARA_VALUE_MEMO"/>')">删除</a>
					</tt:authority>
				</ww:if>
			</tt:col>
		</tt:row>
	</tt:grid>	
</body>