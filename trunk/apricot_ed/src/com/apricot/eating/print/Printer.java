/**
 * 
 */
package com.apricot.eating.print;

import com.apricot.eating.util.DataUtil;

/**
 * @author xudahu
 * 
 */
public class Printer {

    public static final String SERIAL_PORT = "2";
    public static final String IP_PORT = "1";
    public static final String NAME = "3";
    private String label;

    /**
     * @return the label
     */
    public String getLabel() {
	if(this.label==null) this.label="";
	if (Printer.IP_PORT.equals(type)) {
	    return new StringBuffer(label).append("(").append(getHostAddress()).append(":").append(hostPort)
		    .append(")").toString();
	}
	return label;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label) {
	this.label = label;
    }

    /**
     * 
     */
    public Printer() {
	// TODO Auto-generated constructor stub
    }

    public Printer(String label, String name, String type, String hostAddress, int hostPort) {
	super();
	this.label = label;
	this.type = type;
	this.hostAddress = hostAddress;
	this.hostPort = hostPort;
	this.name = name;
    }

    public Printer(Object obj) {
	this.hostAddress = DataUtil.getString(obj, "printer_ip");
	this.hostPort = DataUtil.parseInt(DataUtil.getString(obj, "printer_port"));
	this.type = DataUtil.getString(obj, "printer_type");
	this.name = DataUtil.getString(obj, "printer_name");
	this.label = DataUtil.getString(obj, "printer_label");
	this.timeout = 30 * 6000;
    }
    
    private String status;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    private String type;

    /**
     * @return the type
     */
    public String getType() {
	return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
	this.type = type;
    }

    private int timeout;

    /**
     * @return the timeout
     */
    public int getTimeout() {
	return timeout;
    }

    /**
     * @param timeout
     *            the timeout to set
     */
    public void setTimeout(int timeout) {
	this.timeout = timeout;
    }

    private String hostAddress;
    private int hostPort;
    private String name;

    /**
     * @return the hostAddress
     */
    public String getHostAddress() {
	return hostAddress;
    }

    /**
     * @param hostAddress
     *            the hostAddress to set
     */
    public void setHostAddress(String hostAddress) {
	this.hostAddress = hostAddress;
    }

    /**
     * @return the hostPort
     */
    public int getHostPort() {
	return hostPort;
    }

    /**
     * @param hostPort
     *            the hostPort to set
     */
    public void setHostPort(int hostPort) {
	this.hostPort = hostPort;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    public String getKey() {
	if (Printer.IP_PORT.equals(this.type)) {

	}
	return new StringBuffer(getHostAddress()).append(getHostPort()).toString();
    }

}
