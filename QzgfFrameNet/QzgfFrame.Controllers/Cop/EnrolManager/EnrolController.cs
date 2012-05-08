using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Cop.EnrolManager.Domain;
using QzgfFrame.Cop.EnrolManager.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Cop.CycTimeManager.Models;
using QzgfFrame.Cop.CycTimeManager.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.Resources.EquipmentManger.Models;
using System.Web;
using System.IO;
using QzgfFrame.Utility.Common.Helpers;
using System;
using System.Text;
using QzgfFrame.Controllers.Resources.EquipmentManger;
using Newtonsoft.Json;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.Cop.PlanManager.Domain;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using Microsoft.Office.Interop.Excel;

using SystemWeb = System.Web;
using SystemText = System.Text;
using SystemIO = System.IO;
using System.Data;
using SystemReflection = System.Reflection;
using MicrosoftWord = Microsoft.Office.Interop.Word;
using Microsoft.Office.Interop.Word;

namespace QzgfFrame.Controllers.Cop.EnrolManager
{
    /// <summary>
    /// 专线巡检登记
    /// </summary>
    public class EnrolController : BaseController
    {
        #region

        /// <summary>
        /// 专线巡检登记
        /// </summary>
        private EnrolFacade enrolFacade { set; get; }
        /// <summary>
        /// 业务保障等级
        /// </summary>
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        /// <summary>
        /// 巡检周期
        /// </summary>
        private CycTimeFacade cycTimeFacade { set; get; }
        /// <summary>
        /// 集团编号
        /// </summary>
        private GroupClieFacade groupClieFacade { set; get; }
        /// <summary>
        /// 专线类型
        /// </summary>
        private BizTypeFacade bizTypeFacade { set; get; }
        /// <summary>
        /// 巡检计划
        /// </summary>
        private PlanFacade planFacade { set; get; }
        /// <summary>
        /// 专线
        /// </summary>
        private DedicateLineFacade dedicateLineFacade { set; get; }

        private EnrolFacadeEx enrolFacadeEx { set; get; }

        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        /// <summary>
        /// 导入文件excel的首行标题
        /// </summary>
        private string[] aryTilte = { "专线类型","集团客户","业务保障等级","巡检周期","专线唯一编号"
                            ,"维护人员","巡检登记时间", "接入基站", "接入局端","客户联系人"
                            , "联系电话","详细地址", "连接情况", "电源线路检查","线路是否杂乱"
                            , "绑扎情况是否正常", "是否有隐患存在", "设备运行状态检查", "是否除尘","标签情况"
                            , "端口资料", "是否环境隐患存在", "环境隐患内容", "设备接地是否正常","尾纤标签"
                            , "网速测试", "下载测试", "是否正常登入", "平均延时","掉包率"
                            , "电话拨测", "传真拨测", "存在问题","上次问题解决情况", "ping 网关最小延时"
                            , "ping 网关最大延时", "ping 网关平均延时","ping 网关掉包率", "ping DNS最小延时", "ping DNS最大延时"
                            , "ping DNS平均延时", "ping DNS掉包率" 
                           };


        //导入文件列首字母
        string beginLetter = "A";
        //导入文件列末字母
        string endLetter = "AP";

        /// <summary>
        /// 上传文件保存路径
        /// </summary>
        string savePath = "~/Upload/LoadFile/CopEnrol\\";
        /// <summary>
        /// 保存导入文件的返回信息文件保存路径
        /// </summary>
        string saveMsgPath = "~/Upload/ImportDataResult/CopEnrol";
        string saveMsgPath1 = "../../../Upload/ImportDataResult/CopEnrol";
        /// <summary>
        /// 验证导入文件的返回信息文件保存路径
        /// </summary>
        string checkMsgPath = "~/Upload/CheckData/CopEnrol";
        string checkMsgPath1 = "../../../Upload/CheckData/CopEnrol";

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
            ViewData["leveNo"] = this.currentUser.UserInfo.LEVELNO.Trim();
            return View();
        }

        #region 基础操作

        /// <summary>
        /// 显示添加页面
        /// </summary>
        /// <param name="id">巡检计划的id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            Enrol result = new Enrol();
            result.CopPlanId = id;

            CopEnrol copEnrol = new CopEnrol();
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //根据"巡检计划的id",查询计划中出"专线类型","集团编号","业务保障等级","巡检周期"
                CopPlan copPlan = new CopPlan();
                copPlan = planFacade.Get(id);

                if (copPlan != null)
                {
                    ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
                    resourceDedicateLine = dedicateLineFacade.Get(copPlan.DedicateLineId);

                    if (resourceDedicateLine != null)
                    {
                        //接入基站
                        copEnrol.ConnectStation = resourceDedicateLine.StationName;
                        //接入局端
                        copEnrol.ConnectPort = resourceDedicateLine.StationTEquipAndPort;

                        //专线类型
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        archiveBizType = resourceDedicateLine.BizTypeId != null ?
                            bizTypeFacade.Get(resourceDedicateLine.BizTypeId) : null;
                        if (archiveBizType != null)
                        {
                            result.BizType = archiveBizType.BizTypeName;
                        }

                        //集团编号
                        ResourceGroupClie resourceGroupClie = new ResourceGroupClie();
                        resourceGroupClie = resourceDedicateLine.ClieId != null ?
                            groupClieFacade.Get(resourceDedicateLine.ClieId) : null;
                        if (resourceGroupClie != null)
                        {
                            result.GroupName = resourceGroupClie.ClieName;
                            copEnrol.Linkman = resourceGroupClie.GroupClieContacts;
                            copEnrol.Phone = resourceGroupClie.ClieContacTel;
                            copEnrol.Address = resourceGroupClie.ClieAddress;
                        }

                        //业务保障等级
                        ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                        archiveBizAssuranLeve = resourceDedicateLine.BizAssuranLeveId != null ?
                            bizAssuranLeveFacade.Get(resourceDedicateLine.BizAssuranLeveId) : null;
                        if (archiveBizAssuranLeve != null)
                        {
                            result.BizAssuranLeve = archiveBizAssuranLeve.AssuranLeveName;
                        }

                        //巡检周期
                        CopCycTime copCycTime = new CopCycTime();
                        copCycTime = copPlan.CycTimeId != null ? cycTimeFacade.Get(copPlan.CycTimeId) : null;
                        if (copCycTime != null)
                        {
                            result.CycTime = copCycTime.CycTime;
                        }
                        else
                        {
                            result.CycTime = copPlan.CycTime;
                        }
                    }
                }
                copEnrol.Id = "0";
                copEnrol.CopPlanId = id;
                result.CopEnrol = copEnrol;
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
            Enrol result = new Enrol();

            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                CopEnrol copEnrol = new CopEnrol();
                copEnrol = enrolFacade.Get(id);

                if (copEnrol != null)
                {
                    result.CopEnrol = copEnrol;

                    CopPlan copPlan = new CopPlan();
                    copPlan = planFacade.Get(copEnrol.CopPlanId);

                    ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
                    resourceDedicateLine = dedicateLineFacade.Get(copPlan.DedicateLineId);

                    //专线类型
                    ArchiveBizType archiveBizType = new ArchiveBizType();
                    archiveBizType = bizTypeFacade.Get(resourceDedicateLine.BizTypeId);
                    if (archiveBizType != null)
                    {
                        result.BizType = archiveBizType.BizTypeName;
                    }

                    //集团客户
                    ResourceGroupClie resourceGroupClie = new ResourceGroupClie();
                    resourceGroupClie = groupClieFacade.Get(resourceDedicateLine.ClieId);
                    if (resourceGroupClie != null)
                    {
                        result.GroupName = resourceGroupClie.ClieName;
                    }

                    //业务保障等级
                    ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                    archiveBizAssuranLeve = bizAssuranLeveFacade.Get(resourceDedicateLine.BizAssuranLeveId);
                    if (archiveBizAssuranLeve != null)
                    {
                        result.BizAssuranLeve = archiveBizAssuranLeve.AssuranLeveName;
                    }

                    //巡检周期
                    CopCycTime copCycTime = new CopCycTime();
                    copCycTime = copPlan.CycTimeId != null ? cycTimeFacade.Get(copPlan.CycTimeId) : null;
                    if (copCycTime != null)
                    {
                        result.CycTime = copCycTime.CycTime;
                    }
                    else
                    {
                        result.CycTime = copPlan.CycTime;
                    }
                }

                result.AttachmentName = copEnrol.AttachmentName;
                result.NewAttachmentName = copEnrol.NewAttachmentName;

                //设置文件下载路径
                //判断该文件夹是否存在，没有的话就创建
                string strsavePath = "";
                strsavePath = HttpContext.Request.MapPath(savePath);
                if (!Directory.Exists(strsavePath))
                {
                    Directory.CreateDirectory(strsavePath);
                }
                string filepath = strsavePath + copEnrol.NewAttachmentName;
                ViewData["fileUrl"] = filepath;

            }
            return View(result);
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, Enrol entity, HttpPostedFileBase from)
        {
            bool bools = false;
            string msg = "操作失败！";
            string sql = "";
            //操作类型（添加/编辑）：添加巡检登记，将返回巡检计划页面；编辑巡检登记，将返回巡检登记页面
            string status = "";
            //专线巡检计划菜单id
            string menuId = "";

            string newFileName = "";
            string fileName = "";
            CopEnrol copEnrol = new CopEnrol();

            try
            {
                if (Request.Files.Count != 0)
                {
                    var files = Request.Files[0];
                    //上传文件,Request.Files.Count 文件数为0上传不成功　　
                    if (Request.Files.Count == 0)
                    {
                        msg = "上传文件失败！";
                        return Json(new { Type = bools, Message = msg }, "text/html");
                    }

                    if (files.ContentLength > 0)
                    {
                        //文件大小不为0　　　　　　
                        HttpPostedFileBase file = Request.Files[0];
                        fileName = Path.GetFileName(file.FileName);
                        newFileName = dateNow.ToString("yyyyMMddHHmmss") + fileName;
                        bools = CommLoadFile.UploadXlsFiles(newFileName, file, savePath);

                        //获取上传文件的名称
                        entity.CopEnrol.NewAttachmentName = newFileName;
                        entity.CopEnrol.AttachmentName = fileName;
                    }
                }

                copEnrol = entity.CopEnrol;
                bool isbool = true;
                if (entity.CopEnrol.Id == "0")
                {
                    status = "Add";

                    //判断该计划在这个时间段是否已经进行巡检登记,有的话不允许再次登记
                    CopPlan copPlan = new CopPlan();
                    copPlan = planFacade.Get(entity.CopEnrol.CopPlanId);
                    if (copPlan.StartCycTime != null)
                    {
                        sql = " ( to_date(CycEnrolTime,'yyyy-mm-dd') between to_date('" + copPlan.StartCycTime + "','yyyy-mm-dd') ";
                        sql += " and to_date('" + copPlan.NextCycTime + "','yyyy-mm-dd') )  and ";
                        sql += " CopPlanId = '" + entity.CopEnrol.CopPlanId + "' and IsDelete = 0 ";
                        IList<CopEnrol> copEnrols = new List<CopEnrol>();
                        copEnrols = enrolFacade.LoadAll("id", sql);
                        if (copEnrols.Count != 0)
                        {
                            isbool = false;
                        }
                    }

                    if (isbool)
                    {
                        //维护人员id
                        copEnrol.CycEnrolTime = dateNow.ToShortDateString().ToString();
                        copEnrol.CopPlanId = entity.CopPlanId;
                        copEnrol.CreationTime = dateNow;
                        bools = this.enrolFacade.Save(copEnrol);

                        //添加完“巡检登记”，修改“巡检计划”中的“起始巡检时间”(当前巡检登记时间)，
                        //并根据“巡检计划”中的“巡检周期”，计算出“下次巡检时间”；
                        copPlan.StartCycTime = dateNow.ToShortDateString();
                        copPlan.NextCycTime = dateNow.AddMonths(Convert.ToInt32(copPlan.CycTime)).ToShortDateString().ToString();

                        bools = planFacade.Update(copPlan);
                    }
                    else
                    {
                        msg = "该计划已经巡检登记过!";
                    }
                }
                else
                {
                    //判断该计划在这个时间段是否已经进行巡检登记,有的话不允许再次登记
                    CopPlan copPlan = new CopPlan();
                    copPlan = planFacade.Get(entity.CopEnrol.CopPlanId);
                    if (copPlan.StartCycTime != null)
                    {
                        sql = " ( to_date(CycEnrolTime,'yyyy-mm-dd') between to_date('" + copPlan.StartCycTime + "','yyyy-mm-dd') ";
                        sql += " and to_date('" + copPlan.NextCycTime + "','yyyy-mm-dd') )  and ";
                        sql += " CopPlanId = '" + entity.CopEnrol.CopPlanId + "' and id != '" + entity.CopEnrol.Id + "'";
                        IList<CopEnrol> copEnrols = new List<CopEnrol>();
                        copEnrols = enrolFacade.LoadAll("id", sql);
                        if (copEnrols.Count != 0)
                        {
                            isbool = false;
                        }
                    }

                    if (isbool)
                    {
                        status = "Edit";
                        bools = this.enrolFacade.Update(copEnrol);
                    }
                    else
                    {
                        msg = "该计划已经巡检登记过!";
                    }
                }
                if (bools) msg = "操作成功！";
                return Json(new { Type = bools, Message = msg, Status = status, MenuId = menuId }, "text/html");
            }
            catch
            {
                return Json(new { Type = bools, Message = msg, Status = status }, "text/html");
            }
        }

        /// <summary>
        ///批量删除
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = enrolFacade.Delete(id.ToString());
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

        #endregion 基础操作

        #region 文件操作

        /// <summary>
        /// 删除附件
        /// </summary>
        /// <param name="id">工单id</param>
        /// <returns></returns>
        public ActionResult DeleteFile(string id)
        {
            CopEnrol copEnrol = new CopEnrol();
            copEnrol = enrolFacade.Get(id);
            copEnrol.AttachmentName = "";
            copEnrol.NewAttachmentName = "";
            this.enrolFacade.Update(copEnrol);

            Enrol result = new Enrol();
            //编辑时返回具体值
            copEnrol = enrolFacade.Get(id);

            if (copEnrol != null)
            {
                result.CopEnrol = copEnrol;

                CopPlan copPlan = new CopPlan();
                copPlan = planFacade.Get(copEnrol.CopPlanId);

                ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
                resourceDedicateLine = dedicateLineFacade.Get(copPlan.DedicateLineId);

                //专线类型
                ArchiveBizType archiveBizType = new ArchiveBizType();
                archiveBizType = bizTypeFacade.Get(resourceDedicateLine.BizTypeId);
                if (archiveBizType != null)
                {
                    result.BizType = archiveBizType.BizTypeName;
                }

                //集团客户
                ResourceGroupClie resourceGroupClie = new ResourceGroupClie();
                resourceGroupClie = groupClieFacade.Get(resourceDedicateLine.ClieId);
                if (resourceGroupClie != null)
                {
                    result.GroupName = resourceGroupClie.ClieName;
                }

                //业务保障等级
                ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                archiveBizAssuranLeve = bizAssuranLeveFacade.Get(resourceDedicateLine.BizAssuranLeveId);
                if (archiveBizAssuranLeve != null)
                {
                    result.BizAssuranLeve = archiveBizAssuranLeve.AssuranLeveName;
                }

                //巡检周期
                CopCycTime copCycTime = new CopCycTime();
                copCycTime = copPlan.CycTimeId != null ? cycTimeFacade.Get(copPlan.CycTimeId) : null;
                if (copCycTime != null)
                {
                    result.CycTime = copCycTime.CycTime;
                }
                else
                {
                    result.CycTime = copPlan.CycTime;
                }
            }

            result.AttachmentName = copEnrol.AttachmentName;
            result.NewAttachmentName = copEnrol.NewAttachmentName;

            //设置文件下载路径
            //判断该文件夹是否存在，没有的话就创建
            string strsavePath = "";
            strsavePath = HttpContext.Request.MapPath(savePath);
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            string filepath = strsavePath + copEnrol.NewAttachmentName;
            ViewData["fileUrl"] = filepath;

            return View("Edit", result);
        }

        /// <summary>
        /// 显示导入页面
        /// </summary>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile(string frameid)
        {
            ViewData["frameid"] = frameid;
            ViewData["downFileName"] = "专线巡检登记.xlsx";
            ViewData["checkMsgPath"] = checkMsgPath1;
            ViewData["saveMsgPath"] = saveMsgPath1;
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
                    result = enrolFacadeEx.CheckExcelData(strFileName, out msg, out reFileName, aryTilte,
                        beginLetter, endLetter, checkMsgPath);//检测数据
                }
                else
                {
                    result = enrolFacadeEx.SaveExcelData(strFileName, out msg, out reFileName, aryTilte, beginLetter,
                        endLetter, saveMsgPath, userid);//导入数据库
                }
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
                result = enrolFacadeEx.ExcelOut(tt, aryTilte);
                return View();
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// 下载文件
        /// </summary>
        /// <param name="AttachmentName">文件名称</param>
        public void DownFile(string id)
        {
            string fileName = id;

            //判断该文件夹是否存在，没有的话就创建
            string strsavePath = "";
            strsavePath = HttpContext.Request.MapPath(savePath);
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            string filepath = strsavePath + fileName;

            FileStream fs = new FileStream(filepath, FileMode.Open);// 设置文件流,filePath为文件路径
            byte[] bytes = new byte[(int)fs.Length];
            fs.Read(bytes, 0, bytes.Length);  // 读取
            fs.Close();
            Response.ClearContent();// 清楚缓冲区所有内容
            Response.ClearHeaders();  // 清楚缓冲区所有头
            Response.ContentType = "application/octet-stream";  // 设置输出流的Http MIME类型//通知浏览器下载文件而不是打开
            Response.AddHeader("Content-Disposition", "attachment; filename=" +
            HttpUtility.UrlEncode(fileName, Encoding.UTF8)); //fileName为需要下载的文件名
            Response.BinaryWrite(bytes);  // 写入输入流
            Response.Flush();  // 向客户端发送数据流
            Response.End();
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
                bools = enrolFacadeEx.WordOut(wordSavePath, id, out msg);

                return Json(new { Type = bools, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = bools, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }

        #endregion 文件操作

        public ActionResult GetAll()
        {
            IList<CopEnrol> citys = enrolFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (CopEnrol item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, 
            string gridviewname, string gridsearch)
        {
            if (this.currentUser.UserInfo.LEVELNO != "2")
            {
                gridsearch += " and rLine.DistrictId = '" + this.currentUser.UserInfo.Areaid + "' ";
            }

            return enrolFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }
    }
}
