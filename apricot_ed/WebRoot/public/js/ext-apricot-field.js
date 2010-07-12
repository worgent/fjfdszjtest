function createField(aObj, fm, noEdit) {

	var obj = Ext.apply( {}, aObj);
	// 定义默认的数据
	var dcfg = {
		fieldLabel : obj.label,
		value : obj.value,
		anchor : nvl(obj.anchor, "98%"),
		width : obj.width,
		height : obj.height,
		readOnly : (noEdit == true) ? true : obj.readOnly,
		name : obj.name,
		allowBlank : nvl(obj.allowBlank, true),
		format : obj.format,
		iconCls : obj.iconCls,
		disabled : nvl(obj.disabled, false),
		handler : obj.handler,
		pressed : obj.pressed,
		value : nvl(obj.value, ""),
		text : obj.text,
		id : obj.id,
		inputType : obj.inputType,
		emptyText : obj.emptyText,
		listeners : obj.listeners,
		validator : obj.validator,
		maxLength : obj.maxLength,
		maxLengthText : obj.maxLengthText,
		invalidText : obj.invalidText,
		hidden : obj.hidden,
		hideMode : obj.hideMode,
		hideLabel : obj.hideLabel

	};

	var cfg = {};
	if (noEdit == true) {
		obj.xtype = "textfield";
		if (obj.code)
			dcfg.name = dcfg.name + "_text";
	}

	//数字域
	if (obj.xtype == "number") {
		return new Ext.form.NumberField(dcfg);
	}

	//下拉选择框
	if (obj.xtype == "combo") {
		dcfg.editable = !(dcfg.readOnly);
		delete dcfg.readOnly;
		var s = new Ext.data.ArrayStore( {
			fields : [ "value", "label" ],
			autoLoad : true,
			url : toURL(obj.code + ".to")
		});
		if (obj.data) {
			s = new Ext.data.ArrayStore( {
				fields : [ "value", "label" ],
				data : obj.data,
				autoLoad : true
			});
		}
		function changeValue(f, n, o) {
			var form = f.findParentByType("form");
			if(!form) return;
			var d = {};
			d[obj.znName] = n.get("label");
			form.getForm().setValues(d);

		}
		cfg = {
			displayField : "label",
			valueField : "value",
			store : s,
			mode : "local",
			hiddenName : obj.name,
			lazyInit : false,
			forceSelection : true,
			triggerAction : "all"

		};
		if (!dcfg.listeners)
			dcfg["listeners"] = {};
		Ext.apply(dcfg.listeners, {
			"select" : changeValue
		});
		dcfg.readOnly = false;
		return new Ext.form.ComboBox(Ext.apply(cfg, dcfg));
	}
	

	//下拉多选框
	if (obj.xtype == "checkboxgroup") {
		
		if(obj.code && !obj.items){
			cfg = {
					fieldLabel : dcfg.label,
					items : []
				};
			var vindex=(obj.keyField=="label")?1:0;
			doSyncRequest(toURL(obj.code + ".to"),{},function(b){
				
				for(var i=0;i<b.length;i++){
					
					cfg.items.push({boxLabel:b[i][1],name:b[i][vindex],value:b[i][0]});
				}
			});
		}else{
		cfg = {
			fieldLabel : 'Auto Layout',
			items : obj.items
		};
		}
		return new Ext.form.CheckboxGroup(Ext.apply(cfg, dcfg));
	}
	//dcfg["name"]=obj.name;

	// 文本区
	if (obj.xtype == "textarea") {
		return new Ext.form.TextArea(dcfg);
	}

	if (obj.xtype == "checkbox") {
		return new Ext.form.Checkbox( {
			id : obj.id,
			name : obj.name,
			fieldLabel : obj.label,
			checked : false,
			handler : obj.handler,
			autoWidth : true
		});
	}

	//弹出选择
	if (obj.xtype == "trigger") {
		dcfg.editable = !(dcfg.readOnly);
		delete dcfg.readOnly;
		var kMap = obj.map;
		var wCfg = {
			rowclick : function(o) {
				if (obj.checkValid && !obj.checkValid(o))
					return;
				var kO = {};
				if (kMap) {
					for ( var n in o) {
						if (kMap[n]) {
							kO[kMap[n]] = o[n];
						} else
							kO[n] = o[n];
					}
				} else
					kO = Ext.apply( {}, o);
				getCmp(fm).getForm().setValues(kO);

			}
		};

		wCfg = Ext.apply(wCfg, obj);
		wCfg.width = 450;
		wCfg.height = 300;
		// wCfg["items"]=obj.items;
		cfg = {
			onTriggerClick : function(e) {
				if (obj.getData)
					wCfg["data"] = obj.getData();
				wCfg["buttons"] = obj.buttons;
				wCfg["afterRowClick"] = obj.afterRowClick;
				wCfg["checkbox"] = obj.checkbox;
				wCfg["singleSelect"] = obj.singleSelect;

				var w = createGridWindow(wCfg);
				w.show(this);
				w = null;
			},
			triggerClass : "x-form-search-trigger"
		};

		cfg = Ext.apply(cfg, dcfg);
		// cfg.readOnly=nvl(dcfg.readOnly,true) ;

		return new Ext.form.TriggerField(cfg);

	}

	//日期时间选择
	if (obj.xtype == "datetime") {
		dcfg.editable = !(dcfg.readOnly);
		delete dcfg.readOnly;
		if (!dcfg.format)
			dcfg.format = "Y-m-d H:i:s";
		if (!dcfg.value) {
			//dcfg.value=new Date().dateFormat(dcfg.format);
		}

		return new Ext.form.DateTimeField(dcfg);
	}

	//日期
	if (obj.xtype == "date") {
		dcfg.editable = !(dcfg.readOnly);
		delete dcfg.readOnly;
		if (!dcfg.format)
			dcfg.format = "Y-m-d";
		if (!dcfg.value) {
			//dcfg.value=new Date().dateFormat(dcfg.format);
		}
		return new Ext.form.DateField(dcfg);
	}

	//时间
	if (obj.xtype == "time") {
		if (!dcfg.format)
			dcfg.format = "H:i";
		if (!dcfg.value) {
			dcfg.value = new Date().dateFormat(dcfg.format);
		}
		return new Ext.form.TimeField(dcfg);
	}

	//按钮
	if (obj.xtype == "button") {
		cfg = {
			text : obj.label,
			handler : obj.click
		};
		return new Ext.Button(Ext.apply(cfg, dcfg));
	}

	//上传按钮
	if (obj.xtype == "upload") {
		return new Ext.form.FileUploadField( {
			fieldLabel : obj.label,
			name : obj.name,
			emptyText : "选择文件上传",
			readOnly : true,
			buttonCfg : {
				text : "",
				iconCls : "upload-icon"
			}
		});
	}

	if (obj.xtype == "image") {
		obj.xtype = "box";
		dcfg.fieldLabel = obj.label;
		return new Ext.BoxComponent(Ext.apply(dcfg, obj));
	}

	return new Ext.form.TextField(Ext.apply(cfg, dcfg));
}
