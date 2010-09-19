/**
 * DistBack.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com._59112580.www;

public class DistBack  implements java.io.Serializable {
    private java.lang.String orderID;

    private java.lang.String sendTime;

    private java.lang.String sentTime;

    private java.lang.String result;

    private java.lang.String distDesc;

    private java.lang.String _package;

    public DistBack() {
    }

    public DistBack(
           java.lang.String orderID,
           java.lang.String sendTime,
           java.lang.String sentTime,
           java.lang.String result,
           java.lang.String distDesc,
           java.lang.String _package) {
           this.orderID = orderID;
           this.sendTime = sendTime;
           this.sentTime = sentTime;
           this.result = result;
           this.distDesc = distDesc;
           this._package = _package;
    }


    /**
     * Gets the orderID value for this DistBack.
     * 
     * @return orderID
     */
    public java.lang.String getOrderID() {
        return orderID;
    }


    /**
     * Sets the orderID value for this DistBack.
     * 
     * @param orderID
     */
    public void setOrderID(java.lang.String orderID) {
        this.orderID = orderID;
    }


    /**
     * Gets the sendTime value for this DistBack.
     * 
     * @return sendTime
     */
    public java.lang.String getSendTime() {
        return sendTime;
    }


    /**
     * Sets the sendTime value for this DistBack.
     * 
     * @param sendTime
     */
    public void setSendTime(java.lang.String sendTime) {
        this.sendTime = sendTime;
    }


    /**
     * Gets the sentTime value for this DistBack.
     * 
     * @return sentTime
     */
    public java.lang.String getSentTime() {
        return sentTime;
    }


    /**
     * Sets the sentTime value for this DistBack.
     * 
     * @param sentTime
     */
    public void setSentTime(java.lang.String sentTime) {
        this.sentTime = sentTime;
    }


    /**
     * Gets the result value for this DistBack.
     * 
     * @return result
     */
    public java.lang.String getResult() {
        return result;
    }


    /**
     * Sets the result value for this DistBack.
     * 
     * @param result
     */
    public void setResult(java.lang.String result) {
        this.result = result;
    }


    /**
     * Gets the distDesc value for this DistBack.
     * 
     * @return distDesc
     */
    public java.lang.String getDistDesc() {
        return distDesc;
    }


    /**
     * Sets the distDesc value for this DistBack.
     * 
     * @param distDesc
     */
    public void setDistDesc(java.lang.String distDesc) {
        this.distDesc = distDesc;
    }


    /**
     * Gets the _package value for this DistBack.
     * 
     * @return _package
     */
    public java.lang.String get_package() {
        return _package;
    }


    /**
     * Sets the _package value for this DistBack.
     * 
     * @param _package
     */
    public void set_package(java.lang.String _package) {
        this._package = _package;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DistBack)) return false;
        DistBack other = (DistBack) obj;
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
            ((this.sendTime==null && other.getSendTime()==null) || 
             (this.sendTime!=null &&
              this.sendTime.equals(other.getSendTime()))) &&
            ((this.sentTime==null && other.getSentTime()==null) || 
             (this.sentTime!=null &&
              this.sentTime.equals(other.getSentTime()))) &&
            ((this.result==null && other.getResult()==null) || 
             (this.result!=null &&
              this.result.equals(other.getResult()))) &&
            ((this.distDesc==null && other.getDistDesc()==null) || 
             (this.distDesc!=null &&
              this.distDesc.equals(other.getDistDesc()))) &&
            ((this._package==null && other.get_package()==null) || 
             (this._package!=null &&
              this._package.equals(other.get_package())));
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
        if (getSendTime() != null) {
            _hashCode += getSendTime().hashCode();
        }
        if (getSentTime() != null) {
            _hashCode += getSentTime().hashCode();
        }
        if (getResult() != null) {
            _hashCode += getResult().hashCode();
        }
        if (getDistDesc() != null) {
            _hashCode += getDistDesc().hashCode();
        }
        if (get_package() != null) {
            _hashCode += get_package().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DistBack.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.59112580.com/", "DistBack"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "OrderID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "SendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sentTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "SentTime"));
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
        elemField.setFieldName("distDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "DistDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_package");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.59112580.com/", "Package"));
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
