
var x0=0,y0=0,x1=0,y1=0;
var offx=6,offy=6;
var moveable=false;
var hover='orange',normal='#336699';//color;
var index=10000;//z-index;
//start move;
function startDrag(obj)
{
if(event.button==1)
{
//lock title;
obj.setCapture();
//dim object;
var win = obj.parentNode;
var sha = win.nextSibling;
//record mouse and div position
x0 = event.clientX;
y0 = event.clientY;
x1 = parseInt(win.style.left);
y1 = parseInt(win.style.top);
//reocrd color;
normal = obj.style.backgroundColor;
//alert style;
obj.style.backgroundColor = hover;
win.style.borderColor = hover;
obj.nextSibling.style.color = hover;
sha.style.left = x1 + offx;
sha.style.top = y1 + offy;
moveable = true;
}
}
//move;
function drag(obj)
{
if(moveable)
{
var win = obj.parentNode;
var sha = win.nextSibling;
win.style.left = x1 + event.clientX - x0;
win.style.top = y1 + event.clientY - y0;
sha.style.left = parseInt(win.style.left) + offx;
sha.style.top = parseInt(win.style.top) + offy;
}
}
//stop move;
function stopDrag(obj)
{
if(moveable)
{
var win = obj.parentNode;
var sha = win.nextSibling;
var msg = obj.nextSibling;
win.style.borderColor = normal;
obj.style.backgroundColor = normal;
msg.style.color = normal;
sha.style.left = obj.parentNode.style.left;
sha.style.top = obj.parentNode.style.top;
obj.releaseCapture();
moveable = false;
}
}
//get focus;
function getFocus(obj)
{
if(obj.style.zIndex!=index)
{
index = index + 2;
var idx = index;
obj.style.zIndex=idx;
obj.nextSibling.style.zIndex=idx-1;
}
}
//minimize;
function min(obj)
{
var win = obj.parentNode.parentNode;
var sha = win.nextSibling;
var tit = obj.parentNode;
var msg = tit.nextSibling;
var flg = msg.style.display=="none";
if(flg)
{
win.style.height = parseInt(msg.style.height) + parseInt(tit.style.height) + 2*2;
sha.style.height = win.style.height;
msg.style.display = "block";
obj.innerHTML = "0";
}
else
{
win.style.height = parseInt(tit.style.height) + 2*2;
sha.style.height = win.style.height;
obj.innerHTML = "2";
msg.style.display = "none";
}
}
//create object;
function xWin(id,w,h,l,t,tit,msg)
{
index = index+2;
this.id = id;
this.width = w;
this.height = h;
this.left = l;
this.top = t;
this.zIndex = index;
this.title = tit;
this.message = msg;
this.obj = null;
this.bulid = bulid;
this.bulid();
}
//initialization;
function bulid()
{
var str = ""
+ "<div id=xMsg" + this.id + " "
+ "style='"
+ "z-index:" + this.zIndex + ";"
+ "width:" + this.width + ";"
+ "height:" + this.height + ";"
+ "left:" + this.left + ";"
+ "top:" + this.top + ";"
+ "background-color:" + normal + ";"
+ "color:" + normal + ";"
+ "font-size:8pt;"
+ "font-family:Tahoma;"
+ "position:absolute;"
+ "cursor:default;"
+ "border:2px solid " + normal + ";"
+ "' "
+ "onmousedown='getFocus(this)'>"
+ "<div "
+ "style='"
+ "background-color:" + normal + ";"
+ "width:" + (this.width-2*2) + ";"
+ "height:20;"
+ "color:white;"
+ "' "
+ "onmousedown='startDrag(this)' "
+ "onmouseup='stopDrag(this)' "
+ "onmousemove='drag(this)' "
+ "ondblclick='min(this.childNodes[1])'"
+ ">"
+ "<span style='width:" + (this.width-2*12-4) + ";padding-left:3px;'>" + this.title + "</span>"
+ "<span style='width:12;border-width:0px;color:white;font-family:webdings;' onclick='min(this)'>0</span>"
+ "<span style='width:12;border-width:0px;color:white;font-family:webdings;' onclick='ShowHide(\""+this.id+"\",null)'>r</span>"
+ "</div>"
+ "<div style='"
+ "width:100%;"
+ "height:" + (this.height-20-4) + ";"
+ "background-color:white;"
+ "line-height:14px;"
+ "word-break:break-all;"
+ "padding:3px;"
+ "'>" + this.message + "</div>"
+ "</div>"
+ "<div id=xMsg" + this.id + "bg style='"
+ "width:" + this.width + ";"
+ "height:" + this.height + ";"
+ "top:" + this.top + ";"
+ "left:" + this.left + ";"
+ "z-index:" + (this.zIndex-1) + ";"
+ "position:absolute;"
+ "background-color:black;"
+ "filter:alpha(opacity=40);"
+ "'></div>";
document.body.insertAdjacentHTML("beforeEnd",str);
}
//display and hide window
function ShowHide(id,dis){
var bdisplay = (dis==null)?((document.getElementById("xMsg"+id).style.display=="")?"none":""):dis
document.getElementById("xMsg"+id).style.display = bdisplay;
document.getElementById("xMsg"+id+"bg").style.display = bdisplay;
}