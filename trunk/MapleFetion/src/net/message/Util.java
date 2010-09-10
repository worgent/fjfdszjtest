package net.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	/*
	 * 得到返回的内容
	 */
	public static String getResult(String urlStr, String content) {
		URL url = null;
		HttpURLConnection connection = null;

		try {
			//设置将要取值的url地址相关参数
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			//connection.setRequestMethod("POST");
			//connection.setRequestMethod("GET");  GET POST HEAD OPTIONS PUT DELETE TRACE 
			connection.setRequestMethod("GET");
			
			connection.setUseCaches(false);
			connection.connect();
			
			
			//写入传入参数post
			/*
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(content);
			out.flush();
			out.close();
			*/
			
			//得到传出的数据,反馈信息
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection
					.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	
	public static String dispatch(String line)
	{
		String result="";
		String[] cmd = line.split(" ");
		String ip="http://travel.mipang.com/weather/17353";
		String sid="mod_weather";
		
		try{
				//天气
				if(cmd[0].equals("weather")){
					 ip="http://travel.mipang.com/weather/17353";
					 sid="mod_weather";
					 result=getResult(ip,"");
					 result=result.substring(result.indexOf(sid)+30,result.indexOf(sid)+1905);
		
				}else if(cmd[0].equals("weatherex")){
					 ip="http://www.weather.com.cn/html/trip_fc/101010300.shtml";
					 sid="mod_weather";
					 result=getResult(ip,"");
					 //<div class=\"forecastBox\" id=\"forecastID\">(全部)   <div class="weatherYubao" id="weatherYubao2">(3天)
					 result="太姥山天气"+result.substring(result.lastIndexOf("<div class=\"box_contentl\">"),result.indexOf("<body></html>"));
				}else if(cmd[0].equals("weather1")){
					 ip="http://travel.mipang.com/weather/17353";
					 sid="mod_weather";
					 result=getResult(ip,"");
					 //<div class=\"forecastBox\" id=\"forecastID\">(全部)   <div class="weatherYubao" id="weatherYubao2">(3天)
					 result="太姥山天气"+result.substring(result.lastIndexOf("<!--day 2-->"),result.indexOf("<div class=\"forecastBox\" id=\"forecastID\">"));
		
				}else if(cmd[0].equals("weather2")){
					 ip="http://travel.mipang.com/weather/2609";
					 sid="mod_weather";
					 result=getResult(ip,"");
					 result="泉州天气"+result.substring(result.lastIndexOf("<!--day 2-->"),result.indexOf("<div class=\"forecastBox\" id=\"forecastID\">"));
			    // IP查询
				}else if(cmd[0].equals("ip")){
					 ip="http://util.org.ru/ipseeker?ip="+line.substring(line.indexOf(cmd[1]));
					 result=getResult(ip,"");
				//新闻	 
				}else if(cmd[0].equals("news")){
					 ip="http://www.google.cn/m/news/";
					 //ip="http://www.google.com.hk/m/news";
					 result=getResult(ip,"");
					 result="google新闻"+result.substring(0,result.lastIndexOf("所有焦点"));
				}else if(cmd[0].equals("newsex")){
					 ip="http://kong.net/home_html.jsp";//http://kong.net/home_html.jsp 
					 //http://kong.net/news/newsroll.jsp?k_id=2344621
					 result=getResult(ip,"");
					 result=result.substring(result.indexOf("<div id=\"content\" class=\"listc\">"),result.indexOf("<div id=\"news\">"));
				//手机归属地
				}else if(cmd[0].equals("mobile")){
					 ip="http://util.org.ru/mobile?mobile="+line.substring(line.indexOf(cmd[1]));
					 result=getResult(ip,""); 
				//电话费查询	 
				}else if(cmd[0].equals("telfee")){
					 ip="http://www.fj.ct10000.com/view/selfquery/queryinfo/custbill.pvm";
					 result=getResult(ip,""); 
				}//电费查询
				else if(cmd[0].equals("elefee")){
					 ip="http://www.95598.fj.sgcc.com.cn/netbusiness/usradmin/getPowerFee.do?branchCode=35403&funcId=5";
					 result=getResult(ip,""); 
				}else if(cmd[0].equals("baidunew")){
					 ip="http://wap.baidu.com/news?tn=bdwcn&word=todaynews&pn=0&ssid=0&from=0&bd_page_type=1&uid=frontui_1267709930_1452&pu=pd@4,sz@176_208,uc@0";
					 result=getResult(ip,""); 
					 result="百度WAP"+result.substring(result.indexOf("【今日焦点】"),result.indexOf("【分类推荐】"));
				}else if(cmd[0].equals("wapsohu")){
					 ip="http://wap.sohu.com/news/?nid=3237";
					 result=getResult(ip,""); 
					 result="搜狐WAP"+result.substring(result.indexOf("【焦点新闻】"),result.indexOf("【社会要闻】"));
				//动车查询	 
				}else if(cmd[0].equals("DTrain")){
					ip="http://open.baidu.com/train/search.php?wd=%CA%AE%D1%DF%D6%C1%CE%E4%BA%BA&act=train&s3="+line.substring(line.indexOf(cmd[1]));
					//ip="http://open.baidu.com/train/search.php?wd=%CA%AE%D1%DF%D6%C1%CE%E4%BA%BA&act=station&s1=%B8%A3%D6%DD&s2=%C9%CF%BA%A3";
					result=getResult(ip,"");
				}else if(cmd[0].equals("Train")){
					ip="http://lieche.58.com/checi/"+line.substring(line.indexOf(cmd[1]))+"/";
					//ip="http://lieche.58.com/fjquanzhou-fuding/";
					result=getResult(ip,"");
				}
		}catch(Exception e){
			result="采集信息异常";
		}
		
		//处理html文件
		 Pattern p = Pattern.compile("<(.|\n)+?>|&nbsp;");
		 Matcher mat = p.matcher(result.toString());
		 result=mat.replaceAll(" ");
		 
		  p = Pattern.compile("\\s+");
		  mat = p.matcher(result.toString());
		 result=mat.replaceAll(",");
		 
		 //去除回车,转行
		 p = Pattern.compile("\t|\r|\n");
		 mat = p.matcher(result.toString());
		 result=mat.replaceAll("");
		 
		 return result;
	}
	
	
	public static void main(String[] args) {
		String test="Hello&大家";
		try {
			//System.out.println(getResult("http://www.fj.ct10000.com/view/selfquery/queryinfo/custbill.pvm?ck_month=201001&PRODTYPE=1&radnum=7263652#1#秦川路76号",""));
			//System.out.println(getResult("http://www.95598.fj.sgcc.com.cn/netbusiness/usradmin/getPowerFee.do?branchCode=35403&funcId=5",""));
			//System.out.println(getResult("http://wap.baidu.com/news?tn=bdwcn&word=todaynews&pn=0&ssid=0&from=0&bd_page_type=1&uid=frontui_1267709930_1452&pu=pd@4,sz@176_208,uc@0",""));
			//
			//System.out.println(getResult("http://util.org.ru/ipseeker?ip=210.34.120.1",""));
			//System.out.println(dispatch("news "));
			//System.out.println(dispatch("ip 61.154.127.203"));
			//System.out.println(dispatch("mobile 13599204723"));
			System.out.println(dispatch("weather1 "));
			System.out.println(dispatch("weather2 "));
			//System.out.println(dispatch("news "));
			//System.out.println(dispatch("telfee "));
			//System.out.println(dispatch("elefee "));
			//System.out.println(dispatch("baidunew "));
			//System.out.println(dispatch("news "));
			System.out.println(dispatch("weather2 ").indexOf("星期二"));
			System.out.println(dispatch("wapsohu "));
			//System.out.println(dispatch("wapsohu ").indexOf("星期二"));
			//System.out.println(dispatch("Train D3202"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
