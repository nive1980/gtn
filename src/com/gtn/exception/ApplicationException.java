package com.gtn.exception;

public class ApplicationException extends NestedException {

	  public ApplicationException(String message, Exception originatingException) {
	        super(message, originatingException);
	    }
}
