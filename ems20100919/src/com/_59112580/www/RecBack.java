/**
 * RecBack.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com._59112580.www;

public class RecBack  implements java.io.Serializable {
    private java.lang.String orderID;

    private java.lang.String recTime;

    private java.lang.String result;

    private java.lang.String recDesc;

    public RecBack() {
    }

    public RecBack(
           java.lang.String orderID,
           java.lang.String recTime,
           java.lang.String result,
           java.lang.String recDesc) {
           this.orderID = orderID;
           this.recTime = recTime;
           this.result = result;
           this.recDesc = recDesc;
    }


    /**
     * Gets the orderID value for this RecBack.
     * 
     * @return orderID
     */
    public java.lang.String getOrderID() {
        return orderID;
    }


    /**
     * Sets the orderID value for this RecBack.
     * 
     * @param orderID
     */
    public void setOrderID(java.lang.String orderID) {
        this.orderID = orderID;
    }


    /**
     * Gets the recTime value for this RecBack.
     * 
     * @return recTime
     */
    public java.lang.String getRecTime() {
        return recTime;
    }


    /**
     * Sets the recTime value for this RecBack.
     * 
     * @param recTime
     */
    public void setRecTime(java.lang.String recTime) {
        this.recTime = recTime;
    }


    /**
     * Gets the result value for this RecBack.
     * 
     * @return result
     */
    public java.lang.String getResult() {
        return result;
    }


    /**
     * Sets the result value for this RecBack.
     * 
     * @param result
     */
    public void setResult(java.lang.String result) {
        this.result = result;
    }


    /**
     * Gets the recDesc value for this RecBack.
     * 
     * @return recDesc
     */
    public java.lang.String getRecDesc() {
        return recDesc;
    }


    /**
     * Sets the recDesc value for this RecBack.
     * 
     * @param recDesc
     */
    public void setRecDesc(java.lang.String recDesc) {
        this.recDesc = recDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RecBack)) return false;
        RecBack other = (RecBack) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.orderID==null && other.getOrderID()==null) || 
             (this.orderID!=null &&
              this.orderID.equals(other.getOrderID()))) &&
            ((this.recTime==null && other.getRecTime()==null) || 
             (this.recTime!=null &&
              this.recTime.equals(other.getRecTime()))) &&
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult()))) &&
            ((this.recDesc==null && other.getRecDesc()==null) || 
             (this.recDesc!=null &&
              this.recDesc.equals(other.getRecDesc())));
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
        if (getOrderID() != null) {
            _hashCode += getOrderID().hashCode();
        }
        if (getRecTime() != null) {
            _hashCode += getRecTime().hashCode();
        }
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        if (getRecDesc() != null) {
            _hashCode += getRecDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RecBack.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.59112580.com/", "RecBack"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "OrderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "RecTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "Result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "RecDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
