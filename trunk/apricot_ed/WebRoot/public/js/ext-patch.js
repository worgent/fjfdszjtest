Ext.override(Ext.DataView, {
	/**
	 * Reload the view by reloading the data from the store;
	 */
	reload : function() {
		if (this.store) {
			this.store.removeAll();
			this.store.reload();
		}
	}
});

Ext.lib.Ajax.createXhrObject = function(transactionId) {
	var obj, http;
	var activeX = [ 'MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP' ];
	try {

		http = new XMLHttpRequest();

		obj = {
			conn : http,
			tId : transactionId
		};
	} catch (e) {
		for ( var i = 0; i < activeX.length; ++i) {
			try {
				http = new ActiveXObject(activeX[i]);

				obj = {
					conn : http,
					tId : transactionId
				};
				break;
			} catch (e) {
			}

		}
	} finally {
		return obj;
	}
};
Ext.lib.Ajax.getConnectionObject = function() {
	var o;
	var tId = this.transactionId;

	try {
		o = this.createXhrObject(tId);
		if (o) {
			this.transactionId++;
		}
	} catch (e) {

	} finally {
		return o;
	}
};

Ext.form.ComboBoxEx = Ext.extend(Ext.form.ComboBox, {
	displayField : "label",
	valueField : "value",
	mode : "local",
	lazyInit : false,
	forceSelection : true,
	triggerAction : "all",
	store : new Ext.data.ArrayStore( {
		fields : [ "value", "label" ],
		url : toURL("/system/StaticData.do")
	}),
	initComponent : function() {
		this.hiddenName = this.name;
		if (this.code) {

			this.store.load( {
				params : {
					StaticDataCode : this.code
				}
			});

		}
		Ext.form.ComboBoxEx.superclass.initComponent.call(this);

	}

});

Ext.reg('combo', Ext.form.ComboBoxEx);
Ext.BLANK_IMAGE_URL = "public/images/s.gif";

Ext.override(Ext.form.DateField, {
	/**
	 * Reload the view by reloading the data from the store;
	 */
	getValue : function() {
		var d = this.parseDate(Ext.form.DateField.superclass.getValue
				.call(this))
		if (!d)
			return "";
		return d.dateFormat(this.format);
	},
	onTriggerClick : function() {
		if (this.disabled) {
			return;
		}
		if (this.menu == null) {
			this.menu = new Ext.menu.DateMenu( {
				hideOnClick : false,
				focusOnSelect : false
			});
		}
		this.onFocus();
		Ext.apply(this.menu.picker, {
			minDate : this.minValue,
			maxDate : this.maxValue,
			disabledDatesRE : this.disabledDatesRE,
			disabledDatesText : this.disabledDatesText,
			disabledDays : this.disabledDays,
			disabledDaysText : this.disabledDaysText,
			format : this.format,
			showToday : this.showToday,
			minText : String.format(this.minText, this
					.formatDate(this.minValue)),
			maxText : String.format(this.maxText, this
					.formatDate(this.maxValue))
		});

		this.menu.picker
				.setValue(this.parseDate(this.getValue()) || new Date());
		// alert("弹出开始");
		this.menu.show(this.el, "tl-bl?");
		this.menuEvents('on');
		// alert("弹出结束");
	}

});

/**
 * 初始化下拉框如果在setValue没有设定时
 */

Ext.override(Ext.form.ComboBox, {
	oldSetValue:Ext.form.ComboBox.prototype.setValue,
	setValue : function(v) {
        
		try {
			//alert(this.mode+"="+this.store.getCount());
			if (this.store && this.store.getCount() == 0) {
				
				this.store.on("load", function() {
					this.oldSetValue( v);
				}, this, {
					single : true
				});
				
				//this.store.load();
				this.doQuery(this.allQuery, true);
			} else
				this.oldSetValue( v);
		} catch (ex) {

		}

	}
});