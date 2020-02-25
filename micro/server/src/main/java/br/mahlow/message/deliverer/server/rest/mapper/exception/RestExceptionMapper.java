package br.mahlow.message.deliverer.server.rest.mapper.exception;

import br.mahlow.message.deliverer.api.handler.exception.mapper.InvalidMessageException;
import br.mahlow.message.deliverer.server.exception.rest.RestException;
import br.mahlow.message.deliverer.server.rest.mapper.BaseMessageMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static java.util.Objects.isNull;

@ApplicationScoped
public class RestExceptionMapper implements BaseMessageMapper<RestException> {

    private JsonObject buildResponse(String message, Response.Status status) {
        JsonObjectBuilder result = Json.createObjectBuilder()
                .add("status", status.getStatusCode())
                .add("success", false);

        if (!isNull(message))
            result.add("message", message);

        return result.build();
    }

    @Override
    public JsonObject toJsonBatch(Collection<RestException> messages) {
        return null;
    }

    @Override
    public JsonObject toJson(RestException message) {
        Response.Status status = message.getResponseStatus();
        Throwable cause = message.getCause();

        return buildResponse(cause.getMessage(), status);
    }

    @Override
    public RestException fromJson(JsonObject jsonObject) throws InvalidMessageException {
        return null;
    }
}
