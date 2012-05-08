using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipModelManger.Models;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Domain;
using QzgfFrame.Archives.SelfHelpEquipTypeManger.Models;
using QzgfFrame.Archives.SelfHelpFactoryManger.Domain;
using QzgfFrame.Archives.SelfHelpFactoryManger.Models;
using QzgfFrame.Utility.Core;
using Newtonsoft.Json;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Archives.SelfHelpEquipModelManger
{
    public class SelfHelpEquipModelController : BaseController
    {
        private SelfHelpEquipModelFacade selfHelpEquipModelFacade { set; get; }
        private SelfHelpFactoryFacade selfHelpFactoryFacade { set; get; }
        private SelfHelpEquipTypeFacade selfHelpEquipTypeFacade { set; get; }
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
        public ActionResult Add(string id)
        {
            IList<ArchiveSelfHelpFactory> factorys = selfHelpFactoryFacade.LoadAll();
            IList<ArchiveSelfHelpEquipType> equipTypes = selfHelpEquipTypeFacade.LoadAll();
            SelectList sList = new SelectList(factorys, "Id", "Abbrevia");
            SelectList selList = new SelectList(equipTypes, "Id", "SelfHelpEquipTypeName");
            ViewData["factory"] = sList;
            ViewData["equiptype"] = selList;
            var result = new ArchiveSelfHelpEquipModel();
            result.Id = "0";
            return View("Edit", result);
        }

        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult Modify(string id)
        {
            IList<ArchiveSelfHelpFactory> factorys = selfHelpFactoryFacade.LoadAll();
            IList<ArchiveSelfHelpEquipType> equipTypes = selfHelpEquipTypeFacade.LoadAll();
            SelectList sList = new SelectList(factorys, "Id", "Abbrevia");
            SelectList selList = new SelectList(equipTypes, "Id", "SelfHelpEquipTypeName");
            ViewData["factory"] = sList;
            ViewData["equiptype"] = selList;
            ArchiveSelfHelpEquipModel result = selfHelpEquipModelFacade.Get(id);
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
                    strsql = " SelfHelpFactoryId='" + idarr[0] + "' and SelfHelpEquipTypeId='" + idarr[1] + "'";
                }
                else if (idarr[0] == "" && idarr[1] != "")
                    strsql = " SelfHelpEquipTypeId='" + idarr[1] + "'";
                else if (idarr[0] != "" && idarr[1] == "")
                    strsql = " SelfHelpFactoryId='" + idarr[0] + "'";
                IList<ArchiveSelfHelpEquipModel> equipModels=null;
                if(strsql!="")
                   equipModels = selfHelpEquipModelFacade.LoadAll("Id", strsql);
                else
                    equipModels = selfHelpEquipModelFacade.LoadAll();
                if (equipModels.Count <= 0)
                    return Json(new { Type = false, mapList = JsonConvert.SerializeObject(null) }, JsonRequestBehavior.AllowGet);
                
                IList mapList = new ArrayList();
                foreach (ArchiveSelfHelpEquipModel item in equipModels)
                {
                    mapList.Add(new
                    {
                        id = item.Id,
                        text = item.ModelName,
                        typeId=item.SelfHelpEquipTypeId,
                        factoryId=item.SelfHelpFactoryId
                    });
                }
                return Json(new { Type = true, mapList = JsonConvert.SerializeObject(mapList) }, JsonRequestBehavior.AllowGet);
            }
            catch
            {
                return Json(new { Type = false, mapList = JsonConvert.SerializeObject(null) }, JsonRequestBehavior.AllowGet);
            }
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ArchiveSelfHelpEquipModel entity)
        {
            try
            {
                bool result = false;
                string msg = "操作失败";
                if (id == "0")
                    result = this.selfHelpEquipModelFacade.Save(entity);
                else
                    result = this.selfHelpEquipModelFacade.Update(entity);
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
            bool result = false; bool DelFlag = false;
            string msg = "操作失败";
            result = selfHelpEquipModelFacade.Delete(id.ToString(),out DelFlag);
            if (result) msg = "操作成功";
            if (DelFlag) msg += "，部份信息存在关联无法删除！！";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return selfHelpEquipModelFacade.FindByPage(page, pagesize);
        }
        public ActionResult GetEquipModel(string list)
        {
            try
            {
                //string list = Request.QueryString["q"].ToString();//获取参数
                list = Server.UrlDecode(list);
                IList<ArchiveSelfHelpEquipModel> result = selfHelpEquipModelFacade.LoadAll("Id", " upper(ModelName) like upper('%" + list + "%')");
                if (result.Count <= 0)
                    return Json(new { Type = false, Data = "" }, JsonRequestBehavior.AllowGet);
                IList mapList = new ArrayList();
                foreach (ArchiveSelfHelpEquipModel item in result)
                {
                    mapList.Add(new
                    {
                        Id = item.Id,
                        modelName = item.ModelName,
                        factoryId = item.SelfHelpFactoryId,
                        typeId = item.SelfHelpEquipTypeId
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
