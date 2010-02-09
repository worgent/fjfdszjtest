package net.trust.datacollection.timer;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;
import net.trust.datacollection.thread.DataCollectionThread;
import net.trust.utils.PubFunction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 数据采集定时器，通过监听器来调用
 *
 */
public class DataCollectionTimerTask extends TimerTask {
	private Log log = LogFactory.getLog(DataCollectionTimerTask.class);
	private ServletContext servletContext;
	private BaseSqlMapDAO baseSqlMapDAO;
	private Properties properties;
	private DataCollectionThread dataCollectionThread;
	
	public DataCollectionTimerTask(ServletContext servletContext){
		this.servletContext = servletContext;
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");
		
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream("/net/trust/datacollection/timer/DataCollectionTimerTask.properties"));//加载Properties文件流
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		if (log.isDebugEnabled())
			log.debug("数据采集器已运行");
	}
	
	/**
	 * 取值方式 1：实时 2：按日 3：按周 4：按月 5：按年 
	 * 		Select * from td_system_para where para_type = 'INTER_FETCH_CONFIG' and para_name = 'FETCH_MODE'
	 */
	@Override
	public void run() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		int currentlyHour = cal.get(Calendar.HOUR_OF_DAY);
		List dataCollectionConfigList = null;
		
		if (log.isDebugEnabled())
			log.debug("按日采集数据");
		//按天方式取得数据采集规则
		dataCollectionConfigList = findDataCollectionConfig(2, currentlyHour);
		getDataCollConfigDetail(dataCollectionConfigList);
		
		
		//按周方式取得数据采集规则
		String weekDay = (new SimpleDateFormat("E")).format(date);	//取得当前时间为星期几
		if (properties.get("Timer.weekDay").equals(weekDay)){	//与配置中的时间对比是否可以开始执行
			if (log.isDebugEnabled())
				log.debug("按周采集数据");
			
			dataCollectionConfigList = findDataCollectionConfig(3, currentlyHour);
			getDataCollConfigDetail(dataCollectionConfigList);
		}//if
		
		
		//按月方式取得数据采集规则
		if (Integer.valueOf(properties.get("Timer.monthDay").toString()) == date.getDay()){	//与配置中的时间对比是否可以开始执行
			if (log.isDebugEnabled())
				log.debug("按月采集数据");
			
			dataCollectionConfigList = findDataCollectionConfig(4, currentlyHour);
			getDataCollConfigDetail(dataCollectionConfigList);
		}//if
		
		
		//按年方式取得数据采集规则
		if (Integer.valueOf(properties.get("Timer.yearMonth").toString()) == date.getYear()){	//与配置中的时间对比是否可以开始执行
			if (Integer.valueOf(properties.get("Timer.yearDay").toString()) == date.getDay()) {
				if (log.isDebugEnabled())
					log.debug("按年采集数据");
				
				dataCollectionConfigList = findDataCollectionConfig(5, currentlyHour);
				getDataCollConfigDetail(dataCollectionConfigList);
			}
		}//if
	}
	
	
	/**
	 * 查询数据采集规则
	 * @param fetchMode	采集方式
	 * @param collTime	采集时间（单位 小时）
	 * @return
	 */
	private List findDataCollectionConfig(int fetchMode, int collTime){
		HashMap param = new HashMap();
		param.put("fetchMode", ""+fetchMode);
		param.put("collTime", ""+collTime);
		return baseSqlMapDAO.queryForList("InterFatchConfig.findDataCollectionConifg", param);
	}
	
	/**
	 * 取得数据采集规则明细
	 * @param dataCollectionConfigList
	 */
	private void getDataCollConfigDetail(List dataCollectionConfigList){
		Iterator dataCollectionConfigIt = dataCollectionConfigList.iterator();
		HashMap dataCollectionConfigMap = null;
		HashMap param = null;
		String interFetchConfigId = null;
		String interSelectSql = null;
		String localSelectSql = null;
		String localInsertSql = null;
		String localUpdateSql = null;
		
		while (dataCollectionConfigIt.hasNext()){
			interFetchConfigId = (String)dataCollectionConfigIt.next();
			param = new HashMap();
			param.put("interFetchConfigId", interFetchConfigId);
			dataCollectionConfigMap = (HashMap)baseSqlMapDAO.queryForObject("InterFatchConfig.findInterFatchConfig", param);
			
			interSelectSql = PubFunction.getNulltoStr(dataCollectionConfigMap.get("inter_select_sql")).replaceAll("''", "'");
			localSelectSql = PubFunction.getNulltoStr(dataCollectionConfigMap.get("local_select_sql")).replaceAll("''", "'");
			localInsertSql = PubFunction.getNulltoStr(dataCollectionConfigMap.get("local_insert_sql")).replaceAll("''", "'");
			localUpdateSql = PubFunction.getNulltoStr(dataCollectionConfigMap.get("local_update_sql")).replaceAll("''", "'");
			
			if (log.isDebugEnabled())
				log.debug("创建“"+dataCollectionConfigMap.get("inter_fetch_config_name")+"”采集线程");
			//创建线程，让线程来执行数据采集
			dataCollectionThread = new DataCollectionThread(dataCollectionConfigMap.get("inter_fetch_config_name").toString(), interSelectSql, localSelectSql, localInsertSql, localUpdateSql, servletContext);
			dataCollectionThread.start();
		}
	}
}
