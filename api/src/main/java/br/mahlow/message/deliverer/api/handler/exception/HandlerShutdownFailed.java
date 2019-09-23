package br.mahlow.message.deliverer.api.handler.exception;

public class HandlerShutdownFailed extends HandlerException {

    private static final long serialVersionUID = 2428935684089783325L;

    public HandlerShutdownFailed() {
    }

    public HandlerShutdownFailed(String message) {
        super(message);
    }

    public HandlerShutdownFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerShutdownFailed(Throwable cause) {
        super(cause);
    }

    public HandlerShutdownFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
