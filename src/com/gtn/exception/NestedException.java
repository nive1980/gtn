package com.gtn.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class NestedException extends Exception {

    private Throwable _nestedException;
    public NestedException(String message, Exception nestedException) {
        super(message);
        _nestedException = nestedException;
    }
    public Throwable getOriginatingException() {
        return _nestedException;
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (_nestedException != null) {
            _nestedException.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        if (_nestedException != null) {
            _nestedException.printStackTrace(s);
        }
    }

    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        if (_nestedException != null) {
            _nestedException.printStackTrace(s);
        }
    }

}
