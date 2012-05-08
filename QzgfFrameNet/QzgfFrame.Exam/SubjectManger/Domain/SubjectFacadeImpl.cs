/*
 * 文件名.........: SubjectFacadeImpl.cs
 * 作者...........:  
 * 说明...........: 试题内容业务逻辑类 
 * 注意...........: 
 * 修改记录.......:   时间       人员    备注
 *                    2011-01-15 XXXX 

*/

using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using QzgfFrame.Exam.SubjectManger.Models;
using QzgfFrame.Exam.SubjectManger.Domain;
using QzgfFrame.Exam.SubjectTypeManger.Models;
using QzgfFrame.Exam.SubjectTypeManger.Domain;
using QzgfFrame.Exam.CourseManger.Models;
using QzgfFrame.Exam.CourseManger.Domain;
using QzgfFrame.Exam.ScaleGradeManger.Models;
using QzgfFrame.Exam.ScaleGradeManger.Domain;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Domain;

using QzgfFrame.Utility.Core.Repository;

namespace QzgfFrame.Exam.SubjectManger.Domain
{
    public class SubjectFacadeImpl : SubjectFacade
    {
        private IRepository<ExamSubjectContent> subjectRepository { set; get; }
        private TestSubjectFacade testsubjectFacade { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private ScaleGradeFacade scalegradeFacade { set; get; }
        

        public ExamSubjectContent Get(object id)
        {
            return subjectRepository.Get(id.ToString());
        }
        public SubjectContent GetContent(object id)
        {
            SubjectContent SubjectContent = new SubjectContent();
            SubjectContent.ExamSubContent = subjectRepository.Get(id.ToString());
            return SubjectContent;
        }
        /// <summary>
        /// 删除多行记录
        /// </summary>
        /// <param name="id">通过,号分隔数据</param>
        /// <returns></returns>
        public bool Delete(string id)
        {
            string[] idarr = id.Split(',');
            bool result = false;
            foreach (var s in idarr)
            {
                result = subjectRepository.Delete(s);
            }
            return result;
        }

        public bool Save(ExamSubjectContent entity)
        {
            entity.Id = subjectRepository.NewSequence("SYSTEM_MENU");
            return subjectRepository.Save(entity);
        }

        public bool Update(ExamSubjectContent entity)
        {
            return subjectRepository.Update(entity);
        }

        public IList<ExamSubjectContent> LoadAll()
        {
            return subjectRepository.LoadAll();
        }
        public IList<ExamSubjectContent> LoadAll(string order, string where)
        {
            return subjectRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize,string TypeID, string CourseTypeID)
        {
            string hql = "";
            if (TypeID == "" || CourseTypeID == "")
                hql = "from ExamSubjectContent";
            else hql = "from ExamSubjectContent where TypeID='" + TypeID + "' and CourseTypeID='" + CourseTypeID + "'";
            IList<ExamSubjectContent> esc = subjectRepository.FindByPage(pageNo, pageSize, hql);


            IList<SubjectContent> sc = new List<SubjectContent>();
            for (int i = 0; i < esc.Count; i++)
            {
                ExamSubjectType SubjectType = subjecttypeFacade.Get(esc[i].TypeId);
                ExamCourseType CourseType = courseFacade.Get(esc[i].CourseTypeId);
                ExamScaleGrade ScaleGrade = scalegradeFacade.Get(esc[i].ScaleGradeId);

                SubjectContent SubContent = new SubjectContent();

                //查询试卷试题表中符合试题ID的数据
                IList<ExamTestSubject> etss = testsubjectFacade.LoadAll("ID", "SubjectID='" + esc[i].Id + "'");
                //如果试卷试题表中存在试题ID，那么该试题不能删除，否则可以删除
                if (etss.Count > 0) SubContent.State = "已使用"; else SubContent.State = "未使用";             
                SubContent.ID = esc[i].Id;
                SubContent.Content = esc[i].Content;
                SubContent.CreateTime = esc[i].CreateTime;
                SubContent.SubjectType = SubjectType.Type;
                SubContent.CourseName = CourseType.Name;
                SubContent.ScaleGradeType = ScaleGrade.Type;
                SubContent.ExamSubContent = esc[i];
                sc.Add(SubContent);
            }

            string rowsjson = JsonConvert.SerializeObject(sc);
            int recordCount = subjectRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

      
    }
}
