/**
 * 
 */
package com.apricot.eating.engine;

/**
 * @author Administrator
 */
public class PlugInConfig {
	private String className;
	private String desc;
	private boolean disable;
	private String name;
	private String runStyle;
	private boolean global;

	/**
	 * 
	 */
	public PlugInConfig() {
		// TODO Auto-generated constructor stub
	}

	public boolean equals(Object obj) {
		PlugInConfig c = (PlugInConfig) obj;
		if (c == null)
			return false;
		if (c.name == null)
			return false;
		// TODO Auto-generated method stub
		return c.name.equals(this.name);
	}

	public String getClassName() {
		return className;
	}

	public String getDesc() {
		return desc;
	}

	public String getName() {
		return name;
	}

	public String getRunStyle() {
		return runStyle;
	}

	public boolean isDisable() {
		return disable;
	}

	public boolean isInit() {
		return "init".equalsIgnoreCase(runStyle) || "both".equalsIgnoreCase(runStyle);
	} 

	public boolean isInvoke() {
		return "invoke".equalsIgnoreCase(runStyle) || "both".equalsIgnoreCase(runStyle);
	}

	public boolean isGlobal() {
		return global;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setDisable(boolean d) {
		this.disable = d;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRunStyle(String r) {
		if (this.runStyle != null && this.runStyle.trim().length() > 0)
			return;
		this.runStyle = r;
	}

	public void setGlobal(boolean g) {
		this.global = g;
	}

	public String toString() {
		return "" + global + "=" + runStyle + "=" + disable;
	}
}
