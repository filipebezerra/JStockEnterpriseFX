package jstockenterprisefx.base.jpa.exceptions;

public class NonExistentEntityException extends Exception {

	public NonExistentEntityException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	public NonExistentEntityException(final String message) {
		super(message);
	}

}
