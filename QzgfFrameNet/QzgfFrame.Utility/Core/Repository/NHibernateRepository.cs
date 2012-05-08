using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernate;
using NHibernate.Mapping;
using QzgfFrame.Utility.Core.DomainModel;
using Spring.Data.NHibernate.Generic;
using Spring.Data.NHibernate.Generic.Support;

namespace QzgfFrame.Utility.Core.Repository
{
    public class NHibernateRepository<T> : HibernateDaoSupport, IRepository<T>
    {
        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        #region 基本方法
        public bool Save(T entity)
        {
            try
            {
                this.HibernateTemplate.Save(entity);
                return true;
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }
        /// <summary>
        /// 返回指定行的对象(通过)
        /// </summary>
        /// <param name="id">主键,传入时类型转化为与模型相同的类型</param>
        /// <returns>返回模型对象</returns>
        public T Get(object id)
        {
            Logger.Info("get info");
            try
            {
                return this.HibernateTemplate.Get<T>(id);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return default(T);
            }
        }

        /// <summary>
        /// 返回指定行的对象(通过)
        /// </summary>
        /// <param name="hql">通过写hql返回对象</param>
        /// <returns>返回模型对象</returns>
        public T GetbyHql(string hql)
        {
            IList<T> ls = this.HibernateTemplate.ExecuteFind<T>(delegate(ISession session)
            {
                try
                {
                    IQuery query = session.CreateQuery(hql);
                    IList<T> list = query.List<T>();
                    return list;
                }
                catch (Exception ex)
                {
                    Logger.Error(ex.Message, ex);
                    return null;
                }
            });
            return (ls == null || ls.Count == 0) ? default(T) : ls[0];
        }


        public bool Update(T entity)
        {
            try
            {
                this.HibernateTemplate.SaveOrUpdate(entity);
                return true;
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }
        public bool Execute(string where)
        {
            try
            {
                string hql = string.Format("from {0}  {1} ",
                   typeof(T).ToString(),
                   where.ToUpper().StartsWith("WHERE") ? where : "WHERE " + where);
                return this.HibernateTemplate.Execute(delegate(ISession session)
                {
                    IQuery query = this.Session.CreateQuery(hql);
                    int ret = query.ExecuteUpdate();
                    if (ret > 0)
                        return true;
                    else
                        return false;

                }, true);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }
        //以上通过HibernateTemplate调用Criteria。
        public bool Update(string where)
        {
            try
            {
                string hql = string.Format("update {0} {1}",
                   typeof(T).ToString(),
                   where.ToUpper().StartsWith("WHERE") ? where : "Set " + where);
                return this.HibernateTemplate.Execute(delegate(ISession session)
                {
                    IQuery query = this.Session.CreateQuery(hql);
                    int ret = query.ExecuteUpdate();
                    if (ret > 0)
                        return true;
                    else
                        return false;
                }, true);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }
        /// <summary>
        ///  删除指定id数据
        /// </summary>
        /// <param name="id">主键id,删除实体</param>
        /// <returns></returns>
        public bool Delete(object id)
        {
            try
            {
                var entity = this.HibernateTemplate.Get<T>(id);
                this.HibernateTemplate.Delete(entity);
                return true;
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }

        /// <summary>
        /// 删除指定实体
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Delete(T entity)
        {
            try
            {
                this.HibernateTemplate.Delete(entity);
                return true;
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }
        public bool DeleteHql(string where)
        {
            try
            {
                string hql = string.Format("from {0}  {1} ",
                   typeof(T).ToString(),
                   where.ToUpper().StartsWith("WHERE") ? where : "WHERE " + where);
                return this.HibernateTemplate.Execute(delegate(ISession session)
                {
                    this.Session.Delete(hql);
                    return true;

                }, true);
            }
            catch (Exception ex)
            {
                return false;
            }
        }

        /// <summary>
        ///  删除对像数据的hql语句
        /// </summary>
        /// <param name="hql">删除对像数据的hql语句</param>
        /// <returns></returns>
        public bool DeletebyHql(string hql)
        {
            try
            {
                this.HibernateTemplate.Delete(hql);
                return true;
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return false;
            }
        }

        public IList<T> LoadAll()
        {
            return this.HibernateTemplate.LoadAll<T>();
        }
        /// <summary>
        /// sql动态的返回对象数据列表
        /// </summary>
        /// <param name="objectName">表名</param>
        /// <param name="order">排序字段</param>
        /// <param name="where">过滤条件</param>
        /// <returns></returns>
        public IList<T> LoadAll(string order, string where)
        {
            //Logger.Warn("Warn");
            //Logger.Debug("Debug");
            //Logger.Error("erro");
            //Logger.Info("info");
            try
            {
                string hql = string.Format("from {0}  {1} order by {2}",
                   typeof(T).ToString(),
                   where.ToUpper().StartsWith("WHERE") ? where : "WHERE " + where, order);
                return this.HibernateTemplate.Find<T>(hql);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return null;
            }
        }
        /// <summary>
        /// hql动态的返回对象数据列表
        /// </summary>
        /// <param name="objectName">表名</param>
        /// <param name="order">排序字段</param>
        /// <param name="where">过滤条件</param>
        /// <returns></returns>
        public IList<object[]> LoadAllObj(string hql)
        {
            //Logger.Warn("Warn");
            //Logger.Debug("Debug");
            //Logger.Error("erro");
            //Logger.Info("info");
            try
            {
                return this.HibernateTemplate.ExecuteFind(delegate(ISession session)
                {
                    IQuery q = Session.CreateQuery(hql);
                    IList<object[]> list = q.List<object[]>();
                    return list;
                }, true);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return null;
            }
        }
        /// <summary>
        /// sql动态的返回数据列表
        /// </summary>
        /// <param name="objectName">表名</param>
        /// <param name="order">排序字段</param>
        /// <param name="where">过滤条件</param>
        /// <returns></returns>
        public IList<object[]> LoadAllSqlObj(string sql)
        {
            //Logger.Warn("Warn");
            //Logger.Debug("Debug");
            //Logger.Error("erro");
            //Logger.Info("info");
            try
            {
                return this.HibernateTemplate.ExecuteFind(delegate(ISession session)
                {
                    ISQLQuery q = Session.CreateSQLQuery(sql);
                    IList<object[]> list = q.List<object[]>();
                    return list;
                }, true);
            }
            catch (Exception ex)
            {
                Logger.Error(ex.Message, ex);
                return null;
            }
        }
        /// <summary>
        /// 返回指定行的对象(通过)
        /// </summary>
        /// <param name="hql">通过写hql返回对象</param>
        /// <returns>返回模型对象</returns>
        public IList<T> LoadAllbyHql(string hql)
        {
            return this.HibernateTemplate.ExecuteFind<T>(delegate(ISession session)
            {
                try
                {
                    IQuery query = session.CreateQuery(hql);
                    IList<T> list = query.List<T>();
                    return list;
                }
                catch (Exception ex)
                {
                    Logger.Error(ex.Message, ex);
                    return default(IList<T>);
                }
            });
        }
        #endregion


        #region 增加方案
        /// <summary>
        /// 分页显示数据代码
        /// </summary>
        /// <param name="pageNo">开始号码</param>
        /// <param name="pageSize">页面大小</param>
        /// <param name="hql">sql语句</param>
        /// <returns>返回list列表</returns>
        public IList<T> FindByPage(int pageNo, int pageSize, String hql)
        {
            return this.HibernateTemplate.ExecuteFind<T>(delegate(ISession session)
            {
                IQuery query = session.CreateQuery(hql);
                int first = (pageNo - 1) * pageSize;
                query.SetFirstResult(first);
                query.SetMaxResults(pageSize);
                IList<T> list = query.List<T>();
                return list;
            }
                );

        }
        /// <summary>
        /// 分页页面用到的页面记录总数
        /// </summary>
        /// <param name="hql">传入sql语句</param>
        /// <returns>记录数</returns>
        public int FindByPageCount(String hql)
        {
            IList<T> listhql = this.HibernateTemplate.ExecuteFind<T>(delegate(ISession session)
            {
                IQuery query = session.CreateQuery(hql);
                IList<T> list = query.List<T>();
                return list;
            });
            return listhql.Count;
        }
        /// <summary>
        /// 分页显示数据代码
        /// </summary>
        /// <param name="pageNo">开始号码</param>
        /// <param name="pageSize">页面大小</param>
        /// <param name="hql">sql语句</param>
        /// <returns>返回list列表</returns>
        public IList<object[]> FindByLinkPage(int pageNo, int pageSize, String hql)
        {
            return this.HibernateTemplate.ExecuteFind<object[]>(delegate(ISession session)
            {
                IQuery query = session.CreateQuery(hql);
                int first = (pageNo - 1) * pageSize;
                query.SetFirstResult(first);
                query.SetMaxResults(pageSize);
                IList<object[]> list = query.List<object[]>();
                return list;
            });
        }

        /// <summary>
        /// 分页页面用到的页面记录总数
        /// </summary>
        /// <param name="hql">传入sql语句</param>
        /// <returns>记录数</returns>
        public int FindByPageLinkCount(String hql)
        {
            IList<object[]> listhql = this.HibernateTemplate.ExecuteFind<object[]>(delegate(ISession session)
            {
                IQuery query = session.CreateQuery(hql);
                IList<object[]> list = query.List<object[]>();
                return list;
            });
            return listhql.Count;
        }

        /// <summary>
        /// 查找一个字段是否已经存在
        /// </summary>
        /// <param name="fieldName"></param>
        /// <param name="fieldValue"></param>
        /// <param name="id"></param>
        /// <param name="where"></param>
        /// <returns></returns>
        public bool IsFieldExist(string fieldName, string fieldValue, string id, string where)
        {
            bool flag = false;

            if (!string.IsNullOrEmpty(where))
                where = @" and " + where;


            IList<long> list = this.HibernateTemplate.ExecuteFind<long>(delegate(ISession session)
            {
                var query = session.CreateQuery(
                string.Format(@"select count(*) from {0} as o where o.{1}='{2}' and o.Id<>'{3}'" + where,
                typeof(T).Name,
                fieldName,
                fieldValue, id));

                return query.List<long>();

            });
            if (list.Count > 0)
            {
                flag = ((int)list[0] > 0) ? true : false;
            }
            return flag;
        }
        /// <summary>
        /// 关键根据DataFilter定义的数据结构,动态生成查询sql语句
        /// </summary>
        /// <param name="filters">过滤字段</param>
        /// <param name="a">表名</param>
        /// <returns></returns>
        public string GetHqlstrByExtFilter(List<DataFilter> filters, string a)
        {
            if (filters == null)
                return string.Empty;
            StringBuilder result = new StringBuilder();
            //type=string
            var stringList = from f in filters where f.type == "string" select f;
            foreach (var i in stringList)
            {
                result.Append(a + "." + i.field + " like " + "'%" + i.value + "%'" + " and ");
            }
            //type=boolean
            var booleanList = from f in filters where f.type == "boolean" select f;
            foreach (var i in booleanList)
            {
                result.Append(a + "." + i.field + "=" + i.value + " and ");
            }
            //type=numeric
            var numericList = from f in filters where f.type == "numeric" group f by f.field into g select g;
            foreach (var i in numericList)
            {
                result.Append("( ");
                string iiStr = string.Empty;
                foreach (var ii in i)
                {
                    iiStr += a + "." + ii.field + GetComparison(ii.comparison) + ii.value + " and ";
                }
                result.Append(iiStr.Substring(0, iiStr.Length - 4));
                result.Append(" )");
                result.Append(" and ");
            }
            //type=date
            var dateList = from f in filters where f.type == "date" group f by f.field into g select g;
            foreach (var i in dateList)
            {
                result.Append("( ");
                string iiStr = string.Empty;
                foreach (var ii in i)
                {
                    iiStr += a + "." + ii.field + GetComparison(ii.comparison) + " to_date('" + ii.value + "', 'mm/dd/yyyy')" + " and ";
                }
                result.Append(iiStr.Substring(0, iiStr.Length - 4));
                result.Append(" )");
                result.Append(" and ");
            }
            //type=list  :["1","2"]
            var listList = from f in filters where f.type == "list" select f;
            foreach (var i in listList)
            {
                result.Append(a + "." + i.field + " in " + i.value.Replace("[", "( ").Replace("]", " )").Replace("\"", "'") + " and ");
            }

            return result.ToString().Substring(0, result.Length - 4);
        }
        /// <summary>
        /// 表达式转化
        /// </summary>
        /// <param name="comparison">页面传入的运算符</param>
        /// <returns></returns>
        private string GetComparison(string comparison)
        {
            string res = string.Empty;
            switch (comparison)
            {
                case "lt":
                    res = "<";
                    break;
                case "gt":
                    res = ">";
                    break;
                case "eq":
                    res = "=";
                    break;
            }
            return res;
        }


        public IList<T> GetGridView(string gridViewName)
        {
            IList<T> list = this.HibernateTemplate.ExecuteFind<T>(delegate(ISession session)
            {
                var query = session.CreateQuery(string.Format(@"select * from " + gridViewName));

                return query.List<T>();
            });
            return list;
        }


        /// <summary>
        /// 主键生成机制,调用存储过程(测试已经过,但针对不同数据库需独立写存函数,暂不用)
        /// </summary>
        /// <param name="tablename">表名</param>
        /// <returns>sql</returns>
        //public string NewSequence(String tablename)
        //{
        //    /**
        //    * tablename为表名
        //    * 调用数据库中的函数sys_get_sequences生成主键YYYYmmDDHHmmss012345678
        //    */
        //    string result = "";

        //    String procName = "select sys_get_sequences(?)";

        //    IList<string> mylist=this.HibernateTemplate.ExecuteFind<string>(delegate(ISession session)
        //    {
        //        try
        //        {

        //            IQuery query = session.CreateSQLQuery(procName);
        //            query.SetString(0, tablename);
        //            IList<string> list = query.List<string>();
        //            return list;
        //        }catch(Exception ex)
        //        {
        //            Logger.Error(ex.Message, ex);
        //            return null;
        //        }

        //    }
        //     );

        //    if (mylist.Count > 0)
        //    {
        //        result = mylist[0];
        //    }
        //    return result;
        //}
        Random ra = new Random();
        public string NewSequence(String tablename)//前缀编号4位
        {
            string strtime = DateTime.Now.ToString("yyyyMMddHHmmss");
            string randNo = ra.Next(10000, 99999).ToString();
            int checkNo = Convert.ToInt32(strtime.Substring(13, 1)) + Convert.ToInt32(randNo.Substring(2, 1));
            string checkNostr = checkNo.ToString();
            if (checkNo >= 10) checkNostr = checkNostr.Substring(1, 1);
            string serialNo = strtime + randNo + checkNostr;//保证20位 14+5+1
            return zeroPlus(20, serialNo);
        }


        public string NewSequence(String tablename, string no)//前缀编号4位
        {
            string strtime = DateTime.Now.ToString("yyyyMMddHHmmss") ;            
            //var ra = new Random();
            string randNo = no + ra.Next(1000, 9999).ToString();
            if (no.Length == 2)
            {
                randNo =no+ ra.Next(100, 999).ToString();
            }
            if (no.Length == 3)
            {
                randNo = no + ra.Next(10, 99).ToString();
            }
            if (no.Length == 4)
            {
                randNo = no + ra.Next(1, 9).ToString();
            }
            int checkNo = Convert.ToInt32(strtime.Substring(13, 1)) + Convert.ToInt32(randNo.Substring(2, 1));
            string checkNostr = checkNo.ToString();
            if (checkNo >= 10) checkNostr = checkNostr.Substring(1, 1);
            string serialNo = strtime + randNo + checkNostr;//保证20位 14+5+1
            return zeroPlus(20, serialNo);
        }

        private static string zeroPlus(int myLength, string myStr)
        {
            int strLength = myStr.Length;
            int zeroLength = myLength - strLength;
            string zeroStr = ""; //补0后返回的字符串
            for (int i = 0; i < zeroLength; i++)
            {
                zeroStr += '0';
            }
            myStr += zeroStr;
            return myStr;
        }
        #endregion
    }
}
