package com.qzgf.context;

/**
 * 总页面数,每页记录数,总页数, 开始记录数,
 * @author lsr
 *
 */
public class Pages {
	int page = 1; // 前面想要跳转到的页号
	long totalNum = -1; // 记录总数
	int perPageNum = 20; // 每页显示记录数
	int epage=1;
	int allPage = 1; // 总页数
	int cpage = 1; // 当前页
	int spage = 1; // 开始记录数

	String fileName = ""; //要调用的URL,即得到需要分页的资源
	boolean useUrlRewrite = false; //动态页面静态化

	public Pages() {
	}
    
	
	public Pages(int page, long totalNum, int perPageNum) {
		this.page = page;
		this.totalNum = totalNum;
		this.perPageNum = perPageNum;
		this.executeCount();
	}

	//执行计算
	public void executeCount() {
		//计算总页数
		this.allPage = (int) Math.ceil((this.totalNum + this.perPageNum - 1) / this.perPageNum);
		int intPage = this.page;
		if (intPage > this.allPage) { // pages == 0
			//最后跳到的页面~~~????/
			this.cpage = 1;
		} else {
			this.cpage = intPage;
		}
		
		//开始记录数(文件头部)
		this.spage = (this.cpage - 1) * this.perPageNum+1;//从1开始oracle 2009-08-11
		//开始记录数(文件尾部)
		this.epage=this.spage+this.perPageNum-1;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public long getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}


	public int getPerPageNum() {
		return perPageNum;
	}


	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}


	public int getAllPage() {
		return allPage;
	}


	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}


	public int getCpage() {
		return cpage;
	}


	public void setCpage(int cpage) {
		this.cpage = cpage;
	}


	public int getSpage() {
		return spage;
	}


	public void setSpage(int spage) {
		this.spage = spage;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public boolean isUseUrlRewrite() {
		return useUrlRewrite;
	}


	public void setUseUrlRewrite(boolean useUrlRewrite) {
		this.useUrlRewrite = useUrlRewrite;
	}


	/**
	 * @return the epage
	 */
	public int getEpage() {
		return epage;
	}


	/**
	 * @param epage the epage to set
	 */
	public void setEpage(int epage) {
		this.epage = epage;
	}
	
	

}
