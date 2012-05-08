using System;
using System.IO;
using System.Web;
using System.Web.Mvc;
using QzgfFrame.Warehouse.IODetailManger.Models;
using QzgfFrame.Warehouse.IODetailManger.Domain;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Warehouse.IODetailManger
{
    public class IODetailController : BaseController
    {
        private IODetailFacade ioDetailFacade { set; get; }

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
            WarehouseIODetail result = null;
            if (id == "0")
            {
                //新增时返回空对象
            }
            else
            {
                //编辑时返回具体值
                result = ioDetailFacade.Get(id);
            }
            return View(result);
        }

        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, WarehouseIODetail entity)
        {
            try
            {
                if (id == "0")
                    this.ioDetailFacade.Save(entity, "0");
                else
                    this.ioDetailFacade.Update(entity);
                return View();
            }
            catch
            {
                return View();
            }
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile()
        {
            return View();
        }
        /// <summary>
        /// 加载视图
        /// </summary>
        /// <returns></returns>
        public string LoadAllIODetail(string id)
        {
            string list=ioDetailFacade.LoadAllViewIODetail(id);
            return list;
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return ioDetailFacade.FindByPage(page, pagesize, gridsearch);
        }
    }
}
