using System;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;

namespace QzgfFrame.Utility.Core.Common
{
    public class CommonHelper
    {
        /// <summary>
        /// 验证权限值
        /// </summary>
        /// <param name="powval">从数据库中取得值</param>
        /// <param name="flagbit">1:查看,2:打印,4:导出</param>
        /// <returns></returns>
        public static bool CheckPower(int powval,int flagbit)
        {
            return (powval & flagbit) == flagbit;
        }
        /// <summary>
        /// md5加密
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static string GetMd5Str32(string str)
        {
            string cl = "";
            if (str != null)
                cl = str;


            string pwd = "";
            MD5 md5 = MD5.Create(); //实例化一个md5对像
            // 加密后是一个字节类型的数组，这里要注意编码UTF8/Unicode等的选择　
            byte[] s = md5.ComputeHash(Encoding.UTF8.GetBytes(cl));
            // 通过使用循环，将字节类型的数组转换为字符串，此字符串是常规字符格式化所得
            for (int i = 0; i < s.Length; i++)
            {
                // 将得到的字符串使用十六进制类型格式。格式后的字符是小写的字母，如果使用大写（X）则格式后的字符是大写字符 
                pwd = pwd + s[i].ToString("X");

            }
            return pwd;
        }
#region ip地址信息
        /// <summary>
        /// 获取客户端真实IP地址(可穿过代理服务器)
        /// </summary>
        /// <returns></returns>
        public static string ClientIP()
        {
            string result = String.Empty;
            result = HttpContext.Current.Request.ServerVariables["HTTP_X_FORWARDED_FOR"];
            if (result != null && result != String.Empty)
            {
                //可能有代理 
                if (result.IndexOf(".") == -1)     //没有“.”肯定是非IPv4格式 
                    result = null;
                else
                {
                    if (result.IndexOf(",") != -1)
                    {
                        //有“,”，估计多个代理。取第一个不是内网的IP。 
                        result = result.Replace(" ", "").Replace("'", "");
                        string[] temparyip = result.Split(",;".ToCharArray());
                        for (int i = 0; i < temparyip.Length; i++)
                        {
                            if (IsIPAddress(temparyip[i]) && temparyip[i].Substring(0, 3) != "10." && temparyip[i].Substring(0, 7) != "192.168" && temparyip[i].Substring(0, 7) != "172.16.")
                            {
                                return temparyip[i];     //找到不是内网的地址 
                            }
                        }
                    }
                    else if (IsIPAddress(result)) //代理即是IP格式 
                        return result;
                    else
                        result = null;     //代理中的内容 非IP，取IP 
                }

            }

            string IpAddress = (HttpContext.Current.Request.ServerVariables["HTTP_X_FORWARDED_FOR"] != null && HttpContext.Current.Request.ServerVariables["HTTP_X_FORWARDED_FOR"] != String.Empty) ? HttpContext.Current.Request.ServerVariables["HTTP_X_FORWARDED_FOR"] : HttpContext.Current.Request.ServerVariables["REMOTE_ADDR"];

            if (null == result || result == String.Empty)
                result = HttpContext.Current.Request.ServerVariables["REMOTE_ADDR"];

            if (result == null || result == String.Empty)
                result = HttpContext.Current.Request.UserHostAddress;

            return result;
        }

        /// <summary>
        /// 判断是否是IP地址格式 0.0.0.0
        /// </summary>
        /// <param name="str1">待判断的IP地址</param>
        /// <returns>true or false</returns>
        public static bool IsIPAddress(string str1)
        {
            if (str1 == null || str1 == string.Empty || str1.Length < 7 || str1.Length > 15) return false;

            string regformat = @"^\d{1,3}[\.]\d{1,3}[\.]\d{1,3}[\.]\d{1,3}$";

            Regex regex = new Regex(regformat, RegexOptions.IgnoreCase);
            return regex.IsMatch(str1);
        }
#endregion

        #region 转换函数
        /// <summary>
        /// object 转换为 int
        /// </summary>
        /// <returns></returns>
        public static int ObjToInt(object obj)
        {
            if (obj == null)
                return 0;
            if (obj.Equals(DBNull.Value))
                return 0;

            int returnValue;

            if (int.TryParse(obj.ToString(), out returnValue))
            {
                return returnValue;
            }
            else
            {
                return 0;
            }
        }

        /// <summary>
        /// 转换为boolean型
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static bool ObjToBool(object obj)
        {
            if (obj == null)
                return false;
            if (obj.Equals(DBNull.Value))
                return false;

            bool returnValue;

            if (bool.TryParse(obj.ToString(), out returnValue))
            {
                return returnValue;
            }
            else
            {
                return false;
            }
        }


        /// <summary>
        /// object 转换为 int?
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static int? ObjToIntNull(object obj)
        {
            if (obj == null)
                return null;
            if (obj.Equals(DBNull.Value))
                return null;

            return ObjToInt(obj);
        }


        /// <summary>
        /// object 转换为 string
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static string ObjToStr(object obj)
        {
            if (obj == null)
                return "";
            if (obj.Equals(DBNull.Value))
                return "";
            return Convert.ToString(obj);
        }


        /// <summary>
        /// object 转换为 decimal
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static decimal ObjToDecimal(object obj)
        {
            if (obj == null)
                return 0;
            if (obj.Equals(DBNull.Value))
                return 0;

            try
            {
                return Convert.ToDecimal(obj);
            }
            catch
            {
                return 0;
            }
        }

        /// <summary>
        /// object 转换为 decimal?
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static decimal? ObjToDecimalNull(object obj)
        {
            if (obj == null)
                return null;
            if (obj.Equals(DBNull.Value))
                return null;

            return ObjToDecimal(obj);
        }


        /// <summary>
        /// 转换为日期
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static DateTime? ObjToDateNull(object obj)
        {
            if (obj == null)
            {
                return null;
            }
            try
            {
                return Convert.ToDateTime(obj);
            }
            catch
            {
                return null;
            }
        }





        #endregion

    }
}
