/**
 * 
 */
package com.apricot.eating.engine.multipart;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

/**
 * @author Administrator
 */
public class MultipartData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9143089958604597622L;

	/**
	 * 
	 */
	protected MultipartData(boolean file) {
		super();
		this.file = file;
		this.freeze = false;
		this.nullFile = false;
	}

	public MultipartData() {
		this(false);
	}

	protected boolean freeze;

	public void freeze() {
		this.freeze = true;
		try {
			if (byteOut != null) {
				if (isFile()){
					value = new sun.misc.BASE64Encoder().encodeBuffer(byteOut
							.toByteArray());
				
				new FileOutputStream("e:/refresh1.gif").write(new sun.misc.BASE64Decoder().decodeBuffer(value));
				}else
					value = new String(byteOut.toByteArray());

			}
		} catch (Exception e) {
		}
		try {
			if (byteOut != null)
				byteOut.close();
		} catch (Exception e) {
		}
		byteOut = null;
	}

	private boolean file;

	public boolean isFile() {
		return this.file;
	}

	private String value;
	private String name;
	private String fileName;
	private boolean nullFile;
	private ByteArrayOutputStream byteOut;

	/**
	 * @return Returns the fileName.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            The fileName to set.
	 */
	public void setFileName(String fileName1) {
		if (freeze)
			return;
		if (fileName1 == null)
			this.nullFile = true;
		if (fileName1.trim().length() == 0)
			this.nullFile = true;
		this.fileName = fileName1;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		if (freeze)
			return;
		this.name = name;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void appendValue(byte[] val, int i, int l) {
		if (freeze)
			return;
		if (val == null || l <= 0)
			return;
		if (byteOut == null)
			byteOut = new ByteArrayOutputStream();
		try {
			byteOut.write(val, i, l);
		} catch (Exception e) {
		}
	}

	/**
	 * @return Returns the nullFile.
	 */
	public boolean isNullFile() {
		return nullFile;
	}
}
