using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.NatureManger.Domain;
using QzgfFrame.Archives.NatureManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Archives.GridManger.Domain
{
    public class GridFacadeImpl : GridFacade
    {
        private IRepository<ArchiveGrid> gridRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<ArchiveNature> natureRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveGrid Get(object id)
        {
            return gridRepository.Get(id.ToString());
        }
        public ArchiveGrid GetHql(string GridName)
        {
            string Hql = " GridName ='" + GridName + "'";
            IList<ArchiveGrid> entitys = gridRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            }
            return null;
        }
        public ArchiveGrid GetHql(string GridName, string strhql)
        {
            string Hql = " GridName = '" + GridName + "'";
            Hql += strhql;
            IList<ArchiveGrid> entitys = gridRepository.LoadAll("Id", Hql);
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
                string strsql = " CId='" + s + "' and RelationName='Grid'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Grid'";
                    result = relationRepository.DeleteHql(sql);
                    result = gridRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Grid'";
                    result = relationRepository.DeleteHql(sql);
                    result = gridRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveGrid entity)
        {
            entity.Id = gridRepository.NewSequence("SYSTEM_MENU");
            bool result = false;
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Grid";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION",i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Grid";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = gridRepository.Save(entity);
            return result;
        }

        public bool Update(ArchiveGrid entity)
        {
            bool result = false;
            //删除关系
            result = relationRepository.DeleteHql(" MId='" + entity.Id + "' and ControllerName='Grid'");
            //添加关系信息
            int i = 0;
            SystemRelation sre1 = new SystemRelation();
            sre1.RelationName = "Company";
            sre1.ControllerName = "Grid";
            sre1.MId = entity.Id;
            sre1.CId = entity.CompanyId;
            sre1.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre1);
            i++;
            SystemRelation sre2 = new SystemRelation();
            sre2.RelationName = "District";
            sre2.ControllerName = "Grid";
            sre2.MId = entity.Id;
            sre2.CId = entity.DistrictId;
            sre2.Id = relationRepository.NewSequence("SYSTEM_RELATION", i.ToString());
            result = relationRepository.Save(sre2);
            result = gridRepository.Update(entity);
            return result;
        }

        public IList<ArchiveGrid> LoadAll()
        {
            return gridRepository.LoadAll();
        }
        public IList<ArchiveGrid> LoadAll(string order, string where)
        {
            return gridRepository.LoadAll(order, where);
        }
        public IList<ArchiveGrid> LoadLAll(string hql)
        {
            if (hql != "")
                hql = " from ArchiveGrid where " + hql;
            else
                hql = " from ArchiveGrid ";
            hql += @" order by Id ";
            var lsGrid = gridRepository.LoadAllbyHql(hql);
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            IList<ArchiveGrid> jsonlist = (from vlsGrid in lsGrid
                            join vlsDistrict in lsDistrict
                                on vlsGrid.DistrictId equals vlsDistrict.Id into joinvlsGridDistrict
                            from vlsGridDistrict in joinvlsGridDistrict.DefaultIfEmpty()

                            select new ArchiveGrid
                            {
                                Id = vlsGrid.Id,
                                GridName = vlsGrid.GridName,
                                GridCode = vlsGrid.GridCode,
                                CompanyId = vlsGrid.CompanyId,
                                DistrictId = vlsGrid.DistrictId,
                                CityId = vlsGridDistrict != null ? vlsGridDistrict.ParentId : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            return jsonlist;
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            var lsGrid = gridRepository.FindByPage(pageNo, pageSize, "from ArchiveGrid");
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsNature = natureRepository.LoadAllbyHql("from ArchiveNature");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");

            var jsonlist = (from vlsGrid in lsGrid
                            join vlsDistrict in lsDistrict
                                on vlsGrid.DistrictId equals vlsDistrict.Id into joinvlsGridDistrict
                            from vlsGridDistrict in joinvlsGridDistrict.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsGrid.CompanyId equals vlsCompany.Id into joinvlsGridCompany
                            from vlsGridCompany in joinvlsGridCompany.DefaultIfEmpty()

                            join vlsNature in lsNature
                                on vlsGrid.NatureId equals vlsNature.Id into joinvlsGridNature
                            from vlsGridNature in joinvlsGridNature.DefaultIfEmpty()

                            join vlsOffice in lsDistrict
                                on vlsGrid.OfficeId equals vlsOffice.Id into joinvlsGridOffice
                            from vlsGridOffice in joinvlsGridOffice.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsGrid.Id,
                                GridName = vlsGrid.GridName,
                                GridCode = vlsGrid.GridCode,
                                Partners = vlsGrid.Partners,
                                GridArea = vlsGrid.GridArea,  
                                DistrictName = vlsGridDistrict != null ? vlsGridDistrict.DistrictName : "",
                                CompanyName = vlsGridCompany != null ? vlsGridCompany.CompanyName : "",
                                Nature = vlsGridNature != null ? vlsGridNature.NatureName : "",
                                OfficeName = vlsGridOffice != null ? vlsGridOffice.DistrictName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();
            int recordCount = gridRepository.FindByPageCount("from ArchiveGrid");
            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        public string GetOSZHql(string GridName)
        {
            string[] idarr = GridName.Split('|');
            string restr = "";
            foreach (var s in idarr)
            {
                string Hql = " GridName like '%" + s + "%'";
                IList<ArchiveGrid> entitys = gridRepository.LoadAll("Id", Hql);
                if (entitys != null)
                {
                    if (entitys.Count > 0)
                    {
                        if(restr=="")
                            restr = entitys[0].Id;
                        else
                           restr +=";"+ entitys[0].Id;
                    }
                }
            }
            return restr;
        }

        /// <summary>
        /// 网格信息下拉框,集团客户在选择网格时使用
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox()
        {
            var ls = LoadAll(); //state!=0
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.GridName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
