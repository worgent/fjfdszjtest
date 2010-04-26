// ====================================================================================================
// [插件名称] jMessage
// ----------------------------------------------------------------------------------------------------
// [描 述] 测试站内消息
// ----------------------------------------------------------------------------------------------------
// [作者网名] fjfdszj
// [更新日期] 2009-08-13
// ====================================================================================================

// megaboxx的方法说明
(function($) {
	// 定义对象
	$.megaboxx = {
		selection_onchange : function(obj) {
			this.update_status_buttons();
		},

		is_selected : function(row) {
			var inputs = row.getElementsByTagName('input');
			return inputs.length && inputs[0].checked;
		},

		select_dropdown_onchange : function(obj) {
			if (obj.value == '^_^') {
				return false;
			}
			var status = obj.value
					? this['STATUS_' + obj.value.toUpperCase()]
					: this.STATUS_NONE;
			this.set_selection(status);
		},

		set_selection : function(status) {
			var rows = $('megaboxx').getElementsByTagName('tr');
			var threads = [];
			for (var i = 0; i < rows.length; i++) {
				if (!status || this.get_status(rows[i]) == status) {
					threads.push(this.get_thread_id(rows[i]));
				}
			}
			this.select_threads(threads, true);
		},

		get_thread_id : function(row) {
			return /thread_(\d+)/.exec(row.id)[1];
		},

		select_threads : function(threads, set) {
			var rows = $('megaboxx').getElementsByTagName('tr');
			for (var i = 0; i < rows.length; i++) {
				if (threads.indexOf(this.get_thread_id(rows[i])) != -1) {
					rows[i].getElementsByTagName('input')[0].checked = true;
				} else if (set) {
					rows[i].getElementsByTagName('input')[0].checked = false;
				}
			}
			this.update_status_buttons();
		},

		update_status_buttons : function() {
			var buttons = ge('inbox_status_buttons');
			if (!buttons) {
				return;
			}
			var threads = this.get_selected_threads();
			var unread_disabled = true;
			var read_disabled = true;
			for (var i = 0; i < threads.length; i++) {
				var status = this.get_status(ge('thread_' + threads[i]));
				if (status == this.STATUS_UNREAD) {
					read_disabled = false;
				} else {
					unread_disabled = false;
				}
			}
			var message_selector = ge('message_selector');
			if (!threads.length && message_selector) {
				message_selector.selectedIndex = 0;
			}
			var delete_disabled = read_disabled && unread_disabled;
			var li = buttons.getElementsByTagName('li');
			var loop = [{
						l : li[0],
						d : unread_disabled
					}, {
						l : li[1],
						d : read_disabled
					}, {
						l : li[2],
						d : delete_disabled
					}];
			for (var i = 0; i < loop.length; i++) {
				if (loop[i].l) {
					loop[i].l.className = trim(loop[i].l.className.replace(
							'menu_disabled', ''))
							+ (loop[i].d ? ' menu_disabled' : '');
				}
			}
		},

		get_status : function(row) {
			if (row.className.indexOf('new_message') != -1) {
				return this.STATUS_UNREAD;
			} else {
				return this.STATUS_READ;
			}
		},

		get_selected_threads : function() {
			var rows = $('megaboxx').getElementsByTagName('tr');
			var threads = [];
			for (var i = 0; i < rows.length; i++) {
				if (this.is_selected(rows[i])) {
					threads.push(this.get_thread_id(rows[i]));
				}
			}
			return threads;
		},

		get_unread_count : function(threads) {
			var count = 0;
			for (var i = 0; i < threads.length; i++) {
				var row = ge('thread_' + threads[i]);
				if (row.className == 'new_message')
					count++;
			}
			return count;
		},

		// 单击事件
		status_menu_onclick : function(obj, action, threads) {
			if (!threads.length|| (typeof obj == 'object' && obj.parentNode.className.indexOf('disabled') != -1)) {
				return false;
			}
			if (action == 'delete') {
				//obj=true;//因为参数threads,经过回调后出现异常。
				if (typeof obj == 'boolean' && obj) {
					//alert(threads[0]);
					for(i=0;i<threads.length;i++){
						$("tr[id='thread_"+threads[i] +"']").remove();
					}
				} else {
					//alert(threads[0]); 
					jConfirm('你确定吗?', '确认窗', function(r) {
						jAlert('确认值是: ' + r, '确认结果');
					});
					/*
					jAlert('确认值是: ' , '确认结果');
					
					jConfirm('你确定吗?', '确认窗', function(r) {
						jAlert('确认值是: ' + r, '确认结果');
					});
					*/
					
					/*
					confirm(((threads.length == 1)
									? '你确定要删除这封站内信吗？'
									: '你确定要删除这些站内信吗？'), function() {
								 alert(this.threads[0]);
								 this.status_menu_onclick(true,action,threads);
									},this,this.threads);			
					*/ 
				}
			} else {
			}
		},

		ajax_callback : function(response) {
			var reload_needed = false;
			// ID
			if (!$('#megaboxx>tbody>tr').length) {
				reload_needed = true;
			}
			if (reload_needed) {
				document.location.reload();
				return;
			}
		},

		submit_delete : function(id, f) {
			(new pop_dialog).show_choice('删除站内信', '你确定要删除这封站内信吗？', '删除',
					function() {
						window.location = '/message/edit.do?action=delete&id='
								+ id + '&f=' + f
					}, '取消', function() {
						generic_dialog.get_dialog(this).hide();
					});
			$("dialog_button2").className = "input-submit gray";
			return false;
		},

		submit_prehook : function(obj, inline, sessionId) {
			var form = ge('compose_message');
			var length = trim(form.message.value).length;
			var subject = ge('subject_field');
			var error_text = null;
			if (subject) {
				var subject_length = trim(subject.value).length;
				if (subject_length == 0) {
					error_text = '标题不能为空';
				} else if (subject_length > 200) {
					error_text = '标题不能超过200个字';
				}
			}

			var ids = ge('ids');
			var empty_ok = false;
			if (length == 0 && !empty_ok) {
				error_text = '请输入站内信的内容';
			} else if (length > 5000) {
				error_text = '内容不能超过5000个字';
			} else if (ids && tokenizer.is_empty(ids)) {
				error_text = '请输入好友的姓名';
			}

			var inputs = document.getElementsByName("ids[]");
			if (inputs.length > 20) {
				error_text = '收件人不能超过20个';
			}

			var error = ge('error');
			if (error) {
				error.parentNode.removeChild(error);
			}
			if (error_text) {
				error = document.createElement('div');
				error.id = 'error';
				error.innerHTML = error_text;
				form.insertBefore(error, form.firstChild);
				return false;
			}

			if (inline) {
				ge('message').disabled = 'true';
				var status = ge('message_post_status');
				status.style.display = "block";
				var post = {
					id : form.thread.value,
					message : form.message.value,
					folder : megaboxx_data.folder
				};
				new Ajax.Request("/message/reply.do", {
							method : 'post',
							parameters : "post="
									+ encodeURIComponent(XN.JSON.build(post)),
							onComplete : submit_callback,
							onFailure : submit_callback
						});
			}
			return !inline;
		},

		read_message : function(id, f) {
			window.location = "/message/read.do?id=" + id + "&f=" + f;
			return true;
		}

	};

})(jQuery);

function deletedate(ids) {
	alert(ids[0]);
	$("tr[id='thread_" + ids[0] + "']").remove();
}

function submit_callback(r) {
	var text = r.responseText;
	try {
		eval("url=" + text);
		var to = url.url;
		if (to != null) {
			window.location = to;
		}
		return;
	} catch (e) {
	}
	if (text.substr(0, 5) == 'error') {
		var form = ge('compose_message');
		var error = ge('error');
		if (error) {
			error.parentNode.removeChild(error);
		}
		error = document.createElement('div');
		error.id = 'error';
		error.innerHTML = '<h2>' + text.substr(5, text.length) + '</h2>';
		form.insertBefore(error, form.firstChild);
	} else {
		var thread = ge('messages');
		var msg = document.createElement('div');
		thread.appendChild(msg);
		msg.innerHTML = text;
		ge('message').value = '';
	}
	ge('message').disabled = '';
	var i = 0;
	var msg = null;
	while (msg = ge('msg_' + (i++))) {
		remove_css_class_name(msg, 'unread');
	}
	ge('message_post_status').style.display = 'none';
}

function jump_another(p) {
	ge('p').value = p;
	ge('read_message').submit();
}

function jump_callback(r) {
	var text = r.responseText;
	try {
		eval("url=" + text);
		var to = url.url;
		if (to != null) {
			window.location = to;
		}
		return true;
	} catch (e) {
	}
	alert("内部错误，请过会儿重试");
	return false;
}

function scrollHere(scroll_obj, page_obj) {
	if (!scroll_obj || !page_obj) {
		return;
	}
	var wh = window.innerHeight
			? window.innerHeight
			: document.documentElement.clientHeight;
	var dh = elementY(page_obj) + page_obj.offsetHeight;
	var ey = elementY(scroll_obj) - 100;
	var bh = Math.max(document.documentElement.scrollTop,
			document.body.scrollTop);
	if (dh - ey < wh) {
		ey -= (wh + ey) - dh;
	}
	if (dh < wh + 100) {
		return;
	}
	var obj = {
		dy : ey,
		i : 750,
		cy : 0,
		bh : bh,
		st : (new Date()).getTime()
	};
	obj.h = setInterval(function() {
				var t = (new Date()).getTime();
				var s = null;
				if (t > this.i + this.st) {
					s = this.dy;
					clearInterval(this.h);
				} else {
					var p = (t - this.st) / this.i;
					s = (this.dy - this.bh)
							* (1 - Math.pow(1 - Math.sin(Math.PI / 2 * p), 2))
							+ this.bh;
				}
				if ((this.ls1 && this.ls1 != document.documentElement.scrollTop)
						|| (this.ls2 && this.ls2 != document.body.scrollTop)) {
					clearInterval(this.h);
				} else {
					document.documentElement.scrollTop = document.body.scrollTop = s;
					this.ls1 = document.documentElement.scrollTop;
					this.ls2 = document.body.scrollTop;
				}
			}.bind(obj), 25);
}

function ge(id) {
	var el;
	if (isString(id) || isNumber(id)) {
		el = document.getElementById(id + "");
	} else {
		el = id;
	}
	if (!el) {
		return null;
	}
	if (!el._extendLevel) {
		if (el._extendLevel)
			return e1;
	}
	return el;

}