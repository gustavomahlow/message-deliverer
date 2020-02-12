package br.mahlow.message.deliverer.auth.rest.resources;

import io.helidon.security.SecurityContext;
import io.helidon.security.annotations.Authenticated;
import io.helidon.security.annotations.Authorized;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @Inject
    private SecurityContext securityContext;

    @GET
    @Authenticated
    @Authorized
    @RolesAllowed("ROLE_ADMIN")
    public void test(@Suspended AsyncResponse asyncResponse) {
        asyncResponse.resume(Response.ok().build());
    }
}
