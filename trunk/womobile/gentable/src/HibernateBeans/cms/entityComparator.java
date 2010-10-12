/*
 * entityComparator.java
 *
 * Created on 2008年5月5日, 下午3:36
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.Comparator;

/**
 *
 * @author Administrator
 */
public class entityComparator implements Comparator{
    
    /** Creates a new instance of entityComparator */
    public entityComparator() {
    }

    public int compare(Object o1, Object o2) {
        entity e1=(entity)o1;
        entity e2=(entity)o2;
        if(e1.getId()<e2.getId())return 1;
        else if(e1.getId()==e2.getId())return 0;
        else return -1;
    }
    
}
