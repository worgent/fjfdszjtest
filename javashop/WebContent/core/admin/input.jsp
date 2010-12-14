<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
function checkUserName(val){
	if(val=='kingapex' )
		return true;
	else
		return "用户名已存在";
}
$.Validator.options={lang:{required:'此项不能为空!'}};
</script>

<form class="validate">
	必须：<input type="text" name="test1" required="true">
	<br/>
	整型：<input type="text" name="test2" required="true" dataType="int">
	<br/>
	浮点型：<input type="text" name="test3"   dataType="float">
	<br/>	
	邮件：<input type="text" name="test4" required="true" dataType="email">
	<br/>	
	日期：<input type="text" name="test5" required="true" dataType="date">
	<br/>	
	电话号码：<input type="text" name="test6" required="true" dataType="tel_num">
	<br/>	
	手机：<input type="text" name="test7" required="true" dataType="mobile">
	<br/>	
	邮政编码：<input type="text" name="test8" required="true" dataType="post_code">
	<br/>		
	网址：<input type="text" name="test9" required="true" dataType="url">
	<br/>
	自定义函数：<input type="text" name="test10" required="true" fun="checkUserName">
	<br/>
		
	<input type="submit" value="确定" />
</form>