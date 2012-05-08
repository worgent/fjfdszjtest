using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.IOListManger.Models;
using QzgfFrame.Warehouse.IOListManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.IOListManger.Domain
{
    public class IOListFacadeImpl : IOListFacade
    {
        private IRepository<WarehouseIOList> ioListRepository { set; get; }

        public WarehouseIOList Get(object id)
        {
            return ioListRepository.Get(id.ToString());
        }
        public WarehouseIOList GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseIOList> IOLists = ioListRepository.LoadAll("Id", Hql);
            if (IOLists.Count > 0)
                return IOLists[0];
            else return null;
        }
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id, string savePath)
        {
            string[] idarr = id.Split(',');
            bool result = false;

            foreach (var s in idarr)
            {
                result = ioListRepository.Delete(s);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseIOList entity, string no)
        {
            entity.Id = ioListRepository.NewSequence("SYSTEM_MENU", no);
            return ioListRepository.Save(entity);
        }

        public bool Update(WarehouseIOList entity)
        {
            return ioListRepository.Update(entity);
        }

        public IList<WarehouseIOList> LoadAll()
        {
            return ioListRepository.LoadAll();
        }
        public IList<WarehouseIOList> LoadAll(string order, string where)
        {
            return ioListRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from WarehouseIOList";
            IList<WarehouseIOList> ls = ioListRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = ioListRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
