using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using System.Web.Mvc;
using QzgfFrame.Cop.TerminalEnrolManager.Domain;
using QzgfFrame.Cop.TerminalEnrolManager.Models;
using System.Collections;
using QzgfFrame.Cop.TerminalPlanManager.Models;
using QzgfFrame.Cop.TerminalPlanManager.Domain;
using QzgfFrame.Cop.TerminalTimeManager.Domain;
using QzgfFrame.Cop.TerminalTimeManager.Models;
using QzgfFrame.Archives.OutletsTypeManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using System.Web;
using QzgfFrame.Resources.PersonnelManger.Models;
using QzgfFrame.Resources.PersonnelManger.Domain;

namespace QzgfFrame.Controllers.Cop.TerminalEnrolManager
{
    /// <summary>
    /// 自助终端巡检登记
    /// </summary>
    public class TerminalEnrolController : BaseController
    {
        #region 

        /// <summary>
        /// 自助终端巡检登记
        /// </summary>
        private TerminalEnrolFacade terminalEnrolFacade { set; get; }
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
        /// 人员档案
        /// </summary>
        private PersonnelFacade personnelFacade { get; set; }
        private TerminalEnrolFacadeEx terminalEnrolFacadeEx { get; set; }

        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        /// <summary>
        /// excel的首行标题
        /// </summary>
        private string[] aryTilte = { "自助终端", "网点类型", "巡检周期", "终端ID","维护人员"
                                        , "完成保养时间","使用单位", "联系人", "联系电话", "运行状态调校"
                                        , "处理清洁", "纸仓内清洁", "热敏头片清洁", "营业员培训与交流", "服务满意度" 
                                    };
        //导入文件列首字母
        string beginLetter = "A";
        //导入文件列末字母
        string endLetter = "S";
        /// <summary>
        /// 上传文件保存路径
        /// </summary>
        string savePath = "~/Upload/LoadFile/TerminalEnrol\\";
        /// <summary>
        /// 保存导入文件的返回信息文件保存路径
        /// </summary>
        string saveMsgPath = "~/Upload/ImportDataResult/CopEnrol";
        /// <summary>
        /// 验证导入文件的返回信息文件保存路径
        /// </summary>
        string checkMsgPath = "~/Upload/CheckData/CopEnrol";

        /// <summary>
        /// 保存生成的“巡检确认书”路径
        /// </summary>
        string wordSavePath = "D:\\";

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
            TerminalEnrol result = new TerminalEnrol();
            CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();

            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //根据"巡检计划的id",查询计划中出"专线类型","集团编号","业务保障等级","巡检周期"
                CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                copTerminalPlan = terminalPlanFacade.Get(id);

                if (copTerminalPlan != null)
                {
                    ResourceSelfHelpEquip resourceSelfHelpEquip = new ResourceSelfHelpEquip();
                    resourceSelfHelpEquip = selfHelpEquipFacade.Get(copTerminalPlan.SelfHelpEquipId);

                    if (copTerminalPlan != null)
                    {
                        result.SelfHelpEquip = resourceSelfHelpEquip.UseNetName;

                        //网点类型
                        ArchiveOutletsType archiveOutletsType = new ArchiveOutletsType();
                        archiveOutletsType = outletsTypeFacade.Get(resourceSelfHelpEquip.NetType);
                        if (archiveOutletsType != null)
                        {
                            result.OutletsType = archiveOutletsType.OutletsTypeName;
                        }

                        //巡检周期
                        CopTerminalTime copTerminalTime = new CopTerminalTime();
                        copTerminalTime = terminalTimeFacade.Get(copTerminalPlan.TerminalTimeId);
                        if (copTerminalTime != null)
                        {
                            result.TerminalTime = copTerminalTime.TerminalTime;
                        }
                    }
                }
                copTerminalEnrol.Id = "0";
                copTerminalEnrol.TerminalPlanId = id;
                result.CopTerminalEnrol = copTerminalEnrol;
            }
            return View("Edit", result);
        }

        /// <summary>
        /// 显示编辑页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            TerminalEnrol result = new TerminalEnrol();
            CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();

            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                copTerminalEnrol = terminalEnrolFacade.Get(id);
                if (copTerminalEnrol != null)
                {
                    CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                    copTerminalPlan = terminalPlanFacade.Get(copTerminalEnrol.TerminalPlanId);

                    if (copTerminalPlan != null)
                    {
                        ResourceSelfHelpEquip resourceSelfHelpEquip = new ResourceSelfHelpEquip();
                        resourceSelfHelpEquip = selfHelpEquipFacade.Get(copTerminalPlan.SelfHelpEquipId);

                        if (copTerminalPlan != null)
                        {
                            result.SelfHelpEquip = resourceSelfHelpEquip.UseNetName;

                            //网点类型
                            ArchiveOutletsType archiveOutletsType = new ArchiveOutletsType();
                            archiveOutletsType = outletsTypeFacade.Get(resourceSelfHelpEquip.NetType);
                            if (archiveOutletsType != null)
                            {
                                result.OutletsType = archiveOutletsType.OutletsTypeName;
                            }

                            //巡检周期
                            CopTerminalTime copTerminalTime = new CopTerminalTime();
                            copTerminalTime = terminalTimeFacade.Get(copTerminalPlan.TerminalTimeId);
                            if (copTerminalTime != null)
                            {
                                result.TerminalTime = copTerminalTime.TerminalTime;
                            }

                        }
                    }
                }
            }
            result.CopTerminalEnrol = copTerminalEnrol;
            return View(result);
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, TerminalEnrol entity)
        {
            bool reasult = false;
            string msg = "操作失败！";
            //操作类型（添加/编辑）：添加巡检登记，将返回巡检计划页面；编辑巡检登记，将返回巡检登记页面
            string status = "";
            string sql = "";
            bool isbool = true;
            CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();
            copTerminalEnrol = entity.CopTerminalEnrol;
            try
            {
                if (id == null)
                {
                    status = "Add";

                    //判断该计划在这个时间段是否已经进行巡检登记,有的话不允许再次登记
                    CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                    copTerminalPlan = terminalPlanFacade.Get(entity.CopTerminalEnrol.TerminalPlanId);
                    if (copTerminalPlan.StartTerminalTime != null)
                    {
                        sql = " ( to_date(TerminalEnrolTime,'yyyy-mm-dd') between to_date('" + copTerminalPlan.StartTerminalTime + "','yyyy-mm-dd') ";
                        sql += " and to_date('" + copTerminalPlan.NextTerminalTime + "','yyyy-mm-dd') )  and ";
                        sql += " TerminalPlanId = '" + entity.CopTerminalEnrol.TerminalPlanId + "' and IsDelete = 0 ";
                        IList<CopTerminalEnrol> copTerminalEnrols = new List<CopTerminalEnrol>();
                        copTerminalEnrols = terminalEnrolFacade.LoadAll("id", sql);
                        if (copTerminalEnrols.Count != 0)
                        {
                            isbool = false;
                        }
                    }

                    if (isbool)
                    {
                        //维护人员id
                        //copTerminalEnrol.PersonnelId = currentUser.UserInfo.Id;
                        copTerminalEnrol.TerminalEnrolTime = dateNow;
                        copTerminalEnrol.CreationTime = dateNow;
                        reasult = terminalEnrolFacade.Save(copTerminalEnrol);

                        //添加完“巡检登记”，修改“巡检计划”中的“起始巡检时间”(当前巡检登记时间)，
                        //并根据“巡检计划”中的“巡检周期”，计算出“下次巡检时间”；
                        copTerminalPlan.StartTerminalTime = dateNow.ToShortDateString();
                        copTerminalPlan.NextTerminalTime = dateNow.AddMonths(Convert.ToInt32(copTerminalPlan.TerminalTime)).ToShortDateString().ToString();

                        reasult = terminalPlanFacade.Update(copTerminalPlan);
                    }
                    else
                    {
                        msg = "该计划已经巡检登记过!";
                    }
                }
                else
                {
                    status = "Edit";
                    reasult = this.terminalEnrolFacade.Update(copTerminalEnrol);
                }

                if (reasult) msg = "操作成功！";
                return Json(new { Type = reasult, Message = msg, Status = status }, "text/html");
            }
            catch
            {
                return Json(new { Type = reasult, Message = msg, Status = status }, "text/html");
            }
        }

        public ActionResult GetAll()
        {
            IList<CopTerminalEnrol> citys = terminalEnrolFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (CopTerminalEnrol item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 实现导入页面
        /// </summary>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            ViewData["downFileName"] = "终端巡检登记.xlsx";
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
            bool result = false; string msg = "操作失败";
            try
            {
                string ShowInfo = ""; string reFileName = "";

                HttpPostedFileBase file = Request.Files[0];
                string strFileName = "";
                result = LoadFile(file, savePath, out strFileName, out msg, out ShowInfo);
                if (!result)
                {
                    return Json(new { Type = result, Message = msg }, "text/html");
                }

                string userid = this.currentUser.UserInfo.Id;
                if (btnFlag == "0")
                {
                    result = terminalEnrolFacadeEx.CheckExcelData(strFileName, out msg, out  reFileName, aryTilte,
                        beginLetter, endLetter, checkMsgPath);//检测数据
                }
                else
                {
                    result = terminalEnrolFacadeEx.SaveExcelData(strFileName, out msg, out  reFileName, aryTilte, beginLetter,
                        endLetter, saveMsgPath,userid);//导入数据库
                }
                GC.Collect();
                if (!result)
                {
                    return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
                }
                if (result)
                {
                    msg = ShowInfo;
                }
                return Json(new { Type = true, Message = msg, FileName = reFileName }, "text/html");
            }
            catch
            {
                GC.Collect();
                return Json(new { Type = false, Message = msg }, "text/html");
            }
        }

        /// <summary>
        /// 导出数据到excel
        /// </summary>
        /// <returns></returns>
        public ActionResult GetExcelOut()
        {
            bool result = false;
            //string msg = "操作失败";
            string[] tt = { };
            try
            {
                string orgId = currentUser.UserInfo.Id;
                result = terminalEnrolFacadeEx.ExcelOut(tt, aryTilte);
                return View();
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// 生成“巡检确认书”
        /// </summary>
        /// <param name="id">巡检登记id</param>
        public ActionResult WordOut(string id)
        {
            string msg = "";
            bool bools = false;
            try
            {
                bools = terminalEnrolFacadeEx.WordOut(wordSavePath, id, out msg);

                return Json(new { Type = bools, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = bools, Message = msg }, JsonRequestBehavior.AllowGet);
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
            result = terminalEnrolFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
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

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, 
            string gridsearch)
        {
            gridsearch += " and rEquip.DistrictId = '" + this.currentUser.UserInfo.Areaid + "' ";
            return terminalEnrolFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
