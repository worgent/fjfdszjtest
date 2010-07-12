/**
 * 
 */
package com.apricot.eating.print;

/**
 * @author xudahu
 * 
 */
public abstract class PrintDocument {

    public abstract byte[] toByteArray();

    private String id;    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the printer
     */
    public Printer getPrinter() {
        return printer;
    }

    /**
     * @param printer the printer to set
     */
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    private int flag;
    
    private Printer printer;
    
    public abstract void release();

    /**
     * @return the flag
     */
    public final int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public final void setFlag(int flag) {
        this.flag = flag;
    }
}
