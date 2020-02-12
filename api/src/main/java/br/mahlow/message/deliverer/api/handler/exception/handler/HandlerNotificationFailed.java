package br.mahlow.message.deliverer.api.handler.exception.handler;

public class HandlerNotificationFailed extends HandlerException {

    private static final long serialVersionUID = 312835380777821426L;

    public HandlerNotificationFailed() {
    }

    public HandlerNotificationFailed(String message) {
        super(message);
    }

    public HandlerNotificationFailed(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerNotificationFailed(Throwable cause) {
        super(cause);
    }

    public HandlerNotificationFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
