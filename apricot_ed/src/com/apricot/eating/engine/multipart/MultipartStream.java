/**
 * 获取前台通过二进制将请求数据传送过来的处理，获取请求数据的Map PS:
 * 文件上传是通过multipart/form-data的方式进行数据传输，在传输过来的数据里面是通过\r\n的方式进行数据换行处理的。
 */
package com.apricot.eating.engine.multipart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class MultipartStream {
	private Map map;
	private InputStream in;
	private int boundaryLength = 0;
	private byte[] boundary;
	private final byte CR = 0x0A;
	/**
	 * The Line Feed ASCII character value.
	 */
	private final byte LF = 0x0D;
	private final int bufferSize = 10240;
	private byte[] buffer = new byte[bufferSize];

	private List files;

	/**
	 * 
	 */
	public MultipartStream() {
		super();
		this.map = (Map) new HashMap();
		this.mark = 0;
		this.bufferLength = 0;
		this.files = new ArrayList();
	}

	/**
	 * 获取请求数据的Map；
	 * 
	 * @return
	 */
	public Map getParameterMap() {
		return this.map;
	}

	/**
	 * 判断两个Byte数组的前面多少位是否相同
	 * 
	 * @param a
	 * @param b
	 * @param count
	 * @return
	 */
	private boolean arrayequals(byte[] a, byte[] b, int count) {
		for (int i = 0; i < count; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	private int mark;
	private int bufferLength;

	private int readByte() throws IOException {
		int i = in.read(buffer, bufferLength, 1);
		if (i > 0) {
			this.bufferLength++;
			this.mark++;
		}
		return i;
	}

	/**
	 * 读数据到缓冲Buffer里面
	 * 
	 * @return
	 * @throws IOException
	 */
	private boolean readLine() throws IOException {
		bufferLength = 0;
		buffer = null;
		buffer = new byte[this.bufferSize];
		while (readByte() > 0) {
			if (buffer[bufferLength - 1] == LF) {
				if (readByte() <= 0)
					return false;
				if (buffer[bufferLength - 1] == CR)
					return true;
			}
			if (bufferLength >= bufferSize - 1)
				return true;
		}
		return false;
	}

	private void readBoundary() throws IOException {
		if (!readLine())
			throw new IOException("No more datas can be read.");
		boundaryLength = bufferLength - 2;
		boundary = new byte[boundaryLength];
		System.arraycopy(buffer, 0, boundary, 0, boundaryLength);
	}

	/**
	 * 初始化输入输出流
	 * 
	 * @param in
	 */
	public void upload(InputStream in) throws Exception {
		this.in = in;
		// 读取分隔字符串
		this.readBoundary();
		// 初始化输入流为
		HashMap tempMap = new HashMap(); //
		String key;
		try { // 循环取数据
			while (read(tempMap)) {
			}
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (Exception e) {
		} // 将循环取出来的数据进行整理 String key = null;
		ArrayList arr = null;
		for (Iterator keys = tempMap.keySet().iterator(); keys.hasNext();) {
			key = (String) keys.next();
			arr = (ArrayList) tempMap.get(key);
			if (arr.size() == 0)
				continue;

			this.map.put(key, arr.toArray(new String[arr.size()]));

			arr.clear();
			arr = null;
		}
		tempMap.clear();
		tempMap = null;

		try {
			in.close();
		} catch (Exception e) {
		}
		;
		in = null;
	}

	private boolean isBoundary() {
		return arrayequals(boundary, buffer, boundaryLength);
	}

	/**
	 * 读取单个元素数据
	 * 
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	private boolean read(HashMap tempMap) throws IOException {
		// 读取第一行
		if (!readLine())
			return false;
		String line = new String(buffer, 0, bufferLength - 2, "GB2312");

		MultipartData data = getMultipartData(line);
		byte[] lastBuffer = null;
		// 保存数据到参数列表
		ArrayList arr = (ArrayList) tempMap.get(data.getName());
		// 如果是文件模式就丢弃文件类型这一行
		if (data.isFile()) {
			readLine();
		}
		// 丢弃参数换行
		readLine();
		// 处理参数
		if (arr == null) {
			arr = new ArrayList();
			tempMap.put(data.getName(), arr);
		}
		readLine();
		do {
			// 数据已经读完
			if (bufferLength == 0)
				return false;
			// 表示当前数据为空，这样要判断是否是结束符为空的问题
			// if (bufferLength == 2) {
			// lastBuffer = new byte[]{LF, CR};
			// readLine();
			// }
			if (isBoundary()) {
				if (lastBuffer != null)
					data.appendValue(lastBuffer, 0, lastBuffer.length - 2);
				data.freeze();
				arr.add(data.getValue());
			
				return true;
			}
			// data.appendValue(lastBuffer, 0, 2);
			// data.appendValue(buffer, 0, bufferLength);
			if (lastBuffer != null)
				data.appendValue(lastBuffer, 0, lastBuffer.length);
			lastBuffer = new byte[bufferLength];
			System.arraycopy(buffer, 0, lastBuffer, 0, bufferLength);
			readLine();
		} while (bufferLength > 0);
		return false;
	}

	private MultipartData getMultipartData(String line) throws IOException {
		MultipartData data = new MultipartData();

		if (line.indexOf("filename=\"") > 0)
			data = new MultipartFile();
		else
			data = new MultipartData();

		line = line.replaceAll("Content-Disposition: form-data; ", "");
		line = line.replaceAll("\"; ", ",");
		line = line.replaceAll("\"", "");
		String[] args = line.split(",");
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("name=")) {
				data.setName(args[i].replaceFirst("name=", ""));
			}
			if (args[i].startsWith("filename=")) {
				data.setFileName(args[i].replaceFirst("filename=", ""));
			}
		}
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	protected void clear() {
		// TODO Auto-generated method stub
		this.map.clear();
		this.map = null;
		this.boundary = null;
		this.buffer = null;
		this.files.clear();
	}

	/**
	 * 下载文件，根据要求
	 * 
	 * @param file
	 * @param out
	 * @param start
	 * @throws IOException
	 */
	public void download(File f, OutputStream out, long start)
			throws IOException {
		FileInputStream in = null;
		try {
			in = new FileInputStream(f);
			if (start > 0)
				in.skip(start);
			byte[] buff = new byte[1024];
			int i = 0;
			while ((i = in.read(buff)) > 0) {
				out.write(buff, 0, i);
				out.flush();
			}
			in.close();
			in = null;
			out.close();
			out = null;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
			}
		}
	}

	public Iterator getFiles() {
		return files.iterator();
	}
}
