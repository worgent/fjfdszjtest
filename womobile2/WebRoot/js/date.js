
function y2k(number)
{
  return (number < 1000) ? number + 1900 : number;
}

function getdate()
{
  var now = new Date();
  var dd = now.getDate() , mt = now.getMonth() + 1 , yy = y2k(now.getYear()) , weekVal = now.getDay();
  var msg="";
  if (weekVal==0)
     msg="星期日";
  else if (weekVal==1)
     msg="星期一";
  else if (weekVal==2)
     msg="星期二";
  else if (weekVal==3)
     msg="星期三";
  else if (weekVal==4)
     msg="星期四";
  else if (weekVal==5)
     msg="星期五";
  else if (weekVal==6)
     msg="星期六";
  document.write(yy+"年"+mt+"月"+dd+"日"+"&nbsp;&nbsp;&nbsp;"+msg);
}
function getdatestring()
{
  var retvalue="";
  var now = new Date();
  var dd = now.getDate() , mt = now.getMonth() + 1 , yy = y2k(now.getYear()) , weekVal = now.getDay();
  var hours = now.getHours();
  var minutes = now.getMinutes();
  var seconds = now.getSeconds();
  retvalue=yy+"-"+mt+"-"+dd+"-"+hours+"-"+minutes+"-"+seconds;
  return retvalue;
}