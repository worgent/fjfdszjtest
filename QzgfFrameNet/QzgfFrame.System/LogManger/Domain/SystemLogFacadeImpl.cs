/*

 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能

*/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.System.LogManger.Models;


namespace QzgfFrame.System.LogManger.Domain
{
    /// <summary>
    /// auto gen
    /// </summary>
    public class SystemLogFacadeImpl : SystemLogFacade
    {
        #region 变量定义,申明

        private IRepository<SystemLog> serverRepository { set; get; }
        private IRepository<SystemMenu> menuRepository { set; get; }
        private IRepository<SystemUser> userRepository { set; get; }

        private string hql =
            @" from SystemLog main where main.Userid!='1'  ";

        #endregion

        #region 基本操作

        public SystemLog Get(object id)
        {
            return serverRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }

        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                SystemLog entity = serverRepository.Get(s);
                result = serverRepository.Update(entity); //假删
            }
            return result;
        }

        public bool Save(SystemLog entity)
        {
            entity.Id = serverRepository.NewSequence("SystemLog");
            return serverRepository.Save(entity);
        }

        public bool Update(SystemLog entity)
        {
            return serverRepository.Update(entity);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string vSql = hql + gridsearch;
            vSql = vSql + @" order by main." + sortname + " " + sortorder;
            IList<SystemLog> lslog = serverRepository.FindByPage(pageNo, pageSize, vSql);
            IList<SystemMenu> lscontrollers = menuRepository.LoadAll("Id", "Ismenu='1'");//菜单
            IList<SystemMenu> lsoptname = menuRepository.LoadAll("Id", "Ismenu='2'");//按钮，即操作
            IList<SystemUser> lsusername = userRepository.LoadAll("Id", "State!='0'");//用户信息
            
            var ls = (from vlslog in lslog
                      join vlsoptname in lsoptname
                              on vlslog.Opercode equals vlsoptname.Url into joinedlog2
                      from vljoinedlog2 in joinedlog2.DefaultIfEmpty()

                      join vlsusername in lsusername
                              on vlslog.Userid equals vlsusername.Id into joinedlog3
                      from vljoinedlog3 in joinedlog3.DefaultIfEmpty()

                      join vlscontrollers in lscontrollers
                             on vlslog.Controllerscode equals vlscontrollers.Url into joinedlog1
                              //on  vlscontrollers.Url.Contains(vlslog.Controllerscode) into joinedlog1
                      from vljoinedlog1 in joinedlog1.DefaultIfEmpty()

                      //出现信息丢失
                      //from vlscontrollers in lscontrollers
                      //where vlscontrollers.Url != null && vlslog.Controllerscode != null && vlscontrollers.Url.Contains(vlslog.Controllerscode)
                            select new
                            {
                                vlslog.Id,
                                vlslog.Opercode,
                                vlslog.Controllerscode,
                                //Opername = vljoinedlog2 != null ? vljoinedlog2.Name : "",
                                Opername = (vljoinedlog2 != null && vljoinedlog1 != null) ? vljoinedlog1.Name + "(" + vljoinedlog2.Name + ")" : "",
                                //Controllersname = vljoinedlog1 != null ? vljoinedlog1.Name : "",
                                //Controllersname = vlscontrollers != null ? vlscontrollers.Name : "",
                                vlslog.Userid,
                                Username = vljoinedlog3 != null ? vljoinedlog3.Username : "",
                                vlslog.Operdate,
                                vlslog.Operresult,
                                vlslog.Operip
                            }
                                      ).ToArray();
            //这里使用自定义日期格式，如果不使用的话，默认是ISO8601格式   
            IsoDateTimeConverter timeConverter = new IsoDateTimeConverter();
            timeConverter.DateTimeFormat = "yyyy'-'MM'-'dd' 'HH':'mm':'ss";
            string rowsjson = JsonConvert.SerializeObject(ls, Formatting.Indented, timeConverter);
            int recordCount = serverRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        #endregion

        #region 保留

        #endregion

    }
}
