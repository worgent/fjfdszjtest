<%@page contentType="text/html;charset=gbk" pageEncoding="gbk" import="java.sql.*"%> 
<jsp:useBean id="connDbBean" scope="page" class="com.apricot.app.pda.MYSQL"/>  
<%   
   out.clear();   
   //keep the user's browser from caching the response 
   response.addHeader("Expires","Mon,26 Jul 1997 07:00:00 GMT");   
   response.addHeader("Last-Modified",new java.util.Date().toGMTString());   
   response.addHeader("Cache-Control","no-cache,must-revalidate");   
   response.addHeader("Pragma","no-cache");


   try{
      Connection  conn =connDbBean.getDBC();
      String sql=null;    
      request.setCharacterEncoding("utf-8");
      String str=request.getParameter("para"); 
      String idname=request.getParameter("idinfo");
      System.out.print("sssss"+str);  
      System.out.print("idname"+idname);    
      if(str==null||str.equals("")){   
         return;   
      } 
      if(idname.equals("suggest"))
      {sql="select BALCONY_NAME from dining_set_info where BALCONY_NAME like ('%"+str+"%') order by BALCONY_NAME"; }
      else
      {sql="select BALCONY_CODE from dining_set_info where BALCONY_CODE like ('%"+str+"%') order by BALCONY_CODE"; }
      Statement stmt=conn.createStatement();   
      ResultSet rs=stmt.executeQuery(sql);   
      while(rs.next()){   
          out.print(rs.getString(1)+"\n");   
          System.out.print(rs.getString(1)+"\n"); 
      }   
      rs.close();   
      stmt.close();   
      conn.close();   
   }catch(Exception e){   
      e.printStackTrace();   
   }   
   out.flush();   
   //out.close();   
%>  

