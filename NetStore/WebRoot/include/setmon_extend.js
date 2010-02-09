document.writeln('<div id=DateLayer style="position: absolute; width: 142; height: 23; z-index: 9998; display: none">');
document.writeln('<span id=tmpSelectYearLayer  style="z-index: 9999;position: absolute;top: 2; left: 18;display: none"></span>');
document.writeln('<span id=tmpSelectMonthLayer style="z-index: 9999;position: absolute;top: 2; left: 75;display: none"></span>');
document.writeln('<table border=0 cellspacing=1 cellpadding=0 width=142 height=23 bgcolor=#808080 onselectstart="return false">');
document.writeln('  <tr><td width=142 height=23 bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 width=140 height=23>');
document.writeln('      <tr align=center><td width=20 align=center bgcolor=#808080 style="font-size:12px;cursor: hand;color: #FFD700" ');
document.writeln('        onclick="PrevM()" title="上个月" Author=a><b Author=a>&lt;&lt;</b>');
document.writeln('        </td><td width=100 align=center style="font-size:12px;cursor:default" Author=a>');
document.writeln('        <span Author=a id=YearHead onclick="tmpSelectYearInnerHTML(this.innerText)"></span>&nbsp;年&nbsp;<span');
document.writeln('         id=MonthHead Author=a onclick="tmpSelectMonthInnerHTML(this.innerText)"></span>&nbsp;月</td>');
document.writeln('        <td width=20 bgcolor=#808080 align=center style="font-size:12px;cursor: hand;color: #FFD700" ');
document.writeln('         onclick="NextM()" title="下个月" Author=a><b Author=a>&gt;&gt;</b></td></tr>');
document.writeln('    </table></td></tr>');
document.writeln('<table border=1 cellspacing=1 cellpadding=0 width=100% bgcolor=#FFFFFF>');
document.writeln(' <tr><td Author=a align=center><input Author=a type=button value="< " title="上一年" onclick="PrevY()" ');
document.writeln(' onfocus="this.blur()" class="button">&nbsp;<input Author=a type=button value="上月" onclick="Today()" ');
document.writeln(' onfocus="this.blur()" title="上一个月" class="button">&nbsp;<input Author=a type=button value="确定" onclick="DayClick()" ');
document.writeln(' onfocus="this.blur()" title="选择此年月" class="button">&nbsp;<input Author=a ');
document.writeln(' type=button value=" >" title="下一年" onclick="NextY()"');
document.writeln(' onfocus="this.blur()" class="button"></td>');
document.writeln('</tr></table></td></tr></table>');
document.writeln('<iframe src="javascript:false" style="position:absolute; visibility:inherit; top:0px; left:0px; width:120px; height:20px; z-index:-1;"></iframe>');
document.writeln('</div>');

var outObject;        
var TheYear; 
var TheMonth; 
/*
callback method called after setmon , by redyc
*/
var callback;
function setMonExt(composite,callbackName){
	setmon(composite);
	callback=callbackName;
}
function setmon(tt,day) 
{
  if (arguments.length >  2){alert("对不起！传入本控件的参数太多！");return;}
  if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
  var dads  = document.all.DateLayer.style;var th = tt;
  var ttop  = tt.offsetTop;     
  var thei  = tt.clientHeight;  
  var tleft = tt.offsetLeft;    
  var ttyp  = tt.type;          
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = th ;
  dads.display = '';
  event.returnValue=false;
  var dd = day;
  TheYear=new Date().getFullYear(); 
  TheMonth=new Date().getMonth(); 
if(TheMonth>1){TheMonth--}else{TheYear--;TheMonth=12;}
  WriteHead(TheYear,TheMonth);     	
}
function setpremon(tt,day) 
{
  if (arguments.length >  2){alert("对不起！传入本控件的参数太多！");return;}
  if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
  var dads  = document.all.DateLayer.style;var th = tt;
  var ttop  = tt.offsetTop;     
  var thei  = tt.clientHeight;  
  var tleft = tt.offsetLeft;    
  var ttyp  = tt.type;          
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = th ;
  dads.display = '';
  event.returnValue=false;
  var dd = day;
  TheYear=new Date().getFullYear()-1; 
  TheMonth=new Date().getMonth()+1; 
if(TheMonth>1){TheMonth--}else{TheYear--;TheMonth=12;}
  WriteHead(TheYear,TheMonth);     	
}
function document.onclick() 
{ 
  with(window.event.srcElement)
  { if (getAttribute("Author")==null && tagName != "INPUT")
    document.all.DateLayer.style.display="none";
  }
}
function WriteHead(yy,mm)  
  { document.all.YearHead.innerText  = yy;
    document.all.MonthHead.innerText = mm;
  }
function tmpSelectYearInnerHTML(strYear)
{
  if (strYear.match(/\D/)!=null){alert("年份输入参数不是数字！");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;}
  var n = m - 10;
  if (n < 1000) n = 1000;
  if (n + 26 > 9999) n = 9974;
  var s = "<select Author=a name=tmpSelectYear style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
     s += "TheYear = this.value;WriteHead(TheYear,TheMonth); '>\r\n";
  var selectInnerHTML = s;
  for (var i = n; i < n + 26; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option value='" + i + "' selected>" + i + "年" + "</option>\r\n";}
    else {selectInnerHTML += "<option value='" + i + "'>" + i + "年" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}
function tmpSelectMonthInnerHTML(strMonth)
{
  if (strMonth.match(/\D/)!=null){alert("月份输入参数不是数字！");return;}
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
  var s = "<select Author=a name=tmpSelectMonth style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
     s += "TheMonth = this.value;DayClick()'>\r\n";
  var selectInnerHTML = s;
  for (var i = 1; i < 13; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option value='"+i+"' selected>"+i+"月"+"</option>\r\n";}
    else {selectInnerHTML += "<option value='"+i+"'>"+i+"月"+"</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectMonthLayer.style.display="";
  document.all.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectMonth.focus();
}
function closeLayer()           
  {
    document.all.DateLayer.style.display="none";
  }
function document.onkeydown()
  {
    if (window.event.keyCode==27)document.all.DateLayer.style.display="none";
  }
function IsPinYear(year)         
  {
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
  }

function PrevY() 
  {
    if(TheYear > 999 && TheYear <10000){TheYear--;}
    else{alert("年份超出范围（1000-9999）！");}
    WriteHead(TheYear,TheMonth);
  }
function NextY() 
  {
    if(TheYear > 999 && TheYear <10000){TheYear++;}
    else{alert("年份超出范围（1000-9999）！");}
    WriteHead(TheYear,TheMonth);
  }
function Today() 
  {
    TheYear = new Date().getFullYear();
    TheMonth = new Date().getMonth();
if(TheMonth>1){TheMonth--}else{TheYear--;TheMonth=12;}
    WriteHead(TheYear,TheMonth);
    DayClick();
  }
function PrevM() 
  {
    if(TheMonth>1){TheMonth--}else{TheYear--;TheMonth=12;}
    WriteHead(TheYear,TheMonth);
  }
function NextM() 
  {
    if(TheMonth==12){TheYear++;TheMonth=1}else{TheMonth++}
    WriteHead(TheYear,TheMonth);
  }

function DayClick() 
{
  var yy = TheYear;
  var mm = TheMonth;
  if (mm < 10){mm = "0" + mm;}
  if (outObject)
  {
    outObject.value= yy +""+  mm  ;
    closeLayer(); 
    //自己添加的
    callback();
  }
  else {closeLayer(); alert("您所要输出的控件对象并不存在！");}
}
   WriteHead(TheYear,TheMonth);