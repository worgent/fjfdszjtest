using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.Supervision.SendManager.Domain;
using System.Web.Mvc;
using QzgfFrame.Supervision.SendManager.Models;
using System.Collections;
using Newtonsoft.Json;
using QzgfFrame.Archives.UnitManger.Domain;
using QzgfFrame.Archives.UnitManger.Models;
using QzgfFrame.Supervision.OrdersManager.Domain;
using QzgfFrame.Supervision.OrdersManager.Models;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using System.Web;
using QzgfFrame.Utility.Common.Helpers;
using System.IO;

namespace QzgfFrame.Controllers.Supervision.SendManager
{
    /// <summary>
    /// 派遣工单
    /// </summary>
    public class SendController : BaseController
    {
        #region

        /// <summary>
        /// 督办工单
        /// </summary>
        private SendFacade sendFacade { set; get; }
        /// <summary>
        /// 区县管理
        /// </summary>
        private DistrictFacade districtFacade { set; get; }
        /// <summary>
        /// 下单/回单
        /// </summary>
        private OrdersFacade ordersFacade { set; get; }

        //上传文件保存路径
        string savePath = "~/Upload/LoadFile\\";
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

        #region 基础操作

        /// <summary>
        /// 显示添加页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            SupervisionSend result = new SupervisionSend();
            if (id == "0")
            {
                //新增时返回空对象
                result.Id = id;
            }
            else
            {
                //编辑时返回具体值
                result = sendFacade.Get(id);
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
            SupervisionSend result = new SupervisionSend();
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = sendFacade.Get(id);

                //设置文件下载路径
                //判断该文件夹是否存在，没有的话就创建
                string strsavePath = "";
                strsavePath = HttpContext.Request.MapPath(savePath);
                if (!Directory.Exists(strsavePath))
                {
                    Directory.CreateDirectory(strsavePath);
                }
                string filepath = strsavePath + result.NewAttachmentName;
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
        public ActionResult Edit(string id, SupervisionSend entity, HttpPostedFileBase from)
        {
            bool bools = true;
            string msg = "操作失败！";
            string newFileName = "";
            string fileName = "";
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
                        entity.NewAttachmentName = newFileName;
                        entity.AttachmentName = fileName;
                    }
                }

                if (bools)
                {
                    if (id == "0")
                    {
                        //entity.DispatchTime = DateTime.Now;
                        bools = this.sendFacade.Save(entity);
                    }
                    else
                    {
                        bools = this.sendFacade.Update(entity);
                    }
                }
                else
                {
                    msg = "上传文件失败！";
                }
                if (bools) msg = "操作成功！";

                return Json(new { Type = bools, Message = msg }, "text/html");
            }
            catch
            {
                return Json(new { Type = bools, Message = msg }, "text/html");
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
            result = sendFacade.DeleteFalse(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        #endregion 基础操作

        /// <summary>
        /// 删除附件
        /// </summary>
        /// <param name="id">工单id</param>
        /// <returns></returns>
        public ActionResult DeleteFile(string id)
        {
            SupervisionSend result = new SupervisionSend();
            result = sendFacade.Get(id);
            result.AttachmentName = "";
            result.NewAttachmentName = "";
            this.sendFacade.Update(result);

            return View("Edit", result);
        }

        /// <summary>
        /// 选择派遣对象
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Unit(string id)
        {
            Send send = new Send();
            try
            {
                if (id != "0")
                {
                    //先每个单位都派遣工单
                    IList<ArchiveDistrict> archiveDistrict = districtFacade.LoadAll();
                    send.archiveDistrict = archiveDistrict;

                    SupervisionSend result = null;
                    if (id == "0")
                    {
                        //新增时返回空对象
                    }
                    else
                    {
                        //编辑时返回具体值
                        result = sendFacade.Get(id);
                    }
                }
                ShowSelect();
                return View(send);
            }
            catch
            {
                return View(send);
            }
        }

        /// <summary>
        /// 生成下拉框
        /// </summary>
        public void ShowSelect()
        {
            //生成派遣单位下拉框
            IList<ArchiveDistrict> archiveDistrict = districtFacade.LoadAll();
            ViewData["selUnit"] = new SelectList(archiveDistrict, "Id", "DistrictName");
        }

        /// <summary>
        /// 添加派遣工单
        /// </summary>
        /// <param name="send"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Unit(Send entity)
        {
            bool bools = false;
            string msg = "操作失败!";
            try
            {
                List<UnitData[]> lst = null;
                if (entity.unitData != null)
                {
                    lst = (List<UnitData[]>)JavaScriptConvert.DeserializeObject(entity.unitData, typeof(List<UnitData[]>));
                }

                if (lst[0].Count() >= 1)
                {
                    for (int i = 0; i < lst[0].Count(); i++)
                    {
                        SupervisionOrders supervisionOrders = new SupervisionOrders();
                        supervisionOrders.SendId = entity.Id;
                        supervisionOrders.SendUnitName = lst[0][i].UnitName;
                        supervisionOrders.Status = "0";
                        supervisionOrders.SendingObject = lst[0][i].UnitName;
                        supervisionOrders.ReturnNum = 0;
                        supervisionOrders.OrdersOvertimeNum = 0;
                        supervisionOrders.BackOvertimeNum = 0;
                        bools = ordersFacade.Save(supervisionOrders);
                    }
                    ShowSelect();
                }

                if (bools) msg = "操作成功!";
                return Json(new { Type = bools, Message = msg }, "text/html");
            }
            catch
            {
                return Json(new { Type = bools, Message = msg }, "text/html");
            }
        }

        /// <summary>
        /// 生成"派遣单位"树
        /// </summary>
        /// <returns></returns>
        public string GetMenu()
        {
            var list = districtFacade.LoadAll();
            var jsonlist = (from a in list
                            select new
                            {
                                text = a.DistrictName,
                                id = a.Id,
                                url = "#",
                                pid = 0
                            }
            ).ToArray();

            return JsonConvert.SerializeObject(jsonlist);
        }

        /// <summary>
        /// 生成"派遣单位"带复选框的树
        /// </summary>
        /// <returns></returns>
        public ActionResult GetTreeData()
        {
            bool bools = false;
            string data = "";
            try
            {
                string sql = "";

                ArchiveDistrict archiveDistrict = new ArchiveDistrict();
                IList<ArchiveDistrict> archiveDistricts = new List<ArchiveDistrict>();

                //查找根结点
                sql = " parentid = '-1' ";
                archiveDistricts = districtFacade.LoadAll("id",sql);
                archiveDistricts = districtFacade.LoadAll();
                foreach (ArchiveDistrict archiveDistrict1 in archiveDistricts)
                {
                    if (archiveDistrict1.ParentId == "-1")
                    { 
                        
                    }
                }

                IList dateList = new ArrayList();
                

                return Json(new { Type = bools, Data = data }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = bools, Data = data }, JsonRequestBehavior.AllowGet);
            }
        }

        public ActionResult GetAll()
        {
            IList<SupervisionSend> citys = sendFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (SupervisionSend item in citys)
            {
                mapList.Add(new
                {
                    cycTimeid = item.Id,
                });
            }
            return Json(mapList, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, 
            string gridsearch)
        {
            return sendFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

    }
}
