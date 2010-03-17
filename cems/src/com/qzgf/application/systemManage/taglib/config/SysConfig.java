package com.qzgf.application.systemManage.taglib.config;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qzgf.application.systemManage.taglib.domain.ConfigFacade;
import com.qzgf.application.systemManage.taglib.dto.Config;

/**
 * 
 * @author lsr
 *
 */
public class SysConfig {

	private static final Log logger = LogFactory.getLog(SysConfig.class);

	private HashMap<String, Config> configs;

	private ConfigFacade configFacade;

	public void init(){
		
	}
	
	
	public SysConfig(){
		this.configs = new HashMap<String, Config>();
		this.loadConfigs();
	}
	
	public HashMap<String, Config> getConfigs() {
		return configs;
	}


	public void setConfigs(HashMap<String, Config> configs) {
		this.configs = configs;
	}


	@SuppressWarnings("unchecked")
	public void loadConfigs() {
		logger.info("Load sys config...");								 
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext*.xml");
		configFacade=(ConfigFacade)context.getBean("configFacade");  
		List l = this.configFacade.findConfigs();
		for (int i = 0; i < l.size(); i++) {
			Config config = (Config) l.get(i);
			this.configs.put(config.getId(), config);
		}
		logger.info("Load sys config complete," + l.size() + " items is loaded.");
	}
	
	public ConfigFacade getConfigFacade() {
		return configFacade;
	}

	public void setConfigFacade(ConfigFacade configFacade) {
		this.configFacade = configFacade;
	}

	
	
}
