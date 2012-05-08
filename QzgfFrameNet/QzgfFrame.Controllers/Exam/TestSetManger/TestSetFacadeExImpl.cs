/*
 * 文件名.........: TestSetFacadeExImpl.cs
 * 作者...........:  
 * 说明...........: 试卷设置业务处理多模型关联类 
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
using QzgfFrame.Exam.TestSetManger.Models;
using QzgfFrame.Exam.TestSetManger.Domain;
using QzgfFrame.Exam.ExamineeRangeManger.Domain;
using QzgfFrame.Exam.ExamineeRangeManger.Models;
using Newtonsoft.Json;
using QzgfFrame.Utility.Common;
namespace QzgfFrame.Controllers.Exam.TestSetManger 
{
    public class TestSetFacadeExImpl: TestSetFacadeEx
    {
        private TestSetFacade testsetFacade { set; get; }
        private TestFacade testFacade { set; get; }
        private ExamineeRangeFacade  examineerangeFacade { set; get; }

        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        public bool Save(TestSet entity)
        {
            bool result = false;

            //更改考试主卷状态
            ExamTestContent ExamTestContent = testFacade.Get(entity.ExamTestSet.TestID);
            ExamTestContent.State = TestState.已组织考试;
            ExamTestContent.IsDelete = false;
            result = testFacade.Update(ExamTestContent);

            //获得考试可选试卷ID和状态
            TestIDState[] ts = (TestIDState[])JavaScriptConvert.DeserializeObject(entity.testids, typeof(TestIDState[]));
            TestState testState;
            for (int i = 0; i < ts.Length; i++)
            {
                testState=(TestState)Enum.Parse(typeof(TestState),ts[i].State);  //把一个枚举名，或者数字值转换为相应的Enum
                if (testState == TestState.已组织考试)   //如果试卷状态被设置为已组织考试，保存
                {
                    entity.ExamTestSet.TestID += ",";
                    entity.ExamTestSet.TestID += ts[i].ID;  //字符串组成所有考卷ID
                    //更新考试可选试卷状态
                    ExamTestContent etc = testFacade.Get(ts[i].ID);
                    etc.State = TestState.已组织考试;
                    etc.IsDelete = false;
                    result = testFacade.Update(etc);
                }
            }

            result = testsetFacade.Save(entity.ExamTestSet);


            if (entity.examineeranges != "")
            {
                string[] strAry = entity.examineeranges.Split(',');
                for (int i = 0; i < strAry.Length; i++)
                {
                    ExamExamineeRange eer = new ExamExamineeRange();
                    eer.TestSetID = entity.ExamTestSet.ID;
                    eer.ExamineeID = strAry[i].ToString();
                    result = examineerangeFacade.Save(eer);
                }
            }

            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Update(TestSet entity)
        {
            bool result = false;

            //获得考试可选试卷ID和状态
            TestIDState[] ts = (TestIDState[])JavaScriptConvert.DeserializeObject(entity.testids, typeof(TestIDState[]));
            TestState testState;
            List<string> noSaveTestID = new List<string>();    //不作为本次可选考卷的考卷ID数组
            for (int i = 0; i < ts.Length; i++)
            {
                testState = (TestState)Enum.Parse(typeof(TestState), ts[i].State);  //把一个枚举名，或者数字值转换为相应的Enum

                if (testState == TestState.已组织考试)   //如果试卷状态被设置为已组织考试，保存
                {
                    entity.ExamTestSet.TestID += ",";
                    entity.ExamTestSet.TestID += ts[i].ID;  //字符串组成所有考卷ID
                    //更新考试可选试卷状态
                    ExamTestContent etc = testFacade.Get(ts[i].ID);
                    etc.State = TestState.已组织考试;
                    etc.IsDelete = false;
                    result = testFacade.Update(etc);
                }
                else                                    //保存到本次不可选考卷的考卷ID数组
                {
                    noSaveTestID.Add(ts[i].ID);
                }
            }

            result = testsetFacade.Update(entity.ExamTestSet);

            result = examineerangeFacade.Delete(entity.ExamTestSet.ID);

            if (entity.examineeranges != "")
            {
                string[] strAry = entity.examineeranges.Split(',');
                for (int i = 0; i < strAry.Length; i++)
                {
                    ExamExamineeRange eer = new ExamExamineeRange();
                    eer.TestSetID = entity.ExamTestSet.ID;
                    eer.ExamineeID = strAry[i].ToString();
                    result = examineerangeFacade.Save(eer);
                }
            }

            for (int i = 0; i < noSaveTestID.Count; i++)
            {
                bool isTrue = false;
                string TestID = noSaveTestID[i].ToString();        //不是本次可选试卷ID
                IList<ExamTestSet> etss = testsetFacade.LoadAll();
                for (int j = 0; j < etss.Count; j++)               //从考卷设置表中查找和不是本次可选试卷相同的ID数据
                {
                    ExamTestSet ets = etss[j];
                    string[] testid = etss[j].TestID.Split(',');  //拆分字符串考卷ID
                    for (int k = 0; k < testid.Length; k++)
                    {
                        if (TestID == testid[k].ToString())       //找到与不是本次可选试卷相同的ID数据
                        {
                            isTrue = true;
                            j = etss.Count;
                            break;
                        }
                    }
                }
                if (isTrue == false)        //如果找不到，表示其他考试设置卷没有包含这张试卷，可以设置为未组织
                {
                    ExamTestContent etc = testFacade.Get(TestID);
                    etc.State = TestState.未组织考试;
                    etc.IsDelete = true;
                    result = testFacade.Update(etc);
                }

            }

            if (result == false)
                throw new Exception("操作失败!!");
            return result;
        }
        public bool Delete(string id)
        {
            bool result = false;

            string[] idarr = id.Split(',');

            for (int h = 0; h < idarr.Length; h++)
            {
               
                ExamTestSet ExamTestSet = testsetFacade.Get(idarr[h]);     

                string[] strAry = ExamTestSet.TestID.Split(',');  //获得考试设置表对应的考卷ID 拆分多个

                result = testsetFacade.Delete(idarr[h]);     //先删除数据，可以在查找中不查找这条数据

                for (int i = 0; i < strAry.Length; i++)
                {
                    bool isTrue = false;
                    string TestID = strAry[i].ToString();          //试卷ID
                    IList<ExamTestSet> etss = testsetFacade.LoadAll();  
                    for (int j = 0; j < etss.Count; j++)           //从考卷设置表中查找和试卷ID相同的数据
                    {
                        ExamTestSet ets = etss[j];
                        string[] testid = etss[j].TestID.Split(',');  //拆分字符串考卷ID
                        for (int k = 0; k < testid.Length; k++)
                        {
                            if (TestID == testid[k].ToString())       //找到与试卷ID相同的数据
                            {
                                isTrue = true;
                                j = etss.Count;
                                break;
                            }
                        }

                    }
                    if (isTrue == false)        //如果找不到，表示其他考试设置卷没有包含这张试卷，可以设置为未组织
                    {
                        ExamTestContent etc = testFacade.Get(TestID);
                        etc.State = TestState.未组织考试;
                        etc.IsDelete = true;
                        result = testFacade.Update(etc);
                    }

                }
            }

            result = examineerangeFacade.Delete(id);    //删除考生范围

            if (result == false)
               throw new Exception("操作失败!!");
            return result;
        }
    }
}
