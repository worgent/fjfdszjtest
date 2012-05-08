using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Supplies.SuppliesStockManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using QzgfFrame.Supplies.CollarSuppliesManger.Domain;
using QzgfFrame.Supplies.CollarSuppliesManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Models;
using QzgfFrame.Supplies.CollarSuppliesDetailManger.Domain;
using Excel = Microsoft.Office.Interop.Excel;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Supplies.CollarSuppliesManger
{
    public class CollarSuppliesFacadeExImpl : CollarSuppliesFacadeEx
    {

        private DistrictFacade districtFacade { set; get; }
        private SuppliesTypeFacade suppliesTypeFacade { set; get; }
        private SuppliesStockFacade suppliesStockFacade { set; get; }

        private CollarSuppliesFacade collarSuppliesFacade { set; get; }
        private CollarSuppliesDetailFacade collarSuppliesDetailFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(CollarSupplies entity)
        {
            bool result = false;           
            int i = 0;            
            string[] suppliesTypes = entity.suppliesTypeIds.Split(',');
            SuppliesCollarSuppliesDetail[] collarDetails = (SuppliesCollarSuppliesDetail[])JavaScriptConvert.DeserializeObject(entity.collarSuppliesDetails, typeof(SuppliesCollarSuppliesDetail[]));
            
            foreach (string supplieType in suppliesTypes)
            {
                SuppliesCollarSupplies collarSupplies = new SuppliesCollarSupplies();
                collarSupplies.CollarDate = entity.suppliesCollarSupplies.CollarDate;
                collarSupplies.CollarUser = entity.suppliesCollarSupplies.CollarUser;
                collarSupplies.CompanyId = entity.suppliesCollarSupplies.CompanyId;
                collarSupplies.DistrictId = entity.suppliesCollarSupplies.DistrictId;
                collarSupplies.CreateUserId = entity.suppliesCollarSupplies.CreateUserId;
                collarSupplies.SuppliesTypeId = supplieType;
                int Num = 0;
                foreach (SuppliesCollarSuppliesDetail rf in collarDetails)
                {
                    if (rf.SuppliesTypeId == supplieType)
                    {
                        Num += rf.Num;
                        collarSupplies.Remark += rf.Remark;
                    }
                }
                collarSupplies.Num = Num;
                result = this.collarSuppliesFacade.Save(collarSupplies, i.ToString());
                i++;
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(CollarSupplies entity)
        {
            bool result = false;
            SuppliesCollarSupplies collarsupplies = collarSuppliesFacade.Get(entity.suppliesCollarSupplies.Id);
            if (entity.Collars != null)
            {
                SuppliesCollar[] Collars = (SuppliesCollar[])JavaScriptConvert.DeserializeObject(entity.Collars, typeof(SuppliesCollar[]));
                int i = 0; int Num=0;
                foreach (SuppliesCollar rf in Collars)
                {
                    rf.CollarSuppliesId = entity.suppliesCollarSupplies.Id;
                    result = collarSuppliesFacade.SaveCollar(rf,i.ToString());
                    Num += rf.Num;
                    i++;
                }
                string hql = " and main.CompanyId='" + entity.suppliesCollarSupplies.CompanyId + "'";
                hql += " and main.DistrictId='" + entity.suppliesCollarSupplies.DistrictId + "'";
                hql += " and main.State='0'";
                hql += " and main.SuppliesTypeId='" + entity.suppliesCollarSupplies.SuppliesTypeId + "'";

                SuppliesSuppliesStock SuppliesStock = suppliesStockFacade.GetHql(hql);
                if (SuppliesStock == null)
                {
                    SuppliesStock = new SuppliesSuppliesStock();
                    SuppliesStock.State = "0";
                    SuppliesStock.Num = Num;
                    SuppliesStock.CompanyId = entity.suppliesCollarSupplies.CompanyId;
                    SuppliesStock.DistrictId = entity.suppliesCollarSupplies.DistrictId;
                    SuppliesStock.SuppliesTypeId = entity.suppliesCollarSupplies.SuppliesTypeId;
                    result = suppliesStockFacade.Save(SuppliesStock, i.ToString());
                }
                else
                {
                    SuppliesStock.Num += Num;
                    result = suppliesStockFacade.UpdateNum(SuppliesStock);
                }            
                if (collarsupplies.Num == entity.suppliesCollarSupplies.ArrivalNum)
                    collarsupplies.IsArrival = "1";
                collarsupplies.ArrivalNum = entity.suppliesCollarSupplies.ArrivalNum;
                result = collarSuppliesFacade.UpdateNum(collarsupplies);
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool Delete(string id)
        {
            bool result = false;
            result = collarSuppliesFacade.Delete(id.ToString());
            result = collarSuppliesDetailFacade.Delete(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool Invalid(string id)
        {
            bool result = false;
            SuppliesCollarSupplies collarsupplies = collarSuppliesFacade.Get(id);
            string hql = " and main.CompanyId='" + collarsupplies.CompanyId + "'";
            hql += " and main.DistrictId='" + collarsupplies.DistrictId + "'";
            hql += " and main.State='0'";
            hql += " and main.SuppliesTypeId='" + collarsupplies.SuppliesTypeId + "'";
            SuppliesSuppliesStock SuppliesStock = suppliesStockFacade.GetHql(hql);
            if (SuppliesStock != null)
            {
                SuppliesStock.Num -= collarsupplies.ArrivalNum;
                if (SuppliesStock.Num < 0)
                    throw new Exception("大于库存数量!!");
                result = suppliesStockFacade.UpdateNum(SuppliesStock);
            } 
            result = collarSuppliesFacade.DeleteFalse(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
    }
}
