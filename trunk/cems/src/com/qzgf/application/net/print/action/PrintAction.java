package com.qzgf.application.net.print.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.net.print.domain.PrintFacade;
import com.qzgf.comm.PageList;
import com.qzgf.comm.Pages;
import com.qzgf.utils.ListParseToPrintXML;
import com.qzgf.utils.StringParseToBase64;

/**
 * 用户信息
 * 
 * 
 */
@SuppressWarnings("serial")
public class PrintAction extends BaseAction {
	Log log = LogFactory.getLog(PrintAction.class);

	private PrintFacade printFacade;
	private HashMap print = new HashMap(); // 商品信息
	private List printList; // 特色商品信息
	private PageList pageList; // 封装分页信息
	private String xml; // 页面返回
	private String base64str;
	
	// 入口函数
	public String execute() {

		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("USERID", "0");
			userInfo = tmp;
		}
		//初始化基本信息
		search.put("commstaffId", userInfo.get("ID").toString());//创建人
		//具体操作方式
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			//转到框架页面
			return "list";
		}
		if ("listdetail".equals(super.getAction())) {
			//明细列表信息
			Pages pages = new Pages();
			pages.setPage(this.getPage());// 默认第一页
			pages.setPerPageNum(10); // 设置每页大小
			pages.setFileName("print.do?action=listdetail");
			setPageList(printFacade.findPrintPage(search, pages));
			return "listdetail";
		} else if ("edit".equals(super.getAction())) {
			// 2.编辑时取后台数据信息（得到单行记录）
			// 用户过滤,根据采编人员2009-11-24
			// search.put("puserid", userInfo.get("USERID").toString());
			print = (HashMap) printFacade.findPrint(search).get(0); 
			this.setAction("update");
			return "edit";
		} else if ("view".equals(super.getAction())) {
			search=((HashMap) printFacade.findPrint(search).get(0)); 
			return "view";
		} else if ("new".equals(super.getAction())) {
			//默认寄件人信息(客户档案)
			/*
			search.put("staffId", userInfo.getStaffId());
			List ls=emsmailFacade.getUserConfig(search);
			if(ls.size()>0){
				search=(HashMap)ls.get(0);
				//默认交寄人与寄件人一致
				search.put("sendsign", search.get("sendname").toString());
			}
			*/
			//默认年份
			Calendar cal = Calendar.getInstance();
			print.put("RECDATEYEAR", cal.get(Calendar.YEAR));//年
			print.put("RECDATEMONTH", cal.get(Calendar.MONTH)+1);//月
			print.put("RECDATEDAY", cal.get(Calendar.DAY_OF_MONTH));//日
			print.put("RECDATEHOUR", cal.get(Calendar.HOUR_OF_DAY));//时

			//默认用户信息
			print.put("SENDNAME", userInfo.get("NAME").toString());
			print.put("SENDTEL", userInfo.get("TEL").toString()+" "+ userInfo.get("MOBILE").toString());	
			print.put("SENDUNIT", userInfo.get("UNIT").toString());	
			//默认地址信息
			search.put("pcreate_code", userInfo.get("ID").toString());//创建人
			List ls=printFacade.setAddress(search);
			if(ls.size()>0){
				HashMap hs=(HashMap)ls.get(0);	
				print.put("SENDADDRESS",hs.get("PROVINCENAME").toString()+hs.get("CITYNAME").toString()+hs.get("COUNTYNAME").toString()+ hs.get("ADDRESS").toString());	
			}
			//默认数量
			print.put("SENDCOUNT", "1");		
			print.put("SENDSIGN", userInfo.get("NAME").toString());
			
			
			this.setAction("insert");
			return "edit";
		}else if ("insert".equals(super.getAction())) {
			//取得价格区域
			String address=search.get("precaddress").toString();
			//处理地址去除(省市县区).
			Pattern p = Pattern.compile("省|市|县|区|\\s");
			Matcher mat = p.matcher(address.toString());
			address=mat.replaceAll("");
			HashMap hsaddress=printFacade.getFeeArea(address);		
			if(hsaddress!=null){
				search.put("pmail_feearea",hsaddress.get("MAIL_FEEAREA").toString());  //计费区
				search.put("pmail_sendoffice",hsaddress.get("SEND_OFFICE").toString());//发送局
				search.put("pemail_fee",hsaddress.get("EMAIL_FEE").toString());//e邮宝信息
			}
			//用户信息更新,包括管理人员的审核,用户的修改
			int i =printFacade.insertPrint(search);
			if (i > 0) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		}else if ("insertclient".equals(super.getAction())) {
			//取得价格区域
			String address=search.get("precaddress").toString();
			//处理地址去除(省市县区).
			Pattern p = Pattern.compile("省|市|县|区|\\s");
			Matcher mat = p.matcher(address.toString());
			address=mat.replaceAll("");
			HashMap hsaddress=printFacade.getFeeArea(address);		
			if(hsaddress!=null){
				search.put("pmail_feearea",hsaddress.get("MAIL_FEEAREA").toString());  //计费区
				search.put("pmail_sendoffice",hsaddress.get("SEND_OFFICE").toString());//发送局
				search.put("pemail_fee",hsaddress.get("EMAIL_FEE").toString());//e邮宝信息
			}
			int i =printFacade.insertPrint(search);
			HashMap hs =new HashMap();
			//保存收件人信息
			hs.clear();
			hs.put("pbill_sort", "0");
			hs.put("prec_code", search.get("preccode").toString());
			hs.put("prec_name", search.get("precname").toString());
			hs.put("prec_unit", search.get("precunit").toString());
			hs.put("prec_address", search.get("precaddress").toString());
			hs.put("prec_tel", search.get("prectel").toString());
			hs.put("prec_post", search.get("precpost").toString());
			hs.put("pcreate_code", search.get("commstaffId").toString());
			i =i+ printFacade.insertRecMsg(hs);
			if (i > 1) {
				this.addActionMessage(this.getText("增加成功!"));
			} else {
				this.addActionError(this.getText("增加异常!"));
			}
			return "list";
		} //打印主入口(ocx方式)
		else if ("ocxprint".equals(super.getAction())) {
			//查询数据集
			printList = printFacade.findPrintDate(search);
			//更新次数及时间
			printFacade.updatePrintDateEms(search);	
			
			//生成xml文档new String(deliverMsg.getMsgContent(),"UTF-16BE")
			String strocx=ListParseToPrintXML.parseToXML(printList);
			//xml=strocx;
			/*
			FileWriter fw  = new FileWriter("d:\\aa\\test.xml");
			fw.write(strocx);
			fw.close();
			*/
			//strocx=new String(strocx.getBytes(),"UTF-8");
			//strocx=new String(strocx.getBytes(),"GBK");
			//生成base64二进制
			byte[] result = StringParseToBase64.encode(strocx.getBytes());
			//转化为base64字符串
 			base64str=new String(result);

			//默认寄件人信息(客户档案),左边距,上边距
			//search.put("staffId", userInfo.getStaffId());
			//List ls=printFacade.getUserConfig(search);
			//if(ls.size()>0)
			//	search=(HashMap)ls.get(0);
 			search.put("TOP_MARGIN", userInfo.get("TOP_MARGIN").toString());
 			search.put("LEFT_MARGIN", userInfo.get("LEFT_MARGIN").toString());
			//刷新页面
			return "ocxprint";
		}  else if ("update".equals(super.getAction())) {
			//取得价格区域
			String address=search.get("precaddress").toString();
			//处理地址去除(省市县区).
			Pattern p = Pattern.compile("省|市|县|区|\\s");
			Matcher mat = p.matcher(address.toString());
			address=mat.replaceAll("");
			HashMap hsaddress=printFacade.getFeeArea(address);		
			if(hsaddress!=null){
				search.put("pmail_feearea",hsaddress.get("MAIL_FEEAREA").toString());  //计费区
				search.put("pmail_sendoffice",hsaddress.get("SEND_OFFICE").toString());//发送局
				search.put("pemail_fee",hsaddress.get("EMAIL_FEE").toString());//e邮宝信息
			}else{
				search.put("pmail_feearea","");  //计费区
				search.put("pmail_sendoffice","");//发送局
				search.put("pemail_fee","");//e邮宝信息
			}
			//用户信息更新,包括管理人员的审核,用户的修改
			int i = printFacade.updatePrintById(search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
			return "list";
		}  else if ("delete".equals(super.getAction())) {
			// 主表
			int i = printFacade.deletePrintById(search);
			java.lang.StringBuffer sb = new StringBuffer();
			sb.append("<delete>");
			sb.append("<value>").append(i).append("</value>");
			sb.append("</delete>");
			xml = sb.toString();
			return "xml";
		}
		return ERROR;
	}

	
	//用户配置信息
	public String PrintConfig() {
		//具体操作方式
		// 从session中读取用户信息
		HashMap userInfo= (HashMap) (ActionContext.getContext().getSession().get(Def.LOGIN_SESSION_NAME));
		// 当匿名登录时
		if (userInfo == null) {
			HashMap tmp = new HashMap();
			tmp.put("USERID", "0");
			userInfo = tmp;
		}
		search.put("CODE", userInfo.get("CODE").toString());
		if ("".equals(super.getAction()) || null == super.getAction()
				|| "index".equals(super.getAction())) {
			search=userInfo; 
		} else if ("update".equals(super.getAction())) {
			int i=printFacade.updatePrintConfig(search);
			search=printFacade.findSessionUser(search);
			ActionContext.getContext().getSession().put(Def.LOGIN_SESSION_NAME, search);
			if (i > 0) {
				this.addActionMessage(this.getText("用户更新成功!"));
			} else {
				this.addActionError(this.getText("用户更新失败!"));
			}
		}
		return "printConfig";
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
	 * @return the printFacade
	 */
	public PrintFacade getPrintFacade() {
		return printFacade;
	}


	/**
	 * @param printFacade the printFacade to set
	 */
	public void setPrintFacade(PrintFacade printFacade) {
		this.printFacade = printFacade;
	}


	/**
	 * @return the print
	 */
	public HashMap getPrint() {
		return print;
	}


	/**
	 * @param print the print to set
	 */
	public void setPrint(HashMap print) {
		this.print = print;
	}


	/**
	 * @return the printList
	 */
	public List getPrintList() {
		return printList;
	}


	/**
	 * @param printList the printList to set
	 */
	public void setPrintList(List printList) {
		this.printList = printList;
	}


	/**
	 * @return the base64str
	 */
	public String getBase64str() {
		return base64str;
	}


	/**
	 * @param base64str the base64str to set
	 */
	public void setBase64str(String base64str) {
		this.base64str = base64str;
	}

}
