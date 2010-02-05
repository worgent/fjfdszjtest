<%@ page language="java" errorPage="/error.jsp" pageEncoding="GBK"
	contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp"%>



<form name="form1" action='/system/firstPage/FirstPage.shtml'
	class="formcheck" onsubmit="return checkSubmit();">
	<script>
	var fm2 = new Ext.form.BasicForm('form1'); 
</script>

	<table class='simple' style='width: 98%' align='center'
		id="maintaindetailinfo">
		<thead>
			<tr>
				<th colspan='10'>
					公告信息
					<br>
				</th>
			</tr>
		</thead>
		<tbody id="maintaintbody">
			<tr>

				<td align='center'>
					公告内容
					<br>
				</td>


			</tr>

			<ww:if test="gobackFirstList!= null && gobackFirstList.size()>0">
				<input type="hidden" name="roldInfo.num"
					value="<ww:property value="gobackFirstList.size()"/>">
				<ww:iterator value="gobackFirstList" status="roldInfo">
					<tr>
						<td valign="top" align='center'>
							<textarea cols="100%" rows="15" name="roldInfo.noticeContent">
				       <ww:property value="noticeContent" />
				    </textarea>
						</td>
					</tr>
				</ww:iterator>
			</ww:if>
			<ww:else>
				<input type="hidden" name="roldInfo.num" value="1">
				<%--中途站点记录数--%>
				<tr>
					<td valign="top" align='center'>
						<textarea cols="60" rows="15" name="roldInfo.noticeContent"></textarea>
					</td>
				</tr>
			</ww:else>
		</tbody>
		<TFOOT id="maintaindetailTfoot">
		</TFOOT>
	</table>



	<input type='hidden' name='actionType' value='firstPageSave' />
</form>
<table style='width: 80%' align='center'>
	<tr>
		<td id="buttonTD1" align='right' width='48%'></td>
		<td>
			&nbsp;
		</td>
		<td id="buttonTD2" align='left' width='48%'></td>
	</tr>
</table>
<script type="text/javascript" src="/themes/default/js/calendar.js"></script>
<script language="javascript">
	Ext.get('form1').un("submit", fm2.onSubmit, fm2);
	var saveButton =new Ext.Button({
        text: '保 存',
        handler: function(){
        	if (fm2.isValid() && document.form1.checkSubmit()) {
	             //1.先判断本页面是否已经存在EditEmployId的值
								document.form1.submit();
							}
	    }
    }).render(document.all.buttonTD1);
			
    new Ext.Button({
        text: '重 置',
        handler: function(){
        	document.form1.reset();
        }
    }).render(document.all.buttonTD2);
    

	
	function uploadWD(str){
                  var feather="dialogWidth=466px;scrollbars=yes;dialogHeight=200px";
				  dWin=showModelessDialog('<%=request.getContextPath() %>/addFile.jsp',window,feather);			  
}	
</script>