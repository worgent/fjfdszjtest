/*
============================================================================
版权所有 (C) 2008-2010 易族智汇（北京）科技有限公司，并保留所有权利。
网站地址：http://www.javamall.com.cn

您可以在完全遵守《最终用户授权协议》的基础上，将本软件应用于任何用途（包括商
业用途），而不必支付软件版权授权费用。《最终用户授权协议》可以从我们的网站下载；
如果担心法律风险，您也可以联系我们获得书面版本的授权协议。在未经授权情况下不
允许对程序代码以任何形式任何目的的修改或再发布。
============================================================================
*/
package com.enation.framework.util.ip;

public class IPLocation {
	    private String country;  
	    private String area;  
	      
	    public IPLocation() {  
	        country = area = "";  
	    }  
	      
	    public IPLocation getCopy() {  
	        IPLocation ret = new IPLocation();  
	        ret.country = country;  
	        ret.area = area;  
	        return ret;  
	    }  
	  
	    public String getCountry() {  
	        return country;  
	    }  
	  
	    public void setCountry(String country) {  
	        this.country = country;  
	    }  
	  
	    public String getArea() {  
	        return area;  
	    }  
	  
	    public void setArea(String area) {  
	                //如果为局域网，纯真IP地址库的地区会显示CZ88.NET,这里把它去掉  
	        if(area.trim().equals("CZ88.NET")){  
	            this.area="本机或本网络";  
	        }else{  
	            this.area = area;  
	        }  
	    }  
}
