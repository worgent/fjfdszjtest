package com.apricot.app.bean;
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
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String str=null;
			str=sdf.format(d);
			return str;
		}
	    
	    public static String simpletime(){
			Date d = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String str=null;
			str=sdf.format(d);
			return str;
		}
	    
	    public String printtoday(String Time) 
	     { 
	         Calendar cal = Calendar.getInstance(); 
	         Date date = new Date(); 
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	         //String today=String.valueOf(cal.get(1));
	         try 
	         { 
	             date = sdf.parse(Time); 
	             cal.setTime(date); 
	             //cal.add(cal.DATE, 1);
	             
	         } 
	         catch (Exception e) 
	         { 
	             // TODO Auto-generated catch block 
	             e.printStackTrace(); 
	         } 
	         return sdf.format(cal.getTime());
	        // return today;
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
	             
	         } 
	         catch (Exception e) 
	         { 
	             // TODO Auto-generated catch block 
	             e.printStackTrace(); 
	         } 
	         return sdf.format(cal.getTime());
	     } 
	    
	    public String printhoutian(String Time) 
	     { 
	         Calendar cal = Calendar.getInstance(); 
	         Date date = new Date(); 
	         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	         try 
	         { 
	             date = sdf.parse(Time); 
	             cal.setTime(date); 
	             cal.add(cal.DATE, 1);
	         } 
	         catch (Exception e) 
	         { 
	             // TODO Auto-generated catch block 
	             e.printStackTrace(); 
	         } 
	         return sdf.format(cal.getTime());
	     }
	    
	    
	    public String today(){
	    	String today=null;	
	    	Date d=new Date();
	    	int year=d.getYear()+1900;
	    	int month=d.getMonth()+1;
	    	int day=d.getDate();
	    	today=year+"-"+month+"-"+day;
	    	    	
	    	return today;
	    }
	    
	    public String tomorrow(){
	    	String tomorrow=null;	
	    	Date d=new Date();
	    	int year=d.getYear()+1900;
	    	int month=d.getMonth()+1;
	    	int day=d.getDate()+1;
	      tomorrow=year+"-"+month+"-"+day;
	    	    	
	    	return tomorrow;
	    }
	    public String Acquired(){
	    	Date d=new Date();
	    	int year=d.getYear()+1900;
	    	int month=d.getMonth()+1;
	    	int day=d.getDate()+2;
	    	String Acquired=year+"-"+month+"-"+day;
	    	return Acquired;
	    }
	    public String tomo(){
	    	String tomorrow=null;	
	    	Date d=new Date();
	    	int year=d.getYear()+1900;
	    	int month=d.getMonth()+1;
	    	int day=d.getDate()+3;
	      tomorrow=year+"-"+month+"-"+day;
	    	    	
	    	return tomorrow;
	    }
	    
	}




