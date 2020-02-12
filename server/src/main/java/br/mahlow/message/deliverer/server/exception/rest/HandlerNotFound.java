package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class HandlerNotFound extends RestException {

    private static final long serialVersionUID = 7298708817268070315L;

    public HandlerNotFound(String message) {
        super(message, Response.status(Response.Status.NOT_FOUND).build());
    }

    public HandlerNotFound(String message, Throwable cause) {
        super(message, cause, Response.status(Response.Status.NOT_FOUND).build());
    }

    public HandlerNotFound() {
        super(Response.status(Response.Status.NOT_FOUND).build());
    }
}
