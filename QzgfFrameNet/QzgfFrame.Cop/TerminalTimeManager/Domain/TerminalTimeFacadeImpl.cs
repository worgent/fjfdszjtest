using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalTimeManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;
using QzgfFrame.Cop.TerminalPlanManager.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.JSON;

namespace QzgfFrame.Cop.TerminalTimeManager.Domain
{
    /// <summary>
    /// 自助终端巡检周期
    /// </summary>
    public class TerminalTimeFacadeImpl : TerminalTimeFacade
    {
        #region

        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        private IRepository<CopTerminalTime> terminalTimeRepository { set; get; }
        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        private IRepository<CopTerminalPlan> terminalPlanRepository { set; get; }
        /// <summary>
        /// 表关系
        /// </summary>
        private IRepository<SystemRelation> relationRepository { set; get; }

        /// <summary>
        /// 关系表"专线巡检周期"
        /// </summary>
        string terminalTimeControl = "TerminalTime";

        #endregion

        public CopTerminalTime Get(object id)
        {
            return terminalTimeRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            //"巡检时间" 有在"巡检周期"中使用的就不能删除
            string sql = "";
            string[] idarr = id.Split(',');
            bool result = false;

            foreach (var s in idarr)
            {
                //判断在 关系表中是否有在存在，有的话，就不能删除
                sql = " CId = '" + s + "' and  RelationName = '" + terminalTimeControl + "'";
                IList<SystemRelation> systemRelations = new List<SystemRelation>();
                systemRelations = relationRepository.LoadAll("id", sql);
                if (systemRelations.Count() == 0)
                {
                    result = terminalTimeRepository.Delete(s);
                }
            }

            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(CopTerminalTime entity)
        {
            entity.Id = terminalTimeRepository.NewSequence("SYSTEM_MENU");
            return terminalTimeRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(CopTerminalTime entity)
        {
            return terminalTimeRepository.Update(entity);
        }

        public IList<CopTerminalTime> LoadAll()
        {
            return terminalTimeRepository.LoadAll();
        }

        public IList<CopTerminalTime> LoadAll(string order, string where)
        {
            return terminalTimeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = " from CopTerminalTime main where 1=1 ";
            hql += gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            var listCycTimes = terminalTimeRepository.FindByPage(pageNo, pageSize, vSql);

            var jsonlist = (
                    from listCycTime in listCycTimes
                    select new
                    {
                        Id = listCycTime.Id,
                        TerminalTime = listCycTime.TerminalTime
                    }
                ).OrderBy(m => m.Id).ToArray();
            int recordCount = terminalTimeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
