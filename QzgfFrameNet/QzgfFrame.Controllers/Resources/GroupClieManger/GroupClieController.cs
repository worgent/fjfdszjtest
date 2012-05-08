using System;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using Newtonsoft.Json;
using System.Collections.Generic;
using QzgfFrame.Archives.GridManger.Domain;
using QzgfFrame.Archives.GridManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.CompanyManger.Domain;
using QzgfFrame.Archives.CompanyManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.ClieScaleGradeManger.Models;
using QzgfFrame.Archives.ClieScaleGradeManger.Domain;
using QzgfFrame.Archives.ClieServStarLeveManger.Models;
using QzgfFrame.Archives.ClieServStarLeveManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Utility.Core;
using QzgfFrame.Controllers.CommonSupport;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.GroupClieManger
{
    public class GroupClieController : BaseController
    {
        private DistrictFacade districtFacade { set; get; }
        private CompanyFacade companyFacade { set; get; }
        private GridFacade gridFacade { set; get; }
        private ClieScaleGradeFacade clieScaleGradeFacade { set; get; }
        private ClieServStarLeveFacade clieServStarLeveFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private GroupClieFacadeEx groupClieFacadeEx { set; get; }
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }
        /// <summary>
        /// 为选择集团客户，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelIndex(string id)
        {
            string[]  ary = id.Split(',');
            ViewData["model"] = ary[0];
            ViewData["strlen"] = ary[1];
            return View();
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ExcelOut(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("ExcelOut");
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id)
        {
            ResourceGroupClie result = groupClieFacade.Get(id);
            ArchiveDistrict district = districtFacade.Get(result.DistrictId);
            ViewData["district"] = district.DistrictName;
            ArchiveCompany company = companyFacade.Get(result.CompanyId);
            ViewData["company"] = company.CompanyName;
            ArchiveClieScaleGrade ScaleGrade = clieScaleGradeFacade.Get(result.ScaleGradeId);
            ViewData["ScaleGrade"] = ScaleGrade.ScaleGradeName;
            
            if (result.StarLeveId != null)
            {
                ArchiveClieServStarLeve servStarLeve = clieServStarLeveFacade.Get(result.StarLeveId);
                ViewData["servStarLeve"] = servStarLeve.StarLeveName;
            }
            else
                ViewData["servStarLeve"] = "";
            string[] gridids = result.GridIds.Split(';');
            string gridnames = "";
            foreach (var gridid in gridids)
            {
                ArchiveGrid grid = gridFacade.Get(gridid);
                if(grid!=null)
                  gridnames +=grid.GridName + ";";
            }
            if (gridnames != "")
                    gridnames = gridnames.Substring(0, gridnames.Length - 1);
            ViewData["gridnames"] = gridnames;
            return View("Display", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            IList<ArchiveCompany> companys = companyFacade.LoadAll();
            ViewData["company"] = new SelectList(companys, "Id", "CompanyName");
            IList<ArchiveClieScaleGrade> ScaleGrades = clieScaleGradeFacade.LoadAll();
            ViewData["ScaleGrade"] = new SelectList(ScaleGrades, "Id", "ScaleGradeName");
            IList<ArchiveClieServStarLeve> servStarLeves = clieServStarLeveFacade.LoadAll();
            ViewData["servStarLeve"] = new SelectList(servStarLeves, "Id", "StarLeveName");
            
            ResourceGroupClie result = groupClieFacade.Get(id);

            return View("Edit", result);
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View();
        }
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult UploadFile(string btnFlag)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                string ShowInfo = ""; string reFileName = "";
                string savePath = "../Upload/LoadFile/GroupClie\\";
               // string savePath = Server.MapPath("../Upload/LoadFile/GroupClie\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = groupClieFacadeEx.CheckExcelData(strFileName, out msg,out  reFileName);//检测数据
                else
                    result = groupClieFacadeEx.SaveExcelData(strFileName, out msg,out  reFileName,userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                
                if (result) msg = ShowInfo+"<br>"+msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                GC.Collect();
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ResourceGroupClie entity)
        {
            try
            {
                entity.CreateUserId = this.currentUser.UserInfo.Id;
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
                else
                    result=this.groupClieFacade.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public ActionResult Get(string id)
        {
            try
            {
                ResourceGroupClie result = groupClieFacade.Get(id);
                if (result != null)
                    return Json(new { Type = true, ClieName = result.ClieName, ClieId = result.Id }, JsonRequestBehavior.AllowGet);
                else
                    return Json(new { Type = false, ClieName = "", ClieId="" }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, ClieName = "", ClieId = "" }, JsonRequestBehavior.AllowGet);
            }
        }
        /// <summary>
        /// 通过集团客户名称查询
        /// </summary>
        /// <param name="list"></param>
        /// <returns></returns>
        public ActionResult GetClie(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list); string hql = "";
                if (this.currentUser.UserInfo.Isrepair == "1")
                    hql += " and CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
                string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);

                if (strhql != "" && hql != "")
                    hql += " and " + strhql;
                else if (strhql != "" && hql == "")
                    hql += strhql;
                
                IList<ResourceGroupClie> result = groupClieFacade.LoadAll("Id", " ClieName like'%" + list + "%' and"+hql);
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
               IList mapList = new ArrayList();
                foreach (ResourceGroupClie item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        clieNo = item.ClieNo,
                        clieName = item.ClieName,
                    });
                }
               // string rowsjson = JsonConvert.SerializeObject(mapList);
                return Json(new { Type = true, Data = mapList }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Data =""}, JsonRequestBehavior.AllowGet);
            }
        }
        /// <summary>
        /// 通过集团客户编号查询
        /// </summary>
        /// <param name="list"></param>
        /// <returns></returns>
        public ActionResult GetClieNo(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list); string hql = "";
                if (this.currentUser.UserInfo.Isrepair == "1")
                    hql += " and CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
                string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);

                if (strhql != "" && hql != "")
                    hql += " and " + strhql;
                else if (strhql != "" && hql == "")
                    hql += strhql;

                IList<ResourceGroupClie> result = groupClieFacade.LoadAll("Id", " ClieNo like'%" + list + "%' and" + hql);
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ResourceGroupClie item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        clieNo = item.ClieNo,
                        clieName = item.ClieName,
                    });
                }
                // string rowsjson = JsonConvert.SerializeObject(mapList);
                return Json(new { Type = true, Data = mapList }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
            }
        }
        /// <summary>
        /// 集团编号作为下拉框
        /// </summary>
        /// <returns></returns>
        public string GetCombobox()
        {
            string hql = "";
            if (this.currentUser.UserInfo.Isrepair == "1")
                hql += " and CompanyId='" + this.currentUser.UserInfo.Departmentid + "'";
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), null);

            if (strhql != "" && hql!="")
                hql += " and " + strhql;
            else if (strhql != "" && hql == "")
                hql +=  strhql;
           
            return groupClieFacade.GetCombobox(hql);
        }//
        // GET: 获取集团客户编号
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetLineClieNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    ResourceGroupClie rse = groupClieFacade.GetHql(id);
                    if (rse != null)
                        return Json(new { Type = true, Message = "操作成功", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                }
                else
                    result = false;
                return Json(new { Type = result, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = groupClieFacade.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "删除失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // 假删除操作
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult DeleteFalse(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = groupClieFacade.DeleteFalse(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return groupClieFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        #region 导出的数据
        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        private string[] aryTilte = { "姓名","联系电话","车牌号","开始使用时间","使用里程"
                            ,"所属城市","所属区县", "所属公司", "所属网格","所属驻点"
                            , "性质","用途", "维护专业", "行驶证号","发动机号"
                            , "年检时间" 
                           };
        private string[] aryType = { "str","str","str","data","int"
                            ,"str","str", "str", "str","str"
                            , "str","str", "str", "str","str"
                            , "data" 
                           };
        private string aryField = @"main.FullName,main.LinkTel,main.LicensePlateNumber,main.StartDatetime,main.UseMileage
                            ,main.FullName,main.FullName, main.FullName, main.FullName,main.FullName
                            , main.FullName,main.FullName, main.FullName, main.DrivingLicenseNo
                            , main.EngineNo, main.AnnualInspectTime";
        #endregion
        /// <summary>
        /// 导出数据到excel
        /// </summary>
        /// <returns></returns>
        public ActionResult GetExcelOut(string gridsearch)
        {
            bool result = false;
            //string msg = "操作失败";
            string[] tt = { };
            try
            {
                string orgId = currentUser.UserInfo.Id;
                IList<object[]> ls = groupClieFacade.FindExcel(aryField, gridsearch);
                result = BaseFun.ExcelOut("车辆信息", aryTilte, ls, aryType,null);
                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}
