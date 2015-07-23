package com.epam.ot.writer;

public class WriterException extends RuntimeException {
    public WriterException() {
        super();
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }
}
