package it.univaq.connectpa.publicbillposting.sia.endpoints.manager.exception;

public class SiaEndpointsManagerException extends RuntimeException{

	private static final long serialVersionUID = -6223624353516670973L;

	public SiaEndpointsManagerException() {
		super();
	}

	public SiaEndpointsManagerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SiaEndpointsManagerException(String message, Throwable cause) {
		super(message, cause);
	}

	public SiaEndpointsManagerException(String message) {
		super(message);
	}

	public SiaEndpointsManagerException(Throwable cause) {
		super(cause);
	}

	
	
}
