using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.RepertotyManger.Models;
using QzgfFrame.Warehouse.RepertotyManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.System.UserManger.Domain;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.RepertotyManger.Domain
{
    public class RepertotyFacadeImpl : RepertotyFacade
    {
        private IRepository<WarehouseRepertoty> repertotyRepository { set; get; }
        private IRepository<ArchiveCompany> companyRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemUser> userRepository { set; get; }

        public WarehouseRepertoty Get(object id)
        {
            return repertotyRepository.Get(id.ToString());
        }
        public WarehouseRepertoty GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseRepertoty> Repertotys = repertotyRepository.LoadAll("Id", Hql);
            if (Repertotys.Count > 0)
                return Repertotys[0];
            else return null;
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
                result = repertotyRepository.Delete(s);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseRepertoty entity, string no)
        {
            entity.Id = repertotyRepository.NewSequence("SYSTEM_MENU", no);
            return repertotyRepository.Save(entity);
        }

        public bool Update(WarehouseRepertoty entity)
        {
            return repertotyRepository.Update(entity);
        }

        public IList<WarehouseRepertoty> LoadAll()
        {
            return repertotyRepository.LoadAll();
        }
        public IList<WarehouseRepertoty> LoadAll(string order, string where)
        {
            return repertotyRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            var lsRepertoty = repertotyRepository.LoadAllbyHql("from WarehouseRepertoty");
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var lsCompany = companyRepository.LoadAllbyHql("from ArchiveCompany");
            var jsonlist = (from vlsRepertoty in lsRepertoty
                            join vlsDistrict in lsDistrict
                                on vlsRepertoty.DistrictId equals vlsDistrict.Id into joinvlsRepertotyDistrict
                            from vlsRepertotyDistrict in joinvlsRepertotyDistrict.DefaultIfEmpty()

                            join vlsCompany in lsCompany
                                on vlsRepertoty.CompanyId equals vlsCompany.Id into joinvlsRepertotyCompany
                            from vlsRepertotyCompany in joinvlsRepertotyCompany.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsRepertoty.Id,
                                RepertotyName = vlsRepertoty.RepertotyName,
                                RepertotyNo = vlsRepertoty.RepertotyNo,
                                MUserName = vlsRepertoty.MUserId,
                                Tel = vlsRepertoty.Tel,
                                DistrictName = vlsRepertotyDistrict != null ? vlsRepertotyDistrict.DistrictName : "",
                                CompanyName = vlsRepertotyCompany != null ? vlsRepertotyCompany.CompanyName : ""
                            }
                          ).OrderBy(m => m.Id).ToArray();

            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + jsonlist.Length + @"""}";
            return json;
        }
        /// <summary>
        /// 用户信息下拉框,仓库信息在选择仓库管理员时使用
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetUserCombobox()
        {
            var lsUser = userRepository.LoadAllbyHql("from SystemUser");
            var jsonlist = (from a in lsUser
                            select new
                            {
                                text = a.Username,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
