package com.qzgf.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

import sun.misc.BASE64Encoder;

import sun.misc.BASE64Decoder;

//import org.apache.tools.zip.*;

public class FileZip {
	/**

	 * 压缩文件

	 * @param inputFileName 要压缩的文件或文件夹路径，例如：c:\\a.txt,c:\\a\

	 * @param outputFileName 输出zip文件的路径，例如：c:\\a.zip

	 */
	public static void zip(String inputFileName, String outputFileName)
			throws Exception {

		FileOutputStream fos = new FileOutputStream("db");
		GZIPOutputStream gz = new GZIPOutputStream(fos);
		ObjectOutputStream oos = new ObjectOutputStream(gz);
		oos.writeObject("");
		oos.flush();
		oos.close();
		fos.close();

	}

	public static void unzip(String inputFileName, String outputFileName)
			throws Exception {

		FileInputStream fis = new FileInputStream("db");
		GZIPInputStream gs = new GZIPInputStream(fis);
		ObjectInputStream ois = new ObjectInputStream(gs);
		//Employee sarah = (Employee) ois.readObject();
		//Employee sam = (Employee) ois.readObject();
		//print the records after reconstruction of state
		//sarah.print();
		//sam.print();
		ois.close();
		fis.close();
	}

	public static String compress(String s) throws IOException {
		ByteArrayInputStream input = new ByteArrayInputStream(s
				.getBytes("UTF-8"));
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		GZIPOutputStream gzout = new GZIPOutputStream(output);

		byte[] buf = new byte[1024];
		int number;

		while ((number = input.read(buf)) != -1) {
			gzout.write(buf, 0, number);
		}

		gzout.close();
		input.close();

		String result = new BASE64Encoder().encode(output.toByteArray());

		output.close();

		return result;
	}

	public static String decompress(String data) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
		ByteArrayInputStream input = new ByteArrayInputStream(
				new BASE64Decoder().decodeBuffer(data));
		GZIPInputStream gzinpt = new GZIPInputStream(input);
		byte[] buf = new byte[1024];
		int number = 0;

		while ((number = gzinpt.read(buf)) != -1) {
			output.write(buf, 0, number);
		}

		gzinpt.close();
		input.close();

		String result = new String(output.toString("UTF-8"));

		output.close();

		return result;

	}
	/**
	 * 2008-11-09
	 * Author:fjfdszj
	 * 功能说明:将List对象压缩转化为byte[]
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] compressList(List data) throws IOException {
		byte[] result=null;
		//序列化使用的输出流 
		ByteArrayOutputStream o = new ByteArrayOutputStream();
		
		GZIPOutputStream gzout=new GZIPOutputStream(o);
		
		//建立对象序列化输出流
		ObjectOutputStream out = new ObjectOutputStream(gzout);
		out.writeObject(data);
		out.flush();
		out.close();
		gzout.close();
		result=o.toByteArray();
		o.close();
		return result;

	}
	/**
	 * 2008-11-09
	 * Author:fjfdszj
	 * 功能说明:将byte[]数据解压成List对象于上面过程逆向
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static List uncompressList(byte[] data) throws IOException {
		List result=null;
		
		ByteArrayInputStream i = new ByteArrayInputStream(data);
		
		GZIPInputStream gzin=new GZIPInputStream(i);
		
		ObjectInputStream in = new ObjectInputStream(gzin);
		
		try {
			result=(List)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		i.close();
		gzin.close();
		in.close();
		return result;
	}
	
	/**
	 * 2008-11-09
	 * Author:fjfdszj
	 * 功能说明:将List不压缩转化为byte[]
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] ListToByte(List data) throws IOException {
		byte[] result=null;
		//序列化使用的输出流 
		ObjectOutputStream OOS=null; 
		//序列化后数据流给ByteArrayOutputStream 来保存。 
		//ByteArrayOutputStream 可转成字符串或字节数组 
		ByteArrayOutputStream BAOS=new ByteArrayOutputStream();
		
		OOS=new ObjectOutputStream(BAOS);
		OOS.writeObject(data);
		OOS.close();
		result=BAOS.toByteArray();
        BAOS.close();
		return result;

	}
	/**
	 * 2008-11-09
	 * Author:fjfdszj
	 * 功能说明:将byte[]转化为List
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static List  ByteToList(byte[] data) throws IOException {
		List result=null;
		//ByteArrayInputStream 可接收一个字节数组"byte[]"。供反序列化做参数 
		ByteArrayInputStream BAIS=null; 
		//反序列化使用的输入流 
		ObjectInputStream OIS=null;
		BAIS=new ByteArrayInputStream(data);
		OIS=new ObjectInputStream(BAIS);
		try {
			result=(List)OIS.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BAIS.close();
		OIS.close();
		return result;

	}
	
	/**
	 * 2008-11-09
	 * Author:fjfdszj
	 * 功能说明:将List不压缩转化为byte[]
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] XmlToByte(Object data) throws IOException {
		byte[] result=null;
		ObjectOutputStream OOS=null; 
		ByteArrayOutputStream BAOS=new ByteArrayOutputStream();
		OOS=new ObjectOutputStream(BAOS);
		OOS.writeObject(data);
		OOS.close();
		result=BAOS.toByteArray();
        BAOS.close();
		return result;
	}
	/**
	 * 2008-11-09
	 * Author:fjfdszj
	 * 功能说明:将byte[]转化为List
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static Object ByteToXml(byte[] data) throws IOException {
		Object result=null;
		ByteArrayInputStream BAIS=null; 
		ObjectInputStream OIS=null;
		BAIS=new ByteArrayInputStream(data);
		OIS=new ObjectInputStream(BAIS);
		try {
			result=(Object)OIS.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		BAIS.close();
		OIS.close();
		return result;

	}
	//测试　
	public static void main(String[] args)
	{
		List testLs=new ArrayList();
		testLs.add("MyName");
		byte[] testbtbyte=null;
		try {
			testbtbyte=compressList(testLs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			List ls=uncompressList(testbtbyte);
			testLs=ls;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		


}
