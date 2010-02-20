package YzSystem.J_System;

import java.io.*;
import java.util.*;

public class BeanSystemFunctionCode implements Serializable {
	// Fields

	private String theCode="";
	private String theName="";
	private String parentCode="";
	private String childFlag="0";
	private String remark="";
	private Integer theState=1;
	private Integer isUse=0;
	private String creater;
	private String createTime;
	private String stater;
	private String stateTime;
	private String editer;
	private String editeTime;

	
    private ArrayList childSystemFunctionCode = null; 
    private BeanSystemFunctionCode father = null; // 父节点
    private String refPage = "";         // 参考页面
    private String theShortCode=""; 	  //#类名
    private String img = "";             // 图片名
    
	// Constructors
	/** default constructor */
	public BeanSystemFunctionCode() {
	}
	
	
	// Property accessors
	
	public ArrayList getChildSystemFunctionCode() {
		return this.childSystemFunctionCode;
	}

	public void setChildSystemFunctionCode(ArrayList ChildSystemFunctionCode) {
        this.childSystemFunctionCode = ChildSystemFunctionCode;
        Iterator itx=ChildSystemFunctionCode.iterator();
        while (itx.hasNext()){
        	BeanSystemFunctionCode aFunction=(BeanSystemFunctionCode)itx.next();
        	aFunction.setFather(this);
        }
	}
	
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getRefPage() {
		return this.refPage;
	}

	public void setRefPage(String refpage) {
		this.refPage = refpage;
	}
	

	
	public BeanSystemFunctionCode getFather() {
		return this.father;
	}

	public void setFather(BeanSystemFunctionCode functionCode) {
		this.father = functionCode;
	}
	
	public String getTheShortCode() {
		return this.theShortCode;
	}

	public void setTheShortCode(String theshortcode) {
		this.theShortCode = theshortcode;
	}

	public String getTheCode() {
		return this.theCode;
	}

	public void setTheCode(String theCode) {
		this.theCode = theCode;
	}

	public String getTheName() {
		return this.theName;
	}

	public void setTheName(String theName) {
		this.theName = theName;
	}

	public String getParentCode() {
		return this.parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getChildFlag() {
		return this.childFlag;
	}

	public void setChildFlag(String childFlag) {
		this.childFlag = childFlag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTheState() {
		return this.theState;
	}

	public void setTheState(Integer theState) {
		this.theState = theState;
	}

	public Integer getIsUse() {
		return this.isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStater() {
		return this.stater;
	}

	public void setStater(String stater) {
		this.stater = stater;
	}

	public String getStateTime() {
		return this.stateTime;
	}

	public void setStateTime(String stateTime) {
		this.stateTime = stateTime;
	}

	public String getEditer() {
		return this.editer;
	}

	public void setEditer(String editer) {
		this.editer = editer;
	}

	public String getEditeTime() {
		return this.editeTime;
	}

	public void setEditeTime(String editeTime) {
		this.editeTime = editeTime;
	}


}
