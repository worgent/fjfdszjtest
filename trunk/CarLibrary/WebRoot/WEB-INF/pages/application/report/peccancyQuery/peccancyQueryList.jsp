<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%request.setCharacterEncoding("GBK");%> 
<%@ include file="/common/taglibs.jsp"%>
<!-- 
	综合报表 -》 违章查询
 -->
<ww:action name="'select'" id="select"></ww:action>

<head>
	<style type="text/css">
	    .x-panel-body p {
	        margin:10px;
	    }
	    #container {
	        padding:10px;
	    }
    </style>
    <script type="text/javascript" src="/js/TreeField.js"></script>
    <script language="javascript">
    	function showSearch(){
    		Ext.onReady(function() {
			    var schForm = new Ext.form.FormPanel({
			        baseCls: 'x-plain',
			        labelAlign: 'right', 
			        method:'GET', 	//提交方法
			        labelWidth: 100,//文本标签长度
			        defaults: {width: 100}, 	//默认宽度
					onSubmit:Ext.emptyFn,
			        submit:function(){
			        	this.getEl().dom.action='/report/peccancyQuery.shtml';
			        	this.getEl().dom.submit();
			        },
			        items: [{
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '违章时间',
			            name: 'search.begPeccancyDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	labelSeparator:'',
			        	xtype: 'datefield',
			        	format: 'Y-m-d',
			            fieldLabel: '',
			            name: 'search.endPeccancyDate',
			            width:100  // 设置宽度，百分比的需加‘号
			        },{
			        	xtype: 'textfield',
			            fieldLabel: '违章地点',
			            name: 'search.peccancyAddress',
			            width:100  // 设置宽度，百分比的需加‘号
			        }],
			        buttons: [{
			            text: '查询',
			            handler: function(){
			            	schForm.form.submit(); 
			            }
			        }]
    			});
    			
    			var window = new Ext.Window({
			        title: '财务单据查询',
			        width: 300,
			        height:230,
			        minWidth: 300,
			        minHeight: 200,
			        layout: 'fit',
			        plain:true,
			        bodyStyle:'padding:5px;',
			        buttonAlign:'center',
			        items: schForm
			    });
			
			    window.show();
			});
    	}
    	Ext.onReady(function(){
			new Ext.Button({
		        text: '查 询',
		        handler: showSearch
		    }).render(document.all.searchPanel);
		})
    </script>
</head>

<body>
	<div id="searchPanel" style="margin:0px;width:100px;float:left;"></div>
	
	<ww:property value="table"/>
	<table width="100%">
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;
				数据导出: <a href="/report/peccancyQuery.shtml?actionType=export&search.begPeccancyDate=${param['search.begPeccancyDate']}&search.endPeccancyDate=${param['search.endPeccancyDate']}&search.peccancyAddress=${pram['search.peccancyAddress']}" target="export" title="导出Excel"><img src="/themes/default/images/ico_file_excel.png"/>Excel</a>
			</td>
		</tr>
	</table>
	<div style="color:red">
		&nbsp;&nbsp;&nbsp;&nbsp;注：单位（次数）<br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击值可查看对应项的明细。
	</div>
	<script>
		Ext.onReady(function() {
		    
		        var grid = new Ext.grid.TableGrid("peccancyQueryTab");
		        grid.render();
		});
		Ext.grid.TableGrid = function(table, config) {
		    config = config || {};
		    var cf = config.fields || [], ch = config.columns || [];
		    table = Ext.get(table);
		
		    var ct = table.insertSibling();
			
		    var fields = [], cols = [];
		    var headers = table.query("thead td");
			for (var i = 0, h; h = headers[i]; i++) {
				var text = h.innerHTML;
				var name = 'tcol-'+i;
				var visible = false;
				if (h.visible == 'true')
					visible  =true;
				else
					visible = false;
					
				var width;
				if (h.width == '')
					width = h.offsetWidth
				else
					width = h.width;
				
		        fields.push(Ext.applyIf(cf[i] || {}, {
		            name: name,
		            mapping: 'td:nth('+(i+1)+')/@innerHTML'
		        }));
		    	cols.push(Ext.applyIf(ch[i] || {}, {
					'header': text,
					'dataIndex': name,
					'width': h.offsetWidth,
					'tooltip': h.title,
		            'sortable': true,
		            'hidden': visible
		        }));
			}
			
		
		    var ds  = new Ext.data.Store({
		        reader: new Ext.data.XmlReader({
		            record:'tbody tr'
		        }, fields)
		    });
		
			ds.loadData(table.dom);
		
		    var cm = new Ext.grid.ColumnModel(cols);
		
		    if(config.width || config.height){
		        ct.setSize(config.width || 'auto', config.height || 'auto');
		    } else {
		        ct.setWidth(table.getWidth());
		    }
		
		    if(config.remove !== false){
		        table.remove();
		    }
		
		    Ext.applyIf(this, {
		        'ds': ds,
		        'cm': cm,
		        'sm': new Ext.grid.RowSelectionModel(),
		        autoHeight:true,
		        autoWidth:false,
		        width: 990 		    });
		    Ext.grid.TableGrid.superclass.constructor.call(this, ct, {});
		};
		
		Ext.extend(Ext.grid.TableGrid, Ext.grid.GridPanel);
	</script>
</body>