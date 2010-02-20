package net.trust.webutils.tags.grid;

import com.opensymphony.webwork.views.jsp.ui.AbstractClosingUITag;
import com.opensymphony.xwork.util.OgnlValueStack;


public class RowTag extends AbstractClosingUITag {

	private static final long serialVersionUID = 1L;
    final public static String OPEN_TEMPLATE = "row";
    final public static String TEMPLATE = "row-close";
    
    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }
    
    public void evaluateExtraParams(OgnlValueStack stack) {
    	if (onclickAttr != null) {
            addParameter("onclick", parsStr(onclickAttr));
        }
    	if (ondblclickAttr != null){
    		addParameter("ondblclick", parsStr(ondblclickAttr));
    	}
    }
    public String parsStr(String str){
    	int start =0;
    	int end =0;
    	int idx =0;
    	StringBuffer tmp = new StringBuffer();
    	
    	String temp = null;
    	while(true){
    		
    		start= str.indexOf("#",start);
    		if(start==-1){
    			tmp.append(str.substring(idx));
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
    
}
