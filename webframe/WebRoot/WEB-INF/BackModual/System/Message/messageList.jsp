<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<style type="text/css">
.message_rows .delete_msg {
	PADDING-LEFT: 10px;
	WIDTH: 24px
}

.message_rows .delete_msg A {
	MARGIN-TOP: 1px;
	WIDTH: 13px;
	DISPLAY: block;
	BACKGROUND: url(/imgpro/icons/msg-icon.png) no-repeat 0px -75px;
	HEIGHT: 14px
}

.message_rows .delete_msg A:hover {
	BACKGROUND: url(/imgpro/icons/msg-icon.png) #3b5998 no-repeat 100% 100%
}

.message_rows .name_and_date {
	PADDING-LEFT: 10px
}

.message_rows .name_and_date .name {
	WIDTH: 130px;
	TEXT-OVERFLOW: ellipsis;
	DISPLAY: block;
	WHITE-SPACE: nowrap;
	OVERFLOW: hidden;
	MARGIN-RIGHT: 20px
}

.name_and_date .date {
	PADDING-BOTTOM: 0px;
	PADDING-LEFT: 0px;
	PADDING-RIGHT: 0px;
	DISPLAY: block;
	COLOR: #777;
	FONT-SIZE: 9px;
	PADDING-TOP: 1px
}

#sortable1 li,#sortable2 li,#sortable3 li {
	margin: 0 5px 5px 5px;
	padding: 5px;
	font-size: 1.2em;
	width: 120px;
}
</style>


<script type="text/javascript">
        //站内消息初始化信息
		//onloadRegister(function() {megaboxx = new megaboxx()});
		var megaboxx_data={"folder":0};


		//1.导航
		$(function() {
		    //设置内容可排序
			//$("#sortable1, #sortable2,#sortable3").sortable().disableSelection();
	        //响应不同的面板
			var $tabs = $("#tabs").tabs();
	 
			var $tab_items = $("ul:first li",$tabs).droppable({
				accept: ".connectedSortable li",
				hoverClass: "ui-state-hover",
				drop: function(ev, ui) {
					var $item = $(this);
					var $list = $($item.find('a').attr('href')).find('.connectedSortable');
	 
					ui.draggable.hide('slow', function() {
						$tabs.tabs('select', $tab_items.index($item));
						$(this).appendTo($list).show('slow');
					});
				}
			});
		});
		
		
		//2.Jquery数据验证
		$(document).ready(function() {
		//测试封装的alert
		//alert("say good!")
		//测试显示站内提示短信诈骗信息
		//showTips();
    	//窗体验证
   		$.formValidator.initConfig({formid:"form1",onerror:function(){alert("校验没有通过，具体错误请看错误提示")}});
        	//收件人验证     
        	$("#preccode").formValidator().inputValidator({min:1,onerror:"这里至少要一个字符,请确认"}).functionValidator({
			    fun:function(val,elem){
				        if(val=="猫冬"){
						    return true;
					    }else{
						    return "额外校验失败,想额外校验通过，请输入\"猫冬\""
					    }
					}
			}); 
			//主题
			$("#ptitle").formValidator().inputValidator({min:1,onerror:"这里至少要一个字符,请确认"});  
		    //内容
			$("#pcontent").formValidator().inputValidator({min:1,onerror:"这里至少要一个字符,请确认"}); 
		});
		//3.Table数据排序
		$(document).ready(function(){ 
		    //收件箱
			$("#revTable").tablesorter({ 
				headers: { 
					1: {sorter: false }       
				}   
			}); 
			//发件箱
			$("#sendTable").tablesorter({ 
				headers: { 
					1: {sorter: false }       
				}   
			}); 
			//系统
			$("#systemTable").tablesorter({ 
				headers: { 
					1: {sorter: false }       
				}   
			}); 
		}); 
		</script>
	</head>
	<body>
		<div class="demo">
			<div id="tabs">
				<!-- 导航 -->
				<ul>
					<li>
						<a href="#tabs-revmessage">收件箱</a>
					</li>
					<li>
						<a href="#tabs-sendmessage">发件箱</a>
					</li>
					<li>
						<a href="#tabs-systemmessage">通知</a>
					</li>
					<li>
						<a href="#tabs-writemessage">写站内信</a>
					</li>
				</ul>
				<!-- 收件箱 -->
				<div id="tabs-revmessage">
					<table id="revTable" class="tablesorter" align="center" border="0"
						cellpadding="0" cellspacing="1" width="750">
						<thead>
							<tr>
								<th>
									发信人
								</th>
								<th>
									发送时间
								</th>
								<th>
									主题
								</th>
								<th>
									短信状态
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="revList" value="%{pageList.objectList}">
								<tr>
									<td class="bgColor3">
										<span class="font1"> <s:property
												value="#revList.SENDCODE" /> </span>
									</td>
									<td class="bgColor4">
										<s:property value="#revList.SENDTIME" />
									</td>
									<td class="bgColor4">
										<s:property value="#revList.TITLE" />
									</td>
									<td class="bgColor4">
										<s:property value="#revList.STATE" />
									</td>
									<td class="bgColor4">
										<a>删除</a>
										<a>查看</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tr class="bgColor3">
							<td colspan="5">
								分页:
								<qzgf:pages value="%{pageList.pages}" />
							</td>
						</tr>
					</table>
				</div>
				<!-- 发件箱 -->
				<div id="tabs-sendmessage">
					<!-- 站内消息广告 -->
					<style type="text/css">
div.note {
	position: relative;
	border: 1px solid #CCC;
	padding: 10px;
	padding-left: 70px;
	margin-top: 10px;
	background: url(http://s.xnimg.cn/imgpro/bg/warning_bg.png) #FEFFCF
		no-repeat 10px center;
}

div.note h3 {
	margin-bottom: 1ex;
}

div.note p {
	font-size: 14px;
}

div.note a.close {
	position: absolute;
	right: 10px;
	top: 10px;
}
</style>
					<div class="note" id="message-tips"  style="height: 90px; width: 990px;">
						<h3>
							谨防虚假抽奖消息
						</h3>
						<a class="close" href="#message-tips" onclick="hideTips();">关闭</a>
						<p>
							亲爱的用户朋友：近日有人以校内官方名义发布虚假抽奖信息。我们再次敬告所有用户，请不要轻信所谓的抽奖、中奖、送礼信息，以免个人财产受到损失。如果发现请立即向校内举报。谢谢！
							<a target="_blank"
								href="http://xiaonei.com/getsysupdateinfo.do?curpage=1#zhongjiang">点击查看详情</a>
						</p>
					</div>

					<script>
					
					function hideTips() {
						$("#message-tips").hide();
					}
					function showTips() {
						$("#message-tips").show();
					}
					</script>

					<!--消息具体内容 -->
					<table class="message_rows" id="megaboxx" width="100%" style="height: 90px; width: 990px;">
						<tbody>
							<s:iterator id="revList" value="%{pageList.objectList}">
								<tr id="thread_<s:property value="#revList.ID" />" class="">
									<td class="msg_icon">
										<a href="#"
											onclick="return $.megaboxx.status_menu_onclick(this, 'toggle_read', [955789751])">&nbsp;</a>
									</td>
									<td class="checkbox_toggle">
										<input type="checkbox"
											onclick="$.megaboxx.selection_onchange(this)" />
									</td>
									<td class="profile_pic">
										<a href="http://xiaonei.com/profile.do?id=286409824"><img
												src="http://hdn311.xnimg.cn/photos/hdn311/20090812/1515/tiny_VZdz_11505p204237.jpg"
												width="50" />
										</a>
									</td>
									<td class="name_and_date">
										<span class="name"> <a
											href="http://xiaonei.com/profile.do?id=286409824"
											title="#revList.RECCODE"><s:property
													value="#revList.RECCODE" />
										</a> </span>
										<span class="date"><s:property
												value="#revList.SENDTIME" />
										</span>
									</td>
									<td class="subject">
										<div class="subject_wrap">
											<a href="#nogo"
												onclick="$.megaboxx.read_message(<s:property value="#revList.ID" />,0);"
												class="subject_text"> <s:property value="#revList.TITLE" />
											</a>
											<div class="snippet_wrap">
												<a href="#nogo"
													onclick="$.megaboxx.read_message(<s:property value="#revList.ID" />,0);"
													class="snippet"><s:property value="#revList.TITLE" />
												</a>
											</div>
										</div>
									</td>
									<td class="delete_msg">
										<a href="#"
											onclick="return $.megaboxx.status_menu_onclick(this, 'delete', [<s:property value="#revList.ID" />])"><s:property value="#revList.ID"/>&nbsp;</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

				</div>
				<!-- 系统消息 -->
				<div id="tabs-systemmessage">
					<div>
						<p>
							系统通知信息
						</p>
					</div>
					<table id="systemTable" class="tablesorter" align="center"
						border="0" cellpadding="0" cellspacing="1" width="750">
						<thead>
							<tr>
								<th>
									发信人
								</th>
								<th>
									发送时间
								</th>
								<th>
									主题
								</th>
								<th>
									短信状态
								</th>
								<th>
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="revList" value="%{pageList.objectList}">
								<tr>
									<td class="bgColor3">
										<span class="font1"> <s:property
												value="#revList.SENDCODE" /> </span>
									</td>
									<td class="bgColor4">
										<s:property value="#revList.SENDTIME" />
									</td>
									<td class="bgColor4">
										<s:property value="#revList.TITLE" />
									</td>
									<td class="bgColor4">
										<s:property value="#revList.STATE" />
									</td>
									<td class="bgColor4">
										<a>删除</a>
										<a>查看</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<tr class="bgColor3">
							<td colspan="5">
								分页:
								<qzgf:pages value="%{pageList.pages}" />
							</td>
						</tr>
					</table>
				</div>
				<!-- 写站内消息 -->
				<div id="tabs-writemessage">
					<form id="compose_message" action="/message/send.do" method="post"
						onsubmit="return false">
				<!-- 
				<div>
					<s:textfield name="preccode" label="收件人" ></s:textfield>
				</div>
				<div>
					<s:textfield name="ptitle" label="主题"></s:textfield>
				</div>
				<div>
					<s:textarea name="pcontent" label="内容"></s:textarea>
				</div>
				 -->
						<div>
							收件人：
							<input type="text" id="preccode" style="width: 120px" />
							<div id="preccodeTip" style="width: 300px"></div>
						</div>
						<div>
							主题：
							<input type="text" id="ptitle" style="width: 120px" />
							<div id="ptitleTip" style="width: 300px"></div>
						</div>
						<div>
							内容：
							<textarea id="pcontent" style="width: 120px" cols="4" rows="7"></textarea>
							<div id="pcontentTip" style="width: 300px"></div>
						</div>
						<div>
							<input tabindex="1" value="发送" class="inputsubmit" type="submit"
								name="send_button"
								onclick="if (megaboxx.submit_prehook(this,false)) {this.form.onsubmit=null;this.form.submit();this.disabled=true}">
							<input tabindex="2" value="取消" name="cancel"
								class="inputsubmit gray" type="button" onclick="history.go(-1);">
						</div>
					</form>
				</div>
			</div>

		</div>
		<!-- End demo -->
	</body>
</html>