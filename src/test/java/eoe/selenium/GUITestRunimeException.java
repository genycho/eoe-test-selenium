package eoe.selenium;

/**
 * 
 *	This is for separating the exceptions involved testing from the target's logical exception
 */
public class GUITestRunimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8263566215387270242L;

//	  public GUITestRunimeException() {
//	        super();
//	    }

	    public GUITestRunimeException(String message) {
	        super(message);
	    }

	    public GUITestRunimeException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
