/***************************************
*                                      *
*           OUTLOOK-LIKE BAR           *
*                                      *
*             Written by               *
*           Massimo Guccione           *
*            Multimedia Lab            *
*       Intersiel S.p.A. (W)1999       *
*                                      *
* Important: to use this script don't  *
*            remove these comments     *
*                                      *
*                                      *
* Version 1.0Beta Freeware (MSIE only) *
*                                      *
* mail : m.guccione@telcal.it          *
*        obyone@antares.it             *
*        please report for bugs        *
*                                      *
*  for both Netscape and MSIE version  *
*  contact me! (freeware of course)    *
*                                      *
*   update : doggie_wang@hotmail.com   *
*           1.0 (MSIE only)            *
*                                      *
****************************************/
function disableselect(e){
return false
}

function reEnable(){
return true
}

//if IE4+
document.onselectstart=new Function ("return false")

function Run(app)
{
	alert(app);
}
document.write('<div id="mask" style="width:'+OB_Width+'px; height:100%; z-index:-1;BACKGROUND-COLOR:white;filter: Alpha(Opacity=15)"></div>');
document.write("<DIV id='OutlookLikeBar' style='position:absolute;top:"+OB_Top+";left:"+OB_Left+";width:"+OB_Width+";height:"+(document.body.clientHeight)+";BORDER-BOTTOM: buttonhighlight 1px solid;BORDER-LEFT: buttonshadow 1px solid;BORDER-TOP: buttonshadow 1px solid;z-index:0;visibility:hidden;clip:rect(0,"+OB_Width+","+document.body.clientHeight+",0)'>");
document.write('<div onmousedown="arrowdown()" onmouseup="arrowup();OutlookLikeBar.ArrowSelected(this)" onmouseout="arrowup()" id="OB_SlideUp" style="position:absolute; left:0px; top:0px; width:16px; height:16px; z-index:500;border-top:1px solid buttonface;border-left:1px solid buttonface;border-right:1px solid threeddarkshadow;border-bottom:1px solid threeddarkshadow;background:buttonface"><div id="arrowinner" style="width:100%; height:100%; border-top:1px solid white;border-left:1px solid white;border-right:1px solid buttonshadow;border-bottom:1px solid buttonshadow"><img id="arrow" src="images/arrowup.bmp" width="9" height="8"></div></div>');
document.write('<div onmousedown="arrowdownd()" onmouseup="arrowupd();OutlookLikeBar.ArrowSelected(this)" onmouseout="arrowupd()" id="OB_SlideDown" style="position:absolute; left:0px; top:0px; width:16px; height:16px; z-index:500;border-top:1px solid buttonface;border-left:1px solid buttonface;border-right:1px solid threeddarkshadow;border-bottom:1px solid threeddarkshadow;background:buttonface"><div id="arrowdinner" style="width:100%; height:100%; border-top:1px solid white;border-left:1px solid white;border-right:1px solid buttonshadow;border-bottom:1px solid buttonshadow"><img id="arrowd" src="images/arrowdown.bmp" width="9" height="8"></div></div>');
//***************************
//* Outlook-Like Bar Folder *
//***************************
j=1;
while(eval("window.OutBarFolder"+j))
	j++;
i=j-1;
var n=0;
while(i>0)
{
	Folder=eval("OutBarFolder"+i)
	if(i==1)
	{
		document.write("<DIV position='UP' id='OB_Button1' onClick='OutlookLikeBar.FolderClicked("+i+","+(j-2)+");this.blur()' style='border-right:1px solid buttonshadow;border-left:1px solid buttonhighlight;border-bottom:1px solid buttonshadow;border-top:1px solid buttonhighlight;position:absolute;top:0;left:0;width:120;height:"+OB_ButtonHeight+";cursor:hand;color:"+OB_ButtonFontColor+";z-index:100;background:buttonface' class='folder' onfocus=this.blur()><DIV id='Button1' align='center' style='height:100%;FONT-SIZE: 11px; FONT-FAMILY: Tahoma,??ì?;border:1px solid buttonface' onMouseOut='OutlookLikeBar.OutFolder(this)' onMouseOver='OutlookLikeBar.OverFolder(this)' onMouseDown='OutlookLikeBar.FolderSelected(this)'  onMouseUp='OutlookLikeBar.OutFolder(this)'><img src='img/spacer.gif' height='3'><br>&nbsp;"+Folder[0]+"</DIV></DIV>");
		MakeItems(Folder,i,OB_ButtonHeight);		
	}	
	else
	{		
		document.write("<div position='DOWN' id='OB_Button"+i+"' style='position:absolute;top:"+(document.body.clientHeight-(j-i)*OB_ButtonHeight-OB_BorderWidth*2-n-4)+";left:0;width:120;height:"+(OB_ButtonHeight+4)+";z-index:99;background:buttonface'><div style='width:100%; height:100%; BACKGROUND-COLOR:white;filter: Alpha(Opacity=15)'><DIV onClick='OutlookLikeBar.FolderClicked("+i+","+(j-2)+");this.blur()' style='border-right:1px solid buttonshadow;border-left:1px solid buttonhighlight;border-bottom:1px solid buttonshadow;border-top:1px solid buttonhighlight;position:relative;top:4;left:0;width:100%;height:"+OB_ButtonHeight+";cursor:hand;color:"+OB_ButtonFontColor+"' class='folder' onfocus=this.blur()><DIV id='Button"+i+"' align='center' style='background:buttonface;height:100%;FONT-SIZE: 11px; FONT-FAMILY: Tahoma,??ì?;border:1px solid buttonface' onMouseOut='OutlookLikeBar.OutFolder(this)' onMouseOver='OutlookLikeBar.OverFolder(this)' onMouseDown='OutlookLikeBar.FolderSelected(this)'  onMouseUp='OutlookLikeBar.OutFolder(this)'><img src='img/spacer.gif' height='3'><br>&nbsp;"+Folder[0]+"</DIV></DIV></div></div>");
	    MakeItems(Folder,i,(document.body.clientHeight-(j-i)*OB_ButtonHeight-OB_BorderWidth*2)+OB_ButtonHeight-n);	
		n++;
	}		
	i--;
}	
document.write("</DIV>");
var OutlookLikeBar=new OutBar(OB_Width,document.body.clientHeight,j-1,OB_ButtonHeight,OB_BorderWidth,OB_SlideSpeed,OB_IconsHeight+12+OB_LabelMargin+OB_ItemsSpacing,OB_ArrowSlideSpeed);
document.all["OutlookLikeBar"].style.visibility="visible";

function MakeItems(Folder,zorder,top)
{
	var items=0;
	var folderWidth=(OB_Width-OB_BorderWidth*2);
	while(Folder[items+1])
		items+=5;
	items/=5;
	document.write("<DIV id='OB_Folder"+zorder+"' style='position:absolute;left:0;top:"+top+";width:"+folderWidth+";height:"+(OB_Margin*2+items*(OB_IconsHeight+12+OB_LabelMargin)+(items-1)*OB_ItemsSpacing)+";z-index:"+zorder+";clip:rect(0 0 0 0);'>");
	for(var i=1;i<items*5;i+=5)
	{
		document.write("<div id='itembg"+i+zorder+"' style='background: buttonface;z-index:40;position:absolute;left:"+(Math.ceil((OB_Width-OB_BorderWidth*2-OB_IconsHeight)/2)-1)+";top:"+(OB_Margin+Math.ceil((i-1)/5)*(OB_ItemsSpacing+12+OB_IconsHeight))+";width:"+(OB_IconsWidth+2)+";height: "+(OB_IconsHeight+2)+"'>");
		document.write("</div>");
		document.write("<div id='itembgmask"+i+zorder+"' style='filter: alpha(Opacity=15);background:white;z-index:41;position:absolute;left:"+(Math.ceil((OB_Width-OB_BorderWidth*2-OB_IconsHeight)/2)-1)+";top:"+(OB_Margin+Math.ceil((i-1)/5)*(OB_ItemsSpacing+12+OB_IconsHeight))+";width:"+(OB_IconsWidth+2)+";height: "+(OB_IconsHeight+2)+"'>");
		document.write("</div>");
		document.write("<div id='itemborder"+i+zorder+"' style='z-index:42;position:absolute;left:"+(Math.ceil((OB_Width-OB_BorderWidth*2-OB_IconsHeight)/2)-1)+";top:"+(OB_Margin+Math.ceil((i-1)/5)*(OB_ItemsSpacing+12+OB_IconsHeight))+";width:"+(OB_IconsWidth+2)+";height: "+(OB_IconsHeight+2)+"'>");
		document.write("</div>");
		document.write("<div id='itempic"+i+zorder+"' title='"+Folder[i+4]+"' targetFrame='"+Folder[i+3]+"' link='"+Folder[i+2]+"' now='"+Folder[i+1]+"' onMouseOut='itemout("+i+zorder+")' onMouseDown='itemdown("+i+zorder+")' onMouseOver='itemover("+i+zorder+")' onMouseUp='itemover("+i+zorder+");OutlookLikeBar.ItemSelected(this)' style='z-index:43;position:absolute;left:"+(Math.ceil(OB_Width-OB_BorderWidth*2-OB_IconsHeight)/2)+";top:"+(OB_Margin+Math.ceil((i-1)/5)*(OB_ItemsSpacing+12+OB_IconsHeight)+1)+";clip:rect(0 "+OB_IconsWidth+" "+OB_IconsHeight+" 0;width:"+OB_IconsWidth+";height:"+OB_IconsHeight+"'>");
		document.write("<img src='"+Folder[i]+"'>");
		document.write("</div>");
		document.write("<div align='center'  style='width=100%;position:absolute;left:0;top:"+(OB_LabelMargin+OB_IconsHeight+OB_Margin+Math.ceil((i-1)/5)*(OB_ItemsSpacing+12+OB_IconsHeight))+";FONT-SIZE: 11px; FONT-FAMILY: Tahoma,??ì?;color:"+OB_LabelFontColor+"'>");
		document.write(Folder[i+1]);
		document.write("</div>");
	}	
	document.write("</DIV>");
}


//***************************
//* Outlook-Like Bar Object *
//***************************
function OutBar(width,height,items,buttonHeight,borderWidth,slideSpeed,slideArrowValue,ArrowSlideSpeed)
{
	this.currentFolder=1;
	this.currentItem=null;
	this.slideCount=0;
	this.slideStep=1;
	this.slideArrowValue=64;
	this.slideSpeed=slideSpeed;
	this.borderWidth=borderWidth;
	this.width=width;
	this.visibleAreaHeight=height-2*borderWidth-items*buttonHeight;
	this.visibleAreaWidth=width;
	this.FolderClicked=FolderClicked;
	this.SlideFolders=SlideFolders;
	this.ItemSelected=ItemSelected;
	this.ArrowSelected=ArrowSelected;
	this.ArrowSlideSpeed=ArrowSlideSpeed;
	this.SlideItems=SlideItems;
	this.SlideItemsAction=SlideItemsAction;
	this.Start=Start;
	this.ClipFolder=ClipFolder;
	this.SetArrows=SetArrows;
	this.HideArrows=HideArrows;
	this.sliding=false;
	this.items=items;
	this.started=false;
	this.Start();
	this.OverFolder=OverFolder;
	this.OutFolder=OutFolder;
	this.FolderSelected=FolderSelected;
}

function FolderClicked(folder,num)
{
	if(this.sliding)
		return;
	if(folder==this.currentFolder)
		return;
	this.sliding=true;		
	this.slideCount=this.visibleAreaHeight-num;
	this.slideStep=1;
	this.countStep=0;
	this.HideArrows();
	this.SlideFolders(folder,document.all["OB_Button"+folder].position=="DOWN");
}

function SlideFolders(folder,down)
{
	var step;	
	if(down)
	{
		this.slideCount-=Math.floor(this.slideStep);
		if(this.slideCount<0)
			this.slideStep+=this.slideCount;
		step=Math.floor(this.slideStep);
		for(var i=2;i<=folder;i++)
			if(document.all["OB_Button"+i].position=="DOWN")
			{
				document.all["OB_Button"+i].style.pixelTop-=step;
				document.all["OB_Folder"+i].style.pixelTop-=step;
			}				

	    filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;

		var clipString=document.all["OB_Folder"+folder].style.clip;
		var clip=clipString.match(filter);
		this.ClipFolder(folder,parseInt(clip[1]),this.visibleAreaWidth,(parseInt(clip[3])+step),0);

		var clipString=document.all["OB_Folder"+this.currentFolder].style.clip;
		var clip=clipString.match(filter);
		this.ClipFolder(this.currentFolder,parseInt(clip[1]),this.visibleAreaWidth,(parseInt(clip[3])-step),0);

		this.slideStep*=this.slideSpeed;
		if(this.slideCount>0)
			setTimeout("OutlookLikeBar.SlideFolders("+folder+",true)",20);
		else		
		{
			for(var k=2;k<=folder;k++)
				document.all["OB_Button"+k].position="UP";
			this.currentFolder=folder;		
			this.SetArrows();
			this.sliding=false;		
		}		
	}
	else		
	{
		this.slideCount-=Math.floor(this.slideStep);
		if(this.slideCount<0)
			this.slideStep+=this.slideCount;
		step=Math.floor(this.slideStep);
		for(var i=folder+1;i<=this.items;i++)
			if(document.all["OB_Button"+i].position=="UP")
			{
				document.all["OB_Button"+i].style.pixelTop+=step;
				document.all["OB_Folder"+i].style.pixelTop+=step;
			}

	    filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;

		var clipString=document.all["OB_Folder"+folder].style.clip;
		var clip=clipString.match(filter);
		this.ClipFolder(folder,parseInt(clip[1]),this.visibleAreaWidth,(parseInt(clip[3])+step),0);

		var clipString=document.all["OB_Folder"+this.currentFolder].style.clip;
		var clip=clipString.match(filter);
		this.ClipFolder(this.currentFolder,parseInt(clip[1]),this.visibleAreaWidth,(parseInt(clip[3])-step),0);

		this.slideStep*=this.slideSpeed;
		if(this.slideCount>0)
			setTimeout("OutlookLikeBar.SlideFolders("+folder+",false)",20);
		else		
		{
			for(var k=folder+1;k<=this.items;k++)
				document.all["OB_Button"+k].position="DOWN";
			this.currentFolder=folder;		
			this.SetArrows();
			this.sliding=false;		
		}		
	}
}

function ItemSelected(item)
{
	if(this.sliding)
		return;		
	if(item.link.indexOf("javascript")!=-1) 
		eval(item.link);
	else if(item.targetFrame=="parent.main.content"){
		eval(item.targetFrame+".location='"+item.link+"'");
		parent.main.outborder.style.borderRight="1 solid buttonhighlight";
		parent.main.outborder.style.borderBottom="1 solid buttonhighlight";
		parent.main.outborder.style.borderLeft="1 solid buttonshadow";
		parent.main.outborder.style.borderTop="1 solid buttonshadow";
		parent.main.innerborder.style.borderRight="1 solid buttonshadow";
	}
	else {
		eval("parent.main.content.location='"+item.link+"'");
		parent.main.outborder.style.border="none";
		parent.main.innerborder.style.border="none";
	}
	parent.bar.itemnow.innerText=" "+item.now+" ";
	parent.bar.itemnow.title=item.title;
}

function itemover(item)
{
	if(this.sliding)
		return;		
	itemborder=eval("itemborder"+item);
	itemborder.style.border="1 solid highlight";
	eval("itembg"+item).style.background="highlight";
	eval("itembgmask"+item).filters.alpha.opacity=70;
}

function itemdown(item)
{
	if(this.sliding)
		return;		
	itemborder=eval("itemborder"+item);
	itemborder.style.border="1 solid highlight";
	eval("itembg"+item).style.background="highlight";
	eval("itembgmask"+item).filters.alpha.opacity=52;
}

function itemout(item)
{
	if(this.sliding)
		return;		
	itemborder=eval("itemborder"+item);
	itemborder.style.border="none";
	eval("itembg"+item).style.background="buttonface";
	eval("itembgmask"+item).filters.alpha.opacity=15;
}

function ArrowSelected(arrow)
{
	if(this.sliding)
		return;		
	this.SlideItems(arrow.id=="OB_SlideUp");
}

function OutFolder(folder)
{
	if(this.sliding)
		return;		
	folder.style.border="1 solid buttonface";
}

function OverFolder(folder)
{
	if(this.sliding)
		return;		
	folder.style.borderRight="1px solid buttonshadow";
	folder.style.borderLeft="1px solid buttonface";
	folder.style.borderBottom="1px solid buttonshadow";
	folder.style.borderTop="1px solid buttonface";
}

function FolderSelected(folder)
{
	if(this.sliding)
		return;		
    folder.style.borderTop="1px solid buttonshadow";
	folder.style.borderBottom="1px solid buttonface";
	folder.style.borderLeft="1px solid buttonshadow";
	folder.style.borderRight="1px solid buttonface";
}

function ClipFolder(folder,top,right,bottom,left)
{
	document.all["OB_Folder"+folder].style.clip=clip='rect('+top+' '+right+' '+bottom+' '+left+')';
}

function Start()
{
	if(!this.started)
	{
		this.ClipFolder(1,0,this.visibleAreaWidth,this.visibleAreaHeight,0);
		this.SetArrows();
	}		
}

function SetArrows()
{
	document.all["OB_SlideDown"].style.pixelTop=document.all["OB_Button"+this.currentFolder].style.pixelTop+document.all["OB_Button"+this.currentFolder].style.pixelHeight+4;
	document.all["OB_SlideDown"].style.pixelLeft=68;
	document.all["OB_SlideUp"].style.pixelTop=document.all["OB_Button"+this.currentFolder].style.pixelTop+document.all["OB_Button"+this.currentFolder].style.pixelHeight+this.visibleAreaHeight-26;
	document.all["OB_SlideUp"].style.pixelLeft=68;

	var folder=document.all["OB_Folder"+this.currentFolder].style;
	var startTop=document.all["OB_Button"+this.currentFolder].style.pixelTop+document.all["OB_Button"+this.currentFolder].style.pixelHeight;

	if(folder.pixelTop<startTop)
		document.all["OB_SlideDown"].style.visibility="visible";
	else		
		document.all["OB_SlideDown"].style.visibility="hidden";

	if(folder.pixelHeight-(startTop-folder.pixelTop)>this.visibleAreaHeight)
		document.all["OB_SlideUp"].style.visibility="visible";
	else		
		document.all["OB_SlideUp"].style.visibility="hidden";
}

function HideArrows()
{
	document.all["OB_SlideUp"].style.visibility="hidden";
	document.all["OB_SlideDown"].style.visibility="hidden";
}

function SlideItems(up)
{
	this.sliding=true;
	this.slideCount=Math.floor(this.slideArrowValue/this.ArrowSlideSpeed);
	up ? this.SlideItemsAction(-this.ArrowSlideSpeed) : this.SlideItemsAction(this.ArrowSlideSpeed);
}

function SlideItemsAction(value)
{
	document.all["OB_Folder"+this.currentFolder].style.pixelTop+=value;
    filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;
    var clipString=document.all["OB_Folder"+this.currentFolder].style.clip;
    var clip=clipString.match(filter);
    this.ClipFolder(this.currentFolder,(parseInt(clip[1])-value),parseInt(clip[2]),(parseInt(clip[3])-value),parseInt(clip[4]));
	this.slideCount--;
	if(this.slideCount>0)
		setTimeout("OutlookLikeBar.SlideItemsAction("+value+")",20);
	else
	{
		if(Math.abs(value)*this.ArrowSlideSpeed!=this.slideArrowValue)		
		{
			document.all["OB_Folder"+this.currentFolder].style.pixelTop+=(value/Math.abs(value)*(this.slideArrowValue%this.ArrowSlideSpeed));
		    filter = /rect\((\d*)px (\d*)px (\d*)px (\d*)px\)/;
			var clipString=document.all["OB_Folder"+this.currentFolder].style.clip;
			var clip=clipString.match(filter);
		    this.ClipFolder(this.currentFolder,(parseInt(clip[1])-(value/Math.abs(value)*(this.slideArrowValue%this.ArrowSlideSpeed))),parseInt(clip[2]),(parseInt(clip[3])-(value/Math.abs(value)*(this.slideArrowValue%this.ArrowSlideSpeed))),parseInt(clip[4]));
		}		    
		this.SetArrows();
		this.sliding=false;
	}		
}

function arrowdown() {  
		if(this.sliding)
		return;		

OB_SlideUp.style.borderTop="1px solid buttonshadow";
OB_SlideUp.style.borderLeft="1px solid buttonshadow";
OB_SlideUp.style.borderRight="1px solid white";
OB_SlideUp.style.borderBottom="1px solid white";

arrowinner.style.borderTop="1px solid threeddarkshadow";
arrowinner.style.borderLeft="1px solid threeddarkshadow";
arrowinner.style.borderRight="1px solid buttonface";
arrowinner.style.borderBottom="1px solid buttonface";

arrow.style.border="1px solid buttonface";
}

function arrowup() {  
		if(this.sliding)
		return;		

OB_SlideUp.style.borderTop="1px solid buttonface";
OB_SlideUp.style.borderLeft="1px solid buttonface";
OB_SlideUp.style.borderRight="1px solid threeddarkshadow";
OB_SlideUp.style.borderBottom="1px solid threeddarkshadow";

arrowinner.style.borderTop="1px solid white";
arrowinner.style.borderLeft="1px solid white";
arrowinner.style.borderRight="1px solid buttonshadow";
arrowinner.style.borderBottom="1px solid buttonshadow";

arrow.style.border="none";
}

function arrowdownd() {  
			if(this.sliding)
		return;		

OB_SlideDown.style.borderTop="1px solid buttonshadow";
OB_SlideDown.style.borderLeft="1px solid buttonshadow";
OB_SlideDown.style.borderRight="1px solid white";
OB_SlideDown.style.borderBottom="1px solid white";

arrowdinner.style.borderTop="1px solid threeddarkshadow";
arrowdinner.style.borderLeft="1px solid threeddarkshadow";
arrowdinner.style.borderRight="1px solid buttonface";
arrowdinner.style.borderBottom="1px solid buttonface";

arrowd.style.border="1px solid buttonface";
}

function arrowupd() {  
			if(this.sliding)
		return;		

OB_SlideDown.style.borderTop="1px solid buttonface";
OB_SlideDown.style.borderLeft="1px solid buttonface";
OB_SlideDown.style.borderRight="1px solid threeddarkshadow";
OB_SlideDown.style.borderBottom="1px solid threeddarkshadow";

arrowdinner.style.borderTop="1px solid white";
arrowdinner.style.borderLeft="1px solid white";
arrowdinner.style.borderRight="1px solid buttonshadow";
arrowdinner.style.borderBottom="1px solid buttonshadow";

arrowd.style.border="none";
}