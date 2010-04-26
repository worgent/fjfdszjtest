

var megaboxx=function(endpoint){if(endpoint){this.endpoint=endpoint;}else{this.endpoint='/message/ajax.do';}}
megaboxx.prototype.STATUS_ALL=0;megaboxx.prototype.STATUS_READ=1;megaboxx.prototype.STATUS_UNREAD=2;megaboxx.prototype.STATUS_NONE=-1;if(typeof tokenizer!='undefined'){tokenizer.prototype.onselect=function(){megaboxx.reset_rand_id();}}

megaboxx.prototype.selection_onchange=function(obj){this.update_status_buttons();}
megaboxx.prototype.is_selected=function(row){var inputs=row.getElementsByTagName('input');return inputs.length&&inputs[0].checked;}
megaboxx.prototype.select_dropdown_onchange=function(obj){
	if(obj.value=='^_^'){return false;}
	var status=obj.value?this['STATUS_'+obj.value.toUpperCase()]:this.STATUS_NONE;
	this.set_selection(status);
}

megaboxx.prototype.set_selection=function(status){
	var rows=$('megaboxx').getElementsByTagName('tr');
	var threads=[];
	for(var i=0;i<rows.length;i++){
		if(!status||this.get_status(rows[i])==status){
			threads.push(this.get_thread_id(rows[i]));
		}
	}
	this.select_threads(threads,true);
}
megaboxx.prototype.get_thread_id=function(row){return/thread_(\d+)/.exec(row.id)[1];}
megaboxx.prototype.select_threads=function(threads,set){
	var rows=$('megaboxx').getElementsByTagName('tr');
	for(var i=0;i<rows.length;i++){
		if(threads.indexOf(this.get_thread_id(rows[i]))!=-1){
			rows[i].getElementsByTagName('input')[0].checked=true;
		}else if(set){
			rows[i].getElementsByTagName('input')[0].checked=false;
		}
	}
	this.update_status_buttons();
}

megaboxx.prototype.update_status_buttons=function(){
	var buttons=ge('inbox_status_buttons');
	if(!buttons){return;}
	var threads=this.get_selected_threads();
	var unread_disabled=true;
	var read_disabled=true;
	for(var i=0;i<threads.length;i++){
		var status=this.get_status(ge('thread_'+threads[i]));
		if(status==this.STATUS_UNREAD){
			read_disabled=false;
		}else{
			unread_disabled=false;
		}
	}
	var message_selector=ge('message_selector');
	if(!threads.length&&message_selector){message_selector.selectedIndex=0;}
	var delete_disabled=read_disabled&&unread_disabled;
	var li=buttons.getElementsByTagName('li');
	var loop=[{l:li[0],d:unread_disabled},{l:li[1],d:read_disabled},{l:li[2],d:delete_disabled}];
	for(var i=0;i<loop.length;i++)
	{
		if(loop[i].l){
			loop[i].l.className=trim(loop[i].l.className.replace('menu_disabled',''))+(loop[i].d?' menu_disabled':'');
		}
	}
}

megaboxx.prototype.get_status=function(row){if(row.className.indexOf('new_message')!=-1){return this.STATUS_UNREAD;}else{return this.STATUS_READ;}}

megaboxx.prototype.get_selected_threads=function(){
	var rows=$('megaboxx').getElementsByTagName('tr');
	var threads=[];
	for(var i=0;i<rows.length;i++){if(this.is_selected(rows[i])){threads.push(this.get_thread_id(rows[i]));}}
	return threads;
}
megaboxx.prototype.get_unread_count=function(threads){
	var count=0;
	for(var i=0;i<threads.length;i++){
		var row=ge('thread_'+threads[i]);
		if(row.className=='new_message')
			count++;
	}
	return count;
}


megaboxx.prototype.status_menu_onclick=function(obj,action,threads){
	threads=threads?threads:this.get_selected_threads();
	if(!threads.length||(typeof obj=='object'&&obj.parentNode.className.indexOf('disabled')!=-1)){this.update_status_buttons();return false;}
	var count=this.get_unread_count(threads);
	this.reload_needed=false;
	var post={action:action,ids:threads,folder:megaboxx_data.folder,unread_count:count};
	if(action=='delete'){
			if(typeof obj=='boolean'&&obj){
				var boxx=ge('megaboxx');
				for(var i=0;i<threads.length;i++){
					var row=document.getElementById('thread_'+threads[i]);
					row.parentNode.removeChild(row);
				}
				post.slice=boxx.getElementsByTagName('tr').length;
				var loading=ge('loading_boxx');
				if(!boxx.getElementsByTagName('tr').length){this.reload_needed=true;}
				if(!loading){
					var loading=document.createElement('div');
					loading.innerHTML=['<table id="loading_boxx" class="',boxx.className,'"><tr><td></td></tr></table>'].join('');
					loading=loading.getElementsByTagName('table')[0];
					boxx.parentNode.insertBefore(loading,boxx.nextSibling);
					window.setTimeout("ge('loading_boxx').style.display='none'", 1000);
				}else{
					loading.style.display='block';
					window.setTimeout("ge('loading_boxx').style.display='none'", 1000);
				}
			}else{
					var dialog=new contextual_dialog();
					dialog.set_context(obj);
					dialog.show_choice(((threads.length==1)?'你确定要删除这封站内信吗？':'你确定要删除这些站内信吗？'),
															'',
															'删除',
															function(){this[0].status_menu_onclick(true,action,threads);this[1].hide()}.bind([this,dialog]),
															'取消',
															function(){generic_dialog.get_dialog(this).hide()});		
					$("dialog_button2").className="input-submit gray";
					return false;
			}
	}else{
			for(var i=0;i<threads.length;i++){
				var row=document.getElementById('thread_'+threads[i]);
				var status=this.get_status(row);
				if(action=='toggle_read'){
					if(status==this.STATUS_UNREAD){
						action='mark_read';
					}else{
						action='mark_unread';
					}
					post.action=action;
				}
				if(action=='mark_read'){
					row.className=trim(row.className.replace('new_message',''));
				}else{
					row.className=trim(row.className.replace(/ ?new_message ?|$/,' new_message '));
				}
			}
	}
	new Ajax.Request( '/message/ajax.do', {
		    method: 'get',
		    parameters: "post="+encodeURIComponent(XN.JSON.build(post)),
		    onComplete: this.ajax_callback.bind(this),
		    onFailure:this.ajax_callback.bind(this)});	    
	this.update_status_buttons();
	var message_selector=ge('message_selector');
	message_selector?message_selector.selectedIndex=0:false;
	return false;
}

megaboxx.prototype.ajax_callback=function(response){
	var reload_needed=false;
	if (!$('megaboxx').getElementsByTagName('tr').length) {
        reload_needed=true;
    }
	if(reload_needed){
		document.location.reload();
		return;
	}

	eval("rt="+response.responseText);
	var count=rt.unReadCount;
	var message_count=ge('global_inbox_link');
	if(count>0){
		message_count.innerHTML='站内信<span class="count">('+count+')</span>'
	}
	else message_count.innerHTML='站内信';
	var top=ge("message_count_top");
	if(count>0){
		if(top.innerHTML.indexOf('/')>0){
			new_count=count+'封未读'+top.innerHTML.substr(top.innerHTML.indexOf('/'),top.innerHTML.length);

		}
		else {
			new_count=count+'封未读/'+top.innerHTML;
		}
	}
	else new_count=top.innerHTML.substr(top.innerHTML.indexOf('/')+1,top.innerHTML.length);
	top.innerHTML=new_count;
	var end=ge("message_count_end");
	end.innerHTML=new_count;
}

megaboxx.prototype.submit_delete=function(id,f){
	(new pop_dialog).show_choice('删除站内信',
								'你确定要删除这封站内信吗？',
								'删除',
								function(){window.location='/message/edit.do?action=delete&id='+id+'&f='+f},
								'取消',
								function(){generic_dialog.get_dialog(this).hide();});
	$("dialog_button2").className="input-submit gray";
	return false;
}

megaboxx.prototype.submit_prehook=function(obj,inline,sessionId){
	var form=ge('compose_message');
	var length=trim(form.message.value).length;
	var subject=ge('subject_field');
	var error_text=null;
	if(subject){
		var subject_length=trim(subject.value).length;
		if(subject_length==0){
			error_text='标题不能为空';
		}
		else if(subject_length>200){
			error_text='标题不能超过200个字';
		}
	}
	
	var ids=ge('ids');
	var empty_ok=false;
	if(length==0&&!empty_ok){
		error_text='请输入站内信的内容';
	}
	else if(length>5000){
		error_text='内容不能超过5000个字';
	}
	else if(ids&&tokenizer.is_empty(ids)){
		error_text='请输入好友的姓名';
	}

	var inputs = document.getElementsByName("ids[]");
	if(inputs.length>20){
		error_text='收件人不能超过20个';
	}

	var error=ge('error');
	if(error){error.parentNode.removeChild(error);}
	if(error_text){
		error=document.createElement('div');
		error.id='error';
		error.innerHTML=error_text;
		form.insertBefore(error,form.firstChild);
		return false;
	}
	
	if(inline){
		ge('message').disabled='true';
		var status=ge('message_post_status');
		status.style.display="block";
		var post={id:form.thread.value,message:form.message.value,folder:megaboxx_data.folder};
		new Ajax.Request( "/message/reply.do", {
		    method: 'post',
		    parameters: "post="+encodeURIComponent(XN.JSON.build(post)),
		    onComplete: submit_callback,
		    onFailure:submit_callback});
	}
	return!inline;
}

function submit_callback(r){
	var text=r.responseText;
	try{
		eval("url="+text);
		var to=url.url;
		if(to!=null){
			window.location=to;
		}
		return;
	}catch(e){}
	if(text.substr(0,5)=='error')
	{
		var form=ge('compose_message');
		var error=ge('error');
		if(error){error.parentNode.removeChild(error);}
		error=document.createElement('div');
		error.id='error';
		error.innerHTML='<h2>'+text.substr(5,text.length)+'</h2>';
		form.insertBefore(error,form.firstChild);
	}
	else{
		var thread=ge('messages');
		var msg=document.createElement('div');
		thread.appendChild(msg);
		msg.innerHTML=text;
		ge('message').value='';
	}
	ge('message').disabled='';
	var i=0;var msg=null;
	while(msg=ge('msg_'+(i++))){remove_css_class_name(msg,'unread');}
	ge('message_post_status').style.display='none';
}

function jump_another(p){
	ge('p').value=p;
	ge('read_message').submit();
	//var time=ge("time").value;
	//window.location="/message/read.do?id="+id+"&p="+p+"&f="+f;
	//new Ajax.Request( "/message/read.do", {
	//	    method: 'post',
	//	    parameters: "id="+id+"&p="+p+"&f="+f+"&time="+time,
	//	    onComplete: jump_callback,
	//	    onFailure:jump_callback});
}
function jump_callback(r){
	var text=r.responseText;
	try{
		eval("url="+text);
		var to=url.url;
		if(to!=null){
			window.location=to;
		}
		return true;
	}catch(e){}
	alert("内部错误，请过会儿重试");
	return false;
}

megaboxx.prototype.read_message=function(id,f){
	window.location="/message/read.do?id="+id+"&f="+f;
	return true;
}


function scrollHere(scroll_obj,page_obj){if(!scroll_obj||!page_obj){return;}
var wh=window.innerHeight?window.innerHeight:document.documentElement.clientHeight;var dh=elementY(page_obj)+page_obj.offsetHeight;var ey=elementY(scroll_obj)-100;var bh=Math.max(document.documentElement.scrollTop,document.body.scrollTop);if(dh-ey<wh){ey-=(wh+ey)-dh;}
if(dh<wh+100){return;}
var obj={dy:ey,i:750,cy:0,bh:bh,st:(new Date()).getTime()};obj.h=setInterval(function(){var t=(new Date()).getTime();var s=null;if(t>this.i+this.st){s=this.dy;clearInterval(this.h);}else{var p=(t-this.st)/this.i;s=(this.dy-this.bh)*(1-Math.pow(1-Math.sin(Math.PI/2*p),2))+this.bh;}
if((this.ls1&&this.ls1!=document.documentElement.scrollTop)||(this.ls2&&this.ls2!=document.body.scrollTop)){clearInterval(this.h);}else{document.documentElement.scrollTop=document.body.scrollTop=s;this.ls1=document.documentElement.scrollTop;this.ls2=document.body.scrollTop;}}.bind(obj),25);}
