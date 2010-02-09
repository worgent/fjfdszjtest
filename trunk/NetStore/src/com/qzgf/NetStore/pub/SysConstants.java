package com.qzgf.NetStore.pub;

public class SysConstants
{
	public static final String SESSIONID = "sessionId";

	// 在调用remoteBean的过程中发生的异常(非应用级异常)时,http的异常代码
	public static final int SC_INVOKEBEANERROR = 888;
	
	//银联
	public static final String MerId="808080111701161";
	public static final String CuryId="156";//货币
	public static final String TransType="0001";//交易类型
	public static final String OrderStatus="1001";//消费交易成功代码  
	public static final String Version="20040916";
	public static final String BgRetUrl="/welcome.jsp";
	public static final String PageRetUrl="/welcome.jsp";
	public static final String Priv1="Memo";
}
