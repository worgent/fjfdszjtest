#region License

/*
 * Copyright ?2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#endregion

#region Imports

using System;
using System.Linq;
using AopAlliance.Intercept;
using QzgfFrame.Controllers.CommonSupport;
using QzgfFrame.Utility.Core;
using QzgfFrame.Utility.Core.Common;

#endregion

namespace QzgfFrame.Mvc3.CommonSupport
{
    public class LoggingAroundAdvice : IMethodInterceptor
	{
        public CurrentUser currentUser { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger(typeof(LoggingAroundAdvice));//typeof(CommonLoggingAroundAdvice)  "Logger"

		public object Invoke(IMethodInvocation invocation)
		{
            //回调原方法
			object returnValue = invocation.Proceed();
		    string ipstr=CommonHelper.ClientIP();
		    //string username = currentUser.UserInfo.Id;
		    object[] tmp= invocation.Method.GetCustomAttributes(true);

            //写入日志
		    String msg = String.Format(
                "ConsoleLoggingAroundAdvice拦截器::Arguments '{0}' method: '{1}',targestring:'{2}',targetype:'{3}',returned '{4}',ip '{5}',username{6}",
		        invocation.Arguments,
		        invocation.Method.Name,
		        invocation.Target,
		        invocation.TargetType,
		        returnValue,ipstr,"");
            //打印日志
            Logger.Info(msg);

            //查找相应权限信息
		    string permissionsflag = invocation.Method.Name;
		    var curmenufieldlist= currentUser.MenufieldPermission.Where(m => m.Permissionsflag == permissionsflag);
            foreach (var curmenufield in curmenufieldlist)
            {
                //显示过滤
                if (!curmenufield.IsShow)
                {
                    //结果集,设置值为空串,加入标识位
                    //curmenufield.Fieldcode;
                }
                //打印
                if (!curmenufield.IsPrint)
		        {
                    //curmenufield.Fieldcode;
		        }
                if(!curmenufield.IsExport)
                {

                }
            }

            //处理结果集
            if(invocation.Method.ReturnType==typeof(string))
            {

            }

        //      string text = "One car red car blue car";
        //      string pat = @"(w+)s+(car)";
        //     Regex r = new Regex(pat, RegexOptions.IgnoreCase);
        //     Match m = r.Match(text);
        //     int matchCount = 0;
        //     while (m.Success)
        //    {
        //          Console.WriteLine("Match" + (++matchCount));
        //          for (int i = 1; i <= 2; i++)
        //          {
        //             Group g = m.Groups[i];
        //             Console.WriteLine(string.Format("Group{0}='{1}'", i, g));
        //             CaptureCollection cc = g.Captures;
        //for (int j = 0; j < cc.Count; j++)
        //         {
        //            Capture c = cc[j];
        //            Console.WriteLine(string.Format(
        //                 "Capture{0}='{1}', Position={2}", j, c, c.Index));
        //        }
        //    }
        //    m = m.NextMatch();
        //}
        //Console.ReadLine();
            //js 删除Json数据中的age属性         delete json["age"]; 
		    /* 
            var bc = (invocation.Target) as BaseController;
		    if(bc != null && !bc.currentUser.IsHaveUser())
		    { 
		        bc.RedirectToAction("login","home");
		        return null;
		    }
            */

		    //
			return returnValue;
		}
	}
}