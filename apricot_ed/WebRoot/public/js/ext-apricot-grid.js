/*
 * Apricot JS Library 1.1 Copyright(c) 2006-2008, Apricot JS, LLC.
 * xu.dahu@hotmail.com
 */

/**
 * 
 * 
 */
function createPageGrid(obj) {
	// 定义一个DataStore
	var store = new Ext.data.JsonStore( {
		root : 'rowSet',
		totalProperty : 'totalCount',
		idProperty : obj.id,
		url : toURL(obj.urls),

		fields : getFields(obj.items)
	});

	function getColumns(arr) {
		var cols = new Array();
		if (obj.checkbox == true)
			cols.push(new Ext.grid.CheckboxSelectionModel());
		for ( var i = 0; i < arr.length; i++) {
			var o = arr[i];
			var cfg = {
				id : o.id,
				header : o.label,
				dataIndex : ((o.code) ? (o.name + "_text") : o.name),
				width : nvl(o.width, 100),
				sortable : nvl(o.sortable, false)
			};
			if (o.hidden == true || o.hide == "grid" || o.hide == "all") {
				cfg["hidden"] = true;
				// continue;
			}

			cols.push(cfg);
		}

		return cols;
	}

	function getQuery(arr) {
		var cols = new Array();
		for ( var o in arr) {
		}
	}

	function getFields(arr) {
		var cols = new Array();

		for ( var i = 0; i < arr.length; i++) {
			var o = arr[i];
			cols.push(o.name);
			if (o.code)
				cols.push(o.name + "_text");
		}

		return cols;
	}

	// 设定分页
	var pagingBar = new Ext.PagingToolbar( {
		pageSize : nvl(obj.pageSize, 20),
		store : store,
		displayInfo : true,
		displayMsg : "当前显示 {0} - {1} ,总数据量 {2}",
		emptyMsg : "当前页没有数据显示",

		items : [ '-' ]
	});

	var grid = new Ext.grid.GridPanel( {
		id : obj.id,
		width : obj.width,
		height : obj.height,
		minSize : obj.minSize,
		maxSize : obj.maxSize,
		region : obj.region,
		split : obj.split,
		frame : obj.frame,
		deferRowRender : false,
		cm : obj.cm,
		bodyBorder : obj.bodyBorder,
		title : obj.title,
		collapsible : obj.collapsible,
		bodyStyle : "overflow:auto",
		store : store,
		buttons : obj.buttons,
		buttonAlign:obj.buttonAlign,
		trackMouseOver : false,
		renderTo : obj.renderTo,
		tools:obj.tools,
		loadMask : true,
		columns : getColumns(obj.items),
		sm : (obj.checkbox == true) ? new Ext.grid.CheckboxSelectionModel()
				: undefined,
		bbar : (obj.page == true) ? pagingBar : obj.bbar,
		tbar : getToolBar(obj),
		selModel : new Ext.grid.RowSelectionModel( {
			singleSelect : nvl(obj.singleSelect, true)
		})
	});

	function getToolBar(obj) {
		var arr = new Array();

		if (!isNull(obj.createFunction)) {
			arr[arr.length] = {
				text : '添加',
				pressed : false,
				cls : "x-btn-text-icon",
				tooltip : '添加一条新的记录',
				iconCls : 'gridBarItemAdd',
				handler : obj.createFunction
			};
			arr[arr.length] = "-";
		}
		if (!isNull(obj.modifyFunction)) {
			arr[arr.length] = {
				text : '修改',
				pressed : false,
				cls : "x-btn-text-icon",
				tooltip : '选中一条记录修改',
				iconCls : 'gridBarItemOption',
				handler : obj.modifyFunction
			};
			arr[arr.length] = "-";
		}
		if (!isNull(obj.deleteFunction)) {
			arr[arr.length] = {
				text : '删除',
				pressed : false,
				cls : "x-btn-text-icon",
				tooltip : '选中一条记录删除',
				iconCls : 'gridBarItemRemove',
				handler : obj.deleteFunction
			};
			arr[arr.length] = "-";
		}
		//自定义的
		if (!isNull(obj.toolBarFields)) {
			for ( var i = 0; i < obj.toolBarFields.length; i++) {
				arr[arr.length] = obj.toolBarFields[i];
			}
		}
		if (nvl(obj.filter, true) == true) {
			arr[arr.length] = {
				text : '过滤条件',
				pressed : false,
				cls : "x-btn-text-icon",
				tooltip : '输入查询条件去查询数据。',
				iconCls : 'gridBarItemQuery',
				handler : function() {
					// alert("ss");
				// var pm=getCmp(obj.id).getStore().lastOptions;
				// pm=(pm)?Ext.apply({},pm.params):{};
				var w = createFilterWindow(obj);
				w.show(this);
				// getCmp(obj.id+"_win_filter").getForm().setValues(pm);
			}
			};
			arr[arr.length] = "-";
		}

		if (!isNull(obj.tbar)) {
			for ( var i = 0; i < obj.tbar.length; i++) {
				arr[arr.length] = obj.tbar[i];
			}
		}
		return (arr.length > 0) ? arr : null;
	}

	if (!isNull(obj.rowclick)) {
		grid.getSelectionModel().addListener('rowselect', obj.rowclick);
	}
	if (obj.afterRowClick) {
		grid.on("rowclick", function() {
			obj.afterRowClick(getDataArrayStr(obj.id));
		});
	}

	if (obj.firstLoad != false) {
		store.removeAll();
		store.load( {
			params : {
				start : 0,
				limit : nvl(obj.pageSize, 20)
			}
		});
		if (obj.selectFirst != false)
			store.on("datachanged", function(e) {
				var s = getCmp(obj.id).getStore();
				if (!s)
					return;
				try {
					getCmp(obj.id).getSelectionModel().selectFirstRow();
				} catch (err) {
				}
			});
	}

	var cm = obj.contextMenu;
	if (cm)
		grid.on("contextmenu", function(ev) {
			cm(ev, this);
		});

	return grid;
}

/**
 * 创建窗口
 */

function createGridWindow(obj) {
	var grid = createPageGrid( {
		title : obj.label + "列表",
		id : obj.id + "_win_table",
		urls : nvl(obj.tableURL, obj.table + ".pages.go") + "?"
				+ urlEncode(obj.data),
		split : true,
		page : true,
		region : "center",
		height : 330,
		filter : false,
		selectFirst : false,
		toolBarFields : getToolBarFiels(obj),
		items : obj.columns,
		checkbox : obj.checkbox,
		selModel : obj.singleSelect,
		afterRowClick : function(d) {
			if (obj.afterRowClick)
				obj.afterRowClick(d);
			// getCmp("dialogWindow_xudahu111").close();
	}
	});

	function getToolBarFiels(obj) {
		var pm = {
			start : 0,
			limit : 10
		};
		var nm = (obj.keyName) ? obj.keyName : obj.name;
		return [ "查询: ", " ", {
			xtype : "trigger",
			height : 30,
			width : 200,
			triggerClass : 'x-form-search-trigger',
			onTriggerClick : function(e) {
				pm[nm] = this.getValue();
				pm["cAndAndAnd"] = "true";
				load(grid.getId(), pm);
			},
			emptyText : "关键字\"" + obj.label + "\"查询"
		} ]
	}

	var win = new Ext.Window( {
		id : "dialogWindow_xudahu111",
		title : "选择" + obj.label,
		closable : true,
		width : nvl(obj.width, 400),
		height : nvl(obj.height, 300),
		layout : 'border',
		hideBorders : true,
		bodyStyle : "padding:5px",
		modal : true,
		buttons : obj.buttons,
		items : [ grid ]
	});

	win.addButton( {
		text : "确定",
		handler : function() {
			if (obj.rowclick)
				obj.rowclick(getDataArrayStr(obj.id + "_win_table"));
			win.close();
		}
	});
	win.addButton( {
		text : "关闭",
		handler : function() {
			win.close();
		}
	});
	return win;
}
