using System;
using System.Collections.Generic;
using System.Data;
using System.Web.Services;
using Newtonsoft.Json;
using QzgfFrame.Utility.Core.Repository;
using QzgfFrame.Webservice.Models;

namespace QzgfFrame.Webservice.Service
{
    //[WebServiceBinding(ConformsTo = WsiProfiles.None)]
    public class WsenterFacadeImpl : WsenterFacade
    {
        private IRepository<Yd170data> wsenterRepository { set; get; }
        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Wsenter");
        /// <summary>
        /// 170数据导入
        /// </summary>
        /// <param name="datajson">170数据的json格式</param>
        /// <returns>true:保存成功,false:保存失败</returns>
        [WebMethod(Description = "功能:导入170数据.参数说明:170数据的json格式。<br/>（<font color=\"red\">返回：true:保存成功,false:保存失败</font>）")]
        public bool Import170(string datajson)
        {
            bool result = false;
            try
            {
                Logger.Info("(时间:"+DateTime.Now+")170导入数据:" + datajson);
                var data = JsonConvert.DeserializeObject<Yd170data>(datajson);
                //var data = JsonConvert.DeserializeObject<IList<Yd170data>>(datajson);多行数据,暂时不用.
                //一些信息初始化
                data.Id = wsenterRepository.NewSequence("Yd170data");
                data.State = "1";
                //保存
                result = wsenterRepository.Save(data);


                Logger.Info("170操作结果" + result);
            }
            catch (Exception ex)
            {
                Logger.Error("170异常信息:"+ex.ToString());
            }

    return result;
        }
        /*测试数据
         {"Id":"","Citycountryno":"","Ensuregrade":"","Clientgrade":"","Importlevel":"","Managertel":"","Name":"","Isflyover":"","Intendmoney":"","Expressremark":"","Operationtype":"","Applyman":"","Applytel":"","Applyfax":"34","Clienttype":"","Communicateaddress":"","Groupid":"","Clientcompanyname":"","Citycounty":"","Projectexpository":"","Completetime13":"\/Date(-62135596800000+0800)\/","Completetime14":"\/Date(-62135596800000+0800)\/","Completetime15":"\/Date(-62135596800000+0800)\/","Completetime16":"\/Date(-62135596800000+0800)\/","Wanequipment":"","Wanequipmentport":"","Bandwidth":"","Circuitno":"","Transfersequipment":"","Transfersequipmentport":"","Coreinformation":"","Orgnetmode":"","Vlan":"","Wanswitchequipment":"","Wanswitchequipmentport":"","Basestation":"","Clientip":"","Clientgateway":"","Clientsubnetmask":"","Accesstothebasestation":"123","Noresource":"","Wanbossfactorymode":"","Userfirstfactory":"","Userfirstmode":"","Usersencodefactory":"","Usersencodemode":"","Userthirdfactory":"","Userthirdmode":"","Userfourfactory":"","Userfourmode":"","Userfivefactory":"","Userfivemode":"","Basestationfirstfactory":"","Basestationfirstmode":"","Basestationsencodefactory":"","Basestationsencodemode":"","Groupinnet":"","Maintenancecompany":"","Isintegratebeforehand":"","Islinepipebefordhand":"","Isintegrateindue":"","Islinepipeindue":"","State":"","IsChanged":true,"IsDeleted":false}
         */
    }
}
