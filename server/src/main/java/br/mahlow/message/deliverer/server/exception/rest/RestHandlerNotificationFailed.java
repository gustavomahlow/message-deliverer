package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class RestHandlerNotificationFailed extends RestException {

    private static final long serialVersionUID = -6801583424416789720L;

    public RestHandlerNotificationFailed() {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

    public RestHandlerNotificationFailed(String message) {
        super(message, Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

    public RestHandlerNotificationFailed(String message, Throwable cause) {
        super(message, cause, Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }
}
