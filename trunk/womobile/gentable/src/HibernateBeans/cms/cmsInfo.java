/*
 * cmsInfo.java
 *
 * Created on 2008年5月19日, 下午6:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package HibernateBeans.cms;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class cmsInfo {
    
    /** Creates a new instance of cmsInfo */
    String version="2.0";
    public cmsInfo() {
    }
    
    public String getVersion()
    {
     return version;
    }
    
    public List getHistory()
    {
     List history=new ArrayList();
     history.add("AICMS1.0,发布时间：2008年5月10日。系统基本功能开发完成。");
     history.add("AICMS1.01,发布时间：2008年5月12日。增加类、属性快捷锁定（改/删保护）功能");
     history.add("AICMS1.02,发布时间：2008年5月19日。增加下级类对上级类属性的继承功能");
     history.add("AICMS1.03,发布时间：2008年5月21日。修改实体查询模块，递归查询方式换用辅助参数方式的查询，减小程序计算量，提高了查询效率。");
     history.add("AICMS1.04,发布时间：2008年5月23日。实体查询加入内部分页功能。修正结果集排序的Bug。修正实体删除时相应类、属性、数据集合的同步更改问题");
     history.add("AICMS1.05,发布时间：2008年5月28日。系统查询启用二级缓存，修正由于hibernate的lazy机制引发的Bug。增加每一类的后台管理定制功能(可以设定在后台管理中类的属性在增加数据/修改数据/数据列表中是否显示)");
     history.add("AICMS1.06,发布时间：2008年6月10日。修改数据查询排序功能，支持按整数数据类型进行排序");
     history.add("AICMS1.07,发布时间：2008年6月13日。修改数据关联查询时由于cache机制引发的空指针Bug");
     history.add("AICMS1.08,发布时间：2008年6月14日。修正类排序Bug");
     history.add("AICMS1.09,发布时间：2008年6月25日。全面修改查询模块，数据查询可以按属性快速排序，排序速度提高数倍.");
     history.add("AICMS1.1,发布时间：2008年7月25日。修正类锁定状态不同步的问题.");
     history.add("AICMS1.2,发布时间：2008年9月1日。修正fckeditor编辑器中文文件出错的问题,fckeditor(jar)版本为2.4");
     history.add("AICMS2.0,发布时间：2008年9月8日。创建CAEV简化易记对象，使用AICMS更加简单，修正hibernate数据库连接关闭Bug");
     return history;
    }
}
