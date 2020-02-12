package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class HandlerNotificationFailed extends RestException {

    private static final long serialVersionUID = -6801583424416789720L;

    public HandlerNotificationFailed() {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

    public HandlerNotificationFailed(String message) {
        super(message, Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

    public HandlerNotificationFailed(String message, Throwable cause) {
        super(message, cause, Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }
}
