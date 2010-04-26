function SimpleHtmlAjax(){
  var that=this;
  var getXMLRequest=function(){
	if(typeof XMLHttpRequest != "undefined"){
		return new XMLHttpRequest();
	}else if(window.ActiveXObject){
		var aVersions=["MSXML2.XMLHttp.5.0","MSXML2.XMLHttp.4.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp","Microsoft.XMLHttp"];
		for (var i=0;i<aVersions.length;i++){
			try{
				var xmlHttp = new ActiveXObject(aVersions[i]);
				return xmlHttp;
			}catch(error){
			 //alert(error.message);
			}
		}
	}
	return false;
  }
  this.work=function(method,url,param,dataHandler,async){
	var xmlHttpRequest=getXMLRequest();
	xmlHttpRequest.open(method,url,async);
	xmlHttpRequest.setRequestHeader("Content-Type","text/html;charset=utf-8");
	if(async){
		xmlHttpRequest.onreadystatechange=function(){
			if(xmlHttpRequest.readyState==4){
				if(xmlHttpRequest.status==200){
					dataHandler(xmlHttpRequest.responseText);
				}else{
					alert("response data error.");
				}
			}
		}
		xmlHttpRequest.send(param);
	}else{
		xmlHttpRequest.send(param);
		dataHandler(xmlHttpRequest.responseText);
	}
  }
  this.getXmlObject=function(async){
	var xmlDom=null;
	if(window.ActiveXObject){
		xmlDom=new ActiveXObject("Microsoft.XMLDOM");
		xmlDom.async=async;
	}else{
		xmlDom=document.implementation.createDocument("", "", null);
		XMLDocument.prototype.loadXML=function(xmlString){
			var childNodes = this.childNodes;
      			for(var i=childNodes.length-1;i>=0;i--){
            			this.removeChild(childNodes[i]);
  			}
        		var dp=new DOMParser();
        		var newDOM=dp.parseFromString(xmlString, "text/xml");
        		var newElt=this.importNode(newDOM.documentElement,true);
        		this.appendChild(newElt);
  		}
		xmlDom.async=async;
	}
	return xmlDom;
  }
  this.getXmlDocument=function(xmlObj){
  	if(window.ActiveXObject){
  		return xmlObj.documentElement;
	}else{
		return xmlObj.childNodes[0];
  	}
  }
  this.loadXmlFromFile=function(filePath,async,dataHandler){
	var xmlDom=that.getXmlObject(async);
  	//alert(xmlDom);
	//xmlDom.async=async;
	if(!window.ActiveXObject){
		if(async){
			xmlDom.onload=function(){dataHandler(xmlDom.childNodes[0]);}
			xmlDom.load(filePath);
		}else{
			xmlDom.load(filePath);
			dataHandler(xmlDom.childNodes[0]);
		}

	}else{
		xmlDom.load(filePath);
		if(xmlDom.async){xmlDom.onreadystatechange=function(){if(xmlDom.readyState==4){operateXmlDom(xmlDom);}}
		}else{operateXmlDom(xmlDom);}
		function operateXmlDom(xmlDom){
			if(xmlDom.parseError.errorCode!=0){alert("An Error Occurred:"+xmlDom.parseError.reason);err(xmlDom.parseError.reason);}
			dataHandler(xmlDom.documentElement);
		}

	}
  }
}