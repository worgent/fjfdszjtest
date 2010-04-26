<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.qzgf.utils.comm.WebFrameUtil;"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
	<head>

	</head>
	<body>
		<!-- 页面导航 -->
		<div class="leaderF" align="left">
			<div class="leader">
				当前位置：
				<a href="/selfconfig/mapcard.do">地图名片</a> ><s:property value="mapcard.MERCHANTNAME"/></div>
		</div>
		<div class="center">
			<div style="float:left; width:60%;"  align="left">
			    <!-- 商家具体信息 -->
				<div style="width: 655px;">
					<div style="height: 5px; width: 655px;">
						<div style="height: 5px; width: 643px;"></div>
					</div>
					<div style="height: 290px; width: 655px;">
						<div style="height: 290px; width: 646px;">
							<div style="float: left; margin-left: 5px; margin-top: 5px;">
								<div id="picBox" align="center"
									style="width: 260px; height: 256px; border: 1px solid #A1A1A1; padding: 1 1 0 1px;">
									
									<img style="width: 260px; height: 256px; "  src="<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='%{mapcard.MERCHANTICON}' />" alt="<s:property value='%{mapcard.MERCHANTICON}' />" />
								</div>
								<div align="center"
									style="width: 260px; height: 20px; margin-top: 3px;">
									<a href="javascript:void(0);" onclick="addFavorite(event);">我要收藏</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="addInfo(event);">优惠券下载</a>
									<a href="javascript:void(0);" onclick="addInfo(event);">名片下载</a>
								</div>
							</div>
							<div style="float: left; margin-left: 10px; margin-top: 5px;">
								<div style="height: 275px; width: 355px;" align="left">
									<table border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<A class="companyTitle"
													href="<%=path%>/selfconfig/mapcard.do?action=detail&search.pid=<s:property value="mapcard.ID" />"><s:property value="mapcard.MERCHANTNAME"/></A>
										<tr>
											<td align="left" style="padding-left: 20px;">
											</td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 20px; height: 22px;">
												<font color="red"><s:property value="mapcard.READCOUNT"/></font>&nbsp;次浏览,&nbsp;&nbsp;
												<font color="red"><s:property value="mapcard.MESSAGECOUNT"/></font>&nbsp;条留言,&nbsp;&nbsp;
												<font color="red"><s:property value="mapcard.VOTECOUNT"/></font>&nbsp;次推荐,&nbsp;&nbsp;
												<font color="red"><s:property value="mapcard.FAVORITECOUNT"/></font>&nbsp;次收藏
											</td>
										</tr>
										<tr>
											<td style="padding-left: 20px; height: 18px;">
												管理员：
												<a href="/user?uid=laoding" target=_blank class="titleLink"><s:property value="mapcard.USERNAME"/></a>&nbsp;&nbsp;店主：
												<a href="/user?uid=jydjd" target=_blank class="titleLink"><s:property value="mapcard.MERCHANT_NAME"/></a>
											</td>
										</tr>

										<tr>
											<td align="left" style="padding-left: 20px; height: 18px;">
												<span class="tt">地址：</span><s:property value="mapcard.MERCHANTADDRESS"/>
											</td>
										</tr>

										<tr>
											<td align="left" style="padding-left: 20px; height: 18px;">
												<span class="tt">电话：</span><s:property value="mapcard.MERCHANTTELPHONE"/>
											</td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 20px; height: 18px;">
												<span class="tt">所属类别：</span><a
													href='<%=path%>/selfconfig/mapcard.do?action=index&search.pmerchanttype='<s:property value="mapcard.MERCHANTTYPE"/> target="_blank"><s:property value="mapcard.MERCHANTTYPE"/></a>
											</td>
										</tr>
										<tr>
											<td align="left" style="padding-left: 20px;" valign="top">
						 						<table border="0" cellpadding="0" cellspacing="0">
													<tr>
														<td valign="top">
															<span class="tt" style="width: 70px;">商家详情：</span>
														</td>
														<td valign="top">
															<div style="width: 250px; height: 125px; overflow-y: scroll; overflow-x: none; line-height: 1.5;">
																	<s:property value="mapcard.MERCHANTINTRODUCE"/>
															</div>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<!-- googlemap的引入经纬度信息 -->
											  	<s:hidden name="search.plat" id="search.plat" value="%{mapcard.LAT}"></s:hidden><!-- 纬度 -->
											  	<s:hidden name="search.plng" id="search.plng" value="%{mapcard.LNG}"></s:hidden><!-- 经度 -->
											</td>
										</tr>
									</table>
								</div>
							</div>

						</div>
					</div>
					<div class="ft" style="height: 11px; width: 655px;">
						<div class="c" style="height: 11px; width: 643px;">
							&nbsp;
						</div>
					</div>
				</div>
                <!-- 商家特色商品 -->
				<div style="width: 655px;">
					<div style="height: 25px; width: 655px;">
						<div style="height: 25px; width: 643px;">
							<span >特色商品</span><span style="padding-left: 425px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div style="height: 130px; width: 655px;">
						<div style="height: 130px; width: 646px;">
							<table border="0" cellpadding="3" cellspacing="0" width="100%">
								<tr>
									<s:iterator id="productList" value="productList">
									<td width="100" valign="top" align="center">
										<a class="pub_link"
											href="<%=path%>/selfconfig/mapcard.do?action=detailitem&search.pid=<s:property value="#productList.ID" />&search.ppid=<s:property value="mapcard.ID" />"
											target="_blank">
											<div>
												<img   src="<%=basePath%><%=WebFrameUtil.getUserWebPath("1")%><s:property value='#productList.SPECIALICON'/>_Small"  width="92"
													height="69" border="0">
											</div> <br><s:property value="#productList.SPECIALNAME"/></a>
									</td>
									</s:iterator>
									<td width="100">
									<span></span>
									</td>

								</tr>
							</table>
						</div>
					</div>
					<div class="ft" style="height: 11px; width: 655px;">
						<div class="c" style="height: 11px; width: 643px;">
							&nbsp;
						</div>
					</div>
				</div>

				<div class="dialog" style="width: 655px;">
					<div class="bd" style="height: 25px; width: 655px;">
						<div class="c" style="height: 25px; width: 643px;">
							<span class="green">用户留言</span><span style="padding-left: 425px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div class="mm" style="height: auto; width: 655px;">
						<div class="mr" style="height: auto; width: 646px;">
							<div style="padding-top: 5px;" id="remarkContent"></div>
							<div style="float: right; padding-right: 25px;" id="remarkPage"></div>
						</div>
					</div>
					<div class="ft" style="height: 11px; width: 655px;">
						<div class="c" style="height: 11px; width: 643px;">
							&nbsp;
						</div>
					</div>
				</div>



				<!-- 用户留言 -->
				<div class="dialog" style="width: 655px;">
					<div class="bd" style="height: 25px; width: 655px;">
						<div class="c" style="height: 25px; width: 643px;">
							<span class="green">我要留言</span><span style="padding-left: 425px;">&nbsp;</span><span
								style="width: 40px; cursor: pointer">&nbsp;&nbsp;</span>
						</div>
					</div>
					<div class="mm" style="height: auto; width: 655px;">
						<div class="mr" style="height: auto; width: 646px;">

							<div style="padding: 10 20 20 30px; line-height: 1.2">
								<form name="replyForm" method="post" action="log/uactrl"
									onSubmit="ajform:getReplyReturn">
									<input type="hidden" name="cardcommenttype" value="B"
										id="cardcommenttype" />
									<div>
										<input type="radio" id="gone" onclick="showMarkPoint('','B');"
											checked="checked" name="a" />
										<label for="gone">
											我去过
										</label>
										&nbsp;&nbsp;
										<input type="radio" id="gona"
											onclick="showMarkPoint('none','T');" name="a" />
										<label for="gona">
											我想去
										</label>
										&nbsp;&nbsp;
										<input type="radio" id="rep"
											onclick="showMarkPoint('none','');" name="a" />
										<label for="rep">
											我来留言
										</label>
									</div>
									<br />
									<div id="markPoint">
										<table border="0" cellpadding="0" cellspacing="0" width="580">
											<tr>
												<td align="left" width="60">
													总评：
												</td>
												<td width="120" id="myMark1">
													<span class="star_high"></span><span class="star_high"></span><span
														class="star_low"></span><span class="star_low"></span><span
														class="star_low"></span>
												</td>
												<td align="right" width="60">
													口味：<s:property value="mapcard.TASTEVALUE"/>
												</td>
												<td>
													<div class="level" id="myMark2">
														<div class="level_focus"></div>
														<div class="level_focus"></div>
														<div class="level_blur"></div>
														<div class="level_blur"></div>
														<div class="level_blur"></div>
													</div>
												</td>
												<td align="right" width="60">
													服务：<s:property value="mapcard.SERVICEVALUE"/>
												</td>
												<td>
													<div class="level" id="myMark3">
														<div class="level_focus"></div>
														<div class="level_focus"></div>
														<div class="level_blur"></div>
														<div class="level_blur"></div>
														<div class="level_blur"></div>
													</div>
												</td>
												<td align="right" width="60">
													环境：<s:property value="mapcard.ENVIRONMENTVALUE"/>
												</td>
												<td>
													<div class="level" id="myMark4">
														<div class="level_focus"></div>
														<div class="level_focus"></div>
														<div class="level_blur"></div>
														<div class="level_blur"></div>
														<div class="level_blur"></div>
													</div>
												</td>
											</tr>
										</table>
									</div>
									<div>
										<br />
										<font color="red">*</font> 留 言：
										<br />
										<textarea rows="7" style="width: 580px;" name="text"
											onFocus="this.select();">顺便说点什么？</textarea>

									</div>
									<!-- 
									<table border="0" cellpadding="0" cellspacing="0" width="210">
										<tr>
											<td>
												验证码：
											</td>
											<td>
												<input type="text" maxlength="4" size="4" id="code"
													onfocus="this.select();" name="checkcode"
													onkeypress="return checkCodeEnter(event);" />
											</td>
											<td>
												<img src="loginmng?act=CODEIMG" id="cimg"
													style="cursor: pointer;" onclick="changeCCode();"
													alt="点击换一个" title="点击换一个" />
											</td>
											<td>
												<a href="javascript:changeCCode();">换一个</a>
											</td>
										</tr>
									</table>
									 -->
									<div style="width: 500px; height: 25px" align="center">
										<input type="button" value="提交留言" onclick="subThisForm();" />
									</div>

								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
			
			<div id="map_canvas" style="float:left; width:40%; height: 400px;"  align="left">
			
			</div>

		</div>
		
		<!-- 测距 -->
  		<script type="text/javascript" src="<%=path%>/js/PointDistance.js"></script>       
        <script type="text/javascript"> 
		    //启动时调用google地图引擎     
		    $(document).ready(function(){
				if (GBrowserIsCompatible()) {
			    //调用googlemap相应方法
					initialize();
					//setInterval(showmyMsg,1000);
					//window.setTimeout(showmyMsg, 1000);
					showmyMsg();
					$(window).unload(function (){
				　　　　　$('.').unbind();
				　　　　　GUnload();
				　　});
				}else{
			　　　　 jalert('你使用的浏览器不支持 Google Map!');　　
			　　 }
			})

    //标记点
    var map;//全局调用地图信息
	function initialize() {     
		//1定点
		map = new GMap2(document.getElementById("map_canvas"));
		//是否有默认点(定位时要除以1000000)
		var srclat=document.getElementById("search.plat").value;
		var srlng=document.getElementById("search.plng").value;
		//默认定位点
		if(srclat!=0){
		    point= new GLatLng(srclat, srlng);
			map.setCenter(point, 16);
			//增加点的层
			var marker = new GMarker(point,{draggable:false});
			map.addOverlay(marker);
	         //单击标识事件,查看经纬度
	         GEvent.addListener(map, "click", function(overlay, latlng) {
		         GEvent.addListener(marker, "click", function() {
		              var html = "<table>" +
		                         "<tr><td>商家名称:</td> <td> <s:property value='mapcard.MERCHANTNAME'/> </td> </tr>" +
		                         "</table>";
		              marker.openInfoWindow(html);
		         });
	         });    
		}else{
			 map.setCenter(new GLatLng(24.894689, 118.602218), 12); 
		}
		//2009-11-25增加测距工具
      // 添加自定义的控件
      map.addControl(new GRulerControl());
	} 
	
	
	//取得周边信息数据2009-11-05()
	
		var userLogScript=<s:property value="json500m" escape="false"/>;
		var	doNowI =1;			
		//泡泡效果(每隔1s动态变化)
		//window.setTimeout(showmyMsg, 5000);
		function showmyMsg(){
		    //移除原标识点(仅标识一个点)
            map.clearOverlays();
            //如果没有猎物信息返回
			if(userLogScript.length ==0)return;
			//循环处理,从头开始
			if(userLogScript.length == doNowI)doNowI = 0;
			//取得当前指向点的经纬度信息,画该点,同时移动到该点
			var lat=userLogScript[doNowI]["LAT"];
			var lng=userLogScript[doNowI]["LNG"];
			var point=new GLatLng(lat, lng);
			map.panTo(point);
			//显示猎物信息的显示
			var name=userLogScript[doNowI]['SPECIALNAME'];//猎物信息
			var nameid=userLogScript[doNowI]['ID'];//连接会用到
			var merchantname=userLogScript[doNowI]['MERCHANTNAME'];//商家信息
			var merchantnameid=userLogScript[doNowI]['MERCHANTID'];//连接会用到
			var intro=userLogScript[doNowI]['SPECIALINTRO'];//猎物介绍

			var html = "<table>" +
						 "<tr><td>商家名称:</td> <td> "+merchantname+
						 "<tr><td>猎物名称:</td> <td> <a href=<%=path%>/selfconfig/mapcard.do?action=detailitem&search.pid="+nameid+"&search.ppid="+merchantnameid+">"+name+"</a>"+ 
                         "<tr><td>猎物介绍:</td> <td> "+intro+" </td> </tr>" +
                         "</table>";
			map.openInfoWindowHtml(point,html);
			//增加标注点的显示
			var marker = new GMarker(point);
			map.addOverlay(marker);
			//document.createTextNode(html)
			//$("#temp").val(doNowI);
			doNowI ++;
			window.setTimeout(showmyMsg, 5000);
		};
	</script> 


	</body>
</html>