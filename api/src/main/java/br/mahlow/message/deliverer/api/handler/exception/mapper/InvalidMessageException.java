package br.mahlow.message.deliverer.api.handler.exception.mapper;

import br.mahlow.message.deliverer.api.handler.exception.handler.HandlerException;

public class InvalidMessageException extends HandlerException {

    private static final long serialVersionUID = -2468771554870032283L;

    public InvalidMessageException() {
    }

    public InvalidMessageException(String message) {
        super(message);
    }

    public InvalidMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMessageException(Throwable cause) {
        super(cause);
    }

    public InvalidMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
