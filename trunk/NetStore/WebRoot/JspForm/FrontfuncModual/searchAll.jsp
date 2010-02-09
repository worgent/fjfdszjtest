<%@ page language="java"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html >
<head>
<title>搜索所有商品</title>

<link href="<%=request.getContextPath()%>/css/header01.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/index.css" type="text/css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/css/catalog.css" type="text/css" rel="stylesheet"/>

</head>
<body class="ProducTypeHome1" >
 <form action="" method="post">
  <center>
  
<jsp:include flush="true" page="top.jsp" />  

 <table width="98%"  cellspacing="1" cellpadding="2" bgcolor="#e1e1e1" border="0" >
 <tr>
 <td align="left" bgcolor="#FFFFFF">
 
<logic:present name="productCatalogList">
       <bean:define id="result" name="productCatalogList"  type="java.util.List" />
				<logic:iterate id="item" name="result" indexId="rowNum">
				
				 <logic:equal  name="item" property="parentId" value="0">
				 <div align="left">
                 <h4>${item.catalogName}</h4>
                 </div> 
                 </logic:equal>
				 
				<logic:notEqual name="item" property="parentId" value="0">
				<bean:define id="result2" value="<%=String.valueOf(((rowNum.intValue()) % 4))%>" type="java.lang.String"/>
	            
				<a href="indexFirst.do?status=someGoodsShow&catalogId=${item.id}">
				${item.catalogName}
				</a>|
				<logic:equal value="5" name="result2"><br/></logic:equal>
				  </logic:notEqual>
	            </logic:iterate>
    </logic:present>
	
											
 </td>
 </tr>
 
 </table> 
 <jsp:include flush="true" page="bottom.jsp" />  
 </center>
  
 
 
 
 </form>
 
</body>
</html>