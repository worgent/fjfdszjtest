using System;
using Quartz;
using Spring.Scheduling.Quartz;

namespace QzgfFrame.Mvc3.CommonSupport.Scheduling
{
	/// <summary>
	/// Example job.
	/// </summary>
    public class ExampleJob : QuartzJobObject
    {
        protected log4net.ILog Logger = log4net.LogManager.GetLogger("Logger");
        private string userName;

		/// <summary>
		/// Simple property that can be injected.
		/// </summary>
        public string UserName
        {
            set { userName = value; }
        }

		/// <summary>
		/// Execute.
		/// </summary>
		/// <param name="context"></param>
        protected override void ExecuteInternal(JobExecutionContext context)
        {
            Logger.Info(String.Format("{0}: ExecuteInternal called, user name: {1}, next fire time {2}",
                DateTime.Now, userName, context.NextFireTimeUtc.Value.ToLocalTime()));

            Console.WriteLine("{0}: ExecuteInternal called, user name: {1}, next fire time {2}", 
                DateTime.Now, userName, context.NextFireTimeUtc.Value.ToLocalTime());
        }
    }
}
