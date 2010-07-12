/**
 * 
 */
package com.apricot.eating.print.io;

import com.apricot.eating.print.PrintDocumentStream;
import com.apricot.eating.print.Printer;

/**
 * @author xudahu
 * 
 */
public class CashierDeskStream extends PrintDocumentStream {

    /**
     * 
     */
    public CashierDeskStream() {
	// TODO Auto-generated constructor stub
    }

    protected String getBOReadMethod() {
	// TODO Auto-generated method stub
	return "readCashierDeskDocument";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.apricot.eating.print.PrintDocumentStream#getPrinter()
     */

    /* (non-Javadoc)
     * @see com.apricot.eating.print.PrintDocumentStream#getBOWriteMethod()
     */
    @Override
    protected String getBOWriteMethod() {
	// TODO Auto-generated method stub
	return "writeCashierDeskDocument";
    }

    protected Printer getPrinter() {
	// TODO Auto-generated method stub
	return new Printer(this.printerLabel, this.printerName, this.printerType, this.printerHost, this.printerPort);
    }

    private String printerName;
    private String printerType;

    private String printerHost;
    private int printerPort;
    private String printerLabel;

    /**
     * @return the printerName
     */
    public String getPrinterName() {
	return printerName;
    }

    /**
     * @param printerName
     *            the printerName to set
     */
    public void setPrinterName(String printerName) {
	this.printerName = printerName;
    }

    /**
     * @return the printerType
     */
    public String getPrinterType() {
	return printerType;
    }

    /**
     * @param printerType
     *            the printerType to set
     */
    public void setPrinterType(String printerType) {
	this.printerType = printerType;
    }

    /**
     * @return the printerHost
     */
    public String getPrinterHost() {
	return printerHost;
    }

    /**
     * @param printerHost
     *            the printerHost to set
     */
    public void setPrinterHost(String printerHost) {
	this.printerHost = printerHost;
    }

    /**
     * @return the printerPort
     */
    public int getPrinterPort() {
	return printerPort;
    }

    /**
     * @param printerPort
     *            the printerPort to set
     */
    public void setPrinterPort(int printerPort) {
	this.printerPort = printerPort;
    }

    /**
     * @return the printerLabel
     */
    public String getPrinterLabel() {
	return printerLabel;
    }

    /**
     * @param printerLabel
     *            the printerLabel to set
     */
    public void setPrinterLabel(String printerLabel) {
	this.printerLabel = printerLabel;
    }

}
