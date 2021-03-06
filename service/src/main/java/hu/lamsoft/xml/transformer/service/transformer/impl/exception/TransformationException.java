package hu.lamsoft.xml.transformer.service.transformer.impl.exception;

public class TransformationException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransformationException() {
		super();
	}
	
	public TransformationException(String message) {
		super(message);
	}

	public TransformationException(Throwable throwable) {
		super(throwable);
	}
	
	public TransformationException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
