/****************************************************************** 
 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
using QzgfFrame.System.DepartmentManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.DepartmentManger.Domain
{
    public class DepartmentFacadeImpl : DepartmentFacade
    {
        #region 变量定义

        private IRepository<SystemDepartment> departmentRepository { set; get; }

        private string hql =
            @"select new SystemDepartment(main.Id,main.Departname,main.Charger,
                            main.Tel,main.Address,main.Father,sd.Departname as Fathername,main.Remark,
                    case main.State when '1' then '启用' when '2' then '停用' else '删除' END as Statename,main.Type,
                    case main.Type when '4' then '区县' when '5' then '公司' when '6' then '驻点' when '7' then '网格' else '异常' END as Typename,
                    main.Orderno,main.Isrepair,main.Levelno) 
                    from SystemDepartment main,SystemDepartment sd  where main.Father=sd.Id and main.State!=0 ";

        #endregion

        #region 基本操作

        /// <summary>
        /// 查询单行记录
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SystemDepartment Get(object id)
        {
            return departmentRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
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
                SystemDepartment entity = departmentRepository.Get(s);
                entity.State = "0";
                result = departmentRepository.Update(entity); //假删
            }
            return result;
        }


        public bool Save(SystemDepartment entity)
        {
            entity.Id = departmentRepository.NewSequence("SYSTEM_DEPARTMENT");
            entity.Createdate = DateTime.Now;
            entity.State = "1";
            entity.Createman = "1";
            return departmentRepository.Save(entity);
        }

        public bool Update(SystemDepartment entity)
        {
            entity.State = "1";
            entity.Createman = "1";
            return departmentRepository.Update(entity);
        }


        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder)
        {
            string vSql = hql + @"order by main." + sortname + " " + sortorder;
            IList<SystemDepartment> ls = departmentRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = departmentRepository.FindByPageCount(vSql);
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
                SystemDepartment entity = departmentRepository.Get(s);
                entity.State = state;
                result = departmentRepository.Update(entity); //假删
            }
            return result;
        }




        //得到父结点信息
        public string GetFather()
        {
            string vSql =
                @"select new SystemDepartment(main.Id,main.Father,main.Departname as text) from SystemDepartment main where main.State='1' order by main.Id";
            IList<SystemDepartment> relist = departmentRepository.LoadAllbyHql(vSql);

            var list = (from vlist in relist
                        select new
                                   {
                                       text=vlist.Departname,
                                       id=vlist.Id.Trim(),
                                       url = "",
                                       icon = "",
                                       pid=vlist.Father.Trim()
                                   }
                       ).ToArray();


            return JsonConvert.SerializeObject(list);
        }

        //根据类型得到某类信息
        public string GetFather(string selid, string type)
        {
            string vSql = " from SystemDepartment main where main.State='1' and  main.Type='" + type + "' order by main.Orderno";

            IList<SystemDepartment> relist = departmentRepository.LoadAllbyHql(vSql);

            var list = (from vlist in relist
                        select new
                        {
                            text = vlist.Departname,
                            id = vlist.Id.Trim(),
                            url = "",
                            icon = "",
                            pid = vlist.Father.Trim(),
                            ischecked = vlist.Id.Trim() != selid ? false : true,
                            hno = vlist.Levelno
                        }
                       ).ToArray();


            return JsonConvert.SerializeObject(list);
        }
        #endregion

        #region 保留

        public IList<SystemDepartment> LoadAll()
        {
            return departmentRepository.LoadAll();
        }

        public IList<SystemDepartment> LoadAll(string order, string where)
        {
            return departmentRepository.LoadAll(order, where);
        }

        #endregion

    }
}
