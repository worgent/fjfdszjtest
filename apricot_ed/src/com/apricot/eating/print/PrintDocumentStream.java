/**
 * 
 */
package com.apricot.eating.print;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.apricot.app.eating.print.PrintBo;
import com.apricot.eating.NestedException;
import com.apricot.eating.engine.Controller;
import com.apricot.eating.engine.DyncParameterMap;
import com.apricot.eating.engine.RequestConfig;
import com.apricot.eating.print.bo.PrinterBO;

/**
 * @author xudahu
 * 
 */
public abstract class PrintDocumentStream {
    public final List read(RequestConfig requestConfig) throws NestedException {
	DyncParameterMap map = new DyncParameterMap();
	map.setGlobalStaticDataMap(requestConfig.getGlobalStaticData());
	requestConfig.setClassName(getBOReadClass());
	requestConfig.setMethodName(getBOReadMethod());

	Printer p = getPrinter();
	if (p != null)
	    map.put("printer", p);

	try {
	    return (List) new Controller(requestConfig).service(map);
	} catch (Exception e) {
	    e.printStackTrace();
	    return new ArrayList();
	}
    }

    protected Printer getPrinter() {
	return null;
    }

    protected abstract String getBOReadMethod();

    protected String getBOReadClass() {
	return PrinterBO.class.getName();
    }

    protected String getBOWriteMethod() {
	return "writeDocument";
    }

    protected String getBOWriteClass() {
	return PrinterBO.class.getName();
    }

    public final void write(List docs, RequestConfig requestConfig) {
	requestConfig.setClassName(getBOWriteClass());
	requestConfig.setMethodName(getBOWriteMethod());
	DyncParameterMap map = new DyncParameterMap();
	for (Iterator objs = docs.iterator(); objs.hasNext();) {
	    PrintDocument doc = (PrintDocument) objs.next();
	    map.add("id", doc.getId());

	    map.add("flag", String.valueOf(doc.getFlag()));
	    map.add("type", doc.getPrinter().getType());
	}

	try {
	    new Controller(requestConfig).service(map);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	docs.clear();
    }
}
