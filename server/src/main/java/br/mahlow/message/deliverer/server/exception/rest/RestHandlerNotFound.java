package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class RestHandlerNotFound extends RestException {

    private static final long serialVersionUID = 7298708817268070315L;

    public RestHandlerNotFound(String message) {
        super(message, Response.status(Response.Status.NOT_FOUND).build());
    }

    public RestHandlerNotFound(String message, Throwable cause) {
        super(message, cause, Response.status(Response.Status.NOT_FOUND).build());
    }

    public RestHandlerNotFound() {
        super(Response.status(Response.Status.NOT_FOUND).build());
    }
}
