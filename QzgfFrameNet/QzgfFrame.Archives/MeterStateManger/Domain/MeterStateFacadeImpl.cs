using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.MeterStateManger.Models;
using QzgfFrame.Archives.MeterStateManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.MeterStateManger.Domain
{
    public class MeterStateFacadeImpl : MeterStateFacade
    {
        private IRepository<ArchiveMeterState> meterStateRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveMeterState Get(object id)
        {
            return meterStateRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='MeterState'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='MeterState'";
                    result = relationRepository.DeleteHql(sql);
                    result = meterStateRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='MeterState'";
                    result = relationRepository.DeleteHql(sql);
                    result = meterStateRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }
        public ArchiveMeterState GetHql(string meterStateName)
        {
            string Hql = " MeterStateName = '"+meterStateName+"'";
            IList<ArchiveMeterState> meterStates = meterStateRepository.LoadAll("Id", Hql);
            if (meterStates != null)
            {
                if (meterStates.Count > 0)
                    return meterStates[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveMeterState entity)
        {
            entity.Id = meterStateRepository.NewSequence("SYSTEM_MENU");
            return meterStateRepository.Save(entity);
        }

        public bool Update(ArchiveMeterState entity)
        {
            return meterStateRepository.Update(entity);
        }

        public IList<ArchiveMeterState> LoadAll()
        {
            return meterStateRepository.LoadAll();
        }
        public IList<ArchiveMeterState> LoadAll(string order, string where)
        {
            return meterStateRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveMeterState";
            IList<ArchiveMeterState> ls = meterStateRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = meterStateRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
