package com.qzgf.utils;

/**
 *@author chenf
 *@date 2005年11月16日
 *@docRoot
 *    执行sql接口类
 * */

import java.sql.ResultSet;

public interface StatementIface {
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public ResultSet executeSql(String sql,Object[] para)throws Exception;

	
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public boolean executeInsertSql(String sql,Object[] para)throws Exception;
	
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public int executeUpdateSql(String sql,Object[] para)throws Exception;
	/**
	 * @author lsr
	 * @dete 2008年11月2日
	 * 用于释放资源
	 * */
	public void clear();
}
