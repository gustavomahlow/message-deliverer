package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class HandlerNotFound extends RestException {

    private static final long serialVersionUID = 6172601579004076518L;

    public HandlerNotFound() {
    }

    public HandlerNotFound(Throwable cause) {
        super(cause);
    }

    public HandlerNotFound(String message) {
        super(message);
    }

    public HandlerNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Response.Status getResponseStatus() {
        return Response.Status.NOT_FOUND;
    }
}
