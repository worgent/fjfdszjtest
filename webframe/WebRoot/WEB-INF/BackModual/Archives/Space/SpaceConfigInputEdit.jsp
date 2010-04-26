<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/util.tld" prefix="qzgf"%>
<%@taglib uri="/WEB-INF/struts-tags.tld" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  //定义全局变量
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<script type="text/javascript">
		$(document).ready(function(){ 
			$("#myTable").tablesorter({ 
				headers: { 
					1: {sorter: false }
				}   
			}); 
		}); 
		</script>
		
<script type="text/javascript">
$(document).ready(function(){
	$.formValidator.initConfig({formid:"form1",onerror:function(msg){alert(msg)},onsuccess:function(){alert('ddd');return true;}});
	$("#spaceName").formValidator({onshow:"请输入空间名称",onfocus:"范围6到32个汉字",oncorrect:"该用户名可以注册"}).inputValidator({min:6,max:32,onerror:"你输入空间名称非法,请确认"}).regexValidator({regexp:"username",datatype:"enum",onerror:"用户名格式不正确"});
	
	$("#showArlticleNum").formValidator({forcevalid:true,triggerevent:"change",onshow:"请输入文章数",onfocus:"只能输入数值哦",oncorrect:"恭喜你,你输对了"}).inputValidator({min:1,max:99,type:"value",onerrormin:"你输入的值必须大于等于1",onerror:"数值必须在1-99之间，请确认"});
	});
	</script>
	
</head>
<body>
<center>
<form  method='POST' name='form1' action='/space.do?action=editSave'  id="form1">
 <s:iterator id="space"  value="%{pageOnlyList.objectList}">
   <table>
   <tr><td colspan=3>空间设置说明：<br/>
1、 为您的空间命名：为自己的家起一个名字吧；<br/>
2、 写空间公告：公告将会显示在空间首页，可用来通告所有的访问者；<br/>
3、 设置显示文章数：设定空间首页显示多少篇文章。
   </td></tr>
    <tr><td colspan=3>&nbsp;
   </td></tr>
   <tr>
      <td>空间名称：</td>
      <td><input type="text" id="spaceName"  name="search.pspaceName"   value="<s:property value="#space.SPACENAME" />"  style="width:220px"  /></td>
      <td><div id="spaceNameTip" style="width:250px"></div></td>
   </tr>
   <tr>
   <td>公告信息：</td>
   <td><textarea id="spaceBulletinId" name="search.pspaceBulletin" rows="6" cols="35"><s:property value="#space.SPACEBULLETIN" /></textarea></td>
   <td><span style="padding-left: 5px; color: rgb(153, 153, 153);">如：&ldquo;欢迎来到我的空间&rdquo;等欢迎信息或空间<br/>更新、内容导读、个人声明等信息，最多不能超<br/>过<font color="blue">256</font>个汉字。</span></td>
   </tr>
   <tr>
   <td>显示文章数:</td>
   <td colspan=2>
   <input type="text" id="showArlticleNum"  name="search.pshowArlticleNum"  value="<s:property value="#space.SHOWARLTICLENUM"/>" style="width:80px" value="10"  />
   
    <span style="padding-left: 5px; color: rgb(153, 153, 153);">(缺省为10，只能输入数字，用于设置首页文章显示数量)</span>
   
</td>
   </tr>
   
   <tr>
   <td>空间风格:</td>
   <td>
   <select name="search.pspaceStyle"     id="spaceStyle">
          <option value="1" <s:if test="#space.SPACESTYLE==1">selected</s:if> >红色</option>
          <option value="2" <s:if test="#space.SPACESTYLE==2">selected</s:if>>紫色</option>
          <option value="3" <s:if test="#space.SPACESTYLE==3">selected</s:if>>蓝色</option>
          <option value="4" <s:if test="#space.SPACESTYLE==4">selected</s:if>>淡黄色</option>
          <option value="5" <s:if test="#space.SPACESTYLE==5">selected</s:if>>青绿色</option>
   </select>
   </td>
   <td>&nbsp;</td>
   </tr>
   
   <tr>
    <td colspan="2">
   <table><tr>
   
   
   <td>匿名回复:</td>
   <td>
    <s:if test="#space.ANONREVERSION==1">
     <input type="checkbox" name="search.panonReversion" id="anonReversionId"   value="1"  <s:if test="#space.ANONREVERSION==1">checked</s:if>  />
    </s:if>
   <s:else>
    <input type="checkbox" name="search.panonReversion"  id="anonReversionId"   value="1"   />
   </s:else>
   </td>
   
    <td>显示访客:</td>
   <td>
   
      <s:if test="#space.SHOWCALLER==1">
        <input type="checkbox" name="search.pshowCaller" id="showCallerId"  value="1" <s:if test="#space.SHOWCALLER==1">checked</s:if>  />
      </s:if>
      <s:else>
      <input type="checkbox" name="search.pshowCaller" id="showCallerId"  value="1"   />
      </s:else>
     
   </td>
   
     <td>显示好友:</td>
   <td>
 
     <s:if test="#space.SHOWFRIEND==1">
      <input type="checkbox" name="search.pshowFriend"  id="showFriendId"   <s:if test="#space.SHOWFRIEND==1">checked</s:if>  value="1"  />
     </s:if>
    <s:else>
    <input type="checkbox" name="search.pshowFriend"  id="showFriendId"    value="1"  />
    </s:else>
    
   </td>
   
    <td>显示公告:</td>
   <td>
   <s:if test="#space.SHOWBULLETIN==1">
   <input type="checkbox" name="search.pshowBulletin" id="showBulletinId" value="1" <s:if test="#space.SHOWBULLETIN==1">checked</s:if>   />
   </s:if>
  <s:else>
      <input type="checkbox" name="search.pshowBulletin" id="showBulletinId" value="1" />
   </s:else>
   
   </td>
  
   </tr></table>
   </td>
   <td>&nbsp;</td>
   </tr>
 
   <tr>
   <td>&nbsp;</td>
   <td  align="center"><input type="submit" id="saveId"  name="save" value="保存"  />
      &nbsp;<input type="reset" id="saveId"  name="save" value="重置"  /></td>
   <td>&nbsp;</td>
   </tr>
 </table>
  <input type="hidden" id="search.SPACEID" name="search.SPACEID"  value="<s:property value="#space.SPACEID" />"/>
 
 </s:iterator>


</form>

</center>
</body>
</html>