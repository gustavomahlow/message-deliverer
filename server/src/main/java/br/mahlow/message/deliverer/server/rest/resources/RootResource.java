package br.mahlow.message.deliverer.server.rest.resources;

import br.mahlow.message.deliverer.server.rest.resources.notification.NotificationController;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

    @Inject
    private NotificationController notificationController;

    @Path("notification")
    public NotificationController notificationController() {
        return notificationController;
    }
}
