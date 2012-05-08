using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Cop.TerminalEnrolManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;
using QzgfFrame.Cop.TerminalPlanManager.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.JSON;

namespace QzgfFrame.Cop.TerminalEnrolManager.Domain
{
    /// <summary>
    /// 自助终端巡检登记
    /// </summary>
    public class TerminalEnrolFacadeImpl : TerminalEnrolFacade
    {
        #region 

        /// <summary>
        /// 自助终端巡检登记
        /// </summary>
        private IRepository<CopTerminalEnrol> terminalEnrolRepository { set; get; }
        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        private IRepository<CopTerminalPlan> terminalPlanRepository { set; get; }
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
        string terminalEnrolControl = "TerminalEnrol";
        /// <summary>
        /// 关系表"专线巡检计划"
        /// </summary>
        string terminalPlanControl = "TerminalPlan";

        /// <summary>
        /// 关系表简称
        /// </summary>
        string RelationName = "SYSTEM_Relation";
        /// <summary>
        /// 菜单表简称
        /// </summary>
        string MemuName = "SYSTEM_MENU";

        #endregion

        public CopTerminalEnrol Get(object id)
        {
            return terminalEnrolRepository.Get(id.ToString());
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
            foreach (var s in idarr)
            {
                CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();
                copTerminalEnrol = terminalEnrolRepository.Get(s);
                copTerminalEnrol.IsDelete = 1;
                copTerminalEnrol.DeleteTime = dateNow;
                reasult = terminalEnrolRepository.Update(copTerminalEnrol);
                //result = terminalEnrolRepository.Delete(s);

                //删除成功后,将关系表SystemRelation中的表关系删除
                if (reasult)
                {
                    string sql = "";
                    //删除与"专线巡检计划"关系
                    if (copTerminalEnrol.TerminalPlanId != null && copTerminalEnrol.TerminalPlanId != "")
                    {
                        //判断关系是否存在
                        sql = " RelationName = '" + terminalPlanControl + "' and ControllerName = '" + terminalEnrolControl 
                            + "' and MId = '" + copTerminalEnrol.Id + "' and CId = '" + copTerminalEnrol.TerminalPlanId + "'";

                        reasult = relationRepository.DeleteHql(sql);
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
        public bool Save(CopTerminalEnrol entity)
        {
            entity.Id = terminalEnrolRepository.NewSequence(MemuName);
            bool reasult = false;
            reasult = terminalEnrolRepository.Save(entity);

            //保存成功后,将调用关系添加到关系表SystemRelation中
            if (reasult)
            {
                int i = 0;
                string sql = "";
                //添加与"专线巡检计划"关系
                if (entity.TerminalPlanId != null && entity.TerminalPlanId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalPlanControl + "' and ControllerName = '" + terminalEnrolControl 
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.TerminalPlanId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalPlanControl;
                        systemRelation.ControllerName = terminalEnrolControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.TerminalPlanId;
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
        public bool Update(CopTerminalEnrol entity)
        {
            bool reasult = false;
            reasult = terminalEnrolRepository.Update(entity);

            //修改成功后,修改表关系里面的数据
            if (reasult)
            {
                string sql = "";
                int i = 0;

                sql = " ControllerName = '" + terminalEnrolControl + "' and MId = '" + entity.Id + "' ";
                //先将以前的记录删除,再添加
                reasult = relationRepository.DeleteHql(sql);

                //判断表关系是否存在,不存在则添加,存在则修改
                //添加与"专线巡检计划"关系
                if (entity.TerminalPlanId != null && entity.TerminalPlanId != "")
                {
                    //判断关系是否存在
                    sql = " RelationName = '" + terminalPlanControl + "' and ControllerName = '" + terminalEnrolControl 
                        + "' and MId = '" + entity.Id + "' and CId = '" + entity.TerminalPlanId + "'";
                    IList<SystemRelation> systemRelations = new List<SystemRelation>();
                    systemRelations = relationRepository.LoadAll("id", sql);
                    if (systemRelations.Count() == 0)
                    {
                        SystemRelation systemRelation = new SystemRelation();
                        systemRelation.RelationName = terminalPlanControl;
                        systemRelation.ControllerName = terminalEnrolControl;
                        systemRelation.MId = entity.Id;
                        systemRelation.CId = entity.TerminalPlanId;
                        systemRelation.Id = relationRepository.NewSequence(RelationName);
                        reasult = relationRepository.Save(systemRelation);
                        i++;
                    }
                }
            }

            return reasult;
        }

        public IList<CopTerminalEnrol> LoadAll()
        {
            return terminalEnrolRepository.LoadAll();
        }

        public IList<CopTerminalEnrol> LoadAll(string order, string where)
        {
            return terminalEnrolRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            //在hql语句里面字段有区分大小写，不然会出错
            string hql = " select main.id,main.Personnel,main.TerminalEnrolTime,rEquip.UseNetName,aType.OutletsTypeName,"
                        + " aDistrict.DistrictName from CopTerminalEnrol main, CopTerminalPlan cPlan,ResourceSelfHelpEquip "
                        + " rEquip,ArchiveOutletsType aType,ArchiveDistrict aDistrict where main.IsDelete = 0 and "
                        + " main.TerminalPlanId = cPlan.id  and cPlan.SelfHelpEquipId = rEquip.id and rEquip.NetType = aType.id "
                        + " and rEquip.DistrictId = aDistrict.id ";

            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            IList<object[]> ls = terminalEnrolRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<TerminalEnrol> terminalEnrols = new List<TerminalEnrol>();
            for (int i = 0; i < ls.Count; i++)
            {
                TerminalEnrol terminalEnrol = new TerminalEnrol();
                terminalEnrol.Operating = "<a href='javascript:WordOut(" + ls[i][0].ToString() + ");'>巡检确认书</a>";
                terminalEnrol.Id = ls[i][0].ToString();
                terminalEnrol.PersonnelName = ls[i][1] != null ? ls[i][1].ToString() : null;
                terminalEnrol.TerminalEnrolTime = ls[i][2] != null ? ls[i][2].ToString() : null;
                terminalEnrol.SelfHelpEquip = ls[i][3] != null ? ls[i][3].ToString() : null;
                terminalEnrol.OutletsType = ls[i][4] != null ? ls[i][4].ToString() : null;

                terminalEnrols.Add(terminalEnrol);
            }

            string rowsjson = JSONHelper.ToJSON(terminalEnrols);
            int recordCount = terminalEnrolRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }    
    }
}
