package br.mahlow.message.deliverer.server.rest.resources.provider.exception;

import br.mahlow.message.deliverer.server.exception.rest.RestException;
import br.mahlow.message.deliverer.server.rest.mapper.exception.RestExceptionMapper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Dependent
public class GeneralRestExceptionMapper implements ExceptionMapper<RestException> {

    @Inject
    private RestExceptionMapper restExceptionMapper;

    @Override
    public Response toResponse(RestException exception) {
        Response.Status status = exception.getResponseStatus();

        return Response.status(status).entity(restExceptionMapper.toJson(exception)).build();
    }
}
