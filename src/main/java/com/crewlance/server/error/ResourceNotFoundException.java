package com.crewlance.server.error;

public class ResourceNotFoundException extends SystemException {

    private static final long serialVersionUID = 9136599413963419229L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}