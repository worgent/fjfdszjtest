package com.qzgf.NetStore.pub;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Redyc
 * @version1.0
 */
public class Page  {
	//UtilDB du=new UtilDB();
	
	//默认每页记录数
	public static final int DEFAULT_PAGESIZE=20;
	//当前页码
	private int currentPage=0;
	//每页记录数
	private int pageSize=DEFAULT_PAGESIZE;
	//总页面
	private int totalPages;
	//总记录数
	private int totalRecords;
	//是否有下一页
	private boolean hasNext;
	//是否有上一页
	private boolean hasPrevious;
	//上一页页码
	private int prevNum;
	//下一页页码
	private int nextNum;
	//记录
	private ResultSet  rowset;
	//记录封装
	@SuppressWarnings("unchecked")
	private List resultList;
	
	@SuppressWarnings("unchecked")
	public List getResultList() {
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Page() {
	}
	
	public Page(int pageNo,int pageSize){
		setCurrentPage(pageNo);
		setPageSize(pageSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public boolean isHasPrevious() {
		return hasPrevious;
	}
	public int getNextNum() {
		return nextNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getPrevNum() {
		return prevNum;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}
	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}
	
	public void setPrevNum(int prevNum) {
		this.prevNum = prevNum;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public void initOtherData() {
		setTotalPages((int)((totalRecords-1)/pageSize)+1);
		setHasNext(totalPages>currentPage?true:false);
		setHasPrevious(currentPage>1?true:false);
		setNextNum(hasNext?currentPage+1:totalPages);
		setPrevNum(hasPrevious?currentPage-1:1);
	}
	
	
	
	
	

	
	//产生流水号　公用函数
	public 	String  lshNO(String tag,int len,String tableStr,String dateField,String initField) //len：表示流水号总长度，init是已到的初始化值
	{
		UtilDB utilDb=null;
		try {
			utilDb = new UtilDB();
		} catch (wlglException e2) { 
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//取得初始化值
		String init="";
		String InitDate="";
		String sql="select  *  from  "+tableStr+"";
		 ResultSet rs = null;
		try {
			//rs=du.stmt.executeQuery(sql);
			rs=utilDb.executeQuery(sql);
			
			
			
			if(rs.next())
			{
				init=Integer.toString(rs.getInt(initField));//获得初始化值
				InitDate=rs.getString(dateField);//取得数据库中的日期
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//取得初始化值
		
		try {
			utilDb.closeCon();
		} catch (wlglException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
       String lshStr="";
       //取得当前日期
         Calendar rightNow = Calendar.getInstance();
	     SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	     String sToday = fmt.format(rightNow.getTime()); 
		//取得当前日期
	     
	    //写入数据库用的
		SimpleDateFormat FormatS = new SimpleDateFormat("yyyy-MM-dd");	
		String Today=FormatS.format(rightNow.getTime());
		
		//2008-08-08
		InitDate=InitDate.substring(0, 4)+InitDate.substring(5, 7)+InitDate.substring(8, 10);
		if(!sToday.equals(InitDate))//今天的日期比数据库存的日期不同
		{
		    init="1";
		}
	
		
		
		//写入数据库用的
	    String	zStr="";
		for (int i=0;i<len-init.length()-8;i++)
		{
			zStr=zStr+"0";	
		}
		lshStr=tag+sToday.substring(2)+zStr+init;//如pt080808+00+11
		
		
		//更新　基表的值
		int nowInit= Integer.parseInt(init)+1;
		String updateStr="update "+tableStr+" set "+dateField+"='"+Today+"',"+initField+"="+nowInit;
		try {
			utilDb = new UtilDB();
		} catch (wlglException e2) { 
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean flag=utilDb.executeUpdate(updateStr);
	    if(flag){
			try {
				utilDb.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }else{
	    	System.out.println("流水号更新失败:");
	    	try {
				utilDb.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		return lshStr;
	}

	//产生订单号
	public 	String  OrdLsh() //len：表示流水号总长度，init是已到的初始化值
	{
		
		UtilDB utilDb=null;
		try {
			utilDb = new UtilDB();
		} catch (wlglException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		//取得初始化值
		int len=16;
		String tableStr="t_lsh";
		String dateField="orderDate";
		String initField="orderInitValue";
		String init="";
		String InitDate="";
		String sql="select  *  from  "+tableStr+"";
		 ResultSet rs = null;
		try {
			rs=utilDb.executeQuery(sql);
			if(rs.next())
			{
				init=Integer.toString(rs.getInt(initField));//获得初始化值
				InitDate=rs.getString(dateField);//取得数据库中的日期
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			utilDb.closeCon();
		} catch (wlglException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       String lshStr="";
       //取得当前日期
         Calendar rightNow = Calendar.getInstance();
         //取得今年的年份
         rightNow.setTime(new Date());   
         String year=String.valueOf(rightNow.get(Calendar.YEAR));   
	     SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	     String sToday = fmt.format(rightNow.getTime()); //20080808
		//取得当前日期
	     
	    //写入数据库用的
		SimpleDateFormat FormatS = new SimpleDateFormat("yyyy-MM-dd");	
		String Today=FormatS.format(rightNow.getTime());
		fmt=new SimpleDateFormat("MMdd");
		String monthDay=fmt.format(rightNow.getTime());
		//2008-08-08---->20080808
		InitDate=InitDate.substring(0, 4)+InitDate.substring(5, 7)+InitDate.substring(8, 10);
		if(!sToday.equals(InitDate))//今天的日期比数据库存的日期不同
		{
		    init="1";
		}
	
		//写入数据库用的
	    String	zStr="";
		for (int i=0;i<len-init.length()-13;i++)
		{
			zStr=zStr+"0";	
		}
		//lshStr=tag+sToday.substring(2)+zStr+init;//如pt080808+00+11
		
		lshStr=year+"01161"+monthDay+zStr+init;
		//更新　基表的值
		int nowInit= Integer.parseInt(init)+1;
		String updateStr="update "+tableStr+" set "+dateField+"='"+Today+"',"+initField+"="+nowInit;
	    try {
			utilDb = new UtilDB();
		} catch (wlglException e2) { 
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		boolean flag=utilDb.executeUpdate(updateStr);
		if(flag){
			try {
				utilDb.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("订单流水号更新出错");
			try {
				utilDb.closeCon();
			} catch (wlglException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lshStr;
	}
	
	
	
	public void setRowset(ResultSet rowset) {
		this.rowset = rowset;
	}

	public ResultSet getRowset() {
		return rowset;
	}
	
	
	
	
	
	
}
