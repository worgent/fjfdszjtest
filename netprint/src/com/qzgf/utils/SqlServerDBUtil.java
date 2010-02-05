package com.qzgf.utils;

import javax.sql.DataSource;
import java.sql.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 数据库工具
 * @author lsr
 * @date 20081102
 *
 */
public final class SqlServerDBUtil {
	private static final Log log = LogFactory.getLog(SqlServerDBUtil.class);
	
	/**
     * 获取系统的数据源
     *
     * @return DataSource
     */
    public static DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            dataSource = (DataSource) ContextHelper.getContext().getBean("sqlServerDataSource");
        } catch (Exception e) {
            log.error("获取数据源出错，请检查Spring数据源配置！");
        }
        return dataSource;
    }

    /**
     * 获取数据库连接
     *
     * @return Connection
     */
    public static Connection makeConnection() {
        Connection conn = null;
        try {
            conn = getDataSource().getConnection();
        } catch (SQLException e) {
            log.error("通过数据源获取数据库连接发生异常！");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行没有参数的SQL过程
     *
     * @param procedureName 存储过程名字
     * @return boolean      返回存储过程执行的结果,true表示执行成功,false表示执行失败.
     */
    public static boolean executeBSDProcedure(String procedureName) {
        boolean flag = false;
        String sqlStr = "{call " + procedureName + "()}";
        CallableStatement cs;
        Connection conn = makeConnection();
        try {
            cs = (CallableStatement) conn.prepareStatement(sqlStr);
            cs.executeUpdate(sqlStr);
            flag = true;
        } catch (SQLException e) {
            log.error("调用存储过程" + sqlStr + "失败！");
            e.printStackTrace();
        }
        return flag;
    }
}
