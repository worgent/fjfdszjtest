using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Archives.PostsManger.Models;

namespace QzgfFrame.Archives.PostsManger.Domain
{
    public interface PostsFacade
    {
        ArchivePosts Get(object id);
        ArchivePosts GetHql(string postsName);
        bool Delete(string id, out bool DelFlag);

        bool Save(ArchivePosts entity);

        bool Update(ArchivePosts entity);

        IList<ArchivePosts> LoadAll();

        IList<ArchivePosts> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
    }
}
