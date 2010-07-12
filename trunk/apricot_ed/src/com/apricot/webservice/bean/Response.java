/**
 * 
 */
package com.apricot.webservice.bean;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apricot.eating.xml.XMLParser;

/**
 * @author Administrator
 * 
 */
public class Response {

	/**
	 * 
	 */
	public Response() {
		// TODO Auto-generated constructor stub
		this.rows = new ArrayList();
	}

	public Response(String errorCode, String errorMessage) {
		this();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Response(String xml){
		this();

		XMLParser x = XMLParser
				.parser(new ByteArrayInputStream(xml.getBytes()));
		System.out.println(xml);

		Node root = x.getRootElement();
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if ("errorCode".equalsIgnoreCase(n.getNodeName())) {
				this.errorCode = x.getNodeCDATA(n);
				continue;
			}
			if ("errorMessage".equalsIgnoreCase(n.getNodeName())) {
				this.errorMessage = x.getNodeCDATA(n);
				continue;
			}

			if ("body".equalsIgnoreCase(n.getNodeName())) {
				NodeList rws = n.getChildNodes();
				Row row = new Row();
				for (int j = 0; j < rws.getLength(); j++) {
					Node r = rws.item(j);
					if (!"row".equalsIgnoreCase(r.getNodeName()))
						continue;
					NodeList cols = rws.item(j).getChildNodes();
					for (int k = 0; k < cols.getLength(); k++) {
						Node c = cols.item(k);
						if (c.getNodeType() == Node.ENTITY_NODE) {
							row.setValue(c.getNodeName(), x.getNodeCDATA(c));
						}
					}

				}

				addRow(row);
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
		xml.append(toNodeXML("errorCode", this.errorCode));
		xml.append(toNodeXML("errorMessage", this.errorMessage));
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

	private String errorCode;
	private String errorMessage;
	private List rows;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Iterator getRows() {
		return rows.iterator();
	}

	public void addRow(Row row) {
		this.rows.add(row);
	}
	
	public void addRows(List row) {
		this.rows.addAll(row);
	}

}
