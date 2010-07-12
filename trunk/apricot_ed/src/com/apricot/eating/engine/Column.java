/**
 * 
 */
package com.apricot.eating.engine;

/**
 * @author Administrator
 */
public class Column {
	public static final int SEQUENCE = 1;
	public static final int MAX = 2;
	public static final int AUTO_INCREMENT = 3;
	public static final int DEFAULT_VALUE = 4;
	private String name;
	private String defaultValue;
	private int defaultValueType;
	private String type;
	private boolean primaryKey;
	private boolean notNull;
	private boolean whereColumn;
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isWhereColumn() {
		return whereColumn;
	}

	public void setWhereColumn(boolean whereColumn) {
		this.whereColumn = whereColumn;
	}

	/**
	 * 
	 */
	public Column(String name, String defaultValue, int seqType) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.defaultValue = defaultValue;
		this.defaultValueType = seqType;
	}

	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isAutoIncrement() {
		return (defaultValueType == Column.AUTO_INCREMENT);
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getName() {
		return name;
	}

	public int getDefaultValueType() {
		return defaultValueType;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
		this.whereColumn = primaryKey;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDefaultValue(String seqScript) {
		this.defaultValue = seqScript;
	}

	public void setDefaultValueType(int seqType) {
		this.defaultValueType = seqType;
	}
}
