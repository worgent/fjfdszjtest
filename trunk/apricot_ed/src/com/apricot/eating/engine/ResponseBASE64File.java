/**
 * 
 */
package com.apricot.eating.engine;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.apricot.eating.log.Log;
import com.apricot.eating.util.DataUtil;

/**
 * @author Administrator
 * 
 */
public class ResponseBASE64File {

	/**
	 * 
	 */
	public ResponseBASE64File() {
		// TODO Auto-generated constructor stub
		
	}

	public ResponseBASE64File(String contentType) {
		super();
		this.contentType = contentType;
	}

	private String contentType = "image/gif";

	private String content;

	public byte[] getContent(String path) {
		
		if (DataUtil.isNull(pathName))
			return null;
		
		if (DataUtil.isNull(content)) {
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 10);
			InputStream in = null;
			try {
				in = new FileInputStream(path);
				byte[] b = new byte[1024];
				int l = 0;
				while ((l = in.read(b)) > 0) {
					out.write(b, 0, l);
				}
			} catch (Exception e) {
				Log.debug("初始文件出错", e);
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (Exception e1) {
				}
				try {
					if (out != null)
						out.close();
				} catch (Exception e1) {
				}
			}
			return out.toByteArray();
		}
		
		try{
			return new sun.misc.BASE64Decoder().decodeBuffer(content);
		}catch(Exception e){}
        //64位编码
	    return null;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String pathName="/img/food_default.gif";

	public String getPathName() {
		return pathName;
	}

	public String getContentType() {
		return contentType;
	}

}
