package com.qzgf.application.biz.query.action;



import java.util.ArrayList;
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

import com.qzgf.application.BaseAction;
import com.qzgf.utils.ajax.AjaxMessagesJson;

/**
 * 查询模块
 * @author lsr
 *
 */
@SuppressWarnings("serial")
public class QueryAction extends BaseAction{
	
	private String emsCheckCode;
	private String mailNum;
	private AjaxMessagesJson ajaxMessagesJson;
	
	
	public String execute() {
		try {
			return this.executeMethod(this.getAction());
		} catch (Exception e) {
			e.printStackTrace();
			return "index";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String index() throws Exception {
		//this.setAction("queryEms");
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

<<<<<<< .mine
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        //String responseBody = httpclient.execute(httpget, responseHandler);
        //System.out.println(responseBody);
        
        
       //HttpResponse response = httpclient.execute(httpget);
       //HttpEntity entity = response.getEntity();
=======
		if (sResponse != null && sResponse.trim().length() > 0) {
			Pattern pattern = Pattern.compile(regExData);
			Matcher matcher = pattern.matcher(sResponse);
			try {
				if (matcher.find()) {
					String myEmsbarCode = matcher.group(0).substring(48, 61);
>>>>>>> .r568

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

								httpost.setEntity(new UrlEncodedFormEntity(nvps1, HTTP.UTF_8));

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
									for(TableRow r:tr){
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
	
		httpclient.getConnectionManager().shutdown();
		////////////////////////////////////////////////////
		
		
		return "EMS";
	}
	
	public String fee(){
		//资费查询
		try{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httpost = new HttpPost("http://211.156.193.130/tools/postageresult.jsp");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("reqCode","Postage"));//大小写有差别
			nvps.add(new BasicNameValuePair("addressfrom", "福建"));
			nvps.add(new BasicNameValuePair("addressto", "四川"));
			nvps.add(new BasicNameValuePair("weight", "8"));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity2 = response.getEntity();
			String sResponse = EntityUtils.toString(entity2);
			System.out.println("返回的值是:"+sResponse);
			this.getAjaxMessagesJson().setMessage("0", this.getText("删除取件地址成功!"));
			
		}catch(Exception e){
			this.getAjaxMessagesJson().setMessage("E_ADDRESS_DELFAILED", this.getText("删除取件地址出错!"));
		}
		
		return RESULT_AJAXJSON;
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

	
	
}