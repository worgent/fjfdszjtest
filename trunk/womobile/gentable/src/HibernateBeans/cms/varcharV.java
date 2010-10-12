
package HibernateBeans.cms ;
/**
 * 
 *
 * @hibernate.class
 *     table="VARCHAR_V"
 *
 */
public class varcharV {
 
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
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String varcharV ">
  private String varcharV;
/**
  *   @hibernate.property
  */
   public String getVarcharV () {
      return varcharV;
   } 
   public void setVarcharV (String varcharV) {
      this.varcharV = varcharV;
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