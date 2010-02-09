package com.qzgf.NetStore.pub;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.cownew.ctk.common.ExceptionUtils;
import com.cownew.ctk.constant.StringConst;
import com.cownew.ctk.io.ResourceUtils;

public class WebConfig
{
	private String serverURL;

	private static WebConfig instance;

	private WebConfig()
	{
		super();
	}

	public static WebConfig getInstance()
	{
		if (instance == null)
		{
			instance = new WebConfig();
			try
			{
				instance.initConfig();
			} catch (Exception e)
			{
				ExceptionUtils.toRuntimeException(e);
			}

		}

		return instance;
	}

	protected void initConfig() throws Exception
	{
		InputStream beansXFStream = null;
		try
		{
			beansXFStream = getClass().getResourceAsStream(
					"/com/qzgf/NetStore/pub/WebConfig.xml");
			Document doc = new SAXReader().read(new InputStreamReader(
					beansXFStream, StringConst.UTF8));

			Node serverURLNode = doc.selectSingleNode("//Config/ServerURL");
			serverURL = serverURLNode.getText();

		} finally
		{
			ResourceUtils.close(beansXFStream);
		}

	}

	public URL getServerURL()
	{
		try
		{
			return new URL(serverURL);
		} catch (MalformedURLException e)
		{
			throw ExceptionUtils.toRuntimeException(e);
		}
	}

}
