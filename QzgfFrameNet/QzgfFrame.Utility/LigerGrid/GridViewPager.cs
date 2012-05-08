using System;
using System.Collections.Generic;
using System.Data;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;
using QzgfFrame.Utility.DataBase;
using QzgfFrame.Utility.JSON;
using QzgfFrame.Utility.View;
using System.Xml;

namespace QzgfFrame.Utility.LigerGrid
{
    public class GridViewPager : DataBasePager
    {
        static IList<ViewInfo> _views = new List<ViewInfo>();
        static GridViewPager()
        {
            try
            {
                var context = System.Web.HttpContext.Current;
                if (context == null) return;
                var xml = new XmlDocument();
                xml.Load(context.Server.MapPath("~/Config/views.xml"));
                var views = xml.SelectNodes("/views/view");
                foreach (XmlNode view in views)
                {
                    _views.Add(new ViewInfo(view.Attributes["name"].Value, view.Attributes["key"].Value));
                }
            }
            catch(Exception err)
            {

            } 
        }
        public override IList<ViewInfo> Views
        {
            get
            {
                return _views;
            } 
        }
         
        /// <summary>
        /// 统一处理方法
        /// </summary>
        /// <returns></returns>
        public string GetDataJSON()
        {
            System.Web.HttpContext ctx = System.Web.HttpContext.Current;

            int pageno = ctx.Request.Params["page"].ToInt();
            int pagesize = ctx.Request.Params["pagesize"].ToInt();
            string sortname = ctx.Request.Params["sortname"].ToStr();
            string sortorder = ctx.Request.Params["sortorder"].ToStr();
            string gridviewname = ctx.Request.Params["gridviewname"].ToStr();
            string gridsearch = ctx.Request.Params["gridsearch"].ToStr(); 
            if (string.IsNullOrEmpty(gridviewname))
                throw new ArgumentNullException("Grid视图名[gridsearch]不能为空"); 
            if (pageno == 0 || pagesize == 0)
            {
                if (string.IsNullOrEmpty(sortorder) && string.IsNullOrEmpty(gridsearch))
                {
                    return GetDataJSON(gridviewname);
                }
                if(string.IsNullOrEmpty(sortorder))
                    return GetDataJSON(gridviewname, gridsearch);
                if (string.IsNullOrEmpty(gridsearch))
                    return GetDataJSONUseSQL(string.Format("select * from {0} order by {1} {2}", gridviewname, sortname, sortorder.ToLower() == "asc" ? "asc" : "desc"));
                return GetDataJSON(gridviewname, gridsearch, string.Format("order by {0} {1}", sortname, sortorder));
            }
            if (string.IsNullOrEmpty(gridsearch) && string.IsNullOrEmpty(sortname))
            {
                return GetDataJSON(gridviewname, pageno, pagesize);
            }
            if (string.IsNullOrEmpty(gridsearch) && !string.IsNullOrEmpty(sortname))
            {
                return GetDataJSON(gridviewname, pageno, pagesize, sortname, sortorder);
            }
            if (!string.IsNullOrEmpty(gridsearch) && string.IsNullOrEmpty(sortname))
            {
                return GetDataJSON(gridviewname, pageno, pagesize, gridsearch);
            }
            if (!string.IsNullOrEmpty(gridsearch) && !string.IsNullOrEmpty(sortname))
            {
                return GetDataJSON(gridviewname, pageno, pagesize, sortname, sortorder, gridsearch);
            }
            return @"{""Rows"":[],""Total"":""0""}";
        }

        public string GetDataJSONUseSQL(string sql)
        {
            DataTable dt = GetDataTable(sql);
            int recordCount = dt.Rows.Count;
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 不分页
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName)
        { 
            DataTable dt = GetDataTable(GridViewName);
            int recordCount = dt.Rows.Count;      
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 不分页，带查询
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <param name="whereStr"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName, string whereStr)
        {
            DataTable dt = GetGridView(GridViewName, whereStr);
            int recordCount = dt.Rows.Count;
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 不分页，带查询和排序
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <param name="whereStr"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName, string whereStr,string orderStr)
        {
            DataTable dt = GetGridView(GridViewName, whereStr, orderStr);
            int recordCount = dt.Rows.Count;
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 简单分页
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName, int pageNo, int pageSize)
        {
            int recordCount = 0;
            DataTable dt = GetGridView(GridViewName, pageNo, pageSize, ref recordCount);
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 带查询的分页
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="whereStr"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName, int pageNo, int pageSize, string whereStr)
        {
            int recordCount = 0;
            string keyname = GetKeyName(GridViewName);
            string orderStr = "order by " + keyname;
            DataTable dt = GetGridView(GridViewName, pageNo, pageSize, orderStr, whereStr, ref recordCount);
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 带排序的分页
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName, int pageNo, int pageSize, string sortname, string sortorder)
        {
            int recordCount = 0;
            sortorder = sortorder.ToLower() == "asc" ? "Asc" : "Desc";
            string orderStr = "order by " + sortname + " " + sortorder;
            DataTable dt = GetGridView(GridViewName, pageNo, pageSize, orderStr, ref recordCount);
            return GetJSONFromDataTable(dt, recordCount);
        }
        /// <summary>
        /// 带排序和查询的分页
        /// </summary>
        /// <param name="GridViewName"></param>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="whereStr"></param>
        /// <returns></returns>
        public string GetDataJSON(string GridViewName, int pageNo, int pageSize, string sortname, string sortorder,string whereStr)
        {
            int recordCount = 0;
            sortorder = sortorder.ToLower() == "asc" ? "Asc" : "Desc";
            string orderStr = "order by " + sortname + " " + sortorder;
            DataTable dt = GetGridView(GridViewName, pageNo, pageSize, orderStr, whereStr, ref recordCount);
            return GetJSONFromDataTable(dt, recordCount);
        }

        public string GetJSONFromDataTable(DataTable dt,int recordCount)
        {
            try
            { 
                string rowsjson = JsonConvert.SerializeObject(dt, new DataTableConverter());
                string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
                return json;
            }
            catch (Exception err)
            {
                return @"{""Rows"":[],""Total"":""0""}";
            }
        }
    }
}
