package com.apricot.app.lin;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;


	public class MYSQLDB {

		private Connection conn; 
		private PreparedStatement pstmt=null;
		private ResultSet rs=null;

	    private static	String DB_Addr = "localhost";  //连接数据库的地址
		private static String DB_Name = "deoa";    //数据库的名字
		  
		private static String DB_User = "zhaoweizi"; //数据库使用者名字，默认的root
		private static String DB_Password = "13807084531"; //数据库的密码 

//	    private static	String DB_Addr = "localhost";  //连接数据库的地址
//	    private static String DB_Name = "deoa";    //数据库的名字
//			  
//		private static String DB_User = "lin"; //数据库使用者名字，默认的root
//		private static String DB_Password = "123"; //数据库的密码 
			
		private static String DB_CharSet = "GB2312"; //编码方式
		private static String DB_Conn_String = "jdbc:mysql://"+DB_Addr+"/"+DB_Name+"?user="+DB_User+"&password="+DB_Password+"&useUnicode=true&characterEncoding="+DB_CharSet; 
		  

		public static Connection getDBC() {
			
			try 
			{ 
		
				String ClassForName ="com.mysql.jdbc.Driver";
				Class.forName(ClassForName).newInstance();

			         Connection conn=DriverManager.getConnection(DB_Conn_String); 
			         return conn; 
			} 
			catch(Exception e) 
			{ 
			         e.printStackTrace(); 
			         return null; 
			} 


		}
		
		public static void close(Connection conn,PreparedStatement stmt,ResultSet rs){
			try{
			if(rs!=null){
				rs.close();
				rs = null;
			}
			if(stmt!=null){
				stmt.close();
				stmt = null;
				
			}
			if(conn!=null){
				conn.close();
				conn=null;
			}
			}catch(SQLException e){
				e.printStackTrace();
			}	
		}
		
		
	}

