/**
 * Copyright (C) qzgf, 2010
 *
 * License        :Apache License 2.0
 * Project        :womobile
 * Package        :word
 * File	         :Test.java
 * Written by     :fjfdszj
 * Created Date   :Oct 9, 2010
 * Purpose        :功能描述

======================================

 * Modifyer by    :fjfdszj
 * Update Date    :Oct 9, 2010
 * Purpose        :描述

 */
package word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.ParagraphProperties;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     Test.java
 *
 */
public class Test {
	public static void main(String[] args) {
		try {
			//原文件模板
			String srcword="H:\\szjwork\\sysback\\桌面\\newwork\\工作计划\\车辆管理\\模板用户手册.doc";
			String targetword="D:\\generator-output\\模板用户手册.doc";

			//新建 HWPFDocument 对象，读入doc文件   
			HWPFDocument doc = new HWPFDocument(new FileInputStream(srcword));
		
			//得到整个doc文档的Range，可以理解为文档对象   
			Range r = doc.getRange();

			System.out.println("Example you supplied:");
			System.out.println("---------------------");

			String text = new String("");
			//得到整个文档里面的所有纯文字，包含回车换行。一段是一行   
			text = r.text();
			System.out.println(text);   
	
			//测试写入数据
//			OutputStream outdoc = new FileOutputStream(targetword);
//
//			outdoc.write(text.getBytes());
//
//			outdoc.flush();
//			outdoc.close();

			

			//得到整个文档的分节数。一般只有一节，排版很漂亮的word文档一般分为多节   
			System.out.println("numSections: " + r.numSections());
			
			
			//得到倒数第一节的Section对象   
			Section section = r.getSection(r.numSections() - 1);
			
		
			
			//得到该节里面的段落数   
			System.out.println(section.numParagraphs());
			System.out.println("numParagraphs: " + section.numParagraphs());

			String searchText = "${menu}";
			String replacementText = "数据字典";
			System.out.println("//**********************************");  
			System.out.println(section.getParagraph(section.numParagraphs()-1).text());  
			System.out.println("//**********************************");  
			//循环得到每一段落的文字。这个跟Range.text()是不同的。   
			for (int np = 0; np < section.numParagraphs(); np++) {
				Paragraph para = section.getParagraph(np);
				ParagraphProperties pp=para.cloneProperties();
				
				//得到该段落的文字   
				text = para.text();
				System.out.println(Integer.toString(np) + ":" + text);   
				int offset = text.indexOf(searchText);
				if (offset >= 0) {
					System.out.println(Integer.toString(np) + ":" + para.text());
					//如果找到了，就进行文字的替换。replaceText只能针对段落   
					//para.replaceText(searchText, replacementText);
					para.replaceText(searchText, replacementText);
					para.text();
				}
			}

			//写入到新的doc文件   
			
			org.apache.poi.hwpf.extractor.WordExtractor exdoc = new WordExtractor(new FileInputStream(srcword)); 
			System.out.println(exdoc.getTextFromPieces());
			
			//完全拷备
			//copyword();
			
			
			OutputStream outdoc = new FileOutputStream(targetword);
			doc.write(outdoc);

			outdoc.flush();
			outdoc.close();
			

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/**
	 * 
	 * Purpose      : 读取word的内容
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getContents(File file) throws FileNotFoundException,
			IOException {
		WordExtractor wordExtractor = new WordExtractor(new FileInputStream(
				file));

		return wordExtractor.getTextFromPieces();
	}
	
	
	//拷备word文件
	public static void copyword()
	{
		//原文件模板
		String srcword="H:\\szjwork\\sysback\\桌面\\newwork\\工作计划\\车辆管理\\模板用户手册.doc";
		String targetword="D:\\generator-output\\模板用户手册.doc";
		try {
			POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(srcword));
			
			
			
			HWPFDocument doc = new HWPFDocument(fs);
			//得到整个doc文档的Range，可以理解为文档对象   
			Range r = doc.getRange();

			
			OutputStream outdoc = new FileOutputStream(targetword);
			fs.writeFilesystem(outdoc);

			outdoc.flush();
			outdoc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
