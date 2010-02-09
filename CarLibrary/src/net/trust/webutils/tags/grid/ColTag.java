package net.trust.webutils.tags.grid;

import javax.servlet.jsp.JspException;

import com.opensymphony.webwork.views.jsp.IteratorStatus;
import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;


public class ColTag extends AbstractClosingUITag  {

	private static final long serialVersionUID = 1L;
    final public static String OPEN_TEMPLATE = "col";
    final public static String TEMPLATE = "col-close";
    
    private String property ="";
    private String visible = "false";
    private String link ;
    private String target ;
    private String width ;
    private String height ;
    private String align ;
	public void setLink(String link) {
		this.link = link;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public void setTarget(String t){
		this.target = t;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	
	protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }
    
//    public int doEndTag() throws JspException {
//    	
//    	return SKIP_BODY;
//    }

    public void evaluateExtraParams(OgnlValueStack stack) {
        //super.evaluateExtraParams(stack);
        if(property != null){
        	String v = (String)findValue(property,String.class);
        	if(v==null){
        		v = "";
        	}
        	addParameter("property",v);
        }
        addParameter("visible",visible);
        if(nameAttr != null){
        	addParameter("name",nameAttr);
        }
        if(link != null){
        	
        	link = parsLink(link);
        	addParameter("link",link);
        }
        
        if(target != null){
        	addParameter("target",target);
        }
        if(width != null){
        	addParameter("width",width);
        }
        if(height != null){
        	addParameter("height",height);
        }
        if(align != null){
        	addParameter("align",align);
        }
        IteratorStatus status = (IteratorStatus)( stack.getContext().get("statusAttr"));
        if(status.getIndex()==0){
        	Integer tmp = (Integer) stack.getContext().get("colCount");
        	int count =1;
        	if(tmp!=null){
        		count = count+tmp.intValue();
        	}
        	stack.getContext().put("colCount",Integer.valueOf(count));
        }

    }
    
    ///test.shml?userid=#userid#&prdd=#prdd#
    public String parsLink(String str){
    	int start =0;
    	int end =0;
    	int idx =0;
    	StringBuffer tmp = new StringBuffer();
    	
    	String temp = null;
    	while(true){
    		
    		start= str.indexOf("#",start);
    		if(start==-1){
    			break;
    		}
    		end = str.indexOf("#",start+1);

    		temp = str.substring(idx,start);
    		tmp.append(temp);
    		temp = str.substring(start+1,end);
    		tmp.append(findValue(temp,String.class));
    		idx= end+1;
    		start = idx;
    	}
    	
    	return tmp.toString();
    }
    public static void main(String[] args){
    	ColTag colTag = new ColTag();
    	System.out.println(colTag.parsLink("/test.shml?userid=#userid#&prdd=#prdd#"));
    }

    

}
