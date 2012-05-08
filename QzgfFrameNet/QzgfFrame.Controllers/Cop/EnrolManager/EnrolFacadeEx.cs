using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace QzgfFrame.Controllers.Cop.EnrolManager
{
    public interface EnrolFacadeEx
    {
        #region excel导入

        /// <summary>
        /// 验证文件内容数据
        /// </summary>
        /// <param name="myArray">读取的文件首行标题</param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <returns></returns>
        bool CheckBodyData(Array myArray, out string procInfo, string[] aryTilte);

        /// <summary>
        /// 验证文件内容数据，并保存数据
        /// </summary>
        /// <param name="myArray">读取的文件首行标题</param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="titleNum">自定义的首行标题个数</param>
        /// <param name="userid">用户id</param>
        /// <returns></returns>
        bool LoadBodyData(Array myArray, out string procInfo, string[] aryTilte, string userid);

        /// <summary>
        /// 验证文件标题字段
        /// </summary>
        /// <param name="myArray">读取的文件首行标题</param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <returns></returns>
        bool CheckHeadData(Array myArray, out string procInfo, string[] aryTilte);

        /// <summary>
        /// 检测数据
        /// </summary>
        /// <param name="strFileName"></param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="reFileName"></param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <param name="beginLetter">导入文件列首字母</param>
        /// <param name="endLetter">导入文件列末字母</param>
        /// <param name="checkMsgPath">验证导入文件的返回信息文件保存路径</param>
        /// <returns></returns>
        bool CheckExcelData(string strFileName, out string procInfo, out string reFileName, string[] aryTilte,
            string beginLetter, string endLetter, string checkMsgPath);

        /// <summary>
        /// 导入数据库
        /// </summary>
        /// <param name="strFileName"></param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="reFileName"></param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <param name="beginLetter">导入文件列首字母</param>
        /// <param name="endLetter">导入文件列末字母</param>
        /// <param name="userid">用户id</param>
        /// <returns></returns>
        bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string[] aryTilte,
            string beginLetter, string endLetter,string saveMsgPath, string userid);

        #endregion excel导入

        #region 导出到excel

        /// <summary>
        /// 导出到excel
        /// </summary>
        /// <param name="ary">数组</param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <returns></returns>
        bool ExcelOut(string[] ary, string[] aryTilte);

        #endregion 导出到excel

        #region 导出word

        bool WordOut(string savePath, string id, out string msg);

        #endregion 导出word
    }
}
