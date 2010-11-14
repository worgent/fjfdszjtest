<#include "/custom.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<#assign actionExtension = "do"> 
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
		<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
	</head>
	<body>
<center>
<s:form method='POST' name='form1' action="${actionBasePath}${classNameLower}.${actionExtension}"   id="form1" enctype="multipart/form-data">
   <table width="95%" height="190" cellspacing=0 cellpadding="0" border="0" class="tableex">
   <tr class="trex">
   <td class="tdex"> 
       <strong>${table.tableAlias}</strong>
   </td>
   </tr>
   <tr class="trex">
    <td class="tdex">
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
   </td>
   </tr>
					<#list table.columns as c>
					<#if !c.htmlHidden>
						<#if c.isDateTimeColumn>
						<tr class="trex">
							 <td class="tdex">${c.columnAlias}
								 <s:textfield id="p${c.sqlName}" name="search.p${c.sqlName}" size="30" readonly="readonly" value="%{${classNameLower}.${c.sqlName}}"></s:textfield> 
								 <div id="p${c.sqlName}Tip" style="display: inline"></div>
							</td>
						</tr>
						<#else>
						<tr class="trex">
							 <td class="tdex">${c.columnAlias}
								 <s:textfield id="p${c.sqlName}" name="search.p${c.sqlName}" size="30" value="%{${classNameLower}.${c.sqlName}}"></s:textfield> 
								 <div id="p${c.sqlName}Tip" style="display: inline"></div>
							</td>
						</tr>
						</#if>
					</#if>	
					</#list>
					<tr class="trex">
						<td style="text-align: center;" class="tdex">
							<s:hidden value="%{order.ID}" name="search.pid" id="pid"></s:hidden>
							<input type="button" id="btnupdate" name="btnupdate"
								onclick="javascript:search();" value="查询" alt="查询"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnadd" name="btnadd"
								onclick="javascript:add();" value="新增" alt="新增" class="outStyle"
								onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnauth" name="btnauth"
								onclick="javascript:promethod(2);" value="审批" alt="审批"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnmodify" name="btnmodify"
								onclick="javascript:promethod(1);;" value="修改" alt="修改"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btndel" name="btndel"
								onclick="javascript:promethod(0);" value="删除" alt="删除" class="outStyle"
								onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnstop" name="btnstop"
								onclick="javascript:promethod(3);" value="中止" alt="中止"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnarchive" name="btnarchive"
								onclick="javascript:promethod(4);" value="归档" alt="归档"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnprint" name="btnprint"
								onclick="javascript:print();" value="打印" alt="打印"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnimport" name="btnimport"
								onclick="javascript:dbimport();" value="导入" alt="导入"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
							<input type="button" id="btnoutport" name="btnoutport"
								onclick="javascript:dboutport();" value="导出" alt="导出"
								class="outStyle" onmouseover="this.className='overStyle'"
								onmouseout="this.className='outStyle'" />
					</tr>
				</table>
</s:form>
</center>	
		<div id="f_bg">
			<div id="f_tabs">
				<ul>
					<li id="tab1" class="f_tabClass1">
						<a href="javascript:;" onclick="loadSearchList('<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=listdetail','',1,1);">全部</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="f_main">
			<div id="defaultlist"></div>
		</div>
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>		
<script language="javascript" type="text/javascript">	
		    //加载默认页面    
		    $(document).ready(function(){
		    //默认加载页面
		    loadSearchList('<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=listdetail','',1,1);
		    //验证
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){jAlert(msg,'提示')},onsuccess:function(){
			return true;
		}});
		//相关验证
		<#list table.columns as c>
		
		<#if c.isDateTimeColumn>
		$("#p${c.sqlName}").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd', oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"请输入${c.columnAlias}",tipid:"p${c.sqlName}Tip",onfocus:"",oncorrect:"请输入${c.columnAlias}"});
		<#else>
		$("#p${c.sqlName}").formValidator({onshow:"请输入${c.columnAlias}",tipid:"p${c.sqlName}Tip",onfocus:"",oncorrect:"请输入${c.columnAlias}"});
		</#if>
		
		</#list>
		
		//默认关闭对话框
		$('#dialog').dialog('close');
	});
	
	//查询
	function search(){
	    loadSearchList('<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=listdetail','',1,1);
	}

	//保存数据
	function add(){
		//window.open("/adminFrame.jsp");打开新窗体
		window.location.href="<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=new";
	}

	//审批
	function auth(){
	      if($.formValidator.pageIsValid()){
			var url ='/net/order.do?action='+action;
			document.forms[0].action=url;
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}

	//数据修改
	function modify(pid){
	    window.location.href="<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=edit&search.pid="+pid;
	}
	
	//删除
	function del(pid){
	   jConfirm('您确定删除该信息!', '确认框', function(r) {	
	    		if (r){
				   var url = '<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=delete&search.pid='+pid
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
							   window.location.href='<%=path%>${actionBasePath}${classNameLower}.${actionExtension}';
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

	

	//中止
	function stop(){
	    jAlert("中止!","提示"); 
	}	
	
	
	//归档
	function archive(){
	     jAlert("归档!","提示");
	}	
	
	
	
	//打印
	function print(){
	   jAlert("打印!","提示");
	}	
	
	
	//数据导入
	function dbimport(){
	   jAlert("数据导入!","提示");
	}		
	
	//数据导出
	function dboutport(){
	   jAlert("数据导出!","提示");
	}	
	
	
	

        //全选复选框
    	function chk_all(){
			var obj = $(searchpid);
			flag= obj[0].checked;
			if (obj != null){
				if (obj.length > 1){
					for (var i=0; i<obj.length; i++){
						obj[i].checked = !flag;
					}
				}else{
					obj[0].checked = !flag;
				}
			}
		}
		

		//全选相关操作
        function promethod(myvar){
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
						    if(obj[0].checked == true)
						    {
						      flag=1;
						      value=obj[0].value;
						    }
						}
					}
					if(flag==1)
					{
					   if(value.charAt(value.length-1)==',')
					   {
					      value=value.substr(0,value.length-1);
					   }
					   if(myvar==0){//删除 method方法0 代表删除
							jConfirm('您确定删除选中的!', '确认框', function(r) {
								    		if (r){
												var url = '<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=alldel&search.pid='+value
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
														     window.location.href='<%=basePath%>${actionBasePath}${classNameLower}.${actionExtension}';
														    //});
														   
														}
														else{
														    jAlert('删除未成功!','提示');  
														}
													}	
												}catch(e){ 
													jAlert(e,"提示");
												}	
											}
										});
					   }else if(myvar==1){//修改
					     if(value.indexOf(",")==-1){
					        window.location.href="<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?action=edit&search.pid="+value;
					     }else{
					        jAlert( '修改时仅可选一项！','提示');
					     }
					   }else if(myvar==2){//审批
							jConfirm('您确定审核该单据吗?', '确认框', function(r) {
								    		if (r){
												            $.ajax({
												                url: "<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?search.pid="+value,
												                type: "POST",
												                data: { action:"auth"},
												                success: function(data) {
												                    //使用eval函数
												                    var json = eval(data);
												                    var result=json[0].value;
												                    if(0<result){
																	     window.location.href='<%=basePath%>${actionBasePath}${classNameLower}.${actionExtension}';
																	}
																	else{
																    	jAlert('审批未成功!','提示');  
																	}
												                },
												                error : function(){
																     alert("服务器忙");
																}
												            });
											}
										});
					   }else if(myvar==3){//中止
							jConfirm('您确定中止单据吗?', '确认框', function(r) {
								    		if (r){
												            $.ajax({
												                url: "<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?search.pid="+value,
												                type: "POST",
												                data: { action:"stop"},
												                success: function(data) {
												                    //使用eval函数
												                    var json = eval(data);
												                    var result=json[0].value;
												                    if(0<result){
																	     window.location.href='<%=basePath%>${actionBasePath}${classNameLower}.${actionExtension}';
																	}
																	else{
																    	jAlert('中止未成功!','提示');  
																	}
												                },
												                error : function(){
																     alert("服务器忙");
																}
												            });
											}
										});
					   }else if(myvar==4){//归档
							jConfirm('您确定归档该单据吗?', '确认框', function(r) {
								    		if (r){
												            $.ajax({
												                url: "<%=path%>${actionBasePath}${classNameLower}.${actionExtension}?search.pid="+value,
												                type: "POST",
												                data: { action:"archive"},
												                success: function(data) {
												                    //使用eval函数
												                    var json = eval(data);
												                    var result=json[0].value;
												                    if(0<result){
																	     window.location.href='<%=basePath%>${actionBasePath}${classNameLower}.${actionExtension}';
																	}
																	else{
																    	jAlert('归档未成功!','提示');  
																	}
												                },
												                error : function(){
																     alert("服务器忙");
																}
												            });
											}
										});
					   }else{//打印(默认代表打印)
					     jAlert( '默认处理！','提示');
					   }
					}else{
					  jAlert( '至少选择一项！','提示');
					}
        }
</script>		    		
	</body>
</html>