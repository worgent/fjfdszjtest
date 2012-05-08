using System;

using QzgfFrame.System.MenuManger.Domain;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.RoleManger.Domain;
using QzgfFrame.System.RoleManger.Models;

namespace QzgfFrame.Controllers.System.MenuManger
{
    public class MenuFacadeExImpl : MenuFacadeEx
    {
        private MenuFacade menuFacade { set; get; }
        private RoleFacade roleFacade { set; get; }
        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Update()
        {
             //menuFacade.Save(new SystemMenu("mytest", "aa", "1", "1", "1", "1", "1"));
             //throw new Exception("测试数据是否回滚");
             //roleFacade.Save(new SystemRole("mytest", "aa", "bb", DateTime.Now)); 
             return true;
        }
    }
}
