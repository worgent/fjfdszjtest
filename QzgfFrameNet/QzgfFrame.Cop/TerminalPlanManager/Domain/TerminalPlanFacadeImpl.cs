using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalPlanManager.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using Newtonsoft.Json;
using QzgfFrame.Cop.TerminalEnrolManager.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Cop.TerminalTimeManager.Models;

namespace QzgfFrame.Cop.TerminalPlanManager.Domain
{
    /// <summary>
    /// 自助终端巡检计划
    /// </summary>
    public class TerminalPlanFacadeImpl : TerminalPlanFacade
    {
        #region

        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        private IRepository<CopTerminalPlan> terminalPlanRepository { set; get; }
        /// <summary>
        /// 自助终端巡检登记
        /// </summary>
        private IRepository<CopTerminalEnrol> terminalEnrolRepository { set; get; }
        /// <summary>
        /// 自助终端
        /// </summary>
        private IRepository<ResourceSelfHelpEquip> selfHelpEquipRepository { set; get; }
        /// <summary>
        /// 网点类型
        /// </summary>
        private IRepository<ArchiveOutletsType> outletsTypeRepository { set; get; }
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
        string terminalPlanControl = "TerminalPlan";
        /// <summary>
        /// 关系表"自助终端巡检周期"
        /// </summary>
        string terminalTimeControl = "TerminalTime";
        /// <summary>
        /// 关系表"自助终端"
        /// </summary>
        string selfHelpEquipControl = "SelfHelpEquip";

        /// <summary>
        /// 关系表简称
        /// </summary>
        string RelationName = "SYSTEM_Relation";
        /// <summary>
        /// 菜单表简称
        /// </summary>
        string MemuName = "SYSTEM_MENU";

        #endregion

        public CopTerminalPlan Get(object id)
        {
            return terminalPlanRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool reasult = false;
            string sql = "";
            foreach (var s in idarr)
            {
                //删除前要判断该计划是否已经进行登记，如果有，将不允许删除
                sql = " TerminalPlanId = '" + s + "' and IsDelete = 0 ";
                IList<CopTerminalEnrol> copTerminalEnrols = new List<CopTerminalEnrol>();
                copTerminalEnrols = terminalEnrolRepository.LoadAll("id", sql);
                if (copTerminalEnrols.Count == 0)
                {
                    CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                    copTerminalPlan = terminalPlanRepository.Get(s);
                    copTerminalPlan.IsDelete = 1;
                    copTerminalPlan.DeleteTime = dateNow;
                    reasult = terminalPlanRepository.Update(copTerminalPlan);
                    //result = terminalPlanRepository.Delete(s);

                    //删除成功后,将关系表SystemRelation中的表关系删除
                    if (reasult)
                    {
                        //删除与"自助终端巡检周期"关系
                        if (copTerminalPlan.TerminalTimeId != null && copTerminalPlan.TerminalTimeId != "")
                        {
                            //判断关系是否存在
                            sql = " RelationName = '" + terminalTimeControl + "' and ControllerName = '" + terminalPlanControl 
                                + "' and MId = '" + copTerminalPlan.Id + "' and CId = '" + copTerminalPlan.TerminalTimeId + "'";
                            reasult = relationRepository.DeleteHql(sql);
                        }

                        //删除与"自助终端"关系
                        if (copTerminalPlan.SelfHelpEquipId != null && copTerminalPlan.SelfHelpEquipId != "")
                        {
                            //判断关系是否存在
                            sql = " RelationName = '" + selfHelpEquipControl + "' and ControllerName = '" + terminalPlanControl
                                + "' and MId = '" + copTerminalPlan.Id + "' and CId = '" + copTerminalPlan.SelfHelpEquipId + "'";
                            reasult = relationRepository.DeleteHql(sql);
                        }
                    }
                }
            }
            return reasult;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(CopTerminalPlan entity)
        {
            entity.Id = terminalPlanRepository.NewSequence(MemuName);
            bool reasult = false;
            reasult = terminalPlanRepository.Save(entity);

            //保存成功后,将调用关系添加到关系表SystemRelation中
            if (reasult)
            {
                int i = 0;
                string sql = "";
                //添加与"自助终端巡检周期"关系
                if (entity.TerminalTimeId != null && entity.TerminalTimeId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalTimeControl + "' and ControllerName = '" + terminalPlanControl 
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.TerminalTimeId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalTimeControl;
                        systemRelation.ControllerName = terminalPlanControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.TerminalTimeId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }

                //添加与"自助终端"关系
                if (entity.SelfHelpEquipId != null && entity.SelfHelpEquipId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalTimeControl + "' and ControllerName = '" + terminalPlanControl 
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.SelfHelpEquipId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalTimeControl;
                        systemRelation.ControllerName = terminalPlanControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.SelfHelpEquipId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }
            }

            return reasult;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <param name="no"></param>
        /// <returns></returns>
        public bool Save(CopTerminalPlan entity, string no)
        {
            entity.Id = terminalPlanRepository.NewSequence(MemuName, no);
            bool reasult = false;
            reasult = terminalPlanRepository.Save(entity);

            //保存成功后,将调用关系添加到关系表SystemRelation中
            if (reasult)
            {
                int i = 0;
                string sql = "";
                //添加与"自助终端巡检周期"关系
                if (entity.TerminalTimeId != null && entity.TerminalTimeId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalTimeControl + "' and ControllerName = '" + terminalPlanControl
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.TerminalTimeId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalTimeControl;
                        systemRelation.ControllerName = terminalPlanControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.TerminalTimeId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }

                //添加与"自助终端"关系
                if (entity.SelfHelpEquipId != null && entity.SelfHelpEquipId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalTimeControl + "' and ControllerName = '" + terminalPlanControl
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.SelfHelpEquipId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalTimeControl;
                        systemRelation.ControllerName = terminalPlanControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.SelfHelpEquipId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }
            }

            return reasult;
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(CopTerminalPlan entity)
        {
            bool reasult = false;
            reasult = terminalPlanRepository.Update(entity);

            //修改成功后,修改表关系里面的数据
            if (reasult)
            {
                string sql = "";
                int i = 0;

                sql = " ControllerName = '" + terminalPlanControl + "' and MId = '" + entity.Id + "' ";
                //先将以前的记录删除,再添加
                reasult = relationRepository.DeleteHql(sql);

                //判断表关系是否存在,不存在则添加,存在则修改
                //添加与"自助终端巡检周期"关系
                if (entity.TerminalTimeId != null && entity.TerminalTimeId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalTimeControl + "' and ControllerName = '" + terminalPlanControl
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.TerminalTimeId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalTimeControl;
                        systemRelation.ControllerName = terminalPlanControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.TerminalTimeId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }

                //添加与"自助终端"关系
                if (entity.SelfHelpEquipId != null && entity.SelfHelpEquipId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + selfHelpEquipControl + "' and ControllerName = '" + terminalPlanControl + "' and MId = '" + entity.Id + "' and CId = '"
                        + entity.SelfHelpEquipId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = selfHelpEquipControl;
                        systemRelation.ControllerName = terminalPlanControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.SelfHelpEquipId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }
            } 
            
            return reasult;
        }

        /// <summary>
        /// 添加自助终端巡检计划
        /// </summary>
        /// <param name="ArchiveOutletsTypeId">网点类型id</param>
        /// <returns></returns>
        public bool AddSelfHelpEquip(string ArchiveOutletsTypeId)
        {
            bool reasult = false;
            string sql = "";

            ////到自助终端管理中,查找出所有业务等级一样的.并根据查询出来的条数,插入相应条数的数据
            //sql = " NetType = '" + ArchiveOutletsTypeId + "' and DelFlag != 1 or DelFlag is null ";
            //IList<ResourceSelfHelpEquip> resourceSelfHelpEquips = new List<ResourceSelfHelpEquip>();
            //resourceSelfHelpEquips = selfHelpEquipRepository.LoadAll("id", sql);

            //if (resourceSelfHelpEquips != null && resourceSelfHelpEquips.Count() != 0)
            //{
            //    foreach (var resourceSelfHelpEquip in resourceSelfHelpEquips)
            //    {
            //        //判断该“自助终端”的巡检计划是否存在
            //        IList<CopTerminalPlan> copTerminalPlans = new List<CopTerminalPlan>();
            //        sql = " SelfHelpEquipId = '" + resourceSelfHelpEquip.Id + "' and IsDelete = 0";
            //        copTerminalPlans = terminalPlanRepository.LoadAll("id", sql);
            //        if (copTerminalPlans.Count == 0)
            //        {
            //            CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
            //            copTerminalPlan.SelfHelpEquipId = resourceSelfHelpEquip.Id;
            //            copTerminalPlan.TerminalTimeId = entity.CopTerminalPlan.TerminalTimeId;
            //            CopTerminalTime copTerminalTime = new CopTerminalTime();
            //            copTerminalTime = terminalTimeFacade.Get(entity.CopTerminalPlan.TerminalTimeId);
            //            copTerminalPlan.TerminalTime = copTerminalTime != null ? copTerminalTime.TerminalTime : "";

            //            //生成计划的时候,"巡检起始时间"与"下次巡检时间"都为空，
            //            copTerminalPlan.StartTerminalTime = "";
            //            copTerminalPlan.NextTerminalTime = "";
            //            copTerminalPlan.CreationTime = dateNow;

            //            terminalPlanFacade.Save(copTerminalPlan);
            //        }
            //    }
            //}

            return reasult;
        }

        /// <summary>
        /// 根据自助终端网点类型的变化，修改自助终端巡检计划的巡检周期
        /// </summary>
        /// <param name="ResourceSelfHelpEquipId">自助终端id</param>
        /// <returns></returns>
        public bool UpdateSelfHelpEquip(string ResourceSelfHelpEquipId)
        {
            bool reasult = false;
            //string sql = "";

            //ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
            //resourceDedicateLine = lineRepository.Get(DedicateLineId);

            //if (resourceDedicateLine != null)
            //{
            //    IList<CopPlan> copPlans = new List<CopPlan>();
            //    sql = " DedicateLineId = '" + DedicateLineId + "' and IsDelete = 0 ";
            //    copPlans = terminalPlanRepository.LoadAll("id", sql);

            //    //判断该自助终端计划是否存在，存在就修改该自助终端巡检周期，不存在就添加该自助终端的巡检计划
            //    if (copPlans != null && copPlans.Count() != 0)
            //    {
            //        //业务保障等级
            //        ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
            //        archiveBizAssuranLeve = assuranRepository.Get(resourceDedicateLine.BizAssuranLeveId);

            //        if (archiveBizAssuranLeve != null)
            //        {
            //            reasult = SavePlan(archiveBizAssuranLeve.AssuranLeveName, copPlans[0], "1");
            //        }
            //    }
            //    else
            //    {
            //        CopPlan copPlan = new CopPlan();
            //        copPlan.DedicateLineId = resourceDedicateLine.Id;
            //        copPlan.Id = terminalPlanRepository.NewSequence(MemuName);
            //        copPlan.StartCycTime = "";
            //        copPlan.NextCycTime = "";
            //        copPlan.CreationTime = dateNow;

            //        //业务保障等级
            //        ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
            //        archiveBizAssuranLeve = assuranRepository.Get(resourceDedicateLine.BizAssuranLeveId);

            //        if (archiveBizAssuranLeve != null)
            //        {
            //            reasult = SavePlan(archiveBizAssuranLeve.AssuranLeveName, copPlan, "0");
            //        }
            //    }
            //}
            return reasult;
        }

        /// <summary>
        /// 自助终端退网，删除计划（退网后，不可再重新加入）
        /// </summary>
        /// <param name="ResourceSelfHelpEquipId">自助终端id</param>
        /// <returns></returns>
        public bool DeleteSelfHelpEquip(string ResourceSelfHelpEquipId)
        {
            bool reasult = false;
            //string sql = "";

            //ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
            //resourceDedicateLine = lineRepository.Get(DedicateLineId);

            //if (resourceDedicateLine != null)
            //{
            //    IList<CopPlan> copPlans = new List<CopPlan>();
            //    sql = " DedicateLineId = '" + DedicateLineId + "' and IsDelete = 0 ";
            //    copPlans = terminalPlanRepository.LoadAll("id", sql);

            //    //判断该自助终端计划是否存在，如存在就删除
            //    if (copPlans != null && copPlans.Count() != 0)
            //    {
            //        reasult = terminalPlanRepository.Delete(copPlans[0].Id);

            //        //删除表关系
            //        if (reasult)
            //        {
            //            reasult = AddRelation(terminalPlanControl, selfHelpEquipControl, copPlans[0].Id, copPlans[0].DedicateLineId, "2");
            //        }
            //    }


            //}

            return reasult;
        }

        /// <summary>
        /// 保存巡检计划
        /// </summary>
        /// <param name="AssuranLeveName">巡检周期</param>
        /// <param name="copPlan">巡检计划</param>
        /// <returns></returns>
        public bool SavePlan(string AssuranLeveName, CopTerminalPlan copPlan, string state)
        {
            bool reasult = false;

            //AAA自助终端1月1次、AA自助终端3月1次、A自助终端6月1次，普通不用巡检
            switch (AssuranLeveName)
            {
                case "AAA":
                    copPlan.TerminalTime = "1";
                    reasult = terminalPlanRepository.Save(copPlan);
                    break;
                case "AA":
                    copPlan.TerminalTime = "3";
                    reasult = terminalPlanRepository.Save(copPlan);
                    break;
                case "A":
                    copPlan.TerminalTime = "6";
                    reasult = terminalPlanRepository.Save(copPlan);
                    break;
                default:
                    copPlan.IsDelete = 1;
                    reasult = terminalPlanRepository.Update(copPlan);
                    state = "2";//2，删除表关系
                    break;
            }

            //如果有添加巡检计划，就添加与"自助终端"的表关系
            if (reasult)
            {
                //添加与"自助终端"的表关系
                reasult = AddRelation(terminalPlanControl, selfHelpEquipControl, copPlan.Id, copPlan.SelfHelpEquipId, state);

                //添加与"自助终端巡检周期"表关系
                //reasult = AddRelation(terminalPlanControl, cycTimeControl, copPlan.Id, copPlan.CycTimeId, state);
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


        public IList<CopTerminalPlan> LoadAll()
        {
            return terminalPlanRepository.LoadAll();
        }

        public IList<CopTerminalPlan> LoadAll(string order, string where)
        {
            return terminalPlanRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = " select main.id,main.TerminalTime,main.StartTerminalTime,main.NextTerminalTime,rEquip.UseNetName, "
                            + " aType.OutletsTypeName,aDistrict.DistrictName "
                            + " from CopTerminalPlan main,ResourceSelfHelpEquip rEquip,ArchiveOutletsType aType,"
                            + " ArchiveDistrict aDistrict "
                            + " where main.IsDelete = 0 and main.SelfHelpEquipId = rEquip.id and rEquip.NetType = aType.id "
                            + " and rEquip.DistrictId = aDistrict.id ";

            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            IList<object[]> ls = terminalPlanRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<TerminalPlan> terminalPlan = new List<TerminalPlan>();
            for (int i = 0; i < ls.Count; i++)
            {
                TerminalPlan terminalPlans = new TerminalPlan();

                terminalPlans.Id = ls[i][0].ToString();
                terminalPlans.TerminalTime = ls[i][1] != null ? ls[i][1].ToString() + "个月" : null;
                terminalPlans.StartTerminalTime = ls[i][2] != null ? ls[i][2].ToString() : null;
                terminalPlans.NextTerminalTime = ls[i][3] != null ? ls[i][3].ToString() : null;
                terminalPlans.SelfHelpEquip = ls[i][4] != null ? ls[i][4].ToString() : null;
                terminalPlans.OutletsType = ls[i][5] != null ? ls[i][5].ToString() : null;
                terminalPlans.District = ls[i][6] != null ? ls[i][6].ToString() : null;

                //判断是否已经巡检,如果"当前时间"比"下次巡检时间"晚的话，就是还没有巡检的
                //当前时间,如果在"下次巡检时间"之前,就显示"已登记",不能再进行巡检登记
                //如果"当前时间",在"下次巡检时间"之后,将显示"巡检登记",可以进行巡检登记
                DateTime nowDate = DateTime.Now;
                if (ls[i][3] != null)
                {
                    DateTime nextCycTime = Convert.ToDateTime(ls[i][3]);
                    if (DateTime.Compare(nowDate, nextCycTime) < 0)
                    {
                        terminalPlans.Operating = "已登记";
                    }
                    else
                    {
                        terminalPlans.Operating = "<a href='/Cop/TerminalEnrol/Add/" + ls[i][0].ToString() + "'>巡检登记</a>";
                    }
                }
                else
                {
                    terminalPlans.Operating = "<a href='/Cop/TerminalEnrol/Add/" + ls[i][0].ToString() + "'>巡检登记</a>";
                }

                if (ls[i][3] != null)
                {
                    if (DateTime.Compare(dateNow, Convert.ToDateTime(ls[i][3])) > 0)
                    {
                        TimeSpan ts1 = new TimeSpan(Convert.ToDateTime(dateNow.ToShortDateString()).Ticks);
                        TimeSpan ts2 = new TimeSpan(Convert.ToDateTime(ls[i][3]).Ticks);
                        TimeSpan ts = ts1.Subtract(ts2).Duration();
                        terminalPlans.DelayTime = ts.Days.ToString() + "天";
                    }
                }
                
                terminalPlan.Add(terminalPlans);
            }

            string rowsjson = JSONHelper.ToJSON(terminalPlan);
            int recordCount = terminalPlanRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
