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
<body>
<center>
   <table width="90%" height="190" border="1">
   <tr>
   <td colspan="3"> 
       <strong>网上寄件</strong>
   </td>
   </tr>
    <tr>
	<td>
		<s:actionerror theme="ems" />
	</td>
	<td>
		<s:actionmessage theme="ems" />
	</td>
  </tr>
   <tr> 
   <td>联系人:</td>
   <td>
   <s:property value="%{order.NAME}"/>
   </td>
   <td><div id="pnameTip" style="width:250px"></div></td>
   </tr>   
   
   <tr> 
   <td>固定电话:</td>
   <td>
   <s:property value="%{order.TEL}" />
   </td>
   <td><div id="ptelTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>手机:</td>
   <td>
   <s:property  value="%{order.MOBILE}"/>
   </td>
   <td><div id="pmobileTip" style="width:250px"></div></td>
   </tr>

 
   <tr> 
   <td rowspan="2">取件地址:</td>
   <td>
                         省份:<s:property value="PROVINCENAME"/>
                        地市：<s:property value="CITYNAME"/>
					  县、区：<s:property value="COUNTYNAME"/>
   </td>
   <td rowspan="2"><div id="paddressTip" style="width:250px"></div></td>
   </tr>
   <tr> 
   <td>
      详细地址:
      <s:property value="%{order.ADDRESS}"/>
   </td>
   </tr>    
 
   
   <tr> 
   <td>邮件种类:</td>
   <td>
   <s:property value="%{order.MAILTYPENAME}"/>
   </td>
   <td><div id="pmailtypeTip" style="width:250px"></div></td>
   </tr>  
 
   <tr> 
   <td>预约时间:</td>
   <td>
   <s:property  value="%{order.BOOKINGTIME}"/>
   </td>
   <td><div id="pbookingtimeTip" style="width:250px"></div></td>
   </tr>   

   <tr> 
   <td>寄件数量:</td>
   <td>
   <s:property value="%{order.ORDERINGNUM}"/>
   </td>
   <td><div id="porderingnumTip" style="width:250px"></div></td>
   </tr>   
   
   <tr> 
   <td>物品重量:</td>
   <td>
   <s:property value="%{order.ORDERINGWEIGHT}"/>(千克)
   </td>
   <td><div id="porderingweightTip" style="width:250px"></div></td>
   </tr>
   
   <tr> 
   <td>客户要求:</td>
   <td>
   <s:property value="%{order.CLIENTREMARK}"/>
   </td>
   <td><div id="pclientremarkTip" style="width:250px"></div></td>
   </tr>     

   <tr> 
   <td>收件人地址:</td>
   <td>
                         省份:<s:property value="%{order.RECPROVINCENAME}"/>
                        地市：<s:property value="%{order.RECCITYNAME}"/>
   </td>
   <td><div id="precaddressTip" style="width:250px"></div></td>
   </tr>
   <tr>
   <td colspan="3">
   <input type="button" onclick="javascript:history.go(-1)" value="返回"/>
   </td>
   </tr>
 </table>
</center>
</body>
</html>