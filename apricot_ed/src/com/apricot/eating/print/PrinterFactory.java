/**
 * 
 */
package com.apricot.eating.print;

/**
 * @author xudahu
 * 
 */
public final class PrinterFactory {

    /**
     * 
     */
    private PrinterFactory() {
	// TODO Auto-generated constructor stub
    }

    public static final PrinterService getPrinterService(String type) {
	
	return new IPPrinterService();
    }

}
