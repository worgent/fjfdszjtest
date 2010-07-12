function Keyboard() {
	this.keyboard = document.getElementById("vs_keyboard_xdh_01");
	this.keyboardDiv = null;
	this.capslock = false;
	this.inputEl = null;
	this.keytitle = null;
	if (!this.keyboard) {
		var arr = [
				"<table width=\"100%\" height=\"100%\" border=\"0\"><tr><td align=\"center\">",
				"<table cellpadding=\"0\" id=\"vs_keyboard_xdh_01\" class=\"keyboard\" cellspacing=\"0\" ",
				"bordercolor=\"#dfdfdf\" border=\"1\" style=\"border-collapse: collapse;\">",
				"<thead><tr><td colspan=\"11\" style=\"height:90px;padding:0px\">",
				"<textarea id=\"vs_keyboard_xdh_01_textarea\" style=\"height:100%;width:100%;border:0px;overflow:auto\" >",
				"</textarea></td></tr></thead>",

				"<tbody><tr><td>Q</td><td>W</td><td>E</td><td>R</td><td>T</td>",
				"<td>Y</td><td>U</td><td>1</td><td>2</td><td>3</td>",
				"<td value=\"backspace\" style=\"padding-left:10px;padding-right:10px\">退格</td></tr><tr><td>I</td>",
				"<td>O</td><td>P</td><td>A</td><td>S</td><td>D</td>",
				"<td>F</td><td>4</td><td>5</td><td>6</td><td value=\"enter\" style=\"padding-left:10px;padding-right:10px\">换行</td>",
				"</tr><tr><td>G</td><td>H</td><td>J</td><td>K</td><td>L</td><td>Z</td>",
				"<td>X</td><td>7</td><td>8</td><td>9</td><td value=\"caps\" style=\"padding-left:7px;padding-right:7px\">大小写</td>",
				"</tr><tr><td>C</td><td>V</td><td>B</td><td>N</td><td>M</td>",
				"<td>(</td><td>)</td><td>+</td><td>-</td><td>.</td><td value=\"clear\" style=\"padding-left:10px;padding-right:10px\">清除</td>",
				"</tr><tr><td colspan=\"10\" value=\"space\">空格</td><td value=\"ok\" style=\"padding-left:10px;padding-right:10px\">确定</td></tr></tbody></table>",
				"</td></tr></table>" ];
		var str = arr.join("");

		this.keyboardDiv = document.createElement("DIV");
		document.body.appendChild(this.keyboardDiv);
		this.keyboardDiv.innerHTML = str;
		this.keyboardDiv.setAttribute("id", "vs_keyboard_xdh_01_div")

		this.keyboard = document.getElementById("vs_keyboard_xdh_01");
		this.keyboardDiv.style.position = "absolute";
		this.keyboardDiv.style.zIndex = 9;
		this.keyboardDiv.style.display = "none";
		this.keyboardDiv.style.left = 0;
		this.keyboardDiv.style.top = 0;
		this.keyboardDiv.style.border = "1px solid red";
		this.keyboardDiv.style.backgroundColor = "#FFFFFF";

		var rows = this.keyboard.tBodies[0].rows;
		var kb = this;
		for ( var i = 0; i < rows.length; i++) {
			var cells = rows[i].cells;
			for ( var n = 0; n < cells.length; n++) {
				cells[n].onclick = function(event) {
					kb.click(event);
				};
				cells[n].onmousedown=function(event){
					kb.onmousedown(event);
				}
				
				cells[n].onmouseup=function(event){
					kb.onmouseup(event);
				}
			}
		}

		this.keytitle = document.createElement("SPAN");
		this.keytitle.className = "keytitle";
		this.keytitle.style.display = "none";
		document.body.appendChild(this.keytitle);
		
		this.keyboard.ondragstart=function(){return false;};
		this.keyboard.onselectstart=function(){return false;};
		this.keyboard.onselect=function(){document.selection.empty();};
		
	}
	this.keyboardDiv = document.getElementById("vs_keyboard_xdh_01_div");
	this.textarea = document.getElementById("vs_keyboard_xdh_01_textarea");
	this.keyboardDiv.onmouseup=function(){kb.keytitle.style.display="none";};
	this.keyboardDiv.onmouseout=function(){kb.keytitle.style.display="none";};
}

Keyboard.prototype.show = function() {
	if (this.keyboardDiv.style.display == "none") {
		this.keyboardDiv.style.display = "";
		this.keyboardDiv.style.width = document.body.offsetWidth-20;
		this.keyboardDiv.style.height = document.body.offsetHeight-2;
	}
}

Keyboard.prototype.hide = function() {
	if (this.keyboardDiv.style.display == "")
		this.keyboardDiv.style.display = "none";
}

Keyboard.prototype.onmousedown = function(event) {
	var el = this.getSrcElement(event);

	var v = el.getAttribute("value");
	if (v)
		return;
	v = el.innerText;
	var x = 0, y = 0;
	if (window.event) {
		x = window.event.x;
		y = window.event.y;
	} else {
		x = event.pageX;
		y = event.pageY;
	}
    this.keytitle.innerText=v;
    this.keytitle.style.left=x-20;
    this.keytitle.style.top=y-60;
    this.keytitle.style.display="";
}

Keyboard.prototype.onmouseup = function(event) {
	var el = this.getSrcElement(event);

	var v = el.getAttribute("value");
	if (v)
		return;
	v = el.innerText;
	var x = 0, y = 0;
	if (window.event) {
		x = window.event.x;
		y = window.event.y;
	} else {
		x = event.pageX;
		y = event.pageY;
	}
    this.keytitle.style.display="none";
}

Keyboard.prototype.click = function(event) {
	var el = this.getSrcElement(event);

	var v = el.getAttribute("value");
	if (!v)
		v = el.innerText;

	switch (v) {
	case "caps":

		if (el.className == "checked") {
			this.capslock = false;
			el.className = "";
		} else {
			this.capslock = true;
			el.className = "checked";
		}
		break;
	case "backspace":
		var v = this.textarea.value;
		if (v.length < 1)
			break;
		this.textarea.value = v.substring(0, v.length - 1);
		break;
	case "enter":
		
		this.textarea.value = this.textarea.value + "\n";
		break;
	case "space":
		this.textarea.value = this.textarea.value + " ";
		break;
	case "ok":
		this.setInputValue(this.textarea.value);
		this.hide();
		break;
	case "clear":
		this.textarea.value = "";
		break;
	default:

		if (this.capslock != true)
			v = v.toLowerCase();

		this.textarea.value = this.textarea.value + v;
		break;
	}

}

Keyboard.prototype.getSrcElement = function(event) {
	var el = null;
	if (window.event)
		el = window.event.srcElement;
	else
		el = event.target;

	return el;
}

Keyboard.prototype.getInputValue = function() {
	switch (this.inputEl.tagName) {
	case "TEXTAREA":
		return this.inputEl.innerText;
	default:
		return this.inputEl.value;
	}
}

Keyboard.prototype.setInputValue = function(v) {
	switch (this.inputEl.tagName) {
	case "TEXTAREA":
		this.inputEl.innerText = v;
		break;
	default:
		this.inputEl.value = v;
		break;
	}
}

Keyboard.prototype.register = function(el) {

	var kb = this;

	el.onfocus = function(event) {

		var c = kb.getSrcElement(event);
		kb.inputEl = el;
		kb.textarea.value = kb.getInputValue();
		kb.show();
		kb.keyboard.focus();
	}

}
