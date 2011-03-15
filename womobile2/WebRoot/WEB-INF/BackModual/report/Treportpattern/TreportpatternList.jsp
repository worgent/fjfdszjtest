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
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
		<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
	</head>
	<body>
 
<s:form method='POST' name='form1' action="treportpattern"  namespace="/report" id="form1" enctype="multipart/form-data">
   <table width="700" align="center"  cellspacing=0 cellpadding="0" border="0" class="tableex">
   <tr class="trex">
   <td align="center" height="50" style="vertical-align:middle;"> 
       <strong>报表管理</strong>
   </td>
   </tr>
   <tr>
    <td>
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
   </td>
   </tr>
   <tr class="trex" align="center">
							 <td class="tdex" style="font-size:12px">模板类别
								<select name="search.ppatterntype" id="ppatterntype" class="selectcss" onchange="changePattern()">
																<option value="" <s:if test="%{treportpattern.patterntype==''}">selected="selected"</s:if>>
																---全部---
																</option>
																<option value="1" <s:if test="%{treportpattern.patterntype==1}">selected="selected"</s:if>>
																日常工作
																</option>
																<option value="2" <s:if test="%{treportpattern.patterntype==2}">selected="selected"</s:if>>
																督办工作
																</option>
																<option value="3" <s:if test="%{treportpattern.patterntype==3}">selected="selected"</s:if>>
																事项申报
																</option>
															</select>
							<div id="pPatternIdTip" style="display: inline"></div>
							</td>
						</tr>
						
						<tr class="trex" align="center">
							 <td class="tdex" style="font-size:12px">模&nbsp;&nbsp;&nbsp;&nbsp;板
								<select id="pPatternId"  name="search.pPatternId" class="selectcss"></select>
								 <div id="pPatternIdTip" style="display: inline"></div>
							报表名称
								 <s:textfield id="pReportName" name="search.pReportName" size="30" value="%{treportpattern.ReportName}" cssClass="editcss"></s:textfield> 
								 <div id="pReportNameTip" style="display: inline"></div>
							
							备&nbsp;&nbsp;&nbsp;&nbsp;注
								 <s:textfield id="pRemark" name="search.pRemark" size="30" value="%{treportpattern.Remark}" cssClass="editcss"></s:textfield> 
								 <div id="pRemarkTip" style="display: inline"></div>
							</td>
						</tr>
						<tr class="trex" >
							 <td class="tdex" style="font-size:12px">创&nbsp;建&nbsp;人
								 <s:textfield id="pMaker" name="search.pMaker" size="30" value="%{treportpattern.Maker}" cssClass="editcss"></s:textfield> 
								 <div id="pMakerTip" style="display: inline"></div>
							 开始时间
								 <s:textfield id="pbegMakeDateTime" name="search.pbegMakeDateTime" size="30" readonly="readonly" value="%{treportpattern.MakeDateTime}" cssClass="editcss"></s:textfield> 
								 <div id="pbegMakeDateTimeTip" style="display: inline"></div>
							 结束时间
								 <s:textfield id="pendMakeDateTime" name="search.pendMakeDateTime" size="30" readonly="readonly" value="%{treportpattern.MakeDateTime}" cssClass="editcss"></s:textfield> 
								 <div id="pendMakeDateTimeTip" style="display: inline"></div>
						    </td>
						</tr>					
					<tr class="trex" height="20px">
						<td style="text-align: center;" class="tdex">
							<s:hidden value="%{order.ID}" name="search.pid" id="pid"></s:hidden>
							<input type="button" id="btnupdate" name="btnupdate"
								onclick="javascript:search();" value="查询" alt="查询" class="btn"/>
							<input type="button" id="btnadd" name="btnadd"
								onclick="javascript:add();" value="新增" alt="新增" class="btn" />
							<input type="button" id="btnmodify" name="btnmodify"
								onclick="javascript:promethod(1);;" value="修改" alt="修改" class="btn"/>
							<input type="button" id="btndel" name="btndel"
								onclick="javascript:promethod(0);" value="删除" alt="删除" class="btn"/>							 
						</td>
					</tr>
				</table>
<div id="f_main" style="display:inline;width:100%;position:relative;left:0px;top:0px;padding-top:0px;padding-left:0px;">
	<div id="defaultlist"></div>
</div>	
</s:form>		
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>		
<script language="javascript" type="text/javascript">	
		    //加载默认页面    
		    $(document).ready(function(){
		    //默认加载页面
		    loadSearchList('<%=path%>/report/treportpattern.do?action=listdetail','',1,1);
		    //验证
		$.formValidator.initConfig({formid:"form1",onerror:function(msg){jAlert(msg,'提示')},onsuccess:function(){
			return true;
		}});
		//相关验证
		
		$("#pID").formValidator({onshow:"",tipid:"pIDTip",onfocus:"",oncorrect:"请输入主键"});
		
		
		$("#pPatternId").formValidator({onshow:"",tipid:"pPatternIdTip",onfocus:"",oncorrect:"请输入模板"});
		
		
		$("#pReportName").formValidator({onshow:"",tipid:"pReportNameTip",onfocus:"",oncorrect:"请输入报表名称"});
		
		
		$("#pState").formValidator({onshow:"",tipid:"pStateTip",onfocus:"",oncorrect:"请输入是否启动"});
		
		
		$("#pRemark").formValidator({onshow:"",tipid:"pRemarkTip",onfocus:"",oncorrect:"请输入备注"});
		
		
		$("#pMaker").formValidator({onshow:"",tipid:"pMakerTip",onfocus:"",oncorrect:"请输入创建人"});
		
		
		$("#pbegMakeDateTime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd', oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"pbegMakeDateTimeTip",onfocus:"",oncorrect:"请输入创建时间"});
		$("#pendMakeDateTime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd', oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"pendMakeDateTimeTip",onfocus:"",oncorrect:"请输入创建时间"});
		
		
		//默认关闭对话框
		$('#dialog').dialog('close');
	});
	
	//查询
	function search(){
	    loadSearchList('<%=path%>/report/treportpattern.do?action=listdetail','',1,1);
	}

	//保存数据
	function add(){
		//window.open("/adminFrame.jsp");打开新窗体
		window.location.href="<%=path%>/report/treportpattern.do?action=new";
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
	    window.location.href="<%=path%>/report/treportpattern.do?action=edit&search.pid="+pid;
	}
	
	//删除
	function del(pid){
	   jConfirm('您确定删除该信息!', '确认框', function(r) {	
	    		if (r){
				   var url = '<%=path%>/report/treportpattern.do?action=ete.do&search.pid='+pid
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
							   window.location.href='<%=path%>/report/treportpattern.do';
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

	
 
	

        //全选复选框
    	function chk_all(){
			var obj = $("[name='searchpid']");
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
        			var obj = $("[name='searchpid']");
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
							jConfirm('您确定删除选中的记录？', '确认框', function(r) {
								    		if (r){
												var url = '<%=path%>/report/treportpattern.do?action=alldel&search.pid='+value
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
														     window.location.href='<%=basePath%>/report/treportpattern.do';
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
					        window.location.href="<%=path%>/report/treportpattern.do?action=edit&search.pid="+value;
					     }else{
					        jAlert( '修改时仅可选一项！','提示');
					     }
					   }else{//打印(默认代表打印)
					     jAlert( '默认处理！','提示');
					   }
					}else{
					  jAlert( '至少选择一项！','提示');
					}
        }
        
        
        //模板变化:改变模板选择
  	function changePattern(){
  	    var value=$("#ppatterntype").val();
  	    $.ajax(
					{	 
						type : "post",  
						url : '<%=path%>treportpattern!selectpattern.do?search.ppatterntype='+value,
						success : function(data){
						  data=eval('('+data+')');
						  if(data!=null){
							    document.getElementById("pPatternId").options.length = 0;
							    for(i=0;i<data.length;i++){
							      document.getElementById("pPatternId").options.add(new Option(data[i].name,data[i].id)); 
							    }
						  }
						},
						complete : function(){
						  //alert("complete");  
						},  
						error : function(e){
						  // alert("请求异常!"); 
						}
					}
		);
  	}
  	
</script>		    		
	</body>
</html>