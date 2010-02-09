package com.qzgf.NetStore.pub;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.naming.*;

import javax.sql.*;
import javax.sql.rowset.CachedRowSet;

import org.apache.log4j.Logger;

import com.qzgf.NetStore.pub.LogUtil;
import com.sun.rowset.CachedRowSetImpl;

import oracle.jdbc.rowset.OracleCachedRowSet;

/**
 * <p>Title:数据库处理代码 </p>
 *
 * <p>Description: 数据库主程序</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: 泉州邮政信息技术中心</p>
 * @author qsy
 * @version 1.0
 * 历史:
 * 2005-04-17  生成代码
 * 
 * @author szj 
 * @version 2.0
 * 说明:
 * 数据库连接不通过ctx.lookup(DBConPool);读取配置文件connection.xml
 */






public class UtilDB {
    Connection myCon = null; // 连接
    public ResultSet myRs = null; // 结果集
    
    Statement mystmt = null; // 语句
    
    public Statement stmt=null;
    public Statement stmtTwo=null;//08.08.14
    PreparedStatement mypstmt = null; // 准备语句
    int rowCount = 0; // 当前查询结果总行数

    
    //080710沈伟庆
    private DataSource ds;
	private String strError;
	private Logger log=LogUtil.getLogger(UtilDB.class);
	//080710沈伟庆
	
    /**
     * getCon
     * 功能：取得连接
     * @return Connection         连接
     */
	
	
	  ///////////////////////用连接池来链接数据库
	  public static synchronized Connection getCon() throws wlglException {
	    	Connection con = null;
	        Context initCtx  = null;
	        Context envCtx =null;
	        DataSource ds = null;
	         
	        //取得配置参数
	       // HttpSession session = UtilWebTools.getSession();
	       // String DBConPool = UtilCommon.NVL((String) (session.getAttribute(
	       //         "DBConPool")));
	        String DBConPool ="jdbc/DBConPool";
	        
	        //读取连接池信息,及异常处理.
	        try {
	        	initCtx  = new InitialContext();
	        	envCtx  = (Context) initCtx.lookup("java:comp/env");
	            ds = (DataSource) envCtx.lookup(DBConPool);
	        } catch (javax.naming.NamingException ex) {
	            wlglException.ProcessMainWebExceptionMessage("201",
	                    "查找数据Tomcat连接池jndi失败,jndi=" + DBConPool + ".", ex);
	        }
	        
	        //取得连接
	        try {
	            con = ds.getConnection();
	        }

	        catch (java.sql.SQLException ex) {
	            wlglException.ProcessMainWebExceptionMessage("202", "取数据连接池失败:", ex);
	        }
	        return con;
	    }

	    ///////////////////////用连接池来链接数据库
	  
	  
	
	  
	  
	
	  //取得多条记录集合
  @SuppressWarnings("unchecked")
public ResultSet exeQueryRow(String sqlStr) throws  wlglException {
	

 // try {
	 // conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	   ResultSet rs = null;
	  try {
		mystmt = myCon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		  myRs = mystmt.executeQuery(sqlStr);
	      rs=myRs;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


  return  rs;
}
  //取得多条记录集合	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

    /**
     * UtilDB
     * 功能: 初试化连接
     */
    public UtilDB() throws wlglException {
        if (myCon == null) {
      	           myCon = getCon();
        }
    }

    public void finalize() throws wlglException {
        System.err.println("关闭utildb");
    }

    /**
     * closeCon
     * 功能: 关闭连接
     */
    public void closeCon() throws wlglException {
        try {
            if (myRs != null) {
            	
                myRs.close();
                myRs=null;
            }
            if (mystmt != null) {
                mystmt.close();
                mystmt=null;
            }

            if (mypstmt != null) {
                mypstmt.close();
                mypstmt=null;
            }
            if (myCon != null) {
            	if(!myCon.isClosed())
            	{
            		 myCon.close();
                     myCon=null;
            	}
              
            }
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("206",
                    "关闭UtilDB的CloseCon失败.", ex);
        }

    }

    
    
    
    public OracleCachedRowSet executeQueryOracle(String sql){
		//log.info(sql);
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		OracleCachedRowSet ocrs=null;
		try {
			con=myCon;//ds.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			ocrs=new OracleCachedRowSet();
			ocrs.populate(rs);
		}catch (Exception e) {
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return ocrs;
	}
	
	/**
	 * 取得查询结果
	 * @param sql
	 * @return CachedRowSet
	 */
	public CachedRowSet executeQuery(String sql){
		//log.info(sql);
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		CachedRowSet crs=null;
		
		try {
			if (myCon.isClosed())
			{
				     try {
						myCon = getCon();
					} catch (wlglException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			con=myCon;//ds.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			crs=new CachedRowSetImpl();
			crs.populate(rs);
		}catch (Exception e) {
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return crs;
	}
	
	/**
	 * 取得查询结果
	 * @param sql
	 * @return List
	 */
	@SuppressWarnings({ "unchecked", "unchecked" })
	public List executeQuery2List(String sql){
		//log.info(sql);
		List resultlist=setRowSet2List(executeQuery(sql));
		return resultlist;
	}
	
	/**
	 * 取得分页查询结果
	 * mysql数据库专用
	 * 依赖ocrs12.jar
	 * @param sql
	 * @param page2
	 * @param pageSize
	 * @return Page
	 */
	public Page executeQueryByPageForMySql(String sql,int pageNo,int pageSize)
	{
		UtilDB utilDB=null;
		try {
			 utilDB=new UtilDB();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ResultSet rs=null;
		Page page=new Page(pageNo,pageSize);//当前页数，每页多少条记录
		
		
		///////////设置sql语句里面总的有条记录
		StringBuffer countSql=new StringBuffer();
		countSql.append("select count(*) as recordcount from ( ");
		countSql.append(sql);
		countSql.append(" ) T ");

		try {
			rs=utilDB.executeQuery(countSql.toString());
			while(rs.next()){
				page.setTotalRecords(rs.getInt("recordcount"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ///////////设置sql语句里面总的有条记录0,4; 4,4; 8,4   pageNo*4
		
		StringBuffer searchSql=new StringBuffer();
		searchSql.append(sql+" limit "+((pageNo-1)*pageSize)+","+pageSize);
		

		rs=utilDB.executeQuery(searchSql.toString());
		
		page.setResultList(setRowSet2List(rs));
		
		page.setRowset(rs);
		
		page.initOtherData();
		
		
		
		
		try {
			utilDB.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return page;
	}
	
	
	
	
	
	
	public Page executeQueryByPageForOracle(String sql,int pageNo,int pageSize){
		//log.info(sql);
		Connection con=null;
		//Statement stmt=null;
		ResultSet rs=null;
		OracleCachedRowSet ocrs=null;
		Page page=new Page(pageNo,pageSize);
		//查询总记录数的sql语句
		StringBuffer countSql=new StringBuffer();
		countSql.append("select count(*) as recordcount from ( ");
		countSql.append(sql);
		countSql.append(" )");
		//根据pageNo，pageSize确定查找rownum范围
		//int rownum_start=(pageNo-1)*pageSize+1;
		//int rownum_end=pageNo*pageSize;
		//根据rownum_start，rownum_end获得记录的sql语句
		StringBuffer searchSql=new StringBuffer();
		
		searchSql.append("select * from testtable ");
		
		
		
		//searchSql.append("select * from ( ");
		//searchSql.append("select A.* , rownum rn from ( ");
		//searchSql.append(sql+" ) A ");
		//searchSql.append("where rownum <= "+rownum_end+") B ");
		//searchSql.append("where rn >= "+rownum_start);
		
		/*
		 * sql语句类似于：
		SELECT * FROM
	     (
	     SELECT A.*, rownum r  FROM
	          (
	          SELECT * FROM tableName Order By field (field字段必须具有唯一性,若没有可再添加一个绝有唯一性的字段)
	          ) A
	     WHERE rownum <= 10
	     ) B
	     WHERE r > 0
	     */
		try {
			log.info("查询记录语句："+searchSql.toString());
			System.out.println(searchSql.toString());
			
			rs=stmt.executeQuery(searchSql.toString());
			ocrs=new OracleCachedRowSet();
			ocrs.populate(rs);
			
			page.setRowset(ocrs);
			page.setResultList(setRowSet2List(ocrs));
			ocrs.beforeFirst();
			page.initOtherData();
		}catch (Exception e) {
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return page;
	}
	
	/**
	 * 执行更新，删除操作
	 * @param sql
	 * @return
	 */
	public boolean executeUpdate(String sql){
		log.info(sql);
		Connection con=null;
		Statement stmt=null;
		try {
			con=myCon;//ds.getConnection();
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (Exception e) {
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return false;
	}
	
	/**
	 * 使用事务处理多条sql语句
	 * @param sql
	 * @return
	 */
	public boolean executeUpdate(String[] sql){
		for (int i = 0; i < sql.length; i++) {
			log.info(sql[i]);
		}
		
		Connection con=null;
		Statement stmt=null;
		try {
			con=myCon;//ds.getConnection();
			con.setAutoCommit(false);
			stmt=con.createStatement();
			for (int i = 0; i < sql.length; i++) {
				stmt.executeUpdate(sql[i]);
			}
			con.commit();
			return true;
		} catch (Exception e) {
			try {
				if(con!=null)
					con.rollback();
			} catch (SQLException e1) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return false;
	}
	
	/**
	 * 使用事务处理多条sql语句
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean executeUpdate(List sql){		Statement stmt1=null;
		Connection conn1=null;
		Statement stmtTwo2=null; 

			 try{ 
	        	 Class.forName("com.mysql.jdbc.Driver").newInstance();
	        	 String url1 ="jdbc:mysql://localhost/netstore?user=root&password=123@123&useUnicode=true&characterEncoding=gbk";
	        	 conn1= DriverManager.getConnection(url1);
	        	 stmt1=conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        	 stmtTwo2=conn1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			
			
			conn1.setAutoCommit(false);
			stmt1=conn1.createStatement();
			
			for (int i = 0; i < sql.size(); i++) {
				log.info(sql.get(i).toString());
				stmt1.executeUpdate(sql.get(i).toString());
			}
			conn1.commit();
			return true;
		} catch (Exception e) {
			try {
				if(conn1!=null)
					conn1.rollback();
			} catch (SQLException e1) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(stmt1!=null)
					stmt1.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(conn1!=null)
					conn1.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return false;
	}
	
	
	/**
	 * 返回最近的一条错误信息
	 * @return
	 */
	public String getLastError(){
		return strError;
	}
	
	/**
	 * 把rowset的内容保存为List
	 * @param rs
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public  List setRowSet2List(ResultSet rs){
		List resultlist = new ArrayList();
		List columnNames=new ArrayList();
		try {
			ResultSetMetaData rsmd=rs.getMetaData();
			//获得所有列名
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnNames.add(rsmd.getColumnName(i));
			}
			//迭代查找出来的所有行
			while(rs.next()){
				Map map=new HashMap();
				//迭代所有字段
				for (int i = 0; i < columnNames.size(); i++) {
					map.put(columnNames.get(i), rs.getObject(i+1));
				}
				resultlist.add(map);
			}
			rs.beforeFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultlist;
	}
	
	/**
	 *  调用存储过程
	 * @param sql
	 * @param parameters 包含存储过程中的所有参数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map executeProcess(String sql,ProcessParameters parameters){
		Connection con=null;
		CallableStatement cs=null;
		Map result=null;
		try {
			con=myCon;//ds.getConnection();
			cs= con.prepareCall(sql);
			result=parameters.getResult(cs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(cs!=null)
					cs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List getAll(String sql){
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		CachedRowSet crs=null;
		List resultlist = new ArrayList();
		List columnNames=new ArrayList();
		try {
			con=myCon;//ds.getConnection();
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);
			crs=new CachedRowSetImpl();
			crs.populate(rs);
			ResultSetMetaData rsmd=crs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				columnNames.add(rsmd.getColumnName(i));
			}
			while(crs.next()){
				Map map=new HashMap();
				for (int i = 0; i < columnNames.size(); i++) {
					map.put(columnNames.get(i), crs.getObject(i+1));
				}
				resultlist.add(map);
			}
			return resultlist;
		}catch (Exception e) {
			strError=e.getMessage();
			System.out.println(strError);
		}finally{
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
			try {
				if(con!=null)
					con.close();
			} catch (SQLException e) {
				strError=e.getMessage();
				System.out.println(strError);
			}
		}
		return null;
	}
	
	  
    
    
    
    
    
    
    
    
    
    
    /**
     * exeUpdate
     * 功能：执行更新
     * @param sqlStr String SQL语句
     *//*
    public void exeUpdate(String sqlStr) throws wlglException {
        if (UtilCommon.NVL(sqlStr).equals("")) {
            return;
        }

        try {
            mystmt = myCon.createStatement();
            mystmt.executeUpdate(sqlStr);
            //mystmt.close();
        } catch (SQLException ex) {
            rollback();
            wlglException.ProcessMainWebExceptionMessage("203",
                    "update SQL失败:SQLStr:" + sqlStr, ex);
        }

        System.out.println("SQLStr是:" + sqlStr);

    }

    *//**
     * beginTransaction
     * 功能：开始事务操作
     *//*
    public void beginTransaction() throws wlglException {
        try {
            myCon.setAutoCommit(false);
        }

        catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "begintransaction失败", ex);
        }
    }


    *//**
     * commit
     * 功能：确认事务操作
     *//*
    public void commit() throws wlglException {
        try {
            myCon.commit();
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "commit失败", ex);
        }

    }

    *//**
     * rollback
     * 功能：回滚事务操作
     *//*
    public void rollback() throws wlglException {
        try {
            myCon.rollback();
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "rollback失败", ex);
        }

    }

    *//**
     * exeQuery
     * 功能：执行更新,带参数
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     *//*
    public ResultSet exeQuery(String sqlStr, ArrayList params) throws
            wlglException {
        return (exeQuery(sqlStr, params, java.sql.ResultSet.CONCUR_READ_ONLY));
    }

    public ResultSet exeQuery(String sqlStr, ArrayList params,
                              int resultSetConcurrency) throws
            wlglException {

        try {
            mypstmt = myCon.prepareStatement(sqlStr,
                                             java.sql.ResultSet.
                                             TYPE_SCROLL_INSENSITIVE,
                                             resultSetConcurrency);
            Iterator itx = params.iterator();
            int i = 1;
            while (itx.hasNext()) {
                Object param = itx.next();
                if (param != null) {
                    if (Integer.class.isInstance(param)) {
                        mypstmt.setInt(i, ((Integer) param).intValue());
                    }
                    else if (String.class.isInstance(param)) {
                        mypstmt.setString(i, ((String) param));
                    }
                    else if (Double.class.isInstance(param)) {
                        mypstmt.setDouble(i, ((Double) param).doubleValue());
                    }
                    else if (Boolean.class.isInstance(param)) {
                        mypstmt.setBoolean(i, ((Boolean) param).booleanValue());
                    }
                } else {
                    mypstmt.setString(i, "");
                }
                i = i + 1;
            }
            myRs = mypstmt.executeQuery();
        }

        catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("204",
                    "execute SQL失败:SQLStr:" + sqlStr + "\nParams:" + params +
                    ".", ex);
        }
        getCount();
        System.out.println("SQLStr是:" + sqlStr);
        System.out.println("params是:" + params);
        return myRs;
    }


    *//**
     * exeUpdate
     * 功能：执行更新,带参数
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     *//*
    public void exeUpdate(String sqlStr, ArrayList params) throws
            wlglException {

        try {
            mypstmt = myCon.prepareStatement(sqlStr);
            Iterator itx = params.iterator();
            int i = 1;
            while (itx.hasNext()) {
                Object param = itx.next();
                if (Integer.class.isInstance(param)) {
                    mypstmt.setInt(i, ((Integer) param).intValue());
                } else if (String.class.isInstance(param)) {
                    mypstmt.setString(i, ((String) param));
                } else if (Double.class.isInstance(param)) {
                    mypstmt.setDouble(i, ((Double) param).doubleValue());
                    
                } else if (Boolean.class.isInstance(param)) {
                    mypstmt.setBoolean(i, ((Boolean) param).booleanValue());
                }
                else {
                    mypstmt.setString(i, ((String) param));
                }

                i = i + 1;
            }
            mypstmt.executeUpdate();
            //mypstmt.close();
        } catch (SQLException ex) {
            rollback();
            wlglException.ProcessMainWebExceptionMessage("204",
                    "update SQL失败:SQLStr:" + sqlStr + "\nParams:" + params +
                    ".", ex);
        }
        System.out.println("SQLStr是:" + sqlStr);
        System.out.println("params是:" + params);

    }

    *//**
     * exeQuery
     * 功能：执行查询
     * @param sqlStr String SQL语句
     * @return ResultSet         结果集
     *//*
    public ResultSet exeQuery(String sqlStr) throws wlglException {

        try {
            mystmt = myCon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                           ResultSet.CONCUR_UPDATABLE);
            myRs = mystmt.executeQuery(sqlStr);
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("204",
                    "execute SQL失败:SQLStr:" + sqlStr, ex);
        }
        System.out.println("SQLStr是:" + sqlStr);
        getCount();
        return myRs;

    }

    *//**
     * exeQueryOneRow
     * 功能：执行更新
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     *//*
    public String exeQueryValue(String sqlStr) throws
            wlglException {

        ArrayList al = exeQueryOneRow(sqlStr);
        String val = "";
        if (al.size() > 0) {
            val = (String) al.get(0);
        }
        return val;
    }

    public String exeQueryValue(String sqlStr, ArrayList params) throws
            wlglException {

        ArrayList al = exeQueryOneRow(sqlStr, params);
        String val = "";
        if (al.size() > 0) {
            val = (String) al.get(0);
        }
        return val;
    }

    *//**
     * exeQueryOneRow
     * 功能：执行更新
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     *//*
    public ArrayList exeQueryOneRow(String sqlStr) throws
            wlglException {
        ArrayList myArray = new ArrayList();

        try {
            mystmt = myCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                           ResultSet.CONCUR_READ_ONLY);
            myRs = mystmt.executeQuery(sqlStr);
            ResultSetMetaData md = myRs.getMetaData();
            if (myRs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    myArray.add(myRs.getString(i));
                }
            }
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "执行单行返回结果SQL失败:SQLStr:" + sqlStr + '.', ex);
        }
        System.out.println("SQLStr是:" + sqlStr);

        return myArray;
    }

    *//**
     * exeQueryOneRow
     * 功能：执行更新,带参数
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     *//*
    public ArrayList exeQueryOneRow(String sqlStr, ArrayList params) throws
            wlglException {

        ArrayList myArray = new ArrayList();
        ArrayList mytitle = new ArrayList();
        exeQueryOneRowTitlesAndDatas(sqlStr, params, mytitle, myArray);
        return myArray;
    }

    public boolean next() throws wlglException {
        boolean s = false;
        try {

            if (myRs != null) {
                return (myRs.next());
            }
        } catch (Exception ex) {
            throw new wlglException("获取下条记录失败", ex);
        }
        return s;

    }

    public void exeQueryOneRowTitlesAndDatas(String sqlStr, ArrayList params,
                                             ArrayList titles, ArrayList datas) throws
            wlglException {

        try {
            mypstmt = myCon.prepareStatement(sqlStr);
            Iterator itx = params.iterator();
            int i = 1;
            while (itx.hasNext()) {
                Object param = itx.next();
                if (Integer.class.isInstance(param)) {
                    mypstmt.setInt(i, ((Integer) param).intValue());
                }
                else if (String.class.isInstance(param)) {
                    mypstmt.setString(i, ((String) param));
                }
                else if (Double.class.isInstance(param)) {
                    mypstmt.setDouble(i, ((Double) param).doubleValue());
                }
                else if (Boolean.class.isInstance(param)) {
                    mypstmt.setBoolean(i, ((Boolean) param).booleanValue());
                }
                i = i + 1;
            }
            myRs = mypstmt.executeQuery();
            ResultSetMetaData md = myRs.getMetaData();
            boolean hasNext = false;
            if (myRs.next()) {
                hasNext = true;
            } else {
                hasNext = false;
            }
            for (i = 1; i <= md.getColumnCount(); i++) {
                if (hasNext) {
                	//临时增加处理数据类型为bit类型的.0,1
                	//Date： 2008-6-15
                	//author: szj
                	int tmp=md.getColumnType(i);
                	
                	if(tmp==java.sql.Types.BIT)
                	{
                		if(myRs.getByte(i)==1)
                			datas.add("1");	
                		else
                		    datas.add("0");
                	}
                	else
                	{
                		datas.add(myRs.getString(i));
                	}
                    titles.add(md.getColumnName(i));
                } else {
                    datas.add("");
                }
            }
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "执行单行返回结果SQL失败:SQLStr:" + sqlStr + "\nParams:" + params +
                    ".", ex);
        }
        System.err.println("SQLStr是:" + sqlStr);
        System.err.println("params是:" + params);
    }
    public String getString(int i) throws wlglException {
        String s="";
        try {

            if (myRs != null) {
                return ( UtilCommon.NVL(myRs.getString(i)));
            }
        } catch (Exception ex) {
            throw new wlglException("获取数据集字段失败，字段索引" + i + ".", ex);
        }
        return s;

    }

    
    

   
    *//**
     * getCount
     * 功能:取得结果纪录数
     * @return int
     *//*
    private int getCount() throws wlglException {
        int count = 0;
        try {
            myRs.last();
            rowCount = myRs.getRow();
            myRs.beforeFirst();
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("207",
                    "获取纪录数目失败.", ex);
        }
        count = rowCount;
        return count;
    }

    *//**
     * getSomeRow
     * 功能:取得指定数目的行数据
     * @param beginIndex int
     * @param endIndex int
     * @return ArrayList
     *//*
    public ArrayList getSomeRow(int beginIndex, int endIndex) throws
            wlglException {
        ArrayList myArray = new ArrayList();
        if (beginIndex > rowCount) {
            return myArray;
        }
        try {
            myRs.absolute(beginIndex);
            for (int row = beginIndex; row <= endIndex; row++) {
                myArray.add(getRowData(myRs));
                if (!myRs.next()) {
                    break;
                }
            }
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("209",
                    "取部分行数失败.", ex);
        }
        return myArray;
    }

    *//**
     * getRowData
     * //取得行数据以数组形式返回
     * @param anObject ResultSet
     * @return ArrayList
     *//*
    public ArrayList getRowData(ResultSet rs) throws wlglException {
        ArrayList myArray = new ArrayList();

        ResultSetMetaData md = null;
        try {
            md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                myArray.add(rs.getString(i));
            }
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("210",
                    "取行数据失败.", ex);
        }
        return myArray;

    }*/

}
