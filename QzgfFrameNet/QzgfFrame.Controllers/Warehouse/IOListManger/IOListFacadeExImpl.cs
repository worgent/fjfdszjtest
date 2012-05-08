using System;
using System.IO;
using System.Web;
using System.Data;
using System.Data.Common;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.Warehouse.IOListManger.Domain;
using QzgfFrame.Warehouse.IOListManger.Models;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Warehouse.IODetailManger.Models;
using QzgfFrame.Warehouse.IODetailManger.Domain;
using QzgfFrame.Warehouse.StockManger.Domain;
using QzgfFrame.Warehouse.StockManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Warehouse.IOListManger
{
    public class IOListFacadeExImpl : IOListFacadeEx
    {
        private IOListFacade ioListFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipModelFacade equipModelFacade { set; get; }
        private IODetailFacade ioDetailFacade { set; get; }
        private StockFacade stockFacade { set; get; }

        public bool Save(IOList entity)
        {
            bool result = false;
            result = this.ioListFacade.Save(entity.warehouseIOList,"0");
            WarehouseIODetail[] IODetails = (WarehouseIODetail[])JavaScriptConvert.DeserializeObject(entity.ioDetails, typeof(WarehouseIODetail[]));
            int j = 0;
            foreach (WarehouseIODetail ioDetail in IODetails)
            {
                ioDetail.IOListId = entity.warehouseIOList.Id;
                result = ioDetailFacade.Save(ioDetail, j.ToString());
                string hql = " and main.CompanyId='" + entity.warehouseIOList.ICompanyId + "'";
                hql += " and main.DistrictId='" + entity.warehouseIOList.IDistrictId + "'";
                hql += " and main.State='0'";
                hql += " and main.EquipModelId='" + ioDetail.EquipModelId + "'";

                WarehouseStock Stock =  stockFacade.GetHql(hql);
                if (Stock == null)
                {
                    Stock = new WarehouseStock();
                    Stock.State = "0";
                    Stock.Num = ioDetail.Num;
                    Stock.UnitName = ioDetail.UnitName;
                    Stock.CompanyId = entity.warehouseIOList.ICompanyId;
                    Stock.DistrictId = entity.warehouseIOList.IDistrictId;
                    Stock.EquipModelId = ioDetail.EquipModelId;
                    Stock.EquipModelName = ioDetail.EquipModelName;
                    result = stockFacade.Save(Stock, j.ToString());
                }
                else
                {
                    Stock.Num += ioDetail.Num;
                    result = stockFacade.UpdateNum(Stock);
                }
                j++;
            }
            return result;
        }
        public bool Update(IOList entity)
        {
            bool result = false;
            result = this.ioListFacade.Update(entity.warehouseIOList);
            IList<WarehouseIODetail> ioDetailLists = ioDetailFacade.LoadAll(" and  main.IOListId='" + entity.warehouseIOList.Id + "'");
            foreach (WarehouseIODetail detail in ioDetailLists)
            {
                string hql = " and main.CompanyId='" + entity.warehouseIOList.ICompanyId + "'";
                hql += " and main.DistrictId='" + entity.warehouseIOList.IDistrictId + "'";
                hql += " and main.State='0'";
                hql += " and main.EquipModelId='" + detail.EquipModelId + "'";
                WarehouseStock RStock = stockFacade.GetHql(hql);

                if (RStock != null)
                {
                    RStock.Num -= detail.Num;
                    if (RStock.Num < 0)
                        throw new Exception("大于库存数量!!");
                    result = stockFacade.UpdateNum(RStock);
                }
            }
            result = ioDetailFacade.Delete(entity.warehouseIOList.Id.ToString());

            WarehouseIODetail[] IODetails = (WarehouseIODetail[])JavaScriptConvert.DeserializeObject(entity.ioDetails, typeof(WarehouseIODetail[]));
            int j = 0;
            foreach (WarehouseIODetail ioDetail in IODetails)
            {
                ioDetail.IOListId = entity.warehouseIOList.Id;
                result = ioDetailFacade.Save(ioDetail, j.ToString());
                string hql = " and main.CompanyId='" + entity.warehouseIOList.ICompanyId + "'";
                hql += " and main.DistrictId='" + entity.warehouseIOList.IDistrictId + "'";
                hql += " and main.State='0'";
                hql += " and main.EquipModelId='" + ioDetail.EquipModelId + "'";

                WarehouseStock Stock = stockFacade.GetHql(hql);
                if (Stock == null)
                {
                    Stock = new WarehouseStock();
                    Stock.State = "0";
                    Stock.Num = ioDetail.Num;
                    Stock.UnitName = ioDetail.UnitName;
                    Stock.CompanyId = entity.warehouseIOList.ICompanyId;
                    Stock.DistrictId = entity.warehouseIOList.IDistrictId;
                    Stock.EquipModelId = ioDetail.EquipModelId;
                    Stock.EquipModelName = ioDetail.EquipModelName;
                    result = stockFacade.Save(Stock, j.ToString());
                }
                else
                {
                    Stock.Num += ioDetail.Num;
                    result = stockFacade.UpdateNum(Stock);
                }
                j++;
            }
            return result;
        }
        public bool SaveTrac(IOList entity)
        {
            bool result = false;
            result = this.ioListFacade.Save(entity.warehouseIOList, "0");
            WarehouseIODetail[] IODetails = (WarehouseIODetail[])JavaScriptConvert.DeserializeObject(entity.ioDetails, typeof(WarehouseIODetail[]));
            int j = 0;
            foreach (WarehouseIODetail ioDetail in IODetails)
            {
                ioDetail.IOListId = entity.warehouseIOList.Id;
                result = ioDetailFacade.Save(ioDetail, j.ToString());                
                j++;
            }
            return result;
        }
        public bool UpdateTrac(IOList entity)
        {
            bool result = false;
            result = this.ioListFacade.Update(entity.warehouseIOList);            
            result = ioDetailFacade.Delete(entity.warehouseIOList.Id.ToString());

            WarehouseIODetail[] IODetails = (WarehouseIODetail[])JavaScriptConvert.DeserializeObject(entity.ioDetails, typeof(WarehouseIODetail[]));
            int j = 0;
            foreach (WarehouseIODetail ioDetail in IODetails)
            {
                ioDetail.IOListId = entity.warehouseIOList.Id;
                result = ioDetailFacade.Save(ioDetail, j.ToString());               
                j++;
            }
            return result;
        }
    }
}
