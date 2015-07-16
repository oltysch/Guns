package com.epam.ot.db;

public class ConnPoolEx extends RuntimeException {
    public ConnPoolEx() {
        super();
    }

    public ConnPoolEx(String message) {
        super(message);
    }

    public ConnPoolEx(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnPoolEx(Throwable cause) {
        super(cause);
    }
}
