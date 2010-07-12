/**
 * 
 */
package com.apricot.webservice.bean;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apricot.eating.xml.XMLParser;

/**
 * @author Administrator
 * 
 */
public class Request {

	/**
	 * 
	 */
	public Request() {
		// TODO Auto-generated constructor stub
		this.rows = new ArrayList();
	}

	public Request(String xml) {
		this();

		XMLParser x = XMLParser
				.parser(new ByteArrayInputStream(xml.getBytes()));

		Node root = x.getRootElement();
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if ("shopId".equalsIgnoreCase(n.getNodeName())) {
				this.shopId = x.getNodeCDATA(n);
				continue;
			}
			if ("action".equalsIgnoreCase(n.getNodeName())) {
				this.action = x.getNodeCDATA(n);
				continue;
			}

			if ("body".equalsIgnoreCase(n.getNodeName())) {
				NodeList rws = n.getChildNodes();
				for (int j = 0; j < rws.getLength(); j++) {
					Row row = new Row();
					Node r = rws.item(j);
					if (!"row".equalsIgnoreCase(r.getNodeName()))
						continue;
					NodeList cols = rws.item(j).getChildNodes();
					for (int k = 0; k < cols.getLength(); k++) {
						Node c = cols.item(k);
						if (c.getNodeType() == Node.ELEMENT_NODE) {
							row.setValue(c.getNodeName(), x.getNodeCDATA(c));
						}
					}
					addRow(row);
				}
			}
		}

	}

	private String toNodeXML(String name, Object value) {
		return new StringBuffer("<").append(name).append("><![CDATA[").append(
				value).append("]]></").append(name).append(">").toString();
	}

	public String toXML() {
		StringBuffer xml = new StringBuffer();
		xml.append("<response>");
		xml.append(toNodeXML("shopId", this.shopId));
		xml.append(toNodeXML("action", this.action));
		xml.append("<body>");
		for (Iterator rs = getRows(); rs.hasNext();) {
			HashMap o = ((Row) rs.next()).getValues();
			xml.append("<row>");
			for (Iterator keys = o.keySet().iterator(); keys.hasNext();) {
				String key = (String) keys.next();
				xml.append(toNodeXML(key, o.get(key)));
			}
			xml.append("</row>");
		}
		xml.append("</body>");
		xml.append("</response>");
		return xml.toString();
	}

	private List rows;

	private String shopId;
	private String action;

	public Iterator getRows() {
		return rows.iterator();
	}

	public void addRow(Row row) {
		this.rows.add(row);
	}
	
	public void addRows(List a){
		if(a!=null);
		this.rows.addAll(a);
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
