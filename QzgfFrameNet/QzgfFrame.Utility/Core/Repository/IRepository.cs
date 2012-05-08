using System;
using System.Collections;
using System.Collections.Generic;

namespace QzgfFrame.Utility.Core.Repository
{
    public interface IRepository<T>
    {
        //基本 
        T Get(object id);
        bool Delete(T entity);
        bool Delete(object id);//这个比上个实用.
        bool DeleteHql(string where);
        bool DeletebyHql(string hql);
        bool Save(T entity);
        bool Update(T entity);
        bool Execute(string where);
        IList<T> LoadAll();
        IList<T> LoadAll(string order, string where);
        IList<object[]> LoadAllObj(string hql);
        IList<object[]> LoadAllSqlObj(string sql);
        bool Update(string where);

        //支持单表与多表,多表时hql中多加入new object()实现
        IList<object[]> FindByLinkPage(int pageNo, int pageSize, String hql);
        int FindByPageLinkCount(String hql);
        IList<T> FindByPage(int pageNo, int pageSize, String hql);
        int FindByPageCount(String hql);
        T GetbyHql(string hql);
        IList<T> LoadAllbyHql(string hql);


        bool IsFieldExist(string fieldName, string fieldValue, string id, string where);
        string NewSequence(String tablename);
        string NewSequence(String tablename, string no);


    }
}
