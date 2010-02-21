

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import net.trust.application.baseArchives.carInfo.action.CarInfoAction;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;

import SMEPClient.SMEPClient;


public class Cmpp2ApiBack {
	//日志处理
	Log log = LogFactory.getLog(CarInfoAction.class);
	
	//获取服务信息
	String IN_pServerIP = "211.143.170.161"; // 服务器 IP
	int IN_ServerPort =8855;			 // 服务器端口
	String IN_pECode = "91384";		     // 企业短号 5位数字
	String IN_pUserName = "913841";      // 用户名  6位
	String IN_pPassword = "pkswd904";        // 密码
	String IN_pZoneID = "0594";          // 所属区号
	int IN_OverTime = 10;               // 超时的时间（秒）
	StringBuffer OUT_pServerIP = new StringBuffer(); // 用于返回可连接的服务器IP地址
	StringBuffer OUT_pPort = new StringBuffer();     // 用于返回可连接的服务器端口
	//关于代理暂时无法使用
	int IN_ProxyType = 0;			                  				 
	String IN_pProxyIP = "";
	int IN_ProxyPort = 0;
	int IN_ProxyAuth = 0;
	String IN_pProxyUserID = "";
	String IN_pProxyPassword = "";
	//发送与接收
	String IN_pQueryIP = "211.143.170.161";	//查询服务器IP地址
	int IN_QueryPort = 8855;			//查询服务器端口
	
	public Cmpp2ApiBack()
	{
		//初始化配置信息
		// GetConfig();
	}
	//=================================
	//得到相关配置
	//=================================
    private void GetConfig()
    {
    	try {
    		Properties properties = new Properties();//得到配置文件相关信息
    		properties.load(getClass().getResourceAsStream("Cmpp2Config.properties"));//加载Properties文件流
    		IN_pServerIP = properties.getProperty("CMPP.IN_pServerIP"); 						// 服务器 IP
    		IN_ServerPort =Integer.parseInt(properties.getProperty("CMPP.IN_ServerPort"));		// 服务器端口
    		IN_pECode = properties.getProperty("CMPP.IN_pECode");		    					// 企业短号
    		IN_pUserName = properties.getProperty("CMPP.IN_pUserName");      					// 用户名
    		IN_pPassword = properties.getProperty("CMPP.IN_pPassword");         				// 密码
    		IN_pZoneID = properties.getProperty("CMPP.IN_pZoneID");           					// 所属区号 
    		IN_OverTime = Integer.parseInt(properties.getProperty("CMPP.IN_OverTime"));        // 超时的时间（秒）      					// 所属区号 
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }
	
	//===============================================
	// 查询服务器调用示例
	//===============================================
	/**
	 * 获取空闲 MT 服务器
	 */
	public void GetServiceMT()
	{
		int nRet = 0;
		SMEPClient smep_clnt = new SMEPClient();
		System.out.println("获取空闲的服务器地址*******************");
		nRet = smep_clnt.SMEP_GetServer(IN_pServerIP, // 服务器 IP
				IN_ServerPort, // 服务器端口
				IN_ProxyType, // 代理类型(0-无代理，1-Socks4代理，2-Socks5代理)
				IN_pProxyIP, // 代理IP地址
				IN_ProxyPort, // 代理端口
				IN_ProxyAuth, //是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID, // 代理用户名
				IN_pProxyPassword, // 代理密码
				0, // 要获取服务器的类型：0 MT, 1 MO
				IN_pECode, // 企业短号
				IN_pUserName, // 用户名
				IN_pPassword, // 密码
				IN_pZoneID, // 所属区号
				IN_OverTime, // 超时的时间（秒）
				OUT_pServerIP, //用于返回可连接的服务器IP地址
				OUT_pPort); // 用于返回可连接的服务器端口
		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲 MT 服务器失败:" + errInfo);
			return;
		}
		System.out.println("获取空闲 MT 服务器成功,空闲服务器信息:" + OUT_pServerIP + ":"
				+ OUT_pPort);
	}
	/**
	 * 获取空闲MO服务
	 */
	public void GetServiceMO()
	{
		int nRet = 0;
		SMEPClient smep_clnt = new SMEPClient();
		nRet = smep_clnt.SMEP_GetServer(IN_pServerIP, // 服务器 IP
				IN_ServerPort, // 服务器端口
				IN_ProxyType, // 代理类型(0-无代理，1-Socks4代理，2-Socks5代理)
				IN_pProxyIP, // 代理IP地址
				IN_ProxyPort, // 代理端口
				IN_ProxyAuth, //是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID, // 代理用户名
				IN_pProxyPassword, // 代理密码
				1, // 要获取服务器的类型：0 MT, 1 MO
				IN_pECode, // 企业短号
				IN_pUserName, // 用户名
				IN_pPassword, // 密码
				IN_pZoneID, // 所属区号
				IN_OverTime, // 超时的时间（秒）
				OUT_pServerIP, // 用于返回可连接的服务器IP地址
				OUT_pPort); // 用于返回可连接的服务器端口
		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲 MO 服务器失败:" + errInfo);
			return;
		}
		System.out.println("获取空闲 MO 服务器成功,空闲服务器信息:" + OUT_pServerIP + ":"
				+ OUT_pPort);
	}
	/**
	 * 获取空闲非法服务器成功
	 */
	public void GetService() {
		int nRet = 0;
		SMEPClient smep_clnt = new SMEPClient();
		nRet = smep_clnt.SMEP_GetServer(IN_pServerIP, // 服务器 IP
				IN_ServerPort, // 服务器端口
				IN_ProxyType, // 代理类型(0-无代理，1-Socks4代理，2-Socks5代理)
				IN_pProxyIP, // 代理IP地址
				IN_ProxyPort, // 代理端口
				IN_ProxyAuth, // 是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID, // 代理用户名
				IN_pProxyPassword, // 代理密码
				2, // 要获取服务器的类型：0 MT, 1 MO
				IN_pECode, // 企业短号
				IN_pUserName, // 用户名
				IN_pPassword, // 密码
				IN_pZoneID, // 所属区号
				IN_OverTime, // 超时的时间（秒）
				OUT_pServerIP, // 用于返回可连接的服务器IP地址
				OUT_pPort); // 用于返回可连接的服务器端口
		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲非法服务器失败:" + errInfo);
		} else {
			System.out.println("获取空闲非法服务器成功,空闲服务器信息:" + OUT_pServerIP + ":"
					+ OUT_pPort);
		}
	}
	/**
	 * 修改密码
	 */
	public void ModifyServicePwd()
	{
		int nRet = 0;
		System.out.println("修改密码*******************");
		String IN_NewPW1 = "newPass";
		String IN_NewPW2 = "newPass";
		
		SMEPClient smep_clnt = new SMEPClient();
		nRet = smep_clnt.SMEP_EditPW(IN_pServerIP, // 服务器 IP
				IN_ServerPort, // 服务器端口
				IN_ProxyType, // 代理类型(0-无代理，1-Socks4代理，2-Socks5代理)
				IN_pProxyIP, // 代理IP地址
				IN_ProxyPort, // 代理端口
				IN_ProxyAuth, //是否进行用户名密码校验（0-不需要，1-需要,对于Socks4代理无效）
				IN_pProxyUserID, // 代理用户名(对于Socks4代理无效)
				IN_pProxyPassword, // 代理密码(对于Socks4代理无效)
				IN_pECode, // 企业短号
				IN_pUserName, // 用户名
				IN_pPassword, // 密码
				IN_pZoneID, // 所属区号
				IN_NewPW1, // 新密码
				IN_NewPW2, // 重复新密码
				IN_OverTime); // 超时时间
		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("修改密码失败:" + errInfo);
			return;
		}
		System.out.println("修改密码成功!");		
		
	}
	/**
	 * 查询历史记录成功,MT总数，MO总数
	 */
	public void GetServiceNum()
	{	
		System.out.println("查询历史记录*******************");
		String IN_YearMonth = "200303";
		StringBuffer OUT_MTNum = new StringBuffer();
		StringBuffer OUT_MONum = new StringBuffer();
		
		int nRet = 0;		
		SMEPClient smep_clnt = new SMEPClient();
		nRet = smep_clnt.SMEP_QueryHistory(IN_pServerIP, // 服务器 IP
				IN_ServerPort, // 服务器端口
				IN_ProxyType, // 代理类型(0-无代理，1-Socks4代理，2-Socks5代理)
				IN_pProxyIP, // 代理IP地址
				IN_ProxyPort, // 代理端口
				IN_ProxyAuth, // 是否进行用户名密码校验（0-不需要，1-需要,对于Socks4代理无效）
				IN_pProxyUserID, // 代理用户名(对于Socks4代理无效)
				IN_pProxyPassword, // 代理密码(对于Socks4代理无效)
				IN_pECode, // 企业短号
				IN_pUserName, // 用户名
				IN_pPassword, // 密码
				IN_pZoneID, // 所属区号
				IN_YearMonth, // 要查询的年月
				OUT_MTNum, // 返回查询出的MT总数。
				OUT_MONum, // 返回查询出的MO总数。
				IN_OverTime); // 超时的时间（秒）
		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("查询历史记录失败:" + errInfo);
			return;
		}
		System.out.println("查询历史记录成功,MT总数:" + OUT_MTNum + "MO总数:" + OUT_MONum);		
	}
	/**
	 * 查询MT发送状态
	 */
	public  void GetServiceMTState()
	{
		System.out.println("查询MT发送状态*******************");
		String IN_YearMonth = "200303";
		String IN_MsgID = "00000000000000000000000004115939";
		String IN_DestTermID = "13906096008";
		
		int nRet = 0;
		SMEPClient smep_clnt = new SMEPClient();
		nRet = smep_clnt.SMEP_QueryReport(IN_pServerIP, // 服务器 IP
				IN_ServerPort, // 服务器端口
				IN_ProxyType, // 代理类型(0-无代理，1-Socks4代理，2-Socks5代理)
				IN_pProxyIP, // 代理IP地址
				IN_ProxyPort, // 代理端口
				IN_ProxyAuth, // 是否进行用户名密码校验（0-不需要，1-需要,对于Socks4代理无效）
				IN_pProxyUserID, // 代理用户名(对于Socks4代理无效)
				IN_pProxyPassword, // 代理密码(对于Socks4代理无效)
				IN_pECode, // 企业短号
				IN_pUserName, // 用户名
				IN_pPassword, // 密码
				IN_pZoneID, // 所属区号
				IN_MsgID, // 要查询的MsgID。
				IN_DestTermID, // 目标手机号
				IN_YearMonth, // 要查询的年月。
				IN_OverTime); // 超时时间(秒)

		StringBuffer errInfo = new StringBuffer(125);
		smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
		System.out.println("查询MT发送状态结果:" + errInfo);
	}
	//===============================================
	// MT 服务器调用示例
	//===============================================
	/**
	 * 短信发送示例
	 */
	public void CmppSend()
	{
		int nRet = -1; //函数的返回值

		int IN_ServerType = 0;//要查询的服务器类型(0-MT,1-MO)
		int IN_ConnectType = 0;//要获取服务器的类型(0 MT, 1 MO)
		StringBuffer OUT_pStatus = new StringBuffer();
		int nConnectID;
		
		StringBuffer OUT_pServerIP = new StringBuffer(); // 用于返回可连接的服务器IP地址
		StringBuffer OUT_pPort = new StringBuffer();     // 用于返回可连接的服务器端口

		SMEPClient smep_clnt = new SMEPClient();

		System.out.println("获取空闲的服务器地址*******************");
		nRet = smep_clnt.SMEP_GetServer(IN_pQueryIP,//查询服务器IP地址
				IN_QueryPort,//查询服务器端口
				IN_ProxyType,//代理类型(0-无代理,1-Socks4代理,2-Socks5代理)
				IN_pProxyIP,//代理IP
				IN_ProxyPort,//代理端口
				IN_ProxyAuth,//代理验证标志(0-不验证,1-验证)
				IN_pProxyUserID,//代理用户名
				IN_pProxyPassword,//代理密码
				IN_ServerType,//要获取服务器的类型(0 MT, 1 MO)
				IN_pECode,//企业短号
				IN_pUserName,//用户名
				IN_pPassword,//密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pServerIP,// 查询到的服务器的IP地址
				OUT_pPort);// 查询到的服务器的端口

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲 MT 服务器失败:" + errInfo);
			return;
		}

		System.out.println("获取空闲的 MT 服务器地址成功，IP地址:" + OUT_pServerIP + ",端口:"
				+ Integer.parseInt(OUT_pPort.toString()));

		System.out.println("连接MT服务器:" + OUT_pServerIP.toString() + ":"
				+ Integer.parseInt(OUT_pPort.toString()));
		
		
		//============================================================================================
		nConnectID = smep_clnt.SMEP_Connect(OUT_pServerIP.toString(),//服务器IP地址
				Integer.parseInt(OUT_pPort.toString()),//服务器端口
				IN_ProxyType,//代理类型
				IN_pProxyIP,// 代理IP地址
				IN_ProxyPort,// 代理端口
				IN_ProxyAuth,//是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID,// 代理用户名
				IN_pProxyPassword,// 代理密码
				IN_ConnectType,// 连接方式：0 MT, 1 MO
				IN_pECode,// 企业短号
				IN_pUserName,// 用户名
				IN_pPassword,// 密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pStatus);// 返回状态


		
		if (nConnectID < 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, Integer.parseInt(OUT_pStatus
					.toString().trim()), 124, errInfo);
			System.out.println("连接 MT 服务器错误:" + errInfo);
			return;
		}
		System.out.println("连接成功：句柄(" + nConnectID + ")");
		//============================================================================================
		int IN_Msg_Level = 0; // 消息级别
		int IN_ReportFlag = 1;
		int IN_MTFlag = 0;
		String IN_MOMsgID = "";
		String IN_AtTime = "";
		String IN_Src_Terminal_ID = "1234";
		int IN_DestCount = 1;
		ArrayList Dest_Terminal_ID = new ArrayList();
		Dest_Terminal_ID.add(0, "13599204724");
		//Dest_Terminal_ID.add(1, "13805710002");
		//Dest_Terminal_ID.add(2, "13805710003");

		int IN_Msg_Fotmat = 0;
		String IN_pMsg_Content = "测试teat123!";
		StringBuffer OUT_pMsgID = new StringBuffer();

		System.out.println("链路测试*************************");
		int iReturn = smep_clnt.SMEP_Active_Test(nConnectID, IN_OverTime);
		if (iReturn == 0) {
			System.out.println("链路测试成功!");
		} else {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, iReturn, 124, errInfo);
			System.out.println("链路测试失败:" + errInfo);

			System.out.println("断开连接***********************");
			smep_clnt.SMEP_Disconnection(nConnectID);
			return;
		}

		System.out.println("提交短消息*************************");
		iReturn = smep_clnt.SMEP_Submit(nConnectID,// 由 SMEP_Connect 返回的连接标识
				IN_Msg_Level,// 消息级别 0
				IN_ReportFlag,// 状态报告标记 1
				IN_MTFlag,// 引起MT的原因
				IN_MOMsgID,// 引起MT的MO的MsgID
				IN_AtTime,// 发送时间设置
				IN_Src_Terminal_ID,// 源手机号码
				IN_DestCount,// 目标手机数量
				Dest_Terminal_ID,// 目标手机号码队列
				IN_Msg_Fotmat,// 消息格式
				IN_pMsg_Content,// 消息内容
				IN_OverTime,// 超时的时间（秒）
				OUT_pMsgID);
		if (iReturn == 0)// 发送成功返回的该条短消息标识
		{
			System.out.println("发送短信成功,消息号为:" + OUT_pMsgID);
		} else {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, iReturn, 124, errInfo);
			System.out.println("errInfo:" + errInfo + " [" + OUT_pMsgID + "]");
		}

		System.out.println("断开连接***********************");
		smep_clnt.SMEP_Disconnection(nConnectID);		
	}
	
	
	/**
	 * 短信发送示例
	 * 群发短信
	 */
	public int CmppSend(List<SmsBean> cmppList)
	{
		if (log.isDebugEnabled())
			log.debug("开始发送短信");
		
		int Result=0;  //发送返回值
		
		int nRet = -1; //函数的返回值

		int IN_ServerType = 0;//要查询的服务器类型(0-MT,1-MO)
		int IN_ConnectType = 0;//要获取服务器的类型(0 MT, 1 MO)
		
		StringBuffer OUT_pStatus = new StringBuffer();
		
		int nConnectID;
		
		StringBuffer OUT_pServerIP = new StringBuffer(); // 用于返回可连接的服务器IP地址
		StringBuffer OUT_pPort = new StringBuffer();     // 用于返回可连接的服务器端口

		SMEPClient smep_clnt = new SMEPClient();

		System.out.println("获取空闲的服务器地址*******************");
		nRet = smep_clnt.SMEP_GetServer(IN_pQueryIP,//查询服务器IP地址
				IN_QueryPort,//查询服务器端口
				IN_ProxyType,//代理类型(0-无代理,1-Socks4代理,2-Socks5代理)
				IN_pProxyIP,//代理IP
				IN_ProxyPort,//代理端口
				IN_ProxyAuth,//代理验证标志(0-不验证,1-验证)
				IN_pProxyUserID,//代理用户名
				IN_pProxyPassword,//代理密码
				IN_ServerType,//要获取服务器的类型(0 MT, 1 MO)
				IN_pECode,//企业短号
				IN_pUserName,//用户名
				IN_pPassword,//密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pServerIP,// 查询到的服务器的IP地址
				OUT_pPort);// 查询到的服务器的端口

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲 MT 服务器失败:" + errInfo);
			
			if (log.isDebugEnabled())
				log.debug("获取空闲 MT 服务器失败:" + errInfo);			
			return Result;
		}

		System.out.println("获取空闲的 MT 服务器地址成功，IP地址:" + OUT_pServerIP + ",端口:"
				+ Integer.parseInt(OUT_pPort.toString()));

		System.out.println("连接MT服务器:" + OUT_pServerIP.toString() + ":"
				+ Integer.parseInt(OUT_pPort.toString()));
		
		if (log.isDebugEnabled())
			log.debug("连接MT服务器:" + OUT_pServerIP.toString() + ":"
					+ Integer.parseInt(OUT_pPort.toString()));
		
		
		//============================================================================================
		nConnectID = smep_clnt.SMEP_Connect(OUT_pServerIP.toString(),//服务器IP地址
				Integer.parseInt(OUT_pPort.toString()),//服务器端口
				IN_ProxyType,//代理类型
				IN_pProxyIP,// 代理IP地址
				IN_ProxyPort,// 代理端口
				IN_ProxyAuth,//是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID,// 代理用户名
				IN_pProxyPassword,// 代理密码
				IN_ConnectType,// 连接方式：0 MT, 1 MO
				IN_pECode,// 企业短号
				IN_pUserName,// 用户名
				IN_pPassword,// 密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pStatus);// 返回状态
		if (nConnectID < 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, Integer.parseInt(OUT_pStatus
					.toString().trim()), 124, errInfo);
			System.out.println("连接 MT 服务器错误:" + errInfo);
			if (log.isDebugEnabled())
				log.debug("连接 MT 服务器错误:" + errInfo);
			return Result;
		}
		System.out.println("连接成功：句柄(" + nConnectID + ")");
		
		if (log.isDebugEnabled())
			log.debug("连接成功：句柄(" + nConnectID + ")");
		//============================================================================================
		int IN_Msg_Level = 0; // 消息级别
		int IN_ReportFlag = 1;
		int IN_MTFlag = 0;
		String IN_MOMsgID = "";
		String IN_AtTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());//时间格式
		//得到首条信息
		String IN_Src_Terminal_ID =((SmsBean)cmppList.get(0)).getSn(); //标识ID
		int IN_DestCount = cmppList.size();                            //手机数
		ArrayList Dest_Terminal_ID = new ArrayList();                  //手机号数组队列
		for (int i=0; i<cmppList.size(); i++){
			SmsBean cmpp = (SmsBean)cmppList.get(i);
			Dest_Terminal_ID.add(i,cmpp.getMobileNo());
		}

		int IN_Msg_Fotmat = 0;
		String IN_pMsg_Content = ((SmsBean)cmppList.get(0)).getSmsContent();//需要发送的内容
		StringBuffer OUT_pMsgID = new StringBuffer();

		System.out.println("链路测试*************************");
		int iReturn = smep_clnt.SMEP_Active_Test(nConnectID, IN_OverTime);
		if (iReturn == 0) {
			System.out.println("链路测试成功!");
		} else {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, iReturn, 124, errInfo);
			smep_clnt.SMEP_Disconnection(nConnectID);
			
			if (log.isDebugEnabled())
				log.debug("链路测试失败:" + errInfo);
			return Result;
		}

		System.out.println("提交短消息*************************");
		iReturn = smep_clnt.SMEP_Submit(nConnectID,// 由 SMEP_Connect 返回的连接标识
				IN_Msg_Level,// 消息级别 0
				IN_ReportFlag,// 状态报告标记 1
				IN_MTFlag,// 引起MT的原因
				IN_MOMsgID,// 引起MT的MO的MsgID
				IN_AtTime,// 发送时间设置
				IN_Src_Terminal_ID,// 源手机号码
				IN_DestCount,// 目标手机数量
				Dest_Terminal_ID,// 目标手机号码队列
				IN_Msg_Fotmat,// 消息格式
				IN_pMsg_Content,// 消息内容
				IN_OverTime,// 超时的时间（秒）
				OUT_pMsgID);
		if (iReturn == 0)// 发送成功返回的该条短消息标识
		{
			if (log.isDebugEnabled())
				log.debug("发送短信成功,消息号为:" + OUT_pMsgID);
			Result=1;
		} else {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, iReturn, 124, errInfo);
			if (log.isDebugEnabled())
				log.debug("errInfo:" + errInfo + " [" + OUT_pMsgID + "]");
		}
		
		System.out.println("断开连接***********************");
		smep_clnt.SMEP_Disconnection(nConnectID);
		
		return Result;
	}
	
	/**
	 * 短信发送示例
	 * 群发短信
	 */
	public int CmppSend(SmsBean cmpp)
	{
		if (log.isDebugEnabled())
			log.debug("开始发送短信");
		
		int Result=0;  //发送返回值
		
		int nRet = -1; //函数的返回值

		int IN_ServerType = 0;//要查询的服务器类型(0-MT,1-MO)
		int IN_ConnectType = 0;//要获取服务器的类型(0 MT, 1 MO)
		
		StringBuffer OUT_pStatus = new StringBuffer();
		
		int nConnectID;
		
		StringBuffer OUT_pServerIP = new StringBuffer(); // 用于返回可连接的服务器IP地址
		StringBuffer OUT_pPort = new StringBuffer();     // 用于返回可连接的服务器端口

		SMEPClient smep_clnt = new SMEPClient();

		System.out.println("获取空闲的服务器地址*******************");
		nRet = smep_clnt.SMEP_GetServer(IN_pQueryIP,//查询服务器IP地址
				IN_QueryPort,//查询服务器端口
				IN_ProxyType,//代理类型(0-无代理,1-Socks4代理,2-Socks5代理)
				IN_pProxyIP,//代理IP
				IN_ProxyPort,//代理端口
				IN_ProxyAuth,//代理验证标志(0-不验证,1-验证)
				IN_pProxyUserID,//代理用户名
				IN_pProxyPassword,//代理密码
				IN_ServerType,//要获取服务器的类型(0 MT, 1 MO)
				IN_pECode,//企业短号
				IN_pUserName,//用户名
				IN_pPassword,//密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pServerIP,// 查询到的服务器的IP地址
				OUT_pPort);// 查询到的服务器的端口

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲 MT 服务器失败:" + errInfo);
			
			if (log.isDebugEnabled())
				log.debug("获取空闲 MT 服务器失败:" + errInfo);			
			return Result;
		}

		System.out.println("获取空闲的 MT 服务器地址成功，IP地址:" + OUT_pServerIP + ",端口:"
				+ Integer.parseInt(OUT_pPort.toString()));

		System.out.println("连接MT服务器:" + OUT_pServerIP.toString() + ":"
				+ Integer.parseInt(OUT_pPort.toString()));
		
		if (log.isDebugEnabled())
			log.debug("连接MT服务器:" + OUT_pServerIP.toString() + ":"
					+ Integer.parseInt(OUT_pPort.toString()));
		
		
		//============================================================================================
		nConnectID = smep_clnt.SMEP_Connect(OUT_pServerIP.toString(),//服务器IP地址
				Integer.parseInt(OUT_pPort.toString()),//服务器端口
				IN_ProxyType,//代理类型
				IN_pProxyIP,// 代理IP地址
				IN_ProxyPort,// 代理端口
				IN_ProxyAuth,//是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID,// 代理用户名
				IN_pProxyPassword,// 代理密码
				IN_ConnectType,// 连接方式：0 MT, 1 MO
				IN_pECode,// 企业短号
				IN_pUserName,// 用户名
				IN_pPassword,// 密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pStatus);// 返回状态
		if (nConnectID < 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, Integer.parseInt(OUT_pStatus
					.toString().trim()), 124, errInfo);
			System.out.println("连接 MT 服务器错误:" + errInfo);
			if (log.isDebugEnabled())
				log.debug("连接 MT 服务器错误:" + errInfo);
			return Result;
		}
		System.out.println("连接成功：句柄(" + nConnectID + ")");
		
		if (log.isDebugEnabled())
			log.debug("连接成功：句柄(" + nConnectID + ")");
		//============================================================================================
		int IN_Msg_Level = 0; // 消息级别
		int IN_ReportFlag = 1;
		int IN_MTFlag = 0;
		String IN_MOMsgID = "";
		String IN_AtTime = (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());//时间格式
		//得到首条信息
		String IN_Src_Terminal_ID =cmpp.getSn(); //标识ID
		int IN_DestCount = 1;                         //手机数
		ArrayList Dest_Terminal_ID = new ArrayList();                  //手机号数组队列
		Dest_Terminal_ID.add(0,cmpp.getMobileNo());

		
		int IN_Msg_Fotmat = 0;
		String IN_pMsg_Content = cmpp.getSmsContent();//需要发送的内容
		StringBuffer OUT_pMsgID = new StringBuffer();

		
		System.out.println("链路测试*************************");
		int iReturn = smep_clnt.SMEP_Active_Test(nConnectID, IN_OverTime);
		if (iReturn == 0) {
			System.out.println("链路测试成功!");
		} else {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, iReturn, 124, errInfo);
			smep_clnt.SMEP_Disconnection(nConnectID);
			
			if (log.isDebugEnabled())
				log.debug("链路测试失败:" + errInfo);
			return Result;
		}

		
		System.out.println("提交短消息*************************");
		iReturn = smep_clnt.SMEP_Submit(nConnectID,// 由 SMEP_Connect 返回的连接标识
				IN_Msg_Level,// 消息级别 0
				IN_ReportFlag,// 状态报告标记 1
				IN_MTFlag,// 引起MT的原因
				IN_MOMsgID,// 引起MT的MO的MsgID
				IN_AtTime,// 发送时间设置
				IN_Src_Terminal_ID,// 源手机号码
				IN_DestCount,// 目标手机数量
				Dest_Terminal_ID,// 目标手机号码队列
				IN_Msg_Fotmat,// 消息格式
				IN_pMsg_Content,// 消息内容
				IN_OverTime,// 超时的时间（秒）
				OUT_pMsgID);
		if (iReturn == 0)// 发送成功返回的该条短消息标识
		{
			if (log.isDebugEnabled())
				log.debug("发送短信成功,消息号为:" + OUT_pMsgID);
			Result=1;
		} else {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, iReturn, 124, errInfo);
			if (log.isDebugEnabled())
				log.debug("errInfo:" + errInfo + " [" + OUT_pMsgID + "]");
		}
		
		System.out.println("断开连接***********************");
		smep_clnt.SMEP_Disconnection(nConnectID);
		
		return Result;
	}
	
	//===============================================
	// MO 服务器调用示例
	//===============================================
	/**
	 * 短信接收
	 */
	public void CmppRecevie()
	{
		int nRet = -1; //函数的返回值
		int IN_ConnectType = 1;
		StringBuffer OUT_pStatus = new StringBuffer();
		int nConnectID;
		int IN_ServerType = 1;//要查询的服务器类型(0-MT,1-MO)
		
		StringBuffer OUT_pServerIP = new StringBuffer(); // 用于返回可连接的服务器IP地址
		StringBuffer OUT_pPort = new StringBuffer();     // 用于返回可连接的服务器端口
		
		
		SMEPClient smep_clnt = new SMEPClient();

		System.out.println("获取空闲的 MO 服务器地址*******************");
		nRet = smep_clnt.SMEP_GetServer(IN_pQueryIP,//查询服务器IP地址
				IN_QueryPort,//查询服务器端口
				IN_ProxyType,//代理类型(0-无代理,1-Socks4代理,2-Socks5代理)
				IN_pProxyIP,//代理IP
				IN_ProxyPort,//代理端口
				IN_ProxyAuth,//代理验证标志(0-不验证,1-验证)
				IN_pProxyUserID,//代理用户名
				IN_pProxyPassword,//代理密码
				IN_ConnectType,//要获取服务器的类型(0 MT, 1 MO)
				IN_pECode,//企业短号
				IN_pUserName,//用户名
				IN_pPassword,//密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pServerIP,// 查询到的服务器的IP地址
				OUT_pPort);// 查询到的服务器的端口

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("获取空闲 MO 服务器失败:" + errInfo);
			return;
		}

		System.out.println("获取空闲的 MO 服务器地址成功，IP地址:" + OUT_pServerIP + ",端口:"
				+ Integer.parseInt(OUT_pPort.toString()));

		System.out.println("连接MO服务器:" + OUT_pServerIP.toString() + ":"
				+ Integer.parseInt(OUT_pPort.toString()));

		nConnectID = smep_clnt.SMEP_Connect(OUT_pServerIP.toString(),//服务器IP地址
				Integer.parseInt(OUT_pPort.toString()),//服务器端口
				IN_ProxyType,//代理类型
				IN_pProxyIP,// 代理IP地址
				IN_ProxyPort,// 代理端口
				IN_ProxyAuth,//是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID,// 代理用户名
				IN_pProxyPassword,// 代理密码
				IN_ConnectType,// 连接方式：0 MT, 1 MO
				IN_pECode,// 企业短号
				IN_pUserName,// 用户名
				IN_pPassword,// 密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pStatus);// 返回状态
		if (nConnectID < 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, Integer.parseInt(OUT_pStatus
					.toString().trim()), 124, errInfo);
			System.out.println("连接服务器错误:" + errInfo);
			return;
		}
		System.out.println("连接成功：句柄(" + nConnectID + ")");

		System.out.println("接收MO信息************************");
		StringBuffer OUT_Flag = new StringBuffer();
		StringBuffer OUT_pStat = new StringBuffer();
		StringBuffer OUT_pSrcTerminalID = new StringBuffer();
		StringBuffer OUT_pDestTerminalID = new StringBuffer();
		StringBuffer OUT_MsgID = new StringBuffer();
		StringBuffer OUT_pBuffer = new StringBuffer();

		nRet = smep_clnt.SMEP_GetRereport(nConnectID,// 连接句柄
				IN_OverTime,// 超时的时间（秒）
				OUT_Flag,// 成功的话，返回MO的状态：0 点播；1 状态报告；2 链路测试；3 服务器要求断开
				OUT_pStat,// 成功的话，如果 OUT_Flag==1，返回状态报告的状态：0 成功，其他失败
				OUT_pSrcTerminalID,// 成功的话，返回源终端号码
				OUT_pDestTerminalID,// 成功的话，返回目标终端号码
				OUT_MsgID,// 成功的话，返回MsgID
				OUT_pBuffer);// 成功的话，返回点播内容

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			System.out.println("收MO消息错误:" + errInfo);
		} else {
			switch (Integer.parseInt(OUT_Flag.toString())) {
			case 0:
				System.out.println("收到点播信息:源终端号码-" + OUT_pSrcTerminalID
						+ "目标终端号码-" + OUT_pDestTerminalID + "MO消息标识-"
						+ OUT_MsgID + "消息内容-" + OUT_pBuffer);
				break;
			case 1:
				System.out.println("收到状态报告:目标终端号码-" + OUT_pDestTerminalID
						+ "消息标识-" + OUT_MsgID + "状态-" + OUT_pStat);
				break;
			case 2:
				System.out.println("收到链路测试!");
				break;
			case 3:
				System.out.println("服务器关闭连接");
				break;
			default:
				System.out.println("错误MO");
			}
		}

		System.out.println("断开连接*******************");
		smep_clnt.SMEP_Disconnection(nConnectID);
	}
	
	
	/**
	 * 短信接收
	 */
	public List CmppDeliver()
	{
		List Result =new ArrayList();
		int nRet = -1; //函数的返回值
		int IN_ConnectType = 1;
		StringBuffer OUT_pStatus = new StringBuffer();
		int nConnectID;
		int IN_ServerType = 1;//要查询的服务器类型(0-MT,1-MO)
		
		StringBuffer OUT_pServerIP = new StringBuffer(); // 用于返回可连接的服务器IP地址
		StringBuffer OUT_pPort = new StringBuffer();     // 用于返回可连接的服务器端口
		
		
		SMEPClient smep_clnt = new SMEPClient();

		System.out.println("获取空闲的 MO 服务器地址*******************");
		nRet = smep_clnt.SMEP_GetServer(IN_pQueryIP,//查询服务器IP地址
				IN_QueryPort,//查询服务器端口
				IN_ProxyType,//代理类型(0-无代理,1-Socks4代理,2-Socks5代理)
				IN_pProxyIP,//代理IP
				IN_ProxyPort,//代理端口
				IN_ProxyAuth,//代理验证标志(0-不验证,1-验证)
				IN_pProxyUserID,//代理用户名
				IN_pProxyPassword,//代理密码
				IN_ConnectType,//要获取服务器的类型(0 MT, 1 MO)
				IN_pECode,//企业短号
				IN_pUserName,//用户名
				IN_pPassword,//密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pServerIP,// 查询到的服务器的IP地址
				OUT_pPort);// 查询到的服务器的端口

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			if (log.isDebugEnabled())
				log.debug("获取空闲 MO 服务器失败:" + errInfo);
			return Result;
		}
		
		if (log.isDebugEnabled())
			log.debug("连接MO服务器:" + OUT_pServerIP.toString() + ":"
					+ Integer.parseInt(OUT_pPort.toString()));
		//===================================================================
		nConnectID = smep_clnt.SMEP_Connect(OUT_pServerIP.toString(),//服务器IP地址
				Integer.parseInt(OUT_pPort.toString()),//服务器端口
				IN_ProxyType,//代理类型
				IN_pProxyIP,// 代理IP地址
				IN_ProxyPort,// 代理端口
				IN_ProxyAuth,//是否进行用户名密码校验（0-不需要，1-需要)
				IN_pProxyUserID,// 代理用户名
				IN_pProxyPassword,// 代理密码
				IN_ConnectType,// 连接方式：0 MT, 1 MO
				IN_pECode,// 企业短号
				IN_pUserName,// 用户名
				IN_pPassword,// 密码
				IN_pZoneID,// 所属区号
				IN_OverTime,// 超时的时间（秒）
				OUT_pStatus);// 返回状态
		if (nConnectID < 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, Integer.parseInt(OUT_pStatus
					.toString().trim()), 124, errInfo);
			if (log.isDebugEnabled())
				log.debug("连接服务器错误:" + errInfo);
			return Result;
		}

		if (log.isDebugEnabled())
			log.debug("连接成功：句柄(" + nConnectID + ")");
		
		System.out.println("接收MO信息************************");
		StringBuffer OUT_Flag = new StringBuffer();
		StringBuffer OUT_pStat = new StringBuffer();
		StringBuffer OUT_pSrcTerminalID = new StringBuffer();
		StringBuffer OUT_pDestTerminalID = new StringBuffer();
		StringBuffer OUT_MsgID = new StringBuffer();
		StringBuffer OUT_pBuffer = new StringBuffer();

		nRet = smep_clnt.SMEP_GetRereport(nConnectID,// 连接句柄
				IN_OverTime,			// 超时的时间（秒）
				OUT_Flag,				// 成功的话，返回MO的状态：0 点播；1 状态报告；2 链路测试；3 服务器要求断开
				OUT_pStat,				// 成功的话，如果 OUT_Flag==1，返回状态报告的状态：0 成功，其他失败
				OUT_pSrcTerminalID,		// 成功的话，返回源终端号码
				OUT_pDestTerminalID,	// 成功的话，返回目标终端号码
				OUT_MsgID,				// 成功的话，返回MsgID
				OUT_pBuffer);			// 成功的话，返回点播内容

		if (nRet != 0) {
			StringBuffer errInfo = new StringBuffer(125);
			smep_clnt.SMEP_GetErrorInfo(2, nRet, 124, errInfo);
			if (log.isDebugEnabled())
				log.debug("收MO消息错误:" + errInfo);
		} else {
			switch (Integer.parseInt(OUT_Flag.toString())) {
			case 0:
				if (log.isDebugEnabled())
					log.debug("收到点播信息:源终端号码-" + OUT_pSrcTerminalID
							+ "目标终端号码-" + OUT_pDestTerminalID + "MO消息标识-"
							+ OUT_MsgID + "消息内容-" + OUT_pBuffer);
				Result.add(OUT_pSrcTerminalID);
				Result.add(OUT_pDestTerminalID);
				Result.add(OUT_MsgID);
				Result.add(OUT_pBuffer);
				break;
			case 1:
				if (log.isDebugEnabled())
					log.debug("收到状态报告:目标终端号码-" + OUT_pDestTerminalID
							+ "消息标识-" + OUT_MsgID + "状态-" + OUT_pStat);
				break;
			case 2:
				if (log.isDebugEnabled())
					log.debug("收到链路测试!");
				break;
			case 3:
				if (log.isDebugEnabled())
					log.debug("服务器关闭连接");
				break;
			default:
				if (log.isDebugEnabled())
					log.debug("错误MO");
			}
		}
		System.out.println("断开连接*******************");
		smep_clnt.SMEP_Disconnection(nConnectID);
		return Result;
	}
	
	public static void main(String[] args)
	{
		Cmpp2ApiBack api=new Cmpp2ApiBack();
		api.CmppSend();
		//api.CmppSend();
	}
}
