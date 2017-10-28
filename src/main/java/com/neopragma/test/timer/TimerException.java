package com.neopragma.test.timer;

public class TimerException extends RuntimeException {
	
	private static final long serialVersionUID = -4853823218922835701L;
	private String message;

	public TimerException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
}
