package br.mahlow.message.deliverer.server.rest.resources.notification;

import br.mahlow.message.deliverer.api.handler.MessageHandler;
import br.mahlow.message.deliverer.server.exception.rest.HandlerNotFound;
import br.mahlow.message.deliverer.server.producer.context.ContextHolder;
import br.mahlow.message.deliverer.server.provider.manager.ProviderManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static java.util.Objects.isNull;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationController {

    @Inject
    private NotificationInstanceController notificationInstanceController;

    @Inject
    private ContextHolder contextHolder;

    @Inject
    private ProviderManager providerManager;

    @Path("{id}")
    public NotificationInstanceController notificationInstanceController(@NotNull @Size(min = 1) @PathParam("id") String id) {
        MessageHandler instance = providerManager.getInstance(MessageHandler.class, id);

        if (isNull(instance))
            throw new HandlerNotFound(String.format("Handler not found for id %s", id));

        contextHolder.put(MessageHandler.class, instance);

        return notificationInstanceController;
    }
}
