<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	


		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>55
		</title>
	

	    	<link rel="StyleSheet" href="<%=request.getContextPath() %>/css/dtree.css" type="text/css" />   
            <script type="text/javascript" src="<%=request.getContextPath() %>/js/dtree.js"></script>   
           
           
       <script type="text/javascript">
       
        function addCateTop(obj){
        
         var cateAdd=document.getElementById("CATEGORIESNAMETop").value;
	     if(cateAdd==''){
	      alert("要添加的顶点名称不能为空!"); 
	     }
	     else
	     {
	   
        if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=addCateTop&search.CID='+document.getElementById("FID").value; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=addCateTop&search.CID='+document.getElementById("FID").value;	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		}
        
        }
       
       
       
       
       
	     function addCate(obj){ 
	     var cateAdd=document.getElementById("CATEGORIESNAME").value;
	     if(cateAdd==''){
	      alert("要添加的子节点名称不能为空!"); 
	     }
	     else
	     {
	   
        if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=addCate&search.CID='+document.getElementById("FID").value; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=addCate&search.CID='+document.getElementById("FID").value;	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		}
		}
		
		  function modifyCate(obj){ 
		  
		 var cateAdd=document.getElementById("MCATEGORIESNAME").value;
	     if(cateAdd==''){
	      alert("要修改的节点名称不能为空!"); 
	     }
	     else
	     {
	   
        if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=modifyCate&search.CID='+document.getElementById("FID").value; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=modifyCate&search.CID='+document.getElementById("FID").value;	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		}
		  
	  }
		
		
	
		
		
		
		
		 function fun_delete(USERID){
		 
		  if (!confirm('您确定删除当前选中的节点吗？')){
				return;
			}else{
			
		 var url ='/user.do?action=deleteCateExist&search.CID='+document.getElementById("FID").value;
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url); 
						
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return ;
						} else {
							root = oXMLDom.documentElement;
						}
						if (null != root){
							var rowSet = root.selectNodes("//check");
							var mgr=rowSet.item(0).selectSingleNode("value").text;
							if(mgr!=0){
							alert(mgr);
							return false;
							}
							else
							{
						
		
		  if(document.forms[0].attributes[83]){
		document.forms[0].attributes[83].value='/user.do?action=deleteCate&search.CID='+document.getElementById("FID").value; //ie7支持用attributes[83]
		}else{
		document.forms[0].action='/user.do?action=deleteCate&search.CID='+document.getElementById("FID").value;	//alert('firefox');firefox浏览器用action
		}
		document.forms[0].submit();	
		
		
		
		
							}
								
						}
					}catch(e){
						alert(e);
					}
			return true;
		
		}
		}
	</script>
	
           
           
           
	</head>
	<body>
	<form  method='POST' name='form1' action='/user.do?action=addCate'  id="form1">
	
	
	<table border="1" width="100%"><tr><td width="22%">
	<div class="dtree">
    <p><a href="javascript: d.openAll();">展 开</a> | <a href="javascript: d.closeAll();">收 缩</a></p>
    <script type="text/javascript">   
        d = new dTree('d');
        d.add(0,-1,'商业分类');  
        
        <s:iterator id="prod" value="%{categoriesList.objectList}">
        d.add(<s:property value="#prod.CATEGORIESID" />,<s:property value="#prod.FID" />,
        '<s:property value="#prod.CATEGORIESNAME" />','user.do?action=categories&search.CID='+<s:property value="#prod.CATEGORIESID"/>);    
         </s:iterator>
        document.write(d);
    </script> 
    </div>   
	</td>
	
	<td valign="top">
	  <table border=""1 width="582" height="167">
	    <tr>
	   <td  nowrap="nowrap"><font color="#ff0000"><strong>添加顶级节点：</strong></font></td>
	   <td>名称:</td>
	  
	  
	  <td>
	  <input  type="text"  id="CATEGORIESNAMETop" name="search.CATEGORIESNAMETop" size="15"/> 
	 
	  </td>
	  
	  <td><input  type="button"  id="cateaddbtn" name="cateAddNamebtn" onclick="javascript:addCateTop(this);" value="添加"/> 
  </td>
	  
	
	  </tr>
	  
	  
	  
	   <tr>
	   <td  nowrap="nowrap"><font color="#ff0000"><strong>当前节点：</strong></font></td>
	   <td>名称:</td>
	  
	  
	  <td>
	 <s:property value="search.CATEGORIESNAME"/>
	  <input type="hidden" id="FID" name="search.FID" value="<s:property value="search.CATEGORIESID" />">  
	 
	  </td>
	  
	  <td>&nbsp;<br> 
  </td>
	  
	 
	  </tr>
	  
	  
	  
	  <tr>
	   <td><font color="#ff0000"><strong>添加子节点：</strong></font></td>
	  <td nowrap="nowrap"><font color="#0000ff">名称</font></td>
	  
	  
	  <td>
	  <input  type="text"  id="CATEGORIESNAME" name="search.CATEGORIESNAME"  size="15"/> 
	  </td>
	  
	  <td nowrap="nowrap">
	   <input  type="button"  id="cateaddbtn" name="cateAddNamebtn" onclick="javascript:addCate(this);" value="添加"/> 
    
    </td>
	  

      
	  </tr>
	  
	 <tr>
	   <td><font color="#ff0000"><strong>修改：</strong></font></td>
	  <td><font color="#0000ff">名称：</font></td>
	  <td>
	   <input  type="text"  id="MCATEGORIESNAME" name="search.MCATEGORIESNAME"  size="15"/>  
	   </td>
	  
	  <td> 
	  <input  type="button"  id="modifybtn" name="modifybtn" onclick="javascript:modifyCate(this);" value="修改"/> 
     
     
      </td>
	  
	
      
	  </tr>
	   <tr>
	   <td>  
	     <input  type="button"  id="modifybtn" name="modifybtn" onclick="javascript:fun_delete(this);" value="删 除"/> 
       </td>
	  <td>
	  
	  <script type="text/javascript">
function creatOption(){

        d = new dTree('d');
        d.add(0,-1,'My example tree');  
        
         <s:iterator id="prod" value="%{categoriesList.objectList}">
         d.add(<s:property value="#prod.CATEGORIESID" />,<s:property value="#prod.FID" />,'<s:property value="#prod.CATEGORIESNAME" />');    
         </s:iterator>
        document.write(d);    
        
}



</script>
     
     
     <input id="myselect" name="myselect"  onclick="creatOption();"  />

	  
	  
	  
	  
	  
	  </td>
	  <td>
	     &nbsp; 
	   </td>
	  
	  <td> 
	   &nbsp;
      </td>
	  
	 
	  </tr>
	  
	  </table>
	
	</td></tr></table>
	
	
	
	</form>


	</body>
</html>