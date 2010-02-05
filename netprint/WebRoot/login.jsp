<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta name="author" content="@Trust Group Develop"/>
	<meta name="Copyright" content="依赖团开发">
	<meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta http-equiv=Content-Language content=zh-cn>
	<link href="/images/login/css.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="/ext/resources/css/ext-all.css" />
	<script type="text/javascript" src="/ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="/ext/ext-all.js"></script>
	<script type="text/javascript" src="/js/examples.js"></script>
	<script type="text/javascript" src="/js/prototype.js"></script>
	<script type="text/javascript">
//清除密码
function clearPwd(){
	 document.getElementById("password").value="";
}
//注册页面
function regUser(){
	var regUserWindow=new RegUserWindow();
	//Ext.get("desktop").mask();
	regUserWindow.show();	
}
	    
RegUserWindow=Ext.extend(Ext.Window,{
 	title : '用户注册信息',		
	width : 280,		
	iconCls:"icon-password",
	height : 220,	
	defaults : {			
		border : false
	},
	buttonAlign : 'center',	
	createFormPanel :function() {
		return new Ext.form.FormPanel({
			bodyStyle : 'padding-top:6px',
			defaultType : 'textfield',
			labelAlign : 'right',
			labelWidth : 70,
			labelPad : 0,
			frame : true,
			defaults : {
			    /*设置控件默认的格式*/
				allowBlank : false,
				width : 158,
				selectOnFocus:true
			},
			items : [{
					cls : 'key',
					name : 'search.staffNo',
					fieldLabel : '用户名',
					blankText : '用户名不能为空'
				}, {
					cls : 'key',
					name : 'search.password',
					fieldLabel : '密码',
					blankText : '密码不能为空',
					inputType : 'password'
				}, {
					cls : 'key',
					name : 'search.password1',
					fieldLabel : '确认密码',
					blankText : '密码不能为空',
					inputType : 'password'
				}, {
					cls : 'key',
					name : 'search.staffName',
					fieldLabel : '昵称',
					allowBlank: true
				}, {
					cls : 'key',
					name : 'search.phone',
					fieldLabel : '联系电话',
					allowBlank: true
				}]
		});
	},					
	save:function() {
			//验证密码
			var p1=this.fp.form.findField("search.password").getValue();
			var p2=this.fp.form.findField("search.password1").getValue();

			if(p1!=p2){
				this.fp.form.findField("search.password").markInvalid("两次输入的新密码不同!");
				this.fp.form.findField("search.password").focus(true);
				return;
			}
			//传递页面参数给后台
			var url = '/system/manage/register.shtml?userInfo.staffNo='+this.fp.form.findField("search.staffNo").getValue()+'&userInfo.password='+p1+'&userInfo.staffName='+this.fp.form.findField("search.staffName").getValue()+'&userInfo.phone='+this.fp.form.findField("search.phone").getValue();
			try{
				var oXMLDom	= new ActiveXObject("Msxml.DOMDocument");
				oXMLDom.async = false ;
				oXMLDom.load(url);
				var root;
				if (oXMLDom.parseError.errorCode != 0) {
					var myErr = oXMLDom.parseError;
					//Ext.Msg.alert("提示",myErr);
					return;
				} else {
					root = oXMLDom.documentElement;
				}
				if (null != root){
					var rowSet = root.selectNodes("//Result");
					//成功注册
					if (1 == rowSet.item(0).selectSingleNode("errCode").text){
						Ext.Msg.alert("提示","成功注册，请重新登录系统!",function(){
							this.close();
						},this);
					}//用户名已经提示
					else if(0 == rowSet.item(0).selectSingleNode("errCode").text){
						Ext.Msg.alert("提示", rowSet.item(0).selectSingleNode("errMsg").text,function(){
							this.fp.form.findField("search.staffNo").focus(true);
						},this);
					}//注册异常
					else {
						Ext.Msg.alert("提示", rowSet.item(0).selectSingleNode("errMsg").text,function(){
							this.fp.form.findField("search.staffNo").focus(true);
						},this);
					}
				}
			}catch(e){ 
				Ext.Msg.alert("提示",e);
			}

	},
		
	//按键的处理(调用事件)
	initComponent : function(){
		this.keys={
			key: Ext.EventObject.ENTER,
		    fn: this.save,
		    scope: this};
        RegUserWindow.superclass.initComponent.call(this);       
        this.fp=this.createFormPanel();
        this.add(this.fp);
        this.addButton('保存',function(){
            if (this.fp.form.isValid()){
        		this.save();
        	}
        	},this);
        this.addButton('取消', function(){this.close();},this);
        /*
        this.on("close",function(){
        	//Ext.get("desktop").unmask();
        	}
        )*/
	 } 	
 }); 	    
	    
	    
</script>
</head>
	<body>
		<form name="admininfo" method="post" action="/system/login.shtml" class="formcheck"  onsubmit="return checkSubmit();">
		<center>
		<table cellspacing=0 cellpadding=0 width=1004 border=0>
		  <tbody>
		  <tr>
		    <td colspan=6><img height=92 alt="" src="/images/login/crm_1.gif" 
		    width=345></td>
		    <td colspan=4><img height=92 alt="" src="/images/login/crm_2.gif" 
		    width=452></td>
		    <td><img height=92 alt="" src="/images/login/crm_3.gif" width=207></td></tr>
		  <tr>
		    <td colspan=6><img height=98 alt="" src="/images/login/crm_4.gif" 
		    width=345></td>
		    <td colspan=4><img height=98 alt="" src="/images/login/crm_5.gif" 
		    width=452></td>
		    <td><img height=98 alt="" src="/images/login/crm_6.gif" width=207></td></tr>
		  <tr>
		    <td rowspan=5><img height=370 alt="" src="/images/login/crm_7.gif" 
		    width=59></td>
		    <td colspan=5><img height=80 alt="" src="/images/login/crm_8.gif" 
		    width=286></td>
		    <td colspan=4><img height=80 alt="" src="/images/login/crm_9.gif" 
		    width=452></td>
		    <td><img height=80 alt="" src="/images/login/crm_10.gif" width=207></td></tr>
		  <tr>
		    <td><img height=110 alt="" src="/images/login/crm_11.gif" width=127></td>
		    <td background=/images/login/crm_12.gif colspan=6>
		      <table id=table1 cellspacing=0 cellpadding=0 width="98%" border=0>
		        <tbody>
		        <tr>
		          <td>
		            <table id=table2 cellspacing=1 cellpadding=0 width="100%" 
		              border=0><tbody>
		              <tr>
		                <td align="left" width=61><font color=#ffffff>用户名：</font></td>
		                <td><input class="wenbenkuang check" verify="string" required="true" shade="true" requiredColor="#ffffff" name="staffNo" type="text" id="staffNo" size="20"  value="admini"><a href="javascript:regUser()"><font color=#ffffff>注册</font></a>&nbsp;&nbsp;<a href="\setup.EXE"><font color=#ffffff>打印下载</font></a></td></tr>
		              <tr>
		                <td align="left" width=61><font color=#ffffff>密 码：</font></td>
		                <td><input class="wenbenkuang check" verify="string" required="true" shade="true" requiredColor="#ffffff" name="password" type="password" id="password" size="20" value="123456"></td></tr>
		              </tbody>
		             </table>
		          </td>
		       </tr>
		       </tbody>
		     </table>
		    </td>
		    <td colspan=2 rowspan=2><img height=158 alt="" 
		      src="/images/login/crm_13.gif" width=295></td>
		    <td rowspan=2><img height=158 alt="" src="/images/login/crm_14.gif" 
		      width=207></td>
		  </tr>
		  <tr>
		    <td rowspan=3><img height=180 alt="" src="/images/login/crm_15.gif" 
		      width=127></td>
		    <td rowspan=3><img height=180 alt="" src="/images/login/crm_16.gif" 
		    width=24></td>
		    <td><input name="imageField" value="管理登录"   type=image height=48 alt="" width=86 
		      src="/images/login/crm_17.gif" ></td>
		    <td><img height=48 alt="" src="/images/login/crm_18.gif" width=21></td>
		    <td colspan=2><a href="javascript:window.close()"><img
		      height=48 alt="" src="/images/login/crm_19.gif" width=84 border=0></a></td>
		    <td><img height=48 alt="" src="/images/login/crm_20.gif" width=101></td></tr>
		  <tr>
		    <td colspan=5 rowspan=2><img height=132 alt="" src="/images/login/crm_21.gif" width=292></td>
		    <td rowspan=2><img height=132 alt="" src="/images/login/crm_22.gif" 
		      width=170></td>
		    <td colspan=2><img height=75 alt="" src="/images/login/crm_23.gif" 
		    width=332></td></tr>
		  <tr>
		    <td colspan=2><img height=57 alt="" src="/images/login/crm_24.gif" 
		    width=332></td></tr>
		  <tr>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=59></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=127></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=24></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=86></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=21></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=28></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=56></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=101></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=170></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=125></td>
		    <td><img height=1 alt="" src="/images/login/spacer.gif" width=207>
		    <div align="center"></div>
		    </td>
		</tr></tbody></table>
		</center>
		
		</form>
		<c:if test="${param.error != null}">
			<script language="javascript">
				 Ext.MessageBox.alert('提示', '工号或密码不正确，请重新输入!', clear_input);
				 function clear_input(){
				 	$('staffNo').value = '';
				 	$('password').value= '';
				 }
			</script>
		</c:if>
	</body>
</html>
