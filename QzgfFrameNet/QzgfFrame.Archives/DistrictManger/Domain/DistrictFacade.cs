using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.DistrictManger.Models;

namespace QzgfFrame.Archives.DistrictManger.Domain
{
    public interface  DistrictFacade
    {
        ArchiveDistrict Get(object id);
        ArchiveDistrict GetHql(string DistrictName);
        ArchiveDistrict GetLikeHql(string DistrictName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchiveDistrict entity);

        bool Update(ArchiveDistrict entity);

        IList<ArchiveDistrict> LoadAll();

        IList<ArchiveDistrict> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        string GetFather(string id, int hno);
        string GetCombobox(string id, int hno);
        IList<ArchiveDistrict> GetCitys(string id, int hno);
        IList<ArchiveDistrict> GetDistricts(string id, int hno);
        string GetSearch(string id, int hno, string obj);//含市级
        string GetPSearch(string id, int hno, string obj);//含空值
        string GetWSearch(string id, int hno, string obj);
    }
}
