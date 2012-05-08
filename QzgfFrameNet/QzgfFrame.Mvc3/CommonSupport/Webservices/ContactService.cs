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
using System.Collections.Generic;
using Spring.Context;
using Spring.Context.Support;

#endregion

namespace QzgfFrame.Mvc3.CommonSupport.Webservices
{
    /// <summary>
    /// Default implementation of the <see cref="IContactService"/>
    /// service interface.
    /// </summary>
    /// <author>Bruno Baia</author>
    /// <version>$Id: ContactService.cs,v 1.1 2007/05/31 18:34:54 markpollack Exp $</version>
    public class ContactService : IContactService
    {
        bool _flag = true;//开关设置

        private List<string> _emails;
        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        /// <summary>
        /// List of emails addresses for all contacts.
        /// </summary>
        public List<string> Emails
        {
            get { return _emails; }
            set { _emails = value; }
        }

        /// <summary>
        /// Creates a new instance of the <see cref="ContactService"/> class.
        /// </summary>
        public ContactService()
        {
        }

        /// <summary>
        /// Returns an array of emails, with a maximum of <paramref name="count"/> 
        /// values, that complete the given <paramref name="prefixText"/>.
        /// </summary>
        /// <param name="prefixText">Text entered by the user.</param>
        /// <param name="count">Maximum number of possible values.</param>
        /// <returns>The array of emails for completing the text.</returns>
        public string[] GetEmails(string prefixText, int count)
        {
            var emails = new List<string>();
            int i = 0;
            foreach (string email in _emails)
            {
                if (email.ToLower().StartsWith(prefixText.ToLower()))
                {
                    emails.Add(email);
                    if (++i == count) break;
                }
            }
            Logger.Info("ContactService: prefixText" + prefixText + ",count:" + count);
            //测试uuvws改为ture;
            _flag = true;//开关设置

            if(_flag)
            {
                _flag = false;
                Test();
            }

            return emails.ToArray();
        }

        public void Test()
        {
            IApplicationContext ctx = ContextRegistry.GetContext();
            try
            {
                //var wsc = ctx.GetObject("ContactWebServiceClient") as IContactService;
                var wsc = ctx.GetObject("uuvwsserviceclient") as Iuuvwsservice;

                if (wsc != null)
                {
                    string[] tmp = wsc.GetDirectlyUnderCompanyByADUser("1");
                    foreach (var s in tmp)
                    {
                        Logger.Info("value:" + s);
                    }
                }
            }
            catch (Exception ex)
            {
               Logger.Info(ex.Message,ex);
            }

        }

    }
}
