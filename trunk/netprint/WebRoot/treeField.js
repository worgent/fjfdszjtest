Ext.onReady(function(){
	var url = '/jsddd.html';	
	
	function createTree(el){
		var Tree = Ext.tree;
	    var tree = new Tree.TreePanel(el, {
	        animate:true, 
	        loader: new Tree.TreeLoader({
	            dataUrl:url
	        }),
	        enableDD:true,
	        containerScroll: true
	    });
	    var root = new Tree.AsyncTreeNode({
	        text: 'Ext demo',
	        draggable:false,
	        id:'ext'
	    });
	    tree.setRootNode(root);
	    tree.render();
	    return tree;
	}
	
	function beforeselect(node,el){
		if (node.id != '-1'){
	    	return true;
	    }else{
	    	alert('请选择一个');	
	    	this.collapse();
	    	return false;
	    }
	}	
	
	Ext.QuickTips.init();
    
    /**
     * 例2
     */
    var treeField01 = new Ext.form.TreeField({
            name: 'dir1'
            ,url:url
            ,createTree : createTree
            ,hiddenName : 'myHidden001'
            ,displayValue:'这是默认显示的值'
            ,value:'这是默认值'
        });
    treeField01.on('beforeselect',beforeselect);
	treeField01.render('myDiv002');

	/**
	 * 例3
	 */
	var treeField02 = new Ext.form.TreeField({
            name: 'dir2'
            ,url:url
            ,displayField:'id'
            ,valueField:'text'
            ,createTree : createTree
            ,hiddenName : 'myHidden001'
        });
    treeField02.on('beforeselect',beforeselect);
	treeField02.applyTo('myInput001');

	
	Ext.get('but001').on('click',function(){alert(treeField01.getValue());});
	Ext.get('but002').on('click',function(){alert(treeField02.getValue());});
	
	
});