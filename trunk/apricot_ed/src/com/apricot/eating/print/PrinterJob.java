/**
 * 
 */
package com.apricot.eating.print;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.apricot.eating.MessageResource;
import com.apricot.eating.engine.Controller;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.RequestConfig;
import com.apricot.eating.print.bo.PrinterBO;
import com.apricot.eating.util.DataUtil;

/**
 * @author xudahu
 * 
 */
public class PrinterJob extends Thread {

    private HashMap cache = new HashMap();
    public static final String KEY = "com.apricot.eating.impl.PrinterServiceDispatcher.PrinterJob";
    private int mode;

    private int queueSize = 5;

    private RequestConfig request;
    private boolean runing = true;
    private int timesteep = 60 * 1000;
    private String stream;
    private Properties property = new Properties();

    public HashMap getParameters() {
	return new HashMap(property);
    }

    /**
     * @return the stream
     */
    public String getStream() {
	return stream;
    }

    /**
     * @param stream
     *            the stream to set
     */
    public void setStream(String stream) {
	this.stream = stream;
    }

    public PrinterJob(RequestConfig request) {
	super();
	this.request = request;
	this.request.setClassName(PrinterBO.class.getName());
	this.load();
    }

    private DyncParameterMap getMap() {
	DyncParameterMap map = new DyncParameterMap();
	map.set("size", queueSize);
	map.setGlobalStaticDataMap(this.request.getGlobalStaticData());
	return map;
    }

    /**
     * @return the mode
     */
    public int getMode() {
	return mode;
    }

    /**
     * @return the queueSize
     */
    public int getQueueSize() {
	return queueSize;
    }

    /**
     * @return the timesteep
     */
    public int getTimesteep() {
	return timesteep;
    }

    public void interrupt() {
	// TODO Auto-generated method stub
	super.interrupt();
	this.runing = false;
    }

    private String setfile = "cfg/print.properties";

    public final OutputStream getPropertiesWriter() throws FileNotFoundException{
	File f=new File(setfile);
	if(!f.exists()) f.getParentFile().mkdirs();
	System.out.println(f.getAbsolutePath());
	return new FileOutputStream(f);
    }
    
    public final void load() {

	try {
	    File f = new File(setfile);
	    if (f.exists()) {
		property.load(new FileInputStream(f));
	    } else

		property.load(getClass().getResourceAsStream("print.properties"));
	} catch (Exception e) {
	}

	for (Iterator keys = property.keySet().iterator(); keys.hasNext();) {
	    String key = (String) keys.next();
	    String v = property.getProperty(key);
	    if (DataUtil.isNull(v))
		continue;
	    v = DataUtil.toSystemCharset(v);
	    DataUtil.setObject(this, key, v);
	}
    }

    private void print(List docs) {
	if (docs == null)
	    return;

	for (Iterator objs = docs.iterator(); objs.hasNext();) {
	    PrintDocument doc = (PrintDocument) objs.next();
	    PrinterService ps = (PrinterService) cache.get(doc.getPrinter().getKey());
	    if (ps == null) {
		ps = PrinterFactory.getPrinterService(doc.getPrinter().getType());
		cache.put(doc.getPrinter().getKey(), ps);
	    }

	    doc.setFlag(ps.print(doc));

	    Printer p = doc.getPrinter();

	    if (doc.getFlag() > 0) {
		StringBuffer sb = new StringBuffer();
		sb.append("(时间:").append(DataUtil.formatDateTime(DataUtil.getCurrDateTime(), "HH点mm分ss秒)"));

		try {
		    this.printers.put(p.getLabel(), MessageResource.getInstance().getMessage(
			    new StringBuffer("printer_").append(doc.getFlag()).toString(),
			    new String[] { sb.toString() }));
		} catch (Exception e) {
		}
	    } else {
		this.printers.remove(p.getLabel());
	    }

	}
    }

    /**
     * @return the printers
     */
    public List getPrinters() {
	ArrayList arr = new ArrayList();
	for (Iterator keys = this.printers.keySet().iterator(); keys.hasNext();) {
	    String key = (String) keys.next();
	    String v = (String) this.printers.get(key);
	    HashMap m = new HashMap();
	    m.put("printer", key);
	    m.put("status", v);
	    arr.add(m);
	}
	return arr;
    }

    public void run() {
	// TODO Auto-generated method stub

	while (this.runing) {

	    String[] args = this.stream.split("[,]");
	    for (int i = 0; i < args.length; i++) {
		print(args[i]);
	    }
	    System.out.println(this.printers);
	    try {
		Thread.sleep(this.timesteep);
	    } catch (Exception e) {
	    }
	}

	for (Iterator ps = cache.keySet().iterator(); ps.hasNext();) {
	    PrinterService p = (PrinterService) ps.next();
	    p.close();
	}

	cache.clear();
    }

    private void print(String streamClass) {
	StringBuffer clzz = new StringBuffer("com.apricot.eating.print.io.").append(streamClass);
	PrintDocumentStream stream = null;
	try {
	    stream = (PrintDocumentStream) Class.forName(clzz.toString()).newInstance();
	} catch (Exception e) {
	}
	if (stream == null)
	    return;
	// 设置初始信息
	for (Iterator keys = property.keySet().iterator(); keys.hasNext();) {
	    String key = (String) keys.next();
	    String v = property.getProperty(key);
	    if (DataUtil.isNull(v))
		continue;
	    if (!key.startsWith(streamClass))
		continue;
	    key = key.replace(new StringBuffer(streamClass).append(".").toString(), "");
	    DataUtil.setObject(stream, key, v);
	}
	//

	List docs = null;
	try {
	    docs = stream.read(this.request);
	    this.print(docs);
	    stream.write(docs, this.request);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	stream = null;
	if (docs != null)
	    clear(docs);

    }

    private void clear(List docs) {
	for (Iterator objs = docs.iterator(); objs.hasNext();) {
	    PrintDocument doc = (PrintDocument) objs.next();
	    doc.release();
	}
	docs.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Thread#interrupt()
     */

    /**
     * @param mode
     *            the mode to set
     */
    public void setMode(int mode) {
	this.mode = mode;
    }

    /**
     * @param queueSize
     *            the queueSize to set
     */
    public void setQueueSize(int queueSize) {

	this.queueSize = queueSize;
    }

    /**
     * @param timesteep
     *            the timesteep to set
     */
    public void setTimesteep(int timesteep) {
	this.timesteep = timesteep;
    }

    private HashMap printers = new HashMap();

}
