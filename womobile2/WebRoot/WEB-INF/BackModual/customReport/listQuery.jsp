<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="webframe"%>
<%
	request.setAttribute("decorator", "none");
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script type="text/javascript" src="js/tinybox.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js" defer="defer"></script>
<style type="text/css">
#tinybox{position:absolute; display:none; padding:10px;
 background:#ffffff url(images/preload.gif) no-repeat 50% 50%; 
 border:10px solid #e3e3e3; z-index:2000;}
#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
#tinycontent{background:#ffffff; font-size:1.1em;}
</style>
<form action="/customReport.do?action=queryReport" method="post">
<table border="0" cellpadding="0" align="center" style="border-collapse: collapse" width="100%" id="table15" height="30">
    	<tr>
    		<td colspan="4" height="50" style="vertical-align: middle;text-align: center">
    		<strong><font size="4">${reportName}</font></strong>
    		<s:hidden name="parentType"></s:hidden>
    		<s:hidden name="reportId"></s:hidden>
    		</td>
    	</tr>
	    <s:iterator id="fl"  status="st" value="%{fieldList}">
            <s:if test="#st.getIndex()%2==0">
	    		<tr>
            </s:if>
				<td  width="10%" style="font-size:12px" align="right"><s:property value="#fl.fielddesc" />:</td>
				<td width="40%" nowrap="nowrap">
					<s:if test="#fl.fieldtype=='date'">
					<s:hidden name="dayMap1.%{fieldname}"/>
					<s:textfield name="dayMap2.%{fieldname}start"  
					cssClass="Wdate"  onclick="WdatePicker()" ></s:textfield>至<s:textfield name="dayMap2.%{fieldname}end"  
					cssClass="Wdate"  onclick="WdatePicker()" ></s:textfield>
					</s:if>
					<s:elseif test="#fl.fieldtype=='enum'">
						<webframe:select pid='#fl.fieldenum' name="#fl.fieldname"/>
					</s:elseif> 
					<s:else>
						<s:textfield name="query.%{fieldname}"></s:textfield>
					</s:else> 
				</td> 
				 <s:if test="#st.last&&#st.getIndex()%2==0">
                   <td>&nbsp;</td><td>&nbsp;</td>
            	 </s:if>
			<s:if test="#st.getIndex()%2==1">
				</tr>
			</s:if>
		</s:iterator>
		<tr>
		    <td colspan="2" align="right"><input type="submit" value="查询" class="btn"/>&nbsp;&nbsp;</td><td colspan="2">&nbsp;&nbsp;<input type="reset" value="重置" class="btn"/></td>
		</tr>
		
</table>
<hr/>
<s:if test="%{pageList.pages!=null&&pageList.pages.allPage!=0}" >
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
    	<td>
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td width="15" style="vertical-align:middle;"><img src="<%=path%>/css/images/tab_03.gif" width="15" /></td>
        		<td background="<%=path%>/css/images/tab_05.gif"><img src="<%=path%>/css/images/311.gif" width="16" /> <span class="STYLE4">${reportName}</span></td>
        		<td width="14"><img src="<%=path%>/css/images/tab_07.gif" width="14"  /></td>
      		</tr>
    	</table>
    	</td>
   </tr>
   
   <tr>
   	<td>
   		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
	        <td bgcolor="e5f1d6">
	        	<table width="99%" id="customtableid" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
	          		<tr>
						 <s:iterator id="rfl" value="%{resultFieldList}" status="s">
							<td width="12%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><s:property value="#rfl.fielddesc"/></div></td>
						
						</s:iterator>
	           		</tr>
	                <s:iterator id="rl" status="status" value="%{pageList.objectList}">
				    <tr>
					    <s:iterator id="rfl"  value="%{resultFieldList}">
						<td  nowrap="nowrap" height="18" bgcolor="#FFFFFF" class="STYLE2">
							<div align="center" class="STYLE2 STYLE1">
							<s:if test="#rfl.fieldtype=='enum'">
								<s:property value="#rl[#rfl.fieldname+'val']"/> 
							</s:if>
							<s:elseif test="#rfl.fieldtype=='date'">
								<s:date name="#rl[#rfl.fieldname]" format="yyyy-MM-dd"/>
							</s:elseif>
							<s:elseif test="#rfl.fieldtype=='photo'&&#rl[#rfl.fieldname]!=''&&#rl[#rfl.fieldname]!=null">
								<a href="javascript:show('photo<s:property value="#status.count" />','<s:property value="#rl[#rfl.fieldname]" />')" id="photo<s:property value="#status.count" />">查看照片</a> 
							</s:elseif>
							<s:else>
								<s:property value="#rl[#rfl.fieldname]" /> 
							</s:else>
							&nbsp;
							</div>
						</td>
				        </s:iterator>
				    </tr>
			        </s:iterator>
	            </table>
	         </td>
	        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
	      </tr>
	    </table>
   	</td>
   </tr>
   <tr>
   	<td height="29">
   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">
              <s:if test="%{pageList.pages!=null&&pageList.pages.allPage!=0}" > 
           		分页:<webframe:pages value="%{pageList.pages}" />
         	  </s:if>&nbsp;
            </span></div></td>
            <td width="30%" class="STYLE1" align="right">
            <s:if test="%{pageList.pages!=null&&pageList.pages.allPage!=0}" >
	        	<a href="#">打印</a>
        	</s:if>&nbsp;
            </td>
            <td width="30%" class="STYLE1" >
            <s:if test="%{pageList.pages!=null&&pageList.pages.allPage!=0}" >
	        	<a href="${exportUrl}">导出xls</a>
        	</s:if>&nbsp;
            </td>
          </tr>
        </table>
        </td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table>
    </td>
   		
   		
   </tr>		
</table>
</s:if>
<s:if test="%{pageList.pages!=null&&pageList.pages.allPage==0}" >
	<table width="50%">
		<tr>	
	        <td>没有相应的数据!</td>
	    </tr>
	</table>
</s:if>
</form>
<script type="text/javascript">
        function show(id,name){
        	var content = "<img width='640' src=<%=basePath%>FeedbackPhoto/"+name+" />";
        	TINY.box.show(content,0,0,0,1);
        }
</script>