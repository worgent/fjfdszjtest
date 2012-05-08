using System;

namespace QzgfFrame.System.PermissionsManger.Models
{
    [Serializable]
    public class CompositeId
    {
        #region Private Members
        private string _id;
        private string _roleid;
        #endregion

        #region Constructor

        public CompositeId()
		{
			_id = String.Empty;
            _roleid = String.Empty; 
		}

        public CompositeId(string id,string roleid)
        {
            _id = id;
            _roleid = roleid;
        }



        #endregion
   
        #region Public Properties
        public virtual string Id
        {
            get
            {
                return _id;
            }

            private set
            {
                if (value == null)
                    throw new ArgumentOutOfRangeException("Null value not allowed for Id", value, "null");

                if (value.Length > 20)
                    throw new ArgumentOutOfRangeException("Invalid value for Id", value, value.ToString());

                _id = value;
            }
        }

        public virtual string Roleid
        {
            get
            {
                return _roleid;
            }

            private set
            {
                if (value != null && value.Length > 20)
                    throw new ArgumentOutOfRangeException("Invalid value for Roleid", value, value.ToString());

                _roleid = value;
            }
        }
	

        #endregion

        #region Equals And HashCode Overrides
        /// <summary>
        /// local implementation of Equals based on unique value members
        /// </summary>
        public override bool Equals(object obj)
        {
            if (this == obj) return true;
            if ((obj == null) || (obj.GetType() != this.GetType())) return false;
            CompositeId castObj = (CompositeId)obj;
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

    [Serializable]
	public  class ViewSystemRolemenu
	{

		#region Private Members
        private CompositeId _compositeid;
		private string _name; 
		private string _father;  
		private Boolean _ischeck; 		
		#endregion

		#region Constructor

		public ViewSystemRolemenu()
		{
		    _compositeid = new CompositeId();
			_name = String.Empty; 
			_father = String.Empty; 
			_ischeck = true; 
		}
		#endregion // End of Default ( Empty ) Class Constuctor

		#region Required Fields Only Constructor
		/// <summary>
		/// required (not null) fields only constructor
		/// </summary>
		public ViewSystemRolemenu(
			string id)
			: this()
		{
            _compositeid = new CompositeId(String.Empty, String.Empty);
			_name = String.Empty;
			_father = String.Empty;
			_ischeck = true;
		}
		#endregion // End Constructor

		#region Public Properties
        public virtual CompositeId CompositeId
		{
			get
			{
                return _compositeid;
			}

			private  set	
			{
                _compositeid = value;
			}
		}
		
		public virtual string Name
		{
			get
			{ 
				return _name;
			}

			private set	
			{	
				if(  value != null &&  value.Length > 256)
					throw new ArgumentOutOfRangeException("Invalid value for Name", value, value.ToString());
				
				_name = value;
			}
		}
			
		public virtual string Father
		{
			get
			{ 
				return _father;
			}

			private set	
			{	
				if(  value != null &&  value.Length > 20)
					throw new ArgumentOutOfRangeException("Invalid value for Father", value, value.ToString());
				
				_father = value;
			}
		}
			
		
		public virtual Boolean Ischeck
		{
			get
			{ 
				return _ischeck;
			}

			private set	
			{	
				_ischeck = value;
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
			ViewSystemRolemenu castObj = (ViewSystemRolemenu)obj; 
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
