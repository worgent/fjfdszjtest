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
package com.enation.framework.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * 单元测试基类<br/>
 * 应用此类必须保证以classpath下有spring包，且含有声明了simpleJdbcTemplate和jdbcTemplate的application的xml配置文件
 * 
 * @author apexking
 * 
 */
public class SpringTestSupport {

	private static ApplicationContext context;

	protected SimpleJdbcTemplate simpleJdbcTemplate;

	protected JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:spring/*.xml" });
		simpleJdbcTemplate = (SimpleJdbcTemplate) this
				.getBean("simpleJdbcTemplate");
		jdbcTemplate = (JdbcTemplate) this.getBean("jdbcTemplate");
		 //mock site 
		
	}

	protected <T> T getBean(String name) {

		return (T)context.getBean(name);

	}

}
