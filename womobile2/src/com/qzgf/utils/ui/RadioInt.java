package com.qzgf.utils.ui;

/**
 * 用于单选按扭
 * @author lsr
 * 
 */
public class RadioInt {

	int key;
	String value;

	public RadioInt() {

	}

	public RadioInt(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
