<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
 <style>
input.txt,select{width:180px}
</style>

<h5 style="color: rgb(255, 164, 61); padding-left: 5%; line-height: 150%;">请填写数据库的相关信息：<br>首先请向主机空间商确认您的数据库在服务器上已建立。</h5>
<form id="db_setting" action="install!step3.${ext }" method="post">
    <table border="0" width="100%">
  <tbody>
    <tr>
      <th align="right" scope="row"><label>数据库类型:</label></th>
      <td>
     <select name="dbtype">
     <option value="mysql">mysql</option>
     </select>
      </td>
    </tr>  
  <tr>
      <th align="right" width="22%"><label for="db_host">数据库主机:</label></th>
      <td width="30%"><input type="text" value="localhost:3306" size="25" name="dbhost" id="db_host" class="txt"></td>
      <td width="48%">如果数据库服务器与WEBSERVER不在同一台主机上,请设置为数据库服务器的地址。</td>
    </tr>
    <tr>
      <th align="right" scope="row"><label for="db_uname">数据库用户名:</label></th>
      <td><input type="text" value="" size="25" name="uname" class="txt" id="db_uname"></td>
      <td style="display: none;" id="db_check_result" rowspan="2">
      <img src="images/db_succ.gif">
      </td>
    </tr>
    <tr>
      <th align="right" scope="row"><label for="db_passwd">数据库密码:</label></th>
      <td><input type="password" value="" size="25" name="pwd" class="txt" id="db_passwd"></td>
    </tr>
    
      <tr>
      <th align="right" scope="row"><label for="db_name">数据库名:</label></th>
      <td id="db_selector"><input type="text" value="" size="25" name="dbname" id="db_name" style="width: 120px;" class="txt">
      <span  id="btn_check_db"><span style="text-decoration: underline; cursor: pointer; color: rgb(0, 0, 255);">测试连接»</span></span>
      </td>
      <td></td>
    </tr>
 </tbody>
</table>
<center>
    <input type="submit" value="下一步：创建配置文件(config/jdbc.properties) »" name="submit" style="margin: 10px;">
</center>    
</form> 
<script type="text/javascript">
 //db_setting
 $(function(){
	 $("#btn_check_db").click(function(){
			var options = {
 					url :"install!testConnection.${ext}",
 					cache:false,
 					type : "POST",
 					dataType : 'json',
 					success : function(result) {				
 						 if(result.result==1){
 	 						 $("#db_check_result").show();
 	 					 }else{
 	 						 $("#db_check_result").hide();
 	 	 				 }
 					},
 					error : function(e) {
 						 alert("出错啦:(");
 					}
 				};
 
 			$("#db_setting").ajaxSubmit(options);		 
	 });
 });
</script>
<jsp:include page="footer.jsp"></jsp:include>