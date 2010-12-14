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
package com.enation.eop.test.cache;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.enation.eop.core.resource.model.EopSite;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;

public class MemcachedTest {

	@org.junit.Test
	public void Test() {
		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil
				.getAddresses("127.0.0.1:11211"));
		MemcachedClient memcachedClient = null;
		try {
			
			memcachedClient = builder.build();
			long beginTime = System.currentTimeMillis();
			for(int i=0;i<10000;i++){
				EopSite site = new EopSite();
				site.setSitename("site"+i);
				String domain = "www.site"+i+".com";
				memcachedClient.set(domain,0, site);
			}
			long endTime = System.currentTimeMillis();
			System.out.println("压入完成");
			System.out.printf("执行时间是:%d ms %n", endTime - beginTime);
			
			
			 beginTime = System.currentTimeMillis();
			 EopSite mysite = memcachedClient.get("www.site789.com");
				System.out.println("找到site"+ mysite.getSitename());
				 endTime = System.currentTimeMillis();
				System.out.printf("执行时间是:%d ms %n", endTime - beginTime);
			
				//memcachedClient.replace(arg0, o, arg2, arg3)
			
			
		} catch (MemcachedException e) {
			System.err.println("MemcachedClient operation fail");
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("MemcachedClient operation timeout");
			e.printStackTrace();
		} catch (InterruptedException e) {
			// ignore
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		
			memcachedClient.shutdown();
		} catch (IOException e) {
			System.err.println("Shutdown MemcachedClient fail");
			e.printStackTrace();
		}
	}
}
