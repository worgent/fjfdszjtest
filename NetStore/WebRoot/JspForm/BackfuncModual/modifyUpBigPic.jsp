<%@ page language="java" contentType="text/html; charset=gb2312" %> 
<%@ page import="com.jspsmart.upload.SmartUpload"%> 
<%@  page  import="java.text.SimpleDateFormat"  %> 
<%@  page  import="java.util.*"  %> 

<html>
 <head>
  <title>文件上传</title>
<jsp:useBean id="mySmartUpload" scope="page"
   class="com.jspsmart.upload.SmartUpload" />
<script type="text/javascript">     
function go()    
  { 
   parent.FileNameStr(document.getElementById("ip1").value);
   document.getElementById("ip1").outerHTML = document.getElementById("ip1").outerHTML; 
  }
 </script> 
   </head>
  <body onload="go()">
 <form method="post" action="" enctype="multipart/form-data"> 
   
<% 
    //新建一个SmartUpload对象 

    SmartUpload su = new SmartUpload();  
   
    //上传初始化 

    su.initialize(pageContext); 
   
    // 设定上传限制 

    //1.限制每个上传文件的最大长度。 

    su.setMaxFileSize(10000000); 
   
    //2.限制总上传数据的长度。 

    su.setTotalMaxFileSize(20000000); 
   
    //3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。 JPG,JPEG,GIF,BMP,SWF,RMVB,RM,AVI

    su.setAllowedFilesList("jpg,JPG,jpeg,JPEG,gif,GIF,bmp,BMP"); 
        
    boolean sign = true; 
        
    //4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,jsp,htm,html扩展名的文件和没有扩展名的文件。 
    String fileStr="";
    try { 
        su.setDeniedFilesList("exe,bat,jsp,htm,html,SWF,RMVB,RM,AVI"); 
   
        //上传文件 

        su.upload(); 
        //将上传文件保存到指定目录 
        com.jspsmart.upload.File myFile = su.getFiles().getFile(0);
        
         Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
		String dataStr = fmt.format(rightNow.getTime());
		
		String fName ="";
        //fName=myFile.getFileName();
        fName=dataStr+"."+myFile.getFileExt();
        
        // su.save("/images/smallPic/",su.SAVE_VIRTUAL);
          myFile.saveAs("/upload/bigPic/" + fName,mySmartUpload.SAVE_VIRTUAL);
        //su.save("c:\\"+ fileName); 
        fileStr=fName;
        %>
        <input type="text" id="ip1" name="ip1" value="<%=fileStr%>"/>
        <%
  
    } catch (Exception e) { 
        e.printStackTrace(); 
        sign = false; 
    } 
    if(sign==true) 
    { 
       
        out.println("<script>parent.callback('信息提示:文件已上成功传.')</script>"); 
        
    }else 
    { 
        out.println("<script>parent.callback('信息提示:文件上传失败,请检查该文件是否允许上传.')</script>"); 
    } 
%> 
</form>
</body>
</html>