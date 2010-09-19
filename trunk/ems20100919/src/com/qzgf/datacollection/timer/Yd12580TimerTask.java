/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :emsex
* Package        :com.qzgf.datacollection.timer
* File	         :Yd12580TimerTask.java
* Written by     :fjfdszj
* Created Date   :Sep 13, 2010
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :Sep 13, 2010
* Purpose        :描述

*/
package com.qzgf.datacollection.timer;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com._59112580.www.Postal;
import com._59112580.www.PostalServiceSoapProxy;
import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.net.communicate.domain.CommunicateFacade;
import com.qzgf.comm.Constant;
import com.qzgf.comm.MD5;


/**
 * Purpose      : 2010年中国移动12580速递邮政接口说明
 *  			: webserivce接口 http://www.59112580.com/PostalService.asmx?WSDL
 * @author fjfdszj
 * @see     Yd12580TimerTask.java
 *
 */
public class Yd12580TimerTask extends TimerTask {
	/**
	 * Purpose      : 说明
	 */

	private Log log = LogFactory.getLog(ImmigrationTimerTask.class);
	private BaseSqlMapDAO baseSqlMapDAO; //mysql数据连接池配置
	//webservice服务
	PostalServiceSoapProxy proxy=new PostalServiceSoapProxy();//连接福建省公安出入境webservice服务
	//基本信息设置(账号，密码，批号)
	String username=Constant.User_Yd12580;
	String passwd=Constant.Pwd_Yd12580;
	String parameter=Constant.Parm_Yd12580; 
	//网上寄件专用用户名
	private String userid=Constant.NetUser_Yd12580;
	private CommunicateFacade communicateFacade;//连接派揽系统发送报告
	//构造类,初始化基本信息
	public Yd12580TimerTask(ServletContext servletContext) {
		//上下文关连
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		//数据库连接
		baseSqlMapDAO = (BaseSqlMapDAO) context.getBean("baseSqlMapDAO");
		//连接派揽系统发送报告
		communicateFacade= (CommunicateFacade) context.getBean("communicateFacade");
		
		if (log.isDebugEnabled())
			log.debug("移动12580采集器已运行");
	}
	
	
	//对于异常信息处理，每隔5分钟处理一次
	@Override
	public void run() {
		//get message
		HashMap	param = new HashMap();
		
		//时间设置
        Date currentTime = new Date();//得到当前系统时间  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        String nowtime = formatter.format(currentTime); //将日期时间格式化  
        SimpleDateFormat formatterex = new SimpleDateFormat("yyyyMMdd");  
        String time = formatterex.format(currentTime); //将日期时间格式化 
        //提示信息
		log.debug(nowtime+":移动12580检测是否每5分钟取一次数据！");
		System.out.println(nowtime+":移动12580检测是否每5分钟取一次数据！");
		
		try {
			//通过webservice取得需要的信息========================
			//组装加密码
			String md5username=username+time;
			String md5passwd=passwd+time;
			MD5 md5 = new MD5();
			md5username=md5.StrToMd5(md5username);
			md5passwd=md5.StrToMd5(md5passwd);
			//=====================================================
			//请求webservice
			
			Postal[] ps= proxy.getList(md5username, md5passwd, parameter);
			String reorderArr="";
			int size=ps.length;
			if(ps!=null&&size>0){
				System.out.println(nowtime+":存在数据移动12580有"+size+"条信息");
				for (int i=0;i<size;i++){
					//生成订单号
					String pidex=baseSqlMapDAO.sequences("t_net_order");
					param.clear();
					param.put("pidex", pidex);
					param.put("pname", ps[i].getRecName());  //取证联系人	
					param.put("punit"," ");  				 //审批机关名称
					
					
					//地址分析
					String paddressex=ps[i].getRecAddress();//福建福州鼓楼区^鼓东路13
					HashMap anlyadd=new HashMap();
					//省
//					anlyadd.put("type", "NAME_PROVINCEID");
//					anlyadd.put("value", ps[i].getProvince());
//					String addcode=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeYd12580",paddress);
					/*
					//市
					anlyadd.clear();
					anlyadd.put("type", "NAME_CITYID");
					anlyadd.put("value", ps[i].getRecCity());
					String pcity=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeYd12580",anlyadd);
					//县
					anlyadd.clear();
					anlyadd.put("type", "NAME_COUNTYID");
					anlyadd.put("value", ps[i].getRecCounty());
					String pcounty=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeYd12580",anlyadd);
					*/
					//ps[i].getRecCity()福州市 ps[i].getRecProvince()福建省 ps[i].getRecCounty()鼓楼区
					String paddress=ps[i].getRecProvince().replace("省", "")+ps[i].getRecCity().replace("市","")+ps[i].getCounty();
					System.out.println("需要分析的地址:"+paddress);
					String addcode=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeImmigration",paddress);
					String[] addcodearr=addcode.split("\\,");//查询对应，省市县。
					
					//寄件人省份固定福建省
					param.put("pprovince","350000");
					param.put("pcity", addcodearr[1]);
					param.put("pcounty", addcodearr[2]);
					param.put("paddress", paddressex);//取证地址,分析详细地址信息
					
					//省
					anlyadd.clear();
					anlyadd.put("type", "NAME_PROVINCEID");
					anlyadd.put("value", ps[i].getProvince());
					String precprovince=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeYd12580",anlyadd);
					//市
					anlyadd.clear();
					anlyadd.put("type", "NAME_CITYID");
					anlyadd.put("value", ps[i].getCity());
					String recpcity=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeYd12580",anlyadd);
			
					//收件人地市
					param.put("precprovince",precprovince);//默认福建省
					param.put("preccity",recpcity);

					param.put("pareacode"," ");
					param.put("ptel", ps[i].getRecTel());//取证电话
					param.put("pmobile", ps[i].getRecTel());
					param.put("ppost","");
					param.put("pmailtype", "0");//0物品型
					//================================================================================
					/**
					 * 品名：，规格：，单价：，代收费
					 */
					String spec=ps[i].getSpec();
					String price=ps[i].getPrice();
					String isfee=ps[i].getIsFee();
					String cnt="";
					if(isfee.equals("0")){
						cnt="规格:"+spec;
					}else{
						cnt="规格:"+spec+",单价:"+price+",代收费";
					}
					param.put("pclientremark",cnt);//客户备注暂时为空串
					
					//=========================================================================================
					param.put("porderingnum","1");
					param.put("porderingweight","0.00");
					

					param.put("pbookingtime", "");//预约时间，暂时设置为空串，2010-06-23
					param.put("porderingtime", nowtime);
					param.put("precevingtime", nowtime);
					//先注册，后填写。testadmin,testadmin
					param.put("pcreate_code", userid);
					param.put("peditor_code", userid);
					param.put("paddressid","0");
					//不同客户随之变化
					param.put("pthirdparty", "移动12580");
					//1.新增加订单
					try{
						baseSqlMapDAO.insert("Order.insertOrder", param);
					}catch(Exception e){
						System.out.println(e.toString());
					}
					//2.提交相应请求信息到派揽系统
					param.clear();
					param.put("pid", pidex);
					
					try{
						HashMap map=communicateFacade.ProRequest("clientadd",param);
						
						param.clear();
						if(map!=null)
						{
							String state=map.get("STATE").toString();
							String remark=map.get("REMARK").toString();
							//修改单据状态
							if(state.equals("2")){
								param.put("orderingstate", "等待收件");//自动下段(已经自动安排揽收人员)
							}else if(state.equals("1")){
								param.put("orderingstate", "等待处理");//手动下段
							}else if(state.equals("0")){
								param.put("orderingstate", "寄件失败");//手动下段
							}
						}else{
							param.put("orderingstate", "等待处理");//手动下段
						}
						//修改订单状态:由派揽系统反馈信息记录，修改单据状态。
						param.put("ORDERID", pidex);
						communicateFacade.modifyBillState(param);
					}catch(Exception ex){
						System.out.println("与派揽通信异常");
					}
					//3.保存采集信息：移动12580提供的数据。
					param.clear();
					reorderArr=reorderArr+ps[i].getOrderID()+",";
					param.put("pno", ps[i].getOrderID());
					param.put("pcontactor", ps[i].getContactor());
					param.put("paddress", paddressex);
					param.put("pphone",ps[i].getPhone());
					param.put("pprice", ps[i].getPrice());
					param.put("pisfee",isfee);
					param.put("pspec", ps[i].getSpec());
					param.put("pprovince", ps[i].getProvince());
					param.put("pcity", ps[i].getCity());
					param.put("pcounty", ps[i].getCounty());
					param.put("ppostcode", ps[i].getPostCode());
					param.put("precprovince",ps[i].getRecProvince());
					param.put("preccity", ps[i].getRecCity());
					param.put("preccounty", ps[i].getCounty());
					param.put("precname", ps[i].getRecName());
					param.put("precaddress", ps[i].getRecAddress());
					param.put("prectel", ps[i].getRecTel());
					//订单号
					param.put("porderid", pidex);
					baseSqlMapDAO.insert("Communicate.insertYd12580", param);
				}//for end
				reorderArr=reorderArr.substring(0,reorderArr.length()-1);
			}//if end
			
			System.out.println(nowtime+" "+reorderArr);
			log.debug(nowtime+" "+reorderArr);
			//提交反馈给移动12580
			System.out.println("移动12850反馈1已经接收"+proxy.postalGetDataBack(reorderArr));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("与移动12580通信异常");
		}
	}
	
	//测试
	public static void main(String[] args) {
		PostalServiceSoapProxy proxy=new PostalServiceSoapProxy();
		

		//时间设置
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");   
        Date currentTime = new Date();//得到当前系统时间   
        String nowtime = formatter.format(currentTime); //将日期时间格式化  
		//请求信息()
		try {
			String username="12580postal";
			String passwd="12580postal";
			String parameter="";//116,-1,178
			
			System.out.println("say no");
			
			//组装
			username=username+nowtime;
			passwd=passwd+nowtime;
			
			MD5 md5 = new MD5();
			username=md5.StrToMd5(username);
			passwd=md5.StrToMd5(passwd);
			
//			try {
//				MessageDigest md;
//				md = MessageDigest.getInstance("MD5");
//				BASE64Encoder base64en = new BASE64Encoder();
////				username=new String(md.digest(username.getBytes("UTF-8")),"UTF-8");
////				passwd=new String(md.digest(passwd.getBytes("UTF-8")),"UTF-8");
//				username=new String(base64en.encode(md.digest(username.getBytes())));
//				passwd=new String(base64en.encode(md.digest(passwd.getBytes())));
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
////			}catch (UnsupportedEncodingException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
//			}

			System.out.println("hello");
			//661B4842E1F2F64FABFE96DE5B1CDBEF
//			username="7D5F89392DDD0DD9A2F76C61C4AD61E2";
//			passwd="7D5F89392DDD0DD9A2F76C61C4AD61E2";
			Postal[] ps= proxy.getList(username, passwd, parameter);//parameter
			
			if(ps!=null){
				for (int i=0;i<ps.length;i++){
					System.out.println("ps[i].getAddress()"+ps[i].getAddress());
					System.out.println("ps[i].getContactor()"+ps[i].getContactor());
					System.out.println("ps[i].getIsFee()"+ps[i].getIsFee());
					System.out.println("ps[i].getOrderID()"+ps[i].getOrderID());
					System.out.println("ps[i].getPhone()"+ps[i].getPhone());
					System.out.println("ps[i].getPrice()"+ps[i].getPrice());
					System.out.println("ps[i].getSpec()"+ps[i].getSpec());
					System.out.println("ps[i].getCity()"+ps[i].getCity());
					System.out.println("ps[i].getPostCode()"+ps[i].getPostCode());
					System.out.println("ps[i].getCounty()"+ps[i].getCounty());
					System.out.println("ps[i].getProvince()"+ps[i].getProvince());
					
					System.out.println("ps[i].getRecAddress()"+ps[i].getRecAddress());
					System.out.println("ps[i].getRecCity()"+ps[i].getRecCity());
					System.out.println("ps[i].getRecTel()"+ps[i].getRecTel());
					System.out.println("ps[i].getRecProvince()"+ps[i].getRecProvince());
					System.out.println("ps[i].getRecCounty()"+ps[i].getRecCounty());
					System.out.println("ps[i].getRecName()"+ps[i].getRecName());
					System.out.println("\r\n\r\n");
				}
			}
///			System.out.println("反馈已经接收"+proxy.postalGetDataBack("1000068,1000081,1000087,1000088,1000089,1000090"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*测试数据
	 * 1000068,1000081,1000087,1000088,1000089,1000090
ps[i].getAddress()厦门市望海路11号楼1706室
ps[i].getContactor()张三
ps[i].getIsFee()0
ps[i].getOrderID()1000033
ps[i].getPhone()13712345678
ps[i].getPrice()
ps[i].getSpec()50cm*30cm*10cm
ps[i].getCity()厦门市
ps[i].getPostCode()361006
ps[i].getCounty()湖里区
ps[i].getProvince()福建省
ps[i].getRecAddress()福州市鼓楼区湖东路168号宏利大厦6层
ps[i].getRecCity()福州市
ps[i].getRecTel()0591-88019098
ps[i].getRecProvince()福建省
ps[i].getRecCounty()鼓楼区
ps[i].getRecName()邦信四海



ps[i].getAddress()福建省福州市鼓楼区湖东路168号
ps[i].getContactor()李四
ps[i].getIsFee()0
ps[i].getOrderID()1000036
ps[i].getPhone()13612345678
ps[i].getPrice()
ps[i].getSpec()50cm*60cm*10cm
ps[i].getCity()福州市
ps[i].getPostCode()350003
ps[i].getCounty()鼓楼区
ps[i].getProvince()福建省
ps[i].getRecAddress()福州市鼓楼区湖东路168号宏利大厦6层
ps[i].getRecCity()福州市
ps[i].getRecTel()0591-88019098
ps[i].getRecProvince()福建省
ps[i].getRecCounty()鼓楼区
ps[i].getRecName()邦信四海
	 */
}
