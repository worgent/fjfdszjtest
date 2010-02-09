<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%>
<%@ include file="/common/taglibs.jsp"%>

<ww:action name="'select'" id="select"></ww:action>
<%--
	 * 回车管理
 	 * @author fangzl 
--%>

	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='4'>
					派车信息信息
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td align='right' width="17%">出车单据号:</td>
				<td colspan="3"><ww:property value="search.expedite_car_id"/></td>
			</tr>
			<tr>
				<td align='right' width="17%">派车人:</td>
				<td width="33%"><ww:property value="search.expedite_man_name"/></td>
				<td align='right' width="17%">车辆编号:</td>
				<td width="33%"><ww:property value="search.car_no_code"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%">用车人:</td>
				<td width="33%"><ww:property value="search.use_car_man_name"/></td>
				<td align='right' width="17%">用车部门:</td>
				<td width="33%"><ww:property value="search.dept_name"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%">驾驶员:</td>
				<td width="33%"><ww:property value="search.driver_name"/></td>
				<td align='right' width="17%">派车日期:</td>
				<td width="33%"><ww:property value="search.expedite_date"/></td>	
			</tr>
			<tr>
				<td align='right' width="17%">起始里程:</td>
				<td width="33%"><ww:property value="search.init_mileage"/> 单位：公里</td>
				<td align='right' width="17%">预计回程时间:</td>
				<td width="33%"><ww:property value="search.intending_date"/></td>
			</tr>
			<tr>
				<td align='right' width="17%">用车事由:</td>
				<td><ww:property value="search.use_excuse"/></td>
			</tr>
		</tbody>
	</table>
	<br>
	<table class='simple' style='width: 80%' align='center'>
		<thead>
			<tr>
				<th colspan='10'>出车中途站点信息</th>
			</tr>
		</thead>
		<tbody>
			<tr>				
				<td align='center'>出车地</td>
				<td align='center'>出车时间</td>
				<td align='center'>中转站</td>
				<td align='center'>中转站时间</td>		
			</tr>
       	  <ww:iterator value="roldInfoList" status="roldInfo">               		
        	<tr>   
        		<td valign="top" align='center'>
					<ww:property value="first_locus"/>
				</td>
				<td valign="top" align='center'>				
					<ww:property value="first_date"/>
				</td>
				<td valign="top" align='center'>
					<ww:property value="transfer_locur"/>					 
				</td>
				<td  valign="top" align='center'>					
					<ww:property value="transfer_date"/>
				</td>
       		</tr>   
       		</ww:iterator>   
		</tbody>
	</table>
	<br>

<form name="form1"  action='/carmanage/expedite/goback.shtml' class="formcheck" onsubmit="return checkSubmit();">
<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>
	<br>
	<table class='simple' style='width:98%' align='center'
		id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='9'>车辆使用清单</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>				
				<td align='center'>起点</td>
				<td align='center'>出车时间</td>
				<td align='center'>起公里数</td>
				<td align='center'>目的地</td>
				<td align='center'>目的时间</td>
				<td align='center'>止公里数</td>
				<td align='center'><font color="red">行驶里程(公里)</font></td>	
				<td align='center'><font color="red">用车时长(小时)</font></td>
				<td align='center'>
					<a style="cursor: hand" onclick="add_maintaindetail();">添加</a>
				</td>
			</tr>
	<ww:if test="gobackRoldList!= null && gobackRoldList.size()>0">
       	  <input type="hidden" name="roldInfo.num" value="<ww:property value="gobackRoldList.size()"/>">
       	  <ww:iterator value="gobackRoldList" status="roldInfo">               		
        	<tr>   
        		<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstLocus" type="text" value="<ww:property value="first_locus"/>" size="10">
				</td>
				<td  valign="top" align='center'>				
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstDate" type="text" value="<ww:property value="first_date"/>"  onfocus="calendar();" size="18">
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+'  required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstMileage" type="text" value="<ww:property value="first_mileage"/>" size="8" onfocus='acc_runTime();' onblur='acc_runMileage()'>
				</td>	
				<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferLocur" type="text" value="<ww:property value="transfer_locur"/>" size="10">					 
				</td>
				<td  valign="top" align='center'>					
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferDate" type="text" value="<ww:property value="transfer_date"/>"  onfocus="calendar()" size="18">
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferMileage" type="text" value="<ww:property value="transfer_mileage"/>" size="8" onfocus='acc_runTime();' onblur='acc_runMileage();'>
				</td>	
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+'  required='true' requiredColor='#ffffff' shade='true' name="roldInfo.runMileage" type="text" value="<ww:property value="run_mileage"/>" size="8" readonly="readonly" >
				</td>	
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.runTime" type="text" value="<ww:property value="run_time"/>" size="8" readonly="readonly" >
				</td>
				<!-- 
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.roadCharge" type="text" value='<ww:property value="road_charge"/>' size="8">
				</td>			
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.useOilNum" type="text" value='<ww:property value="use_oil_num"/>' size="8" >				
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.useOilCharge" type="text" value='<ww:property value="use_oil_charge"/>' size="8" >				
				</td>
				
				<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.beiZhu" type="text" value="<ww:property value="bei_zhu"/>" size="10">					
				</td>	
				 -->		 
				<td  align="center" align='center'>
					<c:if test="${param.actionType != 'new'}">
						<a onclick='del_maintaindetail();' style='cursor: hand'>删除</a>
					</c:if>
				</td>
       		</tr>   
       		</ww:iterator>   
       	</ww:if>
		<ww:else> 
			<input type="hidden" name="roldInfo.num" value="1"><%--中途站点记录数--%>
			<tr>				 
        	     <td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstLocus" type="text" value="<ww:property value="first_locus"/>" size="10">
				</td>
				<td  valign="top" align='center'>				
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstDate" type="text" value="<ww:property value="first_date"/>"  onfocus="calendar();" size="18">
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.firstMileage" type="text" value="<ww:property value="search.init_mileage"/>" size="8" onfocus='acc_runTime();' onblur='acc_runMileage();'>
				</td>	
				<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferLocur" type="text" value="<ww:property value="transfer_locur"/>" size="10">					 
				</td>
				<td  valign="top" align='center'>					
					<input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferDate" type="text" value="<ww:property value="transfer_date"/>"   onfocus="calendar();" size="18">
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.transferMileage" type="text" value="<ww:property value="search.totalDistance"/>" size="8" onfocus='acc_runTime();' onblur='acc_runMileage();'>
				</td>	
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+'  required='true' requiredColor='#ffffff' shade='true' name="roldInfo.runMileage" type="text" value="<ww:property value="run_mileage"/>" size="8" readonly="readonly" >
				</td>	
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.runTime" type="text" value="<ww:property value="run_time"/>" size="8" readonly="readonly" >
				</td>
				<!--  
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.roadCharge"  type="text" value='0.00' size="8">
				</td>			
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.useOilNum" type="text" value='0.00' size="8" >				
				</td>
				<td  valign="top" align='center'>
					<input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.useOilCharge" type="text" value='0.00' size="8" >				
				</td>	
						
				<td  valign="top" align='center'>
					<input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name="roldInfo.beiZhu" type="text" value="<ww:property value="bei_zhu"/>" size="10">					
				</td>	
				-->			 
				<td  align="center" align='center'>
					<a onclick='del_maintaindetail();' style='cursor: hand'>删除</a>
				</td>
			</tr>
		</ww:else>
		</tbody>
		<TFOOT id="maintaindetailTfoot">
		</TFOOT>
	</table>
	<input type='hidden' name='search.initMileage' value='<ww:property value="search.init_mileage"/>'><%-- 出车时的里程值 --%>
	<input type='hidden' name='search.oldEndMileage' value='<ww:property value="search.end_mileage"/>'><%-- 修改时候使用 用于存储修改前的里程值 --%>
	<input type='hidden' name='search.carNoId' value='<ww:property value="search.car_no_id"/>'><%-- 修改时候使用 用于存储修改前的里程值 --%>
	<input type='hidden' name='search.gobackCarId' value='<ww:property value="search.goback_car_id"/>' />
	<input type='hidden' name='search.expediteCarId' value='${param['search.expediteCarId']}' />
	<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
	<input type='hidden' name='search.cityId' value='<ww:property value="search.city_id"/>'>
	<%--隐藏主表的信息 --%>
	<input type='hidden' name='search.endMileage' value='<ww:property value="search.endMileage"/>'>
	<input type='hidden' name='search.gobackDate' value='<ww:property value="search.goback_date"/>'>
	<input type='hidden' name='search.usrTimeLen' value='<ww:property value="search.usr_time_len"/>'>
	<input type='hidden' name='search.billType' value='<ww:property value="search.bill_type"/>'>
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
<!--
	<script type="text/javascript" src="/themes/default/js/calendar.js"/> 
-->
<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid() && document.form1.checkSubmit()) {
        		if (!fun_compMileage()){
	        		Ext.Msg.show({
	 					title:'信息',
						msg: '止里程值不可小于派车单起始里程!',
						modal : true,
						buttons: Ext.Msg.OK
	 				});
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
    /*备份原来向sqlserver的取数的处理*/
	function ajaxGetGPSCarMileage(carNoCode){
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
					document.all("search.endMileage").value = rowSet.item(0).selectSingleNode("value").text;
				}else{
					alert('获取车辆里程时出错，请重试！');
				}
			}
		}catch(e){ 
			alert(e);
		}
    }

	function ajaxGetGPSCarMileage(carNoCode){
	    document.all("search.endMileage").value = 0;
    }
    	
	ajaxGetGPSCarMileage('<ww:property value="search.car_no_code"/>');
	
	function fun_compMileage(){
		set_dateandendmileage();
		if (Math.floor($('search.initMileage').value)  >= Math.floor($('search.endMileage').value)){
			return false;
		}else{
			return true;
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
			myTD3.innerHTML="<div align='center'><input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.firstDate'  type='text'   onfocus='calendar();' size='18'></div>";
		var myTD4=myTR.insertCell();	
			myTD4.innerHTML="<div align='center'><input class='text_input check' verify='double+' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.firstMileage'  type='text'   size='8' onfocus='acc_runTime();' onblur='acc_runMileage();'></div>";	
		var myTD5=myTR.insertCell();	
			myTD5.innerHTML="<div align='center'><input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.transferLocur'  type='text'   size='10'></div>";
		var myTD6=myTR.insertCell();	
			myTD6.innerHTML="<div align='center'><input class='text_input check' verify='datetime' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.transferDate'  type='text'    onfocus='calendar();' size='18' ></div>";
		var myTD7=myTR.insertCell();	
			myTD7.innerHTML="<div align='center'><input class='text_input check' verify='double+' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.transferMileage'  type='text'   size='8' onfocus='acc_runTime();' onblur='acc_runMileage();'></div>";	

		var myTD8=myTR.insertCell();	
			myTD8.innerHTML="<div align='center'><input class='text_input check' verify='double+' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.runMileage'  type='text'   size='8'  readonly='readonly' ></div>";	
		var myTD9=myTR.insertCell();		
			myTD9.innerHTML="<div align='center'><input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.runTime'  type='text'   size='8' readonly='readonly'></div>";
		/*
		var myTD10=myTR.insertCell();		
			myTD10.innerHTML="<div align='center'><input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.roadCharge'  type='text'   size='8' value='0.00'></div>";
		var myTD11=myTR.insertCell();		
			myTD11.innerHTML="<div align='center'><input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.useOilNum'  type='text'   size='8'  value='0.00'></div>";
		var myTD12=myTR.insertCell();		
			myTD12.innerHTML="<div align='center'><input class='text_input check' verify='double+0' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.useOilCharge'  type='text'   size='8'  value='0.00'></div>";
		*/	
    	//var myTD13=myTR.insertCell();		
		//	myTD13.innerHTML="<div align='center'><input class='text_input check' verify='string' required='true' requiredColor='#ffffff' shade='true' name='roldInfo.beiZhu'  type='text'   size='10'></div>";
		var myTD14=myTR.insertCell();		
			myTD14.innerHTML="<div align='center'>"+
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
	
	//得到当前序号
	function get_index()
	{
		//event.cancelBubble=true;
		try
		{
	   	var the_obj = event.srcElement;
	   	var the_td	= get_Element(the_obj,"td");
	   	var the_tr	= the_td.parentElement;
	   	cur_row = the_tr.rowIndex-2;
	   	}catch(e)
	   	{
	   	alert(e);
	   	} 
	   	return cur_row;
	}
	//自动计算
	function acc_runMileage(){
	   	var i=get_index();
	    var firstmileage=document.getElementsByName('roldInfo.firstMileage')[i].value == '' ? 0 : document.getElementsByName('roldInfo.firstMileage')[i].value;
		var transferMileage =document.getElementsByName('roldInfo.transferMileage')[i].value == '' ? 0 : document.getElementsByName('roldInfo.transferMileage')[i].value ;
		document.getElementsByName("roldInfo.runMileage")[i].value = Subtr(transferMileage,firstmileage);
	}
	
	function acc_runTime(){
	   	var i=get_index();
	    var transferdate= document.getElementsByName("roldInfo.transferDate")[i].value;
		var firstdate = document.getElementsByName("roldInfo.firstDate")[i].value;
		document.getElementsByName("roldInfo.runTime")[i].value = minusDate(transferdate,firstdate);
	}
	
	function minusDate(DateOne,DateTwo){	
		var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ("-"));
		var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ("-")+1);
		var OneYear = DateOne.substring(0,DateOne.indexOf ("-"));
		
		var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ("-"));
		var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ("-")+1);
		var TwoYear = DateTwo.substring(0,DateTwo.indexOf ("-"));
		
		var oneDate=Date.parse(OneMonth+"/"+OneDay+"/"+OneYear);
		var towDate=Date.parse(TwoMonth+"/"+TwoDay+"/"+TwoYear);
	    return (oneDate.valueOf()-towDate.valueOf())/(60*60*1000);
	}
	
	//隐藏域
	function set_dateandendmileage()
	{
		var num = Math.floor(document.all("roldInfo.num").value);
		$('search.endMileage').value=document.getElementsByName('roldInfo.transferMileage')[num-1].value;
		$('search.gobackDate').value=document.getElementsByName('roldInfo.transferDate')[num-1].value;
		var total_time=0;
		for(i=0;i<num;i++)
		{
			total_time=total_time+Math.round(document.getElementsByName('roldInfo.runTime')[i].value);
		}
		$('search.usrTimeLen').value=total_time;
	}
</script>