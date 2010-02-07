package  com.qzgf.NetStore.pub;

import org.apache.log4j.Logger;

public class LogUtil {
	//日志工具
	public static Logger getLogger(Class logClass){
		return Logger.getLogger(logClass);
	}

}
