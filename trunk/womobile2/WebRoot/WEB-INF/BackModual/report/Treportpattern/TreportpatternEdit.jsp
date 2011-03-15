<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<script type="text/javascript" src="<%=path%>/js/defValidator.js"></script>
<!-- 引入日期控件及验证 -->
<script defer="defer" src="<%=path%>/js/datepicker/WdatePicker.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/DateTimeMask.js" type="text/javascript"></script>	
<script language="javascript" src="<%=path%>/js/optiontransferselect.js" type="text/javascript"></script>
<script language="javascript" src="<%=path%>/js/formToWizard.js" type="text/javascript"></script>
    <style type="text/css">    
        #main { width:960px; margin: 0px auto; border:solid 1px #b2b3b5; -moz-border-radius:10px; padding:20px; background-color:#FFFFFF;}
        #header { text-align:center; border-bottom:solid 1px #b2b3b5; margin: 0 0 20px 0; }
        fieldset { border:none; width:320px;}
        legend { font-size:18px; margin:0px; padding:10px 0px; color:#b0232a; font-weight:bold;}
        .selectcss {font-family:"宋体";height:20px; width:250px; border:solid 1px #cadcb2; font-size:12px; color:#000000;vertical-align:middle;}
        input[type=text], input[type=password] {font-family:"宋体";height:20px; width:250px; border:solid 1px #cadcb2; font-size:12px; color:#000000;vertical-align:middle;}
        .prev:link, .next:link { background-color:#b0232a; padding:5px 10px;color:#FFFFFF;text-decoration:none;}
        .prev:active, .next:active { background-color:#b0232a; padding:5px 10px;color:#FFFFFF;text-decoration:none;}
        .prev:visited, .next:visited { background-color:#b0232a; padding:5px 10px;color:#FFFFFF;text-decoration:none;}
        .prev:hover, .next:hover { background-color:#000;color:#FFFFFF; text-decoration:none;} 
        .prev { float:left;}
        .next { float:right;}
        a { background-color:#b0232a; padding:5px 10px;color:#FFFFFF;text-decoration:none;}
        #steps { list-style:none; width:100%; overflow:hidden; margin:0px; padding:0px;}
        #steps li {font-size:20px; float:left; padding:10px; color:#b0b1b3;}
        #steps li span {font-size:11px; display:block;}
        #steps li.current { color:#b0232a;}
    </style>
  
</head>
<body>
<center>
<form  method='POST' name='form1' action='/report/treportpattern.do'  id="form1" onsubmit="customOnsubmit();">
    <div id="main">
        <div id="header">
        <strong>报表管理</strong>
        <s:actionerror theme="ems" />
		<s:actionmessage theme="ems" />
        </div>
        <fieldset>
            <legend>报表基本信息</legend>
				<span style="FONT-SIZE: 9pt">
				模板类别：</span>
				<span style="font-size: 9pt">
				<select name="search.ppatterntype" id="ppatterntype" class="selectcss" onchange="changePattern()">
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
					</span> <div id="ppatterntypeTip" style="display: inline "></div><br/><br />
					<span style="FONT-SIZE: 9pt">
					模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板：</span>
					<span style="font-size: 9pt"><select onchange="changeCombo()" id="pPatternId" class="selectcss"  name="search.pPatternId"></select>
					</span> <div id="pPatternIdTip" style="display: inline "></div><br /><br />
					<span style="FONT-SIZE: 9pt">
					报表名称：</span>
					<span style="font-size: 9pt">
					<s:textfield id="pReportName"  name="search.pReportName"  size="30" value="%{treportpattern.ReportName}" > </s:textfield>
					</span> <div id="pReportNameTip" style="display: inline "></div><br /><br />
					<span style="FONT-SIZE: 9pt">
					备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</span>
					<span style="font-size: 9pt">
					<s:textfield id="pRemark"  name="search.pRemark"  size="30" value="%{treportpattern.Remark}" > </s:textfield>
					</span> <div id="pRemarkTip" style="display: inline "></div><br /><br />
					<span style="FONT-SIZE: 9pt">
					创&nbsp;&nbsp;建&nbsp;&nbsp;人：</span>
					<span style="font-size: 9pt">
					<s:textfield id="pMaker"  name="search.pMaker"  size="30" value="%{treportpattern.Maker}" > </s:textfield>
					</span> <div id="pMakerTip" style="display: inline "></div>

        </fieldset>
        <fieldset>
            <legend>报表查询字段</legend>
<table>
												    <tr>
											    	    <td rowspan="2" align="center">
													    	<select name="leftSideCartoonCharacters" size="15" id="search_leftSideCartoonCharacters" multiple="multiple">
													    		<option value="headerKey">--- 未分配的字段 ---</option>
															</select>
														</td>
														<td align="center"><INPUT type="button" value="&gt;&gt;" name="gr" onclick="moveSelectedOptions(document.getElementById('search_leftSideCartoonCharacters'), document.getElementById('search_rightSideCartoonCharacters'), false, 'headerKey', '')" /></td>
														 <td rowspan="2" align="center">
													    	<select name="search.psearchid" size="15"	multiple="multiple" id="search_rightSideCartoonCharacters">
													    		<option value="headerKey">--- 已分配的字段 ---</option>
															</select>
														</td>
													</tr>
													<tr>
													<td align="center"><INPUT type="button" value="&lt;&lt;" name="gl" onclick="moveSelectedOptions(document.getElementById('search_rightSideCartoonCharacters'), document.getElementById('search_leftSideCartoonCharacters'), false, 'headerKey', '')" ></td>
												    </tr>
												</table>
        </fieldset>
        <fieldset>
            <legend>报表表头字段</legend>
<table>
												<tr>
													    <td rowspan="2" align="center">
													    	<select name="head_leftSideCartoonCharacters" size="15" id="head_leftSideCartoonCharacters" multiple="multiple">
													    		<option value="headerKey">--- 未分配的字段 ---</option> 
															</select>
														</td>
														<td align="center"><INPUT type="button" value="&gt;&gt;" name="gr" onclick="moveSelectedOptions(document.getElementById('head_leftSideCartoonCharacters'), document.getElementById('head_rightSideCartoonCharacters'), false, 'headerKey', '')" /></td>
													    <td rowspan="2" align="center">
													    
													    	<select name="search.pheadid" size="15"	multiple="multiple" id="head_rightSideCartoonCharacters">
													    		<option value="headerKey">--- 已分配的字段 ---</option> 
															</select>
														</td>
												</tr>
												<tr>	
												<td align="center"><INPUT type="button" value="&lt;&lt;" name="gl" onclick="moveSelectedOptions(document.getElementById('head_rightSideCartoonCharacters'), document.getElementById('head_leftSideCartoonCharacters'), false, 'headerKey', '')" ></td>	 
												</tr>
												</table>

        </fieldset>
        <p>
		<s:hidden value="%{treportpattern.id}" name="search.pid" id="pid"></s:hidden>
      	<s:hidden value="%{action}" name="action" id="action"></s:hidden>
		<input type="button" id="saveform" onclick="javascript:save()" value="保存" class="btn"  />
		<input type="button" onclick="javascript:reset()" value="重置" class="btn" />
		<input type="button" onclick="javascript:history.go(-1)" value="返回" class="btn"  />
        </p>
    </div>
 
</form>
</center>
 <script type="text/javascript">
     //验证
    $(document).ready(function(){   
        $("#form1").formToWizard({ submitButton: 'saveform' });   
        $.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){return true;}});
		$("#pID").formValidator({onshow:"",tipid:"pIDTip",onfocus:"",oncorrect:"请输入主键"});
		$("#pPatternId").formValidator({onshow:"",tipid:"pPatternIdTip",onfocus:"",oncorrect:"请输入模板"});
		$("#pReportName").formValidator({onshow:"",tipid:"pReportNameTip",onfocus:"",oncorrect:"请输入报表名称"});
		$("#pState").formValidator({onshow:"",tipid:"pStateTip",onfocus:"",oncorrect:"请输入是否启动"});
		$("#pRemark").formValidator({onshow:"",tipid:"pRemarkTip",onfocus:"",oncorrect:"请输入备注"});
		$("#pMaker").formValidator({onshow:"",tipid:"pMakerTip",onfocus:"",oncorrect:"请输入创建人"});
		$("#pMakeDateTime").focus(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss',oncleared:function(){$(this).blur();},onpicked:function(){}})}).formValidator({onshow:"",tipid:"pMakeDateTimeTip",onfocus:"",oncorrect:"请输入创建时间"});
		
	});
changePattern();

changeCombo();

 
  	//保存数据
	function save(){
	    if($.formValidator.pageIsValid()){
	        customOnsubmit();
			document.forms[0].submit();
		}else
		{
		  return false;
		}
	}
	 //重置数据
	function reset(){
			document.forms[0].reset();
			return true;
	}
 	//选项卡变化
      	 $(".tabs>li").click(function(){
	 	 	 var tabid=$(this).attr("tabid");
	 	 	 //选项卡 
	 	 	 $(".tabs>li").addClass("f_tabClass2");
	 	 	 $(this).removeClass("f_tabClass2");
	 	 	 $(this).addClass("f_tabClass1");
	 	 	 //选项卡内容
	 	 	 $(".tab-panel").hide();
	 	 	 $("div[tabid=tab_"+tabid+"]").show();
  		});
  		
  	//模板变化,查询,表头的列表变化
  	function changeCombo(){
  	    var value=$("#pPatternId").val();
  	    var reportvalue=$("#pid").val();
  	    var ppatterntype=$("#ppatterntype").val();
  	   // alert(value);  +'&search.pReportId='+value   ppatterntype
  	    $.ajax(
					{	 
						type : "post", 
						<s:if test="%{action=='update'}">
						url : '<%=path%>treportpattern!selectdata.do?search.pPatternId='+value+'&search.pReportId='+reportvalue+'&search.ppatterntype='+ppatterntype, 
						</s:if>
						<s:else>
						url : '<%=path%>treportpattern!selectdata.do?search.pPatternId='+value+'&search.ppatterntype='+ppatterntype+'&search.pReportId=\'\' ', 
						</s:else>
						success : function(data){
						  data=eval('('+data+')');
						  var cjson=data["cjson"];
						  var calljson=data["calljson"];
						  var fjson=data["fjson"];
						  var falljson=data["falljson"];  
						  if(calljson!=null){
							    document.getElementById("search_leftSideCartoonCharacters").options.length = 1;
							    for(i=0;i<calljson.length;i++){
							      document.getElementById("search_leftSideCartoonCharacters").options.add(new Option(calljson[i].name,calljson[i].id)); 
							    }
						  }
						  if(cjson!=null){
							    document.getElementById("search_rightSideCartoonCharacters").options.length = 1;
							    for(i=0;i<cjson.length;i++){
							      document.getElementById("search_rightSideCartoonCharacters").options.add(new Option(cjson[i].name,cjson[i].id)); 
							    }
						  }
						  if(falljson!=null){
							    document.getElementById("head_leftSideCartoonCharacters").options.length = 1;
							    for(i=0;i<falljson.length;i++){
							      document.getElementById("head_leftSideCartoonCharacters").options.add(new Option(falljson[i].name,falljson[i].id)); 
							    }
						  }
						  if(fjson!=null){
							    document.getElementById("head_rightSideCartoonCharacters").options.length = 1;
							    for(i=0;i<fjson.length;i++){
							      document.getElementById("head_rightSideCartoonCharacters").options.add(new Option(fjson[i].name,fjson[i].id)); 
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
  	
  	
  	//模板变化:改变模板选择
  	function changePattern(){
  	//value="%{treportpattern.PatternId}" 
  	//pPatternId
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
  	    <s:if test="%{action=='update'}">
  	        $("#pPatternId").val( <s:property value="treportpattern.PatternId" default="" />);
		</s:if>
		changeCombo();
  	}
  	
  	
  	//选择该选择
  	function customOnsubmit() {
  	    //选择全部数据
		var head_right = document.getElementById("head_rightSideCartoonCharacters");
		selectAllOptions(head_right);
		var search_right = document.getElementById("search_rightSideCartoonCharacters");
		selectAllOptions(search_right);
		//排除不传输的
		selectUnselectMatchingOptions(head_right, "headerKey", "unselect", false, "key")
		selectUnselectMatchingOptions(search_right, "headerKey", "unselect", false, "key")
	}
	

	
	</script>
</body>
</html>	