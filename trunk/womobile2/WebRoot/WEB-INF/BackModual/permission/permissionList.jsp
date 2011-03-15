<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>权限菜单</title>
		<link href="css/admin.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/comm.js"></script>
		<script type="text/javascript" src="js/xtree.js"></script>
		<script type="text/javascript" src="js/xmlextras.js"></script>
		<script type="text/javascript" src="js/xloadtree.js"></script>
		<link type="text/css" rel="stylesheet" href="css/xtree.css" />
		<style type="text/css">

		body {
			background:	white;
			color:		black;
		}
		
		</style>
		<script language="JavaScript" type="text/javascript">
		//触发菜单事件
		function menuHandler(){
			var src=tree.getSelected().src;
			if(src==null){
				alert("不能操作Root节点");
			}else{
				$("#perentPermissionId").val(src.split("&")[0].split("=")[1]);
				$("#parentPermissionName").text(tree.getSelected().text);
			}
		}
		
		//添加菜单
		function addMenuButton() {
			var selectedNode=tree.getSelected();
			if(selectedNode){
				tree.getSelected().focus();
				$("#perentPermissionId").val(tree.getSelected().src.split("&")[0].split("=")[1]);
				$("#parentPermissionName").text(tree.getSelected().text);
				$("#menuSet").show() ;
  				$('#menuSet').html("<center>页面载入中...</center>");
  			 	$.get("permission.do",{action:'add',date:new Date()},showResult);
			}else if(selectedNode==null||selectedNode==""){
				alert("请先选择父节点菜单!");
			}else{
			 	$("#menuSet").show() ;
  				$('#menuSet').html("<center>页面载入中...</center>");
  			 	$.get("permission.do",{action:'add',date:new Date()},showResult);
  			 }
		}
		function showResult(res){
			$('#menuSet').html(res); 
			$("#perentPermissionId").val(tree.getSelected().src.split("&")[0].split("=")[1]);
			$("#parentPermissionName").text(tree.getSelected().text);
		}
		
		//添加菜单
		function addMenu(){
			var isMenu=0;
			var isMenus = document.getElementsByName("isMenu"); 
			for (i = 0; i < 2; i++) { 
			if (isMenus[i].checked) 
				isMenu=i;
			} 
  			var pars="action=addsave&permissionName="+encodeURIComponent($('#permissionName').val())+
  				"&perentPermissionId="+$('#perentPermissionId').val()+"&resource="+$('#resource').val()+
  				"&actionName="+$('#actionName').val()+"&isMenu="+isMenu+"&permissionOrder="+$('#permissionOrder').val()+"&date="+new Date();
   			$.get("permission.do",pars,addMenuOK);
		}
		function addMenuOK(res){
			$('#messageDiv').hide();
    		$('#messageDiv').html(res);
			var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  			var codeid = jsonMsgObj.getCodeid();
  			
  			if (codeid == "0") {
  				alert("添加菜单成功!");
  			    tree.getSelected().focus();
  				tree.getSelected().add(new WebFXLoadTreeItem($('#permissionName').val()+"("+($('#permissionOrder').val()==""?0:$('#permissionOrder').val())+")","permission.do?perentPermissionId="+jsonMsgObj.getMessage()));
  				$("#menuSet").html("");
  				$('#menuSet').hide();
  			}else{
  				alert(jsonMsgObj.getMessage());
  			}
		}
		
		//删除菜单
		function delMenuButton() {
			var selectedNode=tree.getSelected();
			if(selectedNode) {
				var id=tree.getSelected().src.split("&")[0].split("=")[1];
				if(id==null){
					alert("请先选择父节点菜单!");
				}
				tree.getSelected().focus();
				if(confirm("是否确认删除“"+tree.getSelected().text+"”")){
					//先判断是否还有下一级的菜单，防止一次性把整棵树删了
					var id=tree.getSelected().src.split("&")[0].split("=")[1];
					var check_url="action=check&id="+id+"&date="+new Date();
					$.get("permission.do",check_url,function(data){
   						$('#messageDiv').hide();
			    		$('#messageDiv').html(data);
						var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
			  			var codeid = jsonMsgObj.getCodeid();
			  			if (codeid == "0") {
			  				var pars="action=del&id="+$('#perentPermissionId').val()+"&date="+new Date();
   							$.get("permission.do",pars,delMenuOK);
			  			}else{
			  				alert(jsonMsgObj.getMessage());
			  				return false;
			  			}
  					});
					
   				}else{
   					return false;
   				}
				
			}else if(selectedNode==null||selectedNode==""){
				alert("请先选择父节点菜单!");
			}
		}
		
		//删除成功
		function delMenuOK(res){
			$('#messageDiv').hide();
    		$('#messageDiv').html(res);
			var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  			var codeid = jsonMsgObj.getCodeid();
  			alert(jsonMsgObj.getMessage());
  			if (codeid == "0") {
  				tree.getSelected().remove();
  			}
		}
		
		
		</script>
	</head>
	<body>
		<p>
			&nbsp;
		</p>
		
		<s:hidden name="perentPermissionId" id="perentPermissionId"></s:hidden>
		<table width="90%" border="0" align="center" cellpadding="10"
			cellspacing="0" class="table1">
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="5" cellspacing="0">
						<tr>
							<td nowrap="nowrap">
								<script type="text/javascript">

								/// XP Look
								webFXTreeConfig.rootIcon		= "css/images/xp/folder.png";
								webFXTreeConfig.openRootIcon	= "css/images/xp/openfolder.png";
								webFXTreeConfig.folderIcon		= "css/images/xp/folder.png";
								webFXTreeConfig.openFolderIcon	= "css/images/xp/openfolder.png";
								webFXTreeConfig.fileIcon		= "css/images/xp/file.png";
								webFXTreeConfig.lMinusIcon		= "css/images/xp/Lminus.png";
								webFXTreeConfig.lPlusIcon		= "css/images/xp/Lplus.png";
								webFXTreeConfig.tMinusIcon		= "css/images/xp/Tminus.png";
								webFXTreeConfig.tPlusIcon		= "css/images/xp/Tplus.png";
								webFXTreeConfig.iIcon			= "css/images/xp/I.png";
								webFXTreeConfig.lIcon			= "css/images/xp/L.png";
								webFXTreeConfig.tIcon			= "css/images/xp/T.png";
								
								//var tree = new WebFXLoadTree("WebFXLoadTree", "tree1.xml");
								//tree.setBehavior("classic");
								
								//*****
								//var tree = new WebFXTree("ROOT");
								//tree.add(new WebFXLoadTreeItem("系统菜单", "permission.do?perentPermissionId=0&date="+new Date()));
								
								//document.write(tree);
								//****
								
								var rti;
								var tree = new WebFXTree("Root");
								tree.add(rti = new WebFXLoadTreeItem("系统菜单", "permission.do?perentPermissionId=0&date="+new Date()));
								document.write(tree);
								</script>
								
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<input type="button" name="addMenuButton" value="添加"
									 class="btn"  onclick="addMenuButton();" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" name="delMenuButton" value="删除"
									 class="btn"  onclick="delMenuButton(this);" />
							</td>
						</tr>
					</table>
					<div id="menuSet"></div>
					<div id="messageDiv"></div>
				</td>
			</tr>
		</table>
	</body>
</html>