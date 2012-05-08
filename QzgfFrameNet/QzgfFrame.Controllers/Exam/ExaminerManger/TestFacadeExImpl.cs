/*
 * 文件名.........: TestFacadeExImpl.cs
 * 作者...........:  
 * 说明...........: 试卷业务处理多模型关联类 
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
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.TestExerciseManger.Models;
using QzgfFrame.Exam.TestExerciseManger.Domain;
using QzgfFrame.Exam.TestMockManger.Models;
using QzgfFrame.Exam.TestMockManger.Domain;
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.GetSubjectManger.Models;
using QzgfFrame.Exam.GetSubjectManger.Domain;
using QzgfFrame.Exam.TestSubjectTypeManger.Models;
using QzgfFrame.Exam.TestSubjectTypeManger.Domain;
using QzgfFrame.Exam.TestSubjectManger.Models;
using QzgfFrame.Exam.TestSubjectManger.Domain;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;

namespace QzgfFrame.Controllers.Exam.ExaminerManger 
{
    public class TestFacadeExImpl: TestFacadeEx
    {
        private SubjectFacade subjectFacade { set; get; }
        private SubjectTypeFacade subjecttypeFacade { set; get; }
        private CourseFacade courseFacade { set; get; }
        private TestFacade testFacade { set; get; }
        private TestSetFacade testsetFacade { set; get; }
        private TestExerciseFacade testexerciseFacade { set; get; }
        private TestMockFacade testmockFacade { set; get; }
        private TestSubjectTypeFacade testsubjecttypeFacade { set; get; }
        private TestSubjectFacade testsubjectFacade { set; get; }
        private GetSubjectFacade getsubjectFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");


        /// <summary>
        /// 获得随机试卷内容并保存
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public TestID GetTestContent(GetSubject entity)
        {
            TestID testID = new TestID();
            testID.result = true;
            testID.msg = "操作成功！";
            ExamGetSubject[] ExamGetSubjects = (ExamGetSubject[])JavaScriptConvert.DeserializeObject(entity.getsubjects, typeof(ExamGetSubject[]));

            if (ExamGetSubjects.Length > 20)
            {
                testID.result = false;
                testID.msg = "试卷题型不能大于20个！";
                return testID;          
            }
            foreach (ExamGetSubject egs in ExamGetSubjects)
            {
                string where = " Type='" + egs.SubjectTypeName + "'";
                ExamSubjectType subjectType = subjecttypeFacade.Get("ID", where);
                IList<ExamSubjectContent> subjectContents = subjectFacade.LoadAll("ID", "TypeID='" + subjectType.ID + "' and CourseTypeID='" + entity.CourseTypeID + "'");
                if (subjectContents.Count < egs.SubjectTypeNumber)
                {
                    testID.result = false;
                    testID.msg = egs.SubjectTypeName + "的题目数量不足!";
                    return testID;
                }

                if (egs.SubjectTypeNumber > 200)
                {
                    testID.result = false;
                    testID.msg = "每个题型下的题目不能超过200道！";
                    return testID;
                }
            }

            if (testID.result == false) return testID;

            ExamTestContent ExamTestContent = new ExamTestContent();
            ExamTestContent.Name = "试卷名称";
            ExamTestContent.TypeID = entity.CourseTypeID;
            ExamTestContent.CreatorID = entity.CreatorID;
            ExamTestContent.State = TestState.未组织考试;
            ExamTestContent.IsDelete = true;
            testID.result = this.testFacade.Save(ExamTestContent);
            if (testID.result == false)
            {
                testID.result = false;
                testID.msg = "操作失败！";
                return testID;
            }

            testID.ID = ExamTestContent.ID;
            int Number = 0;                //题型数量
            decimal Score = 0;             //题型小题分数
            int orderNo=0;                 //顺序

            for (int i = 0; i < ExamGetSubjects.Length; i++)
            {
                ExamTestSubjectType ExamTestSubjectType = new ExamTestSubjectType();
              
                Number = ExamGetSubjects[i].SubjectTypeNumber;
                Score = ExamGetSubjects[i].SubjectTypeScore;

                ExamTestSubjectType.TypeName = ExamGetSubjects[i].SubjectTypeName;      //试卷题型名称
                string where = " Type='" + ExamGetSubjects[i].SubjectTypeName + "'";
                ExamSubjectType subjectType = subjecttypeFacade.Get("ID", where);
                ExamTestSubjectType.SubjectTypeID = subjectType.ID;                     //题型ID
                ExamTestSubjectType.Explaination = "";                                  //试卷题型说明
                orderNo = i + 1;
                ExamTestSubjectType.OrderNo = orderNo.ToString();                       //试卷题型顺序

                ExamTestSubjectType.TestID = ExamTestContent.ID;                        //试卷ID
                testID.result = testsubjecttypeFacade.Save(ExamTestSubjectType);
                if (testID.result == false)
                {
                    testID.result = false;
                    testID.msg = "操作失败！";
                    return testID;
                }

                IList<ExamSubjectContent> RandomSubjectList = getsubjectFacade.GetTestSubject(subjectType.ID,entity.CourseTypeID, Number);

                //遍历题目数量，并赋值保存
                for (int j = 0; j < RandomSubjectList.Count; j++)
                {
                    ExamTestSubject ExamTestSubject = new ExamTestSubject();
                    ExamTestSubject.SubjectID = RandomSubjectList[j].Id;  //试卷试题ID
                    ExamTestSubject.Score = Score.ToString();             //试卷试题分值
                    orderNo = j + 1;
                    ExamTestSubject.OrderNo = orderNo.ToString();         //试卷试题顺序

                    ExamTestSubject.TestSubjectTypeID = ExamTestSubjectType.ID;  //试卷题型ID
                    testID.result = testsubjectFacade.Save(ExamTestSubject);
                    if (testID.result == false)
                    {
                        testID.result = false;
                        testID.msg = "操作失败！";
                        return testID;
                    }
                }             
            }

            return testID;
        }

        public bool Save(TestContent entity)
        {;
            bool result = false;
            entity.ExamTestContent.State = TestState.未组织考试;
            entity.ExamTestContent.IsDelete = true;
            result = this.testFacade.Save(entity.ExamTestContent);
            if (result == false) throw new Exception("操作失败!!");             
            int i = 0;
            int j = 0;
            List<ExamTestSubject[]> lst= null;
            if (entity.testsubjects != null)
            {
                lst = (List<ExamTestSubject[]>)JavaScriptConvert.DeserializeObject(entity.testsubjects, typeof(List<ExamTestSubject[]>));
            }
            if (entity.testsubjecttypes != null)
            {
                ExamTestSubjectType[] TestSubjectTypes = (ExamTestSubjectType[])JavaScriptConvert.DeserializeObject(entity.testsubjecttypes, typeof(ExamTestSubjectType[]));
                i = 0;
                foreach (ExamTestSubjectType st in TestSubjectTypes)
                {
                   st.TestID = entity.ExamTestContent.ID;
                   result = testsubjecttypeFacade.Save(st);
                   if (result == false) throw new Exception("操作失败!!");
                  
                       j = 0;
                       foreach (ExamTestSubject ts in lst[i])
                       {
                           ts.TestSubjectTypeID = st.ID;
                           result = testsubjectFacade.Save(ts);
                           if (result == false) throw new Exception("操作失败!!");             
                           j++;
                       }
                   i++;
                }
            }
    
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(TestContent entity)
        {
            bool result = false;
            result = this.testFacade.Update(entity.ExamTestContent);
            if (result == false) throw new Exception("操作失败!!");
            int i = 0;
            List<ExamTestSubject[]> lst = null;
            if (entity.testsubjects != null)
            {
                lst = (List<ExamTestSubject[]>)JavaScriptConvert.DeserializeObject(entity.testsubjects, typeof(List<ExamTestSubject[]>));
            }

            if (entity.testsubjecttypes != null)
            {
                IList<ExamTestSubjectType>  ExamTestSubjectTypes = testsubjecttypeFacade.LoadAll("OrderNo","TestID='"+ entity.ExamTestContent.ID +"'");
                foreach (ExamTestSubjectType st in ExamTestSubjectTypes)
                {
                    result = testsubjectFacade.Delete(st.ID.ToString());
                }
                result = testsubjecttypeFacade.Delete(entity.ExamTestContent.ID.ToString());
                ExamTestSubjectType[] TestSubjectTypes = (ExamTestSubjectType[])JavaScriptConvert.DeserializeObject(entity.testsubjecttypes, typeof(ExamTestSubjectType[]));
                i = 0;
                foreach (ExamTestSubjectType st in TestSubjectTypes)
                {
                    st.TestID = entity.ExamTestContent.ID;
                    result = testsubjecttypeFacade.Save(st);
                    if (result == false) throw new Exception("操作失败!!");
                    int j = 0;
                    foreach (ExamTestSubject ts in lst[i])
                    {
                        ts.TestSubjectTypeID = st.ID;
                        result = testsubjectFacade.Save(ts);
                        if (result == false) throw new Exception("操作失败!!");
                        j++;
                    }
                    i++;
                }
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Delete(string id)
        {
            bool result = false;
            result = testFacade.Delete(id.ToString());    //删除试卷内容

            string[] idarr = id.Split(',');
            for (int i = 0; i < idarr.Length; i++)
            {           
                IList<ExamTestSubjectType> testSubjectTypes = testsubjecttypeFacade.LoadAll("ID", "TestID='" + idarr[i] + "'");
                for (int j = 0; j < testSubjectTypes.Count; j++)
                {
                    result = testsubjectFacade.Delete(testSubjectTypes[j].ID);    //删除试卷试题
                }
                result = testsubjecttypeFacade.Delete(idarr[i]);    //删除试卷试题类型
            }
            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
    }
}
