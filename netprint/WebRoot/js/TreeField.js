/**  
 * Ext JS Library 2.0 extend  
 * Version : 1.1  
 * Author : 飞天色鼠  
 * Date : 2008-1-29  
 * E-mail : gx80@qq.com  
 * HomePage : http://www.gx80.cn  
 * 
 * 
 * 示例代码：
<div id="myDiv002"></div>
	<input type="hidden" name="myHidden001" value=""/>
    <input type="button" value="aaaaa" onclick="alert(document.all.myHidden001.value)">
<script>
	Ext.onReady(function(){
		
		var url = '/system/loadMenu.shtml?type=json';	
		
		Ext.QuickTips.init();
		var treeField01 = new Ext.form.TreeField({
				minListHeight:200,
	            hiddenName : 'myHidden001',
	            valueField : 'id',
	            baseParams : {menuId : 'Root'},
	            treeRootConfig : {
	            	id:'Root',   
			        text : '请选择',   
			        draggable:false  
	            },
	            displayValue:'',
	            value:''
		});
		treeField01.render('myDiv002');	//输出到指定的对象中
		var loader = new Ext.tree.TreeLoader({	//重写数据加载类
			dataUrl:url,
			baseParams: {menuId:'Root'},
			clearOnLoad : false ,
			preloadChildren : false,
			requestMethod : "GET" 
		});
		loader.on("beforeload", function(treeLoader,node){	//添加事件，当重载时指定新的结点
			this.baseParams.menuId = node.attributes.id;
		}, loader);
		treeField01.tree.loader = loader;
	});
</script>
 * 
 */  
Ext.form.TreeField = Ext.extend(Ext.form.TriggerField,  {   
    /**  
     * @cfg {Boolean} readOnly  
     * 设置为只读状态  
     *   
     */  
    readOnly : true ,   
    /**  
     * @cfg {String} displayField  
     * 用于显示数据的字段名  
     *   
     */  
    displayField : 'text',   
    /**  
     * @cfg {String} valueField  
     * 用于保存真实数据的字段名  
     */  
    valueField : null,   
    /**  
     * @cfg {String} hiddenName  
     * 保存真实数据的隐藏域名  
     */  
    hiddenName : null,   
    /**  
     * @cfg {Integer} listWidth  
     * 下拉框的宽度  
     */  
    listWidth : null,   
    /**  
     * @cfg {Integer} minListWidth  
     * 下拉框最小宽度  
     */  
    minListWidth : 50,   
    /**  
     * @cfg {Integer} listHeight  
     * 下拉框高度  
     */  
    listHeight : null,   
    /**  
     * @cfg {Integer} minListHeight  
     * 下拉框最小高度  
     */  
    minListHeight : 50,   
    /**  
     * @cfg {String} dataUrl  
     * 数据地址  
     */  
    dataUrl : null,   
    /**  
     * @cfg {Ext.tree.TreePanel} tree  
     * 下拉框中的树  
     */  
    tree : null,   
    /**  
     * @cfg {String} value  
     * 默认值  
     */  
    value : null,   
    /**  
     * @cfg {String} displayValue  
     * 用于显示的默认值  
     */  
    displayValue : null,   
    /**  
     * @cfg {Object} baseParams  
     * 向后台传递的参数集合  
     */  
    baseParams : {},   
    /**  
     * @cfg {Object} treeRootConfig  
     * 树根节点的配置参数  
     */  
    treeRootConfig : {   
        id : ' ',   
        text : 'please select...',   
        draggable:false  
        },   
    /**  
     * @cfg {String/Object} autoCreate  
     * A DomHelper element spec, or true for a default element spec (defaults to  
     * {tag: "input", type: "text", size: "24", autocomplete: "off"})  
     */  
    defaultAutoCreate : {tag: "input", type: "text", size: "24", autocomplete: "off"},   
  
    initComponent : function(){   
        Ext.form.TreeField.superclass.initComponent.call(this);   
        this.addEvents(   
                'select',   
                'expand',   
                'collapse',   
                'beforeselect'        
        );   
           
    },   
    initList : function(){   
        if(!this.list){   
            var cls = 'x-treefield-list';   
  
            this.list = new Ext.Layer({   
                shadow: this.shadow, cls: [cls, this.listClass].join(' '), constrain:false  
            });   
  
            var lw = this.listWidth || Math.max(this.wrap.getWidth(), this.minListWidth);   
            this.list.setWidth(lw);   
            this.list.swallowEvent('mousewheel');   
               
            this.innerList = this.list.createChild({cls:cls+'-inner'});   
            this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));   
            this.innerList.setHeight(this.listHeight || this.minListHeight);   
            if(!this.tree){   
                this.tree = this.createTree(this.innerList);       
            }   
            this.tree.on('click',this.select,this);   
            this.tree.render();   
        }   
    },   
    onRender : function(ct, position){   
        Ext.form.TreeField.superclass.onRender.call(this, ct, position);   
        if(this.hiddenName){
        	this.hiddenField = document.all(this.hiddenName);
        	if (this.hiddenField == undefined){
	            this.hiddenField = this.el.insertSibling({tag:'input',    
	                                                     type:'hidden',    
	                                                     name: this.hiddenName,    
	                                                     id: (this.hiddenId||this.hiddenName)},   
	                    								 'before', true);   
			}
            this.hiddenField.value =   
                this.hiddenValue !== undefined ? this.hiddenValue :   
                this.value !== undefined ? this.value : '';   
            this.el.dom.removeAttribute('name');   
        }   
        if(Ext.isGecko){   
            this.el.dom.setAttribute('autocomplete', 'off');   
        }   
  
        this.initList();   
    },   
    select : function(node){   
        if(this.fireEvent('beforeselect', node, this)!= false){   
            this.onSelect(node);   
            this.fireEvent('select', this, node);   
        }   
    },   
    onSelect:function(node){   
        this.setValue(node);   
        this.collapse();   
    },   
    //创建树
    createTree:function(el){   
        var Tree = Ext.tree;   
       
        var tree = new Tree.TreePanel({   
            el:el,   
            autoScroll:true,   
            animate:true,   
            containerScroll: true,    
            loader: new Tree.TreeLoader({   
                dataUrl : this.dataUrl,   
                baseParams : this.baseParams   
            })   
        });   
       
        var root = new Tree.AsyncTreeNode(this.treeRootConfig);   
        tree.setRootNode(root);   
        return tree;   
    },   
  
    getValue : function(){   
        if(this.valueField){   
            return typeof this.value != 'undefined' ? this.value : '';   
        }else{   
            return Ext.form.TreeField.superclass.getValue.call(this);   
        }   
    },   
    setValue : function(node){   
        //if(!node)return;   
        var text,value;   
        if(node && typeof node == 'object'){   
            text = node[this.displayField];   
            value = node[this.valueField || this.displayField];
        }else{   
            text = this.displayValue;   
            value = node;   
        }   
        if(this.hiddenField){   
            this.hiddenField.value = value;   
        }   
        Ext.form.TreeField.superclass.setValue.call(this, text);   
        this.value = value;  
    },   
    onResize: function(w, h){   
        Ext.form.TreeField.superclass.onResize.apply(this, arguments);   
        if(this.list && this.listWidth == null){   
            var lw = Math.max(w, this.minListWidth);   
            this.list.setWidth(lw);   
            this.innerList.setWidth(lw - this.list.getFrameWidth('lr'));   
        }   
    },   
    validateBlur : function(){   
        return !this.list || !this.list.isVisible();      
    },   
    onDestroy : function(){   
        if(this.list){   
            this.list.destroy();   
        }   
        if(this.wrap){   
            this.wrap.remove();   
        }   
        Ext.form.TreeField.superclass.onDestroy.call(this);   
    },   
    collapseIf : function(e){   
        if(!e.within(this.wrap) && !e.within(this.list)){   
            this.collapse();   
        }   
    },   
  
    collapse : function(){   
        if(!this.isExpanded()){   
            return;   
        }   
        this.list.hide();   
        Ext.getDoc().un('mousewheel', this.collapseIf, this);   
        Ext.getDoc().un('mousedown', this.collapseIf, this);   
        this.fireEvent('collapse', this);   
    },   
    expand : function(){   
        if(this.isExpanded() || !this.hasFocus){   
            return;   
        }   
        this.onExpand();   
        this.list.alignTo(this.wrap, this.listAlign);   
        this.list.show();   
        Ext.getDoc().on('mousewheel', this.collapseIf, this);   
        Ext.getDoc().on('mousedown', this.collapseIf, this);   
        this.fireEvent('expand', this);   
    },   
    onExpand : function(){   
        var doc = Ext.getDoc();   
        this.on('click',function(){alert(111)},this);   
    },   
    isExpanded : function(){   
        return this.list && this.list.isVisible();   
    },   
    onTriggerClick : function(){   
        if(this.disabled){   
            return;   
        }   
        if(this.isExpanded()){   
            this.collapse();   
        }else {   
            this.onFocus({});   
            this.expand();   
        }   
        this.el.focus();   
    }   
});   
Ext.reg('treeField', Ext.form.TreeField);  