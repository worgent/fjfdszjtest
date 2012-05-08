using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using QzgfFrame.Utility.Common;
using QzgfFrame.Utility.Common.Helpers;
using FieldInfo = QzgfFrame.Utility.Entity.FieldInfo;

namespace QzgfFrame.Utility.Entity
{
    public abstract class EntityBase
    {
        #region 设置属性
        public virtual void SetFieldValue(string FieldName, object Value)
        {
            ReflectionHelper.SetProperty(this, FieldName, Value);
        }
        public virtual object GetFieldValue(string FieldName)
        {
            return ReflectionHelper.GetProperty(this, FieldName);
        }
        #endregion

        #region 获取字段信息
        public virtual FieldInfo GetFieldByName(string name)
        {
            foreach (var info in this._Fields)
            {
                if (info.Name == name) return info;
            }
            return null;
        }
        #endregion

        #region 记录状态
        /// <summary>
        /// 设置实体类的记录状态
        /// </summary> 
        public virtual void SetRecordStatus(string recordStatus, int? createUserID, int? modifyUserID, DateTime date)
        { 
            this.SetFieldValue("RecordStatus", recordStatus);
            if (createUserID != null)
            {
                this.SetFieldValue("CreateUserID", createUserID);
                this.SetFieldValue("CreateDate", date);
            }
            if (modifyUserID != null)
            {
                this.SetFieldValue("ModifyUserID", modifyUserID);
                this.SetFieldValue("ModifyDate", date);
            }
        }
        /// <summary>
        /// 设置记录状态，使用当前时间
        /// </summary>
        public virtual void SetRecordStatus(string recordStatus, int? createUserID, int? modifyUserID)  
        {
            SetRecordStatus(recordStatus, createUserID, modifyUserID, DateTime.Now);
        }
        /// <summary>
        /// 设置实体类的记录状态，会使用默认的 CurrentUserID 和当前日期自动进行设置
        /// </summary>
        public virtual void SetRecordStatus(string recordStatus) 
        {
            SetRecordStatus(recordStatus, false);
        }
        /// <summary>
        /// 设置实体类的记录状态，同时使用默认的 CurrentUserID 和当前日期自动进行记录
        /// </summary>
        public virtual void SetRecordStatus(string recordStatus,bool IsModifyOnly)
        {
            int userID = SysContext.CurrentUserID;
            if (IsModifyOnly)
                SetRecordStatus(recordStatus, null, userID, DateTime.Now);
            else
                SetRecordStatus(recordStatus, userID, userID, DateTime.Now);
        }
        #endregion

        #region 属性
        public virtual IList<FieldInfo> _Fields { get; set; }
        private bool _isnew = false;
        public virtual bool _IsNew
        {
            get { return _isnew; }
            set
            {
                if(value)
                    ___status = EntityAction.add.ToString();
                else
                    ___status = EntityAction.update.ToString();
                _isnew = value;
            }
        }
        private bool _isdelete = false;
        public virtual bool _IsDelete
        {
            get { return _isdelete; }
            set
            {
                if(value)
                    ___status = EntityAction.delete.ToString();
                _isdelete = value;
            }
        }
        private string ___status = null;
        public virtual string __status
        {
            get { return ___status; }
            set 
            {
                if (value == EntityAction.add.ToString())
                    _isnew = true;
                else if (value == EntityAction.delete.ToString())
                    _isdelete = true;
                ___status = value; 
            }
        }
        #endregion

        #region 从控件加载属性值
        public virtual void ShowToControl(Control control)
        {
            ShowToControl(control, "", null);
        }
        public virtual void ShowToControl(Control control, string prefix)
        {
            ShowToControl(control, prefix, null);
        }
        public virtual void ShowToControl(Control control, string prefix, Control[] ignore)
        {
            try
            {
                if (control == null) return;
                if (control is System.Web.UI.WebControls.WebControl)
                {
                    if (control.ID == null || !control.ID.StartsWith(prefix)) return;
                    if (ignore != null && ignore.Contains(control)) return;
                    var fieldName = control.ID.Substring(prefix.Length);
                    var field = GetFieldByName(fieldName);
                    if (field == null) return;
                    var value = GetFieldValue(fieldName).ToStr();
                    ControlHelper.SetValue(control, value);
                }
                else if (control.Controls != null)
                {
                    foreach (var children in control.Controls)
                    {
                        ShowToControl(children as Control, prefix, ignore);
                    }
                }
            }
            catch (Exception err)
            {
            }
        }

 
        #endregion

        #region 加载属性值到控件

        public virtual void LoadFromControl(Control control, string prefix) 
        {
            LoadFromControl(control, prefix, null);
        }
        public virtual void LoadFromControl(Control control, string prefix, Control[] ignore)
        {
            try
            {
                if (control == null) return;
                if (control is System.Web.UI.WebControls.WebControl)
                {
                    if (control.ID == null || !control.ID.StartsWith(prefix)) return;
                    if (ignore != null && ignore.Contains(control)) return;
                    var fieldName = control.ID.Substring(prefix.Length);
                    var field = GetFieldByName(fieldName);
                    if (field == null) return;
                    var value = Convert.ChangeType(ControlHelper.GetValue(control), field.FieldType);
                    SetFieldValue(fieldName, value);
                }
                else if(control.Controls != null)
                {
                    foreach (var children in control.Controls)
                    {
                        LoadFromControl(children as Control, prefix, ignore);
                    }
                }
            }
            catch (Exception err)
            {
            }
        } 
        #endregion
    }
}
