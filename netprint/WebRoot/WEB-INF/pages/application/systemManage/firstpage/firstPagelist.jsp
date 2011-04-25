<%@ page language="java"
	import="java.util.List,java.util.Iterator,com.opensymphony.xwork.util.OgnlValueStack,java.util.HashMap"
	errorPage="/error.jsp" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<head>
	<style type="text/css">
.x-panel-body p {
	margin: 10px;
}

#container {
	padding: 10px;
}
</style>

</head>


<script language="javascript">   
<tt:authority value="EditFirstPage">	
     Ext.onReady(function(){
		 new Ext.Button({
		        text: '管理公告',
		        handler: function(){
		        	parent.addTab('公告信息','addFirst','/system/firstPage/FirstPage.shtml?actionType=firstPageManage','NO');
		        }
		    }).render(document.all.addPanel);
		})
</tt:authority>		
	
	
</script>
<link rel="stylesheet" href="/css/css.css" type="text/css" />


<body>
	<center>
		<div id="addPanel" style="margin: 0px; width: 100px;"></div>
	</center>
	<br>
	<table width="95%" class="tbl" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td height="30" background="/images/top1_14.jpg" class="txt_b">
				<font size="4"><strong>公告信息</strong> </font>
			</td>
		</tr>

		<tr>
			<td>

				<div align="left" style="line-height: 21px">
					<marquee id="affiche" behavior="scroll" direction="up" height="225"
						width="100%" hspace="0" vspace="10" loop="-2" scrollamount="1"
						scrolldelay="10" onmouseout="this.start()"
						onmouseover="this.stop()">
						<%
	    OgnlValueStack ov = (OgnlValueStack) request.getAttribute("webwork.valueStack");       
		List nyList = (List) ov.findValue("search.contentList");      
		
	 %>
						<%
		      Iterator it = nyList.iterator(); 
              while(it.hasNext()) {
                  HashMap map=new HashMap();
                  map =(HashMap) it.next(); 
                 
              %>
						&nbsp;
						<font color="#ff0000"><%=map.get("noticeContent") %></font>

						<%
              }
		      %>

					</marquee>


				</div>
			</td>
		</tr>
	</table>

</body>