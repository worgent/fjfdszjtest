package com.qzgf.NetStore.struts.action.FrontAction;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class TestPrivateKey {

	public void testKye(){
		String MerId="808080092399170";//商户号
        String OrdId="2008991700000000";//订单,从前台直接够得
        String TransAmt="000000001234";//交易金额,这一块要到后台去重新验证一遍并与前台比较
        String CuryId="156";
        String TransDate="20081022";//从数据库查得这个订单的交易日期
        String TransType="0001";
        String OrderStatus="1001";//消费交易成功代码  
        
        chinapay.PrivateKey key=new chinapay.PrivateKey();
        chinapay.SecureLink t;
        boolean flag;
        flag=key.buildKey(MerId, 0, "/MerPrk.key");
        if(flag==false){
        	System.out.println("build key error!");
        }
        else{
        	System.out.println("加载成功");
        }
        // t=new chinapay.SecureLink(key);
        //订单签名函数
       // String CheckValue=t.signOrder(MerId, OrdId, TransAmt, CuryId, TransDate, TransType);
        //boolean flag1=t.verifyTransResponse(MerId, OrdId, TransAmt, CuryId, TransDate, TransType, TransCode, CheckValue);
	}
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	/*	  String pathStr=rqt.getRealPath("/key");
		  
		  
		  
		  String smallName=pathStr+"\\MerPrK.key"; 
		  
			String path = new String(smallName);
			
			
			//boolean flag = (new File(path)).delete();
			//System.out.println(flag);
			
			
		
		File f=new File(path);
		if(f.exists()){
			System.out.println("文件存在");
		}
		else{
			System.out.println("文件不存在");
		}
		*/
		
		
		TestPrivateKey key=new TestPrivateKey();
		key.testKye();
	}

}
