package com.apricot.app.pda;
import java.util.Calendar;
import java.util.Date;
import java.text.*;
public class nowtime {

	/**
	 * @param args
	 */
	    public String timenumber(){
		Date d = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddkkmmss");
		String str=null;
		str=sdf.format(d);
		return str;
	}
	    public String time(){
			Date d = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
			String str=null;
			str=sdf.format(d);
			return str;
		}    
	    
	    public String printNextTime(int i) 
	     { 
	    	
	    	
	    	
	         Calendar cal = Calendar.getInstance(); 
	         Date date = new Date(); 
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	         String Time=null;
	         Time=sdf.format(date);

	         try 
	         { 
	             date = sdf.parse(Time); 
	             cal.setTime(date); 
	             cal.add(cal.DATE, i);
	             
	         } 
	         catch (Exception e) 
	         { 
	             // TODO Auto-generated catch block 
	             e.printStackTrace(); 
	         } 
	         return sdf.format(cal.getTime());
	     }
	    
	    public static void main(String[] args) {
	    	
			
			
		}
	    
}




