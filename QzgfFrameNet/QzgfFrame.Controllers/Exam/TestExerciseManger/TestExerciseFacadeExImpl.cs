/*
 * 文件名.........: TestExerciseFacadeExImpl.cs
 * 作者...........:  
 * 说明...........: 练习试卷设置业务处理多模型关联类 
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
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestManger.Domain;
using QzgfFrame.Exam.TestMockManger.Models;
using QzgfFrame.Exam.TestMockManger.Domain;
using QzgfFrame.Exam.TestExerciseManger.Models;
using QzgfFrame.Exam.TestExerciseManger.Domain;
using QzgfFrame.Exam.ExamineeRangeManger.Domain;
using QzgfFrame.Exam.ExamineeRangeManger.Models;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;
namespace QzgfFrame.Controllers.Exam.TestExerciseManger 
{
    public class TestExerciseFacadeExImpl : TestExerciseFacadeEx
    {
        private TestExerciseFacade testexerciseFacade { set; get; }
        private TestMockFacade testmockFacade { set; get; }
        private TestFacade testFacade { set; get; }
        private ExamineeRangeFacade  examineerangeFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(TestExercise entity)
        {
            bool result = false;

            result = testexerciseFacade.Save(entity.ExamTestExercise);

            if (entity.examineeranges != "")
            {
                string[] strAry = entity.examineeranges.Split(',');
                for (int i = 0; i < strAry.Length; i++)
                {
                    ExamExamineeRange eer = new ExamExamineeRange();
                    eer.TestSetID = entity.ExamTestExercise.ID;
                    eer.ExamineeID = strAry[i].ToString();
                    result = examineerangeFacade.Save(eer);
                }
            }

            ExamTestContent etc = testFacade.Get(entity.ExamTestExercise.TestID);
            etc.State = TestState.已组织练习考;
            etc.IsDelete = false;
            result = testFacade.Update(etc); 

            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(TestExercise entity)
        {
            bool result = false;

            result = testexerciseFacade.Update(entity.ExamTestExercise);
            result = examineerangeFacade.Delete(entity.ExamTestExercise.ID);

            if (entity.examineeranges != "")
            {
                string[] strAry = entity.examineeranges.Split(',');
                for (int i = 0; i < strAry.Length; i++)
                {
                    ExamExamineeRange eer = new ExamExamineeRange();
                    eer.TestSetID = entity.ExamTestExercise.ID;
                    eer.ExamineeID = strAry[i].ToString();
                    result = examineerangeFacade.Save(eer);
                }
            }

            ExamTestContent etc = testFacade.Get(entity.ExamTestExercise.TestID);
            etc.State = TestState.已组织练习考;
            etc.IsDelete = false;
            result = testFacade.Update(etc); 

            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Delete(string id)
        {
            bool result = false;

            string[] idarr = id.Split(',');

            for (int i = 0; i < idarr.Length; i++)
            {
                ExamTestExercise ete = testexerciseFacade.Get(idarr[i]);
                //查找模拟考卷中是否存在与练习卷表相同的试卷ID
                IList<ExamTestMock> etms = testmockFacade.LoadAll("ID", "TestID='" + ete.TestID + "'");
                if (etms.Count == 0)     ///模拟考表中不存在，修改试卷的状态
                {
                    ExamTestContent etc = testFacade.Get(ete.TestID);
                    etc.State = TestState.未组织考试;
                    etc.IsDelete = true;
                    result = testFacade.Update(etc);
                }
                else
                {
                    ExamTestContent etc = testFacade.Get(ete.TestID);
                    etc.State = TestState.已组织模拟考;
                    result = testFacade.Update(etc);
                }
            }

            result = testexerciseFacade.Delete(id);         //删除试卷设置
            result = examineerangeFacade.Delete(id);    //删除考生范围

            if (result == false)
               throw new Exception("操作失败!!");
            return result;
        }
    }
}
