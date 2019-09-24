package br.mahlow.message.deliverer.server.resources;

import br.mahlow.message.deliverer.core.exception.provider.FailedToInitializeProvider;
import br.mahlow.message.deliverer.core.provider.manager.ProviderManager;

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
import java.util.HashMap;


@Path("/")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @Inject
    private ProviderManager providerManager;

    @GET
    public void test(@Suspended AsyncResponse asyncResponse) {
        asyncResponse.resume(
                Response.ok(
                        new HashMap<String, String>() {
                            private static final long serialVersionUID = -1934755094298451304L;

                            {
                                put("teste", "oi");
                            }
                        }
                ).build()
        );
    }
}
