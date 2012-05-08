/*
 * 文件名.........: GetSubjectFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 随机获取试题业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.SubjectManger .Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.GetSubjectManger.Domain
{
    public class GetSubjectFacadeImpl : GetSubjectFacade
    {
        private SubjectFacade subjectFacade { set; get; }

        public IList<ExamSubjectContent> GetTestSubject(string typeid,string coursetypeid,int Number)
        {
            IList<ExamSubjectContent> subjectContents = subjectFacade.LoadAll("ID", "TypeId='" + typeid + "' and CourseTypeId='" + coursetypeid + "'");
            //集合的总数量
            int num = subjectContents.Count;
            //记录取出的下标值
            List<int> List = new List<int>();
            IList<ExamSubjectContent> reSubjectList = new List<ExamSubjectContent>(Number);
            if (num >= Number)
            {
                Random random = new Random();
                while (List.Count < Number)
                {
                    //随机产生一个集合的下标
                    int iter = random.Next(0, num);
                    if (!List.Contains(iter))
                    {
                        List.Add(iter);
                        reSubjectList.Add(subjectContents[iter]);
                    }
                }
            }
            //题目不足，直接返回原集合
            else
            {
                reSubjectList = subjectContents;
            }

            return reSubjectList;
        }
    }
}
