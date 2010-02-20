/*
  必须填写的字段
  作者:qsy
*/
var
  // 必填字段
  mustFillArray=[];
  mustFillCaptionArray=[];
  // 必须为整数字段
  mustIntArray=[];
  mustIntCaptionArray=[];
  mustFloatArray=[];
  mustFloatCaptionArray=[];

  pageType="";

  // 当前主键值
  seqn="";
  // 当前模式
  mode="";
  // 当前详表主键值
  detailSeqn="";

  //
  ownValidate=false;

/*
  功能:记录哪条记录被选
  作者:qsy
*/
function doWhich(){
        var i=0;
            // 单条
            if (document.theForm.radio.length==null){
                document.theForm.hWhich.value=document.theForm.radio.value;
            }
            // 多条
            else{
              while(true){
                if(document.theForm.radio[i].checked){
                  document.theForm.hWhich.value=document.theForm.radio[i].value;
                  break;
                }
                i++;
              }
            }
}
/*
  功能:选择一行信息
  作者:qsy
*/
function doChoose(myArray){
   window.returnValue=myArray;
   self.close();
}

/*
  功能:查找
  作者:qsy
*/
function doFind() {
  if (parames==""){
    formSubmit(curPage+parames+"?mode=query");
  }
  else{
    formSubmit(curPage+parames+"&mode=query");
  }
}


/*
  功能:增加
  作者:qsy
*/
function doAdd()
{
  formSubmit(curPage+findparames+"&mode=add");
}

/*
  功能:编辑
  作者:qsy
*/
function doEdit()
{
  if (document.theForm.hWhich.value=="") {
    alert("请选择一条记录!");
    return false;
  }
  seqn=document.theForm.hWhich.value;
  formSubmit(curPage+findparames+"&mode=edit"+"&seqn="+seqn);
}

/*
  功能:删除
  作者:qsy
*/
function doDel()
{
  if (document.theForm.hWhich.value=="") {
    alert("请选择一条记录!");
    return false;
  }
  if (confirm("确定要删除吗?")){
  seqn=document.theForm.hWhich.value;
  formSubmit(curPage+findparames+"&mode=del"+"&seqn="+seqn);
  }
}
/*
  功能:察看
  作者:qsy
*/
function doView()
{
  if (document.theForm.hWhich.value=="") {
    alert("请选择一条记录!");
    return false;
  }
  seqn=document.theForm.hWhich.value;
  formSubmit(curPage+findparames+"&mode=view"+"&seqn="+seqn);
}
/*
  功能:打印
  作者:qsy
*/
function doDetailPrint()
{
  window.open(curPage+findparames+"&mode=query&action=print&filename=wlprint.pdf&seqn="+seqn);
}
function doPrint()
{
//  a = curPage.indexOf(".faces");
//  window.open(curPage.substring(0,a)+"_print.faces"+findparames+"&mode=query&filename=dummy.pdf");
  if (pageType=="masterDetail"){
    if (document.theForm.hWhich.value=="") {
      alert("请选择一条记录!");
      return false;
    }
    seqn=document.theForm.hWhich.value;
    window.open(curPage+findparames+"&mode=query&action=print&filename=wlprint.pdf"+"&seqn="+seqn);
  }
  else {
    //window.open(curPage+findparames+"&mode=query&action=print&filename=wlprint.pdf");
    //window.open(curPage+findparames+"&mode=query&action=print&filename=wlprint.pdf");
    window.open(curPage+findparames+"&action=print");
  }
}
function doQryPrint()
{

//  a = curPage.indexOf(".faces");
//  window.open(curPage.substring(0,a)+"_print.faces"+findparames+"&mode=query&filename=dummy.pdf");
  window.open(curPage+findparames+"&mode=query&action=print&filename=wlprint.pdf");
}
/*
  功能:校验
  作者:qsy
*/
function doValidate(){
  for (var index = 0; index < mustFillArray.length; index++) {
    sValue=document.theForm(mustFillArray[index]).value;
    if (sValue==""){
      alert(mustFillCaptionArray[index]+"不能为空！");
      return false;
    }
  }
  for (var index = 0; index < mustIntArray.length; index++) {
    sValue=document.theForm(mustIntArray[index]).value;
    if (!isValidInt(sValue)){
      alert(mustIntCaptionArray[index]+"必须为整数!");
      return false;
    }
  }
  for (var index = 0; index < mustFloatArray.length; index++) {
    sValue=document.theForm(mustFloatArray[index]).value;
    if (!isValidDecimal(sValue)){
      alert(mustFloatCaptionArray[index]+"必须为数字!");
      return false;
    }
  }

  if (ownValidate) {
       if (!doMyValidate()){
         return false;
       }
  }
  return true;
}

/*
  功能:保存
  作者:qsy
*/
function doSave()
{
  if (!(doValidate())) {
    return false;
  }

  aquerystring="";
  if (findparames==""){
    aquerystring="?action=save";
  }
  else{
    aquerystring="&action=save";
  }

  aquerystring=aquerystring+"&mode="+mode  ;
  if (seqn!=""){
    aquerystring=aquerystring+"&seqn="+seqn;
  }
  formSubmit(curPage+findparames+aquerystring);
}
/*
  功能:操作　
  作者:qsy
*/
function doAction(actionname)
{
  if ((actionname=="SaveAttemper") || (actionname=="Attemper")){
     if (!(doValidate())) {
         return false;
     }
  }


  aquerystring="";
  if (findparames==""){
    aquerystring="?action="+actionname;
  }
  else{
    aquerystring="&action="+actionname;
  }

  aquerystring=aquerystring+"&mode="+mode  ;
  if (seqn!=""){
    aquerystring=aquerystring+"&seqn="+seqn;
  }
  formSubmit(curPage+findparames+aquerystring);
}


/*
  功能:详单增加
  作者:qsy
*/
function doDetailAdd()
{
  formSubmit(curPage+detailParames+"&detailMode=add");
}

/*
  功能:详单编辑
  作者:qsy
*/
function doDetailEdit()
{
  if (document.theForm.hWhich.value=="") {
    alert("请选择一条记录!");
    return false;
  }
  detailSeqn=document.theForm.hWhich.value;
  formSubmit(curPage+detailParames+"&detailMode=edit"+"&detailSeqn="+detailSeqn);
}

/*
  功能:详单查看
  作者:qsy
*/
function doDetailView()
{
  if (document.theForm.hWhich.value=="") {
    alert("请选择一条记录!");
    return false;
  }
  detailSeqn=document.theForm.hWhich.value;
  formSubmit(curPage+detailParames+"&detailMode=view"+"&detailSeqn="+detailSeqn);
}


/*
  功能:详单删除
  作者:qsy
*/
function doDetailDel()
{
  if (document.theForm.hWhich.value=="") {
    alert("请选择一条记录!");
    return false;
  }
  detailSeqn=document.theForm.hWhich.value;
  formSubmit(curPage+detailParames+"&detailMode=del"+"&detailSeqn="+detailSeqn);
}
/*
  功能:详单保存
  作者:qsy
*/
function doDetailSave()
{
  if (!(doValidate())) {
    return false;
  }
  aquerystring="";
  if (parames==""){
    aquerystring="?action=save";
  }
  else{
    aquerystring="&action=save";
  }
//  aquerystring=aquerystring+"&mode=edit&detailMode="+mode;
  if (detailSeqn!=""){
    aquerystring=aquerystring+"&detailSeqn="+detailSeqn;
  }
  formSubmit(curPage+detailParames+aquerystring);
}

/*
  功能:提供各类信息(如产品,仓库)的选择界面,进行选择
  @param sField    String 使用字段
  @param sDisField String 显示字段
  @param facename  String jsp页面
  @param selname   String 选择名字
  @param selFillCompArray  Array 相关字段填写控件
  @param selFillIndexArray Array 相关字段填写索引
  作者:qsy
*/
function selectseqn(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam){
 if (facename==='selectReceiveUnit'){
 selectseqnwindow(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam,'800','550');
 }
 else
 {
 selectseqnwindow(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam,'520','550');
 }
}
function selectseqnwindow(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam,swidth,sheight){
  if (sDisField==''){
    sDisField=sField;
  }
var
  sValue=document.theForm(sDisField).value;
  if (sValue=="＝请选择＝") {
      sValue="";
  }
var
  surl="../inc/"+facename+".faces?"+selname+"="+sValue+sparam;
  sReturn=window.showModalDialog(surl,"asd",'dialogHeight:'+sheight+'px;dialogWidth:'+swidth+'px;status:no;help:no;');

  if(sReturn!=null){
    if (sDisField!="") {
      document.all(sDisField).value=sReturn[2];
    }
    document.all(sField).value=sReturn[0];
    for (var index = 0;
    index < selFillCompArray.length; index++) {
      document.theForm(selFillCompArray[index]).value=sReturn[selFillIndexArray[index]];
    }
  }
  else
  {
    if (sDisField!="") {
      document.all(sDisField).value="";
    }
    document.all(sField).value="";
    for (var index = 0; index < selFillCompArray.length; index++) {
      document.theForm(selFillCompArray[index]).value="";
    }
  }


}
/*
  功能:提供各类信息(如产品,仓库)的选择界面,进行选择
  @param sField    String 使用字段
  @param sDisField String 显示字段
  @param facename  String jsp页面
  @param selname   String 选择名字
  @param selFillCompArray  Array 相关字段填写控件
  @param selFillIndexArray Array 相关字段填写索引
  作者:qsy
  修改:szj作用是多选处理  2008-06-09
*/
function selectseqnex(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam){
 if (facename==='selectReceiveUnit'){
 selectseqnwindowex(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam,'800','550');
 }
 else
 {
 selectseqnwindowex(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam,'520','550');
 }
}
function selectseqnwindowex(sField,sDisField,facename,selname,selFillCompArray,selFillIndexArray,sparam,swidth,sheight){
  if (sDisField==''){
    sDisField=sField;
  }
var
  sValue=document.theForm(sDisField).value;
  if (sValue=="＝请选择＝") {
      sValue="";
      document.all(sDisField).value="";
      document.all(sField).value="";
  }
var
  surl="../inc/"+facename+".faces?"+selname+"="+sValue+sparam;
  sReturn=window.showModalDialog(surl,"asd",'dialogHeight:'+sheight+'px;dialogWidth:'+swidth+'px;status:no;help:no;');

  if(sReturn!=null){
    if (sDisField!="") {
      if(document.all(sDisField).value.indexOf(","+sReturn[2]+",")>0||document.all(sDisField).value.indexOf(sReturn[2]+",")==0)
      {
       	if(document.all(sDisField).value.indexOf(sReturn[2]+",")==0)
      	document.all(sDisField).value=document.all(sDisField).value.replace(sReturn[2]+",","");
      	else if(document.all(sDisField).value.indexOf(","+sReturn[2]+",")>0)
      	document.all(sDisField).value=document.all(sDisField).value.replace(","+sReturn[2]+",",",");
      }
      else
      {
      	document.all(sDisField).value+=sReturn[2]+",";
      }
    }
    if(document.all(sField).value.indexOf(","+sReturn[0]+",")>0||document.all(sField).value.indexOf(sReturn[0]+",")==0)
    {
       	if(document.all(sField).value.indexOf(sReturn[0]+",")==0)
      	document.all(sField).value=document.all(sField).value.replace(sReturn[0]+",","");
      	else if(document.all(sField).value.indexOf(","+sReturn[0]+",")>0)
      	document.all(sField).value=document.all(sField).value.replace(","+sReturn[0]+",",",");
    }
    else
    {
    	document.all(sField).value+=sReturn[0]+",";
    }
  }
  else
  {
    if (sDisField!="") {
      document.all(sDisField).value="";
    }
    document.all(sField).value="";
    for (var index = 0; index < selFillCompArray.length; index++) {
      document.theForm(selFillCompArray[index]).value="";
    }
  }
}

function selectVal(values,selname){
var
  sValue=document.theForm(selname).value;
  if (sValue=="＝请选择＝") {
      sValue="";
  }
var
  surl="../inc/selVal.faces?values="+values+"&"+selname+"="+sValue;
  sReturn=window.showModalDialog(surl,"asd",'dialogHeight:80px;dialogWidth:40px;status:no;help:no;');

  if(sReturn!=null){
    document.all(selname).value=sReturn[0];
  }
}

/*
function isInt(iNumber)
{

  if (isNaN(iNumber))
  {
	  return false;
  }
  else if (parseInt(iNumber))
  {
      return true;
  }
  else
  {
      return false;
  }


}

function isNumeric(iNumber,IsPlus)
{

 if (isNaN(iNumber))
 {
	 return false;
 }
 else if ((parseFloat(iNumber)>0 && IsPlus==1) ||(parseFloat(iNumber)<0 && IsPlus==-1)|| (IsPlus==0))
 {
    return true;
 }
 else
 {
 	 return false;
 }



}
*/
 function isEmpty(strData)
{
	if (strData=="")
	{
		return true;
	}

}

function formSubmit(strUrl)
{
   document.theForm.action=strUrl;
   document.theForm.submit();
}

function compareTwoDate(startDate,endDate,startDesc,endDesc){
/*
  判断开始时间和结束时间的大小
*/

  if(!isEmpty(startDate)){
       if(!isValidDate(startDate)){
         alert("您输入的"+startDesc+"格式不正确,输入的格式应为YYYY-MM-DD");
         return false;
       }
   }

 if(!isEmpty(endDate)){
       if(!isValidDate(endDate)){
         alert("您输入的"+endDesc+"格式不正确,输入的格式应为YYYY-MM-DD");
         return false;
       }
  }


   if(!isEmpty(startDate) && !isEmpty(endDate)){
         date1=returnDate(startDate);
         date2=returnDate(endDate);
         sStart=date1.getYear()+"";
         sEnd=date2.getYear()+"";

         if(date1.getMonth()<10){
          sStart+="0"+date1.getMonth();
         }
         else{
          sStart+=date1.getMonth();
         }

         if(date1.getDate()<10){
           sStart+="0"+date1.getDate();
         }
         else{
           sStart+=date1.getDate();
         }

         if(date2.getMonth()<10){
          sEnd+="0"+date2.getMonth();
         }
         else{
          sEnd+=date2.getMonth();
         }

         if(date2.getDate()<10){
           sEnd+="0"+date2.getDate();
         }
         else{
           sEnd+=date2.getDate();
         }

         if(parseInt(sStart)>parseInt(sEnd)){
           alert(startDesc+"必须小于"+endDesc)
           return false;
         }

   }
	return true;

 }


  function returnDate(strValue){
 /*
   返回时间对象
 */
    var index=strValue.indexOf("-");
    var year=strValue.substring(0,index);
    strValue=strValue.substring(index+1,strValue.length);
    index=strValue.indexOf("-");
    var month=strValue.substring(0,index);
    var day=strValue.substring(index+1,strValue.length);
    return new Date(year,month,day);

 }

  function isValidDate(theField)
{
	var s1, s2, sDate, sMonth, sYear;
	var inDate = theField;
	if (inDate.substring(4,5)=="-"||inDate.substring(4,5)=="/")
	{
	  s1=4;
	}
	else
	{
	  return false;
	}

	if (inDate.substring(6,7)=="-"||inDate.substring(6,7)=="/")
	{
	 s2=6;
	 }
	else if (inDate.substring(7,8)=="-" || inDate.substring(7,8)=="/")
	{
	s2=7;
	}
	else
	{
	   return false;

	}

	if (isValidInt(inDate.substring(0,s1)))
	{
       sYear= parseInt(inDate.substring(0,s1));
	}
	else
	{
		return false;
	}

	if (isValidInt(inDate.substring(s1+1,s2)))
	{
      sMonth= inDate.substring(s1+1,s2);
	}
	else
	{
		return false;
	}

	if (isValidInt(inDate.substring(s2+1,inDate.length)))
	{
       sDate= inDate.substring(s2+1,inDate.length);
	}
	else
	{
		return false;
	}




	if (sMonth==1 || sMonth==3 || sMonth==5 || sMonth==7 || sMonth==8 || sMonth==10 || sMonth==12)
	{
		if (sDate>=1 && sDate<=31) { return true; }
		else {return false;}

	}
	if (sMonth==4 || sMonth==6 || sMonth==9 || sMonth==11)
	{
		if (sDate>=1 && sDate<=30) { return true; }
		else{ return false; }

	}
	if (sMonth==2)
	{
		if (sYear%4 == 0)
		{ if (sDate>=1 && sDate <= 29) { return true; }
		  else {return false;}
	        }
		else
		{ if (sDate>=1 && sDate <= 28) { return true; }
		   else{return false;}
		 }
	}
      return false;

}






function isValidDecimal(theField)
{


	var inStr=theField;
	var inLen=inStr.length;

	if (inLen == 0)
		return true;
	if (inLen == 1 && inStr==" ")
		return false;
	for(var i=0; i< inLen; i++)
	{
		var ch = inStr.substring(i,i+1)
        if ((ch<"0" || ch>"9") && ch!="." && ch!="-")
		{
			if (ch != "")
				return false;
		}
    }
	return true;
/*
	if (
        .isNaN(theField) || (theField.length == 0)){
	   return false;
	}
	else{
	  return true;
	}
		*/
}

function isValidInt(theField)
{
	var inStr=theField;
	var inLen=inStr.length;

	if (inLen == 0)
		return true;
	if (inLen == 1 && inStr==" ")
		return false;
	for(var i=0; i< inLen; i++)
	{
		var ch = inStr.substring(i,i+1)
        if (ch < "0" || ch > "9")
		{
			if (ch != "")
				return false;
		}
    }
	return true;
}

function openNewWindow(strFile,strTitle){//开窗
 window.open(strFile,strTitle,"toolbar=no,menubar=no,left=100,top=100,");
}


function selectDate(sField){
/*
日期选择
*/
var sValue=document.theForm(sField).value;//日期
var
sReturn=window.showModalDialog("../inc/calendar.jsp?sValue="+sValue,"asd",'dialogHeight:210px;dialogWidth:260px;status:no;help:no;');

if(sReturn!=""){
 document.all(sField).value=sReturn;
}

}
function selectClient(sField){
/*
客户选择
*/
var sValue=document.theForm(sField).value;//日期
var
sReturn=window.showModalDialog("../inc/selectClient.faces?sValue="+sValue,"asd",'dialogHeight:420px;dialogWidth:520px;status:no;help:no;');

if(sReturn!=""){
 document.all(sField).value=sReturn;
}

}

function doSelectMemberForArray(sFieldName,sFieldID,sType){
/*
  用于数组元素的情况
*/

   var sReturn=window.showModalDialog("/wuye/inc/selectmember.jsp?sType="+sType,"asd",'dialogHeight:280px;dialogWidth:320px;status:no;help:no;');
   if(sReturn!=""){
     var index=sReturn.indexOf("*");
     var name=sReturn.substring(0,index);
     sReturn=sReturn.substring(index+1,sReturn.length);
	 var aa=eval("document.theForm."+sFieldName);
     aa.value=name;
     var iType=parseInt(sType);
	 var bb=eval("document.theForm."+sFieldID);
     switch(iType){
     case 1:
        bb.value=sReturn;
        break;
     case 2:
       index=sReturn.lastIndexOf("?");
       bb.value=sReturn.substring(index+1,sReturn.length);
       break;
     case 3:
       index=sReturn.lastIndexOf("?");
       bb.value=sReturn.substring(index+1,sReturn.length);
       break;
     }
   }
  }


function doSelectMember(sFieldName,sFieldID,sType){
/*
  公司,部门或员工选择(sType的值 1:公司,2:部门,3:员工)
  sFieldName为显示字段名称
  sFieldID为隐藏字段名称
*/

   var sReturn = window.showModalDialog("/wuye/inc/selectmember.jsp?sType="+sType,"asd",'dialogHeight:280px;dialogWidth:320px;status:no;help:no;');
   if(sReturn!=""){
     var index=sReturn.indexOf("*");
     var name=sReturn.substring(0,index);
     sReturn=sReturn.substring(index+1,sReturn.length);
     document.all(sFieldName).value=name;
     var iType=parseInt(sType);
     switch(iType){
     case 1:
        document.all(sFieldID).value=sReturn;
        break;
     case 2:
       index=sReturn.lastIndexOf("?");
       document.all(sFieldID).value=sReturn.substring(index+1,sReturn.length);
       break;
     case 3:
       index=sReturn.lastIndexOf("?");
       document.all(sFieldID).value=sReturn.substring(index+1,sReturn.length);
       break;
     }
   }
  }


//---打开新的网页。
function winOpen( str ){
    window.open ( str, 'newwindow', 'height=500, width=800, top=20,left=20,scrollbar=yes,resize=yes,toolbar=no, menubar=no, location=no, status=no')
    //window.showModalDialog();
 }

 function procCurrency(money){
   if(money<0){
     money=0.00;
   }

   money=(Math.round(money*100.00)/100.00);
   return money;
 }



