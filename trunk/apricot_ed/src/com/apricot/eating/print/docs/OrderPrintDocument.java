/**
 * 
 */
package com.apricot.eating.print.docs;

import com.apricot.eating.print.PrintDocument;

/**
 * @author xudahu
 *
 */
public class OrderPrintDocument extends PrintDocument {

    /**
     * 
     */
    public OrderPrintDocument() {
	// TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.apricot.eating.print.PrintDocument#release()
     */
    public void release() {
	// TODO Auto-generated method stub

    }
    
    private StringBuffer content=new StringBuffer();
    public void append(String food){
	this.content.append(food);
    }
    
    private StringBuffer id=new StringBuffer();
    public void add(String id){
	if(this.id.length()>0) this.id.append(",");
	this.id.append(id);
    }
    
    public String getId(){
	return this.id.toString();
    }

    /* (non-Javadoc)
     * @see com.apricot.eating.print.PrintDocument#toByteArray()
     */
    public byte[] toByteArray() {
	// TODO Auto-generated method stub
	return content.toString().getBytes();
    }

}
