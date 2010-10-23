package com.womobile.util;



/**
 * A runtime exception for when Statusnet requests don't work. All
 * {@link Statusnet} methods can throw this.
 * <p>
 * This contains several subclasses which should be thrown to mark
 * different problems.
 * <p>
 * I believe unchecked exceptions are preferable to checked ones,
 * because they avoid the problems caused by swallowing exceptions.
 * But if you don't like runtime exceptions, just edit this class.
 * 
 * @author Daniel Winterstein
 */
public class StatusnetException extends RuntimeException {
	/**
	 * A timeout exception - probably caused by Twitter being overloaded.
	 */
	public static class Timeout extends StatusnetException {
		public Timeout(String string) {
			super(string);
		}
		private static final long serialVersionUID = 1L;
	}
	/**
	 * A code 50X error (e.g. 502) - indicating something went wrong at 
	 * Statusnet's end. The API equivalent of the Fail Whale. 
	 * Usually retrying in a minute will fix this.
	 */
	public static class E50X extends StatusnetException {
		public E50X(String string) {
			super(string);
		}
		private static final long serialVersionUID = 1L;
	}
	/**
	 * A Forbidden exception
	 * @author daniel
	 *
	 */
	public static class E403 extends StatusnetException {
		public E403(String string) {
			super(string);
		}
		private static final long serialVersionUID = 1L;
	}

	private static final long serialVersionUID = 1L;
	
	private String additionalInfo = "";
	
	/**
	 * Wrap an exception as a StatusnetException.
	 */
	StatusnetException(Exception e) {
		super(e);
		// avoid gratuitous nesting of exceptions
		assert !(e instanceof StatusnetException) : e;
	}

	/**
	 * @param string
	 */
	public StatusnetException(String string) {
		super(string);
	}
	
	public StatusnetException(String string, String additionalInfo) {
		this(string);
		this.setAdditionalInfo(additionalInfo);
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Indicates a 404: resource does not exist error from Statusnet. Maybe the site is mistyped?
	 * Note: Can be throw in relation to suspended users.
	 */
	public static class E404 extends StatusnetException {
		public E404(String string) {
			super(string);
		}
		private static final long serialVersionUID = 1L;		
	}
	/**
	 * Indicates a rate limit error (i.e. you've over-used Statusnet) (Note: There is currently no limit)
	 */
	public static class RateLimit extends StatusnetException {
		public RateLimit(String string) {
			super(string);
		}
		private static final long serialVersionUID = 1L;		
	}
}
