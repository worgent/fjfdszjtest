<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.qzgf.utils.comm.WebFrameUtil;"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<body>   
		<s:form action="mapcard" method="post" enctype="multipart/form-data" theme="xhtml">
		  <s:head  value="地图名片"/>
		  <table align="center">
				<tr>
					<td colspan="4">
						<s:actionerror theme="webframe0" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<s:actionmessage theme="webframe0" />
					</td>
				</tr>
		 <tr>
			 <s:textfield name="search.pmerchantname" value="%{mapcard.MERCHANTNAME}" label="商家名称" cssStyle="colspan=2"></s:textfield>
			 
			 <s:hidden name="search.pmerchanticonex" id="search.pmerchanticonex" value="%{mapcard.MERCHANTICON}"></s:hidden><!-- 个性图标的文件名信息 -->
			 <s:file name="upload" id="upload" title="商家个性图标" label="商家个性图标"></s:file>
			 <s:if test="%{action=='update'}">
			 <td><img height="25" width="25"  src="<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='%{mapcard.MERCHANTICON}'/>_Small" alt="<s:property value='%{mapcard.MERCHANTICON}' />" /></td>
			 </s:if>
		 </tr>
		 <tr>
		     
			 <!--<s:textfield name="search.pmerchanttype" value="%{mapcard.MERCHANTTYPE}" label="商家类型"></s:textfield>-->
			 <td>商家类型:
			 </td>
			 <td>
			 <select id="pbelong" name="search.pbelong" onchange="changeCombo('pmerchanttype','pbelong','combomenu')">
			 </select>
			  <select id="pmerchanttype" name="search.pmerchanttype">
			</select>
			</td>
			 <s:textfield name="search.pmerchantid" value="%{mapcard.MERCHANTID}" label="认领商家" ></s:textfield>		 	
		 </tr>
		 <tr>
 			 <s:textfield name="search.pmerchanttelphone" value="%{mapcard.MERCHANTTELPHONE}" label="联系方式"></s:textfield>
			 <s:textfield name="search.pmerchantaddress" value="%{mapcard.MERCHANTADDRESS}" label="地址"></s:textfield>
		  </tr>
		  <tr>
		  	<s:textfield name="search.pmerchantintroduce" value="%{mapcard.MERCHANTINTRODUCE}" label="介绍"></s:textfield>
		  </tr>
		  <tr>
		       <!-- 地图显示 -->
		  	   <div id="map_canvas" style="width: 500px; height: 300px"></div>
		  </tr>
		  <tr>
		    <!-- 主键内部隐藏 -->
		  	<s:hidden name="action" value="%{action}"></s:hidden>
		  	<s:hidden name="search.pid" value="%{mapcard.ID}"></s:hidden>
		  	<!-- googlemap的引入经纬度信息 -->
		  	<s:hidden name="search.plat" id="search.plat" value="%{mapcard.LAT}"></s:hidden><!-- 纬度 -->
		  	<s:hidden name="search.plng" id="search.plng" value="%{mapcard.LNG}"></s:hidden><!-- 经度 -->
		  	
		  	<!--<s:hidden name="uploadFileName" id="uploadFileName" value="123"></s:hidden>  -->
		  </tr>
		  </table>
		  <!-- 地图名片:特色商品信息 -->
		  <table class='simple' style='width: 80%' align='center' id="maintaindetailinfo">
			<thead>
				<tr>
					<th colspan='5'>特色商品信息</th>
				</tr>
			</thead>
			<tbody id="maintaintbody">
				<tr>				
					<td align='center'>商品名称</td>
					<td align='center'>特品图标</td>
					<td align='center'>特品介绍</td>		
					<td align='center'>
						<a style="cursor: hand" onclick="add_maintaindetail();">添加</a>
					</td>
				</tr>
		<s:if test="productList!= null&&productList.size()>0">
	       	  <input type="hidden" name="product.num" value="<s:property value="productList.size()"/>"><%--记录被盗电话的记录数--%>
	       	  <s:iterator value="productList" status="product">               		
	        	<tr>   
	        		<td  valign="top" align='center'>
						<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="product.pspecialname" type="text" value="<s:property value="SPECIALNAME"/>" size="10">
					</td>
					  <td align="center">
					     <s:hidden name="product.pspecialiconex" id="product.pspecialiconex" value="%{SPECIALICON}"></s:hidden><!-- 个性图标的文件名信息 -->
					  	 <input type='file' name='product.pspecialicon' id='product.pspecialicon' title='商家个性图标'/>
						 <s:if test="%{action=='update'}">
						 <img height="25" width="25" src="<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='SPECIALICON'/>_Small" alt="<s:property value="SPECIALICON"/>" />
						 </s:if>
					  </td>
					<td  valign="top" align='center'>
						<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="product.pspecialintro" type="text" value="<s:property value="SPECIALINTRO"/>" size="10">					 
					</td>	 
					<td  align="center" align='center'>
						<a onclick='del_maintaindetail();' style='cursor: hand'>删除</a>
					</td>
	       		</tr>
	       		</s:iterator>   
	       	</s:if>
			<s:else> 
				<input type="hidden" name="product.num" value="1"><%--中途站点记录数--%>
				<tr>				 
					<td  valign="top" align='center'>
						<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="product.pspecialname" type="text"  size="10">
					</td>
					  <td valign="top" align="center">
					  	 <input type='file' name='product.pspecialicon' id='product.pspecialicon' title='商家个性图标'/>
					  </td> 				
					<td  valign="top" align='center'>
						<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="product.pspecialintro" type="text"  size="10">					 
					</td>
					<td  align="center" align='center'>
						<a onclick='del_maintaindetail();' style='cursor: hand'>删除</a>
					</td>
				</tr>
			</s:else>
			</tbody>
			<TFOOT id="maintaindetailTfoot">
			</TFOOT>
		  <tr>
		  <s:submit value="提交" align="right"></s:submit>
		  <s:reset value="重置" align="left"></s:reset>
		  </tr>
		</table>
		</s:form>
<!-- 共用地图基本处理 -->
<script type="text/javascript" src="<%=path%>/js/MapBase.js"></script>       		
<script language="javascript" type="text/javascript">	
		    //启动时调用google地图引擎     
		    $(document).ready(function(){
				if (GBrowserIsCompatible()) {
			        //调用googlemap相应方法
					editmap();
					$(window).unload(function (){
				　　　　　$('.').unbind();
				　　　　　GUnload();
				　　});
				}else{
			　　　　 jalert('你使用的浏览器不支持 Google Map!');　　
			　　 }
			})

	function add_maintaindetail(){	 
		var rowNum = Math.floor(document.all("product.num").value);
		rowNum ++;
		document.all("product.num").value = rowNum;//roldInfo
		var oBody=document.all("maintaintbody");//mobileTbody
		document.all("maintaindetailinfo").insertBefore(oBody,document.all("maintaindetailTfoot"));
		var myTR =oBody.insertRow();		
		var myTD2=myTR.insertCell();				
			myTD2.innerHTML="<div align='center'><input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='product.pspecialname' type='text'   size='10'></div>";			 

		   
		var myTD3=myTR.insertCell();	
			myTD3.innerHTML="<div align='center'>  <input type='file' name='product.pspecialicon' id='product.pspecialicon' title='商家个性图标'/></div>";
			
			
		var myTD4=myTR.insertCell();	
			myTD4.innerHTML="<div align='center'><input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='product.pspecialintro'  type='text'   size='10'></div>";
		var myTD11=myTR.insertCell();		
			myTD11.innerHTML="<div align='center'>"+
						"	<a onclick='del_maintaindetail();' style='cursor:hand'>删除</a>"+
						"</div>";
	}
	
 	function del_maintaindetail(){	 
 		var num = Math.floor(document.all("product.num").value);
		num = num-1;
		document.all("product.num").value = num;		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.maintaindetailinfo.deleteRow(cur_row);
	}
	
	// 被上个函数调用
 	function get_Element(the_ele,the_tag){
   		the_tag = the_tag.toLowerCase();
  		if(the_ele.tagName.toLowerCase()==the_tag)
  			return the_ele;
   		while(the_ele=the_ele.offsetParent){
     		if(the_ele.tagName.toLowerCase()==the_tag)
     			return the_ele;
   		}
   		return(null);
	}
	
//2009-12-01
//省,地,市连动
//参数说明:chCombo变化控件的id      被动方
//		  srcCombo引起变化的控件id  主动方
//		  action事件类型
function changeCombo(chCombo,srcCombo,action)
{
	var cdsales=new ActiveXObject("Microsoft.XMLDOM"); //创建XmlDom对象
    cdsales.async=false; //使用异步加载
    var parmid='';
    if(srcCombo!='')
    {
      parmid=document.getElementById(srcCombo).value
    }else
    {
    	parmid=0;
    }
    cdsales.load("/selfconfig/mapcardsort.do?action="+action+"&search.pbelongid="+parmid);
     var bi;
     if(cdsales.documentElement!=null)
         bi=cdsales.documentElement.selectNodes("NODE");
    if(bi!=null&&bi.length>0)
    {
       for(var i=0;i<bi.length;i++){     
                document.getElementById(chCombo).length=i+1; 
	   			document.getElementById(chCombo).options[i].value = bi[i].selectSingleNode("ID").text;//隐藏值
	   			document.getElementById(chCombo).options[i].text =  bi[i].selectSingleNode("SORTNAME").text;//显示值
       }
    }else{
       document.getElementById(chCombo).options.length = 1;   
       document.getElementById(chCombo).options[0].value ="0";//隐藏值
	   document.getElementById(chCombo).options[0].text = "请选择";//显示值
    }
}
//初始化信息
changeCombo('pbelong','','combomenu');
changeCombo('pmerchanttype','pbelong','combomenu');		
</script>
	</body>
</html>