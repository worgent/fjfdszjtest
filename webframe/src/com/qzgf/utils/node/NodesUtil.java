package com.qzgf.utils.node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author chenf
 * @date Apr 15, 2006
 * 节点的工具类
 * */
public class NodesUtil {
	public NodesUtil(){
	}
	/**
	 * @author chenf
	 * @date Apr 15, 2006
	 * 把节点集合转换成xml格式的字符串
	 * @param List nodes 节点集合
	 * @param boolean havCheckBox 是否显示Checkbox
	 * @return String xml格式的字符串
	 * @throws 无
	 * */
	/*oracle专用:有树型的支持*/
	public String getTreeNodes(List nodes){
		if(null == nodes || nodes.size()==0)
			return "";
		int size = nodes.size();
		Element root =  DocumentHelper.createElement("datasource");
		Map tmp = new HashMap();
		try{
		for(int i=0;i<size;i++){
			
			HashMap node = (HashMap)nodes.get(i);
			if(null == node)
				continue;
			//<node id="1" type="D" text="A">
			Element element = DocumentHelper.createElement("node");
			element.addAttribute("id",node.get("id").toString());
			element.addAttribute("type","D");
			if(null !=node.get("title").toString() && node.get("title").toString().trim().length()>0){
				element.addAttribute("text",node.get("title").toString());
			}
			//动态追加到相应的父结点
			if(null != tmp.get(node.get("superId").toString())){
				((Element)tmp.get(node.get("superId").toString())).add(element);
			}else{
				root.add(element);
			}
			tmp.put(node.get("id").toString(),element);
		}
		}catch(Exception e)
		{
			System.out.print(e.toString());
		}
		tmp = null;
		return root.asXML();
	}
	public static void main(String[] args){
		List list = new java.util.ArrayList();
		Node node1 = new Node();
		node1.setId("1");
		node1.setTitle("A");
		node1.setSuperId("Root");
		list.add(node1);
		
		Node node2 = new Node();
		node2.setId("2");
		node2.setTitle("A1");
		node2.setSuperId("1");
		list.add(node2);
		
		Node node3 = new Node();
		node3.setId("3");
		node3.setTitle("A2");
		node3.setSuperId("2");
		list.add(node3);

		Node node4 = new Node();
		node4.setId("4");
		node4.setTitle("B");
		node4.setSuperId("Root");
		list.add(node4);
		
		NodesUtil nu = new NodesUtil();
		System.out.println(nu.getTreeNodes(list));
	}
}
