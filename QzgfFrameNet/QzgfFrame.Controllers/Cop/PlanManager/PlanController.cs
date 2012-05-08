using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.Cop.PlanManager.Domain;
using System.Web.Mvc;
using QzgfFrame.Cop.PlanManager.Models;
using System.Collections;
using QzgfFrame.Cop.CycTimeManager.Domain;
using QzgfFrame.Cop.CycTimeManager.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.PersonnelManger.Models;
using QzgfFrame.Resources.PersonnelManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.BizTypeManger.Domain;
using Newtonsoft.Json;
using System.Web;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;

namespace QzgfFrame.Controllers.Cop.PlanManager
{
    /// <summary>
    /// 专线巡检计划
    /// </summary>
    public class PlanController : BaseController
    {
        #region

        /// <summary>
        /// 专线巡检计划
        /// </summary>
        private PlanFacade planFacade { set; get; }
        /// <summary>
        /// 专线巡检周期
        /// </summary>
        private CycTimeFacade cycTimeFacade { set; get; }
        /// <summary>
        /// 集团客户
        /// </summary>
        private GroupClieFacade groupClieFacade { set; get; }
        /// <summary>
        /// 业务保障等级
        /// </summary>
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        /// <summary>
        /// 专线
        /// </summary>
        private DedicateLineFacade dedicateLineFacade { set; get; }
        /// <summary>
        /// 专线类型
        /// </summary>
        private BizTypeFacade bizTypeFacade { set; get; }

        private PlanFacadeExImpl planFacadeExImpl { get; set; }
        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        //导入文件标题字段
        string[] aryTilte = { "专线名称", "集团名称", "业务保障等级", "巡检周期"};
        //导入文件列首字母
        string beginLetter = "A";
        //导入文件列末字母
        string endLetter = "D";

        #endregion

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            //巡检登记后，传回的巡检计划菜单id
            //ViewData["frameId"] = id;
            string a = this.currentUser.UserInfo.LEVELNO.Trim();
            ViewData["leveNo"] = this.currentUser.UserInfo.LEVELNO.Trim();
            ShowSelect();
            return View();
        }

        /// <summary>
        /// 显示添加页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            Plan plan = new Plan();
            CopPlan result = new CopPlan();

            if (id == null)
            {
            }
            else
            {
                //编辑时返回具体值
                result = planFacade.Get(id);
            }
            plan.CopPlan = result;
            ShowSelect();
            return View("Add", plan);
        }

        /// <summary>
        /// 显示编辑页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            Plan plan = new Plan();
            CopPlan copPlan = new CopPlan();

            if (id == null)
            {
                copPlan.Id = id;
            }
            else
            {
                //编辑时返回具体值
                copPlan = planFacade.Get(id);
                if (copPlan != null)
                {
                    ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
                    resourceDedicateLine = dedicateLineFacade.Get(copPlan.DedicateLineId);
                    if (resourceDedicateLine != null)
                    {
                        //专线类型
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        archiveBizType = bizTypeFacade.Get(resourceDedicateLine.BizTypeId);
                        if (archiveBizType != null)
                        {
                            plan.BizType = archiveBizType.BizTypeName;
                        }

                        //集团客户
                        ResourceGroupClie resourceGroupClie = new ResourceGroupClie();
                        resourceGroupClie = groupClieFacade.Get(resourceDedicateLine.ClieId);
                        if (resourceGroupClie != null)
                        {
                            plan.GroupName = resourceGroupClie.ClieName;
                        }

                        //业务保障等级
                        ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                        archiveBizAssuranLeve = bizAssuranLeveFacade.Get(resourceDedicateLine.BizAssuranLeveId);
                        if (archiveBizAssuranLeve != null)
                        {
                            plan.BizAssuranLeve = archiveBizAssuranLeve.AssuranLeveName;
                        }
                    }
                }
            }
            plan.CopPlan = copPlan;
            ShowSelect();
            return View("Edit", plan);
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, Plan entity)
        {
            bool reasult = false;
            //string msg = "";
            string sql = "";
            try
            {
                if (id == null)
                {//添加

                    //到专线管理中,查找出所有业务等级一样的.并根据查询出来的条数,插入相应条数的数据
                    sql = " BizAssuranLeveId = '" + entity.ResourceDedicateLine.BizAssuranLeveId + "' and DelFlag != 1 or DelFlag is null";
                    IList<ResourceDedicateLine> resourceDedicateLines = new List<ResourceDedicateLine>();
                    resourceDedicateLines = dedicateLineFacade.LoadAll("id", sql);
                    foreach (var resourceDedicateLine in resourceDedicateLines)
                    {
                        //要判断下该专线是否已经添加计划
                        IList<CopPlan> copPlans = new List<CopPlan>();
                        sql = " DedicateLineId = '" + resourceDedicateLine.Id + "' and IsDelete = 0 ";
                        copPlans = planFacade.LoadAll("id", sql);
                        if (copPlans.Count == 0)
                        {
                            CopPlan copPlan = new CopPlan();
                            copPlan.DedicateLineId = resourceDedicateLine.Id;

                            //业务保障等级
                            ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                            archiveBizAssuranLeve = bizAssuranLeveFacade.Get(entity.ResourceDedicateLine.BizAssuranLeveId);
                            if (archiveBizAssuranLeve != null)
                            {
                                string cycTime = "";
                                switch (archiveBizAssuranLeve.AssuranLeveName)
                                { 
                                    case "AAA":
                                        cycTime = "1";
                                        break;
                                    case "AA":
                                        cycTime = "3";
                                        break;
                                    case "A":
                                        cycTime = "6";
                                        break;
                                }
                                copPlan.CycTime = cycTime;
                            }

                            //copPlan.CycTimeId = entity.CopPlan.CycTimeId;
                            //CopCycTime copCycTime = new CopCycTime();
                            //copCycTime = cycTimeFacade.Get(entity.CopPlan.CycTimeId);
                            //if (copCycTime != null)
                            //{
                            //    copPlan.CycTime = copCycTime.CycTime;
                            //}

                            //生成计划的时候,"巡检起始时间"与"下次巡检时间"都为空，
                            //当进行"巡检登记"的时候,根据用户的登记时间,生成"巡检起始时间",
                            //并根据"巡检周期",自动计算出"下次巡检时间"
                            copPlan.StartCycTime = "";
                            copPlan.NextCycTime = "";
                            copPlan.CreationTime = dateNow;
                            reasult = planFacade.Save(copPlan);
                        }
                    }
                }
                else
                {//编辑

                    CopPlan copPlan = new CopPlan();
                    copPlan = planFacade.Get(entity.CopPlan.Id);
                    if (copPlan != null)
                    {
                        //修改该计划的巡检周期，并根据新周期，计算出“下次巡检时间”
                        copPlan.CycTimeId = entity.CopPlan.CycTimeId;
                        CopCycTime copCycTime = new CopCycTime();
                        copCycTime = cycTimeFacade.Get(entity.CopPlan.CycTimeId);
                        if (copCycTime != null)
                        {
                            copPlan.CycTime = copCycTime.CycTime;
                            if (copPlan.StartCycTime != null)
                            {
                                DateTime startCycTime = Convert.ToDateTime(copPlan.StartCycTime);
                                copPlan.NextCycTime = startCycTime.AddMonths(Convert.ToInt32(copCycTime.CycTime)).ToShortDateString().ToString();
                            }
                        }

                        reasult = planFacade.Update(copPlan);
                    }
                }

                ShowSelect();
                return View();
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// 显示查询页面
        /// </summary>
        /// <param name="frameid"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Search(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View("Search");
        }


        public ActionResult GetAll()
        {
            IList<CopPlan> citys = planFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (CopPlan item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 生成下拉框
        /// </summary>
        public void ShowSelect()
        {
            //生成客户等级下拉框(根据“业务保障等级”)
            IList<ArchiveBizAssuranLeve> bizAssuranLeve = bizAssuranLeveFacade.LoadAll();
            ViewData["selBizAssuranLeveId"] = new SelectList(bizAssuranLeve, "Id", "AssuranLeveName");

            //生成巡检周期下拉框
            IList<CopCycTime> copCycTime = cycTimeFacade.LoadAll();
            ViewData["selCopCycTimeId"] = new SelectList(copCycTime, "Id", "CycTime");
        }

        /// <summary>
        /// 显示导入数据页
        /// </summary>
        /// <param name="frameid">菜单id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            return View();
        }

        /// <summary>
        /// 导入数据
        /// </summary>
        /// <param name="btnFlag"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult UploadFile(string btnFlag)
        {
            bool result = false; 
            string msg = "操作失败";

            try
            {
                string ShowInfo = ""; 
                string reFileName = "";
                string savePath = "../Upload/LoadFile/Plan\\";
                // string savePath = Server.MapPath("../Upload/LoadFile/Personnel\\");//上传文件保存路径
                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                //用户id
                string userid = this.currentUser.UserInfo.Id;
                if (!result)
                {
                    return Json(new { Type = result, Message = msg }, "text/html");
                }
                if (btnFlag == "0")
                {
                    //检测数据
                    result = planFacadeExImpl.CheckExcelData(strFileName, out msg, out  reFileName, aryTilte, 
                        beginLetter, endLetter);
                }
                else
                {
                    //导入数据库
                    result = planFacadeExImpl.SaveExcelData(strFileName, out msg, out  reFileName, aryTilte, beginLetter, 
                        endLetter, userid);
                }
                if (!result)
                {
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                }
                if (result)
                {
                    msg = ShowInfo + "<br>" + msg;
                } 
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch (Exception ex)
            {
                return Json(new { Type = false, Message = msg + ex.Message }, "text/html");
            }
        }


        /// <summary>
        /// 批量删除
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = planFacade.DeleteFlase(id.ToString(), out msg);
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, 
            string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO != "2")
            {
                gridsearch += " and rLine.DistrictId = '" + this.currentUser.UserInfo.Areaid + "' ";
            }
            return planFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
