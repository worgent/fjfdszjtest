<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="java.sql.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
  </head>
  
  <body>
<object ID='WebBrowser' WIDTH=0 HEIGHT=0 CLASSID='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2'></object> 

  <%
		String URL="jdbc:mysql://localhost:3306/deoa?useUnicode=true&amp;characterEncoding=utf8";
		String USER="root";
		String PASSWORD="123@123";
		boolean flag;
		try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery("select t1.*,t2.food_name from order_list t1,food_info t2 where t1.food_id=t2.food_id and  serving_flag='1' and t1.order_id='"+request.getParameter("par")+"'");
				//if(rs.next()){
				%>
						<table>
						<tr><td width="120">菜品名称</td><td width="140">当前价格(￥)</td><td width="60">数量</td>
							<td width="120">菜品动作</td><td width="120">座位名称</td>
						</tr>
				<%
				while(rs.next())
				{
					%>
					
						<tr>
							<td width="120"><%=rs.getString("food_name")%></td>
							<td width="140"><%=rs.getString("food_price")%></td>
							<td width="60"><%=rs.getString("food_count")%></td>
							<td width="60">
							 	<%if("1".equals(rs.getString("food_action"))){
							 		out.print("点菜");
							 	} else if("2".equals(rs.getString("food_action"))){
							 		out.print("退菜");
							 	}else{
							 		out.print("取消");
							 	}%>
							 	
							</td>
							<td width="120"><%=request.getParameter("balconyname") %></td>
						</tr>
					
					<% 
					}
				//}else{}	
				%>
						</table>
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

  </body>
</html>
