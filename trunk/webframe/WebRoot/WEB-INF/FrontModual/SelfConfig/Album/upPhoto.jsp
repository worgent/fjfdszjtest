<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@page import="com.qzgf.utils.comm.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="<%=path %>/css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path %>/js/comm.js"></script>
		<script language="JavaScript" type="text/javascript">
<!--

var contextPath = "<%=request.getContextPath()%>";
var servletMappingStr = "<%=Constant.SERVLET_MAPPING%>";

function previewPic(upfilename){
  window.parent.upIframeSize();
  var upfile = document.getElementById(upfilename);
  if (upfile != "") {
    var extname = getExtName(upfile.value);
    if(extname != "jpg" && extname != "gif" && extname != "jpeg"){
      upfile.value = "";
      alert("<s:text name="格式只能是jpg、gif、jpeg"/>");
    }else{
      preViewPic('photoview', null, upfile.value, 0, null, 0, 120);
    }
  }
}

//图片预览
function preViewPic(picDivName,imgName,fn,iBorder,iBorderColor,iWidth,iHeight){
    //var picdiv=document.all(picDivName);
    var picdiv = document.getElementById(picDivName);
    
    if(picdiv != null && fn != null && fn!=''){
      if(imgName ==null) imgName = "picname";
      if(iBorder == null) iBorder=0;
      if(iBorderColor == null) iBorderColor="#CCCCCC";
      var strw,strh
      if(iWidth == null || iWidth == 0) strw = "";
      else strw = " width="+iWidth+" ";
      
      if(iHeight == null || iHeight == 0) strh = "";
      else strh = " height=" + iHeight + " ";
      
      picdiv.innerHTML="<img name='"+imgName+"' alt='预览状态...' "+strw+strh+" border="+iBorder+
            " src='"+fn+"' style='border-color:"+iBorderColor+"'>";
      document.focus();
    }
}

//-->
</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="5" cellspacing="0">
			<s:form action="/album.do" method="POST"
				enctype="multipart/form-data">
				<s:hidden name="action" value="addPhoto"></s:hidden>
				<s:hidden name="a_Id"></s:hidden>

				<tr>
					<td colspan="2">
						<s:actionerror theme="webframe0" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:actionmessage theme="webframe0" />
					</td>
				</tr>

				<tr>
					<td align="right">
						照片名称:
					</td>
					<td>
						<s:textfield name="p_Title"></s:textfield>
					</td>
				</tr>

				<tr>
					<td align="right">
						照片简介:
					</td>
					<td>
						<s:textarea name="p_Desc"></s:textarea>
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<s:file name="upload" cssClass="input2" size="40" onchange="previewPic('upload');" id="upload"></s:file>
					</td>
				</tr>
				<tr>
					<td>
						<span id="photoview"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:submit value="提交" cssClass="button2"></s:submit>
					</td>
				</tr>
			</s:form>
		</table>
	</body>
</html>
