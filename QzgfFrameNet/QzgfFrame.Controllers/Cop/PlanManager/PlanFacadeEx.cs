using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Excel = Microsoft.Office.Interop.Excel;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;
using System.Web;
using System.IO;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.Cop.PlanManager.Domain;

namespace QzgfFrame.Controllers.Cop.PlanManager
{
    public class PlanFacadeEx : PlanFacadeExImpl
    {
        #region

        /// <summary>
        /// 专线巡检计划
        /// </summary>
        private PlanFacade planFacade { set; get; }

        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        #endregion

        /// <summary>
        /// 验证文件内容数据
        /// </summary>
        /// <param name="myArray">读取的文件首行标题</param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <returns></returns>
        public bool CheckBodyData(Array myArray, out string procInfo, string[] aryTilte)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < aryTilte.Count())
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = ""; object value = null;
                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "人员姓名不可为空!!";
                }
                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() != "")
                {
                    if (str.Trim() != "男" && str.Trim() != "女")
                        procInfo += "性别格式错误!!";
                }
                else
                    procInfo += "性别不可为空!!";
                value = myArray.GetValue(1, 4);
                DateTime? birthtime = ExtensionMethods.ToDateOANull(value);
                if (birthtime == null)
                {
                    procInfo += "出生日期不可为空!!";
                }
                value = myArray.GetValue(1, 5);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "身份证号不可为空!!";
                }
                value = myArray.GetValue(1, 6);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "手机号码不可为空!!";
                }
                value = myArray.GetValue(1, 7);
                str = ExtensionMethods.ToStr(value);

            }
            if (procInfo != "") isAllValid = false;
            return isAllValid;
        }

        /// <summary>
        /// 读取文件内容数据
        /// </summary>
        /// <param name="myArray">读取的文件首行标题</param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <param name="userid">用户id</param>
        /// <returns></returns>
        public bool LoadBodyData(Array myArray, out string procInfo, string[] aryTilte, string userid)
        {
            procInfo = "";
            bool isAllValid = true;
            if (myArray.Length < aryTilte.Count())
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                Plan entity = new Plan();

                string str = ""; 
                object value = null;

                //"专线名称":1,是否为空;2,是否存在
                value = myArray.GetValue(1, 1);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "专线名称不可为空!!";
                    isAllValid = false;
                }
                entity.BizType = str;

                value = myArray.GetValue(1, 2);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "集团名称不可为空!!";
                    isAllValid = false;
                }
                entity.GroupName = str;

                value = myArray.GetValue(1, 3);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "业务保障等级不可为空!!";
                    isAllValid = false;
                }
                entity.BizAssuranLeve = str;

                //根据"专线名称","集团客户id","业务保障等级"

                value = myArray.GetValue(1, 4);
                DateTime? birthtime = ExtensionMethods.ToDateOANull(value);
                if (birthtime == null)
                {
                    procInfo += "巡检周期不可为空!!";
                    isAllValid = false;
                }
                entity.CopCycTime = birthtime.ToString();

                entity.CreateUser = userid;

                CopPlan copPlan = new CopPlan();
                copPlan.CreateUserId = entity.CreateUser;
                copPlan.CycTime = "";
                copPlan.DedicateLineId = "20120222151808566374";
                copPlan.CreationTime = dateNow;
                if (isAllValid)
                {
                    isAllValid = planFacade.Save(copPlan, "0");
                }
            }
            return isAllValid;
        }

        /// <summary>
        /// 验证文件标题字段
        /// </summary>
        /// <param name="myArray">读取的文件首行标题</param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <returns></returns>
        public bool CheckHeadData(Array myArray, out string procInfo, string[] aryTilte)
        {
            procInfo = "";
            bool isAllValid = true;

            //判断标题字段是否完全
            if (myArray.Length < aryTilte.Length)
            {
                procInfo += "数据没有读取完整!!";
                isAllValid = false;
            }
            else
            {
                string str = "";
                object value = null;
                //验证标题是否完整
                for (int i = 1; i <= aryTilte.Length; i++)
                {
                    value = myArray.GetValue(1, i);
                    str = ExtensionMethods.ToStr(value);
                    if (str.Trim() != aryTilte[i - 1])
                    {
                        procInfo += "文件中未找到'" + aryTilte[i - 1] + "'列";
                        isAllValid = false;
                    }
                }
            }
            return isAllValid;
        }

        /// <summary>
        /// 检测数据
        /// </summary>
        /// <param name="strFileName"></param>
        /// <param name="procInfo">返回的错误信息</param>
        /// <param name="reFileName"></param>
        /// <param name="aryTilte">自定义的首行标题</param>
        /// <param name="beginLetter">导入文件列首字母</param>
        /// <param name="endLetter">导入文件列末字母</param>
        /// <returns></returns>
        public bool CheckExcelData(string strFileName, out string procInfo, out string reFileName, string[] aryTilte, 
            string beginLetter, string endLetter)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, 
                miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            //读取excel的列数
            int sheetColumn = aryTilte.Count(); 
            int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                //读取excel的列的起始位置:从A列到F列
                Excel.Range range = workSheet.get_Range(beginLetter + i.ToString(), endLetter + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    //验证标题字段是否完整
                    isAllValid = CheckHeadData(myArray, out procInfo, aryTilte);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, 
                        Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = "验证结果";
                    if (procInfo == "")
                    {
                        procInfo = "验证成功!!";
                    }
                }
                else
                {
                    string myText = "";
                    //验证文件内容数据是否正确
                    isAllValid = CheckBodyData(myArray, out myText, aryTilte);
                    if (myText == "")
                    {
                        myText = "验证成功!!";
                        m++;
                    }
                    else
                    {
                        isAllValid = false;
                    }
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 1) + "条，成功" + m + "条,未成功" + (sheetRow - 1 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/Personnel");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, 
                miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }

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
        public bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string[] aryTilte,
            string beginLetter,string endLetter, string userid)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, 
                miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 33; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range(beginLetter + i.ToString(), endLetter + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo,aryTilte);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, 
                        Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = "验证结果";

                }
                else
                {
                    string myText = "";
                    isAllValid = LoadBodyData(myArray, out myText, aryTilte, userid);
                    if (isAllValid)
                    {
                        myText += "导入数据库成功";
                        m++;
                    }
                    else
                        myText += "导入数据库失败!!";
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow - 1) + "条，成功" + m + "条,未成功" + (sheetRow - 1 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/Personnel");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, 
                miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }
    }
}
