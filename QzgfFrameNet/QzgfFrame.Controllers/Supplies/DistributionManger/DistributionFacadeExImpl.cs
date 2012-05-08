using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Supplies.DistributionManger.Domain;
using QzgfFrame.Supplies.DistributionManger.Models;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Supplies.DistributionDetailManger.Models;
using QzgfFrame.Supplies.DistributionDetailManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Supplies.DistributionManger
{
    public class DistributionFacadeExImpl : DistributionFacadeEx
    {
        private DistrictFacade districtFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }
        private SuppliesTypeFacade suppliesTypeFacade { set; get; }
        private UnitFacade unitFacade { set; get; }
        private SuppliesStockFacade suppliesStockFacade { set; get; }

        private DistributionFacade distributionFacade { set; get; }
        private DistributionDetailFacade distributionDetailFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool SaveYw(Distribution entity)
        {
            bool result = false;
            entity.suppliesDistribution.State = "0";
            result = this.distributionFacade.Save(entity.suppliesDistribution, "0");
            int i = 0;
            SuppliesDistributionDetail[] distributionDetails = (SuppliesDistributionDetail[])JavaScriptConvert.DeserializeObject(entity.distributionDetails, typeof(SuppliesDistributionDetail[]));
            foreach (SuppliesDistributionDetail rf in distributionDetails)
            {
                rf.DistributionId = entity.suppliesDistribution.Id;
                result = distributionDetailFacade.Save(rf, i.ToString());
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + rf.SuppliesTypeId + "' and main.State='0'" + sql;
                SuppliesSuppliesStock suppliesStockC = suppliesStockFacade.GetHql(strsql);
                SuppliesSuppliesStock suppliesStockR = suppliesStockFacade.GetHql(strsql + " and main.SaleDepartmentId='" + rf.SaleDepartmentId+ "'");
                if (suppliesStockR != null)
                {
                    suppliesStockR.Num += rf.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockR);
                }
                else
                {
                    suppliesStockR = new SuppliesSuppliesStock();
                    suppliesStockR.State = "0";
                    suppliesStockR.Num = rf.Num;
                    suppliesStockR.SuppliesTypeId = rf.SuppliesTypeId;
                    suppliesStockR.DistrictId = entity.suppliesDistribution.CityId;
                    suppliesStockR.CompanyId = entity.suppliesDistribution.CompanyId;
                    suppliesStockR.SaleDepartmentId = rf.SaleDepartmentId;
                    result = suppliesStockFacade.Save(suppliesStockR, i.ToString());
                }
                if (suppliesStockC != null)
                {
                    suppliesStockC.Num -= rf.Num;
                    if (suppliesStockC.Num < 0)
                        throw new Exception("分配数量大于耗材库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockC);
                }
                if (result == false)
                    throw new Exception("操作失败!!");
                i++;
            }

            //OrgId[] orgIds = (OrgId[])JavaScriptConvert.DeserializeObject(entity.OrgIds, typeof(OrgId[]));
            string[] orgids = entity.OrgIds.Split(',');
            SuppliesDistributionDetail[] dDetails = (SuppliesDistributionDetail[])JavaScriptConvert.DeserializeObject(entity.distributionDetails, typeof(SuppliesDistributionDetail[]));

            foreach (string org in orgids)
            {
                SuppliesDistribution sdistributtion = new SuppliesDistribution();
                sdistributtion.CityId = entity.suppliesDistribution.CityId;
                sdistributtion.CompanyId = entity.suppliesDistribution.CompanyId;
                sdistributtion.DCompanyId = entity.suppliesDistribution.CompanyId;
                sdistributtion.DistrictId = entity.suppliesDistribution.CityId;
                sdistributtion.SaleDepartmentId = org;
                sdistributtion.State = "0";
                sdistributtion.DistributionUser = entity.suppliesDistribution.DistributionUser;
                result = distributionFacade.Save(sdistributtion, i.ToString());
                int Num = 0; int k = 0;
                foreach (SuppliesDistributionDetail srf in dDetails)
                {
                    if (srf.SaleDepartmentId == org)
                    {
                        Num += srf.Num;
                        srf.DistributionId = sdistributtion.Id;
                        result = distributionDetailFacade.Save(srf, (i + k).ToString());
                        if (result == false)
                            throw new Exception("操作失败!!");
                    }
                    k++;
                }
                sdistributtion.Num = Num;
                result = distributionFacade.Update(sdistributtion);
                if (result == false)
                    throw new Exception("操作失败!!");
                i++;
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Save(Distribution entity)
        {
            bool result = false;
            entity.suppliesDistribution.State = "0";
            entity.suppliesDistribution.DCompanyId = entity.suppliesDistribution.CompanyId;
            result = this.distributionFacade.Save(entity.suppliesDistribution, "0");
            int i = 0;
            SuppliesDistributionDetail[] distributionDetails = (SuppliesDistributionDetail[])JavaScriptConvert.DeserializeObject(entity.distributionDetails, typeof(SuppliesDistributionDetail[]));
            foreach (SuppliesDistributionDetail rf in distributionDetails)
            {
                rf.DistributionId = entity.suppliesDistribution.Id;
                rf.CompanyId = entity.suppliesDistribution.CompanyId;
                result = distributionDetailFacade.Save(rf, i.ToString());
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + rf.SuppliesTypeId + "'" + sql;
                SuppliesSuppliesStock suppliesStockC = suppliesStockFacade.GetHql(" and main.State='0'" + strsql);
                SuppliesSuppliesStock suppliesStockR = suppliesStockFacade.GetHql(" and main.State='1'" + strsql);
                if (suppliesStockR != null)
                {
                    suppliesStockR.Num += rf.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockR);
                }
                else
                {
                    suppliesStockR = new SuppliesSuppliesStock();
                    suppliesStockR.State = "1";
                    suppliesStockR.Num = rf.Num;
                    suppliesStockR.SuppliesTypeId = rf.SuppliesTypeId;
                    suppliesStockR.DistrictId = entity.suppliesDistribution.CityId;
                    suppliesStockR.CompanyId = entity.suppliesDistribution.CompanyId;
                    result = suppliesStockFacade.Save(suppliesStockR,i.ToString());
                }
                if (suppliesStockC != null)
                {
                    suppliesStockC.Num -= rf.Num;
                    if (suppliesStockC.Num < 0)
                        throw new Exception("分配数量大于耗材库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockC);
                }
                if (result == false)
                    throw new Exception("操作失败!!");
                i++;
            } 
            OrgId[] orgIds = (OrgId[])JavaScriptConvert.DeserializeObject(entity.OrgIds, typeof(OrgId[]));
            SuppliesDistributionDetail[] dDetails = (SuppliesDistributionDetail[])JavaScriptConvert.DeserializeObject(entity.distributionDetails, typeof(SuppliesDistributionDetail[]));
            
            foreach (OrgId org in orgIds)
            {
                SuppliesDistribution sdistributtion = new SuppliesDistribution();
                sdistributtion.CityId = entity.suppliesDistribution.CityId;
                sdistributtion.CompanyId = entity.suppliesDistribution.CompanyId;
                sdistributtion.DCompanyId = entity.suppliesDistribution.CompanyId;
                sdistributtion.DistrictId = org.DistrictId;
                sdistributtion.State = "0";
                sdistributtion.DistributionUser = entity.suppliesDistribution.DistributionUser;
                result = distributionFacade.Save(sdistributtion, i.ToString());
                int Num = 0; int k = 0;
                foreach (SuppliesDistributionDetail srf in dDetails)
                {
                    if (srf.DistrictId == org.DistrictId)
                    {
                        Num += srf.Num;
                        srf.DistributionId = sdistributtion.Id;
                        srf.CompanyId = entity.suppliesDistribution.CompanyId;
                        result = distributionDetailFacade.Save(srf, (i + k).ToString());
                        if (result == false)
                            throw new Exception("操作失败!!");                 
                    }
                    k++;
                } 
                sdistributtion.Num=Num;
                result = distributionFacade.Update(sdistributtion);
                if (result == false)
                    throw new Exception("操作失败!!");
                i++;
            }
            if (result == false)
              throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(Distribution entity)
        {
            bool result = false;
            entity.suppliesDistribution.State = "0";
            entity.suppliesDistribution.DCompanyId = entity.suppliesDistribution.CompanyId;
            result = this.distributionFacade.Update(entity.suppliesDistribution);
            IList<SuppliesDistributionDetail> dDetailLists = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + entity.suppliesDistribution.Id + "'");
            foreach (SuppliesDistributionDetail ddetail in dDetailLists)
            {
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "'" + sql;
                SuppliesSuppliesStock suppliesStockDC = suppliesStockFacade.GetHql(" and main.State='1'" + strsql);
                SuppliesSuppliesStock suppliesStockDR = suppliesStockFacade.GetHql(" and main.State='0'" + strsql);
                if (suppliesStockDC != null)
                {
                    suppliesStockDC.Num -= ddetail.Num;
                    if (suppliesStockDC.Num < 0)
                        throw new Exception("大于库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockDC);
                }
                if (suppliesStockDR != null)
                {
                    suppliesStockDR.Num += ddetail.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockDR);
                }
            }
            result = distributionDetailFacade.Delete(entity.suppliesDistribution.Id.ToString());
            SuppliesDistributionDetail[] distributionDetails = (SuppliesDistributionDetail[])JavaScriptConvert.DeserializeObject(entity.distributionDetails, typeof(SuppliesDistributionDetail[]));
            int i = 0;
            foreach (SuppliesDistributionDetail rf in distributionDetails)
            {
                rf.DistributionId = entity.suppliesDistribution.Id;
                rf.DistrictId = entity.suppliesDistribution.DistrictId;
                rf.CompanyId = entity.suppliesDistribution.DCompanyId;
                result = distributionDetailFacade.Save(rf, i.ToString());
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + rf.SuppliesTypeId + "'" + sql;
                SuppliesSuppliesStock suppliesStockC = suppliesStockFacade.GetHql(" and main.State='0'" + strsql);
                SuppliesSuppliesStock suppliesStockR = suppliesStockFacade.GetHql(" and main.State='1'" + strsql);
                if (suppliesStockR != null)
                {
                    suppliesStockR.Num += rf.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockR);
                }
                else
                {
                    suppliesStockR = new SuppliesSuppliesStock();
                    suppliesStockR.State = "1";
                    suppliesStockR.Num = rf.Num;
                    suppliesStockR.SuppliesTypeId = rf.SuppliesTypeId;
                    suppliesStockR.DistrictId = entity.suppliesDistribution.CityId;
                    suppliesStockR.CompanyId = entity.suppliesDistribution.CompanyId;
                    result = suppliesStockFacade.Save(suppliesStockR, i.ToString());
                }
                if (suppliesStockC != null)
                {
                    suppliesStockC.Num -= rf.Num;
                    if (suppliesStockC.Num < 0)
                        throw new Exception("分配数量大于耗材库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockC);
                }
                i++;
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool UpdateYw(Distribution entity)
        {
            bool result = false;
            entity.suppliesDistribution.State = "0";
            entity.suppliesDistribution.DCompanyId = entity.suppliesDistribution.CompanyId;
            result = this.distributionFacade.Update(entity.suppliesDistribution);
            IList<SuppliesDistributionDetail> dDetailLists = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + entity.suppliesDistribution.Id + "'");
            foreach (SuppliesDistributionDetail ddetail in dDetailLists)
            {
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "' and main.State='0'" + sql;
                SuppliesSuppliesStock suppliesStockDR = suppliesStockFacade.GetHql(strsql);//区县
                SuppliesSuppliesStock suppliesStockDC = suppliesStockFacade.GetHql(strsql + " and main.SaleDepartmentId='" + entity.suppliesDistribution.SaleDepartmentId + "'");//营业厅
                
                if (suppliesStockDC != null)
                {
                    suppliesStockDC.Num -= ddetail.Num;
                    if (suppliesStockDC.Num < 0)
                        throw new Exception("大于库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockDC);
                }
                if (suppliesStockDR != null)
                {
                    suppliesStockDR.Num += ddetail.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockDR);
                }
            }
            result = distributionDetailFacade.Delete(entity.suppliesDistribution.Id.ToString());
            SuppliesDistributionDetail[] distributionDetails = (SuppliesDistributionDetail[])JavaScriptConvert.DeserializeObject(entity.distributionDetails, typeof(SuppliesDistributionDetail[]));
            int i = 0;
            foreach (SuppliesDistributionDetail rf in distributionDetails)
            {
                rf.DistributionId = entity.suppliesDistribution.Id;
                rf.DistrictId = entity.suppliesDistribution.DistrictId;
                rf.CompanyId = entity.suppliesDistribution.DCompanyId;
                result = distributionDetailFacade.Save(rf, i.ToString());
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + rf.SuppliesTypeId + "' and main.State='0'" + sql;
                SuppliesSuppliesStock suppliesStockC = suppliesStockFacade.GetHql(strsql);//区县
                SuppliesSuppliesStock suppliesStockR = suppliesStockFacade.GetHql(strsql + " and main.SaleDepartmentId='" + entity.suppliesDistribution.SaleDepartmentId + "'");//营业厅
                
                if (suppliesStockR != null)
                {
                    suppliesStockR.Num += rf.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockR);
                }
                else
                {
                    suppliesStockR = new SuppliesSuppliesStock();
                    suppliesStockR.State = "1";
                    suppliesStockR.Num = rf.Num;
                    suppliesStockR.SuppliesTypeId = rf.SuppliesTypeId;
                    suppliesStockR.DistrictId = entity.suppliesDistribution.CityId;
                    suppliesStockR.CompanyId = entity.suppliesDistribution.CompanyId;
                    result = suppliesStockFacade.Save(suppliesStockR, i.ToString());
                }
                if (suppliesStockC != null)
                {
                    suppliesStockC.Num -= rf.Num;
                    if (suppliesStockC.Num < 0)
                        throw new Exception("分配数量大于耗材库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockC);
                }
                i++;
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool Delete(string id)
        {
            bool result = false;
            result = distributionFacade.Delete(id.ToString());
            result = distributionDetailFacade.Delete(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool DeleteFalse(string id)
        {
            bool result = false;
            result = distributionFacade.DeleteFalse(id.ToString());
            result = distributionDetailFacade.DeleteFalse(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool Confirm(Distribution entity)
        {
            bool result = false;
            result = this.distributionFacade.UpdateH(entity.suppliesDistribution);
            IList<SuppliesDistributionDetail> dDetailLists = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + entity.suppliesDistribution.Id + "'");
            int i = 0;
            foreach (SuppliesDistributionDetail ddetail in dDetailLists)
            {
                //接收方数量
                string hql = " and main.DistrictId='" + entity.suppliesDistribution.DistrictId + "' and main.CompanyId='" + entity.suppliesDistribution.DCompanyId + "'";
                string strhql = " and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "'" + hql;
                //分配方
                string sql = " and main.DistrictId='" + entity.suppliesDistribution.CityId + "' and main.CompanyId='" + entity.suppliesDistribution.CompanyId + "'";
                string strsql = " and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "'" + sql;
                SuppliesSuppliesStock suppliesStockDC = suppliesStockFacade.GetHql(" and main.State='1'" + strsql);
                SuppliesSuppliesStock suppliesStockDR = suppliesStockFacade.GetHql(" and main.State='0'" + strhql);
                if (suppliesStockDC != null)
                {
                    suppliesStockDC.Num -= ddetail.Num;
                    if (suppliesStockDC.Num < 0)
                        throw new Exception("大于库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockDC);
                }
                if (suppliesStockDR != null)
                {
                    suppliesStockDR.Num += ddetail.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockDR);
                }
                else
                {
                    suppliesStockDR = new SuppliesSuppliesStock();
                    suppliesStockDR.State = "0";
                    suppliesStockDR.Num = ddetail.Num;
                    suppliesStockDR.SuppliesTypeId = ddetail.SuppliesTypeId;
                    suppliesStockDR.DistrictId = entity.suppliesDistribution.DistrictId;
                    suppliesStockDR.CompanyId = entity.suppliesDistribution.DCompanyId;
                    result = suppliesStockFacade.Save(suppliesStockDR, i.ToString());
                }
                i++;
                if (result == false)
                    throw new Exception("操作失败!!");
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool Invalid(string id)
        {
            bool result = false;
            string[] idarr = id.Split(',');
            string hql = "";
            foreach (var s in idarr)
            {
                if (hql == "")
                    hql = " DistributionId='" + s + "'";
                else
                    hql += " or DistributionId='" + s + "'";
                SuppliesDistribution distribution = distributionFacade.Get(s);
                IList<SuppliesDistributionDetail> dDetailLists = distributionDetailFacade.LoadAll(" and  main.DistributionId='" + distribution.Id + "'");
                foreach (SuppliesDistributionDetail ddetail in dDetailLists)
                {
                    string sql = " and main.DistrictId='" + distribution.CityId + "' and main.CompanyId='" + distribution.CompanyId + "'";
                    string strsql = " and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "'" + sql;
                    SuppliesSuppliesStock suppliesStockDC = suppliesStockFacade.GetHql(" and main.State='1'" + strsql);
                    SuppliesSuppliesStock suppliesStockDR = suppliesStockFacade.GetHql(" and main.State='0'" + strsql);
                    if (suppliesStockDC != null)
                    {
                        suppliesStockDC.Num -= ddetail.Num;
                        if (suppliesStockDC.Num < 0)
                            throw new Exception("大于库存数量!!");
                        result = suppliesStockFacade.UpdateNum(suppliesStockDC);
                    }
                    if (suppliesStockDR != null)
                    {
                        suppliesStockDR.Num += ddetail.Num;
                        result = suppliesStockFacade.UpdateNum(suppliesStockDR);
                    }
                }
                result = distributionFacade.DeleteFalse(s);
            }
           // result = distributionDetailFacade.DeleteFalse(hql);
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
    }
}
