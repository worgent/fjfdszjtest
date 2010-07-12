package com.chinasms.JavaHttpSample;

import java.io.*;
import java.net.*;

public class Test
{
	public static void main(String[] args)
	{
	   String str;  //存放调用返回的结果,判断成功与否应该分析这个字符串
	   ChinaSmsTest sms = new ChinaSmsTest("jux3","888888");  //这里修改成你自己的用户名和密码
	   str = sms.singleSend("13657912707","测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送测试单条发送");  //这里修改成要发送的手机号码和发送内容
	   String newstring = null;
	   try
	   {
		   newstring = new String(str.getBytes("GBK"),"GB2312");
		   System.out.println(newstring);
	   }catch(Exception ex){
	      System.out.println("异常"); 
	   }	   
	}
}
/*
类名：ChinaSms
说明：简单封装中国短信接口规范v1.2
更新历史：
   1.2005-01-14 创建,ying_yu 
*/
class ChinaSmsTest
{
	private String comName;  //企业用户登陆名
	private String comPwd;   //企业用户登陆密码

    public ChinaSmsTest()
	{
      this("default","default");  
	}

	public ChinaSmsTest(String name, String pwd)
	{
		this.comName = name;
		this.comPwd = pwd;
	}

	/*群发接口*/
	public String singleSend(String dst, String msg)
	{
		String sUrl = null;
		sUrl = "http://www.china-sms.com//send/gsend.asp?name="
		              +comName+"&pwd="+comPwd+"&dst="+dst+"&txt=ccdx&msg="+msg;
		System.out.println(sUrl);
	    return getUrl(sUrl);
	}

	/*通用调用接口*/
	public String getUrl(String urlString)
	{
		StringBuffer sb = new StringBuffer();

		try{
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			reader.close();
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
        //System.out.println(sb.toString());
		return sb.toString();
	}
}
