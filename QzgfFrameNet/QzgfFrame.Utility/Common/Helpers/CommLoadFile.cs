using System;
using System.IO;
using System.Web;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using System.Data.OleDb;

namespace QzgfFrame.Utility.Common.Helpers
{
    public class CommLoadFile
    {
        /// <summary>
        /// 上传文件
        /// </summary>
        /// <param name="newfilename">返回新文件名</param>
        /// <param name="file">传递HtmlInputFile对象</param>
        /// <param name="savePath">保存的文件目录</param>
        /// <returns></returns>
        public static int UploadXlsFile(out string newfilename, HttpPostedFileBase file, string savePath)
        {
            string strFileName = Path.GetFileName(file.FileName);
            string strsavePath = HttpContext.Current.Request.MapPath(savePath);
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            string filename = DateTime.Now.ToString("yyyyMMddHHmmss") + "导入" + strFileName;
            string filepath = strsavePath + filename;
            try
            {
                file.SaveAs(filepath);
            }
            catch (Exception ex)
            {
                //上传时出错
                newfilename = ex.Message;
                return -1;
            }
            newfilename = filepath;
            //newfilename = savePath + filename;
            return 0;
        }

        /// <summary>
        /// 上传文件,返回bool类型,判断上传是否成功
        /// </summary>
        /// <param name="newfilename">上传到服务器后的新名称</param>
        /// <param name="file">传递HtmlInputFile对象</param>
        /// <returns></returns>
        public static bool UploadXlsFiles(string newfilename, HttpPostedFileBase file, string savePath)
        {
            bool bools = false;

            //判断该文件夹是否存在，没有的话就创建
            string strsavePath = "";
            strsavePath = HttpContext.Current.Request.MapPath(savePath);
            if (!Directory.Exists(strsavePath))
            {
                Directory.CreateDirectory(strsavePath);
            }
            string filepath = strsavePath + newfilename;
            try
            {
                file.SaveAs(filepath);
                bools = true;
            }
            catch 
            {
                return bools;
            }
            return bools;
        }

        /// <summary>
        /// 读EXCEL文件到DataSet
        /// </summary>
        /// <param name="strXlsFilePath"></param>
        /// <param name="strSheetName"></param>
        /// <returns></returns>
        public static DataSet CreateDataSource(string strXlsFilePath, string strSheetName)
        {
            string strConn;
            OleDbConnection conn = null;
            OleDbDataAdapter myCommand = null;
            strConn = "Provider=Microsoft.Jet.OLEDB.4.0;Data Source=" + HttpContext.Current.Server.MapPath(strXlsFilePath) + ";Extended Properties='Excel 8.0;HDR=Yes;IMEX=1';";
            try
            {
                conn = new OleDbConnection(strConn);
                myCommand = new OleDbDataAdapter("SELECT * FROM [" + strSheetName + "$]", strConn);
            }
            catch (OleDbException e)
            {
                throw new Exception(e.Message);
            }
            DataSet myDataSet = new DataSet();
            try
            {
                myCommand.Fill(myDataSet);
            }
            catch (Exception e)
            {
                throw new Exception(e.Message);
            }
            conn.Close();
            conn.Dispose();
            return myDataSet;
        }
    }
}
