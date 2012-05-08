using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.NetworkingModeManger.Models;
using QzgfFrame.Archives.NetworkingModeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.NetworkingModeManger.Domain
{
    public class NetworkingModeFacadeImpl:NetworkingModeFacade
    {
        private IRepository<ArchiveNetworkingMode> modeRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveNetworkingMode Get(object id)
        {
            return modeRepository.Get(id.ToString());
        }
        public ArchiveNetworkingMode GetHql(string modeName)
        {
            string Hql = " ModeName = '" + modeName + "'";
            IList<ArchiveNetworkingMode> entitys = modeRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else return null;
            }
            return null;
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
                string strsql = " CId='" + s + "' and RelationName='NetWorkingMode'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='NetWorkingMode'";
                    result = relationRepository.DeleteHql(sql);
                    result = modeRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='NetWorkingMode'";
                    result = relationRepository.DeleteHql(sql);
                    result = modeRepository.Delete(s);
                }
                else
                    DelFlag = true;
            }
            return result;
        }

        public bool Save(ArchiveNetworkingMode entity)
        {
            entity.Id = modeRepository.NewSequence("SYSTEM_MENU");
            return modeRepository.Save(entity);
        }

        public bool Update(ArchiveNetworkingMode entity)
        {
            return modeRepository.Update(entity);
        }

        public IList<ArchiveNetworkingMode> LoadAll()
        {
            return modeRepository.LoadAll();
        }
        public IList<ArchiveNetworkingMode> LoadAll(string order, string where)
        {
            return modeRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveNetworkingMode";
            IList<ArchiveNetworkingMode> ls = modeRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = modeRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
        /// <summary>
        /// 下拉框,
        /// </summary>
        /// <returns>json数据格式</returns>
        public string GetCombobox()
        {
            var ls = LoadAll();
            var jsonlist = (from a in ls
                            select new
                            {
                                text = a.ModeName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
