package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class InvalidMessage extends RestException {

    private static final long serialVersionUID = -3230472287794613815L;

    public InvalidMessage() {
    }

    public InvalidMessage(Throwable cause) {
        super(cause);
    }

    public InvalidMessage(String message) {
        super(message);
    }

    public InvalidMessage(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public Response.Status getResponseStatus() {
        return Response.Status.BAD_REQUEST;
    }
}
