<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/extremecomponents.tld" prefix="ec"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
    <script type="text/javascript">
	function add_roleValue(){	 
		var rowNum = Math.floor(document.all("num").value);
		rowNum ++;
		document.all("num").value = rowNum;//roldInfo
		var oBody=document.all("maintaintbody");//mobileTbody
		document.all("maintaindetailinfo").insertBefore(oBody,document.all("maintaindetailTfoot"));
		var myTR =oBody.insertRow();		
		var myTD3=myTR.insertCell();	
			//myTD3.innerHTML="<div class='txt_td_normal'><input name='menuCode' type='text' size='10'/></div>";
			myTD3.innerHTML="<div class='txt_td_normal'><input name='menuCode' type='text' size='10'/></div>";
		var myTD4=myTR.insertCell();	
			myTD4.innerHTML="<div class='txt_td_normal'><input name='funIsShow' type='checkbox' size='10'/></div>";
		var myTD6=myTR.insertCell();	
			myTD6.innerHTML="<div class='txt_td_normal'><input name='remark' type='text' size='10'/></div>";	
		var myTD7=myTR.insertCell();		
			myTD7.innerHTML="<div class='txt_td_normal'><a style='cursor: hand' onclick='del_roleValue();'> 删除</a> </div>";
	}

 	function del_roleValue(){	 
 		var num = Math.floor(document.all("num").value);
		num = num-1;
		document.all("num").value = num;		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.maintaindetailinfo.deleteRow(cur_row);
	}
	// 被上个函数调用
 	function get_Element(the_ele,the_tag){
   		the_tag = the_tag.toLowerCase();
  		if(the_ele.tagName.toLowerCase()==the_tag)
  			return the_ele;
   		while(the_ele=the_ele.offsetParent){
     		if(the_ele.tagName.toLowerCase()==the_tag)
     			return the_ele;
   		}
   		return(null);
	}
	
	 function selectAll(ckboxname){
   		o=document.getElementsByName(ckboxname); 
   		for(i=0;i<o.length;i++) 
   			o[i].checked=event.srcElement.checked 
  	}
    </script>
	<title>角色权限管理</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/css.css" type="text/css"/>

</head>
  <body background="<%=request.getContextPath()%>/images/bg.gif">

		<form
		action="<%=request.getContextPath()%>/JspForm/BackfuncModual/roleValue.do"
		method="post">
		<center>
		
		<table class="tbl" width="80%"  cellspacing="0" cellpadding="0" id="maintaindetailinfo">
		<tbody id="maintaintbody">
		
		    <logic:present name="rid">
    		<input type="hidden" name="rid" value="${rid}">
    		</logic:present>
    		<logic:present name="rname">
    		<input type="hidden" name="rname" value="${rname}">
    		</logic:present>
    		
    	   <tr><td colspan="5"  bgcolor="#009CD6" background="../../images/newsystem/th2.gif" class="txt_b"  align="center">角色权限设置</td></tr>
    		<tr>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			<div class="txt_tr">菜单编号</div>
    			</td>
    			<td background="../../images/newsystem/th2.gif" valign="middle" class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			<div class="txt_tr"><input type="checkbox" onclick=selectAll('funIsShow')> 是否显示</div>
    			</td>	
    			<td background="../../images/newsystem/th2.gif" valign="middle"　class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			<div class="txt_tr">备注</div>
    			</td>   	
    		</tr>
    		<logic:present name="Size">
    			<logic:equal name="Size" value="0">
  				<tr>
    					<input type="hidden" name="num" value="0">
    			</tr>			
    			</logic:equal>
    		<logic:notEqual name="Size" value="0">
    		<logic:present name="roleValueList">
    			<logic:iterate id="roleValue" name="roleValueList">
    				<tr>
    					<input type="hidden" name="num" value="${Size}">
    				</tr>
    				<tr>
    					<td class="main">
    					<div class="txt_td_normal">
    					<logic:present name="menuList">
    						<logic:iterate id="menu" name="menuList">
    						<logic:equal name="menu" property="theCode" value="${roleValue.menuCode}">
    							<input type="hidden" name="menuCode" value="${roleValue.menuCode}" >${menu.theName}
    						</logic:equal>						
    					    </logic:iterate>
    					</logic:present>
    					</div>
    					</td>
    					<td class="main">
    					<div class="txt_td_normal">
    					<logic:equal name="roleValue" property="funisShow" value="1">
    		 			<input name="funIsShow" type="checkbox" value="${roleValue.menuCode}" size="10" checked />			
    					</logic:equal>
    					<logic:equal name="roleValue" property="funisShow" value="0">
       		 			<input name="funIsShow" type="checkbox" value="${roleValue.menuCode}" size="10"/>	 					
    					</logic:equal>
    					</div>
    					</td>
      					<td class="main">
    					<div class="txt_td_normal">
      					<input name="remark" type="text" value="${roleValue.remark}" size="20"/>
    					</div>
    					</td>  					
    				</tr> 
    			</logic:iterate>
    		</logic:present>
    		</logic:notEqual>
    	    </logic:present>
    	</tbody>
		<TFOOT id="maintaindetailTfoot">
		</TFOOT>	
		 <div align="center">   
         <table>
         <tr>
         <td width="80%" align="right">
         <input type="hidden" name="status" value="addRoleValue"/>
         <input type="submit" value="保存" class="button" />
         <input type="button" value="返回" class="button" onclick="javascript:self.history.back(); " />
         </td>
         </tr>
         </table>
    </div>	
    	</table>
		</center>
	</form>
</body>
</html:html>
<logic:present name="xgResult">
   <script type="text/javascript">
       alert("${xgResult}");
   </script>
</logic:present>

<%
//    					<input name="menuCode" type="text" value="${roleValue.menuCode}" size="10"/>
/*
    					
    					<td>
    					<div class="txt_td_normal">
    						<a style="cursor: hand" onclick='del_roleValue();'>删除</a> 
    				    </div>
    					</td>
    					
    					    			<td background="../../images/newsystem/th2.gif" valign="middle"　class="main" bgcolor="#ECF2FF" nowrap="nowrap">
    			<div class="txt_tr"> <a style="cursor: hand" onclick="add_roleValue();">添加</a></div>
    			</td>
*/
 %>
