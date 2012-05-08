using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;
using QzgfFrame.Cop.CycTimeManager.Models;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.System.RelationManger.Models;

namespace QzgfFrame.Cop.CycTimeManager.Domain
{
    /// <summary>
    /// 专线巡检周期
    /// </summary>
    public class CycTimeFacadeImpl : CycTimeFacade
    {
        #region

        /// <summary>
        /// 专线巡检周期
        /// </summary>
        private IRepository<CopCycTime> cycTimeRepository { set; get; }
        /// <summary>
        /// 专线巡检计划
        /// </summary>
        private IRepository<CopPlan> planRepository { set; get; }
        /// <summary>
        /// 表关系
        /// </summary>
        private IRepository<SystemRelation> relationRepository { set; get; }

        /// <summary>
        /// 关系表"专线巡检周期"
        /// </summary>
        string cycTimeControl = "CycTime";

        #endregion

        public CopCycTime Get(object id)
        {
            return cycTimeRepository.Get(id.ToString());
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
                sql = " CId = '" + s + "' and  RelationName = '" + cycTimeControl + "'";
                IList<SystemRelation> systemRelations = new List<SystemRelation>();
                systemRelations = relationRepository.LoadAll("id", sql);
                if (systemRelations.Count() == 0)
                {
                    result = cycTimeRepository.Delete(s);
                }
            }

            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(CopCycTime entity)
        {
            entity.Id = cycTimeRepository.NewSequence("SYSTEM_MENU");
            return cycTimeRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(CopCycTime entity)
        {
            return cycTimeRepository.Update(entity);
        }

        public IList<CopCycTime> LoadAll()
        {
            return cycTimeRepository.LoadAll();
        }

        public IList<CopCycTime> LoadAll(string order, string where)
        {
            return cycTimeRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = " from CopCycTime main where 1=1 ";
            hql += gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            var listCycTimes = cycTimeRepository.FindByPage(pageNo, pageSize, vSql);

            var jsonlist = (
                    from listCycTime in listCycTimes 
                    select new 
                    {
                        Id = listCycTime.Id,
                        CycTime = listCycTime.CycTime
                    }
                ).OrderBy(m => m.Id).ToArray();
            int recordCount = cycTimeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
