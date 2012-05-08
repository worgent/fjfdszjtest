using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Domain;
using QzgfFrame.Archives.ComponentManger.Models;
using QzgfFrame.Archives.ComponentManger.Domain;
using QzgfFrame.Resources.EquipComponentManger.Domain;
using QzgfFrame.Resources.EquipComponentManger.Models;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using QzgfFrame.Controllers.CommonSupport;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.SelfHelpEquipManger
{
    public class SelfHelpEquipController : BaseController
    {
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }
        private SelfHelpFactoryFacade selfHelpFactoryFacade { set; get; }
        private SelfHelpEquipTypeFacade selfHelpEquipTypeFacade { set; get; }
        private SelfHelpEquipModelFacade selfHelpEquipModelFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }
        private OutletsTypeFacade outletsTypeFacade { set; get; }
        private SelfHelpEquipFacadeEx selfHelpEquipFacadeEx { set; get; }
        private ComponentFacade componentFacade { set; get; }
        private EquipComponentFacade equipComponentFacade { set; get; }
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

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ExcelOut(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("ExcelOut");
        }
        public ActionResult GetDropDownList()
        {
            string msg = "操作失败";
            IList<ArchiveComponent> Components = componentFacade.LoadAll();
            string list = JsonConvert.SerializeObject(Components);
            return Json(new { Type = true, Message = msg, Rows = list }, JsonRequestBehavior.AllowGet);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Display(string id, string frameid)
        {
            ViewData["frameid"] = frameid;
            SelfHelpEquip result = new SelfHelpEquip();
            ResourceSelfHelpEquip ResourceSelfHelpEquip = selfHelpEquipFacade.Get(id);
            ArchiveSelfHelpFactory factory = selfHelpFactoryFacade.Get(ResourceSelfHelpEquip.FactoryId);
            ArchiveSelfHelpEquipType equipType = selfHelpEquipTypeFacade.Get(ResourceSelfHelpEquip.EquipTypeId);
            ArchiveDistrict district = districtFacade.Get(ResourceSelfHelpEquip.DistrictId);
            ArchiveOutletsType outletsType = outletsTypeFacade.Get(ResourceSelfHelpEquip.NetType);
            
            ViewData["factory"] =factory.Abbrevia ;
            ViewData["equipType"] = equipType.SelfHelpEquipTypeName ;
            ViewData["district"] =district.DistrictName ;
            ViewData["outletsType"] = outletsType.OutletsTypeName ;

            IList<ResourceEquipComponent> equipComponents = new List<ResourceEquipComponent>();
            ResourceEquipComponent equipComponent = new ResourceEquipComponent();
            equipComponents.Add(equipComponent);
            IList<ResourceEquipComponent> Components = equipComponentFacade.LoadAll("Id", " SelfHelpEquipId='" + ResourceSelfHelpEquip.Id + "'");
            if (Components.Count == 0)
            {
                result.equipComponent = equipComponents;
            }
            else
            {
               result.equipComponent= new List<ResourceEquipComponent>();
                foreach (ResourceEquipComponent ec in Components)
                {
                    ArchiveComponent  Component = componentFacade.Get(ec.ComponentId);
                    ec.ComponentName=Component.ComponentName;
                    result.equipComponent.Add(ec);
                }
            }
            ViewData["startdt"] = string.Format("{0:d}", ResourceSelfHelpEquip.StartDatetime);
            result.selfHelpEquip = ResourceSelfHelpEquip;
            return View("Display", result);
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id, string frameid)
        {
            var result = new SelfHelpEquip();
            ResourceSelfHelpEquip ResourceSelfHelpEquip = new ResourceSelfHelpEquip();
            result.Id = "0";
           // IList<ArchiveSelfHelpFactory> factorys = selfHelpFactoryFacade.LoadAll();
           // IList<ArchiveSelfHelpEquipType> equipTypes = selfHelpEquipTypeFacade.LoadAll();
           // IList<ArchiveSelfHelpEquipModel> equipModels = selfHelpEquipModelFacade.LoadAll();
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            IList<ArchiveOutletsType> outletsTypes = outletsTypeFacade.LoadAll();
            IList<ArchiveComponent> Components = componentFacade.LoadAll();
            /**
            IList<ArchiveDistrict> citys = districtFacade.GetCitys(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            ViewData["city"] = new SelectList(citys, "Id", "DistrictName");**/
           // ViewData["factory"] = new SelectList(factorys, "Id", "Abbrevia");
           // ViewData["equipType"] = new SelectList(equipTypes, "Id", "SelfHelpEquipTypeName");
           // ViewData["equipModel"] = new SelectList(equipModels, "Id", "ModelName");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            ViewData["outletsType"] = new SelectList(outletsTypes, "Id", "OutletsTypeName");
            result.Components = Components;
            IList<ResourceEquipComponent> equipComponents = new List<ResourceEquipComponent>();
            ResourceEquipComponent equipComponent = new ResourceEquipComponent();
            equipComponents.Add(equipComponent);
            ViewData["startdt"] = DateTime.Now.ToShortDateString();
            result.equipComponent = equipComponents;
            result.selfHelpEquip = ResourceSelfHelpEquip;
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id, string frameid)
        {
            SelfHelpEquip result = new SelfHelpEquip();
            ResourceSelfHelpEquip ResourceSelfHelpEquip = new ResourceSelfHelpEquip();
            //IList<ArchiveSelfHelpFactory> factorys = selfHelpFactoryFacade.LoadAll();
            //IList<ArchiveSelfHelpEquipType> equipTypes = selfHelpEquipTypeFacade.LoadAll();
           // IList<ArchiveSelfHelpEquipModel> equipModels = selfHelpEquipModelFacade.LoadAll();
            IList<ArchiveDistrict> districts = districtFacade.GetDistricts(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO));
            IList<ArchiveOutletsType> outletsTypes = outletsTypeFacade.LoadAll();
            IList<ArchiveComponent> Components = componentFacade.LoadAll();

            //ViewData["factory"] = new SelectList(factorys, "Id", "Abbrevia");
           // ViewData["equipType"] = new SelectList(equipTypes, "Id", "SelfHelpEquipTypeName");
           // ViewData["equipModel"] = new SelectList(equipModels, "Id", "ModelName");
            ViewData["district"] = new SelectList(districts, "Id", "DistrictName");
            ViewData["outletsType"] = new SelectList(outletsTypes, "Id", "OutletsTypeName");
            result.Components = Components;
            IList<ResourceEquipComponent> equipComponents = new List<ResourceEquipComponent>();
            ResourceEquipComponent equipComponent = new ResourceEquipComponent();
            equipComponents.Add(equipComponent);
            ResourceSelfHelpEquip = selfHelpEquipFacade.Get(id);
            result.equipComponent = equipComponentFacade.LoadAll("Id", " SelfHelpEquipId='" + ResourceSelfHelpEquip.Id + "'");
            if (result.equipComponent.Count == 0)
            {
                result.equipComponent = equipComponents;
            }
            ViewData["startdt"] = string.Format("{0:d}", ResourceSelfHelpEquip.StartDatetime);
            result.selfHelpEquip = ResourceSelfHelpEquip;
            result.FactoryId = result.selfHelpEquip.FactoryId;
            result.EquipTypeId = result.selfHelpEquip.EquipTypeId;
            ViewData["frameid"] = frameid;
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, SelfHelpEquip entity)
        {
            bool result = false; string msg = "操作失败";
            try
            {
                entity.selfHelpEquip.CreateUserId = this.currentUser.UserInfo.Id;
                entity.selfHelpEquip.FactoryId = entity.FactoryId;
                entity.selfHelpEquip.EquipTypeId = entity.EquipTypeId;
                if (id == "0")
                    result=this.selfHelpEquipFacadeEx.Save(entity);
                else
                    result=this.selfHelpEquipFacadeEx.Update(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
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
                string savePath = "../Upload/LoadFile/SelfHelpEquip\\";
                // string savePath = Server.MapPath("../Upload/LoadFile/Apparatus\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                    return Json(new { Type = result, Message = msg }, "text/html");
                if (btnFlag == "0")
                    result = selfHelpEquipFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName);//检测数据
                else
                    result = selfHelpEquipFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName,userid);//导入数据库
                GC.Collect();
                if (!result)
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                if (result) msg = ShowInfo + "<br>" + msg;
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                GC.Collect();
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
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
                result = selfHelpEquipFacadeEx.Delete(id.ToString());
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            } 
            catch
            {
                return Json(new { Type = false, Message = "操作失败" }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult DeleteFalse(string id)
        {
            bool DelFlag = false;
            string msg = "操作失败"; 
            try
            {
                bool result = false;
                result = selfHelpEquipFacadeEx.DeleteFalse(id.ToString(), out DelFlag);
                if (result) msg = "操作成功";
                if (DelFlag) msg += "，部份信息存在关联无法删除！！";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                if (DelFlag) msg += "，部份信息存在关联无法删除！！";
                return Json(new { Type = false, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetTerimNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Length != 8)
                        result = false;
                    else
                    {
                        ResourceSelfHelpEquip rse = selfHelpEquipFacade.GetHql(id);
                        if (rse != null)
                            return Json(new { Type = true, Message = "操作成功", Id = rse.Id }, JsonRequestBehavior.AllowGet);
                    }
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
        public ActionResult GetTerim(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<ResourceSelfHelpEquip> result = selfHelpEquipFacade.LoadAll(" and TermiId like'%" + list + "%'");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ResourceSelfHelpEquip item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        termiId = item.TermiId,
                        districtId = item.DistrictId,
                        districtName = item.DistrictName,
                        useNetNo=item.UseNetNo,
                        useNetName=item.UseNetName,
                        factoryId = item.FactoryId,
                        factoryName = item.FactoryName,
                        equipModelName=item.EquipModelName
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
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult GetCheckTerimNo(string id)
        {
            try
            {
                bool result = false;
                if (id != null || id != "")
                {
                    if (id.Length != 8)
                        result = false;
                    else
                    {
                        ResourceSelfHelpEquip item = selfHelpEquipFacade.GetSql(id);
                        if (item != null)
                            return Json(new
                            {
                                Type = true,
                                Message = "操作成功",
                                Rows = new
                                {
                                    Id = item.Id,
                                    districtId = item.DistrictId,
                                    districtName = item.DistrictName,
                                    useNetNo = item.UseNetNo,
                                    useNetName = item.UseNetName,
                                    factoryId = item.FactoryId,
                                    factoryName = item.FactoryName,
                                    equipModelName = item.EquipModelName
                                }
                            }, JsonRequestBehavior.AllowGet);
                    }
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
        public ActionResult Quit(string id)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                result = selfHelpEquipFacade.Quit(id.ToString());
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
            string strhql = districtFacade.GetSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

            if (strhql != "")
                gridsearch += " and " + strhql;
            return selfHelpEquipFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
        #region 导出的数据
        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        private string[] aryTilte = { "设备厂家","设备类型","设备型号","设备安装时间","终端ID号"
                            ,"所属区县", "网点类型", "网点编号","网点名称"
                            , "终端IP地址","掩码", "终端MAC地址", "设备采购时间","可使用年限"
                            , "故障次数" ,"状态"
                           };
        private string[] aryType = { "str","str","str","date","str"
                            ,"str","str", "str", "str"
                            , "str","str", "str", "date","str"
                            , "str" ,"state"
                           };
        private string aryField = @"factory.Abbrevia,etype.SelfHelpEquipTypeName,main.EquipModelName,main.StartDatetime,main.TermiId
                            ,ad.DistrictName,ntype.OutletsTypeName, main.UseNetNo, main.UseNetName,main.TerimIP
                            , main.SubnetMask,main.MacAddress, main.BuyDatetime, main.Life
                            , main.FaultNum,main.State";
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
                string strhql = districtFacade.GetPSearch(this.currentUser.UserInfo.Areaid, Convert.ToInt32(this.currentUser.UserInfo.LEVELNO), "main");

                if (strhql != "")
                    gridsearch += " and " + strhql; IList<object[]> ls = selfHelpEquipFacade.FindExcel(aryField, gridsearch);
                result = BaseFun.ExcelOut("自助设备信息", aryTilte, ls, aryType,"selfequip");
                return View();
            }
            catch
            {
                return View();
            }
        }
    }
}
