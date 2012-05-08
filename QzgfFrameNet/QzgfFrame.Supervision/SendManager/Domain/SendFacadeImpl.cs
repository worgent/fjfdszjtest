using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Supervision.SendManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;

namespace QzgfFrame.Supervision.SendManager.Domain
{
    /// <summary>
    /// 督办工单
    /// </summary>
    public class SendFacadeImpl : SendFacade
    {
        #region

        /// <summary>
        /// 督办工单
        /// </summary>
        private IRepository<SupervisionSend> sendRepository { set; get; }

        #endregion

        public SupervisionSend Get(object id)
        {
            return sendRepository.Get(id.ToString());
        }

        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = sendRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 删除多行记录,假删
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool DeleteFalse(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                SupervisionSend supervisionSend = sendRepository.Get(s);
                result = sendRepository.Update(supervisionSend);
            }
            return result;
        }


        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(SupervisionSend entity)
        {
            entity.Id = sendRepository.NewSequence("SYSTEM_MENU");
            return sendRepository.Save(entity);
        }

        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(SupervisionSend entity)
        {
            return sendRepository.Update(entity);
        }

        public IList<SupervisionSend> LoadAll()
        {
            return sendRepository.LoadAll();
        }

        public IList<SupervisionSend> LoadAll(string order, string where)
        {
            return sendRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder, string gridsearch)
        {
            string url = "";
            string urlTile = "";
            string hql = " select main.id,main.SendTitle,main.SendContent,main.DispatchTime from SupervisionSend main ";
            //hql += " where IsDelete != 1 or IsDelete is null ";
            hql += " where 1=1 ";
            string vSql = hql + gridsearch;
            vSql += @" order by main." + sortname + " " + sortorder;

            IList<object[]> ls = sendRepository.FindByLinkPage(pageNo, pageSize, vSql);
            IList<Send> sends = new List<Send>();
            for (int i = 0; i < ls.Count; i++)
            {
                Send send = new Send();
                url = "<a href='javascript:f_ChooseUnit(";
                urlTile = ");'>派遣对象</a>";
                send.Operating = url + '"' + ls[i][0].ToString() + '"' + urlTile;
                send.Id = ls[i][0].ToString();
                send.SendTitle = ls[i][1] != null ? ls[i][1].ToString() : "";
                send.SendContent = ls[i][2] != null ? ls[i][2].ToString() : "";
                send.DispatchTime = ls[i][3] != null ? ls[i][3].ToString() : "";
                sends.Add(send);
            }

            string rowsjson = JsonConvert.SerializeObject(sends);
            int recordCount = sendRepository.FindByPageLinkCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;

        }
    }
}
