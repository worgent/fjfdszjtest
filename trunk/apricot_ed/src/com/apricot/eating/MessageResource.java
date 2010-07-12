/**
 * 
 */
package com.apricot.eating;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.apricot.eating.xml.XMLParser;
/**
 * @author Administrator
 */
public class MessageResource {
	private Properties msgs;
	private static MessageResource instance;
	private String resourcePath;

	/**
	 * 
	 */
	private MessageResource(String p) {
		// TODO Auto-generated constructor stub
		this.msgs = new Properties();
		this.resourcePath = p;
		load();
	}

	private void load() {
		XMLParser xml = XMLParser.parser(this.resourcePath);
		NodeList nodes = xml.getElementsByTagName("message");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (!"message".equalsIgnoreCase(n.getNodeName()))
				continue;
			msgs.put(xml.getAttribute(n, "name"), xml.getNodeValue(n));
		}
		// msgs.list(System.out);
	}

	public static final synchronized MessageResource getInstance() {
		// Reload testing use
		instance.load();
		return instance;
	}

	static final synchronized void createInstance(String p) {
		instance = new MessageResource(p);
	}

	public static void main(String[] args) throws IOException {
		MessageResource.createInstance("E:/program/apricot_ed/web/WEB-INF/messageresource.xml");
	}

	public String getMessage(String name, String[] args) throws NestedException {
		String s = (String) this.msgs.get(name);
		if (s == null)
			throw new NestedException("SYS-0026", new String[] { name });
		if (args == null)
			return s;
		return MessageFormat.format(s, args);
	}
}
