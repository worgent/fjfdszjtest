/*
 * 文件名.........: SubjectFacadeExImpl.cs
 * 作者...........:  
 * 说明...........: 试题业务处理多模型关联类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.IO;
using System.Web;
using System.Data;
using System.Collections;
using System.Collections.Generic;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Domain;
using QzgfFrame.Exam.FillBlanksSubjectManger.Models;
using QzgfFrame.Exam.FillBlanksSubjectManger.Domain;
using QzgfFrame.Exam.MultipleSubjectManger.Models;
using QzgfFrame.Exam.MultipleSubjectManger.Domain;
using QzgfFrame.Exam.JudgeSubjectManger.Models;
using QzgfFrame.Exam.JudgeSubjectManger.Domain;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;
namespace QzgfFrame.Controllers.Exam.ExaminerManger 
{
    public class SubjectFacadeExImpl: SubjectFacadeEx
    {
        private SubjectFacade subjectFacade { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private ScaleGradeFacade scalegradeFacade { set; get; }
        private FillBlanksSubjectFacade fillblankssubjectFacade { set; get; }
        private MultipleSubjectFacade multiplesubjectFacade { set; get; }
        private JudgeSubjectFacade judgesubjectFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(SubjectContent entity)
        {
            bool result = false;
            result = this.subjectFacade.Save(entity.ExamSubContent);
            int j = 0;
            if (entity.multiples != null)
            {
                ExamMultipleSubject[] MultipleSubjects = (ExamMultipleSubject[])JavaScriptConvert.DeserializeObject(entity.multiples, typeof(ExamMultipleSubject[]));
                j = 0;
                foreach (ExamMultipleSubject em in MultipleSubjects)
                {
                    em.SubjectID = entity.ExamSubContent.Id;
                    result = multiplesubjectFacade.Save(em);
                    j++;
                }
            }
            if (entity.fillblanks != null)
            {
                ExamFillBlanksSubject[] FillBalnksSubject = (ExamFillBlanksSubject[])JavaScriptConvert.DeserializeObject(entity.fillblanks, typeof(ExamFillBlanksSubject[]));
                j = 0;
                foreach (ExamFillBlanksSubject ef in FillBalnksSubject)
                {
                    ef.SubjectID = entity.ExamSubContent.Id;
                    result = fillblankssubjectFacade.Save(ef);
                    j++;
                }
            }
            if (entity.judges != null)
            {
                ExamJudgeSubject[] FillBalnksSubject = (ExamJudgeSubject[])JavaScriptConvert.DeserializeObject(entity.judges, typeof(ExamJudgeSubject[]));
                j = 0;
                foreach (ExamJudgeSubject ef in FillBalnksSubject)
                {
                    ef.SubjectID = entity.ExamSubContent.Id;
                    result = judgesubjectFacade.Save(ef);
                    j++;
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(SubjectContent entity)
        {
            bool result = false;
            result = subjectFacade.Update(entity.ExamSubContent);
            int j = 0;
            if (entity.multiples != null)
            {
                result = multiplesubjectFacade.Delete(entity.ExamSubContent.Id.ToString());
                ExamMultipleSubject[] MultipleSubjects = (ExamMultipleSubject[])JavaScriptConvert.DeserializeObject(entity.multiples, typeof(ExamMultipleSubject[]));
                j = 0;
                foreach (ExamMultipleSubject em in MultipleSubjects)
                {
                    em.SubjectID = entity.ExamSubContent.Id;
                    result = multiplesubjectFacade.Save(em);
                    j++;
                }
            }
            if (entity.fillblanks!= null)
            {
                result = fillblankssubjectFacade.Delete(entity.ExamSubContent.Id.ToString());
                ExamFillBlanksSubject[] FillBalnksSubject = (ExamFillBlanksSubject[])JavaScriptConvert.DeserializeObject(entity.fillblanks, typeof(ExamFillBlanksSubject[]));
                j = 0;
                foreach (ExamFillBlanksSubject ef in FillBalnksSubject)
                {
                    ef.SubjectID = entity.ExamSubContent.Id;
                    result = fillblankssubjectFacade.Save(ef);
                    j++;
                }
            }
            if (entity.judges != null)
            {
                result = judgesubjectFacade.Delete(entity.ExamSubContent.Id.ToString());
                ExamJudgeSubject[] FillBalnksSubject = (ExamJudgeSubject[])JavaScriptConvert.DeserializeObject(entity.judges, typeof(ExamJudgeSubject[]));
                j = 0;
                foreach (ExamJudgeSubject ef in FillBalnksSubject)
                {
                    ef.SubjectID = entity.ExamSubContent.Id;
                    result = judgesubjectFacade.Save(ef);
                    j++;
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Delete(string id, string typeid)
        {
           bool result = false;
           result = subjectFacade.Delete(id.ToString());

           string[] idarr = id.Split(',');
           string[] typeidarr = typeid.Split(',');
           for (int i = 0; i < idarr.Length; i++)
           {
               ExamSubjectType SubjectType = subjecttypeFacade.Get(typeidarr[i].ToString());
               string type = SubjectType.Type.ToString();
               switch (type)
               {
                   case "选择题":
                       result = multiplesubjectFacade.Delete(idarr[i].ToString());
                       break;
                   case "填空题":
                       result = fillblankssubjectFacade.Delete(idarr[i].ToString());
                       break;
                   case "判断题":
                       result = judgesubjectFacade.Delete(idarr[i].ToString());
                       break;
               }
           }
         
           if (result == false)
               throw new Exception("操作失败!!");
            return result;
        }


        /// <summary>
        /// 序号转字母
        /// </summary>
        /// <param name="number"></param>
        /// <returns></returns>
        public string numberConvert(string number)
        {
            string No = "";
            switch (number)
            {
                case "1": { No = "A"; break; }
                case "2": { No = "B"; break; }
                case "3": { No = "C"; break; }
                case "4": { No = "D"; break; }
                case "5": { No = "E"; break; }
                case "6": { No = "F"; break; }
                case "7": { No = "G"; break; }
                case "8": { No = "H"; break; }
                case "9": { No = "I"; break; }
                case "10": { No = "J"; break; }
                case "11": { No = "K"; break; }
                case "12": { No = "L"; break; }
                case "13": { No = "M"; break; }
                case "14": { No = "N"; break; }
                case "15": { No = "O"; break; }
                case "16": { No = "P"; break; }
                case "17": { No = "Q"; break; }
                case "18": { No = "R"; break; }
                case "19": { No = "S"; break; }
                case "20": { No = "T"; break; }
                case "21": { No = "U"; break; }
                case "22": { No = "V"; break; }
                case "23": { No = "W"; break; }
                case "24": { No = "X"; break; }
                case "25": { No = "Y"; break; }
                case "26": { No = "Z"; break; }
            }
            return No;
        }
    }
}
