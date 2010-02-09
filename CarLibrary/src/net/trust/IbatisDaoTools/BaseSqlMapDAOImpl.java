package net.trust.IbatisDaoTools;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 数据库基础操作工具包
 */
public class BaseSqlMapDAOImpl extends SqlMapClientDaoSupport implements BaseSqlMapDAO {
	protected SqlMapClientTemplate smcTemplate=this.getSqlMapClientTemplate();
	
	public List queryForList(String statementName, Object parameterObject){
		return this.smcTemplate.queryForList(statementName, parameterObject);
	}
	
	public List queryForList(String statementName, Object parameterObject, int skipResults, int maxResults){
		return this.smcTemplate.queryForList(statementName, parameterObject, skipResults, maxResults);
	}
	

	public Map queryForMap(String statementName, Object parameterObject, String keyProperty){
		return this.smcTemplate.queryForMap(statementName, parameterObject, keyProperty);
	}
	
	public Map queryForMap(String statementName, Object parameterObject, String keyProperty, String valueProperty){
		return this.smcTemplate.queryForMap(statementName, parameterObject, keyProperty, valueProperty);
	}
	
	public Object queryForObject(String statementName, Object parameterObject){
		return this.smcTemplate.queryForObject(statementName, parameterObject);
	}
	
	public Object queryForObject(String statementName, Object parameterObject, Object resultObject){
		return this.smcTemplate.queryForObject(statementName, parameterObject, resultObject);
	}
	
	public int update(String statementName, Object parameterObject){
		return this.smcTemplate.update(statementName, parameterObject);
	}
	
	public void update(String statementName, Object parameterObject, int requiredRowsAffected){
		this.smcTemplate.update(statementName, parameterObject, requiredRowsAffected);
	}
	
	public void insert(String statementName, Object parameterObject){
		this.smcTemplate.insert(statementName, parameterObject);
	}
	
	public void delete(String statementName, Object parameterObject){
		this.smcTemplate.delete(statementName, parameterObject);
	}
	
	/*得到主键*/
	public String sequences(String seqName){
		return (String)this.smcTemplate.queryForObject("getSequencesValue", seqName);
	}
	/*得到Msisdn*/
	public String sequencesmsisdn(String seqName){
		return (String)this.smcTemplate.queryForObject("getMsisdnValue", seqName);
	}
}
