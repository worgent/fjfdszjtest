/**
 * 
 */
package com.apricot.eating.print;

/**
 * @author xudahu
 * 
 */
public interface PrinterService {
    public static final int NO_CONNECT=1;
    public static final int OUT_BLOCK=2;
    public static final int IN_BLOCK=3;
    public static final int WRITE_ERROR=4;
    public static final int READ_ERROR=5;
    public static final int CHECK_PRINT_ERROR=6;
    public static final int PRINTER_OFFLINE=7;
    public static final int COVER_OPEN=8;
    public static final int ERROR_BLADE=9;
    public static final int ERROR_COVERENABLE=10;
    public static final int ERROR_AUTOCOVER=11;
    public static final int NO_PAPER=12;
    public static final int ERROR_OTHER=13;
    public int print(PrintDocument doc);
    public void close();
}
