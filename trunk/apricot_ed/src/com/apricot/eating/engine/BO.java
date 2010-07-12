/**
 * 
 */
package com.apricot.eating.engine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.transaction.UserTransaction;
import org.apache.commons.beanutils.LazyDynaBean;
import com.apricot.eating.MessageResource;
import com.apricot.eating.NestedException;
import com.apricot.eating.log.Log;
import com.apricot.eating.util.DataUtil;
/**
 * @author Administrator
 */
public abstract class BO {
	private List bacth;
	private Connection connection;
	private RowSet rowSet;
	private UserTransaction transaction;
	private HashMap globalStaticDataMap;

	public HashMap getGlobalStaticDataMap() {
		return globalStaticDataMap;
	}

	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		this.globalStaticDataMap.clear();
		rowSet = null;
	}

	public void setGlobalStaticDataMap(HashMap gm) {
		this.globalStaticDataMap = new HashMap();
		if(gm==null) return;
		this.globalStaticDataMap.putAll(gm);
	}

	protected String getMessage(String name, String[] args) throws NestedException {
		return MessageResource.getInstance().getMessage(name, args);
	}
	
	protected Connection getConnection(){
		return this.connection;
	}

	/**
	 * 
	 */
	public BO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 将脚本添加到批处理缓存里面
	 * 
	 * @param sql
	 */
	protected void addBatch(StringBuffer sql) {
		if (bacth == null) {
			bacth = new ArrayList();
		}
		bacth.add(sql);
	}

	protected final void begin() throws NestedException {
		try {
			if (transaction != null)
				transaction.begin();
			else {
				throw new NestedException("SYS-0002");
			}
		} catch (NestedException e) {
			throw e;
		} catch (Throwable e) {
			Log.error("BO.begin", e, this);
			throw new NestedException("SYS-0003", new String[] { e.getMessage() });
		}
	}

	final void closeConnection() {
		try {
			if (connection != null && !connection.getAutoCommit()) {
				connection.setAutoCommit(true);
			}
		} catch (Exception e) {
		}
		try {
			connection.close();
		} catch (Exception e) {
		}
	}

	protected final void clearRuntimeParameter() {
		this.rowSet.clearRuntimeParameter();
	}

	protected final void setRuntimeStaticData(String tabName) {
		// System.out.println(globalStaticDataMap);
		if (globalStaticDataMap == null)
			return;
		HashMap o = (HashMap) globalStaticDataMap.get(tabName.toLowerCase());
		if (o == null)
			return;
		this.rowSet.setRuntimeStaticData(o);
	}

	/**
	 * 关闭游标
	 * 
	 * @param stmt
	 * @param res
	 */
	protected final void closeCursor(PreparedStatement stmt, ResultSet res) {
		try {
			if (res != null)
				res.close();
		} catch (Exception e) {
		}
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
		}
	}

	protected final void commit() throws NestedException {
		try {
			if (transaction != null)
				transaction.commit();
			else {
				throw new NestedException("SYS-0001");
			}
		} catch (NestedException e) {
			throw e;
		} catch (Throwable e) {
			Log.error("BO.commit", e, this);
			throw new NestedException("SYS-0004", new String[] { e.getMessage() });
		}
	}

	/**
	 * 创建一个Bo能处理的对象数据
	 * 
	 * @return
	 */
	protected final Object createObject() {
		return new LazyDynaBean();
	}

	protected final int execute(String tabName, DyncParameterMap map, int sqlType) throws NestedException {
		if (sqlType == Table.SELECT)
			throw new NestedException("SYS-0025", "execute(String tabName,DyncParameterMap map,int sqlType)");
		Table tab = getTable(tabName);
		tab.setParameterMap(map);
		tab.setSqlType(sqlType);
		return execute(tab);
	}

	protected final int getInt(StringBuffer sql, int i) throws NestedException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = prepareStatement(sql);
			res = stmt.executeQuery();
			if (res.next()) {
				return res.getInt(i);
			}
			return 0;
		} catch (Throwable e) {
			Log.error("BO.getInt", e, this);
			throw new NestedException("SYS-0005", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, res);
		}
	}

	protected final int getInt(StringBuffer sql) throws NestedException {
		return getInt(sql, 1);
	}

	protected final int execute(Table tab) throws NestedException {
		return update(tab.getSQL());
	}

	protected final int[] executeAll(Table tab) throws NestedException {
		List l = tab.getAllSQL();
		return updateBatch((StringBuffer[]) l.toArray(new StringBuffer[l.size()]));
	}

	protected final long getMax(String tabName, String colName) throws NestedException {
		StringBuffer sql = new StringBuffer("select max(");
		sql.append(colName).append(") as maxid from ").append(tabName);
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = prepareStatement(sql);
			res = stmt.executeQuery();
			if (res.next()) {
				return res.getLong(1) + 1;
			} else
				return 1L;
		} catch (Throwable e) {
			Log.error("BO.get", e, this);
			throw new NestedException("SYS-0006", new String[] { tabName, colName });
		} finally {
			closeCursor(stmt, res);
			stmt = null;
			res = null;
		}
	}

	/**
	 * 获取SQl语句查询到的第一行数据，形成一个的对象
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final Object get(StringBuffer sql) throws NestedException {
		return this.get(sql, null);
	}

	/**
	 * 获取所有数据,然后将查询出来的数据的字段名称用properties里面的替换。
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final Object get(StringBuffer sql, String[] properties) throws NestedException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = prepareStatement(sql);
			res = stmt.executeQuery();
			return rowSet.getRow(res, LazyDynaBean.class, properties);
		} catch (Throwable e) {
			Log.error("BO.get", e, this);
			throw new NestedException("SYS-0005", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, res);
			stmt = null;
			res = null;
		}
	}

	/**
	 * 获取所有数据
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final List getAll(StringBuffer sql) throws NestedException {
		return this.getAll(sql, null);
	}

	/**
	 * 获取所有数据,然后将查询出来的数据的字段名称用properties里面的替换。
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final List getAll(StringBuffer sql, String[] properties) throws NestedException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = prepareStatement(sql);
			if(maxSize>0) stmt.setMaxRows(maxSize);
			res = stmt.executeQuery();
			rowSet.setMaxSize(maxSize);
			return rowSet.getRows(res, LazyDynaBean.class, properties);
		} catch (Throwable e) {
			Log.error("BO.getAll", e, this);
			throw new NestedException("SYS-0005", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, res);
			stmt = null;
			res = null;
		}
	}
	
	private int maxSize=-1;



	/**
	 * @param maxSize the maxSize to set
	 */
	protected final void setMaxSize(int maxSize) {
	    this.maxSize = maxSize;
	}

	protected final Table getTable(String name) throws NestedException {
		Table tab = new Table();
		tab.setName(name.toLowerCase());
		tab.setColumns(getAll(new StringBuffer("desc ").append(tab.getName()), null));
		return tab;
	}

	final void init() {
		this.rowSet = new RowSet();
	}

	protected final void rollback() throws NestedException {
		try {
			if (transaction != null)
				transaction.rollback();
			else {
				throw new NestedException("SYS-0002");
			}
		} catch (NestedException e) {
			throw e;
		} catch (Throwable e) {
			Log.error("BO.rollback", e, this);
			throw new NestedException("SYS-0007=", new String[] { e.getMessage() });
		}
	}

	/**
	 * 获取SQl语句查询到的第一行数据，形成一个的对象
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final void set(StringBuffer sql, Object obj) throws NestedException {
		this.set(sql, obj, null);
	}

	/**
	 * 获取所有数据,然后将查询出来的数据的字段名称用properties里面的替换。
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final void set(StringBuffer sql, Object obj, String[] properties) throws NestedException {
		if (!(obj instanceof LazyDynaBean)) {
			throw new NestedException("SYS-0008");
		}
		PreparedStatement stmt = null;
		ResultSet res = null;
		try {
			stmt = prepareStatement(sql);
			res = stmt.executeQuery();
			rowSet.setRow(res, obj, properties);
		} catch (Throwable e) {
			Log.error("BO.set", e, this);
			throw new NestedException("SYS-0009", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, res);
			stmt = null;
			res = null;
		}
	}

	final void setConnection(Connection connection) {
		this.connection = connection;
	}

	final void setTransaction(UserTransaction transaction) throws NestedException {
		try {
			this.transaction = transaction;
			this.transaction.setTransactionTimeout(60000);
		} catch (Throwable e) {
			Log.error("BO.setTransaction", e, this);
			throw new NestedException("SYS-0010");
		}
	}

	/**
	 * Get where sql by parameter is no null
	 * 
	 * @param map
	 * @param tabName
	 * @return
	 * @throws NestedException
	 */
	protected final String getWhereByNotNull(DyncParameterMap map, String tabName, String aliasTable) throws NestedException {
		Table tab = getTable(tabName);
		tab.setAnd("true".equalsIgnoreCase(map.getString("cAndAndAnd")));
		String where = null;
		tab.setParameterMap(map);
		where = tab.getWhere(aliasTable);
		if (DataUtil.isNull(where)) {
			return "";
		}
		return new StringBuffer(" and (").append(where).append(")").toString();
	}



	/**
	 * 分页查询
	 * 
	 * @param sql
	 * @param startPage
	 * @param pageSize
	 * @return
	 * @throws NestedException
	 */
	protected final DataSet showPages(String sql, int startRow, int pageSize) throws NestedException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		StringBuffer page = new StringBuffer(sql);
		if (pageSize > 1) {
			page.append(" limit ").append(startRow);
			page.append(",").append(pageSize);
		}
		DataSet ds = new DataSet();
		// System.out.println(sql);
		try {
			// 读取总数据量
			if (pageSize > 1) {
				StringBuffer totalCount = new StringBuffer("select count(*) from (");
				totalCount.append(sql).append(") totalCount_xudahu");
				stmt = prepareStatement(totalCount);
				res = stmt.executeQuery();
				res.next();
				ds.setTotalCount(String.valueOf(res.getInt(1)));
				closeCursor(stmt, res);
				res = null;
				stmt = null;
			} else
				ds.setTotalCount("0");
			// 读取某一页数据
			stmt = prepareStatement(page);
			res = stmt.executeQuery();
			ds.setRowSet(rowSet.getRows(res, null));
			closeCursor(stmt, res);
			stmt = null;
			res = null;
			return ds;
		} catch (Throwable e) {
			Log.error("BO.get", e, this);
			throw new NestedException("SYS-0005", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, res);
			stmt = null;
			res = null;
		}
	}

	protected final int update(StringBuffer sql) throws NestedException {
		PreparedStatement stmt = null;
		try {
			stmt = prepareStatement(sql);
			return stmt.executeUpdate();
		} catch (Throwable e) {
			Log.error("BO.update", e, this);
			throw new NestedException("SYS-0011", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, null);
			stmt = null;
		}
	}

	protected final PreparedStatement prepareStatement(StringBuffer sql) throws SQLException {
		return connection.prepareStatement(DataUtil.toDataBaseCharset(sql.toString()));
		// return connection.prepareStatement(sql.toString());
	}

	/**
	 * 更新数据，参数只有一行
	 * 
	 * @param sql
	 * @param map
	 * @param properties
	 * @return
	 * @throws NestedException
	 */
	protected final int update(StringBuffer sql, DyncParameterMap map, String[] properties, int[] types) throws NestedException {
		PreparedStatement stmt = null;
		try {
			stmt = prepareStatement(sql);
			// int len=map.getMaxSize(properties);
			// for(int i=0;i<len;i++){
			for (int a = 0; a < properties.length; a++) {
				switch (types[a]) {
				case Types.STRING:
					stmt.setString(a + 1, map.getString(properties[a]));
					break;
				case Types.INTEGER:
					stmt.setInt(a + 1, map.getInt(properties[a]));
					break;
				case Types.DATE:
					stmt.setDate(a + 1, map.getDate(properties[a]));
					break;
				case Types.DOUBLE:
					stmt.setDouble(a + 1, map.getDouble(properties[a]));
					break;
				case Types.LONG:
					stmt.setDouble(a + 1, map.getLong(properties[a]));
					break;
				case Types.TIMESTAMP:
					stmt.setTimestamp(a + 1, map.getTimestamp(properties[a]));
					break;
				}
			}
			// stmt.addBatch();
			// }
			return stmt.executeUpdate();
		} catch (Throwable e) {
			Log.error("BO.update", e, this);
			throw new NestedException("SYS-0011", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, null);
			stmt = null;
		}
	}

	/**
	 * 执行批处理缓存里面所有的脚本
	 * 
	 * @throws NestedException
	 */
	protected int[] updateBatch() throws NestedException {
		Statement stmt = null;
		if (bacth == null || bacth.size() == 0) {
			throw new NestedException("SYS-0012");
		}
		try {
			stmt = connection.createStatement();
		} catch (Exception e) {
			Log.error("BO.updateBatch", e, this);
			throw new NestedException("SYS-0013");
		}
		try {
			for (Iterator sqls = bacth.iterator(); sqls.hasNext();) {
				stmt.addBatch((String) sqls.next());
			}
			return stmt.executeBatch();
		} catch (Throwable e) {
			Log.error("BO.updateBatch", e, this);
			throw new NestedException("SYS-0014", new String[] { e.getMessage() });
		} finally {
			bacth.clear();
			bacth = null;
		}
	}

	/**
	 * 更新数据，参数只有多行
	 * 
	 * @param sql
	 * @param map
	 * @param properties
	 * @return
	 * @throws NestedException
	 */
	protected final int[] updateBatch(StringBuffer sql, DyncParameterMap map, String[] properties, int[] types) throws NestedException {
		PreparedStatement stmt = null;
		try {
			stmt = prepareStatement(sql);
			int len = map.getMaxSize(properties);
		
			for (int i = 0; i < len; i++) {
				for (int a = 0; a < properties.length; a++) {

					switch (types[a]) {
					case Types.STRING:
						stmt.setString(a + 1, map.getString(properties[a], i));
						break;
					case Types.INTEGER:
						stmt.setInt(a + 1, map.getInt(properties[a], i));
						break;
					case Types.DATE:
						stmt.setDate(a + 1, map.getDate(properties[a], i));
						break;
					case Types.DOUBLE:
						stmt.setDouble(a + 1, map.getDouble(properties[a], i));
						break;
					case Types.LONG:
						stmt.setDouble(a + 1, map.getLong(properties[a], i));
						break;
					case Types.TIMESTAMP:
						stmt.setTimestamp(a + 1, map.getTimestamp(properties[a], i));
						break;
					}
				}
				stmt.addBatch();
			}
			return stmt.executeBatch();
		} catch (Throwable e) {
			Log.error("BO.update", e, this);
			throw new NestedException("SYS-0011", new String[] { sql.toString(), e.getMessage() });
		} finally {
			closeCursor(stmt, null);
			stmt = null;
		}
	}

	/**
	 * 批量执行数据库脚本
	 * 
	 * @param sql
	 * @return
	 * @throws NestedException
	 */
	protected final int[] updateBatch(StringBuffer[] sql) throws NestedException {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (Exception e) {
			Log.error("BO.updateBatch", e, this);
			throw new NestedException("SYS-0013");
		}
		try {
			for (int i = 0; i < sql.length; i++) {
			
				stmt.addBatch(sql[i].toString());
			}
			return stmt.executeBatch();
		} catch (Throwable e) {
			Log.error("BO.updateBatch", e, this);
			throw new NestedException("SYS-0014", new String[] { e.getMessage() });
		}
	}

	protected final int[] updateBatch(String[] sql) throws NestedException {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
		} catch (Exception e) {
			Log.error("BO.updateBatch", e, this);
			throw new NestedException("SYS-0013");
		}
		try {
			for (int i = 0; i < sql.length; i++) {
				stmt.addBatch(sql[i]);
			}
			return stmt.executeBatch();
		} catch (Throwable e) {
			Log.error("BO.updateBatch", e, this);
			throw new NestedException("SYS-0014", new String[] { e.getMessage() });
		}
	}
}
