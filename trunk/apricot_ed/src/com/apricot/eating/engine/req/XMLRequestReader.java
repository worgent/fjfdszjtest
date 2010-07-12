/**
 * 
 */
package com.apricot.eating.engine.req;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.LazyDynaBean;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.apricot.eating.util.DataUtil;
import com.apricot.eating.xml.XMLParser;

/**
 * @author Administrator
 * 
 */
public class XMLRequestReader {
	private XMLParser xml;

	/**
	 * 
	 */
	public XMLRequestReader(InputStream io) {
		// TODO Auto-generated constructor stub
		/**
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		
		byte[] b=new byte[1024];
		int l=0;
		try {
			while((l=io.read(b))>0){
				out.write(b,0,l);
			}
			out.close();
			System.out.println("xml="+new String(out.toByteArray()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
		
		
		this.xml = XMLParser.parser(io);
	}

	/**
	 * 读取所有数据
	 * 
	 * @return
	 */
	public HashMap read() {
		HashMap map = new HashMap();

		Node[] elements =xml.getChildsByTagName(xml.getRootElement(), "element");

		for (int i = 0; i < elements.length; i++) {
			org.w3c.dom.Node el = elements[i];
			map.put(xml.getAttribute(el, "name"), read(el));
			
		}
		return map;
	}

	private Object read(org.w3c.dom.Node el) {
		String type = xml.getAttribute(el, "type");
		
		if ("string".equals(type)) {
			return readString(el);
		}

		if ("object".equals(type)) {
			return readObject(el);
		}

		if ("array".equals(type)) {
			return readArray(el);
		}
		return "";
	}

	private Object readString(org.w3c.dom.Node el) {
			return  xml.getNodeCDATA(el);
	}

	private Object readObject(org.w3c.dom.Node el) {
		org.w3c.dom.Node[] objs = xml.getChildsByTagName(el, "element");
		LazyDynaBean obj = new LazyDynaBean();

		for (int i = 0; i < objs.length; i++) {
			DataUtil.setObject(obj, xml.getAttribute(objs[i], "name"),
					read(objs[i]));
		}

		return obj;
	}

	private Object readArray(org.w3c.dom.Node el) {
		ArrayList arr = new ArrayList();

		NodeList els = el.getChildNodes();
		for (int i = 0; i < els.getLength(); i++) {
			org.w3c.dom.Node e = els.item(i);

			if(e.getNodeType()==Node.CDATA_SECTION_NODE){
				arr.add(xml.getNodeCDATA(e));
				continue;
			}
			
			if("value".equals(e.getNodeName())){
				arr.add(xml.getNodeCDATA(e));
				continue;
			}


			if ("row".equals(e.getNodeName())) {
				org.w3c.dom.Node[] objs = xml.getChildsByTagName(e, "element");
				LazyDynaBean obj = new LazyDynaBean();

				for (int a = 0; a < objs.length; a++) {
					DataUtil.setObject(obj, xml.getAttribute(objs[a], "name"),
							read(objs[a]));
				}

				arr.add(obj);
				continue;
			}
		}

		return arr;
	}

}
