package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class HandlerNotificationFailed extends RestException {

    private static final long serialVersionUID = -5671466619744594337L;

    public HandlerNotificationFailed() {
    }

    public HandlerNotificationFailed(Throwable cause) {
        super(cause);
    }

    public HandlerNotificationFailed(String message) {
        super(message);
    }

    public HandlerNotificationFailed(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Response.Status getResponseStatus() {
        return Response.Status.INTERNAL_SERVER_ERROR;
    }
}
