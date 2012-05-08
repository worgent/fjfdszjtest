using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Aspose.Cells;
using System.IO;
using System.Web;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.CommonSupport
{
    public class BaseFun
    {
        /// <summary>
        /// 导出到excel
        /// </summary>
        /// <param name="strid"></param>
        /// <param name="aryTilte"></param>
        /// <returns></returns>
        public static bool ExcelOut(string titlename, string[] aryTilte, IList<object[]> dataList, string[] strTypes,string type)
        {
            try
            {
                Workbook workbook = new Workbook();
                workbook.Worksheets.Clear();
                //设置sheet名称
                workbook.Worksheets.Add(titlename);
                Worksheet ws = workbook.Worksheets[0];

                Cells cs = ws.Cells;
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
                for (int j = 0; j < dataList.Count(); j++)
                {
                    cRow = 0;
                    cCol++;
                    for (int k = 0; k < strTypes.Count(); k++)
                    {
                        if (strTypes[k] == "str")
                            ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToStr(dataList[j][k]));
                        else if (strTypes[k] == "date")
                            ws.Cells[cCol, cRow].PutValue(string.Format("{0:d}", ExtensionMethods.ToDateNull(dataList[j][k])));
                        else if (strTypes[k] == "time")
                            ws.Cells[cCol, cRow].PutValue(string.Format("{0:T}", ExtensionMethods.ToDateNull(dataList[j][k])));
                        else if (strTypes[k] == "datetime")
                            ws.Cells[cCol, cRow].PutValue(string.Format("{0:g}", ExtensionMethods.ToDateNull(dataList[j][k])));
                        else if (strTypes[k] == "int")
                            ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToInt(dataList[j][k]));
                        else if (strTypes[k] == "int")
                            ws.Cells[cCol, cRow].PutValue(ExtensionMethods.ToInt(dataList[j][k]));
                        else if (strTypes[k] == "bool")
                        {
                            string strdata=ExtensionMethods.ToStr(dataList[j][k])=="0"?"否":"是";
                            ws.Cells[cCol, cRow].PutValue(strdata);
                        }
                        else if (strTypes[k] == "position")
                        {
                            string strdata = ExtensionMethods.ToStr(dataList[j][k]) == "0" ? "用户机房" : "局端";
                            ws.Cells[cCol, cRow].PutValue(strdata);
                        }
                        else if (strTypes[k] == "sex")
                        {
                            string strdata = ExtensionMethods.ToStr(dataList[j][k]) == "0" ? "男" : "女";
                            ws.Cells[cCol, cRow].PutValue(strdata);
                        }
                        else if (strTypes[k] == "linetype")
                        {
                            string strdata = ExtensionMethods.ToStr(dataList[j][k]) ;
                            ws.Cells[cCol, cRow].PutValue(ExtensionMethods.GetLineType(strdata));
                        }
                        else if (strTypes[k] == "state")
                        {
                            string strdata ="";
                            short stateshort = ExtensionMethods.ToShort(dataList[j][k]);
                            if (type == "line")
                                strdata = ExtensionMethods.GetLineState(stateshort);
                            else
                                strdata = ExtensionMethods.GetEquipState(stateshort);

                            ws.Cells[cCol, cRow].PutValue(strdata);
                        }
                            
                        ws.Cells[cCol, cRow].Style.Font.Size = 10;
                        ws.Cells[cCol, cRow].Style.VerticalAlignment = TextAlignmentType.Center;
                        cRow++;
                    }
                }
                MemoryStream ms = new MemoryStream();
                HttpContext.Current.Response.Charset = "utf-8";
                HttpContext.Current.Response.ContentEncoding = Encoding.GetEncoding("utf-8");
                string excelName = HttpUtility.UrlEncode("导出" + titlename) + ".xls";
                HttpContext.Current.Response.ContentType = "application/ms-excel";
                workbook.Save(excelName, SaveType.OpenInExcel, FileFormatType.Default, HttpContext.Current.Response, Encoding.UTF8);
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
