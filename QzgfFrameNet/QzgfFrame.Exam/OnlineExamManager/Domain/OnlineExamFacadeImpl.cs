using System.Collections.Generic;
using QzgfFrame.Exam.OnlineExamManager.Models;
using QzgfFrame.Utility.Core.Repository;
using Newtonsoft.Json;
using QzgfFrame.Exam.TestManger.Models;
using QzgfFrame.Exam.TestTypeManger.Models;
using QzgfFrame.Exam.TestTypeManger.Domain;
using Newtonsoft.Json;

namespace QzgfFrame.Exam.OnlineExamManager.Domain
{
    /// <summary>
    /// 在线考试
    /// </summary>
    public class OnlineExamFacadeImpl : OnlineExamFacade
    {
        private IRepository<ExamOnlineExam> onlineExamRepository { set; get; }
        private IRepository<ExamTestContent> testRepository { set; get; }

        private TestTypeFacade testtypeFacade { set; get; }
        public ExamOnlineExam Get(object id)
        {
            return onlineExamRepository.Get(id.ToString());
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
                result = onlineExamRepository.Delete(s);
            }
            return result;
        }

        /// <summary>
        /// 保存数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Save(ExamOnlineExam entity)
        {
            entity.Id = onlineExamRepository.NewSequence("SYSTEM_MENU");
            return onlineExamRepository.Save(entity);
        }

        public bool Save(TestContent entity)
        {
            bool reslut = false;

            return reslut;
        }


        /// <summary>
        /// 更新数据
        /// </summary>
        /// <param name="entity"></param>
        /// <returns></returns>
        public bool Update(ExamOnlineExam entity)
        {
            return onlineExamRepository.Update(entity);
        }

        public IList<ExamOnlineExam> LoadAll()
        {
            return onlineExamRepository.LoadAll();
        }

        public IList<ExamOnlineExam> LoadAll(string order, string where)
        {
            return onlineExamRepository.LoadAll(order, where);
        }

        public string FindByPage(int pageNo, int pageSize)
        {
            const string hql = "from ExamTestContent";
            IList<ExamOnlineExam> ls = onlineExamRepository.FindByPage(pageNo, pageSize, hql);
            string rowsjson = JsonConvert.SerializeObject(ls);
            int recordCount = onlineExamRepository.FindByPageCount(hql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }

        public string FindByPage(int pageNo, int pageSize, string gridsearch)
        {
            string sql = "select a.ID,a.Name,a.Points,a.TypeID from ExamTestContent a,ExamTestSet b where b.TestID = a.id";
            string editUrl = "";
            //在列表页设置的"epStatus"查询条件,0:显示"在线考试";2:显示"模拟考试";3:显示"在线练习";
            if (gridsearch != null)
            {
                switch (gridsearch)
                { 
                    case "0":
                        //"在线考试"必须设置考试开始时间
                        sql += " and b.BeginTime is not null ";
                        editUrl = "Exam";
                        break;
                    case "1":
                        //"模拟考试"不用设置开始时间,但是必须设置考试时长
                        sql += " and b.BeginTime is null and b.TimeLength is not null ";
                        editUrl = "Simulation";
                        break;
                    case "2":
                        //"在线练习"不用设置开始时间与考试时长
                        sql += " and b.BeginTime is null and b.TimeLength is null ";
                        editUrl = "Exercises";
                        break;
                }
            }

            IList<object[]> ls = testRepository.FindByLinkPage(pageNo, pageSize, sql);

            IList<ExamOnlineExam> cyc = new List<ExamOnlineExam>();
            for (int i = 0; i < ls.Count; i++)
            {
                ExamOnlineExam cycs = new ExamOnlineExam();
                string id = "";
                id = ls[i][3].ToString();
                ExamTestType TestType = testtypeFacade.Get(id);
                cycs.Id = ls[i][0].ToString();
                string url = "";
                url = "<a href='/Exam/OnlineExam/" + editUrl + "/" + ls[i][0].ToString() + "'>开始考试</a>";
                cycs.Operating = url;
                cycs.PaperName = ls[i][1].ToString();
                cycs.Score = ls[i][2].ToString();
                cycs.PaperType = TestType.Type; ;
                cyc.Add(cycs);
            }

            string rowsjson = JsonConvert.SerializeObject(cyc);
            int recordCount = testRepository.FindByPageLinkCount(sql);
            string json = @"{""Rows"":" + rowsjson + @",""Total"":""" + recordCount + @"""}";
            return json;
        }
    }
}
