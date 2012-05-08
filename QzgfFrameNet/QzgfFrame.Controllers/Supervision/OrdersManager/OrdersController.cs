using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.Supervision.OrdersManager.Domain;
using System.Web.Mvc;
using QzgfFrame.Supervision.OrdersManager.Models;
using System.Collections;
using QzgfFrame.Supervision.SendManager.Models;
using QzgfFrame.Supervision.SendManager.Domain;
using QzgfFrame.Supervision.BackManager.Models;
using QzgfFrame.Supervision.BackManager.Domain;
using System.IO;
using System.Web;
using QzgfFrame.Utility.Common.Helpers;

namespace QzgfFrame.Controllers.Supervision.OrdersManager
{
    public class OrdersController : BaseController
    {
        private OrdersFacade ordersFacade { set; get; }
        private SendFacade sendFacade { set; get; }
        private BackFacade backFacade { set; get; }

        //上传文件保存路径
        string savePath = "~/Upload/LoadFile\\";

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }

        /// <summary>
        /// 显示编辑页面
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Edit(string id)
        {
            SupervisionOrders result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = ordersFacade.Get(id);
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
        public ActionResult Edit(string id, SupervisionOrders entity)
        {
            try
            {
                if (id == "0")
                {
                    //entity.DispatchTime = DateTime.Now;
                    this.ordersFacade.Save(entity);
                }
                else
                {
                    this.ordersFacade.Update(entity);
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
            IList<SupervisionOrders> citys = ordersFacade.LoadAll();
            if (citys.Count <= 0)
                return Json(new { success = false });
            IList mapList = new ArrayList();
            foreach (SupervisionOrders item in citys)
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
            result = ordersFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        /// <summary>
        /// 显示所接工单
        /// </summary>
        /// <param name="id">工单id</param>
        /// /// <param name="sendId">派遣单id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Order(string id)
        {
            Order Order = new Order();
            try
            {
                if (id != null)
                {
                    string[] idsAry = null;
                    idsAry = id.Split(',');
                    if (idsAry[0] != null)
                    {
                        SupervisionSend supervisionSend = sendFacade.Get(idsAry[1]);
                        Order.SupervisionSend = supervisionSend;
                        Order.OrderId = idsAry[0];
                    }
                }

                //设置文件下载路径
                //判断该文件夹是否存在，没有的话就创建
                string strsavePath = "";
                strsavePath = HttpContext.Request.MapPath(savePath);
                if (!Directory.Exists(strsavePath))
                {
                    Directory.CreateDirectory(strsavePath);
                }
                string filepath = strsavePath + Order.SupervisionSend.NewAttachmentName;
                ViewData["fileUrl"] = filepath;

                return View(Order);
            }
            catch
            {
                return View(Order);
            }
        }

        /// <summary>
        /// 接单操作
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Order(Order order)
        {
            try
            {
                SupervisionOrders supervisionOrders = new SupervisionOrders();
                SupervisionSend supervisionSend = new SupervisionSend();
                if (order.OrderId != null)
                {
                    supervisionOrders = ordersFacade.Get(order.OrderId);
                    supervisionOrders.AuditStaff = order.AuditStaff;
                    supervisionOrders.ReviewedPhone = order.ReviewedPhone;
                    supervisionOrders.OrdersTime = DateTime.Now;
                    //如果"接单时间"大于"接单时限"，接单超时次数将加1
                    if (supervisionOrders.OrdersTime > order.SupervisionSend.LimitOrders)
                    {
                        supervisionOrders.OrdersOvertimeNum = supervisionOrders.OrdersOvertimeNum + 1;
                    }
                    supervisionOrders.Status = "1";
                    ordersFacade.Update(supervisionOrders);
                }
                return View();
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// 显示回单
        /// </summary>
        /// <param name="id">工单id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Back(string id)
        {
            Order order = new Order();
            try
            {
                if (id != null)
                {
                    string[] idsAry = null;
                    idsAry = id.Split(',');
                    if (idsAry[0] != null)
                    {
                        SupervisionSend supervisionSend = sendFacade.Get(idsAry[1]);
                        SupervisionOrders supervisionOrders = ordersFacade.Get(idsAry[0]);

                        if (supervisionOrders.AuditStaff != null)
                        {
                            order.AuditStaff = supervisionOrders.AuditStaff;
                        }
                        order.OrdersOvertimeNum = supervisionOrders.OrdersOvertimeNum;
                        order.BackOvertimeNum = supervisionOrders.BackOvertimeNum;
                        order.OrderId = idsAry[0];
                        order.SupervisionSend = supervisionSend;
                    }
                }
                return View(order);
            }
            catch 
            {
                return View(order);
            }
        }

        /// <summary>
        /// 回单操作
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Back(Order order, HttpPostedFileBase from)
        {
            bool bools = false;
            string msg = "操作失败！";
            string newFileName = "";
            string fileName = "";
            try
            {
                SupervisionOrders supervisionOrders = new SupervisionOrders();

                //上传文件,Request.Files.Count 文件数为0上传不成功　　
                if (Request.Files.Count == 0)
                {
                    return Json(new { Type = bools, Message = msg }, "text/x-json");
                }
                var files = Request.Files[0];
                if (files.ContentLength == 0)
                {
                    //文件大小大（以字节为单位）为0时，做一些操作　　　　　　
                    return Json(new { Type = bools, Message = msg }, "text/x-json");
                }
                else
                {
                    //文件大小不为0　　　　　　
                    HttpPostedFileBase file = Request.Files[0];
                    fileName = Path.GetFileName(file.FileName);
                    newFileName = DateTime.Now.ToString("yyyyMMddHHmmss") + fileName;
                    bools = CommLoadFile.UploadXlsFiles(newFileName, file, savePath);

                    //获取上传文件的名称
                    supervisionOrders.NewBackAttachmentName = newFileName;
                    supervisionOrders.BackAttachmentName = fileName;
                }


                if (order.OrderId != null)
                {
                    supervisionOrders = ordersFacade.Get(order.OrderId);
                    supervisionOrders.BackTime = DateTime.Now;
                    //如果"回单时间"大于"回单时限"，回单超时次数将加1
                    if (supervisionOrders.BackTime > order.SupervisionSend.LimitBack)
                    {
                        supervisionOrders.BackOvertimeNum = supervisionOrders.BackOvertimeNum + 1;
                    }
                    supervisionOrders.Status = "2";
                    supervisionOrders.ApprovalStatus = "0";
                    ordersFacade.Update(supervisionOrders);
                }
                return View();
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// 查看工单详细
        /// </summary>
        /// <param name="id">工单id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Detailed(string id)
        {
            Order order = new Order();
            try
            {
                if (id != null)
                {
                    string[] idsAry = null;
                    idsAry = id.Split(',');
                    if (idsAry[0] != null)
                    {
                        SupervisionSend supervisionSend = sendFacade.Get(idsAry[1]);
                        SupervisionOrders supervisionOrders = ordersFacade.Get(idsAry[0]);

                        if (supervisionOrders.AuditStaff != null)
                        {
                            order.AuditStaff = supervisionOrders.AuditStaff;
                        }
                        order.OrdersOvertimeNum = supervisionOrders.OrdersOvertimeNum;
                        order.BackOvertimeNum = supervisionOrders.BackOvertimeNum;
                        order.SupervisionSend = supervisionSend;
                    }
                }
                return View(order);
            }
            catch
            {
                return View(order);
            }
        }

        /// <summary>
        /// 显示重新回单
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult ReBack(string id)
        {
            Order Order = new Order();
            try
            {
                if (id != null)
                {
                    string[] idsAry = null;
                    idsAry = id.Split(',');
                    if (idsAry[0] != null)
                    {
                        SupervisionSend supervisionSend = sendFacade.Get(idsAry[1]);
                        IList<SupervisionBack> supervisionBack = backFacade.LoadAll("id", " OrdersId= '" + idsAry[0] + "'");
                        Order.SupervisionSend = supervisionSend;
                        Order.SupervisionBack = supervisionBack[0];
                        Order.OrderId = idsAry[0];
                    }
                }
                return View(Order);
            }
            catch
            {
                return View(Order);
            }
        }

        /// <summary>
        /// 重新回单操作
        /// </summary>
        /// <param name="order"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult ReBack(Order order)
        {
            try
            {
                SupervisionOrders supervisionOrders = new SupervisionOrders();
                if (order.OrderId != null)
                {
                    supervisionOrders = ordersFacade.Get(order.OrderId);
                    supervisionOrders.BackTime = DateTime.Now;
                    //如果"重新回单时间"大于"重新回单时限"，回单超时次数将加1
                    if (supervisionOrders.BackTime > order.SupervisionBack.ReBackTimeLimit)
                    {
                        supervisionOrders.BackOvertimeNum = supervisionOrders.BackOvertimeNum + 1;
                    }
                    supervisionOrders.Status = "2";
                    supervisionOrders.ApprovalStatus = "0";
                    supervisionOrders.BackAttachmentName = order.BackAttachment;
                    ordersFacade.Update(supervisionOrders);
                }
                return View();
            }
            catch
            {
                return View();
            }
        }

        /// <summary>
        /// 下载附件
        /// </summary>
        public void LoadFile()
        {
            
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            //查询条件
            string[] gridsearchs = null;
            if (gridsearch != null)
            {
                gridsearchs = gridsearch.Split(',');
            }
            return ordersFacade.FindByPage(page, pagesize, gridsearchs);
        }
    }
}
