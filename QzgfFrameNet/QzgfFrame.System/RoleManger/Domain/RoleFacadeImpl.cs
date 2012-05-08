/*

 * 文件名.........: RoleFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能

*/

using System;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using QzgfFrame.System.RoleManger.Models;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.RoleManger.Domain
{
    public class RoleFacadeImpl : RoleFacade
    {
        #region 变量定义,申明

        private IRepository<SystemRole> roleRepository { set; get; }

        private string hql =
            @"select new SystemRole(main.Id,main.Rolename,main.Remark,main.State,
                    case main.State when '1' then '启用' when '2' then '停用' else '删除' END as Statename) 
                    from SystemRole main where main.State!='0' ";

        #endregion

        #region 基本操作

        /// <summary>
        /// 取得单行记录
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SystemRole Get(object id)
        {
            return roleRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }

        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                SystemRole entity = roleRepository.Get(s);
                entity.State = "0"; //停用标识
                result = roleRepository.Update(entity); //假删
            }
            return result;
        }

        public bool Save(SystemRole entity)
        {
            entity.Id = roleRepository.NewSequence("SYSTEM_Role");
            //默认当前时间
            entity.Createdate = DateTime.Now;
            entity.State = "1";
            entity.Createman = "1";
            return roleRepository.Save(entity);
        }

        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(SystemRole entity)
        {
            entity.State = "1";
            entity.Createman = "1";
            return roleRepository.Update(entity);
        }

        /// <summary>
        /// 列表页面
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <returns></returns>
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string vSql = hql + gridsearch;
            vSql +=  @" order by main." + sortname + " " + sortorder;
            IList<SystemRole> ls = roleRepository.FindByPage(pageNo, pageSize, vSql);
            IsoDateTimeConverter timeConverter = new IsoDateTimeConverter();
            //这里使用自定义日期格式，如果不使用的话，默认是ISO8601格式     
            timeConverter.DateTimeFormat = "yyyy'-'MM'-'dd' 'HH':'mm':'ss";
            string rowsjson = JsonConvert.SerializeObject(ls, Formatting.Indented, timeConverter);
            int recordCount = roleRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        /// <summary>
        /// 标识启用与停用
        /// </summary>
        /// <param name="id">主键</param>
        /// <param name="state">1:启用,2停用</param>
        /// <returns></returns>
        public bool UseState(string id, string state)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                SystemRole entity = roleRepository.Get(s);
                entity.State = state; //停用标识
                result = roleRepository.Update(entity); //假删
            }
            return result;
        }


        /// <summary>
        /// 角色信息下拉框,用户在选择角色时使用
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox()
        {
            var ls = LoadAll("Id", "state='1'"); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.Rolename,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }

        #endregion

        #region 保留

        /// <summary>
        /// 加载所有信息
        /// </summary>
        /// <returns></returns>
        public IList<SystemRole> LoadAll()
        {
            return roleRepository.LoadAll();
        }

        /// <summary>
        /// 加载带条件列表
        /// </summary>
        /// <param name="order"></param>
        /// <param name="where"></param>
        /// <returns></returns>
        public IList<SystemRole> LoadAll(string order, string where)
        {
            return roleRepository.LoadAll(order, where);
        }

        #endregion
    }
}
