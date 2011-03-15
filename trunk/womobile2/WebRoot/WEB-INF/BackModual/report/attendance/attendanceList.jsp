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
       <strong>人员位置查询</strong>
   </td>
   </tr>
   <tr>
    <td>
		<s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
   </td>
   </tr>
   <tr class="trex" align="center">
						<tr class="trex" >
							 <td class="tdex" style="font-size:12px">
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
								onclick="javascript:promethod(1);" value="查询" alt="查询" class="btn"/>
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
		    loadSearchList('<%=path%>/report/repattendance.do?action=listdetail','',1,1);
		    //验证
		    $.formValidator.initConfig({formid:"form1",onerror:function(msg){jAlert(msg,'提示')},onsuccess:function(){
			return true;
		}});
		//相关验证
		
		$("#pbegMakeDateTime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd', oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"pbegMakeDateTimeTip",onfocus:"",oncorrect:"请输入创建时间"});
		$("#pendMakeDateTime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd', oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"pendMakeDateTimeTip",onfocus:"",oncorrect:"请输入创建时间"});
		
		
		//默认关闭对话框
		$('#dialog').dialog('close');
	});
	
	//查询
	function search(){
	    window.location.href="<%=path%>/report/repattendance.do?action=view&search.pid="+pid;
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
		               if(myvar==1){//修改
					     if(value.indexOf(",")==-1){
					       window.location.href="<%=path%>/report/repattendance.do?action=view&search.pid="+value+"&search.begindate="+$('#pbegMakeDateTime').val()+"&search.enddate="+$('#pendMakeDateTime').val();
					     }else{
					        jAlert( '查看时仅可选一项！','提示');
					     }
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