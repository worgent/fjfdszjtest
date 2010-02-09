package net.trust.datacollection.thread;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import net.trust.IbatisDaoTools.BaseSqlMapDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 数据采集定时器，DataCollectionTimerTask类是调用该方法
 * 线程DataCollectionTimerListener
 *
 */
public class DataCollectionThread extends Thread {
	private Log log = LogFactory.getLog(DataCollectionThread.class);
	
	private String interFetchConfigName;//采集规则的名称
	
	private String interSelectSql;	//采集接口数据Select SQL语句
	private String localSelectSql;	//本地查询SQL，用于判断数据是否已存在
	private String localInsertSql;	//本地数据表Insert SQL语句
	private String localUpdateSql;	//本地数据表Update SQL语句
	private ServletContext servletContext;
	private BaseSqlMapDAO baseSqlMapDAO;
	
	boolean runflag = true;

	public DataCollectionThread(){}
	/**
	 * @param interSelectSql	采集接口数据Select SQL语句
	 * @param localSelectSql	本地查询SQL，用于判断数据是否已存在
	 * @param localInsertSql	本地数据表Insert SQL语句
	 * @param localUpdateSql	本地数据表Update SQL语句
	 * @param servletContext
	 */
	public DataCollectionThread(String interFetchConfigName, String interSelectSql, String localSelectSql, String localInsertSql, String localUpdateSql, ServletContext servletContext){
		this.interFetchConfigName = interFetchConfigName;
		
		this.interSelectSql = interSelectSql;
		this.localSelectSql = localSelectSql;
		this.localInsertSql = localInsertSql;
		this.localUpdateSql = localUpdateSql;
		this.servletContext = servletContext;
	}
	
	public void run() {
		if (log.isDebugEnabled())
			log.debug("线程“"+interFetchConfigName+"”开始执行采集");
		
		//取得Spring上下文
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		DataSource dataSource = (DataSource)context.getBean("sqlServerDataSource");	//获取SqlServer数据连接池
		baseSqlMapDAO = (BaseSqlMapDAO)context.getBean("baseSqlMapDAO");	//获取MySql的Ibatis数据操作对象
		
		try {
			Connection conn = dataSource.getConnection();
			Statement stem = conn.createStatement();
			if (log.isDebugEnabled())
				log.debug(interSelectSql);
			ResultSet rs = stem.executeQuery(interSelectSql);
			
			boolean localSelectFlag = false, localInsertFlag = false, localUpdateFlag = false;
			
			//判断“查询MySql库表中是否已存在该记录”的SQL是否为空 
			if (null != localSelectSql && !"".equals(localSelectSql)){
				localSelectFlag = true;
			}//if
			//判断“MySql的Insert”的SQL是否为空
			if (null != localInsertSql && !"".equals(localInsertSql)){
				localInsertFlag = true;
			}//if
			//判断“MySql的Update”的SQL是否为空
			if (null != localUpdateSql && !"".equals(localUpdateSql)){
				localUpdateFlag = true;
			}//if
			
			if (localSelectFlag){	//有select的
				List selectField = createField(localSelectSql);
				
				if (localInsertFlag){	//有insert的
					List insertField = createField(localInsertSql);
					
					if (localUpdateFlag){	//有update的
						List updateField = createField(localUpdateSql);
						
						while (rs.next()){
							if (findIsExistIbatisSql(localSelectSql, selectField, rs) == 0){//判断数据库是否已存在该数据，不存在insert，存在update
								executeIbatisSql(localInsertSql, insertField, rs);	//不存在用insert
							}else{
								executeIbatisSql(localUpdateSql, updateField, rs);	//存在用update
							}//if
						}
						
					}else{
						while (rs.next()){
							if (findIsExistIbatisSql(localSelectSql, selectField, rs) > 0){//判断数据库是否已存在该数据，已存在则跳过
								executeIbatisSql(localInsertSql, insertField, rs);	//执行insert方法
							}
						}
						
					}//if
					
				}else if (localUpdateFlag){
					List updateField = createField(localUpdateSql);
					while (rs.next()){
						executeIbatisSql(localUpdateSql, updateField, rs);	//存在用update
					}
				}//if
				
			}else{
				if (localInsertFlag){	//有insert的
					List insertField = createField(localInsertSql);
					while (rs.next()){
						executeIbatisSql(localInsertSql, insertField, rs);	//不存在用insert
					}
					
				}else if (localUpdateFlag){
					List updateField = createField(localUpdateSql);
					while (rs.next()){
						executeIbatisSql(localUpdateSql, updateField, rs);	//存在用update
					}
				}//if
			}//if
			
			if (log.isDebugEnabled())
				log.debug("线程“"+interFetchConfigName+"”结束采集");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//try
	}
	
	/**
	 * 查询MySql库表中是否已存在该记录 
	 * @param sql	要执行的SQL，需经过语句替换与整合才是完整的
	 * @param field	字段列表，从原SQL提取出来的
	 * @param rs	SqlServer执行完所返回的结果集
	 * @return
	 * @throws SQLException
	 */
	public int findIsExistIbatisSql(String sql, List field, ResultSet rs) throws SQLException{
		Iterator it = field.iterator();
		String oldChar = "";
		String newChar = "";
		String tmp1 = "";
		String tmp2 = "";
		while (it.hasNext()){
			tmp1 = (String)it.next();
			oldChar = "::" + tmp1 + "::";
			newChar = "NULL";
			if (null != rs.getString(tmp1) && !"".equals(rs.getString(tmp1)))
				newChar = rs.getString(tmp1);
			
			tmp1 = sql.substring(0, sql.indexOf(oldChar));
			tmp2 = sql.substring(sql.indexOf(oldChar)+oldChar.length(), sql.length());
			sql = tmp1 + newChar + tmp2;
		}
		return ((Integer)baseSqlMapDAO.queryForObject("dynamicSqlCount", sql));
	}
	
	/**
	 * 执行MySql语句，用于执行Insert Update
	 * @param sql	要执行的SQL，需经过语句替换与整合才是完整的
	 * @param field	字段列表，从原SQL提取出来的
	 * @param rs	SqlServer执行完所返回的结果集
	 * @return
	 * @throws SQLException
	 */
	public int executeIbatisSql(String sql, List field, ResultSet rs) throws SQLException{
		Iterator it = field.iterator();
		String oldChar = "";
		String newChar = "";
		String tmp1 = "";
		String tmp2 = "";
		while (it.hasNext()){
			tmp1 = (String)it.next();
			oldChar = "::" + tmp1 + "::";
			newChar = "NULL";
			if (null != rs.getString(tmp1) && !"".equals(rs.getString(tmp1)))
				newChar = rs.getString(tmp1);
			
			tmp1 = sql.substring(0, sql.indexOf(oldChar));
			tmp2 = sql.substring(sql.indexOf(oldChar)+oldChar.length(), sql.length());
			sql = tmp1 + newChar + tmp2;
		}
		
		return baseSqlMapDAO.update("dynamicInsertAndUpdateSql", sql);
	}
	
	/**
	 * 生成要转换的字段名
	 * @param sql
	 * @return
	 */
	public List createField(String sql){
		List list = new ArrayList();
		String field = "";
		while (sql.indexOf("::") > -1){
			sql = sql.substring(sql.indexOf("::") + 2, sql.length());
			field = sql;
			field = field.substring(0, sql.indexOf("::"));
			list.add(field);
			sql = sql.substring(sql.indexOf("::") + 2, sql.length());
		}
		return list;
	}
	
	/**
	 * 停止线程
	 */
	public synchronized void stopThread() {
		runflag = false;
	}

	/**
	 * 获取线程运行状态
	 * @return
	 */
	public synchronized boolean getRunFlag() {
		return runflag;
	}
	
	public static void main(String arg[]){
		String a = "111";
		System.out.println(a.getClass().getName());
		
		
//		DataCollectionThread dataCollectionThread = new DataCollectionThread();
//		String sql = "INSERT INTO INTER_CAR_INFO"+
//					"		  (INTER_CAR_INFO_ID,"+
//					"		   CAR_MARK,"+
//					"		   CARID,"+
//					"		   CAR_GROUP,"+
//					"		   CAR_CLASS,"+
//					"		   CAR_TYPE,"+
//					"		   MOTORID,"+
//					"		   GSM_NUMBER,"+
//					"		   CAR_COLOR,"+
//					"		   COMPANYID,"+
//					"		   INSTALL_TIME,"+
//					"		   CAR_STATUS,"+
//					"		   LICENSE,"+
//					"		   REGIST_AGENT,"+
//					"		   REGIST_TIME,"+
//					"		   ACCIDENT_DATE,"+
//					"		   CAR_MUSTERTIME,"+
//					"		   CAR_MUSTERFLAG,"+
//					"		   LAST_LON,"+
//					"		   LAST_LAT,"+
//					"		   LAST_SPEED,"+
//					"		   LAST_ANGLE,"+
//					"		   LAST_TIME,"+
//					"		   LAST_FLAG,"+
//					"		   TERMINAL_VER,"+
//					"		   TERMINAL_UPDATE_TIME,"+
//					"		   MAX_SPEED,"+
//					"		   INBOUND_LIMIT,"+
//					"		   OUTBOUND_LIMIT,"+
//					"		   TERMINAL_REPORT,"+
//					"		   TERMINAL_REPORT_TIME,"+
//					"		   LAST_LOCATION,"+
//					"		   UPDATE_RECORD_DATE)"+
//					"		VALUES"+
//					"		  (pb_get_sequences(@in_seq_name:='INTER_CAR_INFO_ID'),"+
//					"		   '::CAR_MARK::',"+
//					"		   ::CARID::,"+
//					"		   ::CAR_GROUP::,"+
//					"		   ::CAR_CLASS::,"+
//					"		   '::CAR_TYPE::',"+
//					"		   '::MOTORID::',"+
//					"		   '::GSM_NUMBER::',"+
//					"		   '::CAR_COLOR::',"+
//					"		   '::COMPANYID::',"+
//					"		   '::INSTALL_TIME::',"+
//					"		   '::CAR_STATUS::',"+
//					"		   '::LICENSE::',"+
//					"		   '::REGIST_AGENT::',"+
//					"		   '::REGIST_TIME::',"+
//					"		   '::ACCIDENT_DATE::',"+
//					"		   '::CAR_MUSTERTIME::',"+
//					"		   '::CAR_MUSTERFLAG::',"+
//					"		   ::LAST_LON::,"+
//					"		   ::LAST_LAT::,"+
//					"		   ::LAST_SPEED::,"+
//					"		   ::LAST_ANGLE::,"+
//					"		   '::LAST_TIME::',"+
//					"		   ::LAST_FLAG::,"+
//					"		   ::TERMINAL_VER::,"+
//					"		   '::TERMINAL_UPDATE_TIME::',"+
//					"		   ::MAX_SPEED::,"+
//					"		   ::INBOUND_LIMIT::,"+
//					"		   ::OUTBOUND_LIMIT::,"+
//					"		   ::TERMINAL_REPORT::,"+
//					"		   '::TERMINAL_REPORT_TIME::',"+
//					"		   '::LAST_LOCATION::',"+
//					"		   NOW())";
//		List list = dataCollectionThread.createField(sql);
//		System.out.println(list);
//		sql = "	UPDATE inter_car_info"+
//				"   SET CAR_GROUP = ::CAR_GROUP::,"+
//				"       CAR_CLASS = ::CAR_CLASS::,"+
//				"       CAR_TYPE = ''::CAR_TYPE::'',"+
//				"       MOTORID = ''::MOTORID::'',"+
//				"       GSM_NUMBER = ''::GSM_NUMBER::'',"+
//				"       CAR_COLOR = ''::CAR_COLOR::'',"+
//				"       COMPANYID = ''::COMPANYID::'',"+
//				"       INSTALL_TIME = ''::INSTALL_TIME::'',"+
//				"       CAR_STATUS = ''::CAR_STATUS::'',"+
//				"       LICENSE = ''::LICENSE::'',"+
//				"       REGIST_AGENT = ''::REGIST_AGENT::'',"+
//				"       REGIST_TIME = ''::REGIST_TIME::'',"+
//				"       ACCIDENT_DATE = ''::ACCIDENT_DATE::'',"+
//				"       CAR_MUSTERTIME = ''::CAR_MUSTERTIME::'',"+
//				"       CAR_MUSTERFLAG = ''::CAR_MUSTERFLAG::'',"+
//				"       LAST_LON = ::LAST_LON::,"+
//				"       LAST_LAT = ::LAST_LAT::,"+
//				"       LAST_SPEED = ::LAST_SPEED::,"+
//				"       LAST_ANGLE = ::LAST_ANGLE::,"+
//				"       LAST_TIME = ''::LAST_TIME::'',"+
//				"       LAST_FLAG = ::LAST_FLAG::,"+
//				"       TERMINAL_VER = ::TERMINAL_VER::,"+
//				"       TERMINAL_UPDATE_TIME = ''::TERMINAL_UPDATE_TIME::'',"+
//				"       MAX_SPEED = ::MAX_SPEED::,"+
//				"       INBOUND_LIMIT = ::INBOUND_LIMIT::,"+
//				"       OUTBOUND_LIMIT = ::OUTBOUND_LIMIT::,"+
//				"       TERMINAL_REPORT = ::TERMINAL_REPORT::,"+
//				"       TERMINAL_REPORT_TIME = ''::TERMINAL_REPORT_TIME::'',"+
//				"       LAST_LOCATION = ''::LAST_LOCATION::'',"+
//				"       UPDATE_RECORD_DATE = NOW()"+
//				" WHERE CAR_MARK = ''::CAR_MARK::''"+
//				"   AND CARID = ::CARID::";
//		list = dataCollectionThread.createField(sql);
//		System.out.println(list);
	}
}
