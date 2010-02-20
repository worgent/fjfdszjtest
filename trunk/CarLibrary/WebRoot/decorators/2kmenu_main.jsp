<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK" %>
<%@ include file="/common/taglibs.jsp"%>

<html> 
	<head>
		<title>
			<ww:i18n name="'messages'">
				<ww:text name="'main.title'"/>
			</ww:i18n>
		</title>
		<decorator:head/>
	</head>
	<body class="office" style="MARGIN: 0px;border-top: 0px solid #a4b6d7;border-bottom: 0px solid #a4b6d7;" scroll=no>
		<decorator:body/>
	</body>
</html>

<script language="javascript">
    var tabs = new Ext.TabPanel({
        renderTo:'tabs',
        resizeTabs:true, // turn on tab resizing
        minTabWidth: 115,
        tabWidth:135,
        enableTabScroll:true,
        height:550,
        defaults: {autoScroll:true},
        plugins: new Ext.ux.TabCloseMenu()
    });
	
	function closeTab(){
		tabs.on('close', function(){
          			this.tabPanel.close();   
        	});
	}
    function addTab(vTitle, iframeId, src, scrolling){
        tabs.add({
            title: vTitle,
            iconCls: 'tabs',
            html: "<iframe id='"+iframeId+"' name='"+iframeId+"' src='"+src+"' style='width:100%;height:100%;margin: 0 0 0 0' frameBorder='0' scrolling='"+scrolling+"' frameborder='NO' noresize></iframe>",
            closable:true
        }).show();
    }
</script>