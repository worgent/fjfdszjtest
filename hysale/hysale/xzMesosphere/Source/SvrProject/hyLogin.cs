using System;
using System.Collections.Generic;
using System.Text;
using XzzLibrary.Source;

namespace xzMesosphere.Source
{
    public class hyLogin
    {
        public static void Login(xzServerManage FServerManage)
        {
            hySystem.Login(FServerManage);
            hyHR.Login(FServerManage);
            ProjectCTI.Login(FServerManage);
        }
    }
}
