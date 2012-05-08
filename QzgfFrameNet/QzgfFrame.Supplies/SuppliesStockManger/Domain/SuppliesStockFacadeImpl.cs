using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Supplies.SuppliesStockManger.Domain
{
    public class SuppliesStockFacadeImpl : SuppliesStockFacade
    {
        private IRepository<SuppliesSuppliesStock> suppliesStockRepository { set; get; }
        private IRepository<ArchiveSuppliesType> suppliesTypeRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveSaleDepartment> saleDepartmentRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public SuppliesSuppliesStock Get(object id)
        {
            return suppliesStockRepository.Get(id.ToString());
        }
        public SuppliesSuppliesStock GetHql(string hql)
        {
            string strhql =
            @"select new SuppliesSuppliesStock(main.Id,main.CompanyId,main.DistrictId,main.SuppliesTypeId,
            main.State,main.Num) from SuppliesSuppliesStock main where 1=1 ";
            strhql += hql;
            return suppliesStockRepository.GetbyHql(strhql);
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
                result = suppliesStockRepository.Delete(s);
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
                string strsql = " CId='" + s + "' and RelationName='SuppliesStock'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SuppliesStock'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = suppliesStockRepository.Update(hql);

                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SuppliesStock'";
                    result = relationRepository.DeleteHql(sql);
                    string hql = " DelFlag='1' where Id='" + s + "'";
                    result = suppliesStockRepository.Update(hql);
                }
            }
            return result;
        }
        public bool Save(SuppliesSuppliesStock entity, string no)
        {
            entity.Id = suppliesStockRepository.NewSequence("SYSTEM_MENU",no);
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "SuppliesStock";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "SuppliesStock";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            if (entity.SaleDepartmentId != null && entity.SaleDepartmentId != "")
            {
                SystemRelation sre3 = new SystemRelation();
                sre3.RelationName = "SaleDepartment";
                sre3.ControllerName = "SuppliesStock";
                sre3.MId = entity.Id;
                sre3.CId = entity.SaleDepartmentId;
                sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
                result = relationRepository.Save(sre3);
                i++;
            }
            SystemRelation sre4 = new SystemRelation();
            sre4.RelationName = "SuppliesType";
            sre4.ControllerName = "SuppliesStock";
            sre4.MId = entity.Id;
            sre4.CId = entity.SuppliesTypeId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            result = suppliesStockRepository.Save(entity);
            return result;
        }

        public bool Update(SuppliesSuppliesStock entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='SuppliesStock'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "SuppliesStock";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "SuppliesStock";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            i++;
            SystemRelation sre3 = new SystemRelation();
            sre3.RelationName = "SaleDepartment";
            sre3.ControllerName = "SuppliesStock";
            sre3.MId = entity.Id;
            sre3.CId = entity.SaleDepartmentId;
            sre3.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre3);

            i++;
            SystemRelation sre4= new SystemRelation();
            sre4.RelationName = "SuppliesType";
            sre4.ControllerName = "SuppliesStock";
            sre4.MId = entity.Id;
            sre4.CId = entity.SuppliesTypeId;
            sre4.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre4);
            result = suppliesStockRepository.Update(entity);
            return result;
        }

        public bool UpdateNum(SuppliesSuppliesStock entity)
        {
            string strhql = "  Num='" + entity.Num + "' where Id='" + entity.Id + "'";
            return this.suppliesStockRepository.Update(strhql);
        }
        public IList<SuppliesSuppliesStock> LoadAll()
        {
            return suppliesStockRepository.LoadAll();
        }
        public IList<SuppliesSuppliesStock> LoadAll(string order, string where)
        {
            return suppliesStockRepository.LoadAll(order, where);
        }
        public string FindSelByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new SuppliesSuppliesStock(main.Id,main.SuppliesTypeId,ast.UnitName,ast.SuppliesTypeName,main.Num,main.DistrictId,main.SaleDepartmentId)
              from SuppliesSuppliesStock main,ArchiveSuppliesType ast
              where main.SuppliesTypeId=ast.Id ";
            string vSql = hql + gridsearch;
            vSql += " order by main." + sortname + " " + sortorder;
            IList<SuppliesSuppliesStock> lsSuppliesStock = suppliesStockRepository.FindByPage(pageNo, pageSize, vSql);
            IList<ArchiveDistrict> lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            IList<ArchiveSaleDepartment> lsSaleDepartment = saleDepartmentRepository.LoadAllbyHql("from ArchiveSaleDepartment");
            var jsonlist = (from vlsSuppliesStock in lsSuppliesStock
                            join vlsSaleDepartment in lsSaleDepartment
                                on vlsSuppliesStock.SaleDepartmentId equals vlsSaleDepartment.Id into joinvlsSuppliesStockSaleDepartment
                            from vlsSuppliesStockSaleDepartment in joinvlsSuppliesStockSaleDepartment.DefaultIfEmpty()

                            join vlsDistrict in lsDistrict
                                 on vlsSuppliesStock.DistrictId equals vlsDistrict.Id into joinvlsSuppliesStockDistrict
                            from vlsSuppliesStockDistrict in joinvlsSuppliesStockDistrict.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsSuppliesStock.Id,
                                SuppliesTypeId=vlsSuppliesStock.SuppliesTypeId,
                                UnitName = vlsSuppliesStock.UnitName,
                                SuppliesTypeName = vlsSuppliesStock.SuppliesTypeName,
                                Num = vlsSuppliesStock.Num,
                                DistrictId = vlsSuppliesStock.DistrictId,
                                SaleDepartmentName = vlsSuppliesStockSaleDepartment != null ? vlsSuppliesStockSaleDepartment.SaleDepartmentName : "",
                                DistrictName = vlsSuppliesStockDistrict != null ? vlsSuppliesStockDistrict.DistrictName : ""
                            }
                           ).OrderBy(m => m.SuppliesTypeId).ToArray();

            int recordCount = suppliesStockRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 按耗材类型分类获取库存
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select main.SuppliesTypeId ,Sum(main.Num) as Count,
            Sum(case when (main.State='0') then main.Num else 0 end) as ZNum,
            Sum(case when (main.State='1') then main.Num else 0 end) as DNum
            from SuppliesSuppliesStock main
            where  1=1";
            string vSql = hql + gridsearch;
            vSql += @" group by main." + sortname; 
            vSql += @" order by main." + sortname + " " + sortorder;
            IList<object[]> ls = suppliesStockRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<SuppliesSuppliesStock> lsSuppliesStock = new List<SuppliesSuppliesStock>();
            IList mapList = new ArrayList(); 
            for (int i = 0; i < ls.Count; i++)
            {
                lsSuppliesStock.Add(new SuppliesSuppliesStock
                {
                    SuppliesTypeId = ExtensionMethods.ToStr(ls[i][0]),
                    Count =ExtensionMethods.ToInt(ls[i][1]),
                    ZNum = ExtensionMethods.ToInt(ls[i][2]),
                    DNum = ExtensionMethods.ToInt(ls[i][3])
                });
            }
            IList<ArchiveCompany> lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            IList<ArchiveSuppliesType> lsSuppliesType = suppliesTypeRepository.LoadAllbyHql("from ArchiveSuppliesType");
            IList<ArchiveDistrict> lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var jsonlist = (from vlsSuppliesStock in lsSuppliesStock
                            join vlsSuppliesType in lsSuppliesType
                                on vlsSuppliesStock.SuppliesTypeId equals vlsSuppliesType.Id into joinvlsSuppliesStockSuppliesType
                            from vlsSuppliesStockSuppliesType in joinvlsSuppliesStockSuppliesType.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsSuppliesStock.CompanyId equals vlsCompany.Id into joinvlsSuppliesStockCompany
                            from vlsSuppliesStockCompany in joinvlsSuppliesStockCompany.DefaultIfEmpty()

                            join vlsDistrict in lsDistrict
                                 on vlsSuppliesStock.DistrictId equals vlsDistrict.Id into joinvlsSuppliesStockDistrict
                            from vlsSuppliesStockDistrict in joinvlsSuppliesStockDistrict.DefaultIfEmpty()

                            select new
                            {
                                SuppliesTypeId = vlsSuppliesStock.SuppliesTypeId,
                                Count = vlsSuppliesStock.Count,
                                ZNum = vlsSuppliesStock.ZNum,
                                DNum = vlsSuppliesStock.DNum,
                                SuppliesTypeName = vlsSuppliesStockSuppliesType != null ? vlsSuppliesStockSuppliesType.SuppliesTypeName : "",
                                CompanyName = vlsSuppliesStockCompany != null ? vlsSuppliesStockCompany.CompanyName : "",
                                DistrictName = vlsSuppliesStockDistrict != null ? vlsSuppliesStockDistrict.DistrictName : ""
                            }
                           ).OrderBy(m => m.SuppliesTypeId).ToArray();

            int recordCount = suppliesStockRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        /// <summary>
        /// 按区县、公司、耗材类型分类获取库存
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindQxByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select main.CompanyId,ac.CompanyName,main.DistrictId,ad.DistrictName,main.SuppliesTypeId ,
            ast.SuppliesTypeName,Sum(main.Num) as Count,
            Sum(case when (main.State='0') then main.Num else 0 end) as ZNum,
            Sum(case when (main.State='1') then main.Num else 0 end) as DNum,
            Sum(case when (main.State='0' and  (main.SaleDepartmentId='' or main.SaleDepartmentId is null)) then main.Num else 0 end) as QNum
            from SuppliesSuppliesStock main,ArchiveCompany ac,ArchiveDistrict ad,ArchiveSuppliesType ast
            where main.CompanyId=ac.Id and main.DistrictId=ad.Id and main.SuppliesTypeId=ast.Id ";
            string vSql = hql + gridsearch;
            vSql += @" group by main.CompanyId,ac.CompanyName,main.DistrictId,ad.DistrictName,ast.SuppliesTypeName,main.SuppliesTypeId";
            vSql += @" order by main." + sortname + " " + sortorder;
            IList<object[]> ls = suppliesStockRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList mapList = new ArrayList();
            for (int i = 0; i < ls.Count; i++)
            {
                mapList.Add(new 
                {
                    CompanyId = ExtensionMethods.ToStr(ls[i][0]),
                    CompanyName = ExtensionMethods.ToStr(ls[i][1]),
                    DistrictId = ExtensionMethods.ToStr(ls[i][2]),
                    DistrictName = ExtensionMethods.ToStr(ls[i][3]),
                    SuppliesTypeId = ExtensionMethods.ToStr(ls[i][4]),
                    SuppliesTypeName = ExtensionMethods.ToStr(ls[i][5]),
                    Count = ExtensionMethods.ToInt(ls[i][6]),
                    ZNum = ExtensionMethods.ToInt(ls[i][7]),
                    DNum = ExtensionMethods.ToInt(ls[i][8]),
                    QNum = ExtensionMethods.ToInt(ls[i][9])
                });
            }
            int recordCount = suppliesStockRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(mapList) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 按营业部、耗材类型分类获取库存
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="sortname"></param>
        /// <param name="sortorder"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        public string FindYwByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select main.CompanyId,ac.CompanyName,main.DistrictId,ad.DistrictName,main.SaleDepartmentId,asd.SaleDepartmentName,
            main.SuppliesTypeId ,ast.SuppliesTypeName,Sum(main.Num) as Count,
            Sum(case when (main.State='0') then main.Num else 0 end) as ZNum,
            Sum(case when (main.State='1') then main.Num else 0 end) as DNum
            from SuppliesSuppliesStock main,ArchiveCompany ac,ArchiveDistrict ad,ArchiveSuppliesType ast,ArchiveSaleDepartment asd
            where main.CompanyId=ac.Id and main.DistrictId=ad.Id and main.SuppliesTypeId=ast.Id and main.SaleDepartmentId=asd.Id ";
            string vSql = hql + gridsearch;
            vSql += @" group by main.CompanyId,ac.CompanyName,main.DistrictId,ad.DistrictName,main.SaleDepartmentId,
                       asd.SaleDepartmentName,ast.SuppliesTypeName,main.SuppliesTypeId";
            vSql += @" order by main." + sortname + " " + sortorder;
            IList<object[]> ls = suppliesStockRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList mapList = new ArrayList();
            for (int i = 0; i < ls.Count; i++)
            {
                mapList.Add(new
                {
                    CompanyId = ExtensionMethods.ToStr(ls[i][0]),
                    CompanyName = ExtensionMethods.ToStr(ls[i][1]),
                    DistrictId = ExtensionMethods.ToStr(ls[i][2]),
                    DistrictName = ExtensionMethods.ToStr(ls[i][3]),
                    SaleDepartmentId = ExtensionMethods.ToStr(ls[i][4]),
                    SaleDepartmentName = ExtensionMethods.ToStr(ls[i][5]),
                    SuppliesTypeId = ExtensionMethods.ToStr(ls[i][6]),
                    SuppliesTypeName = ExtensionMethods.ToStr(ls[i][7]),
                    Count = ExtensionMethods.ToInt(ls[i][8]),
                    ZNum = ExtensionMethods.ToInt(ls[i][9]),
                    DNum = ExtensionMethods.ToInt(ls[i][10])
                });
            }
            int recordCount = suppliesStockRepository.FindByPageLinkCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(mapList) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /**
         *  public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
           @"select new SuppliesSuppliesStock(main.SuppliesTypeId ,ast.SuppliesTypeName ,Sum(main.Num) as Count)
            from SuppliesSuppliesStock main,ArchiveSuppliesType ast
            where 1=1 and main.SuppliesTypeId=ast.Id";
            string vSql = hql + gridsearch;
            vSql += @" group by main." + sortname; 
            vSql += @" order by main." + sortname + " " + sortorder;
            IList<SuppliesSuppliesStock> lsSuppliesStock = suppliesStockRepository.FindByPage(pageNo, pageSize, vSql);
            IList<ArchiveCompany> lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            IList<ArchiveSuppliesType> lsSuppliesType = suppliesTypeRepository.LoadAllbyHql("from ArchiveSuppliesType");
            IList<ArchiveDistrict> lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var jsonlist = (from vlsSuppliesStock in lsSuppliesStock
                            join vlsSuppliesType in lsSuppliesType
                                on vlsSuppliesStock.SuppliesTypeId equals vlsSuppliesType.Id into joinvlsSuppliesStockSuppliesType
                            from vlsSuppliesStockSuppliesType in joinvlsSuppliesStockSuppliesType.DefaultIfEmpty()

                            select new
                            {
                                SuppliesTypeId = vlsSuppliesStock.SuppliesTypeId,
                                Count = vlsSuppliesStock.Count,
                                ZNum = vlsSuppliesStock.ZNum,
                                DNum = vlsSuppliesStock.DNum,
                                SuppliesTypeName = vlsSuppliesStockSuppliesType != null ? vlsSuppliesStockSuppliesType.SuppliesTypeName : ""
                            }
                           ).OrderBy(m => m.SuppliesTypeId).ToArray();

            int recordCount = suppliesStockRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
         **/

    }
}
