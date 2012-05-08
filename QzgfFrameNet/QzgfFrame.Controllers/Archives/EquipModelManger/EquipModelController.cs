using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.equipModelManger
{
    public class EquipModelController : BaseController
    {
        private EquipModelFacade equipModelFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipTypeFacade equipTypeFacade { set; get; }
        /// <summary>
        /// 首页信息调用grid,时通过service取得数据
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            return View();
        }
        /// <summary>
        /// 入库,选择，
        /// </summary>
        /// <returns></returns>
        public ActionResult SelIndex(string id)
        {
            ViewData["tids"] = id;
            return View();
        }
        //
        // GET: /User/Home/Edit/5
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Add(string id)
        {
            IList<ArchiveFactory> factorys = factoryFacade.LoadAll();
            IList<ArchiveEquipType> equipTypes = equipTypeFacade.LoadAll();
            SelectList sList = new SelectList(factorys, "Id", "Abbrevia");
            SelectList selList = new SelectList(equipTypes, "Id", "EquipTypeName");
            ViewData["selfactory"] = sList;
            ViewData["selequiptype"] = selList;
            var result = new ArchiveEquipModel();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveFactory> factorys = factoryFacade.LoadAll();
            IList<ArchiveEquipType> equipTypes = equipTypeFacade.LoadAll();
            SelectList sList = new SelectList(factorys, "Id", "Abbrevia");
            SelectList selList = new SelectList(equipTypes, "Id", "EquipTypeName");
            ViewData["selfactory"] = sList;
            ViewData["selequiptype"] = selList;
            ArchiveEquipModel result = equipModelFacade.Get(id);
            return View("Edit", result);
        }
        public ActionResult Get(string id)
        {
            try
            {
                string[] idarr = id.Split(',');
                string strsql = "";
                if (idarr[0] != "" && idarr[1] != "" )
                {
                    strsql = " FactoryId='" + idarr[0] + "' and EquipTypeId='" + idarr[1] + "'";
                }
                else if (idarr[1] != "" &&idarr[0] =="")
                    strsql = " EquipTypeId='" + idarr[1] + "'";
                else if(idarr[0] != "" && idarr[1] == "")
                    strsql = " FactoryId='" + idarr[1] + "'";
                IList<ArchiveEquipModel> equipModels =new List<ArchiveEquipModel>();
                if(strsql!="")
                    equipModels = equipModelFacade.LoadAll("Id",strsql);
                else
                    equipModels = equipModelFacade.LoadAll();
                if (equipModels.Count <= 0)
                    return Json(new { Type = false, mapList = JsonConvert.SerializeObject(null) }, JsonRequestBehavior.AllowGet);
                
                IList mapList = new ArrayList();
                foreach (ArchiveEquipModel item in equipModels)
                {
                    mapList.Add(new
                    {
                        id = item.Id,
                        text = item.EquipModelName,
                        typeId=item.EquipTypeId,
                        factoryId=item.FactoryId
                    });
                }
                return Json(new { Type = true, mapList = JsonConvert.SerializeObject(mapList) }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, mapList = JsonConvert.SerializeObject(null) }, JsonRequestBehavior.AllowGet);
            }
        }

        public ActionResult GetDropDownList()
        {
            string[] arry = new string[2];
            IList<ArchiveFactory> factorys = factoryFacade.OSZLoadAll();
            IList<ArchiveEquipModel> equipModels = equipModelFacade.OSZLoadAll();
            arry[0] = JsonConvert.SerializeObject(factorys);
            arry[1] = JsonConvert.SerializeObject(equipModels);
            return Json(new { Type = true, Rows = arry }, JsonRequestBehavior.AllowGet);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveEquipModel entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.equipModelFacade.Save(entity);
                else
                    result = this.equipModelFacade.Update(entity);
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
            result = equipModelFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return equipModelFacade.FindByPage(page, pagesize, sortname, sortorder, gridsearch);
        }

        public ActionResult GetEquipModel(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<ArchiveEquipModel> result = equipModelFacade.LoadAll("Id", " upper(EquipModelName) like upper('%" + list + "%')");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ArchiveEquipModel item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        modelName = item.EquipModelName,
                        factoryId = item.FactoryId,
                        typeId = item.EquipTypeId
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
    }
}
