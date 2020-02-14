package br.mahlow.message.deliverer.api.rest.client.resources.notification;

import br.mahlow.message.deliverer.api.rest.client.resources.notification.instance.NotificationInstanceResource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public interface NotificationResource {

    @Path("{id}")
    NotificationInstanceResource notificationInstanceController(@PathParam("id") String id);
}
