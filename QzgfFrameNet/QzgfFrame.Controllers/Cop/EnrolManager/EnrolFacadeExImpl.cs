using System;
using System.Collections.Generic;
using System.Linq;
using QzgfFrame.Utility.Common;
using Excel = Microsoft.Office.Interop.Excel;
using System.IO;
using QzgfFrame.Cop.EnrolManager.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Domain;
using QzgfFrame.Resources.GroupClieManger.Domain;
using QzgfFrame.Archives.BizTypeManger.Domain;
using QzgfFrame.Archives.BizAssuranLeveManger.Models;
using QzgfFrame.Cop.EnrolManager.Models;
using QzgfFrame.Resources.GroupClieManger.Models;
using QzgfFrame.Archives.BizTypeManger.Models;
using Aspose.Cells;
using AsposeCells = Aspose.Cells;
using SystemWeb = System.Web;
using SystemText = System.Text;
using System.Web;
using QzgfFrame.Cop.PlanManager.Models;
using QzgfFrame.Cop.PlanManager.Domain;
using QzgfFrame.Resources.DedicateLineManger.Models;
using QzgfFrame.Resources.DedicateLineManger.Domain;
using QzgfFrame.Cop.CycTimeManager.Models;
using QzgfFrame.Cop.CycTimeManager.Domain;
using System.Web.UI;

using SystemIO = System.IO;
using MicrosoftWord = Microsoft.Office.Interop.Word;
using SystemReflection = System.Reflection;
using Microsoft.Office.Interop.Word;
using System.Reflection;

namespace QzgfFrame.Controllers.Cop.EnrolManager
{
    public class EnrolFacadeExImpl : EnrolFacadeEx
    {
        #region 

        /// <summary>
        /// 专线巡检计划
        /// </summary>
        private PlanFacade planFacade { set; get; }
        /// <summary>
        /// 专线巡检登记
        /// </summary>
        private EnrolFacade enrolFacade { set; get; }
        /// <summary>
        /// 专线
        /// </summary>
        private DedicateLineFacade dedicateLineFacade { set; get; }
        /// <summary>
        /// 业务保障等级（就是客户等级）
        /// </summary>
        private BizAssuranLeveFacade bizAssuranLeveFacade { set; get; }
        /// <summary>
        /// 集团编号
        /// </summary>
        private GroupClieFacade groupClieFacade { set; get; }
        /// <summary>
        /// 业务类型
        /// </summary>
        private BizTypeFacade bizTypeFacade { set; get; }
        /// <summary>
        /// 巡检周期
        /// </summary>
        private CycTimeFacade cycTimeFacade { set; get; }

        /// <summary>
        /// 当前时间
        /// </summary>
        private DateTime dateNow = DateTime.Now;
        

        #endregion

        #region excel批量导入

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
            //判断类型是否存在（如，专线唯一编号）
            CopEnrol copEnrol = new CopEnrol();
            string str = "";
            object value = null;
            string sql = "";
            //计划id
            string planId = "";

            value = myArray.GetValue(1, 5);//专线唯一编号
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "")
            {
                sql = " ProductNo = '" + value + "' ";
                IList<ResourceDedicateLine> resourceDedicateLines = new List<ResourceDedicateLine>();
                resourceDedicateLines = dedicateLineFacade.LoadAll("Id", sql);
                if (resourceDedicateLines.Count() == 0)
                {
                    procInfo += "该'专线唯一编号'不存在!!";
                    isAllValid = false;
                }
                else
                {
                    sql = " DedicateLineId = '" + resourceDedicateLines[0].Id + "' ";
                    IList<CopPlan> copPlans = new List<CopPlan>();
                    copPlans = planFacade.LoadAll("Id", sql);
                    if (copPlans.Count() == 0)
                    {
                        procInfo += "该专线还没有制定巡检计划!!";
                        isAllValid = false;
                    }
                    else
                    {
                        planId = copPlans[0].Id;
                    }
                }
            }
            else
            {
                procInfo += "专线唯一编号为空!!";
                isAllValid = false;
            }

            //验证不能为空的数据是否为空
            for (int i = 5; i <= aryTilte.Length - 30; i++)
            {
                //因为前四列是非导入项,所以必须从第五项开始
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
        /// 验证文件内容数据，并保存数据
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
            //判断类型是否存在（如，专线唯一编号）
            CopEnrol copEnrol = new CopEnrol();
            string str = "";
            object value = null;
            string sql = "";
            //计划id
            string planId = "";

            value = myArray.GetValue(1, 5);//专线唯一编号
            str = ExtensionMethods.ToStr(value);
            if (str.Trim() != "")
            {
                sql = " ProductNo = '" + value + "' ";
                IList<ResourceDedicateLine> resourceDedicateLines = new List<ResourceDedicateLine>();
                resourceDedicateLines = dedicateLineFacade.LoadAll("Id", sql);
                if (resourceDedicateLines.Count() == 0)
                {
                    procInfo += "该'专线唯一编号'不存在!!";
                    isAllValid = false;
                }
                else
                {
                    sql = " DedicateLineId = '" + resourceDedicateLines[0].Id + "' ";
                    IList<CopPlan> copPlans = new List<CopPlan>();
                    copPlans = planFacade.LoadAll("Id",sql);
                    if (copPlans.Count() == 0)
                    {
                        procInfo += "该专线还没有制定巡检计划!!";
                        isAllValid = false;
                    }
                    else
                    {
                        planId = copPlans[0].Id;
                    }
                }
            }
            else
            {
                procInfo += "专线唯一编号为空!!";
                isAllValid = false;
            }

            //验证不能为空的数据是否为空
            for (int i = 5; i <= aryTilte.Length - 30; i++)
            {
                //因为前四列是非导入项,所以必须从第五项开始
                value = myArray.GetValue(1, i);
                str = ExtensionMethods.ToStr(value);
                if (str.Trim() == "")
                {
                    procInfo += "'" + aryTilte[i - 1] + "'为空!!";
                    isAllValid = false;
                }
            }

            //全部验证通过后,才将数据加入类中
            if (isAllValid)
            {
                copEnrol.CopPlanId = planId;//计划id

                copEnrol.Personnel = ExtensionMethods.ToStr(myArray.GetValue(1, 6));//维护人员
                copEnrol.CycEnrolTime = ExtensionMethods.ToDateOANull(myArray.GetValue(1, 7)).ToString();//巡检登记时间
                copEnrol.ConnectStation = ExtensionMethods.ToStr(myArray.GetValue(1, 8));//接入基站
                copEnrol.ConnectPort = ExtensionMethods.ToStr(myArray.GetValue(1, 9));//接入局端
                copEnrol.Linkman = ExtensionMethods.ToStr(myArray.GetValue(1, 10));//客户联系人

                copEnrol.Phone = ExtensionMethods.ToStr(myArray.GetValue(1, 11));//联系电话
                copEnrol.Address = ExtensionMethods.ToStr(myArray.GetValue(1, 12));//详细地址
                copEnrol.ConnectCircs = ExtensionMethods.ToStr(myArray.GetValue(1, 13));//连接情况
                copEnrol.ElectricalLineCheck = ExtensionMethods.ToStr(myArray.GetValue(1, 14));//电源线路检查
                copEnrol.Lineclutter = ExtensionMethods.ToStr(myArray.GetValue(1, 15));//线路是否杂乱

                copEnrol.IsColligationCircsInGear = ExtensionMethods.ToStr(myArray.GetValue(1, 16));//绑扎情况是否正常
                copEnrol.IsHiddenTroubleBe = ExtensionMethods.ToStr(myArray.GetValue(1, 17));//是否有隐患存在
                copEnrol.EquipmentRunStateCheck = ExtensionMethods.ToStr(myArray.GetValue(1, 18));//设备运行状态检查
                copEnrol.IsDustCatcher = ExtensionMethods.ToStr(myArray.GetValue(1, 19));//是否除尘
                copEnrol.LabelCircs = ExtensionMethods.ToStr(myArray.GetValue(1, 20));//标签情况

                copEnrol.PortData = ExtensionMethods.ToStr(myArray.GetValue(1, 21));//端口资料
                copEnrol.IsEnvironmentalHazardsExist = ExtensionMethods.ToStr(myArray.GetValue(1, 22));//是否环境隐患存在
                copEnrol.Entironmentcontent = ExtensionMethods.ToStr(myArray.GetValue(1, 23));//环境隐患内容
                copEnrol.IsEquipmentEarth = ExtensionMethods.ToStr(myArray.GetValue(1, 24));//设备接地是否正常
                copEnrol.EmpennageLabel = ExtensionMethods.ToStr(myArray.GetValue(1, 25));//尾纤标签

                copEnrol.NetworkSpeedTest = ExtensionMethods.ToStr(myArray.GetValue(1, 26));//网速测试
                copEnrol.DownloadTest = ExtensionMethods.ToStr(myArray.GetValue(1, 27));//下载测试
                copEnrol.IsNormalLogin = ExtensionMethods.ToStr(myArray.GetValue(1, 28));//是否正常登入
                copEnrol.TheAverageDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 29));//平均延时
                copEnrol.SubstitutionRate = ExtensionMethods.ToStr(myArray.GetValue(1, 30));//掉包率

                copEnrol.PhoneCallTesting = ExtensionMethods.ToStr(myArray.GetValue(1, 31));//电话拨测
                copEnrol.FaxCallTesting = ExtensionMethods.ToStr(myArray.GetValue(1, 32));//传真拨测
                copEnrol.Problems = ExtensionMethods.ToStr(myArray.GetValue(1, 33));//存在问题
                copEnrol.LastProblemSolvingSituations = ExtensionMethods.ToStr(myArray.GetValue(1, 34));//上次问题解决情况
                copEnrol.GatewayMinimumDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 35));//ping 网关最小延时

                copEnrol.GatewayMaximumDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 36));//ping 网关最大延时
                copEnrol.GatewayAverageDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 37));//ping 网关平均延时
                copEnrol.GatewaySubstitutionRate = ExtensionMethods.ToStr(myArray.GetValue(1, 38));//ping 网关掉包率
                copEnrol.DnsMinimumDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 39));//ping DNS最小延时
                copEnrol.DnsMaximumDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 40));//ping DNS最大延时

                copEnrol.DnsAverageDelay = ExtensionMethods.ToStr(myArray.GetValue(1, 41));//ping DNS平均延时
                copEnrol.DnsSubstitutionRate = ExtensionMethods.ToStr(myArray.GetValue(1, 42));//ping DNS掉包率

                copEnrol.CreateUserId = userid;
                copEnrol.CreationTime = dateNow;
                isAllValid = enrolFacade.Save(copEnrol);
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
        /// <param name="checkMsgPath">验证导入文件的返回信息文件保存路径</param>
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
        /// <param name="saveMsgPath">保存导入文件的返回信息文件保存路径</param>
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

                if (i == 1)//首行是标题
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
                if(i == 2)//第二行是提示信息
                {
                    continue;
                }
                else//从第三行开始读取数据
                {
                    string myText = "";
                    isAllValid = LoadBodyData(myArray, out myText, aryTilte, userid);
                    if (isAllValid)
                    {
                        myText += "导入数据库成功";
                        m++;
                    }
                    else
                    {
                        myText += "导入数据库失败!!";
                    }
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
            GC.Collect();
            File.Delete(strFileName);
            return isAllValid;
        }

        #endregion excel批量导入

        #region 导出到excel

        /// <summary>
        /// 导出到excel
        /// </summary>
        /// <param name="strid"></param>
        /// <param name="aryTilte"></param>
        /// <returns></returns>
        public bool ExcelOut(string[] strid, string[] aryTilte)
        {
            try
            {
                string sql = "";

                Workbook workbook = new Workbook();
                workbook.Worksheets.Clear();
                //设置sheet名称
                workbook.Worksheets.Add("导出所有登记信息");
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

                IList<CopPlan> CopPlans = new List<CopPlan>();
                sql = " IsDelete = 0 ";
                CopPlans = planFacade.LoadAll("Id", sql);
                foreach (CopPlan item in CopPlans)
                {
                    Plan plan = new Plan();
                    ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
                    resourceDedicateLine = item.DedicateLineId != null ? dedicateLineFacade.Get(item.DedicateLineId) : null;
                    if (resourceDedicateLine != null)
                    {
                        //专线类型
                        ArchiveBizType archiveBizType = new ArchiveBizType();
                        archiveBizType = resourceDedicateLine.BizTypeId != null ? bizTypeFacade.Get(resourceDedicateLine.BizTypeId) : null;
                        plan.BizType = archiveBizType != null ? archiveBizType.BizTypeName : "";

                        //集团客户
                        ResourceGroupClie resourceGroupClie = new ResourceGroupClie();
                        resourceGroupClie = resourceDedicateLine.ClieId != null ? groupClieFacade.Get(resourceDedicateLine.ClieId) : null;
                        plan.GroupName = resourceGroupClie != null ? resourceGroupClie.ClieName : "";

                        //业务保障等级
                        ArchiveBizAssuranLeve archiveBizAssuranLeve = new ArchiveBizAssuranLeve();
                        archiveBizAssuranLeve = resourceDedicateLine.BizAssuranLeveId != null ? bizAssuranLeveFacade.Get(resourceDedicateLine.BizAssuranLeveId) : null;
                        plan.BizAssuranLeve = archiveBizAssuranLeve != null ? archiveBizAssuranLeve.AssuranLeveName : "";

                        //巡检周期
                        CopCycTime copCycTime = new CopCycTime();
                        copCycTime = item.CycTimeId != null ? cycTimeFacade.Get(item.CycTimeId) : null;
                        plan.CopCycTime = copCycTime != null ? copCycTime.CycTime : item.CycTime;

                        //专线唯一编号
                        plan.ProductNoLine = resourceDedicateLine.ProductNo;
                    }

                    cRow = 0;
                    cCol++;

                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(plan.BizType));//专线类型
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(plan.GroupName));//集团客户
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(plan.BizAssuranLeve));//业务保障等级
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(plan.CopCycTime));//巡检周期
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                    cRow++;
                    ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(plan.ProductNoLine));//专线唯一编号
                    ws.Cells[cCol, cRow].Style.Font.Size = 10;
                    ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                }
                MemoryStream ms = new MemoryStream();
                HttpContext.Current.Response.Charset = "utf-8";
                HttpContext.Current.Response.ContentEncoding = SystemText.Encoding.GetEncoding("utf-8");
                string excelName = SystemWeb.HttpUtility.UrlEncode("导出专线巡检登记信息") + ".xls";
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
                CopEnrol copEnrol = new CopEnrol();
                copEnrol = enrolFacade.Get(id);

                //联系人
                string linkMan = "";
                //使用单位
                string useUnit = "";
                //终端ID
                string connectPort = "";
                //联系电话
                string phone = "";

                if (copEnrol != null)
                {
                    linkMan = copEnrol.Linkman;
                    connectPort = copEnrol.ConnectPort;
                    phone = copEnrol.Phone;

                    CopPlan copPlan = new CopPlan();
                    copPlan = planFacade.Get(copEnrol.CopPlanId);

                    if (copPlan != null)
                    {
                        ResourceDedicateLine resourceDedicateLine = new ResourceDedicateLine();
                        resourceDedicateLine = dedicateLineFacade.Get(copPlan.DedicateLineId);

                        if (resourceDedicateLine != null)
                        {
                            //集团客户
                            ResourceGroupClie resourceGroupClie = new ResourceGroupClie();
                            resourceGroupClie = groupClieFacade.Get(resourceDedicateLine.ClieId);
                            if (resourceGroupClie != null)
                            {
                                useUnit = resourceGroupClie != null ? resourceGroupClie.ClieName : "";
                            }
                        }
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
                newTable.Cell(2, 2).Range.Text = connectPort;
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

                ////激活文档否则出现
                //WordDoc.Activate();
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
            catch(Exception ex)
            {
                message = "文件导出异常！" + ex.Message;
                return false;
            }
        }

        #endregion 导出word
    }
}
