using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using QzgfFrame.Exam.OnlineExamManager.Models;
using QzgfFrame.Exam.TestManger.Models;

namespace QzgfFrame.Exam.OnlineExamManager.Domain
{
    /// <summary>
    /// 在线考试
    /// </summary>
    public interface OnlineExamFacade
    {
        ExamOnlineExam Get(object id);

        bool Delete(string id);

        bool Save(ExamOnlineExam entity);
        bool Save(TestContent entity);

        bool Update(ExamOnlineExam entity);

        IList<ExamOnlineExam> LoadAll();

        IList<ExamOnlineExam> LoadAll(string order, string where);
        //分页
        string FindByPage(int pageNo, int pageSize);
        /// <summary>
        /// 分页
        /// </summary>
        /// <param name="pageNo"></param>
        /// <param name="pageSize"></param>
        /// <param name="gridsearch"></param>
        /// <returns></returns>
        string FindByPage(int pageNo, int pageSize, string gridsearch);
    }
}
