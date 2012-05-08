using System;

namespace QzgfFrame.System.PermissionsManger.Models
{
	[Serializable]
    public  class ViewSystemRolepowerval
	{

		#region Private Members
        private CompositeId _compositeid;
        private string _menuid;
        private string _menuname;
        private string _fieldcode;
        private string _fieldname;
        private string _tablecode;
        private string _tablename;
        private Int32 _powerval; 		
		#endregion

		#region Constructor

		public ViewSystemRolepowerval()
		{
            _compositeid = new CompositeId(String.Empty, String.Empty);
            _menuid = String.Empty;
            _fieldcode = String.Empty;
            _fieldname = String.Empty;
            _tablecode = String.Empty;
            _tablename = String.Empty;
		    _menuname = string.Empty;
			_powerval = 0; 
		}
		#endregion // End of Default ( Empty ) Class Constuctor

		#region Required Fields Only Constructor
		/// <summary>
		/// required (not null) fields only constructor
		/// </summary>
		public ViewSystemRolepowerval(
			string id)
			: this()
		{
            _compositeid = new CompositeId(String.Empty, String.Empty);
            _menuid = String.Empty;
            _fieldcode = String.Empty;
            _fieldname = String.Empty;
            _tablecode = String.Empty;
            _tablename = String.Empty;
		    _menuname = string.Empty;
			_powerval = 0;
		}
		#endregion // End Constructor

		#region Public Properties

        public virtual string Menuid
        {
            get
            {
                return _menuid;
            }

            private set
            {
                if (value != null && value.Length > 20)
                    throw new ArgumentOutOfRangeException("Invalid value for Menuid", value, value.ToString());

                _menuid = value;
            }
        }

        public virtual string Menuname
        {
            get
            {
                return _menuname;
            }

            private set
            {
                if (value != null && value.Length > 100)
                    throw new ArgumentOutOfRangeException("Invalid value for Menuid", value, value.ToString());

                _menuname = value;
            }
        }

        public virtual string Fieldcode
        {
            get
            {
                return _fieldcode;
            }

            private set
            {
                if (value != null && value.Length > 50)
                    throw new ArgumentOutOfRangeException("Invalid value for Filedcode", value, value.ToString());

                _fieldcode = value;
            }
        }

        public virtual string Fieldname
        {
            get
            {
                return _fieldname;
            }

            private set
            {
                if (value != null && value.Length > 100)
                    throw new ArgumentOutOfRangeException("Invalid value for Filedname", value, value.ToString());

                _fieldname = value;
            }
        }

        public virtual string Tablecode
        {
            get
            {
                return _tablecode;
            }

            private set
            {
                if (value != null && value.Length > 50)
                    throw new ArgumentOutOfRangeException("Invalid value for Tablecode", value, value.ToString());

                _tablecode = value;
            }
        }

        public virtual string Tablename
        {
            get
            {
                return _tablename;
            }

            private set
            {
                if (value != null && value.Length > 100)
                    throw new ArgumentOutOfRangeException("Invalid value for Tablename", value, value.ToString());

                _tablename = value;
            }
        }

        public virtual CompositeId CompositeId
        {
            get
            {
                return _compositeid;
            }

            private set
            {
                _compositeid = value;
            }
        }

        public virtual Int32 Powerval
		{
			get
			{ 
				return _powerval;
			}

			private set	
			{	
				_powerval = value;
			}
		}
			
				
		#endregion 

		#region Public Functions

		#endregion //Public Functions

		#region Equals And HashCode Overrides
		/// <summary>
		/// local implementation of Equals based on unique value members
		/// </summary>
		public override bool Equals( object obj )
		{
			if( this == obj ) return true;
			if( ( obj == null ) || ( obj.GetType() != this.GetType() ) ) return false;
			ViewSystemRolepowerval castObj = (ViewSystemRolepowerval)obj; 
			return castObj.GetHashCode() == this.GetHashCode();
		}
		
		/// <summary>
		/// local implementation of GetHashCode based on unique value members
		/// </summary>
		public override int GetHashCode()
		{
			return this.GetType().FullName.GetHashCode();
				
		}
		#endregion
		
	}
}
