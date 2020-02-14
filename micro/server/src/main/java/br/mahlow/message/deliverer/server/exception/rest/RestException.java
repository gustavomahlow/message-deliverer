package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public abstract class RestException extends WebApplicationException {

    private static final long serialVersionUID = -8759658643888405062L;

    public RestException() {
        super(Response.ok().build());
    }

    public RestException(Throwable cause) {
        super(cause);
    }

    public RestException(String message) {
        super(message, Response.ok().build());
    }

    public RestException(String message, Throwable cause) {
        super(message, cause, Response.ok().build());
    }

    public abstract Response.Status getResponseStatus();
}
