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

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;
import com.qzgf.application.net.communicate.domain.CommunicateFacade;
import com.qzgf.comm.Constant;
import com.startech.els.IPostalPortTypeProxy;
import com.startech.els.Postal;

/**
 * Purpose      : 福建省公安出入境webservice服务
 *              : http://218.85.72.214:9080/PostalService/postalService.ws?wsdl
 *              
 *
 * @author fjfdszj
 * @see     ImmigrationTimerTask.java
 *
 */
public class ImmigrationTimerTask extends TimerTask {
	private Log log = LogFactory.getLog(ImmigrationTimerTask.class);
	private BaseSqlMapDAO baseSqlMapDAO; //mysql数据连接池配置
	//webservice服务
	IPostalPortTypeProxy proxy=new 	IPostalPortTypeProxy();//连接福建省公安出入境webservice服务
	//基本信息设置(账号，密码，批号)
	String username=Constant.User_Immigration;
	String passwd=Constant.Pwd_Immigration;
	String parameter=Constant.Parm_Immigration; //测试使用116,正式环境使用-1
	//网上寄件专用用户名
	private String userid=Constant.NetUser_Immigration;
	private CommunicateFacade communicateFacade;//连接派揽系统发送报告
	
	//构造类初始化基础信息
	public ImmigrationTimerTask(ServletContext servletContext) {
		//上下文关连
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		//数据库连接
		baseSqlMapDAO = (BaseSqlMapDAO) context.getBean("baseSqlMapDAO");
		//连接派揽系统发送报告
		communicateFacade= (CommunicateFacade) context.getBean("communicateFacade");
		
		if (log.isDebugEnabled())
			log.debug("福建省公安出入境采集器已运行");
	}
	
	//对于异常信息处理，每隔5分钟处理一次
	@Override
	public void run() {
		//get message
		HashMap	param = new HashMap();
		
		//时间设置
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
        Date currentTime = new Date();//得到当前系统时间   
        String nowtime = formatter.format(currentTime); //将日期时间格式化  
        
		log.debug(nowtime+":福建省公安出入境检测是否每5分钟取一次数据！");
		System.out.println(nowtime+":福建省公安出入境检测是否每5分钟取一次数据！");
		
		try {
			//通过webservice取得需要的信息
			Postal[] ps= proxy.getList(username, passwd, parameter);
			
			if(ps!=null){
				for (int i=0;i<ps.length;i++){
					//生成订单号
					String pidex=baseSqlMapDAO.sequences("t_net_order");
					param.clear();
					param.put("pidex", pidex);
					param.put("pname", ps[i].getSemscontactor());//取证联系人	
					param.put("punit"," ");  					 //审批机关名称
					
					//分析ps[i].getSaddress()取证地址 //getExaminearea()审批机关名称
					String paddress=ps[i].getSaddress();//福建福州鼓楼区^鼓东路13
					String[] paddressarr=paddress.split("\\^");//paddressarr[0]为对照表，paddressarr[1]为详细地址
					paddress=paddressarr[0];//前缀到对照表
					String addcode=(String)baseSqlMapDAO.queryForObject("Communicate.gerCodeImmigration",paddress);
					String[] addcodearr=addcode.split("\\,");//查询对应，省市县。
					//省份固定福建省
					param.put("pprovince","350000");
					param.put("pcity", addcodearr[1]);
					param.put("pcounty", addcodearr[2]);
					param.put("paddress", paddressarr[1]);//取证地址,分析详细地址信息
					//收件人地市
					param.put("precprovince","350000");//默认福建省
					param.put("preccity",addcodearr[1]);
					
					
					param.put("pareacode"," ");
					param.put("ptel", ps[i].getSphone());//取证电话
					param.put("pmobile", ps[i].getSphone());
					param.put("ppost", "");
					param.put("pmailtype", "1");//1文件型
					//================================================================================
					/**
					 * 你判断一下如果getcbtype1,getcbtype2内容不为空，组成下面的内容
					   “签证:getcbtype1,getmoney1元;getmoney2,getmoney2元”
					   最新格式：2010-07-02
					   签证：passno，cbtype1money1元，cbtype2money2元；审批：examinearea；
					   最新格式：2010-07-06
					   examinearea取市级信息
					   paddressex,返程信息取前两个字
					 */
					String ppassno=ps[i].getPassno();
					String pexaminearea=ps[i].getExaminearea();
					String paddressex=ps[i].getPaddress();
					
					String pmoney1= ps[i].getMoney1();
					String pcbtype1= ps[i].getCbtype1();
					String pmoney2= ps[i].getMoney2();
					String pcbtype2= ps[i].getCbtype2();
					String cnt="签证："+ppassno+"，";
					if(!pcbtype1.equals("")){
						cnt=cnt+pcbtype1+pmoney1+"元";
					}
					if(!pcbtype2.equals("")){
						if(!pcbtype1.equals(""))
							cnt=cnt+"，"+pcbtype2+pmoney2+"元";
						else
							cnt=cnt+pcbtype2+pmoney2+"元";
					}
					cnt=cnt+"；审批："+pexaminearea.substring(3,5)+"；";
					
					if(!paddressex.equals("")){
						cnt=cnt+"返程："+paddressex.substring(0, 2)+"；";
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
					param.put("peditor_code",userid);
					param.put("paddressid","0");
					//不同客户随之变化
					param.put("pthirdparty", "公安局出入境管理处");
					//1.新增加订单
					try{
						baseSqlMapDAO.insert("Order.insertOrder", param);
					}catch(Exception e){
						System.out.println(e.toString());
					}
					//2.提交相应请求信息到派揽系统
					param.clear();
					param.put("pid", pidex);
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
					//3.保存采集信息：福建出入境管理提供数据。
					param.clear();
					param.put("pbarcode", ps[i].getBarcode());
					param.put("pcbtype1", pcbtype1);
					param.put("pcbtype2", pcbtype2);
					param.put("pexaminearea",pexaminearea);
					param.put("pmoney1", pmoney1);
					param.put("pmoney2", pmoney2);
					param.put("ppassno",ppassno);
					param.put("psaddress", ps[i].getSaddress());
					param.put("psemscontactor", ps[i].getSemscontactor());
					param.put("psphone", ps[i].getSphone());
					param.put("ptblno", ps[i].getTblno());
					//2010-07-02增加字段:返程地址信息
					param.put("paddress", paddressex);
					//订单号
					param.put("porderid", pidex);
					baseSqlMapDAO.insert("Communicate.insertImmigration", param);
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	//测试
	public static void main(String[] args) {
		IPostalPortTypeProxy proxy=new 	IPostalPortTypeProxy();
		
		//请求信息()
		try {
			String username="emsusername";
			String passwd="emspassword";
			String parameter="0";//116,-1,178
			
			/*
			String addcode="  ,  , ";
			String[] addcodearr=addcode.split("\\,");//查询对应，省市县。
			
			String test="123456";
		    test=test.substring(1,3);//结果2,3
			*/

			
			if("   ".equals("")){
				System.out.println("say no");
			}
			
			
			Postal[] ps= proxy.getList(username, passwd, parameter);
			
			if(ps!=null){
				for (int i=0;i<ps.length;i++){
				
					System.out.println("ps[i].getBarcode()"+ps[i].getBarcode());
					System.out.println("ps[i].getCbtype1()"+ps[i].getCbtype1());
					System.out.println("ps[i].getCbtype2()"+ps[i].getCbtype2());
					System.out.println("ps[i].getExaminearea()"+ps[i].getExaminearea());
					System.out.println("ps[i].getMoney1()"+ps[i].getMoney1());
					System.out.println("ps[i].getMoney2()"+ps[i].getMoney2());
					System.out.println("ps[i].getPassno()"+ps[i].getPassno());
					System.out.println("ps[i].getSaddress()"+ps[i].getSaddress());
					System.out.println("ps[i].getSemscontactor()"+ps[i].getSemscontactor());
					System.out.println("ps[i].getSphone()"+ps[i].getSphone());
					System.out.println("ps[i].getTblno()"+ps[i].getTblno());
					System.out.println("ps[i].getTblno()"+ps[i].getPaddress());
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	/* 
	ps[i].getBarcode()3A00002979
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()40
	ps[i].getMoney2()0
	ps[i].getPassno()W00000200
	ps[i].getSaddress()取证地址1
	ps[i].getSemscontactor()联系人1
	ps[i].getSphone()12345678901
	ps[i].getTblno()1

	ps[i].getBarcode()9A00002981
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W80000007
	ps[i].getSaddress()add11111111
	ps[i].getSemscontactor()111111111
	ps[i].getSphone()111111111111
	ps[i].getTblno()1

	ps[i].getBarcode()7A00002983
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()澳门
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()40
	ps[i].getMoney2()20
	ps[i].getPassno()W80000007
	ps[i].getSaddress()add1111111111
	ps[i].getSemscontactor()11111
	ps[i].getSphone()11111111111
	ps[i].getTblno()1

	ps[i].getBarcode()6A00002984
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W80000007
	ps[i].getSaddress()测试测试题 
	ps[i].getSemscontactor()测试
	ps[i].getSphone()13599030606
	ps[i].getTblno()1

	ps[i].getBarcode()5A00002985
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W80000007
	ps[i].getSaddress()test1
	ps[i].getSemscontactor()test1
	ps[i].getSphone()13599030606
	ps[i].getTblno()1

	ps[i].getBarcode()4A00002986
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()澳门
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()20
	ps[i].getPassno()W80000007
	ps[i].getSaddress()铜盘路11111
	ps[i].getSemscontactor()11111
	ps[i].getSphone()13599030606
	ps[i].getTblno()1

	ps[i].getBarcode()5A00002977
	ps[i].getCbtype1()
	ps[i].getCbtype2()澳门
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()0
	ps[i].getMoney2()20
	ps[i].getPassno()W80000006
	ps[i].getSaddress()add11111111111
	ps[i].getSemscontactor()11111111111
	ps[i].getSphone()11111111111
	ps[i].getTblno()1

	ps[i].getBarcode()0000000034
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省南平市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W00000385
	ps[i].getSaddress()取证地址1
	ps[i].getSemscontactor()联系人
	ps[i].getSphone()059586211021
	ps[i].getTblno()1
			
			
			ps[i].getBarcode()3A00002979
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()40
	ps[i].getMoney2()0
	ps[i].getPassno()W00000200
	ps[i].getSaddress()取证地址1
	ps[i].getSemscontactor()联系人1
	ps[i].getSphone()12345678901
	ps[i].getTblno()116
	ps[i].getBarcode()9A00002981
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W80000007
	ps[i].getSaddress()add11111111
	ps[i].getSemscontactor()111111111
	ps[i].getSphone()111111111111
	ps[i].getTblno()116
	ps[i].getBarcode()7A00002983
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()澳门
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()40
	ps[i].getMoney2()20
	ps[i].getPassno()W80000007
	ps[i].getSaddress()add1111111111
	ps[i].getSemscontactor()11111
	ps[i].getSphone()11111111111
	ps[i].getTblno()116
	ps[i].getBarcode()6A00002984
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W80000007
	ps[i].getSaddress()测试测试题 
	ps[i].getSemscontactor()测试
	ps[i].getSphone()13599030606
	ps[i].getTblno()116
	ps[i].getBarcode()5A00002985
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W80000007
	ps[i].getSaddress()test1
	ps[i].getSemscontactor()test1
	ps[i].getSphone()13599030606
	ps[i].getTblno()116
	ps[i].getBarcode()4A00002986
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()澳门
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()20
	ps[i].getPassno()W80000007
	ps[i].getSaddress()铜盘路11111
	ps[i].getSemscontactor()11111
	ps[i].getSphone()13599030606
	ps[i].getTblno()116
	ps[i].getBarcode()5A00002977
	ps[i].getCbtype1()
	ps[i].getCbtype2()澳门
	ps[i].getExaminearea()福建省漳州市公安局出入境管理处
	ps[i].getMoney1()0
	ps[i].getMoney2()20
	ps[i].getPassno()W80000006
	ps[i].getSaddress()add11111111111
	ps[i].getSemscontactor()11111111111
	ps[i].getSphone()11111111111
	ps[i].getTblno()116
	ps[i].getBarcode()0000000034
	ps[i].getCbtype1()香港
	ps[i].getCbtype2()
	ps[i].getExaminearea()福建省南平市公安局出入境管理处
	ps[i].getMoney1()20
	ps[i].getMoney2()0
	ps[i].getPassno()W00000385
	ps[i].getSaddress()取证地址1
	ps[i].getSemscontactor()联系人
	ps[i].getSphone()059586211021
	ps[i].getTblno()116
	测试使用116
	*/
			
}
