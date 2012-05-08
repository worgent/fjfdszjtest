using System;
using System.IO;
using System.Web;
using System.Data;
using System.Web.Mvc;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Resources.OneSevenZeroManger.Domain;
using QzgfFrame.Resources.OneSevenZeroManger.Models;
using QzgfFrame.Archives.FactoryManger.Models;
using QzgfFrame.Archives.FactoryManger.Domain;
using QzgfFrame.Archives.EquipTypeManger.Models;
using QzgfFrame.Archives.EquipTypeManger.Domain;
using QzgfFrame.Archives.EquipModelManger.Models;
using QzgfFrame.Archives.EquipModelManger.Domain;
using QzgfFrame.Resources.ClieEquipManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Models;
using QzgfFrame.Resources.FiberCoreManger.Domain;
using QzgfFrame.Resources.NumberGroupManger.Domain;
using QzgfFrame.Resources.NumberGroupManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Models;
using QzgfFrame.Resources.OtherInfoManger.Domain;
using QzgfFrame.Archives.SignalModelManger.Models;
using QzgfFrame.Archives.SignalModelManger.Domain;
using QzgfFrame.Resources.EquipmentManger.Models;
using QzgfFrame.Resources.EquipmentManger.Domain;
using QzgfFrame.Utility.Core;
using System.Text.RegularExpressions;
using BaseController = QzgfFrame.Controllers.CommonSupport.BaseController;

namespace QzgfFrame.Controllers.Resources.OneSevenZeroManger
{
    public class OneSevenZeroController : BaseController
    {
        private OneSevenZeroFacade oneSevenZeroFacade { set; get; }
        private FactoryFacade factoryFacade { set; get; }
        private EquipTypeFacade equipTypeFacade { set; get; }
        private EquipModelFacade equipModelFacade { set; get; }
        private OneSevenZeroFacadeEx oneSevenZeroFacadeEx { set; get; }

        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        private NetworkingModeFacade networkingModeFacade { set; get; }
        private GroupClieFacade groupClieFacade { set; get; }
        private BizTypeFacade bizTypeFacade { set; get; }
        private DedicateLineFacade dedicateLineFacade { set; get; }
        private FiberCoreFacade fiberCoreFacade { set; get; }
        private NumberGroupFacade numberGroupFacade { set; get; }
        private OtherInfoFacade otherInfoFacade { set; get; }
        private SignalModelFacade signalModelFacade { set; get; }
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
        public ActionResult Modify(string id)
        {
            OneSevenZeroLine result = new OneSevenZeroLine();
            // ResourceOneSevenZero OneSevenZero = new ResourceOneSevenZero();
            ResourceOneSevenZero OneSevenZero = oneSevenZeroFacade.Get(id);
            result.OneSevenZero = OneSevenZero;
            string where = " BizTypeName='" + OneSevenZero.OperationType + "'";
            ArchiveBizType biztype = bizTypeFacade.Get("Id", where);
            if (biztype == null)
                biztype = new ArchiveBizType();
            ArchiveNetworkingMode networkmode = networkingModeFacade.GetHql(OneSevenZero.OrgNetMode);
            if (networkmode == null)
                networkmode = new ArchiveNetworkingMode();
            
            ArchiveBizAssuranLeve bizAssuranLeve = bizAssuranLeveFacade.GetHql(OneSevenZero.EnsureGrade);
            if (bizAssuranLeve == null)
                bizAssuranLeve = new ArchiveBizAssuranLeve();
            IList<ArchiveBizAssuranLeve> bizAssurans = bizAssuranLeveFacade.LoadAll();
            IList<ArchiveNetworkingMode> networkingModes = networkingModeFacade.LoadAll();
            IList<ResourceGroupClie> groupClies = groupClieFacade.LoadAll();
            ViewData["selbizassuran"] = new SelectList(bizAssurans, "Id", "AssuranLeveName");
            ViewData["selnetwork"] = new SelectList(networkingModes, "Id", "ModeName");
            ViewData["selgroupclie"] = new SelectList(groupClies, "Id", "ClieNo");
            IList<ArchiveBizType> biztypes = bizTypeFacade.LoadAll();
            ViewData["selbiztype"] = new SelectList(biztypes, "Id", "BizTypeName");

            DedicateLine Line = new DedicateLine();
            ResourceDedicateLine rdl = new ResourceDedicateLine();
            ResourceGroupClie groupClie = groupClieFacade.GetHql(OneSevenZero.GroupId);
            if (groupClie == null)
            {
                groupClie = new ResourceGroupClie();
                groupClie.Id = "0";
                groupClie.ClieNo = OneSevenZero.GroupId;
                groupClie.ClieName = OneSevenZero.ClientCompanyName;
                ViewData["displyMsg"] = "集团客户不存在，请添加后到集团客户管理界面进行修改";
            }
            rdl.BizTypeId = biztype.Id;
            rdl.NetWorkingModeId = networkmode.Id;
            rdl.ClieId = groupClie.Id;
            Line.ClieNo = groupClie.ClieNo;
            Line.ClieName = groupClie.ClieName;
            rdl.BizAssuranLeveId = bizAssuranLeve.Id;

            rdl.VLANNumber = OneSevenZero.VLAN;
            rdl.IP = OneSevenZero.ClientIp;
            rdl.GateWay = OneSevenZero.ClientGateway;
            rdl.SubnetMask = OneSevenZero.ClientSubnetMask;
            string[] BandwidthAry = OneSevenZero.Bandwidth.Split('M');
            if (BandwidthAry.Length <= 1)
                BandwidthAry = OneSevenZero.Bandwidth.Split('m');

            rdl.ActualBandwidth = BandwidthAry[0];
            rdl.ContractBandwidth = BandwidthAry[0];

            rdl.CircuitNumber = OneSevenZero.CircuitNo;
            rdl.CityNetSwitch = OneSevenZero.WanSwitchEquipment;
            rdl.CityNetSwitchPort = OneSevenZero.WanSwitchEquipmentPort;

            rdl.ComputRoomtTEquip = OneSevenZero.WanEquipment;
            rdl.ComputRoomtTEquipPort = OneSevenZero.WanEquipmentPort;
            rdl.StationTEquipAndPort = OneSevenZero.TransfersEquipment + "-" + OneSevenZero.TransfersEquipmentPort;//基站传输设备及端口

            rdl.StationName = OneSevenZero.AccessToTheBasestation;
            rdl.BizComputRoomName = OneSevenZero.Basestation;
            rdl.LineNo = OneSevenZero.CityCountryNo;
            rdl.StartDateTime = OneSevenZero.CompleteTime16;

            if (OneSevenZero.IsIntegrateBeforeHand == '是')
                rdl.State = 1;
            else if (OneSevenZero.IsIntegrateIndue == '是')
                rdl.State = 0;
            IList<ResourceNumberGroup> numberGroups = new List<ResourceNumberGroup>();
            ResourceNumberGroup numberGroup = new ResourceNumberGroup();
            numberGroups.Add(numberGroup);
            //纤芯
            IList<ResourceFiberCore> fibercores = new List<ResourceFiberCore>();
            if (OneSevenZero.CoreInformation != "" && OneSevenZero.CoreInformation != null)
            {
                int i = 0;
                string[] endAry = Regex.Split(OneSevenZero.CoreInformation, "至客户端", RegexOptions.IgnoreCase);
                string[] MidAry = Regex.Split(endAry[0], "经", RegexOptions.IgnoreCase);
                foreach (string mstr in MidAry)
                {
                    ResourceFiberCore fibercore = new ResourceFiberCore();
                    fibercore.SeqNo = i.ToString();
                    fibercore.BaseStationName = mstr;
                    fibercores.Add(fibercore);
                    i++;
                }
                ResourceFiberCore fcore = new ResourceFiberCore();
                fcore.SeqNo = i.ToString();
                fcore.BaseStationName = endAry[0];
                fibercores.Add(fcore);
            }
            else
            {
                for (int i = 0; i < 2; i++)
                {
                    ResourceFiberCore fibercore = new ResourceFiberCore();
                    fibercore.SeqNo = i.ToString();
                    fibercores.Add(fibercore);
                }
            }

            Line.DediLine = rdl;
            Line.FiberCores = fibercores;
            Line.NumberGroups = numberGroups;
            result.Line = Line;
            
            return View("Edit", result);
        }
        //
        // POST: /User/Home/Edit/5
        //[AcceptVerbs(HttpVerbs.Post)]
        //FormCollection
        [AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Edit(string id, ResourceOneSevenZero entity)
        {
            bool result = false; string msg = "操作失败";                
            try
            {
                result = this.oneSevenZeroFacadeEx.Save(entity);
                if (result) msg = "操作成功";
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
            catch (Exception ex)
            {
                return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
            }
        }
        [AcceptVerbs(HttpVerbs.Get)]
        public ActionResult LoadFile()
        {
            return View();
        }
        //
        // GET: /User/Home/Delete/5,2,1
        //[AcceptVerbs(HttpVerbs.Post)]
        public ActionResult Delete(string id)
        {
            bool result = false;
            string msg = "操作失败";
            //result = oneSevenZeroFacadeEx.Delete(id.ToString());
            if (result) msg = "操作成功";
            return Json(new { Type = result, Message = msg }, JsonRequestBehavior.AllowGet);
        }
        public override string GridPager(int page, int pagesize, string sortname, string sortorder, string gridviewname, string gridsearch)
        {
            return oneSevenZeroFacade.FindByPage(page, pagesize);
        }
    }
}
