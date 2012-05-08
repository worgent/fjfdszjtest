/****************************************************************** 
 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System.Collections.Generic;
using Newtonsoft.Json;
using QzgfFrame.System.MenufieldManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.MenufieldManger.Domain
{
    public class MenufieldFacadeImpl : MenufieldFacade
    {
        #region 变量定义

        private IRepository<SystemMenufield> menufieldRepository { set; get; }

        private string hql =
            @"select new SystemMenufield(smf.Id,smf.Menuid,sm.Name as Menuname,smf.Fieldcode,smf.Fieldname,smf.Tablecode,smf.Tablename,smf.Remark,smf.Permissionsflag) 
                         from SystemMenufield smf,SystemMenu sm  where smf.Menuid=sm.Id ";

        #endregion

        #region 基本操作

        /// <summary>
        /// 查询单行
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SystemMenufield Get(object id)
        {
            return menufieldRepository.GetbyHql(hql + " and smf.Id='" + id.ToString() + "'");
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
                result = menufieldRepository.Delete(s);
            }
            return result;
        }


        public bool Save(SystemMenufield entity)
        {
            entity.Id = menufieldRepository.NewSequence("SYSTEM_MENUFIELD");
            return menufieldRepository.Save(entity);
        }

        public bool Update(SystemMenufield entity)
        {
            return menufieldRepository.Update(entity);
        }

        /// <summary>
        /// 分页列表信息
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <returns></returns>
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder)
        {
            string vSql = hql + @"order by smf." + sortname + " " + sortorder;
            IList<SystemMenufield> ls = menufieldRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = menufieldRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        public string FindByPage(string sortname, string sortorder)
        {
            string vSql = hql + @"order by smf." + sortname + " " + sortorder;
            IList<SystemMenufield> ls = menufieldRepository.LoadAllbyHql(vSql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = ls.Count;
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 保留

        public IList<SystemMenufield> LoadAll()
        {
            return menufieldRepository.LoadAll();
        }

        public IList<SystemMenufield> LoadAll(string order, string where)
        {
            return menufieldRepository.LoadAll(order, where);
        }

        #endregion

    }
}
