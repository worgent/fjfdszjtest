package net.trust.utils;
/**
 * @author chenjq
 * @date 20060928
 * @title WebService 对象序列化与对象反序列化
 * 
 * @description 
 */
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DOMParser {
	
		private static final String ROOT = "ROOT";
        private static final String MAP = "MAP";
        private static final String LIST = "LIST";
        private static final String ARRAY = "ARRAY";
        
        private static final String KEY = "key";
        private static final String TYPE = "type";
        
        private static final String OBJECT = "OBJECT";
        private static final String STRING = "STRING";
        private static final String INTEGER = "INTEGER";
        private static final String FLOAT = "FLOAT";
        private static final String BOOLEAN = "BOOLEAN";
        private static final String DOUBLE = "DOUBLE";
        private static final String BYTE = "BYTE";
        private static final String SHORT = "SHORT";
        
        /**
         * 对象解析成xml字符串
         * @param obj
         * @param flag
         * @param upkey
         * @return
         */
        public static String parse(Object obj){
        	return parse(obj,0,null);
        }

        private static String parse(Object obj,int flag,String upkey){
        	if(obj!=null){
	        	StringBuffer sb = new StringBuffer();
	        	String objType= obj.getClass().getName().toUpperCase();
	        	
	        	//flag=0根节点标识，获取根节点对象的类型，放在root的type属性中
	        	if(flag == 0){                        
	        		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	        		sb.append("<").append(ROOT).append(" ").append(TYPE).append("=\"");
	        		if(objType.indexOf(MAP)!=-1){
	        			sb.append(MAP);
	        		}else if (objType.indexOf(LIST)!=-1){
	        			sb.append(LIST);
	        		}else if (objType.indexOf(ARRAY)!=-1){
	        			sb.append(ARRAY);
	        		}else if (objType.indexOf(INTEGER)!=-1){
	        			sb.append(INTEGER);
	        		}else if (objType.indexOf(FLOAT)!=-1){
	        			sb.append(FLOAT);
	        		}else if (objType.indexOf(BOOLEAN)!=-1){
	        			sb.append(BOOLEAN);
	        		}else if (objType.indexOf(BYTE)!=-1){
	        			sb.append(BYTE);
	        		}else if (objType.indexOf(SHORT)!=-1){
	        			sb.append(SHORT);
	        		}else{
	        			sb.append(STRING);
	        		}
	        		sb.append("\">");
	        	}
	    
	        	//根节点对象是list
	        	if(objType.indexOf(LIST)!=-1){
	        		List list = (List)obj;
	        		for(int i=0 ;i<list.size(); i++){
	        			Object item = list.get(i);
	        			
	        			//list中放的是HashMap集合
	        			if(item.getClass().getName().toUpperCase().indexOf(MAP)!=-1){
	        				sb.append("<").append(MAP).append(">");
	        				sb.append(parse(list.get(i),1,upkey));
	        				sb.append("</").append(MAP).append(">");
	        			}
	        			
	        			//list中放的是string类型数据
	        			else if(item.getClass().getName().toUpperCase().indexOf(STRING)!=-1){
	        				sb.append(parse(list.get(i),1,upkey));
	        			}else{
	        				//TODO 实现list中放的其他类型数据
	        			}
	        		}
	        	}else if(objType.indexOf(MAP)!=-1){//根节点对象是map
	        		Map map = (Map)obj;
	        		Iterator it = map.keySet().iterator();                        
	        		while(it.hasNext()){
	        			String key = (String)it.next();
	        			Object value = map.get(key);
	        			
	        			if(value == null){
	        				sb.append(parse("",1,key));
	        			}else{
		        			//map值类型
		        			if(value.getClass().getName().toUpperCase().indexOf(MAP)!=-1){
		        				sb.append("<").append(MAP).append(" ").append(KEY).append("=\"").append(key).append("\">");
		        				sb.append(parse(value,1,key));
		        				sb.append("</").append(MAP).append(">");
		        			}else if(value.getClass().getName().toUpperCase().indexOf(LIST)!=-1){//list类型
		        				sb.append("<").append(LIST).append(" ").append(KEY).append("=\"").append(key).append("\">");
		        				sb.append(parse(value,1,key));
		        				sb.append("</").append(LIST).append(">");
		        			}else if(value.getClass().getName().toUpperCase().indexOf("[L")!=-1){//数组类型
		        				sb.append("<").append(ARRAY).append(" ").append(KEY).append("=\"").append(key).append("\">");
		        				sb.append(parse(value,1,key));
		        				sb.append("</").append(ARRAY).append(">");
		        			}else{//其它按sring类型来走
		        				sb.append(parse(value,1,key));
		        			}
	        			}
	        		}
	        	}else if(objType.equals(Object[].class.getName().toUpperCase())){//数组类型
	        		Object[] arr = (Object[])obj;
	        		for(int i=0; i<arr.length; i++){
	        			sb.append(parse(arr[i],1,""));
	        		}
	        	}else if(objType.equals(String[].class.getName().toUpperCase())){
	        		String[] arr = (String[])obj;
	        		for(int i=0; i<arr.length; i++){
	        			sb.append(parse(arr[i],1,""));
	        		}
	        	}else{
	        		if(upkey==null || upkey.equals("")){//其它把它转成字符串形
	        			if(objType.indexOf(MAP)!=-1){
		        			sb.append("<").append(MAP).append(">");
		        		}else if (objType.indexOf(LIST)!=-1){
		        			sb.append("<").append(LIST).append(">");
		        		}else if (objType.indexOf(ARRAY)!=-1){
		        			sb.append("<").append(ARRAY).append(">");
		        		}else if (objType.indexOf(INTEGER)!=-1){
		        			sb.append("<").append(INTEGER).append(">");
		        		}else if (objType.indexOf(FLOAT)!=-1){
		        			sb.append("<").append(FLOAT).append(">");
		        		}else if (objType.indexOf(BOOLEAN)!=-1){
		        			sb.append("<").append(BOOLEAN).append(">");
		        		}else if (objType.indexOf(BYTE)!=-1){
		        			sb.append("<").append(BYTE).append(">");
		        		}else if (objType.indexOf(SHORT)!=-1){
		        			sb.append("<").append(SHORT).append(">");
		        		}else{
		        			sb.append("<").append(STRING).append(">");
		        		}
	        		}else{
	        			if(objType.indexOf(MAP)!=-1){
		        			sb.append("<").append(MAP).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(LIST)!=-1){
		        			sb.append("<").append(LIST).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(ARRAY)!=-1){
		        			sb.append("<").append(ARRAY).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(INTEGER)!=-1){
		        			sb.append("<").append(INTEGER).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(FLOAT)!=-1){
		        			sb.append("<").append(FLOAT).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(BOOLEAN)!=-1){
		        			sb.append("<").append(BOOLEAN).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(BYTE)!=-1){
		        			sb.append("<").append(BYTE).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else if (objType.indexOf(SHORT)!=-1){
		        			sb.append("<").append(SHORT).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}else{
		        			sb.append("<").append(STRING).append(" ").append(KEY).append("=\"").append(upkey).append("\">");
		        		}
	        		}
	        		sb.append("<![CDATA[");
	        		sb.append(obj.toString());
	        		sb.append("]]>");
	        		if(objType.indexOf(MAP)!=-1){
	        			sb.append("</").append(MAP).append(">");
	        		}else if (objType.indexOf(LIST)!=-1){
	        			sb.append("</").append(LIST).append(">");
	        		}else if (objType.indexOf(ARRAY)!=-1){
	        			sb.append("</").append(ARRAY).append(">");
	        		}else if (objType.indexOf(INTEGER)!=-1){
	        			sb.append("</").append(INTEGER).append(">");
	        		}else if (objType.indexOf(FLOAT)!=-1){
	        			sb.append("</").append(FLOAT).append(">");
	        		}else if (objType.indexOf(BOOLEAN)!=-1){
	        			sb.append("</").append(BOOLEAN).append(">");
	        		}else if (objType.indexOf(BYTE)!=-1){
	        			sb.append("</").append(BYTE).append(">");
	        		}else if (objType.indexOf(SHORT)!=-1){
	        			sb.append("</").append(SHORT).append(">");
	        		}else{
	        			sb.append("</").append(STRING).append(">");
	        		}
	        	}
	        	
	        	if(flag == 0){
	        		sb.append("</").append(ROOT).append(">");
	        	}
	        	return sb.toString(); 
        	}else{
        		return "";
        	}
        }
        
        public static Document parseToDocument(String xmlStr){

            //解析xml字符
	        Document doc = null;
	        try {
	            SAXReader sax = new SAXReader();
	            doc = sax.read(new StringReader(xmlStr));
	        }
	        catch (Exception ex) {
	            throw new RuntimeException(ex);
	        }
	
	        //返回Document对象
	        return doc;
        }
        
        public static Object build(String xml){
        	if(xml == null || xml.equals("")){
        		return "";
        	}
            Document doc = parseToDocument(xml);
            Element root = doc.getRootElement();
            String objectType =root.attributeValue(TYPE);
            Object obj = null;
            //传入根节点，和要返回的数据类型
            if(objectType.equals(MAP)){
                obj = new HashMap();
            }else if(objectType.equals(LIST)){
                obj = new ArrayList();
            }else if(objectType.equals(ARRAY)){
            	String type = ((Element)root.elements().get(0)).getName();
            	if(type.equals(OBJECT)){
            		obj = new Object[root.elements().size()];
            	}else if(type.equals(STRING)){
            		obj = new String[root.elements().size()];
            	}
            }else{
                return ((Element)root.elements().get(0)).getText();
            }        
            recursion(root,obj);
            return obj;
        }
        
        //私有方法，向容器(HashMap或ArrayList或Object[])中增加元素
        private static void add(Object container,String key,Object value, String nodeType){
        	String containerType = container.getClass().getName();
        	if(containerType.equals(HashMap.class.getName())){
        		HashMap map = (HashMap)container;
        		if (null != nodeType && !"".equals(nodeType)){
        			if (nodeType.indexOf(INTEGER)!=-1){
        				map.put(key,(new Integer(value.toString())));
	        		}else if (nodeType.indexOf(FLOAT)!=-1){
	        			map.put(key,(new Float(value.toString())));
	        		}else if (nodeType.indexOf(BOOLEAN)!=-1){
	        			map.put(key,(new Boolean(value.toString())));
	        		}else if (nodeType.indexOf(BYTE)!=-1){
	        			map.put(key,(new Byte(value.toString())));
	        		}else if (nodeType.indexOf(SHORT)!=-1){
	        			map.put(key,(new Short(value.toString())));
	        		}else{
	        			map.put(key,value);
	        		}
        		}else{
        			map.put(key,value);
        		}
        	}else if(containerType.equals(ArrayList.class.getName())){
        		ArrayList list = (ArrayList)container;
        		list.add(value);
        	}else if(containerType.equals(Object[].class.getName())){
        		Object[] array = (Object[])container;
        		for(int i=0; i<array.length; i++){
        			if(array[i] == null){
        				array[i] = value;
        				break;
        			}
        		}
        	}else if(containerType.equals(String[].class.getName())){
        		String[] array = (String[])container;
        		for(int i=0; i<array.length; i++){
        			if(array[i] == null){
        				array[i] = value.toString();
        				break;
        			}
        		}
        	}
        }
        
        
        /**
         * 传入要装载Element对象的容器,进行递归
         * @param e
         * @param up
         */
        private static void recursion(Element e,Object up){
        	List item = e.elements();
        	for(int i=0; i<item.size(); i++){
        		Element node = (Element)item.get(i);
        		String nodeType = node.getName();
        		String nodeKey = node.attributeValue(KEY);
        		
        		//MAP
        		if(nodeType.equals(MAP)){
        			HashMap map = new HashMap();
        			add(up,nodeKey, map, null);
        			recursion(node,map);
        		}else if(nodeType.equals(LIST)){//LIST
        			ArrayList list = new ArrayList();
        			add(up,nodeKey,list, null);
        			recursion(node,list);
        		}else if(nodeType.equals(ARRAY)){//ARRAY
        			if(((Element)node.elements().get(0)).getName().equals(OBJECT)){
            			Object[] array = new Object[item.size()];
            			add(up,nodeKey,array, null);
            			recursion(node,array);        				
        			}
        			if(((Element)node.elements().get(0)).getName().equals(STRING)){
            			String[] array = new String[item.size()];
            			add(up,nodeKey,array, null);
            			recursion(node,array);        				
        			}        			

        		}else{//OTHERS:STRING
        			add(up,nodeKey,node.getText(), nodeType);
        		}
        	}
        	
        }
        
        /**
         * test 测试代码
         * @param args
         */
        public static void main(String[] args){
            HashMap obj = new HashMap ();
            HashMap memberInfo = new HashMap();
            memberInfo.put("NAME","chenqf");
            memberInfo.put("CITY","0592");
            memberInfo.put("AGE",new Integer(23));
            String[] like = new String[]{"music","guitar","basketball"};
            memberInfo.put("like",like);
            obj.put("MEMBERINFO",memberInfo);
            
            System.out.println(obj);
            String returnXML = parse(obj);
            System.out.println(returnXML);
            
        	String xml = returnXML;
        	HashMap map = (HashMap)build(xml);
        	System.out.println(map);
        	
          }        
}
