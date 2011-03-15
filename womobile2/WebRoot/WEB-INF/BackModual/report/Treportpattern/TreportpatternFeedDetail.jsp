<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

-->
</style>
</head>
<table width="100%" border="0" cellpadding="4" cellspacing="0">
	<tr class="trClass">
	<td>
		<a href="javascript:chk_all(true);">全选</a>&nbsp;<a href="javascript:chk_all(false);">取消</a>&nbsp;<a href="javascript:allprint(1);">打印</a>&nbsp;<a href="javascript:allprint(0);">删除</a>
	</td>
		<td>
			<strong>主键</strong>
		</td>
		<td>
			<strong>模板</strong>
		</td>
		<td>
			<strong>报表名称</strong>
		</td>
		<td>
			<strong>是否启动</strong>
		</td>
		<td>
			<strong>备注</strong>
		</td>
		<td>
			<strong>创建人</strong>
		</td>
		<td>
			<strong>创建时间</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{pageList.objectList}" >
		<tr>
		    <td>
		    <input type='checkbox' name='searchpid' id='searchpid' value='<s:property value="#g.ID"/>'/>
		    </td>
			<td>
				<s:property value="#g.ID" />
			</td>
			<td>
				<s:property value="#g.PatternId" />
			</td>
			<td>
				<s:property value="#g.ReportName" />
			</td>
			<td>
				<s:property value="#g.State" />
			</td>
			<td>
				<s:property value="#g.Remark" />
			</td>
			<td>
				<s:property value="#g.Maker" />
			</td>
			<td>
				<s:property value="#g.MakeDateTime" />
			</td>
		</tr>
	</s:iterator>
	<tr class="bgColor3">
		<td colspan="20" align="center">
		    <center>
			分页:                                                     
			<qzgf:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
			</center>
		</td>
	</tr>
</table>
<script type="text/javascript">


        //全选复选框
    	function chk_all(flag){
			var obj = $(searchpid);
			if (obj != null){
				if (obj.length > 1){
					for (var i=0; i<obj.length; i++){
						obj[i].checked = flag;
					}
				}else{
					obj.chekced = flag;
				}
			}
		}
		

		//全选相关操作
        function allprint(myvar){
        			var obj = $(searchpid);
		            var value="";
		            var flag=0;
		            //组合打印单据的字符串
		            if (obj != null){
						if (obj.length > 1){
							for (var i=0; i<obj.length; i++){
								if(obj[i].checked == true)
								{
								  flag=1;
								  value=value+obj[i].value+',';
								}
							}
						}else{
						    if(obj.chekced == true)
						    {
						      flag=1;
						      value=obj;
						    }
						}
					}
					if(flag==1)
					{
					   if(value.charAt(value.length-1)==',')
					   {
					      value=value.substr(0,value.length-1);
					   }
					   if(myvar==0){//删除
							jConfirm('您确定删除选中的!', '确认框', function(r) {
								    		if (r){
												var url = '<%=path%>//report/treportpattern.do?action=alldel&search.pid='+value
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
														if(0<rowSet.item(0).selectSingleNode("value").text){
														    //jAlert("删除成功!",fuction(r){
														     window.location.href='<%=path%>//report/treportpattern.do';
														    //});
														   
														}
														else{
														    jAlert('删除失败!','提示');  
														}
													}	
												}catch(e){ 
													jAlert(e,"提示");
												}	
											}
										});
					   }else{//打印
					     window.location.href='<%=path%>//report/treportpattern.do?action=ocxprint&search.pid='+value;
					   }
					}else{
					  jAlert( '至少选择一项！','提示');
					}
        }		


		function fun_delete(pid){
			jConfirm('您确定删除该信息!', '确认框', function(r) {	
	    		if (r){
				   var url = '<%=path%>//report/treportpattern.do?action=delete&search.pid='+pid
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
						
							if(0<rowSet.item(0).selectSingleNode("value").text){
							    //jAlert("删除成功!",fuction(r){
							   window.location.href='<%=path%>//report/treportpattern.do';
							    //});
							}
							else{
							    jAlert('删除失败!');  
							}
						}	
					}catch(e){ 
						jAlert(e,"提示");
					}	
				}
			});
		}
		
 </script>
