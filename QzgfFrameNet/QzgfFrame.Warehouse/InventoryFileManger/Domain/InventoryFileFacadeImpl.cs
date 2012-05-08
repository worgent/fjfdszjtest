using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Warehouse.InventoryFileManger.Models;
using QzgfFrame.Warehouse.InventoryFileManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Warehouse.InventoryFileManger.Domain
{
    public class InventoryFileFacadeImpl : InventoryFileFacade
    {
        private IRepository<WarehouseInventoryFile> inventoryFileRepository { set; get; }

        public WarehouseInventoryFile Get(object id)
        {
            return inventoryFileRepository.Get(id.ToString());
        }
        public WarehouseInventoryFile GetHql(string fileName)
        {
            string Hql = " LedgerFileName ='" + fileName.Trim() + "'";
            IList<WarehouseInventoryFile> inventoryFiles = inventoryFileRepository.LoadAll("Id", Hql);
            if (inventoryFiles.Count > 0)
                return inventoryFiles[0];
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
                WarehouseInventoryFile entity = inventoryFileRepository.Get(s);
                string strsavePath =HttpContext.Current.Request.MapPath(savePath);
                string filepath = strsavePath + entity.LedgerFileName;
                result = inventoryFileRepository.Delete(s);
                File.Delete(filepath);            
            }
            if(result==false)
                throw new Exception("操作失败!!");
            return result;
        }

        public bool Save(WarehouseInventoryFile entity, string no)
        {
            entity.Id = inventoryFileRepository.NewSequence("SYSTEM_MENU", no);
            return inventoryFileRepository.Save(entity);
        }

        public bool Update(WarehouseInventoryFile entity)
        {
            return inventoryFileRepository.Update(entity);
        }

        public IList<WarehouseInventoryFile> LoadAll()
        {
            return inventoryFileRepository.LoadAll();
        }
        public IList<WarehouseInventoryFile> LoadAll(string order, string where)
        {
            return inventoryFileRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string hql = "from WarehouseInventoryFile main where 1=1 ";
            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;
            IList<WarehouseInventoryFile> ls = inventoryFileRepository.FindByPage(pageNo, pageSize, vSql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = inventoryFileRepository.FindByPageCount(vSql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
