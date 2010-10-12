/*
 * pageResult.java
 *
 * Created on 2008年5月16日, 上午2:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 * 分页辅助类
 */
public class pageResult {
    
    /** Creates a new instance of pageResult */
    private int pageSize;
    private int currentPage;
    private int pageCount;
    private int recordCount;
    private int firstResult;
    private int lastResult;
    private int sortRules=-1;//排序规则，1代表正序，-1代表倒序，默认为倒序
    private String sortKey;//排序关键词(属性)
    private List list=new ArrayList();
    public pageResult() {
        pageSize=10;
        currentPage=1;
        firstResult=0;
        lastResult=pageSize;
    }
    public pageResult(int pageSize,int currentPage) {
        this.currentPage=currentPage;
        this.pageSize=pageSize;
    }
//============================================================================================
//<editor-fold defaultstate="collapsed" desc="pageSize,页面记录条数--手动设定">
/**
 *设置页面记录条数
 */
public void setPageSize(int pageSize)
{
 this.pageSize=pageSize;
}

/**
 *获得每页记录数
 */
public int getPageSize()
{
 return pageSize;
}
//</editor-fold>
//=============================================================================================
//<editor-fold defaultstate="collapsed" desc="currentPage,当前页码--手动设定">
/**
 *设置当前页码
 */
public void setCurrentPage(int currentPage)
{
  this.currentPage=currentPage;
}

/**
 *获得当前页码
 */
public int getCurrentPage()
{
 return currentPage;
}
//</editor-fold>
//=============================================================================================
//<editor-fold defaultstate="collapsed" desc="firstResult,第一条记录--通过rebuild计算得出">
/**
 *设置第一条记录
 */
public void setFirstResult(int firstResult)
{
  this.firstResult=firstResult;
}
/**
 *获得第一条记录
 */
public int getFirstResult()
{
 return firstResult;
}
//</editor-fold>
//=============================================================================================
//<editor-fold defaultstate="collapsed" desc="lastResult,最后一条记录--通过rebuild计算得出">
/**
 *设置最后一条记录
 */
public void setLastResult(int lastResult)
{
  this.lastResult=lastResult;
}
/**
 *获得最后一条记录
 */
public int getLastResult()
{
 return lastResult;
}
//</editor-fold>
//=============================================================================================
//<editor-fold defaultstate="collapsed" desc="pageCount,总页数--根据记录集rebuild计算得出">
/**
 *设置总页数
 */
public void setPageCount(int pageCount)
{
  this.pageCount=pageCount;
}
/**
 *获得总页数
 */
public int getPageCount()
{
 return pageCount;
}
//</editor-fold>
//=============================================================================================
//<editor-fold defaultstate="collapsed" desc="recordCount,总记录数--根据记录集设定">
/**
 *设置总记录数
 */
public void setRecordCount(int recordCount)
{
  this.recordCount=recordCount;
  rebuild();
}
/**
 *获得总页数
 */
public int getRecordCount()
{
 return recordCount;
}
//</editor-fold>
//==============================================================================================
//<editor-fold defaultstate="collapsed" desc="sortRules,排序规则">
/**
 *排序规则，1代表正序，-1代表倒序，默认为倒序
 */
public void setSortRules(int sortRules)
{
  this.sortRules=sortRules;
}
/**
 *排序规则，1代表正序，-1代表倒序，默认为倒序
 */
public int getSortRules()
{
 return sortRules;
}
//</editor-fold>
//==============================================================================================
//<editor-fold defaultstate="collapsed" desc="sortKey,排序关键词">
/**
 *排序关键词(属性名)
 */
public void setSortKey(String sortKey)
{
  this.sortKey=sortKey;
}
/**
 *排序关键词(属性名)
 */
public String getSortKey()
{
 return sortKey;
}
//</editor-fold>
//==============================================================================================
//<editor-fold defaultstate="collapsed" desc="list,本页记录集--最终分页得到的当前页记录集">
/**
 *设置本页记录集
 */
public void setList(List list)
{
 this.list=list;
}
/**
 *设置本页记录集
 */
public void setList(Set set)
{
 this.list=new ArrayList(set);
}
/**
 *获得本页记录集
 */
public List getList()
{
 return list;
}
//</editor-fold>
//================================================================================================
//<editor-fold defaultstate="collapsed" desc="rebuild(),重新计算">
/**
 *重新计算
 */
private void rebuild()
{
 pageCount=(int)Math.ceil(recordCount/new Double(pageSize*1.0).doubleValue());
 if(currentPage<1)currentPage=1;
 if(currentPage>pageCount)currentPage=pageCount;
 firstResult=currentPage*pageSize-pageSize;
 lastResult=currentPage*pageSize;
 if(firstResult<0)firstResult=0;
 if(lastResult>=recordCount)lastResult=recordCount;
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="clean():清空结果">
/**
 *清空结果
 */
    public void clean()
    {
     list.clear();
    }
//</editor-fold>
}
