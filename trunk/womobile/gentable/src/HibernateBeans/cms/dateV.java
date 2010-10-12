
package HibernateBeans.cms ;

import java.util.Date;
/**
 * 
 *
 * @hibernate.class
 *     table="DATE_V"
 *
 */
public class dateV {
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   int e_id ">
  private int e_id;
/**
  *   @hibernate.property
  */
   public int getE_id () {
      return e_id;
   } 
   public void setE_id (int e_id) {
      this.e_id = e_id;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   int a_id ">
  private int a_id;
/**
  *   @hibernate.property
  */
   public int getA_id () {
      return a_id;
   } 
   public void setA_id (int a_id) {
      this.a_id = a_id;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   Date dateV ">
  private Date dateV;
/**
  *   @hibernate.property
  */
   public Date getDateV () {
      return dateV;
   } 
   public void setDateV (Date dateV) {
      this.dateV = dateV;
   }
   // </editor-fold>

   // <editor-fold defaultstate="collapsed" desc=" PrimaryKey:   int id ">
   private int id;
/**
  *   @hibernate.id
  *     generator-class="increment"
  */
   public int getId () {
      return id;
   } 
   public void setId (int id) {
      this.id = id;
   }
   //</editor-fold>
}