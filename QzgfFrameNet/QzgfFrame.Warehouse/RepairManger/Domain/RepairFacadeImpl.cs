using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.RepairManger.Models;
using QzgfFrame.Warehouse.RepairManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.RepairManger.Domain
{
    public class RepairFacadeImpl : RepairFacade
    {
        private IRepository<WarehouseRepair> repairRepository { set; get; }

        public WarehouseRepair Get(object id)
        {
            return repairRepository.Get(id.ToString());
        }
        public WarehouseRepair GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseRepair> Repairs = repairRepository.LoadAll("Id", Hql);
            if (Repairs.Count > 0)
                return Repairs[0];
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
                result = repairRepository.Delete(s);
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseRepair entity, string no)
        {
            entity.Id = repairRepository.NewSequence("SYSTEM_MENU", no);
            return repairRepository.Save(entity);
        }

        public bool Update(WarehouseRepair entity)
        {
            return repairRepository.Update(entity);
        }

        public IList<WarehouseRepair> LoadAll()
        {
            return repairRepository.LoadAll();
        }
        public IList<WarehouseRepair> LoadAll(string order, string where)
        {
            return repairRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from WarehouseRepair";
            IList<WarehouseRepair> ls = repairRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = repairRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
