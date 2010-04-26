<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<script type='text/javascript' src='<%=basePath%>js/SimpleHtmlTree.js'></script>
<script type='text/javascript' src='<%=basePath%>js/SimpleHtmlAjax.js'></script>
<style>
input {
	font-size: 12px;
	border: 1 solid #000000;
	background-color: #FFFFFF;
}
</style>
	<body>
		<table border="1" width="100%" cellpadding="0" cellspacing="0" height="426">
			<tr>
			    <!-- 树的存放 -->
				<td width="29%" height="80%" valign="top">
					<div id="treepanel1"></div>
				</td>
				<div id="treepanel1"></div>
				<td width="71%" valign="top">
					<div></div>
					<div id="xmnr">
						<input name="newnodename"  id="newnodename" type="text" value="" />	
					</div>
					<div>
						<input type="button" name="addchild" onclick="addchildfu()" value="添加子项" />
						<input type="button" name="save" onclick="saveNode()" value="保存修改" />
						<input type="button" name="del" onclick="delNode()" value="删除节点" />

					</div>
				</td>

			</tr>
		</table>


		

	<script  language="JavaScript">
		//var jq = jQuery.noConflict(); 
	    //定义树型对象 
        var simpleTree = new SimpleHtmlTree("treepanel1","<%=basePath%>js/images/mn/","<%=basePath%>js/images/style0/");
        //新增根结点
        var noderoot=simpleTree.createTreeNode("0","商家类别","D");//参数1：节点ID，参数2：节点文本  
        simpleTree.add(noderoot);
        //动态加载xml文件生成树形结构
  		simpleTree.loadXmlFromFile("<%=basePath%>selfconfig/mapcard.do?action=getTree",true,noderoot,"",load,false);
  		function load(){alert("auto loading...");}
   		 
	   		//删除节点     
	        function delNode(){   
	           try{
		            //ajax脚本开始------------------------------------------ 
		            var curnode=simpleTree.getCurrentNode();
		            if(curnode==null)
		            {
		           		 jAlert('请选择一个结点后,进行操作!');  
		           		 return;
		            }
		            var curid=curnode.id; 
		            var ajaxTool=new SimpleHtmlAjax();  
		            ajaxTool.work("POST","<%=basePath%>selfconfig/mapcardsort.do?action=delete&search.pid="+curid,"action=delete&search.pid="+curid,getxml,false);
		            function getxml(text){
		            	  	var xmlObj=ajaxTool.getXmlObject(false);
							xmlObj.loadXML(text);
							var childs=ajaxTool.getXmlDocument(xmlObj);
							if(childs.childNodes[0].text!=0){
							   window.location.href='<%=basePath%>selfconfig/mapcardsort.do';
							}
							else{
							    jAlert('删除失败!');  
							}
		            }
	           } catch(e) {   
	            	alert(e.message);   
	       	   }   
	        }

		   //添加 子目录   
		    function addchildfu(){   
		        try{   
		            var newnode = $("newnodename").value; 
					//ajax脚本开始------------------------------------------ 
		            var curnode=simpleTree.getCurrentNode();
		            if(curnode==null)
		            {
		           		 jAlert('请选择一个结点后,进行操作!');  
		           		 return;
		            }
		            var curid=curnode.id; 
		            var ajaxTool=new SimpleHtmlAjax();  
		            ajaxTool.work("POST","<%=basePath%>selfconfig/mapcardsort.do?action=insert&search.psortname="+newnode+"&search.pbelongid="+curid,null,getxml,false);
		            function getxml(text){
		            	  	var xmlObj=ajaxTool.getXmlObject(false);
							xmlObj.loadXML(text);
							var childs=ajaxTool.getXmlDocument(xmlObj);
							if(childs.childNodes[0].text!=0){
							   window.location.href='<%=basePath%>selfconfig/mapcardsort.do';
							}
							else{
							    jAlert('删除失败!');  
							}
		            }
		   			//ajax脚本结束-------------------------------------     
		        } catch(e) {   
		            alert(e.message);   
		        }   
		    }      
       
	        //保存节点   
	        function saveNode(){   
	  		            var newnode = $("newnodename").value; 
						//ajax脚本开始------------------------------------------ 
			            var curnode=simpleTree.getCurrentNode();
			            if(curnode==null)
			            {
			           		 jAlert('请选择一个结点后,进行操作!');  
			           		 return;
			            }
			            var curid=curnode.id; 
			            var ajaxTool=new SimpleHtmlAjax();  
			            ajaxTool.work("POST","<%=basePath%>selfconfig/mapcardsort.do?action=update&search.psortname="+newnode+"&search.pid="+curid,null,getxml,false);
			            function getxml(text){
			            	  	var xmlObj=ajaxTool.getXmlObject(false);
								xmlObj.loadXML(text);
								var childs=ajaxTool.getXmlDocument(xmlObj);
								if(childs.childNodes[0].text!=0){
								   window.location.href='<%=basePath%>selfconfig/mapcardsort.do';
								}
								else{
								    jAlert('删除失败!');  
								}
			            }
			   			//ajax脚本结束-------------------------------------    
	        }
</script>
	</body>
</html>
