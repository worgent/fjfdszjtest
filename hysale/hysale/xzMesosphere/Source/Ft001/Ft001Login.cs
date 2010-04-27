using System;
using System.Collections.Generic;
using XzzLibrary.Source;
using System.Text;
using System.Collections;

namespace xzMesosphere.Source
{
    public class Ft001Login
    {
        public static void Login(xzServerManage FServerManage)
        {
            HyTradeArchives.Login(FServerManage); //基础档案
            HyTradeOrder.Login(FServerManage);    //业务订单   
            HyTradeSample.Login(FServerManage);   //样品相关  
        }
    }
}
