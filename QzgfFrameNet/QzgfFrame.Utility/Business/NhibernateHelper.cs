using System;
using System.Data;
using NHibernate;
using NHibernate.Cfg;
using NHibernate.Engine;

namespace QzgfFrame.Utility.Business
{
    public class NhibernateHelper
    {
        private const string CurrentSessionKey = "ligerRM.NHibernateSession";
        //后备Session,System.Web.HttpContext.Current为空是可以使用
        private static ISession reserveSession = null;
        private static ISessionFactory factory;
        private static Configuration configure;
        static NhibernateHelper()
        {
            var context = System.Web.HttpContext.Current;
            if (context == null) return;
            configure =  new Configuration().Configure(context.Server.MapPath("~/Config/hibernate.cfg.xml"));
            factory = configure.BuildSessionFactory(); 
        } 
        public static ISession CreateSession()
        {
            return factory.OpenSession();
        }
        public static ISession GetCurrentSession()
        {
            var context = System.Web.HttpContext.Current;
            if (context == null) return reserveSession;
            ISession session = context.Items[CurrentSessionKey] as ISession;
            if (session == null || !session.IsConnected || !session.IsOpen)
            {
                session = factory.OpenSession();
                context.Items[CurrentSessionKey] = session;
            }  
            return session;
        }
        public static void RemoveSession()
        {
            var context = System.Web.HttpContext.Current;
            if (context == null)
            {
                if (reserveSession != null) reserveSession.Close();
                return;
            }
            ISession currentSession = context.Items[CurrentSessionKey] as ISession;
            if (currentSession == null) return;
            if(currentSession.IsConnected || currentSession.IsOpen) currentSession.Close();
            context.Items.Remove(CurrentSessionKey);
        }

        public static void CloseSessionFactory()
        {
            if (factory != null) factory.Close();
        }
        /// <summary>
        /// 设置后备Session,System.Web.HttpContext.Current为空是可以使用
        /// </summary>
        /// <param name="session"></param>
        public static void SetReserveSession(ISession session)
        {
            reserveSession = session;
        }


        #region 扩展方法
        public static object ExecuteScalar(string commandText)
        {
            ISessionFactoryImplementor s = (ISessionFactoryImplementor)factory;
            IDbConnection conn = s.ConnectionProvider.GetConnection();
            IDbCommand cmd = conn.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = commandText;
            if (conn.State == ConnectionState.Closed) conn.Open();
            return cmd.ExecuteScalar();
        }

        public static int ExecuteNonQuery(string commandText)
        {
            ISessionFactoryImplementor s = (ISessionFactoryImplementor)factory;
            IDbConnection conn = s.ConnectionProvider.GetConnection();
            IDbCommand cmd = conn.CreateCommand();
            cmd.CommandType = CommandType.Text;
            cmd.CommandText = commandText;
            if (conn.State == ConnectionState.Closed) conn.Open();
            return cmd.ExecuteNonQuery();
        }

        public static bool Has(string commandText)
        {
            var obj = ExecuteScalar(commandText);
            if (obj == null || Convert.IsDBNull(obj)) return false;
            if (Convert.ToInt32(obj) == 0) return false;
            return true;
        } 
        #endregion
    }
}
