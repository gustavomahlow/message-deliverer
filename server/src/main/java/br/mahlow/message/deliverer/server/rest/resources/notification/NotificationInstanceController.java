package br.mahlow.message.deliverer.server.rest.resources.notification;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.api.handler.exception.HandlerNotificationFailed;
import br.mahlow.message.deliverer.server.exception.rest.RestHandlerNotificationFailed;
import br.mahlow.message.deliverer.server.producer.context.ContextHolder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationInstanceController {

    @Inject
    private ContextHolder contextHolder;

    @POST
    public void publishMessage(@Suspended AsyncResponse asyncResponse) {
        MessageHandler handler = contextHolder.get(MessageHandler.class);

        try {
            handler.onNotification();
        } catch (HandlerNotificationFailed e) {
            throw new RestHandlerNotificationFailed("Failed to post notification", e);
        }

        asyncResponse.resume(Response.ok().build());
    }
}
