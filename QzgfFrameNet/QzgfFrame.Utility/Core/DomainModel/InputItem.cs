using System;
using QzgfFrame.Utility.Core.QzgfException;

namespace QzgfFrame.Utility.Core.DomainModel
{
    /// <summary>
    /// 下拉输入项实体
    /// </summary>
    public abstract class InputItem : Entity
    {
        private string name;
        private string code;
        /// <summary>
        /// 文本值
        /// </summary>
        public virtual string Name
        {
            get
            {
                return this.name;
            }
            set
            {
                if (String.IsNullOrEmpty(value))
                    throw new NotNullException();
                this.name = value;
            }
        }
        /// <summary>
        /// 编码
        /// </summary>
        public virtual string Code
        {
            get
            {
                return this.code;
            }
            set
            {
                if (String.IsNullOrEmpty(value))
                    throw new NotNullException();
                this.code = value;
            }
        }
        /// <summary>
        /// 助记码
        /// </summary>
        public virtual string InputCode1 { get; set; }
        /// <summary>
        /// 助记码2
        /// </summary>
        public virtual string InputCode2 { get; set; }
        /// <summary>
        /// 助记码3
        /// </summary>
        public virtual string InputCode3 { get; set; }
    }
}
