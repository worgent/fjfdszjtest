<%@page contentType="text/html; charset=utf-8"%>
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
<script type="text/javascript">

      function print(){
    		    document.getElementById("actionid").value="print";
				document.forms[0].submit();
	}
		
      function myupload(val){
				showProgressBar('tab8','scrolltransparence');
				if(val){
					document.getElementById("actionid").value="upload";
				}else{
					document.getElementById("actionid").value="import";
				}
				document.forms[0].submit();
				hideProgressBar('tab8','scrolltransparence');
				alert("操作成功");
		}
    function showProgressBar(divid,imgid) {
		document.getElementById( imgid ).style.display = '' ;
	}
	
	function hideProgressBar(divid,imgid) {
		document.getElementById(imgid).style.display = 'none' ;
	}
	
		/*客户信息*/
		function setClientValue(frmname, frmmobile, frmunit,frmtel){
			document.all.frmname.value = frmname;
			document.all.frmmobile.value = frmmobile;
			document.all.frmunit.value = frmunit;
			document.all.frmtel.value = frmtel;			
		}
		/*地址信息*/
		function setAddValue(frmprovincename,frmcityname,frmcountyname,frmaddress){
			document.all.frmprovincename.value = frmprovincename;
			document.all.frmcityname.value = frmcityname;
			document.all.frmcountyname.value = frmcountyname;
			document.all.frmaddress.value = frmaddress;
		}
</script>
</head>
<!-- 上传excel页面,仅量不使用共用脚本 -->
<form name="form1" action="<%=path%>/net/uploadExcle.do" method="post" enctype="multipart/form-data">
<table><tr><td>excel文件</td>
<td>
    <s:file name="upload"  id="upload" title="excel文件" label="excel文件"></s:file>
</td> 
<td><input type="button"  name="btnupload"  id="btnuploadid" value="上传" onclick="javascript:myupload(true);" /></td>
<td><input type="button"  name="btnimport"  id="btnimportid" value="导入" onclick="javascript:myupload(false);" /></td>
<!-- 
<td><input type="button"  name="btnimport"  id="btnimportid" value="打印" onclick="javascript:print();" /></td>
 -->
<s:hidden name="action" id="actionid" value="%{action}"></s:hidden>
<s:hidden name="serfilename" value="%{serfilename}"></s:hidden>
</tr>
</table>
地址信息
<table class="table6" width="100%" border="0" cellpadding="3" cellspacing="0">
	<tr class="trClass">
		<td>
			<strong>选择</strong>
		</td>
		<td>
			<strong>省</strong>
		</td>
		<td>
			<strong>市</strong>
		</td>
		<td>
			<strong>县区</strong>
		</td>
		<td>
			<strong>详细地址</strong>
		</td>
		<td>
			<strong>是否默认地址</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{addressList}">
		<tr>
		    <td>
		   	     <input type="radio" name="addMsg" onclick="setAddValue('<s:property value="#g.PROVINCENAME" />','<s:property value="#g.CITYNAME" />','<s:property value="#g.COUNTYNAME" />','<s:property value="#g.ADDRESS" />')"/>
		    </td>
			<td>
				<s:property value="#g.PROVINCENAME" />
			</td>
			<td>
				<s:property value="#g.CITYNAME" />
			</td>
			<td>
				<s:property value="#g.COUNTYNAME" />
			</td>
			<td>
				<s:property value="#g.ADDRESS" />
			</td>
			<td>
				 <s:if test="#g.ISCHECK==1">是</s:if>
			     <s:else>否</s:else>
			</td>
		</tr>
	</s:iterator>
	<tr align="left">
		<td colspan="2"> 
			<input type="hidden" name="search.frmprovincename" id="frmprovincename" value="">
			<input type="hidden" name="search.frmcityname"  id="frmcityname" value="">
			<input type="hidden" name="search.frmcountyname" id="frmcountyname" value="">
			<input type="hidden" name="search.frmaddress" id="frmaddress" value="">
		</td>
	</tr>
</table>
客户信息
<table class="table6" width="100%" border="0" cellpadding="3" cellspacing="0">
	<tr class="trClass">
		<td>
			<strong>选择</strong>
		</td>
		<td>
			<strong>联系人</strong>
		</td>
		<td>
			<strong>手机</strong>
		</td>
		<td>
			<strong>固话</strong>
		</td>
		<td>
			<strong>单位名称</strong>
		</td>
	</tr>
	<s:iterator id="g" value="%{clientMsgList}">
		<tr>
		    <td>
		    	<input type="radio" name="clientMsg" onclick="setClientValue('<s:property value="#g.NAME" />', '<s:property value="#g.MOBILE" />', '<s:property value="#g.UNIT" />','<s:property value="#g.TEL" />')"/>
		    </td>
			<td>
				<s:property value="#g.NAME" />
			</td>
			<td>
				<s:property value="#g.MOBILE" />
			</td>
			<td>
				<s:property value="#g.TEL" />
			</td>
			<td>
				<s:property value="#g.UNIT" />
			</td>
		</tr>
	</s:iterator>
	<tr  align="left">
		<td colspan="2">
			<input type="hidden" name="search.frmname"  id="frmname" value="">
			<input type="hidden" name="search.frmmobile" id="frmmobile"  value="">
			<input type="hidden" name="search.frmunit"  id="frmunit" value="">
			<input type="hidden" name="search.frmareacode" id="frmareacode" value="">
			<input type="hidden" name="search.frmtel" id="frmtel"  value="">
		</td>
	</tr>
</table>


<span id="scrolltransparence"  style="display:none; border:1px solid #666;text-align:center;vertical-align:middle"><table id="imgload" height=100% width=100% ><tr><td align="center" valign="center" height=100%><img src="/images/ajax-loader.gif" id="tableprodress" style="widht:72px;height:72px"/></td></tr></table></span> 
</form>

		<table id="myTable">
			<thead>
				<tr>
					<th>
						序号
					</th>
					<th>
						ems单号
					</th>
					<th>
						库位号
					</th>
					<th>
						货号
					</th>
					<th>
						颜色
					</th>
					<th>
						尺码
					</th>
					<th>
						数量
					</th>
					
					<th>
						姓名
					</th>
					<th>
						省
					</th>
					<th>
						市
					</th>
					<th>
						县区
					</th>
					<th>
						街道
					</th>
					<th>
						联系电话
					</th>
					<th>
						渠道
					</th>
					<th>
						备注
					</th>						
				</tr>
			</thead>
			<tbody>
				<s:iterator id="excel" value="%{uploadExcelList}">
					<tr>
						<td>
							<s:property value="#excel.pno" />
						</td>
						<td>
							<s:property value="#excel.pmailno" />
						</td>
						<td>
							<s:property value="#excel.pstorageno" />
						</td>
						<td>
							<s:property value="#excel.pproductno" />
						</td>
						<td>
							<s:property value="#excel.pcolour" />
						</td>
						<td>
							<s:property value="#excel.psize" />
						</td>
						<td>
							<s:property value="#excel.pnumber" />
						</td>
						<td>
							<s:property value="#excel.precname" />
						</td>
						<td>
							<s:property value="#excel.precprovince" />
						</td>
						<td>
							<s:property value="#excel.preccity" />
						</td>
						<td>
							<s:property value="#excel.preccounty" />
						</td>
						<td>
							<s:property value="#excel.pstree" />
						</td>
						
						<td>
							<s:property value="#excel.prectel" />
						</td>
						<td>
							<s:property value="#excel.pchannel" />
						</td>
						<td>
							<s:property value="#excel.premark" />
						</td>
					</tr>
				</s:iterator>
			</tbody>
	</table>
</html>

 
 
 
 