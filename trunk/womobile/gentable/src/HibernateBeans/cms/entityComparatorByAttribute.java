/*
 * entityComparatorByAttribute.java
 *
 * Created on 2008年5月13日, 下午1:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class entityComparatorByAttribute implements Comparator{
    
    /** Creates a new instance of entityComparatorByAttribute */
    private String attributeName="";
    private int rules=-1;
    public entityComparatorByAttribute(String attributeName,int rules) {
        this.attributeName=attributeName;
        this.rules=rules;
    }
    public entityComparatorByAttribute() {
    }
    
    public int compare(Object o1, Object o2){
        entity e1=(entity)o1;
        entity e2=(entity)o2;
        Map map1=new HashMap();
        Map map2=new HashMap();
        try {
            map1 = e1.getAllAttributeValues();
            map2=e2.getAllAttributeValues();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(e1.getCmsclass().getAttributeByName(attributeName).getType().equals("int"))
        {
            int v1=0;
            int v2=0;
            try{
            v1=Integer.parseInt(map1.get(attributeName).toString());
            v2=Integer.parseInt(map2.get(attributeName).toString());
            }catch(Exception cx){
            //
            }
            if(v1<v2)return rules*(-1);
            else if(v1==v2)return 0;
            else return rules;
        }
        else if(e1.getCmsclass().getAttributeByName(attributeName).getType().equals("float"))
        {
            float v1=0;
            float v2=0;
            try{
            v1=Float.parseFloat(map1.get(attributeName).toString());
            v2=Float.parseFloat(map2.get(attributeName).toString());
            }catch(Exception cx){
            //
            }
            if(v1<v2)return rules*(-1);
            else if(v1==v2)return 0;
            else return rules;
        }
        else return 0;
    }
}
