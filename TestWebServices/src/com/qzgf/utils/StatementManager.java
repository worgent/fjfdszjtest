package com.qzgf.utils;

import com.ibatis.sqlmap.client.SqlMapClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementManager implements StatementIface {

	private Connection conn =null;
	private PreparedStatement pstmt = null;
	private SqlMapClient sqlMapClient = null;
	private ResultSet rs =null;
	/**
	 * @author chenf
	 * @dete 2005年11月16日
	 * @param String sql 所要运行的sql
	 * @param Object[] para sql所要的参数
	 * @exception Exception
	 * @return ResultSet
	 * */
	public ResultSet executeSql(String sql,Object[] para)throws Exception{
		
		conn = sqlMapClient.getDataSource().getConnection();
		pstmt = conn.prepareStatement(sql);
		if(para!=null && para.length>0){
			for(int i=0;i<para.length;i++){
				pstmt.setObject(i+1,para[i]);
			}
		}
		rs = pstmt.executeQuery();
		return rs;
	}
	/**
	 * @author chenf
	 * @dete 2005年11月16日
	 * 用于释放资源
	 * */
	public void clear(){
		
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException sqlex){
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException sqlex){
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException sqlex){
			}
		}
	}
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}
