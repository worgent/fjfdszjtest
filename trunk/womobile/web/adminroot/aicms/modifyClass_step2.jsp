<%@ page contentType="text/html; charset=utf-8" language="java" import="HibernateBeans.cms.cmsClass,util.HibernateUtil,java.util.List,HibernateBeans.cms.attribute" errorPage="" %>
<%
String idStr=request.getParameter("id");
int id=Integer.parseInt(idStr);

String pidStr=request.getParameter("pid");
if(pidStr==null||pidStr.equals(""))pidStr="0";
int pid=Integer.parseInt(pidStr);

if(id==pid){out.println("不能把类别本身做为父类别！,请重新选择父类别");out.println("<a href=\"javascript:window.history.back()\" target=\"_self\">返回</a>");out.close();return;}

String pname="根目录";
if(pid!=0){
org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscskk=(cmsClass)s.load(cmsClass.class,new Integer(pid));
pname=cmscskk.getName();
s.close();
}

org.hibernate.Session s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
cmsClass cmscs=(cmsClass)s.load(cmsClass.class,new Integer(id));
int selectedId=cmscs.getPid();
String name=cmscs.getName();
s.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="StyleSheet" href="dtree/dtree.css" type="text/css" />
<script type="text/javascript" src="dtree/dtree.js"></script>
<script type="text/javascript" src="ajax.js"></script>
<title>修改类别</title>
<link href="../styles/table.css" rel="stylesheet" type="text/css" />
<style>
#dtree{
position:absolute;
left:100px;
top:100px;
background-color:#FFFF99;
width:600px;
height:400px;
filter:alpha(opacity=95);
-moz-opacity:0.95;
opacity:0.95;
text-align:left;
vertical-align:top;
margin-left:0px;
margin-top:0px;
overflow:auto;
float:left;
clear:left;
border:#FF9900 solid 1px;
display:none;

scrollbar-face-color: #ffc0cb;
scrollbar-shadow-color: #ffb6c1;
scrollbar-highlight-color: #98fb98;
scrollbar-3dlight-color: #ffb6c1;
scrollbar-darkshadow-color: #90ee90;
scrollbar-track-color: #ffdead;
scrollbar-arrow-color: #f5deb3;
}
</style>
<script language="javascript">
String.prototype.Trim = function() 
{
return this.replace(/(^\s*)|(\s*$)/g, ""); 
}

var needvObj;
/**
*弹开目录树准备选择
*/
function getV(obj)
{
this.needvObj=obj;
document.getElementById("dtree").style.display="block";
}
/**
*关闭目录树返回所选值
*/
function setV()
{
var ggv=getRadioValue("fckid");
var ggvid=ggv.split("|")[0];
var ggvname=ggv.split("|")[1];
this.needvObj.value=ggvid;
var lnodenum=(this.needvObj).parentNode.childNodes.length-1;
this.needvObj.parentNode.childNodes[lnodenum].innerHTML="【"+ggvname+"】下的分类";

document.getElementById("dtree").style.display="none";
}
/**
*获取radio表单的值
*/
function getRadioValue(objid)
{
var value="";
var obj=document.getElementsByName(objid);
for(i=0;i<obj.length;i++)
{
if(obj[i].checked){value=obj[i].value;break;}
}
return value;
}
/////////////////////////////////////////////////////////属性操作//////////////////////////////////////////////////////////////////////
this.attr=0;
function rows(iobj)
{
this.attr++;
if(iobj.value=="增加属性"){
tab=iobj.parentNode.parentNode.parentNode;//document.getElementById("xcjtb");
tr=tab.insertRow(tab.rows.length);
tr.className="gird";
td1=tr.insertCell(0);
td1.innerHTML="<input type=\"text\" id=\"attrname\" name=\"attrname\" value=\"属性"+(this.attr)+"\"/>";

td2=tr.insertCell(1);
td2.innerHTML="<select name=\"attrtype\" id=\"attrtype\"><option value=\"string\">文本</option><option value=\"password\">密码</option><option value=\"int\">整数</option><option value=\"float\">精确小数</option><option value=\"radio\">单选</option><option value=\"checkbox\">多选</option><option value=\"list\">下拉列表</option><option value=\"text\">内容/长文本</option><option value=\"datetime\">日期/时间</option><option value=\"image\">图像</option><option value=\"file\">文件</option></select>";

td3=tr.insertCell(2);
td3.innerHTML="<input type=\"text\" size=\"3\" id=\"attrvs\" name=\"attrvs\" onclick=\"javascript:getV(this)\" value=\"1\"/><span style=\"color:red\" id=\"lv"+this.attr+"\" name=\"lv"+this.attr+"\"></span>";

td4=tr.insertCell(3);
td4.innerHTML="<input type=\"text\" id=\"attrsv\" name=\"attrsv\" value=\"无\"/>";

td5=tr.insertCell(4);
td5.innerHTML="<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td><select name=\"listView\" id=\"listView\"><option value=\"true\" selected=\"selected\">&radic;显示</option><option value=\"false\">&times;隐藏</option></select></td>    <td><select name=\"addView\" id=\"addView\"><option value=\"true\" selected=\"selected\">&radic;显示</option><option value=\"false\">&times;隐藏</option></select></td><td><select name=\"modView\" id=\"modView\"><option value=\"true\" selected=\"selected\">&radic;显示</option><option value=\"false\">&times;隐藏</option></select></td></tr></table>";

td6=tr.insertCell(5);
td6.innerHTML="<input type='button' value='删除' onClick='javascript:rows(this)'>";
}else{
rpelement(iobj);
}
}

function rpelement(iobj)
{
var elem = iobj.parentNode.parentNode;
if(confirm("你确定要删除这个属性吗？"))elem.parentNode.removeChild(elem);
}
///////异步删除/////////////////////////////////////////////////////////////////////////////////
function removeAttr(btobj,deleteID)
{
if(!confirm("你确定要删除这个属性吗？"))return;
var ajax=AJAX();
var requetUrl="delAttribute.jsp";
var params="id="+deleteID+"&author=james";
var result=null;
ajax.open("POST",requetUrl, true);
ajax.onreadystatechange=function ()
{
 if (ajax.readyState == 4)
  {
   if (ajax.status == 200)
   {
	try{
	////////////////////////////////////
	  result=ajax.responseText;
	  result=result.Trim();
	  if(result=="YES"){
	  var elem = btobj.parentNode.parentNode;
	  elem.parentNode.removeChild(elem);
	  }else if(result=="NO")alert("该属性被终极管理员锁定，无法删除或修改!");
	/////////////////////////////////////  
	   }catch(e)
	   {
	     document.write(e.description);
	    }
   }else
    {
     alert('远程通信发生错误！');
    }
  }else{
  //恐怕是失败了！！
  }
};
ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
ajax.setRequestHeader("Content-length", params.length);
ajax.setRequestHeader("Connection", "close");
ajax.send(params);
}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
</script>
<script language="javascript"> 
//类锁定操作
function lockCMS(imgObj,id)
{
  var ajax=AJAX();
var requetUrl="lockCms.jsp";
var params="id="+id;
var result=null;
ajax.open("POST",requetUrl, true);
ajax.onreadystatechange=function ()
{
 if (ajax.readyState == 4)
  {
   if (ajax.status == 200)
   {
	try{
	////////////////////////////////////
	  result=ajax.responseText;
          result=result.Trim();
          if(result=="lock")imgObj.src="dtree/img/usergroupicon.gif";
	  else if(result=="unlock")imgObj.src="dtree/img/usericon.gif";
	/////////////////////////////////////  
	   }catch(e)
	   {
	     document.write(e.description);
	    }
   }else
    {
     alert('远程通信发生错误！');
    }
  }else{
  //恐怕是失败了！！
  }
};
ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
ajax.setRequestHeader("Content-length", params.length);
ajax.setRequestHeader("Connection", "close");
ajax.send(params);
}
//属性锁定操作
function lockAttribute(imgObj,id)
{
var ajax=AJAX();
var requetUrl="lockAttribute.jsp";
var params="id="+id;
var result=null;
ajax.open("POST",requetUrl, true);
ajax.onreadystatechange=function ()
{
 if (ajax.readyState == 4)
  {
   if (ajax.status == 200)
   {
	try{
	////////////////////////////////////
	  result=ajax.responseText;
          result=result.Trim();
      if(result=="lock")imgObj.src="dtree/img/usergroupicon.gif";
	  else if(result=="unlock")imgObj.src="dtree/img/usericon.gif";
	/////////////////////////////////////  
	   }catch(e)
	   {
	     document.write(e.description);
	    }
   }else
    {
     alert('远程通信发生错误！');
    }
  }else{
  //恐怕是失败了！！
  }
};
ajax.setRequestHeader("Content-type","application/x-www-form-urlencoded");
ajax.setRequestHeader("Content-length", params.length);
ajax.setRequestHeader("Connection", "close");
ajax.send(params);
}
</script>
</head>

<body>
<div>
<table class="dtree" id="dtree">
<tr>
<td align="left" valign="top">
<script type="text/javascript">
<!--
d = new dTree('d');
//mytree.add(1, 0, 'My node', 'node.html', 'node title', 'mainframe', 'img/musicfolder.gif');
var ix=-1;
var rootId=0;
var pId=0;
d.add(0,-1,'请选择分类&nbsp;&nbsp;<span style="text-align:right; height:15px; background-color:#F0F0F0; vertical-align:middle"><a href="javascript:d.openAll();"><img src="dtree/img/icon-expandall.gif" border="0" /></a>-<a href="javascript:d.closeAll();"><img src="dtree/img/icon-closeall.gif" border="0" /></a></span>');
<%
s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List list=s.createQuery("from cmsClass order by pid ASC,sort ASC").list();
s.getTransaction().commit();
for(int i=0;i<list.size();i++)
{
    cmsClass cs=(cmsClass)list.get(i);
%>
    d.add(<%=cs.getId()%>,<%=cs.getPid()%>,'<%=cs.getName()%>&nbsp;<input type="radio" id="fckid" name="fckid"  onchange="javascript:setV()" value="<%=cs.getId()%>|<%=cs.getName()%>"/>');
<%
}
%>
document.write(d);
//-->
</script>
</td>
</tr>
<tr>
<td align="center"><input type="button" value="确定" onclick="javascript:setV()" />&nbsp;&nbsp;<input type="button" value="关闭" onclick="javascript:document.getElementById('dtree').style.display='none'" /></td>
</tr>
</table>
</div>
<form action="updateClass.jsp" method="post">
<input type="hidden" name="id" id="id" value="<%=id%>" />
<input type="hidden" name="pid" id="pid" value="<%=pid%>" />
<table width="100%" border="1" cellspacing="0" cellpadding="0">
  <tr class="title">
    <td colspan="2" align="center">修改数据类别(步骤2：修改属性)</td>
  </tr>
  
  <tr>
    <td height="30">父类别：</td>
    <td height="30"><%=pname%></td>
  </tr>
  <tr>
    <td width="9%" height="30">名称：</td>
    <td width="91%" height="30"><input name="name" type="text" id="name" size="50" maxlength="50" value="<%=name%>"  /><%if(cmscs.getLockme()){%><img src="dtree/img/usergroupicon.gif" ondblclick="javascript:lockCMS(this,<%=id%>)" /><%}else{%><img src="dtree/img/usericon.gif"  ondblclick="javascript:lockCMS(this,<%=id%>)"/><%}%></td>
  </tr>
  <tr>
    <td height="30">属性：</td>
    <td height="30"><table width="100%" border="1" cellspacing="0" cellpadding="0" frame="void">
      <tr>
        <td align="center" bgcolor="#CCCCCC">属性名*</td>
        <td align="center" bgcolor="#CCCCCC">属性值类型*</td>
        <td align="center" bgcolor="#CCCCCC">备选项(选)</td>
        <td align="center" bgcolor="#CCCCCC">默认值(选)</td>
        <td align="center" bgcolor="#CCCCCC"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td colspan="3" align="center">后台管理显示 </td>
              </tr>
            <tr style="font-size:9px">
              <td width="31%" align="center">列表中</td>
              <td width="40%" align="center">创建时</td>
              <td width="29%" align="center">修改时</td>
            </tr>
          </table></td>
        <td align="center" bgcolor="#CCCCCC"><input type="button" name="addbt" id="addbt" value="增加属性" onclick="javascript:rows(this)"/></td>
      </tr>
<%
s=HibernateUtil.getSessionFactory().getCurrentSession();
s.beginTransaction();
List attrlist=s.createQuery("from attribute where cid="+id+" order by sort ASC").list();
s.getTransaction().commit();
for(int i=0;i<attrlist.size();i++)
{
    attribute attri=(attribute)attrlist.get(i);
%>
      <tr class="gird">
        <td>
        <input type="hidden" id="attridM" name="attridM" value="<%=attri.getId()%>"/>
        <%if(attri.getLockme()){%><img src="dtree/img/usergroupicon.gif" ondblclick="javascript:lockAttribute(this,<%=attri.getId()%>)" /><%}else{%><img src="dtree/img/usericon.gif" ondblclick="javascript:lockAttribute(this,<%=attri.getId()%>)"/><%}%><input type="text" id="attrnameM" name="attrnameM" value="<%=attri.getName()%>"/></td>
        <td>
        <select name="attrtypeM" id="attrtypeM">
        <option value="string"<%if(attri.getType().equals("string")){%> selected="selected"<%}%>>文本</option>
        <option value="password"<%if(attri.getType().equals("password")){%> selected="selected"<%}%>>密码</option>
        <option value="int"<%if(attri.getType().equals("int")){%> selected="selected"<%}%>>整数</option>
        <option value="float"<%if(attri.getType().equals("float")){%> selected="selected"<%}%>>精确小数</option>
        <option value="radio"<%if(attri.getType().equals("radio")){%> selected="selected"<%}%>>单选</option>
        <option value="checkbox"<%if(attri.getType().equals("checkbox")){%> selected="selected"<%}%>>多选</option>
        <option value="list"<%if(attri.getType().equals("list")){%> selected="selected"<%}%>>下拉列表</option>
        <option value="text"<%if(attri.getType().equals("text")){%> selected="selected"<%}%>>内容/长文本</option>
        <option value="datetime"<%if(attri.getType().equals("datetime")){%> selected="selected"<%}%>>日期/时间</option>
        <option value="image"<%if(attri.getType().equals("image")){%> selected="selected"<%}%>>图像</option>
        <option value="file"<%if(attri.getType().equals("file")){%> selected="selected"<%}%>>文件</option>
        </select>        </td>
        <td><input type="text" size="3" id="attrvsM" name="attrvsM" onclick="javascript:getV(this)" value="<%=attri.getValueList()%>"/>
<span style="color:red" id="lv<%=i%>" name="lv<%=i%>"><%
            if(attri.getValueList()==1)out.println("未指定");
            else{
            try{
            s=HibernateUtil.getSessionFactory().getCurrentSession();
            s.beginTransaction();
            cmsClass cmscs_tt=(cmsClass)s.load(cmsClass.class,new Integer(attri.getValueList()));
            String valuelistname=cmscs_tt.getName();
            s.close();
            out.println("【"+valuelistname+"】下的分类");
            }catch(Exception cx)
            {
            out.println("未指定");
            }
            }
            %></span></td>
        <td><input type="text" id="attrsvM" name="attrsvM" value="<%=attri.getDefaultValue()%>"/></td>
        <td>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><select name="listViewM" id="listView">
        <option value="true"<%if(attri.getListColumn()){%> selected="selected"<%}%>>&radic;显示</option>
        <option value="false"<%if(!attri.getListColumn()){%> selected="selected"<%}%>>&times;隐藏</option>
      </select>
      </td>
      <td><select name="addViewM" id="addView">
        <option value="true"<%if(attri.getNewEdit()){%> selected="selected"<%}%>>&radic;显示</option>
        <option value="false"<%if(!attri.getNewEdit()){%> selected="selected"<%}%>>&times;隐藏</option>
            </select></td>
      <td><select name="modViewM" id="modView">
        <option value="true"<%if(attri.getUpdateEdit()){%> selected="selected"<%}%>>&radic;显示</option>
        <option value="false"<%if(!attri.getUpdateEdit()){%> selected="selected"<%}%>>&times;隐藏</option>
            </select></td>
    </tr>
  </table>
        </td>
        <td><input type='button' value="删除" id="tM<%=i%>"<%if(!attri.getLockme()){%> onClick="javascript:removeAttr(this,<%=attri.getId()%>)"<%}%>/></td>
      </tr>
<%
}
%>
<script language="javascript">
this.attr=<%=attrlist.size()%>;
</script>
    </table></td>
  </tr>
  <tr>
    <td height="30">&nbsp;</td>
    <td height="30">注：在属性管理中，打*的是必填项目，其他的为选填项。仅当属性类型为单选、多选、列表时才需要填写备选项</td>
  </tr>
  <tr>
    <td height="30">&nbsp;</td>
    <td height="30"><input type="button" name="button1" id="button1" value="上一步" onclick="javascript:window.history.back()" />&nbsp;&nbsp;<input type="submit" name="button" id="button" value="提交修改" />&nbsp;&nbsp;<input name="" value="撤销修改" type="reset" /></td>
  </tr>
</table>
</form>
</body>
</html>
