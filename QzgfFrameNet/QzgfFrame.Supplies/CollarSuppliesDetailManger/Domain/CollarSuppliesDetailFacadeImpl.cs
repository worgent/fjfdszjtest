using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Models;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Supplies.CollarSuppliesDetailManger.Domain
{
    public class CollarSuppliesDetailFacadeImpl : CollarSuppliesDetailFacade
    {
        private IRepository<SuppliesCollarSuppliesDetail> collarSuppliesDetailRepository { set; get; }
        private IRepository<ArchiveSuppliesType> suppliesTypeRepository { set; get; }

        public SuppliesCollarSuppliesDetail Get(object id)
        {
            return collarSuppliesDetailRepository.Get(id.ToString());
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
                result = collarSuppliesDetailRepository.Delete(s);
            }
            return result;
        }

        public bool Save(SuppliesCollarSuppliesDetail entity, string no)
        {
            entity.Id = collarSuppliesDetailRepository.NewSequence("SYSTEM_MENU",no);
            return collarSuppliesDetailRepository.Save(entity);
        }

        public bool Update(SuppliesCollarSuppliesDetail entity)
        {
            bool result = false;
            
            result = collarSuppliesDetailRepository.Update(entity);
            return result;
        }

        public IList<SuppliesCollarSuppliesDetail> LoadAll()
        {
            return collarSuppliesDetailRepository.LoadAll();
        }

        public IList<SuppliesCollarSuppliesDetail> LoadAll(string where)
        {
            string hql =
            @"select new SuppliesCollarSuppliesDetail(main.Id,main.Num,main.SuppliesTypeId,ast.UnitName,ast.SuppliesTypeName)
                      from SuppliesCollarSuppliesDetail main,ArchiveSuppliesType ast
            where main.SuppliesTypeId=ast.Id and (main.DelFlag!=1 or main.DelFlag is null)";
            hql += where;
            hql += " order by main.Id";
            return collarSuppliesDetailRepository.LoadAllbyHql(hql);
        }
        public IList<SuppliesCollarSuppliesDetail> LoadAll(string order, string where)
        {
            return collarSuppliesDetailRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            var lsCollarSuppliesDetail = collarSuppliesDetailRepository.LoadAllbyHql("from SuppliesCollarSuppliesDetail main where (main.DelFlag!=1 or main.DelFlag is null) ");
            var lsSuppliesType = suppliesTypeRepository.LoadAllbyHql("from ArchiveSuppliesType");
            
            var jsonlist = (from vlsCollarSuppliesDetail in lsCollarSuppliesDetail
                            join vlsSuppliesType in lsSuppliesType
                                on vlsCollarSuppliesDetail.SuppliesTypeId equals vlsSuppliesType.Id into joinvlsCollarSuppliesDetailType
                            from vlsCollarSuppliesDetailType in joinvlsCollarSuppliesDetailType.DefaultIfEmpty()
                            select new
                            {
                                Id = vlsCollarSuppliesDetail.Id,
                                SuppliesTypeId = vlsCollarSuppliesDetail.SuppliesTypeId,
                                Num = vlsCollarSuppliesDetail.Num,
                                SuppliesTypeName = vlsCollarSuppliesDetailType != null ? vlsCollarSuppliesDetailType.SuppliesTypeName : "",
                                UnitName = vlsCollarSuppliesDetailType != null ? vlsCollarSuppliesDetailType.UnitName : ""
                            }
                           ).OrderBy(m => m.Id).ToArray();

            string json = @"{""Rows"":" + JSONHelper.ToJSON(jsonlist) + @",""Total"":""" + jsonlist.Length + @"""}";
            return json;
        }
    }
}
