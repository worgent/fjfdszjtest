<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<link href="<%=path%>/css/mapFortune.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/css1.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/pageList.js"></script>
	</head>
	<body>
		<div id="f_bg">
			<div id="f_tabs">
				<ul>
					<li id="tab1" class="f_tabClass1">
						<a href="javascript:;" onclick="loadDefaultList('<%=path%>/net/order.do?action=listdetail&search.pbill_type=0',1,3);">等待寄件</a>
					</li>
					<li id="tab2" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultList('<%=path%>/net/order.do?action=listdetail&search.pbill_type=1',2,3);">其他</a>
					</li>
					<li id="tab3" class="f_tabClass2">
						<a href="javascript:;" onclick="loadDefaultList('<%=path%>/net/order.do?action=listdetail',3,3);">全部</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="f_main">
			<div id="defaultlist"></div>
		</div>
<script language="javascript" type="text/javascript">	
		    //加载默认页面    
		    $(document).ready(function(){
		    	loadDefaultList('<%=path%>/net/order.do?action=listdetail&search.pbill_type=0',1,3);
		    });
</script>		    		
	</body>
</html>