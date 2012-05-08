

using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using QzgfFrame.Utility.Common;
using QzgfFrame.Utility.Core.DomainModel;
using Spring.Data.Common;
using Spring.Data.Core;

namespace QzgfFrame.Utility.DataBase
{
    public class DataBaseHelper : AdoDaoSupport
    {
        public  AdoTemplate GetCurrentDB()
        {
            return this.AdoTemplate;
        }

        public  DataSet GetDataSet(string sql)
        {
            return this.AdoTemplate.DataSetCreate(CommandType.Text, sql);
        }

        public  DataTable GetDataTable(string sql)
        {
            DataSet ds = GetDataSet(sql);
            if (ds.Tables.Count == 0) return null;
            return ds.Tables[0];
        }
        /// <summary>
        /// sql影响行数,用于update,insert,delete等方法
        /// </summary>
        /// <param name="sql"></param>
        /// <returns></returns>
        public  int ExecuteNonQuery(string sql)
        {
            return AdoTemplate.ExecuteNonQuery(CommandType.Text, sql);
        }
        /// <summary>
        /// 返回select count(*)值 
        /// </summary>
        /// <param name="sql"></param>
        /// <returns></returns>
        public object ExecuteScalar(string sql)
        {
            return AdoTemplate.ExecuteScalar(CommandType.Text, sql);
        }


        /// <summary>
        /// 是否有数据
        /// </summary>
        /// <param name="sql">传入sql</param>
        /// <returns>存在为真</returns>
        public  bool Has(string sql)
        {
            var obj = AdoTemplate.ExecuteScalar(CommandType.Text, sql);
            if (obj == null || Convert.IsDBNull(obj)) return false;
            if (obj.ToInt() == 0) return false;
            return true;
        }

        /// <summary>
        /// 组合过滤条件查询,调用存储过程
        /// </summary>
        /// <param name="storename">存储过程名</param>
        /// <param name="filters">过滤列表</param>
        /// <returns>list</returns>
        public DataTable GetGridViewByStore(string storename, List<DataFilter> filters)
        {
            IDbParameters parameters = CreateDbParameters();

            var stringList = from f in filters where f.type == "string" select f;
            foreach (var i in stringList)
            {
                parameters.Add(i.field, DbType.String).Value = i.value;
            }
            
            DataTable dt = AdoTemplate.DataTableCreateWithParams(CommandType.StoredProcedure, storename, parameters);
            return dt;
        }
    }
}
