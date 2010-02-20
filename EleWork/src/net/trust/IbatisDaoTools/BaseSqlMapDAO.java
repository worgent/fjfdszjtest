package net.trust.IbatisDaoTools;

import java.util.List;
import java.util.Map;
/**
 * 数据库基础操作工具包
 * @author chenqf
 * @date 20080518
 */
public interface BaseSqlMapDAO {
	/**
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public List queryForList(String statementName, Object parameterObject);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param skipResults
	 * @param maxResults
	 * @return
	 */
	public List queryForList(String statementName, Object parameterObject, int skipResults, int maxResults);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param keyProperty
	 * @return
	 */
	public Map queryForMap(String statementName, Object parameterObject, String keyProperty);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param keyProperty
	 * @param valueProperty
	 * @return
	 */
	public Map queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public Object queryForObject(String statementName, Object parameterObject);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param resultObject
	 * @return
	 */
	public Object queryForObject(String statementName, Object parameterObject, Object resultObject);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @return
	 */
	public int update(String statementName, Object parameterObject);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 * @param requiredRowsAffected
	 */
	public void update(String statementName, Object parameterObject, int requiredRowsAffected);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	public void insert(String statementName, Object parameterObject);
	/**
	 * 
	 * @param statementName
	 * @param parameterObject
	 */
	public void delete(String statementName, Object parameterObject);
	/**
	 * 返回sequences值
	 * @param seqName
	 * @return
	 */
	public String sequences(String seqName);
}
