using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SaleDepartmentManger.Domain
{
    public class SaleDepartmentFacadeImpl : SaleDepartmentFacade
    {
        private IRepository<ArchiveSaleDepartment> saleDepartmentRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSaleDepartment Get(object id)
        {
            return saleDepartmentRepository.Get(id.ToString());
        }
        public ArchiveSaleDepartment GetHql(object SaleDepartmentName)
        {
            string Hql = "";
            if (SaleDepartmentName != null)
            {
                if (SaleDepartmentName.ToString() != "")
                    Hql = " SaleDepartmentName = '" + SaleDepartmentName.ToString() + "'";
            }
            IList<ArchiveSaleDepartment> entitys = saleDepartmentRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            }
            return null;
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id, out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='SaleDepartment'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SaleDepartment'";
                    result = relationRepository.DeleteHql(sql);
                    result = saleDepartmentRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SaleDepartment'";
                    result = relationRepository.DeleteHql(sql);
                    result = saleDepartmentRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveSaleDepartment entity)
        {
            entity.Id = saleDepartmentRepository.NewSequence("SYSTEM_MENU");
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "SaleDepartment";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "SaleDepartment";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = saleDepartmentRepository.Save(entity);

            return result;
        }

        public bool Update(ArchiveSaleDepartment entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='SaleDepartment'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "SaleDepartment";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "SaleDepartment";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = saleDepartmentRepository.Update(entity);
            return result;
        }

        public IList<ArchiveSaleDepartment> LoadAll()
        {
            return saleDepartmentRepository.LoadAll();
        }
        public IList<ArchiveSaleDepartment> LoadAll(string order, string where)
        {
            return saleDepartmentRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql =
            @"select new ArchiveSaleDepartment(main.Id,main.SaleDepartmentName,main.CompanyId,main.DistrictId,
            ac.CompanyName,ad.DistrictName)
                      from ArchiveSaleDepartment main,ArchiveDistrict ad,ArchiveCompany ac
            where main.DistrictId=ad.Id and main.CompanyId=ac.Id ";
            hql += gridsearch;
            hql += " order by main." + sortname + " " + sortorder;

            IList<ArchiveSaleDepartment> lsDistribution = saleDepartmentRepository.FindByPage(pageNo, pageSize, hql);
            int recordCount = saleDepartmentRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + JSONHelper.ToJSON(lsDistribution) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /**
        public string FindByPage(int pageNo, int pageSize)
        {
            var lsSaleDepartment = saleDepartmentRepository.LoadAllbyHql("from ArchiveSaleDepartment");
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            var jsonlist = (from vlsSaleDepartment in lsSaleDepartment
                            join vlsDistrict in lsDistrict
                                on vlsSaleDepartment.DistrictId equals vlsDistrict.Id into joinvlsSaleDepartmentDistrict
                            from vlsSaleDepartmentDistrict in joinvlsSaleDepartmentDistrict.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsSaleDepartment.CompanyId equals vlsCompany.Id into joinvlsSaleDepartmentCompany
                            from vlsSaleDepartmentCompany in joinvlsSaleDepartmentCompany.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsSaleDepartment.Id,
                                SaleDepartmentName = vlsSaleDepartment.SaleDepartmentName,
                                DistrictName = vlsSaleDepartmentDistrict != null ? vlsSaleDepartmentDistrict.DistrictName : "",
                                CompanyName = vlsSaleDepartmentCompany != null ? vlsSaleDepartmentCompany.CompanyName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();

            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + jsonlist.Length + @"""}";
            return json;
        }**/
        /// <summary>
        /// 营业部下拉框
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox(string hql)
        {
            var ls = LoadAll("Id",hql); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.SaleDepartmentName,
                                id = a.Id,
                                cid=a.CompanyId,
                                did=a.DistrictId
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
