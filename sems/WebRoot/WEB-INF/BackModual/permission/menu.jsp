<% 
response.setContentType("text/xml; charset=utf-8");
String xml = (String) request.getAttribute("curMenu") ;
out.write(xml) ;
%>