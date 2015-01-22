package cn.mini.exception;

public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);
	}

	public UserServiceException(Throwable cause) {
		super(cause);
	}

	public UserServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
