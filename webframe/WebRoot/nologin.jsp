<%@page language="java" contentType="text/html; charset=GBK"%>
<%@include file="/common/taglibs.jsp"%>
<% 
	session.invalidate(); 
%>
<script>
	function  window.onload(){   
	  	//var win = window.open("/login.jsp", "", "left=0, top=0, width="+(screen.width-14)+", height="+(screen.height-18)+",status=no,toolbar=no,menubar=no,location=no,resizable=no") ;
	  	//self.opener=null;
	  	//self.close();
	  	window.top.location.href ="/login.jsp";
	}
</script>