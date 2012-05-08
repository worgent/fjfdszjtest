using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Utility.Core
{
    public class Constant
    {
        //用户session存储
        public static string Userkey = "Userkey";
        public static string Sessionkey = "Sessionkey";
        public static string ExamineeKey = "ExamineeKey";

        //权限控制专用标识位(数据权限)
        public static int Show = 1;
        public static int Print = 2;
        public static int Export = 4;
        //权限控制专用标识位(菜单权限)
        //2011-12-6　导入，退网
        public enum Optflag:int
        {
            List=1,
            Add=2,
            Del=4,
            Modify=8,
            Usestart=16,
            Usestop=32,
            Import=64,
            Netout=128,
            Search=256
        }
    }
}
