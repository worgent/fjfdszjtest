package com.qzgf.utils.pagination;

/**
 *
 * <p>Title: 分页接口</p>
 * @version 1.0<br>
 */
public interface Pagination {

  /**
   * 第一页
   */
  public void firstPage();

  /**
   * 最后一页
   */
  public void lastPage();

  /**
   * 下一页
   */
  public void nextPage();

  /**
   * 前一页
   */
  public void previousPage();

  /**
   * 转到指定页数
   * @param i 要转到页数
   */
  public void gotoPage(int i);

  /**
   * 转到指定页数,如果输入next,则跳转到下一页,如果输入previous,则跳转到上一页,
   * 如果输入first,则跳转到第一页,如果输入last,则跳转到最后一页,其他则把输入的
   * 字符串转的数字,然后跳转到指定的页数
   * @param s
   */
  public void turnPage(String s);

  /**
   * 取得当前页是第几页
   * @return
   */
  public int getCurrentPage();

  /**
   * 取得当前页的记录数
   * @return
   */
  public int getCurrentPageRecord();

  /**
   * 取得当前页开始的记录是第几个记录
   * @return 前页开始记录是第几个记录
   */
  public int getPageStart();

  /**
   * 取得当前页最后一个是第几个记录
   * @return 前页最后一个是第几个记录
   */
  public int getPageEnd();

  /**
   * 取得每一页的记录数
   * @return
   */
  public int getPageRecord();

  /**
   * 取得总的页数
   * @return 总的页数
   */
  public int getTotalPage();
  /**
   * 设置总的记录数
   * @param iTotalRecord
   */
  public void setTotalRecord(int iTotalRecord) ;

  /**
   * 取得总的记录数
   * @return 记录数
   */
  public int getTotalRecord();

  /**
   * 设置每页的记录数
   * @param i 每页的记录数
   */
  public void setPageRecord(int i);



}
