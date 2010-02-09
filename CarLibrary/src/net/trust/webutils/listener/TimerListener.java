package net.trust.webutils.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.StringUtils;

import net.trust.utils.GcUtil;


public class TimerListener implements ServletContextListener  {
	private java.util.Timer timer = null; 
	public void contextInitialized(ServletContextEvent sce) { 
		
		Map para =getConfig(sce.getServletContext());
		System.out.println(para);
		if("true".equals(para.get("open"))){
			timer = new java.util.Timer(true); 
			GcUtil gc =new GcUtil();
			if(para.get("minMemory")!=null){
				try{
					gc.setMinMemory(1024*1024*Long.parseLong((String)para.get("minMemory")));
				}catch(Exception ex){}
			}
			if(para.get("timer")!=null){
				try{
					gc.setTimer(1000 * Long.parseLong((String)para.get("timer")));
				}catch(Exception ex){}
			}			
			timer.schedule(gc,0,1*5*1000);
		}
	} 
	public void contextDestroyed(ServletContextEvent sce) { 
		timer.cancel(); 
		sce.getServletContext().log("Timer destroyed!"); 
	} 
	private Map getConfig(ServletContext sc){
		HashMap para = new HashMap();
		String configs=sc.getInitParameter("GcConfig");
		String[] params = StringUtils.tokenizeToStringArray(configs, ",; \t\n");
		int len = params.length;
		String[] tmp = null;
		
		for(int i=0; i<len; i++){
			tmp = StringUtils.tokenizeToStringArray(params[i], "=");
			if(tmp != null && tmp.length==2){
				para.put(tmp[0],tmp[1]);
			}
		}
		return para;
	}

}
