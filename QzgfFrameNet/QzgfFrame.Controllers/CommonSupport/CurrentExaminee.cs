using System;
using System.Collections.Generic;
using System.Web;
using QzgfFrame.Exam.ExamineeInfoManger.Domain;
using QzgfFrame.Exam.ExamineeInfoManger.Models;
using QzgfFrame.Exam.ExamTypeManger.Domain;
using QzgfFrame.Exam.ExamTypeManger.Models;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.Cache;

namespace QzgfFrame.Controllers.CommonSupport
{
    /// <summary>
    /// 当前考生信息
    /// </summary>
    public class CurrentExaminee
    {
        #region 变量定义

        //spring中注入
        public ICache cacheFacade { set; get; }
        public ExamineeInfoFacade examineeinfoFacade { set; get; }
        private ExamTypeFacade examtypeFacade { set; get; }
        //方法中设置这些属性的值
        //考生信息
        public ExamineeInfo ExamineeInfo { get; set; }
        #endregion

        #region 基本操作

        /// <summary>
        /// 在登录成功后,将考生信息加入到session中
        /// </summary>
        /// <param name="examineeid"></param>
        public void SetExaminee(string examineeid)
        {
            ExamineeInfo = examineeinfoFacade.GetExamineeInfo(examineeid);
            ExamType examtype = examtypeFacade.Get(ExamineeInfo.ExamExamineeInfo.ExamTypeID);
            ExamineeInfo.ExamType = examtype.Type;
            ExamineeInfo.FullName = ExamineeInfo.ExamExamineeInfo.FullName;
            cacheFacade.SetSessionCache(Constant.ExamineeKey, this);
        }

        /// <summary>
        /// 从缓存中取得考生信息
        /// </summary>
        /// <returns>false:seesion过期,true:可用</returns>
        public bool IsHaveUser()
        {
            if (cacheFacade.GetSessionCache(Constant.ExamineeKey) == null)
            {
                return false;
            }
            else
            {
                var e = (CurrentExaminee)cacheFacade.GetSessionCache(Constant.ExamineeKey);
                ExamineeInfo = e.ExamineeInfo;
                return true;
            }
        }

        /// <summary>
        /// 移除用户session
        /// </summary>
        public void RemoveUser()
        {
            cacheFacade.RemoveSessionCache(Constant.ExamineeKey);
        }

        #endregion


        #region 加强

        /// <summary>
        /// 调用session存储信息
        /// </summary>
        /// <param name="key"></param>
        /// <param name="obj"></param>
        public void SetSession(string key, object obj)
        {
            cacheFacade.SetSessionCache(key, obj);
        }

        /// <summary>
        /// 取得session信息
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public object GetSession(string key)
        {
            return cacheFacade.GetSessionCache(key);
        }

        #endregion


    }
}
