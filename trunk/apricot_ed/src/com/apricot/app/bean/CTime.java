package com.apricot.app.bean;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.*; 
public class CTime 
{ 

    
     public static String time(){
			Date d = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=null;
			str=sdf.format(d);
			return str;
		}
     
     
     public String printNextTime(String Time) 
     { 
         Calendar cal = Calendar.getInstance(); 
         Date date = new Date(); 
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
         try 
         { 
             date = sdf.parse(Time); 
             cal.setTime(date); 
             cal.add(cal.DATE, 1); 
            
             System.out.println("下一天的时间是：" + sdf.format(cal.getTime())); 
         } 
         catch (Exception e) 
         { 
             // TODO Auto-generated catch block 
             e.printStackTrace(); 
         } 
         return sdf.format(cal.getTime());
     } 

     public static void main(String[] args) 
     { 
         CTime time = new CTime(); 
         time.printNextTime(time()); 
     } 
} 
