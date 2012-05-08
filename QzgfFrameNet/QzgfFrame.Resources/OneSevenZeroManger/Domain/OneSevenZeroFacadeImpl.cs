using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Resources.OneSevenZeroManger.Models;
using QzgfFrame.Resources.OneSevenZeroManger.Domain;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Resources.OneSevenZeroManger.Domain
{
    public class OneSevenZeroFacadeImpl : OneSevenZeroFacade
    {
        private IRepository<ResourceOneSevenZero> OneSevenZeroRepository { set; get; }

        public ResourceOneSevenZero Get(object id)
        {
            return OneSevenZeroRepository.Get(id.ToString());
        }
        /// <summary>
        /// 同时删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string hql)
        {
            return OneSevenZeroRepository.DeleteHql(hql);
        }

        public bool Save(ResourceOneSevenZero entity, string no)
        {
            entity.Id = OneSevenZeroRepository.NewSequence("SYSTEM_MENU", no);
            return OneSevenZeroRepository.Save(entity);
        }

        public bool Update(ResourceOneSevenZero entity)
        {
            return OneSevenZeroRepository.Update(entity);
        }
        public bool Update(string id)
        {
            string hql = " State='1' where Id='" + id + "'";
            return OneSevenZeroRepository.Update(hql);
        }
        public IList<ResourceOneSevenZero> LoadAll()
        {
            return OneSevenZeroRepository.LoadAll();
        }
        public IList<ResourceOneSevenZero> LoadAll(string order, string where)
        {
            return OneSevenZeroRepository.LoadAll(order, where);
        }
        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ResourceOneSevenZero where State=0";
            IList<ResourceOneSevenZero> ls = OneSevenZeroRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JSONHelper.ToJSON(ls);
            int recordCount = OneSevenZeroRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
