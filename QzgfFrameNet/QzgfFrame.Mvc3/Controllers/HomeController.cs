using System.Web.Mvc;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.System.UserManger.Domain;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.Common;

namespace QzgfFrame.Mvc3.Controllers
{
    public class HomeController : BaseController
    {
        private UserFacade userFacade { set; get; }
        //首页
        public ActionResult Index()
        {
            //ViewData["user"] = currentUser.UserInfo;
            SystemUser su = currentUser.UserInfo;

            return View(su);
        }

        //welcome页面
        public ActionResult Welcome()
        {
            return View();
        }

        //登录
        public ActionResult Login()
        {
            return View();
        }

        /// <summary>
        /// 用户登录验证
        /// </summary>
        /// <param name="username">用户名</param>
        /// <param name="password">密码</param>
        /// <param name="authcode">验证码</param>
        /// <returns>0:用户不存在，1：正常，2验证码错误</returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public string CheckLogin(string username, string password, string authcode)
        {
            string result = "0";
            //取得验证码
            var objauthcode = currentUser.GetSession(Constant.Sessionkey);
            if (objauthcode == null || objauthcode.ToString() != authcode)
            {
                return "2";
            }

            var viewuser = new SystemUser();

            string checkuser = userFacade.CheckLogin(username, password, out viewuser);

            //验证成功,存储session.
            if (checkuser == "true")
            {
                result = "1";
                currentUser.SetUser(viewuser.Id);
            }
            return result;
        }
        /// <summary>
        /// 修改用户密码
        /// </summary>
        /// <param name="userid"></param>
        /// <param name="oldpwd"></param>
        /// <param name="newpwd"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public string ModifyPwd(string oldpwd, string newpwd)
        {
            string userid = currentUser.UserInfo.Id;
            string result = userFacade.ModifyPwd(userid, oldpwd, newpwd);
            return result;
        }
        //修改密码页面显示
        public ActionResult Myconfig()
        {
            ViewData["userid"] = currentUser.UserInfo.Id.ToString();
            return View();
        }

        /// <summary>
        /// 注销用户session信息
        /// </summary>
        public string LoginOut()
        {
            //移除用户session;
            currentUser.RemoveUser();
            return "true";
        }

        /// <summary>
        /// 生成验证码
        /// </summary>
        public ActionResult Authcode()
        {
            string code = Imagecode.CreateValidateNumber(4);
            currentUser.SetSession(Constant.Sessionkey, code);
            return File(Imagecode.CreateValidateGraphic(code), "image/jpeg");
        }
    }
}
