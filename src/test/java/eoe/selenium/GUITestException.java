package eoe.selenium;

/**
 * 
 *	This is for separating the exceptions involved testing from the target's logical exception 
 */
public class GUITestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6515943846099646022L;

//	public GUITestException() {
//		super();
//	}

	public GUITestException(String message) {
		super(message);
	}

	public GUITestException(String message, Throwable cause) {
		super(message, cause);
	}

}
