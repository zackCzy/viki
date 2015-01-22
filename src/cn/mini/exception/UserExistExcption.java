package cn.mini.exception;

public class UserExistExcption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExistExcption() {
		super();
	}

	public UserExistExcption(String message, Throwable cause) {
		super(message, cause);
	}

	public UserExistExcption(String message) {
		super(message);
	}

	public UserExistExcption(Throwable cause) {
		super(cause);
	}

}
