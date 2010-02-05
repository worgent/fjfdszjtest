import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.GetListDatebyBytesClient;
import com.qzgf.GetListDatebyBytesPortType;
import com.qzgf.utils.FileZip;
import com.qzgf.utils.WriteFile;


public class GetGpsRecordTimer extends TimerTask {
	private static boolean isRunning = false;		//是否运行
	private static boolean flag = true;				
	private int hour = 23;	//定时在每天晚上11点开始
	
	public GetGpsRecordTimer()
	{
		try {
			Properties properties = new Properties();
			properties.load(getClass().getResourceAsStream("GpsTimerTask.properties"));//加载Properties文件流
			hour = Integer.valueOf(properties.get("Timer.hour").toString());
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	@Override
	public void run() {
		Calendar cal = Calendar.getInstance();
		if (!isRunning) {
			if (hour == cal.get(Calendar.HOUR_OF_DAY) && flag) {
				isRunning = true;
				
				try{
					ProTimerTasker();
				}catch (Exception e){
					e.printStackTrace();
				}
				isRunning = false;
				flag = false;
			}
		}else {
			
		}
		if (hour != cal.get(Calendar.HOUR_OF_DAY)) {
			flag = true;
		}


	}
	/**
	 * 处理定时处理功能
	 */
	private void ProTimerTasker()
	{
		String commandStr = "";
		GetListDatebyBytesClient client = new GetListDatebyBytesClient();

		GetListDatebyBytesPortType service = client
				.getGetListDatebyBytesHttpPort();

		try {
			// 1.取得所有邮局车辆信息
			commandStr = "103,C,*,*,*";
			List lsCarNum = (List) FileZip.uncompressList(service
					.getCompress(commandStr));
			if (lsCarNum.size()!=0) {
				Iterator itcarnum = lsCarNum.iterator();// 主类叠代:得到记录
				while (itcarnum.hasNext()) {
					HashMap rowcarnumlist = (HashMap) itcarnum.next();// 取得车牌号
					String CarNum = rowcarnumlist.get("CarNum").toString();
					// 结合请求命令
					commandStr = "103,GPS_Timer," + CarNum + ",*,*";
					// 取回数据
					List carRecoder = (List) FileZip.uncompressList(service
							.getCompress(commandStr));
					// 写文件
					if (carRecoder.size() != 0) {
						WriteFile wf = new WriteFile();
						String filename = wf.ProWriteFilebyList(CarNum, System
								.getProperty("user.dir")
								+ "\\", commandStr, carRecoder);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
