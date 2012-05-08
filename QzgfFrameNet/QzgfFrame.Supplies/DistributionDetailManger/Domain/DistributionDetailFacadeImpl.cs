using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.DistributionDetailManger.Models;
using QzgfFrame.Supplies.DistributionDetailManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.DistributionDetailManger.Domain
{
    public class DistributionDetailFacadeImpl : DistributionDetailFacade
    {
        private IRepository<SuppliesDistributionDetail> distributionDetailRepository { set; get; }
        private IRepository<ArchiveSuppliesType> suppliesTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }


        public SuppliesDistributionDetail Get(object id)
        {
            return distributionDetailRepository.Get(id.ToString());
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
                string hql = " DistributionId='" + s + "'";
                result = distributionDetailRepository.DeleteHql(hql);
            }
            return result;
        }

        /// <summary>
        /// 假删除操作,即更新状态
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string hql)
        {
            return distributionDetailRepository.Update(" DelFlag='1' where " + hql);            
        }
        public bool Save(SuppliesDistributionDetail entity, string no)
        {
            entity.Id = distributionDetailRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "DistributionDetail";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "DistributionDetail";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            if (entity.SaleDepartmentId != null && entity.SaleDepartmentId != "")
            {
                SystemRelation sre3 = new SystemRelation();
                sre3.RelationName = "SaleDepartment";
                sre3.ControllerName = "DistributionDetail";
                sre3.MId = entity.Id;
                sre3.CId = entity.SaleDepartmentId;
                sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre3);
                i++;
            }
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "SuppliesType";
            sre4.ControllerName = "DistributionDetail";
            sre4.MId = entity.Id;
            sre4.CId = entity.SuppliesTypeId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            result = distributionDetailRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesDistributionDetail entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='DistributionDetail'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "DistributionDetail";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "DistributionDetail";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;

            if (entity.SaleDepartmentId != null && entity.SaleDepartmentId != "")
            {
                SystemRelation sre3 = new SystemRelation();
                sre3.RelationName = "SaleDepartment";
                sre3.ControllerName = "DistributionDetail";
                sre3.MId = entity.Id;
                sre3.CId = entity.SaleDepartmentId;
                sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre3);
                i++;
            }
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "SuppliesType";
            sre4.ControllerName = "DistributionDetail";
            sre4.MId = entity.Id;
            sre4.CId = entity.SuppliesTypeId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            result = distributionDetailRepository.Update(entity);
            return result;
        }

        public IList<SuppliesDistributionDetail> LoadAll()
        {
            return distributionDetailRepository.LoadAll();
        }
        public IList<SuppliesDistributionDetail> LoadAll(string where)
        {
            string hql =
            @"select new SuppliesDistributionDetail(main.Id,main.Num,main.DistributionId,main.SuppliesTypeId,
            at.UnitName,at.SuppliesTypeName,ad.DistrictName as DistrictName,ac.CompanyName as CompanyName)
                    from SuppliesDistributionDetail main,ArchiveDistrict ad,ArchiveCompany ac,
                     ArchiveSuppliesType at
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id  and main.SuppliesTypeId=at.Id";
            hql += where;
            hql += " order by main.Id";
            return distributionDetailRepository.LoadAllbyHql(hql);
        }
        public IList<SuppliesDistributionDetail> LoadAll(string order, string where)
        {
            var lsDistributionDetail = distributionDetailRepository.LoadAllbyHql("from SuppliesDistributionDetail where " + where);
            var lsSuppliesType = suppliesTypeRepository.LoadAllbyHql("from ArchiveSuppliesType");
            var jsonlist = (from vlsDistributionDetail in lsDistributionDetail
                            join vlsSuppliesType in lsSuppliesType
                                on vlsDistributionDetail.SuppliesTypeId equals vlsSuppliesType.Id into joinvlsDistributionDetailType
                            from vlsDistributionDetailType in joinvlsDistributionDetailType.DefaultIfEmpty()
                            select new SuppliesDistributionDetail
                            {
                                Id = vlsDistributionDetail.Id,
                                Num = vlsDistributionDetail.Num,
                                DistributionId = vlsDistributionDetail.DistributionId,
                                SuppliesTypeId = vlsDistributionDetail.SuppliesTypeId,
                                SuppliesTypeName = vlsDistributionDetailType != null ? vlsDistributionDetailType.SuppliesTypeName : "",
                                UnitName = vlsDistributionDetailType != null ? vlsDistributionDetailType.UnitName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            return jsonlist;// distributionDetailRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from SuppliesDistributionDetail";
            IList<SuppliesDistributionDetail> ls = distributionDetailRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = distributionDetailRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
