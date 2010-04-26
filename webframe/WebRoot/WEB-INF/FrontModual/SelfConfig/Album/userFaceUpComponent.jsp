<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<script language="JavaScript" type="text/javascript">
window.parent.OnUploadCompleted("<s:property value="%{ajaxCodeid}"/>","<s:property value="%{ajaxMsg}"/>");
</script>