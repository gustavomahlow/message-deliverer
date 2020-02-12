package br.mahlow.message.deliverer.server.exception.rest;

import javax.ws.rs.core.Response;

public class InvalidMessage extends RestException {

    private static final long serialVersionUID = 128722818547930367L;

    public InvalidMessage() {
        super(Response.status(Response.Status.BAD_REQUEST).build());
    }

    public InvalidMessage(String message) {
        super(message, Response.status(Response.Status.BAD_REQUEST).build());
    }

    public InvalidMessage(String message, Throwable cause) {
        super(message, cause, Response.status(Response.Status.BAD_REQUEST).build());
    }
}
