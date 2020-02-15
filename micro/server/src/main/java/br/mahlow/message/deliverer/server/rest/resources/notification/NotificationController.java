package br.mahlow.message.deliverer.server.rest.resources.notification;

import br.mahlow.message.deliverer.server.business.handler.MessageHandlerBusiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class NotificationController {

    @Inject
    private NotificationInstanceController notificationInstanceController;

    @Inject
    private MessageHandlerBusiness messageHandlerBusiness;

    @Path("{id}")
    public NotificationInstanceController notificationInstanceController(@NotNull @Size(min = 1) @PathParam("id") String id) {
        messageHandlerBusiness.addMessageHandlerContext(id);

        return notificationInstanceController;
    }

    @GET
    public void listMessageHandlerInstances(@Suspended AsyncResponse asyncResponse) {
        asyncResponse.resume(Response.ok(messageHandlerBusiness.listMessageHandlerInstancesResponse()).build());
    }
}
