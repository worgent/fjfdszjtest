<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%
request.setAttribute("decorator", "none");
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<script type="text/javascript" src="<%=path%>/js/tinybox.js"></script>
<style type="text/css">
#tinybox{position:absolute; display:none; padding:10px;
 background:#ffffff url(images/preload.gif) no-repeat 50% 50%; 
 border:10px solid #e3e3e3; z-index:2000;}
#tinymask{position:absolute; display:none; top:0; left:0; height:100%; width:100%; background:#000000; z-index:1500;}
#tinycontent{background:#ffffff; font-size:1.1em;}
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

-->
</style>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="<%=path%>/css/images/tab_03.gif" width="15" height="30" /></td>
        <td background="<%=path%>/css/images/tab_05.gif"><img src="<%=path%>/css/images/311.gif" width="16" height="16" /> <span class="STYLE4">事项申报反馈</span></td>
        <td width="14"><img src="<%=path%>/css/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="<%=path%>/css/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
          <tr>
            <td width="6%" height="26" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><a href="javascript:chk_all();">选择</a></div></td>
            <s:iterator id="head" value="%{head}" >  
			<td width="10%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><s:property value="#head.fielddesc"/></div></td>
            </s:iterator>
            <td width="7%" height="18" background="<%=path%>/css/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">操作</div></td>
          </tr>
          
          <s:iterator id="g" value="%{pageList.objectList}" >
          <tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
            <input class="STYLE2" type='checkbox' name='searchpid' id='searchpid' value='<s:property value="#g.id"/>'/>
		    <a href='<%=path%>/work/tdailywork.do?action=view&search.pid=<s:property value="#g.id"/>'  target='_self'> <s:property value="#g.id"/></A>
            </div></td>
	        <!-- #head.fieldname -->		
			<s:iterator id="headname" value="%{head}" >
			<s:set name="fieldname" value="#headname.fieldname"></s:set>
			<s:set name="tablename" value="#headname.tablename"></s:set>
			<s:if test="#headname.fieldtype=='photo'">
                <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><a href="javascript:show('photo<s:property value="#g[id]" />','<s:property value="#g[fieldname]" />')" id="photo<s:property value="#g[id]" />">查看照片</a></div></td>
			</s:if>
			<s:else>
                <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><s:property value="#g[fieldname]" /></div></td>			
			</s:else>
            </s:iterator>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="<%=path%>/css/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="javascript:feedback(<s:property value="#g.id" />,'<s:property value="%{tablename}" />');">回复</a><span class="STYLE1">]</span></div></td>
         </tr>
		 </s:iterator>

        </table></td>
        <td width="9" background="<%=path%>/css/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="<%=path%>/css/images/tab_20.gif" width="15" height="29" /></td>
        <td background="<%=path%>/css/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">
              分页:<qzgf:pages value="%{pageList.pages}"  javaScript="loadDefaultList"/>
            </span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="<%=path%>/css/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>

<script language="javascript" type="text/javascript">	
        function show(id,name){
        	var content = "<img width='640' src=<%=basePath%>FeedbackPhoto/"+name+" />";
        	TINY.box.show(content,0,0,0,1);
        }
        //全选复选框
    	function chk_all(){
			var obj = $(searchpid);
			flag= obj[0].checked;
			if (obj != null){
				if (obj.length > 1){
					for (var i=0; i<obj.length; i++){
						obj[i].checked = !flag;
					}
				}else{
					obj[0].checked = !flag;
				}
			}
		}
		//数据反馈意见
		function feedback(value,tablename){
		  							jPrompt('回复:', ' ', '提示框', function(r){
								    		if (r){
												            $.ajax({
												                url: "<%=path%>/work/tdeclare.do?search.pid="+value+"&search.preplyremark="+encodeURIComponent(r)+"&search.tablename="+tablename,
												                type: "POST",
												                data: { "action":"feedback"},
												                success: function(data) {
												                    //使用eval函数
												                    var json = eval(data);
												                    var result=json[0].value;
												                    if(0<result){
																	    // window.location.href='<%=basePath%>/work/tdailywork.do';
																	    jAlert('回复成功!','提示');  
																	}
																	else{
																    	jAlert('回复未成功!','提示');  
																	}
												                },
												                error : function(){
																     alert("服务器忙");
																}
												            });
											}
										});
		}
		
</script>		


</body>
