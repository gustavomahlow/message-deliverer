package br.mahlow.message.deliverer.server.rest.mapper;

import br.mahlow.message.deliverer.server.exception.rest.RestException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Objects;

@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    private JsonObject buildResponse(String message) {
        return Json.createObjectBuilder()
                .add("message", message)
                .add("success", false)
                .build();
    }

    @Override
    public Response toResponse(RestException exception) {
        Response.Status status = exception.getResponseStatus();
        Throwable cause = exception.getCause();

        if (Objects.isNull(cause))
            return Response.status(status).build();

        return Response.status(status).entity(buildResponse(cause.getMessage())).build();
    }
}
