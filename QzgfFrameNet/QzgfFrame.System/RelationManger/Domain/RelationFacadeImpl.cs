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
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.RelationManger.Domain
{
    /// <summary>
    /// auto gen
    /// </summary>
    public class RelationFacadeImpl : RelationFacade
    {
        #region 变量定义,申明

        private IRepository<SystemRelation> relationRepository { set; get; }

        private string hql =
            @"select new SystemRelation(main.Id,main.RelationName,main.ControllerName,main.MId,main.CId) from SystemRelation 
            main where 1=1  ";

        #endregion

        #region 基本操作

        public SystemRelation Get(object id)
        {
            return relationRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }

        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                //TabQywyyRelationSend entity = serverRepository.Get(s);
                //entity.State = "0"; //停用标识
                result = relationRepository.Delete(id); //假删
            }
            return result;
        }

        public bool Save(SystemRelation entity,string no)
        {
           
            return relationRepository.Save(entity);
        }

        public bool Update(SystemRelation entity)
        {
            //entity.State = "1";
            //entity.Createman = "1";
            return relationRepository.Update(entity);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder)
        {
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            IList<SystemRelation> ls = relationRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = relationRepository.FindByPageCount(vSql);
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
