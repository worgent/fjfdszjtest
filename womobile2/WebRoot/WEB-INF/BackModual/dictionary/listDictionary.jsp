<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
	<head>
		<title>罗列数据字典</title>
		<script type="text/javascript">
			//复选框只能选择一个
			function check(cb){
         		var obj = document.getElementsByName("dictionaryCheckBox"); 
        		for (i=0; i<obj.length; i++){ 
	                if (obj[i]!=cb) 
	                	obj[i].checked = false; 
				}
			}
			
			function edit(){
				//1.判断是否有选中的项
				var obj = document.getElementsByName("dictionaryCheckBox"); 
				for (var i = 0; i < obj.length; i++) {
					if (obj[i].checked) {
						
						document.getElementById("tab2").style.display="none";
						var url = '/dictionary.do?action=getDictionaryName&dictId='+obj[i].value;
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
								var rowSet = root.selectNodes("//result");
								var name=rowSet.item(0).selectSingleNode("value").text;
								document.getElementById("oldVal1").value=name;
								document.getElementById("dictId").value=obj[i].value;
								document.getElementById("newVal").focus();
							}	
						}catch(e){ 
							
						}	
						
						document.getElementById("tab1").style.display="";
						return;
					}
			    }
			    alert("需先选择某一项再点击“修改”按钮！");
				//2.如果有选中的项的话，则判断是否是系统字典.系统字典不能删除
			}
			
			function add(){
				var obj = document.getElementsByName("dictionaryCheckBox"); 
        		for (i=0; i<obj.length; i++){ 
	                obj[i].checked = false; 
				}
				
				document.getElementById("tab1").style.display="none";
				document.getElementById("tab2").style.display="";
				document.getElementById("newVal1").focus();
				
			}
			
			function del(){
				//1.判断是否有选中的项
				var obj = document.getElementsByName("dictionaryCheckBox"); 
				for (var i = 0; i < obj.length; i++) {
					if (obj[i].checked) {
						
						document.getElementById("tab2").style.display="none";
						var url = '/dictionary.do?action=getDictionaryName&dictId='+obj[i].value;
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
								var rowSet = root.selectNodes("//result");
								var name=rowSet.item(0).selectSingleNode("value").text;
								if(confirm("确认删除“"+name+"”吗？")){
									window.location.href="<%=path%>/dictionary.do?action=del&search.dictId="+obj[i].value;
								}
								
							}	
						}catch(e){ 
							
						}	
						return;
					}
			    }
			    alert("需先选择某一项再点击“删除”按钮！");
				//2.如果有选中的项的话，则判断是否是系统字典.系统字典不能删除
			}
		</script>
	</head>
	<body>
	    <br/>
		<input type="button" name="edit" value="修改" class="btn"  onclick="edit()"/>
		&nbsp;&nbsp;&nbsp;&nbsp;   <input type="button" name="add" class="btn"  value="新增" onclick="add()"/>
		&nbsp;&nbsp;&nbsp;&nbsp;   <input type="button" name="del" class="btn"  value="删除" onclick="del()"/>
		<br/>
		<TABLE cellpadding="0" cellspacing="0" border="0">
		    <s:iterator id="dict" value="dictionaryList">
			<TR>
				<td>
					<s:if test="#dict.IsSys!=null&&#dict.IsSys==0">
						<input type="checkbox" name="dictionaryCheckBox" value='<s:property value="#dict.Id"/>' onclick="check(this)">
					</s:if>
					&nbsp;
				</td>
				<td>
					<s:if test="#dict.IsSys!=null&&#dict.IsSys==1">
						<a href="<%=path%>/dictionary.do?action=list&search.dictId=<s:property value="%{Id}"/>" target="user_admin_mainFrame"><font color="red"><s:property value="#dict.Name"/></font></a>(<s:property value="#dict.SysDictId"/>)
					</s:if>
					<s:else>
						<a href="<%=path%>/dictionary.do?action=list&search.dictId=<s:property value="%{Id}"/>" target="user_admin_mainFrame"><s:property value="#dict.Name"/></a>
					</s:else>
				</td>
			</TR>
			</s:iterator>
		</TABLE>
		<br/>
		<s:form action="dictionary" namespace="/" enctype="multipart/form-data">
		<s:hidden name="action" value="edit"></s:hidden>
		<table border="0" id="tab1" style="display: none">
			<tr>
				<td colspan="2" align="center">修改</td>
			</tr>
		    <tr>
		    	<td>旧名称：</td>
		    	<td>
		    		<input type="text" id="oldVal1" name="oldVal1" value="" disabled="disabled">
		    		<input type="hidden" id="dictId" name="search.dictId" value="" >
		    	</td>
		    </tr>
		    <tr>
		    	<td>新名称：</td><td><input type="text" id="newVal" name="search.newVal" value=""/></td>
		    </tr>
		    <tr>
		    	<td colspan="2" align="center">
		    		<input type="submit" class="btn"  value="修改"/>
		    	</td>
		    </tr>
		</table>
		</s:form>
		<s:form action="dictionary" namespace="/" enctype="multipart/form-data">
		<s:hidden name="action" value="add"></s:hidden>
		<table border="0" id="tab2" style="display:none">
			<tr>
				<td colspan="2" align="center">新增</td>
			</tr>
		    <tr>
		    	<td>新名称：</td>
		    	<td>
		    		<input type="text" id="newVal1" name="search.newVal" value=""/>
		    	</td>
		    </tr>
		    <tr>
		    	<td colspan="2" align="center">
		    		<input type="submit" class="btn"  value="添加"/>
		    	</td>
		    </tr>
		</table>
		</s:form>
	</body>

</HTML>