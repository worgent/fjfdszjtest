using System;

namespace QzgfFrame.Utility.Common.Helpers
{
    public class CommonHelper
    {

        #region 转换函数
        /// <summary>
        /// object 转换为 int
        /// </summary>
        /// <returns></returns>
        public static long ObjToLong(object obj)
        {
            if (obj == null)
                return -1;
            try
            {
                if (obj.Equals(DBNull.Value))
                    return -1;

                long returnValue;

                if (long.TryParse(obj.ToString(), out returnValue))
                {
                    return returnValue;
                }
                else
                {
                    return -1;
                }
            }
            catch
            {
                return -1;
            }
        }
        /// <summary>
        /// object 转换为 int
        /// </summary>
        /// <returns></returns>
        public static int ObjToInt(object obj)
        {
            if (obj == null)
                    return 0;
            try
            {
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
            catch
            {
                return 0;
            }
        }
        /// <summary>
        /// object 转换为 short
        /// </summary>
        /// <returns></returns>
        public static short ObjToShort(object obj)
        {
            if (obj == null)
                    return 0;
            try
            {
                if (obj.Equals(DBNull.Value))
                    return 0;

                return short.Parse(obj.ToString());
            }
            catch
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
            try
            {
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
            catch
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
            try
            {
                if (obj.Equals(DBNull.Value))
                    return null;

                return ObjToInt(obj);
            }
            catch
            {
                return null;
            }
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
            try
            {
                if (obj.Equals(DBNull.Value))
                    return "";
                return Convert.ToString(obj).Trim();
            }
            catch
            {
                return ""; ;
            }
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
            try
            {
                if (obj.Equals(DBNull.Value))
                    return null;

                return ObjToDecimal(obj);
            }
            catch
            {
                return null;
            }
        }
        /// <summary>
        /// object 转换为 TimeSpan
        /// </summary>
        /// <returns></returns>
        public static TimeSpan ObjToTimeSpan(object obj)
        {
            if (obj == null)
                return TimeSpan.Parse("0");
            try
            {
                if (obj.Equals(DBNull.Value))
                    return TimeSpan.Parse("0");

                return TimeSpan.Parse(obj.ToString());
            }
            catch
            {
                return TimeSpan.Parse("0");
            }
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
