package com.qzgf.application.net.communicate.action;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com._59112580.www.PostalServiceSoapProxy;
import com._59112580.www.RecBack;
import com.qzgf.application.BaseAction;
import com.qzgf.application.net.communicate.domain.CommunicateFacade;
import com.qzgf.application.net.proLog.domain.ProLogFacade;
import com.qzgf.comm.PageList;
import com.startech.els.Back;
import com.startech.els.IPostalPortTypeProxy;

/**
 * 网上下单(与派揽系统之间的通信)
 * 
 * IoC方式

　　要使用IoC方式，我们首先要告诉IoC容器（Container）想取得某个对象的意愿，通过实现相应的接口做到这点

非IoC方式

　　要获得上述对象，关键Struts 2.0中com.opensymphony.xwork2.ActionContext类。我们可以通过它的静态方法getContext()获取当前Action的上下文对象。 另外，org.apache.struts2.ServletActionContext作为辅助类（Helper Class），可以帮助您快捷地获得这几个对象。
 * 
 */
@SuppressWarnings("serial")
public class CommunicateAction extends BaseAction {
	Log log = LogFactory.getLog(CommunicateAction.class);

    private CommunicateFacade communicateFacade;//接收信息的处理 
    private ProLogFacade proLogFacade;          //接收日志的记录
	private HashMap communicate = new HashMap();// 商品信息
	private List communicateList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	
	 
	//webservice服务 
	IPostalPortTypeProxy proxy=new 	IPostalPortTypeProxy();//连接福建省公安出入境webservice服务
	
	//webservice服务 
	PostalServiceSoapProxy proxyyd=new PostalServiceSoapProxy();//移动12580
	
	// 入口函数
	public String execute() { 
		HttpServletRequest request=ServletActionContext.getRequest(); 
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			xml="测试页面";
			return "xml";
		}
		//揽收成功
		if ("success".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "揽收成功");
				//修改单据状态
				HashMap map=new HashMap();
				communicateFacade.addsuccessnum(hs);
				//成功揽收时间
				map.put("precevingtime", "1");
				map.put("pid", hs.get("ORDERID").toString());
				communicateFacade.setOrderById(map);
				//修改状态
				int i = communicateFacade.modifyBillState(hs);
				
				//写日志 
				map.clear();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				
				
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				
				//========2010-06-22==============
				//反馈给 福建省公安出入境网上服务
				try {
					//发送反馈信息(给出入境局)
					//查询信息反馈
					List ls=communicateFacade.findImmigration(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "揽收成功");
						hsext.put("presult", "1");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyImmigration(hsext);
						//取时间和状态
						String barcode=tmp.get("BARCODE").toString();
						Back bk=new Back();
						bk.setBarcode(barcode);//条形码
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setGetdate(nowtime);//Date
						bk.setRemark("揽收成功");//备注
						bk.setResult("1");//返回结果,成功1，默认0
						//加入数组，同时发送给公安出入境局
						Back[] bks=new Back[]{bk};
						proxy.postalBack(bks);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==============================================
				//========2010-09-14==============
				//反馈给 移动12580
				try {
					//发送反馈信息(移动12580)
					//查询信息反馈
					List ls=communicateFacade.findYd12580(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "揽收成功");
						hsext.put("presult", "1");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyYd12580(hsext);
						//取时间和状态
						RecBack bk=new RecBack();
						bk.setOrderID(tmp.get("NO").toString());
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setRecTime(nowtime);//Date
						bk.setRecDesc("揽收成功");//备注
						bk.setResult("1");//返回结果,成功1，默认0
						//加入数组，同时发送给公安出入境局
						RecBack[] backList=new RecBack[]{bk};
						//proxyyd.postalRecBack(backList);
						System.out.println("揽收成功移动12850反馈2单号:"+tmp.get("NO").toString()+"结果"+proxyyd.postalRecBack(backList));
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==============================================
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//客户取消	1
		} else if ("cancel".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "客户取消");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				
				//========2010-06-22==============
				//反馈给 福建省公安出入境网上服务
				try {
					//发送反馈信息(给出入境局)
					//查询信息反馈
					List ls=communicateFacade.findImmigration(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "客户取消");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyImmigration(hsext);
						//取时间和状态
						String barcode=tmp.get("BARCODE").toString();
						Back bk=new Back();
						bk.setBarcode(barcode);//条形码
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setGetdate(nowtime);//Date
						bk.setRemark("客户取消");//备注
						bk.setResult("0");//返回结果,成功1，默认0
						//加入数组，同时发送给公安出入境局
						Back[] bks=new Back[]{bk};
						proxy.postalBack(bks);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//========2010-09-14==============
				//反馈给 移动12580
				try {
					//发送反馈信息(移动12580)
					//查询信息反馈
					List ls=communicateFacade.findYd12580(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "客户取消");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyYd12580(hsext);
						//取时间和状态
						RecBack bk=new RecBack();
						bk.setOrderID(tmp.get("NO").toString());
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setRecTime(nowtime);//Date
						bk.setRecDesc("客户取消");//备注
						bk.setResult("2");//返回结果,成功1，默认0 2失败
						//加入数组，同时发送给公安出入境局
						RecBack[] backList=new RecBack[]{bk};
						System.out.println("客户取消移动12850反馈2单号:"+tmp.get("NO").toString()+"结果"+proxyyd.postalRecBack(backList));
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==============================================
				
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//客户要求延时	
		} else if ("delay".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "客户延时");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//无法联系客户	1
		} else if ("uncontact".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "无法联系客户");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				
				//========2010-06-22==============
				//反馈给 福建省公安出入境网上服务
				try {
					//发送反馈信息(给出入境局)
					//查询信息反馈
					List ls=communicateFacade.findImmigration(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "无法联系客户");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyImmigration(hsext);
						//取时间和状态
						String barcode=tmp.get("BARCODE").toString();
						Back bk=new Back();
						bk.setBarcode(barcode);//条形码
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setGetdate(nowtime);//Date
						bk.setRemark("无法联系客户");//备注
						bk.setResult("0");//返回结果,成功1，默认0 2失败
						//加入数组，同时发送给公安出入境局
						Back[] bks=new Back[]{bk};
						proxy.postalBack(bks);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//========2010-09-14==============
				//反馈给 移动12580
				try {
					//发送反馈信息(移动12580)
					//查询信息反馈
					List ls=communicateFacade.findYd12580(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "无法联系客户");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyYd12580(hsext);
						//取时间和状态
						RecBack bk=new RecBack();
						bk.setOrderID(tmp.get("NO").toString());
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setRecTime(nowtime);//Date
						bk.setRecDesc("无法联系客户");//备注
						bk.setResult("2");//返回结果,成功1，默认0
						//加入数组，同时发送给公安出入境局
						RecBack[] backList=new RecBack[]{bk};
						System.out.println("无法联系客户移动12850反馈2单号:"+tmp.get("NO").toString()+"结果"+proxyyd.postalRecBack(backList));
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==============================================

				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//超范围 1
		} else if ("range".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				
				//========2010-06-22==============
				//反馈给 福建省公安出入境网上服务
				try {
					//发送反馈信息(给出入境局)
					//查询信息反馈
					List ls=communicateFacade.findImmigration(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "超范围");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyImmigration(hsext);
						//取时间和状态
						String barcode=tmp.get("BARCODE").toString();
						Back bk=new Back();
						bk.setBarcode(barcode);//条形码
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setGetdate(nowtime);//Date
						bk.setRemark("超范围");//备注
						bk.setResult("0");//返回结果,成功1，默认0
						//加入数组，同时发送给公安出入境局
						Back[] bks=new Back[]{bk};
						proxy.postalBack(bks);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==============================================
				//========2010-09-14==============
				//反馈给 移动12580
				try {
					//发送反馈信息(移动12580)
					//查询信息反馈
					List ls=communicateFacade.findYd12580(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "超范围");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyYd12580(hsext);
						//取时间和状态
						RecBack bk=new RecBack();
						bk.setOrderID(tmp.get("NO").toString());
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setRecTime(nowtime);//Date
						bk.setRecDesc("超范围");//备注
						bk.setResult("2");//返回结果,成功1，默认0 2失败
						//加入数组，同时发送给公安出入境局
						RecBack[] backList=new RecBack[]{bk};
						System.out.println("超范围移动12850反馈2单号:"+tmp.get("NO").toString()+"结果"+proxyyd.postalRecBack(backList));
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//===================================
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//2010-06-28证件不合规定
		} else if ("notreq".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "证件不合规定");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				
				//========2010-06-22==============
				//反馈给 福建省公安出入境网上服务
				try {
					//发送反馈信息(给出入境局)
					//查询信息反馈
					List ls=communicateFacade.findImmigration(hs);
					if(ls.size()>0){
						//写时间和状态
						HashMap tmp=(HashMap)ls.get(0);
						HashMap hsext=new HashMap();
						hsext.put("pclientremark", "证件不合规定");
						hsext.put("presult", "0");
						hsext.put("ORDERID", hs.get("ORDERID").toString());
						communicateFacade.modifyImmigration(hsext);
						//取时间和状态
						String barcode=tmp.get("BARCODE").toString();
						Back bk=new Back();
						bk.setBarcode(barcode);//条形码
						//反馈给出入境局
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				        Date currentTime = new Date();//得到当前系统时间   
				        String nowtime = formatter.format(currentTime); //将日期时间格式化  
						bk.setGetdate(nowtime);//Date
						bk.setRemark("证件不合规定");//备注
						bk.setResult("0");//返回结果,成功1，默认0
						//加入数组，同时发送给公安出入境局
						Back[] bks=new Back[]{bk};
						proxy.postalBack(bks);
					}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//==============================================

				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}	
		//等待收件 PLORDERID
		} else if ("waitreceive".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "等待收件");
				hs.put("pplorderid", hs.get("PLORDERID").toString());//派揽系统id
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				map.put("pproname", hs.get("DEALMAN").toString());//用户编号
				map.put("pprocontent",hs.get("DEALTIME").toString()+hs.get("REMARK").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		//测试
		}else if ("clientadd".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=new HashMap();
				hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("clientmodify".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("clientcancel".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if ("clienthurry".equals(super.getAction())) {
			try {
				//将请求的信息xml转化为HashMap
				//request.setCharacterEncoding("utf-8");
				HashMap hs=communicateFacade.StreamToHashMap(request.getInputStream());
				hs.put("orderingstate", "超范围");
				//修改单据状态
				int i = communicateFacade.modifyBillState(hs);
				//写日志 
				HashMap map=new HashMap();
				map.put("porderid", hs.get("ORDERID").toString());
				proLogFacade.insertProLog(map);
				//返回
				map.clear();
				map.put("ORDERID", hs.get("ORDERID").toString());
				map.put("STATE", i);
				map.put("REMARK", "");
				xml = communicateFacade.HashMapToXmlStr(map);//解析成xml文档型式
				return "xml";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ERROR;
	}



	/**
	 * @return the pageList
	 */
	public PageList getPageList() {
		return pageList;
	}

	/**
	 * @param pageList the pageList to set
	 */
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	/**
	 * @return the xml
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * @param xml the xml to set
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * @return the communicateFacade
	 */
	public CommunicateFacade getCommunicateFacade() {
		return communicateFacade;
	}



	/**
	 * @param communicateFacade the communicateFacade to set
	 */
	public void setCommunicateFacade(CommunicateFacade communicateFacade) {
		this.communicateFacade = communicateFacade;
	}



	/**
	 * @return the proLogFacade
	 */
	public ProLogFacade getProLogFacade() {
		return proLogFacade;
	}



	/**
	 * @param proLogFacade the proLogFacade to set
	 */
	public void setProLogFacade(ProLogFacade proLogFacade) {
		this.proLogFacade = proLogFacade;
	}



	/**
	 * @return the communicate
	 */
	public HashMap getCommunicate() {
		return communicate;
	}



	/**
	 * @param communicate the communicate to set
	 */
	public void setCommunicate(HashMap communicate) {
		this.communicate = communicate;
	}



	/**
	 * @return the communicateList
	 */
	public List getCommunicateList() {
		return communicateList;
	}



	/**
	 * @param communicateList the communicateList to set
	 */
	public void setCommunicateList(List communicateList) {
		this.communicateList = communicateList;
	}
}
