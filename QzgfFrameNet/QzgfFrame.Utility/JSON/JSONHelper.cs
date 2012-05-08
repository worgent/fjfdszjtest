using System.Collections;
using System.Collections.Generic;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace QzgfFrame.Utility.JSON
{
    public class JSONHelper
    {
        #region  JSON To ArrayList
        /// <summary>
        /// JSON To ArrayList
        /// </summary>
        /// <param name="json"></param>
        /// <returns></returns>
        public static ArrayList GetArrayListFromJSON(string json)
        {
            object o = JsonConvert.DeserializeObject(json);
            object v = FormatObject(o);
            ArrayList taskList = (ArrayList)FormatObject(o);
            return taskList;
        }

        /// <summary>
        /// 格式化对象
        /// </summary>
        /// <param name="o"></param>
        /// <returns></returns>
        public static object FormatObject(object o)
        {
            if (o == null) return null;
             
            if (o is JObject)
            {
                JObject jo = o as JObject;

                Hashtable h = new Hashtable();

                foreach (KeyValuePair<string, JToken> entry in jo)
                {
                    h[entry.Key] = FormatObject(entry.Value);
                }

                o = h;
            }
            else if (o is IList)
            {

                ArrayList list = new ArrayList();
                list.AddRange((o as IList));
                int i = 0, l = list.Count;
                for (; i < l; i++)
                {
                    list[i] = FormatObject(list[i]);
                }
                o = list;

            }
            else if (typeof(JValue) == o.GetType())
            {
                JValue v = (JValue)o;
                o = FormatObject(v.Value);
            }
            else
            {
            }
            return o;
        }
        #endregion

        #region Obj To JSON String
        public static string ToJSON(object obj)
        {
            return JsonConvert.SerializeObject(obj);
        }
        #endregion 
    }
}
