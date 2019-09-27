package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public abstract class RestException extends WebApplicationException {

    private static final long serialVersionUID = -8759658643888405062L;

    public RestException(Response response) {
        super(response);
    }

    public RestException(String message, Response response) {
        super(message, response);
    }

    public RestException(String message, Throwable cause, Response response) {
        super(message, cause, response);
    }
}
