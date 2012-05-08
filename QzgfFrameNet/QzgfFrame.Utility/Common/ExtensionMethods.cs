using System;
using System.Text.RegularExpressions;
using System.Collections.Generic;
using System.Linq;
using System.Data;
using System.Diagnostics;
using QzgfFrame.Utility.Common.Helpers;
using QzgfFrame.Utility.Entity;

namespace QzgfFrame.Utility.Common
{
    /// <summary>
    /// 扩展方法
    /// </summary>
    public static class ExtensionMethods
    {
        public static string GetLineState(short state)
        {
            try
            {
                if (state == 0)
                    return "正式移交";
                else if (state == 1)
                    return "预移交";
                else if (state == 2)
                    return "退网";
                else if (state == 3)
                    return "未移交";
                else
                    return "退网";
            }
            catch
            {
                return "退网";
            }
        }
        public static string GetLineType(string type)
        {
            try
            {
                if (type == "0")
                    return "本地";
                else if (type == "1")
                    return "跨地";
                else if (type == "2")
                    return "跨省";
                else
                    return "本地";
            }
            catch
            {
                return "本地";
            }
        }
        public static short ConvertToState(object state)
        {
            try
            {
                if (state == null)
                    return 4;
                else
                {
                    string strState = state.ToString().Trim();
                    if (strState == "正式移交")
                        return 0;
                    else if (strState == "预移交")
                        return 1;
                    else if (strState == "退网")
                        return 2;
                    else if (strState == "未移交")
                        return 3;
                    else
                        return 4;
                }
            }
            catch
            {
                return 4;
            }
        }

        public static string ConvertToLineType(object ltype)
        {
            try
            {
                if (ltype == null)
                    return "4";
                else
                {
                    string strState = ltype.ToString().Trim();
                    if (strState == "本地")
                        return "0";
                    else if (strState == "跨地")
                        return "1";
                    else if (strState == "跨省")
                        return "2";
                    else
                        return "4";
                }
            }
            catch
            {
                return "4";
            }
        }
        public static short ConvertToBool(object isStr)
        {
            try
            {
                if (isStr == null)
                    return 4;
                else
                {
                    string strState = isStr.ToString().Trim();
                    if (strState == "否")
                        return 0;
                    else if (strState == "是")
                        return 1;
                    else
                        return 4;
                }
            }
            catch
            {
                return 4;
            }
        }
        public static string GetEquipState(short state)
        {
            try
            {
                if (state == 0)
                    return "在线";
                else if (state == 1)
                    return "退网";
                else
                    return "退网";
            }
            catch
            {
                return "退网";
            }
        }
        #region 数据转换 /// <summary>
        public static bool IsNumeric(string str)
        {
            try
            {
                if (str != "")
                {
                    return Regex.IsMatch(str, @"(^([0-9]*)$)");
                }
                else
                    return false;
            }
            catch
            {
                return false;
            }
        }

        public static long ToLong(this object obj)
        {
            return CommonHelper.ObjToLong(obj);
        }
        public static int ToInt(this object obj)
        {
            return CommonHelper.ObjToInt(obj);
        }
        public static string ToStr(this object obj)
        {
            return CommonHelper.ObjToStr(obj);
        }

        public static decimal ToDecimal(this object obj)
        {
            return CommonHelper.ObjToDecimal(obj);
        }

        public static bool IPCheck(string IP)
        {
            string num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
            return Regex.IsMatch(IP, ("^" + num + "\\." + num + "\\." + num + "\\." + num + "$"));
        }
        //身份证号
        public static bool CardCheck(string card)
        {
            if (card.Length == 15)
            {
                if (ToLong(card) != -1)
                    return true;
            }
            else if (card.Length == 18)
            {
                if (ToLong(card.Substring(0, 17)) != -1)
                {
                    string endstr = card.Substring(17, 1);
                    if (ToLong(endstr) != -1 || endstr == "X" || endstr == "x")
                        return true;
                }
            }
            return false;
        }
        public static bool MacCheck(string Mac)
        {
            string pattrn = @"(^[0-9a-fA-F][0-9a-fA-F]-[0-9a-fA-F][0-9a-fA-F]-[0-9a-fA-F][0-9a-fA-F]-[0-9a-fA-F][0-9a-fA-F]-[0-9a-fA-F][0-9a-fA-F]-[0-9a-fA-F][0-9a-fA-F]$)";
            return Regex.IsMatch(Mac, pattrn);
        }

        public static int? ToIntNull(this object obj)
        {
            return CommonHelper.ObjToIntNull(obj);
        }

        public static decimal? ToDecimalNull(this object obj)
        {
            return CommonHelper.ObjToDecimalNull(obj);
        }

        public static bool ToBool(this object obj)
        {
            return CommonHelper.ObjToBool(obj);
        }
        /// <summary>
        /// 把时长转换为具体天数
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static string GetDiffTime(TimeSpan span)
        {
            string strResout = "";
            //获得2时间的时间间隔秒计算  
            int iTatol = Convert.ToInt32(span.TotalSeconds);

            int iMinutes = 1 * 60;
            int iHours = iMinutes * 60;
            int iDay = iHours * 24;
            int iMonth = iDay * 30;
            int iYear = iMonth * 12;

            if (iTatol > iYear)
            {
                int year = iTatol / iYear;
                strResout += year + "年";
                int month = iTatol - (iYear * year) ;
                strResout += month / iMonth + "月";
                int day = iTatol - (iYear * year) - (month / iMonth * iMonth);
                strResout += day / iDay + "天";
                int hour = iTatol - (iYear * year) - (month / iMonth * iMonth) - (day / iDay * iDay);
                strResout += hour / iHours + "小时";
                int minute = iTatol - (iYear * year) - (month / iMonth * iMonth) - (day / iDay * iDay) - (hour / iHours * iHours);
                strResout += minute / iMinutes + "分钟";
                int second = iTatol - (iYear * year) - (month / iMonth * iMonth) - (day / iDay * iDay) - (hour / iHours * iHours) - (minute / iMinutes * iMinutes);
                strResout += second  + "秒";
            }
            else if (iTatol > iMonth)
            {
                int month = iTatol ;
                strResout += month / iMonth + "月";
                int day = iTatol -  (month / iMonth * iMonth);
                strResout += day / iDay + "天";
                int hour = iTatol -  (month / iMonth * iMonth) - (day / iDay * iDay);
                strResout += hour / iHours + "小时";
                int minute = iTatol -  (month / iMonth * iMonth) - (day / iDay * iDay) - (hour / iHours * iHours);
                strResout += minute / iMinutes + "分钟";
                int second = iTatol -  (month / iMonth * iMonth) - (day / iDay * iDay) - (hour / iHours * iHours) - (minute / iMinutes * iMinutes);
                strResout += second + "秒";
            }
            else if (iTatol > iDay)
            {
                int day = iTatol ;
                strResout += day / iDay + "天";
                int hour = iTatol -  (day / iDay * iDay);
                strResout += hour / iHours + "小时";
                int minute = iTatol - (day / iDay * iDay) - (hour / iHours * iHours);
                strResout += minute / iMinutes + "分钟";
                int second = iTatol - (day / iDay * iDay) - (hour / iHours * iHours) - (minute / iMinutes * iMinutes);
                strResout += second + "秒";
            }
            else if (iTatol > iHours)
            {
                int hour = iTatol;
                strResout += hour / iHours + "小时";
                int minute = iTatol - (hour / iHours * iHours);
                strResout += minute / iMinutes + "分钟";
                int second = iTatol - (hour / iHours * iHours) - (minute / iMinutes * iMinutes);
                strResout += second + "秒";
            }
            else if (iTatol > iMinutes)
            {
                int minute = iTatol ;
                strResout += minute / iMinutes + "分钟";
                int second = iTatol - (minute / iMinutes * iMinutes);
                strResout += second + "秒";
            }
            else
            {
                strResout += iTatol  + "秒";
            }
            return strResout;
        } 

        /// <summary>
        /// 转换为日期
        /// </summary>
        /// <param name="obj"></param>
        /// <returns></returns>
        public static DateTime? ToDateNull(this object obj)
        {
            return CommonHelper.ObjToDateNull(obj);
        }
        public static DateTime? ToDateOANull(this object obj)
        {
            DateTime? dt = CommonHelper.ObjToDateNull(obj);
            try
            {
                if (dt == null && obj != null)
                {
                    return DateTime.FromOADate(Convert.ToDouble(obj));
                }
                else
                    return dt;
            }
            catch
            {
                return null;
            }
        }

        public static double ToDouble(this decimal value)
        {
            return Convert.ToDouble(value);
        }
        public static short ToShort(this object obj)
        {
            return CommonHelper.ObjToShort(obj);
        }
        public static short ObjStrToShort(object obj)
        {
            if (obj == null)
                return 0;
            try
            {
                string objstr = obj.ToString();
                if (objstr == "是")
                    return 1;
                else if (objstr == "否")
                    return 0;
                else return 0;
            }
            catch
            {
                return 0;
            }
        }
        public static short ObjSexToShort(object obj)
        {
            if (obj == null)
                return 0;
            try
            {
                string objstr = obj.ToString();
                if (objstr == "女")
                    return 1;
                else if (objstr == "男")
                    return 0;
                else return 0;
            }
            catch
            {
                return 0;
            }
        }
        public static short ObjLocalToShort(object obj)
        {
            if (obj == null)
                return 0;
            try
            {
                string objstr = obj.ToString();
                if (objstr == "局端")
                    return 1;
                else if (objstr == "用户机房")
                    return 0;
                else return 0;
            }
            catch
            {
                return 0;
            }
        }
        public static TimeSpan ToTimeSpan(this object obj)
        {
            return CommonHelper.ObjToTimeSpan(obj);
        }
        #endregion

        #region 实体相关的扩展方法
        /// <summary>
        /// 从 DataTable 转换到 实体集合
        /// </summary> 
        public static List<T> ToEntities<T>(this DataTable source)
            where T : EntityBase, new()
        {
            return EntityHelper.DataTableToEntities<T>(source);
        }

        /// <summary>
        /// 从 DataRow 转换到 TEntity
        /// </summary> 
        public static T ToEntity<T>(DataRow row) where T : EntityBase, new()
        {
            return EntityHelper.DataRowToEntity<T>(row);
        }
        #endregion

        #region 常见判断扩展函数
        /// <summary>
        /// 判断一个整数是否为有效的 Id，增强代码可读性
        /// </summary>
        /// <param name="value"></param>
        /// <returns></returns>
        public static bool IsId(this int value)
        {
            return value > 0;
        }

        /// <summary>
        /// 判断一个数值是否存在于集合
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="t"></param>
        /// <param name="enumerable"></param>
        /// <returns></returns>
        public static bool In<T>(this T t, IEnumerable<T> enumerable)
        {
            return enumerable.Contains(t);
        }

        /// <summary>
        /// 判断一个字符串是否存在于数组
        /// </summary>
        /// <param name="str"></param>
        /// <param name="args"></param>
        /// <returns></returns>
        public static bool In(this string str, params string[] args)
        {
            foreach (var arg in args)
            {
                if (arg.Equals(str))
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// 判断数值是否在指定的范围内
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="t"></param>
        /// <param name="minT"></param>
        /// <param name="maxT"></param>
        /// <returns></returns>
        public static bool InRange<T>(this IComparable<T> t, T minT, T maxT)
        {
            return t.CompareTo(minT) >= 0 && t.CompareTo(maxT) <= 0;
        }


        /// <summary>
        /// 根据字符串进行分隔
        /// </summary>
        /// <param name="str"></param>
        /// <param name="splitString"></param>
        /// <returns></returns>
        public static string[] Split(this string str, string splitString)
        {
            return str.Split(new[] { splitString }, StringSplitOptions.None);
        }

        /// <summary>
        /// 格式化字符串，等同于 string.Format()
        /// Luoly 090728
        /// </summary>
        /// <param name="str"></param>
        /// <param name="args"></param>
        /// <returns></returns>
        public static string FormatString(this string str, params object[] args)
        {
            return string.Format(str, args);
        }

        /// <summary>
        /// 是否为空
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static bool IsEmpty(this string str)
        {
            return string.IsNullOrEmpty(str);
        }


        /// <summary>
        /// 返回不带时间的日期
        /// </summary>
        /// <param name="dateTime"></param>
        /// <returns></returns>
        public static DateTime ShortDateValue(this DateTime dateTime)
        {
            return new DateTime(dateTime.Year, dateTime.Month, dateTime.Day);
        }
        #endregion

        #region TryCatch 扩展
        /// <summary>
        /// TryCatch 扩展
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="source"></param>
        /// <param name="action">要执行的动作</param>
        /// <param name="failureAction">失败的委托</param>
        /// <param name="successAction">成功的委托</param>
        /// <returns>如果成功返回 True</returns>
        public static bool TryCatch<T>(this T source, Action<T> action,
Action<Exception> failureAction, Action<T> successAction) where T : class
        {
            try
            {
                action.Invoke(source);
                successAction.Invoke(source);
                return true;
            }
            catch (Exception ex)
            {
#if DEBUG
                Trace.WriteLine(ex.Message);
                Trace.WriteLine(ex.StackTrace);
#endif
                failureAction.Invoke(ex);
                return false;
            }
        }

        /// <summary>
        /// TryCatch 扩展
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="source"></param>
        /// <param name="action">要执行的动作，只能是表达式</param>
        /// <param name="failureAction">失败的委托</param>
        /// <returns>如果成功返回 True</returns>
        public static bool TryCatch<T>(this T source, Action<T> action, Action<Exception> failureAction) where T : class
        {
            return source.TryCatch<T>(action, failureAction, c => { });
        }

        /// <summary>
        /// TryCatch 扩展
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <typeparam name="U"></typeparam>
        /// <param name="source"></param>
        /// <param name="func">要执行的函数，只能是表达式</param>
        /// <param name="failureAction">失败的委托</param>
        /// <param name="successAction">成功的委托</param>
        /// <returns></returns>
        public static U TryCatch<T, U>(this T source, Func<T, U> func,
            Action<Exception> failureAction, Action<T> successAction) where T : class
        {
            try
            {
                var result = func.Invoke(source);
                successAction.Invoke(source);
                return result;
            }
            catch (Exception ex)
            {
#if DEBUG
                Trace.WriteLine(ex.Message);
                Trace.WriteLine(ex.StackTrace);
#endif
                failureAction.Invoke(ex);
                return default(U);
            }
        }

        /// <summary>
        /// TryCatch 扩展
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <typeparam name="U"></typeparam>
        /// <param name="source"></param>
        /// <param name="func">要执行的函数，只能是表达式</param>
        /// <param name="failureAction">失败的委托</param>
        /// <returns></returns>
        public static U TryCatch<T, U>(this T source, Func<T, U> func, Action<Exception> failureAction) where T : class
        {
            return source.TryCatch<T, U>(func, failureAction);
        }
        
        #endregion
         

    }
}
