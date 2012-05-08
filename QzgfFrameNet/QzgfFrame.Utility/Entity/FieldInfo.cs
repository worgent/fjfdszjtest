using System;

namespace QzgfFrame.Utility.Entity
{
    public class FieldInfo
    {
        public bool IsPrimaryKey { get; set; }
        public string Name { get; set; }
        public Type FieldType { get; set; }
        public string FieldTypeString { get; set; }
        public int Length { get; set; }
        public string Description { get; set; }
        public bool AllowDBNull { get; set; }
        public object DefaultValue { get; set; }
    }
}
