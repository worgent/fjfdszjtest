/*
 * 文件名.........: ExamineeController.cs
 * 作者...........:  
 * 说明...........: 考生控制器类  
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 
*/

using System.Web.Mvc;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.System.UserManger.Domain;
using QzgfFrame.System.UserManger.Models;
using QzgfFrame.Exam.ExamineeInfoManger.Domain;
using QzgfFrame.Exam.ExamineeInfoManger.Models;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.Common;

namespace QzgfFrame.Controllers.Exam.ExamineeManger
{
    public class ExamineeController : BaseController
    {
        private UserFacade userFacade { set; get; }
        private ExamineeInfoFacade examineeinfoFacade { set; get; }

        //首页
        public ActionResult Index()
        {
            ExamineeInfo ei = currentExaminee.ExamineeInfo;
            if (ei == null)
            {
                return View("login");
            }
            return View(ei);
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
        /// 考生登录验证
        /// </summary>
        /// <param name="loginname">考生登录名</param>
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

            var examinee = new ExamExamineeInfo();

            string checkuser = examineeinfoFacade.CheckLogin(username, password, out examinee);

            //验证成功,存储session.
            if (checkuser == "true")
            {
               result = "1";
               currentExaminee.SetExaminee(examinee.ID);
               currentUser.SetUser("EX201112091752534276");
            }
            return result;
        }
        /// <summary>
        /// 修改考生密码
        /// </summary>
        /// <param name="userid"></param>
        /// <param name="oldpwd"></param>
        /// <param name="newpwd"></param>
        /// <returns></returns>
        [AcceptVerbs(HttpVerbs.Post)]
        public string ModifyPwd(string oldpwd, string newpwd)
        {
            string userid = currentExaminee.ExamineeInfo.ExamExamineeInfo.ID;
            string result = examineeinfoFacade.ModifyPwd(userid, oldpwd, newpwd);
            return result;
        }
        //修改密码页面显示
        public ActionResult Myconfig()
        {
            if (currentExaminee.ExamineeInfo == null)
            {
                return View("login");
            }
            ViewBag.examineeid = currentExaminee.ExamineeInfo.ExamExamineeInfo.ID.ToString();
            return View();
        }

        /// <summary>
        /// 注销用户session信息
        /// </summary>
        public string LoginOut()
        {
            //移除用户session;
            currentUser.RemoveUser();
            currentExaminee.RemoveUser();
            return "true";
        }

        /// <summary>
        /// 生成验证码
        /// </summary>
        public ActionResult Authcode()
        {
            string code = Imagecode.CreateValidateNumber(4);
            currentExaminee.SetSession(Constant.Sessionkey, code);
            return File(Imagecode.CreateValidateGraphic(code), "image/jpeg");
        }
    }
}
