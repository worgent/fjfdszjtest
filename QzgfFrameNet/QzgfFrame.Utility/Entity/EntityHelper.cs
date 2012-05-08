using System;
using System.Collections.Generic;
using System.Data;

namespace QzgfFrame.Utility.Entity
{
    public class EntityHelper
    {
        /// <summary>
        /// DataTableToEntities
        /// </summary> 
        public static List<T> DataTableToEntities<T>(DataTable dt)
            where T: EntityBase,new()
        {
            var entities = new List<T>();

            if (dt == null) return entities;
            foreach (DataRow dr in dt.Rows)
            {
                var t = DataRowToEntity<T>(dr);
                entities.Add(t);
            }
            return entities;
        }

        /// <summary>
        /// DataRowToEntity
        /// </summary>
        public static T DataRowToEntity<T>(DataRow dr) where T :EntityBase ,new()
        { 
            T entity = new T();  
            //set value
            foreach (FieldInfo field in entity._Fields)
            {
                if (dr.Table.Columns.Contains(field.Name))
                {
                    string value = string.Empty;
                    if (dr.RowState == DataRowState.Deleted)
                    {
                        value = dr[field.Name, DataRowVersion.Original].ToString();
                    }
                    else
                    {
                        value = dr[field.Name].ToString();
                    }
                    if (string.IsNullOrEmpty(value))
                    {
                        entity.SetFieldValue(field.Name, null);
                    }
                    else
                    {

                        var dataType = entity.GetFieldByName(field.Name).FieldType;
                        entity.SetFieldValue(field.Name, Convert.ChangeType(value, dataType));
                    }
                }
            }
            return entity;
        }
    }
}
