using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.EquipFaultManger.Models;
using QzgfFrame.Resources.EquipFaultManger.Domain;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Resources.EquipFaultManger.Domain
{
    public class EquipFaultFacadeImpl : EquipFaultFacade
    {
        private IRepository<ResourceEquipFault> equipFaultRepository { set; get; }
        private IRepository<ArchiveSelfHelpEquipModel> selfHelpEquipModelRepository { set; get; }
        private IRepository<ArchiveSelfHelpEquipType> selfHelpEquipTypeRepository { set; get; }
        private IRepository<ArchiveSelfHelpFactory> selfHelpFactoryRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ResourceEquipFault Get(object id)
        {
            string hql =
             @"select new ResourceEquipFault(main.Id,main.NoticeDateTime,main.ReachDatetime,main.SolveDatetime
          ,main.HandleTime,main.TroubleShooter,main.Description,main.HandleProcess ,main.HandleResult
          ,main.ComponentId,main.IsReplace,main.BreakdownTypeId,main.Remark ,main.SelfHelpEquipId
          ,main.ClieTel,main.UseNetNo,main.UseNetName,main.TermiId ,main.DistrictId,main.FactoryId
          ,main.EquipModelName,main.CompanyId,main.State,ad.DistrictName,asf.Abbrevia as FactoryName,ac.CompanyName
          )
                      from ResourceEquipFault main,ArchiveSelfHelpFactory asf,ArchiveDistrict ad,ArchiveCompany ac
            where main.DistrictId=ad.Id and main.FactoryId=asf.Id and main.CompanyId=ac.Id
                  and (main.DelFlag!=1 or main.DelFlag is null)";

            return equipFaultRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
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
                result = equipFaultRepository.Delete(s);
            }
            return result;
        }
        /// <summary>
        /// 同时退网多行记录,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Quit(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string hql = " State='1' where Id='" + s + "'";
                result = equipFaultRepository.Update(hql);
            }
            return result;
        }

        public bool Save(ResourceEquipFault entity, string no)
        {
            entity.Id = equipFaultRepository.NewSequence("SYSTEM_MENU",no);
            entity.CreateDate = DateTime.Now;
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "EquipFault";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "Component";
            sre2.ControllerName = "EquipFault";
            sre2.MId = entity.Id;
            sre2.CId = entity.ComponentId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "BreakdownType";
            sre3.ControllerName = "EquipFault";
            sre3.MId = entity.Id;
            sre3.CId = entity.BreakdownTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);

            i++;
            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "SelfHelpEquip";
            sre6.ControllerName = "EquipFault";
            sre6.MId = entity.Id;
            sre6.CId = entity.SelfHelpEquipId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);

            result = equipFaultRepository.Save(entity);
            return result;   
        }

        public bool Update(ResourceEquipFault entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='EquipFault'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "EquipFault";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "Component";
            sre2.ControllerName = "EquipFault";
            sre2.MId = entity.Id;
            sre2.CId = entity.ComponentId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "BreakdownType";
            sre3.ControllerName = "EquipFault";
            sre3.MId = entity.Id;
            sre3.CId = entity.BreakdownTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            i++;
            SystemRelation sre6 = new SystemRelation();
            sre6.RelationName = "SelfHelpEquip";
            sre6.ControllerName = "EquipFault";
            sre6.MId = entity.Id;
            sre6.CId = entity.SelfHelpEquipId;
            sre6.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre6);

            return equipFaultRepository.Update(entity);
        }

        public IList<ResourceEquipFault> LoadAll()
        {
            return equipFaultRepository.LoadAll();
        }
        public IList<ResourceEquipFault> LoadAll(string order, string where)
        {
            return equipFaultRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from ResourceEquipFault main where (main.DelFlag!=1 or main.DelFlag is null) ";
            hql +=  gridsearch;
            string vSql = hql + @" order by main." + sortname + " " + sortorder;
            var lsSelfHelpEquipFault = equipFaultRepository.FindByPage(pageNo, pageSize, vSql);
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            var lsType = selfHelpEquipTypeRepository.LoadAllbyHql("from ArchiveSelfHelpEquipType");
            var lsFactory = selfHelpFactoryRepository.LoadAllbyHql("from ArchiveSelfHelpFactory");

            var jsonlist = (from vlsSelfHelpEquipFault in lsSelfHelpEquipFault
                            join vlsDistrict in lsDistrict
                                on vlsSelfHelpEquipFault.DistrictId equals vlsDistrict.Id into joinvlsSelfHelpEquipFaultDistrict
                            from vlsSelfHelpEquipFaultDistrict in joinvlsSelfHelpEquipFaultDistrict.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsSelfHelpEquipFault.CompanyId equals vlsCompany.Id into joinvlsSelfHelpEquipFaultCompany
                            from vlsSelfHelpEquipFaultCompany in joinvlsSelfHelpEquipFaultCompany.DefaultIfEmpty()

                            join vlsType in lsType
                                on vlsSelfHelpEquipFault.SelfHelpEquipId equals vlsType.Id into joinvlsSelfHelpEquipFaultType
                            from vlsSelfHelpEquipFaultType in joinvlsSelfHelpEquipFaultType.DefaultIfEmpty()

                            join vlsFactory in lsFactory
                                on vlsSelfHelpEquipFault.FactoryId equals vlsFactory.Id into joinvlsSelfHelpEquipFaultFactory
                            from vlsSelfHelpEquipFaultFactory in joinvlsSelfHelpEquipFaultFactory.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsSelfHelpEquipFault.Id,
                                TermiId = vlsSelfHelpEquipFault.TermiId,
                                UseNetName = vlsSelfHelpEquipFault.UseNetName,
                                BreakDownDate = string.Format("{0:d}", ExtensionMethods.ToDateNull(vlsSelfHelpEquipFault.NoticeDateTime)),
                                BreakDownTime = string.Format("{0:T}", ExtensionMethods.ToDateNull(vlsSelfHelpEquipFault.NoticeDateTime)),
                                TroubleShooter = vlsSelfHelpEquipFault.TroubleShooter,
                                HandleTime = vlsSelfHelpEquipFault.HandleTime+"分钟",
                                IsReplace =vlsSelfHelpEquipFault.IsReplace,
                                EquipModelName = vlsSelfHelpEquipFault.EquipModelName,
                                DistrictName = vlsSelfHelpEquipFaultDistrict != null ? vlsSelfHelpEquipFaultDistrict.DistrictName : "",
                                CompanyName = vlsSelfHelpEquipFaultCompany != null ? vlsSelfHelpEquipFaultCompany.CompanyName : "",
                                EquipTypeName = vlsSelfHelpEquipFaultType != null ? vlsSelfHelpEquipFaultType.SelfHelpEquipTypeName : "",
                                FactoryName = vlsSelfHelpEquipFaultFactory != null ? vlsSelfHelpEquipFaultFactory.Abbrevia : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            int recordCount = equipFaultRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='EquipFault'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='EquipFault'";
                    result = relationRepository.DeleteHql(sql);

                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = equipFaultRepository.Update(hql);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='EquipFault'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = equipFaultRepository.Update(hql);
                } 
            }
            return result;
        }
        /// <summary>
        /// 导出信息
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public IList<object[]> FindExcel(string aryField, string gridsearch)
        {
            string hql =
           @" from tab_Equip_Fault main left join tab_Company ac on ac.Id=main.CompanyId 
                                      left join tab_District ad on ad.Id=main.DistrictId   
                                      left join tab_Breakdown_Type btype on btype.Id=main.BreakdownTypeId  
                                      left join tab_Component component on component.Id=main.ComponentId  
                                      left join tab_SelfHelp_Factory factory on factory.Id=main.FactoryId  
            where  (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = "select " + aryField + hql + gridsearch;
            vSql += @" order by main.Id desc";

            IList<object[]> ls = equipFaultRepository.LoadAllSqlObj(vSql);

            return ls;
        }
    }
}
