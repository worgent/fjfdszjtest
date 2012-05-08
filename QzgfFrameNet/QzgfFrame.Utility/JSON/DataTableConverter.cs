using System;
using System.Data;
using Newtonsoft.Json;

namespace QzgfFrame.Utility.JSON
{
    public class DataTableConverter : JsonConverter
    { 
        public override bool CanConvert(Type objectType)
        {
            return typeof(DataTable).IsAssignableFrom(objectType);
        } 
        public override object ReadJson(JsonReader reader, Type objectType, object existingValue, JsonSerializer serializer)
        {
            throw new NotImplementedException();
        }

        public override void WriteJson(JsonWriter writer, object value, JsonSerializer serializer)
        {
            DataTable dt = (DataTable)value;
            writer.WriteStartArray();
            foreach (DataRow dr in dt.Rows)
            {
                writer.WriteStartObject();
                foreach (DataColumn dc in dt.Columns)
                {
                    writer.WritePropertyName(dc.ColumnName);
                    writer.WriteValue(dr[dc].ToString());
                }
                writer.WriteEndObject();
            }
            writer.WriteEndArray();
        }
    }

}
