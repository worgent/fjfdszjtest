using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Cop.EnrolManager.Models;
using Newtonsoft.Json;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.System.RelationManger.Models;

namespace QzgfFrame.Cop.EnrolManager.Domain
{
    /// <summary>
    /// 巡检登记
    /// </summary>
    public class EnrolFacadeImpl : EnrolFacade
    {
        #region 

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
        /// 巡检计划
        /// </summary>
        private IRepository<CopPlan> planRepository { set; get; }
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
        string enrolControl = "Enrol";
        /// <summary>
        /// 关系表"专线巡检计划"
        /// </summary>
        string planControl = "Plan";

        /// <summary>
        /// 关系表简称
        /// </summary>
        string RelationName = "SYSTEM_Relation";
        /// <summary>
        /// 菜单表简称
        /// </summary>
        string MemuName = "SYSTEM_MENU";

        #endregion

        public CopEnrol Get(object id)
        {
            return enrolRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// 假删除,并未真正删除数据,只是将数据的状态变下
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool reasult = false;
            foreach (var s in idarr)
            {
                CopEnrol copEnrol = new CopEnrol();
                copEnrol = enrolRepository.Get(s);
                copEnrol.IsDelete = 1;
                copEnrol.DeleteTime = dateNow;
                reasult = enrolRepository.Update(copEnrol);

                //删除成功后,将关系表SystemRelation中的表关系删除
                if (reasult)
                {
                    //删除与"专线巡检计划"关系
                    reasult = AddRelation(enrolControl, planControl, copEnrol.Id, copEnrol.CopPlanId, "2");
                }
            }
            return reasult;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(CopEnrol entity)
        {
            bool reasult = false;
            entity.Id = enrolRepository.NewSequence(MemuName);
            reasult = enrolRepository.Save(entity);

            //保存成功后,将调用关系添加到关系表SystemRelation中
            if (reasult)
            {
                //添加与"专线巡检计划"关系
                reasult = AddRelation(enrolControl, planControl, entity.Id, entity.CopPlanId, "0");
            }
            return reasult;
        }

        /// <summary>
        /// 修改数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(CopEnrol entity)
        {
            bool reasult = false;
            reasult = enrolRepository.Update(entity);

            //修改成功后,修改表关系里面的数据
            if (reasult)
            {
                //添加与"专线巡检计划"关系
                reasult = AddRelation(enrolControl, planControl, entity.Id, entity.CopPlanId, "1");
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

        public IList<CopEnrol> LoadAll()
        {
            return enrolRepository.LoadAll();
        }

        public IList<CopEnrol> LoadAll(string order, string where)
        {
            return enrolRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            //在hql语句里面字段有区分大小写，不然会出错
            string hql = " select main.id,main.Personnel,main.CycEnrolTime,aType.BizTypeName,rClie.ClieName"
                + " ,aLeve.AssuranLeveName,aDistrict.DistrictName,aCompany.CompanyName "
                + " from CopEnrol main,CopPlan cPlan,ResourceDedicateLine rLine,ResourceGroupClie rClie,"
                + " ArchiveBizType aType,ArchiveBizAssuranLeve aLeve,ArchiveDistrict aDistrict,ArchiveCompany aCompany "
                + " where main.IsDelete = 0 and main.CopPlanId = cPlan.id and cPlan.DedicateLineId = rLine.id and "
                + " rLine.BizTypeId = aType.id and rLine.ClieId = rClie.id "
                + " and rLine.BizAssuranLeveId = aLeve.id and rLine.DistrictId = aDistrict.id "
                //rLine.State != 2, State为2，代表专线已经退网
                + " and rLine.CompanyId = aCompany.id  and rLine.State != 2 ";

            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            IList<object[]> ls = planRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<Enrol> cyc = new List<Enrol>();
            for (int i = 0; i < ls.Count; i++)
            {
                Enrol cycs = new Enrol();
                //cycs.Operating = "<a href='javascript:WordOut(" + ls[i][0].ToString() + ");'>巡检确认书</a>";
                cycs.Id = ls[i][0].ToString();
                cycs.PersonnelName = ls[i][1] != null ? ls[i][1].ToString() : null;
                cycs.CycEnrolTime = ls[i][2] != null ? ls[i][2].ToString() : null;
                cycs.BizType = ls[i][3] != null ? ls[i][3].ToString() : null;
                cycs.GroupName = ls[i][4] != null ? ls[i][4].ToString() : null;
                cycs.BizAssuranLeve = ls[i][5] != null ? ls[i][5].ToString() : null;
                cycs.District = ls[i][6] != null ? ls[i][6].ToString() : null;
                cycs.Company = ls[i][7] != null ? ls[i][7].ToString() : null;
                cyc.Add(cycs);
            }

            string rowsjson = JsonConvert.SerializeObject(cyc);
            int recordCount = enrolRepository.FindByPageLinkCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
