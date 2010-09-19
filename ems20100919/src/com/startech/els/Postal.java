/**
 * Postal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.startech.els;

public class Postal  implements java.io.Serializable {
    private java.lang.String barcode;

    private java.lang.String cbtype1;

    private java.lang.String cbtype2;

    private java.lang.String examinearea;

    private java.lang.String money1;

    private java.lang.String money2;

    private java.lang.String paddress;

    private java.lang.String passno;

    private java.lang.String saddress;

    private java.lang.String semscontactor;

    private java.lang.String sphone;

    private java.lang.String tblno;

    public Postal() {
    }

    public Postal(
           java.lang.String barcode,
           java.lang.String cbtype1,
           java.lang.String cbtype2,
           java.lang.String examinearea,
           java.lang.String money1,
           java.lang.String money2,
           java.lang.String paddress,
           java.lang.String passno,
           java.lang.String saddress,
           java.lang.String semscontactor,
           java.lang.String sphone,
           java.lang.String tblno) {
           this.barcode = barcode;
           this.cbtype1 = cbtype1;
           this.cbtype2 = cbtype2;
           this.examinearea = examinearea;
           this.money1 = money1;
           this.money2 = money2;
           this.paddress = paddress;
           this.passno = passno;
           this.saddress = saddress;
           this.semscontactor = semscontactor;
           this.sphone = sphone;
           this.tblno = tblno;
    }


    /**
     * Gets the barcode value for this Postal.
     * 
     * @return barcode
     */
    public java.lang.String getBarcode() {
        return barcode;
    }


    /**
     * Sets the barcode value for this Postal.
     * 
     * @param barcode
     */
    public void setBarcode(java.lang.String barcode) {
        this.barcode = barcode;
    }


    /**
     * Gets the cbtype1 value for this Postal.
     * 
     * @return cbtype1
     */
    public java.lang.String getCbtype1() {
        return cbtype1;
    }


    /**
     * Sets the cbtype1 value for this Postal.
     * 
     * @param cbtype1
     */
    public void setCbtype1(java.lang.String cbtype1) {
        this.cbtype1 = cbtype1;
    }


    /**
     * Gets the cbtype2 value for this Postal.
     * 
     * @return cbtype2
     */
    public java.lang.String getCbtype2() {
        return cbtype2;
    }


    /**
     * Sets the cbtype2 value for this Postal.
     * 
     * @param cbtype2
     */
    public void setCbtype2(java.lang.String cbtype2) {
        this.cbtype2 = cbtype2;
    }


    /**
     * Gets the examinearea value for this Postal.
     * 
     * @return examinearea
     */
    public java.lang.String getExaminearea() {
        return examinearea;
    }


    /**
     * Sets the examinearea value for this Postal.
     * 
     * @param examinearea
     */
    public void setExaminearea(java.lang.String examinearea) {
        this.examinearea = examinearea;
    }


    /**
     * Gets the money1 value for this Postal.
     * 
     * @return money1
     */
    public java.lang.String getMoney1() {
        return money1;
    }


    /**
     * Sets the money1 value for this Postal.
     * 
     * @param money1
     */
    public void setMoney1(java.lang.String money1) {
        this.money1 = money1;
    }


    /**
     * Gets the money2 value for this Postal.
     * 
     * @return money2
     */
    public java.lang.String getMoney2() {
        return money2;
    }


    /**
     * Sets the money2 value for this Postal.
     * 
     * @param money2
     */
    public void setMoney2(java.lang.String money2) {
        this.money2 = money2;
    }


    /**
     * Gets the paddress value for this Postal.
     * 
     * @return paddress
     */
    public java.lang.String getPaddress() {
        return paddress;
    }


    /**
     * Sets the paddress value for this Postal.
     * 
     * @param paddress
     */
    public void setPaddress(java.lang.String paddress) {
        this.paddress = paddress;
    }


    /**
     * Gets the passno value for this Postal.
     * 
     * @return passno
     */
    public java.lang.String getPassno() {
        return passno;
    }


    /**
     * Sets the passno value for this Postal.
     * 
     * @param passno
     */
    public void setPassno(java.lang.String passno) {
        this.passno = passno;
    }


    /**
     * Gets the saddress value for this Postal.
     * 
     * @return saddress
     */
    public java.lang.String getSaddress() {
        return saddress;
    }


    /**
     * Sets the saddress value for this Postal.
     * 
     * @param saddress
     */
    public void setSaddress(java.lang.String saddress) {
        this.saddress = saddress;
    }


    /**
     * Gets the semscontactor value for this Postal.
     * 
     * @return semscontactor
     */
    public java.lang.String getSemscontactor() {
        return semscontactor;
    }


    /**
     * Sets the semscontactor value for this Postal.
     * 
     * @param semscontactor
     */
    public void setSemscontactor(java.lang.String semscontactor) {
        this.semscontactor = semscontactor;
    }


    /**
     * Gets the sphone value for this Postal.
     * 
     * @return sphone
     */
    public java.lang.String getSphone() {
        return sphone;
    }


    /**
     * Sets the sphone value for this Postal.
     * 
     * @param sphone
     */
    public void setSphone(java.lang.String sphone) {
        this.sphone = sphone;
    }


    /**
     * Gets the tblno value for this Postal.
     * 
     * @return tblno
     */
    public java.lang.String getTblno() {
        return tblno;
    }


    /**
     * Sets the tblno value for this Postal.
     * 
     * @param tblno
     */
    public void setTblno(java.lang.String tblno) {
        this.tblno = tblno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Postal)) return false;
        Postal other = (Postal) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.barcode==null && other.getBarcode()==null) || 
             (this.barcode!=null &&
              this.barcode.equals(other.getBarcode()))) &&
            ((this.cbtype1==null && other.getCbtype1()==null) || 
             (this.cbtype1!=null &&
              this.cbtype1.equals(other.getCbtype1()))) &&
            ((this.cbtype2==null && other.getCbtype2()==null) || 
             (this.cbtype2!=null &&
              this.cbtype2.equals(other.getCbtype2()))) &&
            ((this.examinearea==null && other.getExaminearea()==null) || 
             (this.examinearea!=null &&
              this.examinearea.equals(other.getExaminearea()))) &&
            ((this.money1==null && other.getMoney1()==null) || 
             (this.money1!=null &&
              this.money1.equals(other.getMoney1()))) &&
            ((this.money2==null && other.getMoney2()==null) || 
             (this.money2!=null &&
              this.money2.equals(other.getMoney2()))) &&
            ((this.paddress==null && other.getPaddress()==null) || 
             (this.paddress!=null &&
              this.paddress.equals(other.getPaddress()))) &&
            ((this.passno==null && other.getPassno()==null) || 
             (this.passno!=null &&
              this.passno.equals(other.getPassno()))) &&
            ((this.saddress==null && other.getSaddress()==null) || 
             (this.saddress!=null &&
              this.saddress.equals(other.getSaddress()))) &&
            ((this.semscontactor==null && other.getSemscontactor()==null) || 
             (this.semscontactor!=null &&
              this.semscontactor.equals(other.getSemscontactor()))) &&
            ((this.sphone==null && other.getSphone()==null) || 
             (this.sphone!=null &&
              this.sphone.equals(other.getSphone()))) &&
            ((this.tblno==null && other.getTblno()==null) || 
             (this.tblno!=null &&
              this.tblno.equals(other.getTblno())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBarcode() != null) {
            _hashCode += getBarcode().hashCode();
        }
        if (getCbtype1() != null) {
            _hashCode += getCbtype1().hashCode();
        }
        if (getCbtype2() != null) {
            _hashCode += getCbtype2().hashCode();
        }
        if (getExaminearea() != null) {
            _hashCode += getExaminearea().hashCode();
        }
        if (getMoney1() != null) {
            _hashCode += getMoney1().hashCode();
        }
        if (getMoney2() != null) {
            _hashCode += getMoney2().hashCode();
        }
        if (getPaddress() != null) {
            _hashCode += getPaddress().hashCode();
        }
        if (getPassno() != null) {
            _hashCode += getPassno().hashCode();
        }
        if (getSaddress() != null) {
            _hashCode += getSaddress().hashCode();
        }
        if (getSemscontactor() != null) {
            _hashCode += getSemscontactor().hashCode();
        }
        if (getSphone() != null) {
            _hashCode += getSphone().hashCode();
        }
        if (getTblno() != null) {
            _hashCode += getTblno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Postal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://els.startech.com", "Postal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("barcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "barcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cbtype1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "cbtype1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cbtype2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "cbtype2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("examinearea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "examinearea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("money1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "money1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("money2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "money2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "paddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "passno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "saddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("semscontactor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "semscontactor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sphone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "sphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tblno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://els.startech.com", "tblno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
