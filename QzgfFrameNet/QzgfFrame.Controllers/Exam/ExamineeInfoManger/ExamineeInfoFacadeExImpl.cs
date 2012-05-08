/*
 * 文件名.........: ExamineeInfoFacadeExImpl.cs
 * 作者...........:  
 * 说明...........: 考生信息业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections.Generic;
using System.Collections;
using System.Text;
using QzgfFrame.Exam.ExamineeInfoManger.Domain;
using QzgfFrame.Exam.ExamineeInfoManger.Models;
using QzgfFrame.Exam.ExamTypeManger.Domain;
using QzgfFrame.Exam.ExamTypeManger.Models;
using QzgfFrame.Archives.DiplomaManger.Models;
using QzgfFrame.Archives.DiplomaManger.Domain;
using Excel = Microsoft.Office.Interop.Excel;
using QzgfFrame.Utility.Common;
using Newtonsoft.Json;

namespace QzgfFrame.Controllers.Exam.ExamineeInfoManger
{
    public class ExamineeInfoFacadeExImpl : ExamineeInfoFacadeEx
    {
        private ExamineeInfoFacade examineeinfoFacade { set; get; }
        private ExamTypeFacade examtypeFacade { set; get; }
        private DiplomaFacade diplomaFacade { set; get; }

        private bool CheckHeadData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "考生姓名 *")
            {
                procInfo += "文件中未找到'考生姓名'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 2);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "性 别 *")
            {
                procInfo += "文件中未找到'性 别'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 3);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "出生日期 *")
            {
                procInfo += "文件中未找到'出生日期'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "身份证号 *")
            {
                procInfo += "文件中未找到'身份证号'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 5);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "手机号码")
            {
                procInfo += "文件中未找到'手机号码'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 6);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "学历文凭 *")
            {
                procInfo += "文件中未找到'学历文凭'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 7);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "考试类型 *")
            {
                procInfo += "文件中未找到'考试类型'列";
                isAllValid = false;
            }
            value = myArray.GetValue(1, 8);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "备注")
            {
                procInfo += "文件中未找到'备注'列";
                isAllValid = false;
            }
            return isAllValid;
        }
        private bool CheckBodyData(Array myArray, out string procInfo)
        {
            procInfo = "";
            bool isAllValid = true;
            string str = ""; object value = null;
            
            value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value); 
            if (str.Trim() == "")
            {
                procInfo += "考生姓名不可为空!!";
            }

            value = myArray.GetValue(1, 2);
            str = ExtensionMethods.ToStr(value); 
            if (str.Trim() != "")
            {
                if (str.Trim() != "男" && str.Trim() != "女")
                    procInfo += "性别格式错误!!";               
            }
            else
                procInfo += "性别不可为空!!";

            value = myArray.GetValue(1, 3);
            DateTime birthtime = DateTime.FromOADate(Convert.ToDouble(value));
            if (birthtime == null)
            {
                procInfo += "出生日期不可为空!!";
            }

            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value); 
            if (str.Trim() == "")
            {
                procInfo += "身份证号不可为空!!";
            }

            value = myArray.GetValue(1, 5);
            str = ExtensionMethods.ToStr(value); 
            if (str.Trim() == "")
            {
                procInfo += "手机号码不可为空!!";
            }

            value = myArray.GetValue(1, 6);
            str = ExtensionMethods.ToStr(value);
            ArchiveDiploma diploma = new ArchiveDiploma();
            if (str.Trim() != "")
            {
                diploma = diplomaFacade.GetHql(str);
                if (diploma == null)
                    procInfo += "该学历文凭在系统中不存在!!";
            }
            else
                procInfo += "学历文凭不可为空!!";

            value = myArray.GetValue(1, 7);
            str = ExtensionMethods.ToStr(value);
            ExamType examtype = new ExamType();
            if (str.Trim() != "")
            {
                examtype = examtypeFacade.GetHql(str);
                if (examtype == null)
                    procInfo += "该考试类型在系统中不存在!!";
            }
            else
                procInfo += "考试类型不可为空!!";
          
            return isAllValid;
        }
        private bool LoadBodyData(Array myArray, out string procInfo, string userid)
        {
            procInfo = "";
            bool isAllValid = true;
            ExamExamineeInfo entity = new ExamExamineeInfo();
            string str = ""; object value = null;

            value = myArray.GetValue(1, 1);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() == "")
            {
                procInfo += "考试姓名不可为空!!";
                isAllValid = false;
            }
            entity.FullName = str;

            value = myArray.GetValue(1, 2);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "")
            {
                if (str.Trim() != "男" && str.Trim() != "女")
                {
                    procInfo += "性别格式错误!!";
                    isAllValid = false;
                }
                else
                {
                    entity.Sex = ExtensionMethods.ObjSexToShort(value);
                }
            }
            else
            {
                procInfo += "性别不可为空!!";
                isAllValid = false;
            }

            value = myArray.GetValue(1, 3);
            DateTime birthtime = DateTime.FromOADate(Convert.ToDouble(value));
            if (birthtime == null)
            {
                procInfo += "出生日期不可为空!!";
                isAllValid = false;
            }
            entity.BirthDate = birthtime;

            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() == "")
            {
                procInfo += "身份证号不可为空!!";
                isAllValid = false;
            }
            entity.IDCardNumber = str;

            value = myArray.GetValue(1, 5);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() == "")
            {
                procInfo += "手机号码不可为空!!";
                isAllValid = false;
            }
            entity.MobileNumber = str;

            value = myArray.GetValue(1, 6);
            str = ExtensionMethods.ToStr(value);
            ArchiveDiploma diploma = new ArchiveDiploma();
            if (str.Trim() != "")
            {
                diploma = diplomaFacade.GetHql(str);
                if (diploma == null)
                {
                    procInfo += "该学历文凭在系统中不存在!!";
                    isAllValid = false;
                }
                else
                    entity.DiplomaID = diploma.Id;
            }
            else
            {
                procInfo += "学历文凭不可为空!!";
                isAllValid = false;
            }

            value = myArray.GetValue(1, 7);
            str = ExtensionMethods.ToStr(value);
            ExamType examtype = new ExamType();
            if (str.Trim() != "")
            {
                examtype = examtypeFacade.GetHql(str);
                if (diploma == null)
                {
                    procInfo += "该考试类型在系统中不存在!!";
                    isAllValid = false;
                }
                else
                    entity.ExamTypeID = examtype.ID;
            }
            else
            {
                procInfo += "考试类型不可为空!!";
                isAllValid = false;
            }
            
            if (isAllValid)
                isAllValid = examineeinfoFacade.Save(entity);
            return isAllValid;
        }
        public bool CheckExcelData(string strFileName, out string procInfo, out string reFileName)
        {
            procInfo = "";
            bool isAllValid = true;
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 8; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "H" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = "验证结果";
                    if (procInfo == "")
                        procInfo = "验证成功!!";
                }
                else
                {
                    string myText = "";
                    isAllValid = CheckBodyData(myArray, out myText);
                    if (myText == "")
                    {
                        myText = "验证成功!!";
                        m++;
                    }
                    else
                        isAllValid = false;
                    procInfo += myText;
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 1) + "条，成功" + m + "条,未成功" + (sheetRow - 1 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/CheckData/ExamineeInfo");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
            workBook.Close(false, miss, miss);
            workBook = null;
            excelApp.Quit();
            excelApp = null;
            GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }
        public bool SaveExcelData(string strFileName, out string procInfo, out string reFileName, string userid)
        {
            procInfo = "";
            bool isAllValid = true; 
            object miss = Type.Missing;
            Excel.Application excelApp = new Excel.Application();
            excelApp.Workbooks.Open(strFileName, miss, false, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss, miss);
            Excel.Worksheet workSheet = (Excel.Worksheet)excelApp.Worksheets[1];

            int sheetRow = workSheet.UsedRange.Rows.Count;
            Excel.Range rng = null;
            int sheetColumn = 8; int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range("A" + i.ToString(), "H" + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 1];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick, Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = "验证结果";

                }
                else
                {
                    string myText = "";
                    isAllValid = LoadBodyData(myArray, out myText, userid);
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
            string strsavePath = HttpContext.Current.Request.MapPath("../Upload/ImportDataResult/ExamineeInfo");
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            reFileName = strFileName.Substring(strFileName.LastIndexOf("\\"));
            string filepath = strsavePath + reFileName;
            Excel.Workbook workBook = excelApp.Workbooks[1];
            workBook.SaveAs(filepath, miss, miss, miss, miss, miss, Excel.XlSaveAsAccessMode.xlNoChange, miss, miss, miss, miss, miss);
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
