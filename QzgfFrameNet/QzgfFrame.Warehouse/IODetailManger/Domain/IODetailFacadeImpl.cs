using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.IODetailManger.Models;
using QzgfFrame.Warehouse.IODetailManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.IODetailManger.Domain
{
    public class IODetailFacadeImpl : IODetailFacade
    {
        private IRepository<WarehouseIODetail> ioDetailRepository { set; get; }

        public WarehouseIODetail Get(object id)
        {
            return ioDetailRepository.Get(id.ToString());
        }
        public WarehouseIODetail GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseIODetail> IODetais = ioDetailRepository.LoadAll("Id", Hql);
            if (IODetais.Count > 0)
                return IODetais[0];
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
                string hql = " IOListId='" + s + "'";
                result = ioDetailRepository.DeleteHql(hql);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseIODetail entity, string no)
        {
            entity.Id = ioDetailRepository.NewSequence("SYSTEM_MENU", no);
            return ioDetailRepository.Save(entity);
        }

        public bool Update(WarehouseIODetail entity)
        {
            return ioDetailRepository.Update(entity);
        }

        public IList<WarehouseIODetail> LoadAll()
        {
            return ioDetailRepository.LoadAll();
        }
        public IList<WarehouseIODetail> LoadAll(string where)
        {
            string hql =
            @"select new WarehouseIODetail(main.Id,main.Num,main.EquipState,main.EquipModelId,main.FactoryId,main.EquipTypeId,
            model.EquipModelName,factory.Abbrevia as FactoryName,etype.EquipTypeName ,main.UnitName,main.StockTypeId,main.Remark)
            from WarehouseIODetail main,ArchiveEquipModel model,ArchiveFactory factory,ArchiveEquipType etype 
            where main.EquipModelId=model.Id and main.FactoryId=factory.Id  and main.EquipTypeId=etype.Id";
            hql += where;
            hql += " order by main.Id";
            return ioDetailRepository.LoadAllbyHql(hql);
        }
        public IList<WarehouseIODetail> LoadAll(string order, string where)
        {
            return ioDetailRepository.LoadAll(order, where);
        }/// <summary>
        /// 加载视图信息
        /// </summary>
        /// <returns></returns>
        public string LoadAllViewIODetail(string ioListId)
        {
            var lsDetail = ioDetailRepository.LoadAllbyHql("from WarehouseIODetail where IOListId='" + ioListId + "'"); 
            string hql = "from WarehouseIODetail where IOListId='" + ioListId + "'";
            var jsonlist = (from vlsDetail in lsDetail
                            select new{Id=vlsDetail.Id,
                                       FactoryId = vlsDetail.FactoryId,
                                       EquipModelId=vlsDetail.EquipModelId,
                                       EquipName = vlsDetail.EquipTypeName,
                                       State = vlsDetail.State,
                                       Num = vlsDetail.Num,
                                       Remark = vlsDetail.Remark
                            }).OrderBy(m => m.Id).ToArray();
            string tmp = JSONHelper.ToJSON(jsonlist);
            tmp = @"{""Rows"":" + tmp + @",""Total"":""" + jsonlist.Length + @"""}";
            return tmp;
        }
        public string FindByPage(int pageNo, int pageSize,string gridsearch)
        {
            string hql = "from WarehouseIODetail where IOListId='" + gridsearch + "'";
            IList<WarehouseIODetail> ls = ioDetailRepository.FindByPage(pageNo, pageSize, hql);

            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = ioDetailRepository.FindByPageCount(hql); 
            if (ls == null)
            {
                WarehouseIODetail iodetail = new WarehouseIODetail();
                ls.Add(iodetail);
                recordCount = 1;
            }
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
