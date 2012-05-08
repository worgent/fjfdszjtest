using System.Linq;
using Newtonsoft.Json;
using QzgfFrame.System.MenuManger.Domain;

namespace QzgfFrame.Controllers.CommonSupport
{
    public class SystemDataPro
    {
        private MenuFacade menuFacade { set; get; }

        public string GetMenu()
        {
            var list = menuFacade.LoadAll("Orderno", "Ismenu='1'");
            /*首页版本1
            var jsonlist = (from a in list
                            select new
                            {
                                name = a.Name,
                                menuno = a.Id,
                                url = a.Url,
                                icon = a.Pic
                            }
                           ).ToArray();
            */

            var jsonlist = (from a in list
                            select new
                            {
                                text = a.Name,
                                id = a.Id,
                                url = a.Url,
                                icon = a.Pic,
                                pid = a.Father
                            }
            ).ToArray();

            return JsonConvert.SerializeObject(jsonlist);
        }

        public string GetButton(string menuid)
        {
            var list = menuid == "null" ? menuFacade.LoadAll("Orderno", "Ismenu='0' and Father='2'") : menuFacade.LoadAll("Orderno", "Ismenu='0' and Father='" + menuid + "'");

            var jsonlist = (from a in list
                            select new
                            {
                                id = a.Url,
                                icon = a.Pic,
                                name = a.Name
                            }
                           ).ToArray();

            return JsonConvert.SerializeObject(jsonlist);
        }
    }
}
