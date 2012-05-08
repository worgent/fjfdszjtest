using System;
using System.Linq.Expressions;
using System.Diagnostics;

namespace QzgfFrame.Utility.Common.Helpers
{

    public class ExceptionHelper
    {

        public static bool TryCatch(Expression<System.Action> action, Action<Exception> failureAction, System.Action successAction)
        {
            try
            {
                action.Compile().Invoke();
                successAction.Invoke();
                return true;
            }
            catch (Exception ex)
            {
#if DEBUG
                Trace.WriteLine(ex.Message);
                Trace.WriteLine(ex.StackTrace);
#endif
                failureAction.Invoke(ex);
                return false;
            }
        }


    }
}
