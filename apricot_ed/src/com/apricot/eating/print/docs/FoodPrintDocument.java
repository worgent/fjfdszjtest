/**
 * 
 */
package com.apricot.eating.print.docs;

import com.apricot.eating.print.PrintDocument;

/**
 * @author xudahu
 *
 */
public class FoodPrintDocument extends PrintDocument {

    /**
     * 
     */
    public FoodPrintDocument() {
	// TODO Auto-generated constructor stub
    }
    

    /* (non-Javadoc)
     * @see com.apricot.eating.print.PrintDocument#release()
     */
    public void release() {
	// TODO Auto-generated method stub

    }
    
    /**
     * 
     * @param str
     */
    public void setContent(String str){
	 this.str=str;
    }
    
    private String str;

    /* (non-Javadoc)
     * @see com.apricot.eating.print.PrintDocument#toByteArray()
     */
    public byte[] toByteArray() {
	// TODO Auto-generated method stub
	return (str!=null)?str.getBytes():"".getBytes();
    }
    
    

}
