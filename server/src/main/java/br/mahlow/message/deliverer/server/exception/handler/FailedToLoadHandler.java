package br.mahlow.message.deliverer.server.exception.handler;

import br.mahlow.message.deliverer.api.handler.exception.HandlerException;

public class FailedToLoadHandler extends HandlerException {

    private static final long serialVersionUID = 6639475568669389098L;

    public FailedToLoadHandler() {
    }

    public FailedToLoadHandler(String message) {
        super(message);
    }

    public FailedToLoadHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedToLoadHandler(Throwable cause) {
        super(cause);
    }

    public FailedToLoadHandler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
