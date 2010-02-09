<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>
<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 派车管理
 	 * @author fangzl 
 	 <script type="text/javascript" src="/themes/default/js/calendar.js"></script>
--%>

<form methd='POST' name='form1' action='/carmanage/expedite/expediteex.shtml'>
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>派车信息管理</th>
			</tr>
		</thead>
		<tbody>
		    <tr>
				<td align='right'>派车人:</td>
				<td>
					<tt:ComboBox name="search.expediteMan" value="search.expedite_man"  verify="empty" required="true" editable="true" 
						sql="'select distinct staff_info_id id, staff_name text 
								from tf_staff_info  
							   where state = 1 
							   	 and staff_type = 2
							     and city_id in (select city_id   
							     				   from tf_staff_city    
							     				  where staff_id=' + #session['UserInfo'].staffId+')'"/>
				</td>
				<td align='right'>派车日期:</td>
				<td>					
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="search.expediteDate" type="text" value="<ww:property value="search.expedite_date"/>" onfocus="calendar();"  size="18">
				</td>				
			</tr>
			<tr>
				<td align='right' width="17%">用车人:</td>
				<td>	     				  
					<tt:TextField name="search.useCarManName" value="search.use_car_man_name" />
				</td>
				<td align='right' width="17%" >用车人联系电话:</td>
				<td width="33%">
					<tt:TextField name="search.useCarManmobile" value="search.use_car_man_mobile" />
				</td>
			</tr>
			<tr>
			    <td align='right'>预计用车时间:</td>
				<td>
				<tt:TextField name="search.intendingDateex" value="search.intending_dateex" />
				</td>
				<td align='right'>预计回程时间:</td>
				<td>
				<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="search.intendingDate" type="text" value="<ww:property value="search.intending_date"/>" onfocus="calendar()" size="18">
				</td>					
			</tr>
			<tr>				
				<td align='right'>驾驶员:</td>
				<td width="33%">
				<tt:TextField name="search.driverNameex" value="search.driver_name" />
				</td>
				<td align='right' width="17%">驾驶员联系电话:</td>
				<td width="33%">
				<tt:TextField name="search.driverMobile" value="search.driver_mobile" />
				</td>
			</tr>
			<tr>				
				<td align='right' width="17%" >用车部门:</td>
				<td>
					<div id="deptDIV"></div>
					<input type="hidden" name="search.deptId" value="">
				</td>				
				<td align='right' width="17%">车辆编号:</td>
				<td width="33%">
					<tt:ComboBox name="search.carNoId" value="search.car_no_id+'|'+search.car_no_code"  verify="empty" required="true" editable="true"
						onchange="ajaxGetGPSCarMileage(this.value)"
						sql="\"select concat(car_no_id, '|', car_no_code) id, car_no_code text 
							   from tf_car_info 
							  where state = 1
							    and city_id in (select city_id 
							    				  from tf_staff_city  
							    				 where staff_id = \" + #session['UserInfo'].staffId+\")
							  union all
							  select concat(car_no_id, '|', car_no_code) id, car_no_code text
							    from tf_car_info 
							   where state = 1
							     and car_no_id = \"+search.car_no_id"/>
				</td>
			</tr>
			<tr>				
				<td align='right'>起始里程:</td>
				<td>				 
					<tt:TextField name="search.initMileage" value="search.init_mileage" width="150" cssClass="check" verify='double+0' required='true' shade='true' requiredColor='#ffffff'/> 单位：公里
				</td>
				<td align='right'>所属地市:</td>
				<td >
					<tt:ComboBox name="search.cityId" value="search.city_id" cssClass="check" shade="true" requiredColor="#ffffff" verify="empty" required="true" onchange="setsmsContext();"
							sql="'select city_id id, city_name text from td_city where city_id in (select city_id from tf_staff_city  where  staff_id=' + #session['UserInfo'].staffId+')'" />
				</td>
			</tr>				
            <tr>   
				<td align='right'>用车事由:</td>
				<td colspan="3">
					<tt:TextArea name="search.useExcuse" value="search.use_excuse" width="400" height="50" verify='string' required='true' msg='请填写用车事由'/>
				</td>
			</tr>		
		</tbody>
	</table>
	<div style="color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;注：“起始里程”为出车时抄表的里程值。
	</div>
	<br>
	<table class='simple' style='width: 80%' align='center' id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='5'>中途站点信息登记</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>出车地</td>
				<td align='center'>出车时间</td>
				<td align='center'>中转站</td>
				<td align='center'>中转站时间</td>			
				<td align='center'>
					<a style="cursor: hand" onclick="add_maintaindetail();">添加</a>
				</td>
			</tr>
	<ww:if test="roldInfoList!= null&&roldInfoList.size()>0">
       	  <input type="hidden" name="roldInfo.num" value="<ww:property value="roldInfoList.size()"/>"><%--记录被盗电话的记录数--%>
       	  <ww:iterator value="roldInfoList" status="roldInfo">               		
        	<tr>   
        		<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstLocus" type="text" value="<ww:property value="first_locus"/>" size="10">
				</td>
				<td  valign="top" align='center'>				
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstDate" type="text" value="<ww:property value="first_date"/>" onfocus="calendar()" size="18">
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferLocur" type="text" value="<ww:property value="transfer_locur"/>" size="10">					 
				</td>
				<td  valign="top" align='center'>					
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferDate" type="text" value="<ww:property value="transfer_date"/>" onfocus="calendar()" size="18">
				</td>		 
				<td  align="center" align='center'>
					<a onclick='del_maintaindetail();' style='cursor: hand'>删除</a>
				</td>
       		</tr>   
       		</ww:iterator>   
       	</ww:if>
		<ww:else> 
			<input type="hidden" name="roldInfo.num" value="0"><%--中途站点记录数--%>
		</ww:else>
		</tbody>
		<TFOOT id="maintaindetailTfoot">
		</TFOOT>
	</table>
	
		<br>
		<table class='simple' style='width: 80%' align='center' id="smsTab">
			<thead>
				<tr>
					<th colspan='10'>短信发送</th>
				</tr>
			</thead>
			<tbody id="smsBody">
				<tr>		
					<td align='center'>短信接收者</td>	
					<td align='center'>接收短信号码</td>	
					<td align='center'>短信内容</td>
					<td align='center'><a href='javascript:add_smsdetail()'>[添加]</a></td>
				</tr>
				<input type="hidden" name="smsInfo.num" value="3">
				<tr>	
					<td  valign="top" align='center'>	
						<input name='search.smsManId'  type='hidden' size='11'>
						<input class='text_input' name='search.smsManName' value='<ww:property value=""/>' type='text' size='11'>
					</td>
					<td  valign="top" align='center'>
						<input class='text_input' name='search.smsMobileNo' value='<ww:property value=""/>' type='text' size='11'>
					</td>
					<td  valign="top" align='center'>
						<input class='text_input' name='search.smsContext' type='text' size='50'>
					</td>
					<td></td>
				</tr>
				<tr>	
					<td  valign="top" align='center'>				
						<input name='search.smsManId'  type='hidden' size='11'>
						<input class='text_input' name='search.smsManName'  type='text' size='11'>
					</td>
					<td  valign="top" align='center'>
						<input class='text_input' name='search.smsMobileNo'  type='text' size='11'>
					</td>
					<td  valign="top" align='center'>
						<input class='text_input' name='search.smsContext' type='text' size='50'>
					</td>	
					<td></td>
				</tr>
				
			</tbody>
			<TFOOT id="smsFfoot">
			</TFOOT>
		</table>
	<input type='hidden' name='search.expediteCarId' value='<ww:property value="search.expedite_car_id"/>' />
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
	<input type='hidden' name='jscarmark' value='' />
	<input type='hidden' name='jsuseMsg' value='' />
</form>
<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>

<script type="text/javascript" src="/js/TreeField.js"></script>
<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid() && document.form1.checkSubmit()) {
        		if (!compareDate($('search.intendingDate').value, $('search.expediteDate').value)) {
	        		Ext.Msg.show({
	 					title:'信息',
						msg: '预计回车日期必须大于等于派车日期！',
						modal : true,
						buttons: Ext.Msg.OK});
	        		return;
	        	}
	        	Ext.Msg.show({
				 	title:'再确认一下',
				 	modal : false,
				 	msg: '您确定资料正确吗?',
				 	buttons: Ext.Msg.OKCANCEL,
				 	fn: function(btn, text){
						if (btn == 'ok'){
					 		document.form1.submit();
					 	} 
				 	},
				 	animEl: 'buttonTD1'
			 	});
        	} else {
        		Ext.Msg.show({
 					title:'信息',
					msg: '请填写完整后再提交!',
					modal : true,
					buttons: Ext.Msg.OK
 				});
        	}
        }
    }).render(document.all.buttonTD1);
			
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
    
    function ajaxGetGPSCarMileage(carNoCode){
    	carNoCode = carNoCode.substring(carNoCode.indexOf('|')+1, carNoCode.length);
    	var url = '/basearchives/car/ajaxGetGPSCarMileage.shtml?search.carNoCode='+carNoCode;
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
				var rowSet = root.selectNodes("//GPSCarMileage");
				if (rowSet.length > 0){
					document.all("search.initMileage").value = rowSet.item(0).selectSingleNode("value").text;
					document.all("jscarmark").value="车号:"+rowSet.item(0).selectSingleNode("carmark").text;
				}else{
					alert('获取车辆里程时出错，请重试！');
				}
			}
		}catch(e){ 
			alert(e);
		}
    }

	function compareDate(DateOne,DateTwo){	
		var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ("-"));
		var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ("-")+1);
		var OneYear = DateOne.substring(0,DateOne.indexOf ("-"));
		
		var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ("-"));
		var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ("-")+1);
		var TwoYear = DateTwo.substring(0,DateTwo.indexOf ("-"));
		
		if (Date.parse(OneMonth+"/"+OneDay+"/"+OneYear) >= Date.parse(TwoMonth+"/"+TwoDay+"/"+TwoYear)){
			return true;
		}else{
			return false;
		}

	}
	
	//下拉型树型菜单
	Ext.onReady(function(){
		Ext.QuickTips.init();
		
		//部门
		var belongCompanyTree = new Ext.form.TreeField({
				minListHeight:200,
				dataUrl : '/basearchives/deptInfo/ajaxDeptInfo.shtml',
	            hiddenName : 'search.deptId',
	            valueField : 'id',
	            allowBlank:false,
	            blankText : '请选择用车部门！',
	            treeRootConfig : {
	            	id:'',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'<ww:property value="search.dept_name"/>',
	            value:'<ww:property value="search.dept_id"/>'
		});
		belongCompanyTree.render('deptDIV');	//输出到指定的对象中
	});
	//得到短信内容信息
	function setsmsContext(){ 
	            //司机提示信息
		   		document.all("search.smsManName")[1].value=document.all("search.driverNameex").value;
	   			document.all("search.smsMobileNo")[1].value=document.all("search.driverMobile").value;
	  		    document.all("search.smsContext")[1].value="用车时间:"+document.all("search.intendingDateex").value+
	   			",用车人:"+document.all("search.useCarManName").value+",手机:"+document.all("search.useCarManmobile").value
	   			+",用车事由:"+document.all("search.useExcuse").value+","+document.all("jscarmark").value;
	   			//用车人提示信息
	   			document.all("search.smsManName")[0].value=document.all("search.useCarManName").value;
	   			document.all("search.smsMobileNo")[0].value=document.all("search.useCarManmobile").value;
		 	 	document.all("search.smsContext")[0].value=document.all("search.useCarManName").value+"先生(小姐),你好,为你服务的："+"司机:"+document.all("search.driverNameex").value
		 	 	+",联系方式:"+document.all("search.driverMobile").value+","+document.all("jscarmark").value;
    }
	/*得到用户名和手机号相关信息*/	
	function editSms(rownum,value){
		var url = '/basearchives/employeeInfo/ajaxEmployee.shtml?search.staffInfoId='+value;
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
				var rowSet = root.selectNodes("//EmployeeInfo");
				if (rowSet.length > 0){
					document.all("search.smsManId")[rownum].value = rowSet.item(0).selectSingleNode("staffInfoId").text;
					document.all("search.smsManName")[rownum].value = rowSet.item(0).selectSingleNode("staffName").text;
					document.all("search.smsMobileNo")[rownum].value = rowSet.item(0).selectSingleNode("smsPhone").text != 'null' ? rowSet.item(0).selectSingleNode("smsPhone").text : '';
					//document.all("search.smsContext")[0].value="<ww:property value="#applyerList.use_man"/>"+"先生(小姐),你好,为你服务的："+"司机:"+rowSet.item(0).selectSingleNode("staffName").text+",联系方式:"+document.all("search.smsMobileNo")[rownum].value+",";
					//document.all("jsuseMsg").value="<ww:property value="#applyerList.use_man"/>"+"先生(小姐),你好,为你服务的："+"司机:"+rowSet.item(0).selectSingleNode("staffName").text+",联系方式:"+document.all("search.smsMobileNo")[rownum].value;
				}else{
					alert('获取员工信息时出错，请重试！');
				}
			}
		}catch(e){ 
			alert(e);
		}
	}
	
	function add_maintaindetail(){	 
		var rowNum = Math.floor(document.all("roldInfo.num").value);
		rowNum ++;
		document.all("roldInfo.num").value = rowNum;//roldInfo
		var oBody=document.all("maintaintbody");//mobileTbody
		document.all("maintaindetailinfo").insertBefore(oBody,document.all("maintaindetailTfoot"));
		var myTR =oBody.insertRow();		
		var myTD2=myTR.insertCell();
			myTD2.innerHTML="<div align='center'><input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.firstLocus' type='text'   size='10'></div>";			 
		var myTD3=myTR.insertCell();	
			myTD3.innerHTML="<div align='center'><input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.firstDate'  type='text'   onfocus='calendar()' size='18'></div>";
		var myTD4=myTR.insertCell();	
			myTD4.innerHTML="<div align='center'><input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.transferLocur'  type='text'   size='10'></div>";
		var myTD5=myTR.insertCell();	
			myTD5.innerHTML="<div align='center'><input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.transferDate'  type='text'   onfocus='calendar()' size='18'></div>";
		var myTD11=myTR.insertCell();		
			myTD11.innerHTML="<div align='center'>"+
						"	<a onclick='del_maintaindetail();' style='cursor:hand'>删除</a>"+
						"</div>";
	}
 	function del_maintaindetail(){	 
 		var num = Math.floor(document.all("roldInfo.num").value);
		num = num-1;
		document.all("roldInfo.num").value = num;		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.maintaindetailinfo.deleteRow(cur_row);
	}
	
	//添加要发送的短信
	function add_smsdetail(){	 
		var rowNum = Math.floor(document.all("smsInfo.num").value);
		rowNum ++;
		document.all("smsInfo.num").value = rowNum;//roldInfo
		var oBody=document.all("smsBody");//mobileTbody
		document.all("smsTab").insertBefore(oBody,document.all("smsFoot"));
		var myTR =oBody.insertRow();		
		var myTD2=myTR.insertCell();				
			myTD2.innerHTML="<div align='center'>"+
							"	<input name='search.smsManId'  type='hidden' size='11'>"+
							"	<input class='text_input' name='search.smsManName'  type='text' size='11'>"+
							"</div>";			 
		var myTD3=myTR.insertCell();	
			myTD3.innerHTML="<div align='center'><input class='text_input' name='search.smsMobileNo'  type='text' size='11'></div>";
		var myTD4=myTR.insertCell();	
			myTD4.innerHTML="<div align='center'><input class='text_input' name='search.smsContext' type='text' size='50'></div>"; 
		var myTD11=myTR.insertCell();		
			myTD11.innerHTML="<div align='center'>"+
						"	<a onclick='del_smsdetail();' style='cursor:hand'>删除</a>"+
						"</div>";
	}
 	function del_smsdetail(){	 
 		var num = Math.floor(document.all("smsInfo.num").value);
		num = num-1;
		document.all("smsInfo.num").value = num;		
	   	event.cancelBubble=true;
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex;
	   	document.all.smsTab.deleteRow(cur_row);
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
</script>