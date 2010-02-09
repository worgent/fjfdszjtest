package net.trust.utils.node;

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
	public String getTreeNodes(List nodes,boolean havCheckBok){
		if(null == nodes || nodes.size()==0)
			return "";
		int size = nodes.size();
		Element root =  DocumentHelper.createElement("Root");
		Map tmp = new HashMap();
		
		for(int i=0;i<size;i++){
			Node node = (Node)nodes.get(i);
			if(null == node)
				continue;
			//<TreeNode Title = 'AA'  CheckData = 'AA' Checked = 'true'>
			Element element = DocumentHelper.createElement("TreeNode");
			element.addAttribute("Title",node.getTitle());
			if(null !=node.getHref() && node.getHref().trim().length()>0){
				element.addAttribute("Href",node.getHref());
			}
			
			if(null !=node.getNodeXMLSrc() && node.getNodeXMLSrc().trim().length()>0){
				element.addAttribute("NodeXMLSrc",node.getNodeXMLSrc());
			}
			if(null !=node.getTarget() && node.getTarget().trim().length()>0){
				element.addAttribute("Target",node.getTarget());
			}
			if(havCheckBok){
				element.addAttribute("CheckData",node.getId());
				if(node.getChecked()>0){
					element.addAttribute("Checked","true");
				}
			}
			if(null != tmp.get(node.getSuperId())){
				((Element)tmp.get(node.getSuperId())).add(element);
			}else{
				root.add(element);
			}
			tmp.put(node.getId(),element);
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
		System.out.println(nu.getTreeNodes(list,true));
	}
}
