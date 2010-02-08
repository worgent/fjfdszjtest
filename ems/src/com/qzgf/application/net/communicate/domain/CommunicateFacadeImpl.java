package com.qzgf.application.net.communicate.domain;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qzgf.IbatisDaoTools.BaseSqlMapDAO;

/**
 * 网上下单(与派揽系统之间的通信)
 *
 */
public class CommunicateFacadeImpl implements CommunicateFacade {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(CommunicateFacadeImpl.class);
	BaseSqlMapDAO baseSqlMapDAO;
	//本地测试
	//private String url="http://127.0.0.1:8080/net/communicate.do";//请求的url地址
	//服务器测试
	//private String url="http://127.0.0.1/net/communicate.do";//请求的url地址
	//派揽系统
	private String url="http://172.30.194.22/netorder.aspx";//请求的url地址
	
	/******************************************系统主动请求处理********************************/
	/**
	 * 将数据转化为二进制形式
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public byte[] XmlToByte(Object data) throws IOException {
		byte[] result=null;
		ObjectOutputStream OOS=null; 
		ByteArrayOutputStream BAOS=new ByteArrayOutputStream();
		OOS=new ObjectOutputStream(BAOS);
		OOS.writeObject(data);
		OOS.close();
		result=BAOS.toByteArray();
        BAOS.close();
		return result;
	}
	
	
	/**
	 * 将数据转化为二进制形式
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public Object ByteToXml(byte[] data) throws IOException {
		Object result=null;
		ByteArrayInputStream BAIS=null; 
		ObjectInputStream OIS=null;
		BAIS=new ByteArrayInputStream(data);
		OIS=new ObjectInputStream(BAIS);
		try {
			result=(Object)OIS.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		BAIS.close();
		OIS.close();
		return result;
	}
	
	/**
	 * 将数据转化为二进制形式
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public Object StreamToXml(InputStream data) throws IOException {
		Object result=null;
		ObjectInputStream OIS=null;
		OIS=new ObjectInputStream(data);
		try {
			result=(Object)OIS.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//data.close();
		OIS.close();
		return result;
	}
	
	/**
	 * 不同的请求处理
	 * @param per
	 * @param ls
	 * @return
	 */
	public HashMap ProRequest(String per,HashMap map){
		HashMap result=new HashMap();
		String xml="";
		//switch仅整型或枚举允许
		//查询相关信息发送给派揽系统
		if(per.equalsIgnoreCase("clientadd"))
		{
			//客户新增加,根据单号取得相关信息
			List ls=baseSqlMapDAO.queryForList("Communicate.clientadd", map);
			xml=ListToXmlStr(ls);//转化为xml字符串
		}/*
		else if(per.equalsIgnoreCase("clientmodify"))
		{
			//客户修改
			//ls=baseSqlMapDAO.queryForList("Communicate.clientmodify", map);
			hs.put("ORDERID",map.get("pid"));
			hs.put("CLIENTREMARK",map.get("pclientremark"));
			xml=HashMapToXmlStr(hs);//转化为xml字符串
		}else if(per.equalsIgnoreCase("clientcancel"))
		{
			//客户撤单
			//ls=baseSqlMapDAO.queryForList("Communicate.clientcancel", map);
			hs.put("ORDERID",map.get("pid"));
			hs.put("CLIENTREMARK",map.get("pclientremark"));
		}else if(per.equalsIgnoreCase("clienthurry"))
		{
			//客户催揽
			//ls=baseSqlMapDAO.queryForList("Communicate.clienthurry", map);
			hs.put("ORDERID",map.get("pid"));
			hs.put("CLIENTREMARK",map.get("pclientremark"));
		}else if(per.equalsIgnoreCase("test"))
		{
			//测试
			ls=baseSqlMapDAO.queryForList("Communicate.clienthurry", map);
		}*/
		else
		{
			HashMap hs=new HashMap();
			hs.put("ORDERID",map.get("pid"));
			hs.put("CLIENTREMARK",map.get("pclientremark"));
			xml=HashMapToXmlStr(hs);//转化为xml字符串
		}
		try {
			if(!xml.equals(""))
			{
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				//logger.info("系统请求的xml文件("+sdf.format((new Date()))+")"+xml+"\n");
				//对xml进行utf-8编码
				result=SendCom(url,"action="+per,xml.getBytes("utf-8"));//发送请求信息,返回xml文档转化为HashMap
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result=null;
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 得到返回的内容,请求URL取得返馈的信息
	 * 参数说明:urlStr:传递的url地址
	 *          per:参数内容前缀,如action="add"
	 *          stream:压缩的二进制流文件
	 * 返回值  :反馈的二进行信息         
	 */
	public HashMap SendCom(String urlStr, String per,byte[] stream) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			//组装包括前缀的url型式传递
			urlStr=urlStr+"?"+per;
			//设置将要取值的url地址相关参数
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//增加编码方式
			connection.setRequestProperty("content-type", "text/xml");
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();
			//写入传入参数post
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			if(stream!=null)
				out.write(stream);//传递二进制流
			//传递字节流信息
			out.flush();
			out.close();
			//装返馈信息xml文件信息转化为HashMap存储
			return StreamToHashMap(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	
	/******************************************用户请求处理********************************/
	/**
	 * 2010-01-05
	 * 更新订单单据状态
	 */
	public int modifyBillState(HashMap map) {
		return baseSqlMapDAO.update("Communicate.modifyBillState", map);
	}	
	
	
	/**
	 * 将用户请求的数据流转化为HashMap型式存储
	 */
	public HashMap StreamToHashMap(InputStream io)
	{
		String getstr="";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(io,Charset.forName("utf-8")));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine())!=null){
                sb.append(line);
            }
            getstr=sb.toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			logger.info("用户请求的流信息或系统请求返馈的流信息("+sdf.format((new Date()))+")"+getstr+"\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HashMap hs=new HashMap();
		if(io!=null)
		{
			try {
				SAXReader saxReader = new SAXReader();
				saxReader.setEncoding("utf-8");
				byte[] bytes = getstr.getBytes("utf-8"); 
				InputStream in = new ByteArrayInputStream(bytes); 
				InputStreamReader strInStream = new InputStreamReader(in, "utf-8"); 
				Document   document =saxReader.read(strInStream);
				Element root=document.getRootElement();//得到根元素orders
				Element order=root.element("ORDER");  //得到订单元素
				Iterator iter=order.elementIterator();//叠代得到子元素
		        while(iter.hasNext()){
		              Element ownerElement = (Element)iter.next();
		              String name=ownerElement.getName();
		              String text=ownerElement.getText();
		              hs.put(name, text);
		           }
		        //关闭流
		        in.close();
				io.close();
			} catch (Exception e) {
				hs=null;
				e.printStackTrace();
			}
		}
		return hs;
	}
	
	
	/**
	 * 将用户请求处理完成的结果,HashMap的值以xml文档的型式返馈给用户
	 */
	public String HashMapToXmlStr(HashMap map)
	{
		String result="";
		try {
			Document document = DocumentHelper.createDocument();
			Element ordersElement = document.addElement("ORDERS");
			Element orderElement =ordersElement.addElement("ORDER");
			Iterator it = map.keySet().iterator();//叠代HashMap中元素信息
			while(it.hasNext()){
				String key = ((String)it.next()).toUpperCase();
				String value=map.get(key).toString().trim();//去空格.replace(" ","")
				Element tmp=orderElement.addElement(key);
				tmp.addText(value);
			}
			result=document.asXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result="";
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 将用户请求处理完成的结果,HashMap的值以xml文档的型式返馈给用户
	 */
	public String ListToXmlStr(List ls)
	{
		String result="";
		try {
			Document document = DocumentHelper.createDocument();
			Element ordersElement = document.addElement("ORDERS");
			Element tmp=null;
			for(int i=0 ; i<ls.size(); i++){
				Element orderElement =ordersElement.addElement("ORDER");
				HashMap map = (HashMap) ls.get(i);
				Iterator it = map.keySet().iterator();
				while(it.hasNext()){
					String key = ((String)it.next()).toUpperCase();
					String value="";
					if(map.get(key)!=null)
					{
							value=map.get(key).toString().trim();//去空格.replace(" ","")
					}
					tmp=orderElement.addElement(key);
					tmp.addText(value);
				}
			}
			result=document.asXML();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result=null;
			e.printStackTrace();
		}
		return result;
	}
	
	public BaseSqlMapDAO getBaseSqlMapDAO() {
		return baseSqlMapDAO;
	}

	public void setBaseSqlMapDAO(BaseSqlMapDAO baseSqlMapDAO) {
		this.baseSqlMapDAO = baseSqlMapDAO;
	}
}
