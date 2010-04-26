<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.qzgf.utils.comm.*"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	//定义全局变量
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	    <link rel="stylesheet" href="<%=path%>/css/basic.css" type="text/css" />
		<link rel="stylesheet" href="<%=path%>/css/galleriffic.css" type="text/css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.galleriffic.js"></script>
		
		<script type="text/javascript" src="<%=path%>/js/comm.js"></script>
		<script type="text/javascript"><!--
		
		    //显示发表新评论页
			function showResult(res){
				$('#newWord').html(res); 
			}
			
			function loadNewWordEditPage(p_ID,path) {
			   
			    $("#newWord").toggle();  
			    $('#newWord').html("<center>页面载入中...</center>"); 
                
			    var url = getActionMappingURL("/album",path);
			    $.get(url,{action:'toNewWordPage',date:new Date()},showResult);
			}
			
			function loadWords(p_ID,path) {
			    $("#newWord1").toggle();  
			    $('#newWord1').html("<center>页面载入中...</center>"); 
                
			    var url = getActionMappingURL("/album",path);
			    $.get(url,{action:'listPhotoWords',p_Id:p_ID,date:new Date()},showResult3);
			}
			
			//显示发表新评论页
			function showResult3(res){
			    //alert("1");
			    //alert(res);
				$('#Words').html(res); 
				//$('#Words').text();
				//$('#Words').html();
				$("#Words").toggle(); 
			}
			
			
			
			function loadSmilePage(inputName1,path) {
			  	$("#smileDiv").toggle();
			  	$('#smileDiv').html("<center>页面载入中...</center>"); 
			  	var url = path+"/smile.jsp";
			  	$.get(url,{inputName:"newWordContent",date:new Date()},showResult1);
			}
			
			//显示笑脸页
			function showResult1(res){
			    $('#smileDiv').html(res); 
			}
			
			//关闭编辑页面
			function closeEditPage() {
  				$("#newWord").html("");
  				$("#newWord").toggle();
			}
			
			//关闭笑脸页
			function closeSmilePage() {
  				$("#smileDiv").html("");
  				$("#smileDiv").toggle();
			}
			
			//把笑脸插入到评论框
			function insertSmile(inputName,smlieTag) {
			    //alert($("#newWordContent").val());
  				$("#newWordContent").val($("#newWordContent").val() + smlieTag);
  				$("#newWordContent").focus();
			}
			
			//保存某一照片的评论
			function saveEditDo(path) {
			    var url = getActionMappingURL("/album",path);

			    url=url+"?action=editdo&content="+encodeURI(encodeURI($('#newWordContent').val()))+"&date=new Date()";
			   
					try{
						var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
						oXMLDom.async = false ;
						oXMLDom.load(url); 
						var root;
						if (oXMLDom.parseError.errorCode != 0) {
							var myErr = oXMLDom.parseError;
							return ;
						} else {
							root = oXMLDom.documentElement;
						}
						if (null != root){
							var rowSet = root.selectNodes("//check");
							var mgr=rowSet.item(0).selectSingleNode("value").text;
							alert(mgr);
						}
					}catch(e){ 
						alert(e);
					}
					
			    
  			} 
  			
  			
             
		--></script>
	</head>
	<body>
		<div id="page">
			<div id="container">
				<s:url action="album?action=toAddPhoto" id="newPhoto" namespace="/">
				    <s:param name="method" value="POST"/>
					<s:param name="a_name" value="%{a_name}"/>
					<s:param name="a_Id" value="%{a_Id}"/>
				</s:url>
				<a href="${newPhoto}">上传照片</a>
				<div id="gallery" class="content">
					<div id="controls" class="controls"></div>
					<div id="loading" class="loader"></div>
					<div id="slideshow" class="slideshow"></div>
					<div id="caption" class="embox"></div>
				</div>
				
				<div id="thumbs" class="navigation">
					<ul class="thumbs noscript">
						<s:iterator id="photo" value="%{pageList.objectList}">
							<li>
								<a class="thumb"  href="<%=WebFrameUtil.getUserWebPath("1")%><s:property value='#photo.P_BIG_PIC' />" title="<s:property value='#photo.P_TITLE' />">
									<img  src="<%=WebFrameUtil.getUserWebPath("1")%><s:property value='#photo.P_SMALL_PIC' />" alt="<s:property value='#photo.P_TITLE' />" />
								</a>
								<div class="caption">
									<div class="image-title"><s:property value='#photo.P_TITLE' /></div>
									<div class="image-desc"><s:property value='#photo.P_DESC' /></div>
									<div class="download"><a href='javascript:;' onclick="loadWords(<s:property value='#photo.P_ID' />,'<%=path %>');" >所有评论</a></div>
									<hr width="100%" />
									<!-- <div class="download"><a href="javascript:;" onclick="loadNewWordEditPage(<s:property value='#photo.P_ID' />,'<%=path %>');">发表评论</a></div> -->
							        <div id="Words" style="display: none;width: 100%"/>
							        
								</div>
								<div  style="width: 50%">
							        	<s:textarea id="newWordContent" name="newWordContent" cols="45" rows="8" cssClass="textarea1"></s:textarea>
							        	<input type="button" name="Submit" value="保存" class="button1" onclick="saveEditDo('<%=path %>');"/>
							    </div> 
							</li>
						</s:iterator>
					</ul>
				</div>
				<!-- End Advanced Gallery Html Containers -->
				<div style="clear: both;"></div>
			</div>
		</div>
		<div id="footer">&copy; 猎图网</div>
		<script type="text/javascript">
			// We only want these styles applied when javascript is enabled
			$('div.navigation').css({'width' : '300px', 'float' : 'left'});
			$('div.content').css('display', 'block');

			// Initially set opacity on thumbs and add
			// additional styling for hover effect on thumbs
			var onMouseOutOpacity = 0.67;
			$('#thumbs ul.thumbs li').css('opacity', onMouseOutOpacity)
				.hover(
					function () {
						$(this).not('.selected').fadeTo('fast', 1.0);
					}, 
					function () {
						$(this).not('.selected').fadeTo('fast', onMouseOutOpacity);
					}
				);

			$(document).ready(function() {
			    
				// Initialize Advanced Galleriffic Gallery
				var galleryAdv = $('#gallery').galleriffic('#thumbs', {
					delay:                  2000,
					numThumbs:              12,
					preloadAhead:           10,
					enableTopPager:         true,
					enableBottomPager:      true,
					imageContainerSel:      '#slideshow',
					controlsContainerSel:   '#controls',
					captionContainerSel:    '#caption',
					loadingContainerSel:    '#loading',
					renderSSControls:       true,
					renderNavControls:      true,
					playLinkText:           '开始幻灯',
					pauseLinkText:          '停止幻灯',
					prevLinkText:           '&lsaquo; 上一图',
					nextLinkText:           '下一图 &rsaquo;',
					nextPageLinkText:       '下一页 &rsaquo;',
					prevPageLinkText:       '&lsaquo; 上一页',
					enableHistory:          true,
					autoStart:              false,
					onChange:               function(prevIndex, nextIndex) {
						$('#thumbs ul.thumbs').children()
							.eq(prevIndex).fadeTo('fast', onMouseOutOpacity).end()
							.eq(nextIndex).fadeTo('fast', 1.0);
					},
					onTransitionOut:        function(callback) {
						$('#caption').fadeTo('fast', 0.0);
						$('#slideshow').fadeTo('fast', 0.0, callback);
					},
					onTransitionIn:         function() {
						$('#slideshow').fadeTo('fast', 1.0);
						$('#caption').fadeTo('fast', 1.0);
					},
					onPageTransitionOut:    function(callback) {
						$('#thumbs ul.thumbs').fadeTo('fast', 0.0, callback);
					},
					onPageTransitionIn:     function() {
						$('#thumbs ul.thumbs').fadeTo('fast', 1.0);
					}
					
				});
			});
		</script>
	</body>
</html>