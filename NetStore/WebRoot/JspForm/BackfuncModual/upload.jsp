<%@ page language="java" contentType="text/html; charset=gbk"%> 

<%@ page pageEncoding="gbk"%> 

<%@ page import="com.jspsmart.upload.*" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> 

<html> 

<head> 

<meta http-equiv="Content-Type" content="text/html; charset=gbk"> 

<title>上传处理 </title> 

</head> 

<body> 

<% 

SmartUpload su=new SmartUpload(); 

//初始化 

su.initialize(pageContext); 

//上传 

//su.setAllowedFilesList("jpg,gif,bmp"); 

su.upload(); 

// 设置保存信息 

String dir="upload"; 


//获取上传文件列表集合 

Files files=su.getFiles(); 

for(int i=0;i <files.getCount();i++) 
{ 

File file=files.getFile(i); 

//判断上传的是不是文件 

if(!file.isMissing()) 
{ 

//将获取的图片另存为文件名为new，后缀名从原是文件中获得 
String str=file.getFileExt(); 
out.println(str); 
file.saveAs(dir+"/new."+str,su.SAVE_VIRTUAL); 
//file.saveAs(dir+"/new."+str); 

       ServletContext   servletContext   =   pageContext.getServletContext();   
       String   realPath   =   servletContext.getRealPath("/");
          
       out.print(realPath);
//获取图片的客户端路径名 

String name1=file.getFilePathName(); 

out.print("客户机原始路径名: "); 

out.print(name1); 

out.print(" <br>"); 

out.print("服务器上的相对路径名: "); 

//构造服务器上的相对路径名 

String name2=realPath+"/upload"+"/new."+str; 

out.print(name2); 

out.print(" <br>"); 

%> 

<!-- 将上传后的图片发布 --> 

<img src=" <%=name2 %>" width="200"> 

<% 

} 

} 

%> 

<!-- 获取传过来的非文件参数值 --> 

<%=su.getRequest().getParameter("title") %> 

<br> 

<%=su.getRequest().getParameter("content") %> 

<br> 

<br> 

</body> 

</html> 
