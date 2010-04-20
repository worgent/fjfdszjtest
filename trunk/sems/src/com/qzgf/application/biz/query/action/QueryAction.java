package com.qzgf.application.biz.query.action;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;

import com.opensymphony.xwork2.ActionContext;
import com.qzgf.Def;
import com.qzgf.application.BaseAction;
import com.qzgf.application.biz.news.domain.NewsFacade;
import com.qzgf.application.biz.query.domain.QueryFacade;
import com.qzgf.comm.PageList;
import com.qzgf.utils.ajax.AjaxMessagesJson;
import com.qzgf.webutils.ListParseToXML;
 
/**
 * 查询模块
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class QueryAction extends BaseAction {
	
	private String emsCheckCode;
	private String mailNum;
	private AjaxMessagesJson ajaxMessagesJson;
	private String addressfrom;
	private String addressto;
	private String weight;
	private QueryFacade queryFacade;
	private String xml; // 页面返回
	private String verifyCode;
	private String addresstoc;
	private NewsFacade newsFacade;
	private PageList topEightNews;
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}
	
	public String checkcode(){
		try{
		String sessioncode = (String) ActionContext.getContext()
		.getSession().get(Def.MAIL_VERIFY_SESSION_NAME);
		this.getAjaxMessagesJson().setMessage("0", sessioncode);
		}catch(Exception e){
			this.getAjaxMessagesJson().setMessage("ERROR_CODE", "验证码出错！");
		}
		return RESULT_AJAXJSON;
	}
	
	@SuppressWarnings("unchecked")
	public String index() throws Exception {
		//this.setAction("queryEms");

			//验证码正确
			this.setMailNum(this.getMailNum());
			this.setEmsCheckCode(this.emsCheckCode);
			
			////////////////////////////////////////////////////
			DefaultHttpClient httpclient = new DefaultHttpClient();

			//为了取得验证码
			HttpGet httpget0 = new HttpGet(
					"http://10.3.10.83/ems/newsystem/thesecond/ttq/ttqMailquery.jsp");
			HttpResponse response = null;
			HttpEntity entity = null;
			response = httpclient.execute(httpget0);
			entity = response.getEntity();
			String sResponse = EntityUtils.toString(entity);
			String regExData = "<input type=.hidden. name=.myEmsbarCode. value=.[0-9]{13}./>"; //要匹配验证码 <input type="hidden" name="myEmsbarCode" value="5216909469582"/>

			if (sResponse != null && sResponse.trim().length() > 0) {
				Pattern pattern = Pattern.compile(regExData);
				Matcher matcher = pattern.matcher(sResponse);
				try {
					if (matcher.find()) {
						String myEmsbarCode = matcher.group(0).substring(48, 61);

						//取得md5加密值:
						HttpPost httpost = new HttpPost("http://10.3.10.83/ems/qcgzBaseQueryAction.do");

						List<NameValuePair> nvps = new ArrayList<NameValuePair>();
						nvps
								.add(new BasicNameValuePair("mailNum",
										this.getMailNum()));//大小写有差别
						nvps.add(new BasicNameValuePair("reqCode", "regoUrl"));
						nvps.add(new BasicNameValuePair("select", "1"));
						nvps.add(new BasicNameValuePair("myEmsbarCode",
								myEmsbarCode));

						httpost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));

						HttpResponse response1 = httpclient.execute(httpost);
						HttpEntity entity1 = response1.getEntity();

						sResponse = EntityUtils.toString(entity1);
						regExData = "md5Mail=[0-9a-zA-Z]{32}"; //要匹配验证码 <input type="hidden" name="myEmsbarCode" value="5216909469582"/>

						if (sResponse != null && sResponse.trim().length() > 0) {
							pattern = Pattern.compile(regExData);
							matcher = pattern.matcher(sResponse);
							try {
								if (matcher.find()) {

									String md5Mail = matcher.group(0).substring(8);

									//定位到最终页面
									httpost = new HttpPost("http://10.3.10.83/ems/qcgzBaseQueryAction.do");

									List<NameValuePair> nvps1 = new ArrayList<NameValuePair>();
									nvps1.add(new BasicNameValuePair("mailNum",this.mailNum));//大小写有差别
									nvps1.add(new BasicNameValuePair("reqCode","browseBASE"));
									nvps1.add(new BasicNameValuePair("select","1"));
									nvps1.add(new BasicNameValuePair("md5Mail",md5Mail));

									httpost.setEntity(new UrlEncodedFormEntity(nvps1, HTTP.ISO_8859_1));

									HttpResponse response2 = httpclient
											.execute(httpost);
									HttpEntity entity2 = response2.getEntity();
									sResponse = EntityUtils.toString(entity2);
									//已经取得数据的内容了,下面是解析的程序
									Parser parser = new Parser(sResponse);
									NodeFilter filter = new NodeClassFilter(
											TableTag.class);//直接解析某个Table
									NodeList list = parser.extractAllNodesThatMatch(filter);
									search.put("mailNum", mailNum);
									if (list.elementAt(0) instanceof TableTag) { //表一
										TableTag table = (TableTag) list.elementAt(0);
										
										TableRow row = table.getRow(0);
										TableRow row1 = table.getRow(1);
										TableRow row2 = table.getRow(2);
										TableColumn column1 = row.getColumns()[1];
										TableColumn column2 = row.getColumns()[3];
										TableColumn column3 = row1.getColumns()[1];
										TableColumn column4 = row1.getColumns()[3];
										TableColumn column5 = row2.getColumns()[1];
										if(column1.getChildCount()>=1){
										search.put("receiveTime", column1.toPlainTextString().split("&nbsp;")[0]+" "+column1.toPlainTextString().split("&nbsp;")[1]);
										search.put("receiveBureau", column2.toPlainTextString());

										search.put("sendAddress", column3.toPlainTextString());
										search.put("postType", column4.toPlainTextString());
										
										search.put("postKind", column5.toPlainTextString());
										}

									}
									
									if (list.elementAt(1) instanceof TableTag) { //表二
										TableTag table = (TableTag) list.elementAt(1);
										TableRow row = table.getRow(0);
										TableColumn column2 = row.getColumns()[2];
										TableColumn column3 = row.getColumns()[4];
										
										search.put("nowHandleTime", column2.toPlainTextString().split("&nbsp;")[0]+" "+column2.toPlainTextString().split("&nbsp;")[1]);
										search.put("nowHandle", column3.toPlainTextString().trim());
										
									}
									List detailList=new ArrayList();
									if (list.elementAt(2) instanceof TableTag) { //表三
										TableTag table = (TableTag) list.elementAt(2);
										TableRow[] tr=table.getRows();//得到table所有的tr
										
										for(int i=1;i<tr.length;i++){
											TableRow r=(TableRow)tr[i];
											Map map = new HashMap();
											TableColumn [] tc=r.getColumns();//得到每行的列值
											if(tc.length>4){
												map.put("time", tc[0].toPlainTextString());//时间
												map.put("handleAddress", tc[1].toPlainTextString());//处理站点
												map.put("handleAction", tc[2].toPlainTextString());//处理动作
												map.put("handleExplain", tc[3].toPlainTextString());//处理说明
												map.put("remark", tc[4].toPlainTextString());//备注
												
												detailList.add(map);
											
											}
										}
										
										
									}
									search.put("detailList", detailList);
									

								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					} else {
						System.out.println("没有找到匹配数据");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);	
			if(topEightNews.getObjectList().isEmpty())
			{
				topEightNews=null;
			}

			httpclient.getConnectionManager().shutdown();
			////////////////////////////////////////////////////
			
			
			return "EMS";
		
	}
	
	public String fee(){
		//资费查询
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpResponse response = null;
		String addressto1 = null;//寄入
		String addressfrom1 = null;//寄出
		try {
			addressto1 = URLEncoder.encode(this.getAddressto(), "utf-8");
			addressfrom1 = URLEncoder.encode(this.getAddressfrom(), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String url = "http://211.156.193.130/tools/postageresult.jsp?reqCode=Postage&addressfrom="
				+ addressfrom1 + "&addressto=" + addressto1 + "&weight="+this.getWeight();
		HttpPost httpost = new HttpPost(url);

		try {
			response = httpclient.execute(httpost);
			HttpEntity entity2 = response.getEntity();
			String sResponse = EntityUtils.toString(entity2);
			this.getAjaxMessagesJson().setMessage("0", sResponse.trim());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return RESULT_AJAXJSON;
	}
	//国外资费查询
	public String ffee(){
		DefaultHttpClient httpclient = new DefaultHttpClient();
	       String addressto1="";
		try {
			addressto1 = URLEncoder.encode(this.getAddresstoc(), "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	       String url="http://211.156.193.130/tools/postageenZsresult.jsp?reqCode=PostageenZs&goodsType="+(String)search.get("fileType")+"&weight="+(String)search.get("weight")+"&addressto="+addressto1;
	       HttpPost httpost = new HttpPost(url);
	       //HttpPost httpost = new HttpPost("http://www.ems.com.cn/altlOutAction.do?reqCode=altlOutQueryAction");
	        HttpResponse response1;
			try {
				response1 = httpclient.execute(httpost);
				 HttpEntity entity1 = response1.getEntity();
			        String val=EntityUtils.toString(entity1);
			        this.getAjaxMessagesJson().setMessage("0", val.trim());
			} catch (Exception e) {
				e.printStackTrace();
				this.getAjaxMessagesJson().setMessage("0", "发生异常");
			}
	       
	        httpclient.getConnectionManager().shutdown();   
		return RESULT_AJAXJSON;
	}
	
	// 区域信息
	@SuppressWarnings("unchecked")
	public String area() {
		//具体操作方式
		List ls=null;
		if ("provincevalue".equals(super.getAction())) {
			ls=queryFacade.ajaxProvince(search); 
		} else if ("cityvalue".equals(super.getAction())) {
			//查询省市下面的分发局
			ls=queryFacade.ajaxCity(search); 
		} 
		xml=ListParseToXML.parseToXML(ls);
		return "xml";
	}
	
	public String time() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//http://www.ems.com.cn/tools/postageenZsresult.jsp?reqCode=PostageenZs&goodsType
		HttpPost httpost = new HttpPost("http://211.156.193.130/altlOutAction.do?reqCode=altlOutQueryAction");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String rcvCityCode=(String)search.get("rcvCityCode");
		String rcvProvinceCode=(String)search.get("rcvProvinceCode");
		String sendCityCode=(String)search.get("sendCityCode");
		String sendProvinceCode=(String)search.get("sendProvinceCode");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("queryFlag","hazy"));//大小写有差别
		nvps.add(new BasicNameValuePair("rcvCityCode",rcvCityCode));//大小写有差别
		nvps.add(new BasicNameValuePair("rcvProvinceCode",rcvProvinceCode));//大小写有差别
		nvps.add(new BasicNameValuePair("sendDate",sdf.format((new Date()))));//大小写有差别
		nvps.add(new BasicNameValuePair("sendCityCode",sendCityCode));//大小写有差别
		nvps.add(new BasicNameValuePair("sendProvinceCode",sendProvinceCode));//大小写有差别
		

		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
			HttpResponse response2 = httpclient.execute(httpost);
			HttpEntity entity2 = response2.getEntity();
			String sResponse = EntityUtils.toString(entity2);
			
			Pattern pattern = Pattern.compile("您所查询的内容已超出系统范围");
			Matcher matcher = pattern.matcher(sResponse);
			try {
				if (matcher.find()) {
					this.getAjaxMessagesJson().setMessage("0","您所查询的内容已超出系统范围");
					return RESULT_AJAXJSON;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			Parser parser = new Parser(sResponse);   //您所查询的内容已超出系统范围
			
			NodeFilter filter = new NodeClassFilter(TableTag.class);//直接解析某个Table
			NodeList list = parser.extractAllNodesThatMatch(filter);
			if (list.elementAt(5) instanceof TableTag) { 
				TableTag table = (TableTag) list.elementAt(5);
				
				TableRow row = table.getRow(0);
				
				TableColumn column1 = row.getColumns()[0];
				String s="ssf";
				s="您的邮件全程递送日数为:"+column1.toPlainTextString().split("&nbsp;")[0].split("您的邮件全程递送日数为")[1].trim()+"－"+column1.toPlainTextString().split("－")[1].split("&nbsp;")[0].trim()+"（天）  （全程运递时限含收寄日） ";
					
				this.getAjaxMessagesJson().setMessage("0",s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//httpclient.getConnectionManager().shutdown();   
		return RESULT_AJAXJSON;
	}
	
	//国际EMS承诺服务时限标准查询
	@SuppressWarnings("unchecked")
	public String ftime() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost("http://220.194.34.3:7001/GC2/jsp/result/result.jsp");
		 List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	        nvps.add(new BasicNameValuePair("I_AcceptDate", (String)search.get("I_AcceptDate")));//收寄时间
	        nvps.add(new BasicNameValuePair("I_DestinationCountry", (String)search.get("I_DestinationCountry")));//寄达地国家简称
	        nvps.add(new BasicNameValuePair("I_DestinationPostalCode", (String)search.get("I_DestinationPostalCode")));//寄达地邮编
	        nvps.add(new BasicNameValuePair("I_DestinationCountryName", (String)search.get("I_DestinationCountryName")));//寄达地国家全称
	        nvps.add(new BasicNameValuePair("I_OriginCountry", (String)search.get("I_OriginCountry")));//收寄地国家简称
	        nvps.add(new BasicNameValuePair("I_OriginCountryName", (String)search.get("I_OriginCountryName")));//收寄地国家全称
	        nvps.add(new BasicNameValuePair("I_OriginPostalCode", (String)search.get("I_OriginPostalCode")));// 收寄地邮编
	        String val="";
	        try {
				httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
				 HttpResponse response1 = httpclient.execute(httpost);
			        HttpEntity entity1 = response1.getEntity();
			        val=EntityUtils.toString(entity1);
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
			String regExData = "338";
			String regExData1="336";
	        
	        Pattern pattern = Pattern.compile(regExData);//寄达地邮编
	        Pattern pattern1 = Pattern.compile(regExData1);//投递日期出错
			Matcher matcher = pattern.matcher(val);
			Matcher matcher1 = pattern1.matcher(val);
			if (matcher.find()) {
	        	this.addActionError(this.getText("寄达地邮编无效！"));
	        	return "ftime_error";
	        }else if(matcher1.find()){
	        	this.addActionError(this.getText("交寄地邮政编码无效！"));
	        	return "ftime_error";
	        }
			else{
	        String outData=val.split("outData = new Array")[1];
	        //您的邮件最晚于当地时间**投递
	        String O_ServiceGuaranteeDate=outData.split("formatDate")[1].substring(2, 10);
	        //当日收寄邮件截止时间
	        String O_PostOfficeCutOfTime_A_Scan=outData.split("formatTime")[1].substring(2,6);
	        
	        //全程运递时限内包含了*天节假日
	        String O_TotalNumberOfNoneWorkingDaysAndHolidays=outData.split("TotalNumberOfNoneWorkingDaysAndHolidays', '")[1].split("'")[0];
	        
	        //交寄地省份
	        String O_OriginStateOrProvince=outData.split("O_OriginStateOrProvince', '")[1].split("'")[0];
	        
	        //交寄地城市
	        String O_OriginCity=outData.split("O_OriginCity', '")[1].split("'")[0];
	        
	        //寄达地省份
	        String O_DestinationStateOrProvince=outData.split("O_DestinationStateOrProvince', '")[1].split("'")[0];
	       
	        //寄达地城市
	        String DestinationCity=outData.split("O_DestinationCity', '")[1].split("'")[0];
	        
	        //寄达地城市邮编
	        String O_DestinationPostalCode=outData.split("O_DestinationPostalCode', '")[1].split("'")[0];
	        
	        search.put("O_PostOfficeCutOfTime_A_Scan", O_PostOfficeCutOfTime_A_Scan.substring(0,2)+":"+O_PostOfficeCutOfTime_A_Scan.substring(2,4));
	        search.put("O_ServiceGuaranteeDate",O_ServiceGuaranteeDate.substring(0,4)+"-"+O_ServiceGuaranteeDate.substring(4,6)+"-"+O_ServiceGuaranteeDate.substring(6,8));
	        search.put("O_TotalNumberOfNoneWorkingDaysAndHolidays", O_TotalNumberOfNoneWorkingDaysAndHolidays);
	        search.put("O_OriginStateOrProvince", O_OriginStateOrProvince);
	        search.put("O_OriginCity", O_OriginCity);
	        search.put("O_DestinationStateOrProvince", O_DestinationStateOrProvince);
	        search.put("DestinationCity", DestinationCity);
	        search.put("O_DestinationPostalCode", O_DestinationPostalCode);
	        }
			this.topEightNews=this.newsFacade.findTopEightNewsList(this.getSearch(), null);	
			if(topEightNews.getObjectList().isEmpty())
			{
				topEightNews=null;
			}
	        httpclient.getConnectionManager().shutdown();       
		return INPUT;
	}
	
	
	public String getEmsCheckCode() {
		return emsCheckCode;
	}

	public void setEmsCheckCode(String emsCheckCode) {
		this.emsCheckCode = emsCheckCode;
	}

	public String getMailNum() {
		return mailNum;
	}

	public void setMailNum(String mailNum) {
		this.mailNum = mailNum;
	}

	public AjaxMessagesJson getAjaxMessagesJson() {
		return ajaxMessagesJson;
	}

	public void setAjaxMessagesJson(AjaxMessagesJson ajaxMessagesJson) {
		this.ajaxMessagesJson = ajaxMessagesJson;
	}

	public String getAddressfrom() {
		return addressfrom;
	}

	public void setAddressfrom(String addressfrom) {
		this.addressfrom = addressfrom;
	}

	public String getAddressto() {
		return addressto;
	}

	public void setAddressto(String addressto) {
		this.addressto = addressto;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public QueryFacade getQueryFacade() {
		return queryFacade;
	}

	public void setQueryFacade(QueryFacade queryFacade) {
		this.queryFacade = queryFacade;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAddresstoc() {
		return addresstoc;
	}

	public void setAddresstoc(String addresstoc) {
		this.addresstoc = addresstoc;
	}

	public PageList getTopEightNews() {
		return topEightNews;
	}

	public void setTopEightNews(PageList topEightNews) {
		this.topEightNews = topEightNews;
	}

	public NewsFacade getNewsFacade() {
		return newsFacade;
	}

	public void setNewsFacade(NewsFacade newsFacade) {
		this.newsFacade = newsFacade;
	}

	
	
}
