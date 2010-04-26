function loadDefaultList(path) {
  $('#tab1').removeClass("f_tabClass2");
  $('#tab1').addClass("f_tabClass1");
  $('#tab2').removeClass("f_tabClass1");
  $('#tab2').addClass("f_tabClass2");
  $('#tab3').removeClass("f_tabClass1");
  $('#tab3').addClass("f_tabClass2");
  $('#defaultlist').html("<center>页面载入中...</center>"); 

  var url = getActionMappingURL("/guideToff",path);
  $.get(url,{action:'listDefault',date:new Date()},showResult);
  
  function showResult(res){
	  $('#defaultlist').html(res); 
  }
}

function loadFameList(path) {
  $('#tab1').removeClass("f_tabClass1");
  $('#tab1').addClass("f_tabClass2");
  $('#tab2').removeClass("f_tabClass2");
  $('#tab2').addClass("f_tabClass1");
  $('#tab3').removeClass("f_tabClass1");
  $('#tab3').addClass("f_tabClass2");
  

  $('#defaultlist').html("<center>页面载入中...</center>"); 

  var url = getActionMappingURL("/guideToff",path);
  $.get(url,{action:'listFame',date:new Date()},showResult);
  
  function showResult(res){
	  $('#defaultlist').html(res); 
  }
}

function loadDallyList(path) {
  $('#tab1').removeClass("f_tabClass1");
  $('#tab1').addClass("f_tabClass2");
  $('#tab2').removeClass("f_tabClass1");
  $('#tab2').addClass("f_tabClass2");
  $('#tab3').removeClass("f_tabClass2");
  $('#tab3').addClass("f_tabClass1");
  

  $('#defaultlist').html("<center>页面载入中...</center>"); 

  var url = getActionMappingURL("/guideToff",path);
  $.get(url,{action:'listDally',date:new Date()},showResult);
  
  function showResult(res){
	  $('#defaultlist').html(res); 
  }
}

function listMore(path) {
  $('#tab1').addClass("f_tabClass1");
  $('#tab2').addClass("f_tabClass2");
  $('#tab3').addClass("f_tabClass2");
 
  $('#defaultlist').html("<center>页面载入中...</center>"); 
  
  var url = getActionMappingURL("/guideToff",path);
  $.get(url,{action:'listMore',date:new Date()},showResult1);
  function showResult1(res){
	  $('#defaultlist').html(res); 
  }
}

function listFameMore(path) {
  $('#tab1').addClass("f_tabClass2");
  $('#tab2').addClass("f_tabClass1");
  $('#tab3').addClass("f_tabClass2");

  $('#defaultlist').html("<center>页面载入中...</center>"); 

  var url = getActionMappingURL("/guideToff",path);
  $.get(url,{action:'listFameMore',date:new Date()},showResult1);
  function showResult1(res){
	  $('#defaultlist').html(res); 
  }
}

function listDallyMore(path) {
  $('#tab1').addClass("f_tabClass2");
  $('#tab2').addClass("f_tabClass2");
  $('#tab3').addClass("f_tabClass1");

  $('#defaultlist').html("<center>页面载入中...</center>"); 
  
  var url = getActionMappingURL("/guideToff",path);
  $.get(url,{action:'listDallyMore',date:new Date()},showResult1);
  function showResult1(res){
	  $('#defaultlist').html(res); 
  }
}



