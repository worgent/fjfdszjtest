/**
 * XMLParser.java 2007-5-29 Copyright (c) 2002-2007 Devei xu . Chongqing ,
 * 400039 , China All Rights Reserved. This software is the confidential and
 * proprietary information of Devei xu . ("Confidential Information"). You shall
 * not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Devei xu.
 */
package com.apricot.eating.xml;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class XMLParser {
	protected Document document;

	/**
	 * 
	 */
	public XMLParser() {
		super();
	}

	private XMLParser(InputStream is) {
		try {
			DocumentBuilderFactory factory = new DocumentBuilderFactoryImpl();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStreamReader isr =new InputStreamReader(is,"utf-8");
			
			StringBuffer sb = new StringBuffer();
			int b;
			while ((b = isr.read()) != -1) {
				sb.append((char) b);
			}
			is=new ByteArrayInputStream(sb.toString().getBytes("utf-8"));
			if (is != null) {
				document = builder.parse(is);
			} else {
				document = builder.newDocument();
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public static XMLParser parser(InputStream is) {
		return new XMLParser(is);
	}

	public static XMLParser parser(String p) {
		try {
			return new XMLParser(new FileInputStream(p));
		} catch (Exception e) {
		}
		return new XMLParser(null);
	}

	public NodeList getElementsByTagName(String tagName) {
		return document.getElementsByTagName(tagName);
	}

	public String getAttribute(Node item, String attName) {
		try {
			String temp = item.getAttributes().getNamedItem(attName).getNodeValue();
			return temp.trim();
		} catch (Exception e) {
		}
		return "";
	}

	public void removeNode(Node node) {
		node.getParentNode().removeChild(node);
	}

	public Node[] getChildsByTagName(Node parent, String tagName) {
		NodeList childs = parent.getChildNodes();
		ArrayList array = new ArrayList();
		if (childs == null)
			return null;
		for (int i = 0; i < childs.getLength(); i++) {
			if (tagName.equals(childs.item(i).getNodeName())) {
				array.add(childs.item(i));
			}
		}
		// childs=null;
		return (Node[]) array.toArray(new Node[array.size()]);
	}

	public Node getRootElement() {
		return document.getLastChild();
	}

	public Object getNodeObject(Node node, Class claz) {
		Object obj = null;
		try {
			obj = claz.newInstance();
		} catch (Exception e) {
		}
		if (obj == null)
			return null;
		try {
			HashMap map = getAttributes(node);
			for (Iterator keys = map.keySet().iterator(); keys.hasNext();) {
				String k = (String) keys.next();
				try {
					BeanUtils.setProperty(obj, k, map.get(k));
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
		}
		return obj;
	}

	public Node getChildByTagName(Node parent, String tagName) {
		NodeList childs = parent.getChildNodes();
		if (childs == null)
			return null;
		for (int i = 0; i < childs.getLength(); i++) {
			if (tagName.equals(childs.item(i).getNodeName())) {
				return childs.item(i);
			}
		}
		return null;
	}

	public Document getDocument() {
		return this.document;
	}

	public String getNodeValue(Node node) {
		NodeList ns = node.getChildNodes();
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i < ns.getLength(); i++) {
			String v = null;
			try {
				v = ns.item(i).getNodeValue();
			} catch (Exception e) {
			}
			;
			if (v != null)
				s.append(v.trim());
		}
		return s.toString();
	}

	public String getNodeCDATA(Node node) {
		NodeList ns = node.getChildNodes();
		for (int i = 0; i < ns.getLength(); i++) {
			
			if (ns.item(i).getNodeType() == Node.CDATA_SECTION_NODE) {
				return ns.item(i).getNodeValue();
			}
		}
		return "";
	}

	public HashMap getAttributes(Node node) {
		NamedNodeMap map = node.getAttributes();
		HashMap attrs = new HashMap();
		for (int i = 0; i < map.getLength(); i++) {
			Node n = map.item(i);
			attrs.put(n.getNodeName(), n.getNodeValue());
			n = null;
		}
		return attrs;
	}
}
