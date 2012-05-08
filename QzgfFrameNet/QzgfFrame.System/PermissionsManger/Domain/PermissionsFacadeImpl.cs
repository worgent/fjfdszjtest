/****************************************************************** 
 * 文件名.........: MenuFacadeImpl.cs 
 * 作者...........: 中文姓名 
 * 说明...........: 用户登录SESSION信息类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 创建文件，初始设计
 *                    2011-01-16 XXXX 增加安全部分的加密功能
 * ******************************************************************/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json;
using QzgfFrame.System.MenuManger.Models;
using QzgfFrame.System.MenufieldManger.Models;
using QzgfFrame.System.PermissionsManger.Models;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Utility.Core.JSON;
using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.System.PermissionsManger.Domain
{
    public class PermissionsFacadeImpl : PermissionsFacade
    {

        #region 变量定义

        private IRepository<SystemUserrole> userroleRepository { set; get; }
        private IRepository<SystemMenu> menuRepository { set; get; }
        private IRepository<SystemRolefiledpower> rolefiledpowerRepository { set; get; }
        private IRepository<SystemRolemenu> rolemenuRepository { set; get; }
        private IRepository<SystemMenufield> menufieldRepository { set; get; }

        #endregion


        #region 基本操作

        /// <summary>
        /// 查询单行记录
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        public SystemRolefiledpower Get(object id)
        {
            return rolefiledpowerRepository.Get(id.ToString());
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
                result = rolefiledpowerRepository.Delete(s);
            }
            return result;
        }

        //回朔保存树数据信息
        public bool SaveTree(ArrayList rolemenuvals, string roleid,out int totol)
        {
            totol = 0;
            bool result = true;
            foreach (var vrolemenuval in rolemenuvals)
            {
                var hsvrolemenuval = (Hashtable)vrolemenuval;
                int optval = 0;
                //如果有children则回朔
                if (hsvrolemenuval.ContainsKey("children"))
                {
                    var arrchildren = (hsvrolemenuval["children"]) as ArrayList;
                    int curtotol = 0;
                    result = SaveTree(arrchildren, roleid, out curtotol);
                    if (curtotol == 0) optval = 0;
                    else optval = 1;
                }else
                {
                    optval = Convert.ToInt32(hsvrolemenuval["optval"]);
                }
                //每行记录都保存
                if (optval > 0)
                {
                    var srm = new SystemRolemenu();
                    srm.Id = rolemenuRepository.NewSequence("SYSTEM_RoleMenu");
                    srm.Roleid = roleid;
                    srm.Menuid = hsvrolemenuval["id"].ToString();
                    srm.Optval = optval;
                    result = rolemenuRepository.Save(srm);
                    totol += optval;
                }
            }
            return result;
        }


        /// <summary>
        /// 保存权限信息
        /// </summary>
        /// <param name="rolemenustr">角色菜单信息</param>
        /// <param name="rolepowerval">角色权限信息</param>
        /// <param name="roleid">角色id</param>
        /// <returns>是否保存成功</returns>
        public bool Save(string rolemenustr, string rolepowerval, string roleid)
        {
            bool result = true;
            //============角色菜单信息保存===================
            //先删除
            result = rolemenuRepository.DeletebyHql("from SystemRolemenu where roleid='" + roleid + "'");
            //再增加
            var rolemenuvals = JSONHelper.GetArrayListFromJSON(rolemenustr);
            int nowtotol = 0;
            //回朔保存数据
            SaveTree(rolemenuvals, roleid, out nowtotol);
            //===================角色权限值信息========================
            //先删除
            result = rolefiledpowerRepository.DeletebyHql("from SystemRolefiledpower where roleid='" + roleid + "'");

            var rolepowervals = JSONHelper.GetArrayListFromJSON(rolepowerval);
            foreach (var vrolepowerval in rolepowervals)
            {
                var vrolepowervals = (Hashtable) vrolepowerval;
                var powerval = Convert.ToInt32(vrolepowervals["Powerval"]);
                if (powerval > 0)
                {
                    var rolefield = new SystemRolefiledpower();
                    rolefield.Id = rolefiledpowerRepository.NewSequence("SYSTEM_ROLEFIELDPOWER");
                    rolefield.Roleid = roleid;
                    rolefield.Powerval = powerval;
                    rolefield.Fieldid = vrolepowervals["Id"].ToString();
                    result = rolefiledpowerRepository.Save(rolefield);
                }
            }

            return result;
        }


        public bool Update(SystemRolefiledpower entity)
        {
            return rolefiledpowerRepository.Update(entity);
        }


        public string FindByPage(int pageNo, int pageSize, string sortname, string sortorder)
        {
            const string hql = "from SystemRolefiledpower";
            IList<SystemRolefiledpower> ls = rolefiledpowerRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = rolefiledpowerRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        #endregion

        #region 加强
        private string jsontree = "";
        private string gettrees(IList<SystemRolemenu> ls, string id)
        {
            string result = "";
            var curval=  ls.Where(m=>m.Fatherid.Trim()==id.Trim());
            foreach (var systemRolemenu in curval)
            {
                if (hasChildren(ls, systemRolemenu.Menuid))
                    result += "{id: '" + systemRolemenu.Menuid + "', name: '" + systemRolemenu.Menuname +
                        "' ,optval:" + systemRolemenu.Optval + ", children:[" + gettrees(ls, systemRolemenu.Menuid) + "]},";
                else
                    result += "{id: '" + systemRolemenu.Menuid + "', name: '" + systemRolemenu.Menuname +
                        "',optval:" + systemRolemenu.Optval+"},";
            }

            if (result.Length > 1)
                 result = result.Substring(0, result.Length - 1);

            return result;
        }

        private bool hasChildren(IList<SystemRolemenu> ls, string id)
        {
           return ls.Count(m => m.Fatherid == id)>0?true:false;
        }
        /// <summary>
        /// 加载角色菜单视图信息
        /// </summary>
        /// <returns></returns>
        public string LoadAllViewrolemenu(string roleid)
        {
            var lsmenu = menuRepository.LoadAllbyHql("from SystemMenu where Ismenu='1'");
            var lsrolemenu = rolemenuRepository.LoadAllbyHql("from SystemRolemenu where Roleid='" + roleid + "'");

            var jsonlist = (from vlsmenu in lsmenu
                            join vlsrolemenu in lsrolemenu
                                on vlsmenu.Id equals vlsrolemenu.Menuid into joinedrolemenu
                            from vexlsrolemenu in joinedrolemenu.DefaultIfEmpty()
                            select new SystemRolemenu(vlsmenu.Id,
                                vexlsrolemenu!=null?vexlsrolemenu.Optval:0,
                                vlsmenu.Name,vlsmenu.Father,vlsmenu.Orderno
                                )).OrderBy(m=>m.Orderno).ToArray();

            string str = "";

            //str= gettrees(jsonlist, "0");//,out str
            str = "{ Rows : [" + gettrees(jsonlist, "0") + "],Total:" + jsonlist.Length + @"}";
            return str;
            //return JSONHelper.ToJSON(jsonlist);
        }

        /// <summary>
        /// 加载权限值列表
        /// </summary>
        /// <returns></returns>
        public string LoadAllViewrolepowerval(string roleid)
        {
            var lsmenufield = menufieldRepository.LoadAllbyHql("from SystemMenufield ");
            var lsrolefieldpower =
                rolefiledpowerRepository.LoadAllbyHql("from SystemRolefiledpower where Roleid='" + roleid + "' ");
            var lsmenu = menuRepository.LoadAllbyHql("from SystemMenu");


            var jsonlist = (from vlsmenufield in lsmenufield
                            join vlsrolefieldpower in lsrolefieldpower
                                on vlsmenufield.Id equals vlsrolefieldpower.Fieldid into joinedvlsmenufield1
                            from vlsmenufield1 in joinedvlsmenufield1.DefaultIfEmpty()

                            join vlsmenu in lsmenu
                                on vlsmenufield.Menuid equals vlsmenu.Id into joinedvlsmenufield2
                            from vlsmenufield2 in joinedvlsmenufield2.DefaultIfEmpty()
                            select new
                                       {
                                           Id = vlsmenufield.Id,
                                           Menuid = vlsmenufield.Menuid,
                                           Menuname = vlsmenufield2 != null ? vlsmenufield2.Name : "",
                                           Fieldcode = vlsmenufield.Fieldcode,
                                           Fieldname = vlsmenufield.Fieldname,
                                           Tablecode = vlsmenufield.Tablecode,
                                           Tablename = vlsmenufield.Tablename,
                                           Powerval = vlsmenufield1 != null ? vlsmenufield1.Powerval : 0
                                       }
                           ).OrderBy(m=>m.Menuid).ToArray();
            string tmp = JSONHelper.ToJSON(jsonlist);
            tmp = @"{""Rows"":" + tmp + @",""Total"":""" + jsonlist.Length + @"""}";
            return tmp;
        }

        /// <summary>
        /// 根据用户id,取得菜单树
        /// </summary>
        /// <param name="userid"></param>
        /// <returns></returns>
        public IList<SystemMenu> GetMenubyUserId(string userid)
        {
            var userroles = userroleRepository.LoadAllbyHql("from SystemUserrole where Userid='" + userid + "'");
            var lsmenu = menuRepository.LoadAllbyHql("from SystemMenu");
            var lsrolemenu = rolemenuRepository.LoadAllbyHql("from SystemRolemenu");

            //先内连接,然后左连接
            var jsonlist = (from vuserroles in userroles
                            join vlsrolemenu in lsrolemenu
                                on vuserroles.Roleid equals vlsrolemenu.Roleid
                            //into joinedrolemenu1
                            //from vlsrolemenu1 in joinedrolemenu1.DefaultIfEmpty(default(SystemRolemenu))
                            join vlsmenu in lsmenu
                                on vlsrolemenu.Menuid equals vlsmenu.Id into joinedrolemenu2
                            from vlsrolemenu2 in joinedrolemenu2.DefaultIfEmpty(default(SystemMenu))
                            select
                                new SystemMenu(vlsrolemenu.Menuid, vlsrolemenu2 != null ? vlsrolemenu2.Name : "",
                                    vlsrolemenu2 != null ? vlsrolemenu2.Pic : "",
                                    vlsrolemenu2 != null ? vlsrolemenu2.Url : "",
                                    vlsrolemenu2 != null ? vlsrolemenu2.Orderno : "",
                                    vlsrolemenu2 != null ? vlsrolemenu2.Father : "",
                                    vlsrolemenu2 != null ? vlsrolemenu2.Ismenu : "",
                                    vlsrolemenu2 != null ? vlsrolemenu2.Permissionsflag : "",
                                    vlsrolemenu.Optval
                                    )).OrderBy(m => m.Orderno).Distinct().ToList();
            //默认系统菜单
            var item = new SystemMenu("0", "Root", "1", "1", "1", "-1", "1", "", 0);
            jsonlist.Add(item);
            return jsonlist;
        }

        /// <summary>
        /// 根据用户id,取得数据控制字段
        /// </summary>
        /// <param name="userid"></param>
        /// <returns></returns>
        public IList<SystemMenufield> GetMenufieldUserId(string userid)
        {
            var userroles = userroleRepository.LoadAllbyHql("from SystemUserrole where Userid='" + userid + "'");
            var lsmenufield = menufieldRepository.LoadAllbyHql("from SystemMenufield ");
            var lsrolefieldpower = rolefiledpowerRepository.LoadAllbyHql("from SystemRolefiledpower");


            //然后左连接,后内连接

            var rolefieldpower = (from vlsrolefieldpower in lsrolefieldpower
                        join vuserroles in userroles
                        on vlsrolefieldpower.Roleid equals vuserroles.Roleid
                         select new SystemRolefiledpower(vlsrolefieldpower.Id,
                                                 vlsrolefieldpower.Roleid,
                                                 vlsrolefieldpower.Fieldid,
                                              vlsrolefieldpower.Powerval
                          )
                         ).ToList();
             


            var jsonlist = (from vlsmenufield in lsmenufield
                            join vrolefieldpower in rolefieldpower
                            on vlsmenufield.Id equals vrolefieldpower.Fieldid into joinedrolefieldpower1
                            from vlsrolefieldpower1 in joinedrolefieldpower1.DefaultIfEmpty()
                            select new SystemMenufield(vlsmenufield.Id,
                                            vlsmenufield.Menuid,
                                            vlsmenufield.Fieldcode,
                                            vlsmenufield.Tablecode,
                                            vlsrolefieldpower1 == null ? 0 : vlsrolefieldpower1.Powerval,
                                            vlsmenufield.Permissionsflag
                                )
                           ).ToList();

            //string tmp = JSONHelper.ToJSON(jsonlist);
            //ArrayList arrlist = JSONHelper.GetArrayListFromJSON(tmp);
            return jsonlist;
        }

        #endregion

        #region 保留

        public IList<SystemRolefiledpower> LoadAll()
        {
            return rolefiledpowerRepository.LoadAll();
        }

        public IList<SystemRolefiledpower> LoadAll(string order, string where)
        {
            return rolefiledpowerRepository.LoadAll(order, where);
        }

        #endregion

    }
}
