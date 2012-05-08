using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Excel = Microsoft.Office.Interop.Excel;
using System.Web;
using System.IO;
using QzgfFrame.Utility.Common;
using QzgfFrame.Cop.TerminalEnrolManager.Domain;
using QzgfFrame.Cop.TerminalEnrolManager.Models;
using Aspose.Cells;
using AsposeCells = Aspose.Cells;
using SystemWeb = System.Web;
using SystemText = System.Text;
using QzgfFrame.Cop.TerminalPlanManager.Models;
using QzgfFrame.Cop.TerminalPlanManager.Domain;
using QzgfFrame.Resources.SelfHelpEquipManger.Models;
using QzgfFrame.Resources.SelfHelpEquipManger.Domain;
using QzgfFrame.Cop.TerminalTimeManager.Models;
using QzgfFrame.Archives.OutletsTypeManger.Models;
using QzgfFrame.Archives.OutletsTypeManger.Domain;
using QzgfFrame.Cop.TerminalTimeManager.Domain;

using MicrosoftWord = Microsoft.Office.Interop.Word;
using Microsoft.Office.Interop.Word;
using System.Reflection;

namespace QzgfFrame.Controllers.Cop.TerminalEnrolManager
{
    public class TerminalEnrolFacadeExImpl : TerminalEnrolFacadeEx
    {
        #region

        /// <summary>
        /// 自助终端巡检登记
        /// </summary>
        private TerminalEnrolFacade terminalEnrolFacade { set; get; }
        /// <summary>
        /// 自助终端巡检计划
        /// </summary>
        private TerminalPlanFacade terminalPlanFacade { set; get; }
        /// <summary>
        /// 自助终端
        /// </summary>
        private SelfHelpEquipFacade selfHelpEquipFacade { set; get; }
        /// <summary>
        /// 网点类型
        /// </summary>
        private OutletsTypeFacade outletsTypeFacade { set; get; }
        /// <summary>
        /// 自助终端巡检周期
        /// </summary>
        private TerminalTimeFacade terminalTimeFacade { set; get; }
        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;

        #endregion

        #region excel导入

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

            //判断类型是否存在（如，客户等级，集团名称等）
            CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();
            string str = "";
            object value = null;
            string sql = "";
            //计划id
            string terminalPlanId = "";

            //验证"终端ID"是否为空
            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "")
            {
                sql = " TermiId = '" + value + "'";
                IList<ResourceSelfHelpEquip> resourceSelfHelpEquips = new List<ResourceSelfHelpEquip>();
                resourceSelfHelpEquips = selfHelpEquipFacade.LoadAll("Id", sql);
                if (resourceSelfHelpEquips.Count() == 0)
                {
                    procInfo += "该'终端ID'不存在!!";
                    isAllValid = false;
                }
                else
                {
                    sql = " SelfHelpEquipId = '" + resourceSelfHelpEquips[0].Id + "'";
                    IList<CopTerminalPlan> copTerminalPlans = new List<CopTerminalPlan>();
                    copTerminalPlans = terminalPlanFacade.LoadAll("Id", sql);
                    if (copTerminalPlans.Count() == 0)
                    {
                        procInfo += "该自助终端还没有制定巡检计划!!";
                        isAllValid = false;
                    }
                    else
                    {
                        terminalPlanId = copTerminalPlans[0].Id;
                    }
                }
            }
            else
            {
                procInfo += "'终端ID'为空!!";
                isAllValid = false;
            }

            //验证非空数据项是否为空
            for (int i = 4; i <= aryTilte.Length - 6; i++)
            {
                //因为前三列是非导入项,所以必须从第四项开始
                value = myArray.GetValue(1, i);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "'" + aryTilte[i - 1] + "'为空!!";
                    isAllValid = false;
                }
            }

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

            //判断类型是否存在（如，客户等级，集团名称等）
            CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();
            string str = "";
            object value = null;
            string sql = "";
            //计划id
            string terminalPlanId = "";

            //验证"终端ID"是否为空
            value = myArray.GetValue(1, 4);
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "")
            {
                sql = " TermiId = '" + value + "'";
                IList<ResourceSelfHelpEquip> resourceSelfHelpEquips = new List<ResourceSelfHelpEquip>();
                resourceSelfHelpEquips = selfHelpEquipFacade.LoadAll("Id", sql);
                if (resourceSelfHelpEquips.Count() == 0)
                {
                    procInfo += "该'终端ID'不存在!!";
                    isAllValid = false;
                }
                else
                {
                    sql = " SelfHelpEquipId = '" + resourceSelfHelpEquips[0].Id + "'";
                    IList<CopTerminalPlan> copTerminalPlans = new List<CopTerminalPlan>();
                    copTerminalPlans = terminalPlanFacade.LoadAll("Id", sql);
                    if (copTerminalPlans.Count() == 0)
                    {
                        procInfo += "该自助终端还没有制定巡检计划!!";
                        isAllValid = false;
                    }
                    else
                    {
                        terminalPlanId = copTerminalPlans[0].Id;
                    }
                }
            }
            else
            {
                procInfo += "'终端ID'为空!!";
                isAllValid = false;
            }

            //验证非空数据项是否为空
            for (int i = 4; i <= aryTilte.Length - 6; i++)
            {
                //因为前三列是非导入项,所以必须从第四项开始
                value = myArray.GetValue(1, i);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "'" + aryTilte[i - 1] + "'为空!!";
                    isAllValid = false;
                }
            }

            if (isAllValid)
            {
                copTerminalEnrol.TerminalPlanId = terminalPlanId;//自助终端巡检计划id
                copTerminalEnrol.Personnel = ExtensionMethods.ToStr(myArray.GetValue(1, 5));//维护人员
                copTerminalEnrol.MaintenanceTime = ExtensionMethods.ToDateOANull(myArray.GetValue(1, 6));//完成保养时间
                copTerminalEnrol.Unit = ExtensionMethods.ToStr(myArray.GetValue(1, 7));//使用单位
                copTerminalEnrol.Contact = ExtensionMethods.ToStr(myArray.GetValue(1, 8));//联系人
                copTerminalEnrol.Phone = ExtensionMethods.ToStr(myArray.GetValue(1, 9));//联系电话
                copTerminalEnrol.OperatingStatus = ExtensionMethods.ToStr(myArray.GetValue(1, 10));//运行状态调校
                copTerminalEnrol.HandlingClean = ExtensionMethods.ToStr(myArray.GetValue(1, 11));//处理清洁
                copTerminalEnrol.PaperWarehouseClean = ExtensionMethods.ToStr(myArray.GetValue(1, 12));//纸仓内清洁
                copTerminalEnrol.ThermalHeadFilmClean = ExtensionMethods.ToStr(myArray.GetValue(1, 13));//热敏头片清洁
                copTerminalEnrol.TrainingAndExchange = ExtensionMethods.ToStr(myArray.GetValue(1, 14));//营业员培训与交流
                copTerminalEnrol.ServiceSatisfaction = ExtensionMethods.ToStr(myArray.GetValue(1, 15));//服务满意度

                copTerminalEnrol.CreateUserId = userid;
                copTerminalEnrol.CreationTime = dateNow;
                isAllValid = terminalEnrolFacade.Save(copTerminalEnrol);
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
            string beginLetter, string endLetter, string checkMsgPath)
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
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
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
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "总验证" + (sheetRow - 1) + "条，成功" + m + "条,未成功" + (sheetRow - 1 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath(checkMsgPath);
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
            string beginLetter, string endLetter,string saveMsgPath, string userid)
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
            int sheetColumn = aryTilte.Count();
            int m = 0;
            for (int i = 1; i <= sheetRow; i++)
            {
                Excel.Range range = workSheet.get_Range(beginLetter + i.ToString(), endLetter + i.ToString());
                Array myArray = (Array)range.Cells.Value2;
                if (i == 1)
                {
                    isAllValid = CheckHeadData(myArray, out procInfo, aryTilte);
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Font.Color = 0;
                    rng.Font.Size = 12;
                    rng.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThick,
                        Excel.XlColorIndex.xlColorIndexAutomatic, 1);
                    rng.Value2 = procInfo;

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
                    rng = (Excel.Range)workSheet.Cells[i, sheetColumn + 2];
                    rng.Font.Color = 255;
                    rng.Font.Bold = true;    //设置字体粗体。
                    rng.Value2 = myText;
                }
            }
            procInfo = "数据库总导入" + (sheetRow - 1) + "条，成功" + m + "条,未成功" + (sheetRow - 1 - m) + "条。";
            string strsavePath = HttpContext.Current.Request.MapPath(saveMsgPath);
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
            //GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }

        #endregion excel导入

        #region 导出到excel

        /// <summary>
        /// 导出到excel
        /// </summary>
        /// <param name="strid"></param>
        /// <param name="aryTilte"></param>
        /// <returns></returns>
        public bool ExcelOut(string[] ary, string[] aryTilte)
        {
            try
            {
                Workbook workbook = new Workbook();
                workbook.Worksheets.Clear();
                //设置sheet名称
                workbook.Worksheets.Add("导出所有自助终端登记信息");
                Worksheet ws = workbook.Worksheets[0];

                AsposeCells.Cells cs = ws.Cells;
                int cCol = 0;
                int cRow = 0;

                //生成导出文件的标题
                for (int i = 0; i < aryTilte.Count(); i++)
                {
                    ws.Cells[cCol, cRow].PutValue(aryTilte[i]);//标题名称
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;//字体大小
                    ws.Cells[cCol, cRow].Style.Font.IsBold = true;//设置为粗体
                    cRow++;
                }

                IList<CopTerminalPlan> copTerminalPlans = new List<CopTerminalPlan>();
                copTerminalPlans = terminalPlanFacade.LoadAll();
                foreach (CopTerminalPlan item in copTerminalPlans)
                {
                    TerminalPlan terminalPlan = new TerminalPlan();

                    ResourceSelfHelpEquip resourceSelfHelpEquip = new ResourceSelfHelpEquip();
                    resourceSelfHelpEquip = selfHelpEquipFacade.Get(item.SelfHelpEquipId);

                    if (resourceSelfHelpEquip != null)
                    {
                        terminalPlan.SelfHelpEquip = resourceSelfHelpEquip.UseNetName;
                        terminalPlan.TermiIdSelfHelpEquip = resourceSelfHelpEquip.TermiId;

                        //网点类型
                        ArchiveOutletsType archiveOutletsType = new ArchiveOutletsType();
                        archiveOutletsType = outletsTypeFacade.Get(resourceSelfHelpEquip.NetType);
                        terminalPlan.OutletsType = archiveOutletsType != null ? archiveOutletsType.OutletsTypeName : "";

                        //巡检周期
                        CopTerminalTime copTerminalTime = new CopTerminalTime();
                        copTerminalTime = terminalTimeFacade.Get(item.TerminalTimeId);
                        terminalPlan.TerminalTime = copTerminalTime != null ? copTerminalTime.TerminalTime : "";

                    }

                    cRow = 0;
                    cCol++;
                    //自助终端
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(terminalPlan.SelfHelpEquip));
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    //网点类型
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(terminalPlan.OutletsType));
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    //巡检周期
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(terminalPlan.TerminalTime));
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    //终端ID
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(terminalPlan.TermiIdSelfHelpEquip));
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                }
                MemoryStream ms = new MemoryStream();
                HttpContext.Current.Response.Charset = "utf-8";
                HttpContext.Current.Response.ContentEncoding = SystemText.Encoding.GetEncoding("utf-8");
                string excelName = SystemWeb.HttpUtility.UrlEncode("导出自助终端巡检登记信息") + ".xls";
                HttpContext.Current.Response.ContentType = "application/ms-excel";
                workbook.Save(excelName, SaveType.OpenInExcel, FileFormatType.Default, HttpContext.Current.Response, SystemText.Encoding.UTF8);
                return true;
            }
            catch
            {
                return false;
            }
        }


        #endregion 导出到excel

        #region 导出word

        /// <summary>
        /// 导出word
        /// </summary>
        /// <param name="savePath">文件保存路径</param>
        /// <param name="id">巡检登记id</param>
        /// <returns></returns>
        public bool WordOut(string savePath, string id, out string message)
        {
            try
            {
                Object Nothing = Missing.Value;
                //创建Word文档
                Application WordApp = new Application();
                Document WordDoc = WordApp.Documents.Add(ref Nothing, ref Nothing, ref Nothing, ref Nothing);

                string name = DateTime.Now.ToString("yyyyMMddHHmmss") + "巡检确认书.doc"; //文件名称

                string strsavePath = savePath;
                //string strsavePath = SystemWeb.HttpContext.Current.Request.MapPath(savePath);

                if (!Directory.Exists(strsavePath))
                {
                    Directory.CreateDirectory(strsavePath);
                }
                object filename = strsavePath + name;   //文件保存路径

                //添加页眉
                WordApp.ActiveWindow.View.Type = WdViewType.wdOutlineView;
                WordApp.ActiveWindow.View.SeekView = WdSeekView.wdSeekPrimaryHeader;
                WordApp.ActiveWindow.ActivePane.Selection.InsertAfter("[页眉内容]");
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphRight;//设置右对齐
                WordApp.ActiveWindow.View.SeekView = WdSeekView.wdSeekMainDocument;//跳出页眉设置

                WordApp.Selection.ParagraphFormat.LineSpacing = 15f;//设置文档的行间距

                //设置标题
                WordApp.Application.Selection.ParagraphFormat.Alignment = Microsoft.Office.Interop.Word.WdParagraphAlignment.wdAlignParagraphCenter;
                WordApp.Application.Selection.TypeText("定期巡检情况记录表");
                WordApp.Application.Selection.TypeParagraph();

                //移动焦点并换行
                object count = 14;
                object WdLine = MicrosoftWord.WdUnits.wdLine;//换一行;
                WordApp.Selection.MoveDown(ref WdLine, ref count, ref Nothing);//移动焦点
                WordApp.Selection.TypeParagraph();//插入段落

                //设置内容
                string fillInDate = dateNow.ToLongDateString().ToString();
                WordApp.Application.Selection.ParagraphFormat.Alignment = Microsoft.Office.Interop.Word.WdParagraphAlignment.wdAlignParagraphRight;
                WordApp.Application.Selection.TypeText("填表时间：" + fillInDate);


                #region 表格

                //获取巡检登记信息
                CopTerminalEnrol copTerminalEnrol = new CopTerminalEnrol();
                copTerminalEnrol = terminalEnrolFacade.Get(id);

                //联系人
                string linkMan = "";
                //使用单位
                string useUnit = "";
                //终端ID
                string termiId = "";
                //联系电话
                string phone = "";

                if (copTerminalEnrol != null)
                {
                    linkMan = copTerminalEnrol.Contact;
                    useUnit = copTerminalEnrol.Unit;
                    phone = copTerminalEnrol.Phone;

                    CopTerminalPlan copTerminalPlan = new CopTerminalPlan();
                    copTerminalPlan = terminalPlanFacade.Get(copTerminalEnrol.TerminalPlanId);
                    if (copTerminalPlan != null)
                    {
                        ResourceSelfHelpEquip resourceSelfHelpEquip = new ResourceSelfHelpEquip();
                        resourceSelfHelpEquip = selfHelpEquipFacade.Get(copTerminalPlan.SelfHelpEquipId);

                        termiId = resourceSelfHelpEquip != null ? resourceSelfHelpEquip.TermiId : "";
                    }
                    
                }

                //文档中创建表格
                //设置行数,列数
                MicrosoftWord.Table newTable = WordDoc.Tables.Add(WordApp.Selection.Range, 10, 7, ref Nothing, ref Nothing);
                //设置表格样式
                newTable.Borders.OutsideLineStyle = MicrosoftWord.WdLineStyle.wdLineStyleSingle;//设置外部边框
                newTable.Borders.InsideLineStyle = MicrosoftWord.WdLineStyle.wdLineStyleSingle;//设置内部边框
                newTable.Columns[1].Width = 20f;//设置第一列宽度
                newTable.Columns[2].Width = 80f;//设置第二列宽度
                newTable.Columns[3].Width = 50f;//设置第三列宽度
                newTable.Columns[4].Width = 20f;//设置第四列宽度
                newTable.Columns[5].Width = 80f;//设置第五列宽度
                newTable.Columns[6].Width = 80f;//设置第六列宽度
                newTable.Columns[7].Width = 100f;//设置第七列宽度

                newTable.Rows[5].Height = 80f;//第五行高度
                newTable.Rows[6].Height = 80f;//第六行高度
                newTable.Rows[7].Height = 80f;//第七行高度
                newTable.Rows[8].Height = 80f;//第八行高度

                //第一行

                //填充表格内容
                newTable.Cell(1, 1).Range.Text = "使用单位";
                //横向合并单元格
                newTable.Cell(1, 1).Merge(newTable.Cell(1, 2));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphRight;//水平居左

                //填充表格内容
                newTable.Cell(1, 2).Range.Text = useUnit;
                //横向合并单元格
                newTable.Cell(1, 2).Merge(newTable.Cell(1, 4));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左

                //填充表格内容
                newTable.Cell(1, 3).Range.Text = "联系人";
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphRight;//水平居左

                //填充表格内容
                newTable.Cell(1, 4).Range.Text = linkMan;


                //第二行

                //填充表格内容
                newTable.Cell(2, 1).Range.Text = "终端ID";
                //横向合并单元格
                newTable.Cell(2, 1).Merge(newTable.Cell(2, 2));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphRight;//水平居左

                //填充表格内容
                newTable.Cell(2, 2).Range.Text = termiId;
                //横向合并单元格
                newTable.Cell(2, 2).Merge(newTable.Cell(2, 4));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左


                //填充表格内容
                newTable.Cell(2, 3).Range.Text = "联系电话";
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphRight;//水平居左

                //填充表格内容
                newTable.Cell(2, 4).Range.Text = phone;


                //第三行

                //填充表格内容
                newTable.Cell(3, 1).Range.Text = "现场保养情况记录";
                //横向合并单元格
                newTable.Cell(3, 1).Merge(newTable.Cell(3, 7));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphCenter;//水平居中


                //第四行

                //填充表格内容
                newTable.Cell(4, 1).Range.Text = "维护项目选择";
                //横向合并单元格
                newTable.Cell(4, 1).Merge(newTable.Cell(4, 2));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左

                //填充表格内容
                newTable.Cell(4, 3).Range.Text = "维护项目选择";
                //横向合并单元格
                newTable.Cell(4, 3).Merge(newTable.Cell(4, 4));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphCenter;//水平居中

                //填充表格内容
                newTable.Cell(4, 4).Range.Text = "设备状态";
                //横向合并单元格
                newTable.Cell(4, 4).Merge(newTable.Cell(4, 5));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphCenter;//水平居中


                //第五行

                //填充表格内容
                newTable.Cell(5, 1).Range.Text = "□";

                //填充表格内容
                newTable.Cell(5, 2).Range.Text = "运行状态调校";

                //填充表格内容
                newTable.Cell(5, 4).Range.Text = "□";
                //纵向合并单元格
                newTable.Cell(5, 4).Select();//选中一行
                object moveUnit54 = MicrosoftWord.WdUnits.wdLine;
                object moveCount54 = 1;
                object moveExtend54 = MicrosoftWord.WdMovementType.wdExtend;
                WordApp.Selection.MoveDown(ref moveUnit54, ref moveCount54, ref moveExtend54);
                WordApp.Selection.Cells.Merge();

                //填充表格内容
                newTable.Cell(5, 5).Range.Text = "易损零件检测与更换";
                //纵向合并单元格
                newTable.Cell(5, 5).Select();//选中一行
                object moveUnit55 = MicrosoftWord.WdUnits.wdLine;
                object moveCount55 = 1;
                object moveExtend55 = MicrosoftWord.WdMovementType.wdExtend;
                WordApp.Selection.MoveDown(ref moveUnit55, ref moveCount55, ref moveExtend55);
                WordApp.Selection.Cells.Merge();

                //填充表格内容
                string str = "";
                str += "1.清单打印机  ____________      ";
                str += "2.发票打印机  ____________      ";
                str += "3.识币器  ____________          ";
                str += "4.UPS电源  ____________         ";
                str += "5.金属小键盘  ____________      ";
                newTable.Cell(5, 6).Range.Text = str;

                //横向合并单元格
                newTable.Cell(5, 6).Merge(newTable.Cell(5, 7));
                newTable.Cell(6, 6).Merge(newTable.Cell(6, 7));

                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphCenter;//水平居中
                //纵向合并单元格
                newTable.Cell(5, 6).Select();//选中一行
                object moveUnit56 = MicrosoftWord.WdUnits.wdLine;
                object moveCount56 = 1;
                object moveExtend56 = MicrosoftWord.WdMovementType.wdExtend;
                WordApp.Selection.MoveDown(ref moveUnit56, ref moveCount56, ref moveExtend56);
                WordApp.Selection.Cells.Merge();


                //第六行

                //填充表格内容
                newTable.Cell(6, 1).Range.Text = "□";

                //填充表格内容
                newTable.Cell(6, 2).Range.Text = "处理清洁";


                //第七行

                //填充表格内容
                newTable.Cell(7, 1).Range.Text = "□";

                //填充表格内容
                newTable.Cell(7, 2).Range.Text = "纸仓内清洁";

                //填充表格内容
                newTable.Cell(7, 4).Range.Text = "营业员培训与交流";
                //横向合并单元格
                newTable.Cell(7, 4).Merge(newTable.Cell(7, 7));
                newTable.Cell(8, 4).Merge(newTable.Cell(8, 7));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalTop;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居中
                //纵向合并单元格
                newTable.Cell(7, 4).Select();//选中一行
                object moveUnit74 = MicrosoftWord.WdUnits.wdLine;
                object moveCount74 = 1;
                object moveExtend74 = MicrosoftWord.WdMovementType.wdExtend;
                WordApp.Selection.MoveDown(ref moveUnit74, ref moveCount74, ref moveExtend74);
                WordApp.Selection.Cells.Merge();


                //第八行

                //填充表格内容
                newTable.Cell(8, 1).Range.Text = "□";

                //填充表格内容
                newTable.Cell(8, 2).Range.Text = "热敏头片清洁";


                //第九行

                //填充表格内容
                newTable.Cell(9, 1).Range.Text = "执行工程师";
                //横向合并单元格
                newTable.Cell(9, 1).Merge(newTable.Cell(9, 2));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左

                //填充表格内容
                newTable.Cell(9, 2).Range.Text = "";
                //横向合并单元格
                newTable.Cell(9, 2).Merge(newTable.Cell(9, 3));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左

                //填充表格内容
                newTable.Cell(9, 3).Range.Text = "完成保养时间";
                //横向合并单元格
                newTable.Cell(9, 3).Merge(newTable.Cell(9, 4));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左


                //第十行

                //填充表格内容
                newTable.Cell(10, 1).Range.Text = "服务满意度";
                //横向合并单元格
                newTable.Cell(10, 1).Merge(newTable.Cell(10, 2));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左

                //填充表格内容
                newTable.Cell(10, 2).Range.Text = "非常满意□  满意□  比较满意□  服务一般□  服务较差□";
                //横向合并单元格
                newTable.Cell(10, 2).Merge(newTable.Cell(10, 6));
                WordApp.Selection.Cells.VerticalAlignment = MicrosoftWord.WdCellVerticalAlignment.wdCellAlignVerticalCenter;//垂直居中
                WordApp.Selection.ParagraphFormat.Alignment = MicrosoftWord.WdParagraphAlignment.wdAlignParagraphLeft;//水平居左

                #endregion  表格


                //文件保存
                WordDoc.SaveAs(ref filename, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing,
                    ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing, ref Nothing,
                    ref Nothing);

                //文档退出
                WordDoc.Close(ref Nothing, ref Nothing, ref Nothing);
                //程序退出
                WordApp.Quit(ref Nothing, ref Nothing, ref Nothing);
                //message = name + "文档生成成功，以保存到" + strsavePath + "下";
                message = "文档生成成功,保存到" + strsavePath + "下";

                return true;
            }
            catch
            {
                message = "文件导出异常！";
                return false;
            }
        }

        #endregion 导出word

    }
}
