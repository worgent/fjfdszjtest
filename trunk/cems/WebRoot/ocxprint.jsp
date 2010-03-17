<%request.setCharacterEncoding("GBK");%>
<%@page contentType="text/html; charset=gbk"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<script language="javascript">
function btn1Clk()
{
	//第一参数为报表模板版本号，第二个参数为报表模板文件，第三个参数为报表文件名称，第四字段为上边距，第五字段为左边距，第六参数为base64的数据
	aa.printdata('1.2','http://172.30.194.123/x.fr3','x',<s:property value="%{search.TOP_MARGIN}"/>,<s:property value="%{search.LEFT_MARGIN}"/>,document.all("RMVIEWER_DATA").value);
	//edit1.value=aa.printxml('1.0','http://172.30.193.28:8080/x.fr3','x',0,0,'http://172.30.193.28:8080/my.xml');

	window.location.href="/net/print.do";
}
function btn2Clk()
{
	//第一参数为报表模板版本号，第二参数为报表模板文件，第三参数为报表文件名称，第四参数为报表文件名称
	edit2.value=aa.reportdesign('1.2','http://172.30.194.123/x.fr3','x',document.all("RMVIEWER_DATA").value);
	
}


</script>
<body onload="btn1Clk();">
<textarea id='RMVIEWER_DATA' style='display:none'>
<s:property value="base64str"/>
</textarea>
<!-- 
codebase="http://172.30.193.28:8080/netprintex.cab#version=1,0,0,0"
<INPUT TYPE="text" NAME="edit1"><input type="button" onclick="btn1Clk()" value="打印"></input>
<INPUT TYPE="text" NAME="edit2"><input type="button" onclick="btn2Clk()" value="设计"></input>
 -->
<br>
<OBJECT id="aa"
	  classid="clsid:40E8496C-E64A-4CC3-A380-99A93EBBF739"
	  width=0
	  height=0
	  hspace=0
	  vspace=0
>
</OBJECT>
<script language="javascript">
 $(document).ready(function(){
	 btn1Clk();
 })
</script>
</body>
