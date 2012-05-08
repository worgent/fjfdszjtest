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
using Newtonsoft.Json;
using QzgfFrame.System.SmsManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.SmsManger.Domain
{
    /// <summary>
    /// auto gen
    /// </summary>
    public class SmsFacadeImpl : SmsFacade
    {
        #region 变量定义,申明

        private IRepository<TabQywyySmsSend> serverRepository { set; get; }

        private string hql =
            @"select new TabQywyySmsSend(main.Id,main.Remark,main.State) from TabQywyySmsSend 
            main where main.State!='0'  ";

        #endregion

        #region 基本操作

        public TabQywyySmsSend Get(object id)
        {
            return serverRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }

        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                //TabQywyySmsSend entity = serverRepository.Get(s);
                //entity.State = "0"; //停用标识
                result = serverRepository.Delete(id); //假删
            }
            return result;
        }

        public bool Save(TabQywyySmsSend entity)
        {
            entity.Id = serverRepository.NewSequence("TabQywyySmsSend");
            //系统要求两个固定值(需从配置文件中读取)
            entity.SendQydm = "8000";//企业代码，本系统固定值为”8000”
            entity.Src="10658473030";//短信端口号，本系统固定值为”10658473030”

            //entity.Createdate = DateTime.Now;
            //entity.State = "1";
            //entity.Createman = "1";
            return serverRepository.Save(entity);
        }

        public bool Update(TabQywyySmsSend entity)
        {
            //entity.State = "1";
            //entity.Createman = "1";
            return serverRepository.Update(entity);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder)
        {
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            IList<TabQywyySmsSend> ls = serverRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = serverRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强

        public bool UseState(string id, string state)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                TabQywyySmsSend entity = serverRepository.Get(s);
                //entity.State = state;
                result = serverRepository.Update(entity); //假删
            }
            return result;
        }

        #endregion

        #region 保留

        #endregion
    }
}
