package com.qzgf.utils.pagination;

public class PaginationImplt implements Pagination {
    protected int pageRecord; //每页的记录数
    protected int currentPage; //当前是第几页
    protected int totalRecord; //总的记录数
    protected int totalPage; //总的页数
    protected int iMaxRowCount = 200; //显示的最大行数

    public PaginationImplt(){
        pageRecord = 15;
        currentPage = 1;
        totalRecord = 0;
        totalPage = 0;
    }
    /**
     * 清空
     */
    public void clear(){
        pageRecord = 15;
        currentPage = 1;
        totalRecord = 0;
        totalPage = 0;    	
    }
    /**
     * 第一页
     */
    public void firstPage() {
        currentPage = 1;
    }

    /**
     * 最后一页
     */
    public void lastPage() {
        currentPage = totalPage;
    }

    /**
     * 下一页
     */
    public void nextPage() {
        currentPage++;
        rationalPage();
    }

    /**
     * 前一页
     */
    public void previousPage() {
        currentPage--;
        rationalPage();
    }

    /**
     * 转到指定页数
     * @param i 要转到页数
     */
    public void gotoPage(int i) {
        currentPage = i;
        rationalPage();
    }

    /**
     * 转到指定页数,如果输入next,则跳转到下一页,如果输入previous,则跳转到上一页,
     * 如果输入first,则跳转到第一页,如果输入last,则跳转到最后一页,其他则把输入的
     * 字符串转的数字,然后跳转到指定的页数
     * @param s
     */
    public void turnPage(String str) {
        if (str != null && !str.equals("")) {
            str = str.toLowerCase();
            if (str.equals("next")) {
                nextPage();
            }
            else if (str.equals("previous")) {
                previousPage();
            }
            else if (str.equals("first")) {
                firstPage();
            }
            else if (str.equals("last")) {
                lastPage();
            }
            else {
                try {
                    int i = Integer.parseInt(str);
                    gotoPage(i);
                }
                catch (NumberFormatException ex) {
                }
            }
        }
    }

    /**
     * 取得当前页是第几页
     * @return
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 取得当前页的记录数
     * @return
     */
    public int getCurrentPageRecord() {
        int i;
        if (getCurrentPage() < getTotalPage()) {
            i = getPageRecord();
        }
        else {//如果到最后一页，取得记录数可是getPageRecord()也有可能是余数
            i = getTotalRecord() % getPageRecord();
            if (i == 0) {
                i = getPageRecord();
            }
        }
        return i;
    }

    /**
     * 取得当前页开始的记录是第几个记录
     * @return 前页开始记录是第几个记录
     */
    public int getPageStart() {
        return getPageStartReal() + 1;
    }

    /**
     * 取得当前页最后一个是第几个记录
     * @return 前页最后一个是第几个记录
     */
    public int getPageEnd() {
        return getPageEndReal() + 1;
    }

    /**
     * 取得每一页的记录数
     * @return
     */
    public int getPageRecord() {
        return pageRecord;
    }

    /**
     * 取得总的页数
     * @return 总的页数
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 取得总的记录数
     * @return 记录数
     */
    public int getTotalRecord() {
        return totalRecord;
    }
    /**
     * 设置总的记录数
     * @param totalRecord
     */
    public void setTotalRecord(int totalRecord) {
        currentPage = 1;
        this.totalRecord = totalRecord;
        totalPage = ( (totalRecord + pageRecord) - 1) / pageRecord;
    }
    /**
     * 设置每页的记录数
     * @param i 每页的记录数
     */
    public void setPageRecord(int i) {
        if (i != pageRecord && i >= 1) {
            pageRecord = i;
            totalPage = ( (totalRecord + pageRecord) - 1) / pageRecord;
            currentPage = 1;
        }
    }
    /**
     * 设置真实的是第几页,使当前的页不会小于0和大于最大记录页数
     */
    private void rationalPage() {
        if(currentPage < 1)
            currentPage = 1;
        else if(currentPage > totalPage)
            currentPage = totalPage;
    }

    //取得当前页开始的记录是第几个记录,按数组的下标计算
    public int getPageStartReal() {
        int i = (currentPage - 1) * pageRecord;
        return i;
    }

    //取得当前页最后一个是第几个记录,按数组的下标计算
    public int getPageEndReal(){
        int i = Math.min(currentPage * pageRecord - 1, totalRecord - 1);
        return i;
    }
    
    //是否是第一页
    public boolean isFirst(){
    	if(currentPage<=1)
    		return true;
    	else 
    		return false;
    }
    
    //是否是最后一页
    public boolean isLast(){
    	if(currentPage>=totalPage)
    		return true;
    	else 
    		return false;
    }
}
