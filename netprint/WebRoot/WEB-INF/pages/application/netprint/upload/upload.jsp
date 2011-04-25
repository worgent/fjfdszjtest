<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<body>
<br>
<br>
<form name="form1" method="post" action='/netprint/emsmail/emsmailupload.shtml' class="formcheck" onsubmit="return checkSubmit();" enctype="multipart/form-data">
<table><tr><td>EMS邮件批量导入</td>
<td>
<input type="file"  name="upload"  id="upload" onpropertychange="if(!/(xls)/.test(this.value.substr(this.value.lastIndexOf('.')+1))){Ext.MessageBox.alert('提示','类型不支持');}"/>
</td> 
<td><input type="button"  name="buttonName"  id="buttonId" value="上传" onclick="selectupload()" /></td>
<td>
<div id="waitlog" style="display:none" >
处理中,请等待......<img src="/images/extanim32.gif">
</div>
</td>
</tr>
</table>
<input type='hidden' name='actionType' value='<ww:property value="action"/>' />
</form>

<font color="red">
注意:
<br>
1.是否保价:必须输入"是","否"两种选择.
<br>
2.邮件类型:必须输入"信函","文件","物品"三种选择.
<br>
3.模板下载
<a href="http://print.yzyogo.com/model.xls">下载</a>
<br>
说明:测试数据中有三条记录,第一条为信息最全的写法,其他两条为可省略不填的信息.
</font>
<script>
function selectupload(){
    //Ext.getCmp("DivLogin").getEl().dom.style.display='none'
     Ext.get('waitlog').dom.style.display='block';
   	document.form1.submit();
}
</script>
</body>
</html>