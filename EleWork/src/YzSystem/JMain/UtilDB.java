package YzSystem.JMain;

import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.http.*;
import javax.sql.*;

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
    PreparedStatement mypstmt = null; // 准备语句
    int rowCount = 0; // 当前查询结果总行数

    /**
     * getCon
     * 功能：取得连接
     * @return Connection         连接
     */
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

    /**
     * exeUpdate
     * 功能：执行更新
     * @param sqlStr String SQL语句
     */
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

    /**
     * beginTransaction
     * 功能：开始事务操作
     */
    public void beginTransaction() throws wlglException {
        try {
            myCon.setAutoCommit(false);
        }

        catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "begintransaction失败", ex);
        }
    }


    /**
     * commit
     * 功能：确认事务操作
     */
    public void commit() throws wlglException {
        try {
            myCon.commit();
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "commit失败", ex);
        }

    }

    /**
     * rollback
     * 功能：回滚事务操作
     */
    public void rollback() throws wlglException {
        try {
            myCon.rollback();
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("208",
                    "rollback失败", ex);
        }

    }

    /**
     * exeQuery
     * 功能：执行更新,带参数
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     */
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


    /**
     * exeUpdate
     * 功能：执行更新,带参数
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     */
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

    /**
     * exeQuery
     * 功能：执行查询
     * @param sqlStr String SQL语句
     * @return ResultSet         结果集
     */
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

    /**
     * exeQueryOneRow
     * 功能：执行更新
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     */
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

    /**
     * exeQueryOneRow
     * 功能：执行更新
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     */
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

    /**
     * exeQueryOneRow
     * 功能：执行更新,带参数
     * @param sqlStr String SQL语句
     * @param params ArrayList 参数对象数组
     */
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

    
    

    /**
     * UtilDB
     * 功能: 初试化连接
     */
    public UtilDB() throws wlglException {
        if (myCon == null) {
            myCon = getCon();
            System.err.println("新的utildb");
        }
    }

    public void finalize() throws wlglException {
        //closeCon();
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
               myCon.close();
               myCon=null;
            }
        } catch (SQLException ex) {
            wlglException.ProcessMainWebExceptionMessage("206",
                    "关闭UtilDB的CloseCon失败.", ex);
        }

    }

    /**
     * getCount
     * 功能:取得结果纪录数
     * @return int
     */
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

    /**
     * getSomeRow
     * 功能:取得指定数目的行数据
     * @param beginIndex int
     * @param endIndex int
     * @return ArrayList
     */
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

    /**
     * getRowData
     * //取得行数据以数组形式返回
     * @param anObject ResultSet
     * @return ArrayList
     */
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

    }

}
