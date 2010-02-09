<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base /> 
    <title>上传大图</title>
<head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/css.css" type="text/css"/>
<script type="text/javascript">    
function callback(msg)    
{    
    document.getElementById("file").outerHTML = document.getElementById("file").outerHTML;
    document.getElementById("msg").innerHTML = "<font color=red>"+msg+"</font>";    
}    
 function FileNameStr(msg) 
 {
 document.getElementById("fileNameIdid").value=msg; 
 }
 
</script>  

<script type="text/javascript"> 
 function   setFatherUpload()     
  {      
    var fileId=document.getElementById("file").value;
    var pathStr=fileId;
    if(fileId!="")
	{
	 //取后缀名
    var hfileName="";
    pathStr=pathStr.replace(/([\\*\.\(\)\$\^])/g,";");
    var hpath=pathStr.split(";");
   
    for(var j=0;j<hpath.length;j++)　
    {　
    hfileName=hpath[j];
   
    }
    //取后缀名
  
   if((hfileName=="jpg")||(hfileName=="JPG")||(hfileName=="jpeg")||(hfileName=="JPEG")||(hfileName=="gif")||(hfileName=="GIF")||(hfileName=="bmp")||(hfileName=="BMP"))
    {
   
    document.getElementById("ipBig").value=document.getElementById("file").value;
    return true;
    }  
    }
    else
    {
    alert('请选择上传文件...');
    return false;  
    } 
    return false;  
    
    
  }  
  
</SCRIPT>
<script type="text/javascript"> 
 function   setFather()     
  {      
    var pathStr="";
    var fileName="";
    var   k=window.dialogArguments;
       
    var path=document.getElementById("ipBig").value;
    pathStr=path;
    if(path!=null)
    {
    //var arrTmp = path.split("\\\");
   // alert(arrTmp[arrTmp.length]);
    
    path=path.replace(/([\\*\[\]\(\)\$\^])/g,";");
    var arrTmp = path.split(";");
    var fileName="";
    for(var i=0;i<arrTmp.length;i++)　
    {　
    fileName=arrTmp[i];
    }

    //取后缀名
    var hfileName="";
    pathStr=pathStr.replace(/([\\*\.\(\)\$\^])/g,";");
    var hpath=pathStr.split(";");
    for(var j=0;j<hpath.length;j++)　
    {　
    hfileName=hpath[j];
    }
   //取后缀名
   
    if((hfileName=="jpg")||(hfileName=="JPG")||(hfileName=="jpeg")||(hfileName=="JPEG")||(hfileName=="gif")||(hfileName=="GIF")||(hfileName=="bmp")||(hfileName=="BMP"))
    {
    k.document.getElementById("bigPathId").value=document.getElementById("fileNameIdid").value;; 
    }
      } 
    window.close();
  }  
</SCRIPT>
</head>
<body  style="background-color:#f2f6fb">
<form action="addUpBigPic.jsp" id="form1" name="form1" encType="multipart/form-data"  method="post" target="hidden_frame" >
    <center>
    <table class="tbl"  cellspacing="0" cellpadding="0" border="0">
    <tr>
    <td colspan="2" align="center"  bgcolor="#009CD6"  background="../../images/newsystem/th2.gif" class="txt_b">
     上传缩略图
    </td>
    </tr>
    <tr>
    <tr>
    <td align="center" class="main">请选择:</td>
    <td align="center" class="main"><input type="file" id="file" name="file" style="width:350" />
    </td>
    </tr>
    <tr>
    <td colspan="2" align="center" class="main">
    <INPUT type="submit" value="上传" onclick="javascript:return setFatherUpload();" class="button"/>
    <input type='button' onclick="setFather()" value="确定返回"  class="button"/>
    <input type="hidden" id="ipBig" name="ipBig" />
    
    <input type="hidden" id="fileNameIdid" name="fileName"  /> 
    </td>
    </tr>
    <tr>
    <td colspan="2" align="left" class="main">
    备注:支持jpg,JPG,jpeg,JPEG,gif,GIF,bmp,BMP文件的上传.
    </td>
    </tr>
    <tr>
    <td colspan="2" align="left" class="main"><span id="msg"></span>&nbsp;</td>
    </tr>
    </table>   
    <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>  
    </center>
</form>    
</body>  
</html:html>