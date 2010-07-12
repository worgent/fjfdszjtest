/**
 * 
 */
package com.apricot.eating.print.io;

import com.apricot.eating.print.PrintDocumentStream;

/**
 * @author xudahu
 * 
 */
public class CancelFoodStream extends PrintDocumentStream {

    /**
     * 
     */
    public CancelFoodStream() {
	// TODO Auto-generated constructor stub
    }


    /*
     * (non-Javadoc)
     * 
     * @see com.apricot.eating.print.PrintDocumentStream#getBOReadMethod()
     */
    protected String getBOReadMethod() {
	// TODO Auto-generated method stub
	return "readCancelFoodDocument";
    }


}
