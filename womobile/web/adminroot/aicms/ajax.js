////////////////////////////////////////异步通信处理////////////
function AJAX()
{//建立ajax对象
http_request = false;
if (window.XMLHttpRequest) 
    { // Mozilla, Safari, ...
    http_request = new XMLHttpRequest();
	if (http_request.overrideMimeType) {//设置MiME类别
            //有些版本的浏览器在处理服务器返回的未包含XML mime-type头部信息的内容时会报错，因此，要确保返回的内容包含text/xml信息。
            http_request.overrideMimeType("text/xml");
        }
    } else if (window.ActiveXObject) { // IE
                                       try {
                                             http_request = new ActiveXObject("Msxml2.XMLHTTP");
                                           } catch (e) {
                                                         try {
                                                               http_request = new ActiveXObject("Microsoft.XMLHTTP");
															   http_request.overrideMimeType('text/xml');
                                                             } catch (e) {
				                                                           document.write("对不起，你的浏览器不支持ajax！！");
				                                                          }
				                                        }
                                       }
 if (!http_request) 
    {
     alert('Giving up :( Cannot create an XMLHTTP instance');
     return false;
    }
return http_request;                 						   						  						   
}
///////////////结果处理示例//////////////////
/*
function control() //远程通信接收后的处理
{
 if (ajax.readyState == 4)
  {
   if (ajax.status == 200)
   {
	try{
	////////////////////////////////////
	  var result=""+ajax.responseText;
	//-----------------------------
	/////////////////////////////////////  
	   }catch(e)
	   {
	     document.write(e.description);
	    }
   }else
    {
     alert('远程通信发生错误！');
    }
  }else{
  //恐怕是失败了！！
  }
}
*/