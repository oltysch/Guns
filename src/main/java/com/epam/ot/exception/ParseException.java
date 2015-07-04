package com.epam.ot.exception;

/**
 * Created by Admin on 04.07.2015.
 */
public class ParseException extends RuntimeException {
    public ParseException(Exception e) {
        super(e);
    }
}
