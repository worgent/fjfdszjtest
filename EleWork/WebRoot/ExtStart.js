Ext.onReady(function(){



    // Note: For the purposes of following along with the tutorial, all 
    // new code should be placed inside this method.
    
    //alert("Congratulations!  You have Ext configured correctly!");
    var myDiv = Ext.get('myDiv');
    
    myDiv.highlight(); //黄色高亮显示然后渐退
    myDiv.addClass('red'); // 添加自定义CSS类 (在ExtStart.css定义)
    myDiv.center(); //在视图中将元素居中
    myDiv.setOpacity(0.25, false); // 使元素半透明
    Ext.select('p').highlight();
    
    Ext.get('myButton').on('click', function(){
        alert("You clicked the button");
    });
    
    var paragraphClicked = function(e){
        var paragraph = Ext.get(e.target);
        paragraph.highlight();
        Ext.MessageBox.show({
            title: 'Paragraph Clicked',
            msg: paragraph.dom.innerHTML,
            width: 400,
            buttons: Ext.MessageBox.OK,
            animEl: paragraph
        });
        
    }
    Ext.select('p').on('click', paragraphClicked);
    
    
    
    var myData = [['Apple', 29.89, 0.24, 0.81, '9/1 12:00am'], ['Ext', 83.81, 0.28, 0.34, '9/12 12:00am'], ['Google', 71.72, 0.02, 0.03, '10/1 12:00am'], ['Microsoft', 52.55, 0.01, 0.02, '7/4 12:00am'], ['Yahoo!', 29.01, 0.42, 1.47, '5/22 12:00am']];
    
    var ds = new Ext.data.Store({
        proxy: new Ext.data.MemoryProxy(myData),
        reader: new Ext.data.ArrayReader({
            id: 0
        }, [{
            name: 'company'
        }, {
            name: 'price',
            type: 'float'
        }, {
            name: 'change',
            type: 'float'
        }, {
            name: 'pctChange',
            type: 'float'
        }, {
            name: 'lastChange',
            type: 'date',
            dateFormat: 'n/j h:ia'
        }])
    });
    
    ds.load();
    
    var colModel = new Ext.grid.ColumnModel([{
        header: "Company",
        width: 120,
        sortable: true,
        dataIndex: 'company'
    }, {
        header: "Price",
        width: 90,
        sortable: true,
        dataIndex: 'price'
    }, {
        header: "Change",
        width: 90,
        sortable: true,
        dataIndex: 'change'
    }, {
        header: "% Change",
        width: 90,
        sortable: true,
        dataIndex: 'pctChange'
    }, {
        header: "Last Updated",
        width: 120,
        sortable: true,
        renderer: Ext.util.Format.dateRenderer('m/d/Y'),
        dataIndex: 'lastChange'
    }]);
    
    var grid = new Ext.grid.GridPanel({
        renderTo: 'grid-example',
        ds: ds,
        cm: colModel
    });
    // grid.render();
    grid.getSelectionModel().selectLastRow();
    
});




