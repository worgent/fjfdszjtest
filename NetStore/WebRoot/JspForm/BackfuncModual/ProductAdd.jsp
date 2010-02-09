<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
	<html:base />
	<title>商品修改</title>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/jquery.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/jslib/setday.js"></script>
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/include/ext/resources/css/ext-all.css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/include/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/include/ext/ext-all.js"></script>

	<link rel="stylesheet"
		href="<%=request.getContextPath() %>/css/css.css" type="text/css" />


	<script type="text/javascript">
function BackAction(obj)
{
document.forms[0].action="../../product.do"; 
document.forms[0].submit();
}
function KeyPress(objTR)
{
   var txtval = objTR.value;
   var key = event.keyCode;
   if((key <48 || key >57)&&key !=46)
   {
         event.keyCode = 0;
   }
   else
   {
         if(key == 46)
         {
               if(txtval.indexOf(".") != -1 || txtval.length == 0)
                     event.keyCode = 0;
         }
   }
} 
</script>

	<script type="text/javascript">
   
  Ext.onReady(function(){
	
	var comboxWithTree = new Ext.form.ComboBox({   
        store:new Ext.data.SimpleStore({fields:[],data:[[]]}),   
        editable:false,
        renderTo: 'combo',
        mode: 'local',   
        triggerAction:'all',   
        maxHeight: 200,   
        tpl: "<tpl for='.'><div style='height:200px'><div id='tree'></div></div></tpl>",   
        selectedClass:'',   
        onSelect:Ext.emptyFn   
    });   
    var tree = new Ext.tree.TreePanel({   
        loader: new Ext.tree.TreeLoader({
           dataUrl:'../../productModify.do?status=getTree'
        }), 
        border:false,
        rootVisible: true,  
        root:new Ext.tree.AsyncTreeNode({text: '商品分类',id:'0'})
      });   
      tree.on('click',function(node){ 
          comboxWithTree.setValue(node.text);
          Ext.get("id").dom.value=node.id;
          Ext.get("catalogName").dom.value=node.text;  
        
          comboxWithTree.collapse();  
            
      });   
    comboxWithTree.on('expand',function(){   
        tree.render('tree');   
      });
    comboxWithTree.render('comboxWithTree'); 
    <%
   if (request.getAttribute("catalogName")!=null) {
   %>
      comboxWithTree.setValue("<%=request.getAttribute("catalogName") %>");
   <%
   }
    %>
});
  </script>
  
	<script type="text/javascript">
 var dWin=null;

function uploadSmallPic(str){

//document.forms[0].action="../../productModify.do?status=updSmallPic";
//document.forms[0].submit();
var feather="dialogWidth=416px;scrollbars=yes;dialogHeight=187px";
dWin=showModelessDialog('JspForm/BackfuncModual/addUpSmall.jsp',window,feather);
//if(dWin!=null)
//{
//document.getElementById("ip2").value=dWin;   
//}
}

		
function smallPicDetail(str){

			var url="productModify.do?status=showSmallPicture&picPath="+document.getElementById("smallPathId").value;
			var arg=document;
			var feather="dialogWidth=220px;scrollbars=yes;dialogHeight=220px";
			var sonvalue=window.showModalDialog(url,arg,feather);	
		
	}	
		//////////////////大图片
	
var dwinBig=null;	
function uploadBigPic(str){
//document.forms[0].action="../../productModify.do?status=updBigPic";
//document.forms[0].submit();
var feather="dialogWidth=416px;scrollbars=yes;dialogHeight=187px";
dwinBig=showModelessDialog('JspForm/BackfuncModual/addUpBig.jsp',window,feather);
		}
		
function bigPicDetail(str){
			var url="productModify.do?status=showBigPicture&picPath="+document.getElementById("bigPathId").value;
			var arg=document;
			var feather="dialogWidth=270px;scrollbars=yes;dialogHeight=270px";
			var sonvalue=window.showModalDialog(url,arg,feather);	
	}
		
		
	//////////////////大图片
</script>


<SCRIPT type="text/javascript">
function cataValue(obj)
{

 var comboStr=document.getElementById("id").value;
 if(comboStr=="0")
 {
 alert("请选择一个具体的商品分类...");
 return false;
 }
  var productname=document.getElementById("productname").value;
 if(productname.trim()=="")
 {
 alert("请填写商品名称.");
 return false;
 }
 var brandNowID=document.getElementById("brandNowID").value;
 if(brandNowID.trim()=="")
 {
 alert("请填写或选择品牌.");
 return false;
 }
 
 var UnitId=document.getElementById("UnitId").value;
 if(UnitId.trim()=="")
 {
 alert("暂时没有单位,请在单位设置添加.");
 return false;
 }
 
 
 
   var marketPrice=document.getElementById("marketPrice").value;
 if(marketPrice.trim()=="")
 {
 alert("请填写市场价.");
 return false;
 }
 
var memberPrice=document.getElementById("memberPrice").value;
 if(memberPrice.trim()=="")
 {
 alert("请填写会员价.");
 return false;
 }
 var stock=document.getElementById("stock").value;
 if(stock.trim()=="")
 {
 alert("请填写库存数量.");
 return false;
 }
 var ManufacturerId=document.getElementById("ManufacturerId").value;
 if(ManufacturerId.trim()=="")
 {
 alert("暂无供应商,请在生产厂家管理添加.");
 return false;
 }

 return true;
}



function fnChange(){
var brandId=document.getElementById("brandId");
if(brandId.selectedIndex!=0)
{
document.getElementById("brandNowId").innerText=brandId.options[brandId.selectedIndex].text;
document.getElementById("brandNowId").value=brandId.options[brandId.selectedIndex].text;
}
}
</SCRIPT>
	<script type="text/javascript">
 <logic:present name="xgResult">
 	alert('${xgResult}');
 </logic:present>
 </script>


</head>

<body onload="go()" background="<%=request.getContextPath()%>/images/bg.gif">
<script type="text/javascript">
function go() { 
alert('<%=request.getParameter("sPic")%>');
document.getElementById("smallPathId").innerText="<%=request.getParameter("sPic")%>";
document.getElementById("smallPathId").value="<%=request.getParameter("sPic")%>"

document.getElementById("UnitId").value="<%=request.getAttribute("UnitName")%>";
document.getElementById("ManufacturerId").value="<%=request.getAttribute("ManufacturerName")%>";
} 
</script>
	<html:form action="/productModify.do?status=AddSave" method="post">
		<center>
			<table class="tbl" width="780" cellspacing="0" cellpadding="0"
				border="0" height="180">
				<tr>
					<td colspan="2" bgcolor="#009CD6"
						background="../../images/newsystem/th2.gif" class="txt_b"
						align="center">
						商品添加
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						商品分类：
					</td>
					<td class="main" align="left">	
					<div id="combo"></div>
						<input type="hidden" name="id" id="id"
							value="<%=request.getAttribute("id") %>" />
						<input type="hidden" name="catalogName" id="catalogName"
							value="<%=request.getAttribute("catalogName") %>" />
					</td>
				</tr>

				<tr>
					<td class="main" align="left">
						商品名称:
					</td>
					<td class="main" align="left">
						<input type="text" id="productname" name="productname" /><font color="#ff0000">*</font>
					</td>
				</tr>

				<tr>
					<td class="main" align="left">
						品牌:
					</td>
					<td class="main" align="left">
						<input type="text" id="brandNowID" name="brand" />
						<select id="brandId" name="brandName" onchange="fnChange()">
							<logic:present name="brandList">
								<logic:iterate id="bitem" name="brandList" scope="request">
									<option value="${bitem.specificationId}">
										${bitem.specificationName}
									</option>
								</logic:iterate>
							</logic:present>
						</select>
						<font color="#ff0000">*</font>
					</td>
				</tr>

				<tr>
					<td class="main" align="left">
						规格:
					</td>
					<td class="main" align="left">
						<input type="text" id="specification" name="specification" />
					</td>
				</tr>

				<tr>
					<td class="main" align="left">
						单位:
					</td>
					<td class="main" align="left">
						<select id="UnitId" name="UnitName">
							<logic:present name="unitList">
								<logic:iterate id="unitem" name="unitList" scope="request">
									<option value="${unitem.unitId}">
										${unitem.unitName}
									</option>
								</logic:iterate>
							</logic:present>
						</select>
					
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						价格:
					</td>
					<td class="main" align="left">
						<table>
							<tr>
								<td class="main">
									市场价：
								</td>
								<td class="main">
									<input type="text" id="marketPrice" name="marketPrice"
										size="12" onkeypress="KeyPress(this)" />
									元<font color="#ff0000">*</font>

								</td>
								<td class="main">
									&nbsp;会员价：
								</td>
								<td class="main">
									<input type="text" id="memberPrice" name="memberPrice"
										size="12" onkeypress="KeyPress(this)" />
									元<font color="#ff0000">*</font>
								</td>
							</tr>
						</table>

					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						其它资料:
					</td>
					<td class="main" align="left">
						<table>
							<tr>
								<td class="main">
									库存:
								</td>
								<td>
									<input type="text" id="stock" size="12"
										onkeypress="KeyPress(this)" name="stock" />
									<font color="#ff0000">*</font>
								</td>
							</tr>
						</table>

					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						商品小图:
					</td>
					<td class="main" align="left">
						<input type="text" id="smallPathId" name="smallPath" readOnly />
						<input type="button" name="small" value="上传小图"
							onclick="uploadSmallPic('small')" class="button" />

						<input type="button" name="smallSee" value="查看图片"
							onclick="smallPicDetail('null')" class="button" />
					</td>

				</tr>
				<tr>
					<td class="main" align="left">
						商品大图:
					</td>
					<td class="main" align="left">
						<input type="text" id="bigPathId" name="bigPath" readOnly />
						<input type="button" value="上传大图"
							onclick="uploadBigPic('uploadBigPic')" class="button" />
						<input type="button" name="bigSee" value="查看图片"
							onclick="bigPicDetail('null')" class="button" />
					</td>

				</tr>
				<tr>
					<td class="main" align="left">
						供应商:
					</td>
					<td class="main" align="left">
						<select id="ManufacturerId" name="ManufacturerName">
							<logic:present name="ManufacturerList">
								<logic:iterate id="mitem" name="ManufacturerList"
									scope="request">
									<option value="${mitem.manufacturerId}">
										${mitem.manufacturerName}
									</option>
								</logic:iterate>
							</logic:present>
						</select>
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						上架时间:
					</td>
					<td class="main" align="left">
						<%=request.getAttribute("displayDate")%>
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						生产时间:
					</td>
					<td class="main" align="left">
						<input type="text" id="productDate" name="productDate"
							value="<%=request.getAttribute("productDate")%>"
							onclick="setday(this)" readonly size="12" />
					</td>
				</tr>

				<tr>
					<td class="main" align="left" height="150">
						商品说明:
					</td>
					<td class="main" align="left">
						<textarea id="shopIntroId" name="productIntro"
							style="width: 260; height: 150; overflow-x: visible; overflow-y: visible;"></textarea>
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						是否发布:
					</td>
					<td class="main" align="left">
						<input type="checkbox" id="chkIsRelease" name="chkIsRelease"
							checked />
					</td>
				</tr>
				<tr>
					<td class="main" align="left">
						&nbsp;
					</td>
					<td class="main" align="left">
						<table>
							<tr>
								<td class="main">
									<input type="checkbox" id="chkIsNewGoods" name="chkIsNewGoods"
										checked />
									新品
								</td>
								<td class="main">
									<input type="checkbox" id="chkIsCommend" name="chkIsCommend" />
									推荐
								</td>
								<td class="main">
									<input type="checkbox" id="chkIsSpecialPrice"
										name="chkIsSpecialPrice" />
									特价
								</td>
							</tr>
						</table>


					</td>
				</tr>

				<tr>
					<td colspan="2" class="main" align="center">
						<html:submit property="Save" value="保存"  onclick="javascript:return cataValue(this);" styleClass="button" />
						
						<html:cancel property="cancel" value="返回"
							onclick="BackAction('back')" styleClass="button" />
					</td>
					
					
				</tr>
				<tr>
				<td colspan="2" class="main" align="left">
					备注:<font color="#ff0000">* 表示为必填项</font>.
					</td>
				</tr>
			</table>
		</center>
		<input type="hidden" id="pId" name="pId"
			value="<%=request.getAttribute("pId") %>" />
		<input type="hidden" id="page" name="page"
			value="<%=request.getAttribute("page") %>" />
		<input type="hidden" id="selType" name="selType"
			value="<%=request.getAttribute("selTypeUrl") %>" />

	</html:form>
</body>
</html:html>
