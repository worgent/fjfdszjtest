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

namespace QzgfFrame.Controllers.Supervision.AuditManager
{
    public class AuditController : BaseController
    {
        private OrdersFacade ordersFacade { set; get; }
        private SendFacade sendFacade { set; get; }
        private BackFacade backFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        //
        // GET: /User/Home/Edit/5
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

        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            result = ordersFacade.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }

        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            //查询条件,如果是“与”条件，以“，”号为分割
            string[] gridsearchs = null;
            if (gridsearch != null)
            {
                gridsearchs = gridsearch.Split(',');
            }
            return ordersFacade.FindByPage(page, pagesize, gridsearchs);
        }

        /// <summary>
        /// 审批工单
        /// </summary>
        /// <param name="id">工单id</param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Approval(string id)
        {
            Order order = new Order();
            try
            {
                if (id != null)
                {
                    string[] ids = null;
                    ids = id.Split(',');
                    SupervisionSend supervisionSend = sendFacade.Get(ids[1]);
                    SupervisionOrders supervisionOrders = ordersFacade.Get(ids[0]);

                    if (supervisionOrders.AuditStaff != null)
                    {
                        order.AuditStaff = supervisionOrders.AuditStaff;
                    }
                    order.OrdersOvertimeNum = supervisionOrders.OrdersOvertimeNum;
                    order.BackOvertimeNum = supervisionOrders.BackOvertimeNum;
                    order.ReturnNum = supervisionOrders.ReturnNum;
                    if (supervisionOrders.BackAttachmentName != null)
                    {
                        order.BackAttachment = supervisionOrders.BackAttachmentName;
                    }
                    order.OrderId = ids[0];
                    order.SupervisionSend = supervisionSend;
                }
                ShowSelect();
                return View(order);
            }
            catch
            {
                return View(order);
            }
        }

        /// <summary>
        /// 审批工单操作
        /// </summary>
        /// <param name="id"></param>
        /// <param name="entity"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Approval(Order order)
        {
            try
            {
                if (order.OrderId != null)
                {
                    SupervisionOrders supervisionOrders = new SupervisionOrders();
                    SupervisionBack supervisionBack = new SupervisionBack();

                    if (order.Status == "4")
                    {
                        supervisionBack.ApprovalStaff = "admin";
                        supervisionBack.OrdersId = order.OrderId;
                        supervisionBack.ReturnReason = order.SupervisionBack.ReturnReason;
                        supervisionBack.ReturnTime = DateTime.Now;
                        supervisionBack.ReBackTimeLimit = order.SupervisionBack.ReBackTimeLimit;
                        backFacade.Save(supervisionBack);

                        supervisionOrders = ordersFacade.Get(order.OrderId);
                        supervisionOrders.BackOvertimeNum = supervisionOrders.BackOvertimeNum + 1;
                        supervisionOrders.Status = order.Status;
                        supervisionOrders.ApprovalStatus = "1";
                        ordersFacade.Update(supervisionOrders);
                    }
                    else if (order.Status == "3")
                    {
                        supervisionOrders = ordersFacade.Get(order.OrderId);
                        supervisionOrders.Status = order.Status;
                        supervisionOrders.ApprovalStatus = "1";
                        ordersFacade.Update(supervisionOrders);
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
        /// 查看详细
        /// </summary>
        /// <param name="id"></param>
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
        /// 生成下拉框
        /// </summary>
        public void ShowSelect()
        {
            //生成“审批”下拉框
            List<SelectListItem> items = new List<SelectListItem>();
            items.Add(new SelectListItem { Text = "通过", Value = "3" });
            items.Add(new SelectListItem { Text = "退回", Value = "4" });
            SelectList selItems = new SelectList(items.AsEnumerable(), "Value", "Text");
            ViewData["selStatus"] = selItems;
        }
    }
}
