<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>

<%@ include file="/common/taglibs.jsp"%>

<head>
	<script>
		Ext.onReady(function(){

       		Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

	       	var viewport = new Ext.Viewport({
	            layout:'border',
	            items:[{
	                region:'center',
	                layout:'column',
	                autoScroll:true,
	                items:[
	                {
	                	columnWidth:.71,
	                    baseCls:'x-plain',
	                    bodyStyle:'padding:5px 0 5px 5px',
	                    items:[{
	                    	collapsible:true,
	                        title: "<a onclick=\"parent.addTab('已接收未处理的短信', 'InceptSmsManage', '/system/sms/inceptSms.shtml')\" href='javascript:void(0)'>已接收未处理的短信</a>",
	                        html:"<iframe id='warningLogIframe' name='warningLogIframe' src='/ippfManage/baseInfo/BaseInfo.shtml?actionType=toAddPage' width='100%' height='100%' frameborder=0/>" ,
                            }]
	                }
	                ]
	            }]
	        });
    	});
	</script>
</head>

<body>
</body>