using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.NetworkNameManger.Domain;
using QzgfFrame.Archives.NetworkNameManger.Models;
using QzgfFrame.Archives.DistrictManger.Domain;
using QzgfFrame.Archives.DistrictManger.Models;
using QzgfFrame.Utility.Core;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.NetworkNameManger
{
    public class NetworkNameController : BaseController
    {
        private NetworkNameFacade networkNameFacade { set; get; }
        private DistrictFacade districtFacade { set; get; }

        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        public ActionResult GetNetworkName(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<ArchiveNetworkName> result = networkNameFacade.LoadAll("Id", " NetworkName like'%" + list + "%'");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ArchiveNetworkName item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        networkNo = item.NetworkNo,
                        networkName = item.NetworkName,
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
        public ActionResult GetNetworkNo(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<ArchiveNetworkName> result = networkNameFacade.LoadAll("Id", " NetworkNo like'%" + list + "%'");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ArchiveNetworkName item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        networkNo = item.NetworkNo,
                        networkName = item.NetworkName,
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
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["seldistrict"] = new SelectList(districts, "Id", "DistrictName");
            var result = new ArchiveNetworkName();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveDistrict> districts = districtFacade.LoadAll("SeqNO", "TypeFlag=3");
            ViewData["seldistrict"] = new SelectList(districts, "Id", "DistrictName");
            ArchiveNetworkName result = networkNameFacade.Get(id);
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveNetworkName entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.networkNameFacade.Save(entity);
                else
                    result = this.networkNameFacade.Update(entity);
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
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败"; bool DelFlag = false;
            result = networkNameFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return networkNameFacade.FindByPage(page, pagesize);
        }
    }
}
