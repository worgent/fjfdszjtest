<%
Integer m_idck=(Integer)session.getAttribute("m_id");
String m_accountck=(String)session.getAttribute("m_account");
if(m_idck==null||m_accountck==null){response.sendRedirect("login.jsp");}
%>