<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>自定义</title>		
<script language="javascript">
function ejectnum(){   
  if ( ((event.keyCode > 47) && (event.keyCode < 58)) ||      
     ((event.keyCode > 95) && (event.keyCode < 124))||((event.keyCode>185)&&(event.keyCode<193)) 
     ||((event.keyCode>218)&&(event.keyCode<223))||(event.keyCode==9)||(event.keyCode==32)) {
       return false;      
      } else {     
        return true;      
      }
}  
	function addrow(){
		var customnum = Math.floor(document.all("num").value);
		customnum ++;
		document.all("num").value = customnum;//自定义字段数
		var oBody=document.all("customtableid");//customtableid
		var tableRow =oBody.insertRow(-1);	
		var a=Math.random();	
		var tableCol21=tableRow.insertCell();
		tableCol21.innerHTML="<div align='center' class='STYLE2 STYLE1'><input name='custom.fielddesc' onkeydown='return ejectnum();' class='' size='20'/></div>";
       
		var tableCol2=tableRow.insertCell(); 
			tableCol2.innerHTML="<div align='center' class='STYLE2 STYLE1'><select class='editcss' id='type"+a+"' onchange='change("+a+")' name='custom.fieldtype' >"+
						"<option value='0'>---请选择---</option>"+
						"<option value='varchar'>字符</option>"+
						"<option value='number'>数字</option>"+
						"<option value='date'>日期</option>"+
						"<option value='enum'>枚举</option>"+
						"<option value='position'>位置</option>"+
						"<option value='photo'>照片</option>"+
						"</select></div>";
	   
		var tableCol3=tableRow.insertCell();
			tableCol3.innerHTML="<div align='center' class='STYLE2 STYLE1'><input name='custom.fieldlength' id='len"+a+"' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\"  class='editcss' size='5' value=''/></div>";
		
		var tableCol51=tableRow.insertCell();
		
		tableCol51.innerHTML="<div align='center' class='STYLE2 STYLE1'><select id='enum"+a+"' name='custom.fieldenum'  class='editcss'>"+
							"<option value='-1' ></option>"+
							"</div>";
		var tableCol5=tableRow.insertCell();
			tableCol5.innerHTML="<div align='center' class='STYLE2 STYLE1'><input name='custom.fieldseqn' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" class='editcss' size='5' value=''/></div>"; 
		
		var tableCol7=tableRow.insertCell();
			tableCol7.innerHTML="<div align='center' class='STYLE2 STYLE1'><input name='custom.fieldremark' class='editcss'  value=''/></div>"; 
		
		var tableCol8=tableRow.insertCell();
			tableCol8.innerHTML="<div align='center' class='STYLE2 STYLE1'><select name='custom.fieldstate' class='editcss'>"+
						"<option value='1'>启用</option>"+
						"<option value='0'>停用</option>"+
						"</select></div>";
		
		var tableCol6=tableRow.insertCell();
			tableCol6.innerHTML="<div align='center' class='STYLE2 STYLE1'><input name='delButton' type='button' value='删除' onclick='delrow(this)' class='btn'/></div>";
	 
	}
	
	function delrow(r){
		if (!confirm('您确定是否删除，删除后该数据将无法恢复!')){
			return;
		}
		var i=r.parentNode.parentNode.rowIndex;
  		document.getElementById('customtableid').deleteRow(i);
  		var customnum = Math.floor(document.all("num").value);
		customnum = customnum-1;
		document.all("num").value = customnum;
	}
	
	function checkSubmit(){
	    var customnum = Math.floor(document.all("num").value);
	    var patternType=document.getElementById("patternType").value;
	    if(patternType=="0"||patternType==null){
	    	alert("请选择模板类别!");
	    	return false;
	    }
	    
	    var patternName=document.getElementById("patternName").value;
	    if(patternName==""||patternName==null){
	    	alert("请填写模板名称!");
	    	return false;
	    }
	    
	    var fielddescs=document.getElementsByName("fielddesc");
	    for(var i=0;i<fielddescs.length;i++){
	    	if(fielddescs[i].value==''||fielddescs[i].value==null){
	    		alert("必须填写字段名称!");
	    		return false;
	    	}
	    }
	    var fieldtypes=document.getElementsByName("custom.fieldtype");
	    for(var i=0;i<fieldtypes.length;i++){
	    	if(fieldtypes[i].value=='0'){
	    		alert("必须选择字段类型!");
	    		return false;
	    	}
	    }
	    var fieldlength=document.getElementsByName("custom.fieldlength");
	    for(var i=0;i<fieldlength.length;i++){
	    	if(fieldlength[i].value==''||fieldlength[i].value==null){
	    		alert("必须填写字段长度!");
	    		return false;
	    	}
	    }
		
	}

	function change(val){
		var type=document.getElementById("type"+val).value;
		//value
		if(type=='varchar'){
		    document.getElementById("len"+val).value = "";
			document.getElementById("enum"+val).value = "";
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=1;   
			mysel.options[0] = new Option("","") 
		}else if(type=='position'){//位置
			document.getElementById("len"+val).value=30;
			document.getElementById("enum"+val).value = "";
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=1;   
			mysel.options[0] = new Option("","") 
		}else if(type=='photo'){//照片
		    document.getElementById("len"+val).value=100;
			document.getElementById("enum"+val).value = "";
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=1;   
			mysel.options[0] = new Option("","") 
		}else if(type=='date'){//日期
		    document.getElementById("len"+val).value=20;
			document.getElementById("enum"+val).value = "";
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=1;   
			mysel.options[0] = new Option("","") 
		}else if(type==0){//默认
			alert("请选择字段类型!");
			document.getElementById("len"+val).value='';
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=1;   
			mysel.options[0] = new Option("","") 
		}else if(type=='number'){//数字
			document.getElementById("len"+val).value=20;
			document.getElementById("enum"+val).value = "";
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=1;   
			mysel.options[0] = new Option("","") 
		}else if(type=='enum'){//枚举
			var mysel=document.getElementById("enum"+val);
			mysel.options.length=0;   
			<s:iterator id='el' value='%{enumList}' status='index'>
			mysel.options[<s:property value='#index.count-1' /> ] = new Option("<s:property value='#el.name'/>","<s:property value='#el.id'/>") 
			</s:iterator>
			document.getElementById("len"+val).value=20;
		}
	}
	

</script>

</head>
	<body>
		<%
			java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
			String str_date1 = formatter.format(currentTime); //将日期时间格式化
		%>
<form action="/custom.do?actionType=save" onsubmit="return checkSubmit()"	method="post">
	<table  border="0" cellpadding="0" align="center" style="border-collapse: collapse" width="700" height="100%" id="table15">
	  <tr>
		<td align="center" colspan=3 height="50" style="vertical-align: middle;">
		<strong>自定义模板</strong><s:hidden id="num" name="num" value="0" />
		</td>
	  </tr>
	  <tr>
		 <td align="left" class="tdex" style="font-size:12px">
			 模板类别：
			<select name="custom.patternType" id="patternType" style="editcss">
				<option value="0">---请选择---</option>
				<option value="1">日常工作</option>
				<option value="2">督办工作</option>
				<option value="3">事项申报</option>
			</select>
		</td>
		<td align="left" class="tdex" style="font-size:12px">模&nbsp;板&nbsp;名：<input type="text" id="patternName" name="custom.patternName" value=""  class="editcss"/></td>
		<td align="left" class="tdex" style="font-size:12px">状&nbsp;&nbsp;&nbsp;&nbsp;态：
		  <select name="custom.state" style="editcss">
			<option value="1">启用</option>
			<option value="0">停用</option>
		  </select>
		</td>
  	 </tr>
	 <tr>
		<td align="left" class="tdex" style="font-size:12px">事&nbsp;&nbsp;&nbsp;&nbsp;件：<s:checkboxlist list="eventValues" listKey="key"
										listValue="value" name="event" theme="womobile"></s:checkboxlist>
		</td>
		<td align="left" class="tdex" style="font-size:12px">创建时间：<%=str_date1%></td>
		<td align="left" class="tdex" style="font-size:12px">创&nbsp;建&nbsp;人：<s:property value="%{custom.marker}" /></td>
	 </tr>
	 <tr>
		<td colspan=3 align="left" class="tdex" style="font-size:12px">描&nbsp;&nbsp;&nbsp;&nbsp;述：<textarea id="remark" name="custom.remark" cols="30" rows="5" style="editcss" style="width:360px;height:40px"></textarea></td>
		
	 </tr>
 </table>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td ><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" style="vertical-align:middle;"><img src="<%=path%>/css/images/tab_03.gif" width="15" /></td>
        <td background="<%=path%>/css/images/tab_05.gif"><img src="<%=path%>/css/images/311.gif" width="16" /> <span class="STYLE4">日常工作事项</span></td>
        <td width="14"><img src="<%=path%>/css/images/tab_07.gif" width="14"  /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" id="customtableid" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
         <tbody id="maintaintbody">
          <tr>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">字段名称(中文)</div></td>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">字段类型</div></td>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">字段长度</div></td>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">枚举来源</div></td>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">排序号</div></td>
			 <td width="16%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">备注</div></td>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">状态</div></td>
			 <td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><input id="addButton" name="addButton" type="button" value="添加" onclick="addrow()" class="btn" /></div></td>
           </tr>
          </tbody>
		<TFOOT id="maintaindetailTfoot" bgcolor="#FFFFFF" width="100%"></TFOOT>
        </table></td>
        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">
              分页:<qzgf:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
            </span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
    <table width="100%" align="center" border="0" cellspacing="0" cellpadding="0">
      <tr>
		<td align="center"  height="40" style="vertical-align: middle;"><input type="submit" name="submitButton"  value="保存" class="btn"/><input type="button" onclick="javascript:history.go(-1)" value="返回"  class="btn" /></td>
	  </tr>	
   </table>
</form>
	</body>
</html>