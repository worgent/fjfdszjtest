using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.BizAssuranLeveManger.Domain
{
    public class BizAssuranLeveFacadeImpl : BizAssuranLeveFacade
    {
        private IRepository<ArchiveBizAssuranLeve> assuranRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveBizAssuranLeve Get(object id)
        {
            return assuranRepository.Get(id.ToString());
        }
        public ArchiveBizAssuranLeve GetHql(object assuranLeveName)
        {
            string Hql = "";
            if(assuranLeveName!=null)
            {
                 if(assuranLeveName.ToString()=="")
                   Hql = " AssuranLeveName = '待定'";
                else
                   Hql = " AssuranLeveName = '" + assuranLeveName.ToString() + "'";
            }
            else
                Hql = " AssuranLeveName = '待定'";
            IList<ArchiveBizAssuranLeve> entitys = assuranRepository.LoadAll("Id", Hql);
            if (entitys != null)
            {
                if (entitys.Count > 0)
                    return entitys[0];
                else
                    return null;
            }
            else return null;
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
                string strsql = " CId='" + s + "' and RelationName='BizAssuranLeve'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='BizAssuranLeve'";
                    result = relationRepository.DeleteHql(sql);
                    result = assuranRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='BizAssuranLeve'";
                    result = relationRepository.DeleteHql(sql);
                    result = assuranRepository.Delete(s);
                }
                else
                    DelFlag = true;
            } 
            return result;
        }

        public bool Save(ArchiveBizAssuranLeve entity)
        {
            entity.Id = assuranRepository.NewSequence("SYSTEM_MENU");
            return assuranRepository.Save(entity);
        }

        public bool Update(ArchiveBizAssuranLeve entity)
        {
            return assuranRepository.Update(entity);
        }

        public IList<ArchiveBizAssuranLeve> LoadAll()
        {
            return assuranRepository.LoadAll();
        }
        public IList<ArchiveBizAssuranLeve> LoadAll(string order, string where)
        {
            return assuranRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveBizAssuranLeve";
            IList<ArchiveBizAssuranLeve> ls = assuranRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = assuranRepository.FindByPageCount(hql);
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
                                text = a.AssuranLeveName,
                                id = a.Id
                            }
                           ).ToArray();
            return JSONHelper.ToJSON(jsonlist);
        }
    }
}
