using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Archives.ClieScaleGradeManger.Models;
using QzgfFrame.Archives.ClieScaleGradeManger.Domain;
using QzgfFrame.System.RelationManger.Models;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Archives.ClieScaleGradeManger.Domain
{
    public class ClieScaleGradeFacadeImpl : ClieScaleGradeFacade
    {
        private IRepository<ArchiveClieScaleGrade> scaleRepository { set; get; }
        private IRepository<SystemRelation> relationRepository { set; get; }

        public ArchiveClieScaleGrade Get(object id)
        {
            return scaleRepository.Get(id.ToString());
        }
        public ArchiveClieScaleGrade GetHql(object ScaleGradeName)
        {
            string Hql = "";
            if (ScaleGradeName != null)
            {
               if(ScaleGradeName.ToString()=="")
                   Hql = " ScaleGradeName = '待定'";
                else
                   Hql = " ScaleGradeName = '" + ScaleGradeName.ToString() + "'";
            }                
            else
                Hql = " ScaleGradeName = '待定'";
            IList<ArchiveClieScaleGrade> entitys = scaleRepository.LoadAll("Id", Hql);
            if(entitys!=null){
              if (entitys.Count > 0)
                return entitys[0];
                else return null;
            }
            else
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
                string strsql = " CId='" + s + "' and RelationName='ScaleGrade'";
                IList<SystemRelation> sres = relationRepository.LoadAll("CId", strsql);
                if (sres == null)
                {
                    string sql = " MId='" + s + "' and ControllerName='ScaleGrade'";
                    result = relationRepository.DeleteHql(sql);
                    result = scaleRepository.Delete(s);
                }
                else if (sres.Count == 0)
                {
                    string sql = " MId='" + s + "' and ControllerName='ScaleGrade'";
                    result = relationRepository.DeleteHql(sql);
                    result = scaleRepository.Delete(s);
                }
                else
                    DelFlag = true;
            }
            return result;
        }

        public bool Save(ArchiveClieScaleGrade entity)
        {
            entity.Id = scaleRepository.NewSequence("SYSTEM_MENU");
            return scaleRepository.Save(entity);
        }

        public bool Update(ArchiveClieScaleGrade entity)
        {
            return scaleRepository.Update(entity);
        }

        public IList<ArchiveClieScaleGrade> LoadAll()
        {
            return scaleRepository.LoadAll();
        }
        public IList<ArchiveClieScaleGrade> LoadAll(string order, string where)
        {
            return scaleRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ArchiveClieScaleGrade";
            IList<ArchiveClieScaleGrade> ls = scaleRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = scaleRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
