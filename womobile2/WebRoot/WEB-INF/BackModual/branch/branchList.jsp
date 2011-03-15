<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>分店树</title>
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
		//触发按钮事件
		function menuHandler(){
			var src=tree.getSelected().src;
			if(src==null){
				alert("不能操作Root节点");
			}else{
				$("#perentBranchId").val(src.split("&")[0].split("=")[1]);
				$("#parentBranchName").text(tree.getSelected().text);
			}
		}
		
		//添加机构
		function addBranchButton() {
			var selectedNode=tree.getSelected();
			if(selectedNode){
				tree.getSelected().focus();
				$("#perentBranchId").val(tree.getSelected().src.split("&")[0].split("=")[1]);
				$("#parentBranchName").text(tree.getSelected().text);
				$("#branchSet").show() ;
  				$('#branchSet').html("<center>页面载入中...</center>");
  			 	$.get("branch.do",{action:'add',date:new Date()},showResult);
			}else if(selectedNode==null||selectedNode==""){
				alert("请先选择父节点机构!");
			}else{
			 	$("#branchSet").show() ;
  				$('#branchSet').html("<center>页面载入中...</center>");
  			 	$.get("branch.do",{action:'add',date:new Date()},showResult);
  			 }
		}
		function showResult(res){
			$('#branchSet').html(res); 
			$("#perentBranchId").val(tree.getSelected().src.split("&")[0].split("=")[1]);
			$("#parentBranchName").text(tree.getSelected().text);
		}
		
		//添加机构
		function addBranch(){
  			var pars="action=addsave&branchName="+encodeURIComponent($('#branchName').val())+
  				"&perentBranchId="+$('#perentBranchId').val()+"&branchOrder="+$('#branchOrder').val()+"&date="+new Date();
   			$.get("branch.do",pars,addBranchOK);
		}
		function addBranchOK(res){
			$('#messageDiv').hide();
    		$('#messageDiv').html(res);
			var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
  			var codeid = jsonMsgObj.getCodeid();
  			
  			if (codeid == "0") {
  				alert("添加机构成功!");
  			    tree.getSelected().focus();
  				tree.getSelected().add(new WebFXLoadTreeItem($('#branchName').val()+"("+($('#branchOrder').val()==""?0:$('#branchOrder').val())+")","branch.do?perentBranchId="+jsonMsgObj.getMessage()));
  				$("#branchSet").html("");
  				$('#branchSet').hide();
  			}else{
  				alert(jsonMsgObj.getMessage());
  			}
		}
		
		//删除机构
		function delBranchButton() {
			var selectedNode=tree.getSelected();
			if(selectedNode) {
				var id=tree.getSelected().src.split("&")[0].split("=")[1];
				if(id==null){
					alert("请先选择父节点菜单!");
				}
				tree.getSelected().focus();
				if(confirm("是否确认删除“"+tree.getSelected().text+"”")){
					//先判断是否还有下一级的机构，防止一次性把整棵树删了
					var id=tree.getSelected().src.split("&")[0].split("=")[1];
					var check_url="action=check&id="+id+"&date="+new Date();
					$.get("branch.do",check_url,function(data){
   						$('#messageDiv').hide();
			    		$('#messageDiv').html(data);
						var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
			  			var codeid = jsonMsgObj.getCodeid();
			  			if (codeid == "0") {
			  				var pars="action=del&id="+$('#perentBranchId').val()+"&date="+new Date();
   							$.get("branch.do",pars,delMenuOK);
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
		
		<s:hidden name="perentBranchId" id="perentBranchId"></s:hidden>
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
								
								var rti;
								var tree = new WebFXTree("Root");
								tree.add(rti = new WebFXLoadTreeItem("组织机构", "branch.do?perentBranchId=0&date="+new Date()));
								document.write(tree);
								</script>
								
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<input type="button" name="addBranchButton" value="新增"
									 class="btn" onclick="addBranchButton();" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" name="delBranchButton" value="删除"
									 class="btn"  onclick="delBranchButton(this);" />
							</td>
						</tr>
					</table>
					<div id="branchSet"></div>
					<div id="messageDiv"></div>
				</td>
			</tr>
		</table>
	</body>
</html>