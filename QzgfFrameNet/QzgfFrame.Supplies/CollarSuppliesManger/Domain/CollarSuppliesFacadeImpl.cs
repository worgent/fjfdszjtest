using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.CollarSuppliesManger.Models;
using QzgfFrame.Supplies.CollarSuppliesManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.CollarSuppliesManger.Domain
{
    public class CollarSuppliesFacadeImpl : CollarSuppliesFacade
    {
        private IRepository<SuppliesCollarSupplies> collarSuppliesRepository { set; get; }
        private IRepository<SuppliesCollar> collarRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveSuppliesType> suppliesTypeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesCollarSupplies Get(object id)
        {
            string hql =
               @"select new SuppliesCollarSupplies(main.Id,main.Num,main.ArrivalNum,main.CollarDate,main.CollarUser,main.Remark,
            main.CompanyId,main.DistrictId,main.SuppliesTypeId,ac.CompanyName,ad.DistrictName,ast.SuppliesTypeName,
            ast.UnitName,main.IsArrival)
            from SuppliesCollarSupplies main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveSuppliesType ast
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id and main.SuppliesTypeId=ast.Id and (main.DelFlag!=1 or main.DelFlag is null) ";

            return collarSuppliesRepository.GetbyHql(hql + " and main.Id='" + id.ToString() + "'");
        }
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = collarSuppliesRepository.Delete(s);
            }
            return result;
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
                string strsql = " CId='" + s + "' and RelationName='CollarSupplies'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='CollarSupplies'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = collarSuppliesRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='CollarSupplies'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = collarSuppliesRepository.Update(hql);
                }
            }
            return result;
        }
        public bool Save(SuppliesCollarSupplies entity, string no)
        {
            entity.Id = collarSuppliesRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='CollarSupplies'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "CollarSupplies";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "CollarSupplies";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SuppliesType";
            sre3.ControllerName = "CollarSupplies";
            sre3.MId = entity.Id;
            sre3.CId = entity.SuppliesTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);
            result = collarSuppliesRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesCollarSupplies entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='CollarSupplies'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "CollarSupplies";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "CollarSupplies";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SuppliesType";
            sre3.ControllerName = "CollarSupplies";
            sre3.MId = entity.Id;
            sre3.CId = entity.SuppliesTypeId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);

            result=collarSuppliesRepository.Update(entity);
            return result;
        }
        public bool UpdateNum(SuppliesCollarSupplies entity)
        {
            string strhql = "  ArrivalNum='" + entity.ArrivalNum + "',IsArrival='"+entity.IsArrival+"' where Id='" + entity.Id + "'";
            return this.collarSuppliesRepository.Update(strhql);
        }
        public IList<SuppliesCollarSupplies> LoadAll()
        {
            return collarSuppliesRepository.LoadAll();
        }
        public IList<SuppliesCollarSupplies> LoadAll(string order, string where)
        {
            return collarSuppliesRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
               @"select new SuppliesCollarSupplies(main.Id,main.Num,main.ArrivalNum,main.CollarDate,main.CollarUser,main.Remark,
            main.CompanyId,main.DistrictId,main.SuppliesTypeId,ac.CompanyName,ad.DistrictName,ast.SuppliesTypeName,
            ast.UnitName,main.IsArrival)
            from SuppliesCollarSupplies main,ArchiveDistrict ad,ArchiveCompany ac,ArchiveSuppliesType ast
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id and main.SuppliesTypeId=ast.Id and (main.DelFlag!=1 or main.DelFlag is null)";
            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;
            var lsCollarSupplies = collarSuppliesRepository.LoadAllbyHql(vSql);
            int strCount = collarSuppliesRepository.FindByPageCount(vSql);

            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsCollarSupplies) + @",""Total"":""" + strCount + @"""}";
            return json;
        }
        #region 领料操作
        
        public IList<SuppliesCollar> LoadAllCollar(string order, string where)
        {
            return collarRepository.LoadAll(order, where);
        }

        public bool SaveCollar(SuppliesCollar entity, string no)
        {
            entity.Id = collarSuppliesRepository.NewSequence("SYSTEM_MENU", no);
            return collarRepository.Save(entity);
        }
        #endregion
    }
}
