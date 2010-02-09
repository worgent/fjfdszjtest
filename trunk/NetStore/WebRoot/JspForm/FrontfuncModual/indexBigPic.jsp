<%@ page language="java"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="com.qzgf.NetStore.dao.IIndexFirstDAO" />
<jsp:directive.page import="com.qzgf.NetStore.dao.impl.IndexFirstDAO" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>ÍøÕ¾ÉÌ³Ç</title>
<link href="<%=request.getContextPath()%>/css/header01.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/index.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/catalog.css" type="text/css" rel="stylesheet"/>

<script type="text/javascript"> 	
function checkValue(obj)
{
var revCon=document.getElementById("contentId").value;
 if(revCon=="")
 {
 alert("ÆÀÂÛÄÚÈÝ²»ÄÜÎª¿Õ.");
 return false;
 }
 document.getElementById("contentHId").value=document.getElementById("contentId").value;
 
 var uId=document.getElementById("userNameId").value;
 if(uId=="")
 {
 alert("ÐÕÃû²»ÄÜÎª¿Õ.");
 return false;
 }
 var tId=document.getElementById("titleNameId").value;
 if(tId=="")
 {
 alert("±êÌâ²»ÄÜÎª¿Õ.");
 return false;
 }

document.forms[0].action='<%=request.getContextPath()%>/indexFirst.do?status=review&productId='+document.getElementById("productId").value+"&userNameReview="+document.getElementById("userNameId").value+"&pingji="+document.getElementById("radioId").value+"&titleName="+document.getElementById("titleNameId").value+"&contentH="+document.getElementById("contentHId").value;         
document.forms[0].submit();
return true;
}	

function buyProduct(productId,price)
{
   document.forms[0].action='<%=request.getContextPath() %>/JspForm/FrontfuncModual/cart.do?status=addItemToCart&productId='+productId+'&productPrice='+price;
   document.forms[0].submit();
}

</script>


</head>
<body >
<form action=""  method="post">
<center>

<jsp:include page="top.jsp"/>
 <table   width="99%" align="center"  >
    <tr>
    <td bgcolor="#ffffff" valign="top"  width="200" align="left">
     <%
        IIndexFirstDAO fr=new IndexFirstDAO();
        List productCatalogList=fr.pdtCatalog();
		request.setAttribute("productCatalogList", productCatalogList); 
		%>
 <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#C0CDAC">
          <tr>
            <td height="26" background="<%= request.getContextPath()%>/images/test/images/bjd.gif"><div align="left"><span class="mulu2">&nbsp;&nbsp;ÉÌÆ··ÖÀà</span></div></td>
          </tr>
          <tr>
            <td height="300" bgcolor="#FFFFFF"><div align="center"> <br />
              <table width="180" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td>
                    

	
	 <logic:present name="productCatalogList">
       <bean:define id="result" name="productCatalogList"  type="java.util.List" />
				<logic:iterate id="item" name="result" indexId="rowNum">
				
				 <logic:equal  name="item" property="parentId" value="0">
				 <div align="left">
                 <h4><font color="#1f7ff0">${item.catalogName}</font></h4>
                 </div> 
                 </logic:equal>
				 
				<logic:notEqual name="item" property="parentId" value="0">
			<!-- 	<bean:define id="result2" value="<%=String.valueOf(((rowNum.intValue()) % 4))%>" type="java.lang.String"/>-->


	            
				<a href="<%=request.getContextPath()%>/indexFirst.do?
status=someGoodsShow&catalogId=${item.id}">
				${item.catalogName}
				</a>
				<br/> 
			<!-- 	<logic:equal value="3" name="+result2"><br/></logic:equal>-->
				  </logic:notEqual>
	            </logic:iterate>
    </logic:present>
    	
	
	

    	
                    
                    
                    </td>
                  </tr>
                  </table>
              <br />
            </div></td>
          </tr>
        </table>
        
 <%
  String name2=request.getContextPath()+"/upload/bigPic"; 
        %>
</td>
<td   valign="top" align="left" bgcolor="#ffffff" >
   <table cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0" width="100%" height="834"><tr><td bgcolor="#ffffff"  valign="top">
      <logic:present name="onlyProductList">
    	<logic:iterate id="item" name="onlyProductList">
       <table border="0">   
        <tr>
           <td bgcolor="#ffffff" >
            <img src="<%=name2 %>\<bean:write name="item" property="bigPath"/>" width="200" height="200" border="0"/>
           </td>
           <td valign="top" bgcolor="#ffffff">
            <table width="342"  >
            <tr><td colspan="2">
            ${item.productName}
            </td>
            <td valign="top"><br /></td></tr>
            <tr><td>ÉÌÆ·ÊýÁ¿:</td><td>${item.stock}</td><td valign="top"><br /></td></tr>
            <tr><td>ÊÐ³¡¼Û:</td><td>${item.marketPrice}</td><td valign="top"><br /></td></tr>
            <tr><td>»áÔ±¼Û:</td><td>${item.memberPrice}</td><td valign="top"><br /></td></tr>
            <tr><td colspan="2" align="center">
            <input type="button" name="buy" value="¹ºÂò" onclick="buyProduct('${item.productId}','${item.memberPrice }')" />
            <input type="button" name="collection" value="ÊÕ²Ø"/>
              </td><td valign="top"><br /></td></tr>
            </table>
           </td>
       </tr>
       </table>
     
     <div>
     <table>
     <tr>
     <td>
     <img src="images/top_img_01.gif" width="546" height="40"></img>
     </td>
     </tr>
      <tr>
     <td>
      ${item.productIntro}
     </td>
     </tr>
     </table>
     </div>
  </logic:iterate>
     </logic:present>
     

    <div>
     <table  height="475" width="613">
     
     <tr>
     <td>
     <img src="images/top_img_02.gif" width="546" height="40"></img>
     </td>
     </tr>
      <tr>
     <td >
     
     ¡¡ <logic:present name="reviewList">
    	<logic:iterate id="item" name="reviewList">
       <table width="587" cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0" height="107">
       <tr><td width="40" bgcolor="#ffffff">Ö÷Ìâ:</td><td bgcolor="#ffffff">${item.title}</td></tr>
       <tr><td bgcolor="#ffffff">×÷Õß:</td><td bgcolor="#ffffff">${item.userId}</td></tr>
       <tr><td bgcolor="#ffffff">ÄÚÈÝ:</td><td bgcolor="#ffffff">${item.content}</td></tr>
       <tr>
       <td colspan="2" align="right" bgcolor="#ffffff">
         <a href="indexFirst.do?status=showAllReview&productId=<%=request.getAttribute("productId") %>">ä¯ÀÀ¸ÃÉÌÆ·µÄÈ«²¿ÆÀÂÛ</a>
       </td>
       </tr>
       
       </table>
       </logic:iterate>
       </logic:present>
       
       <br/>
       
      <table cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0" >
       <tr><td bgcolor="#ffffff">ÐÕÃû:</td>
       <td bgcolor="#ffffff">
       <input type="text" id="userNameId" name="userNameReview" value="ÄäÃû"/>
       <input type="radio" id="radioId" name="pingji"  value="1" checked />¡î
       <input type="radio" id="radioId" name="pingji"  value="2" />¡î¡î
       <input type="radio" id="radioId" name="pingji" value="3" />¡î¡î¡î
       <input type="radio" id="radioId" name="pingji"  value="4" />¡î¡î¡î¡î
       <input type="radio" id="radioId" name="pingji"  value="5"/>¡î¡î¡î¡î¡î
       </td>
       </tr>
       <tr>
       <td bgcolor="#ffffff">±êÌâ:</td>
       <td bgcolor="#ffffff">
       <input type="text" id="titleNameId" name="titleName" />
       </td>
       </tr>
       <tr>
       <td bgcolor="#ffffff">ÄÚÈÝ:</td>
       <td bgcolor="#ffffff"> 
       <textarea id="contentId" name="content"  cols="30" rows="4"></textarea> 
       <input type="hidden" id="contentHId" name="contentH"/>
       </td>
       </tr>
       
     
       
       <tr>
       <td colspan="2" align="center" bgcolor="#ffffff">
       <input type="button" name="reviewPost"  onclick="javascript:return checkValue(this);" value="Ìá½»±£´æ"/> 
       <input type="reset" name="resetName" value="ÖØ ÖÃ"/> 
       </td>
       </tr>
       
       </table>
     
     </td>
     </tr>
     </table>
     </div>
   
   
   
   </td></tr></table>
   
    </td>
    </tr>	
 </table>

<jsp:include flush="true" page="bottom.jsp" />
<input type="hidden" id="productId" name="productId" value="<%=request.getAttribute("productId") %>"/>      
</center>
</form>
</body>
</html>