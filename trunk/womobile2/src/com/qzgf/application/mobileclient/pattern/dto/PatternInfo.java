package com.qzgf.application.mobileclient.pattern.dto;
import java.util.List;
public class PatternInfo {
	private String fieldName="";
	private String fieldDesc="";
	private String fieldType="";
	private String fieldLength="";
	private String fieldEnum="";
	private List<DictInfo> details=null;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getFieldEnum() {
		return fieldEnum;
	}
	public void setFieldEnum(String fieldEnum) {
		this.fieldEnum = fieldEnum;
	}
	public List<DictInfo> getDetails() {
		return details;
	}
	public void setDetails(List<DictInfo> details) {
		this.details = details;
	}
	
}
