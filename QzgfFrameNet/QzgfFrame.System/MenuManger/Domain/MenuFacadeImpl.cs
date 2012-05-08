/****************************************************************** 
 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.MenuManger.Domain
{
    public class MenuFacadeImpl : MenuFacade
    {
        #region 变量定义

        private IRepository<SystemMenu> menuRepository { set; get; }

        private string hql =
            @"select new SystemMenu(sm.Id,sm.Name,sm.Pic,sm.Url,sm.Orderno,sm.Father,sm.Ismenu,
                      smex.Name as FatherName,case sm.Ismenu when '1' then '是' else '否' END as IsmenuName,sm.Permissionsflag)  
                                    from SystemMenu sm,SystemMenu smex  where sm.Father=smex.Id and sm.Ismenu!='2' ";

        //sm.Ismenu='1'有三种状态，2为内部维护,0,1开放给用户

        /*
        private string hql =
            @"select new SystemMenu(sm.Id,sm.Name,sm.Pic,sm.Url,sm.Orderno,sm.Father,sm.Ismenu,
                            smex.Name as FatherName,case sm.Ismenu when '1' then '是' else '否' END as IsmenuName)  
                            from SystemMenu sm left  join SystemMenu smex on sm.Father=smex.Id ";
         */

        /*
        private string hql =
    @"select new SystemMenu(sm.Id,sm.Name,sm.Pic,sm.Url,sm.Orderno,sm.Father,sm.Ismenu,
                            smex.Name as FatherName,case sm.Ismenu when '1' then '是' else '否' END as IsmenuName)  
                            from SystemMenu sm,SystemMenu smex ";
        */

        #endregion

        #region 基本操作

        /// <summary>
        /// 查询单行记录
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SystemMenu Get(object id)
        {
            return menuRepository.GetbyHql(hql + " and sm.Id='" + id.ToString() + "'");
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
                result = menuRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SystemMenu entity)
        {
            entity.Id = menuRepository.NewSequence("SYSTEM_MENU");
            return menuRepository.Save(entity);
        }

        public bool Update(SystemMenu entity)
        {
            return menuRepository.Update(entity);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string vSql = hql + gridsearch;
            vSql +=  @" order by sm." + sortname + " " + sortorder;//排序
            IList<SystemMenu> ls = menuRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = menuRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        /// <summary>
        /// 按钮(斩时)
        /// </summary>
        /// <param name="menuid"></param>
        /// <returns></returns>
        public string GetButton(string menuid)
        {
            var list = LoadAll("Id", "Ismenu='0'");

            var jsonlist = (list.Select(a => new
            {
                id = a.Url,
                icon = a.Pic,
                name = a.Name
            })
                           ).ToArray();



            return JsonConvert.SerializeObject(jsonlist);
        }

        /// <summary>
        /// 框架左边菜单
        /// </summary>
        /// <returns></returns>
        public string GetMenu()
        {
            var list = LoadAll("Orderno", "Ismenu='1'");
            /*首页版本1
            var jsonlist = (from a in list
                            select new
                            {
                                name = a.Name,
                                menuno = a.Id,
                                url = a.Url,
                                icon = a.Pic
                            }
                           ).ToArray();
            */

            var jsonlist = (list.Select(a => new
            {
                text = a.Name,
                id = a.Id.Trim(),
                url = a.Url,
                icon = a.Pic,
                pid = a.Father.Trim()
            })).ToArray();

            return JsonConvert.SerializeObject(jsonlist);

        }



        #endregion

        #region 保留

        public IList<SystemMenu> LoadAll()
        {
            return menuRepository.LoadAll();
        }

        public IList<SystemMenu> LoadAll(string order, string where)
        {
            return menuRepository.LoadAll(order, where);
        }

        #endregion

    }
}
