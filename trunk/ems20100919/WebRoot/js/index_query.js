//--

function query(){
				//alert("aa");
				var province=$("#pprovince").val();//省编号
				var city=$("#pcity").val();//市编号
				var county=$("#pcounty").val();//县区编号
				
				if(province=="0"||province==""){
					alert("请先选择省份!");
				}else{
					//有选择省份
					if(city=="0"||city==""){
						alert("请选择地市!");
					}else{
						var provinceName=document.getElementById("pprovince").options[document.getElementById("pprovince").options.selectedIndex].text;
						var cityName=document.getElementById("pcity").options[document.getElementById("pcity").options.selectedIndex].text;
						//有选择县区
						if(county=="0"||county==""){
							$("#address").text(provinceName+cityName);
							$("#postalcode").text(city);
						}else{
							var countyName=document.getElementById("pcounty").options[document.getElementById("pcounty").options.selectedIndex].text;
							$("#address").text(provinceName+cityName+countyName);
							$("#postalcode").text(county);
						}
					}
				}
			
			}
		//国内资费查询
		function makeRequest(path,url,ownertypenum){
				showProgressBar('table13','scrolltransparence');
				$("#gofee").val('');
				var addressfrom=$("#addressfrom").val();
				var addressto=$("#addressto").val();
				var weight=$("#weight").val();
				if(addressfrom==""){
					alert("寄件人省份下拉选框无数据!");
					hideProgressBar('table13','scrolltransparence');
					return false;
				}
				if(addressto==""){
					alert("收件人省份下拉选框无数据!");
					hideProgressBar('table13','scrolltransparence');
					return false;
				}
				if(weight==""){
					alert("查询的输入值不能为空!");
					hideProgressBar('table13','scrolltransparence');
					return false;
				}
				if(weight>40){
					alert("国内特快专递邮件,单件重量不能超过40公斤,超过的需分件交寄!");
					hideProgressBar('table13','scrolltransparence');
					return false;
				}
				
				var urls = getActionMappingURL("/query/fee",path);
		  	    var pars="action=fee&date="+new Date()+"&reqCode=Postage&addressfrom="+encodeURIComponent(addressfrom)+"&addressto="+encodeURIComponent(addressto)+"&weight="+weight ;
		        $.get(urls,pars,showResult1);
		        
		      	function showResult1(res){
		      		//alert(res);没有相关资费讯息
		      		$("#gofee").val(res);
		      		
		      		$('#messageDiv').hide();
			    	$('#messageDiv').html(res);
			  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
			  		var codeid = jsonMsgObj.getCodeid();
			  		//alert(jsonMsgObj.getMessage());
			  		if (codeid == "0") {
			  			$("#gofee").val(jsonMsgObj.getMessage());
			  			hideProgressBar('table13','scrolltransparence');
			  			$("#queryFeeId").attr({"disabled":false});
			  		}
		      		
		      	}
			}
		
		//国际资费查询
		function makeRequest1(path,url){
			$("#goffee").val('');
			showProgressBar('tab8','scrolltransparence');
			
			var addressto=$("#addresstoc").val();//寄达地
			//var fileType=$("#njxz").val();//内件性质
			var weight=$("#weightid1").val();//重量
			//alert(addressto);
			var fileType="files";
			var fileType1=document.getElementsByName("njxz") 
			for(var i=0;i<fileType1.length;i++) 
			{ 
				if(fileType1[i].checked){ 
					fileType=fileType1[i].value;
				}
			} 

			var urls = getActionMappingURL("/query/ffee",path);
			var pars="action=ffee&addresstoc="+encodeURIComponent(addressto)+"&search.fileType="+fileType+"&search.weight="+weight+"&date="+new Date();
			
			$.get(urls,pars,function(res){
		      		$("#goffee").val("");
			    	$('#messageDiv').html(res);
			  		var jsonMsgObj = new JsonMsgObj($('#messageDiv').text());
			  		
			  		var codeid = jsonMsgObj.getCodeid();
			  		if (codeid == "0") {
			  			$("#goffee").val(jsonMsgObj.getMessage());
			  			
			  		}
			  		hideProgressBar('tab8','scrolltransparence');
			});
		}
		
		//国际时限查询中改变寄达地国家
				function changeDestinationCountry(){
					var country=$("#I_DestinationCountry").val();
					if(country!=null&& country=="HK"){
						$("#I_DestinationPostalCode").val("HKG");
						$("#I_DestinationPostalCode").attr({"disabled":true});
					}else{
						$("#I_DestinationPostalCode").val("");
						$("#I_DestinationPostalCode").attr({"disabled":false});
					}
				}
		
		//国际时限查询
		function queryFTime(){
				showProgressBar('tab4','scrolltransparence');
				$("#I_DestinationCountryName").val(document.getElementById("I_DestinationCountry").options[document.getElementById("I_DestinationCountry").options.selectedIndex].text);
				document.forms[2].submit();
				hideProgressBar('tab4','scrolltransparence');
			}

//国内时限查询			
function timeQuery(o,path){
				$("#resultValue").text("");
				var s=$("#pcity3").val();
				var r=$("#pcity4").val();
				if (s== "" ||  s=="0"){alert("如果进行模糊查询请选择原寄地城市");s.focus();return;}
    			if (r== "" ||  r=="0"){alert("如果进行模糊查询请选择收寄地城市");r.focus();return;}
    			
    			showProgressBar('tab5','scrolltransparence');
    			var urls = getActionMappingURL("/query/time",path);
    			var sendCityCode=$('#pcity3').val();
    			var rcvCityCode=$('#pcity4').val();
    			var sendProvinceCode=$("#pprovince3").val().substring(0,2);
    			var rcvProvinceCode=$("#pprovince4").val().substring(0,2);
		        var pars="date="+new Date+"&search.sendCityCode="+sendCityCode+"&search.rcvCityCode="+rcvCityCode+
		        	"&search.sendProvinceCode="+sendProvinceCode+"&search.rcvProvinceCode="+rcvProvinceCode;
		        $.ajax({type: "POST",
		        url:urls,data:pars,success:function(res){
			    	$('#messageDiv1').html(res);
			  		var jsonMsgObj = new JsonMsgObj($('#messageDiv1').text());
			  		var codeid = jsonMsgObj.getCodeid();
			  		//alert(jsonMsgObj.getMessage());
			  		if (codeid == "0") {
			  			hideProgressBar('tab5','scrolltransparence');
			  			$("#resultValue").text(jsonMsgObj.getMessage());
			  			
			  		}
		        }});
		        
		        
		      	function showResult1(res){
		      		hideProgressBar('tab5','scrolltransparence');
		      		$("#resultValue").html(res);
		      		
		      	}
    			
    			
    			
			}			
			
				
	function checkEms(path){
		var bool;
	    var str;//邮件号
	    var len;//邮件号长度
	    var i;
	    var charsets;
	    //showProgressBar('tab3','scrolltransparence');
	    bool="true";
	    charnum="0123456789";
	    charsets="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		str=$("#mailNum").val();
		len=str.length;
		if (str=="") {
        bool="false1";
	    }
	    else{
	        if (len!=13) {
	            bool="false2";
	          
	        }
	        else {  
	            if ((charsets.indexOf(str.substring(0,1))==-1)||(charsets.indexOf(str.substring(1,2))==-1)) {bool="false3";}
	            for(i=2;i<11;i++) {
	                //alert(charnum.indexOf(ch,0));
	                if (charnum.indexOf(str.substring(i,i+1),0)<0) {bool="false4";}
	            }
	            if ((charsets.indexOf(str.substring(11,12))==-1)||(charsets.indexOf(str.substring(12,13))==-1)) {bool="false5";}                                    
	        }
	    }
	    
	    if (bool!="true") {
	        if (bool=="false1") {alert("用户查询的输入值不能为空！");bool="true";hideProgressBar('tab3','scrolltransparence');  return false;}
	        if (bool=="false2") {alert("输入值必须为13位！");bool="true";hideProgressBar('tab3','scrolltransparence');  return false;}
	        if (bool=="false3") {alert("输入值前两位必须为字符！");bool="true";hideProgressBar('tab3','scrolltransparence');  return false;}
	        if (bool=="false4") {alert("输入值第3位至第11位必须为数字123456789！");bool="true";hideProgressBar('tab3','scrolltransparence');  return false;}
	        if (bool=="false5") {alert("输入值后两位必须为字符！");bool="true";hideProgressBar('tab3','scrolltransparence');  return false;}
	         
	    }
		var mailverifycode=$('#mailverifycode').val();
		var sessionverify;
		var urls2 = getActionMappingURL("/query/mail",path);
		var pars="action=checkcode&date="+new Date();
		$.get(urls2,pars,function(res){
			    	$('#messageDiv3').html(res);
			  		var jsonMsgObj = new JsonMsgObj($('#messageDiv3').text());
			  		var codeid = jsonMsgObj.getCodeid();
			  	    sessionverify=jsonMsgObj.getMessage();
			  		if (codeid == "0" &&(sessionverify==mailverifycode) ) {
			  			document.forms[0].submit();
			  		}else{
			  			alert("验证码有误，请重输！");
			  			//hideProgressBar('tab3','scrolltransparence');  
			  			return false;
			  		}}
			  		
		);
      //hideProgressBar('tab3','scrolltransparence');
	}
	
//========================================登录自助系统模块脚本==============================
	
	//验证
    function checkmsg(){
    	result=false;
    	var pcode=document.getElementById("pcode").value;
    	var ppasswd=document.getElementById("ppasswd").value;
    	var pverifycode=document.getElementById("pverifycode").value;
    	if(!(getLength(pcode)>=3&&getLength(pcode)<=40))
    	{
    		alert('用户名必须在3到40个字符之间');
    		return result;
    	}
    	if(!(ppasswd.length>=6&&ppasswd.length<=20))
    	{
    		alert('密码必须在6到20个字符之间');
    		return result;
    	}
    	if(!(pverifycode.length==4))
    	{
    		alert('验证码必须4个字符');
    		return result;
    	}
		return true;
    }
    
	//汉字为2个字符
	function getLength(val){
				   var len = 0;
					for (var i = 0; i < val.length; i++) 
					{
						if (val.charCodeAt(i) >= 0x4e00 && val.charCodeAt(i) <= 0x9fa5){ 
							len += 2;
						}else {
							len++;
						}
					}
					return len;
	}
	
	
	//发送密码到手机,邮箱==手机.密码设置
	function fun_forget(){
			var str=prompt("请输入电子邮箱:","123@163.com");
			if (str)
			{
			    alert("电子邮箱:"+str+"已经提交到服务器,您的手机将会得到重置后的密码!");
			}
			else
			{
			    alert("你点击了取消");
			}
	}
	
	//注册页面
	function regedit(){
	        window.location.target="_blank"
	        window.location.href="/regedit.jsp";
	}

	function showProgressBar(divid,imgid) {
		document.getElementById( imgid ).style.display = '' ;
		//document.getElementById( divid ).style.disabled = true ;
	}
	
	function hideProgressBar(divid,imgid) {
		document.getElementById(imgid).style.display = 'none' ;
		//document.getElementById(divid).style.disabled = false ;
		
		
	}

//-->

