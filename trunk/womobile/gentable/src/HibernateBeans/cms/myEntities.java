/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author James liu
 */
public class myEntities extends pageResult {
 List mylist=new ArrayList();
 public myEntities()
 {
  //
 }
 public void addList(List list)
 {
   List jj=new ArrayList();
   jj.addAll(mylist);
   jj.retainAll(list);
   list.removeAll(jj);
   mylist.addAll(list);
 }
 @Override
 public List getList()
 {
  setRecordCount(mylist.size());
  return mylist.subList(getFirstResult(),getLastResult());
 }
}
