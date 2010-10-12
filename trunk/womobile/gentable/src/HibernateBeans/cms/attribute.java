
package HibernateBeans.cms ;
/**
 * 
 *
 * @hibernate.class
 *     table="ATTRIBUTE"
 *
 */
public class attribute {
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   cmsClass cmsclass ">
  private cmsClass cmsclass;
/**
  *   @hibernate.property
  */
   public cmsClass getCmsclass () {
      return cmsclass;
   } 
   public void setCmsclass (cmsClass cmsclass) {
      this.cmsclass = cmsclass;
   }
   // </editor-fold>
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   int sort ">
  private int sort;
/**
  *   @hibernate.property
  */
   public int getSort () {
      return sort;
   } 
   public void setSort (int sort) {
      this.sort = sort;
   }
   // </editor-fold> 
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String name ">
  private String name;
/**
  *   @hibernate.property
  */
   public String getName () {
      return name;
   } 
   public void setName (String name) {
      this.name = name;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String type ">
  private String type;
/**
  *   @hibernate.property
  */
   public String getType () {
      return type;
   } 
   public void setType (String type) {
      this.type = type;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   int legth ">
  private int legth;
/**
  *   @hibernate.property
  */
   public int getLegth () {
      return legth;
   } 
   public void setLegth (int legth) {
      this.legth = legth;
   }
   // </editor-fold>
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   boolean lockme ">
  private boolean lockme;
/**
  *   @hibernate.property
  */
   public boolean getLockme() {
      return lockme;
   } 
   public void setLockme(boolean lockme) {
      this.lockme = lockme;
   }
   // </editor-fold> 
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   boolean listColumn ">
  private boolean listColumn;
/**
  *   @hibernate.property
  */
   public boolean getListColumn () {
      return listColumn;
   } 
   public void setListColumn (boolean listColumn) {
      this.listColumn = listColumn;
   }
   // </editor-fold>  
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   boolean newEdit ">
  private boolean newEdit;
/**
  *   @hibernate.property
  */
   public boolean getNewEdit () {
      return newEdit;
   } 
   public void setNewEdit (boolean newEdit) {
      this.newEdit = newEdit;
   }
   // </editor-fold>    
   
  // <editor-fold defaultstate="collapsed" desc=" Property:   boolean updateEdit ">
  private boolean updateEdit;
/**
  *   @hibernate.property
  */
   public boolean getUpdateEdit () {
      return updateEdit;
   } 
   public void setUpdateEdit (boolean updateEdit) {
      this.updateEdit = updateEdit;
   }
   // </editor-fold>
   
 // <editor-fold defaultstate="collapsed" desc=" Property:   int valueList ">
  private int valueList;
/**
  *   @hibernate.property
  */
   public int getValueList () {
      return valueList;
   } 
   public void setValueList (int valueList) {
      this.valueList = valueList;
   }
   // </editor-fold>
   
 // <editor-fold defaultstate="collapsed" desc=" Property:   String defaultValue ">
  private String defaultValue;
/**
  *   @hibernate.property
  */
   public String getDefaultValue () {
      return defaultValue;
   } 
   public void setDefaultValue (String defaultValue) {
      this.defaultValue = defaultValue;
   }
   // </editor-fold>
 
  // <editor-fold defaultstate="collapsed" desc=" Property:   String position ">
  private String position;
/**
  *   @hibernate.property
  */
   public String getPosition () {
      return position;
   } 
   public void setPosition (String position) {
      this.position = position;
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