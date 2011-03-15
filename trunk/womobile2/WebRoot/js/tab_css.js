
function loadOnLineQuery(){ //自助服务 tab1
		document.getElementById("td1").style.background = "url(images/navA.jpg)";
		document.getElementById("td4").style.background = "url(images/navB.jpg)";
		document.getElementById("td5").style.background = "url(images/navB.jpg)";
		document.getElementById("td6").style.background = "url(images/navB.jpg)";
		document.getElementById("td7").style.background = "url(images/navB.jpg)";
		document.getElementById("td8").style.background = "url(images/navB.jpg)";


		document.getElementById("table13").style.display = "";
		document.getElementById("tab1").style.display = "";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab6").style.display = "none";
		document.getElementById("tab7").style.display = "none";
		document.getElementById("tab8").style.display = "none";
}

function loadPrint(){
		
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab3").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab6").style.display = "none";
		document.getElementById("tab7").style.display = "none";
		document.getElementById("tab8").style.display = "none";
		
}

function loadBoundQuery(){//国际时限查询tab4
		document.getElementById("td1").style.background = "url(images/navB.jpg)";
		document.getElementById("td4").style.background = "url(images/navA.jpg)";
		document.getElementById("td5").style.background = "url(images/navB.jpg)";
		document.getElementById("td6").style.background = "url(images/navB.jpg)";
		document.getElementById("td7").style.background = "url(images/navB.jpg)";
		document.getElementById("td8").style.background = "url(images/navB.jpg)";

		document.getElementById("table13").style.display = "";
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab4").style.display = "";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab6").style.display = "none";
		document.getElementById("tab7").style.display = "none";
		document.getElementById('I_DestinationCountry').value="HK";
		document.getElementById('I_DestinationPostalCode').value="HKG";
		document.getElementById("tab8").style.display = "none";
}

function loadTimeQuery(){ //国内时限查询 tab5
		document.getElementById("td1").style.background = "url(images/navB.jpg)";
		document.getElementById("td4").style.background = "url(images/navB.jpg)";
		document.getElementById("td5").style.background = "url(images/navA.jpg)";
		document.getElementById("td6").style.background = "url(images/navB.jpg)";
		document.getElementById("td7").style.background = "url(images/navB.jpg)";
		document.getElementById("td8").style.background = "url(images/navB.jpg)";

		document.getElementById("table13").style.display = "";
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "";
		document.getElementById("tab6").style.display = "none";
		document.getElementById("tab7").style.display = "none";
		document.getElementById("tab8").style.display = "none";
}

function loadMoneyQuery(){//国内资费查询 tab6
		document.getElementById("td1").style.background = "url(images/navB.jpg)";
		document.getElementById("td4").style.background = "url(images/navB.jpg)";
		document.getElementById("td5").style.background = "url(images/navB.jpg)";
		document.getElementById("td6").style.background = "url(images/navA.jpg)";
		document.getElementById("td7").style.background = "url(images/navB.jpg)";
		document.getElementById("td8").style.background = "url(images/navB.jpg)";

		document.getElementById("table13").style.display = "";
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab6").style.display = "";
		document.getElementById("tab7").style.display = "none";
		document.getElementById("tab8").style.display = "none";
}

function loadFMoneyQuery(){ //国际资费查询 tab8
		document.getElementById("td1").style.background = "url(images/navB.jpg)";
		document.getElementById("td4").style.background = "url(images/navB.jpg)";
		document.getElementById("td5").style.background = "url(images/navB.jpg)";
		document.getElementById("td6").style.background = "url(images/navB.jpg)";
		document.getElementById("td7").style.background = "url(images/navB.jpg)";
		document.getElementById("td8").style.background = "url(images/navA.jpg)";
		document.getElementById("table13").style.display = "";
		document.getElementById("tab1").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab8").style.display = "";
		document.getElementById("tab7").style.display = "none";
		document.getElementById("tab6").style.display = "none";
}


function loadAddressQuery(){//邮政编码查询 tab7
		document.getElementById("td1").style.background = "url(images/navB.jpg)";
		document.getElementById("td4").style.background = "url(images/navB.jpg)";
		document.getElementById("td5").style.background = "url(images/navB.jpg)";
		document.getElementById("td6").style.background = "url(images/navB.jpg)";
		document.getElementById("td7").style.background = "url(images/navA.jpg)";
		document.getElementById("td8").style.background = "url(images/navB.jpg)";

		document.getElementById("table13").style.display = "";
	    document.getElementById("tab1").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab6").style.display = "none";
		document.getElementById("tab7").style.display = "";	
		document.getElementById("tab8").style.display = "none";
		
}

function back(){//返回
		document.getElementById("td1").style.background = "url(images/navB.jpg)";
		document.getElementById("td4").style.background = "url(images/navB.jpg)";
		document.getElementById("td5").style.background = "url(images/navB.jpg)";
		document.getElementById("td6").style.background = "url(images/navB.jpg)";
		document.getElementById("td7").style.background = "url(images/navB.jpg)";
		document.getElementById("td8").style.background = "url(images/navB.jpg)";
		document.getElementById("table13").style.display = "none";
	    document.getElementById("tab1").style.display = "none";
		document.getElementById("tab4").style.display = "none";
		document.getElementById("tab5").style.display = "none";
		document.getElementById("tab6").style.display = "none";
		document.getElementById("tab7").style.display = "none";	
		document.getElementById("tab8").style.display = "none";
		
}


