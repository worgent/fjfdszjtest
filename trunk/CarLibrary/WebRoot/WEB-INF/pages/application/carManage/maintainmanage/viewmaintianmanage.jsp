<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					添加新保养管理信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">
					保养类型:
				</td>
				<td width="33%">
					<ww:property value="search.nurseType"/>
			    </td>
			    <td align='right' width="17%">
					车辆编号:
				</td>
				<td width="33%">
					<ww:property value="search.carNoId"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					保养日期:
				</td>
				<td>
					<ww:property value="search.nurse_date"/>
				</td>
				<td align='right'>
					保养里程:
				</td>
				<td>
					<ww:property value="search.nurse_mileage"/> 单位：公里
				</td>
			</tr>
			<tr>
				<td align='right'>保养金额:</td>
				<td>
					<ww:property value="search.nurse_charge"/> 单位：元
				</td>
			</tr>
			<tr>
				<td align='right'>
					用途:
				</td>
				<td colspan="3">
					<ww:property value="search.purpose"/>
				</td>
			</tr>
			<tr>
				<td align='right'>备注</td>
				<td colspan="3">
					<ww:property value="search.memo"/>
				</td>
			</tr>
			<tr>
				<td align='right'>
					所属地市:
				</td>
				<td colspan="3">
					<ww:property value="search.cityId"/>
				</td>	
			</tr>
			<tr>
				<td align='right'>创建人:</td>
				<td>
					<ww:property value="search.create_man_name "/>
				</td>
				<td align='right'>创建时间:</td>
				<td>
					<ww:property value="search.create_date"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>编辑人:</td>
				<td>
					<ww:property value="search.editor_man_name"/>
				</td>
				<td align='right'>编辑时间:</td>
				<td>
					<ww:property value="search.editor_date"/>
			    </td>
			</tr>
			<tr>
				<td align='right'>执行人:</td>
				<td>
					<ww:property value="search.execute_man_name"/>
				</td>
				<td align='right'>执行时间:</td>
				<td>
					<ww:property value="search.execute_date"/>
			    </td>
			</tr>
		</tbody>
	</table>
	<div style="color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;注：“保养里程”为登记保养单据时抄表的里程值。
	</div>
	<br>
	<table id='carFixingTab' class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='3'>要保养的设备</th>
			</tr>
		</thead>
		<tr>
			<td align='center' width='20%'>设备编码</td>
			<td align='center' width='20%'>设备名称</td>
			<td align='center'>设备备注</td>
		</tr>
		<tbody id='carFixingBody'>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
		<tfoot id='carFixingFoot'>
		</tfoot>
	</table>
<script>
	//获取车辆的设备信息
	function fun_carFixing(carNoId, nurseId){
		if(carFixingBody.hasChildNodes()) {   
	        while(carFixingBody.childNodes.length > 0) {         //判断有多少行，遍历所有行   
	            carFixingBody.removeChild(carFixingBody.childNodes[0]); //移除首行(个)元素   
	        }   
	    }   

		var url = '/basearchives/car/ajaxCarInfo.shtml?search.carNoId='+carNoId+'&search.nurseId='+nurseId;
		try{
			var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
			oXMLDom.async = false ;
			oXMLDom.load(url); 
			var root;
			if (oXMLDom.parseError.errorCode != 0) {
				var myErr = oXMLDom.parseError;
				return;
			} else {
				root = oXMLDom.documentElement;
			}
			if (null != root){
				var rowSet = root.selectNodes("//carFixing");
				for (var i=0; i<rowSet.length; i++){
					fun_addrow(rowSet.item(i).selectSingleNode("fixingId").text, 
							   rowSet.item(i).selectSingleNode("fixingCode").text, 
							   rowSet.item(i).selectSingleNode("fixingName").text, 
							   rowSet.item(i).selectSingleNode("memo").text,
							   rowSet.item(i).selectSingleNode("checked").text);
				}
			}
		}catch(e){ 
			alert(e);
		}
	}
	
	function fun_addrow(fixingId, fixingCode, fixingName, memo, checked){
		if (checked == 'null'){
			return;
		}
		
		var oBody = $(carFixingBody);
		document.all("carFixingTab").insertBefore(oBody,document.all("carFixingFoot"));
		var myTR =oBody.insertRow();
		var myTD1 = myTR.insertCell();
		myTD1.innerHTML = "<div align='center'>"+fixingCode+"</div>";
		var myTD2 = myTR.insertCell();
		myTD2.innerHTML = "<div align='center'>"+fixingName+"</div>";
		
		var myTD3 = myTR.insertCell();
		myTD3.innerHTML = "<div align='center'>"+memo+"</div>";
	}
	
	// 删除行
	function del_row(){
		if (!confirm('您确定是否删除，删除后该数据将无法恢复!')){
			return;
		}
		
		var num = Math.floor(document.form1('search.carFixingNum').value);
		num = num-1;
		document.form1('search.carFixingNum').value = num;
		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.carFixingTab.deleteRow(cur_row);
	}
	
	// 被上个函数调用
	function get_Element(the_ele,the_tag){
	   the_tag = the_tag.toLowerCase();
	   if(the_ele.tagName.toLowerCase()==the_tag)return the_ele;
	   while(the_ele=the_ele.offsetParent){
	     if(the_ele.tagName.toLowerCase()==the_tag)return the_ele;
	   }
	   return(null);
	}
	
	fun_carFixing('<ww:property value="search.car_no_id"/>', '<ww:property value="search.nurse_id"/>');
</script>