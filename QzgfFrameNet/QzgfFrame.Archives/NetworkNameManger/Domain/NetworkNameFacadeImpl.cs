using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.NetworkNameManger.Models;
using QzgfFrame.Archives.NetworkNameManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.NetworkNameManger.Domain
{
    public class NetworkNameFacadeImpl:NetworkNameFacade
    {
        private IRepository<ArchiveNetworkName> networkNameRepository { set; get; }
        private IRepository<ArchiveDistrict> districtRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveNetworkName Get(object id)
        {
            return networkNameRepository.Get(id.ToString());
        }
        public ArchiveNetworkName Get(string order, string where)
        {
            IList<ArchiveNetworkName> bizTypes= networkNameRepository.LoadAll(order, where);
            if (bizTypes.Count > 0)
                return bizTypes[0];
            else
                return null;
        }
        public ArchiveNetworkName GetHql(string networkNameName)
        {
            string Hql = " NetworkNameName like '%" + networkNameName + "%'";
            IList<ArchiveNetworkName> citys = networkNameRepository.LoadAll("Id", Hql);
            if (citys != null)
            {
                if (citys.Count > 0)
                    return citys[0];
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
                string strsql = " CId='" + s + "' and RelationName='NetworkName'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='NetworkName'";
                    result = relationRepository.DeleteHql(sql);
                    result = networkNameRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='NetworkName'";
                    result = relationRepository.DeleteHql(sql);
                    result = networkNameRepository.Delete(s);
                }
                else
                    DelFlag = true;
            }
            return result;
        }

        public bool Save(ArchiveNetworkName entity)
        {
            entity.Id = networkNameRepository.NewSequence("SYSTEM_MENU");
            return networkNameRepository.Save(entity);
        }

        public bool Update(ArchiveNetworkName entity)
        {
            return networkNameRepository.Update(entity);
        }

        public IList<ArchiveNetworkName> LoadAll()
        {
            return networkNameRepository.LoadAll();
        }
        public IList<ArchiveNetworkName> LoadAll(string order, string where)
        {
            return networkNameRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            var lsNetworkName = networkNameRepository.LoadAllbyHql("from ArchiveNetworkName");
            var lsDistrict = districtRepository.LoadAllbyHql("from ArchiveDistrict");
            var jsonlist = (from vlsNetworkName in lsNetworkName
                            join vlsDistrict in lsDistrict
                                on vlsNetworkName.DistrictId equals vlsDistrict.Id into joinvlsNetworkNameDistrict
                            from vlsNetworkNameDistrict in joinvlsNetworkNameDistrict.DefaultIfEmpty()

                            select new
                            {
                                Id = vlsNetworkName.Id,
                                NetworkName = vlsNetworkName.NetworkName,
                                NetworkNo = vlsNetworkName.NetworkNo,
                                DistrictName = vlsNetworkNameDistrict != null ? vlsNetworkNameDistrict.DistrictName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();

            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + jsonlist.Length + @"""}";
            return json;
        }
    }
}
