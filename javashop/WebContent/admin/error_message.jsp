<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div    class='success'>
	 
	  <h4>哎呀，javashop出现了一个意外的错误:(<br/></h4>
	     
  <div style="clear:both;"> 
  
        <p>
        
   你愿意将这个错误提交给我们吗？ 这会使javashop更加完善、稳定！
<input type="button" name="bu" value="报告此错误" />
    </p>
  
   </div>
</div>

<div>
<h4>Exception Name: <s:property value="exception" /> </h4>
<h4>Exception Details: <s:property value="exceptionStack" /></h4> 
</div>