//--

//get the extend value
function getExtName(fileName) {
	if (fileName.lastIndexOf(".") < 0) {
		return "";
	}
	return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length).toLowerCase();
}

//get the action mapping URL
function getActionMappingURL(action,value) {
	var servletMapping = "*.do";
	var queryString;
	var question = action.indexOf("?");
	if (question >= 0) {
		queryString = action.substring(question);
	}
	var actionMapping = getActionMappingName(action);
	if (startsWith(servletMapping, "*.")) {
		value += actionMapping;
		value += servletMapping.substring(1);
	} else {
		if (endsWith(servletMapping, "/*")) {
			value += servletMapping.substring(0, servletMapping.length - 2);
			value += actionMapping;
		} else {
			if (servletMapping == "/") {
				value += actionMapping;
			}
		}
	}
	if (queryString != undefined) {
		value += queryString;
	}
	return value;
}

function getActionMappingName(action) {
	var value = action;
	var question = action.indexOf("?");
	if (question >= 0) {
		value = value.substring(0, question);
	}
	var slash = value.lastIndexOf("/");
	var period = value.lastIndexOf(".");
	if ((period >= 0) && (period > slash)) {
		value = value.substring(0, period);
	}
	return startsWith(value, "/") ? value : ("/" + value);
}

function startsWith(txt, tar) {
	if (txt.indexOf(tar) == 0) {
		return true;
	} else {
		return false;
	}
}

function getResponseJsonMsgsCodeid(responseText) {
  var jsonObj = eval('(' + responseText + ')');
  return jsonObj.codeid;
}

var JsonMsgObj = function(responseText) {
  this.json = eval('(' + responseText + ')');
}

JsonMsgObj.prototype.getCodeid = function() {
  	return this.json.codeid;
}

JsonMsgObj.prototype.getMessage = function() {
  return this.json.message;
}

JsonMsgObj.prototype.getText = function() {
  return this.json.text;
}


//-->

