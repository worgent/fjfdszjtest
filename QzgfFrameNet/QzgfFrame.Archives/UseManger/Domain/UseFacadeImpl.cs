using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.UseManger.Models;
using QzgfFrame.Archives.UseManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.UseManger.Domain
{
    public class UseFacadeImpl : UseFacade
    {
        private IRepository<ArchiveUse> useRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveUse Get(object id)
        {
            return useRepository.Get(id.ToString());
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
                string strsql = " CId='" + s + "' and RelationName='Use'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='Use'";
                    result = relationRepository.DeleteHql(sql);
                    result = useRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='Use'";
                    result = relationRepository.DeleteHql(sql);
                    result = useRepository.Delete(s);
                }
                else
                    DelFlag = true;
            }
            return result;
        }
        public ArchiveUse GetHql(string useName)
        {
            string Hql = " UseName = '"+useName+"'";
            IList<ArchiveUse> uses = useRepository.LoadAll("Id", Hql);
            if (uses != null)
            {
                if (uses.Count > 0)
                    return uses[0];
                else return null;
            }
            return null;
        }
        public bool Save(ArchiveUse entity)
        {
            entity.Id = useRepository.NewSequence("SYSTEM_MENU");
            return useRepository.Save(entity);
        }

        public bool Update(ArchiveUse entity)
        {
            return useRepository.Update(entity);
        }

        public IList<ArchiveUse> LoadAll()
        {
            return useRepository.LoadAll();
        }
        public IList<ArchiveUse> LoadAll(string order, string where)
        {
            return useRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveUse";
            IList<ArchiveUse> ls = useRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = useRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
