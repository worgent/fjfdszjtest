package com.apricot.app.pda;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
public class nihao {
    String url = "jdbc:mysql://localhost:3306/deoa"; 
    //url="jdbc:oracle:thin:@localhost:1521:orcl";  jdbc:mysql://localhost:3306/test 
    String userName = "root";
    String password = "1234";
    public Connection getConnection() {
        Connection con=null;
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            con = DriverManager.getConnection(url, this.userName, this.password);
        }catch(SQLException sw){ 
         }
        System.out.println(" ddddddfffffffffdd="+con);
        return con;
    }
    public void testProc(){
        Connection conn = getConnection();
        CallableStatement stmt = null;
        
        try{System.out.println(" dddddddd="+conn);
            stmt = conn.prepareCall("{call lingylong()}");  
            
            //stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();
            int i= stmt.getInt(1);
            System.out.println("count = " + i);
        }catch(Exception e){
            System.out.println("hahad = "+e.toString());
        }finally{
            try {
                stmt.close();
                conn.close();
            }catch (Exception ex) {
                System.out.println("ex : "+ ex.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new nihao().testProc();
    }
}