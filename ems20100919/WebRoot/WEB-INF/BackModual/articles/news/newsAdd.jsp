<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@ taglib uri="/WEB-INF/FCKeditor.tld" prefix="fck"%>
 <%@  page  import="java.util.*"  %> 
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>

		<s:head theme="ajax" /> 
<script type="text/javascript"
		src="/js/setday.js"></script>
 
<SCRIPT type="text/javascript">
function cataValue(obj)
{
 var titleId=document.getElementById("titleId").value;
 if(titleId=='')
 {
 alert("文章标题不能为空");
 return false;
 }

 var content=document.getElementById("search.content").value;
 if(content=='')
 {
 alert("文章内容不能为空,请填写");
 return false;
 }
 else {
 
 var url ='/news.do?action=addSave';
			document.forms[0].action=url;
			document.forms[0].method="POST";
			document.forms[0].submit();
			
    
}
 }
 
 function cataReturn(obj)
{
            var url ='/news.do?action=index';
			document.forms[0].action=url;
			document.forms[0].method="POST";
			document.forms[0].submit();
			
 
}
 
 </script>


	</head>
	 <body><!-- 装饰器屏蔽了background -->
	 <form  method="post" >
	 <center>
	 <table border="0" cellpadding="0" style="border-collapse: collapse"  id="table3" height="648" bordercolor="#FFFFFF" cellspacing="0">
		<tr>
			<td valign="top">

			<table border="0" cellpadding="0" style="border-collapse: collapse" width="650" id="table6" height="272">
				<tr>
					<td width="884" valign="top" bgcolor="#E5E5E5">
					
					<div align="center">
						<table border="0" width="687" id="table50" cellpadding="0" style="border-collapse: collapse">
							<tr>
								<td>
								<table border="0" cellpadding="0" style="border-collapse: collapse" width="687" id="table54" background="images/nei01.jpg" height="34">
									<tr>
										<td width="38">　</td>
										<td align="left">
										<font color="#FFFFFF" style="font-size: 10pt">
										<span style="font-weight: 700"><strong> <font color="#ff0000">添加文章</font>
					</strong></span></font><br></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td   background="images/nei02.jpg">
								<table border="0" cellSpacing="0" cellPadding="0" width="100%" align="center" id="table52">
									
									<tr>
										<td height="12">
										<div align="center">
			<table  class="tablesorter" align="center"
			border="0" cellpadding="0" cellspacing="1" width="100%">
						<tr class="trClass">
							<td colspan="3" bgcolor="#009CD6"
								background="../../images/newsystem/th2.gif" class="txt_b"
								align="center"> 
								文章发布	<br></td>
						</tr>
						<tr>
							<td class="main"  align="left" width="60"> 
								文章类别： 
							</td>
							<td class="main"  colspan="2" align="left">
					         <select id="articlestype" name="search.articlestype">
					            <option value="1"> 企业品牌 </option>
					            <option value="2"> 企业文化</option>
					            <option value="4"> 福建EMS</option>
					            <option value="5"> 行业动态</option>
					            <option value="6"> 公 告</option>
					         </select>
							</td>
						</tr>

						<tr>
					 <td class="main" align="left">文章标题: 
					 </td>
					<td class="main"  colspan="2" align="left">
						<input type="text" id="titleId" name="search.title"      size="50" /><font color="#ff0000">*</font>
					</td>
				    </tr>
				    
				    	<tr>
					 <td class="main" align="left">文章来源: 
					 </td>
					<td class="main"  colspan="2" align="left">
						<input type="text" id="sourceId" name="search.source"      size="50" />
					</td>
				    </tr>
				    
				    	<tr>
					 <td class="main" align="left">作 者: 
					 </td>
					<td class="main"  colspan="2" align="left">
						<input type="text" id="authorID" name="search.author"      />
					</td>
				    </tr>
					
						<tr>
					<td class="main" align="left" height="150">
						文章内容:
					</td>
					<td class="main" colspan="2" align="left">
			      <fck:editor  id="search.content"  
                  basePath="/fckeditor/"   
                  height="600"   
                  skinPath="/fckeditor/editor/skins/default/"   
                  toolbarSet="Default"   
                  imageBrowserURL="/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"   
                  linkBrowserURL="/fckeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"   
                  flashBrowserURL="/fckeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"   
                  imageUploadURL="/fckeditor/editor/filemanager/upload/simpleuploader?Type=Image"   
                  linkUploadURL="/fckeditor/editor/filemanager/upload/simpleuploader?Type=File"   
                  flashUploadURL="/fckeditor/editor/filemanager/upload/simpleuploader?Type=Flash">
                  </fck:editor>   
				            
   
				    <!--上面的URL要根据FCKeditor放置在项目中的位置做相应的修改  
       此处假定将FCKeditor解压包中的editor目录直接放在项目的WebRoot下，fckeditortest是项目名  -->	    
					
					<!--
						<textarea id="shopIntroId" name="productIntro"
							style="width: 560; height: 250; overflow-x: visible; overflow-y: visible;"></textarea>
							 -->
					</td>
				</tr>
				
				
						<tr>
					<td class="main" align="left"> 
						是否审核: 
					</td>
					<td class="main" colspan="2" align="left">
						<input type="checkbox" id="search.pisaudit" name="search.pisaudit" checked  value="1"/><font  color="#ff0000" size="2">（如果选中的话将直接发布）</font>
					</td>
				</tr>
						<tr>
					<td class="main" align="left">&nbsp; 
						录入时间: 
					</td>
					<td class="main" colspan="2" align="left">
							
				<%    
Calendar  calendar=Calendar.getInstance();  
   int  year=calendar.get(Calendar.YEAR);  
   int  month=calendar.get(Calendar.MONTH)+1;  
   int  day=calendar.get(Calendar.DATE);  
     String  date=year+"-"+month+"-"+day;
 %>
				<input type="text" id="search.releasetime" name="search.releasetime"
							value="<%=date%>"
							onclick="setday(this)" readonly size="12" />&nbsp;

					</td>
				</tr>
						
				
				
				</table>
		
				
		<table  width=99%  class="tbl">
    <tr></tr>
    			
 
    			
          <tr>
							<td colspan="2" class="main" align="center">
							
							
					        <input type="button"   onclick="javascript:cataValue(this);" value="保 存" />		
							<input type="button"   onclick="javascript:cataReturn(this);" value="返 回" />
							<input value="重 置"  type="reset"  name="search"/>
							
									</td>
						</tr>

</table>		
				
			
		
										
										
										
										
										
										
										</div>
									
										<p style="LINE-HEIGHT: 150%; MARGIN: 0cm 0cm 0pt" class="MsoNormal">
										<b>
										<span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt">
										</span></b><span id="TabSkinContainer48"><span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent3" class="Normal"></span></span><b><span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt"><br></span></b></p>
										
										<p style="LINE-HEIGHT: 150%; MARGIN: 0cm 0cm 0pt" class="MsoNormal">
										<b>
										<span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt">
										</span></b><span id="TabSkinContainer49"><span id="ess_ctr1622_HtmlModule_HtmlModule_lblContent4" class="Normal"></span></span><b><span style="LINE-HEIGHT: 150%; FONT-FAMILY: 宋体; COLOR: #cc0000; FONT-SIZE: 10pt"><br></span></b></p>
										
									

										<p style="text-align: left; line-height: 150%; margin-left: 0cm; margin-right: 0cm; margin-top: 0cm; margin-bottom: 0pt" class="MsoNormal" align="left">　</p>
										<br>
　<p style="text-align: center; line-height: 200%; text-indent: 18.05pt; margin-left: 0cm; margin-right: 0cm; margin-top: 0cm; margin-bottom: 0pt" class="MsoNormal" align="center">
										<b>
										<span style="line-height: 200%; font-family: 宋体; color: #333333; font-size: 9pt">
										详情请咨询<font face="宋体" color="#333333"><span lang="EN-US">“</span>在线客服<span lang="EN-US">”</span>或当地客服热线：</font></span></b><span lang="en-us"><b><font face="宋体" style="font-size: 9pt" color="#333333">11185</font></b></span></p>
										&nbsp; </td>
									</tr>
								</table>
								
							</td>
							</tr>
							<tr>
								<td>
								<img border="0" src="images/nei03.jpg" width="687" height="4"></td>
							</tr>
						</table>
						<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table51">
							<tr>
								<td>　</td>
							</tr>
						</table>
					</div>
					</td>
				</tr>
			</table>
			
			
			
			
			
			
			
			
			
			
			
			<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%" id="table10" background="images/di.jpg" height="27">
		     <tr>
					<td valign="middle"><span style="font-size: 4pt"><br>
					</span><span style="font-size: 9pt">&nbsp;<font color="#FFFFFF">All 
					rights reserved. 2010&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					技术支持 by </font><font color="#02687F">泉州市冠发信息科技有限公司</font></span></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</center>
</form>
</body>
</html>
