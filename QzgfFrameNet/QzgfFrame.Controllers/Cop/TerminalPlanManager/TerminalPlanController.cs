using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using System.Web.Mvc;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Cop.TerminalPlanManager.Domain;
using QzgfFrame.Cop.TerminalTimeManager.Domain;
using QzgfFrame.Archives.OutletsTypeManger.Domain;
using QzgfFrame.Cop.TerminalPlanManager.Models;
using System.Collections;
using QzgfFrame.Cop.TerminalTimeManager.Models;
using QzgfFrame.Archives.OutletsTypeManger.Models;

namespace QzgfFrame.Controllers.Cop.TerminalPlanManager
{
    /// <summary>
    /// 自助终端巡检计划
    /// </summary>
    public class TerminalPlanController : BaseController
    {
        #region
        
        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        private TerminalPlanFacade terminalPlanFacade { set; get; }
        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        private TerminalTimeFacade terminalTimeFacade { set; get; }
        /// <summary>
        /// 网点类型
        /// </summary>
        private OutletsTypeFacade outletsTypeFacade { set; get; }
        /// <summary>
        /// 自助终端
        /// </summary>
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }

        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        #endregion

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
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
            ShowSelect();
            TerminalPlan plan = new TerminalPlan();
            CopTerminalPlan result = new CopTerminalPlan();

            if (id == "0")
            {
                result.Id = id;
            }
            else
            {
                //编辑时返回具体值
                result = terminalPlanFacade.Get(id);
            }
            plan.CopTerminalPlan = result;
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
            ShowSelect();
            TerminalPlan terminalPlan = new TerminalPlan();
            CopTerminalPlan copTerminalPlan = new CopTerminalPlan();

            if (id == "0")
            {
                copTerminalPlan.Id = id;
            }
            else
            {
                //编辑时返回具体值
                copTerminalPlan = terminalPlanFacade.Get(id);
                if(copTerminalPlan != null)
                {
                    ResourceSelfHelpEquip resourceSelfHelpEquip = new ResourceSelfHelpEquip();
                    resourceSelfHelpEquip = selfHelpEquipFacade.Get(copTerminalPlan.SelfHelpEquipId);
                    if(resourceSelfHelpEquip != null)
                    {
                        terminalPlan.SelfHelpEquip = resourceSelfHelpEquip.UseNetName;
                        ArchiveOutletsType archiveOutletsType = new ArchiveOutletsType();
                        archiveOutletsType = outletsTypeFacade.Get(resourceSelfHelpEquip.NetType);
                        terminalPlan.OutletsType = archiveOutletsType != null ? archiveOutletsType.OutletsTypeName : "";  
                    }
                }
            }
            terminalPlan.CopTerminalPlan = copTerminalPlan;
            
            return View("Edit", terminalPlan);
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, TerminalPlan entity)
        {
            ShowSelect();
            bool reasult = false;
            string msg = "";
            string sql = "";
            try
            {
                if (id == null)
                {//添加

                    //到"自助终端"管理中,查找出所有"网点类型"一样的.并根据查询出来的条数,插入相应条数的数据
                    sql = " NetType = '" + entity.ResourceSelfHelpEquip.NetType + "'  and DelFlag != 1 or DelFlag is null";
                    IList<ResourceSelfHelpEquip> resourceSelfHelpEquips = new List<ResourceSelfHelpEquip>();
                    resourceSelfHelpEquips = selfHelpEquipFacade.LoadAll("id", sql);

                    foreach (var resourceSelfHelpEquip in resourceSelfHelpEquips)
                    {
                        //判断该“自助终端”的巡检计划是否存在
                        IList<CopTerminalPlan> copTerminalPlans = new List<CopTerminalPlan>();
                        sql = " SelfHelpEquipId = '" + resourceSelfHelpEquip.Id + "' and IsDelete = 0";
                        copTerminalPlans = terminalPlanFacade.LoadAll("id", sql);
                        if (copTerminalPlans.Count == 0)
                        {
                            CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                            copTerminalPlan.SelfHelpEquipId = resourceSelfHelpEquip.Id;
                            copTerminalPlan.TerminalTimeId = entity.CopTerminalPlan.TerminalTimeId;
                            CopTerminalTime copTerminalTime = new CopTerminalTime();
                            copTerminalTime = terminalTimeFacade.Get(entity.CopTerminalPlan.TerminalTimeId);
                            copTerminalPlan.TerminalTime = copTerminalTime != null ? copTerminalTime.TerminalTime : "";

                            //生成计划的时候,"巡检起始时间"与"下次巡检时间"都为空，
                            copTerminalPlan.StartTerminalTime = "";
                            copTerminalPlan.NextTerminalTime = "";
                            copTerminalPlan.CreationTime = dateNow;

                            terminalPlanFacade.Save(copTerminalPlan);
                        }
                    }
                }
                else
                {//编辑 

                    CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                    copTerminalPlan = terminalPlanFacade.Get(entity.CopTerminalPlan.Id);
                    if (copTerminalPlan != null)
                    {
                        //修改该计划的巡检周期，并根据新周期，计算出“下次巡检时间”
                        copTerminalPlan.TerminalTimeId = entity.CopTerminalPlan.TerminalTimeId;
                        CopTerminalTime copTerminalTime = new CopTerminalTime();
                        copTerminalTime = terminalTimeFacade.Get(entity.CopTerminalPlan.TerminalTimeId);
                        if (copTerminalTime != null)
                        {
                            copTerminalPlan.TerminalTime = copTerminalTime.TerminalTime;
                            if (copTerminalPlan.StartTerminalTime != null)
                            {
                                DateTime startCycTime = Convert.ToDateTime(copTerminalPlan.StartTerminalTime);
                                copTerminalPlan.NextTerminalTime = startCycTime.AddMonths(Convert.ToInt32(copTerminalTime.TerminalTime)).ToShortDateString().ToString();
                            }
                        }

                        reasult = terminalPlanFacade.Update(copTerminalPlan);
                    }
                }


                return View();
            }
            catch
            {
                return View();
            }
        }

        public ActionResult GetAll()
        {
            IList<CopTerminalPlan> citys = terminalPlanFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (CopTerminalPlan item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
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
            result = terminalPlanFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 生成下拉框
        /// </summary>
        public void ShowSelect()
        {
            //生成"网点类型"下拉框
            IList<ArchiveOutletsType> aArchiveOutletsType = new List<ArchiveOutletsType>();
            aArchiveOutletsType = outletsTypeFacade.LoadAll();
            if (aArchiveOutletsType != null)
            {
                ViewData["ArchiveOutletsTypeList"] = new SelectList(aArchiveOutletsType, "Id", "OutletsTypeName");
            }

            //生成巡检周期下拉框
            IList<CopTerminalTime> copTerminalTime = new List<CopTerminalTime>();
            copTerminalTime = terminalTimeFacade.LoadAll();
            if (copTerminalTime != null)
            {
                ViewData["CopTerminalTimeList"] = new SelectList(copTerminalTime, "Id", "TerminalTime");
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

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname
            , string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO != "2")
            {
                gridsearch += " and rEquip.DistrictId = '" + this.currentUser.UserInfo.Areaid + "' ";
            }
            return terminalPlanFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
