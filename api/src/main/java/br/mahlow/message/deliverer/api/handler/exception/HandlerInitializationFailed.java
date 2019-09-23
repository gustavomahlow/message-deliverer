package br.mahlow.message.deliverer.api.handler.exception;

public class HandlerInitializationFailed extends HandlerException {

    private static final long serialVersionUID = -315831130222504232L;

    public HandlerInitializationFailed() {
    }

    public HandlerInitializationFailed(String message) {
        super(message);
    }

    public HandlerInitializationFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerInitializationFailed(Throwable cause) {
        super(cause);
    }

    public HandlerInitializationFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
