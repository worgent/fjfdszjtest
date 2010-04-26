<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>55
		</title>
	

	    	<link rel="StyleSheet" href="<%=request.getContextPath() %>/css/dtree.css" type="text/css" />   
            <script type="text/javascript" src="<%=request.getContextPath() %>/js/dtree.js"></script>   
           
	</head>
	<body>
	
	<div class="dtree">   
   
    <p><a href="javascript: d.openAll();">展 开</a> | <a href="javascript: d.closeAll();">收 缩</a></p>   
   
    <script type="text/javascript">   
    
        d = new dTree('d');
        d.add(0,-1,'My example tree');  
        
        <s:iterator id="prod" value="%{categoriesList.objectList}">
        d.add(<s:property value="#prod.CATEGORIESID" />,<s:property value="#prod.FID" />,'<s:property value="#prod.CATEGORIESNAME" />');    
         </s:iterator>
        document.write(d);    

    </script>   
   
</div>   
	
	
	
	
	
	</body>
</html>