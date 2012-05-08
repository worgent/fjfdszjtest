

using System.Collections.Generic;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using QzgfFrame.Utility.Common;
using QzgfFrame.Utility.Core.DomainModel;
using QzgfFrame.Utility.View;
using Spring.Context;
using Spring.Context.Support;
using Spring.Data.Common;
using Spring.Data.Core;

namespace QzgfFrame.Utility.DataBase
{
    public abstract class DataBasePager
    {
        //private readonly DataBaseHelper _dbHelp=new DataBaseHelper();
        public virtual IList<ViewInfo> Views { get; set; }

        public string GetKeyName(string ViewName)
        {
            if (Views == null) return null;
            foreach (var view in Views)
            {
                if (view.ViewName.Equals(ViewName))
                    return view.KeyName;
            }
            return null;
        }
        public AdoTemplate DB
        {
            get
            {
                IApplicationContext ctx = ContextRegistry.GetContext();
                DataBaseHelper dbHelp = (DataBaseHelper)ctx.GetObject("databasehelper");
                return dbHelp.GetCurrentDB();
            }
        }
        /*
        方案一:采用HibernateDaoSupport的HibernateTemplate,会返回T,类型难以确定,改用方案二
        /// <summary>
        /// 获取全部数据
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <returns></returns>
        public string GetGridView(string gridViewName)
        {
            var mm = Type.GetType(gridViewName);
            //mm.Name EntityBase
            //DataTable table = (DataTable)Activator.CreateInstance(t);
            object dObj = Activator.CreateInstance(mm,null);
            //mm.
            IList<mm> list = this.HibernateTemplate.ExecuteFind(delegate(ISession session)
            {
                var query = session.CreateQuery(string.Format(@"select * from " + gridViewName));

                return query.List<mm>();
            });
            //list[0].

            string json = JsonConvert.SerializeObject(list);
            return json;
        }
         */

       
        /// 获取全部数据
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <returns></returns>
        public DataTable GetDataTable(string sql)
        {
            IApplicationContext ctx = ContextRegistry.GetContext();
            DataBaseHelper dbHelp = (DataBaseHelper)ctx.GetObject("databasehelper");
            DataTable dt = dbHelp.GetDataTable(sql);
            return dt;
        }
        /// 获取全部数据
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <returns></returns>
        public DataTable GetGridView(string gridViewName)
        {
            IApplicationContext ctx = ContextRegistry.GetContext();
            DataBaseHelper dbHelp = (DataBaseHelper)ctx.GetObject("databasehelper");
            string sql = "select * from " + gridViewName;
            DataTable dt = dbHelp.GetDataTable(sql);
            return dt;
        }
        /// <summary>
        /// 简单查询
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <returns></returns>
        public DataTable GetGridView(string gridViewName,string whereStr)
        {
            IApplicationContext ctx = ContextRegistry.GetContext();
            DataBaseHelper dbHelp = (DataBaseHelper)ctx.GetObject("databasehelper");
            string sql = "select * from " + gridViewName + " where " + whereStr;
            DataTable dt = dbHelp.GetDataTable(sql);
            return dt;
        }
        /// <summary>
        /// 简单查询(包含排序)
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <param name="whereStr"></param>
        /// <param name="orderStr"></param>
        /// <returns></returns>
        public DataTable GetGridView(string gridViewName, string whereStr,string orderStr)
        {
            IApplicationContext ctx = ContextRegistry.GetContext();
            DataBaseHelper dbHelp = (DataBaseHelper)ctx.GetObject("databasehelper");
            string sql = "select * from " + gridViewName + " where " + whereStr + " " + orderStr;
            DataTable dt = dbHelp.GetDataTable(sql);
            return dt;
        }
        /// <summary>
        /// 简单的分页
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="recordTotal"></param>
        /// <returns></returns>
        public DataTable GetGridView(string gridViewName, int pageNo, int pageSize, ref int recordTotal)
        {
            string keyname = GetKeyName(gridViewName);
            if (string.IsNullOrEmpty(keyname)) return null;
            return GetGridView(gridViewName, "*", keyname, pageNo, pageSize, "order by " + keyname, "1=1", ref recordTotal);
        }
        /// <summary>
        /// 带排序的分页
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="orderStr"></param>
        /// <param name="recordTotal"></param>
        /// <returns></returns>
        public DataTable GetGridView(string gridViewName, int pageNo, int pageSize, string orderStr, ref int recordTotal)
        {
            string keyname = GetKeyName(gridViewName);
            if (string.IsNullOrEmpty(keyname)) return null;
            return GetGridView(gridViewName, "*", keyname, pageNo, pageSize, orderStr, "1=1", ref recordTotal);
        }
        /// <summary>
        /// 带排序和查询的分页
        /// </summary>
        /// <param name="gridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="orderStr"></param>
        /// <param name="whereStr"></param>
        /// <param name="recordTotal"></param>
        /// <returns></returns>
        public DataTable GetGridView(string gridViewName, int pageNo, int pageSize, string orderStr, string whereStr, ref int recordTotal)
        {
            string keyname = GetKeyName(gridViewName);
            if (string.IsNullOrEmpty(keyname)) return null;
            return GetGridView(gridViewName, "*", keyname, pageNo, pageSize, orderStr, whereStr, ref recordTotal);
        }
         


        public DataTable GetGridView(string gridViewName, string fieldName, string gridKeyName, int pageNo, int pageSize, ref int recordTotal)
        {
            return GetGridView(gridViewName, fieldName, gridKeyName, pageNo, pageSize, "order by " + gridKeyName, ref recordTotal);
        }

        public DataTable GetGridView(string gridViewName, string fieldName, string gridKeyName, int pageNo, int pageSize, string orderStr, ref int recordTotal)
        {
            return GetGridView(gridViewName, fieldName, gridKeyName, pageNo, pageSize, orderStr, ref recordTotal);
        }


        public DataTable GetGridView(string gridViewName, string fieldName, string gridKeyName, int pageNo, int pageSize, string orderStr, string whereStr, ref int recordTotal)
        {
            IApplicationContext ctx = ContextRegistry.GetContext();
            DataBaseHelper dbHelp = (DataBaseHelper)ctx.GetObject("databasehelper");
            //方案一:存储过程
            //string sqlCommad = "P_GridViewPager";
            //List<DataFilter> filters = new List<DataFilter>();
            //var df = new DataFilter();
            //df.field = "viewName";
            //df.value = gridViewName;
            //df.type = "string";
            //filters.Add(df);
            //df = new DataFilter();
            //df.field = "fieldName";
            //df.value = fieldName;
            //df.type = "string";
            //filters.Add(df);
            //df = new DataFilter();
            //df.field = "keyName";
            //df.value = gridKeyName;
            //df.type = "string";
            //filters.Add(df);
            //df = new DataFilter();
            //df.field = "pageNo";
            //df.value = pageNo.ToString();
            //df.type = "string";
            //filters.Add(df);
            //df = new DataFilter();
            //df.field = "pageSize";
            //df.value = pageSize.ToString();
            //df.type = "string";
            //filters.Add(df);
            //df = new DataFilter();
            //df.field = "orderString";
            //df.value = orderStr;
            //df.type = "string";
            //filters.Add(df);
            //df = new DataFilter();
            //df.field = "whereString";
            //df.value = whereStr;
            //df.type = "string";
            //filters.Add(df);
            //df.field = "recordTotal";
            //df.value = pageSize;
            //df.type = "string";
            //filters.Add(df);

            //DataTable dt = dbHelp.GetGridViewByStore(sqlCommad, filters);
            //方案二:写sql
            string sql = "";
            //sql = "select " + fieldName + " from " + gridViewName + " WHERE " + whereStr + " " + orderStr + " limit "+(pageNo - 1) * pageSize+","+pageSize;
            //select * from (select rownum rn,NAME  from system_menu where NAME='主页' and rownum <= 4) where rn >=0 order by ID desc
            if (fieldName == "*")
                fieldName = "a.*";
            sql = "select * from (select " + fieldName + ",rownum rn from " + gridViewName + " a WHERE " + whereStr + " and rownum <= " + pageNo * pageSize + ")  where rn>" + (pageNo - 1) * pageSize + " " + orderStr ;
            DataTable dt = dbHelp.GetDataTable(sql);
            sql = "select count(*) from " + gridViewName + " where " + whereStr;
            recordTotal = dbHelp.ExecuteScalar(sql).ToInt();
            return dt;
        }
    }


   
}
