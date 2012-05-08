using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.SignalModelManger.Models;
using QzgfFrame.Archives.SignalModelManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.SignalModelManger.Domain
{
    public class SignalModelFacadeImpl : SignalModelFacade
    {
        private IRepository<ArchiveSignalModel> signalModelRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveSignalModel Get(object id)
        {
            return signalModelRepository.Get(id.ToString());
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id, out bool DelFlag)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            DelFlag = false;
            foreach (var s in idarr)
            {
                string strsql = " CId='" + s + "' and RelationName='SignalModel'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='SignalModel'";
                    result = relationRepository.DeleteHql(sql);
                    result = signalModelRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='SignalModel'";
                    result = relationRepository.DeleteHql(sql);
                    result = signalModelRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveSignalModel GetHql(string signalModelName)
        {
            string Hql = " SignalModelName = '"+signalModelName+"'";
            IList<ArchiveSignalModel> signalModels = signalModelRepository.LoadAll("Id", Hql);
            if (signalModels != null)
            {
                if (signalModels.Count > 0)
                    return signalModels[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveSignalModel entity)
        {
            entity.Id = signalModelRepository.NewSequence("SYSTEM_MENU");
            return signalModelRepository.Save(entity);
        }

        public bool Update(ArchiveSignalModel entity)
        {
            return signalModelRepository.Update(entity);
        }

        public IList<ArchiveSignalModel> LoadAll()
        {
            return signalModelRepository.LoadAll();
        }
        public IList<ArchiveSignalModel> LoadAll(string order, string where)
        {
            return signalModelRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveSignalModel";
            IList<ArchiveSignalModel> ls = signalModelRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = signalModelRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
