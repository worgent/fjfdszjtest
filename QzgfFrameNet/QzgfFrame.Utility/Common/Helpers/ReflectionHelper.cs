namespace QzgfFrame.Utility.Common.Helpers
{
    public class ReflectionHelper
    { 
        public static void SetProperty(object obj, string name, object value, params object[] args)
        {
            var propertyInfo = obj.GetType().GetProperty(name);
            if (propertyInfo == null) return;
            propertyInfo.SetValue(obj, value, args);
        }
         
        public static object GetProperty(object obj, string name, params object[] args)
        {
            var propertyInfo = obj.GetType().GetProperty(name);
            if (propertyInfo == null) return null;
            return propertyInfo.GetValue(obj, args);
        }
         
        public static void BatchCopyProperty(object fromEntity, object toEntity, params string[] args)
        {
            foreach (string name in args)
            {
                SetProperty(toEntity, name, GetProperty(fromEntity, name));
            }
        } 
    }
}
