package br.mahlow.message.deliverer.core.exception.provider.handler;

import br.mahlow.message.deliverer.api.handler.exception.HandlerException;

public class InvalidHandlerException extends HandlerException {

    private static final long serialVersionUID = -1829750409677705274L;

    public InvalidHandlerException() {
    }

    public InvalidHandlerException(String message) {
        super(message);
    }

    public InvalidHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidHandlerException(Throwable cause) {
        super(cause);
    }

    public InvalidHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
