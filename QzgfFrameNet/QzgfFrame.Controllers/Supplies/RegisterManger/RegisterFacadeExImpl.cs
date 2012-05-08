using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Text;
using QzgfFrame.Archives.SaleDepartmentManger.Domain;
using QzgfFrame.Archives.SaleDepartmentManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Archives.SuppliesTypeManger.Domain;
using QzgfFrame.Archives.SuppliesTypeManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Models;
using QzgfFrame.Supplies.CommunityManagerManger.Domain;
using QzgfFrame.Supplies.RegisterManger.Domain;
using QzgfFrame.Supplies.RegisterManger.Models;
using QzgfFrame.Supplies.RegisterDetailManger.Models;
using QzgfFrame.Supplies.RegisterDetailManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Domain;
using QzgfFrame.Supplies.SuppliesStockManger.Models;
using Excel = Microsoft.Office.Interop.Excel;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Supplies.RegisterManger
{
    public class RegisterFacadeExImpl : RegisterFacadeEx
    {

        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private SaleDepartmentFacade saleDepartmentFacade { set; get; }
        private CommunityManagerFacade communityManagerFacade { set; get; }
        private SuppliesTypeFacade suppliesTypeFacade { set; get; }
        private UnitFacade unitFacade { set; get; }
        private SuppliesStockFacade suppliesStockFacade { set; get; }

        private RegisterFacade registerFacade { set; get; }
        private RegisterDetailFacade registerDetailFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(Register entity)
        {
            bool result = false;
            result = this.registerFacade.Save(entity.suppliesRegister, "0");
            SuppliesRegisterDetail[] registerDetails = (SuppliesRegisterDetail[])JavaScriptConvert.DeserializeObject(entity.registerDetails, typeof(SuppliesRegisterDetail[]));
            int i = 0;
            foreach (SuppliesRegisterDetail rf in registerDetails)
            {
                rf.RegisterId = entity.suppliesRegister.Id;
                result = registerDetailFacade.Save(rf, i.ToString());
                string sql = " and main.DistrictId='" + entity.suppliesRegister.DistrictId + "'";
                sql += " and main.SaleDepartmentId='" + entity.suppliesRegister.SaleDepartmentId + "' and main.State='0'";
                sql += "and main.SuppliesTypeId='" + rf.SuppliesTypeId + "'";
                SuppliesSuppliesStock suppliesStockC = suppliesStockFacade.GetHql(sql);
                if (suppliesStockC != null)
                {
                    suppliesStockC.Num -= rf.Num;
                    // if (suppliesStockC.Num < 0)
                    //throw new Exception("分配数量大于耗材库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockC);
                }
                else
                {
                    suppliesStockC = new SuppliesSuppliesStock();
                    ArchiveCompany company = companyFacade.GetHql("移动");
                    suppliesStockC.CompanyId = company.Id;
                    suppliesStockC.DistrictId = entity.suppliesRegister.DistrictId;
                    suppliesStockC.SaleDepartmentId = entity.suppliesRegister.SaleDepartmentId;
                    suppliesStockC.SuppliesTypeId = rf.SuppliesTypeId;
                    suppliesStockC.Num = 0 - rf.Num;
                    result = suppliesStockFacade.Save(suppliesStockC,i.ToString());
                }
                i++;
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(Register entity)
        {
            bool result = false;
            result = this.registerFacade.Update(entity.suppliesRegister);
            IList<SuppliesRegisterDetail> regDetails = registerDetailFacade.LoadAll("Id"," RegisterId='" + entity.suppliesRegister.Id + "'");
            foreach (SuppliesRegisterDetail ddetail in regDetails)
            {
                string sql = " and main.DistrictId='" + entity.suppliesRegister.DistrictId + "'";
                sql += " and main.SaleDepartmentId='" + entity.suppliesRegister.SaleDepartmentId + "' and main.State='0'";
                sql += "and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "'";
                SuppliesSuppliesStock suppliesStockR = suppliesStockFacade.GetHql(sql);

                if (suppliesStockR != null)
                {
                    suppliesStockR.Num += ddetail.Num;
                    result = suppliesStockFacade.UpdateNum(suppliesStockR);
                }
            }
            result = registerDetailFacade.Delete(entity.suppliesRegister.Id.ToString());
            SuppliesRegisterDetail[] registerDetails = (SuppliesRegisterDetail[])JavaScriptConvert.DeserializeObject(entity.registerDetails, typeof(SuppliesRegisterDetail[]));
            int i = 0;
            foreach (SuppliesRegisterDetail rf in registerDetails)
            {
                rf.RegisterId = entity.suppliesRegister.Id;
                result = registerDetailFacade.Save(rf, i.ToString());
                string sql = " and main.DistrictId='" + entity.suppliesRegister.DistrictId + "'";
                sql += " and main.SaleDepartmentId='" + entity.suppliesRegister.SaleDepartmentId + "' and main.State='0'";
                sql += "and main.SuppliesTypeId='" + rf.SuppliesTypeId + "'";
                SuppliesSuppliesStock suppliesStockC = suppliesStockFacade.GetHql(sql);

                if (suppliesStockC != null)
                {
                    suppliesStockC.Num -= rf.Num;
                    // if (suppliesStockC.Num < 0)
                    // throw new Exception("分配数量大于耗材库存数量!!");
                    result = suppliesStockFacade.UpdateNum(suppliesStockC);
                }
                else
                {
                    suppliesStockC = new SuppliesSuppliesStock();
                    ArchiveCompany company = companyFacade.GetHql("移动");
                    suppliesStockC.CompanyId = company.Id;
                    suppliesStockC.DistrictId = entity.suppliesRegister.DistrictId;
                    suppliesStockC.SaleDepartmentId = entity.suppliesRegister.SaleDepartmentId;
                    suppliesStockC.SuppliesTypeId = rf.SuppliesTypeId;
                    suppliesStockC.Num = 0 - rf.Num;
                    result = suppliesStockFacade.Save(suppliesStockC, i.ToString());
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
            result = registerFacade.Delete(id.ToString());
            result = registerDetailFacade.Delete(id.ToString());
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
        public bool DeleteFalse(string id)
        {
            bool result = false;
            result = registerFacade.DeleteFalse(id.ToString());
            result = registerDetailFacade.DeleteFalse(id.ToString());
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
                SuppliesRegister register = registerFacade.Get(s);
                IList<SuppliesRegisterDetail> regDetails = registerDetailFacade.LoadAll("Id", " RegisterId='" + register.Id + "'");
                foreach (SuppliesRegisterDetail ddetail in regDetails)
                {
                    string sql = " and main.DistrictId='" + register.DistrictId + "' ";
                    sql += " and main.SaleDepartmentId='" + register.SaleDepartmentId + "' and main.State='0'";
                    sql += "and main.SuppliesTypeId='" + ddetail.SuppliesTypeId + "'";
                    SuppliesSuppliesStock suppliesStockR = suppliesStockFacade.GetHql(sql);

                    if (suppliesStockR != null)
                    {
                        suppliesStockR.Num += ddetail.Num;
                        result = suppliesStockFacade.UpdateNum(suppliesStockR);
                    }
                }
                result = registerFacade.DeleteFalse(s);
            }
            //result = registerDetailFacade.DeleteFalse(hql);
            if (result == false)
                throw new Exception("操作失败!!");
            return true;
        }
    }
}
