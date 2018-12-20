package com.crewlance.server.error;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1723326475395184680L;

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    SystemException(String message) {
        super(message);
    }
}