package com.philips.dmis.swt.ui.toolkit.statement.method;

public class IllegalStatementException extends Exception {
    public IllegalStatementException() {
    }

    public IllegalStatementException(String message) {
        super(message);
    }

    public IllegalStatementException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalStatementException(Throwable cause) {
        super(cause);
    }
}
