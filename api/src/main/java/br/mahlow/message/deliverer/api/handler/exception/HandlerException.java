package br.mahlow.message.deliverer.api.handler.exception;

public class HandlerException extends Exception {

    private static final long serialVersionUID = 3828388151228447267L;

    public HandlerException() {
    }

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerException(Throwable cause) {
        super(cause);
    }

    public HandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
