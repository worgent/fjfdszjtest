package com.apricot.eating.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.table.DefaultTableModel;


public class Printer implements Printable {
	
private DefaultTableModel dtm = new DefaultTableModel();
private String title = "";
private String printerName = "";
	
	public Printer(DefaultTableModel table,String title,String printerName)
	{
		this.dtm = table;
		this.title = title;
		this.printerName = printerName;
	}

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setPaint(Color.black);
		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//		Font font = new Font("黑体", Font.ITALIC, 14);
		Font font = new Font("仿宋", Font.BOLD, 10);
		g2.setFont(font);
		g2.drawString(title, 10, 40);
		Object[] obj ={0,70,100};
		for(int i = 0;i < dtm.getRowCount();i ++)
		{
			for(int ii = 0;ii < dtm.getColumnCount();ii ++)
			{
				String   cell;   
				Object   original=dtm.getValueAt(i,ii);   
				if(original==null)   cell="";   
				else   cell=original.toString();
				
				font = new Font("仿宋", Font.BOLD, 10);
				g2.setFont(font);
				g2.drawString(cell,(Integer)obj[ii], 55 + i * 15);
			}
		}
		return Printable.PAGE_EXISTS;
	}

	public String print() {
		String message = "0";
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		Book book = new Book();

		PageFormat pageFormat = printerJob.defaultPage();
		Paper paper = new Paper();
		paper.setImageableArea(20, 5, 150, 400);// A4(595 X	// 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
		pageFormat.setPaper(paper);

		book.append(new Printer(dtm,title,printerName), pageFormat);
		printerJob.setPageable(book);
		HashAttributeSet hs = new HashAttributeSet();
//		String printerName = "Grandi Pos58 III Printer";
//		String printerName = "XP-80III+";
		hs.add(new PrinterName(printerName, null));
		PrintService[] pss = PrintServiceLookup.lookupPrintServices(null, hs);
		if (pss.length == 0) {
			message = "无法找到打印机:" + printerName;
			return message;
		}
		try {
			printerJob.setPrintService(pss[0]);
			printerJob.print();
		} catch (PrinterException ex) {
			System.out.println(ex.getMessage());
		}
		return message;
	}

}
