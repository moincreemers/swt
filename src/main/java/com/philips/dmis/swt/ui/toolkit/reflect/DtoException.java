package com.philips.dmis.swt.ui.toolkit.reflect;

public class DtoException extends RuntimeException {
    public DtoException() {
    }

    public DtoException(String message) {
        super(message);
    }

    public DtoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DtoException(Throwable cause) {
        super(cause);
    }
}
