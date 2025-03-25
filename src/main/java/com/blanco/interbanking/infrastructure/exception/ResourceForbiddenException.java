package com.blanco.interbanking.infrastructure.exception;

public class ResourceForbiddenException extends RuntimeException {
    public ResourceForbiddenException(final String message) {
        super(message);
    }
}
