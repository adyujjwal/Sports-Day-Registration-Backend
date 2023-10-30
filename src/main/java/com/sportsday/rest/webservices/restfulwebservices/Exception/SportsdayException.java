package com.sportsday.rest.webservices.restfulwebservices.Exception;

public class SportsdayException extends RuntimeException {

    public SportsdayException() {
        super();
    }

    public SportsdayException(String message) {
        super(message);
    }

    public SportsdayException(String message, Throwable cause) {
        super(message, cause);
    }
}

