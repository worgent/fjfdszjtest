<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.sql.*" %>
<jsp:directive.page import="java.util.Date"/>
<jsp:directive.page import="java.text.SimpleDateFormat"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>水酒打印</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body onbeforeprint="pp.beforePrint()" onafterprint="pp.afterPrint()">
<%--<object ID='WebBrowser' WIDTH=0 HEIGHT=0 CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object> 
  
 --%>
 
 <%
 
    Date dt=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf1=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String time=sdf.format(dt);
		String datetime=sdf1.format(dt);
		String URL="jdbc:mysql://localhost:3306/deoa?useUnicode=true&amp;characterEncoding=utf8";
		String USER="zhaoweizi";
		String PASSWORD="13807084531";
		boolean flag;
		try {
		String sql="";
		 sql=sql+"select t1.*,t2.food_name,t3.* from order_list t1,food_info t2,order_info t3 where t1.food_id=t2.food_id and  t3.order_id=t1.order_id and t2.food_type in ('04','99','05','07','08') and t3.order_time like '"+time+"%'";
			if(request.getParameter("type").trim()!=""){
			String first=request.getParameter("type");
			//String last=request.getParameter("lastID");
			sql=sql+" and t3.order_type='"+first+"'";
			}
			if(request.getParameter("state").trim()!=""){
			String first=request.getParameter("state");
			//String last=request.getParameter("lastID");
			sql=sql+" and t3.order_status='"+first+"'";
			}
			if(request.getParameter("orderno").trim()!=""){
			String first=request.getParameter("orderno");
			//String last=request.getParameter("lastID");
			sql=sql+" and t3.order_no='"+first+"'";
			}
			if(request.getParameter("balconyname").trim()!=""){
			String first=request.getParameter("balconyname");
			//String last=request.getParameter("lastID");
			sql=sql+" and t3.balcony_name='"+first+"'";
			}
			if(request.getParameter("FOODACTION").trim()!=""){
			String first=request.getParameter("FOODACTION");
			//String last=request.getParameter("lastID");
			sql=sql+" and t1.food_action='"+first+"'";
			}if(request.getParameter("hurry").trim()!=""){
			String first=request.getParameter("hurry");
			sql=sql+" and t3.hurry_times !='0'";
			}if(request.getParameter("str").trim()!=""){
				String s2=request.getParameter("str").trim();
				sql=sql+" and t1.order_list_id in("+s2+")";
			//	System.out.print("str"+request.getParameter("str"));
			}
			if(request.getParameter("str").trim()!=""){
				String s2=request.getParameter("str").trim();
				sql=sql+" and t1.order_list_id in("+s2+")";
			//	System.out.print("str"+request.getParameter("str"));
			}else{
			sql=sql+" and t1.is_print='02'";
			}
			sql=sql+" order by  FIELD(t3.order_status, 0,2,1,3,4),FIELD(t1.food_action, 2,9,1)";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
				Statement stmt=con.createStatement();
				Statement stmt1=con.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				//if(rs.next()){
				%>
				<table width="95%" align=center>
							    <tr>
							        <td align=right><%--
							            <input type=button id="bp"  onclick="pp.beforePrint();this.disabled = true;document.getElementById('ap').disabled = false;" value="打印前" style="border:1px solid #000000">&nbsp;<input id="ap" type=button onclick="pp.afterPrint();this.disabled = true;document.getElementById('bp').disabled = false;" value="打印后" style="border:1px solid #000000" disabled=true>&nbsp; <input type=button value='打印' onclick="window.print();"  style="border:1px solid #000000">
							        --%></td>
							    </tr>
				</table>
					<table border=0 width="95%" align=center id="tabHeader">
					    <tr>
					        <td align="center"><font size='3' face='楷体_gb2312'>水酒打印</font></td>
					
					    </tr>
					    <tr>
					        <td><strong>日期:<%=datetime %></strong></td>
					    </tr>
					</table>
				
						<table border=1 width="95%" style="border-collapse:collapse;" align=center cellpadding=3 id="tabDetail">
						<tr><td>订单编号</td><td>订单类型</td><td>订单状态</td>
							<td>餐位名称</td><td>菜名</td><td>数量</td><td>菜品ID</td>
							<td>上菜标识</td><td>催菜次数</td>
						</tr>
				<%
				while(rs.next())
				{
				String	sql1=" update order_list set is_print='01' where order_list_id="+rs.getString("order_list_id");
				stmt1.executeUpdate(sql1);
					%>
					
						<tr>
							<td><%=rs.getString("order_no")%></td>
							<td><%=rs.getString("order_type")%></td>
							<td><%=rs.getString("order_status")%></td>
							<td><%=rs.getString("balcony_name")%></td>
							<td><%=rs.getString("food_name")%></td>
							<td><%=rs.getString("food_count")%></td>
							<td><%=rs.getString("order_list_id")%></td>
							<td>
								<%if("1".equals(rs.getString("food_action"))){
							 		out.print("点菜");
							 	} else if("2".equals(rs.getString("food_action"))){
							 		out.print("退菜");
							 	}else{
							 		out.print("取消");
							 	}%>
							</td>
							<td><%=rs.getString("hurry_times")%></td>
						</tr>
					
					<% 
					}
				//}else{}	
				%>
						</table>
						<table width="95%" border=0 id="tabFooter" align=center cellpadding=4>
							    <tr>
							        <td><!--ct--></td><td align=right></td>
							
							    </tr>
							</table>
							<div id="divPrint"></div>
							
						
				<%
				con.close();
			}
			catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
	

  %>
  <object id="factory" viewastext  style="display:none"
  classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
  codebase="ScriptX.cab#Version=5,60,0,360">
</object>
    <SCRIPT language='javascript'>
maxWin();
function maxWin()
{//纸张大小  A4
          var aw = 1240.5;
         var ah = 1741.5;
         window.moveTo(0, 0);
         window.resizeTo(aw, ah);
}
printBase();
function printBase() {
try
{

factory.printing.portrait = true;//true为纵向,false为横向
factory.printing.footer = "";//页脚
factory.printing.header = "";//页眉
factory.printing.leftMargin =0.5;//左对齐
factory.printing.topMargin = 0.5;//上对齐
factory.printing.rightMargin = 0.5;//右对齐
factory.printing.bottomMargin = 0.5;//下对齐
factory.printing.Print(false);
 }
        catch(e)
        {
        alert('没有设置默认打印机或安装smsx.exe文件');
                location.href='smsx.exe';
        }
}

</SCRIPT>



  </body>
</html>
