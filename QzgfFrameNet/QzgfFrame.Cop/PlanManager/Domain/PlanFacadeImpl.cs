using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Cop.EnrolManager.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.DistrictManger.Models;
using System.Collections;

namespace QzgfFrame.Cop.PlanManager.Domain
{
    /// <summary>
    /// 专线巡检计划
    /// </summary>
    public class PlanFacadeImpl : PlanFacade
    {
        #region

        /// <summary>
        /// 专线巡检计划
        /// </summary>
        private IRepository<CopPlan> planRepository { set; get; }
        /// <summary>
        /// 巡检登记
        /// </summary>
        private IRepository<CopEnrol> enrolRepository { set; get; }

        /// <summary>
        /// 专线
        /// </summary>
        private IRepository<ResourceDedicateLine> lineRepository { set; get; }
        /// <summary>
        /// 集团客户
        /// </summary>
        private IRepository<ResourceGroupClie> clieRepository { set; get; }
        /// <summary>
        /// 业务类型(专线类型)
        /// </summary>
        private IRepository<ArchiveBizType> bizTypeRepository { set; get; }
        /// <summary>
        /// 业务保障等级
        /// </summary>
        private IRepository<ArchiveBizAssuranLeve> assuranRepository { set; get; }
        /// <summary>
        /// 表关系
        /// </summary>
        private IRepository<SystemRelation> relationRepository { set; get; }

        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        /// <summary>
        /// 主表Controller名称
        /// </summary>
        string planControl = "Plan";
        /// <summary>
        /// 关系表"专线巡检周期"
        /// </summary>
        string cycTimeControl = "CycTime";
        /// <summary>
        /// 关系表"专线"
        /// </summary>
        string dedicateLineControl = "DedicateLine";

        /// <summary>
        /// 关系表简称
        /// </summary>
        string RelationName = "SYSTEM_Relation";
        /// <summary>
        /// 菜单表简称
        /// </summary>
        string MemuName = "SYSTEM_MENU";

        #endregion

        public CopPlan Get(object id)
        {
            return planRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// 假删除,并未真正删除数据,只是将数据的状态变下
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string sql = "";
            string[] idarr = id.Split(',');
            bool reasult = false;
            foreach (var s in idarr)
            {
                //删除前要判断该计划是否已经进行登记，如果有，将不允许删除
                sql = " CopPlanId = '" + s + "' and IsDelete = 0 ";
                IList<CopEnrol> copEnrols = new List<CopEnrol>();
                copEnrols = enrolRepository.LoadAll("id", sql);
                if (copEnrols == null || copEnrols.Count == 0)
                {
                    CopPlan copPlan = new CopPlan();
                    copPlan = planRepository.Get(s);
                    copPlan.IsDelete = 1;
                    copPlan.DeleteTime = dateNow;
                    reasult = planRepository.Update(copPlan);

                    //删除成功后,将关系表SystemRelation中的表关系删除
                    if (reasult)
                    {
                        //删除"巡检计划"与"专线"关系
                        reasult = AddRelation(planControl, dedicateLineControl, copPlan.Id, copPlan.DedicateLineId, "2");
                        //if (reasult)
                        //{
                        //    //删除"巡检计划"与"专线巡检周期"关系
                        //    reasult = AddRelation(planControl, cycTimeControl, copPlan.Id, copPlan.CycTimeId, "2");
                        //}
                    }
                }
            }
            return reasult;
        }

        /// <summary>
        /// 删除多行记录
        /// 假删除,并未真正删除数据,只是将数据的状态变下
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFlase(string id,out string msg)
        {
            string sql = "";
            string[] idarr = id.Split(',');
            bool reasult = false;
            foreach (var s in idarr)
            {
                //删除前要判断该计划是否已经进行登记，如果有，将不允许删除
                sql = " CopPlanId = '" + s + "' and IsDelete = 0 ";
                IList<CopEnrol> copEnrols = new List<CopEnrol>();
                copEnrols = enrolRepository.LoadAll("id", sql);
                if (copEnrols == null || copEnrols.Count == 0)
                {
                    CopPlan copPlan = new CopPlan();
                    copPlan = planRepository.Get(s);
                    copPlan.IsDelete = 1;
                    copPlan.DeleteTime = dateNow;
                    reasult = planRepository.Update(copPlan);

                    //删除成功后,将关系表SystemRelation中的表关系删除
                    if (reasult)
                    {
                        //删除"巡检计划"与"专线"关系
                        reasult = AddRelation(planControl, dedicateLineControl, copPlan.Id, copPlan.DedicateLineId, "2");
                        //if (reasult)
                        //{
                        //    //删除"巡检计划"与"专线巡检周期"关系
                        //    reasult = AddRelation(planControl, cycTimeControl, copPlan.Id, copPlan.CycTimeId, "2");
                        //}
                    }
                }
            }
            msg = "";
            return reasult;
        }


        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(CopPlan entity)
        {
            entity.Id = planRepository.NewSequence("SYSTEM_MENU");
            bool reasult = false;
            reasult = planRepository.Save(entity);

            //保存成功后,将调用关系添加到关系表SystemRelation中
            if (reasult)
            {
                //添加"巡检计划"与"专线"表关系
                reasult = AddRelation(planControl, dedicateLineControl, entity.Id, entity.DedicateLineId, "0");
                //if (reasult)
                //{
                //    //添加"巡检计划"与"专线巡检周期"表关系
                //    reasult = AddRelation(planControl, cycTimeControl, entity.Id, entity.CycTimeId, "0");
                //}
            }

            return reasult;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <param name="no"></param>
        /// <returns></returns>
        public bool Save(CopPlan entity,string no)
        {
            entity.Id = planRepository.NewSequence(MemuName, no);
            bool reasult = false;
            reasult = planRepository.Save(entity);

            //保存成功后,将调用关系添加到关系表SystemRelation中
            if (reasult)
            {
                //添加与"专线"的表关系
                //reasult = AddRelation(planControl, dedicateLineControl, entity.Id, entity.DedicateLineId, "0");
                //if (reasult)
                //{
                //    //添加与"专线巡检周期"表关系
                //    reasult = AddRelation(planControl, cycTimeControl, entity.Id, entity.CycTimeId, "0");
                //}
            }

            return reasult;
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(CopPlan entity)
        {
            bool reasult = false;
            reasult = planRepository.Update(entity);

            //修改成功后,修改表关系里面的数据
            if (reasult)
            {
                //添加与"专线"的表关系
                reasult = AddRelation(planControl, dedicateLineControl, entity.Id, entity.DedicateLineId, "1");
                //if (reasult)
                //{
                //    //添加与"专线巡检周期"表关系
                //    reasult = AddRelation(planControl, cycTimeControl, entity.Id, entity.CycTimeId, "1");
                //}
            }

            return reasult;
        }

        public IList<CopPlan> LoadAll()
        {
            return planRepository.LoadAll();
        }

        public IList<CopPlan> LoadAll(string order, string where)
        {
            return planRepository.LoadAll(order, where);
        }

        /// <summary>
        /// 添加专线的时候，自动添加专线巡检计划
        /// </summary>
        /// <param name="BizAssuranLeveId">业务保障等级id</param>
        /// <returns></returns>
        public bool AddLinePlan(string BizAssuranLeveId)
        {
            bool reasult = false;
            string sql = "";

            //到专线管理中,查找出所有业务等级一样的.并根据查询出来的条数,插入相应条数的数据
            sql = " BizAssuranLeveId = '" + BizAssuranLeveId + "' and DelFlag != 1 or DelFlag is null";
            IList<ResourceDedicateLine> resourceDedicateLines = new List<ResourceDedicateLine>();
            resourceDedicateLines = lineRepository.LoadAll("id", sql);

            if (resourceDedicateLines != null && resourceDedicateLines.Count() != 0)
            {
                foreach (var resourceDedicateLine in resourceDedicateLines)
                {
                    //要判断下该专线是否已经添加计划
                    IList<CopPlan> copPlans = new List<CopPlan>();
                    sql = " DedicateLineId = '" + resourceDedicateLine.Id + "' and IsDelete = 0 ";
                    copPlans = planRepository.LoadAll("id", sql);
                    if (copPlans == null || copPlans.Count == 0)
                    {
                        CopPlan copPlan = new CopPlan();
                        copPlan.DedicateLineId = resourceDedicateLine.Id;
                        copPlan.Id = planRepository.NewSequence(MemuName);
                        copPlan.StartCycTime = "";
                        copPlan.NextCycTime = "";
                        copPlan.CreationTime = dateNow;

                        //业务保障等级
                        ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                        archiveBizAssuranLeve = assuranRepository.Get(BizAssuranLeveId);

                        //添加巡检计划
                        if (archiveBizAssuranLeve != null)
                        {
                            reasult = SavePlan(archiveBizAssuranLeve.AssuranLeveName, copPlan, "0");
                        }
                    }
                }
            }

            return reasult;
        }

        /// <summary>
        /// 修改专线的时候，根据专线业务保障等级的变化，修改专线巡检计划的巡检周期
        /// </summary>
        /// <param name="DedicateLineId">专线id</param>
        /// <returns></returns>
        public bool UpdateLinePlan(string DedicateLineId)
        {
            bool reasult = false;
            string sql = "";

            ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
            resourceDedicateLine = lineRepository.Get(DedicateLineId);

            if (resourceDedicateLine != null)
            {
               IList<CopPlan> copPlans = new List<CopPlan>();
               sql = " DedicateLineId = '" + DedicateLineId + "' and IsDelete = 0 ";
               copPlans = planRepository.LoadAll("id", sql);

               //判断该专线计划是否存在，存在就修改该专线巡检周期，不存在就添加该专线的巡检计划
               if (copPlans != null && copPlans.Count() != 0)
               {
                   //业务保障等级
                   ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                   archiveBizAssuranLeve = assuranRepository.Get(resourceDedicateLine.BizAssuranLeveId);

                   if (archiveBizAssuranLeve != null)
                   {
                       reasult = SavePlan(archiveBizAssuranLeve.AssuranLeveName, copPlans[0],"1");
                   }
               }
               else
               {
                   CopPlan copPlan = new CopPlan();
                   copPlan.DedicateLineId = resourceDedicateLine.Id;
                   copPlan.Id = planRepository.NewSequence(MemuName);
                   copPlan.StartCycTime = "";
                   copPlan.NextCycTime = "";
                   copPlan.CreationTime = dateNow;

                   //业务保障等级
                   ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                   archiveBizAssuranLeve = assuranRepository.Get(resourceDedicateLine.BizAssuranLeveId);

                   if (archiveBizAssuranLeve != null)
                   {
                       reasult = SavePlan(archiveBizAssuranLeve.AssuranLeveName, copPlan,"0");
                   }
               }
            }
            return reasult;
        }

        /// <summary>
        /// 专线退网时，删除计划（退网后，不可再重新加入）
        /// </summary>
        /// <param name="DedicateLineId">专线id</param>
        /// <returns></returns>
        public bool DeleteLinePlan(string DedicateLineId)
        {
            bool reasult = false;
            string sql = "";

            ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
            resourceDedicateLine = lineRepository.Get(DedicateLineId);

            if (resourceDedicateLine != null)
            {
                IList<CopPlan> copPlans = new List<CopPlan>();
                sql = " DedicateLineId = '" + DedicateLineId + "' and IsDelete = 0 ";
                copPlans = planRepository.LoadAll("id", sql);

                //判断该专线计划是否存在，如存在就删除
                if (copPlans != null && copPlans.Count() != 0)
                {
                    reasult = planRepository.Delete(copPlans[0].Id);

                    //删除表关系
                    if (reasult)
                    {
                        reasult = AddRelation(planControl, dedicateLineControl, copPlans[0].Id, copPlans[0].DedicateLineId, "2");
                    }
                }


            }

            return reasult;
        }

        /// <summary>
        /// 保存巡检计划
        /// </summary>
        /// <param name="AssuranLeveName">巡检周期</param>
        /// <param name="copPlan">巡检计划</param>
        /// <returns></returns>
        public bool SavePlan(string AssuranLeveName, CopPlan copPlan,string state)
        {
            bool reasult = false;

            //AAA专线1月1次、AA专线3月1次、A专线6月1次，普通不用巡检
            switch (AssuranLeveName)
            {
                case "AAA":
                    copPlan.CycTime = "1";
                    reasult = planRepository.Save(copPlan);
                    break;
                case "AA":
                    copPlan.CycTime = "3";
                    reasult = planRepository.Save(copPlan);
                    break;
                case "A":
                    copPlan.CycTime = "6";
                    reasult = planRepository.Save(copPlan);
                    break;
                default:
                    copPlan.IsDelete = 1;
                    reasult = planRepository.Update(copPlan);
                    state = "2";//2，删除表关系
                    break;
            }

            //如果有添加巡检计划，就添加与"专线"的表关系
            if (reasult)
            {
                //添加与"专线"的表关系
                reasult = AddRelation(planControl, dedicateLineControl, copPlan.Id, copPlan.DedicateLineId, state);

                //添加与"专线巡检周期"表关系
                //reasult = AddRelation(planControl, cycTimeControl, copPlan.Id, copPlan.CycTimeId, state);
            }

            return reasult;
        }

        /// <summary>
        /// 添加表关系
        /// </summary>
        /// <param name="controllerName">主表controller名称</param>
        /// <param name="relationName">关系表controller名称</param>
        /// <param name="mId">主表id</param>
        /// <param name="cId">关系表id</param>
        /// <param name="state">状态：0，为添加巡检计划；1，为修改巡检计划</param>
        /// 0,为添加，是直接添加表关系
        /// 1,为修改，是需要先删除原来的表关系，再添加表关系
        /// 2,为删除，直接删除表关系
        /// <returns></returns>
        public bool AddRelation(string controllerName, string relationName, string mId, string cId, string state)
        {
            bool reasult = false;
            string sql = "";

            //判断表关系是否存在
            sql = " RelationName = '" + relationName + "' and ControllerName = '" + controllerName + "' and MId = '"
                + mId + "' and CId = '" + cId + "'";
            IList<SystemRelation> systemRelations = new List<SystemRelation>();
            systemRelations = relationRepository.LoadAll("id", sql);
            if (systemRelations != null && systemRelations.Count() != 0)
            {
                reasult = true;
            }

            SystemRelation systemRelation = new SystemRelation();
            systemRelation.Id = relationRepository.NewSequence(RelationName);
            systemRelation.RelationName = relationName;
            systemRelation.ControllerName = controllerName;
            systemRelation.MId = mId;
            systemRelation.CId = cId;

            switch (state)
            {
                case "0":
                    if (reasult)
                    {
                        reasult = relationRepository.DeleteHql(sql);
                        if (reasult)
                        {
                            reasult = relationRepository.Save(systemRelation);
                        }
                    }
                    else
                    {
                        reasult = relationRepository.Save(systemRelation);
                    }
                    break;
                case "1":
                    reasult = relationRepository.DeleteHql(sql);
                    if (reasult)
                    {
                        reasult = relationRepository.Save(systemRelation);
                    }
                    break;
                case "2":
                    reasult = relationRepository.DeleteHql(sql);
                    break;
                default:
                    break;
            }

            return reasult;
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "";
            string vSql = "";
            IList<object[]> ls = new List<object[]>();
            hql = " select main.id,main.CycTime,main.StartCycTime,main.NextCycTime,aType.BizTypeName,"
                + " rClie.ClieName,aLeve.AssuranLeveName,aDistrict.DistrictName,aCompany.CompanyName "
                + " from CopPlan main,ResourceDedicateLine rLine,ResourceGroupClie rClie,ArchiveBizType aType,"
                + " ArchiveBizAssuranLeve aLeve,ArchiveDistrict aDistrict,ArchiveCompany aCompany "
                + " where main.IsDelete = 0 and main.DedicateLineId = rLine.id and rLine.BizTypeId = aType.id "
                + " and rLine.ClieId = rClie.id and rLine.BizAssuranLeveId = aLeve.id "
                //rLine.State != 2, State为2，代表专线已经退网,0表示可用
                + " and rLine.DistrictId = aDistrict.id and rLine.CompanyId = aCompany.id and rLine.State = '0' ";

            vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            ls = planRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<Plan> plans = new List<Plan>();
            for (int i = 0; i < ls.Count; i++)
            {
                Plan plan = new Plan();

                plan.Id = ls[i][0].ToString();
                plan.CopCycTime = ls[i][1] != null ? ls[i][1].ToString() + "个月" : null;
                plan.StartCycTime = ls[i][2] != null ? ls[i][2].ToString() : null;
                plan.NextCycTime = ls[i][3] != null ? ls[i][3].ToString() : null;
                plan.BizType = ls[i][4] != null ? ls[i][4].ToString() : null;
                plan.GroupName = ls[i][5] != null ? ls[i][5].ToString() : null;
                plan.BizAssuranLeve = ls[i][6] != null ? ls[i][6].ToString() : null;
                plan.District = ls[i][7] != null ? ls[i][7].ToString() : null;
                plan.Company = ls[i][8] != null ? ls[i][8].ToString() : null;

                //if (bools)
                //{
                //    //判断是否已经巡检,如果"当前时间"比"下次巡检时间"晚的话，就是还没有巡检的
                //    //当前时间,如果在"下次巡检时间"之前,就显示"已登记",不能再进行巡检登记
                //    //如果"当前时间",在"下次巡检时间"之后,将显示"巡检登记",可以进行巡检登记
                //    if (ls[i][3] != null)
                //    {
                //        DateTime nextCycTime = Convert.ToDateTime(ls[i][3]);
                //        if (DateTime.Compare(dateNow, nextCycTime) < 0)
                //        {
                //            plan.Operating = "已登记";
                //        }
                //        else
                //        {
                //            plan.Operating = "<a href='/Cop/Enrol/Add/" + ls[i][0].ToString() + "'>巡检登记</a>";
                //        }
                //    }
                //    else
                //    {
                //        plan.Operating = "<a href='/Cop/Enrol/Add/" + ls[i][0].ToString() + "'>巡检登记</a>";
                //    }
                //}

                //计算出该巡检计划已经延迟登记多长时间
                if (ls[i][3] != null)
                {
                    if (DateTime.Compare(dateNow, Convert.ToDateTime(ls[i][3])) > 0)
                    {
                        TimeSpan ts1 = new TimeSpan(Convert.ToDateTime(dateNow.ToShortDateString()).Ticks);
                        TimeSpan ts2 = new TimeSpan(Convert.ToDateTime(ls[i][3]).Ticks);
                        TimeSpan ts = ts1.Subtract(ts2).Duration();
                        plan.DelayTime = ts.Days.ToString() + "天";
                    }
                }

                plans.Add(plan);
            }
            string rowsjson = JSONHelper.ToJSON(plans);
            int recordCount = planRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
