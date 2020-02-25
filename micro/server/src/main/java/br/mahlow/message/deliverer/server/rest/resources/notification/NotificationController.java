package br.mahlow.message.deliverer.server.rest.resources.notification;

import br.mahlow.message.deliverer.server.business.handler.MessageHandlerBusiness;
import br.mahlow.message.deliverer.server.business.notification.NotificationBusiness;
import br.mahlow.message.deliverer.server.pojo.handler.batch.BatchMessagePOJO;
import br.mahlow.message.deliverer.server.pojo.notification.NotificationPOJO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class NotificationController {

    @Inject
    private NotificationInstanceController notificationInstanceController;

    @Inject
    private MessageHandlerBusiness messageHandlerBusiness;

    @Inject
    private NotificationBusiness notificationBusiness;

    @Path("{id}")
    public NotificationInstanceController notificationInstanceController(@NotNull @Size(min = 1) @PathParam("id") String id) {
        messageHandlerBusiness.addMessageHandlerContext(id);

        return notificationInstanceController;
    }

    @GET
    public void listMessageHandlerInstances(@Suspended AsyncResponse asyncResponse) {
        asyncResponse.resume(Response.ok(messageHandlerBusiness.listMessageHandlerInstancesResponse()).build());
    }

    @POST
    @Path("batch")
    public void batchMessage(@NotNull @Valid BatchMessagePOJO messages,
                             @QueryParam("ignore_errors") @DefaultValue("false") Boolean ignoreErrors,
                             @Suspended AsyncResponse asyncResponse) {
        List<NotificationPOJO> notifications = messageHandlerBusiness.batchMessage(messages.getMessages());

        List<Object> result = notificationBusiness.sendNotificationBatch(notifications, ignoreErrors);

        asyncResponse.resume(Response.ok(result).build());
    }
}
